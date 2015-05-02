package com.acds.letsshare;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("CouponRequest")

public class CouponRequest extends ParseObject {
    public CouponRequest() {
    }

    public void setCoupon(Coupon coupon) { put("coupon", coupon); }

    public ParseObject getCoupon() { return getParseObject("coupon"); }

    public void setSender(ParseUser sender) { put("sender", sender); }

    public ParseObject getSender () { return  getParseObject("sender"); }

    public void setReceiver(ParseUser receiver) { put("receiver", receiver); }

    public ParseObject getReceiver () { return  getParseObject("receiver"); }

}