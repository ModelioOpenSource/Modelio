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

package org.modelio.diagram.editor.statik.elements.packaze;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.statik.elements.packaze.v0._GmPackage;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic representation for a {@link Package}.
 * 
 * @author cmarin
 */
@objid ("3618e1a1-55b7-11e2-877f-002564c97630")
public class GmPackage extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3618e1a8-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3618e1ab-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("3618e1b3-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_MODE_HEADER = "image mode header";

    @objid ("3618e1b5-55b7-11e2-877f-002564c97630")
     static final String BODY_CONTENT_AS_SATELLITE = GmPortContainer.CONTENT_AS_SATELLITE_ROLE;

    /**
     * Structured mode style keys.
     */
    @objid ("3618e1ad-55b7-11e2-877f-002564c97630")
    public static GmPackageStructuredStyleKeys STRUCTURED_KEYS = new GmPackageStructuredStyleKeys();

    /**
     * Simple mode style keys.
     */
    @objid ("3618e1af-55b7-11e2-877f-002564c97630")
    public static GmPackageSimpleStyleKeys SIMPLE_KEYS = new GmPackageSimpleStyleKeys();

    @objid ("a72e9d4d-55c2-11e2-9337-002564c97630")
    private Package element;

    /**
     * Image mode style keys.
     */
    @objid ("62426ebe-5bd5-11e2-9e33-00137282c51b")
    public static GmPackageImageStyleKeys IMAGE_KEYS = new GmPackageImageStyleKeys();

    /**
     * Image mode style keys.
     */
    @objid ("e014ebd6-dd0e-40c6-9542-58b86af61eec")
    public static GmPackageUserImageStyleKeys USERIMAGE_KEYS = new GmPackageUserImageStyleKeys();

    /**
     * Creates a GmPackage.
     * 
     * @param diagram The diagram.
     * @param thePackage The represented package, may be <tt>null</tt>
     * @param ref The represented package reference, may not be <tt>null</tt>.
     */
    @objid ("3618e1b7-55b7-11e2-877f-002564c97630")
    public GmPackage(IGmDiagram diagram, Package thePackage, MRef ref) {
        super(diagram, ref);
        this.element = thePackage;
        
        GmPackagePrimaryNode primary = new GmPackagePrimaryNode(diagram, ref);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        GmDefaultModelElementLabel  imageModeHeader = new GmDefaultModelElementLabel (diagram, ref);
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        addChild(imageModeHeader);
    }

    /**
     * For deserialization only.
     */
    @objid ("361a6819-55b7-11e2-877f-002564c97630")
    public GmPackage() {
    }

    @objid ("361a681c-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("361a6824-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("361a682c-55b7-11e2-877f-002564c97630")
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

    @objid ("361a6836-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
                case STRUCTURED:
                    return STRUCTURED_KEYS.getStyleKeys();
                case IMAGE:
                    return IMAGE_KEYS.getStyleKeys();
                case USER_IMAGE:
                    return USERIMAGE_KEYS.getStyleKeys();
                case SIMPLE:
                    return SIMPLE_KEYS.getStyleKeys();
                default:
                    return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("361a683e-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPackage.");
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

    @objid ("361a6844-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("361a684b-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("361a6852-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
                case STRUCTURED:
                case SIMPLE: {
                    GmNodeModel imageModeHeader = getFirstChild(IMAGE_MODE_HEADER);
                    ret.remove(imageModeHeader);
                    break;
                }
                case IMAGE:
                default: {
                    break;
                }
        
            }
        }
        return ret;
    }

    @objid ("361beebf-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPackage.", GmPackage.MINOR_VERSION);
    }

    @objid ("361beec5-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.element = (Package) resolveRef(getRepresentedRef());
    }

    @objid ("361beeca-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("361beed5-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return BODY_CONTENT_AS_SATELLITE.equals(childNode.getRoleInComposition()) || IMAGE_MODE_HEADER.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("361beedf-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return false;
    }

    @objid ("361beee9-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("361beef0-55b7-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
    }

    /**
     * Migration constructor from major version 0, should only be called by migrator.
     * 
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("361d7559-55b7-11e2-877f-002564c97630")
    GmPackage(final _GmPackage oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.element = (Package) oldVersionGm.getRelatedElement();
        
        GmPackagePrimaryNode primary = new GmPackagePrimaryNode(oldVersionGm);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        GmDefaultModelElementLabel  imageModeHeader = new GmDefaultModelElementLabel (oldVersionGm.getDiagram(),
                                                                      oldVersionGm.getRepresentedRef());
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        addChild(imageModeHeader);
    }

    @objid ("361d755e-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(final GmNodeModel node) {
        if (BODY_CONTENT_AS_SATELLITE.equals(node.getRoleInComposition())) {
            final MObject childEl = node.getRelatedElement();
            return childEl == null || (childEl.isValid() && ((GmPackagePrimaryNode) getMainNode()).getBody().canUnmask(childEl));
        }
        return true;
    }

    @objid ("361d7567-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(final GmNodeModel child) {
        // Children with no role are probably body content created as satellite in simple/image mode
        if ((child.getRoleInComposition() == null || child.getRoleInComposition().equals("")) &&
            getMainNode() != null &&
            getMainNode().getRepresentationMode() != RepresentationMode.STRUCTURED) {
            child.setRoleInComposition(BODY_CONTENT_AS_SATELLITE);
        }
        super.addChild(child);
    }

    @objid ("361d756e-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(final Class<? extends GmNodeModel> nodeClass) {
        return !GmElementLabel.class.isAssignableFrom(nodeClass);
    }

    @objid ("be98e36f-f4f1-4c7b-90e7-983883b613c3")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_MODE_HEADER);
    }

}
