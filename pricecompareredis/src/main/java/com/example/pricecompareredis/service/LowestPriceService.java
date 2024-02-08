package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface LowestPriceService {
    Set getZsetValue(String key);

    int setNewProduct(Product product);

    int setNewProductGrp(ProductGrp productGrp);

    int setNewProductGrpToKeyword(String keyword, String productGrpId, double score);

    Keyword getLowestPriceProductByKeyword(String keyword);

    Set GetZsetValueWithStatus(String key) throws Exception;

    Set<String> GetZsetValueWithSpecificException(String key);

    List<ProductGrp> GetProdGrpUsingKeyword(String keyword);
}
