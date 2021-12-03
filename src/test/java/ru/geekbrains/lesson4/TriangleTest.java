package ru.geekbrains.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {



    @Test
    void positiveSquareCalculationTest() {
        Triangle triangle = new Triangle(15, 20, 30);
        double actualResult = triangle.calculateTriangleSquare();
        assertEquals(133.31705629813464, actualResult);
    }

    @Test
    void illegalArgumentException1Test(){
        Triangle triangle = new Triangle(5,6,40);
        Assertions.assertThrows(Exception.class,() -> triangle.calculateTriangleSquare() );
    }

    @Test
    void  illegalArgumentException2Test (){
        Triangle triangle = new Triangle(5,6,-4);
        Assertions.assertThrows(Exception.class,() -> triangle.calculateTriangleSquare() );
    }


    public static Stream<Arguments> forTrianglePatameterTest() {
       return  Stream.of(Arguments.of(new Triangle(4,6,8),11.61895003862225),
               Arguments.of(new Triangle(4,10,8),15.198684153570664),
                       Arguments.of(new Triangle(15,20,30),133.31705629813464));
    }

    @ParameterizedTest
    @MethodSource("forTrianglePatameterTest")
    void triangleParameterTest(Triangle triangle, double expectedResult){
        double actualResult = triangle.calculateTriangleSquare();
        assertEquals(expectedResult, actualResult);
    }

}
