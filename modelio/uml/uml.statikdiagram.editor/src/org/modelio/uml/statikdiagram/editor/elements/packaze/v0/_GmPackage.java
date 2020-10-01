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

package org.modelio.uml.statikdiagram.editor.elements.packaze.v0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.statikdiagram.editor.elements.namespaceheader.GmNamespaceHeader;
import org.modelio.uml.statikdiagram.editor.elements.packaze.GmPackageBody;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic representation for a {@link Package}.
 * 
 * @author cmarin
 */
@objid ("3635df92-55b7-11e2-877f-002564c97630")
public class _GmPackage extends GmCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("36376607-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3637660a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("363765fd-55b7-11e2-877f-002564c97630")
    private GmPackageBody body;

    /**
     * Structured mode style keys.
     */
    @objid ("363765fe-55b7-11e2-877f-002564c97630")
    public static GmPackageStructuredStyleKeys STRUCTURED_KEYS = new GmPackageStructuredStyleKeys();

    /**
     * Simple mode style keys.
     */
    @objid ("36376600-55b7-11e2-877f-002564c97630")
    public static GmPackageSimpleStyleKeys SIMPLE_KEYS = new GmPackageSimpleStyleKeys();

    /**
     * Image mode style keys.
     */
    @objid ("36376602-55b7-11e2-877f-002564c97630")
    public static GmPackageImageStyleKeys IMAGE_KEYS = new GmPackageImageStyleKeys();

    @objid ("a74a149a-55c2-11e2-9337-002564c97630")
    private Package element;

    @objid ("a74a149b-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Image mode style keys.
     */
    @objid ("d8a63ec7-2da7-4c9a-bf67-8691fb27024e")
    public static GmPackageUserImageStyleKeys USERIMAGE_KEYS = new GmPackageUserImageStyleKeys();

    /**
     * Creates a GmPackage.
     * 
     * @param diagram The diagram.
     * @param thePackage The represented package, may be <tt>null</tt>
     * @param ref The represented package reference, may not be <tt>null</tt>.
     */
    @objid ("3637660c-55b7-11e2-877f-002564c97630")
    public _GmPackage(IGmDiagram diagram, Package thePackage, MRef ref) {
        super(diagram, ref);
        
        this.element = thePackage;
        
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
    @objid ("36376618-55b7-11e2-877f-002564c97630")
    public _GmPackage() {
    }

    @objid ("3637661b-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        // Add all sub nodes to the body, except the header and the body themselves.
        if (!child.getRepresentedRef().equals(this.getRepresentedRef()))
            this.body.addChild(child);
        else
            super.addChild(child);
    }

    @objid ("36376621-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Must be of the proper metaclass
        if ((NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type)) ||
                (Instance.class.isAssignableFrom(type)) ||
                (Behavior.class.isAssignableFrom(type)))
            return true;
        return false;
    }

    @objid ("36376629-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        // Must belong to the package
        if (!el.getCompositionOwner().equals(this.getRelatedElement())) {
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
    @objid ("36376630-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return this.body;
    }

    @objid ("3638eca2-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("3638eca9-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null)
            return ret;
        
        ret = SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null)
            return ret;
        
        ret = IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("3638ecb3-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKeys();
        case IMAGE:
            return IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("3638ecbb-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPackage.");
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

    @objid ("3638ecc1-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("3638ecc8-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    /**
     * Get the stereotype image to display.
     * 
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("3638eccf-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(this.element);
    }

    @objid ("3638ecd5-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case IMAGE:
            ret = new ArrayList<>(1);
            ret.add(this.header);
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

    @objid ("363a7340-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed 
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("363a7343-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPackage.", _GmPackage.MINOR_VERSION);
    }

    @objid ("363a7349-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.element = (Package) resolveRef(this.getRepresentedRef());
        
        this.header = (GmModelElementHeader) getFirstChild("header");
        this.body = (GmPackageBody) getFirstChild("body");
    }

    @objid ("363a734e-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("363a7353-55b7-11e2-877f-002564c97630")
    public GmPackageBody getBody() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.body;
    }

    @objid ("363a7357-55b7-11e2-877f-002564c97630")
    public GmModelElementHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

}
