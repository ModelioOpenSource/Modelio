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
 * Generalization v0.0.9054
 * 
 * 
 * Each instance of the specific Classifier is also an instance of the general Classifier. Thus, the specific Classifier indirectly has Features of the more general Classifier. Generalization corresponds to the usual Inheritance concept.  
 * 
 * In Modelio, a Generalization belongs to its SpecializationNameSpace.
 */
@objid ("000c7bb4-c4bf-1fd8-97fe-001ec947cd2a")
public interface Generalization extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("41eec111-a7a4-4729-9a60-e013d6c667c2")
    public static final String MNAME = "Generalization";

    /**
     * The metaclass qualified name.
     */
    @objid ("a82ed30b-7152-4a47-963d-26ebd7c92f81")
    public static final String MQNAME = "Standard.Generalization";

    /**
     * Getter for attribute 'Generalization.Discriminator'
     * 
     * Metamodel description:
     * <i>Designates a family of sub-classes with the same parent Class. The name appears graphically, by linking the Generalizations belonging to this family.</i>
     */
    @objid ("c55732ed-f8ae-4307-94b7-51936780e20f")
    String getDiscriminator();

    /**
     * Setter for attribute 'Generalization.Discriminator'
     * 
     * Metamodel description:
     * <i>Designates a family of sub-classes with the same parent Class. The name appears graphically, by linking the Generalizations belonging to this family.</i>
     */
    @objid ("af730096-3765-42f2-9413-8d02f68cf9c7")
    void setDiscriminator(String value);

    /**
     * Getter for relation 'Generalization->SuperType'
     * 
     * Metamodel description:
     * <i>Defines the parent element.</i>
     */
    @objid ("1b207654-b85e-4131-92de-6da41edc92b8")
    NameSpace getSuperType();

    /**
     * Setter for relation 'Generalization->SuperType'
     * 
     * Metamodel description:
     * <i>Defines the parent element.</i>
     */
    @objid ("642446fc-f69a-40a9-a4ae-951d7401b9c2")
    void setSuperType(NameSpace value);

    /**
     * Getter for relation 'Generalization->SubType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a60ed23d-121d-4c9e-a184-6ca53283bc76")
    NameSpace getSubType();

    /**
     * Setter for relation 'Generalization->SubType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1aafc575-9817-4f74-a30c-6295af5c6fda")
    void setSubType(NameSpace value);

}
