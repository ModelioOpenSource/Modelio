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

package org.modelio.diagram.editor.usecase.elements.usecase.v0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.statik.elements.attributegroup.GmAttributeGroup;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClass;
import org.modelio.diagram.editor.statik.elements.internalstructure.GmInternalStructureGroup;
import org.modelio.diagram.editor.statik.elements.internalstructure.GmInternalStructureZone;
import org.modelio.diagram.editor.statik.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.diagram.editor.statik.elements.operationgroup.GmOperationGroup;
import org.modelio.diagram.editor.usecase.elements.usecase.GmExtensionPointGroup;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCaseImageStyleKeys;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCaseSimpleStyleKeys;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCaseStructuredStyleKeys;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCaseUserImageStyleKeys;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e74e9b2-55b7-11e2-877f-002564c97630")
public class _GmUseCase extends GmCompositeNode implements IImageableNode {
    @objid ("5e76701d-55b7-11e2-877f-002564c97630")
    private UseCase useCase;

    @objid ("5e767041-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("5e767044-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5e767046-55b7-11e2-877f-002564c97630")
    private static final String HEADER = "Header";

    @objid ("5e767048-55b7-11e2-877f-002564c97630")
    private static final String ATTRIBUTE_GROUP = "AttributeGroup";

    @objid ("5e76704a-55b7-11e2-877f-002564c97630")
    private static final String METHOD_GROUP = "MethodGroup";

    @objid ("5e76704c-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL_GROUP = "InternalGroup";

    @objid ("5e76704e-55b7-11e2-877f-002564c97630")
    private static final String INTERNAL_ZONE = "InternalZone";

    @objid ("5e767050-55b7-11e2-877f-002564c97630")
    private static final String INNER = "Inner";

    @objid ("5e767052-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_HEADER = "ImageHeader";

    @objid ("5e767054-55b7-11e2-877f-002564c97630")
    private static final String EXTENSIONS = "Extensions";

    @objid ("5e767024-55b7-11e2-877f-002564c97630")
    private static final GmUseCaseSimpleStyleKeys SIMPLEKEYS = new GmUseCaseSimpleStyleKeys();

    @objid ("5e767026-55b7-11e2-877f-002564c97630")
    private static final GmUseCaseStructuredStyleKeys STRUCTKEYS = new GmUseCaseStructuredStyleKeys();

    @objid ("5e767028-55b7-11e2-877f-002564c97630")
    private static final GmUseCaseImageStyleKeys IMAGEKEYS = new GmUseCaseImageStyleKeys();

    @objid ("d9cbc489-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    @objid ("d9cbc48b-55c2-11e2-9337-002564c97630")
    private GmGroup attributeGroup;

    @objid ("d9cbc48d-55c2-11e2-9337-002564c97630")
    private GmGroup methodGroup;

    @objid ("d9cbc48f-55c2-11e2-9337-002564c97630")
    private GmGroup extensionPointGroup;

    @objid ("d9cbc491-55c2-11e2-9337-002564c97630")
    private GmDefaultModelElementLabel imageModeHeader;

    @objid ("d9cbc492-55c2-11e2-9337-002564c97630")
    private GmGroup internalStructureGroup;

    @objid ("d9cbc494-55c2-11e2-9337-002564c97630")
    private GmFreeZone internalStructureZone;

    @objid ("7b759ef5-5eff-11e2-b9cc-001ec947c8cc")
    private GmInnerClass innerElements;

    @objid ("3e4a615b-804c-4227-b996-0c392cbed14d")
    private static final GmUseCaseUserImageStyleKeys USERIMAGE_KEYS = new GmUseCaseUserImageStyleKeys();

    @objid ("5e77f6ba-55b7-11e2-877f-002564c97630")
    public _GmUseCase(IGmDiagram diagram, UseCase theUseCase, MRef ref) {
        super(diagram, ref);
        this.useCase = theUseCase;
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition(_GmUseCase.HEADER);
        
        this.attributeGroup = new GmAttributeGroup(diagram, ref);
        this.attributeGroup.setRoleInComposition(_GmUseCase.ATTRIBUTE_GROUP);
        
        this.methodGroup = new GmOperationGroup(diagram, ref);
        this.methodGroup.setRoleInComposition(_GmUseCase.METHOD_GROUP);
        
        this.extensionPointGroup = new GmExtensionPointGroup(diagram, ref);
        this.extensionPointGroup.setRoleInComposition(_GmUseCase.EXTENSIONS);
        
        this.internalStructureGroup = new GmInternalStructureGroup(diagram, ref);
        this.internalStructureGroup.setRoleInComposition(_GmUseCase.INTERNAL_GROUP);
        
        this.internalStructureZone = new GmInternalStructureZone(diagram, ref);
        this.internalStructureZone.setRoleInComposition(_GmUseCase.INTERNAL_ZONE);
        
        this.imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        this.imageModeHeader.setRoleInComposition(_GmUseCase.IMAGE_HEADER);
        
        this.innerElements = new GmInnerClass(diagram, ref);
        this.innerElements.setRoleInComposition(_GmUseCase.INNER);
        
        super.addChild(this.header);
        super.addChild(this.attributeGroup);
        super.addChild(this.methodGroup);
        super.addChild(this.extensionPointGroup);
        super.addChild(this.internalStructureGroup);
        super.addChild(this.internalStructureZone);
        super.addChild(this.innerElements);
        addChild(this.imageModeHeader);
    }

    @objid ("5e77f6c6-55b7-11e2-877f-002564c97630")
    public _GmUseCase() {
        // empty constructor for the serialization
    }

    @objid ("5e77f6c9-55b7-11e2-877f-002564c97630")
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
            return this.internalStructureGroup.canCreate(type);
        }
        
        if (CollaborationUse.class.isAssignableFrom(type)) {
            return this.internalStructureZone.canCreate(type);
        }
        return false;
    }

    @objid ("5e77f6d1-55b7-11e2-877f-002564c97630")
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
            return this.internalStructureZone.canUnmask(el);
        }
        
        if (el instanceof CollaborationUse) {
            return this.internalStructureZone.canUnmask(el);
        }
        
        if (el instanceof NameSpace) {
            return this.innerElements.canUnmask(el);
        }
        return false;
    }

