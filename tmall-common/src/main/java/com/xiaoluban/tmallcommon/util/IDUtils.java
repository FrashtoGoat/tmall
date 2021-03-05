package com.xiaoluban.tmallcommon.util;

import cn.hutool.core.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.Inet4Address;
import cn.hutool.core.util.RandomUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

/**
 * @author txb
 * @date 2021/3/5 10:49
 */
@Slf4j
public class IDUtils {

    //长度为5位
    private static long workerIdBits = 5L;
    private static long datacenterIdBits = 5L;
    //最大值
    private static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private static Long workId;
    private static Long datacenterId;

    static {
        try {
            workId=getWorkId();
            datacenterId=getDatacenterId(maxDatacenterId);
        } catch (Exception e) {
            throw new RuntimeException("初始化datacenterId异常",e);
        }
    }

    private static Long getWorkId() throws UnknownHostException {
        String hostAddress = Inet4Address.getLocalHost().getHostAddress();
        String[] ints = hostAddress.split(".");
        int sums = 0;
        for(String s : ints){
            sums += Integer.parseInt(s);
        }
        return (long)(sums % 32);
    }

    protected static long getDatacenterId(long maxDatacenterId) throws Exception{
        long datacenterId;
        InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        if (network == null) {
            datacenterId = 1L;
        } else {
            byte[] mac = network.getHardwareAddress();
            datacenterId = ((0x000000FF & (long) mac[mac.length - 1])
                    | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            datacenterId = datacenterId % (maxDatacenterId + 1);
        }
        return datacenterId;
    }

    public static long getNextIdBySnow(){
        return new Snowflake(workId,datacenterId).nextId();
    }



//    public static void main(String[] args) {
//        System.out.println(workId);
//        System.out.println(datacenterId);
//    }

}
