package com.springboot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String productName;
	private String productBrand;
	private String productMadeIn;
	private double productPrice;	
	private int productQuantity;
	private double discountRate;
	private double taxRate;
	private double discountPrice;
	private double taxPrice;
	private double offerPrice;
	private double finalPrice;
	private double stockValue;
}
