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
 * ResourceType v2.1.00
 * 
 * 
 * 
 * 
 * 
 */
@objid ("0092fefa-c4be-1fd8-97fe-001ec947cd2a")
public interface ResourceType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("6f77cf38-3f3d-4817-99f5-5b6bfafc27fd")
    public static final String MNAME = "ResourceType";

    /**
     * The metaclass qualified name.
     */
    @objid ("c0386c62-8529-4750-88c1-baccab147c5e")
    public static final String MQNAME = "Infrastructure.ResourceType";

    @objid ("1cb032b7-9ac9-46f1-b30f-f2f395b83488")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'ResourceType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this resource will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("577208a4-098c-4cf2-ab22-50907cf124ba")
    boolean isIsHidden();

    /**
     * Setter for attribute 'ResourceType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this resource will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("d5b99381-b2e1-4c39-9186-d68181c562c2")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'ResourceType.LabelKey'
     * 
     * Metamodel description:
     * <i>The label key used to look into module resources for the translated label.</i>
     * 
     */
    @objid ("0972cac7-fc07-4d0d-83f5-6c9c90398f1e")
    String getLabelKey();

    /**
     * Setter for attribute 'ResourceType.LabelKey'
     * 
     * Metamodel description:
     * <i>The label key used to look into module resources for the translated label.</i>
     * 
     */
    @objid ("563ae5bb-52c4-4144-945b-fe045182cb61")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'ResourceType.Icon'
     * 
     * Metamodel description:
     * <i>Optional icon for the browser and diagram</i>
     * 
     */
    @objid ("a355fcfa-7a49-4202-8081-55db50bc0121")
    String getIcon();

    /**
     * Setter for attribute 'ResourceType.Icon'
     * 
     * Metamodel description:
     * <i>Optional icon for the browser and diagram</i>
     * 
     */
    @objid ("7ab83fbb-47be-44a0-9ca1-990fc91f8061")
    void setIcon(String value);

    /**
     * Getter for attribute 'ResourceType.Image'
     * 
     * Metamodel description:
     * <i>Big image for the diagram in image mode.</i>
     * 
     */
    @objid ("36c7804c-6132-456a-89f5-1aeec4d69b20")
    String getImage();

    /**
     * Setter for attribute 'ResourceType.Image'
     * 
     * Metamodel description:
     * <i>Big image for the diagram in image mode.</i>
     * 
     */
    @objid ("85c4bc24-16dd-407c-a31f-238f519553ef")
    void setImage(String value);

    /**
     * Getter for relation 'ResourceType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5f703585-f530-4264-9d8c-cd215993c52c")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'ResourceType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("75e5c35f-cca1-49e3-ae02-999d15be09cb")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'ResourceType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0cf7e52b-be2a-4098-8116-f459c022ef05")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'ResourceType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a59af2c1-acdf-411b-9740-dd9a5ce370c7")
    void setOwnerReference(MetaclassReference value);

    /**
     * Getter for relation 'ResourceType->TypedResource'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("794ed6f6-cfd3-4e31-aae1-47363fe5605b")
    EList<AbstractResource> getTypedResource();

    /**
     * Filtered Getter for relation 'ResourceType->TypedResource'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e26b3c67-d1ca-4bbd-98a0-875fadfedeb5")
    <T extends AbstractResource> List<T> getTypedResource(java.lang.Class<T> filterClass);
}

