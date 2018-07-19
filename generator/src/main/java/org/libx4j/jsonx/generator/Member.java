/* Copyright (c) 2017 lib4j
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

package org.libx4j.jsonx.generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.lib4j.lang.JavaIdentifiers;
import org.lib4j.util.Collections;
import org.lib4j.xml.datatypes_1_0_4.xL3gluGCXYYJc.$JavaIdentifier;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$Array;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$MaxCardinality;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$Number;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$Object;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$String;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$Template;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.Jsonx;
import org.libx4j.jsonx.runtime.ArrayProperty;
import org.libx4j.jsonx.runtime.BooleanProperty;
import org.libx4j.jsonx.runtime.NumberProperty;
import org.libx4j.jsonx.runtime.ObjectProperty;
import org.libx4j.jsonx.runtime.StringProperty;
import org.libx4j.xsb.runtime.Binding;
import org.libx4j.xsb.runtime.Bindings;
import org.w3.www._2001.XMLSchema.yAA.$Boolean;
import org.w3.www._2001.XMLSchema.yAA.$NonNegativeInteger;

abstract class Member extends Element {
  protected static Member toMember(final Registry registry, final ComplexModel declarer, final Field field) {
    final BooleanProperty booleanProperty = field.getDeclaredAnnotation(BooleanProperty.class);
    if (booleanProperty != null)
      return BooleanModel.referenceOrDeclare(registry, declarer, booleanProperty, field);

    final NumberProperty numberProperty = field.getDeclaredAnnotation(NumberProperty.class);
    if (numberProperty != null)
      return NumberModel.referenceOrDeclare(registry, declarer, numberProperty, field);

    final StringProperty stringProperty = field.getDeclaredAnnotation(StringProperty.class);
    if (stringProperty != null)
      return StringModel.referenceOrDeclare(registry, declarer, stringProperty, field);

    final ObjectProperty objectProperty = field.getDeclaredAnnotation(ObjectProperty.class);
    if (objectProperty != null)
      return ObjectModel.referenceOrDeclare(registry, declarer, objectProperty, field);

    final ArrayProperty arrayProperty = field.getDeclaredAnnotation(ArrayProperty.class);
    if (arrayProperty != null)
      return ArrayModel.referenceOrDeclare(registry, declarer, arrayProperty, field);

    return null;
  }

  protected static String getName(final String name, final Field field) {
    return name.length() > 0 ? name : field.getName();
  }

  protected static final Function<Binding,String> elementXPath = new Function<>() {
    @Override
    public String apply(final Binding t) {
      final String name;
      if (t instanceof xL2gluGCXYYJc.$Boolean)
        name = ((xL2gluGCXYYJc.$Boolean)t).getName$().text();
      else if (t instanceof $Number)
        name = (($Number)t).getName$().text();
      else if (t instanceof $String)
        name = (($String)t).getName$().text();
      else if (t instanceof $Array)
        name = (($Array)t).getName$().text();
      else if (t instanceof Jsonx.Object)
        name = ((Jsonx.Object)t).getClass$().text();
      else if (t instanceof xL2gluGCXYYJc.$Boolean)
        name = ((xL2gluGCXYYJc.$Boolean)t).getName$().text();
      else if (t instanceof $Number)
        name = (($Number)t).getName$().text();
      else if (t instanceof $String)
        name = (($String)t).getName$().text();
      else if (t instanceof $Array)
        name = (($Array)t).getName$().text();
      else if (t instanceof $Template)
        name = (($Template)t).getName$().text();
      else if (t instanceof $Object)
        name = (($Object)t).getName$().text();
      else
        name = null;

      return name != null ? t.name().getLocalPart() + "[@" + (t instanceof $Object ? "class=" : "name=") + name + "]" : t.name().getLocalPart();
    }
  };

  private static Integer parseMaxCardinality(final int minCardinality, final $MaxCardinality maxCardinality) {
    final Integer max = "unbounded".equals(maxCardinality.text()) ? null : Integer.parseInt(maxCardinality.text());
    if (max != null && minCardinality > max)
      throw new ValidationException(Bindings.getXPath(((org.libx4j.xsb.runtime.Attribute)maxCardinality).owner(), elementXPath) + ": minOccurs=\"" + minCardinality + "\" > maxOccurs=\"" + max + "\"");

    return max;
  }

  protected final Registry registry;
  private final String name;
  private final Boolean nullable;
  private final Boolean required;
  private final Integer minOccurs;
  private final Integer maxOccurs;

  public Member(final Registry registry, final String name, final Boolean nullable, final Boolean required, final Integer minOccurs, final Integer maxOccurs) {
    this.registry = registry;
    this.name = name;
    this.nullable = nullable != null && nullable ? null : nullable;
    this.required = required != null && required ? null : required;
    this.minOccurs = minOccurs == null || minOccurs == 0 ? null : minOccurs;
    this.maxOccurs = maxOccurs == null || maxOccurs == Integer.MAX_VALUE ? null : maxOccurs;
  }

  public Member(final Registry registry, final Boolean nullable, final Integer minOccurs, final Integer maxOccurs) {
    this(registry, null, nullable, null, minOccurs, maxOccurs);
  }

  public Member(final Registry registry, final String name, final $Boolean nullable, final $NonNegativeInteger minOccurs, final $MaxCardinality maxOccurs) {
    this(registry, name, nullable == null ? null : nullable.text(), null, minOccurs.isDefault() ? null : minOccurs.text().intValue(), parseMaxCardinality(minOccurs.text().intValue(), maxOccurs));
  }

  public Member(final Registry registry, final $JavaIdentifier name, final $Boolean nullable, final $Boolean required) {
    this(registry, name.text(), nullable.text(), required.text(), null, null);
  }

  public final String name() {
    return this.name;
  }

  public final String instanceName() {
    return JavaIdentifiers.toInstanceCase(name);
  }

  public final Boolean nullable() {
    return nullable;
  }

  public final Boolean required() {
    return required;
  }

  public final Integer minOccurs() {
    return minOccurs;
  }

  public final Integer maxOccurs() {
    return maxOccurs;
  }

  @Override
  protected Map<String,String> toAnnotationAttributes(final Element owner, final String packageName) {
    final Map<String,String> attributes = super.toAnnotationAttributes(owner, packageName);
    if (name != null)
      attributes.put("name", name);
//    else if (!(this instanceof Template) && !(this instanceof ObjectModel))
    else if (owner instanceof Schema && !(this instanceof ObjectModel))
      attributes.put("template", id().toString());

    final boolean shouldWriteNullable = !(owner instanceof Schema);
    if (shouldWriteNullable && nullable != null && !nullable)
      attributes.put("nullable", String.valueOf(nullable));

    if (required != null && !required)
      attributes.put("required", String.valueOf(required));

    if (minOccurs != null && minOccurs != 0)
      attributes.put("minOccurs", String.valueOf(minOccurs));

    if (maxOccurs != null)
      attributes.put("maxOccurs", String.valueOf(maxOccurs));

    return attributes;
  }

  protected void toAnnotationAttributes(final AttributeMap attributes) {
    if (nullable != null)
      attributes.put("nullable", nullable);

    if (required != null)
      attributes.put("required", required);

    if (minOccurs != null)
      attributes.put("minOccurs", minOccurs);

    if (maxOccurs != null)
      attributes.put("maxOccurs", maxOccurs);
  }

  protected final String toField() {
    final StringBuilder builder = new StringBuilder();
    final List<AnnotationSpec> elementAnnotations = toElementAnnotations();
    if (elementAnnotations != null && elementAnnotations.size() > 0)
      builder.append(Collections.toString(elementAnnotations, '\n')).append('\n');

    final AttributeMap attributes = new AttributeMap();
    toAnnotationAttributes(attributes);
    builder.append(new AnnotationSpec(propertyAnnotation(), attributes));
    builder.append("\npublic ").append(type().toCanonicalString()).append(' ').append(name).append(';');
    return builder.toString();
  }

  protected List<AnnotationSpec> toElementAnnotations() {
    return null;
  }

  public abstract Id id();
  protected abstract Registry.Type type();
  protected abstract Class<? extends Annotation> propertyAnnotation();
  protected abstract Class<? extends Annotation> elementAnnotation();
}