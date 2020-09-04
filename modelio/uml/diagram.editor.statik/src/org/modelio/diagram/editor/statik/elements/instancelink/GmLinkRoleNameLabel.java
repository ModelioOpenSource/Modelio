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

package org.modelio.diagram.editor.statik.elements.instancelink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of {@link GmDefaultModelElementHeader} for representing roles of a {@link LinkEnd}.
 * 
 * @author fpoyer
 */
@objid ("355eb95c-55b7-11e2-877f-002564c97630")
public class GmLinkRoleNameLabel extends GmDefaultModelElementHeader {
    @objid ("355eb960-55b7-11e2-877f-002564c97630")
    private LinkEnd role;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("355eb963-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("355eb966-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("355eb968-55b7-11e2-877f-002564c97630")
    public GmLinkRoleNameLabel() {
        // Nothing to do.
    }

    /**
     * c'tor.
     * 
     * @param diagram the diagram in which this label is created
     * @param role the represented role, might be null.
     * @param ref a reference to the represented role. must be non null.
     */
    @objid ("355eb96b-55b7-11e2-877f-002564c97630")
    public GmLinkRoleNameLabel(IGmDiagram diagram, LinkEnd role, MRef ref) {
        super(diagram, ref);
        this.role = role;
    }

    @objid ("355eb977-55b7-11e2-877f-002564c97630")
    @Override
    public ModelElement getRelatedElement() {
        return this.role;
    }

    @objid ("355eb97e-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLinkRoleNameLabel.");
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

    @objid ("35603fe4-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getDisplayedStyle().getProperty(getStyleKeyStrict(MetaKey.SHOWROLES));
    }

    @objid ("35603fe9-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLinkRoleNameLabel.", GmLinkRoleNameLabel.MINOR_VERSION);
    }

    @objid ("35603fef-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        final MObject resolveRef = resolveRef(this.getRepresentedRef());
        if (resolveRef instanceof LinkEnd) {
            this.role = (LinkEnd) resolveRef;
        }
    }

    @objid ("35603ff4-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("0f9733cc-f1be-4818-8edb-a27126773031")
    @Override
    public ModelElement getRepresentedElement() {
        return null;
    }

}
