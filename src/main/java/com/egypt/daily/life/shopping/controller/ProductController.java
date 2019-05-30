package com.egypt.daily.life.shopping.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.model.ProductComment;
import com.egypt.daily.life.shopping.repository.ProductCommentRepository;
import com.egypt.daily.life.shopping.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCommentRepository productCommentRepository;

	@GetMapping("/search")
	public ResponseEntity<List<Product>> getProductsByKeyword(
			@RequestParam(value = "na", required = false) String productName,
			@RequestParam(value = "mc", required = false) String mainCategoryName,
			@RequestParam(value = "sc", required = false) String subCategoryName,
			@RequestParam(value = "st", required = false) String sortType,
			@RequestParam(value = "t", required = false) String tag,
			@RequestParam(value = "lp", required = false) String lowerPrice,
			@RequestParam(value = "hp", required = false) String higherPrice) throws IOException {
		// filter initiate
		boolean nameFilter = false;
		boolean mainCategoryFilter = false;
		boolean subCategoryFilter = false;
		boolean needSort = false;
		boolean tagFilter = false;
		boolean priceFilter = false;

		int lowerPrice_i = 0;
		int higherPrice_i = 0;

		/* All the required filter check here */
		if (productName != null) {
			nameFilter = true;
		}
		if (mainCategoryName != null) {
			mainCategoryFilter = true;
		}
		if (subCategoryName != null) {
			subCategoryFilter = true;
		}
		if (sortType != null) {
			needSort = true;
		}
		if (tag != null) {
			tagFilter = true;
		}
		if (lowerPrice != null && higherPrice != null) {
			lowerPrice_i = Integer.parseInt(lowerPrice);
			higherPrice_i = Integer.parseInt(higherPrice);
			priceFilter = true;
		}

		// get all product
		List<Product> products = productService.getAllProducts();

		// Filter by product name
		Iterator<Product> iter = products.iterator();

		if (nameFilter) {
			while (iter.hasNext()) {
				Product product = iter.next();
				if (!product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
					iter.remove();
				}
			}
		}
		// Filter by product mainCategory
		iter = products.listIterator();
		if (mainCategoryFilter) {
			while (iter.hasNext()) {
				Product product = iter.next();
				Category category = product.getProductCategory();
				if (!category.getMainCategoryName().equalsIgnoreCase(mainCategoryName)) {
					iter.remove();
				}
			}
		}
		// Filter by product subCategory
//		iter = products.listIterator();
//		if (subCategoryFilter) {
//			while (iter.hasNext()) {
//				Product product = iter.next();
//				Category category = product.getProductCategory();
//				if (!category.getSubCategoryName().equalsIgnoreCase(subCategoryName)) {
//					iter.remove();
//				}
//			}
//		}
		// Filter by product tags
		iter = products.iterator();
//		if (tagFilter) {
//			while (iter.hasNext()) {
//				Product product = iter.next();
//				List<ProductTag> productTags = product.getProductTags();
//				boolean notFound = true;
//				for (ProductTag productTag : productTags) {
//					if (productTag.getTagContents().equalsIgnoreCase(tag)) {
//						notFound = false;
//						break;
//					}
//				}
//				if (notFound) {
//					iter.remove();
//				}
//			}
//		}
		// Filter by product price
		iter = products.listIterator();
		if (priceFilter) {
			while (iter.hasNext()) {
				Product product = iter.next();
				if (product.getProductPrice() <= lowerPrice_i || product.getProductPrice() >= higherPrice_i) {
					iter.remove();
				}
			}
		}
		// create tag List, subCategory List
//		Set<String> tagList = new HashSet<String>();
//		Set<String> subCategoryList = new HashSet<String>();
//		for (Product product : products) {
////			subCategoryList.add(product.getProductCategory().getSubCategoryName());
//			List<ProductTag> productTags = product.getProductTags();
//			if (productTags != null && !productTags.isEmpty()) {
//				for (ProductTag productTag : productTags) {
//					tagList.add(productTag.getTagContents());
//				}
//			}
//		}
		// sort product
		if (needSort) {
			products = productService.sort(products, sortType);
		}

		// model.addAttribute("products", products);
		// model.addAttribute("subCategoryList", subCategoryList);
		// model.addAttribute("tagList", tagList);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/productComment/{id}")
	public ResponseEntity<Response> addProductComment(@PathVariable("id") Long productId,
			@RequestBody ProductComment productComment) {
		productComment.setCommentDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		Product product = productService.getProductById(productId);
		productComment.setProduct(product);
		productCommentRepository.save(productComment);

		return new ResponseEntity<Response>(new Response("your comment is saved correctly"), HttpStatus.OK);
	}
	
	@GetMapping("/productComments/{id}")
	public ResponseEntity<List<ProductComment>> getproductComments(@PathVariable("id") Long id ) {
		Product product = productService.getProductById(id) ;
		List<ProductComment> comments = productCommentRepository.findByProduct(product) ;
		return new ResponseEntity<List<ProductComment>>(comments, HttpStatus.OK) ;
	}
}
