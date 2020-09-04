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

package org.modelio.diagram.editor.state.elements.statediagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a ActivityDiagram.
 */
@objid ("f58d2379-55b6-11e2-877f-002564c97630")
public class GmStateDiagram extends GmAbstractDiagram {
    @objid ("f58d237e-55b6-11e2-877f-002564c97630")
    private StateMachineDiagram obDiagram;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f58d2381-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f58d2384-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("fe4f91ce-5a5b-11e2-9e33-00137282c51b")
    private static GmStateDiagramStyleKeys STYLEKEYS = new GmStateDiagramStyleKeys();

    /**
     * Initialize the diagram.
     * 
     * @param manager The model manager
     * @param theStateDiagram the displayed diagram.
     * @param diagramRef the reference of the displayed diagram. Must reference a {@link StateMachineDiagram}.
     */
    @objid ("f58d2386-55b6-11e2-877f-002564c97630")
    public GmStateDiagram(IModelManager manager, StateMachineDiagram theStateDiagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.obDiagram = theStateDiagram;
    }

    @objid ("f58d2395-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return acceptMetaclass(type);
    }

    @objid ("f58d239d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        if (el == null) {
            return false;
        }
        if (el instanceof Region) {
            return (el.getCompositionOwner() != null && el.getCompositionOwner() instanceof State);
        }
        return acceptMetaclass(el.getClass());
    }

    @objid ("f58eaa00-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (acceptMetaclass(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("f58eaa0a-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("f58eaa11-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmStateDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("f58eaa1b-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmStateDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("f58eaa24-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmStateDiagram.");
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

    @objid ("f58eaa2a-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Nothing to do.
    }

    @objid ("f58eaa2d-55b6-11e2-877f-002564c97630")
    @Override
    public StateMachineDiagram getRepresentedElement() {
        return this.obDiagram;
    }

    @objid ("f58eaa34-55b6-11e2-877f-002564c97630")
    private boolean acceptMetaclass(Class<? extends MObject> metaclass) {
        return (StateMachine.class.isAssignableFrom(metaclass) ||
                StateVertex.class.isAssignableFrom(metaclass) ||
                Transition.class.isAssignableFrom(metaclass) ||
                Region.class.isAssignableFrom(metaclass) ||
                Dependency.class.isAssignableFrom(metaclass) ||
                Note.class.isAssignableFrom(metaclass) ||
                Constraint.class.isAssignableFrom(metaclass) ||
                AbstractDiagram.class.isAssignableFrom(metaclass));
    }

    @objid ("f58eaa3b-55b6-11e2-877f-002564c97630")
    @Override
    public StateMachineDiagram getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("f590309b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmStateDiagram.", GmStateDiagram.MINOR_VERSION);
    }

    @objid ("f59030a1-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.obDiagram = (StateMachineDiagram) resolveRef(this.getRepresentedRef());
    }

    @objid ("f59030a6-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmStateDiagram.MAJOR_VERSION;
    }

    @objid ("998841c9-f892-4b03-9970-10235c2def23")
    @Override
    public String getFactoryIdentifier() {
        return StateMachineDiagram.MNAME;
    }

    @objid ("3609c9e0-eac0-4fa2-a0d7-62e74132d1db")
    @Override
    public boolean canUnmaskGenericElements() {
        return false;
    }

}
