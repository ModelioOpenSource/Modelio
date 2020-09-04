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

package org.modelio.diagram.editor.activity.elements.callevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.activity.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the header to handle the Operation icon showing when there is an operation bound.
 */
@objid ("29c8d511-55b6-11e2-877f-002564c97630")
public class GmCallEventHeader extends GmActivityNodeHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29c8d513-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29c8d516-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("29c8d518-55b6-11e2-877f-002564c97630")
    public GmCallEventHeader() {
        super();
    }

    /**
     * C'tor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef a reference to the node this gm is related to.
     */
    @objid ("29c8d51b-55b6-11e2-877f-002564c97630")
    public GmCallEventHeader(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("29ca5b82-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isShowMetaclassIcon() {
        // True if their is a called operation.
        return ((AcceptCallEventAction) getRelatedElement()).getCalled() != null;
    }

    @objid ("29ca5b87-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        Operation calledOperation = ((AcceptCallEventAction) getRelatedElement()).getCalled();
        if (calledOperation != null) {
            return ElementImageService.getIcon(calledOperation);
        }
        return super.getMetaclassIcon();
    }

    @objid ("29ca5b8c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCallEventHeader.");
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

    @objid ("29ca5b92-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCallEventHeader.", GmCallEventHeader.MINOR_VERSION);
    }

    @objid ("29ca5b98-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29ca5b9d-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmCallEventHeader.MAJOR_VERSION;
    }

}
