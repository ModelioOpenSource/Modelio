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

package org.modelio.xmi.generation;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.TransitionKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impact.ImpactDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.FinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.FlowFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.DurationConstraint;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ChoicePseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.FinalState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ForkPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JoinPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JunctionPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.TerminatePseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
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
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.metamodel.visitors.IDefaultInfrastructureVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.ModelioPrimitiveTypeMapper;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.XMIProperties;

/**
 * This class creates an Ecore element corresponding to given Modelio element.
 * It is used when a Modelio element has a reference to another element not already exported.
 * 
 * @author ebrosse
 */
@objid ("2090910d-9b0b-449a-974e-a1318a5eb5bf")
public class PartialCreationExportVisitor {
    @objid ("e7cc4cdb-ffd3-46fd-8259-d3617d555dee")
     org.eclipse.uml2.uml.Element ecoreElt = null;

    @objid ("bfd45eda-8885-43b3-9b91-7e638b917bc1")
     CreationExportMapper mapper = null;

    @objid ("3f126aa1-fa77-4123-b4cd-64d6b1674fdd")
     PartialExportMap partialMap = null;

    /**
     * The default constructor
     */
    @objid ("c09422bd-33c3-428e-8177-5da5e19fae8f")
    public PartialCreationExportVisitor() {
        this.ecoreElt = null;
        this.mapper = new CreationExportMapper();
        this.partialMap = PartialExportMap.getInstance();
    }

    /**
     * This is method supervise the Ecore element creation
     * @param objingElt : the referenced Modelio element
     * @return the corresponding Ecore element
     */
    @objid ("4cba6df5-fd6a-410c-9650-a97131c8e25e")
    public org.eclipse.uml2.uml.Element createPartialEcoreElt(final MObject objingElt) {
        if (objingElt != null) {
            this.ecoreElt = null;
            org.eclipse.uml2.uml.Element ecoreEltFromMap = this.partialMap.get(objingElt.getUuid().toString());
        
            if (ecoreEltFromMap == null) {
                this.mapper.accept(objingElt);
                this.partialMap.put(objingElt.getUuid().toString(), this.ecoreElt);
                ecoreEltFromMap = this.ecoreElt;
            }else {
                this.partialMap.put(objingElt.getUuid().toString(), ecoreEltFromMap);
            }
            return ecoreEltFromMap;
        } else{
            Xmi.LOG.warning(Xmi.PLUGIN_ID, "Modelio element is null.");
            throw new RuntimeException("Modelio element is null.");
        }
    }

