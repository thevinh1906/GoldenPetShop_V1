package com.utc2.petShop.utils;

import java.time.LocalDate;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        ScannerUtils sc = new ScannerUtils();
        LocalDate d = sc.nhapDATE();
        System.out.println(d.toString());
    }

}
