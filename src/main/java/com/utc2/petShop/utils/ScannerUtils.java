package com.utc2.petShop.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtils {
    private static final Scanner sc = new Scanner(System.in);

    public static int nhapSoINT() {
        while (true) {
            try {
                int n = sc.nextInt();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số nguyên kiểu int");
                sc.nextLine();
            }
        }
    }

    public static int nhapSoINTLonHonHoacBangX(int x) {
        while (true) {
            try {
                int n = nhapSoINT();
                if (n < x) {
                    throw new InputMismatchException();
                }
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số nguyên kiểu int lớn hơn hoặc bằng " + x);
            }
        }
    }

    public static int nhapSoINTNhoHonHoacBangX(int x) {
        while (true) {
            try {
                int n = nhapSoINT();
                if (n > x) {
                    throw new InputMismatchException();
                }
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số nguyên kiểu int nhỏ hơn hoặc bằng " + x);

            }
        }
    }

    public static int nhapSoINTTuXDenY(int x, int y) {
        if (x > y) {
            int t = x;
            x = y;
            y = t;
        }
        while (true) {
            try {
                int n = nhapSoINT();
                if (n < x || n > y) {
                    throw new InputMismatchException();
                }
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số nguyên kiểu int trong khoảng [" + x + ";" + y + "]");
            }
        }
    }

    public static long nhapSoLONG() {
        while (true) {
            try {
                long n = sc.nextLong();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số nguyên kiểu long");
                sc.nextLine();
            }
        }
    }

    public static float nhapSoFLOAT() {
        while (true) {
            try {
                float n = sc.nextFloat();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số thực kiểu float");
                sc.nextLine();
            }
        }
    }

    public static float nhapSoFLOATTuXDenY(float x, float y) {
        if (x > y) {
            float t = x;
            x = y;
            y = t;
        }
        while (true) {
            try {
                float n = nhapSoFLOAT();
                if (n < x || n > y) {
                    throw new InputMismatchException();
                }
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số thực kiểu float trong khoảng [" + x + ";" + y + "]");
            }
        }
    }

    public static double nhapSoDOUBLE() {
        while (true) {
            try {
                double n = sc.nextDouble();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhâp số thực kiểu double");
                sc.nextLine();
            }
        }
    }

    public static double nhapSoDOUBLETuXDenY(double x, double y) {
        if (x > y) {
            double t = x;
            x = y;
            y = t;
        }
        while (true) {
            try {
                double n = nhapSoDOUBLE();
                if (n < x || n > y) {
                    throw new InputMismatchException();
                }
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Bạn phải nhập số thực kiểu double trong khoảng [" + x + ";" + y + "]");
            }
        }
    }

    public static void chuoiRong(String n) throws Exception {
        if (n.isEmpty()) {
            throw new Exception("Bạn không thể nhập chuỗi rỗng");
        }
    }

    public static String nhapChuoiSTRING() {
        while (true) {
            try {
                String s = sc.nextLine();
                chuoiRong(s);
                return s;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String nhapHoVaTen() {
        String s = nhapChuoiSTRING();
        s = s.trim();
        String[] arr = s.split("\\s+");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].toLowerCase();
            arr[i] = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
        }
        return String.join(" ", arr);
    }

    public static double phepChia(double a, double b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Bạn không thể chia cho 0");
            return 0.0;
        }
    }

    public static LocalDate nhapDATE() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral('/')
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral('/')
                .appendValue(ChronoField.YEAR, 4)
                .toFormatter();
        while (true) {
            String s = nhapChuoiSTRING();
            try {
                return LocalDate.parse(s, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Bạn nhập ngày tháng năm không đúng định dạng");
            }
        }
    }
}
