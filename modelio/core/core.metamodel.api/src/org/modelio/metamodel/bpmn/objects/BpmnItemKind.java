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
package org.modelio.metamodel.bpmn.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnItemKind v0.0.9054
 * 
 * 
 * Items can be either physical items, such as the mechanical part of a vehicle, or information items such the catalog of the mechanical parts of a vehicle.
 * 
 * 
 */
@objid ("006f680a-91e1-1f74-804b-001ec947cd2a")
public enum BpmnItemKind {
    @objid ("7a32dd9f-f75c-4dec-a32a-ef59b6a81062")
    PHYSICAL(0, "physical", "physical"),
    @objid ("79f2a457-0702-45eb-bce7-5f1dc6b01bd6")
    INFORMATION(1, "information", "information");

public static final int PHYSICAL_VALUE = 0;
    public static final int INFORMATION_VALUE = 1;
    private static final BpmnItemKind[] VALUES_ARRAY =
    new BpmnItemKind[] {
    PHYSICAL,
    INFORMATION,
    };
    public static final List<BpmnItemKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static BpmnItemKind get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        BpmnItemKind result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static BpmnItemKind getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    BpmnItemKind result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static BpmnItemKind get(int value) {
        switch (value) {
            case PHYSICAL_VALUE: return PHYSICAL;
            case INFORMATION_VALUE: return INFORMATION;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private BpmnItemKind(int value, String name, String literal) {
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
