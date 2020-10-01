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

package org.modelio.metamodel.bpmn.activities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * TransactionMethod v0.0.9054
 * 
 * 
 * null
 */
@objid ("00558606-91e1-1f74-804b-001ec947cd2a")
public enum TransactionMethod {
    COMPENSATETRANSACTION (0, "compensateTransaction", "compensateTransaction"),
    STORETRANSACTION (1, "storeTransaction", "storeTransaction"),
    IMAGETRANSACTION (2, "imageTransaction", "imageTransaction");

public static final int COMPENSATETRANSACTION_VALUE = 0;
public static final int STORETRANSACTION_VALUE = 1;
public static final int IMAGETRANSACTION_VALUE = 2;
private static final TransactionMethod[] VALUES_ARRAY =
new TransactionMethod[] {
COMPENSATETRANSACTION,
STORETRANSACTION,
IMAGETRANSACTION,
};
public static final List<TransactionMethod> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static TransactionMethod get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    TransactionMethod result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static TransactionMethod getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
TransactionMethod result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static TransactionMethod get(int value) {
    switch (value) {
        case COMPENSATETRANSACTION_VALUE: return COMPENSATETRANSACTION;
        case STORETRANSACTION_VALUE: return STORETRANSACTION;
        case IMAGETRANSACTION_VALUE: return IMAGETRANSACTION;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private TransactionMethod(int value, String name, String literal) {
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
