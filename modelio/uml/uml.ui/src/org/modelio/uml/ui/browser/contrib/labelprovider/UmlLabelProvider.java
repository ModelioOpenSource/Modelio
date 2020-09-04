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

package org.modelio.uml.ui.browser.contrib.labelprovider;

import java.util.List;
import java.util.Stack;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.core.ui.swt.images.ElementStyler;
import org.modelio.core.ui.swt.images.IModelioElementLabelProvider;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.uml.ui.browser.contrib.labelprovider.builders.BpmnItemAwareElementLabelBuilder;
import org.modelio.uml.ui.browser.contrib.labelprovider.builders.BpmnMessageLabelBuilder;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default label provider with Uml elements for the model browser.
 */
@objid ("31f1e14d-5a25-4f93-88ae-fcba61ee8ef7")
public class UmlLabelProvider extends LabelProvider implements IModelioElementLabelProvider {
    @objid ("de8ca65d-755b-4d99-b646-a2b27726b0a3")
    private boolean showFeatureVisibility = true;

    @objid ("7bea6342-62a9-408a-ac31-0ef5c298cbf7")
    private boolean showNamespaceVisibility = false;

    @objid ("04b1721e-3f86-439f-b080-be353dae201a")
    protected BrowserLabelService umlLabelService;

    /**
     * Default c'tor.
     */
    @objid ("913792d9-9b96-427c-97db-e74381555f93")
    public UmlLabelProvider() {
        this.umlLabelService = new BrowserLabelService();
    }

    @objid ("7ceea02e-e57b-448d-a535-004c8363d1a5")
    @Override
    public Image getImage(Object object) {
        if (object instanceof MObject) {
            return ElementImageService.getIcon((MObject) object);
        }
        
        // Unknown object
        return null;
    }

    @objid ("5b5f95a3-b188-471a-97d2-2deee57b67fe")
    @Override
    public StyledString getStyledText(Object obj) {
        if (obj == null) {
            return new StyledString("<null>", StyledString.createColorRegistryStyler("red", null));
        } else if (obj instanceof MObject) {
            return this.umlLabelService.getLabel((MObject) obj, this.showFeatureVisibility, this.showNamespaceVisibility);
        }
        
        // Unknown object
        return null;
    }

    /**
     * Returns the simple label of an object, without style additions. Uses {@link IModelioElementLabelProvider#getStyledText(Object)}.
     */
    @objid ("14f60196-f9ed-4f2d-b894-56f0c8dd24ce")
    @Override
    public String getText(Object element) {
        return getStyledText(element).getString();
    }

    /**
     * @return <code>true</code> if feature visibility is shown.
     */
    @objid ("560b0585-100d-4f0a-8340-8ca8ea1778b6")
    public boolean isShowFeatureVisibility() {
        return this.showFeatureVisibility;
    }

    /**
     * Enable or disable display of feature visibility.
     * @param showFeatureVisibility <code>true</code> to enable, <code>false</code> to disable.
     */
    @objid ("815b6533-5514-4850-af5c-30f78ba12775")
    public void setShowFeatureVisibility(boolean showFeatureVisibility) {
        this.showFeatureVisibility = showFeatureVisibility;
    }

    /**
     * @return <code>true</code> if namespace visibility is shown.
     */
    @objid ("26c835a7-64ba-4d5b-97f5-a82f9face913")
    public boolean isShowNamespaceVisibility() {
        return this.showNamespaceVisibility;
    }

    /**
     * Enable or disable display of namespace visibility.
     * @param showFeatureVisibility <code>true</code> to enable, <code>false</code> to disable.
     */
    @objid ("973f6fbb-41a2-4b54-a84e-ae595076cdcb")
    public void setShowNamespaceVisibility(boolean showFeatureVisibility) {
        this.showNamespaceVisibility = showFeatureVisibility;
    }

    @objid ("cd8a14ad-4516-4134-9aaa-d749cad271c6")
    @Override
    public boolean showAsReference(Object object) {
        return false;
    }

    /**
     * This class provide the label of the elements displayed in the UML explorer
     */
    @objid ("b646f0a3-c585-4469-9654-9cbc555aa0e6")
    private static class BrowserLabelService extends DefaultModelVisitor {
        @objid ("ac4a5f53-2e36-4ca4-acdb-3a4f6e1358a0")
        private boolean showFeaturesVisibility;

        @objid ("3c8d52fc-2ab3-4ec9-a051-a69961ade139")
        private boolean showNamespaceVisibility;

        /**
         * a stack used for recursive calls to {@link #getLabel(Element, boolean)}
         */
        @objid ("42af7404-e558-4bec-abe1-5368ea1c61a0")
        private final Stack<MObject> elementStack;

        @objid ("3c2b90de-c749-4695-9254-51a8b2793aa8")
        public BrowserLabelService() {
            this(new Stack<MObject>());
        }

        /**
         * Get the explorer label for the given element.
         * @param element The element to get symbol
         * @param featuresVisibility Whether or not to show the visibility in feature's labels.
         * @param namespaceVisibility Whether or not to show the visibility in namespace's labels.
         * @return The element symbol.
         */
        @objid ("62a08341-ad0b-475e-8261-ea66aa4a90a6")
        public StyledString getLabel(MObject element, boolean featuresVisibility, boolean namespaceVisibility) {
            // Guard agains't null elements
            if (element == null) {
                return new StyledString("<null>", ElementStyler.getStyler(null));
            }
            
            if (this.elementStack.contains(element)) {
                // loop detected, return the name...
                return new StyledString(element.getName());
            }
            
            // store the element for loop detection, push context
            boolean oldFeaturesVisibility = this.showFeaturesVisibility;
            boolean oldNamespaceVisibility = this.showNamespaceVisibility;
            this.elementStack.push(element);
            
            try {
                // reset the returned value
            
                this.showFeaturesVisibility = featuresVisibility;
                this.showNamespaceVisibility = namespaceVisibility;
            
                // call the visitor
                return (StyledString) element.accept(this);
            
            } finally {
                this.showFeaturesVisibility = oldFeaturesVisibility;
                this.showNamespaceVisibility = oldNamespaceVisibility;
                this.elementStack.pop();
            }
        }

        @objid ("2ed8a6ba-2a9d-40f5-ac7c-0e81be1a9069")
        @Override
        public Object visitAbstraction(Abstraction obj) {
            final ModelElement destination = obj.getDependsOn();
            return visitDependencyLikeObject(obj, "abstracts", destination);
        }

        @objid ("db1d13b6-447e-4123-ba79-2954063ce0ad")
        @Override
        public Object visitAcceptCallEventAction(AcceptCallEventAction theAcceptCallEventAction) {
            final StyledString symbol = new StyledString();
            
            final String acceptCallEventActionName = theAcceptCallEventAction.getName();
            final Operation operation = theAcceptCallEventAction.getCalled();
            
            if ((operation != null)
                    && (acceptCallEventActionName.equals("Unnamed") || acceptCallEventActionName.equals(""))) {
                symbol.append(operation.getName(), ElementStyler.getStyler(theAcceptCallEventAction, operation));
            } else {
                symbol.append(acceptCallEventActionName, ElementStyler.getStyler(theAcceptCallEventAction));
            }
            return symbol;
        }

        @objid ("ee504961-86e4-45a4-8d50-04554ff094b2")
        @Override
        public Object visitAcceptSignalAction(AcceptSignalAction theAcceptSignalAction) {
            final StyledString symbol = new StyledString();
            
            final String acceptSignalActionName = theAcceptSignalAction.getName();
            final List<Signal> signals = theAcceptSignalAction.getAccepted();
            final Styler styler = ElementStyler.getStyler(theAcceptSignalAction);
            if ((signals.size() > 0)
                    && (acceptSignalActionName.equals("Unnamed") || acceptSignalActionName.equals(""))) {
                for (int i = 0; i < signals.size(); i++) {
                    if (i > 0) {
                        symbol.append(", ", styler);
                    }
                    symbol.append(signals.get(i).getName(),
                            ElementStyler.getStyler(theAcceptSignalAction, signals.get(i)));
                }
            } else {
                symbol.append(acceptSignalActionName, styler);
            }
            return symbol;
        }

