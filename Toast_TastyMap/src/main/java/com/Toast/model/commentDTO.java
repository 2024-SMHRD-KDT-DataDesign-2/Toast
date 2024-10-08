package com.Toast.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class commentDTO {
    private int comment_id;
    private int review_idx;
    private String mem_id;
    private int parent_comment_id;
    private String comment_content;
    private Date created_at;
}
