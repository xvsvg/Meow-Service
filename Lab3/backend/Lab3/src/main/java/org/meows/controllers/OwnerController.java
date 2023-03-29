package org.meows.controllers;

import org.meows.exceptions.OwnerServiceException;
import org.meows.models.create.CreateOwnerRequest;
import org.meows.models.get.OwnerGetResponse;
import org.meows.models.page.OwnerPageResponse;
import org.meows.models.OwnerResponse;
import org.meows.models.update.UpdateOwnerRequest;
import org.meows.services.OwnerService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<OwnerGetResponse> getOwnerById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(ownerService.getById(id), HttpStatus.OK);
        } catch (OwnerServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/owners")
    public ResponseEntity<OwnerPageResponse> getAllOwners(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                          @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {

        try {
            Pageable info = PageRequest.of(page - 1, size);
            return new ResponseEntity<>(ownerService.getAll(info), HttpStatus.PARTIAL_CONTENT);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @PostMapping("/api/owners/create")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody CreateOwnerRequest owner) {
        try {
            return new ResponseEntity<>(ownerService.create(owner), HttpStatus.CREATED);
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
