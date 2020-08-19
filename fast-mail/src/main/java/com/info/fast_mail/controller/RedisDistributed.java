package com.info.fast_mail.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.fast_mail.service.DistributedService;
import com.info.pojo.Board;
import com.info.pojo.Courier;
import com.info.pojo.Package;
import com.info.pojo.User;
import com.info.utils.SnowFlake;
import org.bouncycastle.util.Pack;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisDistributed
 * @Description TODO
 * @author: cdf
 * @Date: 2020-08-19 03:51
 **/
@RestController
public class RedisDistributed {

    @Autowired
    private Redisson redisson1;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private DistributedService distributedService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock(@RequestBody JSONObject jsonObject) {
        Courier courier   = JSONObject.parseObject(jsonObject.toString(), Courier.class);
        Package package1 = new Package();
        Long package_id = package1.getPackage_id();
        Long courier_id = courier.getCourier_id();

        String lockKKey = String.valueOf(snowFlake.nextId());
        RLock redisLock = redisson1.getLock(lockKKey);

//		Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKKey,"1"); //jedis.setnx(key,value)
//		stringRedisTemplate.expire(lockKKey,10, TimeUnit.SECONDS);
//		Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKKey,clientId,10,TimeUnit.SECONDS);
//		if (!result){
//			return "error";
//		}

        //只有一个线程能加锁，剩下的while
        redisLock.lock(30, TimeUnit.SECONDS);


        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，已被" + courier_id+ "抢到");
                distributedService.insertCourier_package(courier_id,package_id);
                return "恭喜您，抢到了订单";

            } else {
                System.out.println("对不起，订单已被抢走");
                return "对不起，订单已被抢走" ;
            }
        }finally {
            redisLock.unlock();
            //释放锁
//			if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKKey))){
//				stringRedisTemplate.delete(lockKKey);
//			}
        }

    }

}
