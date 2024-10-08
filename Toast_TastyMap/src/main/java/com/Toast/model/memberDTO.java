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

public class memberDTO {
    private String mem_img;
    private String mem_id;
    private String mem_pw;
    private String mem_nick;
    private int mem_ribbon;
    private String mem_grade;
    
    public String getMem_img() {
        return mem_img == null ? "" : mem_img; // mem_img가 null일 경우 빈 문자열로 처리
    }
}
