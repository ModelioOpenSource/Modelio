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
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of {@link GmDefaultModelElementHeader} for representing roles of a {@link NaryLinkEnd}.
 * 
 * @author fpoyer
 */
@objid ("d6084e52-e765-439d-9d98-498925717c03")
public class GmNaryLinkRoleNameLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3eed16a1-7401-48bb-a9b6-89edcdb12c2c")
    private static final int MINOR_VERSION = 0;

    @objid ("1bd7c415-e161-4144-95c5-1ce636d71d9e")
    private static final int MAJOR_VERSION = 0;

    @objid ("858331e6-c55c-4152-b558-8d7a0d68b467")
    private NaryLinkEnd role;

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("df99b2c3-32e4-40af-b373-5e27a4d9bf24")
    public GmNaryLinkRoleNameLabel() {
        // Nothing to do.
    }

    /**
     * c'tor.
     * @param diagram the diagram in which this label is created
     * @param role the represented role, might be null.
     * @param ref a reference to the represented role. must be non null.
     */
    @objid ("218e8de6-0c3c-4c06-97b2-060c04465b05")
    public GmNaryLinkRoleNameLabel(IGmDiagram diagram, NaryLinkEnd role, MRef ref) {
        super(diagram, ref);
        this.role = role;
    }

    @objid ("6e07e5b7-8e6e-430e-b7bd-77c797467c87")
    @Override
    public NaryLinkEnd getRepresentedElement() {
        return this.role;
    }

    @objid ("be0f6abf-45a4-48eb-805a-1d970b36d05b")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNaryLinkRoleNameLabel.");
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

    @objid ("b2ec5a40-6c94-48f4-80f5-87878f75b5b7")
    @Override
    public ModelElement getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("68da8304-c06c-4f1a-a0b9-c2a4635f4e91")
    @Override
    public boolean isVisible() {
        return getParent() == null || getDisplayedStyle().getBoolean(getStyleKeyStrict(MetaKey.SHOWROLES));
    }

    @objid ("7566fb98-871e-4be0-b2fc-df60516509cd")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNaryLinkRoleNameLabel.", GmNaryLinkRoleNameLabel.MINOR_VERSION);
    }

    @objid ("12522e04-332d-4de1-a142-fb7a9038e51f")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.role = (NaryLinkEnd) resolveRef(getRepresentedRef());
    }

    @objid ("f3ff15a3-8703-4cfd-b4fa-debb1c96b966")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
