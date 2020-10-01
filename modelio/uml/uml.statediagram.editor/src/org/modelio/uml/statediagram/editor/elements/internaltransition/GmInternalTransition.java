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

package org.modelio.uml.statediagram.editor.elements.internaltransition;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a {@link InternalTransition}.
 */
@objid ("f54d0d1a-55b6-11e2-877f-002564c97630")
public class GmInternalTransition extends GmModelElementLabel {
    @objid ("f54d0d1f-55b6-11e2-877f-002564c97630")
    private InternalTransition element = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f54d0d22-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f54d0d25-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("fdbbc001-5a5b-11e2-9e33-00137282c51b")
    private static GmInternalTransitionStructuredStyleKeys STRUCTKEYS = new GmInternalTransitionStructuredStyleKeys();

    /**
     * Creates an internal transition graphic.
     * 
     * @param diagram The diagram.
     * @param theInternalTransition The represented element, may be null.
     * @param ref The represented element reference, may not be null.
     */
    @objid ("f54d0d27-55b6-11e2-877f-002564c97630")
    public GmInternalTransition(IGmDiagram diagram, InternalTransition theInternalTransition, MRef ref) {
        super(diagram, ref);
        this.element = theInternalTransition;
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("f54d0d33-55b6-11e2-877f-002564c97630")
    public GmInternalTransition() {
        // empty
    }

    @objid ("f54d0d36-55b6-11e2-877f-002564c97630")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        return stereotypes;
    }

    @objid ("f54e93c4-55b6-11e2-877f-002564c97630")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return taggedValues;
    }

    @objid ("f54e93d2-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("f54e93d9-55b6-11e2-877f-002564c97630")
    @Override
    public ModelElement getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("f54e93e0-55b6-11e2-877f-002564c97630")
    @Override
    public ModelElement getRepresentedElement() {
        return this.element;
    }

    @objid ("f54e93e7-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTKEYS.getStyleKey(metakey);
    }

    @objid ("f54e93f1-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTKEYS.getStyleKeys();
    }

    @objid ("f54e93fa-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInternalTransition.");
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

    @objid ("f54e9400-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        final InternalTransition theTransition = (InternalTransition) getRelatedElement();
        
        final StringBuilder symbol = new StringBuilder();
        
        final StateVertex targetVertex = theTransition.getTarget();
        final boolean withEvent = true;
        
        // Trigger
        final Event trigger = theTransition.getTrigger();
        if (trigger != null) {
            symbol.append(trigger.getName());
        } else {
            symbol.append(theTransition.getReceivedEvents());
        }
        
        // Guard condition
        final String condition = theTransition.getGuard();
        if (condition != null && !condition.equals("")) {
            symbol.append("[");
            symbol.append(condition);
            symbol.append("]");
        }
        
        // Action
        String sEffect = theTransition.getEffect();
        Operation op = theTransition.getProcessed();
        Behavior b = theTransition.getBehaviorEffect();
        if (sEffect != null && !sEffect.isEmpty()) {
            symbol.append("/");
            symbol.append(sEffect);
        } else if (op != null) {
            symbol.append("/");
            symbol.append(op.getName());
            symbol.append("()");
        } else if (b != null) {
            symbol.append("/");
            symbol.append(b.getName());
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
        
        // postGard
        final String postCondition = theTransition.getPostCondition();
        if (postCondition != null && !postCondition.equals("")) {
            symbol.append("{");
            symbol.append(postCondition);
            symbol.append("}");
        }
        
        if (symbol.length() == 0) {
            symbol.append(theTransition.getName());
            if (targetVertex != null) {
                symbol.append("::");
                symbol.append(targetVertex.getName());
            }
        
        }
        
        String elementLabel = symbol.toString();
        if (elementLabel.equals("")) {
            elementLabel = "/";
        }
        return elementLabel;
    }

    @objid ("f5501a5d-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInternalTransition.", GmInternalTransition.MINOR_VERSION);
    }

    @objid ("f5501a63-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (InternalTransition) resolveRef(this.getRepresentedRef());
    }

    @objid ("f5501a68-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
