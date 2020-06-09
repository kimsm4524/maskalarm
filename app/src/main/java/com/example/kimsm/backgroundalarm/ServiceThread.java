package com.example.kimsm.backgroundalarm;
import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class ServiceThread extends Thread{
    Handler handler;
    boolean isRun = true;

    public ServiceThread(Handler handler){
        this.handler = handler;
    }

    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run(){
        //반복적으로 수행할 작업을 한다.
        while(isRun){
            String url = "https://smartstore.naver.com/mdamayo/products/3803685971";
            Document doc=null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements able = doc.select("div.sum_total");


            if(able.isEmpty())  {
                System.out.println("구매불가능");
            }else{
                handler.sendEmptyMessage(0);
            }
            try{
                Thread.sleep(3000); //10초씩 쉰다.
            }catch (Exception e) {}
        }
    }
}