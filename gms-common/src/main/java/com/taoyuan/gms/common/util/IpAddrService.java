package com.taoyuan.gms.common.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Service
public class IpAddrService {

    private static String dbPath = "/GeoLite2-City/GeoLite2-City.mmdb";

    private static DatabaseReader reader;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        try {
            String path = env.getProperty("geolite2.city.db.path");
            if (!StringUtils.isEmpty(path)) {
                dbPath = path;
            }

            Resource resource = new ClassPathResource(dbPath);
            File database = resource.getFile();
            reader = new DatabaseReader.Builder(database).build();
        } catch (Exception e) {
            log.error("IP地址服务初始化异常:", e);
        }
    }

    public String getCity(String ipAddr) {
        String cityName = "";
        if (StringUtils.isEmpty(ipAddr)) {
            return cityName;
        }

        try {
            InetAddress ipAddress = InetAddress.getByName(ipAddr);
            CityResponse response = reader.city(ipAddress);
            City city = response.getCity();
            cityName = city.getNames().get("zh-CN");
        } catch (UnknownHostException e) {
            log.error("获取地址异常:", e);
        } catch (GeoIp2Exception e) {
            log.error("获取地址异常:", e);
        } catch (IOException e) {
            log.error("获取地址异常:", e);
        }

        return cityName;
    }
}
