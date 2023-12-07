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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;

/**
 * TagType v0.0.9054
 * 
 * 
 * <p>TagTypes are defined in MDA Modeler projects. TagTypes cannot be created using the Java / Jython language. They belong at metalevel, and are not structured at model level.</p>
 * 
 * 
 * 
 */
@objid ("008f48d2-c4be-1fd8-97fe-001ec947cd2a")
public interface TagType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("6f1f5328-5344-4da3-b777-5c8dd3baa609")
    public static final String MNAME = "TagType";

    /**
     * The metaclass qualified name.
     */
    @objid ("49ca0fbb-25f1-4ac4-ac51-6f7be583cffa")
    public static final String MQNAME = "Infrastructure.TagType";

    @objid ("1bb2d0a1-acf1-4d78-85d4-08a7d74e0427")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'TagType.ParamNumber'
     * 
     * Metamodel description:
     * <i>Number of parameters an occurrence may have.</i>
     * 
     */
    @objid ("1b1ba998-236f-4f2a-92da-df2369cbd4ed")
    String getParamNumber();

    /**
     * Setter for attribute 'TagType.ParamNumber'
     * 
     * Metamodel description:
     * <i>Number of parameters an occurrence may have.</i>
     * 
     */
    @objid ("16e54638-f1af-4edc-99fa-1b760ff37121")
    void setParamNumber(String value);

    /**
     * Getter for attribute 'TagType.IsQualified'
     * 
     * Metamodel description:
     * <i>Determines whether or not an occurrence (TaggedValue) has a qualifier.</i>
     * 
     */
    @objid ("af0202c3-797e-48e3-9c43-0725747d9644")
    boolean isIsQualified();

    /**
     * Setter for attribute 'TagType.IsQualified'
     * 
     * Metamodel description:
     * <i>Determines whether or not an occurrence (TaggedValue) has a qualifier.</i>
     * 
     */
    @objid ("336b6dbb-4fb2-4bc2-8af4-f5616e1e7719")
    void setIsQualified(boolean value);

    /**
     * Getter for attribute 'TagType.BelongToPrototype'
     * 
     * Metamodel description:
     * <i>Determines whether or not a TagType occurrence  belongs to the signature. For example, the TagType * that defines a pointer declaration in C++ belongs to the prototype of operations.</i>
     * 
     */
    @objid ("783799c4-26a9-463e-947f-5dc9858d2c8e")
    boolean isBelongToPrototype();

    /**
     * Setter for attribute 'TagType.BelongToPrototype'
     * 
     * Metamodel description:
     * <i>Determines whether or not a TagType occurrence  belongs to the signature. For example, the TagType * that defines a pointer declaration in C++ belongs to the prototype of operations.</i>
     * 
     */
    @objid ("13b95828-552b-40fa-8276-b0fa401c4978")
    void setBelongToPrototype(boolean value);

    /**
     * Getter for attribute 'TagType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this tag type will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("e6f37eb5-c78a-4dbb-9d3e-0949ce0e08a1")
    boolean isIsHidden();

    /**
     * Setter for attribute 'TagType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this tag type will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("43d363b7-fd83-415f-8bc0-1fe6827a7163")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'TagType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The tag type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     * 
     */
    @objid ("56a3a381-5a1e-4f51-9b91-14788b192ea2")
    String getLabelKey();

    /**
     * Setter for attribute 'TagType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The tag type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     * 
     */
    @objid ("b48f4806-c331-45ee-9221-6befed77cd1f")
    void setLabelKey(String value);

    /**
     * Getter for relation 'TagType->TagOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("317819f5-67b7-4892-858f-d1f1792bb616")
    EList<TaggedValue> getTagOccurence();

    /**
     * Filtered Getter for relation 'TagType->TagOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e6f79040-d932-4be8-bfff-42d2c86f0a6c")
    <T extends TaggedValue> List<T> getTagOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TagType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7419109c-e52a-484a-a3af-03d6ec9daf1e")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'TagType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("91f6da9e-6e42-46a1-9a51-3d3bc714e84e")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'TagType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("52912479-287f-44bd-9b27-05cd7dbb2216")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'TagType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d1021af8-8996-453c-ad00-4fb2ec352572")
    void setOwnerReference(MetaclassReference value);
}

