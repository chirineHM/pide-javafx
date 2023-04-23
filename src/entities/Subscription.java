package entities;

import java.sql.Date;

public class Subscription {
    private int id;
    private String description;
    private int price;
    private Date start_date;
    private Date end_date;
    private int period;
    private typeSub typeSubs;
    int type_sub_id;

    
    public Subscription() {
    }

    public Subscription(String description, int price, Date start_date, Date end_date, int period,int type_sub_id) {
        this.description = description;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.period = period;
    
        this.type_sub_id = type_sub_id;
    }

    public Subscription(int id, String description, int price, Date start_date, Date end_date, int period, typeSub typeSubs) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.period = period;
        this.typeSubs = typeSubs;
    }

    public Subscription(String description, int price, Date start_date, Date end_date, int period, typeSub typeSubs) {
        this.description = description;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.period = period;
        this.typeSubs = typeSubs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public typeSub getTypeSubs() {
        return typeSubs;
    }

    public void setTypeSubs(typeSub typeSubs) {
        this.typeSubs = typeSubs;
    }

    @Override
    public String toString() {
        return "Subscription{" + "id=" + id + ", description=" + description + ", price=" + price + ", start_date=" + start_date + ", end_date=" + end_date + ", period=" + period + ", typeSubs=" + typeSubs + '}';
    }
public int getType_sub_id() {
        return type_sub_id;
    }

    public void setType_sub_id(int type_sub_id) {
        this.type_sub_id = type_sub_id;
    }


}
