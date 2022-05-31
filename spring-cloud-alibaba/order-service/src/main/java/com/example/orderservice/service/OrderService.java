package com.example.orderservice.service;


import com.example.orderservice.mapper.GoodsMapping;
import com.example.orderservice.model.CreateOrderItem;
import com.example.service.api.client.GoodsClient;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    GoodsClient goodsClient;

    @Autowired
    GoodsMapping goodsMapping;

    public String createOder(List<CreateOrderItem> param){
        var pMap = param.stream().collect(Collectors.toMap(s->s.getGoodsId(), Function.identity()));
        var ids = param.stream().map(p->p.getGoodsId()).collect(Collectors.toList());
        var result = goodsClient.listGoodsByIds(ids);
        if(result.isSuccess()){
            var goodsList = result.getData();
            var mapList = goodsMapping.toList(goodsList);
            var amount = BigDecimal.ZERO;
            if(goodsList!=null&&goodsList.size()>0){
                for (var g:goodsList){
                    var pItem = pMap.get(g.getId());
                    if(pItem!=null){
                        amount = amount.add(g.getPrice().multiply(BigDecimal.valueOf(pItem.getQty())));
                    }
                }
            }
            return "订单总金额："+amount;
        }else {
            return result.getErrorMessage();
        }
    }



}
