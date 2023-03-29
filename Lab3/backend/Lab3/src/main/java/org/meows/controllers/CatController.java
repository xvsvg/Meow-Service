package org.meows.controllers;

import org.meows.exceptions.CatServiceException;
import org.meows.models.*;
import org.meows.models.create.CreateCatRequest;
import org.meows.models.filter.CatFilterRequest;
import org.meows.models.get.CatGetResponse;
import org.meows.models.page.CatPageResponse;
import org.meows.models.update.UpdateCatRequest;
import org.meows.services.CatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CatController {

    private final Integer DATA_PER_PAGE = 20;
    private CatService catService;
    private Logger logger;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
        logger = LoggerFactory.getLogger(CatController.class);
    }

    @GetMapping("/api/cats/{id}")
    public ResponseEntity<CatGetResponse> getCatById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(catService.getById(id), HttpStatus.OK);
        } catch (CatServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/cats")
    public ResponseEntity<CatPageResponse> getAllCats(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                      @ModelAttribute CatFilterRequest request) {
        try {
            Pageable info = PageRequest.of(page - 1, size);
            return new ResponseEntity<>(catService.getAllCats(info, request), HttpStatus.PARTIAL_CONTENT);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    @PostMapping("/api/cats/create")
    public ResponseEntity<CatResponse> createCat(@RequestBody CreateCatRequest request) {
        try {
            return new ResponseEntity<>(catService.create(request), HttpStatus.CREATED);
        } catch (CatServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/cats/delete/{id}")
    public ResponseEntity<CatResponse> deleteCat(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(catService.deleteById(id), HttpStatus.OK);
        } catch (CatServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/cats/update")
    public ResponseEntity<CatResponse> updateCat(@RequestBody UpdateCatRequest request) {
        try {
            return new ResponseEntity<>(catService.update(request), HttpStatus.OK);
        } catch (CatServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/cats/{id}/friend/{friendId}")
    public ResponseEntity<CatResponse> addFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        try {
            return new ResponseEntity<>(catService.addFriend(id, friendId), HttpStatus.OK);
        } catch (CatServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/cats/{id}/unfriend/{friendId}")
    public ResponseEntity<CatResponse> removeFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        try {
            return new ResponseEntity<>(catService.removeFriend(id, friendId), HttpStatus.OK);
        } catch (CatServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
