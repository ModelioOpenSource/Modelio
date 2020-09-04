/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;

/**
 * Abstraction v0.0.9054
 * 
 * 
 * An Abstraction is a relationship that relates two Elements or sets of Elements that represent the same concept at different levels of abstraction or from different viewpoints. 
 * 
 * An Abstraction is a Dependency in which the supplier and the client are mapped.
 */
@objid ("00849680-c4be-1fd8-97fe-001ec947cd2a")
public interface Abstraction extends Dependency {
    /**
     * The metaclass simple name.
     */
    @objid ("1d324128-9d99-43c0-9718-6472f148e86f")
    public static final String MNAME = "Abstraction";

    /**
     * The metaclass qualified name.
     */
    @objid ("aa79c253-faa5-43e5-95e8-94b0312f6749")
    public static final String MQNAME = "Standard.Abstraction";

    /**
     * Getter for attribute 'Abstraction.Mapping'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("77fd934a-81fd-4c8d-9ed5-35507433649e")
    String getMapping();

    /**
     * Setter for attribute 'Abstraction.Mapping'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("18aef41f-23f5-4155-8a6d-c1c5897e18a7")
    void setMapping(String value);

}
