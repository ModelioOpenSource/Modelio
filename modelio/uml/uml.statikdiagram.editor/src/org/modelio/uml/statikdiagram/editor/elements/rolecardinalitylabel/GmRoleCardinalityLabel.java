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
package org.modelio.uml.statikdiagram.editor.elements.rolecardinalitylabel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.uml.statikdiagram.editor.elements.association.GmAssocStructuredStyleKeys;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an association role cardinality label.
 * 
 * @see AssociationEnd
 * @author cma
 */
@objid ("368537da-55b7-11e2-877f-002564c97630")
public class GmRoleCardinalityLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("368537e1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("368537e4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("368537de-55b7-11e2-877f-002564c97630")
    private AssociationEnd role;

    /**
     * Creates a role cardinality label.
     * @param diagram The diagram
     * @param role The represented role, may be null
     * @param roleRef The represented role reference, may not be null
     */
    @objid ("368537e6-55b7-11e2-877f-002564c97630")
    public  GmRoleCardinalityLabel(IGmDiagram diagram, AssociationEnd role, MRef roleRef) {
        super(diagram, roleRef);
        this.role = role;
        
    }

    @objid ("368537f2-55b7-11e2-877f-002564c97630")
    private String computeLabel(final AssociationEnd theAssociationEnd) {
        StringBuilder multiplicity = new StringBuilder();
        
        String multiplicityMinStr = theAssociationEnd.getMultiplicityMin();
        String multiplicityMaxStr = theAssociationEnd.getMultiplicityMax();
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
    @objid ("3686be7d-55b7-11e2-877f-002564c97630")
    public  GmRoleCardinalityLabel() {
        
    }

    @objid ("3686be80-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        return computeLabel((AssociationEnd) getRelatedElement());
    }

    @objid ("3686be85-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.role;
    }

    @objid ("3686be8c-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmRoleCardinalityLabel.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert false : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("3686be92-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("3686be99-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() != null) {
            return this.getDisplayedStyle().getProperty(GmAssocStructuredStyleKeys.SHOWCARD);
        } else {
            return false;
        }
        
    }

    @objid ("3686be9e-55b7-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        final AssociationEnd assocEnd = (AssociationEnd) getRelatedElement();
        if (assocEnd == null || !assocEnd.isValid()) {
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
                    assocEnd.setMultiplicityMin("0");
                    assocEnd.setMultiplicityMax(values[0]);
                } else if (values.length == 1) {
                    assocEnd.setMultiplicityMin(values[0]);
                    assocEnd.setMultiplicityMax(values[0]);
                } else if (values.length == 2) {
                    assocEnd.setMultiplicityMin(values[0]);
                    assocEnd.setMultiplicityMax(values[1]);
                }
            }
        };
        
    }

    @objid ("3686bea5-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmRoleCardinalityLabel.", GmRoleCardinalityLabel.MINOR_VERSION);
        
    }

    @objid ("3686beab-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        final MObject resolveRef = resolveRef(this.getRepresentedRef());
        if (resolveRef instanceof AssociationEnd) {
            this.role = (AssociationEnd) resolveRef;
        }
        
    }

    @objid ("36884519-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmRoleCardinalityLabel.MAJOR_VERSION;
    }

    @objid ("30cac07e-97bb-4849-ad83-5385ad58b033")
    @Override
    public boolean isWrapped() {
        return false;
    }

}
