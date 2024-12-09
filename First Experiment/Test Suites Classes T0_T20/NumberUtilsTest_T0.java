/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 this is T0
 */
package org.apache.commons.lang3.math;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.apache.commons.lang3.AbstractLangTest;

import org.junit.jupiter.api.Test;


import java.io.FileWriter; 
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/*import org.junit.jupiter.api.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Test;
*/


/**
 * Unit tests {@link org.apache.commons.lang3.math.NumberUtils}.
 */
public class NumberUtilsTest extends AbstractLangTest {
    public static int test_count;
	public static FileWriter results_writer;
	private  static  NumberUtils model;

   
//
   @BeforeEach
	public void setUp(){
		model = new NumberUtils();
	}
	
	@BeforeAll
	public static void setUpWriter(){
		try {
		results_writer = new FileWriter("./test_results/T0.txt");
		} catch (IOException e) {
		}
		test_count=1;
	}
	public static void printTestOutput(int line_number,  Object output){
		try {
		results_writer.write(line_number+", "+output);
		results_writer.write("\n");
		results_writer.flush();
		
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		//test_count++;
	}
	
	
	
	private boolean checkCreateNumber(final String val) {
		
        try {
            final Object obj = NumberUtils.createNumber(val);
		  
            return obj != null;
        } catch (final Exception e) {
            return false;
        }
    }

   /* @Test
    public void compareByte() {
        assertTrue(NumberUtils.compare((byte) -3, (byte) 0) < 0);
        assertEquals(0, NumberUtils.compare((byte) 113, (byte) 113));
        assertTrue(NumberUtils.compare((byte) 123, (byte) 32) > 0);
    }

    @Test
    public void compareInt() {
        assertTrue(NumberUtils.compare(-3, 0) < 0);
        assertEquals(0, NumberUtils.compare(113, 113));
        assertTrue(NumberUtils.compare(213, 32) > 0);
    }

    private void compareIsCreatableWithCreateNumber(final String val, final boolean expected) {
        final boolean isValid = NumberUtils.isCreatable(val);
        final boolean canCreate = checkCreateNumber(val);// try 2
		
	

        assertTrue(isValid == expected && canCreate == expected, "Expecting " + expected
            + " for isCreatable/createNumber using \"" + val + "\" but got " + isValid + " and " + canCreate);
    }

    @SuppressWarnings("deprecation")
    private void compareIsNumberWithCreateNumber(final String val, final boolean expected) {
        final boolean isValid = NumberUtils.isNumber(val);
        final boolean canCreate = checkCreateNumber(val);// try1
        assertTrue(isValid == expected && canCreate == expected, "Expecting " + expected
            + " for isNumber/createNumber using \"" + val + "\" but got " + isValid + " and " + canCreate);
    }

    @Test
    public void compareLong() {
        assertTrue(NumberUtils.compare(-3L, 0L) < 0);
        assertEquals(0, NumberUtils.compare(113L, 113L));
        assertTrue(NumberUtils.compare(213L, 32L) > 0);
    }

    @Test
    public void compareShort() {
        assertTrue(NumberUtils.compare((short) -3, (short) 0) < 0);
        assertEquals(0, NumberUtils.compare((short) 113, (short) 113));
        assertTrue(NumberUtils.compare((short) 213, (short) 32) > 0);
    }

    /**
     * Test for {@link NumberUtils#toDouble(BigDecimal)}
     */
   /* @Test
    public void testBigIntegerToDoubleBigInteger() {
        assertEquals(0.0d, NumberUtils.toDouble((BigDecimal) null), "toDouble(BigInteger) 1 failed");
        assertEquals(8.5d, NumberUtils.toDouble(BigDecimal.valueOf(8.5d)), "toDouble(BigInteger) 2 failed");
    }

    /**
     * Test for {@link NumberUtils#toDouble(BigDecimal, double)}
     */
   /* @Test
    public void testBigIntegerToDoubleBigIntegerD() {
        assertEquals(1.1d, NumberUtils.toDouble((BigDecimal) null, 1.1d), "toDouble(BigInteger) 1 failed");
        assertEquals(8.5d, NumberUtils.toDouble(BigDecimal.valueOf(8.5d), 1.1d), "toDouble(BigInteger) 2 failed");
    }

    // Testing JDK against old Lang functionality
    @Test
    public void testCompareDouble() {
        assertEquals(0, Double.compare(Double.NaN, Double.NaN));
        assertEquals(Double.compare(Double.NaN, Double.POSITIVE_INFINITY), +1);
        assertEquals(Double.compare(Double.NaN, Double.MAX_VALUE), +1);
        assertEquals(Double.compare(Double.NaN, 1.2d), +1);
        assertEquals(Double.compare(Double.NaN, 0.0d), +1);
        assertEquals(Double.compare(Double.NaN, -0.0d), +1);
        assertEquals(Double.compare(Double.NaN, -1.2d), +1);
        assertEquals(Double.compare(Double.NaN, -Double.MAX_VALUE), +1);
        assertEquals(Double.compare(Double.NaN, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(Double.POSITIVE_INFINITY, Double.NaN), -1);
        assertEquals(0, Double.compare(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, Double.MAX_VALUE), +1);
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, 1.2d), +1);
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, 0.0d), +1);
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, -0.0d), +1);
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, -1.2d), +1);
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, -Double.MAX_VALUE), +1);
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(Double.MAX_VALUE, Double.NaN), -1);
        assertEquals(Double.compare(Double.MAX_VALUE, Double.POSITIVE_INFINITY), -1);
        assertEquals(0, Double.compare(Double.MAX_VALUE, Double.MAX_VALUE));
        assertEquals(Double.compare(Double.MAX_VALUE, 1.2d), +1);
        assertEquals(Double.compare(Double.MAX_VALUE, 0.0d), +1);
        assertEquals(Double.compare(Double.MAX_VALUE, -0.0d), +1);
        assertEquals(Double.compare(Double.MAX_VALUE, -1.2d), +1);
        assertEquals(Double.compare(Double.MAX_VALUE, -Double.MAX_VALUE), +1);
        assertEquals(Double.compare(Double.MAX_VALUE, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(1.2d, Double.NaN), -1);
        assertEquals(Double.compare(1.2d, Double.POSITIVE_INFINITY), -1);
        assertEquals(Double.compare(1.2d, Double.MAX_VALUE), -1);
        assertEquals(0, Double.compare(1.2d, 1.2d));
        assertEquals(Double.compare(1.2d, 0.0d), +1);
        assertEquals(Double.compare(1.2d, -0.0d), +1);
        assertEquals(Double.compare(1.2d, -1.2d), +1);
        assertEquals(Double.compare(1.2d, -Double.MAX_VALUE), +1);
        assertEquals(Double.compare(1.2d, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(0.0d, Double.NaN), -1);
        assertEquals(Double.compare(0.0d, Double.POSITIVE_INFINITY), -1);
        assertEquals(Double.compare(0.0d, Double.MAX_VALUE), -1);
        assertEquals(Double.compare(0.0d, 1.2d), -1);
        assertEquals(0, Double.compare(0.0d, 0.0d));
        assertEquals(Double.compare(0.0d, -0.0d), +1);
        assertEquals(Double.compare(0.0d, -1.2d), +1);
        assertEquals(Double.compare(0.0d, -Double.MAX_VALUE), +1);
        assertEquals(Double.compare(0.0d, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(-0.0d, Double.NaN), -1);
        assertEquals(Double.compare(-0.0d, Double.POSITIVE_INFINITY), -1);
        assertEquals(Double.compare(-0.0d, Double.MAX_VALUE), -1);
        assertEquals(Double.compare(-0.0d, 1.2d), -1);
        assertEquals(Double.compare(-0.0d, 0.0d), -1);
        assertEquals(0, Double.compare(-0.0d, -0.0d));
        assertEquals(Double.compare(-0.0d, -1.2d), +1);
        assertEquals(Double.compare(-0.0d, -Double.MAX_VALUE), +1);
        assertEquals(Double.compare(-0.0d, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(-1.2d, Double.NaN), -1);
        assertEquals(Double.compare(-1.2d, Double.POSITIVE_INFINITY), -1);
        assertEquals(Double.compare(-1.2d, Double.MAX_VALUE), -1);
        assertEquals(Double.compare(-1.2d, 1.2d), -1);
        assertEquals(Double.compare(-1.2d, 0.0d), -1);
        assertEquals(Double.compare(-1.2d, -0.0d), -1);
        assertEquals(0, Double.compare(-1.2d, -1.2d));
        assertEquals(Double.compare(-1.2d, -Double.MAX_VALUE), +1);
        assertEquals(Double.compare(-1.2d, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(-Double.MAX_VALUE, Double.NaN), -1);
        assertEquals(Double.compare(-Double.MAX_VALUE, Double.POSITIVE_INFINITY), -1);
        assertEquals(Double.compare(-Double.MAX_VALUE, Double.MAX_VALUE), -1);
        assertEquals(Double.compare(-Double.MAX_VALUE, 1.2d), -1);
        assertEquals(Double.compare(-Double.MAX_VALUE, 0.0d), -1);
        assertEquals(Double.compare(-Double.MAX_VALUE, -0.0d), -1);
        assertEquals(Double.compare(-Double.MAX_VALUE, -1.2d), -1);
        assertEquals(0, Double.compare(-Double.MAX_VALUE, -Double.MAX_VALUE));
        assertEquals(Double.compare(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY), +1);

        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, Double.NaN), -1);
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY), -1);
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, Double.MAX_VALUE), -1);
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, 1.2d), -1);
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, 0.0d), -1);
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, -0.0d), -1);
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, -1.2d), -1);
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, -Double.MAX_VALUE), -1);
        assertEquals(0, Double.compare(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testCompareFloat() {
        assertEquals(0, Float.compare(Float.NaN, Float.NaN));
        assertEquals(Float.compare(Float.NaN, Float.POSITIVE_INFINITY), +1);
        assertEquals(Float.compare(Float.NaN, Float.MAX_VALUE), +1);
        assertEquals(Float.compare(Float.NaN, 1.2f), +1);
        assertEquals(Float.compare(Float.NaN, 0.0f), +1);
        assertEquals(Float.compare(Float.NaN, -0.0f), +1);
        assertEquals(Float.compare(Float.NaN, -1.2f), +1);
        assertEquals(Float.compare(Float.NaN, -Float.MAX_VALUE), +1);
        assertEquals(Float.compare(Float.NaN, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(Float.POSITIVE_INFINITY, Float.NaN), -1);
        assertEquals(0, Float.compare(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, Float.MAX_VALUE), +1);
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, 1.2f), +1);
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, 0.0f), +1);
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, -0.0f), +1);
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, -1.2f), +1);
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, -Float.MAX_VALUE), +1);
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(Float.MAX_VALUE, Float.NaN), -1);
        assertEquals(Float.compare(Float.MAX_VALUE, Float.POSITIVE_INFINITY), -1);
        assertEquals(0, Float.compare(Float.MAX_VALUE, Float.MAX_VALUE));
        assertEquals(Float.compare(Float.MAX_VALUE, 1.2f), +1);
        assertEquals(Float.compare(Float.MAX_VALUE, 0.0f), +1);
        assertEquals(Float.compare(Float.MAX_VALUE, -0.0f), +1);
        assertEquals(Float.compare(Float.MAX_VALUE, -1.2f), +1);
        assertEquals(Float.compare(Float.MAX_VALUE, -Float.MAX_VALUE), +1);
        assertEquals(Float.compare(Float.MAX_VALUE, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(1.2f, Float.NaN), -1);
        assertEquals(Float.compare(1.2f, Float.POSITIVE_INFINITY), -1);
        assertEquals(Float.compare(1.2f, Float.MAX_VALUE), -1);
        assertEquals(0, Float.compare(1.2f, 1.2f));
        assertEquals(Float.compare(1.2f, 0.0f), +1);
        assertEquals(Float.compare(1.2f, -0.0f), +1);
        assertEquals(Float.compare(1.2f, -1.2f), +1);
        assertEquals(Float.compare(1.2f, -Float.MAX_VALUE), +1);
        assertEquals(Float.compare(1.2f, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(0.0f, Float.NaN), -1);
        assertEquals(Float.compare(0.0f, Float.POSITIVE_INFINITY), -1);
        assertEquals(Float.compare(0.0f, Float.MAX_VALUE), -1);
        assertEquals(Float.compare(0.0f, 1.2f), -1);
        assertEquals(0, Float.compare(0.0f, 0.0f));
        assertEquals(Float.compare(0.0f, -0.0f), +1);
        assertEquals(Float.compare(0.0f, -1.2f), +1);
        assertEquals(Float.compare(0.0f, -Float.MAX_VALUE), +1);
        assertEquals(Float.compare(0.0f, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(-0.0f, Float.NaN), -1);
        assertEquals(Float.compare(-0.0f, Float.POSITIVE_INFINITY), -1);
        assertEquals(Float.compare(-0.0f, Float.MAX_VALUE), -1);
        assertEquals(Float.compare(-0.0f, 1.2f), -1);
        assertEquals(Float.compare(-0.0f, 0.0f), -1);
        assertEquals(0, Float.compare(-0.0f, -0.0f));
        assertEquals(Float.compare(-0.0f, -1.2f), +1);
        assertEquals(Float.compare(-0.0f, -Float.MAX_VALUE), +1);
        assertEquals(Float.compare(-0.0f, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(-1.2f, Float.NaN), -1);
        assertEquals(Float.compare(-1.2f, Float.POSITIVE_INFINITY), -1);
        assertEquals(Float.compare(-1.2f, Float.MAX_VALUE), -1);
        assertEquals(Float.compare(-1.2f, 1.2f), -1);
        assertEquals(Float.compare(-1.2f, 0.0f), -1);
        assertEquals(Float.compare(-1.2f, -0.0f), -1);
        assertEquals(0, Float.compare(-1.2f, -1.2f));
        assertEquals(Float.compare(-1.2f, -Float.MAX_VALUE), +1);
        assertEquals(Float.compare(-1.2f, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(-Float.MAX_VALUE, Float.NaN), -1);
        assertEquals(Float.compare(-Float.MAX_VALUE, Float.POSITIVE_INFINITY), -1);
        assertEquals(Float.compare(-Float.MAX_VALUE, Float.MAX_VALUE), -1);
        assertEquals(Float.compare(-Float.MAX_VALUE, 1.2f), -1);
        assertEquals(Float.compare(-Float.MAX_VALUE, 0.0f), -1);
        assertEquals(Float.compare(-Float.MAX_VALUE, -0.0f), -1);
        assertEquals(Float.compare(-Float.MAX_VALUE, -1.2f), -1);
        assertEquals(0, Float.compare(-Float.MAX_VALUE, -Float.MAX_VALUE));
        assertEquals(Float.compare(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY), +1);

        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, Float.NaN), -1);
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY), -1);
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, Float.MAX_VALUE), -1);
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, 1.2f), -1);
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, 0.0f), -1);
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, -0.0f), -1);
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, -1.2f), -1);
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, -Float.MAX_VALUE), -1);
        assertEquals(0, Float.compare(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY));
    }

    @SuppressWarnings("cast") // suppress instanceof warning check
    @Test
    public void testConstants() {
        assertTrue(NumberUtils.LONG_ZERO instanceof Long);
        assertTrue(NumberUtils.LONG_ONE instanceof Long);
        assertTrue(NumberUtils.LONG_MINUS_ONE instanceof Long);
        assertTrue(NumberUtils.INTEGER_ZERO instanceof Integer);
        assertTrue(NumberUtils.INTEGER_ONE instanceof Integer);
        assertTrue(NumberUtils.INTEGER_MINUS_ONE instanceof Integer);
        assertTrue(NumberUtils.SHORT_ZERO instanceof Short);
        assertTrue(NumberUtils.SHORT_ONE instanceof Short);
        assertTrue(NumberUtils.SHORT_MINUS_ONE instanceof Short);
        assertTrue(NumberUtils.BYTE_ZERO instanceof Byte);
        assertTrue(NumberUtils.BYTE_ONE instanceof Byte);
        assertTrue(NumberUtils.BYTE_MINUS_ONE instanceof Byte);
        assertTrue(NumberUtils.DOUBLE_ZERO instanceof Double);
        assertTrue(NumberUtils.DOUBLE_ONE instanceof Double);
        assertTrue(NumberUtils.DOUBLE_MINUS_ONE instanceof Double);
        assertTrue(NumberUtils.FLOAT_ZERO instanceof Float);
        assertTrue(NumberUtils.FLOAT_ONE instanceof Float);
        assertTrue(NumberUtils.FLOAT_MINUS_ONE instanceof Float);

        assertEquals(0, NumberUtils.LONG_ZERO.longValue());
        assertEquals(1, NumberUtils.LONG_ONE.longValue());
        assertEquals(NumberUtils.LONG_MINUS_ONE.longValue(), -1);
        assertEquals(0, NumberUtils.INTEGER_ZERO.intValue());
        assertEquals(1, NumberUtils.INTEGER_ONE.intValue());
        assertEquals(NumberUtils.INTEGER_MINUS_ONE.intValue(), -1);
        assertEquals(0, NumberUtils.SHORT_ZERO.shortValue());
        assertEquals(1, NumberUtils.SHORT_ONE.shortValue());
        assertEquals(NumberUtils.SHORT_MINUS_ONE.shortValue(), -1);
        assertEquals(0, NumberUtils.BYTE_ZERO.byteValue());
        assertEquals(1, NumberUtils.BYTE_ONE.byteValue());
        assertEquals(NumberUtils.BYTE_MINUS_ONE.byteValue(), -1);
        assertEquals(0.0d, NumberUtils.DOUBLE_ZERO.doubleValue());
        assertEquals(1.0d, NumberUtils.DOUBLE_ONE.doubleValue());
        assertEquals(NumberUtils.DOUBLE_MINUS_ONE.doubleValue(), -1.0d);
        assertEquals(0.0f, NumberUtils.FLOAT_ZERO.floatValue());
        assertEquals(1.0f, NumberUtils.FLOAT_ONE.floatValue());
        assertEquals(NumberUtils.FLOAT_MINUS_ONE.floatValue(), -1.0f);
    }

    @Test
    public void testConstructor() {
        assertNotNull(new NumberUtils());
        final Constructor<?>[] cons = NumberUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(NumberUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(NumberUtils.class.getModifiers()));
    }

    @Test
    public void testCreateBigDecimal() {
        assertEquals(new BigDecimal("1234.5"), NumberUtils.createBigDecimal("1234.5"),
            "createBigDecimal(String) failed");
        assertNull(NumberUtils.createBigDecimal(null), "createBigDecimal(null) failed");
        this.testCreateBigDecimalFailure("");
        this.testCreateBigDecimalFailure(" ");
        this.testCreateBigDecimalFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigDecimalFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // sign alone not valid
        this.testCreateBigDecimalFailure("-");
        // comment in NumberUtils suggests some implementations may incorrectly allow this
        this.testCreateBigDecimalFailure("--");
        this.testCreateBigDecimalFailure("--0");
        // sign alone not valid
        this.testCreateBigDecimalFailure("+");
        // in case this was also allowed by some JVMs
        this.testCreateBigDecimalFailure("++");
        this.testCreateBigDecimalFailure("++0");
    }

    protected void testCreateBigDecimalFailure(final String str) {
        assertThrows(Exception.class, () -> NumberUtils.createBigDecimal(str),
            "createBigDecimal(\"" + str + "\") should have failed.");
    }

    @Test
    public void testCreateBigInteger() {
        assertEquals(new BigInteger("12345"), NumberUtils.createBigInteger("12345"), "createBigInteger(String) failed");
        assertNull(NumberUtils.createBigInteger(null), "createBigInteger(null) failed");
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("0xff"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("0Xff"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("#ff"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("-255"), NumberUtils.createBigInteger("-0xff"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("0377"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("-255"), NumberUtils.createBigInteger("-0377"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("-255"), NumberUtils.createBigInteger("-0377"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("-0"), NumberUtils.createBigInteger("-0"), "createBigInteger(String) failed");
        assertEquals(new BigInteger("0"), NumberUtils.createBigInteger("0"), "createBigInteger(String) failed");
        testCreateBigIntegerFailure("#");
        testCreateBigIntegerFailure("-#");
        testCreateBigIntegerFailure("0x");
        testCreateBigIntegerFailure("-0x");
        // LANG-1645
        assertEquals(new BigInteger("+FFFFFFFFFFFFFFFF", 16), NumberUtils.createBigInteger("+0xFFFFFFFFFFFFFFFF"));
        assertEquals(new BigInteger("+FFFFFFFFFFFFFFFF", 16), NumberUtils.createBigInteger("+#FFFFFFFFFFFFFFFF"));
        assertEquals(new BigInteger("+1234567", 8), NumberUtils.createBigInteger("+01234567"));
    }

    protected void testCreateBigIntegerFailure(final String str) {
        assertThrows(Exception.class, () -> NumberUtils.createBigInteger(str),
            "createBigInteger(\"" + str + "\") should have failed.");
    }

    @Test
    public void testCreateDouble() {
        assertEquals(Double.valueOf("1234.5"), NumberUtils.createDouble("1234.5"), "createDouble(String) failed");
        assertNull(NumberUtils.createDouble(null), "createDouble(null) failed");
        this.testCreateDoubleFailure("");
        this.testCreateDoubleFailure(" ");
        this.testCreateDoubleFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateDoubleFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
    }

    protected void testCreateDoubleFailure(final String str) {
        assertThrows(Exception.class, () -> NumberUtils.createDouble(str),
            "createDouble(\"" + str + "\") should have failed.");
    }

    @Test
    public void testCreateFloat() {
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createFloat("1234.5"), "createFloat(String) failed");
        assertNull(NumberUtils.createFloat(null), "createFloat(null) failed");
        this.testCreateFloatFailure("");
        this.testCreateFloatFailure(" ");
        this.testCreateFloatFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateFloatFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
    }

    protected void testCreateFloatFailure(final String str) {
        assertThrows(Exception.class, () -> NumberUtils.createFloat(str),
            "createFloat(\"" + str + "\") should have failed.");
    }

    @Test
    public void testCreateInteger() {
        assertEquals(Integer.valueOf("12345"), NumberUtils.createInteger("12345"), "createInteger(String) failed");
        assertNull(NumberUtils.createInteger(null), "createInteger(null) failed");
        this.testCreateIntegerFailure("");
        this.testCreateIntegerFailure(" ");
        this.testCreateIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // LANG-1645
        assertEquals(Integer.decode("+0xF"), NumberUtils.createInteger("+0xF"));
    }

    protected void testCreateIntegerFailure(final String str) {
        assertThrows(Exception.class, () -> NumberUtils.createInteger(str),
            "createInteger(\"" + str + "\") should have failed.");
    }

    /*@Test
    public void testCreateLong() {
        assertEquals(Long.valueOf("12345"), NumberUtils.createLong("12345"), "createLong(String) failed");
        assertNull(NumberUtils.createLong(null), "createLong(null) failed");
        this.testCreateLongFailure("");
        this.testCreateLongFailure(" ");
        this.testCreateLongFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateLongFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // LANG-1645
        assertEquals(Long.decode("+0xFFFFFFFF"), NumberUtils.createLong("+0xFFFFFFFF"));
    }

    protected void testCreateLongFailure(final String str) {
        assertThrows(Exception.class, () -> NumberUtils.createLong(str),
            "createLong(\"" + str + "\") should have failed.");
    }
*/
    @Test
    public void testCreateNumber() {
        // a lot of things can go wrong
      
try {
      // assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5"), "createNumber(String) 1 failed");		
	    printTestOutput(1,model.createNumber("1234.5"));// test 1 
		} catch (final Exception e) {

        printTestOutput(1,Exception.class);// test 1
		}
        
		
		
		try {
     // assertEquals(Integer.valueOf("12345"), NumberUtils.createNumber("12345"), "createNumber(String) 2 failed");
        printTestOutput(2,model.createNumber("12345"));// test 2 
		} catch (final Exception e) {

        printTestOutput(2,Exception.class);// test 2
		}
		
        try {
		//assertEquals(Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5D"), "createNumber(String) 3 failed");
        printTestOutput(3,model.createNumber("1234.5D"));// test 3

        } catch (final Exception e) {

        printTestOutput(3,Exception.class);// test 3
		}




		
        try {
		//assertEquals(Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5d"), "createNumber(String) 3 failed");
		printTestOutput(4,model.createNumber("1234.5d"));// test 4
		} catch (final Exception e) {

        printTestOutput(4,Exception.class);// test 4
		}
		
		
		
		
		
		try {
		//assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5F"), "createNumber(String) 4 failed");
		printTestOutput(5,model.createNumber("1234.5F"));// test 5
		} catch (final Exception e) {

        printTestOutput(5,Exception.class);// test 5
		}
		
		
		try{
       // assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5f"), "createNumber(String) 4 failed");
		printTestOutput(6,model.createNumber("1234.5f"));// test 6
		} catch (final Exception e) {

        printTestOutput(6,Exception.class);// test 4
		}
		
		
		
        try{
			//assertEquals(Long.valueOf(Integer.MAX_VALUE + 1L), NumberUtils.createNumber("" + (Integer.MAX_VALUE + 1L)), "createNumber(String) 5 failed");
	    printTestOutput(7,model.createNumber("" + (Integer.MAX_VALUE + 1L)));// test 7 
		} catch (final Exception e) {

        printTestOutput(7,Exception.class);// test 4
		}
		
        try{
		//assertEquals(Long.valueOf(12345), NumberUtils.createNumber("12345L"), "createNumber(String) 6 failed");
	    printTestOutput(8,model.createNumber("12345L"));// test 8
        } catch (final Exception e) {

        printTestOutput(8,Exception.class);// test 4
		}
		
       try{
		 // assertEquals(Long.valueOf(12345), NumberUtils.createNumber("12345l"), "createNumber(String) 6 failed");
	   printTestOutput(9,model.createNumber("12345l"));// test 9 
         } catch (final Exception e) {

        printTestOutput(9,Exception.class);// test 4
		}
       


	   try{
		  // assertEquals(Float.valueOf("-1234.5"), NumberUtils.createNumber("-1234.5"), "createNumber(String) 7 failed");
	    printTestOutput(10,model.createNumber("-1234.5"));// test 10 
 } catch (final Exception e) {

        printTestOutput(10,Exception.class);// test 4
		}
       

 try{
	  // assertEquals(Integer.valueOf("-12345"), NumberUtils.createNumber("-12345"), "createNumber(String) 8 failed");
	    printTestOutput(11,model.createNumber("-12345"));// test 11 
 } catch (final Exception e) {

        printTestOutput(11,Exception.class);// test 4
		}
       


 try{
	   //assertEquals(0xFADE, NumberUtils.createNumber("0xFADE").intValue(), "createNumber(String) 9a failed");
	    printTestOutput(12,model.createNumber("0xFADE").intValue());// test 12
 } catch (final Exception e) {

        printTestOutput(12,Exception.class);// test 4
		}
		
         try{
			 
			// assertEquals(0xFADE, NumberUtils.createNumber("0Xfade").intValue(), "createNumber(String) 9b failed");
		printTestOutput(13,model.createNumber("0Xfade").intValue());// test 13
 } catch (final Exception e) {

        printTestOutput(13,Exception.class);// test 4
		}
		
         try{
			// assertEquals(-0xFADE, NumberUtils.createNumber("-0xFADE").intValue(), "createNumber(String) 10a failed");
		printTestOutput(14,model.createNumber("-0xFADE").intValue());// test 14
           } catch (final Exception e) {

        printTestOutput(14,Exception.class);// test 4
		}
        
		 try{
			// assertEquals(-0xFADE, NumberUtils.createNumber("-0Xfade").intValue(), "createNumber(String) 10b failed");
		printTestOutput(15,model.createNumber("-0Xfade").intValue());// test 15
		} catch (final Exception e) {

        printTestOutput(15,Exception.class);// test 4
		}
		
		
		
         try{
			// assertEquals(Double.valueOf("1.1E200"), NumberUtils.createNumber("1.1E200"), "createNumber(String) 11 failed");
		printTestOutput(16,model.createNumber("1.1E200"));// test 16
		} catch (final Exception e) {

        printTestOutput(16,Exception.class);// test 4
		}
		
		
		
		
         try{
			 //assertEquals(Float.valueOf("1.1E20"), NumberUtils.createNumber("1.1E20"), "createNumber(String) 12 failed");
		printTestOutput(17,model.createNumber("1.1E20"));// test 17
		} catch (final Exception e) {

        printTestOutput(17,Exception.class);// test 4
		}
		
		
		
		
         try{
			 //assertEquals(Double.valueOf("-1.1E200"), NumberUtils.createNumber("-1.1E200"),
            //"createNumber(String) 13 failed");
	    printTestOutput(18,model.createNumber("-1.1E200"));// test 18
       } catch (final  Exception e) {

        printTestOutput(18,Exception.class);// test 4
		}





	    try{
			//assertEquals(Double.valueOf("1.1E-200"), NumberUtils.createNumber("1.1E-200"),
           // "createNumber(String) 14 failed");
		printTestOutput(19,model.createNumber("1.1E-200"));// test 19
		} catch (final Exception e) {

        printTestOutput(19,"class java.lang.Exception");// test 4
		}
		
		
		

         try{
			// assertNull(NumberUtils.createNumber(null), "createNumber(null) failed");
		printTestOutput(20,model.createNumber(null));// test 20

		} catch (final Exception e) {

        printTestOutput(20,"class java.lang.Exception");// test 4
		}
		
		
		
		
         try{
			// assertEquals(new BigInteger("12345678901234567890"), NumberUtils.createNumber("12345678901234567890L"),
            //"createNumber(String) failed");
		printTestOutput(21,model.createNumber("12345678901234567890L"));// test 21	

		} catch (final Exception e) {

        printTestOutput(21,"class java.lang.Exception");// test 4
		}
		
		
		
        try{
			//assertEquals(new BigDecimal("1.1E-700"), NumberUtils.createNumber("1.1E-700F"),
           // "createNumber(String) 15 failed");
		printTestOutput(22,model.createNumber("1.1E-700F"));// test 22

		
		} catch (final Exception e) {

        printTestOutput(22,"class java.lang.Exception");// test 4
		}
		
		
		
        try{
			//assertEquals(Long.valueOf("10" + Integer.MAX_VALUE), NumberUtils.createNumber("10" + Integer.MAX_VALUE + "L"),
            //"createNumber(String) 16 failed");
		printTestOutput(23,model.createNumber("10" + Integer.MAX_VALUE + "L"));// test 23
		} catch (final Exception e) {

        printTestOutput(23,"class java.lang.Exception");// test 4
		}
		
		
		
         try{
			 //assertEquals(Long.valueOf("10" + Integer.MAX_VALUE), NumberUtils.createNumber("10" + Integer.MAX_VALUE),
           // "createNumber(String) 17 failed");
			
	   printTestOutput(24,model.createNumber("10" + Integer.MAX_VALUE));// test 24
	   } catch (final Exception e) {

        printTestOutput(24,"class java.lang.Exception");// test 4
		}
		
		
		
	   
         try{
			// assertEquals(new BigInteger("10" + Long.MAX_VALUE), NumberUtils.createNumber("10" + Long.MAX_VALUE),
           // "createNumber(String) 18 failed");
       printTestOutput(25,model.createNumber("10" + Long.MAX_VALUE));// test 25 
	   } catch (final Exception e) {

        printTestOutput(25,Exception.class);// test 4
		}
		
		
	   
        // LANG-521
         try{
			// assertEquals(Float.valueOf("2."), NumberUtils.createNumber("2."), "createNumber(String) LANG-521 failed");
		printTestOutput(26,model.createNumber("2."));// test 26 
} catch (final Exception e) {

        printTestOutput(26,Exception.class);// test 4
		}
		
		
		

        /*// LANG-638
        assertFalse(checkCreateNumber("1eE"), "createNumber(String) succeeded");// try 3
*/
        // LANG-693
         try{
			// assertEquals(Double.valueOf(Double.MAX_VALUE), NumberUtils.createNumber("" + Double.MAX_VALUE), "createNumber(String) LANG-693 failed");
		
		printTestOutput(27,model.createNumber("" + Double.MAX_VALUE));// test 27 
        } catch (final Exception e) {

        printTestOutput(27,Exception.class);// test 4
		}
		
		

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
 try{       
	  // final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        //assertNotNull(bigNum);
        //assertEquals(BigDecimal.class, bigNum.getClass());
		printTestOutput(28,model.createNumber("-1.1E-700F"));// test 28 
         } catch (final Exception e) {

        printTestOutput(28,Exception.class);// test 4
		}
		
		
		
		
        // LANG-1018
         try{
			// assertEquals(Double.valueOf("-160952.54"), NumberUtils.createNumber("-160952.54"),
            //"createNumber(String) LANG-1018 failed");
		printTestOutput(29,model.createNumber("-160952.54"));// test 29 
        // LANG-1187
       } catch (final Exception e) {

        printTestOutput(29,Exception.class);// test 4
		}
		
		



	   try{
		   //assertEquals(Double.valueOf("6264583.33"), NumberUtils.createNumber("6264583.33"),
            //"createNumber(String) LANG-1187 failed");
		printTestOutput(30,model.createNumber("6264583.33"));// test 30 
       } catch (final Exception e) {

        printTestOutput(30,Exception.class);// test 4
		}
		





	   // LANG-1215
        try{
		//	/assertEquals(Double.valueOf("193343.82"), NumberUtils.createNumber("193343.82"),
            //"createNumber(String) LANG-1215 failed");
		printTestOutput(31,model.createNumber("193343.82"));// test 31
       } catch (final Exception e) {

        printTestOutput(31,Exception.class);// test 4
		}
		
		



	   // LANG-1060
         try{
			// assertEquals(Double.valueOf("001234.5678"), NumberUtils.createNumber("001234.5678"),
          //  "createNumber(String) LANG-1060a failed");
		printTestOutput(32,model.createNumber("001234.5678"));// test 32
		} catch (final Exception e) {

        printTestOutput(32,Exception.class);// test 4
		}
		
		
		
        try{
		//	assertEquals(Double.valueOf("+001234.5678"), NumberUtils.createNumber("+001234.5678"),
           // "createNumber(String) LANG-1060b failed");
		printTestOutput(33,model.createNumber("+001234.5678"));// test 33
		} catch (final Exception e) {

        printTestOutput(33,Exception.class);// test 4
		}
		
		
		
		
         try{
			// assertEquals(Double.valueOf("-001234.5678"), NumberUtils.createNumber("-001234.5678"),
            //"createNumber(String) LANG-1060c failed");
		printTestOutput(34,model.createNumber("-001234.5678"));// test 34
		} catch (final Exception e) {

        printTestOutput(34,Exception.class);// test 
		}
		
		
		
		
		
         try{
			 //assertEquals(Double.valueOf("0000.00000"), NumberUtils.createNumber("0000.00000d"),
            //"createNumber(String) LANG-1060d failed");
		printTestOutput(35,model.createNumber("0000.00000d"));// test 35
		} catch (final Exception e) {

        printTestOutput(35,Exception.class);// test 
		}
		
		
		
		
         try{
			// assertEquals(Float.valueOf("001234.56"), NumberUtils.createNumber("001234.56"),
            //"createNumber(String) LANG-1060e failed");
		printTestOutput(36,model.createNumber("001234.56"));// test 36
		} catch (final Exception e) {

        printTestOutput(36,Exception.class);// test 
		}
		
		
		
		
        try{
			//assertEquals(Float.valueOf("+001234.56"), NumberUtils.createNumber("+001234.56"),
           // "createNumber(String) LANG-1060f failed");
		printTestOutput(37,model.createNumber("+001234.56"));// test 37
		} catch (final Exception e) {

        printTestOutput(37,Exception.class);// test 4
		}
		
		
         try{
			// assertEquals(Float.valueOf("-001234.56"), NumberUtils.createNumber("-001234.56"),
           // "createNumber(String) LANG-1060g failed");
		printTestOutput(38,model.createNumber("-001234.56"));// test 38
		} catch (final Exception e) {

        printTestOutput(38,Exception.class);// test 
		}
		
		
        try{
			//assertEquals(Float.valueOf("0000.10"), NumberUtils.createNumber("0000.10"),
            //"createNumber(String) LANG-1060h failed");
		printTestOutput(39,model.createNumber("0000.10"));// test 39
		} catch (final Exception e) {

        printTestOutput(39,Exception.class);// test 
		}
		
         try{
			// assertEquals(Float.valueOf("001.1E20"), NumberUtils.createNumber("001.1E20"),
            //"createNumber(String) LANG-1060i failed");
		printTestOutput(40,model.createNumber("001.1E20"));// test 40
		} catch (final Exception e) {

        printTestOutput(40,Exception.class);// test 4
		}
		
         try{
			// assertEquals(Float.valueOf("+001.1E20"), NumberUtils.createNumber("+001.1E20"),
            //"createNumber(String) LANG-1060j failed");
		printTestOutput(41,model.createNumber("+001.1E20"));// test 41
		} catch (final Exception e) {

        printTestOutput(41,Exception.class);// test 4
		}
		
        try{
		//	assertEquals(Float.valueOf("-001.1E20"), NumberUtils.createNumber("-001.1E20"),
           // "createNumber(String) LANG-1060k failed");
		printTestOutput(42,model.createNumber("-001.1E20"));// test 42
		} catch (final Exception e) {

        printTestOutput(42,Exception.class);// test 4
		}
		
        try{
			//assertEquals(Double.valueOf("001.1E200"), NumberUtils.createNumber("001.1E200"),
           // "createNumber(String) LANG-1060l failed");
		printTestOutput(43,model.createNumber("001.1E200"));// test 43
		
       } catch (final Exception e) {

        printTestOutput(43,Exception.class);// test 
		}



	    try{
			//assertEquals(Double.valueOf("+001.1E200"), NumberUtils.createNumber("+001.1E200"),
            //"createNumber(String) LANG-1060m failed");
		printTestOutput(44,model.createNumber("+001.1E200"));// test 44
		} catch (final Exception e) {

        printTestOutput(44,Exception.class);// test 
		}
		
		
		
		
         try{
			 //assertEquals(Double.valueOf("-001.1E200"), NumberUtils.createNumber("-001.1E200"),
           // "createNumber(String) LANG-1060n failed");
		printTestOutput(45,model.createNumber("-001.1E200"));// test 45
		} catch (final Exception e) {

        printTestOutput(45,Exception.class);// test 
		}
        
		
		
		
		
		
		// LANG-1645
        try{
			//assertEquals(Integer.decode("+0xF"), NumberUtils.createNumber("+0xF"),
           // "createNumber(String) LANG-1645a failed");
		printTestOutput(46,model.createNumber("+0xF"));// test 46
		} catch (final Exception e) {

        printTestOutput(46,Exception.class);// test 
		}
       




	   try{
		   //assertEquals(Long.decode("+0xFFFFFFFF"), NumberUtils.createNumber("+0xFFFFFFFF"),
           // "createNumber(String) LANG-1645b failed");
		printTestOutput(47,model.createNumber("+0xFFFFFFFF"));// test 47
		} catch (final Exception e) {

        printTestOutput(47,Exception.class);// test 4
		}
        
		
		
		
		 try{
			// assertEquals(new BigInteger("+FFFFFFFFFFFFFFFF", 16), NumberUtils.createNumber("+0xFFFFFFFFFFFFFFFF"),
           // "createNumber(String) LANG-1645c failed");
		printTestOutput(48,model.createNumber("+0xFFFFFFFFFFFFFFFF"));// test 48
		 } catch (final Exception e) {

        printTestOutput(48,Exception.class);// test 
		} 
	
	
		
	}

    @Test
    // Check that the code fails to create a valid number when preceded by -- rather than -
    public void testCreateNumberFailure_1() {

 try {
	 		//assertThrows(Exception.class, () -> NumberUtils.createNumber("--1.1E-700F"));

			//printTestOutput(74,Exception.class);// test 74
			printTestOutput(74,NumberUtils.createNumber("--1.1E-700F"));// test 74

  } catch (final Exception e) {
printTestOutput(74,Exception.class);// test 74
           
        }
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (with decimal)
    public void testCreateNumberFailure_2() {
		try {
		
	  //assertThrows(Exception.class, () -> NumberUtils.createNumber("-1.1E+0-7e00"));
//printTestOutput(75,Exception.class);// test 75
printTestOutput(75,NumberUtils.createNumber("-1.1E+0-7e00"));// test 75
  } catch (final Exception e) {

        printTestOutput(75,Exception.class);// test 75   
        }
		
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_3() {
   try {
	            // assertThrows(Exception.class, () -> NumberUtils.createNumber("-11E+0-7e00"));
 //printTestOutput(76,Exception.class);// test 76
 printTestOutput(76,NumberUtils.createNumber("-11E+0-7e00"));

  } catch (final Exception e) {

         printTestOutput(76,Exception.class);// test 76   
        }


   }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_4() {
   
try {
	 // assertThrows(Exception.class, () -> NumberUtils.createNumber("1eE+00001"));
	   	 //printTestOutput(77,Exception.class);// test 77
		  printTestOutput(77,NumberUtils.createNumber("1eE+00001"));// test 77
 } catch (final Exception e) {
 printTestOutput(77,Exception.class);// test 77
           
   }


   }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'f' characters (LANG-1205)
    public void testCreateNumberFailure_5() {
    try {
		        //assertThrows(Exception.class, () -> NumberUtils.createNumber("1234.5ff"));
				//printTestOutput(78,Exception.class);// test 78 
				printTestOutput(78,NumberUtils.createNumber("1234.5ff"));
	} catch (final Exception e) {

      printTestOutput(78,Exception.class);// test 78      
   }

	
	}

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'F' characters (LANG-1205)
    public void testCreateNumberFailure_6() {
    try {
		       // assertThrows(Exception.class, () -> NumberUtils.createNumber("1234.5FF"));
				//printTestOutput(79,Exception.class);// test 79
				printTestOutput(79,NumberUtils.createNumber("1234.5FF"));// test 79
} catch (final Exception e) {
printTestOutput(79,Exception.class);// test 79
           
   }
	
	
	
	}

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'd' characters (LANG-1205)
    public void testCreateNumberFailure_7() {
        

    try {
		
		       // assertThrows(Exception.class, () -> NumberUtils.createNumber("1234.5dd"));
			  	//printTestOutput(80,Exception.class);// test 80
				printTestOutput(80,NumberUtils.createNumber("1234.5dd"));// test 80
  } catch (final Exception e) {
printTestOutput(80,Exception.class);// test 80
           
   }


   }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'D' characters (LANG-1205)
    public void testCreateNumberFailure_8() {
try {
	        //assertThrows(Exception.class, () -> NumberUtils.createNumber("1234.5DD"));
				//printTestOutput(81,Exception.class);//  test 81
				printTestOutput(81,NumberUtils.createNumber("1234.5DD"));

  } catch (final Exception e) {
	  printTestOutput(81,Exception.class);// test 81

   }

  }

    // Tests to show when magnitude causes switch to next Number type
    // Will probably need to be adjusted if code is changed to check precision (LANG-693)
    @Test
    public void testCreateNumberMagnitude() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
     
	    try{
			//assertEquals(Float.valueOf(Float.MAX_VALUE), NumberUtils.createNumber("3.4028235e+38"));
		    printTestOutput(52,model.createNumber("3.4028235e+38"));// test 52
		 } catch (final Exception e) {

        printTestOutput(52,Exception.class);// test 
		} 
		
	      try{
			 // assertEquals(Double.valueOf(3.4028236e+38), NumberUtils.createNumber("3.4028236e+38"));
		printTestOutput(53,model.createNumber("3.4028236e+38"));// test 53
         } catch (final Exception e) {

        printTestOutput(53,Exception.class);// test 
		} 
		
        // Test Double.MAX_VALUE
          try{
			 // assertEquals(Double.valueOf(Double.MAX_VALUE), NumberUtils.createNumber("1.7976931348623157e+308"));
		       printTestOutput(54,model.createNumber("1.7976931348623157e+308"));// test 54
		 } catch (final Exception e) {

        printTestOutput(54,Exception.class);// test 
		} 
		// Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
         try{
			// assertEquals(new BigDecimal("1.7976931348623159e+308"), NumberUtils.createNumber("1.7976931348623159e+308"));
		printTestOutput(55,model.createNumber("1.7976931348623159e+308"));// test 55
          } catch (final Exception e) {

        printTestOutput(55,Exception.class);// test 
		} 
		
        // Requested type is parsed as zero but the value is not zero
          			  final Double nonZero1 = Double.valueOf(((double) Float.MIN_VALUE) / 2);

		  try{
        //assertEquals(nonZero1, NumberUtils.createNumber(nonZero1.toString()));
		printTestOutput(56,model.createNumber(nonZero1.toString()));// test 56
 } catch (final Exception e) {

        printTestOutput(56,Exception.class);// test 
		} 
		
          try{
			  //assertEquals(nonZero1, NumberUtils.createNumber(nonZero1.toString() + "F"));
		printTestOutput(57,model.createNumber(nonZero1.toString() + "F"));// test 57
		 } catch (final Exception e) {

        printTestOutput(57,Exception.class);// test 
		} 
		
        // Smallest double is 4.9e-324.
        // Test a number with zero before and/or after the decimal place to hit edge cases.
         			 final BigDecimal nonZero2 = new BigDecimal("4.9e-325");

		 try{
       // assertEquals(nonZero2, NumberUtils.createNumber("4.9e-325"));
		printTestOutput(58,model.createNumber("4.9e-325"));// test 58
		 } catch (final Exception e) {

        printTestOutput(58,Exception.class);// test 
		} 
		
		
         try{
			// assertEquals(nonZero2, NumberUtils.createNumber("4.9e-325D"));
		printTestOutput(59,model.createNumber("4.9e-325D"));// test 59
		 } catch (final Exception e) {

        printTestOutput(59,Exception.class);// test 
		} 
		
          			  final BigDecimal nonZero3 = new BigDecimal("1e-325");

		  try{
       // assertEquals(nonZero3, NumberUtils.createNumber("1e-325"));
		printTestOutput(60,model.createNumber("1e-325"));// test 60
		 } catch (final Exception e) {

        printTestOutput(60,Exception.class);// test 
		} 
		
          try{
			  //assertEquals(nonZero3, NumberUtils.createNumber("1e-325D"));
		printTestOutput(61,model.createNumber("1e-325D"));// test 61
		 } catch (final Exception e) {

        printTestOutput(61,Exception.class);// test 
		} 
		
		
          			  final BigDecimal nonZero4 = new BigDecimal("0.1e-325");

		  try{
        //assertEquals(nonZero4, NumberUtils.createNumber("0.1e-325"));
		printTestOutput(62,model.createNumber("0.1e-325"));// test 62
		 } catch (final Exception e) {

        printTestOutput(62,Exception.class);// test 
		} 
		
		
          try{
			 // assertEquals(nonZero4, NumberUtils.createNumber("0.1e-325D"));
		printTestOutput(63,model.createNumber("0.1e-325D"));// test 63
} catch (final Exception e) {

        printTestOutput(63,Exception.class);// test 
		} 
		
		
         try{
			 
			 //assertEquals(Integer.valueOf(0x12345678), NumberUtils.createNumber("0x12345678"));
		printTestOutput(64,model.createNumber("0x12345678"));// test 64
		 } catch (final Exception e) {

        printTestOutput(64,Exception.class);// test 
		} 
		
		
         try{
			// assertEquals(Long.valueOf(0x123456789L), NumberUtils.createNumber("0x123456789"));
		printTestOutput(65,model.createNumber("0x123456789"));// test 65
		 } catch (final Exception e) {

        printTestOutput(65,Exception.class);// test 
		} 
		
		

        try{
			//assertEquals(Long.valueOf(0x7fffffffffffffffL), NumberUtils.createNumber("0x7fffffffffffffff"));
		printTestOutput(66,model.createNumber("0x7fffffffffffffff"));// test 66
		 } catch (final Exception e) {

        printTestOutput(66,Exception.class);// test 
		} 
		
		
        // Does not appear to be a way to create a literal BigInteger of this magnitude
        try{
			//assertEquals(new BigInteger("7fffffffffffffff0", 16), NumberUtils.createNumber("0x7fffffffffffffff0"));
		printTestOutput(67,model.createNumber("0x7fffffffffffffff0"));// test 67
		 } catch (final Exception e) {

        printTestOutput(67,Exception.class);// test 
		} 
		
		
		

        try{
			//assertEquals(Long.valueOf(0x7fffffffffffffffL), NumberUtils.createNumber("#7fffffffffffffff"));
		printTestOutput(68,model.createNumber("#7fffffffffffffff"));// test 68
		 } catch (final Exception e) {

        printTestOutput(68,Exception.class);// test 
		} 
		
		
		
       try
	   {
		   //assertEquals(new BigInteger("7fffffffffffffff0", 16), NumberUtils.createNumber("#7fffffffffffffff0"));
		printTestOutput(69,model.createNumber("#7fffffffffffffff0"));// test 69
		 } catch (final Exception e) {

        printTestOutput(69,Exception.class);// test 
		} 
		
		

        try
		{
		//assertEquals(Integer.valueOf(017777777777), NumberUtils.createNumber("017777777777")); // 31 bits

		printTestOutput(70,model.createNumber("017777777777"));// test 70
		 } catch (  final Exception    e) {

        printTestOutput(70,Exception.class);// test 
		} 
		
		
       try
	   {
		   //assertEquals(Long.valueOf(037777777777L), NumberUtils.createNumber("037777777777")); // 32 bits
		printTestOutput(71,model.createNumber("037777777777"));// test 71
		 } catch ( final Exception e) {

        printTestOutput(71,Exception.class);// test 
		} 
		
		

        // 63 bits
       try
	   {
		   //assertEquals(Long.valueOf(0777777777777777777777L), NumberUtils.createNumber("0777777777777777777777"));
		   printTestOutput(72,model.createNumber("0777777777777777777777"));// test 72
		 } catch  (final Exception e) {

        printTestOutput(72,Exception.class);// test 
		} 
		
		
        // 64 bits
        try{
			//assertEquals(new BigInteger("1777777777777777777777", 8), NumberUtils.createNumber("01777777777777777777777"));
		printTestOutput(73,model.createNumber("01777777777777777777777"));// test 73
		   } catch ( final Exception e) {

        printTestOutput(73,Exception.class);// test 
		} 
		
	}

    /**
     * LANG-1646: Support the requested Number type (Long, Float, Double) of valid zero input.
     */
    /*@Test
    public void testCreateNumberZero() {
        // Handle integers
        assertEquals(Integer.valueOf(0), NumberUtils.createNumber("0"));
        assertEquals(Integer.valueOf(0), NumberUtils.createNumber("-0"));
        assertEquals(Long.valueOf(0), NumberUtils.createNumber("0L"));
        assertEquals(Long.valueOf(0), NumberUtils.createNumber("-0L"));

        // Handle floating-point with optional leading sign, trailing exponent (eX)
        // and format specifier (F or D).
        // This should allow: 0. ; .0 ; 0.0 ; 0 (if exponent or format specifier is present)

        // Exponent does not matter for zero
        final int[] exponents = {-2345, 0, 13};
        final String[] zeros = {"0.", ".0", "0.0", "0"};
        final Float f0 = Float.valueOf(0);
        final Float fn0 = Float.valueOf(-0F);
        final Double d0 = Double.valueOf(0);
        final Double dn0 = Double.valueOf(-0D);

        for (final String zero : zeros) {
            // Assume float if no preference.
            // This requires a decimal point if there is no exponent.
            if (zero.indexOf('.') != -1) {
                assertCreateNumberZero(zero, f0, fn0);
            }
            for (final int exp : exponents) {
                assertCreateNumberZero(zero + "e" + exp, f0, fn0);
            }
            // Type preference
            assertCreateNumberZero(zero + "F", f0, fn0);
            assertCreateNumberZero(zero + "D", d0, dn0);
            for (final int exp : exponents) {
                final String number = zero + "e" + exp;
                assertCreateNumberZero(number + "F", f0, fn0);
                assertCreateNumberZero(number + "D", d0, dn0);
            }
        }
    }

    private static void assertCreateNumberZero(   final String number, final Object zero, final Object negativeZero) {
        assertEquals(zero, NumberUtils.createNumber(number), () -> "Input: " + number);
		
		
        assertEquals(zero, NumberUtils.createNumber("+" + number), () -> "Input: +" + number);
		
        assertEquals(negativeZero, NumberUtils.createNumber("-" + number), () -> "Input: -" + number);
		
    }

    /**
     * Tests isCreatable(String) and tests that createNumber(String) returns a valid number iff isCreatable(String)
     * returns false.
     */
   /* @Test
    public void testIsCreatable() {
        compareIsCreatableWithCreateNumber("12345", true);
        compareIsCreatableWithCreateNumber("1234.5", true);
        compareIsCreatableWithCreateNumber(".12345", true);
        compareIsCreatableWithCreateNumber("1234E5", true);
        compareIsCreatableWithCreateNumber("1234E+5", true);
        compareIsCreatableWithCreateNumber("1234E-5", true);
        compareIsCreatableWithCreateNumber("123.4E5", true);
        compareIsCreatableWithCreateNumber("-1234", true);
        compareIsCreatableWithCreateNumber("-1234.5", true);
        compareIsCreatableWithCreateNumber("-.12345", true);
        compareIsCreatableWithCreateNumber("-1234E5", true);
        compareIsCreatableWithCreateNumber("0", true);
        compareIsCreatableWithCreateNumber("0.1", true); // LANG-1216
        compareIsCreatableWithCreateNumber("-0", true);
        compareIsCreatableWithCreateNumber("01234", true);
        compareIsCreatableWithCreateNumber("-01234", true);
        compareIsCreatableWithCreateNumber("-0xABC123", true);
        compareIsCreatableWithCreateNumber("-0x0", true);
        compareIsCreatableWithCreateNumber("123.4E21D", true);
        compareIsCreatableWithCreateNumber("-221.23F", true);
        compareIsCreatableWithCreateNumber("22338L", true);

        compareIsCreatableWithCreateNumber(null, false);
        compareIsCreatableWithCreateNumber("", false);
        compareIsCreatableWithCreateNumber(" ", false);
        compareIsCreatableWithCreateNumber("\r\n\t", false);
        compareIsCreatableWithCreateNumber("--2.3", false);
        compareIsCreatableWithCreateNumber(".12.3", false);
        compareIsCreatableWithCreateNumber("-123E", false);
        compareIsCreatableWithCreateNumber("-123E+-212", false);
        compareIsCreatableWithCreateNumber("-123E2.12", false);
        compareIsCreatableWithCreateNumber("0xGF", false);
        compareIsCreatableWithCreateNumber("0xFAE-1", false);
        compareIsCreatableWithCreateNumber(".", false);
        compareIsCreatableWithCreateNumber("-0ABC123", false);
        compareIsCreatableWithCreateNumber("123.4E-D", false);
        compareIsCreatableWithCreateNumber("123.4ED", false);
        compareIsCreatableWithCreateNumber("1234E5l", false);
        compareIsCreatableWithCreateNumber("11a", false);
        compareIsCreatableWithCreateNumber("1a", false);
        compareIsCreatableWithCreateNumber("a", false);
        compareIsCreatableWithCreateNumber("11g", false);
        compareIsCreatableWithCreateNumber("11z", false);
        compareIsCreatableWithCreateNumber("11def", false);
        compareIsCreatableWithCreateNumber("11d11", false);
        compareIsCreatableWithCreateNumber("11 11", false);
        compareIsCreatableWithCreateNumber(" 1111", false);
        compareIsCreatableWithCreateNumber("1111 ", false);

        compareIsCreatableWithCreateNumber("2.", true); // LANG-521
        compareIsCreatableWithCreateNumber("1.1L", false); // LANG-664
        compareIsCreatableWithCreateNumber("+0xF", true); // LANG-1645
        compareIsCreatableWithCreateNumber("+0xFFFFFFFF", true); // LANG-1645
        compareIsCreatableWithCreateNumber("+0xFFFFFFFFFFFFFFFF", true); // LANG-1645
        compareIsCreatableWithCreateNumber(".0", true); // LANG-1646
        compareIsCreatableWithCreateNumber("0.", true); // LANG-1646
        compareIsCreatableWithCreateNumber("0.D", true); // LANG-1646
        compareIsCreatableWithCreateNumber("0e1", true); // LANG-1646
        compareIsCreatableWithCreateNumber("0e1D", true); // LANG-1646
        compareIsCreatableWithCreateNumber(".D", false); // LANG-1646
        compareIsCreatableWithCreateNumber(".e10", false); // LANG-1646
        compareIsCreatableWithCreateNumber(".e10D", false); // LANG-1646
    }

    @Test
    public void testIsDigits() {
        assertFalse(NumberUtils.isDigits(null), "isDigits(null) failed");
        assertFalse(NumberUtils.isDigits(""), "isDigits('') failed");
        assertTrue(NumberUtils.isDigits("12345"), "isDigits(String) failed");
        assertFalse(NumberUtils.isDigits("1234.5"), "isDigits(String) neg 1 failed");
        assertFalse(NumberUtils.isDigits("1ab"), "isDigits(String) neg 3 failed");
        assertFalse(NumberUtils.isDigits("abc"), "isDigits(String) neg 4 failed");
    }

    /**
     * Tests isCreatable(String) and tests that createNumber(String) returns a valid number iff isCreatable(String)
     * returns false.
     */
  /*  @Test
    public void testIsNumber() {
        compareIsNumberWithCreateNumber("12345", true);
        compareIsNumberWithCreateNumber("1234.5", true);
        compareIsNumberWithCreateNumber(".12345", true);
        compareIsNumberWithCreateNumber("1234E5", true);
        compareIsNumberWithCreateNumber("1234E+5", true);
        compareIsNumberWithCreateNumber("1234E-5", true);
        compareIsNumberWithCreateNumber("123.4E5", true);
        compareIsNumberWithCreateNumber("-1234", true);
        compareIsNumberWithCreateNumber("-1234.5", true);
        compareIsNumberWithCreateNumber("-.12345", true);
        compareIsNumberWithCreateNumber("-0001.12345", true);
        compareIsNumberWithCreateNumber("-000.12345", true);
        compareIsNumberWithCreateNumber("+00.12345", true);
        compareIsNumberWithCreateNumber("+0002.12345", true);
        compareIsNumberWithCreateNumber("-1234E5", true);
        compareIsNumberWithCreateNumber("0", true);
        compareIsNumberWithCreateNumber("-0", true);
        compareIsNumberWithCreateNumber("01234", true);
        compareIsNumberWithCreateNumber("-01234", true);
        compareIsNumberWithCreateNumber("-0xABC123", true);
        compareIsNumberWithCreateNumber("-0x0", true);
        compareIsNumberWithCreateNumber("123.4E21D", true);
        compareIsNumberWithCreateNumber("-221.23F", true);
        compareIsNumberWithCreateNumber("22338L", true);

        compareIsNumberWithCreateNumber(null, false);
        compareIsNumberWithCreateNumber("", false);
        compareIsNumberWithCreateNumber(" ", false);
        compareIsNumberWithCreateNumber("\r\n\t", false);
        compareIsNumberWithCreateNumber("--2.3", false);

        compareIsNumberWithCreateNumber(".12.3", false);
        compareIsNumberWithCreateNumber("-123E", false);
        compareIsNumberWithCreateNumber("-123E+-212", false);
        compareIsNumberWithCreateNumber("-123E2.12", false);
        compareIsNumberWithCreateNumber("0xGF", false);
        compareIsNumberWithCreateNumber("0xFAE-1", false);
        compareIsNumberWithCreateNumber(".", false);
        compareIsNumberWithCreateNumber("-0ABC123", false);
        compareIsNumberWithCreateNumber("123.4E-D", false);
        compareIsNumberWithCreateNumber("123.4ED", false);
        compareIsNumberWithCreateNumber("+000E.12345", false);
        compareIsNumberWithCreateNumber("-000E.12345", false);
        compareIsNumberWithCreateNumber("1234E5l", false);
        compareIsNumberWithCreateNumber("11a", false);
        compareIsNumberWithCreateNumber("1a", false);
        compareIsNumberWithCreateNumber("a", false);
        compareIsNumberWithCreateNumber("11g", false);
        compareIsNumberWithCreateNumber("11z", false);
        compareIsNumberWithCreateNumber("11def", false);
        compareIsNumberWithCreateNumber("11d11", false);
        compareIsNumberWithCreateNumber("11 11", false);
        compareIsNumberWithCreateNumber(" 1111", false);
        compareIsNumberWithCreateNumber("1111 ", false);

        compareIsNumberWithCreateNumber("2.", true); // LANG-521
        compareIsNumberWithCreateNumber("1.1L", false); // LANG-664
        compareIsNumberWithCreateNumber("+0xF", true); // LANG-1645
        compareIsNumberWithCreateNumber("+0xFFFFFFFF", true); // LANG-1645
        compareIsNumberWithCreateNumber("+0xFFFFFFFFFFFFFFFF", true); // LANG-1645
        compareIsNumberWithCreateNumber(".0", true); // LANG-1646
        compareIsNumberWithCreateNumber("0.", true); // LANG-1646
        compareIsNumberWithCreateNumber("0.D", true); // LANG-1646
        compareIsNumberWithCreateNumber("0e1", true); // LANG-1646
        compareIsNumberWithCreateNumber("0e1D", true); // LANG-1646
        compareIsNumberWithCreateNumber(".D", false); // LANG-1646
        compareIsNumberWithCreateNumber(".e10", false); // LANG-1646
        compareIsNumberWithCreateNumber(".e10D", false); // LANG-1646
    }

    @Test
    public void testIsNumberLANG1252() {
        compareIsNumberWithCreateNumber("+2", true);
        compareIsNumberWithCreateNumber("+2.0", true);
    }

    @Test
    public void testIsNumberLANG1385() {
        compareIsNumberWithCreateNumber("L", false);
    }

    @Test
    public void testIsNumberLANG971() {
        compareIsNumberWithCreateNumber("0085", false);
        compareIsNumberWithCreateNumber("085", false);
        compareIsNumberWithCreateNumber("08", false);
        compareIsNumberWithCreateNumber("07", true);
        compareIsNumberWithCreateNumber("00", true);
    }

    @Test
    public void testIsNumberLANG972() {
        compareIsNumberWithCreateNumber("0xABCD", true);
        compareIsNumberWithCreateNumber("0XABCD", true);
    }

    @Test
    public void testIsNumberLANG992() {
        compareIsNumberWithCreateNumber("0.0", true);
        compareIsNumberWithCreateNumber("0.4790", true);
    }

    @Test
    public void testIsParsable() {
        assertFalse(NumberUtils.isParsable(null));
        assertFalse(NumberUtils.isParsable(""));
        assertFalse(NumberUtils.isParsable("0xC1AB"));
        assertFalse(NumberUtils.isParsable("65CBA2"));
        assertFalse(NumberUtils.isParsable("pendro"));
        assertFalse(NumberUtils.isParsable("64, 2"));
        assertFalse(NumberUtils.isParsable("64.2.2"));
        assertFalse(NumberUtils.isParsable("64."));
        assertFalse(NumberUtils.isParsable("64L"));
        assertFalse(NumberUtils.isParsable("-"));
        assertFalse(NumberUtils.isParsable("--2"));
        assertTrue(NumberUtils.isParsable("64.2"));
        assertTrue(NumberUtils.isParsable("64"));
        assertTrue(NumberUtils.isParsable("018"));
        assertTrue(NumberUtils.isParsable(".18"));
        assertTrue(NumberUtils.isParsable("-65"));
        assertTrue(NumberUtils.isParsable("-018"));
        assertTrue(NumberUtils.isParsable("-018.2"));
        assertTrue(NumberUtils.isParsable("-.236"));
    }*/

    @Test
    public void testLang1087() {
        // no sign cases
try{       
		
		
       // assertEquals(Float.valueOf("0.0"), NumberUtils.createNumber("0.0"));
		printTestOutput(49,model.createNumber("0.0"));// test 49
		} catch (final Exception e) {

        printTestOutput(49,Exception.class);// test 
		} 
		
		 try{
	  //assertEquals(Float.valueOf("+0.0"), NumberUtils.createNumber("+0.0"));
		printTestOutput(50,model.createNumber("+0.0"));// test 50
		} catch (final Exception e) {

        printTestOutput(50,Exception.class);// test 
		} 
		
		
		
        
		
        try{
			//assertEquals(Float.valueOf("-0.0"), NumberUtils.createNumber("-0.0"));
		printTestOutput(51,model.createNumber("-0.0"));// test 51
		 } catch (final Exception e) {

        printTestOutput(51,Exception.class);// test 
		} 
		
		
		
		
	}

    /*@Test
    public void testLANG1252() {
        compareIsCreatableWithCreateNumber("+2", true);
        compareIsCreatableWithCreateNumber("+2.0", true);
    }

    @Test
    public void testLang300() {
        NumberUtils.createNumber("-1l");
        NumberUtils.createNumber("01l");
        NumberUtils.createNumber("1l");
    }

    @Test
    public void testLang381() {
        assertTrue(Double.isNaN(NumberUtils.min(1.2, 2.5, Double.NaN)));
        assertTrue(Double.isNaN(NumberUtils.max(1.2, 2.5, Double.NaN)));
        assertTrue(Float.isNaN(NumberUtils.min(1.2f, 2.5f, Float.NaN)));
        assertTrue(Float.isNaN(NumberUtils.max(1.2f, 2.5f, Float.NaN)));

        final double[] a = {1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        assertTrue(Double.isNaN(NumberUtils.max(a)));
        assertTrue(Double.isNaN(NumberUtils.min(a)));

        final double[] b = {Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        assertTrue(Double.isNaN(NumberUtils.max(b)));
        assertTrue(Double.isNaN(NumberUtils.min(b)));

        final float[] aF = {1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN};
        assertTrue(Float.isNaN(NumberUtils.max(aF)));

        final float[] bF = {Float.NaN, 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN};
        assertTrue(Float.isNaN(NumberUtils.max(bF)));
    }
*/
    @Test
    public void TestLang747() {
       try{


		//assertEquals(Integer.valueOf(0x8000), NumberUtils.createNumber("0x8000"));
		printTestOutput(82,model.createNumber("0x8000"));// test 82
		} catch (final Exception e) {

        printTestOutput(82,Exception.class);// test 
		} 
		
		
		
          try{
			 // assertEquals(Integer.valueOf(0x80000), NumberUtils.createNumber("0x80000"));
		  printTestOutput(83,model.createNumber("0x80000"));// test 83
		  } catch (final Exception e) {

        printTestOutput(83,Exception.class);// test 
		} 
		
		  
        try{
			//assertEquals(Integer.valueOf(0x800000), NumberUtils.createNumber("0x800000"));
		printTestOutput(84,model.createNumber("0x800000"));// test 84
		} catch (final Exception e) {

        printTestOutput(84,Exception.class);// test 
		} 
		
		
        try{
			//assertEquals(Integer.valueOf(0x8000000), NumberUtils.createNumber("0x8000000"));
		printTestOutput(85,model.createNumber("0x8000000"));// test 85
		} catch (final Exception e) {

        printTestOutput(85,Exception.class);// test 
		} 
		
		
        try{
			//assertEquals(Integer.valueOf(0x7FFFFFFF), NumberUtils.createNumber("0x7FFFFFFF"));
		printTestOutput(86,model.createNumber("0x7FFFFFFF"));// test 86
		} catch (final Exception e) {

        printTestOutput(86,Exception.class);// test 
		} 
		
		
        try{
			//assertEquals(Long.valueOf(0x80000000L), NumberUtils.createNumber("0x80000000"));
		printTestOutput(87,model.createNumber("0x80000000"));// test 87
		} catch (final Exception e) {

        printTestOutput(87,Exception.class);// test 
		} 
		
		
       try{
		   //assertEquals(Long.valueOf(0xFFFFFFFFL), NumberUtils.createNumber("0xFFFFFFFF"));
		printTestOutput(88,model.createNumber("0xFFFFFFFF"));// test 88
		} catch (final Exception e) {

        printTestOutput(88,Exception.class);// test 
		} 
		
		

        // Leading zero tests
       try{
		  // assertEquals(Integer.valueOf(0x8000000), NumberUtils.createNumber("0x08000000"));
		printTestOutput(89,model.createNumber("0x08000000"));// test 89
		} catch (final Exception e) {

        printTestOutput(89,Exception.class);// test 
		} 
		
		
       try{
		  // assertEquals(Integer.valueOf(0x7FFFFFFF), NumberUtils.createNumber("0x007FFFFFFF"));
		printTestOutput(90,model.createNumber("0x007FFFFFFF"));// test 90
		} catch (final Exception e) {

        printTestOutput(90,Exception.class);// test 
		} 
		
		
        try{
			//assertEquals(Long.valueOf(0x80000000L), NumberUtils.createNumber("0x080000000"));
		printTestOutput(91,model.createNumber("0x080000000"));// test 91
		
		} catch (final Exception e) {

        printTestOutput(91,Exception.class);// test 
		} 
		
        try{
			//assertEquals(Long.valueOf(0xFFFFFFFFL), NumberUtils.createNumber("0x00FFFFFFFF"));
		printTestOutput(92,model.createNumber("0x00FFFFFFFF"));// test 92
		} catch (final Exception e) {

        printTestOutput(92,Exception.class);// test 
		} 
		
		

       try{
		  // assertEquals(Long.valueOf(0x800000000L), NumberUtils.createNumber("0x800000000"));
		printTestOutput(93,model.createNumber("0x800000000"));// test 93
		} catch (final Exception e) {

        printTestOutput(93,Exception.class);// test 
		} 
		
		
       try{
		   //assertEquals(Long.valueOf(0x8000000000L), NumberUtils.createNumber("0x8000000000"));
		printTestOutput(94,model.createNumber("0x8000000000"));// test 94
		} catch (final Exception e) {

        printTestOutput(94,Exception.class);// test 
		} 
		
       try{
		   //assertEquals(Long.valueOf(0x80000000000L), NumberUtils.createNumber("0x80000000000"));
		printTestOutput(95,model.createNumber("0x80000000000"));// test 95
		} catch (final Exception e) {

        printTestOutput(95,Exception.class);// test 
		} 
		
       try{
		   //assertEquals(Long.valueOf(0x800000000000L), NumberUtils.createNumber("0x800000000000"));
		printTestOutput(96,model.createNumber("0x800000000000"));// test 96
		} catch (final Exception e) {

        printTestOutput(96,Exception.class);// test 
		} 
		
        try{
			//assertEquals(Long.valueOf(0x8000000000000L), NumberUtils.createNumber("0x8000000000000"));
		printTestOutput(97,model.createNumber("0x8000000000000"));// test 97
		} catch (final Exception e) {

        printTestOutput(97,Exception.class);// test 
		} 
		
        try{
			//assertEquals(Long.valueOf(0x80000000000000L), NumberUtils.createNumber("0x80000000000000"));
		printTestOutput(98,model.createNumber("0x80000000000000"));// test 98
		} catch (final Exception e) {

        printTestOutput(98,Exception.class);// test 
		} 
		
       try{
		   //assertEquals(Long.valueOf(0x800000000000000L), NumberUtils.createNumber("0x800000000000000"));
		printTestOutput(99,model.createNumber("0x800000000000000"));// test 99
		} catch (final NumberFormatException e) {

        printTestOutput(99,NumberFormatException.class);// test 
		} 
		
		
       try{
		  // assertEquals(Long.valueOf(0x7FFFFFFFFFFFFFFFL), NumberUtils.createNumber("0x7FFFFFFFFFFFFFFF"));
		printTestOutput(100,model.createNumber("0x7FFFFFFFFFFFFFFF"));// test 100
		} catch (final Exception e) {

        printTestOutput(100,Exception.class);// test 
		} 
		
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
       try{
		  // assertEquals(new BigInteger("8000000000000000", 16), NumberUtils.createNumber("0x8000000000000000"));
		printTestOutput(101,model.createNumber("0x8000000000000000"));// test 101
		} catch (final Exception e) {

        printTestOutput(101,Exception.class);// test 
		} 
		
		
       try{
		  // assertEquals(new BigInteger("FFFFFFFFFFFFFFFF", 16), NumberUtils.createNumber("0xFFFFFFFFFFFFFFFF"));
		printTestOutput(102,model.createNumber("0xFFFFFFFFFFFFFFFF"));// test 102
		} catch (final Exception e) {

        printTestOutput(102,Exception.class);// test 
		} 
		

        // Leading zero tests
        try{
			//assertEquals(Long.valueOf(0x80000000000000L), NumberUtils.createNumber("0x00080000000000000"));
		printTestOutput(103,model.createNumber("0x00080000000000000"));// test 103
		} catch (final Exception e) {

        printTestOutput(103,Exception.class);// test 
		} 
		
       try{
		  // assertEquals(Long.valueOf(0x800000000000000L), NumberUtils.createNumber("0x0800000000000000"));
		printTestOutput(104,model.createNumber("0x0800000000000000"));// test 104
		} catch (final Exception e) {

        printTestOutput(104,Exception.class);// test 
		} 
		
        try{
			//assertEquals(Long.valueOf(0x7FFFFFFFFFFFFFFFL), NumberUtils.createNumber("0x07FFFFFFFFFFFFFFF"));
		printTestOutput(105,model.createNumber("0x07FFFFFFFFFFFFFFF"));// test 105
		} catch (final Exception e) {

        printTestOutput(105,Exception.class);// test 
		} 
		
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
       try{
		   //assertEquals(new BigInteger("8000000000000000", 16), NumberUtils.createNumber("0x00008000000000000000"));
		printTestOutput(106,model.createNumber("0x00008000000000000000"));// test 106
		} catch (final Exception e) {

        printTestOutput(106,Exception.class);// test 
		} 
		 
       try{
		  // assertEquals(new BigInteger("FFFFFFFFFFFFFFFF", 16), NumberUtils.createNumber("0x0FFFFFFFFFFFFFFFF"));
		printTestOutput(107,model.createNumber("0x0FFFFFFFFFFFFFFFF"));// test 107
	   } catch (final Exception e) {

        printTestOutput(107,Exception.class);// test 
		} 
		
	}
  /*  @Test
    public void testLANG971() {
        compareIsCreatableWithCreateNumber("0085", false);
        compareIsCreatableWithCreateNumber("085", false);
        compareIsCreatableWithCreateNumber("08", false);
        compareIsCreatableWithCreateNumber("07", true);
        compareIsCreatableWithCreateNumber("00", true);
    }

    @Test
    public void testLANG972() {
        compareIsCreatableWithCreateNumber("0xABCD", true);
        compareIsCreatableWithCreateNumber("0XABCD", true);
    }

    @Test
    public void testLANG992() {
        compareIsCreatableWithCreateNumber("0.0", true);
        compareIsCreatableWithCreateNumber("0.4790", true);
    }

    @Test
    public void testMaxByte() {
        assertEquals((byte) 5, NumberUtils.max((byte) 5), "max(byte[]) failed for array length 1");
        assertEquals((byte) 9, NumberUtils.max((byte) 6, (byte) 9), "max(byte[]) failed for array length 2");
        assertEquals((byte) 10, NumberUtils.max((byte) -10, (byte) -5, (byte) 0, (byte) 5, (byte) 10),
            "max(byte[]) failed for array length 5");
        assertEquals((byte) 10, NumberUtils.max((byte) -10, (byte) -5, (byte) 0, (byte) 5, (byte) 10));
        assertEquals((byte) 10, NumberUtils.max((byte) -5, (byte) 0, (byte) 10, (byte) 5, (byte) -10));
    }

    @Test
    public void testMaxByte_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxByte_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((byte[]) null));
    }

    @Test
    public void testMaxDouble() {
        final double[] d = null;
        assertThrows(NullPointerException.class, () -> NumberUtils.max(d), "No exception was thrown for null input.");

        assertThrows(IllegalArgumentException.class, NumberUtils::max, "No exception was thrown for empty input.");

        assertEquals(5.1f, NumberUtils.max(5.1f), "max(double[]) failed for array length 1");
        assertEquals(9.2f, NumberUtils.max(6.3f, 9.2f), "max(double[]) failed for array length 2");
        assertEquals(10.4f, NumberUtils.max(-10.5f, -5.6f, 0, 5.7f, 10.4f), "max(double[]) failed for float length 5");
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10), 0.0001);
        assertEquals(10, NumberUtils.max(-5, 0, 10, 5, -10), 0.0001);
    }

    @Test
    public void testMaxDouble_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxDouble_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((double[]) null));
    }

    @Test
    public void testMaxFloat() {
        assertEquals(5.1f, NumberUtils.max(5.1f), "max(float[]) failed for array length 1");
        assertEquals(9.2f, NumberUtils.max(6.3f, 9.2f), "max(float[]) failed for array length 2");
        assertEquals(10.4f, NumberUtils.max(-10.5f, -5.6f, 0, 5.7f, 10.4f), "max(float[]) failed for float length 5");
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10), 0.0001f);
        assertEquals(10, NumberUtils.max(-5, 0, 10, 5, -10), 0.0001f);
    }

    @Test
    public void testMaxFloat_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxFloat_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((float[]) null));
    }

    @Test
    public void testMaximumByte() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), "maximum(byte, byte, byte) 1 failed");
        assertEquals(high, NumberUtils.max(mid, low, high), "maximum(byte, byte, byte) 2 failed");
        assertEquals(high, NumberUtils.max(mid, high, low), "maximum(byte, byte, byte) 3 failed");
        assertEquals(high, NumberUtils.max(high, mid, high), "maximum(byte, byte, byte) 4 failed");
    }

    @Test
    public void testMaximumDouble() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001);
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001);
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001);
        assertEquals(mid, NumberUtils.max(low, mid, low), 0.0001);
        assertEquals(high, NumberUtils.max(high, mid, high), 0.0001);
    }

    @Test
    public void testMaximumFloat() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001f);
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001f);
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001f);
        assertEquals(mid, NumberUtils.max(low, mid, low), 0.0001f);
        assertEquals(high, NumberUtils.max(high, mid, high), 0.0001f);
    }

    @Test
    public void testMaximumInt() {
        assertEquals(12345, NumberUtils.max(12345, 12345 - 1, 12345 - 2), "maximum(int, int, int) 1 failed");
        assertEquals(12345, NumberUtils.max(12345 - 1, 12345, 12345 - 2), "maximum(int, int, int) 2 failed");
        assertEquals(12345, NumberUtils.max(12345 - 1, 12345 - 2, 12345), "maximum(int, int, int) 3 failed");
        assertEquals(12345, NumberUtils.max(12345 - 1, 12345, 12345), "maximum(int, int, int) 4 failed");
        assertEquals(12345, NumberUtils.max(12345, 12345, 12345), "maximum(int, int, int) 5 failed");
    }

    @Test
    public void testMaximumLong() {
        assertEquals(12345L, NumberUtils.max(12345L, 12345L - 1L, 12345L - 2L), "maximum(long, long, long) 1 failed");
        assertEquals(12345L, NumberUtils.max(12345L - 1L, 12345L, 12345L - 2L), "maximum(long, long, long) 2 failed");
        assertEquals(12345L, NumberUtils.max(12345L - 1L, 12345L - 2L, 12345L), "maximum(long, long, long) 3 failed");
        assertEquals(12345L, NumberUtils.max(12345L - 1L, 12345L, 12345L), "maximum(long, long, long) 4 failed");
        assertEquals(12345L, NumberUtils.max(12345L, 12345L, 12345L), "maximum(long, long, long) 5 failed");
    }

    @Test
    public void testMaximumShort() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), "maximum(short, short, short) 1 failed");
        assertEquals(high, NumberUtils.max(mid, low, high), "maximum(short, short, short) 2 failed");
        assertEquals(high, NumberUtils.max(mid, high, low), "maximum(short, short, short) 3 failed");
        assertEquals(high, NumberUtils.max(high, mid, high), "maximum(short, short, short) 4 failed");
    }

    @Test
    public void testMaxInt() {
        assertEquals(5, NumberUtils.max(5), "max(int[]) failed for array length 1");
        assertEquals(9, NumberUtils.max(6, 9), "max(int[]) failed for array length 2");
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10), "max(int[]) failed for array length 5");
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10));
        assertEquals(10, NumberUtils.max(-5, 0, 10, 5, -10));
    }

    @Test
    public void testMaxInt_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxInt_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((int[]) null));
    }

    @Test
    public void testMaxLong() {
        assertEquals(5L, NumberUtils.max(5L), "max(long[]) failed for array length 1");
        assertEquals(9L, NumberUtils.max(6L, 9L), "max(long[]) failed for array length 2");
        assertEquals(10L, NumberUtils.max(-10L, -5L, 0L, 5L, 10L), "max(long[]) failed for array length 5");
        assertEquals(10L, NumberUtils.max(-10L, -5L, 0L, 5L, 10L));
        assertEquals(10L, NumberUtils.max(-5L, 0L, 10L, 5L, -10L));
    }

    @Test
    public void testMaxLong_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxLong_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((long[]) null));
    }

    @Test
    public void testMaxShort() {
        assertEquals((short) 5, NumberUtils.max((short) 5), "max(short[]) failed for array length 1");
        assertEquals((short) 9, NumberUtils.max((short) 6, (short) 9), "max(short[]) failed for array length 2");
        assertEquals((short) 10, NumberUtils.max((short) -10, (short) -5, (short) 0, (short) 5, (short) 10),
            "max(short[]) failed for array length 5");
        assertEquals((short) 10, NumberUtils.max((short) -10, (short) -5, (short) 0, (short) 5, (short) 10));
        assertEquals((short) 10, NumberUtils.max((short) -5, (short) 0, (short) 10, (short) 5, (short) -10));
    }

    @Test
    public void testMaxShort_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxShort_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.max((short[]) null));
    }

    @Test
    public void testMinByte() {
        assertEquals((byte) 5, NumberUtils.min((byte) 5), "min(byte[]) failed for array length 1");
        assertEquals((byte) 6, NumberUtils.min((byte) 6, (byte) 9), "min(byte[]) failed for array length 2");

        assertEquals((byte) -10, NumberUtils.min((byte) -10, (byte) -5, (byte) 0, (byte) 5, (byte) 10));
        assertEquals((byte) -10, NumberUtils.min((byte) -5, (byte) 0, (byte) -10, (byte) 5, (byte) 10));
    }

    @Test
    public void testMinByte_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinByte_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((byte[]) null));
    }

    @Test
    public void testMinDouble() {
        assertEquals(5.12, NumberUtils.min(5.12), "min(double[]) failed for array length 1");
        assertEquals(6.23, NumberUtils.min(6.23, 9.34), "min(double[]) failed for array length 2");
        assertEquals(-10.45, NumberUtils.min(-10.45, -5.56, 0, 5.67, 10.78), "min(double[]) failed for array length 5");
        assertEquals(-10, NumberUtils.min(-10, -5, 0, 5, 10), 0.0001);
        assertEquals(-10, NumberUtils.min(-5, 0, -10, 5, 10), 0.0001);
        assertEquals(5.12, NumberUtils.min(6.11, 5.12));
    }

    @Test
    public void testMinDouble_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinDouble_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((double[]) null));
    }

    @Test
    public void testMinFloat() {
        assertEquals(5.9f, NumberUtils.min(5.9f), "min(float[]) failed for array length 1");
        assertEquals(6.8f, NumberUtils.min(6.8f, 9.7f), "min(float[]) failed for array length 2");
        assertEquals(-10.6f, NumberUtils.min(-10.6f, -5.5f, 0, 5.4f, 10.3f), "min(float[]) failed for array length 5");
        assertEquals(-10, NumberUtils.min(-10, -5, 0, 5, 10), 0.0001f);
        assertEquals(-10, NumberUtils.min(-5, 0, -10, 5, 10), 0.0001f);
        assertEquals(Float.NaN, NumberUtils.min(6.8f, Float.NaN));
        assertEquals(3.7f, NumberUtils.min(6.8f, 3.7f));
    }

    @Test
    public void testMinFloat_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinFloat_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((float[]) null));
    }

    @Test
    public void testMinimumByte() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), "minimum(byte, byte, byte) 1 failed");
        assertEquals(low, NumberUtils.min(mid, low, high), "minimum(byte, byte, byte) 2 failed");
        assertEquals(low, NumberUtils.min(mid, high, low), "minimum(byte, byte, byte) 3 failed");
        assertEquals(low, NumberUtils.min(low, mid, low), "minimum(byte, byte, byte) 4 failed");
    }

    @Test
    public void testMinimumDouble() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001);
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001);
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001);
        assertEquals(low, NumberUtils.min(low, mid, low), 0.0001);
        assertEquals(mid, NumberUtils.min(high, mid, high), 0.0001);
    }

    @Test
    public void testMinimumFloat() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001f);
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001f);
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001f);
        assertEquals(low, NumberUtils.min(low, mid, low), 0.0001f);
        assertEquals(mid, NumberUtils.min(high, mid, high), 0.0001f);
    }

    @Test
    public void testMinimumInt() {
        assertEquals(12345, NumberUtils.min(12345, 12345 + 1, 12345 + 2), "minimum(int, int, int) 1 failed");
        assertEquals(12345, NumberUtils.min(12345 + 1, 12345, 12345 + 2), "minimum(int, int, int) 2 failed");
        assertEquals(12345, NumberUtils.min(12345 + 1, 12345 + 2, 12345), "minimum(int, int, int) 3 failed");
        assertEquals(12345, NumberUtils.min(12345 + 1, 12345, 12345), "minimum(int, int, int) 4 failed");
        assertEquals(12345, NumberUtils.min(12345, 12345, 12345), "minimum(int, int, int) 5 failed");
    }

    @Test
    public void testMinimumLong() {
        assertEquals(12345L, NumberUtils.min(12345L, 12345L + 1L, 12345L + 2L), "minimum(long, long, long) 1 failed");
        assertEquals(12345L, NumberUtils.min(12345L + 1L, 12345L, 12345 + 2L), "minimum(long, long, long) 2 failed");
        assertEquals(12345L, NumberUtils.min(12345L + 1L, 12345L + 2L, 12345L), "minimum(long, long, long) 3 failed");
        assertEquals(12345L, NumberUtils.min(12345L + 1L, 12345L, 12345L), "minimum(long, long, long) 4 failed");
        assertEquals(12345L, NumberUtils.min(12345L, 12345L, 12345L), "minimum(long, long, long) 5 failed");
    }

    @Test
    public void testMinimumShort() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), "minimum(short, short, short) 1 failed");
        assertEquals(low, NumberUtils.min(mid, low, high), "minimum(short, short, short) 2 failed");
        assertEquals(low, NumberUtils.min(mid, high, low), "minimum(short, short, short) 3 failed");
        assertEquals(low, NumberUtils.min(low, mid, low), "minimum(short, short, short) 4 failed");
    }

    @Test
    public void testMinInt() {
        assertEquals(5, NumberUtils.min(5), "min(int[]) failed for array length 1");
        assertEquals(6, NumberUtils.min(6, 9), "min(int[]) failed for array length 2");

        assertEquals(-10, NumberUtils.min(-10, -5, 0, 5, 10));
        assertEquals(-10, NumberUtils.min(-5, 0, -10, 5, 10));
    }

    @Test
    public void testMinInt_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinInt_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((int[]) null));
    }

    @Test
    public void testMinLong() {
        assertEquals(5L, NumberUtils.min(5L), "min(long[]) failed for array length 1");
        assertEquals(6L, NumberUtils.min(6L, 9L), "min(long[]) failed for array length 2");

        assertEquals(-10L, NumberUtils.min(-10L, -5L, 0L, 5L, 10L));
        assertEquals(-10L, NumberUtils.min(-5L, 0L, -10L, 5L, 10L));
    }

    @Test
    public void testMinLong_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinLong_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((long[]) null));
    }

    @Test
    public void testMinShort() {
        assertEquals((short) 5, NumberUtils.min((short) 5), "min(short[]) failed for array length 1");
        assertEquals((short) 6, NumberUtils.min((short) 6, (short) 9), "min(short[]) failed for array length 2");

        assertEquals((short) -10, NumberUtils.min((short) -10, (short) -5, (short) 0, (short) 5, (short) 10));
        assertEquals((short) -10, NumberUtils.min((short) -5, (short) 0, (short) -10, (short) 5, (short) 10));
    }

    @Test
    public void testMinShort_emptyArray() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinShort_nullArray() {
        assertThrows(NullPointerException.class, () -> NumberUtils.min((short[]) null));
    }

    /**
     * Test for {(@link NumberUtils#createNumber(String)}
     */
  /*  @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        assertTrue(NumberUtils.createNumber(shouldBeFloat) instanceof Float);

        assertTrue(NumberUtils.createNumber(shouldBeDouble) instanceof Double);
		
        assertTrue(NumberUtils.createNumber(shouldBeBigDecimal) instanceof BigDecimal);
		
        // LANG-1060
        assertTrue(NumberUtils.createNumber("001.12") instanceof Float);
		
        assertTrue(NumberUtils.createNumber("-001.12") instanceof Float);
		
        assertTrue(NumberUtils.createNumber("+001.12") instanceof Float);
		
        assertTrue(NumberUtils.createNumber("003.40282354e+38") instanceof Double);
		
        assertTrue(NumberUtils.createNumber("-003.40282354e+38") instanceof Double);
		
        assertTrue(NumberUtils.createNumber("+003.40282354e+38") instanceof Double);
		
        assertTrue(NumberUtils.createNumber("0001.797693134862315759e+308") instanceof BigDecimal);
		
        assertTrue(NumberUtils.createNumber("-001.797693134862315759e+308") instanceof BigDecimal);
		
        assertTrue(NumberUtils.createNumber("+001.797693134862315759e+308") instanceof BigDecimal);
		
        //LANG-1613
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_NORMAL)) instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_NORMAL) + "D") instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_NORMAL) + "F") instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_VALUE)) instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_VALUE) + "D") instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_VALUE) + "F") instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MAX_VALUE)) instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MAX_VALUE) + "D") instanceof Double);
		
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MAX_VALUE) + "F") instanceof Double);
		
        assertTrue(NumberUtils.createNumber("4.9e-324D") instanceof Double);
		
        assertTrue(NumberUtils.createNumber("4.9e-324F") instanceof Double);
		
    }

    /**
     * Test for {@link NumberUtils#toDouble(String)}.
     */
   /* @Test
    public void testStringToDoubleString() {
        assertEquals(NumberUtils.toDouble("-1.2345"), -1.2345d, "toDouble(String) 1 failed");
        assertEquals(1.2345d, NumberUtils.toDouble("1.2345"), "toDouble(String) 2 failed");
        assertEquals(0.0d, NumberUtils.toDouble("abc"), "toDouble(String) 3 failed");
        // LANG-1060
        assertEquals(NumberUtils.toDouble("-001.2345"), -1.2345d, "toDouble(String) 4 failed");
        assertEquals(1.2345d, NumberUtils.toDouble("+001.2345"), "toDouble(String) 5 failed");
        assertEquals(1.2345d, NumberUtils.toDouble("001.2345"), "toDouble(String) 6 failed");
        assertEquals(0d, NumberUtils.toDouble("000.00000"), "toDouble(String) 7 failed");

        assertEquals(NumberUtils.toDouble(Double.MAX_VALUE + ""), Double.MAX_VALUE,
            "toDouble(Double.MAX_VALUE) failed");
        assertEquals(NumberUtils.toDouble(Double.MIN_VALUE + ""), Double.MIN_VALUE,
            "toDouble(Double.MIN_VALUE) failed");
        assertEquals(0.0d, NumberUtils.toDouble(""), "toDouble(empty) failed");
        assertEquals(0.0d, NumberUtils.toDouble((String) null), "toDouble(null) failed");
    }

    /**
     * Test for {@link NumberUtils#toDouble(String, double)}.
     */
  /*  @Test
    public void testStringToDoubleStringD() {
        assertEquals(1.2345d, NumberUtils.toDouble("1.2345", 5.1d), "toDouble(String, int) 1 failed");
        assertEquals(5.0d, NumberUtils.toDouble("a", 5.0d), "toDouble(String, int) 2 failed");
        // LANG-1060
        assertEquals(1.2345d, NumberUtils.toDouble("001.2345", 5.1d), "toDouble(String, int) 3 failed");
        assertEquals(NumberUtils.toDouble("-001.2345", 5.1d), -1.2345d, "toDouble(String, int) 4 failed");
        assertEquals(1.2345d, NumberUtils.toDouble("+001.2345", 5.1d), "toDouble(String, int) 5 failed");
        assertEquals(0d, NumberUtils.toDouble("000.00", 5.1d), "toDouble(String, int) 7 failed");
    }

    /**
     * Test for {@link NumberUtils#toByte(String)}.
     */
   /* @Test
    public void testToByteString() {
        assertEquals(123, NumberUtils.toByte("123"), "toByte(String) 1 failed");
        assertEquals(0, NumberUtils.toByte("abc"), "toByte(String) 2 failed");
        assertEquals(0, NumberUtils.toByte(""), "toByte(empty) failed");
        assertEquals(0, NumberUtils.toByte(null), "toByte(null) failed");
    }

    /**
     * Test for {@link NumberUtils#toByte(String, byte)}.
     */
   /* @Test
    public void testToByteStringI() {
        assertEquals(123, NumberUtils.toByte("123", (byte) 5), "toByte(String, byte) 1 failed");
        assertEquals(5, NumberUtils.toByte("12.3", (byte) 5), "toByte(String, byte) 2 failed");
    }

    /**
     * Test for {@link NumberUtils#toFloat(String)}.
     */
    /*@Test
    public void testToFloatString() {
        assertEquals(NumberUtils.toFloat("-1.2345"), -1.2345f, "toFloat(String) 1 failed");
        assertEquals(1.2345f, NumberUtils.toFloat("1.2345"), "toFloat(String) 2 failed");
        assertEquals(0.0f, NumberUtils.toFloat("abc"), "toFloat(String) 3 failed");
        // LANG-1060
        assertEquals(NumberUtils.toFloat("-001.2345"), -1.2345f, "toFloat(String) 4 failed");
        assertEquals(1.2345f, NumberUtils.toFloat("+001.2345"), "toFloat(String) 5 failed");
        assertEquals(1.2345f, NumberUtils.toFloat("001.2345"), "toFloat(String) 6 failed");
        assertEquals(0f, NumberUtils.toFloat("000.00"), "toFloat(String) 7 failed");

        assertEquals(NumberUtils.toFloat(Float.MAX_VALUE + ""), Float.MAX_VALUE, "toFloat(Float.MAX_VALUE) failed");
        assertEquals(NumberUtils.toFloat(Float.MIN_VALUE + ""), Float.MIN_VALUE, "toFloat(Float.MIN_VALUE) failed");
        assertEquals(0.0f, NumberUtils.toFloat(""), "toFloat(empty) failed");
        assertEquals(0.0f, NumberUtils.toFloat(null), "toFloat(null) failed");
    }

    /**
     * Test for {@link NumberUtils#toFloat(String, float)}.
     */
   /* @Test
    public void testToFloatStringF() {
        assertEquals(1.2345f, NumberUtils.toFloat("1.2345", 5.1f), "toFloat(String, int) 1 failed");
        assertEquals(5.0f, NumberUtils.toFloat("a", 5.0f), "toFloat(String, int) 2 failed");
        // LANG-1060
        assertEquals(5.0f, NumberUtils.toFloat("-001Z.2345", 5.0f), "toFloat(String, int) 3 failed");
        assertEquals(5.0f, NumberUtils.toFloat("+001AB.2345", 5.0f), "toFloat(String, int) 4 failed");
        assertEquals(5.0f, NumberUtils.toFloat("001Z.2345", 5.0f), "toFloat(String, int) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toInt(String)}.
     */
   /* @Test
    public void testToIntString() {
        assertEquals(12345, NumberUtils.toInt("12345"), "toInt(String) 1 failed");
        assertEquals(0, NumberUtils.toInt("abc"), "toInt(String) 2 failed");
        assertEquals(0, NumberUtils.toInt(""), "toInt(empty) failed");
        assertEquals(0, NumberUtils.toInt(null), "toInt(null) failed");
    }

    /**
     * Test for {@link NumberUtils#toInt(String, int)}.
     */
   /* @Test
    public void testToIntStringI() {
        assertEquals(12345, NumberUtils.toInt("12345", 5), "toInt(String, int) 1 failed");
        assertEquals(5, NumberUtils.toInt("1234.5", 5), "toInt(String, int) 2 failed");
    }

    /**
     * Test for {@link NumberUtils#toLong(String)}.
     */
    /*@Test
    public void testToLongString() {
        assertEquals(12345L, NumberUtils.toLong("12345"), "toLong(String) 1 failed");
        assertEquals(0L, NumberUtils.toLong("abc"), "toLong(String) 2 failed");
        assertEquals(0L, NumberUtils.toLong("1L"), "toLong(String) 3 failed");
        assertEquals(0L, NumberUtils.toLong("1l"), "toLong(String) 4 failed");
        assertEquals(NumberUtils.toLong(Long.MAX_VALUE + ""), Long.MAX_VALUE, "toLong(Long.MAX_VALUE) failed");
        assertEquals(NumberUtils.toLong(Long.MIN_VALUE + ""), Long.MIN_VALUE, "toLong(Long.MIN_VALUE) failed");
        assertEquals(0L, NumberUtils.toLong(""), "toLong(empty) failed");
        assertEquals(0L, NumberUtils.toLong(null), "toLong(null) failed");
    }

    /**
     * Test for {@link NumberUtils#toLong(String, long)}.
     */
  /*  @Test
    public void testToLongStringL() {
        assertEquals(12345L, NumberUtils.toLong("12345", 5L), "toLong(String, long) 1 failed");
        assertEquals(5L, NumberUtils.toLong("1234.5", 5L), "toLong(String, long) 2 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(BigDecimal)}.
     */
    /*@Test
    public void testToScaledBigDecimalBigDecimal() {
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(123.456)), BigDecimal.valueOf(123.46),
            "toScaledBigDecimal(BigDecimal) 1 failed");
        // Test RoundingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.515)), BigDecimal.valueOf(23.52),
            "toScaledBigDecimal(BigDecimal) 2 failed");
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.525)), BigDecimal.valueOf(23.52),
            "toScaledBigDecimal(BigDecimal) 3 failed");
        assertEquals("2352.00",
            NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.525)).multiply(BigDecimal.valueOf(100)).toString(),
            "toScaledBigDecimal(BigDecimal) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((BigDecimal) null), BigDecimal.ZERO,
            "toScaledBigDecimal(BigDecimal) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(BigDecimal, int, RoundingMode)}.
     */
   /* @Test
    public void testToScaledBigDecimalBigDecimalIRM() {
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(123.456), 1, RoundingMode.CEILING),
            BigDecimal.valueOf(123.5), "toScaledBigDecimal(BigDecimal, int, RoundingMode) 1 failed");
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.5159), 3, RoundingMode.FLOOR),
            BigDecimal.valueOf(23.515), "toScaledBigDecimal(BigDecimal, int, RoundingMode) 2 failed");
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.525), 2, RoundingMode.HALF_UP),
            BigDecimal.valueOf(23.53), "toScaledBigDecimal(BigDecimal, int, RoundingMode) 3 failed");
        assertEquals("23521.0000",
            NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.521), 4, RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(1000)).toString(),
            "toScaledBigDecimal(BigDecimal, int, RoundingMode) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((BigDecimal) null, 2, RoundingMode.HALF_UP), BigDecimal.ZERO,
            "toScaledBigDecimal(BigDecimal, int, RoundingMode) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double)}.
     */
  /*  @Test
    public void testToScaledBigDecimalDouble() {
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(123.456d)), BigDecimal.valueOf(123.46),
            "toScaledBigDecimal(Double) 1 failed");
        // Test RoundingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.515d)), BigDecimal.valueOf(23.52),
            "toScaledBigDecimal(Double) 2 failed");
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.525d)), BigDecimal.valueOf(23.52),
            "toScaledBigDecimal(Double) 3 failed");
        assertEquals("2352.00",
            NumberUtils.toScaledBigDecimal(Double.valueOf(23.525d)).multiply(BigDecimal.valueOf(100)).toString(),
            "toScaledBigDecimal(Double) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((Double) null), BigDecimal.ZERO,
            "toScaledBigDecimal(Double) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double, int, RoundingMode)}.
     */
   /* @Test
    public void testToScaledBigDecimalDoubleIRM() {
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(123.456d), 1, RoundingMode.CEILING),
            BigDecimal.valueOf(123.5), "toScaledBigDecimal(Double, int, RoundingMode) 1 failed");
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.5159d), 3, RoundingMode.FLOOR),
            BigDecimal.valueOf(23.515), "toScaledBigDecimal(Double, int, RoundingMode) 2 failed");
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.525d), 2, RoundingMode.HALF_UP),
            BigDecimal.valueOf(23.53), "toScaledBigDecimal(Double, int, RoundingMode) 3 failed");
        assertEquals("23521.0000",
            NumberUtils.toScaledBigDecimal(Double.valueOf(23.521d), 4, RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(1000)).toString(),
            "toScaledBigDecimal(Double, int, RoundingMode) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((Double) null, 2, RoundingMode.HALF_UP), BigDecimal.ZERO,
            "toScaledBigDecimal(Double, int, RoundingMode) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Float)}.
     */
  /*  @Test
    public void testToScaledBigDecimalFloat() {
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(123.456f)), BigDecimal.valueOf(123.46),
            "toScaledBigDecimal(Float) 1 failed");
        // Test RoundingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.515f)), BigDecimal.valueOf(23.51),
            "toScaledBigDecimal(Float) 2 failed");
        // Note. NumberUtils.toScaledBigDecimal(Float.valueOf(23.515f)).equals(BigDecimal.valueOf(23.51))
        // because of roundoff error. It is ok.
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.525f)), BigDecimal.valueOf(23.52),
            "toScaledBigDecimal(Float) 3 failed");
        assertEquals("2352.00",
            NumberUtils.toScaledBigDecimal(Float.valueOf(23.525f)).multiply(BigDecimal.valueOf(100)).toString(),
            "toScaledBigDecimal(Float) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((Float) null), BigDecimal.ZERO,
            "toScaledBigDecimal(Float) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Float, int, RoundingMode)}.
     */
   /* @Test
    public void testToScaledBigDecimalFloatIRM() {
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(123.456f), 1, RoundingMode.CEILING),
            BigDecimal.valueOf(123.5), "toScaledBigDecimal(Float, int, RoundingMode) 1 failed");
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.5159f), 3, RoundingMode.FLOOR),
            BigDecimal.valueOf(23.515), "toScaledBigDecimal(Float, int, RoundingMode) 2 failed");
        // The following happens due to roundoff error. We're ok with this.
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.525f), 2, RoundingMode.HALF_UP),
            BigDecimal.valueOf(23.52), "toScaledBigDecimal(Float, int, RoundingMode) 3 failed");
        assertEquals("23521.0000", NumberUtils.toScaledBigDecimal(Float.valueOf(23.521f), 4, RoundingMode.HALF_EVEN)
            .multiply(BigDecimal.valueOf(1000)).toString(), "toScaledBigDecimal(Float, int, RoundingMode) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((Float) null, 2, RoundingMode.HALF_UP), BigDecimal.ZERO,
            "toScaledBigDecimal(Float, int, RoundingMode) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double)}.
     */
   /* @Test
    public void testToScaledBigDecimalString() {
        assertEquals(NumberUtils.toScaledBigDecimal("123.456"), BigDecimal.valueOf(123.46),
            "toScaledBigDecimal(String) 1 failed");
        // Test RoundingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal("23.515"), BigDecimal.valueOf(23.52),
            "toScaledBigDecimal(String) 2 failed");
        assertEquals(NumberUtils.toScaledBigDecimal("23.525"), BigDecimal.valueOf(23.52),
            "toScaledBigDecimal(String) 3 failed");
        assertEquals("2352.00", NumberUtils.toScaledBigDecimal("23.525").multiply(BigDecimal.valueOf(100)).toString(),
            "toScaledBigDecimal(String) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((String) null), BigDecimal.ZERO,
            "toScaledBigDecimal(String) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double, int, RoundingMode)}.
     */
   /* @Test
    public void testToScaledBigDecimalStringIRM() {
        assertEquals(NumberUtils.toScaledBigDecimal("123.456", 1, RoundingMode.CEILING), BigDecimal.valueOf(123.5),
            "toScaledBigDecimal(String, int, RoundingMode) 1 failed");
        assertEquals(NumberUtils.toScaledBigDecimal("23.5159", 3, RoundingMode.FLOOR), BigDecimal.valueOf(23.515),
            "toScaledBigDecimal(String, int, RoundingMode) 2 failed");
        assertEquals(NumberUtils.toScaledBigDecimal("23.525", 2, RoundingMode.HALF_UP), BigDecimal.valueOf(23.53),
            "toScaledBigDecimal(String, int, RoundingMode) 3 failed");
        assertEquals(
            "23521.0000", NumberUtils.toScaledBigDecimal("23.521", 4, RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(1000)).toString(),
            "toScaledBigDecimal(String, int, RoundingMode) 4 failed");
        assertEquals(NumberUtils.toScaledBigDecimal((String) null, 2, RoundingMode.HALF_UP), BigDecimal.ZERO,
            "toScaledBigDecimal(String, int, RoundingMode) 5 failed");
    }

    /**
     * Test for {@link NumberUtils#toShort(String)}.
     */
   /* @Test
    public void testToShortString() {
        assertEquals(12345, NumberUtils.toShort("12345"), "toShort(String) 1 failed");
        assertEquals(0, NumberUtils.toShort("abc"), "toShort(String) 2 failed");
        assertEquals(0, NumberUtils.toShort(""), "toShort(empty) failed");
        assertEquals(0, NumberUtils.toShort(null), "toShort(null) failed");
    }

    /**
     * Test for {@link NumberUtils#toShort(String, short)}.
     */
   /* @Test
    public void testToShortStringI() {
        assertEquals(12345, NumberUtils.toShort("12345", (short) 5), "toShort(String, short) 1 failed");
        assertEquals(5, NumberUtils.toShort("1234.5", (short) 5), "toShort(String, short) 2 failed");
    }*/
}
