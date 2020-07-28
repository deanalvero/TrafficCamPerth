package com.lowbottgames.au.perth.traffic.cam.domain

class PlaceItem {

    val placeName: String
    val camItem: Array<CamItem>

    constructor(
        placeName: String,
        camItem: Array<CamItem>
    ) {
        this.placeName = placeName
        this.camItem = camItem
    }

}
