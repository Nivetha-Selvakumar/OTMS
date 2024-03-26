package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.OrganisationDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.OrganisationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/org")
@Controller
@SecurityRequirement(name = "nivethaAuth")
public class OrganisationController {

    @Autowired
    OrganisationService organisationService;

    @GetMapping(path="/viewOrg")
    public ResponseEntity<OrganisationDTO> listOrg(@RequestParam String orgRef) throws CommonException{
        return new ResponseEntity<>(organisationService.viewOrgList(orgRef), HttpStatus.OK);
    }
}
