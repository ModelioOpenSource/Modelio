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

package org.modelio.metamodel.uml.behavior.stateMachineModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * PredefinedEventType v0.0.9054
 * 
 * 
 * null
 */
@objid ("000d47b0-91e1-1f74-804b-001ec947cd2a")
public enum PredefinedEventType {
    ENTRYEVENT (0, "EntryEvent", "EntryEvent"),
    DOEVENT (1, "DoEvent", "DoEvent"),
    EXITEVENT (2, "ExitEvent", "ExitEvent");

public static final int ENTRYEVENT_VALUE = 0;
public static final int DOEVENT_VALUE = 1;
public static final int EXITEVENT_VALUE = 2;
private static final PredefinedEventType[] VALUES_ARRAY =
new PredefinedEventType[] {
ENTRYEVENT,
DOEVENT,
EXITEVENT,
};
public static final List<PredefinedEventType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static PredefinedEventType get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    PredefinedEventType result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static PredefinedEventType getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
PredefinedEventType result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static PredefinedEventType get(int value) {
    switch (value) {
        case ENTRYEVENT_VALUE: return ENTRYEVENT;
        case DOEVENT_VALUE: return DOEVENT;
        case EXITEVENT_VALUE: return EXITEVENT;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private PredefinedEventType(int value, String name, String literal) {
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
