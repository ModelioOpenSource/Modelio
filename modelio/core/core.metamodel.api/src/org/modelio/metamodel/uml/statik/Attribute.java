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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;

/**
 * Attribute v0.0.9054
 * 
 * 
 * An Attribute is a named slot within a Classifier that describes a range of values that instances of the Classifier may hold.  
 * 
 * In Modelio, an Attribute belongs to a Classifier as a Feature or to an AssociationEnd as a Qualifier.
 * 
 * 
 */
@objid ("0097ec08-c4be-1fd8-97fe-001ec947cd2a")
public interface Attribute extends StructuralFeature {
    /**
     * The metaclass simple name.
     */
    @objid ("3ea70adf-b8e9-4ad4-b613-19f241065a2d")
    public static final String MNAME = "Attribute";

    /**
     * The metaclass qualified name.
     */
    @objid ("6a0a7d23-cf26-4702-b027-c0eb2325747b")
    public static final String MQNAME = "Standard.Attribute";

    /**
     * Getter for attribute 'Attribute.TypeConstraint'
     * 
     * Metamodel description:
     * <i>Provides an indication of the instanciation of the Attribute's elementary class. For example, in the case of an attribute string, TypeConstraint determines the size of the string (*, 10, etc.).</i>
     * 
     */
    @objid ("b517c1a1-c869-4bdb-bcc4-2f47a88667d7")
    String getTypeConstraint();

    /**
     * Setter for attribute 'Attribute.TypeConstraint'
     * 
     * Metamodel description:
     * <i>Provides an indication of the instanciation of the Attribute's elementary class. For example, in the case of an attribute string, TypeConstraint determines the size of the string (*, 10, etc.).</i>
     * 
     */
    @objid ("74b70ec5-d12a-479b-9466-e84ad4417608")
    void setTypeConstraint(String value);

    /**
     * Getter for attribute 'Attribute.Value'
     * 
     * Metamodel description:
     * <i>Default value of the Attribute. This value is assigned at creation time, unless a specific value is specified.</i>
     * 
     */
    @objid ("62cf8fe1-19ee-4531-8cd0-3365cbdfbd59")
    String getValue();

    /**
     * Setter for attribute 'Attribute.Value'
     * 
     * Metamodel description:
     * <i>Default value of the Attribute. This value is assigned at creation time, unless a specific value is specified.</i>
     * 
     */
    @objid ("a58785e0-9426-47f8-bea0-d5a8d415e92b")
    void setValue(String value);

    /**
     * Getter for attribute 'Attribute.TargetIsClass'
     * 
     * Metamodel description:
     * <i>Determines that the target is itself a metaclass.</i>
     * 
     */
    @objid ("929b38af-a5c3-47e7-8f2b-2291cb0612e9")
    boolean isTargetIsClass();

    /**
     * Setter for attribute 'Attribute.TargetIsClass'
     * 
     * Metamodel description:
     * <i>Determines that the target is itself a metaclass.</i>
     * 
     */
    @objid ("872c37cc-3ce5-43b6-8c4c-c9a0875cc813")
    void setTargetIsClass(boolean value);

    /**
     * Getter for relation 'Attribute->Type'
     * 
     * Metamodel description:
     * <i>Determines which Class is the Attribute's type.</i>
     * 
     */
    @objid ("6d9e8206-93d1-4f1c-9dc2-18b8fd5b1b2a")
    GeneralClass getType();

    /**
     * Setter for relation 'Attribute->Type'
     * 
     * Metamodel description:
     * <i>Determines which Class is the Attribute's type.</i>
     * 
     */
    @objid ("904296ad-a0de-450b-9d10-0a3c9e1f38cb")
    void setType(GeneralClass value);

    /**
     * Getter for relation 'Attribute->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b2f2e64e-48b2-4e26-98d5-54e39dc1910e")
    Classifier getOwner();

    /**
     * Setter for relation 'Attribute->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("dd8077ae-72b3-40f7-ba53-d821efb74ec5")
    void setOwner(Classifier value);

    /**
     * Getter for relation 'Attribute->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c6508729-9b62-458f-b4b5-5361e037d236")
    EList<AttributeLink> getOccurence();

    /**
     * Filtered Getter for relation 'Attribute->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5354331f-6943-465a-9e41-6e7a52139b4f")
    <T extends AttributeLink> List<T> getOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Attribute->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a9592275-8d76-43a8-8786-6c2a061c93fd")
    EList<ObjectNode> getRepresentingObjectNode();

    /**
     * Filtered Getter for relation 'Attribute->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1df20163-1e0c-4103-b1ae-bd294958ac45")
    <T extends ObjectNode> List<T> getRepresentingObjectNode(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Attribute->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c3195f43-4fa6-4816-a252-d261a6caa8d0")
    AssociationEnd getQualified();

    /**
     * Setter for relation 'Attribute->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("00e55493-ae96-4e0a-957e-0a65dc4f0b4b")
    void setQualified(AssociationEnd value);
}

