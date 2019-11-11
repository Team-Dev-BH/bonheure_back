package com.bonheure.controller;

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "categories")
@Api(tags = "categories")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// saveCategory

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/saveCategory")
	@ApiOperation(value = "$CategoryController.saveCategory}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
	})
	public CategoryDTO saveCategorie(@RequestBody @Valid CategoryDTO categorie) {

		return categoryService.saveCategory(categorie);
	}

	// getCategoryByReference

	@GetMapping("/getCategoryByReference")
	@ApiOperation(value = "${CategoryController.getCategoryByReference}", response = UserDTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The category doesn't exist") })
	public CategoryDTO getCategoryByReference(@RequestParam(required = false) String reference) {

		return categoryService.getCategoryByReference(reference);
	}

//deleteCategoryByReference

	@DeleteMapping("/deleteCategoryByReference")
	@ApiOperation(value = "${CategoryController.deleteCategoryByReference}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The category doesn't exist") })
	public void deleteCategoryByReference(@PathVariable(value = "reference") String reference) {
		categoryService.deleteCategoryByReference(reference);
	}

	// updateCategoryByReference

	/*
	 * @PutMapping("/updatePrestationByReference")
	 * 
	 * @ApiOperation(value = "${CategoryController.updatePrestationByReference}")
	 * public CategoryDTO updatePrestationByReference(@PathVariable(value =
	 * "reference") String reference, @Valid @RequestBody CategoryDTO categorie) {
	 * return categoryService.updateCategoryByReference(reference, categorie); }
	 */

	// getallCategory

	@GetMapping("/getallCategory")
	@ApiOperation(value = "$CategoryController.getAll}", response = UserDTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
	})
	public List<CategoryDTO> getAllCategory() {

		return categoryService.getAllCategory();
	}
////getPrestationFromCategory
//	@PostMapping("/getPrestationFromCategory")
//	@ApiResponses(value = { //
//			@ApiResponse(code = 400, message = "Something went wrong"), //
//			@ApiResponse(code = 403, message = "Access denied"), //
//	})
//	public List<PrestationDTO> getPrestationFromCategory(@RequestBody String reference) {
//
//		return categoryService.getListPrestationByCategory(reference);
//	}

}
