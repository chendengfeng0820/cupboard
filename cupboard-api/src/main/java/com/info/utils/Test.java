package com.info.utils;

/**
 * @ClassName Test
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-23 10:53
 **/
public class Test {
    public static void main(String[] args) {
        laber:
        for (int i = 0; i < 10; i++) {
            laber2:
            for(int j= 1;j<10;j++){
                if (j > 5) {
                    break laber;
                }
                System.out.println(i);
            }


        }
    }
}
