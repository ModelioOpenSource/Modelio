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

package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ExpansionKind v0.0.9054
 * 
 * 
 * null
 */
@objid ("007f0dd2-91e0-1f74-804b-001ec947cd2a")
public enum ExpansionKind {
    PARALLEL (0, "Parallel", "Parallel"),
    ITERATIVE (1, "Iterative", "Iterative"),
    STREAM (2, "Stream", "Stream");

public static final int PARALLEL_VALUE = 0;
public static final int ITERATIVE_VALUE = 1;
public static final int STREAM_VALUE = 2;
private static final ExpansionKind[] VALUES_ARRAY =
new ExpansionKind[] {
PARALLEL,
ITERATIVE,
STREAM,
};
public static final List<ExpansionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static ExpansionKind get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    ExpansionKind result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static ExpansionKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
ExpansionKind result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static ExpansionKind get(int value) {
    switch (value) {
        case PARALLEL_VALUE: return PARALLEL;
        case ITERATIVE_VALUE: return ITERATIVE;
        case STREAM_VALUE: return STREAM;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private ExpansionKind(int value, String name, String literal) {
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
