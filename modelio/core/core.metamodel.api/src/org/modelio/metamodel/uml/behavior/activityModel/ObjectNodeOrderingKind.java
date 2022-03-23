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
package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ObjectNodeOrderingKind v0.0.9054
 * 
 * 
 * null
 */
@objid ("007f536e-91e0-1f74-804b-001ec947cd2a")
public enum ObjectNodeOrderingKind {
    @objid ("d91895c8-5ad7-4a47-8710-36a77aed443a")
    UNORDERED(0, "Unordered", "Unordered"),
    @objid ("3f8cb8e9-af65-4a6a-a61a-c012271b3fc2")
    ORDERED(1, "Ordered", "Ordered"),
    @objid ("0c082a10-96e2-414f-9683-2dc43f5fbd79")
    LIFO(2, "LIFO", "LIFO"),
    @objid ("e66bd06e-9f7b-4168-aa01-f6f5939c6bbb")
    FIFO(3, "FIFO", "FIFO");
public static final int UNORDERED_VALUE = 0;
    public static final int ORDERED_VALUE = 1;
    public static final int LIFO_VALUE = 2;
    public static final int FIFO_VALUE = 3;
    private static final ObjectNodeOrderingKind[] VALUES_ARRAY =
    new ObjectNodeOrderingKind[] {
    UNORDERED,
    ORDERED,
    LIFO,
    FIFO,
    };
    public static final List<ObjectNodeOrderingKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static ObjectNodeOrderingKind get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        ObjectNodeOrderingKind result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static ObjectNodeOrderingKind getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    ObjectNodeOrderingKind result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static ObjectNodeOrderingKind get(int value) {
        switch (value) {
            case UNORDERED_VALUE: return UNORDERED;
            case ORDERED_VALUE: return ORDERED;
            case LIFO_VALUE: return LIFO;
            case FIFO_VALUE: return FIFO;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private ObjectNodeOrderingKind(int value, String name, String literal) {
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
