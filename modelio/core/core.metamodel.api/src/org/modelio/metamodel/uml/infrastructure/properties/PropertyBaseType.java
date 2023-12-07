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
package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * PropertyBaseType v0.0.9054
 * 
 * 
 * Each literal value corresponds to the following  conversion table:
 *  
 * <table border='1'>
 * <th>Enum Type</th>
 * <th>Java Object type</th>
 * <th>Storage string</th>
 * <tr align='left'>
 * <td>BOOLEAN</td>
 * <td>java.lang.Boolean</td>
 * <td>Boolean.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>ENUMERATE</td>
 * <td>org.modelio.metamodel.uml.infrastructure.properties.
 * PropertyEnumerationLitteral</td>
 * <td>The property literal name</td>
 * </tr>
 * <tr align='left'>
 * <td>FLOAT</td>
 * <td>java.lang.Float</td>
 * <td>Float.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>UNSIGNED</td>
 * <td>java.lang.Integer</td>
 * <td>Integer.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>INTEGER</td>
 * <td>java.lang.Integer</td>
 * <td>Integer.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>ELEMENT</td>
 * <td>MRef</td>
 * <td>MRef.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>RICHTEXT</td>
 * <td>MRef</td>
 * <td>MRef.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>TIME</td>
 * <td>java.sql.Time</td>
 * <td>Time as long, long.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>DATE</td>
 * <td>java.util.Date</td>
 * <td>Date as long, long.toString()</td>
 * </tr>
 * <tr align='left'>
 * <td>STRING</td>
 * <td>java.lang.String</td>
 * <td>the string itself</td>
 * </tr>
 * <tr align='left'>
 * <td>TEXT</td>
 * <td>java.lang.String</td>
 * <td>the string itself</td>
 * </tr>
 * </table>
 * 
 * 
 */
@objid ("0074c304-ec87-1098-b22e-001ec947cd2a")
public enum PropertyBaseType {
    @objid ("56f799df-68ad-4521-a31b-f2a0c8567132")
    STRING(0, "String", "String"),
    @objid ("cb78e141-7076-4837-be89-a8b5d0cde2ae")
    TEXT(1, "Text", "Text"),
    @objid ("7e4c01a4-11b6-409c-84ca-bcc0f867f425")
    BOOLEAN(2, "Boolean", "Boolean"),
    @objid ("9a6da3aa-5cd9-4158-87fb-a1541f928fdb")
    INTEGER(3, "Integer", "Integer"),
    @objid ("779c677f-62c0-40c6-8531-9a36dc9ee238")
    UNSIGNED(4, "Unsigned", "Unsigned"),
    @objid ("1a6cb6be-e0a7-4756-b583-455cf2022613")
    FLOAT(5, "Float", "Float"),
    @objid ("b44f3171-b2a4-45c2-8789-2cc03490b3aa")
    ENUMERATE(6, "Enumerate", "Enumerate"),
    @objid ("11befa07-3baa-44d4-aa51-a709aa2051be")
    DATE(7, "Date", "Date"),
    @objid ("1a9429ac-df1f-45a6-a526-0a24eaaab4cd")
    TIME(8, "Time", "Time"),
    @objid ("71dfc11b-e1a1-4fc2-b9bc-252faf67aea4")
    ELEMENT(9, "Element", "Element"),
    @objid ("e42feafb-ba33-42f2-9690-e696a1867546")
    RICHTEXT(10, "Richtext", "Richtext"),
    @objid ("6d7f0d52-4c1e-442e-8f1a-bf81880ac5bb")
    MULTISTRING(11, "MultiString", "MultiString"),
    @objid ("66aefeae-285f-4bdd-abb6-c30eda564732")
    MULTIELEMENT(12, "MultiElement", "MultiElement");

public static final int STRING_VALUE = 0;
    public static final int TEXT_VALUE = 1;
    public static final int BOOLEAN_VALUE = 2;
    public static final int INTEGER_VALUE = 3;
    public static final int UNSIGNED_VALUE = 4;
    public static final int FLOAT_VALUE = 5;
    public static final int ENUMERATE_VALUE = 6;
    public static final int DATE_VALUE = 7;
    public static final int TIME_VALUE = 8;
    public static final int ELEMENT_VALUE = 9;
    public static final int RICHTEXT_VALUE = 10;
    public static final int MULTISTRING_VALUE = 11;
    public static final int MULTIELEMENT_VALUE = 12;
    private static final PropertyBaseType[] VALUES_ARRAY =
    new PropertyBaseType[] {
    STRING,
    TEXT,
    BOOLEAN,
    INTEGER,
    UNSIGNED,
    FLOAT,
    ENUMERATE,
    DATE,
    TIME,
    ELEMENT,
    RICHTEXT,
    MULTISTRING,
    MULTIELEMENT,
    };
    public static final List<PropertyBaseType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    public static PropertyBaseType get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
        PropertyBaseType result = VALUES_ARRAY[i];
        if (result.toString().equals(literal)) {
           return result;
        }
      }
      return null;
    }
    public static PropertyBaseType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    PropertyBaseType result = VALUES_ARRAY[i];
    if (result.getName().equals(name)) {
    return result;
    }
    }
    return null;
    }
    public static PropertyBaseType get(int value) {
        switch (value) {
            case STRING_VALUE: return STRING;
            case TEXT_VALUE: return TEXT;
            case BOOLEAN_VALUE: return BOOLEAN;
            case INTEGER_VALUE: return INTEGER;
            case UNSIGNED_VALUE: return UNSIGNED;
            case FLOAT_VALUE: return FLOAT;
            case ENUMERATE_VALUE: return ENUMERATE;
            case DATE_VALUE: return DATE;
            case TIME_VALUE: return TIME;
            case ELEMENT_VALUE: return ELEMENT;
            case RICHTEXT_VALUE: return RICHTEXT;
            case MULTISTRING_VALUE: return MULTISTRING;
            case MULTIELEMENT_VALUE: return MULTIELEMENT;
        }
        return null;
    }
    private final int value;
    private final String name;
    private final String literal;
    private PropertyBaseType(int value, String name, String literal) {
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
