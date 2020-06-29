package com.haytech.haytechstyles.expandableLayout;

import java.util.ArrayList;
import java.util.List;

public class DataList {

    private String expertName;
    private String inquiryNumber;
    private String date;
    private Boolean isExpended = false;

    public DataList() {
    }

    public DataList(String expertName, String inquiryNumber, Boolean isExpended) {
        this.expertName = expertName;
        this.inquiryNumber = inquiryNumber;
        this.isExpended = isExpended;

    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getInquiryNumber() {
        return inquiryNumber;
    }

    public void setInquiryNumber(String inquiryNumber) {
        this.inquiryNumber = inquiryNumber;
    }

    public Boolean getExpended() {
        return isExpended;
    }

    public void setExpended(Boolean expended) {
        isExpended = expended;
    }

    public static List<DataList> getLists() {
        return lists;
    }

    public static void setLists(List<DataList> lists) {
        DataList.lists = lists;
    }

    static List<DataList> lists;

    public static List<DataList> getListData() {
        if (lists == null)
            lists = new ArrayList<>();
        lists.add(new DataList("عیسی یوسفی", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("مهندس جانباز", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("مجید حسنی", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("وریا مرادی", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("بروا یوسفی", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("عیسی یوسفی", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("مهندس جانباز", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("مجید حسنی", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("وریا مرادی", "1398 / 11201/05 / 1008 / 244", false));
        lists.add(new DataList("بروا یوسفی", "1398 / 11201/05 / 1008 / 244", false));
        return lists;
    }
}
