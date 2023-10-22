package edu.hw2;

import edu.hw2.task4.CallingInfoUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task4Test {

    static class AnotherTestClass {
        public static CallingInfoUtil.CallingInfo getInfo() {
            return CallingInfoUtil.callingInfo();
        }
    }
    @Test
    public void testGetInfoShouldReturnCorrectInformation() {
        CallingInfoUtil.CallingInfo callingInfo = CallingInfoUtil.callingInfo();
        assertNotNull(callingInfo);
        Assertions.assertThat(callingInfo.className()).isEqualTo("edu.hw2.Task4Test");
        Assertions.assertThat(callingInfo.methodName()).isEqualTo("testGetInfoShouldReturnCorrectInformation");

        callingInfo = AnotherTestClass.getInfo();
        assertNotNull(callingInfo);
        Assertions.assertThat(callingInfo.className()).isEqualTo("edu.hw2.Task4Test$AnotherTestClass");
        Assertions.assertThat(callingInfo.methodName()).isEqualTo("getInfo");
    }

}
