package edu.hw2.task4;

public class CallingInfoUtil {
    public record CallingInfo(String className, String methodName) {
    }

    public static CallingInfo callingInfo() {
        Throwable stackTrace = new Throwable();
        StackTraceElement[] elements = stackTrace.getStackTrace();
        String className = elements[1].getClassName();
        String methodName = elements[1].getMethodName();
        return new CallingInfo(className, methodName);
    }
}
