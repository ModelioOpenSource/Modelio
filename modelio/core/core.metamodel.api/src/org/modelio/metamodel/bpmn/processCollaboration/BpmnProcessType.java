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

package org.modelio.metamodel.bpmn.processCollaboration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnProcessType v0.0.9054
 * 
 * 
 * null
 */
@objid ("003a17f4-91e1-1f74-804b-001ec947cd2a")
public enum BpmnProcessType {
    NONEPROCESS (0, "NoneProcess", "NoneProcess"),
    PUBLICPROCESS (1, "PublicProcess", "PublicProcess"),
    PRIVATEPROCESS (2, "PrivateProcess", "PrivateProcess");

public static final int NONEPROCESS_VALUE = 0;
public static final int PUBLICPROCESS_VALUE = 1;
public static final int PRIVATEPROCESS_VALUE = 2;
private static final BpmnProcessType[] VALUES_ARRAY =
new BpmnProcessType[] {
NONEPROCESS,
PUBLICPROCESS,
PRIVATEPROCESS,
};
public static final List<BpmnProcessType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static BpmnProcessType get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    BpmnProcessType result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static BpmnProcessType getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
BpmnProcessType result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static BpmnProcessType get(int value) {
    switch (value) {
        case NONEPROCESS_VALUE: return NONEPROCESS;
        case PUBLICPROCESS_VALUE: return PUBLICPROCESS;
        case PRIVATEPROCESS_VALUE: return PRIVATEPROCESS;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private BpmnProcessType(int value, String name, String literal) {
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
