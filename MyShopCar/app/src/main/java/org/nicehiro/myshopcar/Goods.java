package org.nicehiro.myshopcar;

/**
 * Created by hiro on 16-11-17.
 */

public class Goods {

    private String id;
    private String desc;
    private float price;
    private String head_link;
    private int remain;
    private String time;

    public Goods(String id, String desc, float price, String head_link, int remain, String time) {
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.head_link = head_link;
        this.remain = remain;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", head_link='" + head_link + '\'' +
                ", remain=" + remain +
                ", time='" + time + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public float getPrice() {
        return price;
    }

    public String getHead_link() {
        return head_link;
    }

    public int getRemain() {
        return remain;
    }

    public String getTime() {
        return time;
    }
}
