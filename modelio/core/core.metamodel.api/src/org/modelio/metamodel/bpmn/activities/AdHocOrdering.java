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
 * AdHocOrdering v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("005556d6-91e1-1f74-804b-001ec947cd2a")
public enum AdHocOrdering {
    @objid ("57f25867-5eb2-4314-af9b-710851dc6ba5")
    PARALLELORDERING(0, "ParallelOrdering", "ParallelOrdering"),
    @objid ("d2c651f8-b768-45d9-bef8-7aa5bedc1aee")
    SEQUENTIALORDERING(1, "SequentialOrdering", "SequentialOrdering");

public static final int PARALLELORDERING_VALUE = 0;
    public static final int SEQUENTIALORDERING_VALUE = 1;
    private static final AdHocOrdering[] VALUES_ARRAY =
    new AdHocOrdering[] {
    PARALLELORDERING,
    SEQUENTIALORDERING,
    };
    public static final List<AdHocOrdering> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static AdHocOrdering get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        AdHocOrdering result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static AdHocOrdering getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    AdHocOrdering result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static AdHocOrdering get(int value) {
        switch (value) {
            case PARALLELORDERING_VALUE: return PARALLELORDERING;
            case SEQUENTIALORDERING_VALUE: return SEQUENTIALORDERING;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private AdHocOrdering(int value, String name, String literal) {
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
