package com.Pineapple.Dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order_has_computer implements Serializable{
	private static final long serialVersionUID = 1L;
    private String id_order;
    private String id_computer;
    private int num;
    public String getIDOrder() {
        return id_order;
    }
    public void setIDOrder(String id_order) {
        this.id_order = id_order;
    }
    public String getIDComputer() {
        return id_computer;
    }
    public void setIDComputer(String id_computer) {
        this.id_computer = id_computer;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

}
