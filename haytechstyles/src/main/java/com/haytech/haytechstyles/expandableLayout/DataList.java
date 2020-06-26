package com.haytech.haytechstyles.expandableLayout;

import java.util.ArrayList;
import java.util.List;

public class DataList {

    private String name;
    private String famil;
    private Boolean isExpended = false;

    public DataList() {
    }

    public DataList(String name, String famil, Boolean isExpended) {
        this.name = name;
        this.famil = famil;
        this.isExpended = isExpended;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamil() {
        return famil;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public Boolean getExpended() {
        return isExpended;
    }

    public void setExpended(Boolean expended) {
        isExpended = expended;
    }

    static List<DataList> lists;

    public static List<DataList> getListData() {
        if (lists == null)
            lists = new ArrayList<>();
        lists.add(new DataList("Eisa", "Yousefi", false));
        lists.add(new DataList("Mosa", "abdi", false));
        lists.add(new DataList("Broa", "Yousefi", false));
        lists.add(new DataList("Majid", "Hasani", false));
        lists.add(new DataList("Ali", "Alavi", false));
        lists.add(new DataList("Eisa", "Yousefi", false));
        lists.add(new DataList("Mosa", "abdi", false));
        lists.add(new DataList("Broa", "Yousefi", false));
        lists.add(new DataList("Majid", "Hasani", false));
        lists.add(new DataList("Ali", "Alavi", false));
        return lists;
    }
}
