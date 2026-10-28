
package client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {

    private String email;
    private String password;
    private String name;
}