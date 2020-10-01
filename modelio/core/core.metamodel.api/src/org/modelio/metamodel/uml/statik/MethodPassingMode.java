/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.metamodel.uml.statik;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * MethodPassingMode v0.0.9054
 * 
 * 
 * null
 */
@objid ("005a4e98-91e0-1f74-804b-001ec947cd2a")
public enum MethodPassingMode {
    METHODIN (0, "MethodIn", "MethodIn"),
    METHODOUT (1, "MethodOut", "MethodOut");

public static final int METHODIN_VALUE = 0;
public static final int METHODOUT_VALUE = 1;
private static final MethodPassingMode[] VALUES_ARRAY =
new MethodPassingMode[] {
METHODIN,
METHODOUT,
};
public static final List<MethodPassingMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static MethodPassingMode get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    MethodPassingMode result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static MethodPassingMode getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
MethodPassingMode result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static MethodPassingMode get(int value) {
    switch (value) {
        case METHODIN_VALUE: return METHODIN;
        case METHODOUT_VALUE: return METHODOUT;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private MethodPassingMode(int value, String name, String literal) {
  this.value = value;
  this.name = name;
  this.literal = literal;
}
public int getValue() {
   return value;
}
 public String getName() {
    return name;
}
public String getLiteral() {
  return literal;
}
 @Override
public String toString() {
   return literal;
}
}
