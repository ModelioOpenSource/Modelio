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

@objid ("230c7c67-2ad9-4e8e-a152-0248c86fe3bb")
public interface IParseModelBehavior {
    @objid ("c4e643e9-a80e-4b8e-a0e9-e49386e944b3")
    ProgressBarComposite getProgressBar();

    @objid ("56fbac65-b0ef-4830-bc73-b10df45e0ef0")
    void visitAbstraction(Abstraction param);

    @objid ("51f05647-3c0f-49e5-98ce-b4fae2fce3db")
    void visitAcceptCallEventAction(AcceptCallEventAction param);

    @objid ("f1328a6d-0781-480d-9012-03e955f3c09b")
    void visitAcceptChangeEventAction(AcceptChangeEventAction param);

    @objid ("b5ad2cdf-045c-4bc4-a780-15dd8ddd12ec")
    void visitAcceptSignalAction(AcceptSignalAction param);

    @objid ("e7feb02e-aa2b-4ee4-ae2b-eebd393a6f99")
    void visitAcceptTimeEventAction(AcceptTimeEventAction param);

    @objid ("58bd83e5-9da2-4ce2-854a-3b1fab8a3e48")
    void visitActivity(Activity param);

    @objid ("e1a3e8a6-a155-4962-84ea-30fa579c6c12")
    void visitActivityFinalNode(ActivityFinalNode param);

    @objid ("d3e99724-0f12-4181-9dfe-d7950ed0d54c")
    void visitActivityParameterNode(ActivityParameterNode param);

    @objid ("8efdb20f-4156-42c1-a354-fb8db3b2ed41")
    void visitActivityPartition(ActivityPartition param);

    @objid ("7f8332fc-00d6-4565-9031-f848a89caee5")
    void visitActor(Actor param);

    @objid ("e2ce95ca-5063-4bee-b0c1-a35f4a01cf0b")
    void visitArtifact(Artifact param);

    @objid ("5621bce3-d322-43a0-b931-6fa4b82516b6")
    void visitAssociation(Association param);

    @objid ("87170c26-166b-4d4f-8459-87313e63b8f1")
    void visitAssociationEnd(AssociationEnd param);

    @objid ("c21a08e9-43d7-442d-a2fb-c72fe32e5fc5")
    void visitAttribute(Attribute param);

    @objid ("5b9fd6d3-7e13-4701-9e14-2bd5f38fd64a")
    void visitAttributeLink(AttributeLink param);

    @objid ("7fbaceb4-89f1-497f-98be-844a4c54d645")
    void visitBehaviorParameter(BehaviorParameter param);

    @objid ("ccaa45ca-e614-4530-a980-3c95206307b2")
    void visitBindableInstance(BindableInstance param);

    @objid ("5f223385-e0f6-492e-a6c0-5358932c122c")
    void visitBinding(Binding param);

    @objid ("265396f9-7075-4961-8c72-076ea655be19")
    void visitBpmnBaseElement(BpmnBaseElement param);

    @objid ("4c9e1d6a-1237-4337-8ddb-f00b7a821762")
    void visitBpmnSharedDefinitions(BpmnSharedDefinitions param);

    @objid ("1aa4e921-b74d-4378-8a56-9c94de19ee1d")
    void visitCallBehaviorAction(CallBehaviorAction param);

    @objid ("e3767e40-2db5-428f-8c57-b36d147bbc1f")
    void visitCallOperationAction(CallOperationAction param);

    @objid ("c664355d-a306-4c18-ad54-372e4296f901")
    void visitCentralBufferNode(CentralBufferNode param);

    @objid ("bd7eeac2-1e87-45d0-913b-c988a395ec70")
    void visitChoicePseudoState(ChoicePseudoState param);

    @objid ("3e40f3e8-0276-4916-ad9a-32156c971779")
    void visitClass(Class param);

