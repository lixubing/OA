package com.edu.oa;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tests {
    @Test
    public void test1(){
        String a = "q";
        a.concat("b");
        System.out.println(a);
    }
    @Test
    public void test2(){
        String a = "20210107";
        String b = "20210108";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String format1 = format.format(date);
        System.out.println(format1);
        System.out.println(a.compareTo(format1));
        System.out.println(b.compareTo(format1));
    }
    @Test
    public void test3(){
        StringBuilder sb = new StringBuilder();
        //sb.append("a").append(",").append("b").append(",");
        System.out.println(sb.indexOf(","));
        if (sb.indexOf(",") > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb.toString() + "]");
    }
    @Test
    public void test4(){
        String s = "001001|001002|";
        String[] split = s.split("\\|");
        for (String s1 : split) {
            System.out.println(s1);
        }
    }
    @Test
    public void test5(){
        List<Object> l = new ArrayList<>();
        for (Object s : l) {
            System.out.println(s.hashCode());;
        }

    }

    @Test
    public void test6() {
        String s = "0,1,2";
        System.out.println(s.contains("0"));

    }
}
