package com.acds.letsshare;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Friends")

public class Friends extends ParseObject{
    public Friends(){}

    public void setFriend1(ParseUser friend1) {
        put("friend1", friend1);
    }

    public ParseObject getFriend1() {
        return getParseObject("friend1");
    }

    public void setFriend2(ParseUser friend2) {
        put("friend2", friend2);
    }

    public ParseObject getFriend2() { return getParseObject("friend2"); }
}