    @objid ("744b39e2-3406-4d6c-a3b4-a9d1a19e20a4")
    void visitClassAssociation(ClassAssociation param);

    @objid ("f06f04c5-7e25-43cb-82c5-445e5792464b")
    void visitClause(Clause param);

    @objid ("b2156159-cb88-4647-8158-73523e4c5c04")
    void visitCollaboration(Collaboration param);

    @objid ("abaf6a89-d307-4276-b70d-e90e206dac30")
    void visitCollaborationUse(CollaborationUse param);

    @objid ("82ccfda0-79cd-40be-b8d4-7685b917cf6c")
    void visitCombinedFragment(CombinedFragment param);

    @objid ("e6fccb77-efb5-4fc9-a570-9d84556dd07f")
    void visitComponent(Component param);

    @objid ("80158227-0355-4864-8c63-3ec7e3b58c99")
    void visitConditionalNode(ConditionalNode param);

    @objid ("5a30d92a-826a-4ea3-b6ca-f4b8c8fbaf2d")
    void visitConnectionPointReference(ConnectionPointReference param);

    @objid ("fdb610a9-5650-4be8-9e5c-2116b35a8b2e")
    void visitConnector(Connector param);

    @objid ("268385fc-aa86-461c-b043-4762da1f489f")
    void visitConnectorEnd(ConnectorEnd param);

    @objid ("5dc510e5-48c5-478f-a92f-a8789e570776")
    void visitConstraint(Constraint param);

    @objid ("dac0f386-e3a2-4072-a25b-f03355cf1117")
    void visitControlFlow(ControlFlow param);

    @objid ("bfa7b734-a343-48c0-810a-f8722d143808")
    void visitDataFlow(DataFlow param);

    @objid ("bd89bb33-dbd2-402e-a6fe-0b562508f02d")
    void visitDataStoreNode(DataStoreNode param);

    @objid ("4c22ed9b-5bf5-47de-9dd5-99fe4d88a01b")
    void visitDataType(DataType param);

    @objid ("57c14e57-413f-4a3d-81db-c61ed664507a")
    void visitDecisionMergeNode(DecisionMergeNode param);

    @objid ("a1581b75-5837-4bc7-9f7f-c5b437c23448")
    void visitDeepHistoryPseudoState(DeepHistoryPseudoState param);

    @objid ("fc8f6a13-8c38-4476-a071-1e3f03efc00f")
    void visitDependency(Dependency param);

    @objid ("7b0a0ff7-93bd-4ef2-965b-62d448c6eec5")
    void visitDurationConstraint(DurationConstraint param);

    @objid ("1fb8f255-4f2b-4464-a8ec-45b05e716e78")
    void visitElementImport(ElementImport param);

    @objid ("e3562d28-cc6d-4c0f-aa6a-dae428cdf6c5")
    void visitElementRealization(ElementRealization param);

    @objid ("9294f5d1-f639-4248-a22c-b32d9094a677")
    void visitEntryPointPseudoState(EntryPointPseudoState param);

    @objid ("6ea744e9-c65b-426a-b06a-a697b7eba1cf")
    void visitEnumeratedPropertyType(EnumeratedPropertyType param);

    @objid ("40426933-12ba-428d-8f53-54a891958cdc")
    void visitEnumeration(Enumeration param);

    @objid ("61019a2f-7ebd-4fc9-963c-32dc3e637848")
    void visitEnumerationLiteral(EnumerationLiteral param);

    @objid ("d0d1847b-7ac3-4c25-b756-3195787181ee")
    void visitEvent(Event param);

    @objid ("3ea8cb95-1a5c-485d-9d06-88aaf1a97afa")
    void visitExceptionHandler(ExceptionHandler param);

