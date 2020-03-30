package order.gas;

public class GasItem {
    String thumbnail;
    String name;
    String price;
    String sale;
    String status;

    public GasItem(String thumbnail, String name, String price, String sale, String status) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
