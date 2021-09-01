package bsu.edummf.task12.entity;

public class Item implements Cloneable {
    private String name;
    private int price;
    private long id;

    public Item(String name, int price, long id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("name='").append(name).append('\'');
        sb.append(", minPrice=").append(price);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
