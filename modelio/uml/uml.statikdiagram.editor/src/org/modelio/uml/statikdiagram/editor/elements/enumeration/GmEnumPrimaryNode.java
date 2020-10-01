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

package org.modelio.uml.statikdiagram.editor.elements.enumeration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.statikdiagram.editor.elements.attributegroup.GmAttributeGroup;
import org.modelio.uml.statikdiagram.editor.elements.classifier.GmClassifierResizableGroup;
import org.modelio.uml.statikdiagram.editor.elements.enumliteral.GmEnumLitteralGroup;
import org.modelio.uml.statikdiagram.editor.elements.innerclass.GmInnerClass;
import org.modelio.uml.statikdiagram.editor.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.uml.statikdiagram.editor.elements.operationgroup.GmOperationGroup;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Classifier}.
 * <p>
 * The GmClass is composed of many zones and groups that are shown depending on the style properties.
 */
@objid ("34db7faf-55b7-11e2-877f-002564c97630")
public class GmEnumPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("34db7fbf-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 3;

    @objid ("34dd0619-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("34dd0621-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("34dd0623-55b7-11e2-877f-002564c97630")
    private static final String ATTRIBUTE_GROUP = "AttributeGroup";

    @objid ("34dd0625-55b7-11e2-877f-002564c97630")
    private static final String METHOD_GROUP = "MethodGroup";

    @objid ("34dd0627-55b7-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("34dd0629-55b7-11e2-877f-002564c97630")
    private static final String LITTERAL_GROUP = "LitteralGroup";

    /**
     * EnumerationLiterals list group
     */
    @objid ("34db7fb9-55b7-11e2-877f-002564c97630")
    private GmEnumLitteralGroup enumGroup;

    /**
     * Inner classes
     */
    @objid ("34dd061f-55b7-11e2-877f-002564c97630")
    private GmInnerClass innerElements;

    /**
     * Classifier header
     */
    @objid ("a663ac2c-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Operations list group
     */
    @objid ("a663ac2e-55c2-11e2-9337-002564c97630")
    private GmGroup methodGroup;

    /**
     * Attribute list group
     */
    @objid ("a663ac30-55c2-11e2-9337-002564c97630")
    private GmGroup attributeGroup;

    /**
     * Constructor for deserialization only.
     */
    @objid ("34dd062b-55b7-11e2-877f-002564c97630")
    public GmEnumPrimaryNode() {
        // Nothing to do.
    }

    /**
     * Creates a GmClass.
     * 
     * @param diagram The owner diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("34dd062e-55b7-11e2-877f-002564c97630")
    public GmEnumPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.header = new GmNamespaceHeader(diagram, relatedRef);
        this.header.setShowMetaclassIcon(true);
        this.header.setRoleInComposition(HEADER);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(diagram, relatedRef);
        
        this.enumGroup = new GmEnumLitteralGroup(diagram, relatedRef);
        this.enumGroup.setRoleInComposition(LITTERAL_GROUP);
        
        this.attributeGroup = new GmAttributeGroup(diagram, relatedRef);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        
        this.methodGroup = new GmOperationGroup(diagram, relatedRef);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        
        this.innerElements = new GmInnerClass(diagram, relatedRef);
        this.innerElements.setRoleInComposition(INNER);
        
        super.addChild(this.header);
        super.addChild(group);
        group.addChild(this.enumGroup);
        group.addChild(this.attributeGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.innerElements);
        
        styleChanged(getDisplayedStyle());
    }

    @objid ("34dd0637-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Must be of the proper metaclass
        if ((NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type)))
            return true;
        return false;
    }

    @objid ("34dd063f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Get the group where <tt>GmEnumLiterals</tt> are unmasked.
     * 
     * @return the enum group.
     */
    @objid ("34dd0647-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getEnumGroup() {
        return this.enumGroup;
    }

    @objid ("34dd064e-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        if (TemplateParameter.class.isAssignableFrom(metaclass)) {
            return null;
        } else if (EnumerationLiteral.class.isAssignableFrom(metaclass)) {
            // EnumerationLiterals are unmasked in the enum group
            ret = getEnumGroup();
        } else if (Attribute.class.isAssignableFrom(metaclass)) {
            // Attributes are unmasked in the attributes group
            ret = getAttributesGroup();
        } else if (Operation.class.isAssignableFrom(metaclass)) {
            // Operations are unmasked in the operations group
            ret = getOperationsGroup();
        } else if (NameSpace.class.isAssignableFrom(metaclass) && metaclass != Signal.class) {
            // Namespaces are unmasked in the inner classes gm
            ret = getInnerElements().getCompositeFor(metaclass);
        }
        return ret;
    }

    @objid ("34de8cbe-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * Get the group where {@link Operation} are unmasked.
     * 
     * @return the operations group.
     */
    @objid ("34de8cc3-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getOperationsGroup() {
        return this.methodGroup;
    }

    @objid ("34de8cca-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmEnum.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("34de8cd1-55b7-11e2-877f-002564c97630")
    @Override
    public Enumeration getRelatedElement() {
        return (Enumeration) super.getRelatedElement();
    }

    @objid ("34de8cd8-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmEnumPrimaryNode.");
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

    @objid ("34de8cde-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("34de8ce1-55b7-11e2-877f-002564c97630")
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

    @objid ("34de8cea-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        super.styleChanged(changedStyle);
        
        refreshHeaderFromStyle(changedStyle);
    }

    @objid ("34de8cf1-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        super.styleChanged(property, newValue);
        
        if (property.equals(EnumStructuredStyleKeys.SHOWSTEREOTYPES)) {
            refreshHeaderFromStyle(getDisplayedStyle());
        }
    }

    /**
     * Show or not the metaclass keyword and the metaclass icon depending on the stereotype show mode.
     */
    @objid ("34e0135f-55b7-11e2-877f-002564c97630")
    private void refreshHeaderFromStyle(final IStyle changedStyle) {
        final ShowStereotypeMode mode = changedStyle.getProperty(EnumStructuredStyleKeys.SHOWSTEREOTYPES);
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

    @objid ("34e01366-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmEnumPrimaryNode.", Integer.valueOf(GmEnumPrimaryNode.MINOR_VERSION));
    }

    @objid ("34e0136c-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.enumGroup = (GmEnumLitteralGroup) children.get(1);
        this.methodGroup = (GmGroup) children.get(2);
        
        // Add the new attribute & inner groups
        this.attributeGroup = new GmAttributeGroup(getDiagram(), getRepresentedRef());
        this.innerElements = new GmInnerClass(getDiagram(), getRepresentedRef());
        
        // Set roles
        this.header.setRoleInComposition(HEADER);
        this.enumGroup.setRoleInComposition(LITTERAL_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.innerElements.setRoleInComposition(INNER);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(3);
        imageModeHeader.delete();
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(getDiagram(), getRepresentedRef());
        removeChild(this.enumGroup);
        group.addChild(this.enumGroup);
        group.addChild(this.attributeGroup);
        removeChild(this.methodGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.innerElements);
        super.addChild(group, 1);
    }

    @objid ("34e01371-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("34e01376-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.enumGroup = (GmEnumLitteralGroup) children.get(1);
        this.methodGroup = (GmGroup) children.get(2);
        
        // Add the new attribute & inner groups
        this.attributeGroup = new GmAttributeGroup(getDiagram(), getRepresentedRef());
        this.innerElements = new GmInnerClass(getDiagram(), getRepresentedRef());
        
        // Set roles
        this.header.setRoleInComposition(HEADER);
        this.enumGroup.setRoleInComposition(LITTERAL_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.innerElements.setRoleInComposition(INNER);
        
        GmClassifierResizableGroup group = new GmClassifierResizableGroup(getDiagram(), getRepresentedRef());
        removeChild(this.enumGroup);
        group.addChild(this.enumGroup);
        group.addChild(this.attributeGroup);
        removeChild(this.methodGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.innerElements);
        super.addChild(group, 1);
    }

    @objid ("34e0137c-55b7-11e2-877f-002564c97630")
    private void read_2(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getChildren().get(0);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.enumGroup = (GmEnumLitteralGroup) group.getChildren().get(0);
        this.methodGroup = (GmGroup) group.getChildren().get(1);
        
        // Add the new attribute & inner groups
        this.attributeGroup = new GmAttributeGroup(getDiagram(), getRepresentedRef());
        this.innerElements = new GmInnerClass(getDiagram(), getRepresentedRef());
        
        // Set roles
        this.header.setRoleInComposition(HEADER);
        this.enumGroup.setRoleInComposition(LITTERAL_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.attributeGroup.setRoleInComposition(ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(METHOD_GROUP);
        this.innerElements.setRoleInComposition(INNER);
        
        group.addChild(this.attributeGroup);
        removeChild(this.methodGroup);
        group.addChild(this.methodGroup);
        group.addChild(this.innerElements);
    }

    @objid ("34e01382-55b7-11e2-877f-002564c97630")
    private GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    /**
     * Get the group where <tt>GmAttributes</tt> are unmasked.
     * 
     * @return the attributes group.
     */
    @objid ("34e01386-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getAttributesGroup() {
        return this.attributeGroup;
    }

    @objid ("34e0138d-55b7-11e2-877f-002564c97630")
    private void read_3(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(HEADER);
        GmClassifierResizableGroup group = (GmClassifierResizableGroup) getChildren().get(1);
        this.enumGroup = (GmEnumLitteralGroup) group.getFirstChild(LITTERAL_GROUP);
        this.attributeGroup = (GmGroup) group.getFirstChild(ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) group.getFirstChild(METHOD_GROUP);
        this.innerElements = (GmInnerClass) group.getFirstChild(INNER);
    }

}
