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
package org.modelio.metamodel.impl.mmextensions.standard.migration.from_37;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.MofBuilder;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * <p>
 * BPMN metamodel Migrator from Modelio 3.7 (mm v2.2.0) to Modelio 3.8 (mm v2.3.0).
 * </p>
 * Metamodel Standard 2.3.0 : BPMN -> UML dependencies replaced with MethodologicalLinks.
 * <ul>
 * <li>- BpmnCallActivity.CalledOperation: Operation</li>
 * <li>- BpmnCallActivity.CalledBehavior: Behavior</li>
 * <li>- BpmnDataInput.RepresentedParameter: Parameter</li>
 * <li>- BpmnDataOutput.RepresentedParameter: Parameter</li>
 * <li>- BpmnDataState.UmlState: State</li>
 * <li>- BpmnInterface.ImplementationRef: GeneralClass</li>
 * <li>- BpmnItemAwareElement.Type: GeneralClass</li>
 * <li>- BpmnItemAwareElement.InState: State</li>
 * <li>- BpmnItemAwareElement.RepresentedAssociationEnd: AssociationEnd</li>
 * <li>- BpmnItemAwareElement.RepresentedAttribute: Attribute</li>
 * <li>- BpmnItemAwareElement.RepresentedInstance: Instance</li>
 * <li>- BpmnItemDefinition.StructureRef: GeneralClass</li>
 * <li>- BpmnLane.PartitionElement: UmlModelElement</li>
 * <li>- BpmnMessage.Type: GeneralClass</li>
 * <li>- BpmnMessage.InState: State</li>
 * <li>- BpmnOperation.ImplementationRef: Operation</li>
 * <li>- BpmnParticipant.PackageRef: Package</li>
 * <li>- BpmnParticipant.Type: Classifier</li>
 * <li>- BpmnReceiveTask.CalledOperation: Operation</li>
 * <li>- BpmnSendTask.CalledOperation: Operation</li>
 * <li>- BpmnServiceTask.CalledOperation: Operation</li>
 * 
 * </ul>
 * 
 * @since 3.8
 */
@objid ("11b975f7-53ad-4685-9f32-a40135c19389")
public class StandardMm220Migrator implements IMofRepositoryMigrator {
    @objid ("778c0be9-b1bd-4a40-a899-68d1aafab517")
    private MofSmClass bpmnCallActivityMC;

    @objid ("46cc03d3-b9dc-4a2a-98df-a807c7b46a5f")
    private MetamodelVersionDescriptor sourceMetamodel;

    @objid ("8263a8b7-01eb-4273-bdb8-af590bfb3848")
    private MetamodelVersionDescriptor targetMetamodel;

    @objid ("5d448499-c131-4220-bd03-fae91539d08c")
    private MofSmClass bpmnDataInputMC;

    @objid ("5988c821-bd2b-46d0-b3c2-4f30eca28cf2")
    private MofSmClass bpmnDataOutputMC;

    @objid ("664e5ccd-42fe-444e-9a1e-4ed689525d8a")
    private MofSmClass bpmnDataStateMC;

    @objid ("625dfad2-40df-46be-807d-b86299753726")
    private MofSmClass bpmnInterfaceMC;

    @objid ("6d7f378b-6862-42c0-8ecd-803ae256e20f")
    private MofSmClass bpmnItemAwareElementMC;

    @objid ("09e890be-02d3-436d-b11c-163f29b70af0")
    private MofSmClass bpmnLaneMC;

    @objid ("f0c1f7f9-2a16-43cc-83c2-6a78acc9c74a")
    private MofSmClass bpmnItemDefinitionMC;

    @objid ("970392ba-be57-458a-b127-7300e693fb08")
    private MofSmClass bpmnMessageMC;

    @objid ("35c3e4d3-c532-49cd-a756-68e5e67d7506")
    private MofSmClass bpmnOperationMC;

    @objid ("b44d81e3-82cc-4072-8b4a-d14aa93d1431")
    private MofSmClass bpmnParticipantMC;

    @objid ("b588c6de-a020-497b-a3fe-a344ad85134c")
    private MofSmClass bpmnReceiveTaskMC;

