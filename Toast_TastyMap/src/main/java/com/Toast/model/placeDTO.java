package com.Toast.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class placeDTO {
    private int place_idx;
    private String place_name;
    private String place_addr;
    private String place_tel;
    private double place_lat;
    private double place_lon;
    private String place_district;
    private double avg_ratings;
    private int price;
    private String category;
    private String mon;
    private String tue;
    private String wed;
    private String thur;
    private String fri;
    private String sat;
    private String sun;
    private String keyword;
    private char parking_yn;
}
