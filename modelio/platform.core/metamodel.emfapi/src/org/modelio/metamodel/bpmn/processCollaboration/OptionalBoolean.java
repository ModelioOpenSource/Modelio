/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.metamodel.bpmn.processCollaboration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * OptionalBoolean v0.0.9054
 * 
 * 
 * null
 */
@objid ("003a2bfe-91e1-1f74-804b-001ec947cd2a")
public enum OptionalBoolean {
    OFALSE (0, "OFalse", "OFalse"),
    OTRUE (1, "OTrue", "OTrue"),
    OUNDEFINED (2, "OUndefined", "OUndefined");

public static final int OFALSE_VALUE = 0;
public static final int OTRUE_VALUE = 1;
public static final int OUNDEFINED_VALUE = 2;
private static final OptionalBoolean[] VALUES_ARRAY =
new OptionalBoolean[] {
OFALSE,
OTRUE,
OUNDEFINED,
};
public static final List<OptionalBoolean> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static OptionalBoolean get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    OptionalBoolean result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static OptionalBoolean getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
OptionalBoolean result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static OptionalBoolean get(int value) {
    switch (value) {
        case OFALSE_VALUE: return OFALSE;
        case OTRUE_VALUE: return OTRUE;
        case OUNDEFINED_VALUE: return OUNDEFINED;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private OptionalBoolean(int value, String name, String literal) {
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
