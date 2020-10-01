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

package org.modelio.uml.statikdiagram.editor.elements.instancelink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link LinkEnd link role} cardinality label.
 * 
 * @see LinkEnd
 * @author cma
 */
@objid ("355d32aa-55b7-11e2-877f-002564c97630")
public class GmLinkRoleCardinalityLabel extends GmElementLabel {
    @objid ("355d32ae-55b7-11e2-877f-002564c97630")
    private LinkEnd role;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("355d32b1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("355d32b4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates a role cardinality label.
     * 
     * @param diagram The diagram
     * @param role The represented role, may be null
     * @param roleRef The represented role reference, may not be null
     */
    @objid ("355d32b6-55b7-11e2-877f-002564c97630")
    public GmLinkRoleCardinalityLabel(IGmDiagram diagram, LinkEnd role, MRef roleRef) {
        super(diagram, roleRef);
        this.role = role;
    }

    @objid ("355d32c2-55b7-11e2-877f-002564c97630")
    private String computeLabel(LinkEnd el) {
        StringBuilder multiplicity = new StringBuilder();
        
        String multiplicityMinStr = el.getMultiplicityMin();
        String multiplicityMaxStr = el.getMultiplicityMax();
        String separator = "";
        
        if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
            if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                multiplicity.append(multiplicityMinStr);
            } else if (multiplicityMinStr.equals("0") && multiplicityMaxStr.equals("*")) {
                multiplicity.append("*");
            } else {
                if (!multiplicityMinStr.equals("") && !multiplicityMaxStr.equals("")) {
                    separator = "..";
                }
        
                multiplicity.append(multiplicityMinStr);
                multiplicity.append(separator);
                multiplicity.append(multiplicityMaxStr);
            }
        }
        return multiplicity.toString();
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("355d32c9-55b7-11e2-877f-002564c97630")
    public GmLinkRoleCardinalityLabel() {
    }

    @objid ("355d32cc-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        return computeLabel(getRelatedElement());
    }

    @objid ("355d32d1-55b7-11e2-877f-002564c97630")
    @Override
    public LinkEnd getRepresentedElement() {
        return this.role;
    }

    @objid ("355d32d8-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLinkRoleCardinalityLabel.");
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

    @objid ("355d32de-55b7-11e2-877f-002564c97630")
    @Override
    public LinkEnd getRelatedElement() {
        return this.role;
    }

    @objid ("355d32e5-55b7-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        final LinkEnd linkEnd = getRelatedElement();
        if (linkEnd == null || !linkEnd.isValid()) {
            return null;
        }
        return new IEditableText() {
                    @Override
                    public String getText() {
                        return computeLabel();
                    }
        
                    @Override
                    public void setText(String text) {
                        String[] values = text.split("\\.\\.");
        
                        if (values.length == 1 && values[0].equals("*")) {
                            linkEnd.setMultiplicityMin("0");
                            linkEnd.setMultiplicityMax(values[0]);
                        } else if (values.length == 1) {
                            linkEnd.setMultiplicityMin(values[0]);
                            linkEnd.setMultiplicityMax(values[0]);
                        } else if (values.length == 2) {
                            linkEnd.setMultiplicityMin(values[0]);
                            linkEnd.setMultiplicityMax(values[1]);
                        }
                    }
                };
    }

    @objid ("355eb93e-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return this.getDisplayedStyle().getProperty(getStyleKeyStrict(MetaKey.SHOWCARDINALITIES));
    }

    @objid ("355eb943-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLinkRoleCardinalityLabel.", GmLinkRoleCardinalityLabel.MINOR_VERSION);
    }

    @objid ("355eb949-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        final MObject resolveRef = resolveRef(this.getRepresentedRef());
        if (resolveRef instanceof LinkEnd) {
            this.role = (LinkEnd) resolveRef;
        }
    }

    @objid ("355eb94e-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmLinkRoleCardinalityLabel.MAJOR_VERSION;
    }

    @objid ("c9777fe7-226e-43ea-9ad3-4e4a9dcf8f1f")
    @Override
    public boolean isWrapped() {
        return false;
    }

}