    @objid ("3d4fb7dd-3831-462c-a50c-a3720993830b")
    void visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification param);

    @objid ("25cb7a8c-1cd2-49f5-a90d-55e86df0fc21")
    void visitExecutionSpecification(ExecutionSpecification param);

    @objid ("2f88e5e1-dc6b-4f9d-b42a-17b68e037feb")
    void visitExitPointPseudoState(ExitPointPseudoState param);

    @objid ("5983e003-bcdc-48ed-9768-72c01858b32b")
    void visitExpansionNode(ExpansionNode param);

    @objid ("719a09de-eeb2-4c4d-8f0f-e0067cb781d2")
    void visitExpansionRegion(ExpansionRegion param);

    @objid ("9b2525ad-a9f7-4d94-820f-05745feb77ce")
    void visitExtensionPoint(ExtensionPoint param);

    @objid ("bbabe981-0cba-4bbd-9382-8e413d4707e3")
    void visitFinalState(FinalState param);

    @objid ("27e0a9bc-8293-4519-bb30-12d38449fb17")
    void visitFlowFinalNode(FlowFinalNode param);

    @objid ("10343c05-4dbe-4180-a36b-2c9a4abee5d5")
    void visitForkJoinNode(ForkJoinNode param);

    @objid ("b5e328e3-7fb7-49a7-a55b-821cf1d5a9ff")
    void visitForkPseudoState(ForkPseudoState param);

    @objid ("b0b6742e-5157-4139-b52f-34f391f2cf9a")
    void visitGate(Gate param);

    @objid ("bcada5e9-99e4-4dde-a704-effef1f9ba92")
    void visitGeneralOrdering(GeneralOrdering param);

    @objid ("7c4fbdf9-c194-4cd7-b876-31a6ed1671ec")
    void visitGeneralization(Generalization param);

    @objid ("d34642ef-c6ce-41e1-b1ad-c9269fc0891a")
    void visitInformationFlow(InformationFlow param);

    @objid ("297e3b22-295e-4990-851b-678ef1e8273a")
    void visitInformationItem(InformationItem param);

    @objid ("4f32b37f-bf8c-4007-bbd2-6d8242854ab5")
    void visitInitialNode(InitialNode param);

    @objid ("bb486d5e-df06-4de5-8940-6c43548d3f50")
    void visitInitialPseudoState(InitialPseudoState param);

    @objid ("a63f09aa-dac3-4c76-92fe-87b2eb07f341")
    void visitInputPin(InputPin param);

    @objid ("3d528c98-0e2a-4083-af01-13d42f04fa05")
    void visitInstance(Instance param);

    @objid ("72d25bbc-ed69-49ec-b031-81666fb3d60b")
    void visitInstanceNode(InstanceNode param);

    @objid ("ea691a78-6187-4243-b72a-c833f90c3f2b")
    void visitInteraction(Interaction param);

    @objid ("9aa6d691-64ab-4f78-aa0c-d6b31d22d95e")
    void visitInteractionOperand(InteractionOperand param);

    @objid ("42ecf5e6-4f80-476c-97d3-344f27e58b7e")
    void visitInteractionUse(InteractionUse param);

    @objid ("7ebb3a83-3d16-4159-b59b-ef4791c93582")
    void visitInterface(Interface param);

    @objid ("70656324-3009-4030-931d-821e8b4fc7ba")
    void visitInterfaceRealization(InterfaceRealization param);

    @objid ("daaff8d9-5b8f-4689-8968-d12e45e25d9f")
    void visitInternalTransition(InternalTransition param);

    @objid ("644b758b-c520-4521-8f6d-91d21d696561")
    void visitInterruptibleActivityRegion(InterruptibleActivityRegion param);

    @objid ("441f5bf9-d68d-44b7-b0e0-1d3c9077ee65")
    void visitJoinPseudoState(JoinPseudoState param);

    @objid ("b8997c09-f2ec-42a2-96a0-8836ce3455e2")
    void visitJunctionPseudoState(JunctionPseudoState param);

    @objid ("e48a5a90-5658-4a9b-8fe2-f9336b03d830")
    void visitLifeline(Lifeline param);

    @objid ("b268944d-2e34-4c10-b594-0585a2a38ad8")
    void visitLink(Link param);

    @objid ("dccac338-c545-4c6f-8f2e-5bb0b8a42c53")
    void visitLinkEnd(LinkEnd param);

    @objid ("5b7f285a-a4cf-4ad0-9916-3faa30e50041")
    void visitLoopNode(LoopNode param);

    @objid ("c0de5f3c-96d8-49ef-a971-9811737a95e7")
    void visitManifestation(Manifestation param);

    @objid ("a95630e0-2a73-4a39-9ff2-d243a81aeedd")
    void visitMessage(Message param);

    @objid ("a9eeac51-4d06-4b63-a82b-8e43aade30f5")
    void visitMessageFlow(MessageFlow param);

    @objid ("f62c3e2e-ff57-4510-b481-c89b863b55b8")
    void visitNode(Node param);

    @objid ("fbfbcd0e-3f11-4b7c-9708-9bd234bf4433")
    void visitNote(Note param);

    @objid ("f9228d33-45f4-4cb6-92c0-824dd11957de")
    void visitNoteType(NoteType param);

    @objid ("34c3318e-b56b-4e0b-9563-2dbf95473250")
    void visitObjectFlow(ObjectFlow param);

    @objid ("d6188345-451d-4328-a1b0-62787cf84f1b")
    void visitOpaqueAction(OpaqueAction param);

    @objid ("946f0410-b44b-42ce-937a-ee9b9a3182f6")
    void visitOpaqueBehavior(OpaqueBehavior param);

    @objid ("7d172f25-ecd9-45e9-9fc1-b9b38399d855")
    void visitOperation(Operation param);

    @objid ("84b981b5-b925-4ea6-9703-9259816639b0")
    void visitOutputPin(OutputPin param);

    @objid ("8994efa8-e850-46ca-b2dc-125bf3cc0bfa")
    void visitPackage(Package param);

    @objid ("604ed4c4-5580-4be9-bbe2-46a8ede76e53")
    void visitPackageImport(PackageImport param);

    @objid ("7f369de4-3097-427a-a980-5b16440d83a2")
    void visitPackageMerge(PackageMerge param);

    @objid ("fafec071-b087-4286-bc12-490cbc21b614")
    void visitParameter(Parameter param);

    @objid ("5cc1b29d-1b9e-4a82-8824-9fdb7b6653d2")
    void visitPartDecomposition(PartDecomposition param);

    @objid ("a2c40ee2-82ac-4710-be89-932c71971ab9")
    void visitPort(Port param);

    @objid ("028c7425-dff5-4b29-a32f-f83d0ab06c4b")
    void visitProject(Project param);

    @objid ("a3faa8cb-87e0-42b2-a1c5-4ddbdc683170")
    void visitPropertyEnumerationLitteral(PropertyEnumerationLitteral param);

    @objid ("f4316921-0163-4ebe-822a-87c53e89c387")
    void visitPropertySet(PropertyTableDefinition param);

    @objid ("65c2858f-ca33-412c-809d-f61c3374fb9e")
    void visitPropertyType(PropertyType param);

    @objid ("cebed798-2e0d-403e-8f0a-31a7415e38c0")
    void visitPropertyValue(PropertyTableDefinition param);

    @objid ("6ea6f2f4-655c-40ec-8c8b-3a773bc09358")
    void visitProvidedInterface(ProvidedInterface param);

    @objid ("7cdd94ce-efbe-41c7-99fa-178301673ad4")
    void visitRaisedException(RaisedException param);

    @objid ("1194afa7-1677-40f2-9280-28a5ad45403d")
    void visitRegion(Region param);

    @objid ("1b83ae93-e555-4cca-86ac-bcb167f45514")
    void visitRequiredInterface(RequiredInterface param);

    @objid ("0b7e053d-0e0c-4ce5-bff2-48f14604d946")
    void visitSendSignalAction(SendSignalAction param);

    @objid ("03bdb626-6c8e-4da5-9e95-377800c73074")
    void visitShallowHistoryPseudoState(ShallowHistoryPseudoState param);

    @objid ("c41d27a3-9fb0-42fa-9644-f0413898bf11")
    void visitSignal(Signal param);

    @objid ("5b5782ef-a24b-4519-9f2a-15a9bdd1bf6e")
    void visitState(State param);

    @objid ("520d3bce-5f4c-45d5-bbc3-6a86bee6d7e7")
    void visitStateInvariant(StateInvariant param);

    @objid ("e7886195-8933-40ce-ab59-64f123675ea2")
    void visitStateMachine(StateMachine param);

    @objid ("a83bf9f5-0667-4d23-bd22-b7edae268c69")
    void visitStereotype(Stereotype param);

    @objid ("2b51078a-830a-44ec-8625-6a5195261b4c")
    void visitStructuredActivityNode(StructuredActivityNode param);

    @objid ("52edc814-d0dd-484c-a4fd-90c6c802db76")
    void visitSubstitution(Substitution param);

    @objid ("69bce679-2f0b-4c86-b861-e9fee31f718e")
    void visitTagParameter(TagParameter param);

    @objid ("2aad0ba8-eff3-4058-b09b-3d68e7a36ec5")
    void visitTagType(TagType param);

    @objid ("1b5c4826-9cfd-48d0-a2ad-fe95a41c102f")
    void visitTaggedValue(TaggedValue param);

    @objid ("80bcb6a9-b8c0-46c1-9ddd-3cf1156921d7")
    void visitTemplateBinding(TemplateBinding param);

    @objid ("c2e7effe-dd17-421f-8c58-f1c355cebcc0")
    void visitTemplateParameter(TemplateParameter param);

    @objid ("979dfb97-b88a-45fb-8ef5-e9a8fb6756b2")
    void visitTemplateParameterSubstitution(TemplateParameterSubstitution param);

    @objid ("c16d66ae-00c1-4398-a93f-feab0e9e036d")
    void visitTerminatePseudoState(TerminatePseudoState param);

    @objid ("f9a22f7b-88d3-4591-9319-a50056554300")
    void visitTransition(Transition param);

    @objid ("4984dbc3-b6b7-4ae2-b126-d996062a9b71")
    void visitUsage(Usage param);

    @objid ("cc4e1ac3-fd3a-485b-a1b0-284da4d95b86")
    void visitUseCase(UseCase param);

    @objid ("3cd6f1ca-126b-41da-82b3-9cef9a2d04b0")
    void visitUseCaseDependency(UseCaseDependency param);

    @objid ("8d97e7de-964b-4fac-b436-eabf43bb6348")
    void visitValuePin(final ValuePin param);

    @objid ("8825f9cc-24d3-4862-baba-cbac8f5975b0")
    void visitNaryAssociationEnd(NaryAssociationEnd param);

    @objid ("a0566d5c-b5e6-4a52-8bcb-cf7e9d13108e")
    void visitNaryAssociation(NaryAssociation param);

    @objid ("46e091b7-a15a-4cc7-a922-c477be831cfd")
    void visitComponentRealization(ComponentRealization param);

    @objid ("27f3c6ae-f1db-49f4-a053-fcaa245bd8f7")
    void visitNaryConnector(NaryConnector param);

    @objid ("2f88aaa5-4712-428e-b321-427ca9c67e92")
    void visitNaryConnectorEnd(NaryConnectorEnd param);

    @objid ("b2e0f9d7-57fc-4823-8bec-32f0f6960d01")
    void visitNaryLink(NaryLink param);

    @objid ("ea2b12b1-c4b5-42a5-a3e1-0a09673c6b84")
    void visitNaryLinkEnd(NaryLinkEnd param);

}
