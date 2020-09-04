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

package org.modelio.diagram.editor.activity.elements.acceptsignal;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.activity.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the header to handle the Signal icon showing when their is at least 1 signal accepted.
 * 
 * @author fpoyer
 */
@objid ("29766f56-55b6-11e2-877f-002564c97630")
public class GmAcceptSignalHeader extends GmActivityNodeHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29766f58-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29766f5b-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("fefa7f2b-ff0e-400a-a7df-e6e503ac75c6")
    public GmAcceptSignalHeader() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef a reference to the accept signal node this gm is related to.
     */
    @objid ("2977f5bd-55b6-11e2-877f-002564c97630")
    public GmAcceptSignalHeader(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("2977f5c8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isShowMetaclassIcon() {
        // True if their is at least 1 accepted Signal.
        return !(((AcceptSignalAction) getRelatedElement()).getAccepted().isEmpty());
    }

    @objid ("2977f5cd-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        List<Signal> acceptedSignals = ((AcceptSignalAction) getRelatedElement()).getAccepted();
        if (!acceptedSignals.isEmpty()) {
            return ElementImageService.getIcon(acceptedSignals.get(0));
        }
        return super.getMetaclassIcon();
    }

    @objid ("2977f5d2-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmAcceptSignalHeader.");
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

    @objid ("2977f5d8-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmAcceptSignalHeader.", GmAcceptSignalHeader.MINOR_VERSION);
    }

    @objid ("2977f5de-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2977f5e3-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmAcceptSignalHeader.MAJOR_VERSION;
    }

}
