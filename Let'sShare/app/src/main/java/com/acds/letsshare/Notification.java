package com.acds.letsshare;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Notification")

public class Notification extends ParseObject {
    public Notification() {
    }

    public void setUser(ParseUser user) {
        put("user", user);
    }

    public ParseObject getUser() {
        return getParseObject("user");
    }

    public void setInformation(String information) {
        put("information", information);
    }

    public String getInformation() { return getString("information"); }

}