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

package org.modelio.diagram.editor.statik.elements.collab;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.statik.elements.collab.v0._GmCollaboration;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClass;
import org.modelio.diagram.editor.statik.elements.internalstructure.GmInternalStructure;
import org.modelio.diagram.editor.statik.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Primary Node for GmCollaboration.
 * 
 * @author fpoyer
 */
@objid ("345b533c-55b7-11e2-877f-002564c97630")
public class GmCollaborationPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("345cd999-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("345cd99c-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("345cd99e-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("345cd9a0-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_GROUP = "InternalGroup";

    @objid ("345cd9a3-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_ZONE = "InternalZone";

    @objid ("345cd9a6-55b7-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("345cd9b0-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL = "Internal";

    /**
     * Inner classes
     */
    @objid ("345cd9ae-55b7-11e2-877f-002564c97630")
    private GmInnerClass innerElements;

    /**
     * Header
     */
    @objid ("a6140589-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Internal structure
     */
    @objid ("58633216-5bd5-11e2-9e33-00137282c51b")
    private GmInternalStructure internalStructure;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param ref a reference to the represented object node.
     */
    @objid ("345cd9b2-55b7-11e2-877f-002564c97630")
    public GmCollaborationPrimaryNode(IGmDiagram diagram, MRef ref) {
        super(diagram, ref);
        
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(HEADER);
        
        this.internalStructure = new GmInternalStructure(diagram, ref);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        this.innerElements = new GmInnerClass(diagram, ref);
        this.innerElements.setRoleInComposition(INNER);
        
        super.addChild(this.header);
        super.addChild(this.internalStructure);
        super.addChild(this.innerElements);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("345cd9bb-55b7-11e2-877f-002564c97630")
    public GmCollaborationPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("345cd9be-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("345cd9c6-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("345cd9ce-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        
        if (InformationItem.class.isAssignableFrom(metaclass)) {
            // Namespaces are unmasked in the inner classes zones or group
            ret = getInnerElements().getCompositeFor(metaclass);
        } else if ((Instance.class.isAssignableFrom(metaclass) && !(Port.class.isAssignableFrom(metaclass))) ||
                CollaborationUse.class.isAssignableFrom(metaclass)) {
            // Instances are unmasked in the internal structure zone or group
            ret = getInternalStructure().getCompositeFor(metaclass);
        }
        return ret;
    }

    /**
     * Get the stereotype image to display.
     * 
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("345e603a-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * @return the internalStructure
     */
    @objid ("345e6040-55b7-11e2-877f-002564c97630")
    public final GmInternalStructure getInternalStructure() {
        return this.internalStructure;
    }

    @objid ("345e6045-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmCollaboration.SIMPLE_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("345e604c-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollaborationPrimaryNode.");
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

    @objid ("345e6052-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // forcing visual refresh in case Image changed 
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("345e6055-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (getRepresentationMode()) {
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

    @objid ("345e605e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmCollaborationPrimaryNode.", Integer.valueOf(GmCollaborationPrimaryNode.MINOR_VERSION));
    }

    @objid ("345e6064-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("345e6069-55b7-11e2-877f-002564c97630")
    private GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    @objid ("345e606d-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(INNER);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        addChild(this.internalStructure);
    }

    /**
     * Migration constructor.
     * 
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("345e6073-55b7-11e2-877f-002564c97630")
    GmCollaborationPrimaryNode(final _GmCollaboration oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        
        this.header = oldVersionGm.getHeader();
        this.header.setRoleInComposition(HEADER);
        
        this.innerElements = oldVersionGm.getInnerElements();
        this.innerElements.setRoleInComposition(INNER);
        
        GmGroup internalStructureGroup = oldVersionGm.getInternalStructureGroup();
        internalStructureGroup.setRoleInComposition(INTERNAL_GROUP);
        
        GmFreeZone internalStructureZone = oldVersionGm.getInternalStructureZone();
        internalStructureZone.setRoleInComposition(INTERNAL_ZONE);
        
        // Migrate internal structure group/zone
        oldVersionGm.removeChild(internalStructureGroup);
        oldVersionGm.removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        oldVersionGm.removeChild(this.header);
        super.addChild(this.header);
        super.addChild(this.internalStructure);
        oldVersionGm.removeChild(this.innerElements);
        super.addChild(this.innerElements);
    }

    @objid ("345fe6dc-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        this.internalStructure = (GmInternalStructure) getFirstChild(INTERNAL);
        this.innerElements = (GmInnerClass) getFirstChild(INNER);
    }

}
