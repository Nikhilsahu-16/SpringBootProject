package com.springboot.productModel;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@NoArgsConstructor
public class ProductModel {
	@NotNull(message = "Product Id cannot be blank")	
	private Long productId;
	@NotBlank(message = "Product Name cannot be blank")
	private String productName;
	@NotBlank(message = "Product MadeIn cannot be blank")
	private String productMadeIn;
	@Positive(message = "Price must be greater than zero")
	private double productPrice;
	@Positive(message = "Product Quantity must be greater than zero")
	private int productQuantity;
	@NotBlank(message = "Product MadeIn cannot be blank")
	private String productBrand;
	@DecimalMax(value = "100.0",message = "Tax Rate cannot be exceed 100")
	private double taxRate;
	@DecimalMax(value = "100.0",message = "Discount Rate cannot be exceed")
	private double discountRate;
}
