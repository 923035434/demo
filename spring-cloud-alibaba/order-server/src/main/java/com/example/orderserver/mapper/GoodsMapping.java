package com.example.orderserver.mapper;

import com.example.orderserver.model.GoodsVO;
import com.example.service.api.model.goods.GoodsInfo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper//(componentModel = "spring")
public interface GoodsMapping extends BaseListMapping<GoodsInfo, GoodsVO> {
}
