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

package org.modelio.diagram.elements.core.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a node that will never have children nodes.
 * 
 * @author cmarin
 */
@objid ("809f1b2f-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmSimpleNode extends GmNodeModel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("809f1b31-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("80a17d4e-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("80a17d50-1dec-11e2-8cad-001ec947c8cc")
    public GmSimpleNode() {
        super();
    }

    /**
     * Initializes a simple node.
     * 
     * @param diagram The diagram owning the node.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("80a17d53-1dec-11e2-8cad-001ec947c8cc")
    public GmSimpleNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("80a17d58-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // By default nothing can be unmasked under a simple node.
        return false;
    }

    @objid ("80a17d60-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canUnmask(MObject el) {
        // By default nothing can be unmasked under a simple node.
        return false;
    }

    @objid ("80a17d66-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSimpleNode.");
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

    @objid ("80a17d6a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSimpleNode.", MINOR_VERSION);
    }

    @objid ("80a17d6e-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("80a17d71-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
