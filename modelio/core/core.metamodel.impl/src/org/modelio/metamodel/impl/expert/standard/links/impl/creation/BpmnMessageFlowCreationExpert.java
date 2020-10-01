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

package org.modelio.metamodel.impl.expert.standard.links.impl.creation;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation expert for {@link BpmnMessageFlow}
 * <p>
 * Extracts from BPMN 2.0.2 official specification:
 * <ul>
 * <li>MessageFlow.sourceRef : InteractionNode The InteractionNode that the Message Flow is connecting from. <br>
 * Of the types of InteractionNode, only Pools/Participants, Activities, and
 * Events can be the source of a Message Flow.
 * 
 * <li>MessageFlow.targetRef : InteractionNode The InteractionNode that the Message Flow is connecting to. <br>
 * Of the types of InteractionNode, only Pools/Participants, Activities, and
 * Events can be the target of a Message Flow.
 * 
 * <li>Interaction Node
 * <p>
 * The InteractionNode element is used to provide a single element as the source and target Message Flow
 * associations instead of the individual associations of the elements that can connect to Message
 * Flows.
 * <p>
 * Only the Pool/Participant, Activity, and Event elements can connect to Message Flows. The
 * InteractionNode element is also used to provide a single element for source and target of Conversation Links.
 * <p>
 * InteractionNode is parent class of ConversationNode, Task, Event, Participant:
 * 
 * <pre>
 * + InteractionNode  (abstract)
 * + ConversationNode (abstract)
 * + SubConversation, Conversation, CallConversation
 * + Task (concrete)
 * + SendTask, ReceiveTask, ServiceTask, UserTask, ManualTask, ScriptTask, BusinessRuleTask
 * + Event (abstract)
 * + ThrowEvent, CatchEvent
 * + Participant (concrete)
 * </pre>
 * <p>
 * <li> An End Event MAY be the source for Message Flow; it can have 0 (zero) or more outgoing Message Flow.
 * <li> An End Event MUST NOT be the target for Message Flow; it can have no incoming Message Flow.
 * <li> A Start Event MUST NOT be a source for Message Flow; it MUST NOT have outgoing Message Flow.
 * <li> A Start Event MAY be the target for Message Flow; it can have 0 (zero) or more incoming Message Flow.
 * </ul>
 */
@objid ("7e974185-1eb2-11e2-8009-002564c97630")
public class BpmnMessageFlowCreationExpert extends DefaultDelegatingLinkExpert {
    @objid ("1a14a6b3-9ccf-4513-822a-0adc28800000")
    public BpmnMessageFlowCreationExpert(ILinkExpert defaultExpert) {
        super(defaultExpert);
    }

    @objid ("7e99a298-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canLink(final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        if (!canSource(linkMetaclass, fromElement.getMClass())) {
            return false;
        }
        
        if (fromElement.equals(toElement)) {
            return false;
        }
        
        if (!isValidStart(fromElement) || !isValidEnd(toElement) || isSameContext(fromElement, toElement)) {
            return false;
        }
        return canTarget(linkMetaclass, toElement.getMClass());
    }

    @objid ("7e97418a-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canSource(MObject linkElement, MObject from) {
        return isValidStart(from);
    }

    @objid ("7e99a2ab-1eb2-11e2-8009-002564c97630")
    private static MObject getContext(final MObject element) {
        MObject o = element;
        
        for (int i = 0; i < 500 && o != null; i++) {
            if (o instanceof BpmnProcess) {
                return o;
            }
            if (o instanceof BpmnParticipant) {
                return ((BpmnParticipant) o).getProcess();
            }
            o = o.getCompositionOwner();
        }
        
        if (o != null) {
            Log.warning(new IllegalArgumentException(String.format("Cycle in composition ownership chain of %s", element)));
        }
        return null;
    }

    /**
     * @param from messageflow's source.
     * @param to messageflow's target.
     * @return <code>true</code> if the from and to elements have the same non-null context.
     */
    @objid ("7e99a2a3-1eb2-11e2-8009-002564c97630")
    private static boolean isSameContext(final MObject from, final MObject to) {
        MObject fromContext = getContext(from);
        MObject toContext = getContext(to);
        return fromContext != null && toContext != null && Objects.equals(fromContext, toContext);
    }

    @objid ("4b7c96e8-d0d8-449e-9e56-421bf481de3b")
    private static boolean isValidEnd(MObject o) {
        // A Start Event MAY be the target for Message Flow; it can have 0 (zero) or more incoming Message Flow.
        // An End Event MUST NOT be the target for Message Flow; it can have no incoming Message Flow.
        if (o instanceof BpmnParticipant
                || o instanceof BpmnTask
                || o instanceof BpmnCatchEvent /* includes BpmnStartEvent */
                || (o instanceof BpmnThrowEvent && !(o instanceof BpmnEndEvent))
                || o instanceof BpmnImplicitThrowEvent
                || o instanceof BpmnIntermediateThrowEvent
                || o instanceof BpmnSubProcess
                || o instanceof BpmnCallActivity) {
            return true;
        }
        return false;
    }

    @objid ("c8a131a3-56e6-48d8-9a10-f9840129029e")
    private static boolean isValidStart(MObject o) {
        // An End Event MAY be the source for Message Flow; it can have 0 (zero) or more outgoing Message Flow.
        // A Start Event MUST NOT be a source for Message Flow; it MUST NOT have outgoing Message Flow.
        if (o instanceof BpmnParticipant
                || o instanceof BpmnTask
                || (o instanceof BpmnCatchEvent && !(o instanceof BpmnStartEvent))
                || o instanceof BpmnThrowEvent /* includes BpmnEndEvent */
                || o instanceof BpmnSubProcess
                || o instanceof BpmnCallActivity) {
            return true;
        }
        return false;
    }

}
