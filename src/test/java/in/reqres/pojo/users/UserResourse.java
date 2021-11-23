package in.reqres.pojo.users;

import in.reqres.pojo.common.Ad;

import java.util.List;

public class UserResourse {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    List<UserData> dataUser;
    private Ad ad;

    public UserResourse() {
        super();
    }

    public UserResourse(Integer page,
                        Integer per_page,
                        Integer total,
                        Integer total_pages,
                        List<UserData> dataUser,
                        Ad ad) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.dataUser = dataUser;
        this.ad = ad;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<UserData> getData() {
        return dataUser;
    }

    public void setData(List<UserData> data) {
        this.dataUser = data;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
