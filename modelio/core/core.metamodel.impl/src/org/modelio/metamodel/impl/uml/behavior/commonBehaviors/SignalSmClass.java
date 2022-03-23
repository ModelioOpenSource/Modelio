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

package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptSignalActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.SendSignalActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationMessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TransitionSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.DataFlowSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.impl.uml.statik.ParameterSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
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

@objid ("c89cedee-1c4a-4e97-82b0-fd0e6622b708")
public class SignalSmClass extends GeneralClassSmClass {
    @objid ("9849ea87-9987-4925-ad80-f6e3cc23b5eb")
    private SmAttribute isEventAtt;

    @objid ("8a926648-dd19-466e-a00a-c55a3c282610")
    private SmAttribute isExceptionAtt;

    @objid ("95e0451b-cbd8-4043-930f-550ac22209c0")
    private SmDependency senderDep;

    @objid ("cee854d2-329d-4ce5-9bbf-fed598764f45")
    private SmDependency usageDep;

    @objid ("a9d97334-f3b4-4493-b12c-f2e700f8c2f5")
    private SmDependency sendsDep;

    @objid ("cc27f47d-c5f0-47ab-b736-83ebe84e8b15")
    private SmDependency pBaseDep;

    @objid ("e5fda3c3-157e-42c9-85ed-b619993dafde")
    private SmDependency oBaseDep;

    @objid ("bd18a244-19e5-474c-b294-9343c4a4cd6b")
    private SmDependency communicationUsageDep;

    @objid ("15652ea1-dcee-4890-8742-182073e528cd")
    private SmDependency dOccurenceDep;

    @objid ("82fbf610-6976-484e-83df-64cd1d160933")
    private SmDependency eOccurenceDep;

    @objid ("57917a2e-b237-4781-b486-f58c28848b18")
    private SmDependency baseDep;

    @objid ("192b4495-17d8-433a-972f-b6e3facbef39")
    private SmDependency receiverDep;

    @objid ("c969b508-26b8-4afe-bca4-c6e22fbd0fa2")
    public  SignalSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1736c3ba-c168-47a1-9caf-597d8e272fa6")
    @Override
    public String getName() {
        return "Signal";
        
    }

