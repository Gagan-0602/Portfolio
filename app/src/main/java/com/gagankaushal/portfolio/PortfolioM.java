package com.gagankaushal.portfolio;

public class PortfolioM {

    String id;
String img;
String tittle;
String desc;


public PortfolioM(String id,String img,String tittle,String desc)
{

    this.id=id;
    this.img=img;
    this.tittle=tittle;
    this.desc=desc;

}

    public String getid() {
        return id;
    }

    public void setid(String userid) {
        this.id = userid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTittle() {
        return tittle;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
