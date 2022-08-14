package com.example.service.api.api;


import com.example.service.api.model.CommonResult;
import com.example.service.api.model.goods.GoodsInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/api/goods")
public interface GoodsApi {


    @PostMapping(value = "/listGoodsByIds")
    CommonResult<List<GoodsInfo>> listGoodsByIds(@RequestBody List<Long> ids);


    @GetMapping(value = "/search")
    CommonResult<List<GoodsInfo>> search();


}
