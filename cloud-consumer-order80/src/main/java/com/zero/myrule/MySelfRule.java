package com.zero.myrule;

/**
 * @Author: tobi
 * @Date: 2020/4/11 14:52
 **/

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个Ribbon自定义配置类不能放在@ComponentScan所扫描的当钱包下以及子包下，
 * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的
 * */
@Configuration
public class MySelfRule {

    //修改负载均衡算法

    @Bean
    public IRule myRule() {
        /*轮询算法（默认）
         *
         * 第 N 次调用 % 总共 M 个服务集群 = S（余数）
         * 所以这次调用的就是集群中的第 S 个服务
         **/
        //return new RoundRobinRule();

        //随机算法
        return new RandomRule();

        //重试算法（先按照轮询获取，获取失败就重试）
        //return new RetryRule();

        //权重算法（响应速度越快，权重越大）
        //return new WeightedResponseTimeRule();

        //过滤掉多次访问失败而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
        //return new BestAvailableRule();

        //过滤掉故障的服务，选择并发量小的
        //return new AvailabilityFilteringRule();

        //默认规则，符合判断服务所在区域的性能和服务的可用性选择服务器
        //return new ZoneAvoidanceRule();
    }
}
