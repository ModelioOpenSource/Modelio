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

package org.modelio.diagram.editor.statik.elements.generalization;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link Generalization} link.
 * 
 * @author cmarin
 */
@objid ("34eac1d1-55b7-11e2-877f-002564c97630")
public class GmGeneralization extends GmLink {
    @objid ("34eac1d5-55b7-11e2-877f-002564c97630")
    private Generalization element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34eac1da-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34ec485b-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5964b017-5bd5-11e2-9e33-00137282c51b")
    private static final GmGeneralizationStyleKeys styleKeyProvider = new GmGeneralizationStyleKeys();

    /**
     * Create a generalization link model.
     * 
     * @param diagram The diagram
     * @param generalization The represented generalization, may be null
     * @param ref The represented generalization reference, may not be null
     */
    @objid ("34ec485d-55b7-11e2-877f-002564c97630")
    public GmGeneralization(IGmDiagram diagram, Generalization generalization, MRef ref) {
        super(diagram, ref);
        this.element = generalization;
    }

    @objid ("34ec4869-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmGeneralization.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("34ec4873-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmGeneralization.styleKeyProvider.getStyleKeys();
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("34ec487c-55b7-11e2-877f-002564c97630")
    public GmGeneralization() {
        // Nothing to do.
    }

    @objid ("34ec487f-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (Generalization) resolveRef(this.getRepresentedRef());
    }

    @objid ("34ec4885-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getSubType();
    }

    @objid ("34ec488c-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getSuperType();
    }

    @objid ("34ec4893-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("34ec489a-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("34edceff-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmGeneralization.", GmGeneralization.MINOR_VERSION);
    }

    @objid ("34edcf05-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmGeneralization.MAJOR_VERSION;
    }

    @objid ("d6c7fb50-0d4b-41d3-8362-c8bb3dd39b12")
    @Override
    protected void read_GmLinkV0_roles() {
        // nothing
    }

}
