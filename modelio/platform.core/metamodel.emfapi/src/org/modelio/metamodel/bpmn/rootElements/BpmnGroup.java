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
package org.modelio.metamodel.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;

/**
 * BpmnGroup v0.0.9054
 * 
 * 
 * <p>The Group object is an Artifact that provides a visual mechanism to group elements of a diagram informally. The grouping is tied to the Category supporting element . That is, a Group is a visual depiction of a single Category. The graphical elements within the Group will be assigned the Category of the Group. (Note: Categories can be highlighted through other mechanisms, such as color, as defined by a modeler or a modeling tool). Categories, which have user-defined semantics, can be used for documentation or analysis purposes.</p><p>For example, FlowElements can be categorized has being customer oriented vs. support oriented.</p><p>Groups are one way in which Categories of objects can be visually displayed on the diagram. That is, a Group is a visual depiction of a single Category. The graphical elements within the Group will be assigned the Category of the Group. The Category name appears on the diagram as the Group label. (Note:&nbsp;Categories can be highlighted through other mechanisms, such as color, as defined by a modeler or a modeling tool). A single Category can be used for multiple Groups in a diagram.</p><p>&nbsp;</p>
 */
@objid ("007a3514-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnGroup extends BpmnArtifact {
    /**
     * The metaclass simple name.
     */
    @objid ("aea3481b-2bdf-45be-bdf4-0342954887f0")
    public static final String MNAME = "BpmnGroup";

    /**
     * The metaclass qualified name.
     */
    @objid ("b4ac765f-f66c-437e-9ad5-451c4ccc70aa")
    public static final String MQNAME = "Standard.BpmnGroup";

    /**
     * Getter for attribute 'BpmnGroup.Category'
     * 
     * Metamodel description:
     * <i>specifies the Category that the Group represents (Further details about the definition of a Category can be found on page 92). The name of the Category provides the label for the Group.
     * The graphical elements within the boundaries of the Group will be assigned the Category.</i>
     */
    @objid ("34203cef-d4af-4b07-adfc-a51588476cdc")
    String getCategory();

    /**
     * Setter for attribute 'BpmnGroup.Category'
     * 
     * Metamodel description:
     * <i>specifies the Category that the Group represents (Further details about the definition of a Category can be found on page 92). The name of the Category provides the label for the Group.
     * The graphical elements within the boundaries of the Group will be assigned the Category.</i>
     */
    @objid ("55fc2394-28f7-40dc-80d2-8dcd9873e36b")
    void setCategory(String value);

    /**
     * Getter for relation 'BpmnGroup->Categorized'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("75d46e82-9db8-4fdd-988b-89d2af8f4c96")
    EList<BpmnFlowElement> getCategorized();

    /**
     * Filtered Getter for relation 'BpmnGroup->Categorized'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("76c8bcab-8c83-4c33-8986-dd678c8240fc")
    <T extends BpmnFlowElement> List<T> getCategorized(java.lang.Class<T> filterClass);

}
