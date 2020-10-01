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

package org.modelio.xmi.reverse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StringExpression;
import org.eclipse.uml2.uml.TimeObservation;
import org.modelio.xmi.gui.ProgressBarComposite;
import org.modelio.xmi.model.ecore.*;

@objid ("478974e8-83d2-4fe1-a45c-acb04c22f231")
public class XMIImportBehavior {
    @objid ("359bbc30-5b9c-4eac-95ce-5c955faefc25")
    private ImportModel importModel;

    @objid ("f854b7f0-4896-4b55-abb9-a2865f0488a5")
    private ProgressBarComposite theProgressBar = null;

    @objid ("3898f67c-a581-409a-bcf5-7db54e091b8e")
    public XMIImportBehavior(ProgressBarComposite progressBar) {
        this.importModel = new ImportModel(progressBar);
        this.theProgressBar = progressBar;
    }

    @objid ("0df64778-e991-4acf-b7d8-345be2f52a4e")
    public XMIImportBehavior() {
        this.importModel = new ImportModel();
    }

    @objid ("fd5c9f8d-bca2-413b-8106-ab5d20ec4079")
    public void visitProperty(Property inputProperty) {
        EProperty element = new EProperty(inputProperty);
        this.importModel.importXMI(element);
    }

    @objid ("4d35a0ab-2085-4a7e-8439-d1b8684fad29")
    public void visitClass(org.eclipse.uml2.uml.Class inputClass) {
        EClass element = new EClass(inputClass);
        this.importModel.importXMI(element);
    }

    @objid ("092ab424-7ad2-4032-aaaf-e6d302ae5187")
    public void visitPackage(org.eclipse.uml2.uml.Package inputPackage) {
        EPackage element = new EPackage(inputPackage);
        this.importModel.importXMI(element);
    }

    @objid ("18361083-2073-4305-bf76-54ee125f1f9f")
    public void visitStereotype(org.eclipse.uml2.uml.Stereotype inputStereotype) {
        EStereotype element = new EStereotype(inputStereotype);
        this.importModel.importXMI(element);
    }

    @objid ("7926863a-714a-4eb4-a0a8-3aa0435dcf5a")
    public void visitAbstraction(org.eclipse.uml2.uml.Abstraction inputAbstraction) {
        EAbstraction element = new EAbstraction(inputAbstraction);
        this.importModel.importXMI(element);
    }

    @objid ("9a9696a6-06dc-40c3-84c9-d095d9c2fa4d")
    public void visitAcceptCallAction(org.eclipse.uml2.uml.AcceptCallAction inputAcceptCallAction) {
        EAcceptCallAction element = new EAcceptCallAction(inputAcceptCallAction);
        this.importModel.importXMI(element);
    }

    @objid ("1d3a22d9-e7a8-4a6c-bbcd-3fe304ffc1ad")
    public void visitAcceptEventAction(org.eclipse.uml2.uml.AcceptEventAction inputAcceptEventAction) {
        EAcceptEventAction element = new EAcceptEventAction(
                inputAcceptEventAction);
        this.importModel.importXMI(element);
    }

    @objid ("16d838b7-eae5-4d3b-91b2-893db99a44bf")
    public void visitActionExecutionSpecification(org.eclipse.uml2.uml.ActionExecutionSpecification inputActionExecutionSpecification) {
        EActionExecutionSpecification element = new EActionExecutionSpecification(
                inputActionExecutionSpecification);
        this.importModel.importXMI(element);
    }

    @objid ("c363af7f-c8a8-46b9-a72e-769434f15e33")
    public void visitActionInputPin(org.eclipse.uml2.uml.ActionInputPin inputActionInputPin) {
        EActionInputPin element = new EActionInputPin(inputActionInputPin);
        this.importModel.importXMI(element);
    }

    @objid ("5709a9b8-e819-4c06-9d37-67550ec7d7b6")
    public void visitActivity(org.eclipse.uml2.uml.Activity inputActivity) {
        EActivity element = new EActivity(inputActivity);
        this.importModel.importXMI(element);
    }

    @objid ("9ec2ebde-79c3-435c-81b3-5106f49a61c0")
    public void visitActivityFinalNode(org.eclipse.uml2.uml.ActivityFinalNode inputActivityFinalNode) {
        EActivityFinalNode element = new EActivityFinalNode(
                inputActivityFinalNode);
        this.importModel.importXMI(element);
    }

    @objid ("b1fce56c-60f8-4f6b-a065-e1265800fefc")
    public void visitActivityParameterNode(org.eclipse.uml2.uml.ActivityParameterNode inputActivityParameterNode) {
        EActivityParameterNode element = new EActivityParameterNode(
                inputActivityParameterNode);
        this.importModel.importXMI(element);
    }

    @objid ("87bee383-76f5-4bab-8978-3ed6cc9b644d")
    public void visitActivityPartition(org.eclipse.uml2.uml.ActivityPartition inputActivityPartition) {
        EActivityPartition element = new EActivityPartition(
                inputActivityPartition);
        this.importModel.importXMI(element);
    }

    @objid ("686183a9-9e8e-47d8-b631-5ca566797560")
    public void visitActor(org.eclipse.uml2.uml.Actor inputActor) {
        EActor element = new EActor(inputActor);
        this.importModel.importXMI(element);
    }

    @objid ("0519716e-5527-498f-a0f0-3f2bb4abf337")
    public void visitAddStructuralFeatureValueAction(org.eclipse.uml2.uml.AddStructuralFeatureValueAction inputAddStructuralFeatureValueAction) {
        EAddStructuralFeatureValueAction element = new EAddStructuralFeatureValueAction(
                inputAddStructuralFeatureValueAction);
        this.importModel.importXMI(element);
    }

    @objid ("a56b432b-6df3-4c57-948d-7b66fb0453b6")
    public void visitAddVariableValueAction(org.eclipse.uml2.uml.AddVariableValueAction inputAddVariableValueAction) {
        EAddVariableValueAction element = new EAddVariableValueAction(
                inputAddVariableValueAction);
        this.importModel.importXMI(element);
    }

