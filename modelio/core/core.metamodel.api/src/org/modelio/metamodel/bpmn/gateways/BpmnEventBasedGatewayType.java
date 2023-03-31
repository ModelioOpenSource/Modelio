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
package org.modelio.metamodel.bpmn.gateways;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnEventBasedGatewayType v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("00653808-91e1-1f74-804b-001ec947cd2a")
public enum BpmnEventBasedGatewayType {
    @objid ("1792d9df-bd4f-49f4-91fb-1fcd5c520487")
    PARALLELGATEWAY(0, "ParallelGateway", "ParallelGateway"),
    @objid ("6f8c7b29-162c-4227-a71f-7072cab5fd08")
    EXCLUSIVEGATEWAY(1, "ExclusiveGateway", "ExclusiveGateway");

public static final int PARALLELGATEWAY_VALUE = 0;
    public static final int EXCLUSIVEGATEWAY_VALUE = 1;
    private static final BpmnEventBasedGatewayType[] VALUES_ARRAY =
    new BpmnEventBasedGatewayType[] {
    PARALLELGATEWAY,
    EXCLUSIVEGATEWAY,
    };
    public static final List<BpmnEventBasedGatewayType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static BpmnEventBasedGatewayType get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        BpmnEventBasedGatewayType result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static BpmnEventBasedGatewayType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    BpmnEventBasedGatewayType result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static BpmnEventBasedGatewayType get(int value) {
        switch (value) {
            case PARALLELGATEWAY_VALUE: return PARALLELGATEWAY;
            case EXCLUSIVEGATEWAY_VALUE: return EXCLUSIVEGATEWAY;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private BpmnEventBasedGatewayType(int value, String name, String literal) {
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
