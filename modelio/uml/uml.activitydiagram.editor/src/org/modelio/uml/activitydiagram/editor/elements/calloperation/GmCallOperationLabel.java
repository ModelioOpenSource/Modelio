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
package org.modelio.uml.activitydiagram.editor.elements.calloperation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A label representing the called behavior.
 * 
 * @author fpoyer
 */
@objid ("29d81756-55b6-11e2-877f-002564c97630")
public final class GmCallOperationLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29d99db9-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29d99dbc-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * @param diagram the diagram.
     * @param relatedRef ref to the related CallBehaviorAction
     */
    @objid ("29d99dbe-55b6-11e2-877f-002564c97630")
    public  GmCallOperationLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("29d99dc7-55b6-11e2-877f-002564c97630")
    public  GmCallOperationLabel() {
        // Nothing to do.
    }

    @objid ("29d99dca-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        CallOperationAction callBehavior = (CallOperationAction) getRelatedElement();
        String calledName = getCalledOperationName(callBehavior);
        String elName = callBehavior.getName();
        
        if (elName.isEmpty() || elName.equals(calledName)) {
            return calledName;
        } else {
            return elName + ": call " + calledName;
        }
        
    }

    @objid ("29d99dce-55b6-11e2-877f-002564c97630")
    protected final String getCalledOperationName(final CallOperationAction callBehavior) {
        // return the name of called behavior if:
        // element is not null
        // element is valid
        // element does call an operation
        if (callBehavior != null && callBehavior.isValid() && callBehavior.getCalled() != null) {
            return callBehavior.getCalled().getName();
        }
        return "";
    }

    @objid ("29d99dd6-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("29d99ddd-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCallOperationLabel.");
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

    @objid ("29d99de3-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCallOperationLabel.", GmCallOperationLabel.MINOR_VERSION);
        
    }

    @objid ("29d99de9-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29d99dee-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
