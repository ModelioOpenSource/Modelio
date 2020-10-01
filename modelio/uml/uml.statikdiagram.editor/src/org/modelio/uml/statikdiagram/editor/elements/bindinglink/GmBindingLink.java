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

package org.modelio.uml.statikdiagram.editor.elements.bindinglink;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an Association.
 */
@objid ("34121516-55b7-11e2-877f-002564c97630")
public class GmBindingLink extends GmLink {
    @objid ("3412151a-55b7-11e2-877f-002564c97630")
    private Binding element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3412151f-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34121522-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("3412151d-55b7-11e2-877f-002564c97630")
    private static final BindingLinkStyleKeys STRUCTURED_KEYS = new BindingLinkStyleKeys();

    /**
     * Constructor for deserialization.
     */
    @objid ("34121524-55b7-11e2-877f-002564c97630")
    public GmBindingLink() {
        // Nothing to do.
    }

    /**
     * Creates a GmElementImport.
     * 
     * @param diagram The diagram containing the link.
     * @param role The represented element.
     * @param ref The represented element reference. May not be null.
     */
    @objid ("34121527-55b7-11e2-877f-002564c97630")
    public GmBindingLink(IGmDiagram diagram, Binding role, MRef ref) {
        super(diagram, ref);
        
        this.element = role;
        
        if (role != null) {
            // Create extensions
        
            addExtension(ExtensionLocation.TargetNW, ROLE_MAIN_LABEL, new GmBindingLinkHeader(diagram, ref));
        }
    }

    @objid ("34121533-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getOwner();
    }

    @objid ("34139b9a-55b7-11e2-877f-002564c97630")
    @Override
    public Binding getRelatedElement() {
        return this.element;
    }

    @objid ("34139ba1-55b7-11e2-877f-002564c97630")
    @Override
    public Binding getRepresentedElement() {
        return this.element;
    }

    @objid ("34139ba8-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("34139bb2-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("34139bbb-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getRepresentedFeature();
    }

    @objid ("34139bc2-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (Binding) resolveRef(getRepresentedRef());
    }

    @objid ("34139bc8-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBindingLink.", GmBindingLink.MINOR_VERSION);
    }

    @objid ("34139bce-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("43ee1256-c6d5-4790-8bf8-bc4c4548ab99")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
