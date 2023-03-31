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
 * KindOfControl v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("000d140c-91e1-1f74-804b-001ec947cd2a")
public enum KindOfControl {
    @objid ("7d052898-192c-420c-aa4f-ae275e93513d")
    IF(0, "If", "If"),
    @objid ("43c546f5-6382-45f9-852e-cb5fa74f618c")
    CASE(1, "Case", "Case");

public static final int IF_VALUE = 0;
    public static final int CASE_VALUE = 1;
    private static final KindOfControl[] VALUES_ARRAY =
    new KindOfControl[] {
    IF,
    CASE,
    };
    public static final List<KindOfControl> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static KindOfControl get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        KindOfControl result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static KindOfControl getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    KindOfControl result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static KindOfControl get(int value) {
        switch (value) {
            case IF_VALUE: return IF;
            case CASE_VALUE: return CASE;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private KindOfControl(int value, String name, String literal) {
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
