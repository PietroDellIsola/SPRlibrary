package com.ap.SPRlibrary.entity;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutputMsg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

}
