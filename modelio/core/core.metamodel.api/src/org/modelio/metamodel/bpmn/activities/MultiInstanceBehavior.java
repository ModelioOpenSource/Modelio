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
 * MultiInstanceBehavior v0.0.9054
 * 
 * 
 * Acts as a shortcut for specifying when events SHALL be thrown from an Activity instance that is about to complete. It can assume values of None, One, All, and Complex, resulting in the following behavior:
 * - None: the EventDefinition which is associated through the noneEvent association will be thrown for each instance completing;
 * - One: the EventDefinition referenced through the oneEvent association will be thrown upon the first instance completing;
 * - All: no Event is ever thrown; a token is produced after completion of all instances
 * - Complex: the complexBehaviorDefinitions are consulted to determine if and which Events to throw.
 * 
 * For the behaviors of none and one, a default SignalEventDefinition will be thrown which automatically carries the current runtime attributes of the MI Activity.
 */
@objid ("00556e64-91e1-1f74-804b-001ec947cd2a")
public enum MultiInstanceBehavior {
    @objid ("454315bc-9f09-4898-b7aa-a5b575d0fbed")
    NONEBEHAVIOR(0, "NoneBehavior", "NoneBehavior"),
    @objid ("19fb862b-968a-4736-b9b1-dff9d7cfb0f5")
    ONEBEHAVIOR(1, "OneBehavior", "OneBehavior"),
    @objid ("9f0801b4-e7e0-44fb-a7c6-dced01ea17b1")
    ALLBEHAVIOR(2, "AllBehavior", "AllBehavior"),
    @objid ("548defe1-732f-44b8-aef0-c261e5b6e50a")
    COMPLEXBEHAVIOR(3, "ComplexBehavior", "ComplexBehavior");
public static final int NONEBEHAVIOR_VALUE = 0;
    public static final int ONEBEHAVIOR_VALUE = 1;
    public static final int ALLBEHAVIOR_VALUE = 2;
    public static final int COMPLEXBEHAVIOR_VALUE = 3;
    private static final MultiInstanceBehavior[] VALUES_ARRAY =
    new MultiInstanceBehavior[] {
    NONEBEHAVIOR,
    ONEBEHAVIOR,
    ALLBEHAVIOR,
    COMPLEXBEHAVIOR,
    };
    public static final List<MultiInstanceBehavior> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static MultiInstanceBehavior get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        MultiInstanceBehavior result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static MultiInstanceBehavior getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    MultiInstanceBehavior result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static MultiInstanceBehavior get(int value) {
        switch (value) {
            case NONEBEHAVIOR_VALUE: return NONEBEHAVIOR;
            case ONEBEHAVIOR_VALUE: return ONEBEHAVIOR;
            case ALLBEHAVIOR_VALUE: return ALLBEHAVIOR;
            case COMPLEXBEHAVIOR_VALUE: return COMPLEXBEHAVIOR;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private MultiInstanceBehavior(int value, String name, String literal) {
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
