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
package org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datainput;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.GmBpmnDataLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("60b447e1-55b6-11e2-877f-002564c97630")
public class GmBpmnDataInputLabel extends GmBpmnDataLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("60b5ce39-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("60b5ce3c-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("60b5ce3e-55b6-11e2-877f-002564c97630")
    public  GmBpmnDataInputLabel(final IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("60b5ce4d-55b6-11e2-877f-002564c97630")
    public  GmBpmnDataInputLabel() {
        // empty constructor for the serialization
    }

    @objid ("60b5ce50-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnDataInputLabel.");
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

    @objid ("60b5ce56-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnDataInputLabel.", MINOR_VERSION);
        
    }

    @objid ("60b5ce5c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("60b5ce61-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
