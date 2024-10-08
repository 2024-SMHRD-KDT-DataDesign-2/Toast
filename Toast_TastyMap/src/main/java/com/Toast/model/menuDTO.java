package com.Toast.model;

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

public class menuDTO {
	private int menu_idx;
	private String menu_name;
	private int menu_price;
	private int place_idx;
}
