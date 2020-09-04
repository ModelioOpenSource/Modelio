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

package org.modelio.xmi.generation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
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
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.DurationConstraint;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
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
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ComponentRealization;
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
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
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
import org.modelio.xmi.gui.ProgressBarComposite;
import org.modelio.xmi.model.objing.*;

@objid ("db2c6c72-ccf9-485f-b0dd-8efe3a42aa70")
public class XMIExportBehavior implements IParseModelBehavior {
    @objid ("b36f28b8-50fd-4749-b1bc-b346c0efbe89")
    private ProgressBarComposite currentProgressBar = null;

    @objid ("5ee28975-be0b-408c-a173-10765ada2245")
    private ExportModel exportModel = null;

    @objid ("c57ee3f6-da33-41f5-931e-914e0c319335")
    public XMIExportBehavior() {
        this.exportModel = new ExportModel();
    }

    @objid ("ea9190d2-e69a-4c5c-994d-3e6de90d1801")
    public XMIExportBehavior(final ProgressBarComposite progressBar) {
        this.currentProgressBar = progressBar;
        this.exportModel = new ExportModel(progressBar);
    }

    @objid ("0bf8fc6d-5496-4a1a-8c32-67d7ce3c73e6")
    @Override
    public ProgressBarComposite getProgressBar() {
        return this.currentProgressBar;
    }

    @objid ("db25751c-9a96-4cdb-834a-6bbda00782dd")
    @Override
    public void visitAbstraction(final Abstraction param) {
        OAbstraction element = new OAbstraction(param);
        this.exportModel.export(element);
    }

    @objid ("816cfa8a-da89-4f33-9fe4-2261b0dc4d32")
    @Override
    public void visitAcceptCallEventAction(final AcceptCallEventAction param) {
        OAcceptCallEventAction element = new OAcceptCallEventAction(param);
        this.exportModel.export(element);
    }

    @objid ("8611a6bd-580a-4a97-9782-e3f70e1fc8f4")
    @Override
    public void visitAcceptChangeEventAction(final AcceptChangeEventAction param) {
        OAcceptChangeEventAction element = new OAcceptChangeEventAction(param);
        this.exportModel.export(element);
    }

    @objid ("fdfdbd91-5a9b-4fd0-b7ed-531a396ab3e5")
    @Override
    public void visitAcceptSignalAction(final AcceptSignalAction param) {
        OAcceptSignalAction element = new OAcceptSignalAction(param);
        this.exportModel.export(element);
    }

    @objid ("57886db2-c4ec-4076-8458-5bb4de61acb1")
    @Override
    public void visitAcceptTimeEventAction(final AcceptTimeEventAction param) {
        OAcceptTimeEventAction element = new OAcceptTimeEventAction(param);
        this.exportModel.export(element);
    }

    @objid ("19c777b7-374c-40c3-9813-cac0e08b0088")
    @Override
    public void visitActivity(final Activity param) {
        OActivity element = new OActivity(param);
        this.exportModel.export(element);
    }

    @objid ("e89419e0-cc74-402f-aa02-3b4289ff788c")
    @Override
    public void visitActivityFinalNode(final ActivityFinalNode param) {
        OActivityFinalNode element = new OActivityFinalNode(param);
        this.exportModel.export(element);
    }

    @objid ("3210d6b2-a743-463b-a29a-8179dfc26314")
    @Override
    public void visitActivityParameterNode(final ActivityParameterNode param) {
        OActivityParameterNode element = new OActivityParameterNode(param);
        this.exportModel.export(element);
    }

    @objid ("a61937d9-ad22-4022-ac52-b90d0f29cbb1")
    @Override
    public void visitActivityPartition(final ActivityPartition param) {
        OActivityPartition element = new OActivityPartition(param);
        this.exportModel.export(element);
    }

    @objid ("95339824-72cf-46a5-ba0e-0c073ac0be00")
    @Override
    public void visitActor(final Actor param) {
        OActor element = new OActor(param);
        this.exportModel.export(element);
    }

    @objid ("aa4d2453-e611-49f3-854d-8de0d3f830f6")
    @Override
    public void visitArtifact(final Artifact param) {
        OArtifact element = new OArtifact(param);
        this.exportModel.export(element);
    }

    @objid ("909645ff-c218-42a6-a1b2-8a83223204e5")
    @Override
    public void visitAssociation(final Association param) {
        OAssociation element = new OAssociation(param);
        this.exportModel.export(element);
    }

