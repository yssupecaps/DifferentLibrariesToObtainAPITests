package in.reqres.pojo.registration;

public class UnsuccessUserRegister extends Register {

    private String error;

    public UnsuccessUserRegister(String error,
                                 String email,
                                 String password) {
        super(email,password);
        this.error = error;
    }

    public UnsuccessUserRegister() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error){
        this.error = error;

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
