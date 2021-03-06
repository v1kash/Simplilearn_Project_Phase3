package com.ecommerce.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.models.Product;
import com.ecommerce.repository.ProductRepository;


@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
    ProductRepository productRepo;
	
	@GetMapping("all")
	public List<Product> getAllProducts()
	{
		List<Product> product=(List<Product>) productRepo.findAll(); 
		return product;
	}
	
	@PostMapping("add")
	public Product addProduct(@RequestBody Product product)
	{
		return productRepo.save(product);
	}
	
	@PutMapping("update/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product product)
	{   
		if(productRepo.findById(id).isPresent()) {
            Product curr=productRepo.findById(id).get();
            curr.setSeason(product.getSeason());
            curr.setBrand(product.getBrand());
            curr.setCategory(product.getCategory());
            curr.setPrice(product.getPrice());
            curr.setColor(product.getColor());
            curr.setCreatedDate(product.getCreatedDate());
            curr.setDiscount(product.getDiscount());
            return productRepo.save(curr);
		}
		else
			return null;
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteProduct(@PathVariable int id)
	{
		productRepo.deleteById(id);
	}
	
	// get product by id
	@GetMapping("product/{id}")
	public Optional<Product> getProductById(@PathVariable int id)
	{
		return productRepo.findById(id);
	}
	
	
	// get products by season
	@GetMapping("season/{season}")
	public List<Product> getProductBySeason(@PathVariable String season)
	{	
	 List<Product> product=(List<Product>) productRepo.findBySeason(season);
	 return product;
	}
	
	
	// get products by brand
	@GetMapping("brand/{brand}")
	public List<Product> getProductByBrand(@PathVariable String brand)
	{	
	 List<Product> product=(List<Product>) productRepo.findByBrand(brand);
	 return product;
	}
	
	// get products by category
	@GetMapping("category/{category}")
	public List<Product> getProductByCategory(@PathVariable String category)
	{	
	 List<Product> product=(List<Product>) productRepo.findByCategory(category);
	 return product;
	}
	
	// get products by color
	@GetMapping("color/{color}")
	public List<Product> getProductByColor(@PathVariable String color)
	{	
	 List<Product> product=(List<Product>) productRepo.findByColor(color);
	 return product;
	}
	
	// get products by price (Asc/Desc)
	@GetMapping("price/{order}")
	public List<Product> getProductByPrice(@PathVariable String order)
	{	
	 List<Product> product=(List<Product>) productRepo.findAll();
	 Collections.sort(product,new Comparator<Product>() {
	        @Override
	        public int compare(Product o1, Product o2) {
	            return Double.compare(o1.getPrice(), o2.getPrice());
	        }
	    });
		 if(order.equals("asc"))
		 return product;
		 else if(order.equals("desc"))
		 { Collections.reverse(product);
			 return product;
		 }
		 else return null;
		 
	}
	
	// sort products by latest date 
	@GetMapping("date")
	public List<Product> getProductByDate()
	{	
	 List<Product> product=(List<Product>) productRepo.findAll();
	 Collections.sort(product, new Comparator<Product>() {
		  public int compare(Product o1, Product o2) {
		      return o1.getCreatedDate().compareTo(o2.getCreatedDate());
		  }
		});
	 Collections.reverse(product);
	 return product; 
	}
	
	
}
