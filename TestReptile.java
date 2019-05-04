package com.dikeni.reptile;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 *
 */
public class TestReptile {
    public static void main( String[] args ) throws Exception {
        String url = "http://www.cosplayla.com/picture";
        HashSet< String > img = getImg( url );

        //downLoadImg( img );
        img.clear();
    }

    //获取图片
    public static HashSet< String > getImg( String url ) throws Exception {
        ArrayList< String > list = new ArrayList< String >();
        HashSet< String > set = new HashSet< String >();
        Document document = null;
        Elements img = null;
        document = Jsoup.connect( url ).get();
        img = document.select( ".pic_kuang img" );
        Elements gpage = document.select( ".gpage a" );
        for ( Element element : gpage ) {
            list.add( element.text() );
        }
        //获取最后一页
        int LastPage = Integer.parseInt( list.get( list.size() - 2 ) );
        for ( int i = 1 ; i <= LastPage ; i++ ) {
            if ( i > 1 ) {
                document = Jsoup.connect( "http://cosplayla.com/picture/?page=" + i ).get();
                img = document.select( ".pic_kuang img" );
            }
            for ( Element imgElement : img ) {
                String src = imgElement.attr( "src" );
                //System.out.println( src );
                set.add( src );
            }
            System.out.println( "添加第" + i + "页成功" );
        }
        return set;
    }


    //下载图片
    public static void downLoadImg( HashSet< String > hashSet ) throws Exception {
         InputStream inputStream =  null ;
        FileOutputStream outputStream =  null ;
        String src = null;
        String imgName = null;
        if ( hashSet.size() != 0 ) {
            //创建iterator迭代器
            Iterator< String > iterator = hashSet.iterator();
            //遍历
            while ( iterator.hasNext() ) {
                //获取迭代器里的图片地址
                src = iterator.next();
                //处理图片地址，获得图片名
                imgName = src.substring( src.lastIndexOf( "/" ) + 1 );
                try {
                    //定义http协议核心类
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    //定义请求对旬并设置请求资源地址
                    HttpGet httpGet = new HttpGet( "http://www.cosplayla.com" + src );
                    //使用HttpClient打执行请求发送，执行会返回respose响应数据对象
                    CloseableHttpResponse response = httpClient.execute( httpGet );
                    //判断影响的http状态码为200代表能信过程正常
                    if ( response.getStatusLine().getStatusCode() == 200 ) {
                        HttpEntity entity = response.getEntity();
                        inputStream = entity.getContent();
                        outputStream= new FileOutputStream( new File( "E:\\javaTest\\Code\\reptile\\" + imgName ) );
                        int copy = IOUtils.copy( inputStream, outputStream );
                        System.out.println( "拷贝成功" + copy );
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
            if ( outputStream!= null ) {
                outputStream.close();
            }
            if ( inputStream != null ) {
                inputStream.close();
            }
        }
    }
}

