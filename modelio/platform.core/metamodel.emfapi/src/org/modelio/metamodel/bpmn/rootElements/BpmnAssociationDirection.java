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

package org.modelio.metamodel.bpmn.rootElements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnAssociationDirection v0.0.9054
 * 
 * 
 * Defines whether or not the Association shows any directionality with an arrowhead. 
 * The default is None (no arrowhead). 
 * A value of One means that the arrowhead SHALL be at the Target Object. 
 * A value of Both means that there SHALL be an arrowhead at both ends of the Association line.
 */
@objid ("00409336-91e1-1f74-804b-001ec947cd2a")
public enum BpmnAssociationDirection {
    NONEDIRECTION (0, "NoneDirection", "NoneDirection"),
    ONEDIRECTION (1, "OneDirection", "OneDirection"),
    BOTHDIRECTION (2, "BothDirection", "BothDirection");

public static final int NONEDIRECTION_VALUE = 0;
public static final int ONEDIRECTION_VALUE = 1;
public static final int BOTHDIRECTION_VALUE = 2;
private static final BpmnAssociationDirection[] VALUES_ARRAY =
new BpmnAssociationDirection[] {
NONEDIRECTION,
ONEDIRECTION,
BOTHDIRECTION,
};
public static final List<BpmnAssociationDirection> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static BpmnAssociationDirection get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    BpmnAssociationDirection result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static BpmnAssociationDirection getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
BpmnAssociationDirection result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static BpmnAssociationDirection get(int value) {
    switch (value) {
        case NONEDIRECTION_VALUE: return NONEDIRECTION;
        case ONEDIRECTION_VALUE: return ONEDIRECTION;
        case BOTHDIRECTION_VALUE: return BOTHDIRECTION;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private BpmnAssociationDirection(int value, String name, String literal) {
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
