package edu.hw11;

import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;

public class FibonacciClassProvider {
    private static final String CLASS_NAME = "FibonacciCalculator";
    private static final String METHOD_NAME = "count";

    private FibonacciClassProvider() {

    }

    public static Class<?> getFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(METHOD_NAME, long.class, Modifier.PUBLIC)
            .withParameter(int.class, "number")
            .intercept(new Implementation.Simple(new FibonacciMethod()))
            .make()
            .load(FibonacciClassProvider.class.getClassLoader())
            .getLoaded();
    }
}
