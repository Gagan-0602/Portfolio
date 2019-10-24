package com.gagankaushal.portfolio;

public class RateM {
    String id;

    String rate;
    String msg;

    public RateM(String id, String rate,String msg)
    {
        this.id=id;
        this.rate=rate;
        this.msg=msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
