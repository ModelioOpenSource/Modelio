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
package org.modelio.uml.statikdiagram.editor.elements.naryassoc;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Main class of the central node for a n-ary association.
 * 
 * @author cmarin
 */
@objid ("35c67bf0-55b7-11e2-877f-002564c97630")
public class GmNAssocPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    @objid ("35c67bf6-55b7-11e2-877f-002564c97630")
    private NaryAssociation assoc;

    @objid ("35c67bf9-55b7-11e2-877f-002564c97630")
    static String PROP_REFRESH_BRANCHES = "refresh branches";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35c67bfa-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35c67bfd-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("35c8025a-55b7-11e2-877f-002564c97630")
    public  GmNAssocPrimaryNode() {
        super();
    }

    /**
     * C'tor.
     * @param diagram the diagram in which this gm is created.
     * @param assoc the represented n-ary association. May be null.
     * @param relatedRef a reference to the represented n-ary association. Must Not be null.
     */
    @objid ("35c8025d-55b7-11e2-877f-002564c97630")
    public  GmNAssocPrimaryNode(final IGmDiagram diagram, final NaryAssociation assoc, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.assoc = assoc;
        
    }

    @objid ("35c8026c-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("35c80275-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("35c8027e-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("35c80283-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.assoc;
    }

    @objid ("35c8028a-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmNAssocNode.KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("35c80291-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.assoc;
    }

    @objid ("35c988fb-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNAssocPrimaryNode.");
        switch (readVersion) {
            case 0: {
                read_0(in);
                break;
            }
            default: {
                assert (false) : "version number not covered!";
                // reading as last handled version: 0
                read_0(in);
                break;
            }
        }
        
    }

    @objid ("35c98902-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        firePropertyChange(PROP_REFRESH_BRANCHES, null, this);
    }

    @objid ("35c98905-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNAssocPrimaryNode.", GmNAssocPrimaryNode.MINOR_VERSION);
        
    }

    @objid ("35c9890b-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.assoc = (NaryAssociation) resolveRef(getRepresentedRef());
        
    }

    @objid ("35c98911-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
