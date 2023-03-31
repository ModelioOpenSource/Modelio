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
 * KindOfStateMachine v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("000d2dc0-91e1-1f74-804b-001ec947cd2a")
public enum KindOfStateMachine {
    @objid ("6d67c725-a88c-4909-9ae6-d6c0ec1b5706")
    DYNAMIC(0, "Dynamic", "Dynamic"),
    @objid ("ba0adeb8-25a2-4228-87d8-a5f2573e4f3b")
    PROTOCOL(1, "Protocol", "Protocol");

public static final int DYNAMIC_VALUE = 0;
    public static final int PROTOCOL_VALUE = 1;
    private static final KindOfStateMachine[] VALUES_ARRAY =
    new KindOfStateMachine[] {
    DYNAMIC,
    PROTOCOL,
    };
    public static final List<KindOfStateMachine> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static KindOfStateMachine get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        KindOfStateMachine result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static KindOfStateMachine getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    KindOfStateMachine result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static KindOfStateMachine get(int value) {
        switch (value) {
            case DYNAMIC_VALUE: return DYNAMIC;
            case PROTOCOL_VALUE: return PROTOCOL;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private KindOfStateMachine(int value, String name, String literal) {
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
