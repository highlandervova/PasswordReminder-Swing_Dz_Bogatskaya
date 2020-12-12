package data;

public class Record {
    private String login;
    private String password;
    private String resource;

    public Record() {
    }

    public Record(String login, String password, String resource) {
        this.login = login;
        this.password = password;
        this.resource = resource;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString(){
        return "Login is: " + this.login + ", password is: " + this.password + ", resource is: " + this.resource;
    }
}
