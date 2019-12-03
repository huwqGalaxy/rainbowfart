package com.forur2bhappy.rainbowfart.common;

import com.alibaba.fastjson.JSONObject;
import com.forur2bhappy.rainbowfart.controller.RainBowFartController;
import com.forur2bhappy.rainbowfart.model.IpInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(RainBowFartController.class);
    public static String findIpInfo() {
        try {
            String url = "https://chp.shadiao.app/api.php";
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
                    findIpInfo();
//                    return;
//                    return findIpInfo(ipAddress);
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
            return response.toString();
//            return parseResponse(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args){
////        String str = findIpInfo();
////        writeIn(str);
//        List<String> strings = readIn();
//        String str ="我有点花心，喜欢每一个你";
//        boolean contains = strings.contains(str);
//        System.out.println(contains);

        while (true) {
            //        1.读取已有的存入List
            List<String> strings = readIn();
            //        2.发送请求
            String str = findIpInfo();
            //        3.若List中已有则跳过，若y无则添加到List中并写入
            if (!strings.contains(str)) {
                strings.add(str);
                writeIn(str);
            }
        }

    }
    public static void writeIn(String str){
        try {
            String newstr = "\r\n"+str;
            String filePath = "src/main/resources/rainbow.txt";
            File file=new File(filePath);
            FileUtils.writeStringToFile(file,newstr,"UTF-8",true);
            System.out.println("写入一条新数据： ["+str+"]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> readIn(){
        try {
            String filePath = "src/main/resources/rainbow.txt";
            File file=new File(filePath);
//            FileUtils.writeLines(file,new ArrayList<String>(),true);
//            FileUtils.writeStringToFile(file,str,"UTF-8",true);
            List<String> strings = FileUtils.readLines(file, "UTF-8");
//            System.out.println(strings);
            return strings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
