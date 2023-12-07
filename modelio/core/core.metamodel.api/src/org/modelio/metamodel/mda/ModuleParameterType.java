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
package org.modelio.metamodel.mda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ModuleParameterType v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("002b11b4-91e1-1f74-804b-001ec947cd2a")
public enum ModuleParameterType {
    @objid ("698318da-f058-4919-b008-be67e72d2b11")
    TYPE_PARAM_BOOLEAN(0, "TYPE_PARAM_BOOLEAN", "TYPE_PARAM_BOOLEAN"),
    @objid ("75a23a1d-d535-4c73-a9c9-78a8092c0c72")
    TYPE_PARAM_STRING(1, "TYPE_PARAM_STRING", "TYPE_PARAM_STRING"),
    @objid ("dfec8aac-a758-44d2-93a8-e25746eb69d9")
    TYPE_PARAM_ENUM(2, "TYPE_PARAM_ENUM", "TYPE_PARAM_ENUM"),
    @objid ("89fa9646-5fff-44b5-abbd-3c453187d146")
    TYPE_PARAM_FILE(3, "TYPE_PARAM_FILE", "TYPE_PARAM_FILE"),
    @objid ("62cd14ae-ff87-4e31-842c-9b911fcd2bb8")
    TYPE_PARAM_INTEGER(4, "TYPE_PARAM_INTEGER", "TYPE_PARAM_INTEGER"),
    @objid ("048e953d-2f5e-41c5-b37e-7414875339f0")
    TYPE_PARAM_DIRECTORY(5, "TYPE_PARAM_DIRECTORY", "TYPE_PARAM_DIRECTORY"),
    @objid ("fceec8f4-9c5c-4306-ad58-c174ac8af2ed")
    TYPE_PARAM_PASSWORD(6, "TYPE_PARAM_PASSWORD", "TYPE_PARAM_PASSWORD"),
    @objid ("f0ced9c6-58b9-413e-a67c-aee107880376")
    TYPE_PARAM_COLOR(7, "TYPE_PARAM_COLOR", "TYPE_PARAM_COLOR");

public static final int TYPE_PARAM_BOOLEAN_VALUE = 0;
    public static final int TYPE_PARAM_STRING_VALUE = 1;
    public static final int TYPE_PARAM_ENUM_VALUE = 2;
    public static final int TYPE_PARAM_FILE_VALUE = 3;
    public static final int TYPE_PARAM_INTEGER_VALUE = 4;
    public static final int TYPE_PARAM_DIRECTORY_VALUE = 5;
    public static final int TYPE_PARAM_PASSWORD_VALUE = 6;
    public static final int TYPE_PARAM_COLOR_VALUE = 7;
    private static final ModuleParameterType[] VALUES_ARRAY =
    new ModuleParameterType[] {
    TYPE_PARAM_BOOLEAN,
    TYPE_PARAM_STRING,
    TYPE_PARAM_ENUM,
    TYPE_PARAM_FILE,
    TYPE_PARAM_INTEGER,
    TYPE_PARAM_DIRECTORY,
    TYPE_PARAM_PASSWORD,
    TYPE_PARAM_COLOR,
    };
    public static final List<ModuleParameterType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static ModuleParameterType get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        ModuleParameterType result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static ModuleParameterType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    ModuleParameterType result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static ModuleParameterType get(int value) {
        switch (value) {
            case TYPE_PARAM_BOOLEAN_VALUE: return TYPE_PARAM_BOOLEAN;
            case TYPE_PARAM_STRING_VALUE: return TYPE_PARAM_STRING;
            case TYPE_PARAM_ENUM_VALUE: return TYPE_PARAM_ENUM;
            case TYPE_PARAM_FILE_VALUE: return TYPE_PARAM_FILE;
            case TYPE_PARAM_INTEGER_VALUE: return TYPE_PARAM_INTEGER;
            case TYPE_PARAM_DIRECTORY_VALUE: return TYPE_PARAM_DIRECTORY;
            case TYPE_PARAM_PASSWORD_VALUE: return TYPE_PARAM_PASSWORD;
            case TYPE_PARAM_COLOR_VALUE: return TYPE_PARAM_COLOR;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private ModuleParameterType(int value, String name, String literal) {
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
