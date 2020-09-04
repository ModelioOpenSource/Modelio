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

package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.core.view.LegacyStyleKeyProviderSymbolViewModel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Try 2 of Base class for all port containers.
 * 
 * @author fpoyer
 */
@objid ("7ee86a52-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmPortContainer extends GmCompositeNode {
    /**
     * The Main Node of the assembly. this role should probably be used on only one node of an assembly at any time.
     */
    @objid ("8f477a66-1e83-11e2-8cad-001ec947c8cc")
    protected static final String MAIN_NODE_ROLE = "MainNode";

    /**
     * Node main satellite Label.
     * <p>
     * usually designates the node floating label that displays the represented element name. At most one child node should have this role.
     */
    @objid ("12a18054-cee9-494d-a3ba-3d6b95d2ea61")
    protected static final String MAIN_SATELLITE_LABEL_ROLE = "Satellite main label";

    @objid ("7ee86a57-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * A Port which position is defined relatively to the Main Node's bounds.
     */
    @objid ("8f477a6c-1e83-11e2-8cad-001ec947c8cc")
    protected static final String PORT_ROLE = "Port";

    /**
     * A Satellite which position is defined relatively to the Main Node's bounds.
     */
    @objid ("8f477a72-1e83-11e2-8cad-001ec947c8cc")
    protected static final String SATELLITE_ROLE = "Satellite";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7ee86a5f-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    /**
     * Empty constructor for deserialization.
     */
    @objid ("7ee86a69-1dec-11e2-8cad-001ec947c8cc")
    public GmPortContainer() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param diagram The diagram in which this port container will be unmasked.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("7ee86a6c-1dec-11e2-8cad-001ec947c8cc")
    public GmPortContainer(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Overridden so that any child added without a specific role is defined as a Port. Subclasses may override this method so that some particular children are defined as Satellites rather than Ports.
     */
    @objid ("7ee86a73-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addChild(GmNodeModel child) {
        // Default: all child are PORTS
        if (child.getRoleInComposition() == null || child.getRoleInComposition().equals("")) {
            child.setRoleInComposition(GmPortContainer.PORT_ROLE);
        } else if (child.getRoleInComposition().equals(GmPortContainer.MAIN_NODE_ROLE)) {
            // Main node should use the style of the container by default.
            child.getPersistedStyle().setCascadedStyle(getPersistedStyle());
        }
        super.addChild(child);
    }

    @objid ("7ee86a78-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canUnmask(MObject el) {
        return el.getCompositionOwner().equals(getRelatedElement()) && canCreate(el.getClass());
    }

    @objid ("7ee86a7e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        // Try delegating to main node.
        GmNodeModel mainNode = getMainNode();
        if (mainNode instanceof GmCompositeNode) {
            GmCompositeNode composite = ((GmCompositeNode) mainNode).getCompositeFor(metaclass);
            if (composite != null) {
                return composite;
            }
        }
        // else
        return null;
    }

    /**
     * Get the main node that is decorated with ports and satellites.
     * 
     * @return a GmNodeModel, can't be <code>null</code>.
     */
    @objid ("7eeacc9d-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    public <T extends GmNodeModel> T getMainNode() {
        return (T) getFirstChild(GmPortContainer.MAIN_NODE_ROLE);
    }

    /**
     * Get the port container main node representation mode.
     * <p>
     * If the container has no main node, returns {@link #getRepresentationMode()}.
     * 
     * @return the main node representation mode.
     */
    @objid ("c75f8ee0-56eb-45a9-a8fe-1bd4448e6874")
    public RepresentationMode getMainNodeRepresentationMode() {
        GmNodeModel n = getMainNode();
        if (n != null) {
            return n.getRepresentationMode();
        } else {
            return getRepresentationMode();
        }
    }

    @objid ("7eeacca0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmPortContainer.MAJOR_VERSION;
    }

    /**
     * A port container is always in structured mode.
     */
    @objid ("694b57d3-5905-4e20-a966-ec12deca30ea")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    /**
     * Tells whether the given child node is the main satellite label of this port container node.
     * <p>
     * The default implementation return the first satellite node. Sub classes are strongly encouraged to subclass this method when they may have more than one satellite node..
     * 
     * @param childNode the node to check.
     * @return the main satellite label of this node.
     */
    @objid ("8dbb52ed-5e2a-4c23-956e-167041f4eaad")
    public abstract boolean isMainSatelliteLabel(GmNodeModel childNode);

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * <p>
     * See {@link #defaultIsPort(GmNodeModel)} for a recommended implementation.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("7eeaccab-1dec-11e2-8cad-001ec947c8cc")
    public abstract boolean isPort(final GmNodeModel childNode);

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * <p>
     * See {@link #defaultIsSatellite(GmNodeModel)} for a recommended implementation.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("7eeaccb0-1dec-11e2-8cad-001ec947c8cc")
    public abstract boolean isSatellite(final GmNodeModel childNode);

    @objid ("7eeaccb5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPortContainer.");
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

    @objid ("7eeaccb9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPortContainer.", GmPortContainer.MINOR_VERSION);
    }

    /**
     * Tells whether the given child node is the main satellite label of this port container node.
     * <p>
     * The default implementation return the first satellite node. Sub classes are strongly encouraged to subclass this method when they may have more than one satellite node..
     * 
     * @param childNode the node to check.
     * @return the main satellite label of this node.
     */
    @objid ("0de2599e-7fa2-46e7-b00b-7672c9b5b20f")
    protected static boolean defaultIsMainSatelliteLabel(GmNodeModel childNode) {
        return childNode.getRoleInComposition().equals(GmPortContainer.MAIN_SATELLITE_LABEL_ROLE);
    }

    /**
     * Default and recommended implementation for {@link #isPort(GmNodeModel)}
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("619729fa-328c-40e1-9a9d-cf61de169a50")
    protected static final boolean defaultIsPort(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return (GmPortContainer.PORT_ROLE.equals(role));
    }

    /**
     * Default implementation for {@link #isSatellite(GmNodeModel)}.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("a6e99434-0416-4491-bfee-32a3ab016e6c")
    protected static final boolean defaultIsSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return (GmPortContainer.MAIN_SATELLITE_LABEL_ROLE.equals(role) ||
                        GmPortContainer.SATELLITE_ROLE.equals(role) ||
                        GmCompositeNode.CONTENT_AS_SATELLITE_ROLE.equals(role));
    }

    @objid ("7eeaccbd-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    /**
     * This implementation is just here to avoid compilation errors until everybody has an implementation.
     * <p>
     * As port containers are always in STRUCTURED mode, checks the primary node's representation mode to display the proper StyleKeys.
     * </p>
     * <p>
     * To be deleted before Modelio 3.7 release.
     * </p>
     * @since 3.7
     */
    @objid ("79331697-6ac9-4708-bcd1-c041daa43379")
    @SuppressWarnings ("deprecation")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        AbstractStyleKeyProvider skProv = getLegacyStyleKeyProvider(getMainNodeRepresentationMode());
        if (skProv != null) {
            return new LegacyStyleKeyProviderSymbolViewModel(skProv, getDiagram().getPersistedStyle());
        } else if (getParent() != null) {
            return getParent().getSymbolViewModel();
        } else {
            return null;
        }
    }

}
