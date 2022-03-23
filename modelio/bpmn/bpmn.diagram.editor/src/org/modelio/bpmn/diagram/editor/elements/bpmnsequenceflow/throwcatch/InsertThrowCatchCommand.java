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
package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.throwcatch;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This command cut the given SequenceFlow link in order to insert an paired of "throw link" and "catch link" events.
 */
@objid ("075c731d-f104-4aac-90d5-ffdfeb80bada")
class InsertThrowCatchCommand extends DefaultCreateElementCommand {
    @objid ("e9360a3d-9a77-4fd5-9703-5cc29d1dcdd4")
    private IGmDiagram diagram;

    @objid ("edfc2b39-f64d-4d29-b080-54da014fcf1b")
    private GmBpmnSequenceFlow gmFlow;

    @objid ("1773672e-f87d-4c03-819e-7b933bd49fd7")
    private BpmnIntermediateCatchEvent catchEvent;

    @objid ("2d8bbd39-1d7e-4b22-8f51-ccc100e6d063")
    private BpmnSequenceFlow newFlow;

    @objid ("f1f6597e-ff89-46ff-bd56-1dd79c77027a")
    private BpmnIntermediateThrowEvent throwEvent;

    @objid ("9c0e3839-6b54-4fa2-af7d-2a5d914ea1ae")
    private BpmnLinkEventDefinition throwEventLinkDefinition;

    @objid ("5939f12e-12a9-469f-bc9f-0b6cf114358b")
    private BpmnLinkEventDefinition catchEventLinkDefinition;

    @objid ("65ff5ae0-cf6c-4b6c-8219-6e63b3eee670")
    public  InsertThrowCatchCommand(IGmDiagram diagram, GmBpmnSequenceFlow gmFlow, ModelioCreationContext ctx, Rectangle requestRect) {
        super(diagram.getRelatedElement().getCompositionOwner(), getEventCompatibleParentNode(ctx.getMetaclass(), gmFlow), ctx, requestRect);
        this.diagram = diagram;
        this.gmFlow = gmFlow;
        
    }

    @objid ("ea5d6c5b-7462-449b-b996-ce87fd3106e0")
    @Override
    protected void beforeUnmask(MObject newElement) {
        // The command can be used to create either a Throw or a Catch link event.
        // Process according to the effective situation.
        if (newElement instanceof BpmnIntermediateThrowEvent) {
            doBeforeUnmask((BpmnIntermediateThrowEvent) newElement);
        } else if (newElement instanceof BpmnIntermediateCatchEvent) {
            doBeforeUnmask((BpmnIntermediateCatchEvent) newElement);
        }
        
    }

