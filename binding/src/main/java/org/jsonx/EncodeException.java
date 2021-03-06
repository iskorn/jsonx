/* Copyright (c) 2016 JSONx
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

/**
 * Signals that an error has occurred while encoding a JSON document.
 */
public class EncodeException extends RuntimeException {
  private static final long serialVersionUID = -5907473656780591942L;

  public EncodeException() {
    this(null, null);
  }

  public EncodeException(final Error error) {
    this(error.toString(), null);
  }

  public EncodeException(final String message) {
    this(message, null);
  }

  public EncodeException(final Throwable cause) {
    this(null, cause);
  }

  public EncodeException(final String message, final Throwable cause) {
    super(message, cause);
  }
}