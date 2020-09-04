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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.bpmn.objects;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemKind;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;

/**
 * BpmnItemDefinition v0.0.9054
 * 
 * 
 * null
 */
@objid ("0007a24c-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnItemDefinition extends BpmnSharedElement {
    /**
     * The metaclass simple name.
     */
    @objid ("b82760c8-58e5-4574-b1c2-2df045b26e52")
    public static final String MNAME = "BpmnItemDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("0c84bdf9-ad09-46ca-9842-8ea484da9af5")
    public static final String MQNAME = "Standard.BpmnItemDefinition";

    /**
     * Getter for attribute 'BpmnItemDefinition.ItemKind'
     * 
     * Metamodel description:
     * <i>This defines the nature of the Item. Possible values are physical or 
     * information. The default value is information.</i>
     */
    @objid ("238a4f1c-bcf4-48c9-b47f-3ed12ec1d7eb")
    BpmnItemKind getItemKind();

    /**
     * Setter for attribute 'BpmnItemDefinition.ItemKind'
     * 
     * Metamodel description:
     * <i>This defines the nature of the Item. Possible values are physical or 
     * information. The default value is information.</i>
     */
    @objid ("4ca12486-02fd-4a7a-af55-050ecdeb9907")
    void setItemKind(BpmnItemKind value);

    /**
     * Getter for attribute 'BpmnItemDefinition.IsCollection'
     * 
     * Metamodel description:
     * <i>Setting this flag to true indicates that the actual data type is a 
     * collection.</i>
     */
    @objid ("90ba3b78-cbc9-42a1-b7cb-c5accbd9355e")
    boolean isIsCollection();

    /**
     * Setter for attribute 'BpmnItemDefinition.IsCollection'
     * 
     * Metamodel description:
     * <i>Setting this flag to true indicates that the actual data type is a 
     * collection.</i>
     */
    @objid ("65577bf1-2650-4a9b-b99d-84e346e1fc1d")
    void setIsCollection(boolean value);

    /**
     * Getter for relation 'BpmnItemDefinition->TypedMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c7ebe1bc-3c06-4e56-a593-405050a6a92b")
    EList<BpmnMessage> getTypedMessage();

    /**
     * Filtered Getter for relation 'BpmnItemDefinition->TypedMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4b8f735b-1e49-48a8-8b4b-8bb0ca8a59d0")
    <T extends BpmnMessage> List<T> getTypedMessage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnItemDefinition->TypedItem'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ce195094-1e86-445f-91e1-e44c5dce605c")
    EList<BpmnItemAwareElement> getTypedItem();

    /**
     * Filtered Getter for relation 'BpmnItemDefinition->TypedItem'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("00ec090b-87fd-4147-9360-b5919816af4b")
    <T extends BpmnItemAwareElement> List<T> getTypedItem(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnItemDefinition->TypedResourceParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cff966a9-f468-46ac-93d4-56aeff862ca6")
    EList<BpmnResourceParameter> getTypedResourceParameter();

    /**
     * Filtered Getter for relation 'BpmnItemDefinition->TypedResourceParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1dd8cf38-dd0b-4dbb-a5e8-2cb205c835f4")
    <T extends BpmnResourceParameter> List<T> getTypedResourceParameter(java.lang.Class<T> filterClass);

}
