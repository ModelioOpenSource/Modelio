/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.collab.v0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.statik.elements.collab.CollaborationImageStyleKeys;
import org.modelio.diagram.editor.statik.elements.collab.CollaborationSimpleStyleKeys;
import org.modelio.diagram.editor.statik.elements.collab.CollaborationStructuredStyleKeys;
import org.modelio.diagram.editor.statik.elements.collab.CollaborationUserImageStyleKeys;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClass;
import org.modelio.diagram.editor.statik.elements.internalstructure.GmInternalStructureGroup;
import org.modelio.diagram.editor.statik.elements.internalstructure.GmInternalStructureZone;
import org.modelio.diagram.editor.statik.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a {@link Collaboration}.
 */
@objid ("34678804-55b7-11e2-877f-002564c97630")
public class _GmCollaboration extends GmCompositeNode implements IImageableNode {
    @objid ("3467880a-55b7-11e2-877f-002564c97630")
    private Collaboration collaboration;

    /**
     * Current version of this Gm.
     */
    @objid ("34678822-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("34678825-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("34678829-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("3467882b-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL_GROUP = "InternalGroup";

    @objid ("3467882d-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL_ZONE = "InternalZone";

    @objid ("3467882f-55b7-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("34678831-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_HEADER = "ImageHeader";

    @objid ("34678811-55b7-11e2-877f-002564c97630")
    private static final CollaborationSimpleStyleKeys SIMPLEKEYS = new CollaborationSimpleStyleKeys();

    @objid ("34678813-55b7-11e2-877f-002564c97630")
    private static final CollaborationStructuredStyleKeys STRUCTKEYS = new CollaborationStructuredStyleKeys();

    @objid ("34678815-55b7-11e2-877f-002564c97630")
    private static final CollaborationImageStyleKeys IMAGEKEYS = new CollaborationImageStyleKeys();

    /**
     * Inner classes
     */
    @objid ("34678827-55b7-11e2-877f-002564c97630")
    private GmInnerClass innerElements;

    /**
     * Header
     */
    @objid ("a61a200f-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Internal structure zone
     */
    @objid ("a61a2011-55c2-11e2-9337-002564c97630")
    private GmFreeZone internalStructureZone;

    /**
     * Internal structure list group
     */
    @objid ("a61a2013-55c2-11e2-9337-002564c97630")
    private GmGroup internalStructureGroup;

    @objid ("a61a2015-55c2-11e2-9337-002564c97630")
    private GmDefaultModelElementLabel imageModeHeader;

    @objid ("071f06fb-6841-4902-bd8a-f203c616be08")
    private static final CollaborationUserImageStyleKeys USERIMAGE_KEYS = new CollaborationUserImageStyleKeys();

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param theCollaboration the represented object node, may be null.
     * @param ref a reference to the represented object node.
     */
    @objid ("34678833-55b7-11e2-877f-002564c97630")
    public _GmCollaboration(IGmDiagram diagram, final Collaboration theCollaboration, MRef ref) {
        super(diagram, ref);
        this.collaboration = theCollaboration;
        
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(HEADER);
        
        this.internalStructureGroup = new GmInternalStructureGroup(diagram, ref);
        this.internalStructureGroup.setRoleInComposition(INTERNAL_GROUP);
        
        this.internalStructureZone = new GmInternalStructureZone(diagram, ref);
        this.internalStructureZone.setRoleInComposition(INTERNAL_ZONE);
        
        this.innerElements = new GmInnerClass(diagram, ref);
        this.innerElements.setRoleInComposition(INNER);
        
        this.imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        this.imageModeHeader.setRoleInComposition(IMAGE_HEADER);
        
        super.addChild(this.header);
        super.addChild(this.internalStructureGroup);
        super.addChild(this.internalStructureZone);
        super.addChild(this.innerElements);
        super.addChild(this.imageModeHeader);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("34690ea0-55b7-11e2-877f-002564c97630")
    public _GmCollaboration() {
        // empty constructor for the serialization
    }

    @objid ("34690ea3-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("34690eab-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("34690eb3-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        
        if (InformationItem.class.isAssignableFrom(metaclass)) {
            // Namespaces are unmasked in the inner classes zones or group
            ret = getInnerElements();
        } else if ((Instance.class.isAssignableFrom(metaclass) && !(Port.class.isAssignableFrom(metaclass))) ||
                CollaborationUse.class.isAssignableFrom(metaclass)) {
            // Instances are unmasked in the internal structure zone or group
            ret = getInternalStructureGroup();
            if (!ret.isVisible())
                ret = getInternalStructureZone();
        }
        return ret;
    }

    /**
     * Get the stereotype image to display.
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("34690ebd-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRepresentedElement());
    }

    /**
     * @return the internalStructureGroup
     */
    @objid ("34690ec3-55b7-11e2-877f-002564c97630")
    public final GmGroup getInternalStructureGroup() {
        return this.internalStructureGroup;
    }

    /**
     * @return the internalStructureZone
     */
    @objid ("34690eca-55b7-11e2-877f-002564c97630")
    public final GmFreeZone getInternalStructureZone() {
        return this.internalStructureZone;
    }

    @objid ("34690ed1-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("34690ed8-55b7-11e2-877f-002564c97630")
    @Override
    public Collaboration getRepresentedElement() {
        return this.collaboration;
    }

    @objid ("346a953d-55b7-11e2-877f-002564c97630")
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

    @objid ("346a9547-55b7-11e2-877f-002564c97630")
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

    @objid ("346a954f-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollaboration.");
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

    @objid ("346a9555-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("346a9558-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("346a955f-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case IMAGE: {
            ret = new ArrayList<>(1);
            ret.add(this.imageModeHeader);
            break;
        }
        default: {
            ret = super.getVisibleChildren();
            // Remove the header used for image mode.
            ret.remove(this.imageModeHeader);
            break;
        }
        }
        return ret;
    }

    @objid ("346a9568-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCollaboration.", _GmCollaboration.MINOR_VERSION);
    }

    @objid ("346a956e-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.collaboration = (Collaboration) resolveRef(getRepresentedRef());
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.internalStructureGroup = (GmGroup) children.get(1);
        this.internalStructureZone = (GmFreeZone) children.get(2);
        GmGroup innerGroup = (GmGroup) children.get(3);
        GmFreeZone innerZone = (GmFreeZone) children.get(4);
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(5);
        
        // Migrate inner group/zone
        removeChild(innerGroup);
        removeChild(innerZone);
        
        this.innerElements = new GmInnerClass(getDiagram(), getRepresentedRef(), innerZone, innerGroup);
        addChild(this.innerElements, 3);
        
        // Add roles
        this.header.setRoleInComposition(HEADER);
        this.internalStructureGroup.setRoleInComposition(INTERNAL_GROUP);
        this.internalStructureZone.setRoleInComposition(INTERNAL_ZONE);
        this.innerElements.setRoleInComposition(INNER);
        this.imageModeHeader.setRoleInComposition(IMAGE_HEADER);
    }

    @objid ("346a9573-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("346a9578-55b7-11e2-877f-002564c97630")
    public GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    @objid ("346c1bd9-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.collaboration = (Collaboration) resolveRef(getRepresentedRef());
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        this.internalStructureGroup = (GmGroup) getFirstChild(INTERNAL_GROUP);
        this.internalStructureZone = (GmFreeZone) getFirstChild(INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(INNER);
        this.imageModeHeader = (GmDefaultModelElementLabel) getFirstChild(IMAGE_HEADER);
    }

    @objid ("346c1bdf-55b7-11e2-877f-002564c97630")
    public GmModelElementHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

}
