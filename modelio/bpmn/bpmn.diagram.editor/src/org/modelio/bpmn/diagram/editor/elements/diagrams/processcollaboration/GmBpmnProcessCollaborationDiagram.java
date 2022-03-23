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
package org.modelio.bpmn.diagram.editor.elements.diagrams.processcollaboration;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.GmBpmnMessage;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessageflow.GmBpmnMessageFlow;
import org.modelio.bpmn.diagram.editor.elements.diagrams.BpmnDiagramSymbolViewModelProvider;
import org.modelio.bpmn.diagram.editor.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.factories.BpmnGmLinkFactory;
import org.modelio.bpmn.diagram.editor.elements.participant.GmBpmnParticipantPortContainer;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a BpmnCollaborationDiagram.
 * <p>
 * Collaboration diagrams might display several workflows at the same time through participants, making the {@link GmBpmnProcessCollaborationDiagram} the owner of every {@link GmBpmnMessageFlow} linking said participants...
 * </p>
 * <p>
 * This is done without the message flow actually knowing anything, please see {@link BpmnGmLinkFactory} for {@link GmBpmnMessageFlow} instanciation
 * </p>
 */
@objid ("61f4b6db-55b6-11e2-877f-002564c97630")
public class GmBpmnProcessCollaborationDiagram extends GmAbstractDiagram {
    @objid ("61f4b6e0-55b6-11e2-877f-002564c97630")
    private BpmnCollaborationDiagram obDiagram;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("61f4b6e3-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("61f4b6e6-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("61f4b6df-55b6-11e2-877f-002564c97630")
    private static GmBpmnDiagramStyleKeys STYLEKEYS = new GmBpmnDiagramStyleKeys();

    /**
     * Default constructor.
     * @param manager the manager needed make the link between the Ob and Gm models.
     * @param diagram the diagram itself.
     * @param diagramRef a reference to the diagram.
     */
    @objid ("61f4b6e8-55b6-11e2-877f-002564c97630")
    public  GmBpmnProcessCollaborationDiagram(IModelManager manager, BpmnCollaborationDiagram diagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.obDiagram = diagram;
        
    }

    @objid ("61f4b6f7-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> metaclass) {
        if (Dependency.class.isAssignableFrom(metaclass) ||
                AbstractDiagram.class.isAssignableFrom(metaclass) ||
                Note.class.isAssignableFrom(metaclass) ||
                Document.class.isAssignableFrom(metaclass)) {
            return true;
        } else {
            return (BpmnParticipant.class.isAssignableFrom(metaclass) || BpmnProcess.class.isAssignableFrom(metaclass));
        }
        
    }

    @objid ("61f4b6ff-55b6-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        if (el instanceof Dependency ||
                el instanceof AbstractDiagram ||
                el instanceof Note ||
                el instanceof Document) {
            return true;
        } else if (el instanceof BpmnParticipant || el instanceof BpmnProcess || el instanceof BpmnBaseElement) {
            return true;
        } else {
            IMdaExpert mdaExpert = getModelManager().getMdaExpert();
            MMetamodel metamodel = getModelManager().getMetamodel();
            return mdaExpert.canLink(Process.MdaTypes.STEREOTYPE_ELT, metamodel.getMClass(MethodologicalLink.class), metamodel.getMClass(BpmnProcess.class), el.getMClass());
        }
        
    }

    @objid ("61f4b707-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        return null;
    }

    @objid ("61f4b711-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("61f4b718-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmBpmnProcessCollaborationDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("61f63d80-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnProcessCollaborationDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("61f63d89-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnProcessCollaborationDiagram.");
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

    @objid ("61f63d97-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnCollaborationDiagram getRepresentedElement() {
        return this.obDiagram;
    }

    @objid ("61f63d9e-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnCollaborationDiagram getRelatedElement() {
        return this.obDiagram;
    }

    @objid ("61f63da5-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnProcessCollaborationDiagram.", GmBpmnProcessCollaborationDiagram.MINOR_VERSION);
        
    }

    @objid ("61f63dab-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.obDiagram = (BpmnCollaborationDiagram) resolveRef(getRepresentedRef());
        
        // Map<BpmnProcess, Rectangle> toUnmask = new HashMap<>();
        for (GmNodeModel child : getChildren()) {
            if (child instanceof GmBpmnParticipantPortContainer || child instanceof GmBpmnMessage || !child.getClass().getName().startsWith("org.modelio.bpmn.diagram.editor")) {
                continue;
            } else {
                // Rectangle childConstraint = ((Rectangle) child.getLayoutData()).getCopy();
                //
                // BpmnProcess process = getOwnerProcess(child.getRepresentedElement());
                // Rectangle processConstraint = toUnmask.get(process);
                // if (processConstraint != null) {
                // processConstraint = processConstraint.union(childConstraint);
                // } else {
                // processConstraint = childConstraint;
                // }
                // toUnmask.put(process, processConstraint);
        
                // Mask elements directly in the diagram, their process should be unmasked but we can't create a new participant right here
                child.delete();
            }
        }
        
    }

    @objid ("61f63db0-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnProcessCollaborationDiagram.MAJOR_VERSION;
    }

    @objid ("a1944d10-d6bb-4b79-b0a9-9bff6846baae")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return BpmnDiagramSymbolViewModelProvider.create(getPersistedStyle(), this);
    }

    @objid ("d797d896-a449-4031-b148-f85575bcaec0")
    private BpmnProcess getOwnerProcess(final MObject element) {
        if (element instanceof BpmnProcess) {
            return (BpmnProcess) element;
        }
        return getOwnerProcess(element.getCompositionOwner());
    }

    /**
     * Hide participants that are not part of the currently edited collaboration.
     */
    @objid ("0d66774e-9b08-4251-a6f7-d604c865cb79")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        if (node instanceof GmBpmnParticipantPortContainer) {
            GmBpmnParticipantPortContainer gmParticipant = (GmBpmnParticipantPortContainer) node;
            BpmnParticipant participant = gmParticipant.getRelatedElement();
            return participant == null || Objects.equals(participant.getContainer(), this.obDiagram.getOrigin());
        } else {
            return super.isValidChild(node);
        }
        
    }

    @objid ("97596a36-f2ff-4acb-911e-68ac450aed3d")
    @Override
    public String getFactoryIdentifier() {
        return BpmnCollaborationDiagram.MNAME;
    }

    @objid ("c0094ead-c3b2-46c9-9a92-db127e100d29")
    @Override
    public boolean canUnmaskGenericElements() {
        return true;
    }

}
