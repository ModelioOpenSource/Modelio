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
package org.modelio.uml.activitydiagram.editor.contributor;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;

@objid ("f30448dd-af27-4bc0-a04b-ac3378c1997b")
public class ActivityDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("ad39d050-753c-4631-9327-9de1b27bc10c")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("e1d68c7a-600a-4f32-8f58-d1ee1f31c041")
    public  ActivityDiagramTemplate() {
        super();
    }

    @objid ("8f0d2d61-8b57-4d08-9add-88ac463c47c2")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("708023bd-7416-4304-9133-076c4a6d3679")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        final IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Depending on the nature of 'base', carry out the proper creation actions.
        ActivityDiagram diagram = null;
        
        if (base instanceof Activity) {
            diagram = smartCreateForActivity(modelFactory, (Activity) base);
        } else if ((base instanceof Classifier) && !(base instanceof UseCase)) {
            diagram = smartCreateForClassifier(modelFactory, (Classifier) base);
        } else if (base instanceof Operation) {
            diagram = smartCreateForOperation(modelFactory, (Operation) base);
        } else if (base instanceof NameSpace) {
            diagram = smartCreateForNameSpace(modelFactory, (NameSpace) base);
        }
        return diagram;
    }

    @objid ("498e72ed-9cdf-4367-a77a-5031d192a179")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("93c24236-61aa-488b-baa8-c94cccadced3")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("ccfa8971-27c4-46d2-9c20-8b135edcb8c0")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("f12bcc29-d1b4-4d7d-afe4-3a41e6b049f8")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

    /**
     * Create an activity diagram under 'activity' and sets its default name.
     * @return the created activity diagram or null in case of problems.
     */
    @objid ("ba01baf3-5d78-4ecf-831c-fb90ffbe574c")
    private ActivityDiagram createActivityDiagram(final IStandardModelFactory modelFactory, final Activity activity) {
        // Create the Activity diagram:
        ActivityDiagram diagram = modelFactory.createActivityDiagram();
        activity.getProduct().add(diagram);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        diagram.setIsVertical(false);
        return diagram;
    }

    /**
     * Checks that the given activity currently owns a 'locals' Collaboration. IF not, create it.
     * @return
     */
    @objid ("6f49d94c-761d-4057-bc1e-02c6f4ab3e23")
    protected Collaboration ensureLocalCollaboration(IStandardModelFactory modelFactory, final Activity interaction) {
        Collaboration locals = null;
        // Look for an existing local Collaboration
        for (Collaboration collab : interaction.getOwnedCollaboration()) {
            locals = collab;
            break;
        }
        
        // Create the local Collaboration if none exists
        if (locals == null) {
            locals = modelFactory.createCollaboration();
            interaction.getOwnedCollaboration().add(locals);
            locals.setName("locals");
        }
        return locals;
    }

    /*
         * {@link #createView(ModelElement)} delegated implementation when base element is an existing Activity.
         */
    @objid ("f6153029-8346-4f91-af63-cd4ee3f3536c")
    private ActivityDiagram smartCreateForActivity(IStandardModelFactory modelFactory, final Activity parentActivity) {
        ActivityDiagram diagram = createActivityDiagram(modelFactory, parentActivity);
        if (diagram != null) {
            ensureLocalCollaboration(modelFactory, parentActivity);
        }
        return diagram;
    }

    /*
         * {@link #createView(ModelElement)} delegated implementation when base element is a NameSpace.
         */
    @objid ("f9d2250f-3d68-4ff2-8bff-3cb2dba93a62")
    private ActivityDiagram smartCreateForNameSpace(IStandardModelFactory modelFactory, final NameSpace parentNameSpace) {
        Activity activity = modelFactory.createActivity();
        activity.setOwner(parentNameSpace);
        activity.setName(this.mmServices.getElementNamer().getUniqueName(activity));
        
        ActivityDiagram diagram = createActivityDiagram(modelFactory, activity);
        if (diagram != null) {
            ensureLocalCollaboration(modelFactory, activity);
        }
        return diagram;
    }

    /*
         * {@link #createView(ModelElement)} delegated implementation when base element is a Classifier.
         */
    @objid ("c1c64c43-1d2d-4b55-bd99-73970644f478")
    private ActivityDiagram smartCreateForClassifier(IStandardModelFactory modelFactory, final Classifier parentClassifier) {
        Activity activity = modelFactory.createActivity();
        activity.setOwner(parentClassifier);
        activity.setName(this.mmServices.getElementNamer().getUniqueName(activity));
        
        ActivityDiagram diagram = createActivityDiagram(modelFactory, activity);
        if (diagram != null) {
            // Create the locals Collaboration
            Collaboration locals = ensureLocalCollaboration(modelFactory, activity);
            if (locals != null) {
                // Create the instance:
                BindableInstance instance = modelFactory.createBindableInstance();
                locals.getDeclared().add(instance);
                instance.setName("this");
                instance.setBase(parentClassifier);
        
                // Create the corresponding InstanceNode:
                InstanceNode instanceNode = modelFactory.createInstanceNode();
                if (instanceNode != null) {
                    activity.getOwnedNode().add(instanceNode);
                    instanceNode.setName("this");
                    instanceNode.setRepresented(instance);
                }
            }
        }
        return diagram;
    }

    /**
     * {@link #createView(ModelElement)} delegated implementation when base element is an Operation.
     */
    @objid ("43441324-a015-4b27-9951-0c4de51a1192")
    private ActivityDiagram smartCreateForOperation(IStandardModelFactory modelFactory, final Operation parentOperation) {
        Activity activity = modelFactory.createActivity();
        activity.setOwnerOperation(parentOperation);
        activity.setName(this.mmServices.getElementNamer().getUniqueName(activity));
        
        ActivityDiagram diagram = createActivityDiagram(modelFactory, activity);
        if (diagram != null) {
            // Create the locals Collaboration
            Collaboration locals = ensureLocalCollaboration(modelFactory, activity);
            if (locals != null) {
                // Create the instance
                BindableInstance instance = modelFactory.createBindableInstance();
                locals.getDeclared().add(instance);
                instance.setName("this");
                instance.setBase(parentOperation.getOwner());
        
                // Create the corresponding InstanceNode:
                InstanceNode instanceNode = modelFactory.createInstanceNode();
                if (instanceNode != null) {
                    activity.getOwnedNode().add(instanceNode);
                    instanceNode.setName("this");
                    instanceNode.setRepresented(instance);
                }
            }
        
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
        
                    if (parameterNode != null) {
                        activity.getOwnedNode().add(parameterNode);
                        parameterNode.setRepresentedRealParameter(behaviorParameter);
                        parameterNode.setName(parameterName);
                    }
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
                    if (parameterNode != null) {
                        parameterNode.setRepresentedRealParameter(behaviorParameter);
                        parameterNode.setName(parameterName);
                    }
                }
            }
        }
        return diagram;
    }

}
