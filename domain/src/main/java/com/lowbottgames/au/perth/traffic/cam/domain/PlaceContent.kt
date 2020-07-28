package com.lowbottgames.au.perth.traffic.cam.domain

object PlaceContent {
    val items = arrayOf(
        PlaceItem(
            "Perth CBD",
            arrayOf(
                CamItem("CAM00183", "Hay St/Thomas St", "South West", "Hay St/Thomas St, Perth CBD"),
//                CamItem("WB185", "Riverside Dr/Barrack St", "", "Riverside Dr/Barrack St"),
                CamItem("CAM00194", "Hay St", "South", "Hay St, Perth CBD")
            )
        ),

        PlaceItem(
            "Joondalup",
            arrayOf(
                CamItem("CAM00080", "Hodges Dr", "North West", "Hodges Dr, Joondalup"),
                CamItem("CAM00082", "Shenton Ave", "South", "Shenton Ave, Joondalup"),
                CamItem("CAM00204", "Whitfords Ave", "North", "Whitfords Ave, Woodvale"),
                CamItem("CAM00205", "Ocean Reef Rd", "South", "Ocean Reef Rd, Joondalup")
            )
        ),
        PlaceItem(
            "Mitchell Freeway",
            arrayOf(
                CamItem("CAM00181", "West Coast Hwy/ Scarb Beach Rd", "North", "West Coast Hwy/ Scarb Beach Rd, Scarborough"),
                CamItem("CAM00195", "Loftus St/Leederville Pde", "East", "Loftus St/Leederville Pde, Leederville"),
                CamItem("CAM00196", "Vincent St", "South", "Vincent St, West Perth"),
                CamItem("CAM00197", "Hutton St", "South", "Hutton St, Osborne Park"),
                CamItem("CAM00198", "Cedric St", "East", "Cedric St, Stirling"),
                CamItem("CAM00199", "Karrinyup Rd", "South", "Karrinyup Rd, Stirling"),
                CamItem("CAM00200", "Erindale Rd", "South", "Erindale Rd, Gwelup"),
                CamItem("CAM00201", "Reid Hwy", "South", "Reid Hwy, Hamersley"),
                CamItem("CAM00202", "Warwick Rd", "North West", "Warwick Rd, Greenwood"),
                CamItem("CAM00203", "Hepburn Ave", "North", "Hepburn Ave, Kingsley")
            )
        ),
        PlaceItem(
            "Kwinana Freeway",
            arrayOf(
                CamItem("CAM00184", "East Pde", "East", "East Pde, East Perth"),
                CamItem("CAM00186", "Narrows Bridge", "South", "Narrows Bridge, South Perth"),
                CamItem("CAM00187", "Mill Pt Rd", "North", "Mill Pt Rd, South Perth"),
                CamItem("CAM00188", "South Tce", "North West", "South Tce, South Perth"),
                CamItem("CAM00189", "Cale St", "North East", "Cale St, Como"),
                CamItem("CAM00190", "Manning Rd", "North", "Manning Rd, Canning Hwy"),
                CamItem("CAM00191", "Mt Henry Bridge", "South West", "Mt Henry Bridge, Ardross"),
                CamItem("CAM00192", "Leach Hwy", "South West", "Leach Hwy, Bull Creek"),
                CamItem("CAM00193", "Farrington Rd", "North", "Farrington Rd, Leming")
            )
        )
    )
}