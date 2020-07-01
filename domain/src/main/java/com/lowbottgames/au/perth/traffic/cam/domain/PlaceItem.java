package com.lowbottgames.au.perth.traffic.cam.domain;

public class PlaceItem {

    public String placeName;
    public CamItem[] camItem;

    public PlaceItem(String placeName, CamItem[] camItem) {
        this.placeName = placeName;

        this.camItem = camItem;
    }

    @Override
    public String toString() {
        return placeName;
    }
}
