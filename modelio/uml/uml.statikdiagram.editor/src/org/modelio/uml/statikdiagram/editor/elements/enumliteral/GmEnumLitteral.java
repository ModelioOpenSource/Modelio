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

package org.modelio.uml.statikdiagram.editor.elements.enumliteral;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.uml.statikdiagram.editor.elements.enumeration.EnumStructuredStyleKeys;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Attribute} label.
 * <p>
 * Extends {@link GmModelElementLabel}.
 */
@objid ("34e3209a-55b7-11e2-877f-002564c97630")
public class GmEnumLitteral extends GmDefaultModelElementLabel {
    @objid ("34e3209e-55b7-11e2-877f-002564c97630")
    private EnumerationLiteral element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34e320a1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34e320a4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("34e320a6-55b7-11e2-877f-002564c97630")
    public GmEnumLitteral() {
    }

    /**
     * Create an attribute representation.
     * 
     * @param diagram The diagram
     * @param el The represented attribute, may be null.
     * @param ref The represented attribute reference, may not be null.
     */
    @objid ("34e320a9-55b7-11e2-877f-002564c97630")
    public GmEnumLitteral(IGmDiagram diagram, EnumerationLiteral el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        init();
    }

    @objid ("34e4a74d-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (metakey == MetaKey.FONT) {
            return EnumStructuredStyleKeys.Litteral.FONT;
        } else if (metakey == MetaKey.SHOWSTEREOTYPES) {
            return EnumStructuredStyleKeys.Litteral.SHOWSTEREOTYPES;
        } else if (metakey == MetaKey.SHOWTAGS) {
            return EnumStructuredStyleKeys.Litteral.SHOWTAGS;
        } else if (metakey == MetaKey.TEXTCOLOR) {
            return EnumStructuredStyleKeys.Litteral.TEXTCOLOR;
        }
        return null;
    }

    @objid ("34e4a761-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return computeSignature(getRelatedElement());
    }

    @objid ("34e4a766-55b7-11e2-877f-002564c97630")
    @Override
    public EnumerationLiteral getRelatedElement() {
        return this.element;
    }

    @objid ("34e4a774-55b7-11e2-877f-002564c97630")
    private String computeSignature(EnumerationLiteral att) {
        return att.getName();
    }

    @objid ("34e4a77b-55b7-11e2-877f-002564c97630")
    private void init() {
        setShowMetaclassKeyword(false);
        setShowMetaclassIcon(false);
    }

    @objid ("34e4a77d-55b7-11e2-877f-002564c97630")
    @Override
    public EnumerationLiteral getRepresentedElement() {
        return this.element;
    }

    @objid ("34e62ddf-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmEnumLitteral.");
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

    @objid ("34e62de5-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmEnumLitteral.", GmEnumLitteral.MINOR_VERSION);
    }

    @objid ("34e62deb-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (EnumerationLiteral) resolveRef(this.getRepresentedRef());
    }

    @objid ("34e62df0-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
