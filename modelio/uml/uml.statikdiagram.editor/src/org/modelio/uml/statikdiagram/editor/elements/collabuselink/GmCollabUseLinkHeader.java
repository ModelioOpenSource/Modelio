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
package org.modelio.uml.statikdiagram.editor.elements.collabuselink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@link CollaborationUse} link header displayed on the link.
 * 
 * @author cmarin
 */
@objid ("348f3465-55b7-11e2-877f-002564c97630")
public class GmCollabUseLinkHeader extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("348f3469-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("348f346c-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("348f346e-55b7-11e2-877f-002564c97630")
    public  GmCollabUseLinkHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("348f3477-55b7-11e2-877f-002564c97630")
    public  GmCollabUseLinkHeader() {
        
    }

    /**
     * OMG tells the label is '&lt;&lt;occurrence>>'.
     */
    @objid ("348f347a-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return "<<occurrence>>";
    }

    /**
     * The label is not editable.
     */
    @objid ("3490badd-55b7-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("3490bae5-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollabUseLinkHeader.");
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

    @objid ("3490baeb-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCollabUseLinkHeader.", GmCollabUseLinkHeader.MINOR_VERSION);
        
    }

    @objid ("3490baf1-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("3490baf6-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
