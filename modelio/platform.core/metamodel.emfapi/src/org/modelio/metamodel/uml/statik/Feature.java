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
package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.VisibilityMode;

/**
 * Feature v0.0.9054
 * 
 * 
 * In the metamodel, a Feature declares a behavioral or structural characteristic of an Instance of a Classifier or of the Classifier itself. Properties of a Class can be handled in an abstract way. 
 * 
 * In Modelio, an AssociationEnd is also a Feature. The name of a Feature corresponds to the name of the role of the opposite related Class. 
 * 
 * In Modelio, a Feature belongs to its Classifier.
 */
@objid ("000b00cc-c4bf-1fd8-97fe-001ec947cd2a")
public interface Feature extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("39d40bc5-9ae6-483a-b5fe-91e956931563")
    public static final String MNAME = "Feature";

    /**
     * The metaclass qualified name.
     */
    @objid ("8a106e34-81b3-4db9-8330-32e99da8647b")
    public static final String MQNAME = "Standard.Feature";

    /**
     * Getter for attribute 'Feature.Visibility'
     * 
     * Metamodel description:
     * <i>Member visibility (public, protected, private, or package).</i>
     */
    @objid ("ae2b8bbe-1e6a-443e-8aa5-8a51a3086f2f")
    VisibilityMode getVisibility();

    /**
     * Setter for attribute 'Feature.Visibility'
     * 
     * Metamodel description:
     * <i>Member visibility (public, protected, private, or package).</i>
     */
    @objid ("fcadaacd-c43d-44b8-abd2-9c1f40b3a602")
    void setVisibility(VisibilityMode value);

    /**
     * Getter for attribute 'Feature.IsClass'
     * 
     * Metamodel description:
     * <i>Specifies a Class member that is shared by all instances of the Class.</i>
     */
    @objid ("cc614d20-6479-4a40-91f8-3773f4af99f7")
    boolean isIsClass();

    /**
     * Setter for attribute 'Feature.IsClass'
     * 
     * Metamodel description:
     * <i>Specifies a Class member that is shared by all instances of the Class.</i>
     */
    @objid ("74f4e8e8-aa78-4475-802e-47238ed65b98")
    void setIsClass(boolean value);

    /**
     * Getter for attribute 'Feature.IsAbstract'
     * 
     * Metamodel description:
     * <i>Determines abstract features, that is to say, those not implemented at this level.</i>
     */
    @objid ("8c8a3ffa-9370-4590-9f55-bb5a4290470f")
    boolean isIsAbstract();

    /**
     * Setter for attribute 'Feature.IsAbstract'
     * 
     * Metamodel description:
     * <i>Determines abstract features, that is to say, those not implemented at this level.</i>
     */
    @objid ("31b22e7f-0efe-453b-a64d-81f7e908d8f9")
    void setIsAbstract(boolean value);

}
