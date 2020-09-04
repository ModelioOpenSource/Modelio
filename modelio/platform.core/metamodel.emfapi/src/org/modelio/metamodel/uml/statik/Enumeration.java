/* 
 * Copyright 2013-2018 Modeliosoft
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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;

/**
 * Enumeration v0.0.9054
 * 
 * 
 * An Enumeration corresponds to C++ or Java enum, or equivalent types in Pascal, Ada or any other language. 
 * 
 * In Modelio, an Enumeration belongs to its NameSpace.
 */
@objid ("000981a2-c4bf-1fd8-97fe-001ec947cd2a")
public interface Enumeration extends GeneralClass {
    /**
     * The metaclass simple name.
     */
    @objid ("941811cd-6fc9-4bc0-82ad-bb5db8a457f4")
    public static final String MNAME = "Enumeration";

    /**
     * The metaclass qualified name.
     */
    @objid ("d6326a4d-3c11-4512-9231-f0ff12479a2f")
    public static final String MQNAME = "Standard.Enumeration";

    /**
     * Getter for relation 'Enumeration->Value'
     * 
     * Metamodel description:
     * <i>Link to the "Literal", which represents the possible values of the type representatives.</i>
     */
    @objid ("a95cdd5d-c795-4e2d-96e6-a806fbc9068d")
    EList<EnumerationLiteral> getValue();

    /**
     * Filtered Getter for relation 'Enumeration->Value'
     * 
     * Metamodel description:
     * <i>Link to the "Literal", which represents the possible values of the type representatives.</i>
     */
    @objid ("a8e40152-46ca-4a78-b904-993cdf74c9ed")
    <T extends EnumerationLiteral> List<T> getValue(java.lang.Class<T> filterClass);

}