    /**
     * The method does not use its arguments and rely on the clearly defined class attributes: this.throwEvent and this.catchEvent.<br/>
     * The reason for this behavior is that newBpmnEvent and newBpmnEventGm may represent either the throw or the catch event depending on the user initial action.
     */
    @objid ("87248e32-495e-407e-9eab-7973039577e9")
    @Override
    protected void afterUnmask(MObject newBpmnEvent, GmNodeModel newBpmnEventGm) {
        // Process the newly created catch event:
        // - move the the catch event on the left side of its target
        // - unmask and route the flow between the catch event and its target
        BpmnSequenceFlow catchFlow = this.catchEvent.getOutgoing().get(0);
        BpmnFlowNode catchFlowTarget = catchFlow.getTargetRef();
        GmNodeModel catchEventGm = getGmForFlowNode(this.catchEvent, getCatchEventCompatibleParentNode((GmNodeModel) this.gmFlow.getTo()));
        GmNodeModel catchFlowTargetGm = getGmForFlowNode(catchFlowTarget, getParentNode());
        if (catchFlowTargetGm != null && catchEventGm != null) {
            Rectangle catchFlowTargetBounds = (Rectangle) catchFlowTargetGm.getLayoutData();
            Rectangle catchFlowTargetMainNodeBounds = (Rectangle) ((GmPortContainer) catchFlowTargetGm).getMainNode().getLayoutData();
            Rectangle catchEventBounds = (Rectangle) catchEventGm.getLayoutData();
            Rectangle catchMainNodeBounds = (Rectangle) ((GmPortContainer) catchEventGm).getMainNode().getLayoutData();
        
            // Compute the 'desired' position of the catch main node
            Rectangle catchMainNodeComputedBounds = new Rectangle(catchFlowTargetBounds.x + catchFlowTargetMainNodeBounds.getLeft().x - catchMainNodeBounds.width * 2,
                    catchFlowTargetBounds.y + catchFlowTargetMainNodeBounds.getLeft().y - catchMainNodeBounds.height / 2,
                    catchMainNodeBounds.width, catchMainNodeBounds.height);
        
            // Compute the required translation
            int dx = catchMainNodeComputedBounds.x - (catchEventBounds.x + catchMainNodeBounds.x);
            int dy = catchMainNodeComputedBounds.y - (catchEventBounds.y + catchMainNodeBounds.y);
        
            // Apply the translation and set the new catch event location
            catchEventGm.setLayoutData(catchEventBounds.translate(dx, dy).getCopy());
        
            // Unmask and route the flow between the catch event and its target node (anchors set to the figure centers)
            IGmPath gmpath = new GmPath();
            gmpath.setSourceAnchor(new GmFixedAnchor(1, 0, 1));
            gmpath.setTargetAnchor(new GmFixedAnchor(3, 0, 1));
            gmpath.setPathData(Collections.EMPTY_LIST);
            gmpath.setRouterKind(ConnectionRouterId.ORTHOGONAL);
            this.diagram.unmaskLink(catchFlow, ((GmPortContainer) catchEventGm).getMainNode(), ((GmPortContainer) catchFlowTargetGm).getMainNode(), gmpath);
        } else {
            // Unmask @ 0,0
            this.diagram.unmaskAsChild(this.catchEvent, new Rectangle(0, 0, -1, -1));
        }
        
        // Process the newly created catch event:
        // - move the the throw event on the right side of its source
        // - unmask and route the flow between the throw event and its source
        BpmnSequenceFlow throwFlow = this.throwEvent.getIncoming().get(0);
        BpmnFlowNode throwFlowSource = throwFlow.getSourceRef();
        GmNodeModel throwEventGm = getGmForFlowNode(this.throwEvent, getThrowEventCompatibleParentNode((GmNodeModel) this.gmFlow.getFrom()));
        GmNodeModel throwFlowSourceGm = getGmForFlowNode(throwFlowSource, getParentNode());
        
        if (throwFlowSourceGm != null && catchEventGm != null) {
            Rectangle throwFlowSourceBounds = (Rectangle) throwFlowSourceGm.getLayoutData();
            Rectangle throwFlowSourceMainNodeBounds = (Rectangle) ((GmPortContainer) throwFlowSourceGm).getMainNode().getLayoutData();
            Rectangle throwEventBounds = (Rectangle) throwEventGm.getLayoutData();
            Rectangle throwMainNodeBounds = (Rectangle) ((GmPortContainer) catchEventGm).getMainNode().getLayoutData();
        
            // Compute the 'desired' position of the throw main node
            Rectangle throwMainNodeComputedBounds = new Rectangle(throwFlowSourceBounds.x + throwFlowSourceMainNodeBounds.getRight().x - throwFlowSourceMainNodeBounds.x + throwMainNodeBounds.width,
                    throwFlowSourceBounds.y + throwFlowSourceMainNodeBounds.getRight().y - throwMainNodeBounds.height / 2,
                    throwMainNodeBounds.width, throwMainNodeBounds.height);
        
            // Compute the required translation
            int dx = throwMainNodeComputedBounds.x - (throwEventBounds.x + throwMainNodeBounds.x);
            int dy = throwMainNodeComputedBounds.y - (throwEventBounds.y + throwMainNodeBounds.y);
        
            // Apply the translation and set the new throw event location
            throwEventGm.setLayoutData(throwEventBounds.translate(dx, dy).getCopy());
        
            // Unmask and route the flow between the throw event and its source node (anchors set to the figure centers)
            IGmPath gmpath = new GmPath();
            gmpath.setSourceAnchor(new GmFixedAnchor(1, 0, 1));
            gmpath.setTargetAnchor(new GmFixedAnchor(3, 0, 1));
            gmpath.setPathData(Collections.EMPTY_LIST);
            gmpath.setRouterKind(ConnectionRouterId.ORTHOGONAL);
            this.diagram.unmaskLink(throwFlow, ((GmPortContainer) throwFlowSourceGm).getMainNode(), ((GmPortContainer) throwEventGm).getMainNode(), gmpath);
        
            this.gmFlow.delete();
        }
        
    }

    /**
     * The 'new element' created by the command is a throw, create the matching catch and branch everything.
     */
    @objid ("acc9af23-15e4-4df6-b5da-e5257d0db710")
    private void doBeforeUnmask(BpmnIntermediateThrowEvent aThrowEvent) {
        final IModelManager modelManager = this.diagram.getModelManager();
        final IModelFactory modelFactory = modelManager.getModelFactory();
        MMetamodel metamodel = aThrowEvent.getMClass().getMetamodel();
        
        // Name the throw event
        this.throwEvent = aThrowEvent;
        this.throwEventLinkDefinition = this.throwEvent.getEventDefinitions(BpmnLinkEventDefinition.class).get(0);
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        this.throwEvent.setName(elementNamer.getUniqueName("N", this.throwEvent));
        
        // Create the catch event and its event definition
        this.catchEvent = (BpmnIntermediateCatchEvent) modelFactory.createElement(metamodel.getMClass(BpmnIntermediateCatchEvent.class), getParentElement(), getParentElement().getMClass().getDependency("FlowElement"));
        this.catchEvent.setName(this.throwEvent.getName());
        this.catchEventLinkDefinition = modelFactory.createElement(BpmnLinkEventDefinition.class);
        this.catchEventLinkDefinition.setDefined(this.catchEvent);
        
        // Connect them
        branchEvents();
        
    }

