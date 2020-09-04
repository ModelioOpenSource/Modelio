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

package org.modelio.diagram.editor.statik.elements.datatype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.statik.elements.attributegroup.GmAttributeGroup;
import org.modelio.diagram.editor.statik.elements.classifier.GmClassifierResizableGroup;
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
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link DataType}.
 * <p>
 * The GmDataType is composed of many zones and groups that are shown depending on the style properties.
 */
@objid ("34c18eef-55b7-11e2-877f-002564c97630")
public class GmDataTypePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("34c18f01-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 3;

    @objid ("34c18f04-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("34c18f06-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("34c18f08-55b7-11e2-877f-002564c97630")
    private static final String ATTRIBUTE_GROUP = "AttributeGroup";

    @objid ("34c18f0a-55b7-11e2-877f-002564c97630")
    private static final String METHOD_GROUP = "MethodGroup";

    @objid ("34c18f0c-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL = "Internal";

    /**
     * Attributes list group
     */
    @objid ("34c18ef9-55b7-11e2-877f-002564c97630")
    private GmAttributeGroup attributeGroup;

    /**
     * Classifier header
     */
    @objid ("a65a846b-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Operations list group
     */
    @objid ("a65a846d-55c2-11e2-9337-002564c97630")
    private GmGroup methodGroup;

    /**
     * Internal structure
     */
    @objid ("5c4ef018-5bd5-11e2-9e33-00137282c51b")
    private GmInternalStructure internalStructure;

    /**
     * Constructor for deserialization only.
     */
    @objid ("34c18f0e-55b7-11e2-877f-002564c97630")
    public GmDataTypePrimaryNode() {
        // Nothing to do.
    }

    /**
     * Creates a GmClass.
     * 
     * @param diagram The owner diagram.
     * @param ref a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("34c18f11-55b7-11e2-877f-002564c97630")
    public GmDataTypePrimaryNode(IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
        
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(HEADER);
        this.header.setShowMetaclassIcon(true);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(diagram, ref);
        
        this.attributeGroup = new GmAttributeGroup(diagram, ref);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        
        this.methodGroup = new GmOperationGroup(diagram, ref);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        
        this.internalStructure = new GmInternalStructure(diagram, ref);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        super.addChild(this.header);
        super.addChild(group);
        group.addChild(this.attributeGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.internalStructure);
        
        styleChanged(getDisplayedStyle());
    }

    @objid ("34c31581-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type) ) ||
                                (Instance.class.isAssignableFrom(type) && !Port.class.isAssignableFrom(type));
    }

    @objid ("34c31589-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Get the group where <tt>GmAttributes</tt> are unmasked.
     * 
     * @return the attributes group.
     */
    @objid ("34c31591-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getAttributesGroup() {
        return this.attributeGroup;
    }

    @objid ("34c31598-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        
        if (Instance.class.isAssignableFrom(metaclass) && !(Port.class.isAssignableFrom(metaclass))) {
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

    @objid ("34c315a2-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * Get the internal structure.
     * 
     * @return the internal structure.
     */
    @objid ("34c315a7-55b7-11e2-877f-002564c97630")
    public GmInternalStructure getInternalStructure() {
        return this.internalStructure;
    }

    /**
     * Get the group where {@link Operation} are unmasked.
     * 
     * @return the operations group.
     */
    @objid ("34c315ac-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getOperationsGroup() {
        return this.methodGroup;
    }

    @objid ("34c315b3-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmDataType.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("34c315ba-55b7-11e2-877f-002564c97630")
    @Override
    public DataType getRelatedElement() {
        return (DataType) super.getRelatedElement();
    }

    @objid ("34c49c1d-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDataTypePrimaryNode.");
        switch (readVersion) {
        case 0: 
            read_0(in);
            break;
        case 1: 
            read_1(in);
            break;
        case 2:
            read_2(in);
            break;
        case 3: 
            read_3(in);
            break;
        default: 
            assert (false) : readVersion+ " version number not covered!";
            // reading as last handled version: 3
            read_3(in);
            break;
        
        }
    }

    @objid ("34c49c23-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("34c49c26-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (getRepresentationMode()) {
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

    @objid ("34c49c2f-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        super.styleChanged(changedStyle);
        
        refreshHeaderFromStyle(changedStyle);
    }

    @objid ("34c49c36-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        super.styleChanged(property, newValue);
        
        if (property.equals(DataTypeStructuredStyleKeys.SHOWSTEREOTYPES)) {
            refreshHeaderFromStyle(getDisplayedStyle());
        }
    }

    /**
     * Show or not the metaclass keyword and the metaclass icon depending on the stereotype show mode.
     */
    @objid ("34c49c3f-55b7-11e2-877f-002564c97630")
    private void refreshHeaderFromStyle(final IStyle changedStyle) {
        final ShowStereotypeMode mode = changedStyle.getProperty(DataTypeStructuredStyleKeys.SHOWSTEREOTYPES);
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

    @objid ("34c49c46-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmDataTypePrimaryNode.", Integer.valueOf(GmDataTypePrimaryNode.MINOR_VERSION));
    }

    @objid ("34c49c4c-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.attributeGroup = (GmAttributeGroup) children.get(1);
        this.methodGroup = (GmGroup) children.get(2);
        GmGroup internalStructureGroup = (GmGroup) children.get(3);
        GmFreeZone internalStructureZone = (GmFreeZone) children.get(4);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(5);
        imageModeHeader.delete();
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        
        // Add roles
        this.header.setRoleInComposition(HEADER);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(getDiagram(), getRepresentedRef());
        removeChild(this.attributeGroup);
        group.addChild(this.attributeGroup);
        removeChild(this.methodGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.internalStructure);
        super.addChild(group, 1);
    }

    @objid ("34c49c51-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("34c622bb-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.attributeGroup = (GmAttributeGroup) children.get(1);
        this.methodGroup = (GmGroup) children.get(2);
        GmGroup internalStructureGroup = (GmGroup) children.get(3);
        GmFreeZone internalStructureZone = (GmFreeZone) children.get(4);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        // Add roles
        this.header.setRoleInComposition(HEADER);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(getDiagram(), getRepresentedRef());
        removeChild(this.attributeGroup);
        group.addChild(this.attributeGroup);
        removeChild(this.methodGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.internalStructure);
        super.addChild(group, 1);
    }

    @objid ("34c622c1-55b7-11e2-877f-002564c97630")
    private void read_2(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getChildren().get(0);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.attributeGroup = (GmAttributeGroup) group.getChildren().get(0);
        this.methodGroup = (GmGroup) group.getChildren().get(1);
        GmGroup internalStructureGroup = (GmGroup) group.getChildren().get(2);
        GmFreeZone internalStructureZone = (GmFreeZone) group.getChildren().get(3);
        
        // Add roles
        this.header.setRoleInComposition(HEADER);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.internalStructure.setRoleInComposition(INTERNAL);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(INTERNAL);
        group.addChild(this.internalStructure);
    }

    @objid ("34c622c7-55b7-11e2-877f-002564c97630")
    private void read_3(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.attributeGroup = (GmAttributeGroup) group.getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) group.getFirstChild(METHOD_GROUP);
        this.internalStructure = (GmInternalStructure) group.getFirstChild(INTERNAL);
    }

}
