package com.bonheure.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bonheure.controller.dto.UserDTO;
import com.bonheure.controller.dto.WorkingAreaDTO;
import com.bonheure.service.WorkingAreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "working areas")
@Api(tags = "working areas")
public class WorkingAreaController {

	@Autowired
	WorkingAreaService workingAreaService;

	
	//Post WorkingArea
	
	@ResponseStatus(HttpStatus.OK)
    @PostMapping("/saveWorkingArea")
    @ApiOperation(value = "${WorkingAreaController.saveWorkingArea}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 422, message = "WorkingArea is already in use")})
	public WorkingAreaDTO saveWorkingArea(@RequestBody @Valid WorkingAreaDTO workingarea) {

		return workingAreaService.saveWorkingArea(workingarea);
	}

	
	
	//Get WorkingArea
	
	
    @GetMapping("/getWorkingAreaByReference")
    @ApiOperation(value = "${WorkingAreaController.getWorkingAreaByReference}", response = UserDTO.class)
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 404, message = "The WorkingArea doesn't exist")})
	public WorkingAreaDTO getWorkingAreaByReference(@RequestParam(required = false) String reference) {

		return workingAreaService.getWorkingAreaByReference(reference);
	}

    
  //delete WorkingArea
    
    @DeleteMapping("/deleteWorkingArearByReference")
    @ApiOperation(value = "${WorkingAreaControlle.deleteWorkingArearByReference}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 404, message = "The WorkingArea doesn't exist")})
	public void deleteWorkingArearByReference(@PathVariable(value = "reference") String reference) {
		workingAreaService.deleteWorkingArearByReference(reference);
	}

  //update WorkingArea
    
    @PutMapping("/updateWorkingAreaByReference")
    @ApiOperation(value = "${WorkingAreaControlle.updateWorkingAreaByReference}")
	public WorkingAreaDTO updateWorkingarea(@PathVariable(value = "reference") String reference,
			@Valid @RequestBody WorkingAreaDTO workingarea) {
		return workingAreaService.updateWorkingAreaByReference(reference, workingarea);
	}

}
