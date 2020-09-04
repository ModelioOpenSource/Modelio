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

package org.modelio.metamodel.uml.behavior.interactionModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * InteractionOperator v0.0.9054
 * 
 * 
 * null
 */
@objid ("00964254-91e0-1f74-804b-001ec947cd2a")
public enum InteractionOperator {
    SEQOP (0, "SeqOp", "SeqOp"),
    ALTOP (1, "AltOp", "AltOp"),
    OPTOP (2, "OptOp", "OptOp"),
    BREAKOP (3, "BreakOp", "BreakOp"),
    PAROP (4, "ParOp", "ParOp"),
    STRICTOP (5, "StrictOp", "StrictOp"),
    LOOPOP (6, "LoopOp", "LoopOp"),
    CRITICALOP (7, "CriticalOp", "CriticalOp"),
    NEGOP (8, "NegOp", "NegOp"),
    ASSERTOP (9, "AssertOp", "AssertOp"),
    IGNOREOP (10, "IgnoreOp", "IgnoreOp"),
    CONSIDEROP (11, "ConsiderOp", "ConsiderOp");

public static final int SEQOP_VALUE = 0;
public static final int ALTOP_VALUE = 1;
public static final int OPTOP_VALUE = 2;
public static final int BREAKOP_VALUE = 3;
public static final int PAROP_VALUE = 4;
public static final int STRICTOP_VALUE = 5;
public static final int LOOPOP_VALUE = 6;
public static final int CRITICALOP_VALUE = 7;
public static final int NEGOP_VALUE = 8;
public static final int ASSERTOP_VALUE = 9;
public static final int IGNOREOP_VALUE = 10;
public static final int CONSIDEROP_VALUE = 11;
private static final InteractionOperator[] VALUES_ARRAY =
new InteractionOperator[] {
SEQOP,
ALTOP,
OPTOP,
BREAKOP,
PAROP,
STRICTOP,
LOOPOP,
CRITICALOP,
NEGOP,
ASSERTOP,
IGNOREOP,
CONSIDEROP,
};
public static final List<InteractionOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static InteractionOperator get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    InteractionOperator result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static InteractionOperator getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
InteractionOperator result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static InteractionOperator get(int value) {
    switch (value) {
        case SEQOP_VALUE: return SEQOP;
        case ALTOP_VALUE: return ALTOP;
        case OPTOP_VALUE: return OPTOP;
        case BREAKOP_VALUE: return BREAKOP;
        case PAROP_VALUE: return PAROP;
        case STRICTOP_VALUE: return STRICTOP;
        case LOOPOP_VALUE: return LOOPOP;
        case CRITICALOP_VALUE: return CRITICALOP;
        case NEGOP_VALUE: return NEGOP;
        case ASSERTOP_VALUE: return ASSERTOP;
        case IGNOREOP_VALUE: return IGNOREOP;
        case CONSIDEROP_VALUE: return CONSIDEROP;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private InteractionOperator(int value, String name, String literal) {
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
