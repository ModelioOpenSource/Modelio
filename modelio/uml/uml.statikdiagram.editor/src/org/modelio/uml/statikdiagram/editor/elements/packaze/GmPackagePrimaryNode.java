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
package org.modelio.uml.statikdiagram.editor.elements.packaze;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
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
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.statikdiagram.editor.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.uml.statikdiagram.editor.elements.packaze.v0._GmPackage;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Primary Node for package.
 * 
 * @author fpoyer
 */
@objid ("3622093f-55b7-11e2-877f-002564c97630")
public class GmPackagePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    @objid ("36220949-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3622094b-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("a4faf46c-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    @objid ("626fbb6c-5bd5-11e2-9e33-00137282c51b")
    private GmPackageBody body;

    /**
     * Creates a GmPackagePrimaryNode.
     * @param diagram The diagram.
     * @param ref The represented package reference, may not be <tt>null</tt>.
     */
    @objid ("3622094e-55b7-11e2-877f-002564c97630")
    public  GmPackagePrimaryNode(IGmDiagram diagram, MRef ref) {
        super(diagram, ref);
        
        this.header = new GmNamespaceHeader(diagram, ref);
        this.header.setRoleInComposition("header");
        
        this.body = new GmPackageBody(diagram, ref);
        this.body.setRoleInComposition("body");
        
        super.addChild(this.header);
        super.addChild(this.body);
        
    }

    /**
     * For deserialization only.
     */
    @objid ("36220957-55b7-11e2-877f-002564c97630")
    public  GmPackagePrimaryNode() {
        
    }

    @objid ("3622095a-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        // Add all sub nodes to the body, except the header and the body themselves.
        if (!child.getRepresentedRef().equals(this.getRepresentedRef())) {
            this.body.addChild(child);
        } else {
            super.addChild(child);
        }
        
    }

    @objid ("36220960-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Must be of the proper metaclass
        if ((NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type)) ||
                (Instance.class.isAssignableFrom(type)) ||
                (Behavior.class.isAssignableFrom(type))) {
            return true;
        }
        return false;
    }

    @objid ("36220968-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        // Must belong to the package
        if (!Objects.equals(el.getCompositionOwner(), this.getRelatedElement())) {
            return false;
        }
        
        if (el instanceof NameSpace) {
            final NameSpace packaze = (NameSpace) el;
        
            StyleKey.UmaskByVisibilityStragegy unmaskmode = getDisplayedStyle().getProperty(GmPackageStructuredStyleKeys.UNMASKINGSTRATEGY);
            switch (unmaskmode) {
            case ALL:
                return true;
            case ALL_PUBLIC:
                return packaze.getVisibility() == VisibilityMode.PUBLIC;
            case ALL_NON_PRIVATE:
                return packaze.getVisibility() != VisibilityMode.PRIVATE;
            case MANUAL:
            default:
                return true;
            }
        } else if (el instanceof Instance) {
            return true;
        } else if (el instanceof Behavior) {
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * All sub nodes are added to the body node.
     */
    @objid ("3622096f-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return this.body;
    }

    @objid ("36238fdd-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmPackage.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("36238fe4-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPackagePrimaryNode.");
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

    /**
     * Get the stereotype image to display.
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("36238fea-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("36238ff0-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case USER_IMAGE:
        case IMAGE:
            ret = Collections.emptyList();
            break;
        default:
            ret = super.getVisibleChildren();
            break;
        }
        return ret;
    }

    @objid ("36238ff9-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
        
    }

    @objid ("36238ffc-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmPackagePrimaryNode.", Integer.valueOf(GmPackagePrimaryNode.MINOR_VERSION));
        
    }

    @objid ("36239002-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.header = (GmModelElementHeader) getFirstChild("header");
        this.body = (GmPackageBody) getFirstChild("body");
        
    }

    @objid ("36239007-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmPackagePrimaryNode.MAJOR_VERSION;
    }

    /**
     * Returns the body of this package.
     * @return the GmCompositeNode instance that is the body of this package.
     */
    @objid ("3623900c-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getBody() {
        return this.body;
    }

    /**
     * Migration constructor.
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("36251679-55b7-11e2-877f-002564c97630")
     GmPackagePrimaryNode(final _GmPackage oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        
        this.header = oldVersionGm.getHeader();
        this.header.setRoleInComposition("header");
        
        this.body = oldVersionGm.getBody();
        this.body.setRoleInComposition("body");
        
        oldVersionGm.removeChild(this.header);
        super.addChild(this.header);
        oldVersionGm.removeChild(this.body);
        super.addChild(this.body);
        
    }

}
