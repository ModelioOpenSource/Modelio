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
package org.modelio.uml.statikdiagram.editor.elements.raisedexception;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an Association.
 */
@objid ("365c04fa-55b7-11e2-877f-002564c97630")
public final class GmRaisedException extends GmLink {
    @objid ("365d8b9a-55b7-11e2-877f-002564c97630")
    private RaisedException element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("365d8b9f-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("365d8ba2-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("633f2803-5bd5-11e2-9e33-00137282c51b")
    private static final RaisedExceptionStyleKeys STRUCTURED_KEYS = new RaisedExceptionStyleKeys();

    /**
     * Constructor for deserialization.
     */
    @objid ("365d8ba4-55b7-11e2-877f-002564c97630")
    public  GmRaisedException() {
        // Nothing to do.
    }

    /**
     * Creates a GmElementImport.
     * @param diagram The diagram containing the link.
     * @param role The represented element.
     * @param ref The represented element reference. May not be null.
     */
    @objid ("365d8ba7-55b7-11e2-877f-002564c97630")
    public  GmRaisedException(IGmDiagram diagram, RaisedException role, MRef ref) {
        super(diagram, ref);
        
        this.element = role;
        
        if (role != null) {
            // Create extensions
            addExtension(ExtensionLocation.MiddleSE, ROLE_MAIN_LABEL, new GmRaisedExceptionHeader(diagram, ref));
        }
        
    }

    @objid ("365d8bb3-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getThrower();
    }

    @objid ("365d8bba-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("365d8bc1-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("365d8bc8-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("365d8bd2-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("365f1241-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getThrownType();
    }

    @objid ("365f1248-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (RaisedException) resolveRef(this.getRepresentedRef());
        
    }

    @objid ("365f124e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmRaisedException.", GmRaisedException.MINOR_VERSION);
        
    }

    @objid ("365f1254-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("1afe3187-c56f-4eae-8e11-c094294109e9")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
