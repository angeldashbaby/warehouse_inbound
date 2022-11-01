package angelbaby.inbound.model;

import java.util.ArrayList;

public class LogList {
    private ArrayList<Log> list;

    public LogList() {
        list = new ArrayList<>();
    }

    public void addLog(Log log) {
        list.add(log);
    }

    @Override
    public String toString() {
        String result = "{";
        for (Log log : list) {
            result += log.toString() + ",";
        }
        return result + "}";
    }

    public Log getLast() {
        return list.get(list.size() - 1);
    }
}
