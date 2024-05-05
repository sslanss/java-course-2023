package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    public void toStringMethodShouldReturnHelloWorld()
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String testString = "Hello World";

        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(testString))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        var instance = dynamicType.getConstructor().newInstance();

        Assertions.assertThat(instance.toString()).isEqualTo(testString);
    }
}
