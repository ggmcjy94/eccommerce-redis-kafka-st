package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.NotFoundException;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService{

    private final RedisTemplate myProductRedis;


    @Override
    public Set getZsetValue(String key) {
        Set myTempSet = new HashSet();
        myTempSet = myProductRedis.opsForZSet().rangeWithScores(key, 0, 9);
        return myTempSet;
    }

    @Override
    public int setNewProduct(Product product) {
        int rank = 0;
        myProductRedis.opsForZSet().add(product.getProductGrpId(), product.getProductId(), product.getPrice());
        rank = myProductRedis.opsForZSet().rank(product.getProductGrpId(), product.getProductId()).intValue();
        return rank;
    }

    @Override
    public int setNewProductGrp(ProductGrp productGrp) {
        List<Product> productList = productGrp.getProductList();
        String productId = productList.get(0).getProductId();
        int price = productList.get(0).getPrice();
        myProductRedis.opsForZSet().add(productGrp.getGroupGrpId(),productId,price);
        return myProductRedis.opsForZSet().zCard(productGrp.getGroupGrpId()).intValue();
    }

    public int setNewProductGrpToKeyword(String keyword, String productGrpId, double score) {
        myProductRedis.opsForZSet().add(keyword, productGrpId, score);
        int rank = myProductRedis.opsForZSet().rank(keyword, productGrpId).intValue();
        return rank;
    }

    @Override
    public Keyword getLowestPriceProductByKeyword(String keyword) {

        Keyword returnInfo = new Keyword();
        List<ProductGrp> tempProdGrp = GetProdGrpUsingKeyword(keyword);

        returnInfo.setKeyword(keyword);
        returnInfo.setProductGrpList(tempProdGrp);

        return returnInfo;
    }

    @Override
    public Set GetZsetValueWithStatus(String key) throws Exception {
        Set myTempSet = new HashSet();
        myTempSet = myProductRedis.opsForZSet().rangeWithScores(key, 0, 9);
        if (myTempSet.size() < 1 ) {
            throw new Exception("The Key doesn't have any member");
        }
        return myTempSet;
    }

    @Override
    public Set<String> GetZsetValueWithSpecificException(String key) {
        Set myTempSet = new HashSet();
        myTempSet = myProductRedis.opsForZSet().rangeWithScores(key, 0, 9);
        if (myTempSet.size() < 1 ) {
            throw new NotFoundException("The Key doesn't exist in redis", HttpStatus.NOT_FOUND);
        }
        return myTempSet;
    }


    @Override
    public List<ProductGrp> GetProdGrpUsingKeyword(String keyword){
        List<ProductGrp> returnInfo = new ArrayList<>();

        List<String> prodGrpIdList = List.copyOf(myProductRedis.opsForZSet().reverseRange(keyword, 0, 9));
        List<Product> tempProdList = new ArrayList<>();

        for (String prodGrpId : prodGrpIdList) {
            ProductGrp tempProdGrp = new ProductGrp();

            Set prodAndPriceList = myProductRedis.opsForZSet().rangeWithScores(prodGrpId, 0, 9);
            Iterator<Object> prodPricObj = prodAndPriceList.iterator();

            while (prodPricObj.hasNext()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map prodPriceMap = objectMapper.convertValue(prodPricObj.next(), Map.class);
                Product tempProduct = new Product();
                tempProduct.setProductId(prodPriceMap.get("value").toString()); // prod_id
                tempProduct.setPrice(Double.valueOf(prodPriceMap.get("score").toString()).intValue()); //es 검색된 score
                tempProduct.setProductGrpId(prodGrpId);
                tempProdList.add(tempProduct);
            }

            tempProdGrp.setGroupGrpId(prodGrpId);
            tempProdGrp.setProductList(tempProdList);
            returnInfo.add(tempProdGrp);
        }

        return returnInfo;
    }
}
