package in.reqres.pojo.registration;

public class SuccessUserRegister extends Register  {
    private Integer id;
    private String token;



    public SuccessUserRegister() {
        super();
    }

    public SuccessUserRegister(String email,
                               String password,
                               Integer id,
                               String token) {
        super(email,password);
        this.id = id;
        this.token = token;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setEmail(email);
    }

    public String getPassword(){
        return super.getPassword();
    }

    public void setPassword(String password){
        super.setPassword(password);
    }
}
