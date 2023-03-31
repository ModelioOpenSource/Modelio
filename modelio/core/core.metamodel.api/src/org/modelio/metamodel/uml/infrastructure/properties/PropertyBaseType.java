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
    @objid ("33056c64-fe35-418a-942a-d5988081a95a")
    STRING(0, "String", "String"),
    @objid ("906c9ec0-7df7-4902-a393-379e26ec317a")
    TEXT(1, "Text", "Text"),
    @objid ("0f4d8bc5-4e93-4490-bcb3-a5bcd04ec897")
    BOOLEAN(2, "Boolean", "Boolean"),
    @objid ("58cab328-31d1-4566-97a2-dbdd8f587f4b")
    INTEGER(3, "Integer", "Integer"),
    @objid ("e1b5900a-6c57-49f1-a963-3516fc8e189d")
    UNSIGNED(4, "Unsigned", "Unsigned"),
    @objid ("5e222b93-f8ec-4f4b-adef-6a67fd59daea")
    FLOAT(5, "Float", "Float"),
    @objid ("038ed693-e130-4add-a5ad-871fd7dcf93a")
    ENUMERATE(6, "Enumerate", "Enumerate"),
    @objid ("cca07c11-ecc5-4bd7-b710-375b08508e9f")
    DATE(7, "Date", "Date"),
    @objid ("23f6eac6-690b-4e6e-abca-2bf52cf9646d")
    TIME(8, "Time", "Time"),
    @objid ("5335432d-a7bb-46cf-a485-0fcd301990ac")
    ELEMENT(9, "Element", "Element"),
    @objid ("b46a2160-1cf0-487f-aa8a-23e7855655bb")
    RICHTEXT(10, "Richtext", "Richtext"),
    @objid ("dadcc62a-8c88-4173-84a7-5002516eed30")
    MULTISTRING(11, "MultiString", "MultiString"),
    @objid ("c00de649-598d-4491-8c3b-18f56ac6d356")
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
