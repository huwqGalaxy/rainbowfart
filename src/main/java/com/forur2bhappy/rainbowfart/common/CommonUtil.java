package com.forur2bhappy.rainbowfart.common;

import com.alibaba.fastjson.JSONObject;
import com.forur2bhappy.rainbowfart.model.IpInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class CommonUtil {
    public static String findIpAddress(HttpServletRequest request) {
        String ipAddress = "";
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    //根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
            return ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
            ipAddress = "127:0:0:1";
        }

        return ipAddress;
    }

    public static IpInfo findIpInfo(String ipAddress) {
        try {
            String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ipAddress;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //默认值我GET
            con.setRequestMethod("GET");

            //添加请求头
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            if (responseCode == 502) {
                try {
                    Thread.sleep(300L);
                    return findIpInfo(ipAddress);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Sending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //打印结果
            System.out.println(response.toString());
            return parseResponse(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IpInfo parseResponse(String response) {
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONObject dataJson = jsonObject.getJSONObject("data");
//        IpInfo parse = JSONObject.parse(dataJson, IpInfo.class);
        IpInfo ipInfo = JSONObject.parseObject(String.valueOf(dataJson), IpInfo.class);
        System.out.println(ipInfo);
        return ipInfo;
    }
}
