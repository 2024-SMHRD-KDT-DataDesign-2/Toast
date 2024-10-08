package com.Toast.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class reviewDTO {
    private int review_idx;
    private String mem_id;
    private int place_idx;
    private String review_content_good;
    private String review_content_bad;
    private String review_content_recommend;
    private String review_img1;
    private String review_img2;
    private String review_img3;
    private java.util.Date created_at;
    private int review_ratings;
}
