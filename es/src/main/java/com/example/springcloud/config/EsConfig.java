package com.example.sentinel.config;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

    private final static String HOST_IP = "127.0.0.1";
    private final static int PORT = 9200;
    private final static String PROTOCAL = "http";

    @Bean
    public RestHighLevelClient client() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost(HOST_IP, PORT, PROTOCAL)
        ));
        if (client!=null) {
            System.out.println("ESConfig connect springcloud success");
        }
        return client;
    }





}
