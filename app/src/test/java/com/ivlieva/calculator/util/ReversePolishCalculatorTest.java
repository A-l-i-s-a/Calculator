package com.ivlieva.calculator.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReversePolishCalculatorTest {

    @Test
    public void calculate() {
        ReversePolishCalculator polishCalculator = new ReversePolishCalculator();
        assertEquals(9, polishCalculator.reversePolishNotation(polishCalculator.getArr("5 + 4")), 0.00001);
        assertEquals(7, polishCalculator.reversePolishNotation(polishCalculator.getArr("5 + 4 / 2")), 0.00001);
        assertEquals(726, polishCalculator.reversePolishNotation(polishCalculator.getArr("( 13 + 1 ) * 55 - 4 / 2 * ( 33 - 11 )")), 0.00001);
        assertEquals(-704, polishCalculator.reversePolishNotation(polishCalculator.getArr("( 1 - 13 ) * 55 - 4 / 2 * ( 33 - 11 )")), 0.00001);

        assertEquals(2, polishCalculator.reversePolishNotation(polishCalculator.getArr(" √[ 1 + 3 ]")), 0.00001);
        assertEquals(2, polishCalculator.reversePolishNotation(polishCalculator.getArr("lg[ 100 ]")), 0.00001);
        assertEquals(4.007333, polishCalculator.reversePolishNotation(polishCalculator.getArr("ln[ 1 + 3 * ( 23 - 5 ) ]")), 0.00001);
        assertEquals(1.470038, polishCalculator.reversePolishNotation(polishCalculator.getArr("tg[ 1 + 3 * ( 23 - 45 ) ]")), 0.00001);
        assertEquals(-0.562454, polishCalculator.reversePolishNotation(polishCalculator.getArr("cos[ 1 + 3 * ( 23 - 45 ) ]")), 0.00001);
        assertEquals(-0.826829, polishCalculator.reversePolishNotation(polishCalculator.getArr("sin[ 1 + 3 * ( 23 - 45 ) ]")), 0.00001);
    }
}