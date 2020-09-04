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

package org.modelio.diagram.editor.statik.elements.narylink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link NaryLinkEnd link role} cardinality label.
 * 
 * @see NaryLinkEnd
 * @author cma
 */
@objid ("2b58252c-0127-45ba-9e10-ccde5d9400f5")
public class GmNaryLinkRoleCardinalityLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("97d667f6-3123-4947-bb1e-5287b95b59a3")
    private static final int MINOR_VERSION = 0;

    @objid ("7c1a9f47-0d4e-4b90-9ac2-3024bc245e77")
    private static final int MAJOR_VERSION = 0;

    @objid ("b6b5b30e-cd03-4840-ac7c-03fc7314d9c4")
    private NaryLinkEnd role;

    /**
     * Creates a role cardinality label.
     * @param diagram The diagram
     * @param role The represented role, may be null
     * @param roleRef The represented role reference, may not be null
     */
    @objid ("bd0509d9-510f-432d-9663-6ca54335b5d2")
    public GmNaryLinkRoleCardinalityLabel(IGmDiagram diagram, NaryLinkEnd role, MRef roleRef) {
        super(diagram, roleRef);
        this.role = role;
    }

    @objid ("864d4503-419e-4d00-968a-1bcab75cb305")
    private String computeLabel(NaryLinkEnd el) {
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
    @objid ("55fea1f7-cb13-4cf3-b7dc-06df9ac87291")
    public GmNaryLinkRoleCardinalityLabel() {
    }

    @objid ("2be1a537-2614-4644-b11b-d2c21ad574ca")
    @Override
    protected String computeLabel() {
        return computeLabel(getRelatedElement());
    }

    @objid ("cc0d92fa-f7a4-4ea3-a934-bd006f65c2d9")
    @Override
    public NaryLinkEnd getRepresentedElement() {
        return this.role;
    }

    @objid ("ae7bc1fc-c3df-4c0c-b506-b44a9b112c8c")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNaryLinkRoleCardinalityLabel.");
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

    @objid ("1b37ae8d-d4dd-405e-8a99-2010a944c41d")
    @Override
    public NaryLinkEnd getRelatedElement() {
        return this.role;
    }

    @objid ("0231e233-e867-4254-8703-bee4f7832080")
    @Override
    public IEditableText getEditableText() {
        final NaryLinkEnd linkEnd = getRelatedElement();
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

    @objid ("4a643c97-696c-4647-9d56-1b76a74ea9b9")
    @Override
    public boolean isVisible() {
        return getParent() == null || getDisplayedStyle().getBoolean(getStyleKeyStrict(MetaKey.SHOWCARDINALITIES));
    }

    @objid ("21430577-3ef8-4aaa-84cb-bfe0b39c62b6")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNaryLinkRoleCardinalityLabel.", GmNaryLinkRoleCardinalityLabel.MINOR_VERSION);
    }

    @objid ("153ff232-49f2-4f36-9eb4-efe060f95b7c")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.role = (NaryLinkEnd) resolveRef(getRepresentedRef());
    }

    @objid ("6b23fd1c-5bff-4dac-9cfa-7dcba337aa0d")
    @Override
    public int getMajorVersion() {
        return GmNaryLinkRoleCardinalityLabel.MAJOR_VERSION;
    }

    @objid ("799ebce6-fa58-4f2d-87e0-4cfd4aad4f03")
    @Override
    public boolean isWrapped() {
        return false;
    }

}
