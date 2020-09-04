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

package org.modelio.diagram.editor.activity.elements.expansionregion;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.activity.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Structuredactivity.
 */
@objid ("2a62f21e-55b6-11e2-877f-002564c97630")
public class GmExpansionRegionPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("2a62f228-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("2a62f22b-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("2a62f224-55b6-11e2-877f-002564c97630")
    private GmActivityNodeHeader header;

    @objid ("d084cea9-55c0-11e2-9337-002564c97630")
    private GmBodyFreeZone innerZone;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef reference to the represented element.
     */
    @objid ("2a62f22d-55b6-11e2-877f-002564c97630")
    public GmExpansionRegionPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.header = new GmActivityNodeHeader(diagram, relatedRef);
        this.innerZone = new GmBodyFreeZone(diagram, relatedRef);
        
        super.addChild(this.header);
        super.addChild(this.innerZone);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("2a62f236-55b6-11e2-877f-002564c97630")
    public GmExpansionRegionPrimaryNode() {
        // constructor empty for the serialization
    }

    @objid ("2a647899-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        if (child.getRelatedElement() instanceof ActivityNode &&
                ((ActivityNode) child.getRelatedElement()).getCompositionOwner()
                        .equals(this.getRelatedElement())) {
            this.innerZone.addChild(child);
        } else
            super.addChild(child);
    }

    @objid ("2a64789f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ActivityNode.class.isAssignableFrom(type) &&
                        !ExpansionNode.class.isAssignableFrom(type) &&
                        !Pin.class.isAssignableFrom(type) &&
                        !ActivityParameterNode.class.isAssignableFrom(type);
    }

    @objid ("2a6478a7-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        Class<? extends MObject> type = el.getClass();
        return ActivityNode.class.isAssignableFrom(type) &&
                        !ExpansionNode.class.isAssignableFrom(type) &&
                        !Pin.class.isAssignableFrom(type) &&
                        !ActivityParameterNode.class.isAssignableFrom(type) &&
                        this.getRelatedElement() != null &&
                        this.getRelatedElement().equals(el.getCompositionOwner());
    }

    @objid ("2a6478af-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return this.innerZone;
    }

    /**
     * Get the stereotype image to display.
     * 
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("2a6478b9-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * @return the structured inner zone.
     */
    @objid ("2a6478bf-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getInnerZone() {
        return this.innerZone;
    }

    @objid ("2a6478c6-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmExpansionRegion.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("2a6478cd-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmExpansionRegionPrimaryNode.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("2a6478d3-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("2a6478d6-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case IMAGE: {
            ret = Collections.emptyList();
            break;
        }
        default: {
            ret = super.getVisibleChildren();
            break;
        }
        }
        return ret;
    }

    @objid ("2a65ff3d-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExpansionRegionPrimaryNode.", GmExpansionRegionPrimaryNode.MINOR_VERSION);
    }

    @objid ("2a65ff43-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.innerZone = (GmBodyFreeZone) this.getChildren().get(1);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(2);
        imageModeHeader.delete();
    }

    @objid ("2a65ff48-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("2a65ff4d-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.innerZone = (GmBodyFreeZone) this.getChildren().get(1);
    }

}
