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

package org.modelio.diagram.editor.bpmn.elements.diagrams.subprocess;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.editor.bpmn.elements.diagrams.BpmnDiagramSymbolViewModelProvider;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.editor.bpmn.elements.workflow.IWorkflowProvider;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.externdocument.GmExternDocument;
import org.modelio.diagram.elements.umlcommon.note.GmNote;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a BpmnProcessCollaborationDiagram.
 */
@objid ("61fddea1-55b6-11e2-877f-002564c97630")
public class GmBpmnSubProcessDiagram extends GmAbstractDiagram implements IWorkflowProvider {
    @objid ("61fddea6-55b6-11e2-877f-002564c97630")
    private BpmnSubProcessDiagram obDiagram;

    /**
     * Current version of this Gm.
     */
    @objid ("61fddea9-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61fddeac-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Role for the workflow.
     */
    @objid ("44f4e539-f378-4f8a-8f32-df561bee0346")
    public static final String ROLE_BODY = "diagram.body";

    @objid ("61fddea5-55b6-11e2-877f-002564c97630")
    private static GmBpmnDiagramStyleKeys STYLEKEYS = new GmBpmnDiagramStyleKeys();

    @objid ("1fc7f0b4-953e-488c-8a43-0b186b443eed")
    private GmWorkflow body;

    /**
     * Default constructor.
     * @param manager the manager needed make the link between the Ob and Gm models.
     * @param diagram the diagram itself.
     * @param diagramRef a reference to the diagram.
     */
    @objid ("61fddeae-55b6-11e2-877f-002564c97630")
    public GmBpmnSubProcessDiagram(final IModelManager manager, final BpmnSubProcessDiagram diagram, final MRef diagramRef) {
        super(manager, diagramRef);
        this.obDiagram = diagram;
        
        // GmWorkflow creation
        createBody();
    }

    @objid ("61fddec0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return acceptMetaclass(type);
    }

    @objid ("61fddec9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(final MObject el) {
        return false;
    }

    @objid ("61fdded2-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        if (this.body != null) {
            return this.body.getCompositeFor(metaclass);
        }
        return null;
    }

    @objid ("61ff653b-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("61ff6542-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return GmBpmnSubProcessDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("61ff654d-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnSubProcessDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("61ff6556-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnSubProcessDiagram.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    /**
     * Returns true if the given metaclass is supported.
     * @param metaclass
     * @return true if the given metaclass is supported.
     */
    @objid ("61ff655d-55b6-11e2-877f-002564c97630")
    private boolean acceptMetaclass(final Class<? extends MObject> metaclass) {
        if (BpmnProcess.class.isAssignableFrom(metaclass) ||
                BpmnBoundaryEvent.class.isAssignableFrom(metaclass)) {
            return false;
        }
        if (Dependency.class.isAssignableFrom(metaclass) ||
                AbstractDiagram.class.isAssignableFrom(metaclass) ||
                Constraint.class.isAssignableFrom(metaclass) ||
                Document.class.isAssignableFrom(metaclass) ||
                Note.class.isAssignableFrom(metaclass)) {
            return true;
        }
        return (BpmnBaseElement.class.isAssignableFrom(metaclass));
    }

    @objid ("61ff6566-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnSubProcessDiagram getRepresentedElement() {
        return this.obDiagram;
    }

    @objid ("61ff656d-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnSubProcessDiagram getRelatedElement() {
        return this.obDiagram;
    }

    @objid ("61ff6574-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnSubProcessDiagram.", GmBpmnSubProcessDiagram.MINOR_VERSION);
    }

    @objid ("61ff657a-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.obDiagram = (BpmnSubProcessDiagram) resolveRef(getRepresentedRef());
        
        this.body = null;
        
        // Look for body
        List<GmNodeModel> oldChildren = getChildren();
        for (GmNodeModel oldChild : oldChildren) {
            if (oldChild instanceof GmWorkflow) {
                this.body = (GmWorkflow) oldChild;
            }
        }
        
        if (this.body == null) {
            this.body = new GmWorkflow(this, getRepresentedRef());
            this.body.setRoleInComposition(GmBpmnProcessDesignDiagram.ROLE_BODY);
            super.addChild(this.body);
        }
        
        // Create the workflow if needed, after a migration it might already have been created by refreshFromObModel
        if (this.body == null) {
            this.body = new GmWorkflow(this, getRepresentedRef());
            this.body.setRoleInComposition(GmBpmnSubProcessDiagram.ROLE_BODY);
            super.addChild(this.body);
        }
        
        // Remove all children of the diagram except its workflow
        for (GmNodeModel oldChild : getChildren()) {
            if (Objects.equals(oldChild.getRelatedElement(), getRelatedElement().getOrigin())) {
                // The SubProcess was unmasked in the diagram, move workflow's children
                Rectangle layoutData = (Rectangle) oldChild.getLayoutData();
        
                for (GmNodeModel newChild : this.body.getChildren()) {
                    Rectangle newProcessLayoutData = ((Rectangle) newChild.getLayoutData())
                            .getCopy()
                            .translate(layoutData.getTopLeft())
                            .translate(4, 25);
                    newChild.setLayoutData(newProcessLayoutData);
                }
        
                // Delete unwanted child
                oldChild.delete();
            }
            if (!oldChild.getRepresentedRef().equals(getRepresentedRef())) {
                // Delete unwanted child
                oldChild.delete();
            }
        }
    }

    @objid ("6200ebdd-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnSubProcessDiagram.MAJOR_VERSION;
    }

    @objid ("7bdd2d75-e9b9-4107-9c7c-dafc50f60f89")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return BpmnDiagramSymbolViewModelProvider.create(getPersistedStyle(), this);
    }

