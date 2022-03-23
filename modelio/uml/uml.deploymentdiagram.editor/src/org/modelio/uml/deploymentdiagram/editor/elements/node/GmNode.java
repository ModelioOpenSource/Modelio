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
package org.modelio.uml.deploymentdiagram.editor.elements.node;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link INode}.
 */
@objid ("97375bb8-55b6-11e2-877f-002564c97630")
public class GmNode extends GmPortContainer {
    @objid ("97375bc2-55b6-11e2-877f-002564c97630")
    private Node element;

    /**
     * Current version of this Gm.
     */
    @objid ("97375bc5-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("97375bc8-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("97375bca-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("438a7209-5beb-11e2-9e33-00137282c51b")
    static final GmNodeImageStyleKeys IMAGE_KEYS = new GmNodeImageStyleKeys();

    @objid ("438a720b-5beb-11e2-9e33-00137282c51b")
    static final GmNodeSimpleStyleKeys SIMPLE_KEYS = new GmNodeSimpleStyleKeys();

    @objid ("438a720d-5beb-11e2-9e33-00137282c51b")
    static final GmNodeStructuredStyleKeys STRUCTURED_KEYS = new GmNodeStructuredStyleKeys();

    @objid ("463fa838-e1c9-4749-98ee-295cd3cca67a")
    static final GmNodeUserImageStyleKeys USERIMAGE_KEYS = new GmNodeUserImageStyleKeys();

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("97375bcc-55b6-11e2-877f-002564c97630")
    public  GmNode() {
        // Nothing specific to do.
    }

    /**
     * Constructor.
     * @param diagram the diagram in which the class is unmasked.
     * @param el the unmasked class.
     * @param ref a reference to the unmasked class.
     */
    @objid ("97375bcf-55b6-11e2-877f-002564c97630")
    public  GmNode(IGmDiagram diagram, Node el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmNodePrimaryNode mainNode = new GmNodePrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmNode.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("9738e23c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("9738e244-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("9738e24c-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("9738e253-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmNode.STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("9738e25d-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmNode.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmNode.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmNode.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmNode.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    @objid ("9738e265-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmNode.");
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

    @objid ("9738e26b-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    /**
     * Automatically unmask ports if asked.
     */
    @objid ("9738e272-55b6-11e2-877f-002564c97630")
    private void refreshPortsFromObModel() {
        if (arePortsAutoDisplayed() && this.element != null && this.element.isValid()) {
            for (BindableInstance part : this.element.getInternalStructure()) {
                if (part instanceof Port && getChild(new MRef(part)) == null) {
                    GmNodeModel gmPort = getDiagram().unmask(this, part, Border.East);
                    gmPort.setRoleInComposition(GmPortContainer.PORT_ROLE);
                }
            }
        
        }
        
    }

    @objid ("9738e275-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        refreshPortsFromObModel();
        
    }

    /**
     * @return true if ports are to be unmasked automatically.
     */
    @objid ("9738e278-55b6-11e2-877f-002564c97630")
    protected Boolean arePortsAutoDisplayed() {
        return getDisplayedStyle().getProperty(GmNodeStructuredStyleKeys.SHOWPORTS);
    }

    @objid ("973a68d9-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmNode.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
            case USER_IMAGE:
            case IMAGE:
            default: {
                break;
            }
        
            }
        }
        return ret;
    }

    @objid ("973a68e2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmNode.", GmNode.MINOR_VERSION);
        
    }

    @objid ("973a68e8-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Node) resolveRef(getRepresentedRef());
        
        // Fix role on the header...
        for (GmNodeModel children : getChildren()) {
            if (children instanceof GmDefaultModelElementLabel) {
                children.setRoleInComposition(GmNode.IMAGE_LABEL_ROLE);
            }
        }
        
    }

    @objid ("973a68ed-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmNode.MAJOR_VERSION;
    }

    @objid ("973a68f2-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (Node) resolveRef(getRepresentedRef());
        
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("973a68f8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmNode.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("973a690a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("973a6914-55b6-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
        
    }

    @objid ("973bef7c-55b6-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
        
    }

    @objid ("71a9b7d4-3761-49d0-997d-d09f567a82f5")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmNode.IMAGE_LABEL_ROLE);
    }

    @objid ("e93cf34e-9388-4d9e-a04a-296cc8c4941b")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return NodeSymbolViewModel.create(getPersistedStyle(), this);
    }

}
