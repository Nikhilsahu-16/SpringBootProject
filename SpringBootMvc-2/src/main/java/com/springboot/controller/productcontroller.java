package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.entity.ProductEntity;
import com.springboot.productModel.ProductModel;
import com.springboot.repository.ProductRepository;
import com.springboot.service.ProductService;

import jakarta.validation.Valid;


@Controller 
public class productcontroller {
	
		@Autowired
		ProductService productservice;
		private CrudRepository<ProductEntity, Long> productRepository;
	
	@GetMapping("/product")
	public String Product(Model model)
	{
		ProductModel productModel=new ProductModel();
		productModel.setProductMadeIn("India");
		productModel.setProductQuantity(3);
		productModel.setDiscountRate(10.5);
		model.addAttribute("productModel",productModel);
		return "product";
	}
	@PostMapping("/saveProduct")
	public String saveProduct(@Valid ProductModel productmodel,BindingResult bindingResult,Model model) {
		//used to store the field name:ErrorMessage in the form of key:value
		HashMap<String,String> validationErrors=new HashMap<String,String>();
		//to  check the errors are present or not
		if(bindingResult.hasErrors())
		{
			//to read all the field errors one by one
			for(FieldError fieldError: bindingResult.getFieldErrors())
			{
				//putting the each field error into map
				validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			//adding the validation errors into the model object to send the view
			model.addAttribute("validationErrors",validationErrors);
			//return the view to display the error
			return "product";
		
		}
		productservice.saveProductDetails(productmodel);
		return "redirect:/getallproducts";
	}
	     
	@GetMapping("/getallproducts")
	public String getAllProducts(Model model)
	{
		List<ProductEntity>products=productservice.getAllproducts();
		
		model.addAttribute("products",products);
		return "product-list";
	}
	
	@GetMapping("/getsearchform")
	public String getSearchForm()
	{
		return"search";
	}
	@PostMapping("/searchbyid")
	public String searchById(@RequestParam Long productId,Model model)
	{
		ProductEntity product=productservice.searchById(productId);
		model.addAttribute("product",product);
		return "search";
	}
	
	@GetMapping("/delete/{productId}")
	public String deleteById(@PathVariable("productId") Long productId) {
	    productservice.deleteProductById(productId);
	    return "redirect:/getallproducts";
	}

	  @GetMapping("/edit/{productId}")
	    public String editById(@PathVariable("productId") Long productId, Model model) {
	       
		  ProductModel product = productservice.getForm(productId);
	        model.addAttribute("product", product);
	        model.addAttribute("id", productId);
	        return "edit-product";
	    }

	    
	    @PostMapping("/editproductsave/{id}")
	    public String saveEditedProduct(@PathVariable("id") Long id,ProductModel productModel) {
	        productservice.saveEditedProduct(id, productModel);
	        return "redirect:/getallproducts";
	    }
	}



