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

package org.modelio.diagram.editor.state.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link State} label.
 * <p>
 * Extends {@link GmModelElementLabel}.
 */
@objid ("f580ee97-55b6-11e2-877f-002564c97630")
public class GmStateLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f580ee9b-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f580ee9e-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * constructor to be used only for deserialization
     */
    @objid ("f580eea0-55b6-11e2-877f-002564c97630")
    public GmStateLabel() {
    }

    /**
     * Creates an State label.
     * @param diagram the owning graphic diagram, may not be <tt>null</tt>.
     * @param ref the represented State reference, may not be <tt>null</tt>.
     */
    @objid ("f58274f9-55b6-11e2-877f-002564c97630")
    public GmStateLabel(final IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
    }

    @objid ("f5827504-55b6-11e2-877f-002564c97630")
    @Override
    public String computeMainLabel() {
        final State inst = (State) getRelatedElement();
        StringBuilder ret = new StringBuilder();
        ret.append(inst.getName());
        if (inst.getSubMachine() != null) {
            ret.append(": ");
            ret.append(inst.getSubMachine().getName());
        }
        return ret.toString();
    }

    @objid ("f5827509-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmStateLabel.");
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

    @objid ("f582750f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmStateLabel.", GmStateLabel.MINOR_VERSION);
    }

    @objid ("f5827515-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("f582751a-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmStateLabel.MAJOR_VERSION;
    }

}
