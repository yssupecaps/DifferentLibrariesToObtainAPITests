package in.reqres.pojo.colors;

import in.reqres.pojo.common.Ad;

import java.util.List;

public class Resource {

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    List<Data> data;
    private Ad ad;

    public Resource() {
        super();
    }

    public Resource(Integer page,
                    Integer per_page,
                    Integer total, Integer total_pages,
                    List<Data> data,
                    Ad ad) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