        @objid ("1dfde606-8ebf-484c-86fc-4acdfac131ce")
        @Override
        public Object visitActivityEdge(ActivityEdge theActivityEdge) {
            final StyledString symbol = new StyledString();
            
            final ActivityNode target = theActivityEdge.getTarget();
            
            if (target != null) {
                StyledString styled_label = getLabel(target, false, false);
                if (styled_label != null) {
                    symbol.append(styled_label.toString(), ElementStyler.getStyler(theActivityEdge, target));
                } else {
                    final String target_name = target.getName();
                    symbol.append(target_name, ElementStyler.getStyler(theActivityEdge));
                }
            
            } else {
                final String name = theActivityEdge.getName();
                symbol.append(name, ElementStyler.getStyler(theActivityEdge));
            }
            return symbol;
        }

        @objid ("b8cfdb52-66f5-4f4c-a409-ff3ac83b7274")
        @Override
        public Object visitActivityParameterNode(ActivityParameterNode theActivityParameterNode) {
            final StyledString symbol = new StyledString();
            
            // PassingMode passingMode =
            // theActivityParameterNode.getParameterPassing();
            final Styler styler = ElementStyler.getStyler(theActivityParameterNode);
            final GeneralClass type = theActivityParameterNode.getType();
            
            final BehaviorParameter behaviorParameter = theActivityParameterNode.getRepresentedRealParameter();
            
            if (behaviorParameter != null) {
                symbol.append(behaviorParameter.getName(),
                        ElementStyler.getStyler(theActivityParameterNode, behaviorParameter));
                symbol.append(" ");
            } else {
                symbol.append(theActivityParameterNode.getName(), styler);
                symbol.append(" ");
            }
            
            Parameter theParameter = null;
            
            if (behaviorParameter != null) {
                theParameter = behaviorParameter.getMapped();
            }
            
            if ((theParameter != null) && (theParameter.getComposed() != null)) {
                final PassingMode passingMode = theParameter.getParameterPassing();
            
                if (passingMode == PassingMode.IN) {
                    symbol.append("In", styler);
                }
                if (passingMode == PassingMode.OUT) {
                    symbol.append("Out", styler);
                }
                if (passingMode == PassingMode.INOUT) {
                    symbol.append("Inout", styler);
                }
            } else if ((theParameter != null) && (theParameter.getReturned() != null)) {
                symbol.append("Out", styler);
            }
            
            if (behaviorParameter != null) {
                symbol.append(": ", styler);
                if (type != null) {
                    symbol.append(type.getName(), ElementStyler.getStyler(theActivityParameterNode, type));
                } else {
                    symbol.append(UmlUi.I18N.getString("NoType"), styler);
                }
            }
            return symbol;
        }

        @objid ("e258690d-037d-4387-b801-3e387b5deed7")
        @Override
        public Object visitActivityPartition(ActivityPartition theActivityPartition) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theActivityPartition);
            symbol.append(theActivityPartition.getName(), styler);
            
