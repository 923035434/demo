package com.example.sentinel;
import com.example.sentinel.dao.es.ProductDao;
import com.example.sentinel.dao.model.Product;
import lombok.var;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.time.Instant;
import java.util.Arrays;

@SpringBootTest
class EsApplicationTests {


    @Autowired
    ProductDao productDao;


    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Test
    public void insertTest(){
        var product1 = new Product();
        product1.setProductName("2020新款中年妈妈秋装风衣");
        product1.setBrandName("胖织缘福");
        product1.setCategoryName("女式风衣");
        product1.setId(1l);
        product1.setDescription("胖织缘福是知名的时尚中老年女装品牌，注重个性文化又不过分张扬，推崇时尚简约、独特个性设计风格，通过胖织缘福把自信、魅力潇洒淋漓尽致的展现出来。");
        product1.setUtime(Instant.now().getEpochSecond());
        var product2 = new Product();
        product2.setProductName("戈美其2020春季新款漆皮水钻单鞋女低跟复古舒适休闲英伦风小皮鞋");
        product2.setBrandName("戈美其");
        product2.setCategoryName("女式单鞋");
        product2.setId(2l);
        product2.setDescription(
                "“戈美其”女鞋成立于1990年，以“追求卓越品质、缔造名优品牌”为宗旨理念。款式时尚，脚感舒适，品质优良\n" + "发货地：浙江温州  发货物流：截单后24小时内顺丰快递发货");
        product2.setUtime(Instant.now().getEpochSecond());
        var product3 = new Product();
        product3.setProductName("【疫情地区不发货】Conniekids【疯狂秒杀】【随机4条装】女童纯棉螺纹吊带");
        product3.setBrandName("Conniekids");
        product3.setCategoryName("女童背心");
        product3.setId(3l);
        product3.setDescription(
                "CONNIEKIDS品牌总部设在英国伦敦，专注研发设计适合各国成长期儿童的贴身内衣内裤家居服等。品牌创始人热爱环球旅行，关注儿童健康成长，成立慈善基金。品牌每卖出一件衣服就会捐出0.01英镑。");
        product3.setUtime(Instant.now().getEpochSecond());
        var product4 = new Product();
        product4.setProductName("隽达卷边V领针织打底毛衣【疫情地区不发货】");
        product4.setBrandName("隽达");
        product4.setCategoryName("女式毛衣");
        product4.setId(4l);
        product4.setDescription("本次活动以春装为主，产品以时尚化、舒适化、精致化的设计理念。\n" + "隽达专为25-45岁成熟、高贵、讲究品位的都市女性设计。\n" +
                "秉承“都市轻熟女之家”的品牌核心。产品选用中高端面料，手感舒适，质地柔软。");
        product4.setUtime(Instant.now().getEpochSecond());
        var product5 = new Product();
        product5.setProductName("吉俪 加厚保暖牛奶绒魔法绒毛毯珊瑚绒毯子空调午睡毯");
        product5.setBrandName("吉俪");
        product5.setCategoryName("毛毯");
        product5.setId(5l);
        product5.setDescription(
                "吉俪家纺对商品的挑选，检验标准均为最高标准，能保证产品质量，服务和信誉；在产品品类的开发上，加强新技术的应用；产品品质上关注每一个细节，做到精益求精； 致力于为您创造高品质、健康舒适的家居生活和睡眠环境。");
        product5.setUtime(Instant.now().getEpochSecond());
        var result =  productDao.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
        System.out.println(result);
    }

    @Test
    public void daoSearchTest(){
        var list = productDao.findAll();
        System.out.println(list);
    }

    @Test
    public void searchTest(){
        int page = 1,size=10;
        var query = new NativeSearchQuery(
                new MultiMatchQueryBuilder("家纺")
                        .field("productName", 0.5f).field("description", 0.1f)
                        .field("brandName", 1).field("categoryName", 0.2f))
                        .setPageable(PageRequest.of(page < 0 ? 0 : page - 1, Math.max(1, Math.min(100, size))));
        var result =restTemplate.search(query, Product.class);
        System.out.println(result);
    }


}
