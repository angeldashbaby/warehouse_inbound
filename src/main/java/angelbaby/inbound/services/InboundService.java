package angelbaby.inbound.services;

import angelbaby.inbound.model.*;
import angelbaby.inbound.security.JwtAccessTokenService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InboundService {

    @Autowired
    private JwtAccessTokenService jwtAccessTokenService;

    private String token = null;

    public String getAccessToken() {
        return jwtAccessTokenService.requestAccessToken();
    }


    private LogList getRequest(URL url) throws IOException, ParseException {
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer " + token);
        http.disconnect();
        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            sb.append(line+"\n");
        }
        br.close();

        LogList list = new LogList();
        JSONArray jsonArr = new JSONArray(sb.toString());
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            Date productInDate = null;
            Date productOutDate = null;
            if (!jsonObj.isNull("productInDate")) {
                productInDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("productInDate"));
            }
            if (!jsonObj.isNull("productOutDate")) {
                productOutDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("productOutDate"));
            }
            Log log = new Log(
                jsonObj.getLong("logID"),
                jsonObj.getString("type"),
                productInDate,
                productOutDate,
                jsonObj.getInt("ioquantity"),
                jsonObj.getInt("totalQuantity"),
                new Stock(
                    jsonObj.getJSONObject("stock").getLong("stockID"),
                    new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.getJSONObject("stock").get("expire")),
                    jsonObj.getJSONObject("stock").getInt("quantity"),
                    new Product(
                        jsonObj.getJSONObject("stock").getJSONObject("item").getLong("itemID"),
                        jsonObj.getJSONObject("stock").getJSONObject("item").getString("name"),
                        jsonObj.getJSONObject("stock").getJSONObject("item").getString("description"),
                        jsonObj.getJSONObject("stock").getJSONObject("item").getString("supplierName")
                    )
                ),
                new User(
                    jsonObj.getJSONObject("user").getLong("userID"),
                    jsonObj.getJSONObject("user").getString("username"),
                    jsonObj.getJSONObject("user").getString("password")
                )
            );
            if (log.getType().equals("inbound")) list.addLog(log);
            }
        return list;
    }

    public void postRequest(JSONObject jsonObject, URL url) throws IOException {
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer " + token);
        http.setRequestProperty("Content-Type", "application/json");

        String data = jsonObject.toString();

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }



    public LogList getInboundList() throws IOException, ParseException {
        token = getAccessToken();
        LogList list = getRequest(new URL("http://localhost:8095/api/log"));
        return list;
    }

    public String postInbound(String payload) throws IOException, ParseException {
        token = getAccessToken();
        JSONObject obj = new JSONObject(payload);

        postRequest(obj, new URL("http://localhost:8094/api/location"));

        LogList list = getRequest(new URL("http://localhost:8095/api/log"));

        return list.getLast().toString();
    }
}
