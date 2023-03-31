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
 * RelativeTime v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("0096910a-91e0-1f74-804b-001ec947cd2a")
public enum RelativeTime {
    @objid ("e64ebe15-c008-4636-98a8-1ed36c7c08cb")
    BEFORE(0, "Before", "Before"),
    @objid ("9786fe83-a52c-47b7-b5a9-833a72b6bec1")
    DURING(1, "During", "During"),
    @objid ("74c3e969-eaef-4ce5-a65b-c737ca77b009")
    AFTER(2, "After", "After"),
    @objid ("7c115d99-fb46-4d44-822f-b89c4b7856e2")
    ON(3, "On", "On");

public static final int BEFORE_VALUE = 0;
    public static final int DURING_VALUE = 1;
    public static final int AFTER_VALUE = 2;
    public static final int ON_VALUE = 3;
    private static final RelativeTime[] VALUES_ARRAY =
    new RelativeTime[] {
    BEFORE,
    DURING,
    AFTER,
    ON,
    };
    public static final List<RelativeTime> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static RelativeTime get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        RelativeTime result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static RelativeTime getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    RelativeTime result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static RelativeTime get(int value) {
        switch (value) {
            case BEFORE_VALUE: return BEFORE;
            case DURING_VALUE: return DURING;
            case AFTER_VALUE: return AFTER;
            case ON_VALUE: return ON;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private RelativeTime(int value, String name, String literal) {
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
