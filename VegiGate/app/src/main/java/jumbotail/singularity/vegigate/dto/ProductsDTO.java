package jumbotail.singularity.vegigate.dto;

import java.io.Serializable;

/**
 * Created by sathish on 30/4/16.
 */
public class ProductsDTO implements Serializable {

    private String name;
    private String qty;
    private String stock;
    private String amount;
    private String image_url;

    public String getName() { return name; }
    public String getQty() { return qty; }
    public String getStock() { return stock; }
    public String getAmount() { return amount; }
    public String getImage_url() { return image_url; }

    public void setName(String name){ this.name = name; }
    public void setQty(String qty){ this.qty = qty; }
    public void setStock(String stock){ this.stock = stock; }
    public void setAmount(String amount){ this.amount = amount; }
    public void setImage_url(String image_url){ this.image_url = image_url; }


}
