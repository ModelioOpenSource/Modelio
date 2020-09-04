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

package org.modelio.diagram.editor.state.elements.connectionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the primary node Gm of a {@link ConnectionPointReference}
 */
@objid ("f4ff3b3f-55b6-11e2-877f-002564c97630")
public class GmConnectionPointPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f4ff3b45-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f4ff3b48-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initialize a connection point
     * 
     * @param diagram The diagram
     * @param ref The represented element reference, must not be null.
     */
    @objid ("f4ff3b4a-55b6-11e2-877f-002564c97630")
    public GmConnectionPointPrimaryNode(IGmDiagram diagram, MRef ref) {
        super(diagram, ref);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("f4ff3b53-55b6-11e2-877f-002564c97630")
    public GmConnectionPointPrimaryNode() {
        // Empty constructor needed for deserialisation.
    }

    @objid ("f4ff3b56-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("f4ff3b5e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("f4ff3b66-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("f4ff3b6b-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmConnectionPoint.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("f4ff3b72-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Force visual refresh of the reference figure.
        if (this.getRelatedElement() != null) {
            firePropertyChange(PROPERTY_LAYOUTDATA, null, this.getLayoutData());
        }
        // forcing visual refresh in case Image changed 
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("f4ff3b75-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmConnectionPointPrimaryNode.");
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

    @objid ("f4ff3b7b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmConnectionPointPrimaryNode.", GmConnectionPointPrimaryNode.MINOR_VERSION);
    }

    @objid ("f500c1dc-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("f500c1e1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
