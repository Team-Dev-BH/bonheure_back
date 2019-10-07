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

import com.bonheure.controller.dto.WorkingAreaDTO;
import com.bonheure.service.WorkingAreaService;

@RestController
@RequestMapping(value="working areas")


public class WorkingAreaController {
	
	
	@Autowired
	WorkingAreaService workingAreaService;
	

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WorkingAreaDTO saveWorkingArea(@RequestBody @Valid WorkingAreaDTO workingarea) {

        return workingAreaService.saveWorkingArea(workingarea);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WorkingAreaDTO getWorkingArea(@RequestParam(required = false) String reference) {

        return workingAreaService.getWorkingAreaByReference(reference);
    }


    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkingArea(@PathVariable(value = "reference") String reference) {
    	workingAreaService.deleteWorkingArearByReference(reference);
    }

    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public WorkingAreaDTO updateWorkingarea(@PathVariable(value = "reference") String reference, @Valid @RequestBody WorkingAreaDTO workingarea) {
        return workingAreaService.updateWorkingAreaByReference(reference, workingarea);
    }
	
	

}
