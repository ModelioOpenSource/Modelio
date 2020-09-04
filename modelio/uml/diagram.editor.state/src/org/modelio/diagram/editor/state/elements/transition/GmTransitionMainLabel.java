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

package org.modelio.diagram.editor.state.elements.transition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the default flat header to compute the correct label for transition.
 * 
 * @author fpoyer
 */
@objid ("f5ad2e98-55b6-11e2-877f-002564c97630")
public class GmTransitionMainLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f5ad2e9c-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f5ad2e9f-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef related element reference, must not be null.
     */
    @objid ("f5ad2ea1-55b6-11e2-877f-002564c97630")
    public GmTransitionMainLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("f5ad2eaa-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        final Transition theTransition = (Transition) getRelatedElement();
        
        if (theTransition == null) {
            return "";
        }
        
        final StringBuilder symbol = new StringBuilder();
        
        final boolean withEvent = true;
        
        // Trigger
        final Event trigger = theTransition.getTrigger();
        if (trigger != null) {
            symbol.append(trigger.getName());
        } else {
            symbol.append(theTransition.getReceivedEvents());
        }
        
        // Action
        String sEffect = theTransition.getEffect();
        Operation op = theTransition.getProcessed();
        Behavior behavior = theTransition.getBehaviorEffect();
        if (sEffect != null && !sEffect.isEmpty()) {
            symbol.append("/");
            symbol.append(sEffect);
        } else if (op != null) {
            symbol.append("/");
            symbol.append(op.getName());
            symbol.append("()");
        } else if (behavior != null) {
            symbol.append("/");
            symbol.append(behavior.getName());
        }
        
        // SentEvent
        final Signal effects = theTransition.getEffects();
        if (effects != null && withEvent) {
            symbol.append("^");
            symbol.append(effects.getName());
            symbol.append("()");
        } else {
            final String sentEvents = theTransition.getSentEvents();
            if (sentEvents.length() > 0) {
                symbol.append("^");
                symbol.append(sentEvents);
            }
        }
        
        if (symbol.length() == 0) {
            // Return the name at last resort
            return theTransition.getName();
        }
        return symbol.toString();
    }

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("f5ad2eaf-55b6-11e2-877f-002564c97630")
    public GmTransitionMainLabel() {
        // Nothing to do.
    }

    @objid ("f5ad2eb7-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmTransitionMainLabel.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("f5ad2ebd-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmTransitionMainLabel.", GmTransitionMainLabel.MINOR_VERSION);
    }

    @objid ("f5aeb51d-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("f5aeb522-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
