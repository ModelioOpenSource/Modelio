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

package org.modelio.uml.statikdiagram.editor.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Label used by GmPort.
 * 
 * @author fpoyer
 */
@objid ("36439b18-55b7-11e2-877f-002564c97630")
public class GmPortLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("36439b1c-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("36439b1f-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Deserialisation c'tor.
     */
    @objid ("36439b21-55b7-11e2-877f-002564c97630")
    public GmPortLabel() {
    }

    @objid ("364521ae-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        String mlabel = null;
        String baseName = null;
        
        Port port = (Port) getRelatedElement();
        
        if (!port.getName().isEmpty()) {
            mlabel = getRelatedElement().getName();
        }
        
        if (port.getBase() != null && !port.getBase().getName().isEmpty()) {
            baseName = port.getBase().getName();
        }
        
        StringBuilder s = new StringBuilder();
        
        // Skip the Port name if :
        // - the Port has a name that begin with the metaclass name (eg Port1 )
        // - and the Port has a type.
        String basename = getDiagram().getModelManager().getModelServices().getElementNamer().getBaseName(port.getMClass());
        if (mlabel != null && !mlabel.isEmpty()) {
            if (!mlabel.startsWith(basename) || baseName == null) {
                s.append(mlabel);
            }
        }
        
        // Append referenced element
        if (baseName != null) {
            s.append(":");
            s.append(baseName);
        }
        return s.toString();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this gm is created.
     * @param relatedRef a reference to the element represented.
     */
    @objid ("364521c3-55b7-11e2-877f-002564c97630")
    public GmPortLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("3646a839-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPortLabel.");
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

    @objid ("3646a83f-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPortLabel.", GmPortLabel.MINOR_VERSION);
    }

    @objid ("3646a845-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("3646a84a-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
