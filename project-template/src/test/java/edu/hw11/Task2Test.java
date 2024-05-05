package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    public void classesBehaviourShouldBeModified() {
        int a = 1;
        int b = 3;

        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.ArithmeticUtils")
            .resolve();
        new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(ElementMatchers.named("sum"))
            .intercept(MethodCall.invoke(typeDescription.getDeclaredMethods().getLast()).withAllArguments())
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        var instance = new ArithmeticUtils();
        int result = instance.sum(a, b);
        System.out.println(result);

        Assertions.assertThat(result).isEqualTo(a * b);
    }

}
