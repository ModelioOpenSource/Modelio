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

package org.modelio.diagram.editor.deployment.elements.node;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.statik.elements.attributegroup.GmAttributeGroup;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClass;
import org.modelio.diagram.editor.statik.elements.internalstructure.GmInternalStructure;
import org.modelio.diagram.editor.statik.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.diagram.editor.statik.elements.operationgroup.GmOperationGroup;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Classifier}.
 * <p>
 * The GmClass is composed of many zones and groups that are shown depending on the style properties.
 */
@objid ("973befb7-55b6-11e2-877f-002564c97630")
public class GmNodePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("973d7620-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 3;

    @objid ("973d7623-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("973d7625-55b6-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("973d7627-55b6-11e2-877f-002564c97630")
    private static final String ATTRIBUTE_GROUP = "AttributeGroup";

    @objid ("973d7629-55b6-11e2-877f-002564c97630")
    private static final String METHOD_GROUP = "MethodGroup";

    @objid ("973d762b-55b6-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_GROUP = "InternalGroup";

    @objid ("973d762e-55b6-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_ZONE = "InternalZone";

    @objid ("973d7631-55b6-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("973d7635-55b6-11e2-877f-002564c97630")
    private static final String INTERNAL = "Internal";

    /**
     * Attributes list group
     */
    @objid ("1cd2ff69-55c2-11e2-9337-002564c97630")
    private GmGroup attributeGroup;

    /**
     * Classifier header
     */
    @objid ("1cd2ff6b-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Operations list group
     */
    @objid ("1cd2ff71-55c2-11e2-9337-002564c97630")
    private GmGroup methodGroup;

    /**
     * Internal structure
     */
    @objid ("43ba2110-5beb-11e2-9e33-00137282c51b")
    private GmInternalStructure internalStructure;

    /**
     * Inner classes
     */
    @objid ("43ba2112-5beb-11e2-9e33-00137282c51b")
    private GmInnerClass innerElements;

    /**
     * Constructor for deserialization only.
     */
    @objid ("973d7637-55b6-11e2-877f-002564c97630")
    public GmNodePrimaryNode() {
        // Nothing to do.
    }

    /**
     * Creates a GmClass.
     * @param diagram The owner diagram.
     * @param ref a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("973d763a-55b6-11e2-877f-002564c97630")
    public GmNodePrimaryNode(IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
        
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(GmNodePrimaryNode.HEADER);
        
        this.attributeGroup = new GmAttributeGroup(diagram, ref);
        this.attributeGroup.setRoleInComposition(GmNodePrimaryNode.ATTRIBUTE_GROUP);
        
        this.methodGroup = new GmOperationGroup(diagram, ref);
        this.methodGroup.setRoleInComposition(GmNodePrimaryNode.METHOD_GROUP);
        
        this.internalStructure = new GmInternalStructure(diagram, ref);
        this.internalStructure.setRoleInComposition(GmNodePrimaryNode.INTERNAL);
        
        this.innerElements = new GmInnerClass(diagram, ref);
        this.innerElements.setRoleInComposition(GmNodePrimaryNode.INNER);
        
        super.addChild(this.header);
        super.addChild(this.attributeGroup);
        super.addChild(this.methodGroup);
        super.addChild(this.internalStructure);
        super.addChild(this.innerElements);
        
        styleChanged(getDisplayedStyle());
    }

    @objid ("973d7644-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("973d764c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Get the group where <tt>GmAttributes</tt> are unmasked.
     * @return the attributes group.
     */
    @objid ("973d7654-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getAttributesGroup() {
        return this.attributeGroup;
    }

    @objid ("973efcbd-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        if (NameSpace.class.isAssignableFrom(metaclass)) {
            // Namespaces are unmasked in the inner classes gm
            ret = getInnerElements().getCompositeFor(metaclass);
        } else if (Instance.class.isAssignableFrom(metaclass) && !(Port.class.isAssignableFrom(metaclass))) {
            // Instances are unmasked in the internal structure zone or group
            ret = getInternalStructure().getCompositeFor(metaclass);
        } else if (Attribute.class.isAssignableFrom(metaclass)) {
            // Attributes are unmasked in the attributes group
            ret = getAttributesGroup();
        } else if (Operation.class.isAssignableFrom(metaclass)) {
            // Operations are unmasked in the operations group
            ret = getOperationsGroup();
        }
        return ret;
    }

    @objid ("973efcc7-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * Get the internal structure.
     * @return the internal structure.
     */
    @objid ("973efccc-55b6-11e2-877f-002564c97630")
    public GmInternalStructure getInternalStructure() {
        return this.internalStructure;
    }

    /**
     * Get the group where {@link Operation} are unmasked.
     * @return the operations group.
     */
    @objid ("973efcd1-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getOperationsGroup() {
        return this.methodGroup;
    }

    @objid ("973efcd8-55b6-11e2-877f-002564c97630")
    @Override
    public Classifier getRelatedElement() {
        return (Classifier) super.getRelatedElement();
    }

    @objid ("973efcdf-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmNode.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("973efce6-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNodePrimaryNode.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        case 2: {
            read_2(in);
            break;
        }
        case 3: {
            read_3(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 3
            read_3(in);
            break;
        }
        }
    }

    @objid ("973efcec-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(IGmObject.PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("973efcef-55b6-11e2-877f-002564c97630")
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

    @objid ("973efcf8-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNodePrimaryNode.", GmNodePrimaryNode.MINOR_VERSION);
    }

    @objid ("97408359-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.attributeGroup = (GmGroup) children.get(1);
        this.methodGroup = (GmGroup) children.get(2);
        GmGroup internalStructureGroup = (GmGroup) children.get(3);
        GmFreeZone internalStructureZone = (GmFreeZone) children.get(4);
        GmGroup innerGroup = (GmGroup) children.get(5);
        GmFreeZone innerZone = (GmFreeZone) children.get(6);
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(7);
        
        // Migrate inner group/zone
        removeChild(innerGroup);
        removeChild(innerZone);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        // Delete image mode label
        imageModeHeader.delete();
        
        this.innerElements = new GmInnerClass(getDiagram(), getRepresentedRef(), innerZone, innerGroup);
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        
        addChild(this.innerElements);
        addChild(this.internalStructure);
        
        // Add roles
        this.header.setRoleInComposition(GmNodePrimaryNode.HEADER);
        this.attributeGroup.setRoleInComposition(GmNodePrimaryNode.ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(GmNodePrimaryNode.METHOD_GROUP);
        this.internalStructure.setRoleInComposition(GmNodePrimaryNode.INTERNAL);
        this.innerElements.setRoleInComposition(GmNodePrimaryNode.INNER);
    }

    @objid ("9740835e-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmNodePrimaryNode.MAJOR_VERSION;
    }

    @objid ("97408363-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(GmNodePrimaryNode.HEADER);
        this.attributeGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(GmNodePrimaryNode.INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(GmNodePrimaryNode.INNER);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(GmNodePrimaryNode.INTERNAL);
        
        addChild(this.internalStructure);
        
        // Delete image mode label
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) getFirstChild("ImageHeader");
        imageModeHeader.delete();
    }

    @objid ("97408369-55b6-11e2-877f-002564c97630")
    private GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    @objid ("9740836d-55b6-11e2-877f-002564c97630")
    private void read_2(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(GmNodePrimaryNode.HEADER);
        this.attributeGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(GmNodePrimaryNode.INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(GmNodePrimaryNode.INNER);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(GmNodePrimaryNode.INTERNAL);
        
        addChild(this.internalStructure);
    }

    @objid ("97408373-55b6-11e2-877f-002564c97630")
    private void read_3(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(GmNodePrimaryNode.HEADER);
        this.attributeGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(GmNodePrimaryNode.METHOD_GROUP);
        this.internalStructure = (GmInternalStructure) getFirstChild(GmNodePrimaryNode.INTERNAL);
        this.innerElements = (GmInnerClass) getFirstChild(GmNodePrimaryNode.INNER);
    }

}
