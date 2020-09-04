/* 
 * Copyright 2013-2019 Modeliosoft
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
 * AggregationKind v0.0.9054
 * 
 * 
 * null
 */
@objid ("005a03ac-91e0-1f74-804b-001ec947cd2a")
public enum AggregationKind {
    KINDISASSOCIATION (0, "KindIsAssociation", "KindIsAssociation"),
    KINDISAGGREGATION (1, "KindIsAggregation", "KindIsAggregation"),
    KINDISCOMPOSITION (2, "KindIsComposition", "KindIsComposition");

public static final int KINDISASSOCIATION_VALUE = 0;
public static final int KINDISAGGREGATION_VALUE = 1;
public static final int KINDISCOMPOSITION_VALUE = 2;
private static final AggregationKind[] VALUES_ARRAY =
new AggregationKind[] {
KINDISASSOCIATION,
KINDISAGGREGATION,
KINDISCOMPOSITION,
};
public static final List<AggregationKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static AggregationKind get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    AggregationKind result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static AggregationKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
AggregationKind result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static AggregationKind get(int value) {
    switch (value) {
        case KINDISASSOCIATION_VALUE: return KINDISASSOCIATION;
        case KINDISAGGREGATION_VALUE: return KINDISAGGREGATION;
        case KINDISCOMPOSITION_VALUE: return KINDISCOMPOSITION;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private AggregationKind(int value, String name, String literal) {
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
