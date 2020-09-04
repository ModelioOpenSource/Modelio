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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;

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
    @objid ("42828bdf-8790-455e-9b24-344616bf9f9e")
    public static final String MNAME = "TagType";

    /**
     * The metaclass qualified name.
     */
    @objid ("0e227500-66e6-4dd1-a422-00aae68aac91")
    public static final String MQNAME = "Infrastructure.TagType";

    @objid ("1bb2d0a1-acf1-4d78-85d4-08a7d74e0427")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'TagType.ParamNumber'
     * 
     * Metamodel description:
     * <i>Number of parameters an occurrence may have.</i>
     */
    @objid ("5c20c6d3-69f1-4a30-a1ad-0e9d6fdf334c")
    String getParamNumber();

    /**
     * Setter for attribute 'TagType.ParamNumber'
     * 
     * Metamodel description:
     * <i>Number of parameters an occurrence may have.</i>
     */
    @objid ("efdc7c44-cda3-41ed-8b59-e2888a87c505")
    void setParamNumber(String value);

    /**
     * Getter for attribute 'TagType.IsQualified'
     * 
     * Metamodel description:
     * <i>Determines whether or not an occurrence (TaggedValue) has a qualifier.</i>
     */
    @objid ("ce3423c1-0edf-4b69-b0b4-d7a92af4cc22")
    boolean isIsQualified();

    /**
     * Setter for attribute 'TagType.IsQualified'
     * 
     * Metamodel description:
     * <i>Determines whether or not an occurrence (TaggedValue) has a qualifier.</i>
     */
    @objid ("f1b5a9ba-c416-4e2c-a486-f5f6c687ec2d")
    void setIsQualified(boolean value);

    /**
     * Getter for attribute 'TagType.BelongToPrototype'
     * 
     * Metamodel description:
     * <i>Determines whether or not a TagType occurrence  belongs to the signature. For example, the TagType * that defines a pointer declaration in C++ belongs to the prototype of operations.</i>
     */
    @objid ("462eeaed-c31b-41cf-a2f1-f8d65763851d")
    boolean isBelongToPrototype();

    /**
     * Setter for attribute 'TagType.BelongToPrototype'
     * 
     * Metamodel description:
     * <i>Determines whether or not a TagType occurrence  belongs to the signature. For example, the TagType * that defines a pointer declaration in C++ belongs to the prototype of operations.</i>
     */
    @objid ("0a3cca09-a31a-415e-96ae-557cf5e237c6")
    void setBelongToPrototype(boolean value);

    /**
     * Getter for attribute 'TagType.IsHidden'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("96524fc7-13f5-447c-ac9a-cf4162cf0f4e")
    boolean isIsHidden();

    /**
     * Setter for attribute 'TagType.IsHidden'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a942b9d5-099e-43ac-b72f-9013d57ce903")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'TagType.LabelKey'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("fa49af99-8ec7-484d-9af7-f3c4294fa713")
    String getLabelKey();

    /**
     * Setter for attribute 'TagType.LabelKey'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("183b31f1-9119-4932-b91e-b5402d60c408")
    void setLabelKey(String value);

    /**
     * Getter for relation 'TagType->TagOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ef1a3fae-8ead-45ca-8880-8f30e568d028")
    EList<TaggedValue> getTagOccurence();

    /**
     * Filtered Getter for relation 'TagType->TagOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a445ed38-5a53-4037-9994-ace79bdbcb35")
    <T extends TaggedValue> List<T> getTagOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TagType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("eaabbeea-0689-4a76-8635-818b8ea34ccf")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'TagType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8afb78b7-efda-4397-a0f3-10c3cd443cf3")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'TagType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c9038fcd-655a-4055-9d50-036ff4493afd")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'TagType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("36584cb5-cefe-46f6-ad3a-2e75d25c204f")
    void setOwnerReference(MetaclassReference value);

}
