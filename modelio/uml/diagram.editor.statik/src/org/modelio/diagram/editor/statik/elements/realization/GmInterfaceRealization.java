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

package org.modelio.diagram.editor.statik.elements.realization;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link InterfaceRealization} link.
 * 
 * @author fpoyer
 */
@objid ("3666b35a-55b7-11e2-877f-002564c97630")
public class GmInterfaceRealization extends GmLink {
    @objid ("3666b35e-55b7-11e2-877f-002564c97630")
    private InterfaceRealization element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3666b363-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3666b366-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("635961e1-5bd5-11e2-9e33-00137282c51b")
    private static final GmInterfaceRealizationStyleKeys styleKeyProvider = new GmInterfaceRealizationStyleKeys();

    /**
     * Create a interface realization link model.
     * 
     * @param diagram The diagram
     * @param generalization The represented link, may be null
     * @param ref The represented link reference, may not be null
     */
    @objid ("3666b368-55b7-11e2-877f-002564c97630")
    public GmInterfaceRealization(IGmDiagram diagram, InterfaceRealization generalization, MRef ref) {
        super(diagram, ref);
        this.element = generalization;
    }

    @objid ("3666b374-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("3666b37e-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return styleKeyProvider.getStyleKeys();
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("366839fd-55b7-11e2-877f-002564c97630")
    public GmInterfaceRealization() {
        // Nothing to do.
    }

    @objid ("36683a00-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (InterfaceRealization) resolveRef(this.getRepresentedRef());
    }

    @objid ("36683a06-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getImplementer();
    }

    @objid ("36683a0d-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getImplemented();
    }

    @objid ("36683a14-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("36683a1b-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("36683a22-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInterfaceRealization.", GmInterfaceRealization.MINOR_VERSION);
    }

    @objid ("36683a28-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("c9ce8805-4375-4eec-b25b-52efd103629d")
    @Override
    protected void read_GmLinkV0_roles() {
        // nothing
    }

}
