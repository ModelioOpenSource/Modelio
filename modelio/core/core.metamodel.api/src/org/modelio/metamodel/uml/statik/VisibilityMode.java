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
 * VisibilityMode v0.0.9054
 * 
 * 
 * null
 */
@objid ("005a96aa-91e0-1f74-804b-001ec947cd2a")
public enum VisibilityMode {
    PUBLIC (0, "Public", "Public"),
    PROTECTED (1, "Protected", "Protected"),
    PRIVATE (2, "Private", "Private"),
    VISIBILITYUNDEFINED (3, "VisibilityUndefined", "VisibilityUndefined"),
    PACKAGEVISIBILITY (4, "PackageVisibility", "PackageVisibility");

public static final int PUBLIC_VALUE = 0;
public static final int PROTECTED_VALUE = 1;
public static final int PRIVATE_VALUE = 2;
public static final int VISIBILITYUNDEFINED_VALUE = 3;
public static final int PACKAGEVISIBILITY_VALUE = 4;
private static final VisibilityMode[] VALUES_ARRAY =
new VisibilityMode[] {
PUBLIC,
PROTECTED,
PRIVATE,
VISIBILITYUNDEFINED,
PACKAGEVISIBILITY,
};
public static final List<VisibilityMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static VisibilityMode get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    VisibilityMode result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static VisibilityMode getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
VisibilityMode result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static VisibilityMode get(int value) {
    switch (value) {
        case PUBLIC_VALUE: return PUBLIC;
        case PROTECTED_VALUE: return PROTECTED;
        case PRIVATE_VALUE: return PRIVATE;
        case VISIBILITYUNDEFINED_VALUE: return VISIBILITYUNDEFINED;
        case PACKAGEVISIBILITY_VALUE: return PACKAGEVISIBILITY;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private VisibilityMode(int value, String name, String literal) {
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
