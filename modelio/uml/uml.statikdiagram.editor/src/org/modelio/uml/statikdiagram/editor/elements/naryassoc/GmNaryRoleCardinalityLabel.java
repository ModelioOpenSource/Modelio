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
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an association role cardinality label.
 * 
 * @see NaryAssociationEnd
 * @author cma
 */
@objid ("a9f54966-5b62-4427-bcb7-cf486382b4b9")
public class GmNaryRoleCardinalityLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("8cce7af1-9c14-4b90-b4a5-45fa2dece943")
    private static final int MINOR_VERSION = 0;

    @objid ("e0175d59-1690-45cc-956a-6c569105ff35")
    private static final int MAJOR_VERSION = 0;

    @objid ("235dbbcc-e44b-4a8a-aeae-70bb9368fa41")
    private NaryAssociationEnd role;

    /**
     * Creates a role cardinality label.
     * @param diagram The diagram
     * @param role The represented role, may be null
     * @param roleRef The represented role reference, may not be null
     */
    @objid ("5d60a9b9-e22e-4c78-80a4-135aae2407e4")
    public  GmNaryRoleCardinalityLabel(IGmDiagram diagram, NaryAssociationEnd role, MRef roleRef) {
        super(diagram, roleRef);
        this.role = role;
        
    }

    @objid ("8fc84721-5a52-4a3a-bd93-d14087c60875")
    private String computeLabel(final NaryAssociationEnd theNaryAssociationEnd) {
        StringBuilder multiplicity = new StringBuilder();
        
        String multiplicityMinStr = theNaryAssociationEnd.getMultiplicityMin();
        String multiplicityMaxStr = theNaryAssociationEnd.getMultiplicityMax();
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
    @objid ("c2bac03c-94e8-48eb-9a33-d43aba459a58")
    public  GmNaryRoleCardinalityLabel() {
        
    }

    @objid ("087b2d51-28e2-45b2-9981-664225362b73")
    @Override
    protected String computeLabel() {
        return computeLabel((NaryAssociationEnd) getRelatedElement());
    }

    @objid ("061f0f5a-2cc0-45eb-af62-aafeb27f0826")
    @Override
    public MObject getRepresentedElement() {
        return this.role;
    }

    @objid ("7cb1bf3c-4d2f-48e1-a356-4d5b50c857bf")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNaryRoleCardinalityLabel.");
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

    @objid ("799c2ab2-09bd-4ab3-86a7-10f6e4f4f90e")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("42744d2e-7da3-403a-8584-7913a5709463")
    @Override
    public boolean isVisible() {
        if (getParent() != null) {
            return getDisplayedStyle().getProperty(NAssocStructuredStyleKeys.SHOWCARD);
        } else {
            return false;
        }
        
    }

    @objid ("ebd37f4a-0b7c-41a8-beba-04c1d93a5727")
    @Override
    public IEditableText getEditableText() {
        final NaryAssociationEnd assocEnd = (NaryAssociationEnd) getRelatedElement();
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

    @objid ("79eb2842-6147-4679-9819-9fde6c9778fd")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNaryRoleCardinalityLabel.", GmNaryRoleCardinalityLabel.MINOR_VERSION);
        
    }

    @objid ("c8ccb7a3-8fb2-4de8-aa04-3ebae1c6cdbf")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.role = (NaryAssociationEnd) resolveRef(this.getRepresentedRef());
        
    }

    @objid ("c2aede2a-3bbf-4eb2-8942-ee4ffe6d541b")
    @Override
    public int getMajorVersion() {
        return GmNaryRoleCardinalityLabel.MAJOR_VERSION;
    }

    @objid ("98555070-bcb2-4d5d-b23c-f045287d8298")
    @Override
    public boolean isWrapped() {
        return false;
    }

}