    @objid ("f79aa1a6-6587-474e-a656-be62de26b733")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.obDiagram = (BpmnSubProcessDiagram) resolveRef(getRepresentedRef());
        
        this.body = (GmWorkflow) getFirstChild(GmBpmnSubProcessDiagram.ROLE_BODY);
    }

    @objid ("3de8b656-f6d9-4a7c-8a2a-a5b467d6543a")
    @Override
    public void addChild(GmNodeModel child) {
        if (child instanceof GmNote || child instanceof GmExternDocument) {
            // Notes & rich notes should be in the workflow
            getWorkflow().addChild(child);
            return;
        } else {
            super.addChild(child);
        }
    }

    @objid ("cc14053c-41cc-4b11-8b96-7a7f2933e35f")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
    }

    @objid ("b3597966-c40a-4d6e-8cf4-ecc78f4dd862")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        firePropertyChange(GmModel.PROP_REFRESH_FROM_OBMODEL, null, this);
    }

    @objid ("06649ac5-526a-4820-8659-719112d6fa0c")
    @Override
    public GmWorkflow getWorkflow() {
        return this.body;
    }

    @objid ("2c83e9a9-5219-4c1a-9391-b98b05130865")
    @Override
    public void delete() {
        this.body = null;
        super.delete();
    }

    @objid ("d2f2f60f-9e1c-402b-8772-d2c34ad8b064")
    private void createBody() {
        this.body = new GmWorkflow(this, getRepresentedRef());
        this.body.setRoleInComposition(GmBpmnSubProcessDiagram.ROLE_BODY);
        addChild(this.body);
    }

    @objid ("dd678702-0928-4475-9459-4495d91b35d9")
    @Override
    protected void reset(boolean hasPersistedData) {
        super.reset(hasPersistedData);
        
        if (!hasPersistedData) {
            createBody();
        }
    }

    /**
     * A sub-process diagram is editable by the user if the diagram model object is modifiable and the diagram is "local".
     */
    @objid ("61f19fee-c14c-490f-a386-9adbb2c6f338")
    @Override
    public boolean isUserEditable() {
        return super.isUserEditable() && isLocal();
    }

    /**
     * A sub-process is local if its owner diagram is also local, or null.
     * @return <code>true</code> if the sub-process is local.
     */
    @objid ("7bf51053-252b-47e7-a811-045a2dffde96")
    private boolean isLocal() {
        IGmDiagram diagramOwner = getDiagramOwner();
        if (diagramOwner == null) {
            return true;
        } else if (diagramOwner instanceof GmBpmnProcessDesignDiagram) {
            return ((GmBpmnProcessDesignDiagram) diagramOwner).isLocal();
        } else if (diagramOwner instanceof GmBpmnSubProcessDiagram) {
            return ((GmBpmnSubProcessDiagram) diagramOwner).isLocal();
        } else {
            // Not in a process diagram,
            return false;
        }
    }

    @objid ("5620548e-2d22-4471-8fc1-9e8524c072d5")
    @Override
    public String getFactoryIdentifier() {
        return BpmnSubProcessDiagram.MNAME;
    }

    @objid ("bc99e598-5dc7-4ec3-8cf7-b5baa1546f24")
    @Override
    public boolean canUnmaskGenericElements() {
        return false;
    }

}
