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

package org.modelio.diagram.editor.activity.elements.activityfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Activityfinal.
 */
@objid ("29aa506e-55b6-11e2-877f-002564c97630")
public class GmActivityFinalPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29aa5074-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29aa5077-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initializes a simple node.
     * 
     * @param diagram The diagram owning the node.
     * @param relatedRef related element reference, must not be <code>null</code>.
     */
    @objid ("29aa5079-55b6-11e2-877f-002564c97630")
    public GmActivityFinalPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("29aa5082-55b6-11e2-877f-002564c97630")
    public GmActivityFinalPrimaryNode() {
        // for the serialization
    }

    @objid ("29aa5085-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("29aa508d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("29aa5095-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
                    @Override
                    public String getText() {
                        return getRelatedElement().getName();
                    }
        
                    @Override
                    public void setText(String text) {
                        getRelatedElement().setName(text);
                    }
                };
    }

    @objid ("29abd6fe-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityFinalNode getRelatedElement() {
        return (ActivityFinalNode) super.getRelatedElement();
    }

    @objid ("29abd705-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("29abd70a-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmActivityFinal.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("29abd711-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (getRelatedElement() != null) {
            firePropertyChange(PROPERTY_LABEL, null, getRelatedElement().getName());
        }
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("29abd714-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmActivityFinalPrimaryNode.");
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

    @objid ("29abd71a-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmActivityFinalPrimaryNode.", GmActivityFinalPrimaryNode.MINOR_VERSION);
    }

    @objid ("29abd720-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29abd725-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
