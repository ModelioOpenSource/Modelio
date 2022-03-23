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
package org.modelio.uml.activitydiagram.editor.elements.callbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A label representing the called behavior.
 * 
 * @author fpoyer
 */
@objid ("29b68586-55b6-11e2-877f-002564c97630")
public class GmBehaviorLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29b68588-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29b6858b-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * @param diagram the diagram.
     * @param relatedRef ref to the related CallBehaviorAction
     */
    @objid ("29b6858d-55b6-11e2-877f-002564c97630")
    public  GmBehaviorLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("29b68596-55b6-11e2-877f-002564c97630")
    public  GmBehaviorLabel() {
        // Nothing to do.
    }

    @objid ("29b80bf9-55b6-11e2-877f-002564c97630")
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

    @objid ("29b80bfd-55b6-11e2-877f-002564c97630")
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

    @objid ("29b80c05-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("29b80c0c-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        CallBehaviorAction callBehavior = (CallBehaviorAction) getRelatedElement();
        Behavior base = callBehavior.getCalled();
        if (base != null) {
            return ElementImageService.getIcon(base);
        } else {
            return ElementImageService.getIcon(callBehavior);
        }
        
    }

    @objid ("29b80c10-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBehaviorLabel.");
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

    @objid ("29b80c16-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBehaviorLabel.", GmBehaviorLabel.MINOR_VERSION);
        
    }

    @objid ("29b80c1c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29b80c21-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
