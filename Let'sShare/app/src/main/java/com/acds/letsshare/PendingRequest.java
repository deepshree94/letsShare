package com.acds.letsshare;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("PendingRequest")

public class PendingRequest extends ParseObject {
    public PendingRequest() {
    }

    public void setSender(ParseUser sender) {
        put("sender", sender);
    }

    public ParseObject getSender() {
        return getParseObject("sender");
    }

    public void setReceiver(ParseUser receiver) {
        put("receiver", receiver);
    }

    public ParseObject getReceiver() { return getParseObject("receiver"); }

}