    @objid ("9f55012c-4047-4710-90d9-79b35e686aea")
    public void visitAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent inputAnyReceiveEvent) {
        EAnyReceiveEvent element = new EAnyReceiveEvent(inputAnyReceiveEvent);
        this.importModel.importXMI(element);
    }

    @objid ("7a90da93-ed26-4495-adbe-b451b035fd6d")
    public void visitArtifact(org.eclipse.uml2.uml.Artifact inputArtifact) {
        EArtifact element = new EArtifact(inputArtifact);
        this.importModel.importXMI(element);
    }

    @objid ("3072b8d9-32be-4524-ae05-2d7a4d1810e7")
    public void visitAssociation(org.eclipse.uml2.uml.Association inputAssociation) {
        EAssociation element = new EAssociation(inputAssociation);
        this.importModel.importXMI(element);
    }

    @objid ("65bf84ac-2ce4-46f6-9a08-39430f6210b0")
    public void visitAssociationClass(org.eclipse.uml2.uml.AssociationClass inputAssociationClass) {
        EAssociationClass element = new EAssociationClass(inputAssociationClass);
        this.importModel.importXMI(element);
    }

    @objid ("448cad52-e423-4bc6-90f8-260284d7952f")
    public void visitBehaviorExecutionSpecification(org.eclipse.uml2.uml.BehaviorExecutionSpecification inputBehaviorExecutionSpecification) {
        EBehaviorExecutionSpecification element = new EBehaviorExecutionSpecification(
                inputBehaviorExecutionSpecification);
        this.importModel.importXMI(element);
    }

    @objid ("f1e43afd-495c-4a60-98fe-416655299734")
    public void visitBroadcastSignalAction(org.eclipse.uml2.uml.BroadcastSignalAction inputBroadcastSignalAction) {
        EBroadcastSignalAction element = new EBroadcastSignalAction(
                inputBroadcastSignalAction);
        this.importModel.importXMI(element);
    }

    @objid ("4a3decb9-794f-4d1f-9c7f-83282f89fd3a")
    public void visitCallBehaviorAction(org.eclipse.uml2.uml.CallBehaviorAction inputCallBehaviorAction) {
        ECallBehaviorAction element = new ECallBehaviorAction(
                inputCallBehaviorAction);
        this.importModel.importXMI(element);
    }

    @objid ("0d610218-a67b-40aa-a350-dfbc6b41fcf6")
    public void visitCallEvent(org.eclipse.uml2.uml.CallEvent inputCallEvent) {
        ECallEvent element = new ECallEvent(inputCallEvent);
        this.importModel.importXMI(element);
    }

    @objid ("49400947-de09-41ea-b104-627ea1c87640")
    public void visitCallOperationAction(org.eclipse.uml2.uml.CallOperationAction inputCallOperationAction) {
        ECallOperationAction element = new ECallOperationAction(
                inputCallOperationAction);
        this.importModel.importXMI(element);
    }

    @objid ("693e654b-34eb-4963-bc8e-0999f293f083")
    public void visitCentralBufferNode(org.eclipse.uml2.uml.CentralBufferNode inputCentralBufferNode) {
        ECentralBufferNode element = new ECentralBufferNode(
                inputCentralBufferNode);
        this.importModel.importXMI(element);
    }

    @objid ("88e15e05-fb62-45b0-81f5-3ce591f77fd5")
    public void visitChangeEvent(org.eclipse.uml2.uml.ChangeEvent inputChangeEvent) {
        EChangeEvent element = new EChangeEvent(inputChangeEvent);
        this.importModel.importXMI(element);
    }

    @objid ("d8ec46e7-edf7-4fa9-9169-7021439b96dd")
    public void visitClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter inputClassifierTemplateParameter) {
        EClassifierTemplateParameter element = new EClassifierTemplateParameter(
                inputClassifierTemplateParameter);
        this.importModel.importXMI(element);
    }

    @objid ("51643bb2-8463-41f5-abc6-bfdbbcbec620")
    public void visitClause(org.eclipse.uml2.uml.Clause inputClause) {
        EClause element = new EClause(inputClause);
        this.importModel.importXMI(element);
    }

    @objid ("529fd2f0-5231-4789-a8b6-e9910573fe86")
    public void visitClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction inputClearAssociationAction) {
        EClearAssociationAction element = new EClearAssociationAction(
                inputClearAssociationAction);
        this.importModel.importXMI(element);
    }

    @objid ("c3cddb9b-10fc-41e1-8232-15a467e65d83")
    public void visitClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction inputClearStructuralFeatureAction) {
        EClearStructuralFeatureAction element = new EClearStructuralFeatureAction(
                inputClearStructuralFeatureAction);
        this.importModel.importXMI(element);
    }

    @objid ("8fee3a35-b12a-4524-8cfb-32550c3a58c1")
    public void visitClearVariableAction(org.eclipse.uml2.uml.ClearVariableAction inputClearVariableAction) {
        EClearVariableAction element = new EClearVariableAction(
                inputClearVariableAction);
        this.importModel.importXMI(element);
    }

    @objid ("dbf5a667-cdf3-49fe-a794-b21aba475033")
    public void visitCollaboration(org.eclipse.uml2.uml.Collaboration inputCollaboration) {
        ECollaboration element = new ECollaboration(inputCollaboration);
        this.importModel.importXMI(element);
    }

    @objid ("1c5917b6-dece-4bf2-818d-bbd8b493646e")
    public void visitCollaborationUse(org.eclipse.uml2.uml.CollaborationUse inputCollaborationUse) {
        ECollaborationUse element = new ECollaborationUse(inputCollaborationUse);
        this.importModel.importXMI(element);
    }

    @objid ("74f54cdf-36d3-4011-9099-f81ea24c0a1a")
    public void visitCombinedFragment(org.eclipse.uml2.uml.CombinedFragment inputCombinedFragment) {
        ECombinedFragment element = new ECombinedFragment(inputCombinedFragment);
        this.importModel.importXMI(element);
    }

    @objid ("8bf0b9fb-b786-401e-aa2e-2fcf06e664cd")
    public void visitComment(org.eclipse.uml2.uml.Comment inputComment) {
        EComment element = new EComment(inputComment);
        this.importModel.importXMI(element);
    }

    @objid ("abb81e33-a0ec-4eab-82ab-2b0e7e38d3f7")
    public void visitCommunicationPath(org.eclipse.uml2.uml.CommunicationPath inputCommunicationPath) {
        ECommunicationPath element = new ECommunicationPath(
                inputCommunicationPath);
        this.importModel.importXMI(element);
    }

    @objid ("a92c3b0e-9c8c-4de4-955e-60e2cbbd47a3")
    public void visitComponent(org.eclipse.uml2.uml.Component inputComponent) {
        EComponent element = new EComponent(inputComponent);
        this.importModel.importXMI(element);
    }

    @objid ("58a3d49d-0db1-415b-883f-3b085a964ee4")
    public void visitComponentRealization(org.eclipse.uml2.uml.ComponentRealization inputComponentRealization) {
        EComponentRealization element = new EComponentRealization(
                inputComponentRealization);
        this.importModel.importXMI(element);
    }

    @objid ("9399dd52-6d01-41c7-8646-f6ec25295af9")
    public void visitConditionalNode(org.eclipse.uml2.uml.ConditionalNode inputConditionalNode) {
        EConditionalNode element = new EConditionalNode(inputConditionalNode);
        this.importModel.importXMI(element);
    }

    @objid ("66ed98a0-9d6a-4897-a7c9-e14ba1e78649")
    public void visitConnectableElementTemplateParameter(org.eclipse.uml2.uml.ConnectableElementTemplateParameter inputConnectableElementTemplateParameter) {
        EConnectableElementTemplateParameter element = new EConnectableElementTemplateParameter(
                inputConnectableElementTemplateParameter);
        this.importModel.importXMI(element);
    }

    @objid ("7f153389-d48a-4dee-a154-9ff91f8fe697")
    public void visitConnectionPointReference(org.eclipse.uml2.uml.ConnectionPointReference inputConnectionPointReference) {
        EConnectionPointReference element = new EConnectionPointReference(
                inputConnectionPointReference);
        this.importModel.importXMI(element);
    }

    @objid ("d1e9196b-92c4-4f80-9e5e-83ea8b0c43f6")
    public void visitConnector(org.eclipse.uml2.uml.Connector inputConnector) {
        EConnector element = new EConnector(inputConnector);
        this.importModel.importXMI(element);
    }

    @objid ("7159ac78-2f59-4f69-9ea0-8742f61495a9")
    public void visitConnectorEnd(org.eclipse.uml2.uml.ConnectorEnd inputConnectorEnd) {
        EConnectorEnd element = new EConnectorEnd(inputConnectorEnd);
        this.importModel.importXMI(element);
    }

    @objid ("2918161e-554d-4fe6-af59-bef92f6efd06")
    public void visitConsiderIgnoreFragment(org.eclipse.uml2.uml.ConsiderIgnoreFragment inputConsiderIgnoreFragment) {
        EConsiderIgnoreFragment element = new EConsiderIgnoreFragment(
                inputConsiderIgnoreFragment);
        this.importModel.importXMI(element);
    }

    @objid ("1f234d01-8268-441f-8d85-f55751ec2c51")
    public void visitConstraint(org.eclipse.uml2.uml.Constraint inputConstraint) {
        EConstraint element = new EConstraint(inputConstraint);
        this.importModel.importXMI(element);
    }

    @objid ("b4a282a4-3af5-4738-97fc-45306f83eda5")
    public void visitContinuation(org.eclipse.uml2.uml.Continuation inputContinuation) {
        EContinuation element = new EContinuation(inputContinuation);
        this.importModel.importXMI(element);
    }

    @objid ("eeab76ad-8ea8-415a-8fea-9d8f656869c5")
    public void visitControlFlow(org.eclipse.uml2.uml.ControlFlow inputControlFlow) {
        EControlFlow element = new EControlFlow(inputControlFlow);
        this.importModel.importXMI(element);
    }

    @objid ("a454cce2-9860-426f-ba77-864f9cfbc6d4")
    public void visitCreateLinkAction(org.eclipse.uml2.uml.CreateLinkAction inputCreateLinkAction) {
        ECreateLinkAction element = new ECreateLinkAction(inputCreateLinkAction);
        this.importModel.importXMI(element);
    }

    @objid ("cb47fa5a-9243-4136-b055-dbd04d6a02cd")
    public void visitCreateLinkObjectAction(org.eclipse.uml2.uml.CreateLinkObjectAction inputCreateLinkObjectAction) {
        ECreateLinkObjectAction element = new ECreateLinkObjectAction(
                inputCreateLinkObjectAction);
        this.importModel.importXMI(element);
    }

    @objid ("8af84f02-cc74-4510-824e-be96e3ec70a0")
    public void visitCreateObjectAction(org.eclipse.uml2.uml.CreateObjectAction inputCreateObjectAction) {
        ECreateObjectAction element = new ECreateObjectAction(
                inputCreateObjectAction);
        this.importModel.importXMI(element);
    }

    @objid ("cf8af8ca-dd97-4fa8-a07e-123b1e08e518")
    public void visitCreationEvent(org.eclipse.uml2.uml.CreationEvent inputCreationEvent) {
        ECreationEvent element = new ECreationEvent(inputCreationEvent);
        this.importModel.importXMI(element);
    }

    @objid ("c71f92b4-7f6d-433d-a0d1-6692e8cdafae")
    public void visitDataStoreNode(org.eclipse.uml2.uml.DataStoreNode inputDataStoreNode) {
        EDataStoreNode element = new EDataStoreNode(inputDataStoreNode);
        this.importModel.importXMI(element);
    }

    @objid ("1b51aaca-357e-4c1a-a2db-a1dbbed06412")
    public void visitDataType(org.eclipse.uml2.uml.DataType inputDataType) {
        EDataType element = new EDataType(inputDataType);
        this.importModel.importXMI(element);
    }

    @objid ("5b842b08-9191-42a2-b77f-cf139c97be4d")
    public void visitDecisionNode(org.eclipse.uml2.uml.DecisionNode inputDecisionNode) {
        EDecisionNode element = new EDecisionNode(inputDecisionNode);
        this.importModel.importXMI(element);
    }

    @objid ("75e961e4-be7c-493e-a778-7ec9f4664f6a")
    public void visitDependency(org.eclipse.uml2.uml.Dependency inputDependency) {
        EDependency element = new EDependency(inputDependency);
        this.importModel.importXMI(element);
    }

    @objid ("ab9ff244-7e08-4394-b6b1-648305deb22c")
    public void visitDeployment(org.eclipse.uml2.uml.Deployment inputDeployment) {
        EDeployment element = new EDeployment(inputDeployment);
        this.importModel.importXMI(element);
    }

    @objid ("11683e11-6ba0-4684-8e35-e67855e34f19")
    public void visitDeploymentSpecification(org.eclipse.uml2.uml.DeploymentSpecification inputDeploymentSpecification) {
        EDeploymentSpecification element = new EDeploymentSpecification(
                inputDeploymentSpecification);
        this.importModel.importXMI(element);
    }

    @objid ("c7b94c25-dea7-4baa-adb6-d0c6c3ac04d8")
    public void visitDestroyLinkAction(org.eclipse.uml2.uml.DestroyLinkAction inputDestroyLinkAction) {
        EDestroyLinkAction element = new EDestroyLinkAction(
                inputDestroyLinkAction);
        this.importModel.importXMI(element);
    }

    @objid ("f734e1aa-e143-49df-9882-af6638825dcd")
    public void visitDestroyObjectAction(org.eclipse.uml2.uml.DestroyObjectAction inputDestroyObjectAction) {
        EDestroyObjectAction element = new EDestroyObjectAction(
                inputDestroyObjectAction);
        this.importModel.importXMI(element);
    }

    @objid ("65a4a435-196c-43f1-a9a1-e90825e4808e")
    public void visitDestructionEvent(org.eclipse.uml2.uml.DestructionEvent inputDestructionEvent) {
        EDestructionEvent element = new EDestructionEvent(inputDestructionEvent);
        this.importModel.importXMI(element);
    }

    @objid ("58ea8574-1ac0-48ed-a373-b3cd7210cae7")
    public void visitDevice(org.eclipse.uml2.uml.Device inputDevice) {
        EDevice element = new EDevice(inputDevice);
        this.importModel.importXMI(element);
    }

    @objid ("f7054a40-4085-42ea-8e63-1020124aac0d")
    public void visitDuration(org.eclipse.uml2.uml.Duration inputDuration) {
        EDuration element = new EDuration(inputDuration);
        this.importModel.importXMI(element);
    }

    @objid ("96fb8da6-63a0-4300-8d9c-d2b5de8e8599")
    public void visitDurationConstraint(org.eclipse.uml2.uml.DurationConstraint inputDurationConstraint) {
        EDurationConstraint element = new EDurationConstraint(
                inputDurationConstraint);
        this.importModel.importXMI(element);
    }

    @objid ("28dc9d3c-b3d0-40eb-ae81-2c4c1047c0ad")
    public void visitDurationInterval(org.eclipse.uml2.uml.DurationInterval inputDurationInterval) {
        EDurationInterval element = new EDurationInterval(inputDurationInterval);
        this.importModel.importXMI(element);
    }

    @objid ("520d1cb9-df16-4627-a2a8-75caacf1c60b")
    public void visitDurationObservation(final DurationObservation inputDurationIservation) {
        EDurationObservation element = new EDurationObservation(
                inputDurationIservation);
        this.importModel.importXMI(element);
    }

    @objid ("0294f855-2a42-4261-b512-4201b23c86d4")
    public void visitElementImport(org.eclipse.uml2.uml.ElementImport inputElementImport) {
        EElementImport element = new EElementImport(inputElementImport);
        this.importModel.importXMI(element);
    }

    @objid ("6642c5da-f7f6-46ab-b352-0e2482aa59dc")
    public void visitEnumeration(org.eclipse.uml2.uml.Enumeration inputEnumeration) {
        EEnumeration element = new EEnumeration(inputEnumeration);
        this.importModel.importXMI(element);
    }

    @objid ("902b62e9-fd9e-4bdf-b992-ac985ead3642")
    public void visitEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral inputEnumerationLiteral) {
        EEnumerationLiteral element = new EEnumerationLiteral(
                inputEnumerationLiteral);
        this.importModel.importXMI(element);
    }

    @objid ("41846584-b7ca-4486-a8c9-ab02e952e751")
    public void visitExceptionHandler(ExceptionHandler inputExceptionHandler) {
        EExceptionHandler element = new EExceptionHandler(inputExceptionHandler);
        this.importModel.importXMI(element);
    }

    @objid ("cfdaf040-baeb-423f-a6b6-5081906b0c7f")
    public void visitExecutionEnvironment(org.eclipse.uml2.uml.ExecutionEnvironment inputExecutionEnvironment) {
        EExecutionEnvironment element = new EExecutionEnvironment(
                inputExecutionEnvironment);
        this.importModel.importXMI(element);
    }

    @objid ("31b3726f-1cd5-4ef3-b1e7-c9978a0c2a6b")
    public void visitExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent inputExecutionEvent) {
        EExecutionEvent element = new EExecutionEvent(inputExecutionEvent);
        this.importModel.importXMI(element);
    }

    @objid ("d1e3bd8e-5339-4a8a-89ae-6aacbee99fa3")
    public void visitExecutionOccurrenceSpecification(org.eclipse.uml2.uml.ExecutionOccurrenceSpecification inputExecutionOccurrenceSpecification) {
        EExecutionOccurrenceSpecification element = new EExecutionOccurrenceSpecification(
                inputExecutionOccurrenceSpecification);
        this.importModel.importXMI(element);
    }

    @objid ("8dad5521-f442-433f-8ccc-f721d74b8961")
    public void visitExpansionNode(org.eclipse.uml2.uml.ExpansionNode inputExpansionNode) {
        EExpansionNode element = new EExpansionNode(inputExpansionNode);
        this.importModel.importXMI(element);
    }

    @objid ("c775e72e-bb27-4717-93ad-bc7a23a9cb5e")
    public void visitExpansionRegion(org.eclipse.uml2.uml.ExpansionRegion inputExpansionRegion) {
        EExpansionRegion element = new EExpansionRegion(inputExpansionRegion);
        this.importModel.importXMI(element);
    }

    @objid ("38814638-c89d-497f-93ab-5ad96351dfcd")
    public void visitExpression(org.eclipse.uml2.uml.Expression inputExpression) {
        EExpression element = new EExpression(inputExpression);
        this.importModel.importXMI(element);
    }

    @objid ("67259a58-ffdc-4bbc-a356-8e54b85fa92a")
    public void visitExtend(org.eclipse.uml2.uml.Extend inputExtend) {
        EExtend element = new EExtend(inputExtend);
        this.importModel.importXMI(element);
    }

    @objid ("cbaf5621-96ef-4ae1-beb9-341872bd98d4")
    public void visitExtension(org.eclipse.uml2.uml.Extension inputExtension) {
        EExtension element = new EExtension(inputExtension);
        this.importModel.importXMI(element);
    }

    @objid ("468013c9-6e18-4928-8fa9-d6f62de810bc")
    public void visitExtensionEnd(org.eclipse.uml2.uml.ExtensionEnd inputExtensionEnd) {
        EExtensionEnd element = new EExtensionEnd(inputExtensionEnd);
        this.importModel.importXMI(element);
    }

    @objid ("b083746d-f15a-4678-a1ae-27c8e35f4310")
    public void visitExtensionPoint(org.eclipse.uml2.uml.ExtensionPoint inputExtensionPoint) {
        EExtensionPoint element = new EExtensionPoint(inputExtensionPoint);
        this.importModel.importXMI(element);
    }

    @objid ("d24f33db-3d61-4595-9cb9-7a863be970bf")
    public void visitFinalState(FinalState inputFinalState) {
        EFinalState element = new EFinalState(inputFinalState);
        this.importModel.importXMI(element);
    }

    @objid ("c50e8534-dbf4-4db7-9c24-d68cd0d61e22")
    public void visitFlowFinalNode(org.eclipse.uml2.uml.FlowFinalNode inputFlowFinalNode) {
        EFlowFinalNode element = new EFlowFinalNode(inputFlowFinalNode);
        this.importModel.importXMI(element);
    }

    @objid ("468b5ce9-9302-46cc-ac76-18e4eb5e90e5")
    public void visitForkNode(org.eclipse.uml2.uml.ForkNode inputForkNode) {
        EForkNode element = new EForkNode(inputForkNode);
        this.importModel.importXMI(element);
    }

    @objid ("6ae65b93-c291-4ead-ad6d-56f1ab2179df")
    public void visitFunctionBehavior(org.eclipse.uml2.uml.FunctionBehavior inputFunctionBehavior) {
        EFunctionBehavior element = new EFunctionBehavior(inputFunctionBehavior);
        this.importModel.importXMI(element);
    }

    @objid ("9a6b9d9a-9cde-4fbd-8140-01797ce0717b")
    public void visitGate(org.eclipse.uml2.uml.Gate inputGate) {
        EGate element = new EGate(inputGate);
        this.importModel.importXMI(element);
    }

    @objid ("3da662c0-42be-40e1-b690-c1927875d8b5")
    public void visitGeneralization(org.eclipse.uml2.uml.Generalization inputGeneralization) {
        EGeneralization element = new EGeneralization(inputGeneralization);
        this.importModel.importXMI(element);
    }

    @objid ("e019eb9e-b302-4fc9-8b4b-4a2b7efb7f68")
    public void visitGeneralizationSet(org.eclipse.uml2.uml.GeneralizationSet inputGeneralizationSet) {
        EGeneralizationSet element = new EGeneralizationSet(
                inputGeneralizationSet);
        this.importModel.importXMI(element);
    }

    @objid ("e4311b21-d3a4-4bf9-950b-af5d9f5f4a4b")
    public void visitGeneralOrdering(org.eclipse.uml2.uml.GeneralOrdering inputGeneralOrdering) {
        EGeneralOrdering element = new EGeneralOrdering(inputGeneralOrdering);
        this.importModel.importXMI(element);
    }

    @objid ("fb008e18-8c62-4790-a33a-cffdc40da9c3")
    public void visitImage(org.eclipse.uml2.uml.Image inputImage) {
        EImage element = new EImage(inputImage);
        this.importModel.importXMI(element);
    }

    @objid ("f4bb44ef-f37a-4cde-b71e-7bfc5348bceb")
    public void visitInclude(org.eclipse.uml2.uml.Include inputInclude) {
        EInclude element = new EInclude(inputInclude);
        this.importModel.importXMI(element);
    }

    @objid ("59dadc70-7644-4679-abd8-5543a2a65301")
    public void visitInformationFlow(org.eclipse.uml2.uml.InformationFlow inputInformationFlow) {
        EInformationFlow element = new EInformationFlow(inputInformationFlow);
        this.importModel.importXMI(element);
    }

    @objid ("e765bd80-a08c-4631-a8ea-a9feef0c21e9")
    public void visitInformationItem(org.eclipse.uml2.uml.InformationItem inputInformationItem) {
        EInformationItem element = new EInformationItem(inputInformationItem);
        this.importModel.importXMI(element);
    }

    @objid ("8774a015-0603-458f-811c-ecd9efb659ec")
    public void visitInitialNode(org.eclipse.uml2.uml.InitialNode inputInitialNode) {
        EInitialNode element = new EInitialNode(inputInitialNode);
        this.importModel.importXMI(element);
    }

    @objid ("38778a24-0c84-4d0d-884a-22d8a8024e9e")
    public void visitInputPin(org.eclipse.uml2.uml.InputPin inputInputPin) {
        EInputPin element = new EInputPin(inputInputPin);
        this.importModel.importXMI(element);
    }

    @objid ("feba4bc4-88eb-4357-a025-74bf76d69d32")
    public void visitInstanceSpecification(InstanceSpecification inputInstanceSpecification) {
        EInstanceSpecification element = new EInstanceSpecification(
                inputInstanceSpecification);
        this.importModel.importXMI(element);
    }

    @objid ("a4405f84-05f3-4281-b12f-b5ce7f006c31")
    public void visitInstanceValue(InstanceValue inputInstanceValue) {
        EInstanceValue element = new EInstanceValue(inputInstanceValue);
        this.importModel.importXMI(element);
    }

    @objid ("2ae00c99-0c50-4a10-9f99-00ef9ddf63f0")
    public void visitInteraction(org.eclipse.uml2.uml.Interaction inputInteraction) {
        EInteraction element = new EInteraction(inputInteraction);
        this.importModel.importXMI(element);
    }

    @objid ("722b6157-ac69-44ed-80b0-c1f6d264a380")
    public void visitInteractionConstraint(org.eclipse.uml2.uml.InteractionConstraint inputInteractionConstraint) {
        EInteractionConstraint element = new EInteractionConstraint(
                inputInteractionConstraint);
        this.importModel.importXMI(element);
    }

    @objid ("beab279c-3728-4f83-b31c-0ba3f4f5ce49")
    public void visitInteractionOperand(org.eclipse.uml2.uml.InteractionOperand inputInteractionOperand) {
        EInteractionOperand element = new EInteractionOperand(
                inputInteractionOperand);
        this.importModel.importXMI(element);
    }

    @objid ("05d92b52-1299-4527-832c-10bbf68a1c9f")
    public void visitInteractionUse(org.eclipse.uml2.uml.InteractionUse inputInteractionUse) {
        EInteractionUse element = new EInteractionUse(inputInteractionUse);
        this.importModel.importXMI(element);
    }

    @objid ("d57bc554-b63e-4c30-b789-c26a3794268c")
    public void visitInterface(org.eclipse.uml2.uml.Interface inputInterface) {
        EInterface element = new EInterface(inputInterface);
        this.importModel.importXMI(element);
    }

    @objid ("eaea38d0-515f-4ba4-80fa-2052a7b192f5")
    public void visitInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization inputInterfaceRealization) {
        EInterfaceRealization element = new EInterfaceRealization(
                inputInterfaceRealization);
        this.importModel.importXMI(element);
    }

    @objid ("95dbe421-b295-4f00-aa83-f09ba1915b2c")
    public void visitInterruptibleActivityRegion(org.eclipse.uml2.uml.InterruptibleActivityRegion inputInterruptibleActivityRegion) {
        EInterruptibleActivityRegion element = new EInterruptibleActivityRegion(
                inputInterruptibleActivityRegion);
        this.importModel.importXMI(element);
    }

    @objid ("f16ac6f8-4a40-400e-99c6-b0f5783d1f61")
    public void visitInterval(org.eclipse.uml2.uml.Interval inputInterval) {
        EInterval element = new EInterval(inputInterval);
        this.importModel.importXMI(element);
    }

    @objid ("1bda02a2-34de-4dfb-bfe0-23539263628a")
    public void visitIntervalConstraint(org.eclipse.uml2.uml.IntervalConstraint inputIntervalConstraint) {
        EIntervalConstraint element = new EIntervalConstraint(
                inputIntervalConstraint);
        this.importModel.importXMI(element);
    }

    @objid ("4579fb3b-81b7-47ef-97a9-368fdce2932a")
    public void visitJoinNode(org.eclipse.uml2.uml.JoinNode inputJoinNode) {
        EJoinNode element = new EJoinNode(inputJoinNode);
        this.importModel.importXMI(element);
    }

    @objid ("fe1c7e60-b64c-4bd3-ba92-bada2acc7554")
    public void visitLifeline(org.eclipse.uml2.uml.Lifeline inputLifeline) {
        ELifeline element = new ELifeline(inputLifeline);
        this.importModel.importXMI(element);
    }

    @objid ("6307ae44-481d-47c6-9bca-283d0f23f12c")
    public void visitLinkEndCreationData(org.eclipse.uml2.uml.LinkEndCreationData inputLinkEndCreationData) {
        ELinkEndCreationData element = new ELinkEndCreationData(
                inputLinkEndCreationData);
        this.importModel.importXMI(element);
    }

    @objid ("89c81cfa-9d1b-40a4-b59f-e7461b388a26")
    public void visitLinkEndData(org.eclipse.uml2.uml.LinkEndData inputLinkEndData) {
        ELinkEndData element = new ELinkEndData(inputLinkEndData);
        this.importModel.importXMI(element);
    }

    @objid ("aaba5c97-1c57-4280-a85a-caea136b1dfd")
    public void visitLinkEndDestructionData(org.eclipse.uml2.uml.LinkEndDestructionData inputLinkEndDestructionData) {
        ELinkEndDestructionData element = new ELinkEndDestructionData(
                inputLinkEndDestructionData);
        this.importModel.importXMI(element);
    }

    @objid ("82a53dfc-0a25-4227-b104-0939ce7a8aa5")
    public void visitLiteralBoolean(org.eclipse.uml2.uml.LiteralBoolean inputLiteralBoolean) {
        ELiteralBoolean element = new ELiteralBoolean(inputLiteralBoolean);
        this.importModel.importXMI(element);
    }

    @objid ("0f873dfc-8c3b-4fd8-9866-139527237426")
    public void visitLiteralInteger(org.eclipse.uml2.uml.LiteralInteger inputLiteralInteger) {
        ELiteralInteger element = new ELiteralInteger(inputLiteralInteger);
        this.importModel.importXMI(element);
    }

    @objid ("afec5cc0-b819-4309-b282-983c9b434338")
    public void visitLiteralNull(org.eclipse.uml2.uml.LiteralNull inputLiteralNull) {
        ELiteralNull element = new ELiteralNull(inputLiteralNull);
        this.importModel.importXMI(element);
    }

    @objid ("f5a6bb07-827a-4c70-a755-89d92d76b013")
    public void visitLiteralString(org.eclipse.uml2.uml.LiteralString inputLiteralString) {
        ELiteralString element = new ELiteralString(inputLiteralString);
        this.importModel.importXMI(element);
    }

    @objid ("f3790123-1c8d-4c60-ae10-0bd5a035e60c")
    public void visitLiteralUnlimitedNatural(org.eclipse.uml2.uml.LiteralUnlimitedNatural inputLiteralUnlimitedNatural) {
        ELiteralUnlimitedNatural element = new ELiteralUnlimitedNatural(
                inputLiteralUnlimitedNatural);
        this.importModel.importXMI(element);
    }

    @objid ("3b75f2e9-7843-4f8e-9504-b2c79bb75912")
    public void visitLoopNode(org.eclipse.uml2.uml.LoopNode inputLoopNode) {
        ELoopNode element = new ELoopNode(inputLoopNode);
        this.importModel.importXMI(element);
    }

    @objid ("6a1a2e70-601e-427d-9c2d-73ae418440a3")
    public void visitManifestation(org.eclipse.uml2.uml.Manifestation inputManifestation) {
        EManifestation element = new EManifestation(inputManifestation);
        this.importModel.importXMI(element);
    }

    @objid ("312ede2f-6477-4443-8f58-845d2c706f9b")
    public void visitMergeNode(org.eclipse.uml2.uml.MergeNode inputMergeNode) {
        EMergeNode element = new EMergeNode(inputMergeNode);
        this.importModel.importXMI(element);
    }

    @objid ("ecebaeb1-2262-40f8-9283-d0709a34b58f")
    public void visitMessage(org.eclipse.uml2.uml.Message inputMessage) {
        EMessage element = new EMessage(inputMessage);
        this.importModel.importXMI(element);
    }

    @objid ("a8b58de5-82b2-4ddc-b23c-086e3fe4ec26")
    public void visitMessageOccurrenceSpecification(org.eclipse.uml2.uml.MessageOccurrenceSpecification inputMessageOccurrenceSpecification) {
        EMessageOccurrenceSpecification element = new EMessageOccurrenceSpecification(
                inputMessageOccurrenceSpecification);
        this.importModel.importXMI(element);
    }

    @objid ("48bb6bd6-e120-4673-9703-ad4bf25f393e")
    public void visitModel(org.eclipse.uml2.uml.Model inputModel) {
        EModel element = new EModel(inputModel);
        this.importModel.importXMI(element);
    }

    @objid ("fa5ab266-f507-4b27-8e26-c79eed32b8e6")
    public void visitNode(org.eclipse.uml2.uml.Node inputNode) {
        ENode element = new ENode(inputNode);
        this.importModel.importXMI(element);
    }

    @objid ("e8836472-9e72-4b0e-9c78-3691c465fcc0")
    public void visitObjectFlow(ObjectFlow inputObjectFlow) {
        EObjectFlow element = new EObjectFlow(inputObjectFlow);
        this.importModel.importXMI(element);
    }

    @objid ("485fca5e-6f13-4974-88da-f7c630a7c293")
    public void visitOccurrenceSpecification(org.eclipse.uml2.uml.OccurrenceSpecification inputOccurrenceSpecification) {
        EOccurrenceSpecification element = new EOccurrenceSpecification(
                inputOccurrenceSpecification);
        this.importModel.importXMI(element);
    }

    @objid ("85c606df-14cb-415b-a70c-c5666405da14")
    public void visitOpaqueAction(org.eclipse.uml2.uml.OpaqueAction inputOpaqueAction) {
        EOpaqueAction element = new EOpaqueAction(inputOpaqueAction);
        this.importModel.importXMI(element);
    }

    @objid ("8f603afd-d4ae-47c0-a97e-dbd814ade822")
    public void visitOpaqueBehavior(org.eclipse.uml2.uml.OpaqueBehavior inputOpaqueBehavior) {
        EOpaqueBehavior element = new EOpaqueBehavior(inputOpaqueBehavior);
        this.importModel.importXMI(element);
    }

    @objid ("aab4301e-2980-48fc-9a0e-dc1bd252ed57")
    public void visitOpaqueExpression(org.eclipse.uml2.uml.OpaqueExpression inputOpaqueExpression) {
        EOpaqueExpression element = new EOpaqueExpression(inputOpaqueExpression);
        this.importModel.importXMI(element);
    }

    @objid ("1de321fd-2eb8-45f8-be4c-c3a17f9546bd")
    public void visitOperation(org.eclipse.uml2.uml.Operation inputOperation) {
        EOperation element = new EOperation(inputOperation);
        this.importModel.importXMI(element);
    }

    @objid ("509a0a5f-af11-4766-8dab-58a687fedb9a")
    public void visitOperationTemplateParameter(org.eclipse.uml2.uml.OperationTemplateParameter inputOperationTemplateParameter) {
        EOperationTemplateParameter element = new EOperationTemplateParameter(
                inputOperationTemplateParameter);
        this.importModel.importXMI(element);
    }

    @objid ("24b35c39-c7da-4b0b-9255-68679e091b1e")
    public void visitOutputPin(org.eclipse.uml2.uml.OutputPin inputOutputPin) {
        EOutputPin element = new EOutputPin(inputOutputPin);
        this.importModel.importXMI(element);
    }

    @objid ("8cbd9eec-ed48-4903-ab32-e9af3506396b")
    public void visitPackageImport(org.eclipse.uml2.uml.PackageImport inputPackageImport) {
        EPackageImport element = new EPackageImport(inputPackageImport);
        this.importModel.importXMI(element);
    }

    @objid ("73e9ed32-9d2f-4cfd-9792-a49eaab889ce")
    public void visitPackageMerge(org.eclipse.uml2.uml.PackageMerge inputPackageMerge) {
        EPackageMerge element = new EPackageMerge(inputPackageMerge);
        this.importModel.importXMI(element);
    }

    @objid ("fd5d9860-7ef0-42d4-9533-5f58e3b7ab92")
    public void visitParameter(org.eclipse.uml2.uml.Parameter inputParameter) {
        EParameter element = new EParameter(inputParameter);
        this.importModel.importXMI(element);
    }

    @objid ("99a3b09d-ca62-453e-bb19-a55d70b21ac1")
    public void visitParameterSet(org.eclipse.uml2.uml.ParameterSet inputParameterSet) {
        EParameterSet element = new EParameterSet(inputParameterSet);
        this.importModel.importXMI(element);
    }

    @objid ("47541d70-f3ef-4df3-9cfb-ce7bc656210f")
    public void visitPartDecomposition(org.eclipse.uml2.uml.PartDecomposition inputPartDecomposition) {
        EPartDecomposition element = new EPartDecomposition(
                inputPartDecomposition);
        this.importModel.importXMI(element);
    }

    @objid ("6dc89ab6-9d64-4f50-b5dd-9077094999d0")
    public void visitPin(org.eclipse.uml2.uml.Pin inputPin) {
        EPin element = new EPin(inputPin);
        this.importModel.importXMI(element);
    }

    @objid ("4a38f1a4-7400-4478-8608-d8cfc009586a")
    public void visitPort(org.eclipse.uml2.uml.Port inputPort) {
        EPort element = new EPort(inputPort);
        this.importModel.importXMI(element);
    }

    @objid ("94bf6cef-403d-4fb2-bb19-2e9f499aefae")
    public void visitPrimitiveType(org.eclipse.uml2.uml.PrimitiveType inputPrimitiveType) {
        EPrimitiveType element = new EPrimitiveType(inputPrimitiveType);
        this.importModel.importXMI(element);
    }

    @objid ("742748ab-12ed-48ec-a2f7-0dc4e5b77b7c")
    public void visitProfile(org.eclipse.uml2.uml.Profile inputProfile) {
        EProfile element = new EProfile(inputProfile);
        this.importModel.importXMI(element);
    }

    @objid ("1ae4e6f2-f6ea-4e6b-b692-ed0ee3d8621a")
    public void visitProfileApplication(org.eclipse.uml2.uml.ProfileApplication inputProfileApplication) {
        EProfileApplication element = new EProfileApplication(
                inputProfileApplication);
        this.importModel.importXMI(element);
    }

    @objid ("7c80dd9f-b3d0-4434-9b1b-a56847862efc")
    public void visitProtocolConformance(org.eclipse.uml2.uml.ProtocolConformance inputProtocolConformance) {
        EProtocolConformance element = new EProtocolConformance(
                inputProtocolConformance);
        this.importModel.importXMI(element);
    }

    @objid ("19b52e18-731d-4246-ab85-d229ab364af5")
    public void visitProtocolStateMachine(org.eclipse.uml2.uml.ProtocolStateMachine inputProtocolStateMachine) {
        EProtocolStateMachine element = new EProtocolStateMachine(
                inputProtocolStateMachine);
        this.importModel.importXMI(element);
    }

    @objid ("5da6e016-a989-4435-a695-cd73ea1e0398")
    public void visitProtocolTransition(org.eclipse.uml2.uml.ProtocolTransition inputProtocolTransition) {
        EProtocolTransition element = new EProtocolTransition(
                inputProtocolTransition);
        this.importModel.importXMI(element);
    }

    @objid ("4d58c4a1-7698-4966-8026-62af8835f7b6")
    public void visitPseudostate(org.eclipse.uml2.uml.Pseudostate inputPseudostate) {
        EPseudostate element = new EPseudostate(inputPseudostate);
        this.importModel.importXMI(element);
    }

    @objid ("dbc52058-72f1-4d5a-8a2e-e3ce61e3fe1d")
    public void visitQualifierValue(org.eclipse.uml2.uml.QualifierValue inputQualifierValue) {
        EQualifierValue element = new EQualifierValue(inputQualifierValue);
        this.importModel.importXMI(element);
    }

    @objid ("6eb5c99c-8f6d-44e9-92fd-a9bb86640e4d")
    public void visitRaiseExceptionAction(org.eclipse.uml2.uml.RaiseExceptionAction inputRaiseExceptionAction) {
        ERaiseExceptionAction element = new ERaiseExceptionAction(
                inputRaiseExceptionAction);
        this.importModel.importXMI(element);
    }

    @objid ("8e9f075b-c996-475e-91ab-439b1336db9c")
    public void visitReadExtentAction(org.eclipse.uml2.uml.ReadExtentAction inputReadExtentAction) {
        EReadExtentAction element = new EReadExtentAction(inputReadExtentAction);
        this.importModel.importXMI(element);
    }

    @objid ("776a7aa1-0cc5-42ff-a79c-3b4130f1598b")
    public void visitReadIsClassifiedObjectAction(org.eclipse.uml2.uml.ReadIsClassifiedObjectAction inputReadIsClassifiedObjectAction) {
        EReadIsClassifiedObjectAction element = new EReadIsClassifiedObjectAction(
                inputReadIsClassifiedObjectAction);
        this.importModel.importXMI(element);
    }

    @objid ("c139649e-508d-44bf-8038-20d39cf8186d")
    public void visitReadLinkAction(org.eclipse.uml2.uml.ReadLinkAction inputReadLinkAction) {
        EReadLinkAction element = new EReadLinkAction(inputReadLinkAction);
        this.importModel.importXMI(element);
    }

    @objid ("3c681856-fcda-4cb7-b916-2e58c75f9d4e")
    public void visitReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction inputReadLinkObjectEndAction) {
        EReadLinkObjectEndAction element = new EReadLinkObjectEndAction(
                inputReadLinkObjectEndAction);
        this.importModel.importXMI(element);
    }

    @objid ("f4de6892-27d7-4770-a706-9e19ef8f7e6c")
    public void visitReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction inputReadLinkObjectEndQualifierAction) {
        EReadLinkObjectEndQualifierAction element = new EReadLinkObjectEndQualifierAction(
                inputReadLinkObjectEndQualifierAction);
        this.importModel.importXMI(element);
    }

    @objid ("689127d9-8157-4e77-9657-9685238aaa84")
    public void visitReadSelfAction(org.eclipse.uml2.uml.ReadSelfAction inputReadSelfAction) {
        EReadSelfAction element = new EReadSelfAction(inputReadSelfAction);
        this.importModel.importXMI(element);
    }

    @objid ("f9adc1ee-a79f-4757-ae3f-09494223d0f8")
    public void visitReadStructuralFeatureAction(org.eclipse.uml2.uml.ReadStructuralFeatureAction inputReadStructuralFeatureAction) {
        EReadStructuralFeatureAction element = new EReadStructuralFeatureAction(
                inputReadStructuralFeatureAction);
        this.importModel.importXMI(element);
    }

    @objid ("ee7d4730-cd06-4fdc-9c9e-75eef26f2e9f")
    public void visitReadVariableAction(org.eclipse.uml2.uml.ReadVariableAction inputReadVariableAction) {
        EReadVariableAction element = new EReadVariableAction(
                inputReadVariableAction);
        this.importModel.importXMI(element);
    }

    @objid ("45d67a2d-a387-4441-8f82-fb0aed9af6f7")
    public void visitRealization(org.eclipse.uml2.uml.Realization inputRealization) {
        ERealization element = new ERealization(inputRealization);
        this.importModel.importXMI(element);
    }

    @objid ("78af7dbb-880c-4bd1-904a-7f581c8581ef")
    public void visitReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent inputReceiveOperationEvent) {
        EReceiveOperationEvent element = new EReceiveOperationEvent(
                inputReceiveOperationEvent);
        this.importModel.importXMI(element);
    }

    @objid ("7afe40d4-5b94-4129-85e8-06f4fc5bd3c6")
    public void visitReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent inputReceiveSignalEvent) {
        EReceiveSignalEvent element = new EReceiveSignalEvent(
                inputReceiveSignalEvent);
        this.importModel.importXMI(element);
    }

    @objid ("46a369e0-7e6c-47a3-9e7c-1620d7e6055f")
    public void visitReception(org.eclipse.uml2.uml.Reception inputReception) {
        EReception element = new EReception(inputReception);
        this.importModel.importXMI(element);
    }

    @objid ("0fcae629-faf5-4916-b2cd-51748755cb8b")
    public void visitReclassifyObjectAction(org.eclipse.uml2.uml.ReclassifyObjectAction inputReclassifyObjectAction) {
        EReclassifyObjectAction element = new EReclassifyObjectAction(
                inputReclassifyObjectAction);
        this.importModel.importXMI(element);
    }

    @objid ("078ebe57-47ca-47c0-98a8-e3b15e2cadc7")
    public void visitRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature inputRedefinableTemplateSignature) {
        ERedefinableTemplateSignature element = new ERedefinableTemplateSignature(
                inputRedefinableTemplateSignature);
        this.importModel.importXMI(element);
    }

    @objid ("77bb6a5d-e17d-4765-850f-382a58bb75f2")
    public void visitReduceAction(org.eclipse.uml2.uml.ReduceAction inputReduceAction) {
        EReduceAction element = new EReduceAction(inputReduceAction);
        this.importModel.importXMI(element);
    }

    @objid ("9b934f93-961b-4a98-81a8-7c54c6aa7f77")
    public void visitRegion(org.eclipse.uml2.uml.Region inputRegion) {
        ERegion element = new ERegion(inputRegion);
        this.importModel.importXMI(element);
    }

    @objid ("ab971bca-c70e-4852-80dc-ffe7b1009e6c")
    public void visitRemoveStructuralFeatureValueAction(org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction inputRemoveStructuralFeatureValueAction) {
        ERemoveStructuralFeatureValueAction element = new ERemoveStructuralFeatureValueAction(
                inputRemoveStructuralFeatureValueAction);
        this.importModel.importXMI(element);
    }

    @objid ("f5f1d5ae-506c-4fd1-acad-fd063719252c")
    public void visitRemoveVariableValueAction(org.eclipse.uml2.uml.RemoveVariableValueAction inputRemoveVariableValueAction) {
        ERemoveVariableValueAction element = new ERemoveVariableValueAction(
                inputRemoveVariableValueAction);
        this.importModel.importXMI(element);
    }

    @objid ("0805c120-7a08-44c0-aaef-ebc276749939")
    public void visitReplyAction(org.eclipse.uml2.uml.ReplyAction inputReplyAction) {
        EReplyAction element = new EReplyAction(inputReplyAction);
        this.importModel.importXMI(element);
    }

    @objid ("ed874d0c-134b-48e0-b8cc-692476eaf92c")
    public void visitSendObjectAction(org.eclipse.uml2.uml.SendObjectAction inputSendObjectAction) {
        ESendObjectAction element = new ESendObjectAction(inputSendObjectAction);
        this.importModel.importXMI(element);
    }

    @objid ("76812a41-4e36-42a6-b580-74640d90bff3")
    public void visitSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent inputSendOperationEvent) {
        ESendOperationEvent element = new ESendOperationEvent(
                inputSendOperationEvent);
        this.importModel.importXMI(element);
    }

    @objid ("bdc8fa3d-7a5e-479e-b627-02591ae69d0d")
    public void visitSendSignalAction(org.eclipse.uml2.uml.SendSignalAction inputSendSignalAction) {
        ESendSignalAction element = new ESendSignalAction(inputSendSignalAction);
        this.importModel.importXMI(element);
    }

    @objid ("996795c0-1f6f-4eb1-9586-40d271dbc435")
    public void visitSendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent inputSendSignalEvent) {
        ESendSignalEvent element = new ESendSignalEvent(inputSendSignalEvent);
        this.importModel.importXMI(element);
    }

    @objid ("5f792089-3596-4570-9bf1-d402a5064424")
    public void visitSequenceNode(org.eclipse.uml2.uml.SequenceNode inputSequenceNode) {
        ESequenceNode element = new ESequenceNode(inputSequenceNode);
        this.importModel.importXMI(element);
    }

    @objid ("3a1dd3a9-7e0c-4f8c-af18-46d21227bd53")
    public void visitSignal(org.eclipse.uml2.uml.Signal inputSignal) {
        ESignal element = new ESignal(inputSignal);
        this.importModel.importXMI(element);
    }

    @objid ("79bf3621-bf2b-4f10-8dd4-c3bab5b7ed2a")
    public void visitSignalEvent(org.eclipse.uml2.uml.SignalEvent inputSignalEvent) {
        ESignalEvent element = new ESignalEvent(inputSignalEvent);
        this.importModel.importXMI(element);
    }

    @objid ("942deb4f-2271-4c68-a682-1cf4a555c1bf")
    public void visitSlot(org.eclipse.uml2.uml.Slot inputSlot) {
        ESlot element = new ESlot(inputSlot);
        this.importModel.importXMI(element);
    }

    @objid ("a0371bb0-879c-48d2-8beb-cfc21c4f3ee0")
    public void visitStartClassifierBehaviorAction(org.eclipse.uml2.uml.StartClassifierBehaviorAction inputStartClassifierBehaviorAction) {
        EStartClassifierBehaviorAction element = new EStartClassifierBehaviorAction(
                inputStartClassifierBehaviorAction);
        this.importModel.importXMI(element);
    }

    @objid ("2c9e651d-7eaa-4aaa-b1d4-9f9dff0ec534")
    public void visitState(org.eclipse.uml2.uml.State inputState) {
        EState element = new EState(inputState);
        this.importModel.importXMI(element);
    }

    @objid ("ced6cd39-af48-4b2b-8e93-4d766dc2493f")
    public void visitStateInvariant(org.eclipse.uml2.uml.StateInvariant inputStateInvariant) {
        EStateInvariant element = new EStateInvariant(inputStateInvariant);
        this.importModel.importXMI(element);
    }

    @objid ("06255152-9d41-464a-b628-5c36f42c59bb")
    public void visitStateMachine(org.eclipse.uml2.uml.StateMachine inputStateMachine) {
        EStateMachine element = new EStateMachine(inputStateMachine);
        this.importModel.importXMI(element);
    }

    @objid ("b5dafa15-967e-49d7-b91c-f4e6b5f79274")
    public void visitStringExpression(StringExpression inputStringExpression) {
        EStringExpression element = new EStringExpression(inputStringExpression);
        this.importModel.importXMI(element);
    }

    @objid ("46432e08-7d44-44dd-b251-1e2bb14f6f2d")
    public void visitStructuredActivityNode(org.eclipse.uml2.uml.StructuredActivityNode inputStructuredActivityNode) {
        EStructuredActivityNode element = new EStructuredActivityNode(
                inputStructuredActivityNode);
        this.importModel.importXMI(element);
    }

    @objid ("692d84b6-99bf-4622-a755-234ee6c20ddd")
    public void visitSubstitution(org.eclipse.uml2.uml.Substitution inputSubstitution) {
        ESubstitution element = new ESubstitution(inputSubstitution);
        this.importModel.importXMI(element);
    }

    @objid ("6c3dadf3-b7a2-41f3-9dad-245218e1183d")
    public void visitTemplateBinding(org.eclipse.uml2.uml.TemplateBinding inputTemplateBinding) {
        ETemplateBinding element = new ETemplateBinding(inputTemplateBinding);
        this.importModel.importXMI(element);
    }

    @objid ("3de93591-09d7-4d03-8f2a-69af71a9aadb")
    public void visitTemplateParameter(org.eclipse.uml2.uml.TemplateParameter inputTemplateParameter) {
        ETemplateParameter element = new ETemplateParameter(
                inputTemplateParameter);
        this.importModel.importXMI(element);
    }

    @objid ("f654cd28-2d20-4b52-b4e7-5c644c20bc5f")
    public void visitTemplateParameterSubstitution(org.eclipse.uml2.uml.TemplateParameterSubstitution inputTemplateParameterSubstitution) {
        ETemplateParameterSubstitution element = new ETemplateParameterSubstitution(
                inputTemplateParameterSubstitution);
        this.importModel.importXMI(element);
    }

    @objid ("dd4f8ab1-78e1-4590-9c86-f93ed8ddfde4")
    public void visitTemplateSignature(org.eclipse.uml2.uml.TemplateSignature inputTemplateSignature) {
        ETemplateSignature element = new ETemplateSignature(
                inputTemplateSignature);
        this.importModel.importXMI(element);
    }

    @objid ("e6da3e03-cad5-429d-86d0-0cd2d6d344ac")
    public void visitTestIdentityAction(org.eclipse.uml2.uml.TestIdentityAction inputTestIdentityAction) {
        ETestIdentityAction element = new ETestIdentityAction(
                inputTestIdentityAction);
        this.importModel.importXMI(element);
    }

    @objid ("53e110df-59b2-4cc5-9e1b-504a09dd2cb5")
    public void visitTimeConstraint(org.eclipse.uml2.uml.TimeConstraint inputTimeConstraint) {
        ETimeConstraint element = new ETimeConstraint(inputTimeConstraint);
        this.importModel.importXMI(element);
    }

    @objid ("ddfeb9f5-e7c1-43a0-b12c-f6e34da6f8fc")
    public void visitTimeEvent(org.eclipse.uml2.uml.TimeEvent inputTimeEvent) {
        ETimeEvent element = new ETimeEvent(inputTimeEvent);
        this.importModel.importXMI(element);
    }

    @objid ("adf77ce1-3c9f-404a-8757-1dac107bdd02")
    public void visitTimeExpression(org.eclipse.uml2.uml.TimeExpression inputTimeExpression) {
        ETimeExpression element = new ETimeExpression(inputTimeExpression);
        this.importModel.importXMI(element);
    }

    @objid ("3b11fd0d-af82-4138-8309-223a5f7ed892")
    public void visitTimeInterval(org.eclipse.uml2.uml.TimeInterval inputTimeInterval) {
        ETimeInterval element = new ETimeInterval(inputTimeInterval);
        this.importModel.importXMI(element);
    }

    @objid ("32dee725-9286-4bcf-9c6f-e1b7b97e30c5")
    public void visitTransition(org.eclipse.uml2.uml.Transition inputTransition) {
        ETransition element = new ETransition(inputTransition);
        this.importModel.importXMI(element);
    }

    @objid ("f8e11d24-fd79-4d13-8009-b656d74c486d")
    public void visitTrigger(org.eclipse.uml2.uml.Trigger inputTrigger) {
        ETrigger element = new ETrigger(inputTrigger);
        this.importModel.importXMI(element);
    }

    @objid ("d6319fed-c9a8-49fb-b084-fe7963229305")
    public void visitUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction inputUnmarshallAction) {
        EUnmarshallAction element = new EUnmarshallAction(inputUnmarshallAction);
        this.importModel.importXMI(element);
    }

    @objid ("4c069deb-5be1-4641-95b6-e045e64ba897")
    public void visitUsage(org.eclipse.uml2.uml.Usage inputUsage) {
        EUsage element = new EUsage(inputUsage);
        this.importModel.importXMI(element);
    }

    @objid ("6b9a1a6d-872c-4b43-9ac6-c5f2eb78661f")
    public void visitUseCase(org.eclipse.uml2.uml.UseCase inputUseCase) {
        EUseCase element = new EUseCase(inputUseCase);
        this.importModel.importXMI(element);
    }

    @objid ("dd8d6a24-e61b-4e0e-a00c-6d0c9fbc5d85")
    public void visitValuePin(org.eclipse.uml2.uml.ValuePin inputValuePin) {
        EValuePin element = new EValuePin(inputValuePin);
        this.importModel.importXMI(element);
    }

    @objid ("417ffb3d-797f-4f13-8046-f5271b383175")
    public void visitValueSpecificationAction(org.eclipse.uml2.uml.ValueSpecificationAction inputValueSpecificationAction) {
        EValueSpecificationAction element = new EValueSpecificationAction(
                inputValueSpecificationAction);
        this.importModel.importXMI(element);
    }

    @objid ("38f16d06-cd71-4b3b-84b6-5a7f28d133ba")
    public void visitVariable(org.eclipse.uml2.uml.Variable inputVariable) {
        EVariable element = new EVariable(inputVariable);
        this.importModel.importXMI(element);
    }

    @objid ("c0181e52-bcad-4daf-a970-f56c4655cb48")
    public ImportModel getTheImportModel() {
        return this.importModel;
    }

    @objid ("c38e4768-a812-4eb6-a56a-8589f29fc68d")
    public ImportModel getImportModel() {
        return this.importModel;
    }

    @objid ("6128c443-0c09-48c2-937d-211e94f308d7")
    public ProgressBarComposite getTheProgressBar() {
        return this.theProgressBar;
    }

    @objid ("4a9c933a-103a-477f-8d73-e57942d64c70")
    public void visitTimeObservation(final TimeObservation inputTimeObservation) {
        ETimeObservation element = new ETimeObservation(inputTimeObservation);
        this.importModel.importXMI(element);
    }

    @objid ("959ff053-67bf-4484-8385-70bb9cc7bc23")
    public void visitStartObjectBehaviorAction(org.eclipse.uml2.uml.StartObjectBehaviorAction inputStartObjectBehaviorAction) {
        EStartObjectBehaviorAction element = new EStartObjectBehaviorAction(
                inputStartObjectBehaviorAction);
        this.importModel.importXMI(element);
    }

}
