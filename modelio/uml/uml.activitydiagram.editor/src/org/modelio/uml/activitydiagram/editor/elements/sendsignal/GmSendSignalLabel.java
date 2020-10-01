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

package org.modelio.uml.activitydiagram.editor.elements.sendsignal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A label representing the sent signal for an SendSignalAction.
 */
@objid ("2b4821e6-55b6-11e2-877f-002564c97630")
public class GmSendSignalLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2b4821ea-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2b4821ed-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("2b4821ef-55b6-11e2-877f-002564c97630")
    public GmSendSignalLabel() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef ref to the related SendSignalAction
     */
    @objid ("2b4821f2-55b6-11e2-877f-002564c97630")
    public GmSendSignalLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * @return the name of sent signal if: <br>
     * - element is not null <br>
     * - element is valid <br>
     * - element does send a signal
     */
    @objid ("2b4821fd-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        SendSignalAction callOperation = (SendSignalAction) getRelatedElement();
        if (callOperation != null && callOperation.isValid() && callOperation.getSent() != null) {
        
            return callOperation.getSent().getName();
        }
        return "";
    }

    @objid ("2b482203-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSendSignalLabel.");
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

    @objid ("2b482209-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSendSignalLabel.", GmSendSignalLabel.MINOR_VERSION);
    }

    @objid ("2b48220f-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2b482214-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
