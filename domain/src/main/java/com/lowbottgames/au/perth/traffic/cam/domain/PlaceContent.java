package com.lowbottgames.au.perth.traffic.cam.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaceContent {

    public static List<PlaceItem> ITEMS;

    static {
        List<PlaceItem> list = new ArrayList<>();


        list.add(new PlaceItem("Perth CBD", new CamItem[] {
                new CamItem("CAM00183", "Hay St/Thomas St", "South West", "Hay St/Thomas St, Perth CBD"),
//                new CamItem("WB185", "Riverside Dr/Barrack St", "", "Riverside Dr/Barrack St"),
                new CamItem("CAM00194", "Hay St", "South", "Hay St, Perth CBD"),
        }
        ));

        list.add(new PlaceItem("Joondalup", new CamItem[] {
                new CamItem("CAM00080", "Hodges Dr", "North West", "Hodges Dr, Joondalup"),
                new CamItem("CAM00082", "Shenton Ave", "South", "Shenton Ave, Joondalup"),
                new CamItem("CAM00204", "Whitfords Ave", "North", "Whitfords Ave, Woodvale"),
                new CamItem("CAM00205", "Ocean Reef Rd", "South", "Ocean Reef Rd, Joondalup"),
        }
        ));

        list.add(new PlaceItem("Mitchell Freeway", new CamItem[] {
                new CamItem("CAM00181", "West Coast Hwy/ Scarb Beach Rd", "North", "West Coast Hwy/ Scarb Beach Rd, Scarborough"),
                new CamItem("CAM00195", "Loftus St/Leederville Pde", "East", "Loftus St/Leederville Pde, Leederville"),
                new CamItem("CAM00196", "Vincent St", "South", "Vincent St, West Perth"),
                new CamItem("CAM00197", "Hutton St", "South", "Hutton St, Osborne Park"),
                new CamItem("CAM00198", "Cedric St", "East", "Cedric St, Stirling"),
                new CamItem("CAM00199", "Karrinyup Rd", "South", "Karrinyup Rd, Stirling"),
                new CamItem("CAM00200", "Erindale Rd", "South", "Erindale Rd, Gwelup"),
                new CamItem("CAM00201", "Reid Hwy", "South", "Reid Hwy, Hamersley"),
                new CamItem("CAM00202", "Warwick Rd", "North West", "Warwick Rd, Greenwood"),
                new CamItem("CAM00203", "Hepburn Ave", "North", "Hepburn Ave, Kingsley"),
        }
        ));

        list.add(new PlaceItem("Kwinana Freeway", new CamItem[] {
                new CamItem("CAM00184", "East Pde", "East", "East Pde, East Perth"),
                new CamItem("CAM00186", "Narrows Bridge", "South", "Narrows Bridge, South Perth"),
                new CamItem("CAM00187", "Mill Pt Rd", "North", "Mill Pt Rd, South Perth"),
                new CamItem("CAM00188", "South Tce", "North West", "South Tce, South Perth"),
                new CamItem("CAM00189", "Cale St", "North East", "Cale St, Como"),
                new CamItem("CAM00190", "Manning Rd", "North", "Manning Rd, Canning Hwy"),
                new CamItem("CAM00191", "Mt Henry Bridge", "South West", "Mt Henry Bridge, Ardross"),
                new CamItem("CAM00192", "Leach Hwy", "South West", "Leach Hwy, Bull Creek"),
                new CamItem("CAM00193", "Farrington Rd", "North", "Farrington Rd, Leming"),
        }
        ));


        ITEMS = Collections.unmodifiableList(list);
    }

}