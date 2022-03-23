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
package org.modelio.uml.deploymentdiagram.editor.elements.manifestation;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link Manifestation}.
 */
@objid ("972fba7a-55b6-11e2-877f-002564c97630")
public class GmManifestation extends GmLink {
    @objid ("972fba7e-55b6-11e2-877f-002564c97630")
    private Manifestation theManifestation;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("972fba83-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("972fba86-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("43644c69-5beb-11e2-9e33-00137282c51b")
    private static final GmManifestationStyleKeys styleKeyProvider = new GmManifestationStyleKeys();

    /**
     * For deserialization only.
     */
    @objid ("972fba88-55b6-11e2-877f-002564c97630")
    public  GmManifestation() {
        
    }

    /**
     * Initialize a control flow graphic model.
     * @param diagram The owning diagram
     * @param theManifestation The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("972fba8b-55b6-11e2-877f-002564c97630")
    public  GmManifestation(IGmDiagram diagram, Manifestation theManifestation, MRef ref) {
        super(diagram, ref);
        this.theManifestation = theManifestation;
        
        GmDefaultModelElementLabel extension = new GmDefaultModelElementLabel(diagram, ref);
        extension.setShowLabel(false);
        extension.setShowMetaclassKeyword(true);
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, extension);
        
    }

    @objid ("972fba97-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.theManifestation;
    }

    @objid ("972fba9e-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmManifestation.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("972fbaa8-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmManifestation.styleKeyProvider.getStyleKeys();
    }

    @objid ("97314121-55b6-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        
        this.theManifestation = (Manifestation) resolveRef(getRepresentedRef());
        
    }

    @objid ("97314127-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.theManifestation.getOwner();
    }

    @objid ("9731412e-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.theManifestation.getUtilizedElement();
    }

    @objid ("97314135-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("9731413c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmManifestation.", GmManifestation.MINOR_VERSION);
        
    }

    @objid ("97314142-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmManifestation.MAJOR_VERSION;
    }

    @objid ("a91f0b94-bd98-41de-bd78-4ef79e7234d8")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
