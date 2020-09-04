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

package org.modelio.diagram.editor.statik.elements.narylink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Main class of the central node for a n-ary {@link NaryLink}.
 * 
 * @author cmarin
 */
@objid ("35eb1ac0-55b7-11e2-877f-002564c97630")
public class GmNLinkPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    @objid ("35eb1ac9-55b7-11e2-877f-002564c97630")
     static final String PROP_REFRESH_BRANCHES = "refresh branches";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35eb1acb-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35eb1ace-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("a4995d96-3e74-4f30-9c98-50f88998b076")
    private NaryLink assoc;

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("35eb1ad0-55b7-11e2-877f-002564c97630")
    public GmNLinkPrimaryNode() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this gm is created.
     * @param assoc the represented n-ary association. May be null.
     * @param relatedRef a reference to the represented n-ary association. Must Not be null.
     */
    @objid ("35eb1ad3-55b7-11e2-877f-002564c97630")
    public GmNLinkPrimaryNode(final IGmDiagram diagram, final NaryLink assoc, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.assoc = assoc;
    }

    @objid ("35eb1ae2-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("35eb1aeb-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("35eb1af4-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmNLinkNode.KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("35eb1afb-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Unmask missing branches
        firePropertyChange(PROP_REFRESH_BRANCHES, null, this);
    }

    @objid ("35eca15b-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.assoc;
    }

    @objid ("35eca162-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.assoc;
    }

    @objid ("35eca169-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNLinkPrimaryNode.");
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

    @objid ("35eca170-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("35eca175-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNLinkPrimaryNode.", GmNLinkPrimaryNode.MINOR_VERSION);
    }

    @objid ("35eca17b-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.assoc = (NaryLink) resolveRef(getRepresentedRef());
    }

    @objid ("35eca181-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
