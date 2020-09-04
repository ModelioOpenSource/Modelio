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

package org.modelio.diagram.editor.usecase.elements.usecase;

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
import org.modelio.diagram.editor.usecase.elements.usecase.v0._GmUseCase;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
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
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e5f8ce9-55b7-11e2-877f-002564c97630")
public class GmUseCasePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    @objid ("5e5f8cef-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("5e5f8cf2-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5e5f8cf4-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("5e5f8cf6-55b7-11e2-877f-002564c97630")
    private static final String ATTRIBUTE_GROUP = "AttributeGroup";

    @objid ("5e5f8cf8-55b7-11e2-877f-002564c97630")
    private static final String METHOD_GROUP = "MethodGroup";

    @objid ("5e5f8cfa-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_GROUP = "InternalGroup";

    @objid ("5e61135b-55b7-11e2-877f-002564c97630")
    @Deprecated
    private static final String INTERNAL_ZONE = "InternalZone";

    @objid ("5e61135e-55b7-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("5e611360-55b7-11e2-877f-002564c97630")
    private static final String EXTENSIONS = "Extensions";

    @objid ("5e611376-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL = "Internal";

    @objid ("d9871a6a-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    @objid ("d9871a6c-55c2-11e2-9337-002564c97630")
    private GmGroup attributeGroup;

    @objid ("d9871a6e-55c2-11e2-9337-002564c97630")
    private GmGroup methodGroup;

    @objid ("d9871a70-55c2-11e2-9337-002564c97630")
    private GmGroup extensionPointGroup;

    @objid ("7c0934ef-5eff-11e2-b9cc-001ec947c8cc")
    private GmInternalStructure internalStructure;

    @objid ("7c0934f1-5eff-11e2-b9cc-001ec947c8cc")
    private GmInnerClass innerElements;

    @objid ("5e611378-55b7-11e2-877f-002564c97630")
    public GmUseCasePrimaryNode(IGmDiagram diagram, MRef ref) {
        super(diagram, ref);
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(GmUseCasePrimaryNode.HEADER);
        
        this.attributeGroup = new GmAttributeGroup(diagram, ref);
        this.attributeGroup.setRoleInComposition(GmUseCasePrimaryNode.ATTRIBUTE_GROUP);
        
        this.methodGroup = new GmOperationGroup(diagram, ref);
        this.methodGroup.setRoleInComposition(GmUseCasePrimaryNode.METHOD_GROUP);
        
        this.extensionPointGroup = new GmExtensionPointGroup(diagram, ref);
        this.extensionPointGroup.setRoleInComposition(GmUseCasePrimaryNode.EXTENSIONS);
        
        this.internalStructure = new GmInternalStructure(diagram, ref);
        this.internalStructure.setRoleInComposition(GmUseCasePrimaryNode.INTERNAL);
        
        this.innerElements = new GmInnerClass(diagram, ref);
        this.innerElements.setRoleInComposition(GmUseCasePrimaryNode.INNER);
        
        super.addChild(this.header);
        super.addChild(this.attributeGroup);
        super.addChild(this.methodGroup);
        super.addChild(this.extensionPointGroup);
        super.addChild(this.internalStructure);
        super.addChild(this.innerElements);
    }

    @objid ("5e611381-55b7-11e2-877f-002564c97630")
    public GmUseCasePrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("5e611384-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (Attribute.class.isAssignableFrom(type)) {
            return this.attributeGroup.canCreate(type);
        }
        
        if (Operation.class.isAssignableFrom(type)) {
            return this.methodGroup.canCreate(type);
        }
        
        if (ExtensionPoint.class.isAssignableFrom(type)) {
            return this.extensionPointGroup.canCreate(type);
        }
        
        if (Instance.class.isAssignableFrom(type) && !(Port.class.isAssignableFrom(type))) {
            return this.internalStructure.canCreate(type);
        }
        
        if (CollaborationUse.class.isAssignableFrom(type)) {
            return this.internalStructure.canCreate(type);
        }
        return false;
    }

    @objid ("5e61138c-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        if (el instanceof Attribute) {
            return this.attributeGroup.canUnmask(el);
        }
        
        if (el instanceof Operation) {
            return this.methodGroup.canUnmask(el);
        }
        
        if (el instanceof ExtensionPoint) {
            return this.extensionPointGroup.canUnmask(el);
        }
        
        if (el instanceof Instance && !(el instanceof Port)) {
            return this.internalStructure.canUnmask(el);
        }
        
        if (el instanceof CollaborationUse) {
            return this.internalStructure.canUnmask(el);
        }
        
        if (el instanceof NameSpace) {
            return this.innerElements.canUnmask(el);
        }
        return false;
    }

    @objid ("5e611394-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        
        if (Attribute.class.isAssignableFrom(metaclass)) {
            // Attributes are unmasked in the attributes group
            ret = this.attributeGroup;
        } else if (Operation.class.isAssignableFrom(metaclass)) {
            // Operations are unmasked in the operations group
            ret = this.methodGroup;
        } else if (ExtensionPoint.class.isAssignableFrom(metaclass)) {
            // ExtensionPoint are unmasked in the extensionPoint group
            ret = this.extensionPointGroup;
        } else if (Instance.class.isAssignableFrom(metaclass) && !(Port.class.isAssignableFrom(metaclass))) {
            // Instances are unmasked in the internal structure zone or group
            ret = this.internalStructure.getCompositeFor(metaclass);
        } else if (CollaborationUse.class.isAssignableFrom(metaclass)) {
            // Collaboration uses are unmasked in the internal structure zone or group
            ret = this.internalStructure.getCompositeFor(metaclass);
        } else if (Collaboration.class.isAssignableFrom(metaclass)) {
            // Namespaces are unmasked in the inner classes gm
            ret = getInnerElements().getCompositeFor(metaclass);
        }
        return ret;
    }

    @objid ("5e6299fd-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("5e629a03-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmUseCase.SIMPLE_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("5e629a0a-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmUseCasePrimaryNode.");
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

    @objid ("5e629a10-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("5e629a13-55b7-11e2-877f-002564c97630")
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

    @objid ("5e629a1c-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmUseCasePrimaryNode.", Integer.valueOf(GmUseCasePrimaryNode.MINOR_VERSION));
    }

    @objid ("5e629a22-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmUseCasePrimaryNode.MAJOR_VERSION;
    }

    @objid ("5e629a27-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(GmUseCasePrimaryNode.HEADER);
        this.attributeGroup = (GmGroup) getFirstChild(GmUseCasePrimaryNode.ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(GmUseCasePrimaryNode.METHOD_GROUP);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(GmUseCasePrimaryNode.INTERNAL_GROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(GmUseCasePrimaryNode.INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(GmUseCasePrimaryNode.INNER);
        this.extensionPointGroup = (GmGroup) getFirstChild(GmUseCasePrimaryNode.EXTENSIONS);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(GmUseCasePrimaryNode.INTERNAL);
        addChild(this.internalStructure);
    }

    @objid ("5e629a2d-55b7-11e2-877f-002564c97630")
    private GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    @objid ("5e629a31-55b7-11e2-877f-002564c97630")
    GmUseCasePrimaryNode(final _GmUseCase oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.header = oldVersionGm.getHeader();
        this.header.setRoleInComposition(GmUseCasePrimaryNode.HEADER);
        
        this.attributeGroup = oldVersionGm.getAttributeGroup();
        this.attributeGroup.setRoleInComposition(GmUseCasePrimaryNode.ATTRIBUTE_GROUP);
        
        this.methodGroup = oldVersionGm.getMethodGroup();
        this.methodGroup.setRoleInComposition(GmUseCasePrimaryNode.METHOD_GROUP);
        
        this.extensionPointGroup = oldVersionGm.getExtensionPointGroup();
        this.extensionPointGroup.setRoleInComposition(GmUseCasePrimaryNode.EXTENSIONS);
        
        GmGroup internalStructureGroup = oldVersionGm.getInternalStructureGroup();
        internalStructureGroup.setRoleInComposition(GmUseCasePrimaryNode.INTERNAL_GROUP);
        
        GmFreeZone internalStructureZone = oldVersionGm.getInternalStructureZone();
        internalStructureZone.setRoleInComposition(GmUseCasePrimaryNode.INTERNAL_ZONE);
        
        // Migrate internal structure group/zone
        oldVersionGm.removeChild(internalStructureGroup);
        oldVersionGm.removeChild(internalStructureZone);
        
        this.internalStructure = new GmInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(GmUseCasePrimaryNode.INTERNAL);
        
        this.innerElements = oldVersionGm.getInnerElements();
        this.innerElements.setRoleInComposition(GmUseCasePrimaryNode.INNER);
        
        oldVersionGm.removeChild(this.header);
        super.addChild(this.header);
        oldVersionGm.removeChild(this.attributeGroup);
        super.addChild(this.attributeGroup);
        oldVersionGm.removeChild(this.methodGroup);
        super.addChild(this.methodGroup);
        oldVersionGm.removeChild(this.extensionPointGroup);
        super.addChild(this.extensionPointGroup);
        super.addChild(this.internalStructure);
        oldVersionGm.removeChild(this.innerElements);
        super.addChild(this.innerElements);
    }

    @objid ("5e629a36-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild(GmUseCasePrimaryNode.HEADER);
        this.attributeGroup = (GmGroup) getFirstChild(GmUseCasePrimaryNode.ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(GmUseCasePrimaryNode.METHOD_GROUP);
        this.innerElements = (GmInnerClass) getFirstChild(GmUseCasePrimaryNode.INNER);
        this.extensionPointGroup = (GmGroup) getFirstChild(GmUseCasePrimaryNode.EXTENSIONS);
        this.internalStructure = (GmInternalStructure) getFirstChild(GmUseCasePrimaryNode.INTERNAL);
    }

}
