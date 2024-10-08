package com.Toast.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class filteringDTO {
	
	// String place_district, char parking_yn, List<String> keywords
	private String place_district;
	private char parking_yn;
	private List<String> keywords;
	
}
