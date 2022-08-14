package com.example.goodsserver.controller;

import com.example.goodsserver.annotation.RequestLimit;
import com.example.goodsserver.config.ApiRequestLimitConfig;
import com.example.service.api.api.GoodsApi;
import com.example.service.api.model.CommonResult;
import com.example.service.api.model.goods.GoodsInfo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GoodsController implements GoodsApi {


    @Autowired
    ApiRequestLimitConfig apiRequestLimitConfig;



    @Override
    public CommonResult<List<GoodsInfo>> listGoodsByIds(List<Long> ids) {
        if(ids==null&&ids.size()<=0){
            return CommonResult.error("1001","商品id不能为空");
        }
        var list = new ArrayList<GoodsInfo>();
        list.add(new GoodsInfo(){{
            setId(1l);
            setName("苹果");
            setPrice(BigDecimal.valueOf(2));
        }});
        list.add(new GoodsInfo(){{
            setId(2l);
            setName("牛奶");
            setPrice(BigDecimal.valueOf(3));
        }});
        list.add(new GoodsInfo(){{
            setId(3l);
            setName("面包");
            setPrice(BigDecimal.valueOf(8));
        }});
        list.add(new GoodsInfo(){{
            setId(4l);
            setName("米");
            setPrice(BigDecimal.valueOf(20));
        }});
        var result = list.stream().filter(l->ids.contains(l.getId())).collect(Collectors.toList());

        return CommonResult.success(result);
    }

    @Override
    @RequestLimit(value = 10,nacosKey = "goodsserver-search")
    public CommonResult<List<GoodsInfo>> search() {
        System.out.println(apiRequestLimitConfig.getLimit());
        var list = new ArrayList<GoodsInfo>();
        list.add(new GoodsInfo(){{
            setId(1l);
            setName("苹果");
            setPrice(BigDecimal.valueOf(2));
        }});
        list.add(new GoodsInfo(){{
            setId(2l);
            setName("牛奶");
            setPrice(BigDecimal.valueOf(3));
        }});
        list.add(new GoodsInfo(){{
            setId(3l);
            setName("面包");
            setPrice(BigDecimal.valueOf(8));
        }});
        list.add(new GoodsInfo(){{
            setId(4l);
            setName("米");
            setPrice(BigDecimal.valueOf(20));
        }});

        return CommonResult.success(list);
    }


}
