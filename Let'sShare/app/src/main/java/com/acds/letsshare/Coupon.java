package com.acds.letsshare;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Coupon")

public class Coupon extends ParseObject{
    public Coupon(){}

    public String getServiceProvider(){

        return getString("serviceProvider");
    }

    public void setServiceProvider(String serviceProvider) {
        put("serviceProvider", serviceProvider);
    }

    public void setOwner(ParseUser owner) {
        put("owner", owner);
    }

    public ParseObject getOwner(){ return getParseObject("owner"); }

}