package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.entity.ProductEntity;
import com.springboot.productModel.ProductModel;
import com.springboot.repository.ProductRepository;


@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public void saveProductDetails(ProductModel productModel)
	{
		double stockValue;
		stockValue=productModel.getProductPrice()*productModel.getProductQuantity();
		
		double discountPrice;
		discountPrice=productModel.getProductPrice()*productModel.getDiscountRate()/100;
		
		double offerPrice;
		offerPrice=productModel.getProductPrice()-discountPrice;
		
		double taxPrice;
		taxPrice=productModel.getProductPrice()*productModel.getTaxRate()/100;
		
		double finalPrice;
		finalPrice=offerPrice+taxPrice;
		
		
		ProductEntity productEntity=new ProductEntity(); 
		productEntity.setProductId(productModel.getProductId());
		productEntity.setProductName(productModel.getProductName());
		productEntity.setTaxRate(productModel.getTaxRate());
		productEntity.setDiscountRate(productModel.getDiscountRate());
	
		productEntity.setProductBrand(productModel.getProductBrand());
		productEntity.setProductPrice(productModel.getProductPrice());
		productEntity.setProductMadeIn(productModel.getProductMadeIn());
		
		productEntity.setProductQuantity(productModel.getProductQuantity());
		productEntity.setDiscountPrice(discountPrice);
		productEntity.setStockValue(stockValue);
		productEntity.setTaxPrice(taxPrice);
		productEntity.setOfferPrice(offerPrice);
		productEntity.setFinalPrice(finalPrice);
		
		productRepository.save(productEntity);
		
      
		
	}
	public List<ProductEntity>getAllproducts(){
		 List<ProductEntity> products=productRepository.findAll();
		return products;
	}
	public ProductEntity searchById(Long productId)
	{
		Optional<ProductEntity>optionalData=productRepository.findById(productId);
		if(optionalData.isPresent())
		{
			ProductEntity product=optionalData.get();
			return product;
		}
		else
		{
			return null;
		}
	}
	public void deleteProductById(Long productId) {
	
		productRepository.deleteById(productId);
		
	}
	  public ProductModel getForm(Long id) {
	        Optional<ProductEntity> entity = productRepository.findById(id);

	        if (entity.isPresent()) {
	            ProductEntity product = entity.get();
	           ProductModel ref = new ProductModel();
	            ref.setProductName(product.getProductName());
               ref.setProductBrand(product.getProductBrand());
	            ref.setProductMadeIn(product.getProductMadeIn());
	            ref.setProductPrice(product.getProductPrice());
	            ref.setProductQuantity(product.getProductQuantity());
                return ref;
	        } else {
	            throw new IllegalArgumentException("Product with ID " + id + " not found.");
	        }
	    }

	    
	    public void saveEditedProduct(Long id, ProductModel productModel) {
	        Optional<ProductEntity> optionalProduct = productRepository.findById(id);

	        if (optionalProduct.isPresent()) {
	            ProductEntity product = optionalProduct.get();
	            product.setProductName(productModel.getProductName());
	            product.setProductBrand(productModel.getProductBrand());
	            product.setProductMadeIn(productModel.getProductMadeIn());
	            product.setProductPrice(productModel.getProductPrice());
	            product.setProductQuantity(productModel.getProductQuantity());
	            productRepository.save(product);
	        } 
	    }
	
	}
	
    
