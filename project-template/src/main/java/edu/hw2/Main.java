package edu.hw2;

import edu.hw2.task4.CallingInfoUtil;

import static edu.hw2.task4.CallingInfoUtil.callingInfo;

public class Main {

    public static void main(String []args){

        /*var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1)); */


        /*Rectangle rect = new Rectangle(20,30);
        rect = rect.setHeight(30);
        rect = rect.setWidth(20);
        System.out.println(rect.area());
        Rectangle square = new Square(10);
        square = square.setHeight(20);
        System.out.println(square.area());*/


        //PopularCommandExecutor ex = new PopularCommandExecutor( new DefaultConnectionManager(), 7);
        //ex.updatePackages();
        CallingInfoUtil.CallingInfo info = callingInfo();
        System.out.println("Calling class: " + info.className());
        System.out.println("Calling method: " + info.methodName());

    }

}
