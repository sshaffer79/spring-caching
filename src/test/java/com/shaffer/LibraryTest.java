package com.shaffer;

import com.shaffer.model.Object;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void test() {
        List<Object> list = Arrays.asList(
                new Object("123", "Temp 123"),
                new Object("456", "Temp 456"),
                new Object("789", "Temp 789"),
                new Object("111", "Temp 111")
        );
    }
}