            final ModelElement represented = theActivityPartition.getRepresented();
            if (represented != null) {
                symbol.append(":", styler);
                final Styler representedStyler = ElementStyler.getStyler(theActivityPartition, represented);
                if (represented instanceof ActivityPartition) {
                    symbol.append(represented.getName(), representedStyler);
                } else {
                    StyledString label = getLabel(represented, false, false);
                    symbol.append(label != null ? label.toString() : "", representedStyler);
                }
            }
            return symbol;
        }

        @objid ("f9355439-e9e9-4922-866e-1362aab29811")
        @Override
        public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theAssociationEnd);
            
            if (this.showFeaturesVisibility) {
                final VisibilityMode visibility = theAssociationEnd.getVisibility();
                symbol.append(getVisibilitySymbol(visibility), styler);
            }
            if (theAssociationEnd.isIsDerived()) {
                symbol.append("/", styler);
            }
            
            final String associationEndName = theAssociationEnd.getName();
            if (associationEndName.isEmpty()) {
                symbol.append(UmlUi.I18N.getString("NoName"), styler);
            } else {
                symbol.append(theAssociationEnd.getName(), styler);
            }
            
            symbol.append(": ", styler);
            
            // The type
            final Classifier type = theAssociationEnd.getTarget();
            if (type != null) {
                symbol.append(type.getName(), ElementStyler.getStyler(theAssociationEnd, type));
            } else {
                symbol.append(UmlUi.I18N.getString("NoType"), styler);
            }
            
            // The cardinality
            symbol.append(BrowserLabelService.getAssociationEndMultiplicity(theAssociationEnd).toString(), styler);
            return symbol;
        }

        @objid ("9b4b019d-12bf-4742-b006-37331e65a4c6")
        @Override
        public Object visitAttribute(Attribute theAttribute) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theAttribute);
            
            if (this.showFeaturesVisibility) {
                final VisibilityMode visibility = theAttribute.getVisibility();
                symbol.append(getVisibilitySymbol(visibility), styler);
            }
            
            if (theAttribute.isIsDerived()) {
                symbol.append("/", styler);
            }
            
            symbol.append(theAttribute.getName(), styler);
            
            final GeneralClass type = theAttribute.getType();
            
            symbol.append(" : ", styler);
            if (type != null) {
                symbol.append(type.getName(), ElementStyler.getStyler(theAttribute, type));
            } else {
                symbol.append(UmlUi.I18N.getString("NoType"), styler);
            }
            
            symbol.append(BrowserLabelService.getAttributeMultiplicity(theAttribute).toString(), styler);
            
            final String value = theAttribute.getValue();
            if ((value != null) && !value.equals("")) {
                symbol.append(" = ", styler);
                symbol.append(value, styler);
            }
            return symbol;
        }

        @objid ("91f47eac-d5ff-449d-b653-c7c2b76591ba")
        @Override
        public Object visitAttributeLink(AttributeLink theAttributeLink) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theAttributeLink);
            final Attribute base = theAttributeLink.getBase();
            
            if (base != null) {
                symbol.append(base.getName(), ElementStyler.getStyler(theAttributeLink, base));
            } else {
                symbol.append(theAttributeLink.getName(), styler);
            }
            
            final String value = theAttributeLink.getValue();
            
            if (!value.equals("")) {
                symbol.append(" = ", styler);
                symbol.append(value, styler);
            }
            return symbol;
        }

        @objid ("c61f9f0e-91a8-4ca1-ade6-30daf8dfe60b")
        @Override
        public Object visitBehaviorParameter(BehaviorParameter theBehaviorParameter) {
            final Parameter theParameter = theBehaviorParameter.getMapped();
            final Styler styler = ElementStyler.getStyler(theBehaviorParameter);
            final StyledString symbol = new StyledString();
            
            final PassingMode passingMode = theBehaviorParameter.getParameterPassing();
            
            final GeneralClass type = theBehaviorParameter.getType();
            if (theParameter != null) {
                final Styler paraStyler = ElementStyler.getStyler(theBehaviorParameter, theParameter);
                if (theParameter.getComposed() != null) {
                    symbol.append(theParameter.getName(), paraStyler);
                    symbol.append(" ", paraStyler);
            
                    if (passingMode == PassingMode.IN) {
                        symbol.append("In", paraStyler);
                    }
                    if (passingMode == PassingMode.OUT) {
                        symbol.append("Out", paraStyler);
                    }
                    if (passingMode == PassingMode.INOUT) {
                        symbol.append("Inout", paraStyler);
                    }
                } else {
                    symbol.append("Out", paraStyler);
                }
            } else {
                symbol.append(theBehaviorParameter.getName(), styler);
            }
            
            symbol.append(": ");
            if (type != null) {
                symbol.append(type.getName(), ElementStyler.getStyler(theBehaviorParameter, type));
            } else {
                symbol.append(UmlUi.I18N.getString("NoType"), styler);
            }
            return symbol;
        }

        @objid ("fb044d3a-400c-4662-a192-0a1a9f6a3ba4")
        @Override
        public Object visitBinding(Binding theBinding) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theBinding);
            
            final ModelElement role = theBinding.getRole();
            final ModelElement feature = theBinding.getRepresentedFeature();
            
            if (role != null) {
                StyledString label = getLabel(role, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theBinding, role));
            }
            symbol.append(" --> ", styler);
            if (feature != null) {
                StyledString label = getLabel(feature, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theBinding, feature));
            }
            return symbol;
        }

        @objid ("292c7655-1ed5-4a8f-b359-0a3b906098d4")
        @Override
        public Object visitBpmnCallActivity(BpmnCallActivity obj) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(obj);
            
            if (obj.getName() != null) {
                symbol.append(obj.getName(), styler);
            }
            
            MObject called = Called.getTarget(obj);
            if (called != null) {
                symbol.append(" call ", styler);
                StyledString subLabel = getLabel(called, false, false);
                symbol.append(subLabel == null ? called.getName() : subLabel.toString(), ElementStyler.getStyler(obj, called));
            }
            return symbol;
        }

        /**
         * BpmnItemAwareElement are Bpmn data objects (input, output datastore ...)
         */
        @objid ("c7af6ce6-4e6a-4f13-94cb-3af1a9e41cc3")
        @Override
        public Object visitBpmnItemAwareElement(BpmnItemAwareElement elt) {
            return BpmnItemAwareElementLabelBuilder.computeLabel(elt);
        }

        @objid ("11e352e7-e2a1-437b-8b08-2f28ffa6f3f8")
        @Override
        public Object visitBpmnLane(BpmnLane lane) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(lane);
            symbol.append(lane.getName(), styler);
            
            ModelElement type = PartitionElement.getTarget(lane);
            if (type != null) {
                symbol.append(": ", styler);
                symbol.append(type.getName(), ElementStyler.getStyler(lane, type));
            }
            return symbol;
        }

        @objid ("060feba5-56d8-4ae6-9b74-49c02c9332bc")
        @Override
        public Object visitBpmnMessage(BpmnMessage m) {
            return BpmnMessageLabelBuilder.computeLabel(m);
        }

        @objid ("126e63b4-d5a1-46ec-919a-b413ef74e2b8")
        @Override
        public Object visitBpmnMessageFlow(BpmnMessageFlow theFlow) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theFlow);
            
            if (theFlow.getName() != null) {
                symbol.append(theFlow.getName(), styler);
                symbol.append(" {", styler);
            }
            
            if (theFlow.getSourceRef() != null) {
                symbol.append(theFlow.getSourceRef().getName(), ElementStyler.getStyler(theFlow.getSourceRef()));
            } else {
                symbol.append("?", styler);
            }
            symbol.append(": ", styler);
            
            symbol.append("", styler);
            if (theFlow.getMessageRef() != null) {
                symbol.append(theFlow.getMessageRef().getName(), ElementStyler.getStyler(theFlow.getMessageRef()));
            } else {
                symbol.append("?", styler);
            }
            
            symbol.append(" -> ", styler);
            
            if (theFlow.getTargetRef() != null) {
                symbol.append(theFlow.getTargetRef().getName(), ElementStyler.getStyler(theFlow.getTargetRef()));
            } else {
                symbol.append("?", styler);
            }
            symbol.append("}", styler);
            return symbol;
        }

        @objid ("05733480-72ca-4691-ac50-cad420bc7c3d")
        @Override
        public Object visitBpmnParticipant(BpmnParticipant theParticipant) {
            final Styler styler = ElementStyler.getStyler(theParticipant);
            final StyledString symbol = new StyledString();
            
            String s = "";
            
            ModelElement representedElement = Represents.getTarget(theParticipant);
            BpmnProcess process = theParticipant.getProcess();
            if (process != null) {
                if ((theParticipant.getName() == null || theParticipant.getName().isEmpty())) {
                    // the participant has no proper name
                    s = process.getName();
                } else {
                    // the participant has its proper name
                    s = String.format("%s (%s)", theParticipant.getName(), process.getName());
                }
            } else if (representedElement != null) {
                if ((theParticipant.getName() == null || theParticipant.getName().isEmpty())) {
                    // the participant has no proper name
                    s = representedElement.getName();
                } else {
                    // the participant has its proper name
                    s = String.format("%s (%s)", theParticipant.getName(), representedElement.getName());
                }
            } else {
                s = theParticipant.getName() != null ? theParticipant.getName() : "";
            }
            symbol.append(s, styler);
            return symbol;
        }

        @objid ("c44794d5-d6f7-4ec5-ab87-fc6a0f0f66dc")
        @Override
        public Object visitCallBehaviorAction(CallBehaviorAction theCallBehaviorAction) {
            final StyledString symbol = new StyledString();
            
            final String callBehaviorActionName = theCallBehaviorAction.getName();
            final Behavior behavior = theCallBehaviorAction.getCalled();
            
            if ((behavior != null) && (callBehaviorActionName.equals("Unnamed") || callBehaviorActionName.equals(""))) {
                symbol.append(behavior.getName(), ElementStyler.getStyler(theCallBehaviorAction, behavior));
            } else {
                symbol.append(callBehaviorActionName, ElementStyler.getStyler(theCallBehaviorAction));
            }
            return symbol;
        }

        @objid ("d9a45b85-54a6-4df8-ba82-182820120a98")
        @Override
        public Object visitCallOperationAction(CallOperationAction theCallOperationAction) {
            final StyledString symbol = new StyledString();
            
            final String callOperationActionName = theCallOperationAction.getName();
            final Operation operation = theCallOperationAction.getCalled();
            
            if ((operation != null)
                    && (callOperationActionName.equals("Unnamed") || callOperationActionName.equals(""))) {
                symbol.append(operation.getName(), ElementStyler.getStyler(theCallOperationAction, operation));
            } else {
                symbol.append(callOperationActionName, ElementStyler.getStyler(theCallOperationAction));
            }
            return symbol;
        }

        @objid ("fb9accba-b84e-445b-9ee5-6f75417736f8")
        @Override
        public Object visitClassAssociation(ClassAssociation theClassAssociation) {
            final StyledString symbol = new StyledString();
            
            final Class theClass = theClassAssociation.getClassPart();
            
            if (theClass != null) {
                symbol.append(theClass.getName(), ElementStyler.getStyler(theClassAssociation, theClass));
            }
            return symbol;
        }

        @objid ("fb7c0945-3689-48a5-b716-340008cbc65c")
        @Override
        public Object visitClause(Clause theClause) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theClause);
            symbol.append("[", styler);
            
            final String test = theClause.getTest();
            if (test.length() == 0) {
                symbol.append("Conditional clause", styler);
            } else {
                symbol.append(test, styler);
            }
            
            symbol.append("]", styler);
            return symbol;
        }

        @objid ("1669fe9f-e799-4ad6-8bdf-127b9c5bcd03")
        @Override
        public Object visitCollaborationUse(CollaborationUse theCollaborationUse) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theCollaborationUse);
            symbol.append(theCollaborationUse.getName(), styler);
            
            final Collaboration base = theCollaborationUse.getType();
            symbol.append(": ", styler);
            if (base != null) {
                symbol.append(base.getName(), ElementStyler.getStyler(theCollaborationUse, base));
            } else {
                symbol.append(UmlUi.I18N.getString("NoBase"), styler);
            }
            return symbol;
        }

        @objid ("15a097f0-1339-48b2-a375-d139eb657940")
        @Override
        public Object visitCommunicationMessage(CommunicationMessage theCommunicationMessage) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theCommunicationMessage);
            final String name = theCommunicationMessage.getName();
            final MessageSort messageSort = theCommunicationMessage.getSortOfMessage();
            
            if (name.equals("")) {
                symbol.append(UmlUi.I18N.getString(messageSort.name()), styler);
            } else {
                symbol.append(name, styler);
            }
            
            if ((messageSort != MessageSort.SYNCCALL) && (messageSort != MessageSort.CREATEMESSAGE)) {
                symbol.append("(", styler);
                symbol.append(theCommunicationMessage.getArgument(), styler);
                symbol.append(")", styler);
            }
            return symbol;
        }

        @objid ("ce69f830-606d-494a-9272-89f41098559d")
        @Override
        public Object visitCommunicationNode(CommunicationNode theCommunicationNode) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theCommunicationNode);
            final Instance instance = theCommunicationNode.getRepresented();
            
            if (instance != null) {
                StyledString label = getLabel(instance, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theCommunicationNode, instance));
            } else {
                symbol.append(theCommunicationNode.getName(), styler);
            }
            return symbol;
        }

        @objid ("f00c4366-b7ef-4497-a982-8e99d87b0d3e")
        @Override
        public Object visitComponentRealization(ComponentRealization obj) {
            final ModelElement destination = obj.getAbstraction();
            return visitDependencyLikeObject(obj, "realizes", destination);
        }

        @objid ("f584558b-289a-4f65-bb47-36dcdbad3ddb")
        @Override
        public Object visitDataFlow(DataFlow theDataFlow) {
            final StyledString symbol = new StyledString();
            
            final Signal signal = theDataFlow.getSModel();
            
            if (signal != null) {
                symbol.append(signal.getName(), ElementStyler.getStyler(theDataFlow, signal));
            } else {
                symbol.append(theDataFlow.getName(), ElementStyler.getStyler(theDataFlow));
            }
            return symbol;
        }

        @objid ("bcfd6713-5aaf-40f2-9191-ca6d306221d1")
        @Override
        public Object visitElementImport(ElementImport theElementImport) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theElementImport);
            final NameSpace importedNamespace = theElementImport.getImportedElement();
            
            if (importedNamespace != null) {
                final Styler importNamespaceStyler = ElementStyler.getStyler(theElementImport, importedNamespace);
                if (theElementImport.getVisibility() == VisibilityMode.PUBLIC) {
                    symbol.append("import ", styler);
                } else {
                    symbol.append("access ", styler);
                }
                symbol.append(importedNamespace.getName(), importNamespaceStyler);
            
                final ModelTree owner = importedNamespace.getOwner();
            
                appendFrom(symbol, theElementImport, owner, styler);
            
            } else {
                symbol.append("<No destination>", styler);
            }
            return symbol;
        }

        @objid ("21e082c1-04d9-45a2-b8c2-302b093fe445")
        @Override
        public Object visitElementRealization(ElementRealization obj) {
            final ModelElement destination = obj.getDependsOn();
            return visitDependencyLikeObject(obj, "realizes", destination);
        }

        @objid ("fd8befbd-6356-4e57-8408-18ea24b902f3")
        @Override
        public Object visitEvent(Event theEvent) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theEvent);
            final Operation operation = theEvent.getCalled();
            final Signal signal = theEvent.getModel();
            
            if (operation != null) {
                symbol.append(operation.getName(), ElementStyler.getStyler(theEvent, operation));
            } else if (signal != null) {
                symbol.append(signal.getName(), ElementStyler.getStyler(theEvent, signal));
            } else if (theEvent.getName().equals("")) {
                symbol.append(theEvent.getExpression(), styler);
            } else {
                symbol.append(theEvent.getName(), styler);
            }
            return symbol;
        }

        @objid ("adce808c-cee2-414c-a7d0-2e459e2ca281")
        @Override
        public Object visitExtensionPoint(ExtensionPoint theExtensionPoint) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theExtensionPoint);
            
            if (this.showFeaturesVisibility) {
                final VisibilityMode visibility = theExtensionPoint.getVisibility();
                symbol.append(getVisibilitySymbol(visibility), styler);
            }
            
            symbol.append(theExtensionPoint.getName(), styler);
            return symbol;
        }

        @objid ("98cd59c8-473c-48d0-8202-7e3cdec65745")
        @Override
        public Object visitFeature(Feature theFeature) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theFeature);
            if (this.showFeaturesVisibility) {
                final VisibilityMode visibility = theFeature.getVisibility();
                symbol.append(getVisibilitySymbol(visibility), styler);
            }
            
            symbol.append(theFeature.getName(), styler);
            return symbol;
        }

        @objid ("2a3791de-6b28-471f-9f51-572f9767106c")
        @Override
        public Object visitGeneralization(Generalization theGeneralization) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theGeneralization);
            symbol.append(UmlUi.I18N.getString("IsA"), styler);
            symbol.append(" ");
            
            final NameSpace parent = theGeneralization.getSuperType();
            
            if (parent != null) {
                StyledString label = getLabel(parent, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theGeneralization, parent));
            
                final ModelTree owner = parent.getOwner();
                appendFrom(symbol, theGeneralization, owner, styler);
            }
            return symbol;
        }

        @objid ("7553a171-11bd-43b2-a832-ebeeb15b95ef")
        @Override
        public Object visitInformationFlow(InformationFlow theInformationFlow) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theInformationFlow);
            final List<Classifier> classifiers = theInformationFlow.getConveyed();
            if (classifiers.size() > 0) {
                for (int i = 0; i < classifiers.size(); i++) {
                    if (i > 0) {
                        symbol.append(", ", styler);
                    }
                    symbol.append(classifiers.get(i).getName(),
                            ElementStyler.getStyler(theInformationFlow, classifiers.get(i)));
                }
            } else {
                symbol.append(theInformationFlow.getName(), styler);
            }
            return symbol;
        }

        @objid ("32853e9f-e8e3-4c1a-b68a-a4d6c33eacef")
        @Override
        public Object visitInstance(Instance theInstance) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theInstance);
            symbol.append(theInstance.getName(), styler);
            
            final NameSpace base = theInstance.getBase();
            symbol.append(": ", styler);
            if (base != null) {
                symbol.append(base.getName(), ElementStyler.getStyler(theInstance, base));
            } else {
                symbol.append(UmlUi.I18N.getString("NoBase"), styler);
            }
            return symbol;
        }

        @objid ("a17ab13c-7a62-4126-afce-e3c715e68d39")
        @Override
        public Object visitInstanceNode(InstanceNode theInstanceNode) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theInstanceNode);
            final Instance instance = theInstanceNode.getRepresented();
            final Attribute attribut = theInstanceNode.getRepresentedAttribute();
            final AssociationEnd assocEnd = theInstanceNode.getRepresentedRole();
            final BehaviorParameter behaviorParameter = theInstanceNode.getRepresentedRealParameter();
            
            final GeneralClass type = theInstanceNode.getType();
            
            if (type != null) {
                symbol.append(theInstanceNode.getName(), styler);
            
                symbol.append(": ", styler);
                symbol.append(type.getName(), ElementStyler.getStyler(theInstanceNode, type));
            } else if (instance != null) {
                StyledString label = getLabel(instance, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theInstanceNode, instance));
            } else if (attribut != null) {
                StyledString label = getLabel(attribut, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theInstanceNode, attribut));
            } else if (assocEnd != null) {
                StyledString label = getLabel(assocEnd, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theInstanceNode, assocEnd));
            } else if (behaviorParameter != null) {
                StyledString label = getLabel(behaviorParameter, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theInstanceNode, behaviorParameter));
            } else {
                symbol.append(theInstanceNode.getName(), styler);
            }
            return symbol;
        }

        @objid ("82679344-1736-4c51-9e57-661f0f58dd8d")
        @Override
        public Object visitInterfaceRealization(InterfaceRealization theInterfaceRealization) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theInterfaceRealization);
            
            // symbol.append("Realize ");
            
            final Interface implemented = theInterfaceRealization.getImplemented();
            
            if (implemented != null) {
                StyledString label = getLabel(implemented, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theInterfaceRealization, implemented));
            
                final ModelTree owner = implemented.getOwner();
                appendFrom(symbol, theInterfaceRealization, owner, styler);
            }
            return symbol;
        }

        @objid ("73289743-2539-41f7-8b19-b2d072e0e6e2")
        @Override
        public Object visitInternalTransition(InternalTransition theInternalTransition) {
            StyledString symbol = (StyledString) super.visitInternalTransition(theInternalTransition);
            if (symbol.length() == 0) {
                symbol = new StyledString("/", ElementStyler.getStyler(theInternalTransition));
            }
            return symbol;
        }

        @objid ("c0406d39-3183-4839-a7ee-c8702e03a658")
        @Override
        public Object visitLinkEnd(LinkEnd theLinkEnd) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theLinkEnd);
            
            final String linkEndName = theLinkEnd.getName();
            if (linkEndName.equals("")) {
                symbol.append(UmlUi.I18N.getString("NoName"), styler);
            } else {
                symbol.append(linkEndName, styler);
            }
            final Instance linked = theLinkEnd.getTarget();
            if (linked != null) {
                symbol.append("::", styler);
                symbol.append(linked.getName(), ElementStyler.getStyler(theLinkEnd, theLinkEnd.getTarget()));
            }
            return symbol;
        }

        @objid ("d88d23bf-8c3e-4d62-8441-5bcd1c0d3a33")
        @Override
        public Object visitManifestation(Manifestation theManifestation) {
            final StyledString symbol = new StyledString();
            
            final Styler styler = ElementStyler.getStyler(theManifestation);
            final ModelElement destination = theManifestation.getUtilizedElement();
            
            if (destination != null) {
                symbol.append("manifested ", styler);
                symbol.append(destination.getName(), ElementStyler.getStyler(theManifestation, destination));
            
                ModelTree owner = null;
            
                if (destination instanceof ModelTree) {
                    owner = ((ModelTree) destination).getOwner();
                }
            
                appendFrom(symbol, theManifestation, owner, styler);
            } else {
                symbol.append("<No destination>", styler);
            }
            return symbol;
        }

        @objid ("71213579-d6fd-4158-be43-6b257864b9ee")
        @Override
        public Object visitNameSpace(NameSpace theNameSpace) {
            final List<TemplateBinding> templateInstanciations = theNameSpace.getTemplateInstanciation();
            final Styler styler = ElementStyler.getStyler(theNameSpace);
            
            final StyledString symbol = new StyledString();
            if (this.showNamespaceVisibility) {
                symbol.append(getVisibilitySymbol(theNameSpace.getVisibility()), styler);
            }
            // append name
            final String name = theNameSpace.getName();
            symbol.append(name, styler);
            
            if (templateInstanciations.isEmpty()) {
                // The name space does not instantiate templates
                return symbol;
            } else {
                // The name space instantiates templates
            
                // append template instantiations
            
                if (!name.isEmpty()) {
                    symbol.append(": ", styler);
                }
            
                boolean first = true;
                for (final TemplateBinding b : templateInstanciations) {
                    final Styler stylerTB = ElementStyler.getStyler(theNameSpace, b);
                    if (first) {
                        first = false;
                    } else {
                        symbol.append(", ", stylerTB);
                    }
                    StyledString label = getLabel(b, false, false);
                    symbol.append(label != null ? label.toString() : "", stylerTB);
                }
            
                return symbol;
            }
        }

        @objid ("75dd74aa-4a68-4548-bd5b-cea434facffa")
        @Override
        public Object visitNaryAssociationEnd(NaryAssociationEnd theAssociationEnd) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theAssociationEnd);
            
            if (this.showFeaturesVisibility) {
                final VisibilityMode visibility = theAssociationEnd.getVisibility();
                symbol.append(getVisibilitySymbol(visibility), styler);
            }
            
            if (theAssociationEnd.isIsDerived()) {
                symbol.append("/", styler);
            }
            
            final String associationEndName = theAssociationEnd.getName();
            if (associationEndName.isEmpty()) {
                symbol.append(UmlUi.I18N.getString("NoName"), styler);
            } else {
                symbol.append(theAssociationEnd.getName(), styler);
            }
            
            symbol.append(": ", styler);
            
            // The type
            final NaryAssociation type = theAssociationEnd.getNaryAssociation();
            symbol.append(type.getName(), ElementStyler.getStyler(theAssociationEnd, type));
            
            // The cardinality
            symbol.append(BrowserLabelService.getNaryAssociationEndMultiplicity(theAssociationEnd).toString(), styler);
            return symbol;
        }

        @objid ("404ba985-f89f-4022-8b4e-3daff70d1864")
        @Override
        public Object visitNaryLinkEnd(NaryLinkEnd theLinkEnd) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theLinkEnd);
            
            final String linkEndName = theLinkEnd.getName();
            
            if (linkEndName.equals("")) {
                symbol.append(UmlUi.I18N.getString("NoName"), styler);
            } else {
                symbol.append(linkEndName, styler);
            }
            final NaryLink linked = theLinkEnd.getNaryLink();
            symbol.append("::", styler);
            symbol.append(linked.getName(), ElementStyler.getStyler(theLinkEnd, linked));
            return symbol;
        }

        @objid ("8b7b5f69-d1aa-4799-a70a-06e165d02413")
        @Override
        public Object visitObjectNode(ObjectNode theObjectNode) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theObjectNode);
            final BehaviorParameter parameter = theObjectNode.getRepresentedRealParameter();
            final Instance instance = theObjectNode.getRepresented();
            final Attribute attribute = theObjectNode.getRepresentedAttribute();
            final AssociationEnd role = theObjectNode.getRepresentedRole();
            
            if (parameter != null) {
                StyledString label = getLabel(parameter, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theObjectNode, parameter));
            } else if (instance != null) {
                StyledString label = getLabel(instance, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theObjectNode, instance));
            } else if (attribute != null) {
                StyledString label = getLabel(attribute, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theObjectNode, attribute));
            } else if (role != null) {
                StyledString label = getLabel(role, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theObjectNode, role));
            } else {
                final GeneralClass type = theObjectNode.getType();
            
                symbol.append(theObjectNode.getName(), styler);
            
                final String upperbound = theObjectNode.getUpperBound();
                if ((upperbound.length() > 0) && !upperbound.equals("1")) {
                    symbol.append("[", styler);
                    symbol.append(upperbound, styler);
                    symbol.append("]", styler);
                }
            
                if (type != null) {
                    symbol.append(" : ", styler);
                    symbol.append(type.getName(), ElementStyler.getStyler(theObjectNode, type));
                }
            }
            return symbol;
        }

        @objid ("aa4a722a-811f-4bfd-9b8a-a1188ebea47c")
        @Override
        public Object visitOperation(Operation theOperation) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theOperation);
            
            if (this.showFeaturesVisibility) {
                final VisibilityMode visibility = theOperation.getVisibility();
                symbol.append(getVisibilitySymbol(visibility), styler);
            }
            symbol.append(theOperation.getName(), styler);
            
            symbol.append("(", styler);
            final List<Parameter> parameters = theOperation.getIO();
            final int parameterNumber = parameters.size();
            
            for (int i = 0; i < parameterNumber; i++) {
                final Parameter para = parameters.get(i);
                symbol.append(BrowserLabelService.getParameterSymbol(para, styler, theOperation));
                if (i < (parameterNumber - 1)) {
                    symbol.append(", ", styler);
                }
            }
            
            symbol.append(")", styler);
            
            final Parameter returnParameter = theOperation.getReturn();
            if (returnParameter != null) {
                final GeneralClass type = returnParameter.getType();
                symbol.append(":", styler);
                symbol.append(" ", styler);
                if (type != null) {
                    symbol.append(type.getName(), ElementStyler.getStyler(theOperation, type));
                } else {
                    symbol.append(UmlUi.I18N.getString("NoType"), styler);
                }
                symbol.append(BrowserLabelService.getParameterMultiplicity(returnParameter).toString(),
                        ElementStyler.getStyler(theOperation, returnParameter));
            }
            return symbol;
        }

        @objid ("d5608e91-d13f-49a9-8d4b-69988cba5c74")
        @Override
        public Object visitPackageImport(PackageImport thePackageImport) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(thePackageImport);
            
            final NameSpace importedNamespace = thePackageImport.getImportedPackage();
            
            if (importedNamespace != null) {
                if (thePackageImport.getVisibility() == VisibilityMode.PUBLIC) {
                    symbol.append("import all ", styler);
                } else {
                    symbol.append("access all ", styler);
                }
                symbol.append(importedNamespace.getName(),
                        ElementStyler.getStyler(thePackageImport, importedNamespace));
            
                final ModelTree owner = importedNamespace.getOwner();
                appendFrom(symbol, thePackageImport, owner, styler);
            } else {
                symbol.append("<No destination>", styler);
            }
            return symbol;
        }

        @objid ("eab0c419-343d-462b-8e5e-77a9b06f0c16")
        @Override
        public Object visitPackageMerge(PackageMerge thePackageMerge) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(thePackageMerge);
            
            final Package mergedPackage = thePackageMerge.getMergedPackage();
            
            if (mergedPackage != null) {
                symbol.append("merge ", styler);
                symbol.append(mergedPackage.getName(), ElementStyler.getStyler(thePackageMerge, mergedPackage));
            
                final ModelTree owner = mergedPackage.getOwner();
                appendFrom(symbol, thePackageMerge, owner, styler);
            } else {
                symbol.append("<No destination>", styler);
            }
            return symbol;
        }

        @objid ("9ffde096-a661-4087-9828-4e0662269e15")
        @Override
        public Object visitParameter(Parameter theParameter) {
            return BrowserLabelService.getParameterSymbol(theParameter, ElementStyler.getStyler(theParameter), null);
        }

        @objid ("a0c14e38-f7b9-4248-97ee-db2722571d74")
        @Override
        public Object visitPort(Port thePort) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(thePort);
            symbol.append(thePort.getName(), styler);
            
            final NameSpace type = BrowserLabelService.getType(thePort);
            if (type != null) {
                symbol.append(" : ", styler);
                symbol.append(type.getName(), ElementStyler.getStyler(thePort, type));
            }
            
            // If the name is empty, build a list of provided and required
            // interfaces as a workaround
            if (symbol.length() == 0) {
                final List<RequiredInterface> requiredInterfaces = thePort.getRequired();
                if (requiredInterfaces.size() > 0) {
                    symbol.append("R = ", styler);
                    for (int i = 0; i < requiredInterfaces.size(); i++) {
                        if (i > 0) {
                            symbol.append(", ", styler);
                        }
                        final RequiredInterface ri = requiredInterfaces.get(i);
            
                        StyledString label = getLabel(ri, false, false);
                        symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(thePort, ri));
                    }
                }
            
                final List<ProvidedInterface> providedInterfaces = thePort.getProvided();
                if (providedInterfaces.size() > 0) {
                    if (requiredInterfaces.size() > 0) {
                        symbol.append(", ", styler);
                    }
                    symbol.append("P = ");
                    for (int i = 0; i < providedInterfaces.size(); i++) {
                        if (i > 0) {
                            symbol.append(", ", styler);
                        }
                        final ProvidedInterface pi = providedInterfaces.get(i);
            
                        StyledString label = getLabel(pi, false, false);
                        symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(thePort, pi));
                    }
                }
            }
            return symbol;
        }

        @objid ("8576cc9f-7fea-4b83-9118-bcf00e8f2573")
        @Override
        public Object visitProvidedInterface(ProvidedInterface theProvidedInterface) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theProvidedInterface);
            final List<Interface> providedElements = theProvidedInterface.getProvidedElement();
            
            if (providedElements.size() > 0) {
                for (int i = 0; i < providedElements.size(); i++) {
                    if (i > 0) {
                        symbol.append(", ", styler);
                    }
                    final Interface pe = providedElements.get(i);
            
                    StyledString label = getLabel(pe, false, false);
                    symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theProvidedInterface, pe));
                }
            } else {
                symbol.append("none", styler);
            }
            return symbol;
        }

        @objid ("d96c15a2-5d45-4c74-86ff-c092fee26def")
        @Override
        public Object visitRaisedException(RaisedException theRaisedException) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theRaisedException);
            symbol.append("throws ", styler);
            
            final Classifier thrownType = theRaisedException.getThrownType();
            
            if (thrownType != null) {
                StyledString label = getLabel(thrownType, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theRaisedException, thrownType));
            
                final ModelTree owner = thrownType.getOwner();
                appendFrom(symbol, theRaisedException, owner, styler);
            }
            return symbol;
        }

        @objid ("e6cd9cd6-4412-4172-beff-779a5076f17a")
        @Override
        public Object visitRequiredInterface(RequiredInterface theRequiredInterface) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theRequiredInterface);
            final List<Interface> requiredElements = theRequiredInterface.getRequiredElement();
            
            if (requiredElements.size() > 0) {
                for (int i = 0; i < requiredElements.size(); i++) {
                    if (i > 0) {
                        symbol.append(", ", styler);
                    }
                    final Interface re = requiredElements.get(i);
            
                    StyledString label = getLabel(re, false, false);
                    symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theRequiredInterface, re));
                }
            } else {
                symbol.append("none", styler);
            }
            return symbol;
        }

        @objid ("a8197f7c-d3a2-46d5-8f01-2422dfba8c51")
        @Override
        public Object visitSendSignalAction(SendSignalAction theSendSignalAction) {
            final StyledString symbol = new StyledString();
            
            final String sendSignalActionName = theSendSignalAction.getName();
            final Signal signal = theSendSignalAction.getSent();
            
            if ((signal != null) && (sendSignalActionName.equals("Unnamed") || sendSignalActionName.equals(""))) {
                symbol.append(signal.getName(), ElementStyler.getStyler(theSendSignalAction, signal));
            } else {
                symbol.append(sendSignalActionName, ElementStyler.getStyler(theSendSignalAction));
            }
            return symbol;
        }

        @objid ("c9bd5b27-5fef-4acc-bfce-497ee8c4ee89")
        @Override
        public Object visitSubstitution(Substitution obj) {
            final ModelElement destination = obj.getContract();
            return visitDependencyLikeObject(obj, "substitute", destination);
        }

        @objid ("f494a24c-7564-49e7-a131-e785eba68792")
        @Override
        public Object visitTemplateBinding(TemplateBinding theTemplateBinding) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theTemplateBinding);
            final NameSpace namespace = theTemplateBinding.getInstanciatedTemplate();
            if (namespace != null) {
                symbol.append(namespace.getName(), ElementStyler.getStyler(theTemplateBinding, namespace));
            } else {
                final Operation operation = theTemplateBinding.getInstanciatedTemplateOperation();
                if (operation != null) {
                    symbol.append(operation.getName(), ElementStyler.getStyler(theTemplateBinding, operation));
                }
            }
            
            symbol.append(" <", styler);
            
            final List<TemplateParameterSubstitution> substitutions = theTemplateBinding.getParameterSubstitution();
            for (int i = 0; i < substitutions.size(); i++) {
                if (i != 0) {
                    symbol.append(", ", styler);
                }
                final TemplateParameterSubstitution ts = substitutions.get(i);
            
                StyledString label = getLabel(ts, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theTemplateBinding, ts));
            }
            
            symbol.append(" >", styler);
            return symbol;
        }

        @objid ("f5a19b75-58fb-4e76-8488-44a27ad8ab25")
        @Override
        public Object visitTemplateParameter(TemplateParameter theTemplateParameter) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theTemplateParameter);
            symbol.append(theTemplateParameter.getName(), styler);
            
            final ModelElement type = theTemplateParameter.getType();
            
            if (type != null) {
                final Styler typeStyler = ElementStyler.getStyler(theTemplateParameter, type);
                StyledString label = getLabel(type, false, false);
                if (theTemplateParameter.isIsValueParameter()) {
                    symbol.append(":", styler);
            
                    symbol.append(label != null ? label.toString() : "", typeStyler);
            
                    symbol.append(" expression", styler);
                } else {
                    boolean isConstrained = false;
            
                    if (type instanceof NameSpace) {
                        final NameSpace nsType = (NameSpace) type;
                        isConstrained = nsType.getSpecialization().size() > 0;
                    }
            
                    if (isConstrained) {
                        symbol.append(" > ", styler);
            
                        symbol.append(label != null ? label.toString() : "", typeStyler);
                    } else if ((type.getClass() != Class.class) || (type.getExtension().size() != 0)) {
                        symbol.append(" : ", styler);
                        symbol.append(type.getMClass().getName(), styler);
                    }
                }
            }
            return symbol;
        }

        @objid ("05e0e6f2-4ee7-4e1d-8475-24f4c14470ea")
        @Override
        public Object visitTemplateParameterSubstitution(TemplateParameterSubstitution theTemplateParameterSubstitution) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theTemplateParameterSubstitution);
            
            final TemplateParameter templateParameter = theTemplateParameterSubstitution.getFormalParameter();
            if (templateParameter != null) {
                StyledString label = getLabel(templateParameter, false, false);
                symbol.append(label != null ? label.toString() : "", ElementStyler.getStyler(theTemplateParameterSubstitution, templateParameter));
            }
            
            final String value = theTemplateParameterSubstitution.getValue();
            if (!value.isEmpty()) {
                symbol.append("=", styler);
                symbol.append(value, styler);
            } else {
                symbol.append("=", styler);
                final ModelElement actual = theTemplateParameterSubstitution.getActual();
                // Be careful if actual is equal to Owner TemplateBinding
                // because it will loop
                if (actual != null) {
                    final Styler actualStyler = ElementStyler.getStyler(theTemplateParameterSubstitution, actual);
                    if (actual.equals(theTemplateParameterSubstitution.getOwner())) {
                        symbol.append(actual.getName(), actualStyler);
                        // Prevent infinite loop
                    } else {
                        StyledString label = getLabel(actual, false, false);
                        symbol.append(label != null ? label.toString() : "", actualStyler);
                    }
                }
            }
            return symbol;
        }

        @objid ("c708083c-f179-40a3-8933-e30cecb8dcf7")
        @Override
        public Object visitTransition(Transition theTransition) {
            final StyledString symbol = new StyledString();
            
            // IStateVertex sourceVertex = theTransition.getSource();
            final StateVertex targetVertex = theTransition.getTarget();
            final boolean withEvent = true;
            
            final Styler styler = ElementStyler.getStyler(theTransition);
            
            // Trigger
            final Event trigger = theTransition.getTrigger();
            if (trigger != null) {
                symbol.append(trigger.getName(), styler);
            } else {
                symbol.append(theTransition.getReceivedEvents(), styler);
            }
            
            // Guard condition
            final String condition = theTransition.getGuard();
            if ((condition != null) && !condition.equals("")) {
                symbol.append("[", styler);
                symbol.append(condition, styler);
                symbol.append("]", styler);
            }
            
            // Action
            final Operation operation = theTransition.getProcessed();
            if (operation != null) {
                symbol.append("/", styler);
                symbol.append(operation.getName(), styler);
                symbol.append("()", styler);
            } else {
                final String effect = theTransition.getEffect();
                if (effect.length() > 0) {
                    symbol.append("/", styler);
                    symbol.append(effect, styler);
                }
            }
            
            // SentEvent
            final Signal effects = theTransition.getEffects();
            if ((effects != null) && withEvent) {
                symbol.append("^", styler);
                symbol.append(effects.getName(), styler);
                symbol.append("()", styler);
            } else {
                final String sentEvents = theTransition.getSentEvents();
                if (sentEvents.length() > 0) {
                    symbol.append("^", styler);
                    symbol.append(sentEvents, styler);
                }
            }
            
            // postGard
            final String postCondition = theTransition.getPostCondition();
            if ((postCondition != null) && !postCondition.equals("")) {
                symbol.append("{", styler);
                symbol.append(postCondition, styler);
                symbol.append("}", styler);
            }
            
            if (symbol.length() == 0) {
                symbol.append(theTransition.getName());
                if (targetVertex != null) {
                    symbol.append("::", styler);
                    symbol.append(targetVertex.getName(), styler);
                }
            }
            return symbol;
        }

        @objid ("30a311b1-6815-4d8e-981e-02d01796ec59")
        @Override
        public Object visitUsage(Usage theUsage) {
            final ModelElement destination = theUsage.getDependsOn();
            return visitDependencyLikeObject(theUsage, "uses", destination);
        }

        @objid ("33600393-ad93-421b-bfba-21ec352a62b2")
        @Override
        public Object visitUseCaseDependency(UseCaseDependency theUseCaseDependency) {
            final StyledString symbol = new StyledString();
            final List<Stereotype> stereotypes = theUseCaseDependency.getExtension();
            
            if (stereotypes.size() > 0) {
                final Styler stylerStereotypes = ElementStyler.getStyler(theUseCaseDependency, stereotypes.get(0));
                symbol.append("<<", stylerStereotypes);
                symbol.append(stereotypes.get(0).getName(), stylerStereotypes);
                symbol.append(">>", stylerStereotypes);
            }
            
            final UseCase target = theUseCaseDependency.getTarget();
            
            if (target != null) {
                symbol.append(target.getName(), ElementStyler.getStyler(theUseCaseDependency, target));
            }
            return symbol;
        }

        /**
         * Initialize the label service.
         * @param elementStack a stack to use for recursive calls.
         */
        @objid ("d6e66939-b8e4-466b-9fa0-7d433f498493")
        BrowserLabelService(Stack<MObject> elementStack) {
            super();
            this.elementStack = elementStack;
        }

        /**
         * Append <code>"(from xxxx)"</code> to the symbol
         * @param symbol the symbol to modify
         * @param srcObj the source object, used to compute the style of <code>'xxxx'</code>
         * @param owner the object to display in <code>'xxxx'</code>
         * @param styler the style of <code>"(from "</code>
         */
        @objid ("a0da4f73-c520-4117-9382-f8bb9181146e")
        private void appendFrom(final StyledString symbol, ModelElement srcObj, ModelTree owner, final Styler styler) {
            if (owner != null) {
                symbol.append(" (", styler);
                symbol.append(UmlUi.I18N.getString("From"), styler);
                symbol.append(" ", styler);
                symbol.append(owner.getName(), ElementStyler.getStyler(srcObj, owner));
                symbol.append(")", styler);
            }
        }

        @objid ("01d4174c-eaaf-424b-955c-32bc911e8f4e")
        private static StringBuilder getAssociationEndMultiplicity(AssociationEnd theAssociationEnd) {
            final StringBuilder multiplicity = new StringBuilder();
            
            final String multiplicityMinStr = theAssociationEnd.getMultiplicityMin();
            final String multiplicityMaxStr = theAssociationEnd.getMultiplicityMax();
            String separator = "";
            
            if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
                multiplicity.append(" [");
            
                if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                    multiplicity.append(multiplicityMinStr);
                } else if (multiplicityMinStr.equals("0") && multiplicityMaxStr.equals("*")) {
                    multiplicity.append("*");
                } else {
                    if (!multiplicityMinStr.equals("") && !multiplicityMaxStr.equals("")) {
                        separator = "..";
                    }
            
                    multiplicity.append(multiplicityMinStr);
                    multiplicity.append(separator);
                    multiplicity.append(multiplicityMaxStr);
                }
                multiplicity.append("]");
            }
            return multiplicity;
        }

        @objid ("96c70b2f-51ad-4dad-acec-ae21f0961851")
        private static StringBuilder getAttributeMultiplicity(Attribute theAttribute) {
            final StringBuilder multiplicity = new StringBuilder();
            
            final String multiplicityMinStr = theAttribute.getMultiplicityMin();
            final String multiplicityMaxStr = theAttribute.getMultiplicityMax();
            String separator = "";
            
            if (multiplicityMinStr.equals("1") && multiplicityMaxStr.equals("1")) {
                return multiplicity;
            }
            
            if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
                multiplicity.append(" [");
            
                if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                    multiplicity.append(multiplicityMinStr);
                } else if (multiplicityMinStr.equals("0") && multiplicityMaxStr.equals("*")) {
                    multiplicity.append("*");
                } else {
                    if (!multiplicityMinStr.equals("") && !multiplicityMaxStr.equals("")) {
                        separator = "..";
                    }
            
                    multiplicity.append(multiplicityMinStr);
                    multiplicity.append(separator);
                    multiplicity.append(multiplicityMaxStr);
                }
                multiplicity.append("]");
            }
            return multiplicity;
        }

        @objid ("5cf5a913-6e5d-4159-bbd5-815c657eab7b")
        private static StringBuilder getDependencyVerb(ModelElement dep, String defaultVerb) {
            final StringBuilder stringBuilder = new StringBuilder();
            if (!dep.getExtension().isEmpty()) {
                for (final Stereotype v : dep.getExtension()) {
                    stringBuilder.append(ModuleI18NService.getLabel(v));
            
                    stringBuilder.append(", ");
                }
                // remove last ", "
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            } else {
                stringBuilder.append(defaultVerb);
                /*
                 * if (dep instanceof Usage) stringBuilder.append("uses"); else if (dep instanceof ElementRealization) stringBuilder.append("realizes"); else if (dep instanceof Abstraction) stringBuilder.append("abstracts"); else
                 * stringBuilder.append("depends on");
                 */
            }
            return stringBuilder;
        }

        @objid ("8b0fc233-64f6-47a2-852a-e6a1f8b3c69e")
        private static StringBuilder getNaryAssociationEndMultiplicity(NaryAssociationEnd theAssociationEnd) {
            final StringBuilder multiplicity = new StringBuilder();
            
            final String multiplicityMinStr = theAssociationEnd.getMultiplicityMin();
            final String multiplicityMaxStr = theAssociationEnd.getMultiplicityMax();
            String separator = "";
            
            if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
                multiplicity.append(" [");
            
                if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                    multiplicity.append(multiplicityMinStr);
                } else if (multiplicityMinStr.equals("0") && multiplicityMaxStr.equals("*")) {
                    multiplicity.append("*");
                } else {
                    if (!multiplicityMinStr.equals("") && !multiplicityMaxStr.equals("")) {
                        separator = "..";
                    }
            
                    multiplicity.append(multiplicityMinStr);
                    multiplicity.append(separator);
                    multiplicity.append(multiplicityMaxStr);
                }
                multiplicity.append("]");
            }
            return multiplicity;
        }

        @objid ("59375a58-1c78-48be-b9d2-6fde7970667f")
        private static StringBuilder getParameterMultiplicity(Parameter theParameter) {
            final StringBuilder multiplicity = new StringBuilder();
            
            final String multiplicityMinStr = theParameter.getMultiplicityMin();
            final String multiplicityMaxStr = theParameter.getMultiplicityMax();
            String separator = "";
            
            if (multiplicityMinStr.equals("1") && multiplicityMaxStr.equals("1")) {
                return multiplicity;
            }
            
            if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
                multiplicity.append(" [");
                if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                    multiplicity.append(multiplicityMinStr);
                } else if (multiplicityMinStr.equals("0") && multiplicityMaxStr.equals("*")) {
                    multiplicity.append("*");
                } else {
                    if (!multiplicityMinStr.equals("") && !multiplicityMaxStr.equals("")) {
                        separator = "..";
                    }
            
                    multiplicity.append(multiplicityMinStr);
                    multiplicity.append(separator);
                    multiplicity.append(multiplicityMaxStr);
                }
                multiplicity.append("]");
            }
            return multiplicity;
        }

        @objid ("32c71241-598a-403c-afcf-666964a7c50b")
        private static StyledString getParameterSymbol(Parameter theParameter, Styler styler, Operation fromOperation) {
            final StyledString symbol = new StyledString();
            
            final PassingMode passingMode = theParameter.getParameterPassing();
            
            final GeneralClass type = theParameter.getType();
            
            if (theParameter.getReturned() != null) {
                symbol.append("out", styler);
            
                symbol.append(" : ", styler);
                if (type != null) {
                    symbol.append(type.getName(), ElementStyler.getStyler(theParameter, type));
                } else {
                    symbol.append(UmlUi.I18N.getString("NoType"), styler);
                }
            
                symbol.append(BrowserLabelService.getParameterMultiplicity(theParameter).toString(), styler);
            } else if (theParameter.getComposed() != null) {
                symbol.append(theParameter.getName(), styler);
                symbol.append(" ", styler);
            
                if (passingMode == PassingMode.IN) {
                    symbol.append("in", styler);
                }
                if (passingMode == PassingMode.OUT) {
                    symbol.append("out", styler);
                }
                if (passingMode == PassingMode.INOUT) {
                    symbol.append("inout", styler);
                }
            
                symbol.append(" : ", styler);
                if (type != null) {
                    symbol.append(type.getName(),
                            ElementStyler.getStyler(fromOperation == null ? theParameter : fromOperation, type));
                } else {
                    symbol.append(UmlUi.I18N.getString("NoType"), styler);
                }
            
                symbol.append(BrowserLabelService.getParameterMultiplicity(theParameter).toString(), styler);
            }
            return symbol;
        }

        @objid ("02509111-4566-44d7-8e3d-236e0ba37ce0")
        private static NameSpace getType(Port thePort) {
            final ModelElement represented = thePort.getRepresentedFeature();
            
            if (represented == null) {
                return thePort.getBase();
            } else if (BrowserLabelService.hasTypeCycles(thePort)) {
                return null;
            } else if (represented instanceof Attribute) {
                return ((Attribute) represented).getType();
            } else if (represented instanceof AssociationEnd) {
                return ((AssociationEnd) represented).getTarget();
            } else if (represented instanceof Instance) {
                return ((Instance) represented).getBase();
            } else if (represented instanceof Parameter) {
                return ((Parameter) represented).getType();
            }
            return null;
        }

        @objid ("f8c76760-1670-4cba-a21a-54704f390b75")
        private String getVisibilitySymbol(VisibilityMode v) {
            switch (v) {
            case PUBLIC:
                return "+";
            case PROTECTED:
                return "#";
            case PRIVATE:
                return "-";
            case PACKAGEVISIBILITY:
                return "\u2248";
            case VISIBILITYUNDEFINED:
                return "";
            default:
                return "";
            }
        }

        @objid ("0466abdb-d63c-4842-a811-54d0a160398a")
        private static boolean hasTypeCycles(Port thePort) {
            BindableInstance currentInstance = thePort;
            boolean hasCycle = false;
            
            while ((currentInstance != null) && !hasCycle) {
                final ModelElement currentRepresented = currentInstance.getRepresentedFeature();
                if ((currentRepresented != null) && (currentRepresented instanceof BindableInstance)) {
                    currentInstance = (BindableInstance) currentRepresented;
                    if (thePort.equals(currentInstance)) {
                        hasCycle = true;
                    }
                } else {
                    currentInstance = null;
                }
            
            }
            return hasCycle;
        }

        @objid ("5096e830-dfe7-4273-ba98-3d7512e73e1f")
        private Object visitDependencyLikeObject(ModelElement theDependency, String mmverb, ModelElement destination) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theDependency);
            final StringBuilder verb = BrowserLabelService.getDependencyVerb(theDependency, mmverb).append(" ");
            
            if (destination != null) {
                symbol.append(verb.toString(), styler);
            
                final StyledString destLabel = new BrowserLabelService(this.elementStack).getLabel(destination, this.showFeaturesVisibility, this.showNamespaceVisibility);
                symbol.append(destLabel != null ? destLabel.getString() : "", ElementStyler.getStyler(theDependency, destination));
            
                ModelTree owner = null;
            
                if (destination instanceof ModelTree) {
                    owner = ((ModelTree) destination).getOwner();
                    appendFrom(symbol, theDependency, owner, styler);
                }
            
            } else {
                symbol.append(verb.toString(), styler);
                symbol.append("<No destination>", styler);
            }
            return symbol;
        }

    }

}