    @objid ("1caa1f27-cb06-4296-8691-47939292316d")
    @Override
    public void visitAssociationEnd(final AssociationEnd param) {
        OAssociationEnd element = new OAssociationEnd(param);
        this.exportModel.export(element);
    }

    @objid ("7f0375f7-2d21-404f-ac06-354e912cdf9f")
    @Override
    public void visitAttribute(final Attribute param) {
        OAttribute element = new OAttribute(param);
        this.exportModel.export(element);
    }

    @objid ("7da76263-d956-4ec2-a606-6ef9795dbae2")
    @Override
    public void visitAttributeLink(final AttributeLink param) {
        OAttributeLink element = new OAttributeLink(param);
        this.exportModel.export(element);
    }

    @objid ("3d1b18b9-e5b4-4b90-a790-c764b74a45f9")
    @Override
    public void visitBehaviorParameter(final BehaviorParameter param) {
        OBehaviorParameter element = new OBehaviorParameter(param);
        this.exportModel.export(element);
    }

    @objid ("7f274a6f-43d7-4fe5-917f-7b23e7752c9d")
    @Override
    public void visitBindableInstance(final BindableInstance param) {
        OBindableInstance element = new OBindableInstance(param);
        this.exportModel.export(element);
    }

    @objid ("3bc9bc97-c40c-400c-8bc1-db06a3161f5a")
    @Override
    public void visitBinding(final Binding param) {
        OBinding element = new OBinding(param);
        this.exportModel.export(element);
    }

    @objid ("d5963856-a13d-463f-b4ff-9ece88d98c52")
    @Override
    public void visitBpmnBaseElement(final BpmnBaseElement param) {
        OBpmnBaseElement element = new OBpmnBaseElement(param);
        this.exportModel.export(element);
    }

    @objid ("4140cdca-9850-4a5a-b44a-082b4d1cedc5")
    @Override
    public void visitBpmnSharedDefinitions(BpmnSharedDefinitions param) {
        OBpmnBehavior element = new OBpmnBehavior(param);
        this.exportModel.export(element);
    }

    @objid ("9f08b370-f826-4bd0-9152-ca8990285d40")
    @Override
    public void visitCallBehaviorAction(final CallBehaviorAction param) {
        OCallBehaviorAction element = new OCallBehaviorAction(param);
        this.exportModel.export(element);
    }

    @objid ("f2ccce78-92cf-4fa5-a5a5-f63b199166a2")
    @Override
    public void visitCallOperationAction(final CallOperationAction param) {
        OCallOperationAction element = new OCallOperationAction(param);
        this.exportModel.export(element);
    }

    @objid ("91d481d1-7ae0-4c3d-bb6a-5f285fa9e2a2")
    @Override
    public void visitCentralBufferNode(final CentralBufferNode param) {
        OCentralBufferNode element = new OCentralBufferNode(param);
        this.exportModel.export(element);
    }

