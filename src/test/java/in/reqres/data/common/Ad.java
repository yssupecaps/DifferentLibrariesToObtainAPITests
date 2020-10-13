package in.reqres.data.common;

public class Ad {
    private String company;
    private String url;
    private String text;

    public Ad() {
        super();
    }

    public Ad(String company,
              String url,
              String text) {
        this.company = company;
        this.url = url;
        this.text = text;
    }

    public String getCompany() {
        return company;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText(String text) {
        this.text = text;
    }
}
