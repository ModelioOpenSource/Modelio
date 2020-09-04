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

package org.modelio.diagram.editor.statik.elements.slot;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link AttributeLink}.
 * <p>
 * To be unmasked in <tt>GmGroups</tt>.
 * 
 * @author cmarin
 */
@objid ("369c1b3a-55b7-11e2-877f-002564c97630")
public class GmSlot extends GmDefaultModelElementLabel {
    @objid ("369c1b3e-55b7-11e2-877f-002564c97630")
    private AttributeLink element = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("369c1b41-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("369da1d9-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param el the represented slot, may be <i>null</i>.
     * @param ref a reference to the represented slot.
     */
    @objid ("369da1db-55b7-11e2-877f-002564c97630")
    public GmSlot(IGmDiagram diagram, AttributeLink el, MRef ref) {
        super(diagram, ref);
        this.element = el;
    }

    /**
     * Empty constructor needed for deserialization only.
     */
    @objid ("369da1e7-55b7-11e2-877f-002564c97630")
    public GmSlot() {
        // Empty constructor needed for deserialization.
    }

    @objid ("369da20d-55b7-11e2-877f-002564c97630")
    @Override
    public AttributeLink getRelatedElement() {
        return this.element;
    }

    @objid ("369da214-55b7-11e2-877f-002564c97630")
    @Override
    public AttributeLink getRepresentedElement() {
        return this.element;
    }

    @objid ("369f287a-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSlot.");
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

    @objid ("369f2880-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return computeSignature(getRelatedElement());
    }

    @objid ("369f2885-55b7-11e2-877f-002564c97630")
    private String computeSignature(AttributeLink att) {
        final Attribute attribute = att.getBase();
        final GeneralClass type = (attribute == null) ? null : attribute.getType();
        String typename = null;
        if (type != null)
            typename = type.getName();
        
        StringBuilder ret = new StringBuilder();
        
        if (attribute != null) {
            ret.append(attribute.getName());
        } else {
            ret.append(att.getName());
        }
        
        if (typename != null) {
            ret.append(" : ");
            ret.append(typename);
        } else {
            ret.append(" : <no type>");
        }
        
        if (!att.getValue().isEmpty()) {
            ret.append(" = ");
            ret.append(att.getValue());
        }
        return ret.toString();
    }

    @objid ("369f288c-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (metakey == MetaKey.FONT) {
            return super.getStyleKey(MetaKey.AttGroup.ATTFONT);
        } else if (metakey == MetaKey.SHOWSTEREOTYPES) {
            return super.getStyleKey(MetaKey.AttGroup.ATTSHOWSTEREOTYPES);
        } else if (metakey == MetaKey.SHOWTAGS) {
            return super.getStyleKey(MetaKey.AttGroup.ATTSHOWTAGS);
        } else if (metakey == MetaKey.TEXTCOLOR) {
            return super.getStyleKey(MetaKey.AttGroup.ATTTEXTCOLOR);
        }
        return null;
    }

    @objid ("369f2896-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSlot.", GmSlot.MINOR_VERSION);
    }

    @objid ("369f289c-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (AttributeLink) resolveRef(this.getRepresentedRef());
    }

    @objid ("369f28a1-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * A slot is a flat label.
     */
    @objid ("7d6e160a-1ad4-4d6b-ab49-68a3acdf77cd")
    @Override
    public boolean isFlat() {
        return true;
    }

}
