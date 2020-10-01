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

package org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatecatchevent.BpmnIntermediateCatchEventEditPart;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("4b16f6b7-095c-4eda-8002-71b9ec783943")
public class ThrowCatchEventMatchingFeedbackEditPolicy extends SelectionHelperLinksEditPolicy {
    /**
     * Compute the matching 'link throw' and 'link catch' events for 'from'. 'from' can be either a 'link throw' or a 'link catch' event.
     */
    @objid ("9a829426-3584-4fdd-a2a5-2f3304ee2d98")
    @Override
    public Map<EditPart, List<EditPart>> getLinkedParts(EditPart fromEditPart) {
        Map<EditPart, List<EditPart>> linkedParts = new HashMap<>();
        
        if (fromEditPart instanceof BpmnIntermediateThrowEventEditPart) {
            linkedParts.put(fromEditPart, getLinkedParts((BpmnIntermediateThrowEventEditPart) fromEditPart));
            return linkedParts;
        } else if (fromEditPart instanceof BpmnIntermediateCatchEventEditPart) {
            EditPart throwEventEditPart = getThrowForCatch((BpmnIntermediateCatchEventEditPart) fromEditPart);
            return throwEventEditPart != null ? getLinkedParts(throwEventEditPart) : Collections.EMPTY_MAP;
        } else
            return Collections.EMPTY_MAP;
    }

    @objid ("14d6783c-7433-45c7-b18c-f38792ce36a4")
    private EditPart getThrowForCatch(BpmnIntermediateCatchEventEditPart catchEventEditPart) {
        // Starting from a link Catch event, just get the corresponding throw and get the links from there
        GraphicalViewer viewer = (GraphicalViewer) getHost().getViewer();
        GmNodeModel model = catchEventEditPart.getModel();
        BpmnIntermediateCatchEvent catchEvent = (BpmnIntermediateCatchEvent) model.getRelatedElement();
        
        List<BpmnLinkEventDefinition> eventDefs = catchEvent.getEventDefinitions(BpmnLinkEventDefinition.class);
        for (BpmnLinkEventDefinition def : eventDefs) {
            BpmnEventDefinition target = def.getTarget();
            if (target != null) {
                BpmnEvent throwEvent = def.getTarget().getDefined();
                List<GmModel> gms = model.getDiagram().getAllGMRepresenting(new MRef(throwEvent));
                if (!gms.isEmpty()) {
                    BpmnIntermediateThrowEventEditPart throwEventEditPart = (BpmnIntermediateThrowEventEditPart) viewer.getEditPartRegistry().get(gms.get(0));
                    return throwEventEditPart;
                }
            }
        }
        return null;
    }

    @objid ("8a56746d-c650-47a0-8eae-67ffd6089d39")
    private List<EditPart> getLinkedParts(BpmnIntermediateThrowEventEditPart throwEventEditPart) {
        GraphicalViewer viewer = (GraphicalViewer) getHost().getViewer();
        GmNodeModel model = throwEventEditPart.getModel();
        BpmnIntermediateThrowEvent throwEvent = (BpmnIntermediateThrowEvent) model.getRelatedElement();
        
        List<BpmnEvent> catchEvents = new ArrayList<>();
        for (BpmnLinkEventDefinition throwDefinition : throwEvent.getEventDefinitions(BpmnLinkEventDefinition.class)) {
            for (BpmnLinkEventDefinition catchDefinition : throwDefinition.getSource()) {
                catchEvents.add(catchDefinition.getDefined());
            }
        }
        
        List<EditPart> catchEventEditParts = new ArrayList<>();
        for (BpmnEvent catchEvent : catchEvents) {
            List<GmModel> candidates = model.getDiagram().getAllGMRepresenting(new MRef(catchEvent));
            if (!candidates.isEmpty()) {
                GmNodeModel gmCatchEvent = (GmNodeModel) candidates.get(0);
                if (gmCatchEvent instanceof GmPortContainer) {
                    gmCatchEvent = ((GmPortContainer) gmCatchEvent).getMainNode();
                    GraphicalEditPart catchEventEditPart = (GraphicalEditPart) viewer.getEditPartRegistry().get(gmCatchEvent);
                    catchEventEditParts.add(catchEventEditPart);
                }
            }
        }
        return catchEventEditParts;
    }

}
