package com.utc2.petShop.utils;

import java.util.Arrays;

public class MathUtils {
    public static final float PI = 3.14f;
    public static int tongMang(int...a){
        return Arrays.stream(a).sum();
    }
    public static int hieuMang(int...a){
        int res = 0;
        for(int i=0; i<a.length; i++){
            res -= a[i];
        }
        return res;
    }
    public static int tich(int...a){
        int res = 0;
        for(int i=0; i<a.length; i++){
            res *= a[i];
        }
        return res;
    }
    public static int thuong(int...a){
        int res = 0;
        for(int i=0; i<a.length; i++){
            res /= a[i];
        }
        return res;
    }
    public static int Max(int...a){
        int res = 0;
        for(int i=0; i<a.length; i++){
            if (a[i] > res) {
                res = a[i];
            }
        }
        return res;
    }
    public static int Min(int...a){
        int res = 0;
        for(int i=0; i<a.length; i++){
            if (a[i] < res)
            res = a[i];
        }
        return res;
    }
    public static float chuViHinhChuNhat(float dai, float rong){
        return (dai+rong)/2;
    }
    public static float dienTichHinhChuNhat(float dai, float rong){
        return dai*rong;
    }
    public static float chuViHinhTron(float r){
        return 2*r*PI;
    }
    public static float dienTichHinhTron(float r){
        return r*r*PI;
    }
}
