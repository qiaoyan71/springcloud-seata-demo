package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyTest01 {

    public static boolean squareIsWhite(String coordinates) {
        if(coordinates==null || coordinates.length()!=2){
            return false;
        }
        int a = coordinates.charAt(0);
        int b = coordinates.charAt(1);
        return (a+b)%2==1;
    }
    /**
        4
            1
            2
     */

    public static String convert(String s, int numRows) {
        if(s==null) return null;
        if(numRows < 2) return s;
        List<StringBuffer> buffers = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            buffers.add(new StringBuffer(""));
        }

        // loop and set
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            buffers.get(i).append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }

        // reloop
        StringBuffer result = new StringBuffer("");
        for (StringBuffer buffer : buffers) {
            result.append(buffer);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String result = convert("AB", 1);
        System.out.println(result);
        // PAHNAPLSIIGYIR
    }
}
