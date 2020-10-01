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

package org.modelio.uml.activitydiagram.editor.elements.callevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A label representing the accepted operation for an AcceptCallEventAction.
 */
@objid ("29c4410f-55b6-11e2-877f-002564c97630")
public class GmCalledOperationLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29c44113-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29c44116-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("29c44118-55b6-11e2-877f-002564c97630")
    public GmCalledOperationLabel() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef ref to the related AcceptCallEventAction
     */
    @objid ("29c4411b-55b6-11e2-877f-002564c97630")
    public GmCalledOperationLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * @return the name of accepted operation if: <br>
     * - element is not null <br>
     * - element is valid <br>
     * - element does accept an operation
     */
    @objid ("29c44126-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        AcceptCallEventAction callOperation = (AcceptCallEventAction) getRelatedElement();
        if (callOperation != null && callOperation.isValid() && callOperation.getCalled() != null) {
            return callOperation.getCalled().getName();
        }
        return "";
    }

    @objid ("29c4412c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCalledOperationLabel.");
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

    @objid ("29c44132-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCalledOperationLabel.", GmCalledOperationLabel.MINOR_VERSION);
    }

    @objid ("29c5c79c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29c5c7a1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
