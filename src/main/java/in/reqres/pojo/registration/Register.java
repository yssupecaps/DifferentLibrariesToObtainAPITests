package in.reqres.pojo.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.reqres.pojo.common.ReqresRequest;

public class Register implements ReqresRequest {

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public Register() {
        super();
    }

    public Register(String email,
                    String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
