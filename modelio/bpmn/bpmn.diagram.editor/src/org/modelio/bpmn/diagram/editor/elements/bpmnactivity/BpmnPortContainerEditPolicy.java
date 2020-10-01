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

package org.modelio.bpmn.diagram.editor.elements.bpmnactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.bpmn.diagram.editor.elements.policies.BpmnBoundaryEventReparentElementCommand;
import org.modelio.bpmn.diagram.editor.elements.policies.BpmnFlowElementReparentElementCommand;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPolicy;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("6078c55f-55b6-11e2-877f-002564c97630")
public class BpmnPortContainerEditPolicy extends PortContainerEditPolicy {
    @objid ("6078c562-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        MObject hostElement = getHostElement();
        Object requestConstraint = getConstraintFor(request);
        
        final ModelioCreationContext ctx = ModelioCreationContext.fromRequest(request);
        MObject elementToUnmask = ctx.getElementToUnmask();
        
        if (elementToUnmask != null) {
            // Unmasking an existing element
            if (getHostCompositeNode().canUnmask(elementToUnmask)) {
                return new BpmnActivityCreateBoundaryEventCommand(hostElement, getHostCompositeNode(), ctx, requestConstraint);
            } else {
                return null;
            }
        } else {
            // Creating a new element, deal with smart interactions
            if (BpmnDataOutput.class.equals(ctx.getJavaClass())) {
                return new BpmnActivityCreateDataOutputCommand(hostElement, getHostCompositeNode(), ctx, requestConstraint);
                
            } else if (BpmnDataInput.class.equals(ctx.getJavaClass())) {
                return new BpmnActivityCreateDataInputCommand(hostElement, getHostCompositeNode(), ctx, requestConstraint);
                
            } else {
                return new BpmnActivityCreateBoundaryEventCommand(hostElement, getHostCompositeNode(), ctx, requestConstraint);
            }
        }
    }

    @objid ("6078c568-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            return getTargetEditPart(createRequest);
        }
        return super.getTargetEditPart(request);
    }

    @objid ("6078c56e-55b6-11e2-877f-002564c97630")
    private EditPart getTargetEditPart(CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
        
            if (ctx.getElementToUnmask() != null) {
                if (getHostCompositeNode().canUnmask(ctx.getElementToUnmask())) {
                    return getHost();
                } else {
                    return null;
                }
            }
        
            if (canHandle(ctx.getMetaclass(), ctx.getDependencyName())) {
                return getHost();
            }
        
        }
        return null;
    }

    @objid ("6078c573-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean canHandle(MClass metaclass, String dep) {
        // Smart interaction available for BpmnDataOutput
        if (BpmnDataOutput.class.equals(metaclass.getJavaInterface())) {
            return true;
        }
        // Smart interaction available for BpmnDataOutput
        else if (BpmnDataInput.class.equals(metaclass.getJavaInterface())) {
            return true;
        }
        // Standard interaction
        else {
            return ((GmCompositeNode) getHost().getModel()).canCreate(metaclass.getJavaInterface());
        }
    }

    @objid ("6078c57b-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        GmNodeModel gmmodel = (GmNodeModel) child.getModel();
        MObject element = gmmodel.getRelatedElement();
        
        if (element instanceof BpmnBoundaryEvent) {
            return new BpmnBoundaryEventReparentElementCommand(getHostElement(),
                    getHostCompositeNode(),
                    (GmNodeModel) child.getModel(),
                    constraint);
        } else if (element instanceof BpmnFlowElement) {
            return new BpmnFlowElementReparentElementCommand(getHostElement(),
                    getHostCompositeNode(),
                    (GmNodeModel) child.getModel(),
                    constraint);
        } else {
            return new DefaultReparentElementCommand(getHostElement(),
                    getHostCompositeNode(),
                    (GmNodeModel) child.getModel(),
                    constraint);
        }
    }

}
