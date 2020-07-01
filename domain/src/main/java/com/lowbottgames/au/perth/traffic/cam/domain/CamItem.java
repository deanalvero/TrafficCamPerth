package com.lowbottgames.au.perth.traffic.cam.domain;

public class CamItem {

    public String camName;
    public String camID;
    public String camDirection;
    public String camInfo;

    public CamItem(String camID, String camName, String camDirection, String camInfo){
        this.camID = camID;
        this.camName = camName;
        this.camDirection = camDirection;
        this.camInfo = camInfo;
    }

}
