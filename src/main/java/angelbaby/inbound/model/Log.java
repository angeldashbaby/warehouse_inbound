package angelbaby.inbound.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class Log {

    private Long logID;
    private String type;
    private Date productInDate;
    private Date productOutDate;
    private int IOQuantity;
    private int totalQuantity;
    private Stock stock;
    private User user;

    @Override
    public String toString() {
        String inDate = null;
        String outDate = null;
        if (productInDate != null) {
            inDate =  new SimpleDateFormat("dd/MM/yyyy").format(productInDate);
        }
        if (productOutDate != null) {
            outDate =  new SimpleDateFormat("dd/MM/yyyy").format(productOutDate);
        }
        return "{" +
            "logID:" + logID +
            ", type:" + type +
            ", productInDate:" + inDate +
            ", productOutDate:" + outDate +
            ", IOQuantity:" + IOQuantity +
            ", totalQuantity:" + totalQuantity +
            ", stock:" + stock.toString() +
            ", user:" + user.toString() +
            '}';
    }

    public Log(Long logID, String type, Date productInDate, Date productOutDate, int IOQuantity, int totalQuantity, Stock stock, User user) {
        this.logID = logID;
        this.type = type;
        this.productInDate = productInDate;
        this.productOutDate = productOutDate;
        this.IOQuantity = IOQuantity;
        this.totalQuantity = totalQuantity;
        this.stock = stock;
        this.user = user;
    }
}
