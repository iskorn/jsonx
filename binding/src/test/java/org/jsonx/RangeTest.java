/* Copyright (c) 2018 JSONx
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.jsonx;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

@SuppressWarnings("unused")
public class RangeTest {
  @Test
  public void test() throws ParseException {
    try {
      new Range(null);
      fail("Expected NullPointerException");
    }
    catch (final NullPointerException e) {
    }

    try {
      new Range("");
      fail("Expected IllegalArgumentException");
    }
    catch (final IllegalArgumentException e) {
      assertEquals("Range spec min length is 4, but was 0", e.getMessage());
    }

    try {
      new Range("4323");
      fail("Expected ParseException");
    }
    catch (final ParseException e) {
      assertEquals("Missing '[' or '(' in string: \"4323\" [errorOffset: 0]", e.getMessage());
    }

    try {
      new Range("[4323");
      fail("Expected ParseException");
    }
    catch (final ParseException e) {
      assertEquals("Missing ',' in string: \"[4323\" [errorOffset: 5]", e.getMessage());
    }

    try {
      new Range("[4323]");
      fail("Expected ParseException");
    }
    catch (final ParseException e) {
      assertEquals("Missing ',' in string: \"[4323]\" [errorOffset: 6]", e.getMessage());
    }

    try {
      new Range("[,,4323]");
      fail("Expected ParseException");
    }
    catch (final ParseException e) {
      assertEquals("Illegal ',' in string: \"[,,4323]\" [errorOffset: 2]", e.getMessage());
    }

    try {
      new Range("[10,1]");
      fail("Expected IllegalArgumentException");
    }
    catch (final IllegalArgumentException e) {
      assertEquals("min=\"10\" > max=\"1\"", e.getMessage());
    }

    try {
      new Range("(10,10]");
      fail("Expected IllegalArgumentException");
    }
    catch (final IllegalArgumentException e) {
      assertEquals("(10,10] defines an empty range", e.getMessage());
    }

    assertEquals(new Range(BigDecimal.ONE, true, BigDecimal.TEN, true), new Range("[1,10]"));
    assertEquals(new Range(BigDecimal.ONE, false, BigDecimal.TEN, true), new Range("(1,10]"));
    assertEquals(new Range(BigDecimal.ONE, true, BigDecimal.TEN, false), new Range("[1,10)"));
    assertEquals(new Range(BigDecimal.ONE, false, BigDecimal.TEN, false), new Range("(1,10)"));
    assertEquals(new Range(BigDecimal.TEN, true, BigDecimal.TEN, true), new Range("[10,10]"));

    assertEquals(new Range(null, true, BigDecimal.TEN, true), new Range("[,10]"));
    assertEquals(new Range(BigDecimal.TEN, true, null, true), new Range("[10,]"));
  }
}