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

package org.modelio.diagram.editor.statik.elements.elementimport;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an Association.
 */
@objid ("34c7a966-55b7-11e2-877f-002564c97630")
public final class GmElementImport extends GmLink {
    @objid ("34c7a96a-55b7-11e2-877f-002564c97630")
    private ElementImport element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34c7a96f-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34c92ffb-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5c6202e8-5bd5-11e2-9e33-00137282c51b")
    private static final GmElementImportStructuredStyleKeys STRUCTURED_KEYS = new GmElementImportStructuredStyleKeys();

    /**
     * Constructor for deserialization.
     */
    @objid ("34c92ffd-55b7-11e2-877f-002564c97630")
    public GmElementImport() {
        // Nothing to do.
    }

    /**
     * Creates a GmElementImport.
     * 
     * @param diagram The diagram containing the link.
     * @param role The represented element.
     * @param ref The represented element reference. May not be null.
     */
    @objid ("34c93000-55b7-11e2-877f-002564c97630")
    public GmElementImport(IGmDiagram diagram, ElementImport role, MRef ref) {
        super(diagram, ref);
        
        this.element = role;
        
        if (role != null) {
            // Create extensions
            addExtension(ExtensionLocation.MiddleSE, ROLE_MAIN_LABEL, new GmElementImportHeader(diagram, ref));
        }
    }

    @objid ("34c9300c-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("34c93016-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("34c9301f-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (ElementImport) resolveRef(this.getRepresentedRef());
    }

    @objid ("34c93025-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        MObject ret = this.element.getImportingNameSpace();
        if (ret == null) {
            ret = this.element.getImportingOperation();
        }
        return ret;
    }

    @objid ("34c9302c-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getImportedElement();
    }

    @objid ("34c93033-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("34cab69d-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("34cab6a4-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmElementImport.", GmElementImport.MINOR_VERSION);
    }

    @objid ("34cab6aa-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("a6441b59-62b6-4800-b698-912a4c434f5f")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
