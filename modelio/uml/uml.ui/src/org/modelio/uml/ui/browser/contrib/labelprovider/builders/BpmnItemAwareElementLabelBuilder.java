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

package org.modelio.uml.ui.browser.contrib.labelprovider.builders;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.platform.model.ui.swt.images.ElementStyler;

/**
 * This class computes a label for a BpmnItemAwareElement (aka data objects).
 * 
 * The computed label is NNN:TTT:SSS where NNN is the data object name, TTT its 'type' name and SSS its 'state' name. (TTT and SSS) are optional.
 */
@objid ("e77971c1-e804-44a0-a589-7859d67cd9eb")
public class BpmnItemAwareElementLabelBuilder {
    @objid ("51fa9641-86f8-4db4-9bb5-bcb440933949")
    private static StyledString getInStateName(BpmnItemAwareElement theDataObject) {
        final StyledString symbol = new StyledString();
        
        // a BPMN can reference a UML state by too many ways:
        // 1) with 'inState : State' relation
        // 2) with 'dataState:BpmnState' (not used anywhere, not visible in Modelio GUI)
        // 2.1) then BpmnState.inState : State
        // 2.2) No UML state on the BpmnState , use the BpmnState name
        
        // Get Bpmn state
        BpmnDataState bpmnState = theDataObject.getDataState();
        
        // Get uml state
        ModelElement umlState = State.getTarget(theDataObject);
        if (umlState == null && bpmnState != null) {
            umlState = State.getTarget(bpmnState);
        }
        
        if (umlState != null) {
            symbol.append(umlState.getName(), ElementStyler.getStyler(umlState));
        } else if (bpmnState != null) {
            symbol.append(bpmnState.getName(), ElementStyler.getStyler(bpmnState));
        } else {
            return null; // no available state label
        }
        return symbol;
    }

    @objid ("3e8dfd67-b06a-464f-8304-e24dab11e6aa")
    private static StyledString getReferencedElementName(BpmnItemAwareElement relatedElement) {
        final StyledString symbol = new StyledString();
        
        ModelElement represented = Represents.getTarget(relatedElement);
        
        if (represented != null) {
            symbol.append(represented.getName(), ElementStyler.getStyler(represented));
        } else if (relatedElement.getItemSubjectRef() != null) {
            BpmnItemDefinition item = relatedElement.getItemSubjectRef();
            ModelElement ref = Reference.getTarget(item);
            if (ref != null) {
                symbol.append(ref.getName(), ElementStyler.getStyler(ref));
            } else {
                symbol.append(item.getName(), ElementStyler.getStyler(item));
            }
        } else {
            return null;
        }
        return symbol;
    }

    /**
     * Computes a label for a BpmnItemAwareElement
     * @param elt the data object whose name is to be computed
     * 
     * @return the computed name
     */
    @objid ("ce41e129-b868-469b-b4ee-453d3c2e87a6")
    public static StyledString computeLabel(BpmnItemAwareElement theDataObject) {
        final StyledString symbol = new StyledString();
        final Styler styler = ElementStyler.getStyler(theDataObject);
        
        if (theDataObject != null && theDataObject.isValid()) {
            symbol.append(theDataObject.getName(), styler);
        
            // Append represented element name and state if required and available
        
            // Append referenced element name
            StyledString referenceName = getReferencedElementName(theDataObject);
            if (referenceName != null) {
                symbol.append(" : ", styler);
                symbol.append(referenceName);
            }
        
            // Append state
            StyledString stateName = getInStateName(theDataObject);
            if (stateName != null) {
                symbol.append(" [", styler);
                symbol.append(stateName);
                symbol.append("]", styler);
            }
        }
        return symbol;
    }

}
