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
package org.modelio.uml.deploymentdiagram.editor.elements.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link Artifact}.
 */
@objid ("9712bcbd-55b6-11e2-877f-002564c97630")
public class GmArtifact extends GmPortContainer {
    @objid ("9712bcc7-55b6-11e2-877f-002564c97630")
    private Artifact element;

    /**
     * Current version of this Gm.
     */
    @objid ("9712bcca-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("9712bccd-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("9712bccf-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("9712bcc1-55b6-11e2-877f-002564c97630")
    static final GmArtifactImageStyleKeys IMAGE_KEYS = new GmArtifactImageStyleKeys();

    @objid ("9712bcc3-55b6-11e2-877f-002564c97630")
    static final GmArtifactSimpleStyleKeys SIMPLE_KEYS = new GmArtifactSimpleStyleKeys();

    @objid ("9712bcc5-55b6-11e2-877f-002564c97630")
    static final GmArtifactStructuredStyleKeys STRUCTURED_KEYS = new GmArtifactStructuredStyleKeys();

    @objid ("683ea44b-853a-4a7e-b3e0-0646aaaec4f9")
    static final GmArtifactUserImageStyleKeys USERIMAGE_KEYS = new GmArtifactUserImageStyleKeys();

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("9712bcd1-55b6-11e2-877f-002564c97630")
    public  GmArtifact() {
        // Nothing specific to do.
    }

    /**
     * Constructor.
     * @param diagram the diagram in which the class is unmasked.
     * @param el the unmasked class.
     * @param ref a reference to the unmasked class.
     */
    @objid ("9712bcd4-55b6-11e2-877f-002564c97630")
    public  GmArtifact(IGmDiagram diagram, Artifact el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmArtifactPrimaryNode mainNode = new GmArtifactPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmArtifact.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("9712bce0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("9714433a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("97144342-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("97144349-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmArtifact.STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("97144353-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmArtifact.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmArtifact.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmArtifact.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmArtifact.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    @objid ("9714435b-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmArtifact.");
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

    @objid ("97144361-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    /**
     * Automatically unmask ports if asked.
     */
    @objid ("97144368-55b6-11e2-877f-002564c97630")
    private void refreshPortsFromObModel() {
        final Artifact node = (Artifact) getRepresentedElement();
        if (node != null && node.isValid() && arePortsAutoDisplayed()) {
            for (BindableInstance part : node.getInternalStructure()) {
                if (part instanceof Port && getChild(new MRef(part)) == null) {
                    GmNodeModel gmPort = getDiagram().unmask(this, part, Border.East);
                    gmPort.setRoleInComposition(GmPortContainer.PORT_ROLE);
                }
            }
        
        }
        
    }

    @objid ("9714436b-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        refreshPortsFromObModel();
        
    }

    /**
     * @return true if ports are to be unmasked automatically.
     */
    @objid ("9714436e-55b6-11e2-877f-002564c97630")
    protected Boolean arePortsAutoDisplayed() {
        return getDisplayedStyle().getProperty(GmArtifactStructuredStyleKeys.SHOWPORTS);
    }

    @objid ("97144374-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmArtifact.", GmArtifact.MINOR_VERSION);
        
    }

    @objid ("9715c9dc-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Artifact) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmArtifact.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("9715c9e1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmArtifact.MAJOR_VERSION;
    }

    @objid ("9715c9e6-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (Artifact) resolveRef(getRepresentedRef());
        
    }

    @objid ("9715c9ec-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmArtifact.IMAGE_LABEL_ROLE);
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

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("9715c9f5-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmArtifact.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("9715ca07-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("9715ca11-55b6-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
        
    }

    @objid ("97175079-55b6-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
        
    }

    @objid ("dd5cf1ef-3a71-4234-937f-9893e44fb1c8")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmArtifact.IMAGE_LABEL_ROLE);
    }

    @objid ("c80930c8-f0dd-4155-b500-1fca3ff02383")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return ArtifactSymbolViewModel.create(getPersistedStyle(), this);
    }

}
