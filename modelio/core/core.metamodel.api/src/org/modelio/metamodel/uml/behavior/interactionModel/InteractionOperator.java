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
    @objid ("7bae722f-beab-4260-92a3-8bb5e8027507")
    SEQOP(0, "SeqOp", "SeqOp"),
    @objid ("a259bb6c-66f3-4e8b-b95f-9674d27848d8")
    ALTOP(1, "AltOp", "AltOp"),
    @objid ("eed590b2-410f-4e4f-9a85-62feebcfcfa2")
    OPTOP(2, "OptOp", "OptOp"),
    @objid ("04cb1992-29ab-4c74-ad92-66843d230aa0")
    BREAKOP(3, "BreakOp", "BreakOp"),
    @objid ("ba86b08c-9c5b-4be1-b991-ea482cc81c79")
    PAROP(4, "ParOp", "ParOp"),
    @objid ("35fd29a4-ba53-4259-9664-fadb651668da")
    STRICTOP(5, "StrictOp", "StrictOp"),
    @objid ("ed25d124-b820-4b16-9735-61b2875681c7")
    LOOPOP(6, "LoopOp", "LoopOp"),
    @objid ("4866c05c-3da4-4171-9b4d-ba6abe3b3e4d")
    CRITICALOP(7, "CriticalOp", "CriticalOp"),
    @objid ("40bfd856-b202-459a-840b-f20ff6f2e726")
    NEGOP(8, "NegOp", "NegOp"),
    @objid ("133ef3ac-4333-439e-b350-0514b43d606d")
    ASSERTOP(9, "AssertOp", "AssertOp"),
    @objid ("3d7540c0-e601-4a3b-ac01-60c3bc263ce4")
    IGNOREOP(10, "IgnoreOp", "IgnoreOp"),
    @objid ("5ae9e203-7ac8-4217-bb54-66becdb5bf87")
    CONSIDEROP(11, "ConsiderOp", "ConsiderOp");
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
