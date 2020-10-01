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

package org.modelio.uml.statikdiagram.editor.elements.signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
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
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.statikdiagram.editor.elements.attributegroup.GmAttributeGroup;
import org.modelio.uml.statikdiagram.editor.elements.classifier.GmClassifierResizableGroup;
import org.modelio.uml.statikdiagram.editor.elements.innerclass.GmInnerClass;
import org.modelio.uml.statikdiagram.editor.elements.internalstructure.GmInternalStructure;
import org.modelio.uml.statikdiagram.editor.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.uml.statikdiagram.editor.elements.operationgroup.GmOperationGroup;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the graphic node of a {@link Signal}.
 */
@objid ("368e5fdf-55b7-11e2-877f-002564c97630")
public class GmSignalPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("368fe64a-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 4;

    @objid ("368fe64d-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("368fe64f-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("368fe651-55b7-11e2-877f-002564c97630")
    private static final String ATTRIBUTE_GROUP = "AttributeGroup";

    @objid ("368fe653-55b7-11e2-877f-002564c97630")
    private static final String METHOD_GROUP = "MethodGroup";

    @objid ("368fe655-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_GROUP = "InternalGroup";

    @objid ("368fe658-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_ZONE = "InternalZone";

    @objid ("368fe65b-55b7-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("368fe65f-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL = "Internal";

    /**
     * Attributes list group
     */
    @objid ("368fe642-55b7-11e2-877f-002564c97630")
    private GmAttributeGroup attributeGroup;

    /**
     * Internal structure
     */
    @objid ("368fe648-55b7-11e2-877f-002564c97630")
    private GmInternalStructure internalStructure;

    /**
     * Inner classes
     */
    @objid ("368fe65d-55b7-11e2-877f-002564c97630")
    private GmInnerClass innerElements;

    /**
     * Header
     */
    @objid ("a79e00fa-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Operations list group
     */
    @objid ("a79e00fc-55c2-11e2-9337-002564c97630")
    private GmGroup methodGroup;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this node is unmasked.
     * @param ref a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("368fe661-55b7-11e2-877f-002564c97630")
    public GmSignalPrimaryNode(IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
        
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(HEADER);
        this.header.setShowMetaclassKeyword(false);
        this.header.setShowMetaclassIcon(false);
        
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

    @objid ("368fe66b-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        if (this.header != null) {
            String oldLabel = this.header.getMainLabel();
            this.header.refreshFromObModel();
            firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        }
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("368fe66e-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Must be of the proper metaclass
        if ((NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type)) ||
                (Instance.class.isAssignableFrom(type) && !Port.class.isAssignableFrom(type)) ||
                (Behavior.class.isAssignableFrom(type)))
            return true;
        return false;
    }

    @objid ("368fe676-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("36916cde-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmSignal.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("36916ce5-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("36916cea-55b7-11e2-877f-002564c97630")
    public GmSignalPrimaryNode() {
        // Nothing to do.
    }

    @objid ("36916ced-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        if (TemplateParameter.class.isAssignableFrom(metaclass)) {
            return null;
        } else if (NameSpace.class.isAssignableFrom(metaclass)) {
            // Namespaces are unmasked in the inner classes gm
            ret = getInnerElements().getCompositeFor(metaclass);
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

    @objid ("36916cf7-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case IMAGE:
            ret = Collections.emptyList();
            break;
        case SIMPLE:
            ret = new ArrayList<>(1);
            ret.add(this.header);
            break;
        default:
            ret = super.getVisibleChildren();
            break;
        }
        return ret;
    }

    @objid ("36916d00-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSignalPrimaryNode.");
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
        case 4: {
            read_4(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 4
            read_4(in);
            break;
        }
        }
    }

    @objid ("36916d06-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        super.styleChanged(changedStyle);
        
        refreshHeaderFromStyle(changedStyle);
    }

    @objid ("36916d0d-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        super.styleChanged(property, newValue);
        
        if (property.equals(GmSignalStructuredStyleKeys.SHOWSTEREOTYPES)) {
            refreshHeaderFromStyle(getDisplayedStyle());
        }
    }

    /**
     * Show or not the metaclass keyword and the metaclass icon depending on the stereotype show mode.
     */
    @objid ("3692f37c-55b7-11e2-877f-002564c97630")
    private void refreshHeaderFromStyle(final IStyle changedStyle) {
        final ShowStereotypeMode mode = changedStyle.getProperty(GmSignalStructuredStyleKeys.SHOWSTEREOTYPES);
        switch (mode) {
        case NONE:
            this.header.setShowMetaclassKeyword(false);
            this.header.setShowMetaclassIcon(true);
            break;
        case ICON:
            this.header.setShowMetaclassKeyword(false);
            this.header.setShowMetaclassIcon(true);
            break;
        case TEXT:
            this.header.setShowMetaclassKeyword(true);
            this.header.setShowMetaclassIcon(false);
            break;
        case TEXTICON:
            this.header.setShowMetaclassKeyword(true);
            this.header.setShowMetaclassIcon(true);
        }
    }

    /**
     * @return the attributes group.
     */
    @objid ("3692f383-55b7-11e2-877f-002564c97630")
    public GmAttributeGroup getAttributesGroup() {
        return this.attributeGroup;
    }

    /**
     * @return the operations group.
     */
    @objid ("3692f388-55b7-11e2-877f-002564c97630")
    public GmGroup getOperationsGroup() {
        return this.methodGroup;
    }

    /**
     * Get the internal structure.
     * 
     * @return the internal structure.
     */
    @objid ("3692f38f-55b7-11e2-877f-002564c97630")
    public GmInternalStructure getInternalStructure() {
        return this.internalStructure;
    }

    @objid ("3692f394-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSignalPrimaryNode.", GmSignalPrimaryNode.MINOR_VERSION);
    }

    @objid ("3692f39a-55b7-11e2-877f-002564c97630")
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
        
        // Delete image label
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) children.get(7);
        imageModeHeader.delete();
        
        // Migrate inner group/zone
        removeChild(innerGroup);
        removeChild(innerZone);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
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

    @objid ("3692f39f-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("3692f3a4-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        this.attributeGroup = (GmAttributeGroup) getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(INNER);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        // Delete image label
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) getFirstChild("ImageHeader");
        imageModeHeader.delete();
        
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

    @objid ("3692f3aa-55b7-11e2-877f-002564c97630")
    private GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    @objid ("3692f3ae-55b7-11e2-877f-002564c97630")
    private void read_2(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        this.attributeGroup = (GmAttributeGroup) getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(INNER);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
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

    @objid ("3692f3b4-55b7-11e2-877f-002564c97630")
    private void read_3(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.attributeGroup = (GmAttributeGroup) group.getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) group.getFirstChild(METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) group.getFirstChild(INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) group.getFirstChild(INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) group.getFirstChild(INNER);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        group.addChild(this.internalStructure);
    }

    @objid ("36947a1d-55b7-11e2-877f-002564c97630")
    private void read_4(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.attributeGroup = (GmAttributeGroup) group.getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) group.getFirstChild(METHOD_GROUP);
        this.internalStructure = (GmInternalStructure) group.getFirstChild(INTERNAL);
        this.innerElements = (GmInnerClass) group.getFirstChild(INNER);
    }

}
