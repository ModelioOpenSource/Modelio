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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

/**
 * ResourceType v2.1.00
 */
@objid ("0092fefa-c4be-1fd8-97fe-001ec947cd2a")
public interface ResourceType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("cabaf104-a7c9-411f-823b-11c357899043")
    public static final String MNAME = "ResourceType";

    /**
     * The metaclass qualified name.
     */
    @objid ("046efd94-bc54-4b4e-9ad2-21aff066a5bb")
    public static final String MQNAME = "Infrastructure.ResourceType";

    @objid ("1cb032b7-9ac9-46f1-b30f-f2f395b83488")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'ResourceType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this resource will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("5f58d834-8063-41a1-b7ea-7d647a40caeb")
    boolean isIsHidden();

    /**
     * Setter for attribute 'ResourceType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this resource will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("40d86791-368c-4761-a700-4e52769f4ed0")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'ResourceType.LabelKey'
     * 
     * Metamodel description:
     * <i>The label key used to look into module resources for the translated label.</i>
     */
    @objid ("cb7718a2-4901-47ae-b850-5e79288bd4ee")
    String getLabelKey();

    /**
     * Setter for attribute 'ResourceType.LabelKey'
     * 
     * Metamodel description:
     * <i>The label key used to look into module resources for the translated label.</i>
     */
    @objid ("afa3130a-3a9a-4009-8378-7fbf960a5547")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'ResourceType.Icon'
     * 
     * Metamodel description:
     * <i>Optional icon for the browser and diagram</i>
     */
    @objid ("63846800-312d-400d-b97b-4e68f785f98f")
    String getIcon();

    /**
     * Setter for attribute 'ResourceType.Icon'
     * 
     * Metamodel description:
     * <i>Optional icon for the browser and diagram</i>
     */
    @objid ("7e5791a1-edaa-4093-acd9-881a18dd8832")
    void setIcon(String value);

    /**
     * Getter for attribute 'ResourceType.Image'
     * 
     * Metamodel description:
     * <i>Big image for the diagram in image mode.</i>
     */
    @objid ("21638f2c-9421-4716-8244-95030f14bccb")
    String getImage();

    /**
     * Setter for attribute 'ResourceType.Image'
     * 
     * Metamodel description:
     * <i>Big image for the diagram in image mode.</i>
     */
    @objid ("801c01fb-329a-405e-9174-cc21f1cf82b7")
    void setImage(String value);

    /**
     * Getter for relation 'ResourceType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("26d5a564-a425-4047-b9bb-95e951ec0466")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'ResourceType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a9d8c1bf-8599-441c-8ef0-b031b3ef97ec")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'ResourceType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f7771cbc-7f0f-43fd-9b30-553bad6d1988")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'ResourceType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e7f38817-a7d3-4118-8a1f-e81b8f07936f")
    void setOwnerReference(MetaclassReference value);

    /**
     * Getter for relation 'ResourceType->TypedResource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("56cac07d-9fd1-44a9-b203-876a4fdde921")
    EList<AbstractResource> getTypedResource();

    /**
     * Filtered Getter for relation 'ResourceType->TypedResource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("655b90bc-6c42-42fa-ac0e-30305c3b53d6")
    <T extends AbstractResource> List<T> getTypedResource(java.lang.Class<T> filterClass);

}
