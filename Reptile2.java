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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.regex.Pattern;


public class Reptile2 {

    public static void main( String[] args ) throws Exception {

        String URL = "http://www.cosplayla.com/picture";
        String src = null;

        Document document = Jsoup.connect( URL ).get();
        Elements elements = document.select( ".pic_kuang a" );
        /*<a href="/picture/picture-nr-3083.html" target="_blank">
        <img src="/picup/cospic/201904/20190428192515_pc_3371_40715_200.jpg" width="200" height="300"></a>
        <a href="/picture/picture-nr-3083.html" target="_blank">七秀cos</a>*/
        for ( Element element : elements ) {
            String href = element.attr( "href" );
            //Elements img = element.select( "img" );
            System.out.println(href);
            /*for ( Element imgElement : img ) {
                String imgUrl = imgElement.attr( "src" );
                //获得图片名
                String imgName = imgUrl.substring( imgUrl.lastIndexOf( "/" ) + 1 );
                src = imgUrl ;
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet( "http://www.cosplayla.com"+src );
                CloseableHttpResponse response = httpClient.execute( httpGet );
                if ( response.getStatusLine().getStatusCode() == 200 ) {
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    OutputStream outputStream = new FileOutputStream( new File( "E:\\javaTest\\Code\\reptile\\" +new Date().getTime()+".jpg" ) );
                    int copy = IOUtils.copy( inputStream, outputStream );
                    Thread.sleep( 500 );
                    System.out.println("拷贝成功"+copy);
                    inputStream.close();
                    outputStream.close();
                }
            }*/
        }
    }
}
