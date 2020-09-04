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

package org.modelio.diagram.editor.sequence.elements.executionoccurencespecification;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.vcore.session.api.transactions.TransactionException;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic Model class for ExecutionOccurenceSpecification.
 * 
 * @author fpoyer
 */
@objid ("d8dbdcf9-55b6-11e2-877f-002564c97630")
public class GmExecutionOccurenceSpecification extends GmSimpleNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d8dd635c-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d8dd635f-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("09b75a28-81cc-4da0-87c9-f4f532363d6d")
    private static GmExecutionOccurenceSpecificationStyleKeys KEYS = new GmExecutionOccurenceSpecificationStyleKeys();

    @objid ("9d51cece-750c-4183-bb25-8c43392922bd")
    private ExecutionOccurenceSpecification executionOccurenceSpecification;

    @objid ("d8dd6361-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        // Only ever represented in structured mode.
        return RepresentationMode.STRUCTURED;
    }

    @objid ("d8dd6368-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromObModel() {
        if (this.executionOccurenceSpecification != null && this.executionOccurenceSpecification.isValid()) {
            // if a message is sent or received by this execution occurrence specification, unmask it.
            Message sentMessage = this.executionOccurenceSpecification.getSentMessage();
            if (sentMessage != null) {
                MRef messageRef = new MRef(sentMessage);
                boolean found = false;
                for (IGmLink link : getStartingLinks()) {
                    if (link.getRepresentedRef().equals(messageRef)) {
                        found = true;
                        break;
                    }
                }
                if (!found && getDiagram().getAllGMRepresenting(new MRef(sentMessage)).isEmpty()) {
                    GmLink link = (GmLink) getDiagram().unmaskLink(sentMessage);
                    this.addStartingLink(link);
                    link.obElementsUpdated();
                    if (link.getTo() == null) {
                        // If the link couldn't get its target, delete it for the moment.
                        link.delete();
                    }
                }
            }
        
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, this.getLayoutData(), null);
        
            // Clean up orphan blue squares: should be done by the delete command itself, but having it here also 'migrates' old sequence diagrams...
            if (this.executionOccurenceSpecification.getFinished() == null && this.executionOccurenceSpecification.getStarted() == null && this.executionOccurenceSpecification.getSentMessage() == null
                    && this.executionOccurenceSpecification.getReceivedMessage() == null) {
                if (this.executionOccurenceSpecification.isModifiable()) {
                    try {
                        this.executionOccurenceSpecification.delete();
                    } catch (@SuppressWarnings ("unused") TransactionException e) {
                        // Ignore error happening during load, the delete will be done at next refresh
                    }
                }
            }
        } else {
            // Ghost ExecutionOccurenceSpecification should not be kept
            delete();
        }
    }

    @objid ("d8dd636b-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return GmExecutionOccurenceSpecification.KEYS.getStyleKey(metakey);
    }

    @objid ("d8dd6376-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmExecutionOccurenceSpecification.KEYS.getStyleKeys();
    }

    @objid ("d8dd637f-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.executionOccurenceSpecification;
    }

    @objid ("d8dd6386-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.executionOccurenceSpecification;
    }

    @objid ("d8dd638d-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmExecutionOccurenceSpecification.");
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

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d8dd6394-55b6-11e2-877f-002564c97630")
    public GmExecutionOccurenceSpecification() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram this node is created in.
     * @param executionOccurenceSpecification the represented ExecutionOccurenceSpecification. May be <code>null</code>.
     * @param relatedRef a reference to the represented ExecutionOccurenceSpecification. May NOT be <code>null</code>.
     */
    @objid ("d8dd6397-55b6-11e2-877f-002564c97630")
    public GmExecutionOccurenceSpecification(final IGmDiagram diagram, final ExecutionOccurenceSpecification executionOccurenceSpecification, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.executionOccurenceSpecification = executionOccurenceSpecification;
    }

    @objid ("d8deea05-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExecutionOccurenceSpecification.", GmExecutionOccurenceSpecification.MINOR_VERSION);
    }

    @objid ("d8deea0b-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.executionOccurenceSpecification = (ExecutionOccurenceSpecification) resolveRef(getRepresentedRef());
    }

    @objid ("d8deea11-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmExecutionOccurenceSpecification.MAJOR_VERSION;
    }

}
