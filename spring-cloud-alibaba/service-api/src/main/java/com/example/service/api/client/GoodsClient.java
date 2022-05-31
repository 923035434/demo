package com.example.service.api.client;

import com.example.service.api.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "goods-service",
        contextId = "com.example.service.api.client.GoodsClient")
public interface GoodsClient extends GoodsApi {

}
