package com.LIGY.jobs;
/*
自定义Job，实现定时清理七牛云的垃圾图片
 */

import com.LIGY.constant.RedisConstant;
import com.LIGY.constants.JobSwitchConstants;
import com.LIGY.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;



public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public QiniuUtils qiniuUtils;

    @Scheduled(cron = "0 30 0 * * ?")
    public void clearImgSwitch(){
        clearImg();
    }

    public void clearImg(){
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片的名称合集；
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set != null){
            for (String picName : set) {
                //删除七牛云服务器上的图片
                qiniuUtils.deleteFileFromQiniu(picName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);

            }
        }
        System.out.println("垃圾图片已清理");
    }

    public Boolean getSwitch(){
        Jedis jedis = jedisPool.getResource();
        String flag = jedis.get(JobSwitchConstants.CLRAR_IMAGE_JOB_SWITCH);
        return Boolean.valueOf(flag);
    }

    public Boolean setSwitch(){
        Boolean flag;
        if (getSwitch() == null){
            flag=false;
        }else {
            flag=!getSwitch();
        }
        Jedis jedis = jedisPool.getResource();
        jedis.set(JobSwitchConstants.CLRAR_IMAGE_JOB_SWITCH,String.valueOf(flag));
        return flag;
    }

}
