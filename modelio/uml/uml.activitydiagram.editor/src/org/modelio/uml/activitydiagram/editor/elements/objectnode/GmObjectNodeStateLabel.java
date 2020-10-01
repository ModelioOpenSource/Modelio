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

package org.modelio.uml.activitydiagram.editor.elements.objectnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A label representing the state for an ObjectNode.
 */
@objid ("2adb7d5f-55b6-11e2-877f-002564c97630")
public class GmObjectNodeStateLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2adb7d63-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2adb7d66-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("2adb7d68-55b6-11e2-877f-002564c97630")
    public GmObjectNodeStateLabel() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef ref to the related ObjectNode
     */
    @objid ("2adb7d6b-55b6-11e2-877f-002564c97630")
    public GmObjectNodeStateLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("2adb7d76-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        StringBuffer mainLabel = new StringBuffer();
        
        ObjectNode objectNode = (ObjectNode) getRelatedElement();
        if (objectNode != null && objectNode.isValid()) {
            State state = objectNode.getInState();
            if (state != null) {
                mainLabel.append("[");
                mainLabel.append(state.getName());
                mainLabel.append("]");
            }
        }
        return mainLabel.toString();
    }

    @objid ("2adb7d7b-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmObjectNodeStateLabel.");
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

    @objid ("2adb7d81-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmObjectNodeStateLabel.", GmObjectNodeStateLabel.MINOR_VERSION);
    }

    @objid ("2adb7d87-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2adb7d8c-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
