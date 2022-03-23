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

package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptCallEventActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallOperationActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.EventSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationMessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TransitionSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.BehavioralFeature;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("77952079-f367-4e69-ac13-e2a961f78214")
public class OperationSmClass extends BehavioralFeatureSmClass {
    @objid ("30c81596-2d67-43b8-a810-6e0ca90e8928")
    private SmAttribute concurrencyAtt;

    @objid ("4b47f2ff-2bb3-4853-8c2c-588379e6175e")
    private SmAttribute finalAtt;

    @objid ("3830bb1a-e341-4b0d-8fbc-686fdd02da47")
    private SmAttribute passingAtt;

    @objid ("e120979d-dd6e-410c-a35e-88045ca57509")
    private SmDependency ownedImportDep;

    @objid ("3de1de3a-db0d-49c6-9d6a-bdcdfdd570e9")
    private SmDependency thrownDep;

    @objid ("2c3a7853-3ea9-4d1b-83a6-bbc9a8ae1155")
    private SmDependency redefinitionDep;

    @objid ("cb78c994-4986-48cd-a69f-946f203a8955")
    private SmDependency exampleDep;

    @objid ("d671b9f0-16db-4873-bbba-9738f8913634")
    private SmDependency sRepresentationDep;

    @objid ("882b4489-834c-473d-acdf-201cc6d88dcc")
    private SmDependency ownedBehaviorDep;

    @objid ("45d8ec9f-7b42-4750-bab5-f5e93f27313e")
    private SmDependency iODep;

    @objid ("a05b0ada-f85a-46d5-b0fc-24465b630b43")
    private SmDependency templateInstanciationDep;

    @objid ("66ac6e83-9c0d-47bb-a39f-207ae24c46fb")
    private SmDependency ownerDep;

    @objid ("d2fe84ca-d900-468a-a62d-d8571cded027")
    private SmDependency ownedPackageImportDep;

    @objid ("16be7230-2481-4b14-832a-e7b93f4c4142")
    private SmDependency returnDep;

    @objid ("f5e116de-bcf2-4f5c-88a9-0bc41a2fe0bb")
    private SmDependency instanciatingBindingDep;

    @objid ("f3d7436f-75da-4fc4-a276-56685551a370")
    private SmDependency usageDep;

    @objid ("34f2899b-0224-4a56-9589-0b2dd321c9c4")
    private SmDependency templateDep;

    @objid ("9f1521a7-0805-4978-8cfe-62480f2d4486")
    private SmDependency occurenceDep;

    @objid ("514acde8-50e3-41dd-bda2-842ee0ef08e6")
    private SmDependency invokerDep;

    @objid ("25df2092-49c8-41ba-b28e-ccc985977de3")
    private SmDependency communicationUsageDep;

    @objid ("3b6c129a-39a9-4445-812d-749118037a98")
    private SmDependency ownedCollaborationUseDep;

    @objid ("5c88af41-b874-4856-8400-20d1de8b47b3")
    private SmDependency redefinesDep;

    @objid ("2ad19f17-fd87-4365-966d-223802516630")
    private SmDependency callingActionDep;

    @objid ("a0d4e59c-1db2-48eb-ab74-32abbd769e3e")
    private SmDependency entryPointActionDep;

    @objid ("f8eb73f8-8467-41a8-8250-c712595d463f")
    public  OperationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("fd1117db-1a34-4997-918f-0b8666c8bb49")
    @Override
    public String getName() {
        return "Operation";
        
    }

