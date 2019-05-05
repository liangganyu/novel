package com.dikeni.reptile;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReptileTest {
    public static void main(String[] args) throws Exception {
        String URL = "http://www.cosplayla.com/picture";
        List<String> list = getPageURL(URL);
        List<String> imgURL = getImgURL(list);
        downImg(imgURL);
    }
    /*获取所有的贴子的链接*/
    public static List<String> getPageURL(String URL) throws Exception {

        List<String> list = new ArrayList<String>();
        Document document = null;
        document = Jsoup.connect(URL).get();
        Elements elements = null;
        //获取第1页贴子的链接
        elements = document.select(".pic_kuang a");
        //处理页面元素，获取最后一页
        Elements gpage = document.select(".gpage a");
        for (Element element : gpage) {
            list.add(element.text());
        }
        //获取最后一页
        int LastPage = Integer.parseInt(list.get(list.size() - 2));
        list.clear();
        //循环遍历获取每一页的贴子的链接
        for (int i = 1; i <= LastPage; i++) {
            if (i > 1) {
                document = Jsoup.connect("http://cosplayla.com/picture/?page=" + i).get();
                elements = document.select(".pic_kuang a");
            }
            for (Element element : elements) {
                String href = element.attr("href");
                list.add("http://www.cosplayla.com" + href);
            }
            System.out.println("添加第" + i + "页成功");
        }
        return list;
    }

    /*获取所有的贴子的所有图片链接*/
    public static List<String> getImgURL(List<String> list)throws Exception {
        List<String> listImg = new ArrayList<String>();
        for (String s : list) {
            //System.out.println(s);
            Document document = null;
            document = Jsoup.connect(s).get();
            Elements select = document.select(".p a");
            for (Element element : select) {
                Elements imgs = element.select("a img");
                String src = imgs.attr("src");
                //System.out.println(src);
                if (src.length() > 10) {
                    listImg.add(src);
                    System.out.println("获取图片src：" + src + "成功");
                }
            }
        }
        return listImg;
    }

    /*抓取图片到本地*/
    public static void downImg(List<String> list) throws Exception {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        for (String imgSrc : list) {
            //获得图片名
            String imgName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
            //创建连接
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //设置超时连接
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
            //创建连接对象
            HttpGet httpGet = new HttpGet("http://www.cosplayla.com" + imgSrc);
            //配置连接超时
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                outputStream = new FileOutputStream(new File("D:\\code\\reptile\\" + imgName));
                if (IOUtils.copy(inputStream, outputStream) > 0) {
                    System.out.println("拷贝成功" + imgName);
                }
                inputStream.close();
                outputStream.close();
            }
        }
    }
}
