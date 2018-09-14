/* Copyright (c) 2017 OpenJAX
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

package org.openjax.jsonx.runtime;

import java.math.BigDecimal;
import java.math.BigInteger;

public enum Form {
  INTEGER(BigInteger.class),
  REAL(BigDecimal.class);

  private final Class<? extends Number> type;

  Form(final Class<? extends Number> type) {
    this.type = type;
  }

  public Class<? extends Number> getType() {
    return this.type;
  }
}