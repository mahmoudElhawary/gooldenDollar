package com.egypt.daily.life.shopping.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.MainCategory;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.service.CategoryService;
import com.egypt.daily.life.shopping.service.ProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AdminProductController {

	@Autowired
	private ServletContext context;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService ;
	@GetMapping("/allProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/sliderProducts")
	public ResponseEntity<List<Product>> getSliderProducts() {
		List<Product> products = productService.getAllProductsSlider(true);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	@PostMapping("/allProductsByMainCategory")
	public ResponseEntity<List<Product>> getProductsByMainCategory(@RequestBody String mainCategory) {
		List<Product> products = productService.getProductsByMainCategory(mainCategory) ;
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK) ;
	}
	@PostMapping("/allProductsBySubCategory")
	public ResponseEntity<List<Product>> getProductsBySubCategory(@RequestBody String subCategory) {
		List<Product> products = productService.getProductsBySubCategory(subCategory) ;
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK) ;
	}
	@PostMapping("/allProductsByCategory")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody Category category) {
		Category categoryid = categoryService.getCategoryById(category.getCategoryId()) ;
		List<Product> products = productService.getProductsByCategory(categoryid) ;
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK) ;
	}
	@PostMapping("/saveProduct")
	public ResponseEntity<Product> createProduct(@RequestParam("productFile") MultipartFile productFile,
			@RequestParam("product") String product) throws JsonParseException, JsonMappingException, IOException {
		if (product != null) {
			
			// get product data from rest api
			Product productData = new ObjectMapper().readValue(product, Product.class);
			
			//get photo data from file to product class
			productData.setProductPhotoName(productFile.getOriginalFilename());
			productData.setProductPhoto(productFile.getBytes());
			
			//save photo
			boolean isExist = new File(context.getRealPath("/products/")).exists();
			if(!isExist) {
				new File(context.getRealPath("/products/")).mkdir() ;
			}
			File serverFile = new File(context.getRealPath("/products/")+File.separator + productFile.getOriginalFilename()) ;
			System.out.println("/////////////"+serverFile);
			try {
				FileUtils.writeByteArrayToFile(serverFile,productFile.getBytes());
			} catch (Exception e) {
				System.out.println("admin controller - save product "+ e.getMessage());
			}
			
			//save product data
			Product productDB = productService.save(productData);
			return new ResponseEntity<Product>(productDB, HttpStatus.OK);
		} else {
			return null;
		}
	}
	@GetMapping("/deleteProduct/{id}")
	public ResponseEntity<Response> deleteProduct(@PathVariable("id") Long id) {
		if (id != null) {
			productService.delete(id);
			return new ResponseEntity<Response>(new Response("this product is deleted successfuly"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("you dont select an product"), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		if (id != null) {
			Product productDB = productService.getProductById(id);
			return new ResponseEntity<Product>(productDB, HttpStatus.OK);
		} else {
			return null;
		}
	}
	@GetMapping(value = "/getProductsImages")
	@CrossOrigin
	public ResponseEntity<List<String>> getImage() throws IOException {

		List<String> images = new ArrayList<String>();

		String filePath = context.getRealPath("/products");
		File fileFolder = new File(filePath);

		if (fileFolder != null) {
			for (final File file : fileFolder.listFiles()) {
				if (!file.isDirectory()){
					String encodeBase64 = null;
					try {
						String extention = FilenameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] bytes = new byte[(int) file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						images.add("data:Image/" + extention + ";base64," + encodeBase64);
						fileInputStream.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}

	@GetMapping("/findByMaxRating")
	public ResponseEntity<List<Product>> findByMaxRating() {
		List<Product> products = productService.findTop12ByOrderByRatingDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	@GetMapping("/maxSellingProducts")
	public ResponseEntity<List<Product>> maxSellingProducts() {
		List<Product> products = productService.findTop12ByOrderBySellCountDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	@GetMapping("/maxViewProducts")
	public ResponseEntity<List<Product>> maxViewProducts() {
		List<Product> products = productService.findTop12ByOrderByViewsDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	@GetMapping("/newestProducts")
	public ResponseEntity<List<Product>> newestProducts() {
		List<Product> products = productService.findTop12ByOrderByProductDateDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
