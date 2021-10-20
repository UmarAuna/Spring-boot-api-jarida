package com.jarida.server.jaridaserver.organization_one_to_one.controller;

import com.jarida.server.jaridaserver.organization_one_to_one.model.Organization;
import com.jarida.server.jaridaserver.organization_one_to_one.repository.OrganizationRepository;
import com.jarida.server.jaridaserver.organization_one_to_one.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@Validated
@Api(tags = "Organization One to One API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "Organization", description = "This is for getting Organization")
})
public class OrganizationController {
    //https://dzone.com/articles/introduction-to-spring-data-jpa-part-5-unidirectio
    // https://github.com/gudpick/jpa-demo/blob/unidirectional-one-to-one-starter/src/main/java/com/notyfyd/service/OrganizationService.java
    private OrganizationService organizationService;
    private OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationService organizationService, OrganizationRepository organizationRepository) {
        this.organizationService = organizationService;
        this.organizationRepository = organizationRepository;
    }

    @PostMapping("/organization/create")
    /*
    * {

    "name": "Nooble Academy",
    "orgId": "NAL",
    "address": {
        "building": "XII/706-A",
        "street": "Andheri",
        "city": "Mumbai",
        "state": "Maharashtra",
        "zipcode": "400703",
       "country": "India"
    }

}
    * */
    public ResponseEntity<Object> createOrganization(@RequestBody Organization organization) {
        return organizationService.createOrganization(organization);
    }

    @DeleteMapping("/organization/delete/{id}")
    public ResponseEntity<Object> deleteOrganization(@PathVariable Long id) {
        if(organizationRepository.findById(id).isPresent()) {
            organizationRepository.deleteById(id);
            if (organizationRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified organization");
            else return ResponseEntity.ok("Successfully deleted the specified organization");
        } else return ResponseEntity.unprocessableEntity().body("Specified organization not present");
    }

    @GetMapping("/organization/get/{id}")
    public Organization getOrganization(@PathVariable Long id) {
        if(organizationRepository.findById(id).isPresent())
            return organizationRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/organization/get")
    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    @PutMapping("/organization/update/{id}")
    public ResponseEntity<Object> updateOrganization(@PathVariable Long id, @RequestBody Organization org) {
        return organizationService.updateOrganization(id, org);
    }

}
