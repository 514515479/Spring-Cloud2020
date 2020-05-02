package com.zero.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tobi
 * @Date: 2020/5/2 19:13
 **/
@Configuration
public class GateWayConfig {

    //配置路由规则
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routers = builder.routes();

        //访问localhost:9527/guonei或转发到http://news.baidu.com/guonei
        routers.route("path_route_zero", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

        return routers.build();
    }
}
