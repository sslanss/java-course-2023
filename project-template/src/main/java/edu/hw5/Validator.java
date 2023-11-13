package edu.hw5;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static Pattern passwordPattern = Pattern.compile("[~!@#$%^&*|\\^]");
    public static Pattern licensePlatePattern = Pattern.compile("^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}$");
    //содержит не менее 3 символов, причем третий символ равен 0
    public static Pattern pattern1 = Pattern.compile("^[01]{2}[0][01]*$");


    //начинается и заканчивается одним и тем же символом |^.*$
    public static Pattern pattern2 = Pattern.compile("^([0|1])([0|1]*?\\1$)?$");

    //длина не менее 1 и не более 3
    public static Pattern pattern3 = Pattern.compile("^[0|1]{1,3}$");

    //каждый нечетный символ равен 1

    public static Pattern pattern4 = Pattern.compile("^0*?!(11(1)?)[0|1]*$");


    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину

    //количество 0 кратно 3
    // любая строка, кроме 11 или 111
    //каждый нечетный символ равен 1
    //содержит не менее двух 0 и не более одной 1
    //нет последовательных 1
    public static void main(String []args){
        //Pattern session = Pattern.compile("([20]1|2\\d-0[1-9]|1[0-2]-0[1-9]|[1,2][0-9]|3[0,1])")
        Matcher matcher = pattern4.matcher("1");
        System.out.println(matcher.find());
    }

}
