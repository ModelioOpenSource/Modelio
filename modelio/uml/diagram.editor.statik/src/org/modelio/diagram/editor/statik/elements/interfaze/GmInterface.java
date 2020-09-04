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

package org.modelio.diagram.editor.statik.elements.interfaze;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.statik.elements.imagenamespacelabel.GmImageNameSpaceLabel;
import org.modelio.diagram.editor.statik.elements.templatecontainer.GmTemplateContainer;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link Interface}.
 * 
 * @author fpoyer
 */
@objid ("35728f5a-55b7-11e2-877f-002564c97630")
public class GmInterface extends GmTemplateContainer {
    @objid ("35728f68-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35728f65-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5ea55e56-5bd5-11e2-9e33-00137282c51b")
    private static final InterfaceImageStyleKeys IMAGE_KEYS = new InterfaceImageStyleKeys();

    @objid ("5ea2fbff-5bd5-11e2-9e33-00137282c51b")
    private static final InterfaceSimpleStyleKeys SIMPLE_KEYS = new InterfaceSimpleStyleKeys();

    @objid ("5ea2fbfd-5bd5-11e2-9e33-00137282c51b")
     static final InterfaceStructuredStyleKeys STRUCTURED_KEYS = new InterfaceStructuredStyleKeys();

    @objid ("8bfa4bf8-19d6-418c-bfc2-078906775eb1")
    private static final InterfaceUserImageStyleKeys USERIMAGE_KEYS = new InterfaceUserImageStyleKeys();

    @objid ("5ac2b02b-73f1-48fa-b642-5dea118df0ec")
    private Interface element;

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("35728f6a-55b7-11e2-877f-002564c97630")
    public GmInterface() {
        // Nothing specific to do.
    }

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the class is unmasked.
     * @param el the unmasked class.
     * @param ref a reference to the unmasked class.
     */
    @objid ("357415f9-55b7-11e2-877f-002564c97630")
    public GmInterface(IGmDiagram diagram, Interface el, MRef ref) {
        super(diagram, new GmInterfacePrimaryNode(diagram, ref), ref);
        
        this.element = el;
        
        final GmImageNameSpaceLabel interfaceLabel = new GmImageNameSpaceLabel(diagram,
                el,
                ref);
        interfaceLabel.setRoleInComposition(SATELLITE_ROLE);
        interfaceLabel.setLayoutData(Integer.valueOf(PositionConstants.EAST));
        
        addChild(interfaceLabel);
    }

    @objid ("35741605-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("3574160d-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("35759cb3-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("35741634-55b7-11e2-877f-002564c97630")
    @Override
    public Interface getRelatedElement() {
        return this.element;
    }

    @objid ("35741615-55b7-11e2-877f-002564c97630")
    @Override
    public Interface getRepresentedElement() {
        return this.element;
    }

    @objid ("3574161c-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("35741626-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch ((RepresentationMode) getDisplayedStyle().getProperty(GmInterface.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE))) {
        case IMAGE:
            return IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("3574163b-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        switch ((RepresentationMode) getDisplayedStyle().getProperty(GmInterface.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE))) {
        case SIMPLE:
        case IMAGE:
        case USER_IMAGE:
            return super.getVisibleChildren();
        case STRUCTURED:
        default:
            List<GmNodeModel> liste = super.getVisibleChildren();
            liste.removeAll(getChildren(SATELLITE_ROLE));
            return liste;
        }
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("35759cc0-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("35759cca-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition()) ||
                                        GmPortContainer.CONTENT_AS_SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("3574162e-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInterface.");
        switch (readVersion) {
        case 0: 
            read_0(in);
            break;
        
        default: 
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        
        }
    }

    @objid ("35759c9f-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        refreshPortsFromObModel();
    }

    @objid ("35759ca8-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInterface.", MINOR_VERSION);
    }

    /**
     * @return true if ports are to be unmasked automatically.
     */
    @objid ("35759ca2-55b7-11e2-877f-002564c97630")
    protected Boolean arePortsAutoDisplayed() {
        return getDisplayedStyle().getProperty(InterfaceStructuredStyleKeys.SHOWPORTS);
    }

    @objid ("35759cae-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Interface) resolveRef(getRepresentedRef());
    }

    /**
     * Automatically unmask ports if asked.
     */
    @objid ("35759c9c-55b7-11e2-877f-002564c97630")
    private void refreshPortsFromObModel() {
        final Interface node = getRepresentedElement();
        if (arePortsAutoDisplayed() && node != null && node.isValid()) {
            for (BindableInstance part : node.getInternalStructure()) {
                if (part instanceof Port && getChild(new MRef(part)) == null) {
                    GmNodeModel gmPort = getDiagram().unmask(this, part, Border.East);
                    gmPort.setRoleInComposition(GmPortContainer.PORT_ROLE);
                }
            }
        
        }
    }

    @objid ("b4750d59-4239-4e16-8328-c6ac21e96f63")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return SATELLITE_ROLE.equals(role);
    }

    @objid ("9cb26322-b8e8-4690-ae64-e350b8e59bcd")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return InterfaceSymbolViewModelProvider.create(getPersistedStyle(), this);
    }

}
