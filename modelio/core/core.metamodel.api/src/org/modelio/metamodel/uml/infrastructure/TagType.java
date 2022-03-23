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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
 */
@objid ("008f48d2-c4be-1fd8-97fe-001ec947cd2a")
public interface TagType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("ea22d5e7-acec-4db0-9883-1507f8e4d925")
    public static final String MNAME = "TagType";

    /**
     * The metaclass qualified name.
     */
    @objid ("6c72ae8b-f1ac-4cc5-8de9-108257a40758")
    public static final String MQNAME = "Infrastructure.TagType";

    @objid ("1bb2d0a1-acf1-4d78-85d4-08a7d74e0427")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'TagType.ParamNumber'
     * 
     * Metamodel description:
     * <i>Number of parameters an occurrence may have.</i>
     */
    @objid ("aad2fbee-f412-42dc-83e4-74afe5eade5b")
    String getParamNumber();

    /**
     * Setter for attribute 'TagType.ParamNumber'
     * 
     * Metamodel description:
     * <i>Number of parameters an occurrence may have.</i>
     */
    @objid ("1a5570f2-cd98-4cde-b1eb-97669b34f285")
    void setParamNumber(String value);

    /**
     * Getter for attribute 'TagType.IsQualified'
     * 
     * Metamodel description:
     * <i>Determines whether or not an occurrence (TaggedValue) has a qualifier.</i>
     */
    @objid ("f00e8a1b-7e10-438d-bfec-92d127f729d4")
    boolean isIsQualified();

    /**
     * Setter for attribute 'TagType.IsQualified'
     * 
     * Metamodel description:
     * <i>Determines whether or not an occurrence (TaggedValue) has a qualifier.</i>
     */
    @objid ("0ab5e72a-dca2-4874-862b-903d0a5daf5a")
    void setIsQualified(boolean value);

    /**
     * Getter for attribute 'TagType.BelongToPrototype'
     * 
     * Metamodel description:
     * <i>Determines whether or not a TagType occurrence  belongs to the signature. For example, the TagType * that defines a pointer declaration in C++ belongs to the prototype of operations.</i>
     */
    @objid ("bf3a5185-6eda-46cc-a924-1658bbcd992a")
    boolean isBelongToPrototype();

    /**
     * Setter for attribute 'TagType.BelongToPrototype'
     * 
     * Metamodel description:
     * <i>Determines whether or not a TagType occurrence  belongs to the signature. For example, the TagType * that defines a pointer declaration in C++ belongs to the prototype of operations.</i>
     */
    @objid ("85d549d7-e4e9-44e1-8bf6-8db4ad17011c")
    void setBelongToPrototype(boolean value);

    /**
     * Getter for attribute 'TagType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this tag type will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("3898ddbe-a21f-404f-86fd-7c4cdfb3cf97")
    boolean isIsHidden();

    /**
     * Setter for attribute 'TagType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this tag type will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("fe602546-27ed-4480-914e-76e6205d2813")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'TagType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The tag type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     */
    @objid ("544a5053-baf6-460d-9271-b0a37bd127c0")
    String getLabelKey();

    /**
     * Setter for attribute 'TagType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The tag type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     */
    @objid ("a21a544f-32ab-418d-8122-276a97f3d253")
    void setLabelKey(String value);

    /**
     * Getter for relation 'TagType->TagOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("08068ac3-de9f-434b-afce-3b24e9235cd8")
    EList<TaggedValue> getTagOccurence();

    /**
     * Filtered Getter for relation 'TagType->TagOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b8a0e20e-e48b-4cc9-8cea-c2c34eb79d8a")
    <T extends TaggedValue> List<T> getTagOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TagType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ce94ece2-7a12-461a-b987-cbf3570a2c3d")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'TagType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3416bc70-4100-4304-94e3-0d837ad4713a")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'TagType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c1620ba2-11e5-40f2-a7c0-42a2b95fe32a")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'TagType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("08051e43-75a6-44aa-b712-2ed2a53fa3b6")
    void setOwnerReference(MetaclassReference value);

}
