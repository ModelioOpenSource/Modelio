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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;

/**
 * Operation v0.0.9054
 * 
 * 
 * In Modelio, this metaclass defines both the Operation, and the method implementing it. 
 * 
 * An Operation belongs to its Classifier.
 * 
 * 
 */
@objid ("001449ac-c4bf-1fd8-97fe-001ec947cd2a")
public interface Operation extends BehavioralFeature {
    /**
     * The metaclass simple name.
     */
    @objid ("a608c29d-278b-4815-8262-d227eeeda175")
    public static final String MNAME = "Operation";

    /**
     * The metaclass qualified name.
     */
    @objid ("5185635f-1bd4-461d-ae75-b03a9a8cbd3e")
    public static final String MQNAME = "Standard.Operation";

    /**
     * Getter for attribute 'Operation.Concurrency'
     * 
     * Metamodel description:
     * <i>Distinguishes the different invocation modes of an Operation. This typically specifies concurrent modes.</i>
     * 
     */
    @objid ("9c000226-6014-4181-84ec-5f359b6c4c62")
    boolean isConcurrency();

    /**
     * Setter for attribute 'Operation.Concurrency'
     * 
     * Metamodel description:
     * <i>Distinguishes the different invocation modes of an Operation. This typically specifies concurrent modes.</i>
     * 
     */
    @objid ("f869f5b5-223f-4239-aa4b-94197ec6330a")
    void setConcurrency(boolean value);

    /**
     * Getter for attribute 'Operation.Final'
     * 
     * Metamodel description:
     * <i>Final operations cannot be redefined. Some OO languages, such as Java, optimize final operations.</i>
     * 
     */
    @objid ("07b8ede5-ef89-45af-8794-2783256f181d")
    boolean isFinal();

    /**
     * Setter for attribute 'Operation.Final'
     * 
     * Metamodel description:
     * <i>Final operations cannot be redefined. Some OO languages, such as Java, optimize final operations.</i>
     * 
     */
    @objid ("352df758-7ff0-431a-84e4-8907086f4912")
    void setFinal(boolean value);

    /**
     * Getter for attribute 'Operation.Passing'
     * 
     * Metamodel description:
     * <i>Method passing mode (in or inout). By default, this is inout. This mode determines whether the message receiver object is updated (inout) or not (in) when the method is invoked.</i>
     * 
     */
    @objid ("246bfd6d-95f0-43f6-8850-a05c869de6e5")
    MethodPassingMode getPassing();

    /**
     * Setter for attribute 'Operation.Passing'
     * 
     * Metamodel description:
     * <i>Method passing mode (in or inout). By default, this is inout. This mode determines whether the message receiver object is updated (inout) or not (in) when the method is invoked.</i>
     * 
     */
    @objid ("189c2a45-7b11-4c1a-a6a2-363fabe4a5b1")
    void setPassing(MethodPassingMode value);

    /**
     * Getter for relation 'Operation->OwnedImport'
     * 
     * Metamodel description:
     * <i>Elements imported by the Operation.</i>
     * 
     */
    @objid ("848a84ad-5a55-4274-850c-87108b87a153")
    EList<ElementImport> getOwnedImport();

