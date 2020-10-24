package com.haytech.haytechstyles.plaque;

public class ModelPlaque {
    private String txtPart1="";
    private String txtPart2="";
    private String txtPart3="";
    private String txtPart4="";

    public ModelPlaque(String txtPart1, String txtPart2, String txtPart3, String txtPart4) {
        this.txtPart1 = txtPart1;
        this.txtPart2 = txtPart2;
        this.txtPart3 = txtPart3;
        this.txtPart4 = txtPart4;
    }

    public String getTxtPart1() {
        return txtPart1;
    }

    public String getTxtPart2() {
        return txtPart2;
    }

    public String getTxtPart3() {
        return txtPart3;
    }

    public String getTxtPart4() {
        return txtPart4;
    }
}
