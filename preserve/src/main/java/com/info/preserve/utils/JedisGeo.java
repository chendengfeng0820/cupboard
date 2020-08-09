package com.info.preserve.utils;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * @ClassName JedisGeo
 * @Description TODO
 * @author: cdf
 * @Date: 2020-08-09 11:25
 **/
@Component
public class JedisGeo {

    //----------------------geo start------------------------------------------

    /**
     * 增加地理位置的坐标
     *
     * @param key
     * @param coordinate
     * @param member
     * @return Long
     */
    public Long geoadd(String key, GeoCoordinate coordinate, String member) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            return jedis.geoadd(key, coordinate.getLongitude(), coordinate.getLatitude(), member);
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

    /**
     * 批量添加地理位置
     *
     * @param key
     * @param memberCoordinateMap
     * @return Long
     */
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            return jedis.geoadd(key, memberCoordinateMap);
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     *
     * @param key
     * @param coordinate
     * @param radius
     * @return List<GeoRadiusResponse>
     */
    public List<GeoRadiusResponse> geoRadius(String key, GeoCoordinate coordinate, double radius) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            return jedis.georadius(key, coordinate.getLongitude(), coordinate.getLatitude(), radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

    /**
     * 根据给定地理位置获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     *
     * @param key
     * @param member
     * @param radius
     * @return List<GeoRadiusResponse>
     */
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            return jedis.georadiusByMember(key, member, radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

    /**
     * 查询2位置距离
     *
     * @param key
     * @param member1
     * @param member2
     * @param unit
     * @return Double
     */
    public Double geoDist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            Double dist = jedis.geodist(key, member1, member2, unit);
            return dist;
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

    /**
     * 查询位置的geohash
     *
     * @param key
     * @param members
     * @return List<String>
     */
    public List<String> geoHash(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            List<String> resultList = jedis.geohash(key, members);
            return resultList;
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

    /**
     * 获取地理位置的坐标
     *
     * @param key
     * @param member
     * @return List<GeoCoordinate>
     */
    public List<GeoCoordinate> geopos(String key, String... member) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            List<GeoCoordinate> result = jedis.geopos(key, member);
            return result;
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }


    /*
     * 添加geo
     * @param key
     * @param longitude
     * @param latitude
     * @param name 位置名称
     * @return
     */
    /*public  Long geoADD(String key,double longitude,double latitude,String eleName){
        String[] params = new String[]{key,String.valueOf(longitude),String.valueOf(latitude),eleName};
        int keyCount = 4;
        return (Long)jedis.eval("return redis.call('GEOADD',KEYS[1],KEYS[2],KEYS[3],KEYS[4])", keyCount,params);
    }*/

    /**
     * 查询2位置距离
     * @param key
     * @param d1
     * @param d2
     * @param unit
     * @return
     */
    /*public  Double geoDist(String key,String d1,String d2,String unit){
        return Double.valueOf((String)jedis.eval("return redis.call('GEODIST',KEYS[1],KEYS[2],KEYS[3],KEYS[4])",4, key,d1,d2,unit));
    }*/

    /**
     * 查询位置的geohash
     * @param key
     * @param dName
     * @return
     */
    /*public  String geoHash(String key,String dName){
        Object data = jedis.eval("return redis.call('GEOHASH',KEYS[1],KEYS[2])", 2, new String[]{key,dName});//GEOPOS 也可以？
        List resultList = (List)data;
        if(resultList!=null&&resultList.size() > 0){
            return (String)resultList.get(0);
        }
        return null;
    }*/

    /**
     * 查询位置坐标
     * @param key
     * @param dName
     * @return
     */
   /* public  List geoPos(String key,String dName){
        Object data = jedis.eval("return redis.call('GEOPOS',KEYS[1],KEYS[2])", 2, key,dName);
        List<List> resultList = (List<List>)data;
        if(resultList!=null&&resultList.size() > 0){
            return resultList.get(0);
        }
        return null;
    }*/

    /**
     * 查询附近坐标地址
     * @param key
     * @param longitude
     * @param latitude
     * @param unit
     * @param asc
     * @return
     */
    /*public  List geoRadius(String key,double longitude,double latitude,int radius,String unit,boolean asc){
        Object data = jedis.eval("return redis.call('GEORADIUS',KEYS[1],KEYS[2],KEYS[3],KEYS[4],KEYS[5],KEYS[6])", 6, key,String.valueOf(longitude),
                String.valueOf(latitude),String.valueOf(radius),unit,asc?"ASC":"DESC");
        return (List)data;
    }*/

    /**
     * 附近的成员们，根据距离
     *
     * 根据位置查询附近点
     * @param key
     * @param dName
     * @param unit
     * @param asc
     * @return
     */
   /* public  List geoNearByMembersByDistance(String key,String dName,int radius,String unit,boolean asc){
        Object data = jedis.eval("return redis.call('GEORADIUSBYMEMBER',KEYS[1],KEYS[2],KEYS[3],KEYS[4],KEYS[5])", 5, key,dName,String.valueOf(radius),unit,asc?"ASC":"DESC");
        return (List)data;
    }*/

    //------------------------------geo end---------------------------------------
}
