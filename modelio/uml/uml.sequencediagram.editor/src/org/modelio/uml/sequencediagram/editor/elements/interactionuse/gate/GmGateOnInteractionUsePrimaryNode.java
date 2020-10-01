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

package org.modelio.uml.sequencediagram.editor.elements.interactionuse.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Gm for the primary node of a Gate on InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d918e5ca-55b6-11e2-877f-002564c97630")
public class GmGateOnInteractionUsePrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d918e5d0-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d918e5d3-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d918e5d5-55b6-11e2-877f-002564c97630")
    public GmGateOnInteractionUsePrimaryNode() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram diagram in which this gm is created.
     * @param relatedRef a ref of the gate.
     */
    @objid ("d918e5d8-55b6-11e2-877f-002564c97630")
    public GmGateOnInteractionUsePrimaryNode(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("d918e5e3-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmGateOnInteractionUse.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("d918e5ea-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromObModel() {
        Gate gate = (Gate) getRelatedElement();
        if (gate != null && gate.isValid()) {
            // if a message is sent or received by this execution occurrence specification, unmask it.
            Message sentMessage = gate.getSentMessage();
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
        }
    }

    @objid ("d918e5ed-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("d918e5f1-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmGateOnInteractionUsePrimaryNode.");
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

    @objid ("d918e5f7-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmGateOnInteractionUsePrimaryNode.", GmGateOnInteractionUsePrimaryNode.MINOR_VERSION);
    }

    @objid ("d91a6c5e-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d91a6c63-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmGateOnInteractionUsePrimaryNode.MAJOR_VERSION;
    }

}
