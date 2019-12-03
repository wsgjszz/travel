package cn.OJ;

import java.io.BufferedReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num=sc.nextInt();
            sb.append(num);
        }
        System.out.println(sb);

    }
}
