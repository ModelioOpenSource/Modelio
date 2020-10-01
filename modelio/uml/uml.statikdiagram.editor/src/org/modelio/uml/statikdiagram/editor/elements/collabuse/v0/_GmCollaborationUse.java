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

package org.modelio.uml.statikdiagram.editor.elements.collabuse.v0;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.CollaborationUseImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.CollaborationUseSimpleStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.CollaborationUseStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.CollaborationUseUserImageStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.collabuse.GmCollaborationUseHeader;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a CollaborationUse.
 */
@objid ("3484860a-55b7-11e2-877f-002564c97630")
public final class _GmCollaborationUse extends GmCompositeNode implements IImageableNode {
    @objid ("34848610-55b7-11e2-877f-002564c97630")
    private CollaborationUse collabUse;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34860c85-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34860c88-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("34860c7f-55b7-11e2-877f-002564c97630")
    private static final CollaborationUseSimpleStyleKeys SIMPLEKEYS = new CollaborationUseSimpleStyleKeys();

    @objid ("34860c81-55b7-11e2-877f-002564c97630")
    private static final CollaborationUseStructuredStyleKeys STRUCTKEYS = new CollaborationUseStructuredStyleKeys();

    @objid ("34860c83-55b7-11e2-877f-002564c97630")
    private static final CollaborationUseImageStyleKeys IMAGEKEYS = new CollaborationUseImageStyleKeys();

    /**
     * Header
     */
    @objid ("a6328a31-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    @objid ("d1d5bd7a-98fb-4385-bcde-0756f6db2ff6")
    private static final CollaborationUseUserImageStyleKeys USERIMAGE_KEYS = new CollaborationUseUserImageStyleKeys();

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theCollabCase the represented object node, may be null.
     * @param ref a reference to the represented object node.
     */
    @objid ("34860c8a-55b7-11e2-877f-002564c97630")
    public _GmCollaborationUse(IGmDiagram diagram, final CollaborationUse theCollabCase, MRef ref) {
        super(diagram, ref);
        this.collabUse = theCollabCase;
        this.header = new GmCollaborationUseHeader(diagram, ref);
        
        super.addChild(this.header);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("34860c97-55b7-11e2-877f-002564c97630")
    public _GmCollaborationUse() {
        // empty constructor for the serialization
    }

    @objid ("34860c9a-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("34860ca2-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("34860caa-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return null;
    }

    /**
     * Get the stereotype image to display.
     * 
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("34860cb4-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRepresentedElement());
    }

    @objid ("3487931e-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRelatedElement() {
        return this.collabUse;
    }

    @objid ("34879325-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("3487932c-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRepresentedElement() {
        return this.collabUse;
    }

    @objid ("34879333-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = STRUCTKEYS.getStyleKey(metakey);
        if (ret != null)
            return ret;
        
        ret = SIMPLEKEYS.getStyleKey(metakey);
        if (ret != null)
            return ret;
        
        ret = IMAGEKEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("3487933d-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        RepresentationMode mode = this.getRepresentationMode();
        switch (mode) {
            case SIMPLE:
                return SIMPLEKEYS.getStyleKeys();
            case STRUCTURED:
                return STRUCTKEYS.getStyleKeys();
            case IMAGE:
                return IMAGEKEYS.getStyleKeys();
            default:
                return Collections.emptyList();
        }
    }

    @objid ("34879345-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollaborationUse.");
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

    @objid ("3487934b-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed 
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("3487934e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCollaborationUse.", _GmCollaborationUse.MINOR_VERSION);
    }

    @objid ("34879354-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
                
        this.collabUse = (CollaborationUse) resolveRef(getRepresentedRef());
                
        final List<GmNodeModel> children = getChildren();
                
        this.header = (GmModelElementHeader) children.get(0);
    }

    @objid ("348919bc-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("348919c1-55b7-11e2-877f-002564c97630")
    public GmModelElementHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

}
