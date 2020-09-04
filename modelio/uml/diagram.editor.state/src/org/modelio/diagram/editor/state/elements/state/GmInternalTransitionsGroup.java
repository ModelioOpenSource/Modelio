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

package org.modelio.diagram.editor.state.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.elements.internaltransition.GmInternalTransition;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Internal transitions group.
 * 
 * @author cmarin
 */
@objid ("f577c69a-55b6-11e2-877f-002564c97630")
public class GmInternalTransitionsGroup extends GmGroup {
    @objid ("f577c69e-55b6-11e2-877f-002564c97630")
    private boolean isVisible = false;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f577c69f-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f577c6a2-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initialize a group.
     * @param diagram The diagram.
     * @param relatedRef a reference to the represented state.
     */
    @objid ("f577c6a4-55b6-11e2-877f-002564c97630")
    public GmInternalTransitionsGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("f577c6ad-55b6-11e2-877f-002564c97630")
    public GmInternalTransitionsGroup() {
    }

    @objid ("f577c6b0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmInternalTransition.class == nodeClass;
    }

    @objid ("f5794d39-55b6-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        this.isVisible = visible;
    }

    @objid ("f5794d3d-55b6-11e2-877f-002564c97630")
    @Override
    public State getRelatedElement() {
        return (State) super.getRelatedElement();
    }

    @objid ("f5794d44-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        return canCreate(el.getClass()) &&
                                                                        new MRef(el.getCompositionOwner()).equals(this.getRepresentedRef());
    }

    @objid ("f5794d4c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    @objid ("f5794d51-55b6-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        final State state = getRelatedElement();
        if (state != null && state.isValid()) {
            setHiddenFeature(state.getInternal().size() > getChildren().size());
        }
    }

    @objid ("f5794d54-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return InternalTransition.class.isAssignableFrom(type);
    }

    @objid ("f5794d5c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInternalTransitionsGroup.");
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

    @objid ("f5794d62-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInternalTransitionsGroup.", GmInternalTransitionsGroup.MINOR_VERSION);
    }

    @objid ("f5794d68-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.isVisible = hasChildren();
    }

    @objid ("f5794d6d-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
