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

package org.modelio.diagram.editor.state.elements.state;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a {@link State}.
 */
@objid ("f5827527-55b6-11e2-877f-002564c97630")
public class GmStatePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("f5827535-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("f5827538-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Internal transitions zone.
     */
    @objid ("f5827531-55b6-11e2-877f-002564c97630")
    private GmInternalTransitionsGroup internalTransitionsZone;

    /**
     * Regions zone.
     */
    @objid ("f5827533-55b6-11e2-877f-002564c97630")
    private GmRegionsGroup regionsGroup;

    /**
     * Header
     */
    @objid ("81927899-55c2-11e2-9337-002564c97630")
    private GmDefaultModelElementHeader header;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef a reference to the represented state.
     */
    @objid ("f582753a-55b6-11e2-877f-002564c97630")
    public GmStatePrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.header = new GmStateLabel(diagram, relatedRef);
        this.header.setRoleInComposition("header");
        addChild(this.header);
        
        this.internalTransitionsZone = new GmInternalTransitionsGroup(diagram, relatedRef);
        this.internalTransitionsZone.setRoleInComposition("transitions");
        addChild(this.internalTransitionsZone);
        
        this.regionsGroup = new GmRegionsGroup(diagram, relatedRef);
        this.regionsGroup.setRoleInComposition("regions");
        this.regionsGroup.setVertical(true);
        addChild(this.regionsGroup);
    }

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("f583fba1-55b6-11e2-877f-002564c97630")
    public GmStatePrimaryNode() {
        // empty
    }

    @objid ("f583fba4-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("f583fbac-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("f583fbb4-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (InternalTransition.class.isAssignableFrom(metaclass)) {
            return this.internalTransitionsZone;
        } else if (Region.class.isAssignableFrom(metaclass)) {
            return this.regionsGroup;
        } else if (StateVertex.class.isAssignableFrom(metaclass)) {
            // State diagram node : return the first unmasked region
            for (GmNodeModel c : this.regionsGroup.getChildren()) {
                if (c instanceof GmCompositeNode) {
                    final GmCompositeNode comp = (GmCompositeNode) c;
                    final GmCompositeNode ret = comp.getCompositeFor(metaclass);
                    if (ret != null)
                        return ret;
                }
            }
            return null;
        } else {
            return this;
        }
    }

    @objid ("f583fbbd-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("f583fbc2-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmState.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("f583fbc9-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmStatePrimaryNode.");
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

    @objid ("f583fbcf-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("f583fbd2-55b6-11e2-877f-002564c97630")
    GmRegionsGroup getRegionsGroup() {
        return this.regionsGroup;
    }

    @objid ("f583fbd6-55b6-11e2-877f-002564c97630")
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
            if (!getDisplayedStyle().getBoolean(GmStateStructuredStyleKeys.SHOWINNER)) {
                ret.remove(this.getRegionsGroup());
            }
            break;
        }
        }
        return ret;
    }

    @objid ("f585823a-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmStatePrimaryNode.", Integer.valueOf(GmStatePrimaryNode.MINOR_VERSION));
    }

    @objid ("f5858240-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.header = (GmDefaultModelElementHeader) this.getFirstChild("header");
        this.internalTransitionsZone = (GmInternalTransitionsGroup) getFirstChild("transitions");
        this.regionsGroup = (GmRegionsGroup) getFirstChild("regions");
        
        GmNodeModel imageModeHeader =  this.getChildren().get(3);
        imageModeHeader.delete();
    }

    @objid ("f5858245-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("f585824a-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmDefaultModelElementHeader) this.getFirstChild("header");
        this.internalTransitionsZone = (GmInternalTransitionsGroup) getFirstChild("transitions");
        this.regionsGroup = (GmRegionsGroup) getFirstChild("regions");
    }

    @objid ("f5858250-55b6-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle changedStyle) {
        super.styleChanged(changedStyle);
        firePropertyChange(PROPERTY_CHILDREN, null, getVisibleChildren());
    }

    @objid ("f5858256-55b6-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        super.styleChanged(property, newValue);
        firePropertyChange(PROPERTY_CHILDREN, null, getVisibleChildren());
    }

}
