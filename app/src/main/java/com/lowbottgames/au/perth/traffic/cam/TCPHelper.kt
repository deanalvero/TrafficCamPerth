package com.lowbottgames.au.perth.traffic.cam

object TCPHelper {
    @JvmStatic
    fun getImageURLString(image: String): String {
        return "https://mrapps.mainroads.wa.gov.au/TrafficImages/$image.jpg"
    }
}