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

package org.modelio.diagram.editor.statik.elements.packageimport;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an Association.
 */
@objid ("360384ba-55b7-11e2-877f-002564c97630")
public final class GmPackageImport extends GmLink {
    @objid ("360384be-55b7-11e2-877f-002564c97630")
    private PackageImport element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("360384c3-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("36050b5b-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("61e57309-5bd5-11e2-9e33-00137282c51b")
    private static final PackageImportStyleKeys STRUCTURED_KEYS = new PackageImportStyleKeys();

    /**
     * Constructor for deserialization.
     */
    @objid ("36050b5d-55b7-11e2-877f-002564c97630")
    public GmPackageImport() {
        // Nothing to do.
    }

    /**
     * Creates a GmElementImport.
     * 
     * @param diagram The diagram containing the link.
     * @param role The represented element.
     * @param ref The represented element reference. May not be null.
     */
    @objid ("36050b60-55b7-11e2-877f-002564c97630")
    public GmPackageImport(IGmDiagram diagram, PackageImport role, MRef ref) {
        super(diagram, ref);
        
        this.element = role;
        
        if (role != null) {
            // Create extensions
            addExtension(ExtensionLocation.MiddleSE, ROLE_MAIN_LABEL, new GmPackageImportHeader(diagram, ref));
        }
    }

    @objid ("36050b6c-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        MObject ret = this.element.getImportingNameSpace();
        if (ret == null) {
            ret = this.element.getImportingOperation();
        }
        return ret;
    }

    @objid ("36050b73-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("36050b7a-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("36050b81-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("36050b8b-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("360691f9-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getImportedPackage();
    }

    @objid ("36069200-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (PackageImport) resolveRef(this.getRepresentedRef());
    }

    @objid ("36069206-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPackageImport.", MINOR_VERSION);
    }

    @objid ("3606920c-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("71d6bfcc-8e8c-47b3-81c7-dee5dd2838f7")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
