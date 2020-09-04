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

package org.modelio.diagram.editor.state.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.elements.region.GmRegion;
import org.modelio.diagram.elements.common.resizablegroup.GmResizableGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the resizable group for regions in a state.
 * 
 * @author fpoyer
 */
@objid ("f5794d7c-55b6-11e2-877f-002564c97630")
public class GmRegionsGroup extends GmResizableGroup {
    @objid ("f5794d80-55b6-11e2-877f-002564c97630")
    private boolean isVisible;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f5794d81-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f57ad3db-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this gm is created.
     * @param relatedRef a reference to the element this gm is related to.
     */
    @objid ("f57ad3dd-55b6-11e2-877f-002564c97630")
    public GmRegionsGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("f57ad3e6-55b6-11e2-877f-002564c97630")
    public GmRegionsGroup() {
    }

    @objid ("f57ad3e9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmRegion.class == nodeClass;
    }

    @objid ("f57ad3f1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return Region.class.isAssignableFrom(type);
    }

    @objid ("f57ad3f9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return Region.class.isAssignableFrom(el.getClass()) &&
                                                                        el.isValid() &&
                                                                        el.getCompositionOwner().equals(this.getRelatedElement());
    }

    @objid ("f57ad401-55b6-11e2-877f-002564c97630")
    @Override
    public void doSetVisible(boolean value) {
        this.isVisible = value;
    }

    @objid ("f57ad405-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    @objid ("f57ad40a-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("f57ad410-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final State state = (State) getRelatedElement();
        
        // Unmask all missing clauses.
        if (state != null && state.isValid()) {
            for (Region region : state.getOwnedRegion()) {
                if (getChild(new MRef(region)) == null)
                    getDiagram().unmask(this, region, null);
            }
        
        }
    }

    @objid ("f57ad413-55b6-11e2-877f-002564c97630")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
        if (this.getChildren().isEmpty()) {
            setVisible(false);
        }
    }

    @objid ("f57ad419-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        if (this.getChildren().isEmpty()) {
            setVisible(true);
        }
        super.addChild(child);
    }

    @objid ("f57c5a79-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmRegionsGroup.");
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

    @objid ("f57c5a7f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("isVisible", this.isVisible);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmRegionsGroup.", GmRegionsGroup.MINOR_VERSION);
    }

    @objid ("f57c5a85-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.isVisible = ((Boolean) in.readProperty("isVisible")).booleanValue();
    }

    @objid ("f57c5a8a-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
