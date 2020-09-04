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

package org.modelio.metamodel.impl.expert.standard.links.impl.creation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation expert for {@link BpmnSequenceFlow}
 */
@objid ("7e99a2b9-1eb2-11e2-8009-002564c97630")
public class BpmnSequenceFlowCreationExpert extends DefaultDelegatingLinkExpert {
    @objid ("f92825f6-49d5-426a-bdc5-76d79a78a49e")
    public BpmnSequenceFlowCreationExpert(ILinkExpert defaultExpert) {
        super(defaultExpert);
    }

    @objid ("7e99a2be-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canLink(final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        if (!canSource(linkMetaclass, fromElement.getMClass())) {
            return false;
        }
        
        if (fromElement.equals(toElement)) {
            return false;
        }
        
        if (toElement instanceof BpmnStartEvent) {
            return false;
        }
        
        if (toElement instanceof BpmnBoundaryEvent) {
            return false;
        }
        
        if (toElement instanceof BpmnItemAwareElement) {
            return false;
        }
        
        if (!(toElement instanceof BpmnFlowNode)) {
            return false;
        }
        
        if (isSameContext(fromElement, toElement)) {
            return true;
        }
        return false;
    }

    @objid ("7e99a2c9-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canSource(final MObject fromElement, final MObject ownerElement) {
        if (fromElement instanceof BpmnEndEvent) {
            return false;
        }
        
        if (fromElement instanceof BpmnItemAwareElement) {
            return false;
        }
        
        if (fromElement instanceof BpmnFlowNode) {
            return true;
        }
        return false;
    }

    @objid ("7e99a2d2-1eb2-11e2-8009-002564c97630")
    private boolean isSameContext(final MObject fromElement, final MObject toElement) {
        MObject formContext = getContext(fromElement, true);
        MObject toContext = getContext(toElement, true);
        return formContext.equals(toContext);
    }

    @objid ("7e99a2da-1eb2-11e2-8009-002564c97630")
    private MObject getContext(final MObject element, final boolean rec) {
        if (element instanceof BpmnProcess) {
            return element;
        } else if (element instanceof BpmnSubProcess) {
            if (!rec) {
                return element;
            }
        } else if (element instanceof BpmnBoundaryEvent) {
            return getContext(((BpmnBoundaryEvent) element).getAttachedToRef(), rec);
        }
        return getContext(element.getCompositionOwner(), false);
    }

    @objid ("7e99a2e2-1eb2-11e2-8009-002564c97630")
    private BpmnLane getFirstLane(final BpmnLane lane) {
        BpmnLaneSet owner = (BpmnLaneSet) lane.getCompositionOwner();
        if (owner.getParentLane() != null) {
            return getFirstLane(owner.getParentLane());
        }
        return lane;
    }

}
