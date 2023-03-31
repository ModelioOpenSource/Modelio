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
package org.modelio.metamodel.uml.statik;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * PortOrientation v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("00741616-7948-1fe9-b4b9-001ec947cd2a")
public enum PortOrientation {
    @objid ("732d6458-c869-4f93-bb43-c8f8ea3f4a00")
    NONE(0, "None", "None"),
    @objid ("e01420e6-01cc-472a-9c00-0f602e8aa176")
    IN(1, "In", "In"),
    @objid ("fbe6796d-e07b-436c-9455-aa02ec56a0af")
    OUT(2, "Out", "Out"),
    @objid ("199b3424-6342-41aa-90d1-cfd4dcb4bc9a")
    INOUT(3, "Inout", "Inout");

public static final int NONE_VALUE = 0;
    public static final int IN_VALUE = 1;
    public static final int OUT_VALUE = 2;
    public static final int INOUT_VALUE = 3;
    private static final PortOrientation[] VALUES_ARRAY =
    new PortOrientation[] {
    NONE,
    IN,
    OUT,
    INOUT,
    };
    public static final List<PortOrientation> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static PortOrientation get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        PortOrientation result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static PortOrientation getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    PortOrientation result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static PortOrientation get(int value) {
        switch (value) {
            case NONE_VALUE: return NONE;
            case IN_VALUE: return IN;
            case OUT_VALUE: return OUT;
            case INOUT_VALUE: return INOUT;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private PortOrientation(int value, String name, String literal) {
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