    @objid ("f48a02ad-d101-4e75-8ef1-0c466c0565a0")
    private MofSmClass bpmnSendTaskMC;

    @objid ("b6c03920-abfa-4a64-b09f-247f7c3408ef")
    private MofSmClass bpmnServiceTaskMC;

    @objid ("60c13098-f8e5-40bb-a0fd-1f381e1aa654")
    private MofSmClass methodologicalLinkMC;

    @objid ("9c8ee5af-4e53-43f2-a536-ffb7f596eddd")
    private static final MRef called_methodologicallink_stereotype = new MRef(Stereotype.MQNAME, "c3862c6c-5983-4d1a-b0e2-58dd2685eda0", "Called");

    @objid ("be525e61-4e5c-4bc1-9f3d-5d9f4f82d5c4")
    private static final MRef partitionelement_methodologicallink_stereotype = new MRef(Stereotype.MQNAME, "5de33d2a-ed28-439c-aa09-d11bf1a6d878", "PartitionElement");

    @objid ("edc9ddb9-b5b3-49c2-89a0-e893ac297744")
    private static final MRef represents_methodologicallink_stereotype = new MRef(Stereotype.MQNAME, "f5d2927d-46d6-4d87-9cf2-adb4a47ca929", "Represents");

    @objid ("0a54af5d-11b0-4a4e-aa39-cc8a7926a659")
    private static final MRef event_methodologicallink_stereotype = new MRef(Stereotype.MQNAME, "143b4e00-fe2e-44d0-9c64-5a95e385ec5a", "Event");

    @objid ("84e284f2-1c70-4b2c-8262-7d0cd780e21e")
    private static final MRef process_methodologicallink_stereotype = new MRef(Stereotype.MQNAME, "616b72d4-1d47-49e1-a381-2e6ecfea637c", "Process");

    @objid ("abd01a7d-68d2-495d-b2af-3d05a199e321")
    private static final MRef reference_methodologicallink_stereotype = new MRef(Stereotype.MQNAME, "3b4dc351-ccaa-47b8-af57-8434f8e0e5f5", "Reference");

    @objid ("81cb6199-d99c-49a9-98a6-d6244c70147f")
    private static final MRef state_methodologicallink_stereotype = new MRef(Stereotype.MQNAME, "c2d2a1ec-2c29-453c-a79c-19e4f2d27f13", "State");

    @objid ("12bb2e74-fe02-48eb-a47a-1261d3a5badc")
    public  StandardMm220Migrator(MetamodelVersionDescriptor sourceMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        this.sourceMetamodel = sourceMetamodel;
        this.targetMetamodel = targetMetamodel;
        
    }

    @objid ("daf685e4-fc5e-4c67-b6f5-e7ac38e13510")
    @Override
    public MetamodelChangeDescriptor getMetamodelChanges() {
        return new MetamodelChangeDescriptor();
    }

    @objid ("59604999-0c59-4266-afa5-b5d00a69825d")
    @Override
    public MetamodelVersionDescriptor getSourceMetamodel() {
        return this.sourceMetamodel;
    }

    @objid ("455d91c7-fde7-436d-a576-bef16159af09")
    @Override
    public MetamodelVersionDescriptor getTargetMetamodel() {
        return this.targetMetamodel;
    }

