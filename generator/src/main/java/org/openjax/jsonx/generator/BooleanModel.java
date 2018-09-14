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

package org.openjax.jsonx.generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.fastjax.lang.IllegalAnnotationException;
import org.openjax.jsonx.jsonx_0_9_8.xL3gluGCXYYJc.$Array;
import org.openjax.jsonx.jsonx_0_9_8.xL3gluGCXYYJc.$Boolean;
import org.openjax.jsonx.jsonx_0_9_8.xL3gluGCXYYJc.Jsonx;
import org.openjax.jsonx.runtime.BooleanElement;
import org.openjax.jsonx.runtime.BooleanProperty;
import org.openjax.jsonx.runtime.JsonxUtil;
import org.openjax.jsonx.runtime.Use;

final class BooleanModel extends Model {
  public static BooleanModel declare(final Registry registry, final Jsonx.Boolean binding) {
    return registry.declare(binding).value(new BooleanModel(registry, binding), null);
  }

  public static Member referenceOrDeclare(final Registry registry, final Referrer<?> referrer, final BooleanProperty property, final Field field) {
    final BooleanModel model = new BooleanModel(registry, property, field);
    final Id id = model.id();

    final BooleanModel registered = (BooleanModel)registry.getModel(id);
    return new Reference(registry, JsonxUtil.getName(property.name(), field), property.use(), registered == null ? registry.declare(id).value(model, referrer) : registry.reference(registered, referrer));
  }

  public static Member referenceOrDeclare(final Registry registry, final Referrer<?> referrer, final BooleanElement element) {
    final BooleanModel model = new BooleanModel(registry, element);
    final Id id = model.id();

    final BooleanModel registered = (BooleanModel)registry.getModel(id);
    return new Reference(registry, element.nullable(), element.minOccurs(), element.maxOccurs(), registered == null ? registry.declare(id).value(model, referrer) : registry.reference(registered, referrer));
  }

  public static BooleanModel reference(final Registry registry, final Referrer<?> referrer, final $Array.Boolean binding) {
    return registry.reference(new BooleanModel(registry, binding), referrer);
  }

  public static BooleanModel reference(final Registry registry, final Referrer<?> referrer, final $Boolean binding) {
    return registry.reference(new BooleanModel(registry, binding), referrer);
  }

  private final Id id;

  private BooleanModel(final Registry registry, final Jsonx.Boolean binding) {
    super(registry);
    this.id = new Id(binding.getTemplate$());
  }

  private BooleanModel(final Registry registry, final $Boolean binding) {
    super(registry, binding.getName$(), binding.getUse$());
    this.id = new Id(this);
  }

  private BooleanModel(final Registry registry, final $Array.Boolean binding) {
    super(registry, binding.getNullable$(), binding.getMinOccurs$(), binding.getMaxOccurs$());
    this.id = new Id(this);
  }

  private BooleanModel(final Registry registry, final BooleanProperty property, final Field field) {
    super(registry, null, property.use());
    if (field.getType() != Boolean.class && (field.getType() != boolean.class || property.use() == Use.OPTIONAL))
      throw new IllegalAnnotationException(property, field.getDeclaringClass().getName() + "." + field.getName() + ": @" + BooleanProperty.class.getSimpleName() + " can only be applied to fields of Boolean type or non-nullable boolean type.");

    this.id = new Id(this);
  }

  private BooleanModel(final Registry registry, final BooleanElement element) {
    super(registry, element.nullable(), null);
    this.id = new Id(this);
  }

  @Override
  protected Id id() {
    return id;
  }

  @Override
  protected Registry.Type type() {
    return registry.getType(Boolean.class);
  }

  @Override
  protected String elementName() {
    return "boolean";
  }

  @Override
  protected Class<? extends Annotation> propertyAnnotation() {
    return BooleanProperty.class;
  }

  @Override
  protected Class<? extends Annotation> elementAnnotation() {
    return BooleanElement.class;
  }
}