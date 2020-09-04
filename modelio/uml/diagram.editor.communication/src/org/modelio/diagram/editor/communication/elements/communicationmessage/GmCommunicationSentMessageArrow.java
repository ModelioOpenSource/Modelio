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

package org.modelio.diagram.editor.communication.elements.communicationmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the communication message arrow.
 * <p>
 * The arrow is visible if there are send messages.
 */
@objid ("7a49a872-55b6-11e2-877f-002564c97630")
public final class GmCommunicationSentMessageArrow extends GmNoStyleSimpleNode {
    @objid ("7a49a876-55b6-11e2-877f-002564c97630")
    private CommunicationChannel relatedEl;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a49a879-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("7a49a87c-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("7a4b2ed9-55b6-11e2-877f-002564c97630")
    public GmCommunicationSentMessageArrow() {
    }

    /**
     * Creates a group.
     * @param diagram The diagram.
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("7a4b2edc-55b6-11e2-877f-002564c97630")
    public GmCommunicationSentMessageArrow(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.relatedEl = (CommunicationChannel) resolveRef(relatedRef);
    }

    @objid ("7a4b2ee7-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.relatedEl;
    }

    @objid ("7a4b2eee-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    /**
     * The arrow is visible if information flows are activated and there are information flows to display.
     */
    @objid ("7a4b2ef5-55b6-11e2-877f-002564c97630")
    @Override
    public final boolean isVisible() {
        return this.relatedEl.isValid() && this.relatedEl.getStartToEndMessage().size() > 0;
    }

    @objid ("7a4b2efb-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(final MObject movedEl) {
        fireVisibilityChanged();
    }

    @objid ("7a4b2f02-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        fireVisibilityChanged();
    }

    @objid ("7a4b2f05-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCommunicationSentMessageArrow.");
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

    @objid ("7a4b2f0c-55b6-11e2-877f-002564c97630")
    @Override
    public final void refreshFromObModel() {
        fireVisibilityChanged();
    }

    @objid ("7a4b2f0f-55b6-11e2-877f-002564c97630")
    @Override
    public final void styleChanged(final IStyle style) {
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("7a4b2f16-55b6-11e2-877f-002564c97630")
    @Override
    public final void styleChanged(final StyleKey property, final Object newValue) {
        super.styleChanged(property, newValue);
    }

    @objid ("7a4cb57f-55b6-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean visible) {
        // Nothing to do
    }

    @objid ("7a4cb584-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCommunicationSentMessageArrow.", GmCommunicationSentMessageArrow.MINOR_VERSION);
    }

    @objid ("7a4cb58a-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.relatedEl = (CommunicationChannel) resolveRef((MRef) in.readProperty("relatedRef"));
    }

    @objid ("7a4cb590-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
