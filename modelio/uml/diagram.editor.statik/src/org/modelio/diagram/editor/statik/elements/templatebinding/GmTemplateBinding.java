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

package org.modelio.diagram.editor.statik.elements.templatebinding;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an Association.
 */
@objid ("36d616fa-55b7-11e2-877f-002564c97630")
public final class GmTemplateBinding extends GmLink {
    @objid ("36d616fe-55b7-11e2-877f-002564c97630")
    private TemplateBinding element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("36d61703-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("36d61706-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("65101291-5bd5-11e2-9e33-00137282c51b")
    private static final TemplateBindingStructuredStyleKeys STRUCTURED_KEYS = new TemplateBindingStructuredStyleKeys();

    /**
     * Constructor for deserialization.
     */
    @objid ("36d61708-55b7-11e2-877f-002564c97630")
    public GmTemplateBinding() {
        // Nothing to do.
    }

    /**
     * Creates a GmElementImport.
     * @param diagram The diagram containing the link.
     * @param role The represented element.
     * @param ref The represented element reference. May not be null.
     */
    @objid ("36d6170b-55b7-11e2-877f-002564c97630")
    public GmTemplateBinding(IGmDiagram diagram, TemplateBinding role, MRef ref) {
        super(diagram, ref);
        
        this.element = role;
        
        if (role != null) {
            // Create extensions
            addExtension(ExtensionLocation.MiddleSE, ROLE_MAIN_LABEL, new GmTemplateBindingHeader(diagram, ref));
        }
    }

    @objid ("36d61717-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("36d61721-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("36d6172a-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (TemplateBinding) resolveRef(this.getRepresentedRef());
    }

    @objid ("36d61730-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        MObject ret = this.element.getBoundElement();
        if (ret == null) {
            ret = this.element.getBoundOperation();
        }
        return ret;
    }

    @objid ("36d61737-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        MObject ret = this.element.getInstanciatedTemplate();
        if (ret == null) {
            ret = this.element.getInstanciatedTemplateOperation();
        }
        return ret;
    }

    @objid ("36d79d9e-55b7-11e2-877f-002564c97630")
    @Override
    public TemplateBinding getRepresentedElement() {
        return this.element;
    }

    @objid ("36d79da5-55b7-11e2-877f-002564c97630")
    @Override
    public TemplateBinding getRelatedElement() {
        return this.element;
    }

    @objid ("36d79dac-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmTemplateBinding.", GmTemplateBinding.MINOR_VERSION);
    }

    @objid ("36d79db2-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("84f4cfbc-d101-4240-bf90-b4d86bcbe22e")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
