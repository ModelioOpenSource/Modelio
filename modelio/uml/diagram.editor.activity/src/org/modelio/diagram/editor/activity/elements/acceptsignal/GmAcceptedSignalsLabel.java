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

package org.modelio.diagram.editor.activity.elements.acceptsignal;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A label representing the accepted signals for an AcceptCallEventAction.
 */
@objid ("2971db4f-55b6-11e2-877f-002564c97630")
public class GmAcceptedSignalsLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2971db53-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2971db56-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("2971db58-55b6-11e2-877f-002564c97630")
    public GmAcceptedSignalsLabel() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef ref to the related AcceptSignalAction
     */
    @objid ("2971db5b-55b6-11e2-877f-002564c97630")
    public GmAcceptedSignalsLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * @return the name of accepted signals if: <br>
     * - element is not null <br>
     * - element is valid <br>
     * - element does accept some signals
     */
    @objid ("2971db66-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        StringBuilder mainLabel = new StringBuilder();
        
        AcceptSignalAction callOperation = (AcceptSignalAction) getRelatedElement();
        if (callOperation != null && callOperation.isValid()) {
            List<Signal> accepted = callOperation.getAccepted();
            for (int i = 0; i < accepted.size(); i++) {
                mainLabel.append(accepted.get(i).getName());
                if (i < accepted.size() - 1) {
                    mainLabel.append(", ");
                }
            }
        }
        return mainLabel.toString();
    }

    @objid ("2971db6c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmAcceptedSignalsLabel.");
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

    @objid ("2971db72-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmAcceptedSignalsLabel.", GmAcceptedSignalsLabel.MINOR_VERSION);
    }

    @objid ("297361dc-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("297361e1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmAcceptedSignalsLabel.MAJOR_VERSION;
    }

}
