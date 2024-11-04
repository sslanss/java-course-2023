package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class Analyzer {
    private static final int NUMBER_OF_FORKS = 1;
    private static final int NUMBER_OF_WARMUP_FORKS = 1;
    private static final int NUMBER_OF_WARMUP_ITERATIONS = 1;
    public static final int WARM_UP_TIME_SECONDS = 5;
    public static final int MEASUREMENT_TIME_MINUTES = 2;
    private Student student;
    private Method method;
    private MethodHandle handle;
    public static final String TESTED_METHOD_NAME = "name";
    private Function<Student, Object> function;

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws Throwable {
        Options options = new OptionsBuilder()
            .include(Analyzer.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(NUMBER_OF_FORKS)
            .warmupForks(NUMBER_OF_WARMUP_FORKS)
            .warmupIterations(NUMBER_OF_WARMUP_ITERATIONS)
            .warmupTime(TimeValue.seconds(WARM_UP_TIME_SECONDS))
            .measurementIterations(1)
            .measurementTime(TimeValue.minutes(MEASUREMENT_TIME_MINUTES))
            .build();

        new Runner(options).run();
    }

    @Setup
    public void setUp() throws Throwable {
        student = new Student("Name", "Surname");
        method = Student.class.getMethod(TESTED_METHOD_NAME);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class);
        handle = lookup.findVirtual(Student.class, TESTED_METHOD_NAME, methodType);

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            handle,
            MethodType.methodType(Object.class, Student.class)
        );

        function = (Function<Student, Object>) callSite.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(method.invoke(student));
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        bh.consume(handle.invoke(student));
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = (String) function.apply(student);
        bh.consume(name);
    }
}
