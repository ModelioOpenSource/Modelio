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
 * Navigability v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("5dedaa90-debc-4e72-8fb8-7a45806b91b6")
public enum Navigability {
    @objid ("43784b23-9105-4b96-8d13-76ad783864e3")
    NONE(0, "None", "None"),
    @objid ("2c39a55f-8192-491a-837b-a68f33dc6f8f")
    THISSIDE(1, "ThisSide", "ThisSide"),
    @objid ("2768da10-fdbd-4ab1-bbfd-3b489a4c0150")
    OTHERSIDE(2, "OtherSide", "OtherSide"),
    @objid ("9b154c2c-1740-4e66-b5fe-251d85e40ef6")
    BOTHSIDES(3, "BothSides", "BothSides");

public static final int NONE_VALUE = 0;
    public static final int THISSIDE_VALUE = 1;
    public static final int OTHERSIDE_VALUE = 2;
    public static final int BOTHSIDES_VALUE = 3;
    private static final Navigability[] VALUES_ARRAY =
    new Navigability[] {
    NONE,
    THISSIDE,
    OTHERSIDE,
    BOTHSIDES,
    };
    public static final List<Navigability> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static Navigability get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        Navigability result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static Navigability getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    Navigability result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static Navigability get(int value) {
        switch (value) {
            case NONE_VALUE: return NONE;
            case THISSIDE_VALUE: return THISSIDE;
            case OTHERSIDE_VALUE: return OTHERSIDE;
            case BOTHSIDES_VALUE: return BOTHSIDES;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private Navigability(int value, String name, String literal) {
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
