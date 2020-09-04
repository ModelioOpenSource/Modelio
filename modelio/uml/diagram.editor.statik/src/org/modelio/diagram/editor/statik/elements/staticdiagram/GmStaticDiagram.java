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

package org.modelio.diagram.editor.statik.elements.staticdiagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link StaticDiagram}
 * 
 * @author phv
 */
@objid ("c1014b7b-55b6-11e2-877f-002564c97630")
public class GmStaticDiagram extends GmAbstractDiagram {
    @objid ("36b2fe9c-55b7-11e2-877f-002564c97630")
    private StaticDiagram obDiagram;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("36b2fea1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("36b2fea4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("64941849-5bd5-11e2-9e33-00137282c51b")
    private static final GmStaticDiagramStyleKeys STYLEKEYS = new GmStaticDiagramStyleKeys();

    /**
     * Creates a diagram model.
     * 
     * @param modelManager The model manager.
     * @param el The represented diagram.
     * @param ref The represented diagram reference.
     */
    @objid ("36b2fea9-55b7-11e2-877f-002564c97630")
    public GmStaticDiagram(IModelManager modelManager, StaticDiagram el, MRef ref) {
        super(modelManager, ref);
        this.obDiagram = el;
    }

    @objid ("36b2feb5-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ((NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type)) ||
                Instance.class.isAssignableFrom(type) ||
                Constraint.class.isAssignableFrom(type) ||
                Note.class.isAssignableFrom(type) ||
                ProvidedInterface.class.isAssignableFrom(type) ||
                NaryAssociation.class.isAssignableFrom(type) ||
                Behavior.class.isAssignableFrom(type) || BpmnProcess.class.isAssignableFrom(type));
    }

    @objid ("36b2febd-55b7-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        // Static diagram can unmask almost anything, except for elements from state, activity and interaction
        return !(el instanceof StateVertex ||
                el instanceof Transition ||
                el instanceof ActivityNode ||
                el instanceof ActivityEdge ||
                el instanceof InteractionFragment || el instanceof Message || el instanceof BpmnMessage);
    }

    @objid ("36b4853f-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("36b48546-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmStaticDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("36b48550-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmStaticDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("36b48559-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmStaticDiagram.");
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

    @objid ("36b4855f-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Nothing to refresh
    }

    @objid ("36b48562-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("36b4856c-55b7-11e2-877f-002564c97630")
    @Override
    public StaticDiagram getRepresentedElement() {
        return this.obDiagram;
    }

    @objid ("36b48573-55b7-11e2-877f-002564c97630")
    @Override
    public StaticDiagram getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("36b4857a-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmStaticDiagram.", GmStaticDiagram.MINOR_VERSION);
    }

    @objid ("36b60bd9-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.obDiagram = (StaticDiagram) resolveRef(getRepresentedRef());
    }

    @objid ("36b60bde-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmStaticDiagram.MAJOR_VERSION;
    }

    @objid ("c797529f-fec1-4f84-a342-f9a0a88be934")
    @Override
    public String getFactoryIdentifier() {
        return StaticDiagram.MNAME;
    }

    @objid ("1ddae434-870f-46f3-9a3e-c416a087ed5e")
    @Override
    public boolean canUnmaskGenericElements() {
        return true;
    }

}