    /**
     * This method returns the CreationExportMapper
     * @return the CreationExportMapper
     */
    @objid ("231e1542-ccb8-4a3b-a8b1-951a1111e334")
    public CreationExportMapper getMapper() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.mapper;
    }

    /**
     * This methods returns the map containing all "partial" exports
     * @return the PartilaExportMap
     */
    @objid ("f994e0a9-92af-4cb1-9f34-3898850646af")
    public PartialExportMap getPartialMap() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.partialMap;
    }

    @objid ("094cc757-5d74-41bc-b5d1-c2c82590937c")
    private class CreationExportMapper extends DefaultModelVisitor implements IDefaultInfrastructureVisitor {
        @objid ("681ea022-8507-47c9-ae77-7c106f138423")
        public CreationExportMapper() {
            super();
            this.infrastructureVisitor = this;
        }

        @objid ("61333097-5dc4-4595-bfc1-132dc317b3f7")
        public void accept(MObject objingElt) {
            objingElt.accept(this);
        }

        @objid ("6788f5de-5206-4af0-8d05-ab25d809eab3")
        @Override
        public Object visitAbstractDiagram(AbstractDiagram obj) {
            return null;
        }

        @objid ("9fa40744-adb9-4d4b-b65f-a86734164d16")
        @Override
        public Object visitAbstractProject(AbstractProject obj) {
            return null;
        }

        @objid ("05a68183-80a2-45fd-9e68-ddb1da2a3959")
        @Override
        public Object visitAbstractPseudoState(AbstractPseudoState objingElt) {
            return null;
        }

        @objid ("2b7b4f75-9816-42b3-ad08-0d6ae6d14cfa")
        @Override
        public Object visitAbstraction(Abstraction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAbstraction();
            return null;
        }

        @objid ("5425e02f-02e6-4599-914a-6257d13d3821")
        @Override
        public Object visitAcceptCallEventAction(AcceptCallEventAction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAcceptEventAction();
            return null;
        }

        @objid ("9aa4d219-541e-4d5b-b512-7b22e00dc9f4")
        @Override
        public Object visitAcceptChangeEventAction(AcceptChangeEventAction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAcceptEventAction();
            return null;
        }

        @objid ("92d55d68-b61b-4175-8b21-490a2ccefd1f")
        @Override
        public Object visitAcceptSignalAction(AcceptSignalAction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAcceptEventAction();
            if (GenerationProperties.getInstance().isRoundtripEnabled()) {
                ObjingEAnnotation.setSignal(PartialCreationExportVisitor.this.ecoreElt, "signal");
            }
            return null;
        }

        @objid ("4bab155b-a70d-4eec-a21a-14b71a85bf2b")
        @Override
        public Object visitAcceptTimeEventAction(AcceptTimeEventAction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAcceptEventAction();
            return null;
        }

        @objid ("7661e419-28d7-4138-bc1c-1daab0ae5e65")
        @Override
        public Object visitActivity(Activity objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createActivity();
            return null;
        }

        @objid ("bc7a6be0-7661-4c55-831d-353f418d1257")
        @Override
        public Object visitActivityFinalNode(ActivityFinalNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createActivityFinalNode();
            return null;
        }

        @objid ("f33b98b3-5394-4128-b037-94a192c9af83")
        @Override
        public Object visitActivityParameterNode(ActivityParameterNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createActivityParameterNode();
            return null;
        }

        @objid ("11b6d010-c5c1-48e7-b1bd-7ae346c38674")
        @Override
        public Object visitActivityPartition(ActivityPartition objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createActivityPartition();
            return null;
        }

        @objid ("59bf1b0f-8eeb-4398-9d40-60d7b941d7b6")
        @Override
        public Object visitActor(Actor objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createActor();
            return null;
        }

        @objid ("2cfd288b-38d4-4557-96ef-500643620247")
        @Override
        public Object visitArtifact(Artifact objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DEPLOYMENTSPECIFICATION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDeploymentSpecification();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createArtifact();
            }
            return null;
        }

        @objid ("6e71c0ad-69f1-45aa-81d6-5d91bafee91b")
        @Override
        public Object visitAssociation(Association objingElt) {
            if (AbstractObjingModelNavigation.isIsClassAssociation(objingElt)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAssociationClass();
                // Case of an AssociationClass - the keys for an ecore
                // AssociationClass are:
                // - the ID of the objing Association
                // - the ID of the objing ClassAssociation
                // - the ID of the objing Class representing the
                // ClassAssociation
                ClassAssociation classAssoc = objingElt.getLinkToClass();
                PartialCreationExportVisitor.this.partialMap.put(classAssoc.getUuid().toString(), PartialCreationExportVisitor.this.ecoreElt);
                PartialCreationExportVisitor.this.partialMap.put(classAssoc.getClassPart().getUuid().toString(), PartialCreationExportVisitor.this.ecoreElt);
            } else {
                if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2COMMUNICATIONPATH)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCommunicationPath();
                } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXTENSION)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExtension();
                } else {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAssociation();
                }
            }
            return null;
        }

        @objid ("5ce9bab1-19b2-4e33-9137-aa006e5802a0")
        @Override
        public Object visitAssociationEnd(AssociationEnd objingElt) {
            Association assoc = objingElt.getAssociation();
            
            if (!AbstractObjingModelNavigation.isOwnedByActor(assoc)) {
                if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXTENSIONEND)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExtensionEnd();
                }
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createProperty();
            }
            return null;
        }

        @objid ("c8540eed-622a-4f31-8868-f61e9d2b3912")
        @Override
        public Object visitAttribute(Attribute objingElt) {
            MObject obOwner = objingElt.getCompositionOwner();
            
            if (obOwner instanceof Actor || obOwner instanceof UseCase) {
                PartialCreationExportVisitor.this.ecoreElt = null;
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createProperty();
            }
            return null;
        }

        @objid ("024f4254-f57f-4805-bbf7-33e7e29f00c3")
        @Override
        public Object visitAttributeLink(AttributeLink objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSlot();
            return null;
        }

        @objid ("d5782dde-5ed1-48e9-8be2-037b1f8137d5")
        @Override
        public Object visitBehaviorParameter(BehaviorParameter objingElt) {
            // BehaviorParameters are not exported (report to the
            // "setRepresentedRealParameter()" method of the
            // ActivityParameterNode
            // mapper for more details.
            return null;
        }

        @objid ("21cd415c-7e76-4f8b-a491-a28acfff7fa7")
        @Override
        public Object visitBindableInstance(BindableInstance objingElt) {
            MObject root = AbstractObjingModelNavigation.getBindableInstanceOwner(objingElt);
            
            if (root instanceof Instance) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSlot();
            
            } else {
                ModelElement representedFeature = objingElt.getRepresentedFeature();
                Property part = null;
                if ((representedFeature != null) && (representedFeature instanceof AssociationEnd)) {
                    part = (Property) GenerationProperties.getInstance().getMappedElement(representedFeature);
                } else {
                    part = UMLFactory.eINSTANCE.createProperty();
                }
            
                part.setIsComposite(true);
                PartialCreationExportVisitor.this.ecoreElt = part;
            }
            return null;
        }

        @objid ("9e58f07c-0232-443c-9c82-3f553059cca2")
        @Override
        public Object visitBinding(Binding objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDependency();
            return null;
        }

        @objid ("3ea61bb5-cbf8-4d62-9aca-a56c34ee0780")
        @Override
        public Object visitCallBehaviorAction(CallBehaviorAction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCallBehaviorAction();
            return null;
        }

        @objid ("6e7ce561-6c21-4506-b4bc-2b4383fc84af")
        @Override
        public Object visitCallOperationAction(CallOperationAction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCallOperationAction();
            return null;
        }

        @objid ("4079a116-abb4-4910-b648-2917f707bbfa")
        @Override
        public Object visitCentralBufferNode(CentralBufferNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCentralBufferNode();
            return null;
        }

        @objid ("0a4968e6-eca1-4c56-93bd-b208f14f086a")
        @Override
        public Object visitChoicePseudoState(ChoicePseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.CHOICE_LITERAL);
            return null;
        }

        @objid ("8654e0fc-39da-4cfd-8456-a73604955d80")
        @Override
        public Object visitClass(Class objingElt) {
            if (AbstractObjingModelNavigation.isIsClassAssociation(objingElt)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAssociationClass();
                // Case of an AssociationClass - the keys for an ecore
                // AssociationClass are:
                // - the ID of the objing Association
                // - the ID of the objing ClassAssociation
                // - the ID of the objing Class representing the
                // ClassAssociation
                ClassAssociation classAssoc = objingElt
                        .getLinkToAssociation();
                // A Class cannot represent more than one ClassAssociation
                // (audit rule 204)
                if (classAssoc != null) {
                    PartialCreationExportVisitor.this.partialMap.put(classAssoc.getUuid().toString(), PartialCreationExportVisitor.this.ecoreElt);
                    PartialCreationExportVisitor.this.partialMap.put(classAssoc.getAssociationPart().getUuid().toString(),
                            PartialCreationExportVisitor.this.ecoreElt);
                }
            
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createClass();
            }
            return null;
        }

        @objid ("135afc86-e5c6-4d0e-b2f3-3bc174c5ecb6")
        @Override
        public Object visitClassAssociation(ClassAssociation objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAssociationClass();
            // Case of an AssociationClass - the keys for an ecore
            // AssociationClass are:
            // - the ID of the objing Association
            // - the ID of the objing ClassAssociation
            // - the ID of the objing Class representing the ClassAssociation
            PartialCreationExportVisitor.this.partialMap.put(objingElt.getAssociationPart().getUuid().toString(), PartialCreationExportVisitor.this.ecoreElt);
            PartialCreationExportVisitor.this.partialMap.put(objingElt.getClassPart().getUuid().toString(), PartialCreationExportVisitor.this.ecoreElt);
            return null;
        }

        @objid ("9c31c2a9-8ae7-4687-baeb-fd2f6a64ded5")
        @Override
        public Object visitClause(Clause objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createClause();
            return null;
        }

        @objid ("76b936f7-d6ed-4978-9096-bac3c92d3ea0")
        @Override
        public Object visitCollaboration(Collaboration objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCollaboration();
            return null;
        }

        @objid ("dfced8bf-5a40-411b-a8b6-cb3c12b63b4f")
        @Override
        public Object visitCollaborationUse(CollaborationUse objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCollaborationUse();
            return null;
        }

        @objid ("9bfa2d71-36e7-411b-82ea-7f0ee7200004")
        @Override
        public Object visitCombinedFragment(CombinedFragment objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCombinedFragment();
            return null;
        }

        @objid ("8a40b922-b65d-40a0-85e4-ed6fd46cc840")
        @Override
        public Object visitCommunicationChannel(CommunicationChannel objingElt) {
            return null;
        }

        @objid ("17b94ec1-4503-4527-93e4-9dd7777efb9c")
        @Override
        public Object visitCommunicationInteraction(CommunicationInteraction objingElt) {
            return null;
        }

        @objid ("1a15f0ef-f8f9-48de-9c12-8815b6901298")
        @Override
        public Object visitCommunicationMessage(CommunicationMessage objingElt) {
            return null;
        }

        @objid ("8ce81b7b-1d7f-4615-ac3a-dec360f4dc0c")
        @Override
        public Object visitCommunicationNode(CommunicationNode objingElt) {
            return null;
        }

        @objid ("faf5e3bd-0613-403e-ae7d-4663bd5bbeda")
        @Override
        public Object visitComponent(Component objingElt) {
            if (AbstractObjingModelNavigation.isIsClassAssociation(objingElt)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAssociationClass();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createComponent();
            }
            return null;
        }

        @objid ("3997a6c0-76c2-4089-ad4b-87db5bbe6ef3")
        @Override
        public Object visitConditionalNode(ConditionalNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConditionalNode();
            return null;
        }

        @objid ("5b4bf0c9-583d-4d73-90b9-65afde262f44")
        @Override
        public Object visitConnectionPointReference(ConnectionPointReference objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConnectionPointReference();
            return null;
        }

        @objid ("d38927fa-0f4e-4f37-bae9-98a6a366e329")
        @Override
        public Object visitConnector(Connector objingElt) {
            MObject root = AbstractObjingModelNavigation.getConnectorOwner(objingElt);
            
            if (root != null) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConnector();
                return null;
            }
                     
            String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport",
                    objingElt.getName(),
                    objingElt.getClass().getSimpleName());
            
            
            GenerationProperties.getInstance().addWarning(message, objingElt);
            
            PartialCreationExportVisitor.this.ecoreElt = null;
            return null;
        }

        @objid ("33d24611-c493-49c0-b407-76b93b8d38ec")
        @Override
        public Object visitConnectorEnd(ConnectorEnd objingElt) {
            org.eclipse.uml2.uml.Element connector = null;
            
            if (objingElt.getLink() != null) {
                connector = GenerationProperties.getInstance().getMappedElement(objingElt.getLink());
            }
            
            if (connector != null) {
                if (connector instanceof org.eclipse.uml2.uml.Connector) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConnectorEnd();
                    return null;
                } else if (connector instanceof InstanceSpecification) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSlot();
                    return null;
                }
            }
                       
            String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport",
                    objingElt.getName(),
                    objingElt.getClass().getSimpleName());
            GenerationProperties.getInstance().addWarning(message, objingElt);
            PartialCreationExportVisitor.this.ecoreElt = null;
            return null;
        }

        @objid ("2ddfcba9-e369-4163-935b-7b5ff54ba01e")
        @Override
        public Object visitConstraint(Constraint objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConstraint();
            return null;
        }

        @objid ("cb9b1d86-c3c8-4cf0-95ca-ca5f0783ece9")
        @Override
        public Object visitControlFlow(ControlFlow objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createControlFlow();
            return null;
        }

        @objid ("0f106d15-8149-415d-a1ca-20a40f486248")
        @Override
        public Object visitDataFlow(DataFlow objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInformationFlow();
            return null;
        }

        @objid ("f0d46c6e-22aa-4bc3-b7ff-65f40513d554")
        @Override
        public Object visitDataStoreNode(DataStoreNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDataStoreNode();
            return null;
        }

        @objid ("71410b26-4f59-4929-ad8f-bbbe894819de")
        @Override
        public Object visitDataType(DataType objingElt) {
            MObject obOwner = objingElt.getCompositionOwner();
            
            if (obOwner instanceof Signal) {
                PartialCreationExportVisitor.this.ecoreElt = null;
            } else {
                if (ModelioPrimitiveTypeMapper.isPredefinedType(objingElt)) {
                    PartialCreationExportVisitor.this.ecoreElt = ModelioPrimitiveTypeMapper.getEcoreType(objingElt);
                } else if (objingElt.isIsElementary()) {
            
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPrimitiveType();
                } else {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDataType();
                }
            }
            return null;
        }

        @objid ("e2258946-64ae-4ffb-af35-7a5efafe92b1")
        @Override
        public Object visitDecisionMergeNode(DecisionMergeNode objingElt) {
            boolean isMergeNode = false;
            boolean isDecisionNode = false;
            if (AbstractObjingModelNavigation.isMergeNode(objingElt)) {
                isMergeNode = true;
            } else if (AbstractObjingModelNavigation.isDecisionNode(objingElt)) {
                isDecisionNode = true;
            }
            
            org.eclipse.uml2.uml.ControlNode ecoreNode = null;
            if (isDecisionNode) {
                ecoreNode = UMLFactory.eINSTANCE.createDecisionNode();
            } else {
                ecoreNode = UMLFactory.eINSTANCE.createMergeNode();
            }
            
            // If the DecisionMerge is not only Decision or Merge, we create a
            // special structure:
            // a Merge connected to a Decision by a flow.
            if (!isMergeNode && !isDecisionNode) {
                DecisionNode decisionNode = UMLFactory.eINSTANCE
                        .createDecisionNode();
            
                ActivityEdge typeOfEdge = null;
                List<ActivityEdge> objingInc = objingElt.getIncoming();
                List<ActivityEdge> objingOut = objingElt.getOutgoing();
            
                if (objingInc.size() > 0) {
                    typeOfEdge = objingInc.get(0);
                } else if (objingOut.size() > 0) {
                    typeOfEdge = objingOut.get(0);
                }
            
                org.eclipse.uml2.uml.ActivityEdge ecoreFlow = null;
                if (typeOfEdge instanceof ControlFlow) {
                    ecoreFlow = UMLFactory.eINSTANCE.createControlFlow();
                } else {
                    ecoreFlow = UMLFactory.eINSTANCE.createObjectFlow();
                }
            
                Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                        .getEnclosingElement(objingElt, objingElt.getMClass().getMetamodel().getMClass(Activity.class));
            
                if (enclosingActivity != null) {
                    org.eclipse.uml2.uml.Element ecoreActivity = GenerationProperties.getInstance()
                            .getMappedElement(enclosingActivity);
                    if (ecoreActivity instanceof org.eclipse.uml2.uml.Activity) {
                        org.eclipse.uml2.uml.Activity owner = (org.eclipse.uml2.uml.Activity) ecoreActivity;
                        owner.getEdges().add(ecoreFlow);
                    }
                }
            
                ecoreFlow.setSource(ecoreNode);
                ecoreFlow.setTarget(decisionNode);
            }
            
            PartialCreationExportVisitor.this.ecoreElt = ecoreNode;
            return null;
        }

        @objid ("e137bca3-c161-42db-a835-893b9c5b59cb")
        @Override
        public Object visitDeepHistoryPseudoState(DeepHistoryPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.DEEP_HISTORY_LITERAL);
            return null;
        }

        @objid ("af2dda02-3ccc-41d9-a15b-aa5b50472ab4")
        @Override
        public Object visitDependency(Dependency objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ASSOCIATIONREFERENCE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2METHODREFERENCE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2STRUCTURALFEATUREREFERENCE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLASSIFIERREFERENCE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ENDCREATIONDATAREFERENCE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ENDDESTRUCTIONDATAREFERENCE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EVENT)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2INSTANCEVALUE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.PART)) {
                PartialCreationExportVisitor.this.ecoreElt = null;
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2COMPONENTREALIZATION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createComponentRealization();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DEPLOYMENT)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDeployment();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2PROTOCOLCONFORMANCE)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createProtocolConformance();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.SATISFY)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.VERIFY)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.DERIVE)
                    || objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ABSTRACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAbstraction();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDependency();
            }
            return null;
        }

        @objid ("70217637-935c-44c3-ae49-7d942a562a95")
        @Override
        public Object visitDiagramSet(DiagramSet obj) {
            return null;
        }

        @objid ("f7c37c45-bb39-4878-970e-c4d601a70e49")
        @Override
        public Object visitDurationConstraint(DurationConstraint objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDurationConstraint();
            return null;
        }

        @objid ("2492dfed-3aad-421c-b309-68455841c645")
        @Override
        public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition obj) {
            return null;
        }

        @objid ("9a58aa37-8921-4518-bc41-153d0583662a")
        @Override
        public Object visitElement(Element obj) {
            return null;
        }

        @objid ("45513051-9109-423f-9afb-97d32f3a9bea")
        @Override
        public Object visitElementImport(ElementImport objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createElementImport();
            return null;
        }

        @objid ("e02b5eee-e20b-4c76-8f23-9e5e0b14206d")
        @Override
        public Object visitElementRealization(ElementRealization objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createRealization();
            return null;
        }

        @objid ("bd41a599-389c-407b-b90c-5fec80190ea9")
        @Override
        public Object visitEntryPointPseudoState(EntryPointPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.ENTRY_POINT_LITERAL);
            return null;
        }

        @objid ("b1e2c6f2-4edc-484f-b314-feb1ddebe820")
        @Override
        public Object visitEnumeratedPropertyType(EnumeratedPropertyType objingElt) {
            return null;
        }

        @objid ("4bf025ce-fb84-4c74-aba6-3b5b2706b011")
        @Override
        public Object visitEnumeration(Enumeration objingElt) {
            MObject obOwner = objingElt.getCompositionOwner();
            
            if (obOwner instanceof Signal) {
                PartialCreationExportVisitor.this.ecoreElt = null;
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createEnumeration();
            }
            return null;
        }

        @objid ("2bf19315-1eb5-427c-8e5f-4c0c19b88e75")
        @Override
        public Object visitEnumerationLiteral(EnumerationLiteral objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createEnumerationLiteral();
            return null;
        }

        @objid ("7e096b67-b5c5-4dd0-b159-2d214b6c9f94")
        @Override
        public Object visitEvent(Event objingElt) {
            EventType eventType = objingElt.getKind();
            if (eventType != null) {
                switch (eventType) {
                case CALLEVENT:
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCallEvent();
                    break;
                case SIGNALEVENT:
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSignalEvent();
                    break;
                case TIMEEVENT:
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createTimeEvent();
                    break;
                case CHANGEEVENT:
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createChangeEvent();
                    break;
                default:
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCallEvent();
                    break;
                }
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CREATIONEVENT)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCreationEvent();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DESTRUCTIONEVENT)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDestructionEvent();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXECUTIONEVENT)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExecutionEvent();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = null;
            }
            return null;
        }

        @objid ("ee6ad66b-606d-457b-a799-c5763d437785")
        @Override
        public Object visitExceptionHandler(ExceptionHandler arg0) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExceptionHandler();
            return null;
        }

        @objid ("378a24eb-9cc4-4f84-8112-daf8cf17171b")
        @Override
        public Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification objingElt) {
            if ((objingElt.getSentMessage() != null)
                    || (objingElt.getReceivedMessage() != null)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();
            }
            
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExecutionOccurrenceSpecification();
            return null;
        }

        @objid ("75358cbd-f246-4eca-9e09-4a64b337d4c6")
        @Override
        public Object visitExecutionSpecification(ExecutionSpecification objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createBehaviorExecutionSpecification();
            return null;
        }

        @objid ("1d0c45c4-ec06-49ba-8495-12ac61a9881f")
        @Override
        public Object visitExitPointPseudoState(ExitPointPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.EXIT_POINT_LITERAL);
            return null;
        }

        @objid ("15819d1c-a728-4572-8a32-4eb72453df16")
        @Override
        public Object visitExpansionNode(ExpansionNode arg0) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExpansionNode();
            return null;
        }

        @objid ("91ddd09d-795e-4e0d-a8a7-a9cbfdee1834")
        @Override
        public Object visitExpansionRegion(ExpansionRegion arg0) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExpansionRegion();
            return null;
        }

        @objid ("8a3c3092-8778-4a0f-9592-2762a2ab2e84")
        @Override
        public Object visitExtensionPoint(ExtensionPoint objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExtensionPoint();
            return null;
        }

        @objid ("c080f700-4e4c-4b1d-b386-f1712b1932c9")
        @Override
        public Object visitDocument(Document obj) {
            return null;
        }

        @objid ("eafd745a-17d0-4919-8a9c-22dc3d238d0c")
        @Override
        public Object visitResourceType(ResourceType obj) {
            return null;
        }

        @objid ("d35176b3-f176-47de-9cb6-5e2c05efdd35")
        @Override
        public Object visitExternProcessor(ExternProcessor obj) {
            return null;
        }

        @objid ("fe1af6de-0c25-4c00-b83b-707f1698066e")
        @Override
        public Object visitFinalNode(FinalNode arg0) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createActivityFinalNode();
            return super.visitFinalNode(arg0);
        }

        @objid ("ca21b39a-3c58-46f2-b331-89a2ef2c6977")
        @Override
        public Object visitFinalState(FinalState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createFinalState();
            return null;
        }

        @objid ("df209e81-39e1-42ae-80e9-f422ccd9174a")
        @Override
        public Object visitFlowFinalNode(FlowFinalNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createFlowFinalNode();
            return null;
        }

        @objid ("de223be1-067d-4e92-a092-bffa66dcc893")
        @Override
        public Object visitForkJoinNode(ForkJoinNode objingElt) {
            boolean isJoinNode = false;
            boolean isForkNode = false;
            if (AbstractObjingModelNavigation.isJoinNode(objingElt)) {
                isJoinNode = true;
            } else if (AbstractObjingModelNavigation.isForkNode(objingElt)) {
                isForkNode = true;
            }
            
            org.eclipse.uml2.uml.ControlNode ecoreNode = null;
            if (isForkNode) {
                ecoreNode = UMLFactory.eINSTANCE.createForkNode();
            } else {
                ecoreNode = UMLFactory.eINSTANCE.createJoinNode();
            }
            
            // If the ForkJoin is not only Fork or Join, we create a special
            // structure:
            // a Join connected to a Fork by a flow.
            if (!isJoinNode && !isForkNode) {
                ForkNode forkNode = UMLFactory.eINSTANCE.createForkNode();
            
                ActivityEdge typeOfEdge = null;
                List<ActivityEdge> objingInc = objingElt.getIncoming();
                List<ActivityEdge> objingOut = objingElt.getOutgoing();
            
                if (objingInc.size() > 0) {
                    typeOfEdge = objingInc.get(0);
                } else if (objingOut.size() > 0) {
                    typeOfEdge = objingOut.get(0);
                }
            
                org.eclipse.uml2.uml.ActivityEdge ecoreFlow = null;
                if (typeOfEdge instanceof ControlFlow) {
                    ecoreFlow = UMLFactory.eINSTANCE.createControlFlow();
                } else {
                    ecoreFlow = UMLFactory.eINSTANCE.createObjectFlow();
                }
            
                Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                        .getEnclosingElement(objingElt, objingElt.getMClass().getMetamodel().getMClass(Activity.class));
            
                if (enclosingActivity != null) {
                    org.eclipse.uml2.uml.Element ecoreActivity = GenerationProperties.getInstance()
                            .getMappedElement(enclosingActivity);
                    if (ecoreActivity instanceof org.eclipse.uml2.uml.Activity) {
                        org.eclipse.uml2.uml.Activity owner = (org.eclipse.uml2.uml.Activity) ecoreActivity;
                        owner.getEdges().add(ecoreFlow);
                    }
                }
            
                ecoreFlow.setSource(ecoreNode);
                ecoreFlow.setTarget(forkNode);
            }
            
            PartialCreationExportVisitor.this.ecoreElt = ecoreNode;
            return null;
        }

        @objid ("9c29eaf2-3070-438f-885a-f593d8d1fc92")
        @Override
        public Object visitForkPseudoState(ForkPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.FORK_LITERAL);
            return null;
        }

        @objid ("72ec1d29-b4f0-4512-9f24-c59d14eb9cd7")
        @Override
        public Object visitGate(Gate objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createGate();
            return null;
        }

        @objid ("44c6518c-0703-4bc0-b0f1-5d0e38b9e9b6")
        @Override
        public Object visitGeneralOrdering(GeneralOrdering objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createGeneralOrdering();
            return null;
        }

        @objid ("c7cf985a-3952-4838-a3b5-0254a16295d2")
        @Override
        public Object visitGeneralization(Generalization objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createGeneralization();
            return null;
        }

        @objid ("ebd68c98-d2a5-4253-95da-0beabc7a258d")
        @Override
        public Object visitInformationFlow(InformationFlow objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInformationFlow();
            return null;
        }

        @objid ("cf2c3bad-38a7-4bb8-8723-0953eaafa5db")
        @Override
        public Object visitInformationItem(InformationItem objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInformationItem();
            return null;
        }

        @objid ("2eac1867-b438-47a3-8ca5-03ca97cd5203")
        @Override
        public Object visitInitialNode(InitialNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInitialNode();
            return null;
        }

        @objid ("2e36a059-83bc-4d31-9b51-e7b53da5a6d3")
        @Override
        public Object visitInitialPseudoState(InitialPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            return null;
        }

        @objid ("92a2ee15-32e2-4d90-a58d-a90dfac6c847")
        @Override
        public Object visitInputPin(InputPin objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ACTIONINPUTPIN)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createActionInputPin();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInputPin();
            }
            return null;
        }

        @objid ("57d3da35-2c14-45f2-952a-f4f286af2bb9")
        @Override
        public Object visitInstance(Instance objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInstanceSpecification();
            return null;
        }

        @objid ("71c4c569-6724-4bcd-88f3-f8e824e0aef5")
        @Override
        public Object visitInstanceNode(InstanceNode objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2VARIABLE)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createVariable();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCentralBufferNode();
                if (GenerationProperties.getInstance().isRoundtripEnabled()) {
                    ObjingEAnnotation.setType(PartialCreationExportVisitor.this.ecoreElt, "InstanceNode");
                }
            }
            return null;
        }

        @objid ("01971091-8e6b-40cb-ba31-189b41d97f81")
        @Override
        public Object visitInteraction(Interaction objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInteraction();
            return null;
        }

        @objid ("8b976357-5dcb-4e1c-aa03-21be7e4400eb")
        @Override
        public Object visitInteractionOperand(InteractionOperand objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInteractionOperand();
            return null;
        }

        @objid ("8dbbb3d0-7494-4891-85ce-4e02f693760e")
        @Override
        public Object visitInteractionUse(InteractionUse objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInteractionUse();
            return null;
        }

        @objid ("da0ced01-fea7-4ebf-8264-6ab189013514")
        @Override
        public Object visitInterface(Interface objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInterface();
            return null;
        }

        @objid ("d85b5b59-e303-4a8d-9f25-58773874c4fa")
        @Override
        public Object visitInterfaceRealization(InterfaceRealization objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInterfaceRealization();
            return null;
        }

        @objid ("f1f4c121-c294-4eda-88be-213f4e3c1572")
        @Override
        public Object visitInternalTransition(InternalTransition objingElt) {
            if (objingElt.getBehaviorEffect() == null) {
                org.eclipse.uml2.uml.Transition result = UMLFactory.eINSTANCE.createTransition();
                result.setKind(TransitionKind.INTERNAL_LITERAL);
                PartialCreationExportVisitor.this.ecoreElt = result;
            } else {
                PartialCreationExportVisitor.this.ecoreElt = null;
            }
            return null;
        }

        @objid ("cd89b250-f15f-4c15-a24b-b893e8a8a6be")
        @Override
        public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInterruptibleActivityRegion();
            return null;
        }

        @objid ("e6439bff-5fea-4a24-a5d9-e914647c3615")
        @Override
        public Object visitJoinPseudoState(JoinPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.JOIN_LITERAL);
            return UMLFactory.eINSTANCE.createPseudostate();
        }

        @objid ("c0d1c2ee-8fa6-4934-a62d-5b6851f9dd52")
        @Override
        public Object visitJunctionPseudoState(JunctionPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.JUNCTION_LITERAL);
            return null;
        }

        @objid ("cc56e89c-fd76-457e-a2c5-bb6ff8966124")
        @Override
        public Object visitLifeline(Lifeline objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createLifeline();
            return null;
        }

        @objid ("e93ba410-5e9a-40f4-bbc6-5f38440b883e")
        @Override
        public Object visitLink(Link objingElt) {
            Element linkOwner = AbstractObjingModelNavigation.getLinkOwner(objingElt);
            MObject connectorOwner = AbstractObjingModelNavigation.getConnectorOwner(objingElt);
            
            if (linkOwner != null) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInstanceSpecification();
                return null;
            } else if (connectorOwner != null) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConnector();
                return null;
            }
            
                       
            String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport",
                    objingElt.getName(),
                    objingElt.getClass().getSimpleName());
            GenerationProperties.getInstance().addWarning(message, objingElt);
            PartialCreationExportVisitor.this.ecoreElt = null;
            return null;
        }

        @objid ("400ea871-c724-4637-b31a-c32dd1ade6d9")
        @Override
        public Object visitLinkEnd(LinkEnd objingElt) {
            org.eclipse.uml2.uml.Element connector = null;
            
            if (objingElt.getLink() != null) {
                connector = GenerationProperties.getInstance().getMappedElement(objingElt.getLink());
            }
            
            if (connector != null) {
                if (connector instanceof org.eclipse.uml2.uml.Connector) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConnectorEnd();
                    return null;
                } else if (connector instanceof InstanceSpecification) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSlot();
                    return null;
                }
            }
            
            String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport",
                    objingElt.getName(),
                    objingElt.getClass().getSimpleName());
            GenerationProperties.getInstance().addWarning(message, objingElt);
            PartialCreationExportVisitor.this.ecoreElt = null;
            return null;
        }

        @objid ("4f74202a-5a6a-464b-b28c-2f89a5c345a5")
        @Override
        public Object visitLocalPropertyTable(LocalPropertyTable obj) {
            return null;
        }

        @objid ("c15e2f71-c6b3-4d20-a8a5-909041ffa0d0")
        @Override
        public Object visitLoopNode(LoopNode objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createLoopNode();
            return null;
        }

        @objid ("f163fbd0-a5d0-4795-970f-38739fb91662")
        @Override
        public Object visitManifestation(Manifestation objingElt) {
            if (AbstractObjingModelNavigation.isManifestationMappable(objingElt)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createManifestation();
            } else {
                String message = Xmi.I18N.getMessage("logFile.warning.export.noumlmanifestation", objingElt.getName());
                GenerationProperties.getInstance().addWarning(message, objingElt);
                PartialCreationExportVisitor.this.ecoreElt = null;
            }
            return null;
        }

        @objid ("378bbe0f-a6c2-44f8-8e93-071abee4703b")
        @Override
        public Object visitMatrixDefinition(MatrixDefinition obj) {
            return null;
        }

        @objid ("43c59cda-5813-41a3-9739-aa988a735d33")
        @Override
        public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
            return null;
        }

        @objid ("a1cd5e89-86b6-499b-ae0f-78e0b407d329")
        @Override
        public Object visitMessage(Message objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createMessage();
            return null;
        }

        @objid ("d9fa6d5d-3a84-4550-b32f-0bba721c3651")
        @Override
        public Object visitMessageEnd(MessageEnd objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();
            return null;
        }

        @objid ("cba3536e-fcb5-461f-aee1-55603e28fd87")
        @Override
        public Object visitMessageFlow(MessageFlow objingElt) {
            return null;
        }

        @objid ("f0800123-37e5-4dc0-8e2e-5e90e4a9c977")
        @Override
        public Object visitMetaclassReference(MetaclassReference obj) {
            return null;
        }

        @objid ("14cbb8b8-f9dc-4dbc-8663-426b7826bb94")
        @Override
        public Object visitModelElement(ModelElement obj) {
            return null;
        }

        @objid ("ddfaa200-6887-463c-99a3-c78886d535ce")
        @Override
        public Object visitModuleComponent(ModuleComponent obj) {
            return null;
        }

        @objid ("c7364943-a4c6-4a32-8f09-7ca601d74ae5")
        @Override
        public Object visitModuleParameter(ModuleParameter obj) {
            return null;
        }

        @objid ("22bba9c9-c05f-4834-b98a-23d9e99350eb")
        @Override
        public Object visitNaryAssociation(NaryAssociation objingElt) {
            if (AbstractObjingModelNavigation.isIsClassAssociation(objingElt)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAssociationClass();
                // Case of an AssociationClass - the keys for an ecore
                // AssociationClass are:
                // - the ID of the objing Association
                // - the ID of the objing ClassAssociation
                // - the ID of the objing Class representing the
                // ClassAssociation
                ClassAssociation classAssoc = objingElt.getLinkToClass();
                PartialCreationExportVisitor.this.partialMap.put(classAssoc.getUuid().toString(), PartialCreationExportVisitor.this.ecoreElt);
                PartialCreationExportVisitor.this.partialMap.put(classAssoc.getClassPart().getUuid().toString(), PartialCreationExportVisitor.this.ecoreElt);
            } else {
                if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2COMMUNICATIONPATH)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCommunicationPath();
                } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXTENSION)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExtension();
                } else {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAssociation();
                }
            }
            return null;
        }

        @objid ("14572ee2-ef17-4d3d-b268-30faeb23e8d1")
        @Override
        public Object visitNaryAssociationEnd(NaryAssociationEnd objingElt) {
            NaryAssociation assoc = objingElt.getNaryAssociation();
            
            if (!AbstractObjingModelNavigation.isOwnedByActor(assoc)) {
                if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXTENSIONEND)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExtensionEnd();
                }
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createProperty();
            }
            return null;
        }

        @objid ("5802e5bd-a37c-410d-a817-8dfcc8950b15")
        @Override
        public Object visitNode(Node objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DEVICE)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDevice();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXECUTIONENVIRONMENT)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExecutionEnvironment();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createNode();
            }
            return null;
        }

        @objid ("b87e1129-cbd9-479f-a0fe-26d80d6572ce")
        @Override
        public Object visitNote(Note objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createComment();
            return null;
        }

        @objid ("38d7493b-90fa-4db9-8cb8-3922bdc19dd2")
        @Override
        public Object visitNoteType(NoteType objingElt) {
            return null;
        }

        @objid ("15c3552e-6d45-4030-b2b0-90fc1c04f040")
        @Override
        public Object visitObjectFlow(ObjectFlow objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createObjectFlow();
            return null;
        }

        @objid ("6b2a24c5-20df-4d99-805b-204c61ba0316")
        @Override
        public Object visitOpaqueAction(OpaqueAction objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2VALUESPECIFICATIONACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createValueSpecificationAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CREATEOBJECTACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCreateObjectAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CREATELINKACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createCreateLinkAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READLINKACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadLinkAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DESTROYLINKACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDestroyLinkAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLEARASSOCIATIONACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createClearAssociationAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ADDSTRUCTURALFEATUREVALUEACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAddStructuralFeatureValueAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READSTRUCTURALFEATUREACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadStructuralFeatureAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READEXTENTACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadExtentAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2DESTROYOBJECTACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createDestroyObjectAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ADDVARIABLEVALUEACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createAddVariableValueAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLEARSTRUCTURALFEATUREACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createClearStructuralFeatureAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLEARVARIABLEACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createClearVariableAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2RAISEEXCEPTIONACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createRaiseExceptionAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READISCLASSIFIEROBJECTACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadIsClassifiedObjectAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READLINKOBJECTENDACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadLinkObjectEndAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READLINKOBJECTENDQUALIFIERACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadLinkObjectEndQualifierAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READSELFACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadSelfAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READVARIABLEACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReadVariableAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2RECLASSIFYOBJECTACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReclassifyObjectAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2REDUCEACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReduceAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2REMOVESTRUCTURALFEATUREACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createRemoveStructuralFeatureValueAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2REMOVEVARIABLEVALUEACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createRemoveVariableValueAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2REPLYACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReplyAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2SENDOBJECTACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSendObjectAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2STARTCLASSIFIERBEHAVIORACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createStartClassifierBehaviorAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2TESTIDENTITYACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createTestIdentityAction();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2UNMARSHALLACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createUnmarshallAction();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createOpaqueAction();
            }
            return null;
        }

        @objid ("7acbe3a0-41bd-4060-b97c-36dca49d70fb")
        @Override
        public Object visitOpaqueBehavior(OpaqueBehavior objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createOpaqueBehavior();
            return null;
        }

        @objid ("5a280837-0f3c-4e29-8658-6f0705ccd9bb")
        @Override
        public Object visitOperation(Operation objingElt) {
            MObject objingOwner = objingElt.getCompositionOwner();
            
            if (objingOwner instanceof Actor
                    || objingOwner instanceof UseCase
                    || objingOwner instanceof Signal) {
                PartialCreationExportVisitor.this.ecoreElt = null;
            } else {
                if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2RECEPTION)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createReception();
                } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2REDEFINABLETEMPLATESIGNATURE)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createRedefinableTemplateSignature();
                } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2TEMPLATESIGNATURE)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createTemplateSignature();
                } else {
            
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createOperation();
                }
            }
            return null;
        }

        @objid ("2b63949b-a446-4fb0-8c57-d5f2d0319445")
        @Override
        public Object visitOutputPin(OutputPin objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createOutputPin();
            return null;
        }

        @objid ("20859b97-0c13-445f-8132-fa31380c07ce")
        @Override
        public Object visitPackage(Package objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPackage();
            return null;
        }

        @objid ("f72890c4-311b-420a-a315-978f60ef3b16")
        @Override
        public Object visitPackageImport(PackageImport objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPackageImport();
            return null;
        }

        @objid ("ce8ce642-d1ba-470a-91f4-3f754199d0c9")
        @Override
        public Object visitPackageMerge(PackageMerge objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPackageMerge();
            return null;
        }

        @objid ("9c393b39-c03b-4583-8480-223c085892c8")
        @Override
        public Object visitParameter(Parameter objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLASSIFIERTEMPLATEPARAMETER)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createClassifierTemplateParameter();
            } else if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2OPERATIONTEMPLATEPARAMETER)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createOperationTemplateParameter();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createParameter();
            }
            return null;
        }

        @objid ("ca21e572-2fdc-48f1-ad81-cc038c803668")
        @Override
        public Object visitPartDecomposition(PartDecomposition objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPartDecomposition();
            return null;
        }

        @objid ("f7ba9a65-89b9-444f-bf98-49a08ad0dbc4")
        @Override
        public Object visitPort(Port objingElt) {
            MObject objOwner = objingElt.getCompositionOwner();
            // MObject objOwner = AbstractObjingModelNavigation.getBindableInstanceOwner(objingElt);
            if (objOwner instanceof BindableInstance) {
                boolean exist = false;
                ModelElement representedFeature = objingElt.getRepresentedFeature();
                NameSpace base = ((Instance) objOwner).getBase();
                exist = ((base != null) && (representedFeature != null)
                        && (representedFeature instanceof Port)
                        && (representedFeature.getCompositionOwner().equals(base)));
            
                if (exist) {
                    PartialCreationExportVisitor.this.ecoreElt = GenerationProperties.getInstance().getMappedElement(representedFeature);
                } else {
                    if ((representedFeature != null) && (representedFeature instanceof Port)
                            && !(representedFeature.getCompositionOwner() instanceof Instance)) {
                        PartialCreationExportVisitor.this.ecoreElt = GenerationProperties.getInstance().getMappedElement(representedFeature);
                    } else {
                        PartialCreationExportVisitor.this.ecoreElt = null;
                    }
                }
                // ModelElement representedFeature = objingElt.getRepresentedFeature();
                // if ((representedFeature != null) && (representedFeature instanceof Port) && !(representedFeature.getCompositionOwner() instanceof Instance)){
                // PartialCreationExportVisitor.this.ecoreElt = GenerationProperties.getInstance().getMappedElement(representedFeature);
                // }else{
                // PartialCreationExportVisitor.this.ecoreElt = null;
                // }
            } else if (objOwner instanceof Instance) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSlot();
            } else if (objOwner instanceof Classifier) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPort();
            }
            return null;
        }

        @objid ("08567366-d3e6-47a4-bad1-396591d9e7fa")
        @Override
        public Object visitProfile(final Profile objingElt) {
            return null;
        }

        @objid ("ae283f3c-45bb-4f54-b220-795e94e2a081")
        @Override
        public Object visitProject(Project objingElt) {
            return null;
        }

        @objid ("306d98c0-b73f-4d05-8bbf-4f562ad3d91b")
        @Override
        public Object visitPropertyDefinition(PropertyDefinition obj) {
            return null;
        }

        @objid ("8af74aa8-9c1d-42c6-8e3a-68f36c090b66")
        @Override
        public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral objingElt) {
            return null;
        }

        @objid ("0c2803c4-4e18-4a7e-91db-1e1b07d60f3e")
        @Override
        public Object visitPropertyTable(PropertyTable obj) {
            return null;
        }

        @objid ("ecac07e4-284c-457f-b384-c366ea3f72c6")
        @Override
        public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
            return null;
        }

        @objid ("9ff110f9-3d46-4cce-aa4a-d76e77ea7f0b")
        @Override
        public Object visitPropertyType(PropertyType objingElt) {
            return null;
        }

        @objid ("fa072c3d-7d4c-42a0-b6dc-175806a45f0c")
        @Override
        public Object visitProvidedInterface(ProvidedInterface objingElt) {
            Port owner = objingElt.getProviding();
            if (owner.getBase() == null) {
                PartialCreationExportVisitor.this.ecoreElt = null;
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInterfaceRealization();
            }
            return null;
        }

        @objid ("d7f02e89-eec4-4bd2-825f-bfcf98459cdc")
        @Override
        public Object visitQueryDefinition(QueryDefinition obj) {
            return null;
        }

        @objid ("fa8ca90f-1c4c-4c8f-b179-1399cb73182c")
        @Override
        public Object visitRaisedException(RaisedException objingElt) {
            return null;
        }

        @objid ("c8ddfdbb-4686-44c2-b27d-a2d10a6b8753")
        @Override
        public Object visitRegion(Region objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createRegion();
            return null;
        }

        @objid ("fb74dfad-7f99-4015-bf7e-66b00ecffe21")
        @Override
        public Object visitRequiredInterface(RequiredInterface objingElt) {
            return null;
        }

        @objid ("0fbf7f94-8bc9-4ddc-aad9-621e1bfbc2ea")
        @Override
        public Object visitSendSignalAction(SendSignalAction objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2BROADCASTSIGNALACTION)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createBroadcastSignalAction();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSendSignalAction();
            }
            return null;
        }

        @objid ("a564eadc-0a43-4be6-bdf8-bbe12ad69ac3")
        @Override
        public Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.SHALLOW_HISTORY_LITERAL);
            return null;
        }

        @objid ("45a736c2-1078-40b6-9cbc-8fe87334f308")
        @Override
        public Object visitSignal(Signal objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSignal();
            return null;
        }

        @objid ("b54a1faa-d9df-43b2-824a-ed907ffc9462")
        @Override
        public Object visitState(State objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createState();
            return null;
        }

        @objid ("e107acd8-44ac-418d-8a8e-a6b800889537")
        @Override
        public Object visitStateInvariant(StateInvariant objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createStateInvariant();
            return null;
        }

        @objid ("a5d173a2-da7a-4727-b8b9-ba380e56799b")
        @Override
        public Object visitStateMachine(StateMachine objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createStateMachine();
            return null;
        }

        @objid ("09d80e02-cb25-493e-806c-52065e0c5ac8")
        @Override
        public Object visitStereotype(Stereotype objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = null;
            return null;
        }

        @objid ("e7959ce7-b5b6-423b-88d0-e89584877d28")
        @Override
        public Object visitStructuredActivityNode(StructuredActivityNode objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2SEQUENCENODE)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSequenceNode();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createStructuredActivityNode();
            }
            return null;
        }

        @objid ("381dac06-aab5-459d-bb3c-fa24bb2647ae")
        @Override
        public Object visitSubstitution(Substitution objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createSubstitution();
            return null;
        }

        @objid ("41d76080-8dc0-4107-b27d-89ae9e3a48e2")
        @Override
        public Object visitTagParameter(TagParameter objingElt) {
            return null;
        }

        @objid ("509c8b97-01f0-44ab-92f4-7593c1571d5a")
        @Override
        public Object visitTagType(TagType objingElt) {
            return null;
        }

        @objid ("ae1aec86-a968-49cf-909b-2837b937bd01")
        @Override
        public Object visitTaggedValue(TaggedValue objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = null;
            return null;
        }

        @objid ("4cbf6cbb-fd4b-41fd-9e44-5b175988ba2f")
        @Override
        public Object visitTemplateBinding(TemplateBinding objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createTemplateBinding();
            return null;
        }

        @objid ("10ba33d2-2282-4433-b880-844b564e548a")
        @Override
        public Object visitTemplateParameter(TemplateParameter objingElt) {
            if (objingElt.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CONNECTABLEELEMENTTEMPLATEPARAMETER)) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConnectableElementTemplateParameter();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createTemplateParameter();
            }
            return null;
        }

        @objid ("744cf25f-35f7-4ecd-9d39-b6feb2d610ce")
        @Override
        public Object visitTemplateParameterSubstitution(TemplateParameterSubstitution objingElt) {
            org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingElt.getCompositionOwner());
            
            if (ecoreOwner instanceof TemplateBinding) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createTemplateParameterSubstitution();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = null;
            }
            return null;
        }

        @objid ("1d84033f-04de-427a-bded-7a8eafff38b8")
        @Override
        public Object visitTerminatePseudoState(TerminatePseudoState objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createPseudostate();
            ((Pseudostate) PartialCreationExportVisitor.this.ecoreElt).setKind(PseudostateKind.TERMINATE_LITERAL);
            return null;
        }

        @objid ("c2da8181-50e8-4a0a-bbd8-3ed920c8e44e")
        @Override
        public Object visitTransition(Transition objingElt) {
            if (!(objingElt instanceof InternalTransition)) {
                if (AbstractObjingModelNavigation.isProtocolTransition(objingElt)) {
                    PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createProtocolTransition();
                }
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createTransition();
            }
            PartialCreationExportVisitor.this.ecoreElt = null;
            return null;
        }

        @objid ("c097d3a6-4faa-4707-8b23-9633a754db8f")
        @Override
        public Object visitTypedPropertyTable(TypedPropertyTable obj) {
            return null;
        }

        @objid ("359006dc-94c6-4a8f-91fb-e3f7955177c4")
        @Override
        public Object visitUmlModelElement(UmlModelElement obj) {
            return null;
        }

        @objid ("aba8db3e-4daf-44b3-8ff3-3e11ea5bb93c")
        @Override
        public Object visitUsage(Usage objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createUsage();
            return null;
        }

        @objid ("378e8f31-94c5-433c-bd4b-d7d7c0983497")
        @Override
        public Object visitUseCase(UseCase objingElt) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createUseCase();
            return null;
        }

        @objid ("4816c727-cc0a-485d-aabd-7c0bc6c93e01")
        @Override
        public Object visitUseCaseDependency(UseCaseDependency objingElt) {
            if (AbstractObjingModelNavigation.isStereotyped(objingElt, Xmi.I18N
                    .getString("objing.java.stereotype.extend"))) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createExtend();
            } else if (AbstractObjingModelNavigation.isStereotyped(objingElt, Xmi.I18N
                    .getString("objing.java.stereotype.include"))) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInclude();
            } else {
                PartialCreationExportVisitor.this.ecoreElt = null;
            }
            return null;
        }

        @objid ("ba98b154-7028-444e-9c97-0ac29f105524")
        @Override
        public Object visitValuePin(ValuePin arg0) {
            PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createValuePin();
            return null;
        }

        @objid ("fca54279-f73f-49bf-b9fa-cd6e5ff0026c")
        @Override
        public Object visitImpactProject(ImpactProject obj) {
            return visitAbstractProject(obj);
        }

        @objid ("9976a7c5-2729-47fa-90d6-e29efe4f4b3d")
        @Override
        public Object visitImpactModel(ImpactModel obj) {
            return visitModelElement(obj);
        }

        @objid ("602eacec-e9ee-4d30-a5ae-76f350e517a4")
        @Override
        public Object visitImpactLink(ImpactLink obj) {
            return visitModelElement(obj);
        }

        @objid ("d34c43d6-353b-42dd-929e-9085b0f5f075")
        @Override
        public Object visitImpactDiagram(ImpactDiagram obj) {
            return visitAbstractDiagram(obj);
        }

        @objid ("5c7d7d56-9b22-4b89-bc73-228577aacda2")
        @Override
        public Object visitNaryLink(NaryLink objingElt) {
            Element linkOwner = AbstractObjingModelNavigation.getNaryLinkOwner(objingElt);
            
            MObject connectorOwner = AbstractObjingModelNavigation.getNaryConnectorOwner(objingElt);
            
            if (connectorOwner != null) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createConnector();
            } else if (linkOwner != null) {
                PartialCreationExportVisitor.this.ecoreElt = UMLFactory.eINSTANCE.createInstanceSpecification();
            }
            return null;
        }

        @objid ("719ab9ba-c4d0-46ef-a6d2-4a6750171426")
        @Override
        public Object visitAbstractResource(AbstractResource obj) {
            return null;
        }

        @objid ("3789c671-9c46-4dd3-bdca-1f2f3bed374b")
        @Override
        public Object visitResource(Resource obj) {
            return null;
        }

    }

}
