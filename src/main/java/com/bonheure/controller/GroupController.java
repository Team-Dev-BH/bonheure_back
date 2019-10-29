package com.bonheure.controller;

import com.bonheure.controller.dto.GroupDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "groups")
@Api(tags = "groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

//Postgroup
     
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/saveGroup")
    @ApiOperation(value = "${GroupController.saveGroup}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 422, message = "group is already in use")})
    public GroupDTO saveGroup(@RequestBody @Valid GroupDTO group) {

        return groupService.saveGroup(group);
    }
//getGroup
    
    @GetMapping("/getGroupByReference")
    @ApiOperation(value = "${GroupController.getGroupByReference}", response = UserDTO.class)
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 404, message = "The group doesn't exist")})
    public GroupDTO getGroupByReference(@RequestParam(required = false) String reference) {

        return groupService.getGroupByReference(reference);
    }

//deleteUGroupByReference
    
    @DeleteMapping("/deleteGroupByReference")
    @ApiOperation(value = "${GroupController.deleteUGroupByReference}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 404, message = "The Group doesn't exist")})
    public void deleteUGroupByReference(@PathVariable(value = "reference") String reference) {
        groupService.deleteUGroupByReference(reference);
    }

//updateUGroupByReference
    
    @PutMapping("/updateUGroupByReference")
    @ApiOperation(value = "${GroupController.updateUGroupByReference}")
    public GroupDTO updateGroup(@PathVariable(value = "reference") String reference, @Valid @RequestBody GroupDTO group) {


        return groupService.updateUGroupByReference(reference, group);
    }

}