    @objid ("9e492366-e178-4a28-a110-ba9965a33852")
    @Override
    public void visitChoicePseudoState(final ChoicePseudoState param) {
        OChoicePseudoState element = new OChoicePseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("03d160a4-6ca2-4dcf-8c7f-5575de1828e3")
    @Override
    public void visitClass(final Class param) {
        OClass element = new OClass(param);
        this.exportModel.export(element);
    }

    @objid ("b9d88bfa-b0f8-4a29-88b5-987a97212f4d")
    @Override
    public void visitClassAssociation(final ClassAssociation param) {
        OClassAssociation element = new OClassAssociation(param);
        this.exportModel.export(element);
    }

    @objid ("d4f36aae-9978-4cd2-ba77-7d974f4fb81a")
    @Override
    public void visitClause(final Clause param) {
        OClause element = new OClause(param);
        this.exportModel.export(element);
    }

    @objid ("a064b253-a8e0-4b37-8330-96d2a1f08176")
    @Override
    public void visitCollaboration(final Collaboration param) {
        OCollaboration element = new OCollaboration(param);
        this.exportModel.export(element);
    }

    @objid ("e61bb4f1-0c0c-4c4c-b74e-38940234a389")
    @Override
    public void visitCollaborationUse(final CollaborationUse param) {
        OCollaborationUse element = new OCollaborationUse(param);
        this.exportModel.export(element);
    }

    @objid ("113d0207-4411-4903-a188-d8c2e081e18c")
    @Override
    public void visitCombinedFragment(final CombinedFragment param) {
        OCombinedFragment element = new OCombinedFragment(param);
        this.exportModel.export(element);
    }

    @objid ("148f1b3c-b79f-408d-95b3-9ab5b5c5bee1")
    @Override
    public void visitComponent(final Component param) {
        OComponent element = new OComponent(param);
        this.exportModel.export(element);
    }

    @objid ("345b3bdc-c6ca-4d4b-a774-1d9cb10cfb63")
    @Override
    public void visitComponentRealization(final ComponentRealization param) {
        OComponentRealization element = new OComponentRealization(param);
        this.exportModel.export(element);
    }

    @objid ("51e1ecac-dd79-4a3a-acd6-fb766df41e36")
    @Override
    public void visitConditionalNode(final ConditionalNode param) {
        OConditionalNode element = new OConditionalNode(param);
        this.exportModel.export(element);
    }

    @objid ("f93bd2de-9648-45db-9f61-f8dcdc8926e0")
    @Override
    public void visitConnectionPointReference(final ConnectionPointReference param) {
        OConnectionPointReference element = new OConnectionPointReference(
                param);
        this.exportModel.export(element);
    }

    @objid ("c719e9d9-aa03-4444-aadc-3f338f3dff00")
    @Override
    public void visitConnector(final Connector param) {
        OConnector element = new OConnector(param);
        this.exportModel.export(element);
    }

    @objid ("9680930c-4d97-49c5-a10c-4b1b9ed80827")
    @Override
    public void visitConnectorEnd(final ConnectorEnd param) {
        OConnectorEnd element = new OConnectorEnd(param);
        this.exportModel.export(element);
    }

    @objid ("8a5b2faf-d749-4365-9740-dd2b04bff8f9")
    @Override
    public void visitConstraint(final Constraint param) {
        OConstraint element = new OConstraint(param);
        this.exportModel.export(element);
    }

    @objid ("398e7057-de78-44c6-a2f7-704c57d6a9c4")
    @Override
    public void visitControlFlow(final ControlFlow param) {
        OControlFlow element = new OControlFlow(param);
        this.exportModel.export(element);
    }

    @objid ("75e38c38-0c74-4bd9-8095-d760b148c8c7")
    @Override
    public void visitDataFlow(final DataFlow param) {
        ODataFlow element = new ODataFlow(param);
        this.exportModel.export(element);
    }

    @objid ("1fc0867b-6bb3-4fa4-9ef0-ba639bcc999a")
    @Override
    public void visitDataStoreNode(final DataStoreNode param) {
        ODataStoreNode element = new ODataStoreNode(param);
        this.exportModel.export(element);
    }

    @objid ("48fbc1cb-7817-43f1-914d-76e1e62bd4cc")
    @Override
    public void visitDataType(final DataType param) {
        ODataType element = new ODataType(param);
        this.exportModel.export(element);
    }

    @objid ("0b9c9695-73c5-4fb3-8001-8512cee51b08")
    @Override
    public void visitDecisionMergeNode(final DecisionMergeNode param) {
        ODecisionMergeNode element = new ODecisionMergeNode(param);
        this.exportModel.export(element);
    }

    @objid ("d4560c8a-f927-4211-8df7-123270aa7656")
    @Override
    public void visitDeepHistoryPseudoState(final DeepHistoryPseudoState param) {
        ODeepHistoryPseudoState element = new ODeepHistoryPseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("557a49e5-7492-4880-ba61-b942f75fc5ea")
    @Override
    public void visitDependency(final Dependency param) {
        ODependency element = new ODependency(param);
        this.exportModel.export(element);
    }

    @objid ("754ec71b-7254-4522-b0ec-ebb068de98f8")
    @Override
    public void visitDurationConstraint(final DurationConstraint param) {
        ODurationConstraint element = new ODurationConstraint(param);
        this.exportModel.export(element);
    }

    @objid ("717aecb6-3655-4879-b794-ead4769d5e4a")
    @Override
    public void visitElementImport(final ElementImport param) {
        OElementImport element = new OElementImport(param);
        this.exportModel.export(element);
    }

    @objid ("f9ccf695-a214-4bc9-b44b-98b8347cef00")
    @Override
    public void visitElementRealization(final ElementRealization param) {
        OElementRealization element = new OElementRealization(param);
        this.exportModel.export(element);
    }

    @objid ("33bc07ee-0277-4698-a28a-5ea980c6433a")
    @Override
    public void visitEntryPointPseudoState(final EntryPointPseudoState param) {
        OEntryPointPseudoState element = new OEntryPointPseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("26e00ab1-e5bb-434b-8e61-ffc201094640")
    @Override
    public void visitEnumeratedPropertyType(final EnumeratedPropertyType param) {
        OEnumeratedPropertyType element = new OEnumeratedPropertyType(param);
        this.exportModel.export(element);
    }

    @objid ("d25b81a5-77c4-4924-9142-9ae0b4c0d098")
    @Override
    public void visitEnumeration(final Enumeration param) {
        OEnumeration element = new OEnumeration(param);
        this.exportModel.export(element);
    }

    @objid ("a3864850-6588-47ee-9617-e77428e2f614")
    @Override
    public void visitEnumerationLiteral(final EnumerationLiteral param) {
        OEnumerationLiteral element = new OEnumerationLiteral(param);
        this.exportModel.export(element);
    }

    @objid ("163e1a4a-2af8-4472-bcb2-e2dd4f2063bb")
    @Override
    public void visitEvent(final Event param) {
        OEvent element = new OEvent(param);
        this.exportModel.export(element);
    }

    @objid ("4a76de70-1a91-4f64-a346-8e9c1000af02")
    @Override
    public void visitExceptionHandler(final ExceptionHandler param) {
        OExceptionHandler element = new OExceptionHandler(param);
        this.exportModel.export(element);
    }

    @objid ("268fcb20-e625-4958-91e3-5d1beffb22c5")
    @Override
    public void visitExecutionOccurenceSpecification(final ExecutionOccurenceSpecification param) {
        OExecutionOccurenceSpecification element = new OExecutionOccurenceSpecification(
                param);
        this.exportModel.export(element);
    }

    @objid ("f51067df-796a-4251-8e9f-5db0434e5750")
    @Override
    public void visitExecutionSpecification(final ExecutionSpecification param) {
        OExecutionSpecification element = new OExecutionSpecification(param);
        this.exportModel.export(element);
    }

    @objid ("c67f25a2-3105-4e29-bedc-872ae12a8372")
    @Override
    public void visitExitPointPseudoState(final ExitPointPseudoState param) {
        OExitPointPseudoState element = new OExitPointPseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("ea0025ad-b71f-41c2-a8de-8b22e9204a47")
    @Override
    public void visitExpansionNode(final ExpansionNode param) {
        OExpansionNode element = new OExpansionNode(param);
        this.exportModel.export(element);
    }

    @objid ("45a2cb9b-d541-4b2a-8625-8eefd44c8db9")
    @Override
    public void visitExpansionRegion(final ExpansionRegion param) {
        OExpansionRegion element = new OExpansionRegion(param);
        this.exportModel.export(element);
    }

    @objid ("c1e002d2-0563-40db-a8de-b3327854ef29")
    @Override
    public void visitExtensionPoint(final ExtensionPoint param) {
        OExtensionPoint element = new OExtensionPoint(param);
        this.exportModel.export(element);
    }

    @objid ("31f11a0d-1877-4404-b67f-333e013e7cb8")
    @Override
    public void visitFinalState(final FinalState param) {
        OFinalState element = new OFinalState(param);
        this.exportModel.export(element);
    }

    @objid ("c489fb2a-33fd-4947-88df-d8c9aca9e821")
    @Override
    public void visitFlowFinalNode(final FlowFinalNode param) {
        OFlowFinalNode element = new OFlowFinalNode(param);
        this.exportModel.export(element);
    }

    @objid ("8a3042ac-0d2d-4008-9d9d-bbfed7967345")
    @Override
    public void visitForkJoinNode(final ForkJoinNode param) {
        OForkJoinNode element = new OForkJoinNode(param);
        this.exportModel.export(element);
    }

    @objid ("0a547466-0d90-4289-9067-d60a7a81f2af")
    @Override
    public void visitForkPseudoState(final ForkPseudoState param) {
        OForkPseudoState element = new OForkPseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("8435559a-41c2-4124-880a-df190b2ce014")
    @Override
    public void visitGate(final Gate param) {
        OGate element = new OGate(param);
        this.exportModel.export(element);
    }

    @objid ("166ad747-3487-4dbc-9b54-4c5300733179")
    @Override
    public void visitGeneralOrdering(final GeneralOrdering param) {
        OGeneralOrdering element = new OGeneralOrdering(param);
        this.exportModel.export(element);
    }

    @objid ("8802f05f-0211-49ec-85e3-b7ae3b837456")
    @Override
    public void visitGeneralization(final Generalization param) {
        OGeneralization element = new OGeneralization(param);
        this.exportModel.export(element);
    }

    @objid ("50aad95c-7926-4f35-8b09-a300259d64ee")
    @Override
    public void visitInformationFlow(final InformationFlow param) {
        OInformationFlow element = new OInformationFlow(param);
        this.exportModel.export(element);
    }

    @objid ("f1893400-1736-4b49-84bc-dfc56868cc0e")
    @Override
    public void visitInformationItem(final InformationItem param) {
        OInformationItem element = new OInformationItem(param);
        this.exportModel.export(element);
    }

    @objid ("24992539-5d89-43fd-a488-644ec819e8c4")
    @Override
    public void visitInitialNode(final InitialNode param) {
        OInitialNode element = new OInitialNode(param);
        this.exportModel.export(element);
    }

    @objid ("c726e0b8-e94f-48bd-a55f-41d7dba497fa")
    @Override
    public void visitInitialPseudoState(final InitialPseudoState param) {
        OInitialPseudoState element = new OInitialPseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("8b5f8a9e-d5c4-4674-97ee-6fdd97b40e16")
    @Override
    public void visitInputPin(final InputPin param) {
        OInputPin element = new OInputPin(param);
        this.exportModel.export(element);
    }

    @objid ("51fc7ed1-7649-49f5-9f95-341459700a28")
    @Override
    public void visitInstance(final Instance param) {
        OInstance element = new OInstance(param);
        this.exportModel.export(element);
    }

    @objid ("de0e1b5c-f503-45c3-9280-e614ddf5f584")
    @Override
    public void visitInstanceNode(final InstanceNode param) {
        OInstanceNode element = new OInstanceNode(param);
        this.exportModel.export(element);
    }

    @objid ("ee4acd0d-a918-4916-ad90-a6d3f1e91c4e")
    @Override
    public void visitInteraction(final Interaction param) {
        OInteraction element = new OInteraction(param);
        this.exportModel.export(element);
    }

    @objid ("d2881778-b432-4297-981f-67cea91cbe46")
    public void visitInteractionFragment(final InteractionFragment param) {
        OInteractionFragment element = new OInteractionFragment(param);
        this.exportModel.export(element);
    }

    @objid ("5e816533-60a9-4d5b-aaf7-5ada22f5de48")
    @Override
    public void visitInteractionOperand(final InteractionOperand param) {
        OInteractionOperand element = new OInteractionOperand(param);
        this.exportModel.export(element);
    }

    @objid ("8b2b9071-bb94-4fef-b4df-961e8d00e03d")
    @Override
    public void visitInteractionUse(final InteractionUse param) {
        OInteractionUse element = new OInteractionUse(param);
        this.exportModel.export(element);
    }

    @objid ("111bf670-ad11-4860-bd1d-cf4a40d10caa")
    @Override
    public void visitInterface(final Interface param) {
        OInterface element = new OInterface(param);
        this.exportModel.export(element);
    }

    @objid ("e19ca8c1-4f55-4756-87fd-1fa5ff4ba3ce")
    @Override
    public void visitInterfaceRealization(final InterfaceRealization param) {
        OInterfaceRealization element = new OInterfaceRealization(param);
        this.exportModel.export(element);
    }

    @objid ("7cc1e62f-c54a-42ed-928a-f06c3606fa05")
    @Override
    public void visitInternalTransition(final InternalTransition param) {
        OInternalTransition element = new OInternalTransition(param);
        this.exportModel.export(element);
    }

    @objid ("57d9cdbe-6fbd-4b1e-a87f-efe9ecfaa9fc")
    @Override
    public void visitInterruptibleActivityRegion(final InterruptibleActivityRegion param) {
        OInterruptibleActivityRegion element = new OInterruptibleActivityRegion(
                param);
        this.exportModel.export(element);
    }

    @objid ("213414a8-0810-423a-80a5-a4af3d3f1049")
    @Override
    public void visitJoinPseudoState(final JoinPseudoState param) {
        OJoinPseudoState element = new OJoinPseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("680d92bd-b35b-4831-b8aa-1aa01f0de269")
    @Override
    public void visitJunctionPseudoState(final JunctionPseudoState param) {
        OJunctionPseudoState element = new OJunctionPseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("67e5b6ef-75ee-4ec9-992e-23e878faac76")
    @Override
    public void visitLifeline(final Lifeline param) {
        OLifeline element = new OLifeline(param);
        this.exportModel.export(element);
    }

    @objid ("119e702e-167f-4dad-98bb-918bc906ecfa")
    @Override
    public void visitLink(final Link param) {
        OLink element = new OLink(param);
        this.exportModel.export(element);
    }

    @objid ("3aa77ad9-4d49-409f-bfdd-838c87542ea4")
    @Override
    public void visitLinkEnd(final LinkEnd param) {
        OLinkEnd element = new OLinkEnd(param);
        this.exportModel.export(element);
    }

    @objid ("33ad6729-4c43-406a-af1d-330fa32e17f0")
    @Override
    public void visitLoopNode(final LoopNode param) {
        OLoopNode element = new OLoopNode(param);
        this.exportModel.export(element);
    }

    @objid ("ff237614-5c0c-4d99-82dc-9adbc26e0a9f")
    @Override
    public void visitManifestation(final Manifestation param) {
        OManifestation element = new OManifestation(param);
        this.exportModel.export(element);
    }

    @objid ("4079d995-f8bd-4c88-b85c-84789915e94e")
    @Override
    public void visitMessage(final Message param) {
        OMessage element = new OMessage(param);
        this.exportModel.export(element);
    }

    @objid ("efa47048-1bb5-454b-b1bd-a7c9fb0c680e")
    @Override
    public void visitMessageFlow(final MessageFlow param) {
        OMessageFlow element = new OMessageFlow(param);
        this.exportModel.export(element);
    }

    @objid ("d0324e32-49ed-4661-a65d-4a0a555668eb")
    @Override
    public void visitNaryAssociation(final NaryAssociation param) {
        ONaryAssociation element = new ONaryAssociation(param);
        this.exportModel.export(element);
    }

    @objid ("68d78f3d-9964-430d-99a8-aea9988de3b8")
    @Override
    public void visitNaryAssociationEnd(final NaryAssociationEnd param) {
        ONaryAssociationEnd element = new ONaryAssociationEnd(param);
        this.exportModel.export(element);
    }

    @objid ("a238ff17-b0be-44f8-89bf-e07da1e5a193")
    @Override
    public void visitNaryConnector(final NaryConnector param) {
        ONaryConnector element = new ONaryConnector(param);
        this.exportModel.export(element);
    }

    @objid ("ea0b0227-609d-46d1-9214-d89bbfa037df")
    @Override
    public void visitNaryConnectorEnd(final NaryConnectorEnd param) {
        ONaryConnectorEnd element = new ONaryConnectorEnd(param);
        this.exportModel.export(element);
    }

    @objid ("676a397a-a6d5-4171-b564-8c8058b7dba2")
    @Override
    public void visitNaryLink(final NaryLink param) {
        ONaryLink element = new ONaryLink(param);
        this.exportModel.export(element);
    }

    @objid ("ad2b4aaa-399b-4986-9fd6-7f0259f8f16f")
    @Override
    public void visitNaryLinkEnd(final NaryLinkEnd param) {
        ONaryLinkEnd element = new ONaryLinkEnd(param);
        this.exportModel.export(element);
    }

    @objid ("cd092910-28fd-4736-8971-a1ce2b095aa1")
    @Override
    public void visitNode(final Node param) {
        ONode element = new ONode(param);
        this.exportModel.export(element);
    }

    @objid ("21bb9146-37ff-4276-8727-1f1ab22bb98e")
    @Override
    public void visitNote(final Note param) {
        ONote element = new ONote(param);
        this.exportModel.export(element);
    }

    @objid ("eaef09eb-201b-402b-808c-ba5e429fcb28")
    @Override
    public void visitNoteType(final NoteType param) {
        ONoteType element = new ONoteType(param);
        this.exportModel.export(element);
    }

    @objid ("f9cad382-28be-4a14-bd9c-9671857162fa")
    @Override
    public void visitObjectFlow(final ObjectFlow param) {
        OObjectFlow element = new OObjectFlow(param);
        this.exportModel.export(element);
    }

    @objid ("89be28da-cf03-4b83-bafc-2677f012af3b")
    @Override
    public void visitOpaqueAction(final OpaqueAction param) {
        OOpaqueAction element = new OOpaqueAction(param);
        this.exportModel.export(element);
    }

    @objid ("f183df0c-3bcb-4648-956b-1a07c33bed8d")
    @Override
    public void visitOpaqueBehavior(final OpaqueBehavior param) {
        OOpaqueBehavior element = new OOpaqueBehavior(param);
        this.exportModel.export(element);
    }

    @objid ("273051c7-28f6-4e93-be7c-c51de60a6442")
    @Override
    public void visitOperation(final Operation param) {
        OOperation element = new OOperation(param);
        this.exportModel.export(element);
    }

    @objid ("324486c6-2bef-4669-bedc-6ee0f352e909")
    @Override
    public void visitOutputPin(final OutputPin param) {
        OOutputPin element = new OOutputPin(param);
        this.exportModel.export(element);
    }

    @objid ("d8dae3c9-2241-4310-8baa-094459aba7dd")
    @Override
    public void visitPackage(final Package param) {
        OPackage element = new OPackage(param);
        this.exportModel.export(element);
    }

    @objid ("6686324b-846f-47af-9f56-df479598905f")
    @Override
    public void visitPackageImport(final PackageImport param) {
        OPackageImport element = new OPackageImport(param);
        this.exportModel.export(element);
    }

    @objid ("5a3dd9c0-dd90-4e29-ab29-2b1838b1e7fe")
    @Override
    public void visitPackageMerge(final PackageMerge param) {
        OPackageMerge element = new OPackageMerge(param);
        this.exportModel.export(element);
    }

    @objid ("c5bc3dc7-1135-467a-9f81-01c51b5f20ca")
    @Override
    public void visitParameter(final Parameter param) {
        OParameter element = new OParameter(param);
        this.exportModel.export(element);
    }

    @objid ("cc4a1f66-a35c-44cc-ba3d-2784bc7c2c47")
    @Override
    public void visitPartDecomposition(final PartDecomposition param) {
        OPartDecomposition element = new OPartDecomposition(param);
        this.exportModel.export(element);
    }

    @objid ("12c830e1-642e-4bac-ade1-c54093f3e93d")
    @Override
    public void visitPort(final Port param) {
        OPort element = new OPort(param);
        this.exportModel.export(element);
    }

    @objid ("7694b4b4-c48a-4b1c-8ab4-9f2ca74233ab")
    @Override
    public void visitProject(final Project param) {
        OProject element = new OProject(param);
        this.exportModel.export(element);
    }

    @objid ("d7cfcd20-ad3f-4f3e-935f-eddc1b959599")
    @Override
    public void visitPropertyEnumerationLitteral(final PropertyEnumerationLitteral param) {
        OPropertyEnumerationLitteral element = new OPropertyEnumerationLitteral(
                param);
        this.exportModel.export(element);
    }

    @objid ("0e18758b-1e35-462e-a17c-784524781b45")
    @Override
    public void visitPropertySet(final PropertyTableDefinition param) {
        OPropertySet element = new OPropertySet(param);
        this.exportModel.export(element);
    }

    @objid ("31c989cb-af42-407b-a4fd-51512b61e8e4")
    @Override
    public void visitPropertyType(final PropertyType param) {
        OPropertyType element = new OPropertyType(param);
        this.exportModel.export(element);
    }

    @objid ("c72eb70f-51a0-4dd7-b3c8-55ae46ea15d8")
    @Override
    public void visitPropertyValue(final PropertyTableDefinition param) {
        OPropertyValue element = new OPropertyValue(param);
        this.exportModel.export(element);
    }

    @objid ("601fbe75-84fb-4e44-a740-2bb1188e7745")
    @Override
    public void visitProvidedInterface(final ProvidedInterface param) {
        OProvidedInterface element = new OProvidedInterface(param);
        this.exportModel.export(element);
    }

    @objid ("9b7d588b-13c5-42f6-8061-3e69018a8efe")
    @Override
    public void visitRaisedException(final RaisedException param) {
        ORaisedException element = new ORaisedException(param);
        this.exportModel.export(element);
    }

    @objid ("b97564c5-9d05-42a8-8c42-7e513b3ac25f")
    @Override
    public void visitRegion(final Region param) {
        ORegion element = new ORegion(param);
        this.exportModel.export(element);
    }

    @objid ("693fee98-9332-45c9-ab53-ecd1abc97bb0")
    @Override
    public void visitRequiredInterface(final RequiredInterface param) {
        ORequiredInterface element = new ORequiredInterface(param);
        this.exportModel.export(element);
    }

    @objid ("6ce06b12-36e1-4083-8f18-170600bf1c21")
    @Override
    public void visitSendSignalAction(final SendSignalAction param) {
        OSendSignalAction element = new OSendSignalAction(param);
        this.exportModel.export(element);
    }

    @objid ("db484120-7d50-478e-9945-03a0d7341462")
    @Override
    public void visitShallowHistoryPseudoState(final ShallowHistoryPseudoState param) {
        OShallowHistoryPseudoState element = new OShallowHistoryPseudoState(
                param);
        this.exportModel.export(element);
    }

    @objid ("09f516d1-7d90-48b1-a890-311e120c4de4")
    @Override
    public void visitSignal(final Signal param) {
        OSignal element = new OSignal(param);
        this.exportModel.export(element);
    }

    @objid ("017e2eee-23f9-4985-a366-69819a695493")
    @Override
    public void visitState(final State param) {
        OState element = new OState(param);
        this.exportModel.export(element);
    }

    @objid ("58b1c2bd-b3e6-4b85-85e4-7f01988c2cb8")
    @Override
    public void visitStateInvariant(final StateInvariant param) {
        OStateInvariant element = new OStateInvariant(param);
        this.exportModel.export(element);
    }

    @objid ("c570cbd3-b6e0-4cb5-8cf7-c4e8ef16266a")
    @Override
    public void visitStateMachine(final StateMachine param) {
        OStateMachine element = new OStateMachine(param);
        this.exportModel.export(element);
    }

    @objid ("e22dc9b0-2a10-44f9-affb-035057dab2bd")
    @Override
    public void visitStereotype(final Stereotype param) {
        OStereotype element = new OStereotype(param);
        this.exportModel.export(element);
    }

    @objid ("8209bb92-0f6c-48f9-af84-0284688bcb5b")
    @Override
    public void visitStructuredActivityNode(final StructuredActivityNode param) {
        OStructuredActivityNode element = new OStructuredActivityNode(param);
        this.exportModel.export(element);
    }

    @objid ("aa7bef2e-bf7a-4bcf-949d-7388ed9a7f57")
    @Override
    public void visitSubstitution(final Substitution param) {
        OSubstitution element = new OSubstitution(param);
        this.exportModel.export(element);
    }

    @objid ("e1e94048-f747-4a38-82ea-1a28e26c6362")
    @Override
    public void visitTagParameter(final TagParameter param) {
        OTagParameter element = new OTagParameter(param);
        this.exportModel.export(element);
    }

    @objid ("e6dfc22f-efea-4a7e-8b6c-3ac0ab987d63")
    @Override
    public void visitTagType(final TagType param) {
        OTagType element = new OTagType(param);
        this.exportModel.export(element);
    }

    @objid ("6cb32fb2-ddbb-4e23-8cea-ab07fd25e826")
    @Override
    public void visitTaggedValue(final TaggedValue param) {
        OTaggedValue element = new OTaggedValue(param);
        this.exportModel.export(element);
    }

    @objid ("29c9c84f-e2e6-48ae-b28e-b372eef59e9c")
    @Override
    public void visitTemplateBinding(final TemplateBinding param) {
        OTemplateBinding element = new OTemplateBinding(param);
        this.exportModel.export(element);
    }

    @objid ("a5924516-3d43-400d-b123-a02678711000")
    @Override
    public void visitTemplateParameter(final TemplateParameter param) {
        OTemplateParameter element = new OTemplateParameter(param);
        this.exportModel.export(element);
    }

    @objid ("810920a2-74f9-45d8-a577-ffbeff6c15ab")
    @Override
    public void visitTemplateParameterSubstitution(final TemplateParameterSubstitution param) {
        OTemplateParameterSubstitution element = new OTemplateParameterSubstitution(
                param);
        this.exportModel.export(element);
    }

    @objid ("208dde36-1588-4472-bec7-2e57bf75f475")
    @Override
    public void visitTerminatePseudoState(final TerminatePseudoState param) {
        OTerminatePseudoState element = new OTerminatePseudoState(param);
        this.exportModel.export(element);
    }

    @objid ("be04515f-edfa-413b-af5b-517c5ea16759")
    @Override
    public void visitTransition(final Transition param) {
        OTransition element = new OTransition(param);
        this.exportModel.export(element);
    }

    @objid ("cba4a90e-0079-4cbc-bb29-53fadbbdc5b7")
    @Override
    public void visitUsage(final Usage param) {
        OUsage element = new OUsage(param);
        this.exportModel.export(element);
    }

    @objid ("6860cb37-6806-4553-92f6-7c054016a23f")
    @Override
    public void visitUseCase(final UseCase param) {
        OUseCase element = new OUseCase(param);
        this.exportModel.export(element);
    }

    @objid ("dd8ba963-9b18-4097-9936-4ce6be46ae4a")
    @Override
    public void visitUseCaseDependency(final UseCaseDependency param) {
        OUseCaseDependency element = new OUseCaseDependency(param);
        this.exportModel.export(element);
    }

    @objid ("f1c9f713-e2dd-4090-874d-cdaec7f2ccb7")
    @Override
    public void visitValuePin(final ValuePin param) {
        OValuePin element = new OValuePin(param);
        this.exportModel.export(element);
    }

}
