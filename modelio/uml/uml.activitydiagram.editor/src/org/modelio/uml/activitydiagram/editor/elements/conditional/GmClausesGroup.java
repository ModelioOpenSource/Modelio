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

package org.modelio.uml.activitydiagram.editor.elements.conditional;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.resizablegroup.GmResizableGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.uml.activitydiagram.editor.elements.clause.GmClause;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Displays all the clauses of a given conditional node.
 * 
 * @author cmarin
 */
@objid ("2a108c5e-55b6-11e2-877f-002564c97630")
public class GmClausesGroup extends GmResizableGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a108c62-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a108c65-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initialize a group.
     * 
     * @param diagram The diagram.
     * @param relatedRef a reference to the represented conditional node.
     */
    @objid ("2a108c67-55b6-11e2-877f-002564c97630")
    public GmClausesGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("2a108c70-55b6-11e2-877f-002564c97630")
    public GmClausesGroup() {
        // nothing
    }

    /**
     * This group can contain only {@link GmClause GmClauses}.
     */
    @objid ("2a108c73-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canContain(java.lang.Class<? extends GmNodeModel> nodeClass) {
        return GmClause.class.isAssignableFrom(nodeClass);
    }

    @objid ("2a1212da-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(java.lang.Class<? extends MObject> type) {
        return Clause.class.isAssignableFrom(type);
    }

    @objid ("2a1212e3-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return Clause.class.isAssignableFrom(el.getClass()) &&
                                        el.getCompositionOwner().equals(this.getRelatedElement());
    }

    @objid ("2a1212eb-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final ConditionalNode conditional = (ConditionalNode) getRelatedElement();
        
        // Unmask all missing clauses.
        if (conditional != null && conditional.isValid()) {
            for (Clause part : conditional.getOwnedClause()) {
                if (getChild(new MRef(part)) == null)
                    getDiagram().unmask(this, part, null);
            }
        
        }
    }

    @objid ("2a1212ee-55b6-11e2-877f-002564c97630")
    @Override
    protected final boolean isValidChild(final GmNodeModel node) {
        final MObject el = node.getRelatedElement();
        
        if (el == null || !el.isValid() || !canUnmask(el))
            return false;
        
        // Cannot unmask if the element is already displayed
        final GmNodeModel sameChild = getChild(node.getRepresentedRef());
        if (sameChild != null && sameChild != node)
            return false;
        return true;
    }

    @objid ("2a1212f7-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED;
    }

    @objid ("2a1212fc-55b6-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean visible) {
        if (visible) {
            StyleKey key = getStyleKey(MetaKey.REPMODE);
            if (key != null)
                getParent().getDisplayedStyle().setProperty(key, RepresentationMode.STRUCTURED);
        }
    }

    @objid ("2a121301-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmClausesGroup.");
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

    @objid ("2a121307-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmClausesGroup.", GmClausesGroup.MINOR_VERSION);
    }

    @objid ("2a12130d-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2a121312-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