    @objid ("2c92b149-d9e7-468e-b57d-b2c5a5452b44")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2ffdf7de-8d4c-4738-8a3e-9afe0e49b72b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Operation.class;
        
    }

    @objid ("7345ab64-4c1d-423c-b66e-0a6f11fcacac")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("c8ee96c2-f5aa-4d3a-9b2c-d721055d12b0")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("b38199f9-7c5d-475c-9b4c-3134f3cea766")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BehavioralFeature.MQNAME);
        this.registerFactory(new OperationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.concurrencyAtt = new ConcurrencySmAttribute();
        this.concurrencyAtt.init("Concurrency", this, Boolean.class );
        registerAttribute(this.concurrencyAtt);
        
        this.finalAtt = new FinalSmAttribute();
        this.finalAtt.init("Final", this, Boolean.class );
        registerAttribute(this.finalAtt);
        
        this.passingAtt = new PassingSmAttribute();
        this.passingAtt.init("Passing", this, MethodPassingMode.class );
        registerAttribute(this.passingAtt);
        
        
        // Initialize and register the SmDependency
        this.ownedImportDep = new OwnedImportSmDependency();
        this.ownedImportDep.init("OwnedImport", this, metamodel.getMClass(ElementImport.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedImportDep);
        
        this.thrownDep = new ThrownSmDependency();
        this.thrownDep.init("Thrown", this, metamodel.getMClass(RaisedException.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.thrownDep);
        
        this.redefinitionDep = new RedefinitionSmDependency();
        this.redefinitionDep.init("Redefinition", this, metamodel.getMClass(Operation.MQNAME), 0, -1 );
        registerDependency(this.redefinitionDep);
        
        this.exampleDep = new ExampleSmDependency();
        this.exampleDep.init("Example", this, metamodel.getMClass(Collaboration.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.exampleDep);
        
        this.sRepresentationDep = new SRepresentationSmDependency();
        this.sRepresentationDep.init("SRepresentation", this, metamodel.getMClass(Signal.MQNAME), 0, -1 );
        registerDependency(this.sRepresentationDep);
        
        this.ownedBehaviorDep = new OwnedBehaviorSmDependency();
        this.ownedBehaviorDep.init("OwnedBehavior", this, metamodel.getMClass(Behavior.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedBehaviorDep);
        
        this.iODep = new IOSmDependency();
        this.iODep.init("IO", this, metamodel.getMClass(Parameter.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.iODep);
        
        this.templateInstanciationDep = new TemplateInstanciationSmDependency();
        this.templateInstanciationDep.init("TemplateInstanciation", this, metamodel.getMClass(TemplateBinding.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.templateInstanciationDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Classifier.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        this.ownedPackageImportDep = new OwnedPackageImportSmDependency();
        this.ownedPackageImportDep.init("OwnedPackageImport", this, metamodel.getMClass(PackageImport.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedPackageImportDep);
        
        this.returnDep = new ReturnSmDependency();
        this.returnDep.init("Return", this, metamodel.getMClass(Parameter.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.returnDep);
        
        this.instanciatingBindingDep = new InstanciatingBindingSmDependency();
        this.instanciatingBindingDep.init("InstanciatingBinding", this, metamodel.getMClass(TemplateBinding.MQNAME), 0, -1 );
        registerDependency(this.instanciatingBindingDep);
        
        this.usageDep = new UsageSmDependency();
        this.usageDep.init("Usage", this, metamodel.getMClass(Message.MQNAME), 0, -1 );
        registerDependency(this.usageDep);
        
        this.templateDep = new TemplateSmDependency();
        this.templateDep.init("Template", this, metamodel.getMClass(TemplateParameter.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.templateDep);
        
        this.occurenceDep = new OccurenceSmDependency();
        this.occurenceDep.init("Occurence", this, metamodel.getMClass(Event.MQNAME), 0, -1 );
        registerDependency(this.occurenceDep);
        
        this.invokerDep = new InvokerSmDependency();
        this.invokerDep.init("Invoker", this, metamodel.getMClass(Transition.MQNAME), 0, -1 );
        registerDependency(this.invokerDep);
        
        this.communicationUsageDep = new CommunicationUsageSmDependency();
        this.communicationUsageDep.init("CommunicationUsage", this, metamodel.getMClass(CommunicationMessage.MQNAME), 0, -1 );
        registerDependency(this.communicationUsageDep);
        
        this.ownedCollaborationUseDep = new OwnedCollaborationUseSmDependency();
        this.ownedCollaborationUseDep.init("OwnedCollaborationUse", this, metamodel.getMClass(CollaborationUse.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedCollaborationUseDep);
        
        this.redefinesDep = new RedefinesSmDependency();
        this.redefinesDep.init("Redefines", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.redefinesDep);
        
        this.callingActionDep = new CallingActionSmDependency();
        this.callingActionDep.init("CallingAction", this, metamodel.getMClass(CallOperationAction.MQNAME), 0, -1 );
        registerDependency(this.callingActionDep);
        
        this.entryPointActionDep = new EntryPointActionSmDependency();
        this.entryPointActionDep.init("EntryPointAction", this, metamodel.getMClass(AcceptCallEventAction.MQNAME), 0, -1 );
        registerDependency(this.entryPointActionDep);
        
        
    }

    @objid ("a2380960-977e-45dd-aa80-61ea4eed9dcc")
    public SmAttribute getConcurrencyAtt() {
        if (this.concurrencyAtt == null) {
        	this.concurrencyAtt = this.getAttributeDef("Concurrency");
        }
        return this.concurrencyAtt;
    }

    @objid ("93887cd3-e6bc-4721-b088-7c4cab6cb14b")
    public SmAttribute getFinalAtt() {
        if (this.finalAtt == null) {
        	this.finalAtt = this.getAttributeDef("Final");
        }
        return this.finalAtt;
    }

    @objid ("bcf4847e-637a-4358-9719-5ef20540992d")
    public SmAttribute getPassingAtt() {
        if (this.passingAtt == null) {
        	this.passingAtt = this.getAttributeDef("Passing");
        }
        return this.passingAtt;
    }

    @objid ("6762459a-cd47-4b7f-b98f-4c37490986e1")
    public SmDependency getOwnedImportDep() {
        if (this.ownedImportDep == null) {
        	this.ownedImportDep = this.getDependencyDef("OwnedImport");
        }
        return this.ownedImportDep;
    }

    @objid ("f6ecbcb8-24ee-446b-ab6a-79eaea68eb60")
    public SmDependency getThrownDep() {
        if (this.thrownDep == null) {
        	this.thrownDep = this.getDependencyDef("Thrown");
        }
        return this.thrownDep;
    }

    @objid ("58ba1287-2f71-4a53-b8c2-46af27b99bea")
    public SmDependency getRedefinitionDep() {
        if (this.redefinitionDep == null) {
        	this.redefinitionDep = this.getDependencyDef("Redefinition");
        }
        return this.redefinitionDep;
    }

    @objid ("fc488855-00a0-4c62-a5c4-d86efeba0d99")
    public SmDependency getExampleDep() {
        if (this.exampleDep == null) {
        	this.exampleDep = this.getDependencyDef("Example");
        }
        return this.exampleDep;
    }

    @objid ("08f0ed7c-6eb1-4110-9e11-e2b775ea198c")
    public SmDependency getSRepresentationDep() {
        if (this.sRepresentationDep == null) {
        	this.sRepresentationDep = this.getDependencyDef("SRepresentation");
        }
        return this.sRepresentationDep;
    }

    @objid ("c99c0f04-4348-43eb-b196-ba4d4a052c8d")
    public SmDependency getOwnedBehaviorDep() {
        if (this.ownedBehaviorDep == null) {
        	this.ownedBehaviorDep = this.getDependencyDef("OwnedBehavior");
        }
        return this.ownedBehaviorDep;
    }

    @objid ("e980754b-836c-4cce-a7ac-722d6a03ef9d")
    public SmDependency getIODep() {
        if (this.iODep == null) {
        	this.iODep = this.getDependencyDef("IO");
        }
        return this.iODep;
    }

    @objid ("337fc02e-c7bc-4ec6-ba5a-7cfc45f5739e")
    public SmDependency getTemplateInstanciationDep() {
        if (this.templateInstanciationDep == null) {
        	this.templateInstanciationDep = this.getDependencyDef("TemplateInstanciation");
        }
        return this.templateInstanciationDep;
    }

    @objid ("8812585c-c7f0-49dc-ac5d-42b495f1242d")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("cc888710-b4bc-4da2-bbda-7572c5761785")
    public SmDependency getOwnedPackageImportDep() {
        if (this.ownedPackageImportDep == null) {
        	this.ownedPackageImportDep = this.getDependencyDef("OwnedPackageImport");
        }
        return this.ownedPackageImportDep;
    }

    @objid ("612121bb-7e53-40f0-92d1-884fda8cf833")
    public SmDependency getReturnDep() {
        if (this.returnDep == null) {
        	this.returnDep = this.getDependencyDef("Return");
        }
        return this.returnDep;
    }

    @objid ("adc61c70-38fc-46b5-a88a-5394cf3fb57a")
    public SmDependency getInstanciatingBindingDep() {
        if (this.instanciatingBindingDep == null) {
        	this.instanciatingBindingDep = this.getDependencyDef("InstanciatingBinding");
        }
        return this.instanciatingBindingDep;
    }

    @objid ("20ada29b-5e68-4a51-8b3b-a7275616ee1c")
    public SmDependency getUsageDep() {
        if (this.usageDep == null) {
        	this.usageDep = this.getDependencyDef("Usage");
        }
        return this.usageDep;
    }

    @objid ("bfd89eb2-5ba6-494b-af68-d5df160ac6db")
    public SmDependency getTemplateDep() {
        if (this.templateDep == null) {
        	this.templateDep = this.getDependencyDef("Template");
        }
        return this.templateDep;
    }

    @objid ("31335350-2f0c-461e-b803-140f1406d072")
    public SmDependency getOccurenceDep() {
        if (this.occurenceDep == null) {
        	this.occurenceDep = this.getDependencyDef("Occurence");
        }
        return this.occurenceDep;
    }

    @objid ("9bf02774-047b-4411-9028-c95316a7cef0")
    public SmDependency getInvokerDep() {
        if (this.invokerDep == null) {
        	this.invokerDep = this.getDependencyDef("Invoker");
        }
        return this.invokerDep;
    }

    @objid ("d76356b1-04e0-4884-bc1b-81c6a0aaa381")
    public SmDependency getCommunicationUsageDep() {
        if (this.communicationUsageDep == null) {
        	this.communicationUsageDep = this.getDependencyDef("CommunicationUsage");
        }
        return this.communicationUsageDep;
    }

    @objid ("1f44aea4-0488-488b-a3a4-ba9982920e87")
    public SmDependency getOwnedCollaborationUseDep() {
        if (this.ownedCollaborationUseDep == null) {
        	this.ownedCollaborationUseDep = this.getDependencyDef("OwnedCollaborationUse");
        }
        return this.ownedCollaborationUseDep;
    }

    @objid ("a710c9db-6fcf-48ad-89f0-15c3ff431ac0")
    public SmDependency getRedefinesDep() {
        if (this.redefinesDep == null) {
        	this.redefinesDep = this.getDependencyDef("Redefines");
        }
        return this.redefinesDep;
    }

    @objid ("d88896a7-ef27-4e98-b5bf-6a93034988e9")
    public SmDependency getCallingActionDep() {
        if (this.callingActionDep == null) {
        	this.callingActionDep = this.getDependencyDef("CallingAction");
        }
        return this.callingActionDep;
    }

    @objid ("92a6fe70-a3e8-460e-9b57-c8f50fcce790")
    public SmDependency getEntryPointActionDep() {
        if (this.entryPointActionDep == null) {
        	this.entryPointActionDep = this.getDependencyDef("EntryPointAction");
        }
        return this.entryPointActionDep;
    }

    @objid ("76c379d0-e15b-4821-8ccd-974572eb27ea")
    private static class OperationObjectFactory implements ISmObjectFactory {
        @objid ("f43ad17d-a3e4-4a1f-822b-c26192b90fc0")
        private OperationSmClass smClass;

        @objid ("9ab1e764-a66a-4be8-9442-971ac37d1fab")
        public  OperationObjectFactory(OperationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c2e611d8-8b68-4a1d-a4dc-7ea8e6ed105a")
        @Override
        public ISmObjectData createData() {
            return new OperationData(this.smClass);
        }

        @objid ("9ee8071c-f27c-481e-b436-1be7d421aede")
        @Override
        public SmObjectImpl createImpl() {
            return new OperationImpl();
        }

    }

    @objid ("254abfff-54ad-4592-95e5-7605e9dae0a5")
    public static class ConcurrencySmAttribute extends SmAttribute {
        @objid ("85bde5df-b394-42b2-a7dc-2ed7b6e337be")
        public Object getValue(ISmObjectData data) {
            return ((OperationData) data).mConcurrency;
        }

        @objid ("226849a3-287c-41cb-9768-77303707efab")
        public void setValue(ISmObjectData data, Object value) {
            ((OperationData) data).mConcurrency = value;
        }

    }

    @objid ("479fde10-969e-44d1-ba1d-d2bbef38b95e")
    public static class FinalSmAttribute extends SmAttribute {
        @objid ("dddad247-52ea-4a58-8aba-ff5eeedcfc44")
        public Object getValue(ISmObjectData data) {
            return ((OperationData) data).mFinal;
        }

        @objid ("3a4e0659-67f7-49e9-9d58-74be6fea6dcc")
        public void setValue(ISmObjectData data, Object value) {
            ((OperationData) data).mFinal = value;
        }

    }

    @objid ("ca3b3684-ceb7-4ee8-aef2-9165cc5c5a16")
    public static class PassingSmAttribute extends SmAttribute {
        @objid ("6361d62e-7f45-4bed-8532-648c776b8e3d")
        public Object getValue(ISmObjectData data) {
            return ((OperationData) data).mPassing;
        }

        @objid ("16762a60-c4bb-41ee-9c15-6143883b56e9")
        public void setValue(ISmObjectData data, Object value) {
            ((OperationData) data).mPassing = value;
        }

    }

    @objid ("2ace98de-3193-4aef-ac7c-7df813947b13")
    public static class OwnedImportSmDependency extends SmMultipleDependency {
        @objid ("61f29cc9-d300-44ea-892b-127750f1ef45")
        private SmDependency symetricDep;

        @objid ("ad12122b-e01a-4fd5-9a8c-1492bbd6be4d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mOwnedImport != null)? ((OperationData)data).mOwnedImport:SmMultipleDependency.EMPTY;
        }

        @objid ("003d217a-61a5-43f3-859d-23aad9a69f3e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mOwnedImport = values;
            
        }

        @objid ("1fb0cde3-2108-43a6-8e27-7f8e32a95908")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ElementImportSmClass)this.getTarget()).getImportingOperationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("ab875c73-915b-46bf-beb3-2152915d67e3")
    public static class ThrownSmDependency extends SmMultipleDependency {
        @objid ("bbbdd9bc-ac0e-421d-ac5b-57486fd5de36")
        private SmDependency symetricDep;

        @objid ("0283b6bb-7fe5-4462-ad4d-9f3afba38c8d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mThrown != null)? ((OperationData)data).mThrown:SmMultipleDependency.EMPTY;
        }

        @objid ("1d5f9382-33f2-4283-befe-c9c742e6ee18")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mThrown = values;
            
        }

        @objid ("8e100497-94c5-49f8-9967-4b0c59c585a0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RaisedExceptionSmClass)this.getTarget()).getThrowerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("0a834e8c-f893-44ef-bdce-4fc91425a58b")
    public static class RedefinitionSmDependency extends SmMultipleDependency {
        @objid ("521f3e1f-5a5b-4b57-a1cb-095a1e8092c3")
        private SmDependency symetricDep;

        @objid ("d0c0210e-3856-49e3-9649-51f011268643")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mRedefinition != null)? ((OperationData)data).mRedefinition:SmMultipleDependency.EMPTY;
        }

        @objid ("d45c9b57-06a2-4704-8a16-a297801da449")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mRedefinition = values;
            
        }

        @objid ("4a60fbb9-7815-470d-bbe7-b35e9fa2400f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getRedefinesDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("60915d0a-8116-4a27-b704-43bb4a9d9ed0")
    public static class ExampleSmDependency extends SmMultipleDependency {
        @objid ("6e8caf47-a2a8-4823-b533-997acb9c609c")
        private SmDependency symetricDep;

        @objid ("fbb9edf9-16ad-4733-afe1-e480ba0d7654")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mExample != null)? ((OperationData)data).mExample:SmMultipleDependency.EMPTY;
        }

        @objid ("cbe34c38-ea9c-4167-9222-88a6cb9015e2")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mExample = values;
            
        }

        @objid ("df2fd59a-2e16-4505-8c22-d55946c57d58")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CollaborationSmClass)this.getTarget()).getORepresentedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("4a8de98c-99d5-44b5-9b86-ad6207628d43")
    public static class SRepresentationSmDependency extends SmMultipleDependency {
        @objid ("a4ef9537-dfa9-40f3-9393-be0d1790dad0")
        private SmDependency symetricDep;

        @objid ("44214b08-914a-45d8-8deb-8f56bdcc3a06")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mSRepresentation != null)? ((OperationData)data).mSRepresentation:SmMultipleDependency.EMPTY;
        }

        @objid ("66e896d0-1972-40ee-8021-eb4e94fd2866")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mSRepresentation = values;
            
        }

        @objid ("7e46a0b3-336a-47f0-8929-366c2d0fbfff")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getOBaseDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("e29cea00-7756-4e46-b1a3-86c6623aa618")
    public static class OwnedBehaviorSmDependency extends SmMultipleDependency {
        @objid ("698e7ad3-e130-45d4-bde6-fe590e2715f1")
        private SmDependency symetricDep;

        @objid ("d8bc554d-25f6-48bc-9947-f8c0d0f26f0e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mOwnedBehavior != null)? ((OperationData)data).mOwnedBehavior:SmMultipleDependency.EMPTY;
        }

        @objid ("a428aff8-deb9-46cb-a472-ac16f4586d3e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mOwnedBehavior = values;
            
        }

        @objid ("96c190f7-71cc-4323-a83c-beb0fa7057de")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorSmClass)this.getTarget()).getOwnerOperationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("3b718d45-779d-4646-ab3e-3c4994d65f48")
    public static class IOSmDependency extends SmMultipleDependency {
        @objid ("282dbe53-3737-435b-968d-8edbeeb6ae5c")
        private SmDependency symetricDep;

        @objid ("0d444b04-3d2b-4238-ba6d-218c23317937")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mIO != null)? ((OperationData)data).mIO:SmMultipleDependency.EMPTY;
        }

        @objid ("3b935b86-dc99-4192-b17b-1dce3e82cbe1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mIO = values;
            
        }

        @objid ("bdb3c44a-7b3b-4744-bb85-8b6c4dcc40e2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ParameterSmClass)this.getTarget()).getComposedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("76bd60b7-dda2-4ba3-9844-66c17157bce6")
    public static class TemplateInstanciationSmDependency extends SmMultipleDependency {
        @objid ("b153e17c-b73a-43be-b2e3-dda37d8bcadf")
        private SmDependency symetricDep;

        @objid ("7b4bd75c-ea1e-4180-af81-a9466d39a46a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mTemplateInstanciation != null)? ((OperationData)data).mTemplateInstanciation:SmMultipleDependency.EMPTY;
        }

        @objid ("f472256d-97a7-4048-8f5e-50a6ddbb5aaa")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mTemplateInstanciation = values;
            
        }

        @objid ("2aa73f51-6812-4805-b5c6-bc9a295db8bc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateBindingSmClass)this.getTarget()).getBoundOperationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("04963292-b6ff-4ff5-9a9f-3f186b3bfd5e")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("f0a47a3f-afef-4570-9ca3-f2d4dc8f7b92")
        private SmDependency symetricDep;

        @objid ("d1f5a5c3-1864-4dff-be4c-5715964749cb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((OperationData) data).mOwner;
        }

        @objid ("75f4b6b0-daad-4fa0-ab56-ed25b17787bb")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((OperationData) data).mOwner = value;
        }

        @objid ("cded7f95-6708-478e-895b-93aff70b3bd9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getOwnedOperationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("3429a6f5-ec0a-41bc-9dd2-c2c3469a7d88")
    public static class OwnedPackageImportSmDependency extends SmMultipleDependency {
        @objid ("23d978a2-7080-43ca-8fbd-275f5cd29c61")
        private SmDependency symetricDep;

        @objid ("5d8fb20b-f179-4d03-aa05-4c96f95e580f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mOwnedPackageImport != null)? ((OperationData)data).mOwnedPackageImport:SmMultipleDependency.EMPTY;
        }

        @objid ("6e71064d-8f1e-4da7-85eb-fd02bc72f30a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mOwnedPackageImport = values;
            
        }

        @objid ("c75c6783-c957-488d-b6d0-e53c2fb6f623")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageImportSmClass)this.getTarget()).getImportingOperationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("ca88d1b4-db75-4ced-ad12-48c9947f2cd6")
    public static class ReturnSmDependency extends SmSingleDependency {
        @objid ("842aeb2b-4268-44ea-8ae7-94d0f741991e")
        private SmDependency symetricDep;

        @objid ("907fa5d4-84cd-4403-9884-00353812c923")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((OperationData) data).mReturn;
        }

        @objid ("ddf89896-0bca-48a6-b3c2-868b81bb8d7a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((OperationData) data).mReturn = value;
        }

        @objid ("fbd3a07d-96c7-4702-ae28-7dff364eece6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ParameterSmClass)this.getTarget()).getReturnedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("1554b2bd-e156-49d0-8545-4062a1bb73fa")
    public static class InstanciatingBindingSmDependency extends SmMultipleDependency {
        @objid ("4ad2abcc-cc35-4105-bb24-dd41b11b3bab")
        private SmDependency symetricDep;

        @objid ("5a5db695-e779-4979-a8f6-4fd3d38d53bd")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mInstanciatingBinding != null)? ((OperationData)data).mInstanciatingBinding:SmMultipleDependency.EMPTY;
        }

        @objid ("252d78c7-a767-4f8a-8c69-320028fa9d05")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mInstanciatingBinding = values;
            
        }

        @objid ("86fefea1-d52d-496c-b775-e577ee576fcd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateBindingSmClass)this.getTarget()).getInstanciatedTemplateOperationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("0c4c1b2f-aa4f-49c8-a27e-4b85f3e987b8")
    public static class UsageSmDependency extends SmMultipleDependency {
        @objid ("b8c19ece-14b6-4c08-a3b6-4e1f8172d2bf")
        private SmDependency symetricDep;

        @objid ("df581cad-aab1-40ad-b548-0abc0ee216bd")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mUsage != null)? ((OperationData)data).mUsage:SmMultipleDependency.EMPTY;
        }

        @objid ("04fee453-42db-48cd-a4ca-0cacb79ded52")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mUsage = values;
            
        }

        @objid ("3ca36f3e-075e-493d-8a38-2f5966a297b0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageSmClass)this.getTarget()).getInvokedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c4f3b18b-8f70-4bf9-99f4-00f832451fff")
    public static class TemplateSmDependency extends SmMultipleDependency {
        @objid ("1fdc0973-c6e7-416f-b29a-b1fda6a320b6")
        private SmDependency symetricDep;

        @objid ("6b92377a-32fe-4f93-8abe-d7671b2b5910")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mTemplate != null)? ((OperationData)data).mTemplate:SmMultipleDependency.EMPTY;
        }

        @objid ("c3138385-c7ed-457f-938e-fa56732437b6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mTemplate = values;
            
        }

        @objid ("3283748d-1289-465e-8a47-ea519879f621")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSmClass)this.getTarget()).getParameterizedOperationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("6e716761-7481-4f11-9d31-6e66884488ae")
    public static class OccurenceSmDependency extends SmMultipleDependency {
        @objid ("5c04bcb3-ab69-4f43-97d0-399bb054798d")
        private SmDependency symetricDep;

        @objid ("2be9f37d-0813-4631-b848-71d4913604d1")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mOccurence != null)? ((OperationData)data).mOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("c0e3d0ab-d31a-4462-a379-fcfb117b96df")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mOccurence = values;
            
        }

        @objid ("210d6f7a-4a54-486c-9ea4-f8aa531f7cab")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EventSmClass)this.getTarget()).getCalledDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("5e106519-07f7-4957-afa2-be9f96b77191")
    public static class InvokerSmDependency extends SmMultipleDependency {
        @objid ("80f01a03-5347-4e05-bae2-117279798a41")
        private SmDependency symetricDep;

        @objid ("a8697d03-a3b1-4b54-b26b-c1cf93307f81")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mInvoker != null)? ((OperationData)data).mInvoker:SmMultipleDependency.EMPTY;
        }

        @objid ("ec5ae4f9-00ed-4ede-8942-33d3d70d86db")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mInvoker = values;
            
        }

        @objid ("7a102837-bcff-418d-9451-8cb586505680")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TransitionSmClass)this.getTarget()).getProcessedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("ac03716b-0820-413e-9a0f-f210c825ab9a")
    public static class CommunicationUsageSmDependency extends SmMultipleDependency {
        @objid ("1ff012b4-f6fe-4e71-9c69-aa9850aaa340")
        private SmDependency symetricDep;

        @objid ("89de71df-48fa-4904-b1c9-d0a810daec67")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mCommunicationUsage != null)? ((OperationData)data).mCommunicationUsage:SmMultipleDependency.EMPTY;
        }

        @objid ("f4bf60b3-7546-4dfe-a9c2-bbdc57178a0f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mCommunicationUsage = values;
            
        }

        @objid ("11c0767d-c9a2-4363-b803-771244a5edf5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationMessageSmClass)this.getTarget()).getInvokedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("b1217edd-f868-48d4-b6e8-c5798c6a7e35")
    public static class OwnedCollaborationUseSmDependency extends SmMultipleDependency {
        @objid ("1f5e0541-95f2-444c-a531-ee72311d446b")
        private SmDependency symetricDep;

        @objid ("338e3908-c5d5-42e1-91c3-aaa5d85b7115")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mOwnedCollaborationUse != null)? ((OperationData)data).mOwnedCollaborationUse:SmMultipleDependency.EMPTY;
        }

        @objid ("5bbde6ce-5e9d-4622-973b-01c6dc0874f7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mOwnedCollaborationUse = values;
            
        }

        @objid ("1048ec95-21d0-485e-b0b3-84a2d084b9ce")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CollaborationUseSmClass)this.getTarget()).getORepresentedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("6010358c-fa56-4d8f-a3f5-08a92433bf2d")
    public static class RedefinesSmDependency extends SmSingleDependency {
        @objid ("7a420431-7c64-4a40-921a-5ae2ef811966")
        private SmDependency symetricDep;

        @objid ("d1b0e0ff-18de-4e29-b536-7f7d6edd74cb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((OperationData) data).mRedefines;
        }

        @objid ("8ed943c7-af65-447b-8040-4cc3992b485d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((OperationData) data).mRedefines = value;
        }

        @objid ("03de0c4a-87cb-4142-9d09-1905fce6197a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getRedefinitionDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("a246685e-73bf-444f-85a6-3273229fc2a1")
    public static class CallingActionSmDependency extends SmMultipleDependency {
        @objid ("2d97f539-361f-491a-80a4-2efbc44aced2")
        private SmDependency symetricDep;

        @objid ("19638e4e-9f23-4027-b35c-a895f03a1f85")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mCallingAction != null)? ((OperationData)data).mCallingAction:SmMultipleDependency.EMPTY;
        }

        @objid ("7c87de2d-e43b-4a73-9f83-eef6b66d9dc1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mCallingAction = values;
            
        }

        @objid ("022d38db-9cc3-4f80-b944-84bcd3a4510c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CallOperationActionSmClass)this.getTarget()).getCalledDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("3ad28c8c-9723-4e2f-b10a-1da36dda3d4e")
    public static class EntryPointActionSmDependency extends SmMultipleDependency {
        @objid ("80105d17-5bba-4a05-a516-c7e7efa7ec6c")
        private SmDependency symetricDep;

        @objid ("b7b218be-1fed-4e40-9f15-9b953e8e95af")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OperationData)data).mEntryPointAction != null)? ((OperationData)data).mEntryPointAction:SmMultipleDependency.EMPTY;
        }

        @objid ("5e44f289-85f5-45db-8cb3-94b6f6c3437a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OperationData) data).mEntryPointAction = values;
            
        }

        @objid ("a6e412cf-e3e0-4401-8980-7a90d0f62455")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AcceptCallEventActionSmClass)this.getTarget()).getCalledDep();
            }
            return this.symetricDep;
            
        }

    }

}
