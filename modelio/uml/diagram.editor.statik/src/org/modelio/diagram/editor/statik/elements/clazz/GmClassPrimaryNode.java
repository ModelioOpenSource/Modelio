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

package org.modelio.diagram.editor.statik.elements.clazz;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.statik.elements.attributegroup.GmAttributeGroup;
import org.modelio.diagram.editor.statik.elements.classifier.GmClassifierResizableGroup;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClass;
import org.modelio.diagram.editor.statik.elements.internalstructure.GmInternalStructure;
import org.modelio.diagram.editor.statik.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.diagram.editor.statik.elements.operationgroup.GmOperationGroup;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Classifier}.
 * <p>
 * The GmClass is composed of many zones and groups that are shown depending on the style properties.
 */
@objid ("343fdbca-55b7-11e2-877f-002564c97630")
public class GmClassPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("343fdbdc-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 3;

    @objid ("343fdbdf-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("343fdbe3-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("343fdbe5-55b7-11e2-877f-002564c97630")
    private static final String ATTRIBUTE_GROUP = "AttributeGroup";

    @objid ("343fdbe7-55b7-11e2-877f-002564c97630")
    private static final String METHOD_GROUP = "MethodGroup";

    @objid ("343fdbe9-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_GROUP = "InternalGroup";

    @objid ("343fdbec-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_ZONE = "InternalZone";

    @objid ("343fdbef-55b7-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("343fdbf1-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL = "Internal";

    @objid ("343fdbf3-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String IMAGE_HEADER = "ImageHeader";

    /**
     * Classifier header
     */
    @objid ("a5135e6f-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Operations list group
     */
    @objid ("a5135e75-55c2-11e2-9337-002564c97630")
    private GmGroup methodGroup;

    /**
     * Attributes list group
     */
    @objid ("5b785c74-5bd5-11e2-9e33-00137282c51b")
    private GmAttributeGroup attributeGroup;

    /**
     * Internal structure
     */
    @objid ("5b785c76-5bd5-11e2-9e33-00137282c51b")
    private GmInternalStructure internalStructure;

    /**
     * Inner classes
     */
    @objid ("5b785c78-5bd5-11e2-9e33-00137282c51b")
    private GmInnerClass innerElements;

    /**
     * Constructor for deserialization only.
     */
    @objid ("3441625b-55b7-11e2-877f-002564c97630")
    public GmClassPrimaryNode() {
        // Nothing to do.
    }

    /**
     * Creates a GmClass.
     * 
     * @param diagram The owner diagram.
     * @param el the represented classifier, may be null.
     * @param ref a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("3441625e-55b7-11e2-877f-002564c97630")
    public GmClassPrimaryNode(IGmDiagram diagram, final Classifier el, final MRef ref) {
        super(diagram, ref);
        
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(HEADER);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(diagram, ref);
        
        this.attributeGroup = new GmAttributeGroup(diagram, ref);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        
        this.methodGroup = new GmOperationGroup(diagram, ref);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        
        this.internalStructure = new GmInternalStructure(diagram, ref);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        this.innerElements = new GmInnerClass(diagram, ref);
        this.innerElements.setRoleInComposition(INNER);
        
        super.addChild(this.header);
        super.addChild(group);
        group.addChild(this.attributeGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.internalStructure);
        group.addChild(this.innerElements);
        
        styleChanged(getDisplayedStyle());
    }

    @objid ("3441626c-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Must be of the proper metaclass
        if ((NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type)) ||
            (Instance.class.isAssignableFrom(type) && !Port.class.isAssignableFrom(type)) ||
            (Behavior.class.isAssignableFrom(type))) {
            return true;
        }
        return false;
    }

    @objid ("34416274-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Get the group where <tt>GmAttributes</tt> are unmasked.
     * 
     * @return the attributes group.
     */
    @objid ("3441627c-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getAttributesGroup() {
        return this.attributeGroup;
    }

    @objid ("34416283-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        if (TemplateParameter.class.isAssignableFrom(metaclass)) {
            return null;
        } else if (NameSpace.class.isAssignableFrom(metaclass)) {
            // Namespaces are unmasked in the inner classes gm
            ret = getInnerElements().getCompositeFor(metaclass);
        } else if (Instance.class.isAssignableFrom(metaclass) && !(Port.class.isAssignableFrom(metaclass))) {
            // Instances are unmasked in the internal structure gm
            ret = getInternalStructure().getCompositeFor(metaclass);
        } else if (CollaborationUse.class.isAssignableFrom(metaclass)) {
            // Collaboration uses are unmasked in the internal structure gm
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

    @objid ("3441628d-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * Get the internal structure.
     * 
     * @return the internal structure.
     */
    @objid ("34416292-55b7-11e2-877f-002564c97630")
    public GmInternalStructure getInternalStructure() {
        return this.internalStructure;
    }

    /**
     * Get the group where {@link Operation} are unmasked.
     * 
     * @return the operations group.
     */
    @objid ("34416297-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getOperationsGroup() {
        return this.methodGroup;
    }

    @objid ("3442e8f9-55b7-11e2-877f-002564c97630")
    @Override
    public Classifier getRelatedElement() {
        return (Classifier) super.getRelatedElement();
    }

    @objid ("3442e900-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmClass.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("3442e907-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (getRepresentationMode()) {
            case IMAGE: {
                return Collections.emptyList();
            }
            case SIMPLE:
            case STRUCTURED:
            default: {
                ret = super.getVisibleChildren();
                break;
            }
        }
        return ret;
    }

    @objid ("3442e910-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmClassPrimaryNode.");
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

    @objid ("3442e916-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("3442e919-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        if (GmClassPrimaryNode.MINOR_VERSION != 0) {
            writeMinorVersion(out, "GmClassPrimaryNode.", Integer.valueOf(GmClassPrimaryNode.MINOR_VERSION));
        }
    }

    @objid ("3442e91f-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.attributeGroup = (GmAttributeGroup) children.get(1);
        this.methodGroup = (GmGroup) children.get(2);
        GmGroup internalStructureGroup = (GmGroup) children.get(3);
        GmFreeZone internalStructureZone = (GmFreeZone) children.get(4);
        GmGroup innerGroup = (GmGroup) children.get(5);
        GmFreeZone innerZone = (GmFreeZone) children.get(6);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(7);
        imageModeHeader.delete();
        
        // Migrate inner group/zone
        removeChild(innerGroup);
        removeChild(innerZone);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(),
                                                         getRepresentedRef(),
                                                         internalStructureZone,
                                                         internalStructureGroup);
        this.innerElements = new GmInnerClass(getDiagram(), getRepresentedRef(), innerZone, innerGroup);
        
        // Add roles
        this.header.setRoleInComposition(HEADER);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.internalStructure.setRoleInComposition(INTERNAL);
        this.innerElements.setRoleInComposition(INNER);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(getDiagram(), getRepresentedRef());
        removeChild(this.attributeGroup);
        group.addChild(this.attributeGroup);
        removeChild(this.methodGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.internalStructure);
        group.addChild(this.innerElements);
        super.addChild(group, 1);
    }

    @objid ("3442e924-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("3442e929-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        this.attributeGroup = (GmAttributeGroup) getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(INNER);
        
        // Delete the old image mode header
        GmNodeModel imageHeader = getFirstChild(IMAGE_HEADER);
        if (imageHeader != null) {
            imageHeader.delete();
        }
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(),
                                                         getRepresentedRef(),
                                                         internalStructureZone,
                                                         internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(getDiagram(), getRepresentedRef());
        removeChild(this.attributeGroup);
        group.addChild(this.attributeGroup);
        removeChild(this.methodGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.internalStructure);
        removeChild(this.innerElements);
        group.addChild(this.innerElements);
        super.addChild(group, 1);
    }

    @objid ("3442e92f-55b7-11e2-877f-002564c97630")
    private GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    @objid ("3442e933-55b7-11e2-877f-002564c97630")
    private void read_2(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.attributeGroup = (GmAttributeGroup) group.getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) group.getFirstChild(METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) group.getFirstChild(INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) group.getFirstChild(INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) group.getFirstChild(INNER);
        
        // Delete the old image mode header
        GmNodeModel imageHeader = getFirstChild(IMAGE_HEADER);
        if (imageHeader != null) {
            imageHeader.delete();
        }
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(),
                                                         getRepresentedRef(),
                                                         internalStructureZone,
                                                         internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        group.addChild(this.internalStructure);
    }

    @objid ("34446f9a-55b7-11e2-877f-002564c97630")
    private void read_3(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.attributeGroup = (GmAttributeGroup) group.getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) group.getFirstChild(METHOD_GROUP);
        this.internalStructure = (GmInternalStructure) group.getFirstChild(INTERNAL);
        this.innerElements = (GmInnerClass) group.getFirstChild(INNER);
    }

}
