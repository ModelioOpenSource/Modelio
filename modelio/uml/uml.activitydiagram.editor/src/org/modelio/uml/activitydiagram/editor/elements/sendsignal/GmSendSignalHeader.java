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
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.activitydiagram.editor.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the header to handle the Signal icon showing when there is a sent signal bound.
 * 
 * @author fpoyer
 */
@objid ("2b4514d6-55b6-11e2-877f-002564c97630")
public class GmSendSignalHeader extends GmActivityNodeHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2b4514d8-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2b4514db-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("2b4514dd-55b6-11e2-877f-002564c97630")
    public  GmSendSignalHeader() {
        super();
    }

    /**
     * C'tor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef a reference to the send signal node this gm is related to.
     */
    @objid ("2b469b39-55b6-11e2-877f-002564c97630")
    public  GmSendSignalHeader(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("2b469b44-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isShowMetaclassIcon() {
        // True if their is a sent Signal.
        return ((SendSignalAction) getRelatedElement()).getSent() != null;
    }

    @objid ("2b469b49-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        Signal sentSignal = ((SendSignalAction) getRelatedElement()).getSent();
        if (sentSignal != null) {
            return ElementImageService.getIcon(sentSignal);
        }
        return super.getMetaclassIcon();
    }

    @objid ("2b469b4e-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSendSignalHeader.");
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

    @objid ("2b469b54-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSendSignalHeader.", GmSendSignalHeader.MINOR_VERSION);
        
    }

    @objid ("2b469b5a-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2b469b5f-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
