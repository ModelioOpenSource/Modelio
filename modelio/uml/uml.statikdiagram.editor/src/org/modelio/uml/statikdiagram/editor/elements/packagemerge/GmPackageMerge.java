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
package org.modelio.uml.statikdiagram.editor.elements.packagemerge;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an Association.
 */
@objid ("360cac7a-55b7-11e2-877f-002564c97630")
public final class GmPackageMerge extends GmLink {
    @objid ("360cac7e-55b7-11e2-877f-002564c97630")
    private PackageMerge element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("360cac83-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("360e331b-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("62105d5d-5bd5-11e2-9e33-00137282c51b")
    private static final PackageMergeStyleKeys STRUCTURED_KEYS = new PackageMergeStyleKeys();

    /**
     * Constructor for deserialization.
     */
    @objid ("360e331d-55b7-11e2-877f-002564c97630")
    public  GmPackageMerge() {
        // Nothing to do.
    }

    /**
     * Creates a GmElementImport.
     * @param diagram The diagram containing the link.
     * @param role The represented element.
     * @param ref The represented element reference. May not be null.
     */
    @objid ("360e3320-55b7-11e2-877f-002564c97630")
    public  GmPackageMerge(IGmDiagram diagram, PackageMerge role, MRef ref) {
        super(diagram, ref);
        
        this.element = role;
        
        if (role != null) {
            // Create extensions
            addExtension(ExtensionLocation.MiddleSE, ROLE_MAIN_LABEL, new GmPackageMergeHeader(diagram, ref));
        }
        
    }

    @objid ("360e332c-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getReceivingPackage();
    }

    @objid ("360e3333-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("360e333a-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("360e3341-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("360e334b-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("360e3354-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getMergedPackage();
    }

    @objid ("360e335b-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (PackageMerge) resolveRef(this.getRepresentedRef());
        
    }

    @objid ("360fb9be-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPackageMerge.", GmPackageMerge.MINOR_VERSION);
        
    }

    @objid ("360fb9c4-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("0b06a9dd-1e3a-4640-a2db-c46bcf916551")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
