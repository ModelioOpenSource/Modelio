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

package org.modelio.diagram.editor.activity.elements.partitioncontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the Partition Container that delete itself when last child is removed.
 * 
 * @author fpoyer
 */
@objid ("2b209ca9-55b6-11e2-877f-002564c97630")
public class GmDiagramPartitionContainer extends GmPartitionContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2b209cab-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2b20c3bb-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("2b20c3bd-55b6-11e2-877f-002564c97630")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
        // If removed child was the last, delete self.
        if (!this.hasChildren())
            delete();
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("2b20eacd-55b6-11e2-877f-002564c97630")
    public GmDiagramPartitionContainer() {
        // Nothing to do.
    }

    /**
     * Default C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef represented element reference, must not be null.
     */
    @objid ("2b2138e9-55b6-11e2-877f-002564c97630")
    public GmDiagramPartitionContainer(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("2b215ffc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDiagramPartitionContainer.");
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

    @objid ("2b21870c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDiagramPartitionContainer.", GmDiagramPartitionContainer.MINOR_VERSION);
    }

    @objid ("2b21ae19-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2b21d529-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
