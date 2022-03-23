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
package org.modelio.uml.sequencediagram.editor.elements.lifeline.body;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * The body zone of the lifeline (== the dashed line).
 * 
 * @author fpoyer
 */
@objid ("d92cbbda-55b6-11e2-877f-002564c97630")
public class GmLifelineBody extends GmNoStyleCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d92cbbde-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d92cbbe1-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d92cbbe3-55b6-11e2-877f-002564c97630")
    public  GmLifelineBody() {
        super();
    }

    /**
     * Default c'tor.
     * @param diagram the diagram into which this gm is created.
     * @param relatedRef a reference to the element this gm is related to.
     */
    @objid ("d92cbbe6-55b6-11e2-877f-002564c97630")
    public  GmLifelineBody(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        // refreshFromObModel();
        
    }

    @objid ("d92cbbf1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return InteractionFragment.class.isAssignableFrom(type);
    }

    @objid ("d92cbbfa-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        boolean ret = (el instanceof InteractionFragment) &&
                ((InteractionFragment) el).getCovered().contains(this.getRelatedElement());
        return ret;
    }

    @objid ("d92cbc03-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        } else {
            return null;
        }
        
    }

    @objid ("d92cbc0d-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Eliminate children that no longer belong here.
        super.refreshFromObModel();
        
        // Unmask whatever should be unmasked.
        MObject relatedMObject = getRelatedElement();
        if (relatedMObject != null && relatedMObject.isValid()) {
            boolean forceUpdate = false;
            int index = 0;
        
            // Unmask and order all the ExecutionSpecification (nesting is only a graphical effect ;))
            // Get all execution specification on given lifeline and sort them by start line number.
            List<ExecutionSpecification> executionSpecifications = new ArrayList<>();
            executionSpecifications.addAll(((Lifeline) this.getRelatedElement()).getCoveredBy(ExecutionSpecification.class));
            Collections.sort(executionSpecifications,
                    (o1, o2) -> Integer.compare(o1.getLineNumber(), o2.getLineNumber()));
        
            // Now unmask if needed and check order.
            for (ExecutionSpecification execution : executionSpecifications) {
                forceUpdate = forceUpdate || unmaskAndOrderChild(execution, index);
                ++index;
            }
        
            // Unmask and order all the StateInvariant
            // Get all state invariants on given lifeline and sort them by start line number.
            List<StateInvariant> stateInvariants = new ArrayList<>();
            stateInvariants.addAll(((Lifeline) this.getRelatedElement()).getCoveredBy(StateInvariant.class));
            Collections.sort(stateInvariants,
                    (o1, o2) -> Integer.compare(o1.getLineNumber(), o2.getLineNumber()));
        
            // Now unmask if needed and check order.
            for (StateInvariant stateInvariant : stateInvariants) {
                forceUpdate = forceUpdate || unmaskAndOrderChild(stateInvariant, index);
                ++index;
            }
        
            // Unmask and order all the ExecutionOccurenceSpecification
            // Get all execution occurence specifications on given lifeline and sort them by start line number.
            List<ExecutionOccurenceSpecification> executionOccurenceSpecifications = new ArrayList<>();
            executionOccurenceSpecifications.addAll(((Lifeline) this.getRelatedElement()).getCoveredBy(ExecutionOccurenceSpecification.class));
            Collections.sort(executionOccurenceSpecifications,
                    (o1, o2) -> Integer.compare(o1.getLineNumber(), o2.getLineNumber()));
        
            // Now unmask if needed and check order.
            for (ExecutionOccurenceSpecification executionOccurenceSpecification : executionOccurenceSpecifications) {
                forceUpdate = forceUpdate || unmaskAndOrderChild(executionOccurenceSpecification, index);
                ++index;
            }
        
            if (forceUpdate) {
                getDiagram().refreshAllFromObModel();
            }
        }
        
    }

    @objid ("d92e4279-55b6-11e2-877f-002564c97630")
    private boolean unmaskAndOrderChild(final InteractionFragment fragment, final int index) {
        boolean updateNeeded = false;
        GmNodeModel child = getChild(new MRef(fragment));
        if (child == null) {
            child = getDiagram().unmask(this, fragment, null);
            updateNeeded = true;
        }
        
        if (child != null && getChildren().indexOf(child) != index) {
            this.moveChild(child, index);
            updateNeeded = true;
        }
        return updateNeeded;
    }

    @objid ("d92e4283-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(final GmNodeModel node) {
        final MObject childEl = node.getRelatedElement();
        return childEl == null || (childEl.isValid() && canUnmask(childEl));
    }

    @objid ("d92e428c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLifelineBody.");
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

    @objid ("d92e4292-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLifelineBody.", GmLifelineBody.MINOR_VERSION);
        
    }

    @objid ("d92e4298-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d92e429d-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
