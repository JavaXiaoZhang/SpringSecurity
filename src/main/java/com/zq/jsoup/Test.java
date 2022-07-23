package com.zq.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {
    public static void main(String[] args) throws IOException {
        String url = "https://zhuanlan.zhihu.com/p/259833884";
        Document document = Jsoup.parse(new URL(url), 10000);
        Element element = document.getElementById("root");
//        Elements element = document.getElementsByClass("root");
        Elements imgs = element.getElementsByTag("img");
        int i = 0;
        for (Element img : imgs) {
            String src = img.attr("src");
            if (src.startsWith("http")) {
                InputStream inputStream = new URL(src).openConnection().getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\zzqq\\Desktop\\imgs\\" + i++ + ".jpg");
                byte[] bytes = new byte[1024];
                while (inputStream.read(bytes, 0, 1024) != -1) {
                    fileOutputStream.write(bytes);
                }
                inputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
    }
}
