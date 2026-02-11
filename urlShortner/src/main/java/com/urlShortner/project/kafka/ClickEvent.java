package com.urlShortner.project.kafka;

public class ClickEvent {

    private String shortCode;
    private String userIp;
    private long timestamp;

    public ClickEvent(){

    }

    public ClickEvent(String shortCode,String userIp,long timestamp){
        this.shortCode=shortCode;
        this.userIp=userIp;
        this.timestamp=timestamp;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