    @objid ("5e77f6d9-55b7-11e2-877f-002564c97630")
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
            ret = this.internalStructureZone;
            if (!ret.isVisible()) {
                ret = this.internalStructureGroup;
            }
        } else if (CollaborationUse.class.isAssignableFrom(metaclass)) {
            // Collaboration uses are unmasked in the internal structure zone or group
            ret = this.internalStructureGroup;
            if (!ret.isVisible()) {
                ret = this.internalStructureZone;
            }
        } else if (NameSpace.class.isAssignableFrom(metaclass)) {
            // Namespaces are unmasked in the inner classes gm
            ret = getInnerElements().getCompositeFor(metaclass);
        }
        return ret;
    }

    @objid ("5e77f6e3-55b7-11e2-877f-002564c97630")
    @Override
    public UseCase getRepresentedElement() {
        return this.useCase;
    }

    @objid ("5e77f6ea-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("5e77f6f0-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(_GmUseCase.SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("5e77f6f7-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = _GmUseCase.STRUCTKEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = _GmUseCase.SIMPLEKEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = _GmUseCase.IMAGEKEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("5e797d5c-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        RepresentationMode mode = this.getRepresentationMode();
        switch (mode) {
        case SIMPLE:
            return _GmUseCase.SIMPLEKEYS.getStyleKeys();
        case STRUCTURED:
            return _GmUseCase.STRUCTKEYS.getStyleKeys();
        case IMAGE:
            return _GmUseCase.IMAGEKEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("5e797d64-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmUseCase.");
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

    @objid ("5e797d6a-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("5e797d6d-55b7-11e2-877f-002564c97630")
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

    @objid ("5e797d76-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("5e797d7d-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmUseCase.", _GmUseCase.MINOR_VERSION);
    }

    @objid ("5e797d83-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.useCase = (UseCase) resolveRef(getRepresentedRef());
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmModelElementHeader) children.get(0);
        this.attributeGroup = (GmGroup) children.get(1);
        this.methodGroup = (GmGroup) children.get(2);
        this.extensionPointGroup = (GmGroup) children.get(3);
        this.internalStructureGroup = (GmGroup) children.get(4);
        this.internalStructureZone = (GmFreeZone) children.get(5);
        GmGroup innerGroup = (GmGroup) children.get(6);
        GmFreeZone innerZone = (GmFreeZone) children.get(7);
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(8);
        
        // Migrate inner group/zone
        removeChild(innerGroup);
        removeChild(innerZone);
        
        this.innerElements = new GmInnerClass(getDiagram(), getRepresentedRef(), innerZone, innerGroup);
        addChild(this.innerElements, 6);
        
        // Add roles
        this.header.setRoleInComposition(_GmUseCase.HEADER);
        this.attributeGroup.setRoleInComposition(_GmUseCase.ATTRIBUTE_GROUP);
        this.methodGroup.setRoleInComposition(_GmUseCase.METHOD_GROUP);
        this.internalStructureGroup.setRoleInComposition(_GmUseCase.INTERNAL_GROUP);
        this.internalStructureZone.setRoleInComposition(_GmUseCase.INTERNAL_ZONE);
        this.innerElements.setRoleInComposition(_GmUseCase.INNER);
        this.imageModeHeader.setRoleInComposition(_GmUseCase.IMAGE_HEADER);
        this.extensionPointGroup.setRoleInComposition(_GmUseCase.EXTENSIONS);
    }

    @objid ("5e797d88-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return _GmUseCase.MAJOR_VERSION;
    }

    @objid ("5e797d8d-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.useCase = (UseCase) resolveRef(getRepresentedRef());
        
        this.header = (GmModelElementHeader) getFirstChild(_GmUseCase.HEADER);
        this.attributeGroup = (GmGroup) getFirstChild(_GmUseCase.ATTRIBUTE_GROUP);
        this.methodGroup = (GmGroup) getFirstChild(_GmUseCase.METHOD_GROUP);
        this.internalStructureGroup = (GmGroup) getFirstChild(_GmUseCase.INTERNAL_GROUP);
        this.internalStructureZone = (GmFreeZone) getFirstChild(_GmUseCase.INTERNAL_ZONE);
        this.innerElements = (GmInnerClass) getFirstChild(_GmUseCase.INNER);
        this.imageModeHeader = (GmDefaultModelElementLabel) getFirstChild(_GmUseCase.IMAGE_HEADER);
        this.extensionPointGroup = (GmGroup) getFirstChild(_GmUseCase.EXTENSIONS);
    }

    @objid ("5e797d93-55b7-11e2-877f-002564c97630")
    public GmInnerClass getInnerElements() {
        return this.innerElements;
    }

    @objid ("5e797d97-55b7-11e2-877f-002564c97630")
    public GmFreeZone getInternalStructureZone() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.internalStructureZone;
    }

    @objid ("5e7b03fa-55b7-11e2-877f-002564c97630")
    public GmGroup getInternalStructureGroup() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.internalStructureGroup;
    }

    @objid ("5e7b0400-55b7-11e2-877f-002564c97630")
    public GmGroup getExtensionPointGroup() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.extensionPointGroup;
    }

    @objid ("5e7b0406-55b7-11e2-877f-002564c97630")
    public GmGroup getMethodGroup() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.methodGroup;
    }

    @objid ("5e7b040c-55b7-11e2-877f-002564c97630")
    public GmGroup getAttributeGroup() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.attributeGroup;
    }

    @objid ("5e7b0412-55b7-11e2-877f-002564c97630")
    public GmModelElementHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

}