    /**
     * The 'new element' created by the command is a catch, create the matching throw and branch everything.
     */
    @objid ("99b10a22-d386-4b53-9f05-4b5214a797e1")
    private void doBeforeUnmask(BpmnIntermediateCatchEvent aCatchEvent) {
        final IModelManager modelManager = this.diagram.getModelManager();
        final IModelFactory modelFactory = modelManager.getModelFactory();
        MMetamodel metamodel = aCatchEvent.getMClass().getMetamodel();
        
        // Name the catch event, no need to create
        this.catchEvent = aCatchEvent;
        this.catchEventLinkDefinition = this.catchEvent.getEventDefinitions(BpmnLinkEventDefinition.class).get(0);
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        this.catchEvent.setName(elementNamer.getUniqueName("N", this.catchEvent));
        
        // Create the throw event and its event definition
        this.throwEvent = (BpmnIntermediateThrowEvent) modelFactory.createElement(metamodel.getMClass(BpmnIntermediateThrowEvent.class), getParentElement(), getParentElement().getMClass().getDependency("FlowElement"));
        this.throwEvent.setName(this.catchEvent.getName());
        this.throwEventLinkDefinition = modelFactory.createElement(BpmnLinkEventDefinition.class);
        this.throwEventLinkDefinition.setDefined(this.throwEvent);
        
        // Connect them
        branchEvents();
        
    }

    @objid ("972ae6e6-bb45-4c2a-a1ba-18b2bb3b146a")
    private void branchEvents() {
        final IModelManager modelManager = this.diagram.getModelManager();
        final IModelFactory modelFactory = modelManager.getModelFactory();
        final MMetamodel metamodel = this.catchEvent.getMClass().getMetamodel();
        
        // Connect the target of the original flow to the throwEvent
        BpmnFlowNode oldTarget = ((BpmnSequenceFlow) this.gmFlow.getRelatedElement()).getTargetRef();
        ((BpmnSequenceFlow) this.gmFlow.getRelatedElement()).setTargetRef(this.throwEvent);
        
        // Branch the catch event using a new flow
        this.throwEventLinkDefinition.getSource().add(this.catchEventLinkDefinition);
        this.catchEventLinkDefinition.setTarget(this.throwEventLinkDefinition);
        
        this.newFlow = (BpmnSequenceFlow) modelFactory.createElement(metamodel.getMClass(BpmnSequenceFlow.class), getParentElement(), getParentElement().getMClass().getDependency("FlowElement"));
        this.newFlow.setSourceRef(this.catchEvent);
        this.newFlow.setTargetRef(oldTarget);
        
    }

    @objid ("4e661045-b77f-4173-b231-7fe442f50240")
    private GmNodeModel getGmForFlowNode(BpmnFlowNode event, GmCompositeNode gmParentNode) {
        List<GmModel> existingGms = this.diagram.getAllGMRepresenting(new MRef(event));
        
        GmNodeModel model = null;
        if (existingGms.isEmpty()) {
            model = this.diagram.unmask(gmParentNode, event, new Rectangle(0, 0, -1, -1));
        } else {
            model = (GmNodeModel) existingGms.get(0);
        }
        return model;
    }

    @objid ("cebf129e-824e-4264-8bb4-cb44cdfbd044")
    private static GmCompositeNode getEventCompatibleParentNode(MClass metaclass, GmBpmnSequenceFlow gmFlow) {
        if (metaclass.getQualifiedName().equals(BpmnIntermediateThrowEvent.MQNAME)) {
            return getThrowEventCompatibleParentNode((GmNodeModel) gmFlow.getFrom());
        } else {
            return getCatchEventCompatibleParentNode((GmNodeModel) gmFlow.getTo());
        }
        
    }

    @objid ("809a0f06-4ba7-4a63-9b52-fd169d64c590")
    private static GmCompositeNode getThrowEventCompatibleParentNode(GmNodeModel gm) {
        if (gm.canCreate(BpmnThrowEvent.class) && gm instanceof GmCompositeNode) {
            return (GmCompositeNode) gm;
        } else {
            return getThrowEventCompatibleParentNode(gm.getParentNode());
        }
        
    }

    @objid ("c8013f02-ea95-4d17-bdaf-49dfc4481e98")
    private static GmCompositeNode getCatchEventCompatibleParentNode(GmNodeModel gm) {
        if (gm.canCreate(BpmnThrowEvent.class) && gm instanceof GmCompositeNode) {
            return (GmCompositeNode) gm;
        } else {
            return getCatchEventCompatibleParentNode(gm.getParentNode());
        }
        
    }

}
