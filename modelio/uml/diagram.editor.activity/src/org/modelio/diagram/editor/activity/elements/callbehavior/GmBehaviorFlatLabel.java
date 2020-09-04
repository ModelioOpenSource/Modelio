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

package org.modelio.diagram.editor.activity.elements.callbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A label representing the called behavior.
 * 
 * @author fpoyer
 */
@objid ("29b4fee3-55b6-11e2-877f-002564c97630")
public class GmBehaviorFlatLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29b4fee7-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29b4feea-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef ref to the related CallBehaviorAction
     */
    @objid ("29b4feec-55b6-11e2-877f-002564c97630")
    public GmBehaviorFlatLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("29b6855a-55b6-11e2-877f-002564c97630")
    public GmBehaviorFlatLabel() {
        // Nothing to do.
    }

    @objid ("29b6855d-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        CallBehaviorAction callBehavior = (CallBehaviorAction) getRelatedElement();
        String calledName = getCalledBehaviorName(callBehavior);
        String elName = callBehavior.getName();
        
        if (elName.isEmpty() || elName.equals(calledName)) {
            return calledName;
        } else {
            return elName + ": call " + calledName;
        }
    }

    @objid ("29b68561-55b6-11e2-877f-002564c97630")
    protected final String getCalledBehaviorName(final CallBehaviorAction callBehavior) {
        // return the name of called behavior if:
        // element is not null
        // element is valid
        // element does call an operation
        if (callBehavior != null && callBehavior.isValid() && callBehavior.getCalled() != null) {
            return callBehavior.getCalled().getName();
        }
        return "";
    }

    @objid ("29b68569-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBehaviorFlatLabel.");
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

    @objid ("29b6856f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBehaviorFlatLabel.", GmBehaviorFlatLabel.MINOR_VERSION);
    }

    @objid ("29b68575-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29b6857a-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
