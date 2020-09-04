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

package org.modelio.diagram.editor.statik.elements.naryassoc;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Association role name label.
 * <p>
 * Displays the role name and visibility.
 * 
 * @author cmarin
 */
@objid ("3de79203-ec5a-48d4-862a-3a72f2035271")
public class GmNaryRoleNameLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("e3524988-d938-4782-89c2-c819779b9235")
    private static final int MINOR_VERSION = 0;

    @objid ("9056b965-a27d-4206-8b31-0eae8816c6df")
    private static final int MAJOR_VERSION = 0;

    @objid ("78db0e6f-5e0a-46a3-8a4c-72c857ef705c")
    private NaryAssociationEnd role;

    /**
     * Constructor for deserialization only.
     */
    @objid ("a64ca64e-08bf-4d10-9847-b27fd1666610")
    public GmNaryRoleNameLabel() {
        // Nothing to do.
    }

    /**
     * Creates a role name label.
     * @param diagram the owning diagram.
     * @param role The represented role, may be null.
     * @param ref the represented role reference, must not be null.
     */
    @objid ("931f701c-4921-4e3d-a157-e99968139f57")
    public GmNaryRoleNameLabel(IGmDiagram diagram, NaryAssociationEnd role, MRef ref) {
        super(diagram, ref);
        this.role = role;
    }

    @objid ("f2d43777-6569-4617-a7e1-000635789242")
    @Override
    public ModelElement getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("195a912a-40fb-4fe6-bff2-5439edf2c5d6")
    @Override
    public ModelElement getRepresentedElement() {
        return this.role;
    }

    @objid ("bdb1fb5e-a004-477f-bd48-22fd51a22545")
    @Override
    public boolean isVisible() {
        return isRoleVisible();
    }

    @objid ("c0f47616-0c7d-4a11-b446-ef85dbfa55ca")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNaryRoleNameLabel.");
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

    @objid ("ddc2376a-40bc-4ed9-aaf6-a83c7e088165")
    @Override
    protected String computeMainLabel() {
        return computeSignature(this.role);
    }

    @objid ("e6277f5c-b3d5-4a81-95ff-1474f2073ae0")
    private String computeSignature(final NaryAssociationEnd att) {
        final String name = att.getName();
        if (name.isEmpty()) {
            return "";
        }
        
        StringBuilder ret = new StringBuilder();
        if (isRoleVisible()) {
            ret.append(name);
        }
        return ret.toString();
    }

    @objid ("d98a08a4-5ddf-4bed-ad75-6766cf3c25e9")
    private boolean isRoleVisible() {
        return this.getDisplayedStyle().getProperty(NAssocStructuredStyleKeys.SHOWROLES);
    }

    @objid ("3fd71ed5-1c43-42bb-9eab-58d71a39c624")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNaryRoleNameLabel.", GmNaryRoleNameLabel.MINOR_VERSION);
    }

    @objid ("6167c3c0-0cd7-470d-a292-654df9a59ad2")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.role = (NaryAssociationEnd) resolveRef(this.getRepresentedRef());
    }

    @objid ("9d2bc021-82b4-4b42-a56a-3707c41632e1")
    @Override
    public int getMajorVersion() {
        return GmNaryRoleNameLabel.MAJOR_VERSION;
    }

}
