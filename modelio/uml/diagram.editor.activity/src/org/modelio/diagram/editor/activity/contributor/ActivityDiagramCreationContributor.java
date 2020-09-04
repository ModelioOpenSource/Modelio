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

package org.modelio.diagram.editor.activity.contributor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.contributor.DefaultWizardPreviewPanel;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.activity.plugin.DiagramEditorActivity;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.osgi.framework.Bundle;

/**
 * Creation contributor for Activity diagrams.
 */
@objid ("3664129d-c44c-42d5-9b20-19ba9c9f759f")
public class ActivityDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("0e4a7fa7-01b6-4df3-b778-07a47e3a4307")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("6af43cc0-8b68-49e1-9504-80decb7ef12e")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        if (diagramContext == null) {
            return null;
        }
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        ActivityDiagram diagram = null;
        
        // Unless the parent element is already an Activity, create the Activity:
        Activity activity = null;
        if (diagramContext instanceof Activity) {
            activity = (Activity) diagramContext;
            diagram = smartCreateForNameSpace(activity, diagramName);
        } else {
            activity = modelFactory.createActivity();
            // Create the diagram, depending on parentElement, carry out the "smart" creation job
            if ((diagramContext instanceof Classifier) && !(diagramContext instanceof UseCase)) {
                activity.setOwner((Classifier) diagramContext);
                setElementDefaultName(activity);
                diagram = smartCreateForClassifier(activity, (Classifier) diagramContext, diagramName);
            } else if (diagramContext instanceof Operation) {
                activity.setOwnerOperation((Operation) diagramContext);
                setElementDefaultName(activity);
                diagram = smartCreateForOperation(activity, (Operation) diagramContext, diagramName);
            } else {
                activity.setOwner((NameSpace) diagramContext);
                setElementDefaultName(activity);
                diagram = smartCreateForNameSpace(activity, diagramName);
            }
        }
        
        if (diagram != null) {
            if (diagramName.equals(getLabel())) {
                setElementDefaultName(diagram);
            } else {
                diagram.setName(diagramName);
            }
            putNoteContent(diagram, "description", diagramDescription);
        }
        return diagram;
    }

    @objid ("cf176d1d-7286-4eea-b1cc-13717e86dae4")
    @Override
    public String getDetails() {
        return DiagramEditorActivity.I18N.getString("CreationWizard.Activity.Details");
    }

    @objid ("07603279-059e-4064-97f3-156c1ebc3a43")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(ActivityDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("5ab94334-9c3b-4bf4-90ad-8ec3f49759a7")
    @Override
    public String getInformation() {
        return DiagramEditorActivity.I18N.getString("CreationWizard.Activity.Information");
    }

    @objid ("a3583af6-0fac-4d11-a810-d54b31ba9dfd")
    @Override
    public String getLabel() {
        return DiagramEditorActivity.I18N.getString("CreationWizard.Activity.Name");
    }

    @objid ("d1c90426-e81b-4a40-9346-b3f2f2417fd9")
    protected Collaboration checkLocalCollaboration(IStandardModelFactory modelFactory, final Activity interaction) {
        Collaboration locals = null;
        // Look for an existing local Collaboration
        for (Collaboration collab : interaction.getOwnedCollaboration()) {
            locals = collab;
            break;
        }
        
        if (locals == null) {
            // Create the local Collaboration
            locals = modelFactory.createCollaboration();
            interaction.getOwnedCollaboration().add(locals);
            locals.setName("locals");
        }
        return locals;
    }

    @objid ("58b64a94-432a-4e2a-badb-84b8ea883d4d")
    private ActivityDiagram createActivityDiagram(final IStandardModelFactory modelFactory, final String diagramName, final Activity activity) {
        // Create the Activity diagram:
        ActivityDiagram diagram = modelFactory.createActivityDiagram(diagramName, activity);
        activity.getProduct().add(diagram);
        if (diagram != null) {
            diagram.setIsVertical(false);
        }
        return diagram;
    }

    @objid ("71190f3d-bd60-44cb-8f8c-dc4948804d7d")
    private ActivityDiagram smartCreateForClassifier(final Activity activity, final NameSpace parentClassifier, final String diagramName) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        ActivityDiagram diagram = createActivityDiagram(modelFactory, diagramName, activity);
        if (diagram != null) {
            // Create the locals Collaboration
            Collaboration locals = checkLocalCollaboration(modelFactory, activity);
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

    @objid ("7bb24f54-c149-4fb7-bcd4-93d1a41f02d6")
    private ActivityDiagram smartCreateForNameSpace(final Activity activity, final String diagramName) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        ActivityDiagram diagram = createActivityDiagram(modelFactory, diagramName, activity);
        if (diagram != null) {
            checkLocalCollaboration(modelFactory, activity);
        }
        return diagram;
    }

    @objid ("80b4bf0a-d258-40e0-8552-807c552d6767")
    private ActivityDiagram smartCreateForOperation(final Activity activity, final Operation parentOperation, final String diagramName) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        ActivityDiagram diagram = createActivityDiagram(modelFactory, diagramName, activity);
        if (diagram != null) {
            // Create the locals Collaboration
            Collaboration locals = checkLocalCollaboration(modelFactory, activity);
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

    @objid ("f360aa85-dec6-4103-985d-a425c7240d5c")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Package.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Interface.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Signal.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Actor.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Node.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(UseCase.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Collaboration.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Operation.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Activity.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("ca214171-d81e-4906-937e-6147dba44283")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorActivity.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/activitydiagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("feecc4d2-6d3b-4588-bc51-6bd215f1808f")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("fd8a63c7-43e0-48d8-aa0a-e99203f78466")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("48e9ba52-cc90-4d0d-8ede-8d27cc8f0003")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("6b4e0b05-544e-4a8a-b964-86c88e401a57")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("4c550d48-3e6b-4a54-b777-05136bc938c2")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, ActivityDiagram.MQNAME);
    }

    @objid ("5ed5c708-5247-43ca-ac6a-089b691aeeec")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ActivityDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
