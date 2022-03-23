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
 * MessageKind v0.0.9054
 * 
 * 
 * null
 */
@objid ("00965cbc-91e0-1f74-804b-001ec947cd2a")
public enum MessageKind {
    @objid ("1475117a-660e-4ccc-81b9-0686ce93262c")
    COMPLETEKIND(0, "CompleteKind", "CompleteKind"),
    @objid ("1f1a8501-7ef4-4148-b3a0-638cd3877ce3")
    LOSTKIND(1, "LostKind", "LostKind"),
    @objid ("1a895374-7b1c-4d1a-ae16-39b130ca6aff")
    FOUNDKIND(2, "FoundKind", "FoundKind"),
    @objid ("f2150c13-60fa-449b-a2c0-61b557639655")
    UNKNOWNKIND(3, "UnknownKind", "UnknownKind");
public static final int COMPLETEKIND_VALUE = 0;
    public static final int LOSTKIND_VALUE = 1;
    public static final int FOUNDKIND_VALUE = 2;
    public static final int UNKNOWNKIND_VALUE = 3;
    private static final MessageKind[] VALUES_ARRAY =
    new MessageKind[] {
    COMPLETEKIND,
    LOSTKIND,
    FOUNDKIND,
    UNKNOWNKIND,
    };
    public static final List<MessageKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static MessageKind get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        MessageKind result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static MessageKind getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    MessageKind result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static MessageKind get(int value) {
        switch (value) {
            case COMPLETEKIND_VALUE: return COMPLETEKIND;
            case LOSTKIND_VALUE: return LOSTKIND;
            case FOUNDKIND_VALUE: return FOUNDKIND;
            case UNKNOWNKIND_VALUE: return UNKNOWNKIND;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private MessageKind(int value, String name, String literal) {
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
