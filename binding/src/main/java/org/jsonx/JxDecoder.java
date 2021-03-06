/* Copyright (c) 2015 JSONx
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

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import org.openjax.json.JsonParseException;
import org.openjax.json.JsonReader;
import org.libj.util.function.TriPredicate;

/**
 * Decoder that deserializes JSON documents to objects of {@code JxObject}
 * classes, or to lists conforming to a provided annotation class that declares
 * an {@link ArrayType} annotation.
 */
public final class JxDecoder {
  /**
   * Parses a JSON object from the supplied {@link JsonReader} as per the
   * specification of the provided {@code JxObject} class.
   *
   * @param <T> The type parameter for the return object of {@code JxObject}
   *          class.
   * @param type The class for the return object.
   * @param reader The {@link JsonReader} containing the JSON object.
   * @param onPropertyDecode Callback predicate to be called for each decoded
   *          JSON properties, accepting arguments of:
   *          <ol>
   *          <li>The {@code JxObject}.</li>
   *          <li>The property name.</li>
   *          <li>The property value.</li>
   *          </ol>
   * @return A {@code JxObject} of the specified type representing the parsed
   *         JSON object.
   * @throws DecodeException If an exception has occurred while decoding a JSON
   *           document.
   * @throws IOException If an I/O error has occurred.
   */
  @SuppressWarnings("unchecked")
  public static <T extends JxObject>T parseObject(final Class<T> type, final JsonReader reader, final TriPredicate<JxObject,String,Object> onPropertyDecode) throws DecodeException, IOException {
    final String token = reader.readToken();
    if (!"{".equals(token))
      throw new DecodeException("Expected '{', but got '" + token + "'", reader.getPosition() - 1);

    final Object object = ObjectCodec.decodeObject(type, reader, onPropertyDecode);
    if (object instanceof Error)
      throw new DecodeException((Error)object);

    return (T)object;
  }


  /**
   * Parses a JSON object at the supplied {@link JsonReader} as per the
   * specification of the provided {@code JxObject} class.
   *
   * @param <T> The type parameter for the return object of {@code JxObject}
   *          class.
   * @param type The class for the return object.
   * @param reader The {@link JsonReader} containing the JSON object.
   * @return A {@code JxObject} of the specified type representing the parsed
   *         JSON object.
   * @throws DecodeException If an exception has occurred while decoding a JSON
   *           document.
   * @throws IOException If an I/O error has occurred.
   */
  public static <T extends JxObject>T parseObject(final Class<T> type, final JsonReader reader) throws DecodeException, IOException {
    return parseObject(type, reader, null);
  }

  /**
   * Parses a JSON array from the supplied {@link JsonReader} as per the
   * specification of the provided annotation class that declares
   * an {@link ArrayType} annotation.
   *
   * @param annotationType The annotation class that declares
   *          an {@link ArrayType} annotation.
   * @param reader The {@link JsonReader} containing the JSON array.
   * @return A {@code List} representing the parsed JSON array.
   * @throws DecodeException If an exception has occurred while decoding a JSON
   *           document.
   * @throws JsonParseException If the content is not well formed.
   * @throws IOException If an I/O error has occurred.
   */
  public static List<?> parseArray(final Class<? extends Annotation> annotationType, final JsonReader reader) throws DecodeException, JsonParseException, IOException {
    final String token = reader.readToken();
    if (!"[".equals(token))
      throw new DecodeException("Expected '[', but got '" + token + "'", reader.getPosition() - 1);

    final IdToElement idToElement = new IdToElement();
    final int[] elementIds = JsdUtil.digest(annotationType.getAnnotations(), annotationType.getName(), idToElement);
    final Object array = ArrayCodec.decodeObject(idToElement.get(elementIds), idToElement.getMinIterate(), idToElement.getMaxIterate(), idToElement, reader, null);
    if (array instanceof Error)
      throw new DecodeException((Error)array);

    return (List<?>)array;
  }

  private JxDecoder() {
  }
}