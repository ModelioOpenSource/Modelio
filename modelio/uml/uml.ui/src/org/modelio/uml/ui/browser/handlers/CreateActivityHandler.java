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

package org.modelio.uml.ui.browser.handlers;

import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.IModelioService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.model.browser.view.handlers.create.CreateCmsElementHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8c7d2031-c9d8-11e1-b479-001ec947c8cc")
public class CreateActivityHandler extends CreateCmsElementHandler {
    @objid ("87804746-c36e-4375-b6bd-dfad361d785e")
    @Inject
     IModelioEventService eventService;

    @objid ("4e57e2bf-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected void postCreationStep(MObject createdElement, IMModelServices mmServices) {
        Activity interaction = (Activity) createdElement;
        // Create the diagram
        MObject owner = interaction.getCompositionOwner();
        if ((owner instanceof Classifier) && !(owner instanceof UseCase)) {
            smartCreateForClassifier(interaction, (Classifier) owner, mmServices);
        } else if (owner instanceof Operation) {
            smartCreateForOperation(interaction, (Operation) owner, mmServices);
        } else {
            smartCreateForNameSpace(interaction, mmServices);
        }
    }

    @objid ("53a5487d-ccff-11e1-97e5-001ec947c8cc")
    @Override
    protected void postCommit(MPart part, MObject element, IMModelServices mmServices) {
        final ActivityDiagram param = ((Activity) element).getProduct(ActivityDiagram.class).get(0);
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                CreateActivityHandler.this.eventService.postAsyncEvent(new IModelioService() {
                    @Override
                    public String getName() {
                        return "openEditor : AbstractDiagram";
                    }
                }, ModelioEvent.EDIT_ELEMENT, param);
            }
        });
    }

    @objid ("53a54882-ccff-11e1-97e5-001ec947c8cc")
    private void smartCreateForClassifier(Activity activity, Classifier parentClassifier, IMModelServices mmServices) {
        IStandardModelFactory modelFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        String activityName = activity.getName();
        
        // Create the Activity diagram:
        ActivityDiagram diagram = modelFactory.createActivityDiagram();
        activity.getProduct().add(diagram);
        diagram.setName(activityName + " diagram");
        
        // Create the locals Collaboration:
        Collaboration locals = modelFactory.createCollaboration();
        activity.getOwnedCollaboration().add(locals);
        locals.setName("locals");
        
        // Create the instance:
        BindableInstance instance = modelFactory.createBindableInstance();
        locals.getDeclared().add(instance);
        
        instance.setName("this");
        instance.setBase(parentClassifier);
        
        // Create the corresponding InstanceNode:
        InstanceNode instanceNode = modelFactory.createInstanceNode();
        activity.getOwnedNode().add(instanceNode);
        instanceNode.setName("this");
        instanceNode.setRepresented(instance);
        
        // TODO Unmask the InstanceNode in the diagram
    }

    @objid ("53a54886-ccff-11e1-97e5-001ec947c8cc")
    private void smartCreateForNameSpace(Activity activity, IMModelServices mmServices) {
        IStandardModelFactory modelFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        String activityName = activity.getName();
        // Create the Activity diagram:
        ActivityDiagram diagram = modelFactory.createActivityDiagram();
        activity.getProduct().add(diagram);
        diagram.setName(activityName + " diagram");
        
        // Create the locals Collaboration:
        Collaboration locals = modelFactory.createCollaboration();
        activity.getOwnedCollaboration().add(locals);
        locals.setName("locals");
    }

    @objid ("53a54889-ccff-11e1-97e5-001ec947c8cc")
    private void smartCreateForOperation(Activity activity, Operation parentOperation, IMModelServices mmServices) {
        IStandardModelFactory modelFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        String activityName = activity.getName();
        // Create the Activity diagram:
        ActivityDiagram diagram = modelFactory.createActivityDiagram();
        activity.getProduct().add(diagram);
        diagram.setName(activityName + " diagram");
        
        // Create the locals Collaboration:
        Collaboration locals = modelFactory.createCollaboration();
        activity.getOwnedCollaboration().add(locals);
        locals.setName("locals");
        
        // Create this pointer:
        
        // Create the instance:
        BindableInstance instance = modelFactory.createBindableInstance();
        locals.getDeclared().add(instance);
        instance.setName("this");
        instance.setBase(parentOperation.getOwner());
        
        // Create the corresponding InstanceNode:
        InstanceNode instanceNode = modelFactory.createInstanceNode();
        activity.getOwnedNode().add(instanceNode);
        instanceNode.setName("this");
        instanceNode.setRepresented(instance);
        
        // Create Activity parameters:
        List<Parameter> paramsList = parentOperation.getIO();
        BehaviorParameter behaviorParameter = null;
        ActivityParameterNode parameterNode = null;
        String parameterName;
        
        // IOParameters...
        for (Parameter parameter : paramsList) {
            // Create the BehaviorParameter:
            behaviorParameter = modelFactory.createBehaviorParameter();
            activity.getParameter().add(behaviorParameter);
            parameterName = parameter.getName();
        
            if (behaviorParameter != null) {
                behaviorParameter.setName(parameterName);
                behaviorParameter.setMultiplicityMin(parameter.getMultiplicityMin());
                behaviorParameter.setMultiplicityMax(parameter.getMultiplicityMax());
                behaviorParameter.setParameterPassing(parameter.getParameterPassing());
                behaviorParameter.setTypeConstraint(parameter.getTypeConstraint());
                behaviorParameter.setDefaultValue(parameter.getDefaultValue());
                behaviorParameter.setMapped(parameter);
                behaviorParameter.setType(parameter.getType());
        
                // Create the ParameterNode:
                parameterNode = modelFactory.createActivityParameterNode();
                activity.getOwnedNode().add(parameterNode);
                parameterNode.setRepresentedRealParameter(behaviorParameter);
                parameterNode.setName(parameterName);
            }
        }
        
        // return parameter
        Parameter returnParameter = parentOperation.getReturn();
        
        if (returnParameter != null) {
            // Create the BehaviorParameter:
            behaviorParameter = modelFactory.createBehaviorParameter();
            activity.getParameter().add(behaviorParameter);
            parameterName = returnParameter.getName();
        
            if (behaviorParameter != null) {
                behaviorParameter.setName(parameterName);
                behaviorParameter.setMultiplicityMin(returnParameter.getMultiplicityMin());
                behaviorParameter.setMultiplicityMax(returnParameter.getMultiplicityMax());
                behaviorParameter.setParameterPassing(returnParameter.getParameterPassing());
                behaviorParameter.setTypeConstraint(returnParameter.getTypeConstraint());
                behaviorParameter.setDefaultValue(returnParameter.getDefaultValue());
                behaviorParameter.setMapped(returnParameter);
                behaviorParameter.setType(returnParameter.getType());
        
                // Create the ParameterNode:
                parameterNode = modelFactory.createActivityParameterNode();
                activity.getOwnedNode().add(parameterNode);
        
                parameterNode.setRepresentedRealParameter(behaviorParameter);
                parameterNode.setName(parameterName);
            }
        }
        
        // TODO Unmask the InstanceNode in the diagram
        // TODO Unmask the parameter nodes in the diagram
    }

}
