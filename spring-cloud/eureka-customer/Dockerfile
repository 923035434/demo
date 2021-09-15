FROM registry.cn-hangzhou.aliyuncs.com/liuxians/alpine-java:v1.0.0

EXPOSE 80

ADD /target/eureka-customer-0.0.1-SNAPSHOT.jar /usr/local/bin/eureka-customer/app.jar

ENTRYPOINT [ "sh", "-c", "java  -jar /usr/local/bin/eureka-customer/app.jar"]