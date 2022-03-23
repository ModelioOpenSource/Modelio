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
package org.modelio.metamodel.uml.behavior.commonBehaviors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ParameterEffectKind v0.0.9054
 * 
 * 
 * Describe the usage made of an operation parameter
 */
@objid ("0086ba28-91e0-1f74-804b-001ec947cd2a")
public enum ParameterEffectKind {
    @objid ("e0263467-ebd1-49ca-aa7f-d7feb18e2374")
    CREATEEFFECT(0, "CreateEffect", "CreateEffect"),
    @objid ("0266b738-e52e-4eaa-b3f0-fb5b7bee32dd")
    READEFFECT(1, "ReadEffect", "ReadEffect"),
    @objid ("320bec4c-eac2-4b1a-9bef-73151c562176")
    UPDATEEFFECT(2, "UpdateEffect", "UpdateEffect"),
    @objid ("d6757f2c-3b60-40f3-9888-24cfda94b1ac")
    DELETEEFFECT(3, "DeleteEffect", "DeleteEffect");
public static final int CREATEEFFECT_VALUE = 0;
    public static final int READEFFECT_VALUE = 1;
    public static final int UPDATEEFFECT_VALUE = 2;
    public static final int DELETEEFFECT_VALUE = 3;
    private static final ParameterEffectKind[] VALUES_ARRAY =
    new ParameterEffectKind[] {
    CREATEEFFECT,
    READEFFECT,
    UPDATEEFFECT,
    DELETEEFFECT,
    };
    public static final List<ParameterEffectKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static ParameterEffectKind get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        ParameterEffectKind result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static ParameterEffectKind getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    ParameterEffectKind result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static ParameterEffectKind get(int value) {
        switch (value) {
            case CREATEEFFECT_VALUE: return CREATEEFFECT;
            case READEFFECT_VALUE: return READEFFECT;
            case UPDATEEFFECT_VALUE: return UPDATEEFFECT;
            case DELETEEFFECT_VALUE: return DELETEEFFECT;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private ParameterEffectKind(int value, String name, String literal) {
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
