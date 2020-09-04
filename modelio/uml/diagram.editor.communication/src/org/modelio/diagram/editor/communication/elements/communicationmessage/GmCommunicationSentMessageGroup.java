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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInfoFlowsGroup;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Group of communication messages.
 */
@objid ("7a4cb5a0-55b6-11e2-877f-002564c97630")
public final class GmCommunicationSentMessageGroup extends GmGroup {
    @objid ("7a4cb5a4-55b6-11e2-877f-002564c97630")
    private CommunicationChannel relatedEl;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a4cb5a7-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("7a4cb5aa-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates a group.
     * @param diagram The diagram.
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("7a4cb5ac-55b6-11e2-877f-002564c97630")
    public GmCommunicationSentMessageGroup(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.relatedEl = (CommunicationChannel) resolveRef(relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("7a4cb5b7-55b6-11e2-877f-002564c97630")
    public GmCommunicationSentMessageGroup() {
    }

    @objid ("7a4cb5ba-55b6-11e2-877f-002564c97630")
    @Override
    public final boolean canContain(final Class<? extends GmNodeModel> nodeClass) {
        return GmCommunicationMessageLabel.class.isAssignableFrom(nodeClass);
    }

    @objid ("7a4e3c1b-55b6-11e2-877f-002564c97630")
    @Override
    public final boolean canCreate(final Class<? extends MObject> type) {
        return CommunicationMessage.class.isAssignableFrom(type);
    }

    @objid ("7a4e3c24-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.relatedEl;
    }

    @objid ("7a4e3c2b-55b6-11e2-877f-002564c97630")
    @Override
    public final boolean isVisible() {
        return true;
    }

    @objid ("7a4e3c30-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        refreshFromObModel();
    }

    @objid ("7a4e3c33-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCommunicationSentMessageGroup.");
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

    @objid ("7a4e3c3a-55b6-11e2-877f-002564c97630")
    @Override
    public final void refreshFromObModel() {
        super.refreshFromObModel();
        
        final MObject related = getRelatedElement();
        if (related != null && related.isValid()) {
            List<GmNodeModel> oldChildren = getChildren();
            for (CommunicationMessage part : this.relatedEl.getStartToEndMessage()) {
                final MRef msgRef = new MRef(part);
                GmNodeModel gmMessageLabel = getNode(oldChildren, GmCommunicationMessageLabel.class, msgRef);
                if (gmMessageLabel == null)
                    gmMessageLabel = getDiagram().unmask(this, part, null);
        
                GmInfoFlowsGroup flowGroup = (GmInfoFlowsGroup) getNode(oldChildren,
                        GmInfoFlowsGroup.class,
                        msgRef);
                if (part.getRealizedInformationFlow().size() > 0) {
                    // Create the info flow group if missing
                    if (flowGroup == null) {
                        flowGroup = new GmInfoFlowsGroup(getDiagram(), msgRef);
                        addChild(flowGroup, getChildIndex(gmMessageLabel) + 1);
                    }
                } else if (flowGroup != null) {
                    // Delete the unused group
                    flowGroup.delete();
                }
            }
        
        }
    }

    @objid ("7a4e3c3d-55b6-11e2-877f-002564c97630")
    @Override
    public final void styleChanged(final IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("7a4e3c44-55b6-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean visible) {
        // Nothing to do
    }

    @objid ("7a4e3c49-55b6-11e2-877f-002564c97630")
    @Override
    protected final boolean isValidElement(final MObject el) {
        // Cannot unmask anything else than a valid ICommunicationMessage
        if (!(el instanceof CommunicationMessage) || !el.isValid())
            return false;
        return (this.relatedEl.getStartToEndMessage().contains(el));
    }

    @objid ("7a4e3c52-55b6-11e2-877f-002564c97630")
    @Override
    protected final void updateHiddenFeatures() {
        setHiddenFeature(false);
    }

    @objid ("7a4e3c55-55b6-11e2-877f-002564c97630")
    private GmNodeModel getNode(final List<GmNodeModel> oldChildren, final Class<?> cl, final MRef relatedRef) {
        for (GmNodeModel c : oldChildren) {
            if (cl.isInstance(c) && c.getRepresentedRef().equals(relatedRef))
                return c;
        }
        return null;
    }

    @objid ("7a4fc2c9-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCommunicationSentMessageGroup.", GmCommunicationSentMessageGroup.MINOR_VERSION);
    }

    @objid ("7a4fc2cf-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.relatedEl = (CommunicationChannel) resolveRef((MRef) in.readProperty("relatedRef"));
    }

    @objid ("7a4fc2d5-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
