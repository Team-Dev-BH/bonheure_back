package com.bonheure.controller;

import com.bonheure.controller.dto.GroupDTO;
import com.bonheure.service.GroupService;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "groups")
@Api(tags = "groups")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO saveGroup(@RequestBody @Valid GroupDTO group) {

        return groupService.saveGroup(group);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO getGroup(@RequestParam(required = false) String reference) {

        return groupService.getGroupByReference(reference);
    }


    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroup(@PathVariable(value = "reference") String reference) {
        groupService.deleteUGroupByReference(reference);
    }


    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO updateGroup(@PathVariable(value = "reference") String reference, @Valid @RequestBody GroupDTO group) {


        return groupService.updateUGroupByReference(reference, group);
    }

}
