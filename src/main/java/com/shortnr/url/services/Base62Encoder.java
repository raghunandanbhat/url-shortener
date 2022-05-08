package com.shortnr.url.services;
/*
* Base-62 Encoding Algorithm Implementation
* Takes an integer and converts it into base 62 representation
* Returns a base 64 number of length 7
*
* */
public class Base62Encoder {
    //Characters [0-9,A-Z,a-z] randomly shuffled
    private static final String CHAR_SET = "a61jtWfde84BySLqxQgKl9DPn7uCA0icNk3hEXs2rRGIvm5UVMZoTwYOpbHFJz";
    private static final int BASE62 = CHAR_SET.length();

    public static String toBase64(long num){
        StringBuilder str = new StringBuilder();
        char[] cstr = {'0','0','0','0','0','0','0'};
        int str_len = 7;

        while(num != 0){
            str.append(CHAR_SET.charAt((int)(num % BASE62)));
            num /= BASE62;
            str_len--;
        }

        return str.append(cstr, 0, str_len).reverse().toString();
    }
}
