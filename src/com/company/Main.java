package com.company;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.PrintWriter;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) throws IOException {
    Connection();
    }

    public static void Connection() throws IOException  {
        PrintWriter zapis = new PrintWriter("kurs.txt");
        Connection connect = Jsoup.connect("https://newsapi.org/v2/top-headlines?country=pl&category=business&apiKey=7e5cdfd037c6474a803d7c379506026c");
        Document document = connect.ignoreContentType(true).get();
        Elements tekst = document.select("body");

        String txt = tekst.text();
        txt = txt.replace("{", "");
        txt = txt.replace("}", "");
        String tab[] = txt.split("title");
        String newTab = "";
        String newTab2 = "";
        String newTab3 = "";
        for(int i =1; i<tab.length; i++){
            newTab += tab[i];

        }
        String tab2[] = newTab.split("description");
        for(int i =1; i<tab2.length; i++){
            newTab2 += tab2[i];

        }
        String tab3[] = newTab2.split("author");
        for(int i =1; i<tab3.length; i++){
            newTab3 += tab3[i];

        }
        char chaR = (char)34;
        String resultTab[] = newTab3.split(":");
        String a = "";
        String b= "";
        String c = "";
        String[] save = new String[6];
        int counter =0;
        for(int i =1; i<20; i++){
            if(i%16 == 0 || i == 2){
                save[counter] = (" title: \t" + (resultTab[i].replace(chaR, ' ') + " "));
                counter++;
            }
            if(i%17 == 0 || i == 3){
                save[counter] =(" description: \t" +(resultTab[i].replace(chaR, ' ')+ " ")+ " ");
                counter++;
            }
            if(i%15 == 0 || i == 1){
                save[counter] =(" author: \t" +(resultTab[i].replace(chaR, ' ')+ "\n"));
                counter++;

            }
        }
        String phrase = "";
        for (int i = save.length-1; i>=0; i--  ){
            phrase += (save[i]);

            if(phrase.contains("author")){
                zapis.println(phrase);
                phrase = "";
            }
    }
            zapis.close();

}}

