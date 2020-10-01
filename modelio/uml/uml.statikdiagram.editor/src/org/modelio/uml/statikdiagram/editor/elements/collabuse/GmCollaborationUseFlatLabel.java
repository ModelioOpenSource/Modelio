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

package org.modelio.uml.statikdiagram.editor.elements.collabuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link CollaborationUse} label.
 * <p>
 * Extends {@link GmModelElementLabel}.
 */
@objid ("3476ca50-55b7-11e2-877f-002564c97630")
public class GmCollaborationUseFlatLabel extends GmDefaultModelElementLabel {
    @objid ("3476ca54-55b7-11e2-877f-002564c97630")
    private CollaborationUse element = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3476ca57-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3476ca5a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("3476ca5c-55b7-11e2-877f-002564c97630")
    public GmCollaborationUseFlatLabel() {
        // Empty constructor needed for (de-)serialization.
    }

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this node is unmasked.
     * @param el the represented element, may be <i>null</i>.
     * @param ref a reference to the represented element.
     */
    @objid ("3476ca5f-55b7-11e2-877f-002564c97630")
    public GmCollaborationUseFlatLabel(IGmDiagram diagram, CollaborationUse el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        setShowMetaclassIcon(true);
    }

    @objid ("347850ee-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRelatedElement() {
        return this.element;
    }

    @objid ("347850f5-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRepresentedElement() {
        return this.element;
    }

    @objid ("347850fc-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (metakey == MetaKey.FONT) {
            return super.getStyleKey(MetaKey.InternalGroup.INTFONT);
        } else if (metakey == MetaKey.SHOWSTEREOTYPES) {
            return super.getStyleKey(MetaKey.InternalGroup.INTSHOWSTEREOTYPES);
        } else if (metakey == MetaKey.SHOWTAGS) {
            return super.getStyleKey(MetaKey.InternalGroup.INTSHOWTAGS);
        } else if (metakey == MetaKey.TEXTCOLOR) {
            return super.getStyleKey(MetaKey.InternalGroup.INTTEXTCOLOR);
        }
        return null;
    }

    @objid ("34785110-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollaborationUseFlatLabel.");
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

    @objid ("34785116-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return computeSignature(getRelatedElement());
    }

    @objid ("3478511b-55b7-11e2-877f-002564c97630")
    private String computeSignature(CollaborationUse CollaborationUse) {
        String stype = "";
        
        final Collaboration type = CollaborationUse.getType();
        if (type != null) {
            stype = type.getName();
        }
        return CollaborationUse.getName() + " : " + stype;
    }

    @objid ("3479d77d-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCollaborationUseFlatLabel.", MINOR_VERSION);
    }

    @objid ("3479d783-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (CollaborationUse) resolveRef(getRepresentedRef());
    }

    @objid ("3479d788-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
