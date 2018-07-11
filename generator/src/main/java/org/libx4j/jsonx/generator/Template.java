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

import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$Array;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$MaxCardinality;
import org.libx4j.jsonx.jsonx_0_9_8.xL2gluGCXYYJc.$Template;
import org.w3.www._2001.XMLSchema.yAA.$Boolean;
import org.w3.www._2001.XMLSchema.yAA.$NonNegativeInteger;

class Template extends Element {
  private final Element model;
  private final Id id;

  public Template(final $Array.Template binding, final Element model) {
    super(null, binding.getNullable$(), binding.getMinOccurs$(), binding.getMaxOccurs$());
    this.model = model;
    this.id = new Id(this);
  }

  public Template(final $Template binding, final Element model) {
    super(binding.getName$().text(), binding.getNullable$().text(), binding.getRequired$().text());
    this.model = model;
    this.id = new Id(this);
  }

  public Template(final String name, final boolean nullable, final boolean required, final Model model) {
    super(name, nullable, required);
    this.model = model;
    this.id = new Id(this);
  }

  public Template(final boolean nullable, final Integer minOccurs, final Integer maxOccurs, final Model model) {
    super(null, nullable, minOccurs, maxOccurs);
    this.model = model;
    this.id = new Id(this);
  }

  public Template(final $Boolean nullable, final $NonNegativeInteger minOccurs, final $MaxCardinality maxOccurs, final Model model) {
    super(null, nullable, minOccurs, maxOccurs);
    this.model = model;
    this.id = new Id(this);
  }

  @Override
  public final Id id() {
    return id;
  }

  public final Element reference() {
    return this.model;
  }

  @Override
  protected final Type type() {
    return model.type();
  }

  @Override
  protected final Class<? extends Annotation> propertyAnnotation() {
    return model.propertyAnnotation();
  }

  @Override
  protected final Class<? extends Annotation> elementAnnotation() {
    return model.elementAnnotation();
  }

  @Override
  protected final String toJSON(final String packageName) {
    final StringBuilder builder = new StringBuilder(super.toJSON(packageName));
    if (builder.length() > 0)
      builder.insert(0, ",\n");

    if (model != null)
      builder.append(",\n  reference: \"").append(Type.getSubName(model.id().toString(), packageName)).append('"');

    return "{\n" + (builder.length() > 0 ? builder.substring(2) : builder.toString()) + "\n}";
  }

  @Override
  protected final String toJSONX(final Registry registry, final Member owner, final String packageName) {
    if ("o24370fee".equals(String.valueOf(model.id())))
      System.out.println();
    if (model instanceof ObjectModel && registry.getNumReferrers(model) == 1) {
      final String templateJsonx = super.toJSONX(registry, owner, packageName);
      String objectJsonx = model.toJSONX(registry, owner, packageName);
      final int n = model.nullable() != null ? templateJsonx.indexOf("nullable") : -1;
      if (n != -1) {
        final String nullable = templateJsonx.substring(n, templateJsonx.indexOf('"', n + 10) + 1);
        if (objectJsonx.contains(nullable))
          objectJsonx = objectJsonx.replace(nullable, "");
        else
          throw new IllegalStateException("Attempted to overwrite nullable");
      }

      final int index = objectJsonx.indexOf(' ');
      return objectJsonx.substring(0, index) + templateJsonx + objectJsonx.substring(index);
    }

    final StringBuilder builder = new StringBuilder(owner instanceof ObjectModel ? "<property xsi:type=\"template\"" : "<template");
    if (model != null)
      builder.append(" reference=\"").append(Type.getSubName(model.id().toString(), packageName)).append('"');
    if (builder.toString().contains("sub1.simple$Booleans") && owner instanceof Element && "a43cdfd71".equals(String.valueOf(((Element)owner).id()))) {
      System.out.println();
    }

    return builder.append(super.toJSONX(registry, owner, packageName)).append("/>").toString();
  }

  @Override
  protected final void toAnnotation(final Attributes attributes, final String packageName) {
    super.toAnnotation(attributes, packageName);
    model.toAnnotation(attributes, packageName);
  }

  @Override
  protected String toElementAnnotations(final String packageName) {
    return model.toElementAnnotations(packageName);
  }
}