    /**
     * Modify the metamodel so that it can read the source repository.
     * @param metamodel the metamodel at the final state
     * @throws MofMigrationException on fatal failure preventing migration
     */
    @objid ("2e819494-7e84-4169-bac3-84d77214fa42")
    @Override
    public void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException {
        // Get pointers to used metaclasses
        this.methodologicalLinkMC = (MofSmClass) requireMClass(metamodel, MethodologicalLink.MQNAME);
        this.bpmnCallActivityMC = (MofSmClass) requireMClass(metamodel, BpmnCallActivity.MQNAME);
        this.bpmnDataInputMC = (MofSmClass) requireMClass(metamodel, BpmnDataInput.MQNAME);
        this.bpmnDataOutputMC = (MofSmClass) requireMClass(metamodel, BpmnDataOutput.MQNAME);
        this.bpmnDataStateMC = (MofSmClass) requireMClass(metamodel, BpmnDataState.MQNAME);
        this.bpmnInterfaceMC = (MofSmClass) requireMClass(metamodel, BpmnInterface.MQNAME);
        this.bpmnItemAwareElementMC = (MofSmClass) requireMClass(metamodel, BpmnItemAwareElement.MQNAME);
        this.bpmnItemDefinitionMC = (MofSmClass) requireMClass(metamodel, BpmnItemDefinition.MQNAME);
        this.bpmnLaneMC = (MofSmClass) requireMClass(metamodel, BpmnLane.MQNAME);
        this.bpmnMessageMC = (MofSmClass) requireMClass(metamodel, BpmnMessage.MQNAME);
        this.bpmnOperationMC = (MofSmClass) requireMClass(metamodel, BpmnOperation.MQNAME);
        this.bpmnParticipantMC = (MofSmClass) requireMClass(metamodel, BpmnParticipant.MQNAME);
        this.bpmnReceiveTaskMC = (MofSmClass) requireMClass(metamodel, BpmnReceiveTask.MQNAME);
        this.bpmnSendTaskMC = (MofSmClass) requireMClass(metamodel, BpmnSendTask.MQNAME);
        this.bpmnServiceTaskMC = (MofSmClass) requireMClass(metamodel, BpmnServiceTask.MQNAME);
        
        if (this.bpmnCallActivityMC.getDependency("CalledOperation") != null) {
            // Everything already present
            return;
        }
        
        // Merge BPMN metamodel 2.2.0 into the current one.
        try (MofBuilder b = metamodel.builder().setTemporary(true);) {
            // Call activity
            b.createDep("CalledOperation")
                    .setSource(this.bpmnCallActivityMC)
                    .setTarget(Operation.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("Caller", 0, -1)
                    .build();
        
            b.createDep("CalledBehavior")
                    .setSource(this.bpmnCallActivityMC)
                    .setTarget(Behavior.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnCaller", 0, -1)
                    .build();
        
            // Data input
            b.createDep("RepresentedParameter")
                    .setSource(this.bpmnDataInputMC)
                    .setTarget(Parameter.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnRepresentingDataInput", 0, -1)
                    .build();
        
            // Data output
            b.createDep("RepresentedParameter")
                    .setSource(this.bpmnDataOutputMC)
                    .setTarget(Parameter.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnRepresentingDataOutput", 0, -1)
                    .build();
        
            // Data state
            b.createDep("UmlState")
                    .setSource(this.bpmnDataStateMC)
                    .setTarget(State.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnDataStateRefs", 0, -1)
                    .build();
        
            // Interface
            b.createDep("ImplementationRef")
                    .setSource(this.bpmnInterfaceMC)
                    .setTarget(GeneralClass.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnInterfaceRefs", 0, -1)
                    .build();
        
            // Item Aware Element
            b.createDep("Type")
                    .setSource(this.bpmnItemAwareElementMC)
                    .setTarget(GeneralClass.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnItemAwareRefs", 0, -1)
                    .build();
        
            b.createDep("InState")
                    .setSource(this.bpmnItemAwareElementMC)
                    .setTarget(State.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("RequiredStateOfBpmnItem", 0, -1)
                    .build();
        
            b.createDep("RepresentedAssociationEnd")
                    .setSource(this.bpmnItemAwareElementMC)
                    .setTarget(AssociationEnd.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("RepresentingItem", 0, -1)
                    .build();
        
            b.createDep("RepresentedAttribute")
                    .setSource(this.bpmnItemAwareElementMC)
                    .setTarget(Attribute.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("RepresentingItem", 0, -1)
                    .build();
        
            b.createDep("RepresentedInstance")
                    .setSource(this.bpmnItemAwareElementMC)
                    .setTarget(Instance.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("RepresentingItem", 0, -1)
                    .build();
        
            // Item Definition
            b.createDep("StructureRef")
                    .setSource(this.bpmnItemDefinitionMC)
                    .setTarget(GeneralClass.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnItemDefinitionRefs", 0, -1)
                    .build();
        
            // Message
            b.createDep("Type")
                    .setSource(this.bpmnMessageMC)
                    .setTarget(GeneralClass.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnMessageRefs", 0, -1)
                    .build();
        
            b.createDep("InState")
                    .setSource(this.bpmnMessageMC)
                    .setTarget(State.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("RequiredStateOfBpmnMessage", 0, -1)
                    .build();
        
            // Lane
            b.createDep("PartitionElement")
                    .setSource(this.bpmnLaneMC)
                    .setTarget(UmlModelElement.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnLaneRefs", 0, -1)
                    .build();
        
            // Operation
            b.createDep("ImplementationRef")
                    .setSource(this.bpmnOperationMC)
                    .setTarget(Operation.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnOperationRef", 0, -1)
                    .build();
        
            // Participant
            b.createDep("Type")
                    .setSource(this.bpmnParticipantMC)
                    .setTarget(Classifier.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnRepresents", 0, -1)
                    .build();
        
            b.createDep("PackageRef")
                    .setSource(this.bpmnParticipantMC)
                    .setTarget(Package.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("ParticipantRef", 0, -1)
                    .build();
        
            // Receive task
            b.createDep("CalledOperation")
                    .setSource(this.bpmnReceiveTaskMC)
                    .setTarget(Operation.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("CallerReceiveTask", 0, -1)
                    .build();
        
            // Send task
            b.createDep("CalledOperation")
                    .setSource(this.bpmnSendTaskMC)
                    .setTarget(Operation.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("CallerSendTask", 0, -1)
                    .build();
        
            // Service task
            b.createDep("CalledOperation")
                    .setSource(this.bpmnServiceTaskMC)
                    .setTarget(Operation.MQNAME)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("CallerServiceTask", 0, -1)
                    .build();
        }
        
    }

    @objid ("bc253f04-9362-4c45-b122-cc7438ad396c")
    @Override
    public void run(IModelioProgress monitor, IMofSession mofSession) throws MofMigrationException {
        SubProgress mon = SubProgress.convert(monitor, 7);
        
        try {
            fixBpmnCallActivity(mon.newChild(1), mofSession);
            fixBpmnDataInput(mon.newChild(1), mofSession);
            fixBpmnDataOutput(mon.newChild(1), mofSession);
            fixBpmnDataState(mon.newChild(1), mofSession);
            fixBpmnInterface(mon.newChild(1), mofSession);
            fixBpmnItemAwareElement(mon.newChild(1), mofSession);
            fixBpmnItemDefinition(mon.newChild(1), mofSession);
            fixBpmnLane(mon.newChild(1), mofSession);
            fixBpmnMessage(mon.newChild(1), mofSession);
            fixBpmnOperation(mon.newChild(1), mofSession);
            fixBpmnParticipant(mon.newChild(1), mofSession);
            fixBpmnReceiveTask(mon.newChild(1), mofSession);
            fixBpmnSendTask(mon.newChild(1), mofSession);
            fixBpmnServiceTask(mon.newChild(1), mofSession);
        } catch (MetaclassNotFoundException e) {
            throw new MofMigrationException(e.getLocalizedMessage(), e);
        }
        
    }

    @objid ("7cee4877-1baf-480a-974d-abf292e234ee")
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[from " + getSourceMetamodel() + " to " + getTargetMetamodel() + "]";
    }

    @objid ("caba41f0-4e82-4902-ab4f-4c528a3cce16")
    @SuppressWarnings ("unchecked")
    private static <T extends SmClass> T requireMClass(MofMetamodel metamodel, String name) {
        return (T) Objects.requireNonNull(metamodel.getMClass(name), name);
    }

    @objid ("17e72ef1-2e77-4607-b8fa-eeef66e28cbd")
    private static boolean assertDependencyConsistent(MofSmClass src, String name, MofSmClass target, String oppositeName) {
        MDependency dep = src.getDependency(name);
        assert (dep != null) : String.format("%s.%s : %s missing", src, name, target);
        assert (dep.getTarget() == target) : String.format("%s.%s : target should be %s.", src, dep, target);
        
        MDependency opposite = dep.getSymetric();
        assert (opposite != null) : String.format("%s.%s has no opposite.", src, dep);
        assert (opposite.getName().equals(oppositeName)) : String.format("%s.%s opposite of %s.%s name should be '%s'.", opposite.getSource(), opposite, src, dep, oppositeName);
        assert (opposite.getSource() == target) : String.format("%s.%s opposite of %s.%s source should be %s.", opposite.getSource(), opposite, src, dep, target);
        assert (opposite.getTarget() == src) : String.format("%s.%s opposite of %s.%s target should be %s.", opposite.getSource(), opposite, src, dep, src);
        return true;
    }

    /**
     * Replace 'UmlState' metamodel dep with a <<State>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("10fd9503-ac5a-4ccc-9be5-f0675b70ddc3")
    private void fixBpmnDataState(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl stateStereotype = mofsession.getObjectReference(StandardMm220Migrator.state_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnDataStateMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "UmlState", stateStereotype);
        }
        
    }

    /**
     * Replace 'RepresentedParameter' metamodel dep with a <<Represents>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("7bb3d7ed-f3dd-412d-85b2-d270d43bfdd6")
    private void fixBpmnDataOutput(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl representsStereotype = mofsession.getObjectReference(StandardMm220Migrator.represents_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnDataOutputMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "RepresentedParameter", representsStereotype);
        }
        
    }

    /**
     * Replace 'RepresentedParameter' metamodel dep with a <<Represents>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("8ef8977b-d01c-4100-95c4-49c7b32eac2c")
    private void fixBpmnDataInput(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl representsStereotype = mofsession.getObjectReference(StandardMm220Migrator.represents_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnDataInputMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "RepresentedParameter", representsStereotype);
        }
        
    }

    /**
     * Replace 'CalledOperation' and 'CalledBehavior' metamodel deps with a <<Called>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("8569a021-469f-497b-8be6-85dedad25129")
    private void fixBpmnCallActivity(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl calledStereotype = mofsession.getObjectReference(StandardMm220Migrator.called_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnCallActivityMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "CalledOperation", calledStereotype);
            createMethodologicalLink(mofsession, bpmnElement, "CalledBehavior", calledStereotype);
        }
        
    }

    /**
     * Replace a metamodel dep with a stereotyped {@link MethodologicalLink}.
     */
    @objid ("cfff8fc3-11b9-4548-9e26-c0c7ea03ebc4")
    protected void createMethodologicalLink(IMofSession mofsession, MofSmObjectImpl source, String targetDep, MofSmObjectImpl linkStereotype) {
        List<MofSmObjectImpl> dep = source.getDep(targetDep);
        for (MofSmObjectImpl target : dep) {
            MofSmObjectImpl newLink = mofsession.createObject(this.methodologicalLinkMC);
            newLink.getDep("Impacted").add(source);
            newLink.getDep("DependsOn").add(target);
            newLink.getDep("Extension").add(linkStereotype);
        
            mofsession.getReport().getLogger().printf("    Replaced %s from %s to %s with a <<%s>> MethodologicalLink.\n", targetDep, source, target, linkStereotype);
        }
        
        dep.clear();
        
    }

    /**
     * Replace 'ImplementationRef' metamodel dep with a <<Reference>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("2434f02b-950b-4f27-ad91-eca0aa001278")
    private void fixBpmnInterface(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl referenceStereotype = mofsession.getObjectReference(StandardMm220Migrator.reference_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnInterfaceMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "ImplementationRef", referenceStereotype);
        }
        
    }

    /**
     * Replace 'Type', 'RepresentedAssociationEnd', 'RepresentedAttribute' and 'RepresentedInstance' metamodel deps with a <<Represents>> {@link MethodologicalLink}. Replace 'InState' metamodel dep with a <<State>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("c038923d-ae8b-4de5-8b5c-b35118a660bd")
    private void fixBpmnItemAwareElement(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl representsStereotype = mofsession.getObjectReference(StandardMm220Migrator.represents_methodologicallink_stereotype);
        MofSmObjectImpl stateStereotype = mofsession.getObjectReference(StandardMm220Migrator.state_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnItemAwareElementMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "Type", representsStereotype);
            createMethodologicalLink(mofsession, bpmnElement, "RepresentedAssociationEnd", representsStereotype);
            createMethodologicalLink(mofsession, bpmnElement, "RepresentedAttribute", representsStereotype);
            createMethodologicalLink(mofsession, bpmnElement, "RepresentedInstance", representsStereotype);
            createMethodologicalLink(mofsession, bpmnElement, "InState", stateStereotype);
        }
        
    }

    /**
     * Replace 'StructureRef' metamodel dep with a <<Reference>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("2bfbf318-db04-484b-8637-aa0105c2e506")
    private void fixBpmnItemDefinition(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl referenceStereotype = mofsession.getObjectReference(StandardMm220Migrator.reference_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnItemDefinitionMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "StructureRef", referenceStereotype);
        }
        
    }

    /**
     * Replace 'PartitionElement' metamodel dep with a <<PartitionElement>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("cd02e384-b1fc-493a-b353-56c62b60d4bd")
    private void fixBpmnLane(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl partitionElementStereotype = mofsession.getObjectReference(StandardMm220Migrator.partitionelement_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnLaneMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "PartitionElement", partitionElementStereotype);
        }
        
    }

    /**
     * Replace 'Type' metamodel dep with a <<Represents>> {@link MethodologicalLink}. <br/>
     * Replace 'InState' metamodel dep with a <<State>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("93ee661e-e955-4eba-85a9-0491c8729d96")
    private void fixBpmnMessage(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl representsStereotype = mofsession.getObjectReference(StandardMm220Migrator.represents_methodologicallink_stereotype);
        MofSmObjectImpl stateStereotype = mofsession.getObjectReference(StandardMm220Migrator.state_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnMessageMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "Type", representsStereotype);
            createMethodologicalLink(mofsession, bpmnElement, "InState", stateStereotype);
        }
        
    }

    /**
     * Replace 'ImplementationRef' metamodel dep with a <<Reference>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("6f460638-7088-4fb2-8e5e-70dacf01de05")
    private void fixBpmnOperation(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl referenceStereotype = mofsession.getObjectReference(StandardMm220Migrator.reference_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnOperationMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "ImplementationRef", referenceStereotype);
        }
        
    }

    /**
     * Replace 'Type' metamodel dep with a <<Represents>> {@link MethodologicalLink}. <br/>
     * Replace 'PackageRef' metamodel dep with a <<Reference>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("2fbed0dd-8f5f-44f1-a674-97e0cc32c62a")
    private void fixBpmnParticipant(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl representsStereotype = mofsession.getObjectReference(StandardMm220Migrator.represents_methodologicallink_stereotype);
        MofSmObjectImpl referenceStereotype = mofsession.getObjectReference(StandardMm220Migrator.reference_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnParticipantMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "Type", representsStereotype);
            createMethodologicalLink(mofsession, bpmnElement, "PackageRef", referenceStereotype);
        }
        
    }

    /**
     * Replace 'CalledOperation' metamodel dep with a <<Called>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("8baaef52-fe1c-418e-afe3-8ea080014780")
    private void fixBpmnReceiveTask(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl calledStereotype = mofsession.getObjectReference(StandardMm220Migrator.called_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnReceiveTaskMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "CalledOperation", calledStereotype);
        }
        
    }

    /**
     * Replace 'CalledOperation' metamodel dep with a <<Called>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("df0218d8-1e7c-4ed4-9688-a0c881bb87ab")
    private void fixBpmnSendTask(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl calledStereotype = mofsession.getObjectReference(StandardMm220Migrator.called_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnSendTaskMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "CalledOperation", calledStereotype);
        }
        
    }

    /**
     * Replace 'CalledOperation' metamodel dep with a <<Called>> {@link MethodologicalLink}.
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws MetaclassNotFoundException should not occur
     */
    @objid ("b103ba15-569e-4603-8674-aed8c1d23e13")
    private void fixBpmnServiceTask(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        MofSmObjectImpl calledStereotype = mofsession.getObjectReference(StandardMm220Migrator.called_methodologicallink_stereotype);
        
        for (MofSmObjectImpl bpmnElement : mofsession.findByClass(this.bpmnServiceTaskMC, true)) {
            createMethodologicalLink(mofsession, bpmnElement, "CalledOperation", calledStereotype);
        }
        
    }

}
