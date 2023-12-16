package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

public class RandomObjectGenerator {

    private Object nextObject(Class<?> classType)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = getLargestConstructor(classType);
        Object[] instanceParameters = generateParameters(constructor.getParameters());
        return constructor.newInstance(instanceParameters);
    }

    private Object nextObject(Class<?> classType, String methodName, Class<?>... params)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = classType.getMethod(methodName, params);
        Object[] instanceParameters = generateParameters(method.getParameters());
        return method.invoke(null, instanceParameters);
    }

    private Constructor<?> getLargestConstructor(Class<?> classType) {
        return Arrays.stream(classType.getConstructors())
            .max(Comparator.comparing(Constructor::getParameterCount))
            .orElse(null);
    }

    private Annotations wrapParameterAnnotations(Parameter param) {
        Double min = param.isAnnotationPresent(Min.class) ? param.getAnnotation(Min.class).value() : null;
        Double max = param.isAnnotationPresent(Max.class) ? param.getAnnotation(Max.class).value() : null;
        boolean notNull = param.isAnnotationPresent(NotNull.class);
        return new Annotations(min, max, notNull);
    }

    private boolean isWrappedPrimitive(Class<?> parameterType) {
        return parameterType == Boolean.class ||
            parameterType == Character.class ||
            parameterType == Byte.class ||
            parameterType == Short.class ||
            parameterType == Integer.class ||
            parameterType == Long.class ||
            parameterType == Float.class ||
            parameterType == Double.class;
    }

    private Object[] generateParameters(Parameter[] parameters) {
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> parameterType = parameters[i].getType();
            Annotations parameterAnnotations = wrapParameterAnnotations(parameters[i]);
            if (parameterType.isPrimitive()) {
                args[i] = PrimitiveAndWrappedTypesGenerator.generatePrimitive(parameterAnnotations, parameterType);
            }
            else if (isWrappedPrimitive(parameterType))
            {
                args[i] = PrimitiveAndWrappedTypesGenerator.generateWrappedPrimitive(parameterAnnotations, parameterType);
            }
            else {
                args[i] = ReferenceTypesGenerator.generateString(parameterAnnotations.notNull());
            }
        }
        return args;
    }

    public static void main(String[] args)
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        MyClass obj = (MyClass) rog.nextObject(MyClass.class);
        System.out.println(obj.getByteField());

        MyClass obj2 = (MyClass) rog.nextObject(MyClass.class, "create", byte.class,
            short.class, int.class, double.class, String.class
        );

        System.out.println(obj2.getByteField());
        System.out.println(obj2.getStringField());

    }
}
