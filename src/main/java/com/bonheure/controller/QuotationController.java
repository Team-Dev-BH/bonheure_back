package com.bonheure.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bonheure.controller.dto.QuotationDTO;
import com.bonheure.service.QuotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "quotations")
@Api(tags = "quotations")
public class QuotationController {
	
	@Autowired
	QuotationService quotationService;

	//post
		 @ResponseStatus(HttpStatus.OK)
		    @PostMapping("/save")
		    @ApiOperation(value = "${QuotationController.save}")
		    @ApiResponses(value = {//
		        @ApiResponse(code = 400, message = "Something went wrong"), //
		        @ApiResponse(code = 403, message = "Access denied"), //
		        @ApiResponse(code = 422, message = "Username is already in use"), //
		        @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
		    public QuotationDTO saveQuotation(@RequestBody @Valid QuotationDTO quotation) {

		        return quotationService.saveQuotation(quotation);
		    }


			//getAllQuotations
		     @GetMapping("/getAll")
		     @ApiOperation(value = "${QuotationController.getAll}", response = QuotationDTO.class)
		     @ApiResponses(value = {//
		     @ApiResponse(code = 400, message = "Something went wrong"), //
		     @ApiResponse(code = 403, message = "Access denied"), //
		     @ApiResponse(code = 404, message = "The user doesn't exist"), //
		     @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
		     public List<QuotationDTO> getAllCommande(){
		    	                   
		        return quotationService.getAllQuotation();
		    }
		     
		     //updateQuotation
		     @PutMapping("/updateQuotation")
		     @ApiOperation(value = "${UserController.updateQuotation}")
		     public QuotationDTO updateCommande(@RequestParam(required = false) String reference, @Valid @RequestBody QuotationDTO quotation) {
		         return quotationService.updateQuotationByRefrence(reference, quotation);
		     }
		     
	
}
