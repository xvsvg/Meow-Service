package org.meows.controllers;

import org.meows.exceptions.OwnerServiceException;
import org.meows.models.CreateOwnerRequest;
import org.meows.models.OwnerResponse;
import org.meows.models.UpdateOwnerRequest;
import org.meows.services.OwnerService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OwnerController {

    private OwnerService ownerService;

    private Logger logger;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
        this.logger = LoggerFactory.getLogger(OwnerController.class);
    }

    @GetMapping("/api/owners/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(ownerService.getById(id), HttpStatus.OK);
        } catch (OwnerServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/owners")
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {
        return new ResponseEntity<>(ownerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/api/owners/create")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody CreateOwnerRequest owner) {
        try {
            return new ResponseEntity<>(ownerService.create(owner), HttpStatus.OK);
        } catch (OwnerServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/owners/delete/{id}")
    public ResponseEntity<OwnerResponse> deleteOwner(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(ownerService.delete(id), HttpStatus.OK);
        } catch (OwnerServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/owners/update")
    public ResponseEntity<OwnerResponse> updateOwner(@RequestBody UpdateOwnerRequest owner) {
        try {
            return new ResponseEntity<>(ownerService.update(owner), HttpStatus.OK);
        } catch (OwnerServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/owners/{id}/pet/{petId}")
    public ResponseEntity<OwnerResponse> addPet(@PathVariable("id") Long id, @PathVariable("petId") Long petId) {
        try {
            return new ResponseEntity<>(ownerService.addCat(id, petId), HttpStatus.OK);
        } catch (OwnerServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/owners/{id}/delete/{petId}")
    public ResponseEntity<OwnerResponse> deletePet(@PathVariable("id") Long id, @PathVariable("petId") Long petId) {
        try {
            return new ResponseEntity<>(ownerService.removeCat(id, petId), HttpStatus.OK);
        } catch (OwnerServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
