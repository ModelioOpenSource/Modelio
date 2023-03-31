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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * EnumerationLiteral v0.0.9054
 * 
 * 
 * An EnumerationLiteral is a possible value of an enumeration. The Name of the EnumerationLiteral represents its symbolic value. 
 * 
 * An EnumerationLiteral belongs to its Enumeration.
 * 
 * 
 * 
 */
@objid ("000a43ee-c4bf-1fd8-97fe-001ec947cd2a")
public interface EnumerationLiteral extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("96801401-cdd2-4675-8c64-ea388cc76e66")
    public static final String MNAME = "EnumerationLiteral";

    /**
     * The metaclass qualified name.
     */
    @objid ("fc10a3e0-af4f-4cdd-9eaf-6e16c6e26eb7")
    public static final String MQNAME = "Standard.EnumerationLiteral";

    /**
     * Getter for relation 'EnumerationLiteral->Valuated'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("fb9a4e36-980a-4054-aa49-5aae66c45a5f")
    Enumeration getValuated();

    /**
     * Setter for relation 'EnumerationLiteral->Valuated'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("74eb1d98-ee5c-4f08-9dd6-d815d32137e9")
    void setValuated(Enumeration value);
}

