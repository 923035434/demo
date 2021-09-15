package com.example.springcloud.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;


@Document(indexName = "demo-product", refreshInterval = "5s")
@Data
@NoArgsConstructor
public class Product {
    @Id
    private Long id;

    //商品名
    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart"), otherFields = @InnerField(type = FieldType.Keyword, suffix = "raw", ignoreAbove = 256))
    private String productName;

    //商品描述
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String description;

    //数据更新时间
    @Field(type = FieldType.Date_Nanos, index = false)
    private Long utime;

    //品牌名称
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String brandName;

    //分类名称
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String categoryName;

    //预配置索引的_class字段
    @Field(name = "_class", type = FieldType.Keyword, index = false)
    @JsonIgnore
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private String clazz;
}
