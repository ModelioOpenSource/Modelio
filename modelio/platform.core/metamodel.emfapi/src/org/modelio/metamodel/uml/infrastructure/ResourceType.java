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
    @objid ("6ba0fb34-b400-4b7d-b483-423ac87b15d8")
    public static final String MNAME = "ResourceType";

    /**
     * The metaclass qualified name.
     */
    @objid ("78ab52aa-460b-4e8c-9000-845f7fc613f9")
    public static final String MQNAME = "Infrastructure.ResourceType";

    @objid ("1cb032b7-9ac9-46f1-b30f-f2f395b83488")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'ResourceType.IsHidden'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("71f1e88c-84ae-4b91-badf-440f5c9cbf63")
    boolean isIsHidden();

    /**
     * Setter for attribute 'ResourceType.IsHidden'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a1fa85ac-7069-4729-b9ba-87b5b119b764")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'ResourceType.LabelKey'
     * 
     * Metamodel description:
     * <i>The label key used to look into module resources for the translated label.</i>
     */
    @objid ("c98c0e10-e140-488b-9944-067246ef0b4f")
    String getLabelKey();

    /**
     * Setter for attribute 'ResourceType.LabelKey'
     * 
     * Metamodel description:
     * <i>The label key used to look into module resources for the translated label.</i>
     */
    @objid ("dc63b6b5-fdcd-4c17-b616-c6b25ba47916")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'ResourceType.Icon'
     * 
     * Metamodel description:
     * <i>Optional icon for the browser and diagram</i>
     */
    @objid ("ad63aa93-cb25-42a7-ae54-ffdb6507246d")
    String getIcon();

    /**
     * Setter for attribute 'ResourceType.Icon'
     * 
     * Metamodel description:
     * <i>Optional icon for the browser and diagram</i>
     */
    @objid ("7fd7569e-3626-4e7d-bf97-2acd71281aa7")
    void setIcon(String value);

    /**
     * Getter for attribute 'ResourceType.Image'
     * 
     * Metamodel description:
     * <i>Big image for the diagram in image mode.</i>
     */
    @objid ("9093a97f-a09a-4dca-b9d6-710c2ecbe5b9")
    String getImage();

    /**
     * Setter for attribute 'ResourceType.Image'
     * 
     * Metamodel description:
     * <i>Big image for the diagram in image mode.</i>
     */
    @objid ("e341c7f2-c32f-4b48-9a0b-099e9504152e")
    void setImage(String value);

    /**
     * Getter for relation 'ResourceType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1bb0770e-ccbb-41c3-a7b9-656d6020c69f")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'ResourceType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ca511679-eab3-4842-94bf-a210a5939c54")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'ResourceType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cc0ccc1c-801c-4bf9-a576-55d46c49267a")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'ResourceType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("766e411f-bdd0-44f1-873a-cdf771929f42")
    void setOwnerReference(MetaclassReference value);

    /**
     * Getter for relation 'ResourceType->TypedResource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b9cad2a0-2395-4477-8bed-b9e9929b8a56")
    EList<AbstractResource> getTypedResource();

    /**
     * Filtered Getter for relation 'ResourceType->TypedResource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a0d59a9d-814f-405e-ba69-939dab7b433e")
    <T extends AbstractResource> List<T> getTypedResource(java.lang.Class<T> filterClass);

}
