package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.service.LowestPriceService;
import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.NotFoundException;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LowestPriceController {
    private final LowestPriceService lowestPriceService;
    @GetMapping("/product")
    public Set getZsetValue(String key) {
        return lowestPriceService.getZsetValue(key);
    }
    @GetMapping("/product1")
    public Set GetZsetValueWithStatus (String key){
        try {
            return lowestPriceService.GetZsetValueWithStatus(key);
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping("/product2")
    public Set GetZsetValueUsingExController (String key) throws Exception {
        try {
            return lowestPriceService.GetZsetValueWithStatus(key);
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @GetMapping("/product3")
    public ResponseEntity<Set> GetZsetValueUsingExControllerWithSpecificException (String key) throws Exception {
        Set<String> mySet = new HashSet<>();
        try {
            mySet =  lowestPriceService.GetZsetValueWithSpecificException(key);
        }
        catch (NotFoundException ex) {
            throw new Exception(ex);
        }
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(mySet, responseHeaders, HttpStatus.OK);
    }



    @PutMapping("/product")
    public int setNewProduct(@RequestBody Product product) {
        return lowestPriceService.setNewProduct(product);
    }

    @PutMapping("/productGroup")
    public int setNewProductGrp(@RequestBody ProductGrp productGrp) {
        return lowestPriceService.setNewProductGrp(productGrp);
    }

    @PutMapping("/productGroupToKeyword")
    public int setNewProductGrpToKeyword(String keyword, String productGrpId, double score) {
        return lowestPriceService.setNewProductGrpToKeyword(keyword,productGrpId,score);
    }

    @GetMapping("/productPrice/lowest")
    public Keyword getLowestPriceProductByKeyword(String keyword) {
        return lowestPriceService.getLowestPriceProductByKeyword(keyword);
    }
}