    /**
     * Filtered Getter for relation 'Operation->OwnedImport'
     * 
     * Metamodel description:
     * <i>Elements imported by the Operation.</i>
     * 
     */
    @objid ("28a51b7b-da3e-4ece-99c0-4954f63601be")
    <T extends ElementImport> List<T> getOwnedImport(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Thrown'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("74ab75ee-4cc4-425f-8f0e-002e0a85ad0e")
    EList<RaisedException> getThrown();

    /**
     * Filtered Getter for relation 'Operation->Thrown'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e9fc8042-52d2-4cce-8618-b483a1c1d91b")
    <T extends RaisedException> List<T> getThrown(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Redefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f2da5e4d-2bc0-4219-990d-c607374244e9")
    EList<Operation> getRedefinition();

    /**
     * Filtered Getter for relation 'Operation->Redefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e9081942-9d8c-4082-bc86-e5a645b215b6")
    <T extends Operation> List<T> getRedefinition(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Example'
     * 
     * Metamodel description:
     * <i>Collaborations that illustrate the dynamic of the Operation. A Collaboration can be used to express the initial state of an Operation when it starts running.</i>
     * 
     */
    @objid ("523437be-edd1-489c-a026-e8099f812c2a")
    EList<Collaboration> getExample();

    /**
     * Filtered Getter for relation 'Operation->Example'
     * 
     * Metamodel description:
     * <i>Collaborations that illustrate the dynamic of the Operation. A Collaboration can be used to express the initial state of an Operation when it starts running.</i>
     * 
     */
    @objid ("7020d31e-66cd-4361-a19d-82a5c9828327")
    <T extends Collaboration> List<T> getExample(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->SRepresentation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("cf4e27ee-1420-423a-aa84-3869e9284bb2")
    EList<Signal> getSRepresentation();

    /**
     * Filtered Getter for relation 'Operation->SRepresentation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5f450c2d-0f1c-4de6-9a32-c43f34d04785")
    <T extends Signal> List<T> getSRepresentation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->OwnedBehavior'
     * 
     * Metamodel description:
     * <i>A behavioral description that implements the behavioral feature. </i>
     * 
     */
    @objid ("66915c3f-c365-471b-a2f6-f4fa9271421a")
    EList<Behavior> getOwnedBehavior();

    /**
     * Filtered Getter for relation 'Operation->OwnedBehavior'
     * 
     * Metamodel description:
     * <i>A behavioral description that implements the behavioral feature. </i>
     * 
     */
    @objid ("90e7d14b-177c-4f6e-8a4f-b01c92a20c84")
    <T extends Behavior> List<T> getOwnedBehavior(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->IO'
     * 
     * Metamodel description:
     * <i>Defines the parameters making up the Operation.</i>
     * 
     */
    @objid ("264ab9b7-4809-4478-83bb-c4d2d9e013d2")
    EList<Parameter> getIO();

    /**
     * Filtered Getter for relation 'Operation->IO'
     * 
     * Metamodel description:
     * <i>Defines the parameters making up the Operation.</i>
     * 
     */
    @objid ("2a49680b-6749-4f38-9abc-08703a871252")
    <T extends Parameter> List<T> getIO(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->TemplateInstanciation'
     * 
     * Metamodel description:
     * <i>Binds the Operation to the template operation that it instanciates.</i>
     * 
     */
    @objid ("651ecca6-6e2f-4cc6-badf-f08846309d7f")
    EList<TemplateBinding> getTemplateInstanciation();

    /**
     * Filtered Getter for relation 'Operation->TemplateInstanciation'
     * 
     * Metamodel description:
     * <i>Binds the Operation to the template operation that it instanciates.</i>
     * 
     */
    @objid ("fdc79a44-fc93-4198-8029-437471fc6ff6")
    <T extends TemplateBinding> List<T> getTemplateInstanciation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("19399513-4989-4aa4-87c7-965abd4896c0")
    Classifier getOwner();

    /**
     * Setter for relation 'Operation->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("09c5a0f0-9b12-45f8-8621-37a0e363f6f1")
    void setOwner(Classifier value);

    /**
     * Getter for relation 'Operation->OwnedPackageImport'
     * 
     * Metamodel description:
     * <i>Packages imported by the Operation.</i>
     * 
     */
    @objid ("76a323e8-25be-4d41-9500-23fe51d416d9")
    EList<PackageImport> getOwnedPackageImport();

    /**
     * Filtered Getter for relation 'Operation->OwnedPackageImport'
     * 
     * Metamodel description:
     * <i>Packages imported by the Operation.</i>
     * 
     */
    @objid ("c195a174-9a63-4019-a662-9dddb59ba567")
    <T extends PackageImport> List<T> getOwnedPackageImport(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Return'
     * 
     * Metamodel description:
     * <i>Link to the possible return parameter. The return parameter is only distinguished by this association, from the IOParameter.</i>
     * 
     */
    @objid ("df8f437c-fef5-42d6-ab40-96413fd07b71")
    Parameter getReturn();

    /**
     * Setter for relation 'Operation->Return'
     * 
     * Metamodel description:
     * <i>Link to the possible return parameter. The return parameter is only distinguished by this association, from the IOParameter.</i>
     * 
     */
    @objid ("12638500-c1f2-4fdc-af40-fa1094bce7dc")
    void setReturn(Parameter value);

    /**
     * Getter for relation 'Operation->InstanciatingBinding'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("036b5050-d598-4663-b38c-de1e3f5ed1fe")
    EList<TemplateBinding> getInstanciatingBinding();

    /**
     * Filtered Getter for relation 'Operation->InstanciatingBinding'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d449e2c2-e682-4ead-a804-32794b89d2d2")
    <T extends TemplateBinding> List<T> getInstanciatingBinding(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Usage'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9b8241d5-ec5f-4a10-affc-05cf792b73dc")
    EList<Message> getUsage();

    /**
     * Filtered Getter for relation 'Operation->Usage'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("211de4f0-8199-493f-87df-b48396b90b93")
    <T extends Message> List<T> getUsage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Template'
     * 
     * Metamodel description:
     * <i>In case of template operations, this association defines its template parameters.</i>
     * 
     */
    @objid ("c09c6283-e90e-4cd3-959c-dabda2aba8e4")
    EList<TemplateParameter> getTemplate();

    /**
     * Filtered Getter for relation 'Operation->Template'
     * 
     * Metamodel description:
     * <i>In case of template operations, this association defines its template parameters.</i>
     * 
     */
    @objid ("a67d3f4a-b002-4f20-817b-524b0c34f094")
    <T extends TemplateParameter> List<T> getTemplate(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("349d5f93-0e7b-4fd9-97b1-fc14bf8f85cc")
    EList<Event> getOccurence();

    /**
     * Filtered Getter for relation 'Operation->Occurence'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("685de5ef-2353-47dc-a3af-fcc82a1705fb")
    <T extends Event> List<T> getOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Invoker'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("42f147c1-5f18-4db4-a331-d31b4c948a62")
    EList<Transition> getInvoker();

    /**
     * Filtered Getter for relation 'Operation->Invoker'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("639ff2a5-d521-4547-9322-46d61fde3017")
    <T extends Transition> List<T> getInvoker(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->CommunicationUsage'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("59d021f7-3c20-4745-9dd2-774915fcc10b")
    EList<CommunicationMessage> getCommunicationUsage();

    /**
     * Filtered Getter for relation 'Operation->CommunicationUsage'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ff3f2e42-2869-4fa3-8f69-f790307b5328")
    <T extends CommunicationMessage> List<T> getCommunicationUsage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->OwnedCollaborationUse'
     * 
     * Metamodel description:
     * <i>Collaboration occurrences owned by the Operation.</i>
     * 
     */
    @objid ("664299f1-4a0b-4cf9-9789-e313cfc9c5d0")
    EList<CollaborationUse> getOwnedCollaborationUse();

    /**
     * Filtered Getter for relation 'Operation->OwnedCollaborationUse'
     * 
     * Metamodel description:
     * <i>Collaboration occurrences owned by the Operation.</i>
     * 
     */
    @objid ("6150d617-2ddb-4cd4-af82-198ad03ab13b")
    <T extends CollaborationUse> List<T> getOwnedCollaborationUse(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->Redefines'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("45bd6668-e131-43ee-a5fe-9b5df478c1ce")
    Operation getRedefines();

    /**
     * Setter for relation 'Operation->Redefines'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("bd1d5056-d13d-4beb-b9c7-c782e740a4cd")
    void setRedefines(Operation value);

    /**
     * Getter for relation 'Operation->CallingAction'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6bfdfd0c-94ef-4bfe-b8eb-701dd06d15a5")
    EList<CallOperationAction> getCallingAction();

    /**
     * Filtered Getter for relation 'Operation->CallingAction'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("10da6c6d-5e6d-4fbe-9a87-151e144b280b")
    <T extends CallOperationAction> List<T> getCallingAction(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Operation->EntryPointAction'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c07d1442-4aa3-4984-9ca4-4f6a1151a5d6")
    EList<AcceptCallEventAction> getEntryPointAction();

    /**
     * Filtered Getter for relation 'Operation->EntryPointAction'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("4ec24ded-545a-41ab-a43f-ebdd530bd3fd")
    <T extends AcceptCallEventAction> List<T> getEntryPointAction(java.lang.Class<T> filterClass);
}

