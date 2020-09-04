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

package org.modelio.diagram.editor.statik.elements.association;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Association name label.
 */
@objid ("a0adcf5b-8261-45eb-af72-428c777ab981")
public class GmAssociationLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("8d24aacc-3060-411b-8b47-5dd159a759f9")
    private static final int MINOR_VERSION = 0;

    @objid ("c056eb26-65e1-45c3-b629-caa0c8a450e7")
    private static final int MAJOR_VERSION = 0;

    @objid ("3abbecfd-f42f-4aa6-929d-5c01670f3e48")
    private Association assoc;

    /**
     * Constructor for deserialization only.
     */
    @objid ("50cfdc54-e0a5-43ab-a988-ed14c9df3c74")
    public GmAssociationLabel() {
        // Nothing to do.
    }

    /**
     * Creates an association name label.
     * @param assoc The represented assoc, may be null.
     * @param ref the represented assoc reference, must not be null.
     * 
     * @param gmDiagram the owning diagram.
     */
    @objid ("e58e6b69-ac38-44fc-8dfe-77e12dbb68ed")
    public GmAssociationLabel(IGmDiagram gmDiagram, MRef assocRef) {
        super(gmDiagram, assocRef);
        this.assoc = (Association) resolveRef(assocRef);
    }

    @objid ("6264f1c1-d9ab-4b07-8c58-806d1958e8b8")
    @Override
    public ModelElement getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("6bddd99c-8a1b-4a5f-9500-e4fd239f510b")
    @Override
    public ModelElement getRepresentedElement() {
        return this.assoc;
    }

    @objid ("c97272c5-0511-4da8-a221-19f8d3eb1f93")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmAssociationLabel.");
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

    @objid ("4e8d5a1e-39bc-4f33-95a3-4a6b3f9a262a")
    @Override
    protected String computeMainLabel() {
        return this.assoc != null ? this.assoc.getName() : "";
    }

    @objid ("4dbbbb38-b07c-414d-a0ff-3a17e72e54e2")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmAssociationLabel.", GmAssociationLabel.MINOR_VERSION);
    }

    @objid ("54ec8c26-f889-470e-b9e6-af664d5c290b")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.assoc = (Association) resolveRef(getRepresentedRef());
    }

    @objid ("c4025796-e9bc-4226-a9b2-2d9ec2f0a1b2")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
