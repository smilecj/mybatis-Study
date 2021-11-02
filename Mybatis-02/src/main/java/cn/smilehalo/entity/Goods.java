package cn.smilehalo.entity;

public class Goods {
    private Integer goodsId; //商品编号
    private String title;       //标题
    private String subTitle;    //子标题
    private Float originalCost;  //原始价格
    private Float currentPrice;   //当前价格
    private Float discount;     //则扣率
    private Integer isFreeDelivery;   //是否包邮，1-包邮，0-不包
    private Integer categoryId;  // 分类编号

    public Goods() {
    }

    public Goods(Integer goodsId, String title, String subTitle,
                 Float originalCost, Float currentPrice, Float discount,
                 Integer isFreeDelivery, Integer categoryId) {
        this.goodsId = goodsId;
        this.title = title;
        this.subTitle = subTitle;
        this.originalCost = originalCost;
        this.currentPrice = currentPrice;
        this.discount = discount;
        this.isFreeDelivery = isFreeDelivery;
        this.categoryId = categoryId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Float getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Float originalCost) {
        this.originalCost = originalCost;
    }

    public Float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getIsFreeDelivery() {
        return isFreeDelivery;
    }

    public void setIsFreeDelivery(Integer isFreeDelivery) {
        this.isFreeDelivery = isFreeDelivery;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", originalCost=" + originalCost +
                ", currentPrice=" + currentPrice +
                ", discount=" + discount +
                ", isFreeDelivery=" + isFreeDelivery +
                ", categoryId=" + categoryId +
                '}';
    }
}
