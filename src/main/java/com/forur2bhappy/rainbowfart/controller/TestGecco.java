package com.forur2bhappy.rainbowfart.controller;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://chp.shadiao.app/api.php", pipelines="consolePipeline")
public class TestGecco implements HtmlBean {

    private static final long serialVersionUID = -7127412585200687225L;

//    @RequestParameter("user")
//    private String user;//url中的{user}值
//
//    @RequestParameter("project")
//    private String project;//url中的{project}值

    @Text
    @HtmlField(cssPath="body")
    private String body;//抽取页面中的star

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static void main(String[] args) {
        GeccoEngine.create()
                //工程的包路径
                .classpath("com.geccocrawler.gecco.demo")
                //开始抓取的页面地址
                .start("https://github.com/xtuhcy/gecco")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                //循环抓取
                .loop(true)
                //使用pc端userAgent
                .mobile(false)
                //非阻塞方式运行
                .start();
    }
}
