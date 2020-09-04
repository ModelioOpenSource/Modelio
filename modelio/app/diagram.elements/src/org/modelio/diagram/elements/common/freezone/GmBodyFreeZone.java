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

package org.modelio.diagram.elements.common.freezone;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Defaults implementation of the abstract GmFreeZone that can be used for "body" of most nodes. It delegates the
 * canUnmask and canCreate operation to the parent Gm.
 */
@objid ("7e3a5eaa-1dec-11e2-8cad-001ec947c8cc")
public class GmBodyFreeZone extends GmFreeZone {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7e3a5eac-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7e3a5eaf-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     */
    @objid ("7e3a5eb1-1dec-11e2-8cad-001ec947c8cc")
    public GmBodyFreeZone() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this Gm is created.
     * @param relatedRef a reference to the element this GmModel is related to. never <i>null</i>.
     */
    @objid ("7e3a5eb4-1dec-11e2-8cad-001ec947c8cc")
    public GmBodyFreeZone(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("7e3a5eb9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Parent node should know.
        return getParent().canCreate(type);
    }

    @objid ("7e3a5ec1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canUnmask(MObject el) {
        // Parent node should know.
        return getParent().canUnmask(el);
    }

    @objid ("7e3a5ec7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isVisible() {
        GmModel parent = getParent();
        return parent != null && parent.getRepresentationMode() == RepresentationMode.STRUCTURED;
    }

    @objid ("7e3a5ecc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            StyleKey key = getStyleKey(MetaKey.REPMODE);
            if (key != null) {
                getParent().getDisplayedStyle().setProperty(key, RepresentationMode.STRUCTURED);
            }
        }
    }

    @objid ("7e3a5ed0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        final MObject childEl = node.getRelatedElement();
        return childEl == null || (!childEl.isDeleted() && canUnmask(childEl));
    }

    @objid ("7e3a5ed6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBodyFreeZone.");
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

    @objid ("7e3a5eda-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBodyFreeZone.", MINOR_VERSION);
    }

    @objid ("7e3a5ede-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7e3cc0f3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
