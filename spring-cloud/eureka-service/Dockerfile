FROM registry.cn-hangzhou.aliyuncs.com/liuxians/alpine-java:v1.0.0

EXPOSE 8679

ADD /target/eurekademo-0.0.1-SNAPSHOT.jar /usr/local/bin/eureka-service-demo/app.jar

#CMD ["java","-jar","/usr/local/bin/eureka-service-demo/app.jar","--server.port=8679","--EUREKA_SERVER=$EUREKA_SERVER","--EUREKA_INSTANCE_HOSTNAME=$EUREKA_INSTANCE_HOSTNAME"]
ENTRYPOINT [ "sh", "-c", "java  -jar /usr/local/bin/eureka-service-demo/app.jar","--EUREKA_SERVER=http://eureka1.360kad.com:30086/eureka/,http://eureka2.360kad.com:30086/eureka/",  "--EUREKA_INSTANCE_HOSTNAME=$EUREKA_INSTANCE_HOSTNAME" ]