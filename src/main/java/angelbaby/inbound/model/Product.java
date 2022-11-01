package angelbaby.inbound.model;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Product {

    private Long itemID;
    private String name;
    private String description;
    private String supplierName;

    public Product(Long itemID, String name, String description, String supplierName) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "{" +
            "itemID:" + itemID +
            ", name:" + name +
            ", description:" + description +
            ", supplierName:" + supplierName +
            '}';
    }
}
