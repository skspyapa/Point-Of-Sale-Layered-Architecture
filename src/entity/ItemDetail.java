package entity;

public class ItemDetail extends SuperEntity{
    private ItemDetailPK itemdetailPK;
    private int qty;
    private double unitPrice;

    public ItemDetail() {
    }

    public ItemDetail(ItemDetailPK itemdetailPK, int qty, double unitPrice) {
        this.itemdetailPK = itemdetailPK;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public ItemDetail(String orderId, String itemCode, int qty, double unitPrice) {
        this.itemdetailPK = new ItemDetailPK(orderId, itemCode);
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public ItemDetailPK getItemdetailPK() {
        return itemdetailPK;
    }

    public void setItemdetailPK(ItemDetailPK itemdetailPK) {
        this.itemdetailPK = itemdetailPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "ItemDetailPK=" + itemdetailPK +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
