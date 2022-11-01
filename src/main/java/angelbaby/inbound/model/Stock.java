package angelbaby.inbound.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class Stock {

    private Long stockID;
    private Date expire;
    private int quantity;
    private Product item;

    public Stock(Long stockID, Date expire, int quantity, Product item) {
        this.stockID = stockID;
        this.expire = expire;
        this.quantity = quantity;
        this.item = item;
    }

    @Override
    public String toString() {
        return "{" +
            "stockID:" + stockID +
            ", expire:" + new SimpleDateFormat("dd/MM/yyyy").format(expire) +
            ", quantity:" + quantity +
            ", item:" + item +
            '}';
    }
}