    @objid ("bad05428-c0cd-4281-8f47-6e1021ede38d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f7a8eae4-59aa-449e-8d20-49ae1f6ad3d5")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Signal.class;
        
    }

    @objid ("655fccf5-5d71-4d76-82a3-b334d66e0cd5")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("85d27296-94f9-41ee-a4a1-29449aeea0c1")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("63c0ae62-f70e-4203-b1ba-b8d4b6ac5c68")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new SignalObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isEventAtt = new IsEventSmAttribute();
        this.isEventAtt.init("IsEvent", this, Boolean.class );
        registerAttribute(this.isEventAtt);
        
        this.isExceptionAtt = new IsExceptionSmAttribute();
        this.isExceptionAtt.init("IsException", this, Boolean.class );
        registerAttribute(this.isExceptionAtt);
        
        
        // Initialize and register the SmDependency
        this.senderDep = new SenderSmDependency();
        this.senderDep.init("Sender", this, metamodel.getMClass(SendSignalAction.MQNAME), 0, -1 );
        registerDependency(this.senderDep);
        
        this.usageDep = new UsageSmDependency();
        this.usageDep.init("Usage", this, metamodel.getMClass(Message.MQNAME), 0, -1 );
        registerDependency(this.usageDep);
        
        this.sendsDep = new SendsSmDependency();
        this.sendsDep.init("Sends", this, metamodel.getMClass(Transition.MQNAME), 0, -1 );
        registerDependency(this.sendsDep);
        
        this.pBaseDep = new PBaseSmDependency();
        this.pBaseDep.init("PBase", this, metamodel.getMClass(Parameter.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.pBaseDep);
        
        this.oBaseDep = new OBaseSmDependency();
        this.oBaseDep.init("OBase", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.oBaseDep);
        
        this.communicationUsageDep = new CommunicationUsageSmDependency();
        this.communicationUsageDep.init("CommunicationUsage", this, metamodel.getMClass(CommunicationMessage.MQNAME), 0, -1 );
        registerDependency(this.communicationUsageDep);
        
        this.dOccurenceDep = new DOccurenceSmDependency();
        this.dOccurenceDep.init("DOccurence", this, metamodel.getMClass(DataFlow.MQNAME), 0, -1 );
        registerDependency(this.dOccurenceDep);
        
        this.eOccurenceDep = new EOccurenceSmDependency();
        this.eOccurenceDep.init("EOccurence", this, metamodel.getMClass(Event.MQNAME), 0, -1 );
        registerDependency(this.eOccurenceDep);
        
        this.baseDep = new BaseSmDependency();
        this.baseDep.init("Base", this, metamodel.getMClass(GeneralClass.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.baseDep);
        
        this.receiverDep = new ReceiverSmDependency();
        this.receiverDep.init("Receiver", this, metamodel.getMClass(AcceptSignalAction.MQNAME), 0, -1 );
        registerDependency(this.receiverDep);
        
        
    }

    @objid ("bcf023ce-9d9b-43ae-9bd2-fca0646fcc8f")
    public SmAttribute getIsEventAtt() {
        if (this.isEventAtt == null) {
        	this.isEventAtt = this.getAttributeDef("IsEvent");
        }
        return this.isEventAtt;
    }

    @objid ("fe5427e4-76db-48af-897e-c2cd8023bc77")
    public SmAttribute getIsExceptionAtt() {
        if (this.isExceptionAtt == null) {
        	this.isExceptionAtt = this.getAttributeDef("IsException");
        }
        return this.isExceptionAtt;
    }

    @objid ("21794b25-f98b-4795-b817-0a4049bd4b59")
    public SmDependency getSenderDep() {
        if (this.senderDep == null) {
        	this.senderDep = this.getDependencyDef("Sender");
        }
        return this.senderDep;
    }

    @objid ("4f9a337f-0014-4e21-885f-17f76e85de8a")
    public SmDependency getUsageDep() {
        if (this.usageDep == null) {
        	this.usageDep = this.getDependencyDef("Usage");
        }
        return this.usageDep;
    }

    @objid ("43de0324-05d0-4eb2-8d19-6edafa653de8")
    public SmDependency getSendsDep() {
        if (this.sendsDep == null) {
        	this.sendsDep = this.getDependencyDef("Sends");
        }
        return this.sendsDep;
    }

    @objid ("005cf6e1-fbb9-4a72-b36f-467570ec663c")
    public SmDependency getPBaseDep() {
        if (this.pBaseDep == null) {
        	this.pBaseDep = this.getDependencyDef("PBase");
        }
        return this.pBaseDep;
    }

    @objid ("808aa5b1-8721-416b-bacc-b9fe20212052")
    public SmDependency getOBaseDep() {
        if (this.oBaseDep == null) {
        	this.oBaseDep = this.getDependencyDef("OBase");
        }
        return this.oBaseDep;
    }

    @objid ("53545c16-4d47-4d70-b726-74410557beea")
    public SmDependency getCommunicationUsageDep() {
        if (this.communicationUsageDep == null) {
        	this.communicationUsageDep = this.getDependencyDef("CommunicationUsage");
        }
        return this.communicationUsageDep;
    }

    @objid ("153f86a8-5bdb-4057-b57f-8c3806b48f66")
    public SmDependency getDOccurenceDep() {
        if (this.dOccurenceDep == null) {
        	this.dOccurenceDep = this.getDependencyDef("DOccurence");
        }
        return this.dOccurenceDep;
    }

    @objid ("153bc394-769f-4cbd-b77a-bf5a6bdf51e1")
    public SmDependency getEOccurenceDep() {
        if (this.eOccurenceDep == null) {
        	this.eOccurenceDep = this.getDependencyDef("EOccurence");
        }
        return this.eOccurenceDep;
    }

    @objid ("8cd18528-975c-4e93-a979-3dd2337ba381")
    public SmDependency getBaseDep() {
        if (this.baseDep == null) {
        	this.baseDep = this.getDependencyDef("Base");
        }
        return this.baseDep;
    }

    @objid ("18e756ef-5a4d-464e-b218-e2ccf93f308f")
    public SmDependency getReceiverDep() {
        if (this.receiverDep == null) {
        	this.receiverDep = this.getDependencyDef("Receiver");
        }
        return this.receiverDep;
    }

    @objid ("3ba2430c-09d1-4946-9777-83401ad9c533")
    private static class SignalObjectFactory implements ISmObjectFactory {
        @objid ("919565b5-87fd-4e52-818f-81e0d7d348fa")
        private SignalSmClass smClass;

        @objid ("826b1caa-b541-4b73-b5c0-ec696faae1b2")
        public  SignalObjectFactory(SignalSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("61c9495b-9a73-402f-8975-9d9e08297ef1")
        @Override
        public ISmObjectData createData() {
            return new SignalData(this.smClass);
        }

        @objid ("145ae0b2-e9d1-4b88-8d77-44bed937da74")
        @Override
        public SmObjectImpl createImpl() {
            return new SignalImpl();
        }

    }

    @objid ("a56c3c15-9342-4747-b0ca-e298b7138701")
    public static class IsEventSmAttribute extends SmAttribute {
        @objid ("06d11cb9-471c-4256-81ae-8b162488f37c")
        public Object getValue(ISmObjectData data) {
            return ((SignalData) data).mIsEvent;
        }

        @objid ("87b9d28f-050b-4a11-bc1a-8676ad118cdb")
        public void setValue(ISmObjectData data, Object value) {
            ((SignalData) data).mIsEvent = value;
        }

    }

    @objid ("f4ee493b-ad84-422e-9a01-48c812f6db66")
    public static class IsExceptionSmAttribute extends SmAttribute {
        @objid ("44152740-e565-4e60-901b-933e71eb4b58")
        public Object getValue(ISmObjectData data) {
            return ((SignalData) data).mIsException;
        }

        @objid ("3fcecc4c-ded7-4833-88be-4bbad40fbd56")
        public void setValue(ISmObjectData data, Object value) {
            ((SignalData) data).mIsException = value;
        }

    }

    @objid ("6d9d7aa0-6e68-49bd-a7a1-1fad7a56d11c")
    public static class SenderSmDependency extends SmMultipleDependency {
        @objid ("6001c468-61c2-40f5-ac61-430bd44ebc60")
        private SmDependency symetricDep;

        @objid ("87aa7768-5bb5-471f-832a-11b543dfae09")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((SignalData)data).mSender != null)? ((SignalData)data).mSender:SmMultipleDependency.EMPTY;
        }

        @objid ("73f51d05-53f8-4f6c-952c-10520664b961")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((SignalData) data).mSender = values;
            
        }

        @objid ("795292fa-09c6-4767-9f01-c006af3278fb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SendSignalActionSmClass)this.getTarget()).getSentDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("57404396-1af6-4b15-8ce1-fcf10ef2e48f")
    public static class UsageSmDependency extends SmMultipleDependency {
        @objid ("a39115ec-fe2d-489c-b46a-8dd02e9bc58a")
        private SmDependency symetricDep;

        @objid ("2b43cf87-4478-4848-b020-06f85bc3cd4f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((SignalData)data).mUsage != null)? ((SignalData)data).mUsage:SmMultipleDependency.EMPTY;
        }

        @objid ("e613b928-89a1-41c8-b7a8-87d36d7d8d08")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((SignalData) data).mUsage = values;
            
        }

        @objid ("dc90d792-1064-4d16-8d24-01fdd4beef50")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageSmClass)this.getTarget()).getSignalSignatureDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("ba32e524-19b6-46b7-b166-5f67b54c7051")
    public static class SendsSmDependency extends SmMultipleDependency {
        @objid ("ce8292a8-d087-4223-8e40-84489a6f97da")
        private SmDependency symetricDep;

        @objid ("538943a6-1aa9-4b59-a786-f67c85bf39f5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((SignalData)data).mSends != null)? ((SignalData)data).mSends:SmMultipleDependency.EMPTY;
        }

        @objid ("6ea69559-2ed1-414b-b99a-be9996d4418e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((SignalData) data).mSends = values;
            
        }

        @objid ("7325b16b-d417-4072-8b2c-0a39c27fd5c4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TransitionSmClass)this.getTarget()).getEffectsDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("77b841b6-d61e-43e2-83ac-11e9787b52d2")
    public static class PBaseSmDependency extends SmSingleDependency {
        @objid ("7ffaacfe-20de-4f0a-838f-961a98318eb9")
        private SmDependency symetricDep;

        @objid ("f6fef338-a8af-465f-87b9-5b9044425a57")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((SignalData) data).mPBase;
        }

        @objid ("a4998d84-3539-4ba7-9298-99a889b247d7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((SignalData) data).mPBase = value;
        }

        @objid ("b4c19d90-c885-4d24-bde0-ab486fe7a053")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ParameterSmClass)this.getTarget()).getSRepresentationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("f9c16009-4dc4-449c-8c18-daf7c524dd2d")
    public static class OBaseSmDependency extends SmSingleDependency {
        @objid ("8501d8ed-f2b6-4804-bb48-ed9c47c60468")
        private SmDependency symetricDep;

        @objid ("4fca7fb1-379e-459f-b64d-259295b1da4c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((SignalData) data).mOBase;
        }

        @objid ("3232ce03-b64f-46d7-a699-f401d02330d7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((SignalData) data).mOBase = value;
        }

        @objid ("5956e2bc-c5c1-43ec-8771-f73e2079c7a8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getSRepresentationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c72144a3-3ab3-43e9-b468-edb35a23b062")
    public static class CommunicationUsageSmDependency extends SmMultipleDependency {
        @objid ("d38eddd6-31d8-4d5c-8276-e57e5fbf2f35")
        private SmDependency symetricDep;

        @objid ("771f4ab5-84e2-4a82-8e08-ca0594578896")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((SignalData)data).mCommunicationUsage != null)? ((SignalData)data).mCommunicationUsage:SmMultipleDependency.EMPTY;
        }

        @objid ("bc041bd8-6a90-4718-80e5-c3bf1a02e2da")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((SignalData) data).mCommunicationUsage = values;
            
        }

        @objid ("13f50404-eb80-4833-93dc-3d4f7f878aa5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationMessageSmClass)this.getTarget()).getSignalSignatureDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("549e91d1-cb9b-4bc1-8e09-779123cb3322")
    public static class DOccurenceSmDependency extends SmMultipleDependency {
        @objid ("d62ba640-6bb5-4c7f-91b3-0eaf57b25e23")
        private SmDependency symetricDep;

        @objid ("9fb0e6e0-d6fb-48f8-8af4-5e6a3d8de1fb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((SignalData)data).mDOccurence != null)? ((SignalData)data).mDOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("817df087-736b-4a68-8ca9-64c7c1601307")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((SignalData) data).mDOccurence = values;
            
        }

        @objid ("e0999cc8-1fed-4ab9-889b-30435d6bb604")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DataFlowSmClass)this.getTarget()).getSModelDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("6a55a261-acef-47c2-a2eb-95fb6140afc3")
    public static class EOccurenceSmDependency extends SmMultipleDependency {
        @objid ("1fd7b80d-19e1-4f87-ab48-c25e034d1a37")
        private SmDependency symetricDep;

        @objid ("3ac09e7b-096a-429a-a0e8-6126ba44a1b5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((SignalData)data).mEOccurence != null)? ((SignalData)data).mEOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("ad9fdd08-6604-422e-9111-063c12e08788")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((SignalData) data).mEOccurence = values;
            
        }

        @objid ("083bc365-1c74-4ff8-9f37-690e53043569")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EventSmClass)this.getTarget()).getModelDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("4dc26df6-6355-4b79-abfb-358ad21a5b81")
    public static class BaseSmDependency extends SmSingleDependency {
        @objid ("853280bd-9760-492c-9456-cc486f4427b3")
        private SmDependency symetricDep;

        @objid ("a4f3f259-b947-48b2-9362-9696565b50df")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((SignalData) data).mBase;
        }

        @objid ("f4267b79-4582-47ae-b2f7-efbf3de6dcb7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((SignalData) data).mBase = value;
        }

        @objid ("4459990b-7276-47d3-9385-63cf9b4e2bd7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralClassSmClass)this.getTarget()).getSRepresentationDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("4984f7b2-1a0b-4cf0-a300-ec0a49611c17")
    public static class ReceiverSmDependency extends SmMultipleDependency {
        @objid ("bb318008-97eb-4f7b-b01a-94d5e398568a")
        private SmDependency symetricDep;

        @objid ("c89f91ac-0951-4891-b4e7-d437a96325a5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((SignalData)data).mReceiver != null)? ((SignalData)data).mReceiver:SmMultipleDependency.EMPTY;
        }

        @objid ("6b3c6695-f1a4-48c6-9dfa-7d47b0557a62")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((SignalData) data).mReceiver = values;
            
        }

        @objid ("19ad560c-8f3f-431a-8c12-24324258e6f2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AcceptSignalActionSmClass)this.getTarget()).getAcceptedDep();
            }
            return this.symetricDep;
            
        }

    }

}
