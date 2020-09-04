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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.bpmn.rootElements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociationDirection;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnAssociation v0.0.9054
 * 
 * 
 * An Association is used to associate information and Artifacts with Flow Objects. Text and graphical non-Flow Objects can be associated with the Flow Objects and Flow. An Association is also used to show the Activity used for
 * compensation. 
 * 
 * Within Modelio, Text Annotation is implemented by Notes: association is thus not used to connect Notes to Model elements.
 */
@objid ("00772770-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnAssociation extends BpmnArtifact {
    /**
     * The metaclass simple name.
     */
    @objid ("58cdee1e-da8a-4c11-afef-4181bcb953fa")
    public static final String MNAME = "BpmnAssociation";

    /**
     * The metaclass qualified name.
     */
    @objid ("f478856a-99f7-4de4-8b03-5c99acbf75cc")
    public static final String MQNAME = "Standard.BpmnAssociation";

    /**
     * Getter for attribute 'BpmnAssociation.AssociationDirection'
     * 
     * Metamodel description:
     * <i>associationDirection is an attribute that defines whether or not the Association shows any directionality with an arrowhead. 
     * The default is None (no arrowhead). 
     * A value of One means that the arrowhead SHALL be at the Target Object. 
     * A value of Both means that there SHALL be an arrowhead at both ends of the Association line.</i>
     */
    @objid ("3a326954-6624-4d2a-a099-851a7841d301")
    BpmnAssociationDirection getAssociationDirection();

    /**
     * Setter for attribute 'BpmnAssociation.AssociationDirection'
     * 
     * Metamodel description:
     * <i>associationDirection is an attribute that defines whether or not the Association shows any directionality with an arrowhead. 
     * The default is None (no arrowhead). 
     * A value of One means that the arrowhead SHALL be at the Target Object. 
     * A value of Both means that there SHALL be an arrowhead at both ends of the Association line.</i>
     */
    @objid ("5fac718f-f5fe-4048-85cf-3b90c88b974a")
    void setAssociationDirection(BpmnAssociationDirection value);

    /**
     * Getter for relation 'BpmnAssociation->TargetRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting to.</i>
     */
    @objid ("64ce76ce-f902-40e8-a473-679199e33a39")
    BpmnBaseElement getTargetRef();

    /**
     * Setter for relation 'BpmnAssociation->TargetRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting to.</i>
     */
    @objid ("5a7cdcb1-d250-4c77-a508-01dd8f72d360")
    void setTargetRef(BpmnBaseElement value);

    /**
     * Getter for relation 'BpmnAssociation->SourceRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting from.</i>
     */
    @objid ("c4b0ce99-c178-492f-a140-e1934e36efe1")
    BpmnBaseElement getSourceRef();

    /**
     * Setter for relation 'BpmnAssociation->SourceRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting from.</i>
     */
    @objid ("736b706e-d707-4fc7-a4ea-ecc5ba9ea801")
    void setSourceRef(BpmnBaseElement value);

}
