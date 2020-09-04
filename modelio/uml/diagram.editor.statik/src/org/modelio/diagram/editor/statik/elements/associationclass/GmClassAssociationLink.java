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

package org.modelio.diagram.editor.statik.elements.associationclass;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link ClassAssociation} link.
 * 
 * @author cmarin
 */
@objid ("33f51728-55b7-11e2-877f-002564c97630")
public class GmClassAssociationLink extends GmLink {
    @objid ("33f5172c-55b7-11e2-877f-002564c97630")
    private ClassAssociation element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("33f51731-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("33f51734-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("33f5172f-55b7-11e2-877f-002564c97630")
    private static final ClassAssociationLinkStyleKeys styleKeyProvider = new ClassAssociationLinkStyleKeys();

    /**
     * Create a generalization link model.
     * @param diagram The diagram
     * @param generalization The represented ClassAssociation, may be null
     * @param ref The represented generalization reference, may not be null
     */
    @objid ("33f51736-55b7-11e2-877f-002564c97630")
    public GmClassAssociationLink(IGmDiagram diagram, ClassAssociation generalization, MRef ref) {
        super(diagram, ref);
        this.element = generalization;
        
        //addExtension(ExtensionLocation.MiddleSE, new GmDefaultModelElementHeader(diagram, ref));
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("33f51742-55b7-11e2-877f-002564c97630")
    public GmClassAssociationLink() {
        // Nothing to do.
    }

    @objid ("33f51745-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        final Association assoc = this.element.getAssociationPart();
        if (assoc != null) {
            return assoc;
        }
        return this.element.getNaryAssociationPart();
    }

    @objid ("33f5174c-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("33f51753-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("33f5175a-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("33f69dbf-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return styleKeyProvider.getStyleKeys();
    }

    @objid ("33f69dc8-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getClassPart();
    }

    @objid ("33f69dcf-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (ClassAssociation) resolveRef(this.getRepresentedRef());
    }

    @objid ("33f69dd5-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmClassAssociationLink.", GmClassAssociationLink.MINOR_VERSION);
    }

    @objid ("33f69ddb-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("8279ad01-2ec7-4a77-bbd3-6222eaa20681")
    @Override
    protected void read_GmLinkV0_roles() {
        // Nothing to do.
    }

}
