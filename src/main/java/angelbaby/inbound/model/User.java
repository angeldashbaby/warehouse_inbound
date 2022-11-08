package angelbaby.inbound.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Long userID;
    private String username;
    private String password;

    public User(Long userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
            "userID:" + userID +
            ", username:" + username +
            ", password:" + password +
            '}';
    }
}
