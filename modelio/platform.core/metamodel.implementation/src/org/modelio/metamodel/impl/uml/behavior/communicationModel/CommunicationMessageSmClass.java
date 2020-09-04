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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.behavior.communicationModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationChannelSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationMessageData;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Operation;
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

@objid ("38d34116-8316-413f-93d4-e2b72097ff42")
public class CommunicationMessageSmClass extends UmlModelElementSmClass {
    @objid ("131beef5-02f6-4be4-939a-e1ff793cb014")
    private SmAttribute argumentAtt;

    @objid ("84df3223-a54d-452a-979f-58077b12330e")
    private SmAttribute sequenceAtt;

    @objid ("8aac7636-080a-448e-b837-4355fb4b753f")
    private SmAttribute sortOfMessageAtt;

    @objid ("1548cd61-99ff-47ef-86d5-49dfbf94b60b")
    private SmDependency realizedInformationFlowDep;

    @objid ("66d03add-a033-4ca8-a225-897f4d863a2a")
    private SmDependency channelDep;

    @objid ("0758b10a-10f3-4f67-b526-7c2ab51de4f3")
    private SmDependency invertedChannelDep;

    @objid ("80b8d5b1-d32e-46c4-84b5-17a11af2d89d")
    private SmDependency invokedDep;

    @objid ("6a601633-2d81-48c9-8c9f-59684fdc25d0")
    private SmDependency signalSignatureDep;

    @objid ("57627a42-8087-4bef-9be9-19ed20871467")
    public CommunicationMessageSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0ddf3e4b-7535-4ed4-ac0b-85ebd819f920")
    @Override
    public String getName() {
        return "CommunicationMessage";
    }

    @objid ("b0fc468d-5420-4e59-af54-f1050388bedb")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("43dece46-760a-4099-a0c8-afaaff85204d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CommunicationMessage.class;
    }

    @objid ("e4c2e06b-4835-452e-bace-464b090b66fe")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("f51c6502-98fb-49bc-b2e5-896599f10dbd")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("2959e3ab-fdb6-48ea-a4e9-8cfadb8b5b48")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new CommunicationMessageObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.argumentAtt = new ArgumentSmAttribute();
        this.argumentAtt.init("Argument", this, String.class );
        registerAttribute(this.argumentAtt);
        
        this.sequenceAtt = new SequenceSmAttribute();
        this.sequenceAtt.init("Sequence", this, String.class );
        registerAttribute(this.sequenceAtt);
        
        this.sortOfMessageAtt = new SortOfMessageSmAttribute();
        this.sortOfMessageAtt.init("SortOfMessage", this, MessageSort.class );
        registerAttribute(this.sortOfMessageAtt);
        
        
        // Initialize and register the SmDependency
        this.realizedInformationFlowDep = new RealizedInformationFlowSmDependency();
        this.realizedInformationFlowDep.init("RealizedInformationFlow", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.realizedInformationFlowDep);
        
        this.channelDep = new ChannelSmDependency();
        this.channelDep.init("Channel", this, metamodel.getMClass(CommunicationChannel.MQNAME), 0, 1 );
        registerDependency(this.channelDep);
        
        this.invertedChannelDep = new InvertedChannelSmDependency();
        this.invertedChannelDep.init("InvertedChannel", this, metamodel.getMClass(CommunicationChannel.MQNAME), 0, 1 );
        registerDependency(this.invertedChannelDep);
        
        this.invokedDep = new InvokedSmDependency();
        this.invokedDep.init("Invoked", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.invokedDep);
        
        this.signalSignatureDep = new SignalSignatureSmDependency();
        this.signalSignatureDep.init("SignalSignature", this, metamodel.getMClass(Signal.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.signalSignatureDep);
    }

    @objid ("ce6ec395-071f-4f76-a5a0-5923c2fd983e")
    public SmAttribute getArgumentAtt() {
        if (this.argumentAtt == null) {
        	this.argumentAtt = this.getAttributeDef("Argument");
        }
        return this.argumentAtt;
    }

    @objid ("0c818413-14af-4ab6-a500-d7ccd9d5635c")
    public SmAttribute getSequenceAtt() {
        if (this.sequenceAtt == null) {
        	this.sequenceAtt = this.getAttributeDef("Sequence");
        }
        return this.sequenceAtt;
    }

    @objid ("c5db4fa2-d805-4214-b3b4-50395c9063f7")
    public SmAttribute getSortOfMessageAtt() {
        if (this.sortOfMessageAtt == null) {
        	this.sortOfMessageAtt = this.getAttributeDef("SortOfMessage");
        }
        return this.sortOfMessageAtt;
    }

    @objid ("154c3023-ad22-4bfb-bf17-7d3d86796ced")
    public SmDependency getRealizedInformationFlowDep() {
        if (this.realizedInformationFlowDep == null) {
        	this.realizedInformationFlowDep = this.getDependencyDef("RealizedInformationFlow");
        }
        return this.realizedInformationFlowDep;
    }

    @objid ("1bfab654-a26e-4a38-bae7-cca8603e2a85")
    public SmDependency getChannelDep() {
        if (this.channelDep == null) {
        	this.channelDep = this.getDependencyDef("Channel");
        }
        return this.channelDep;
    }

    @objid ("f8c57e2a-f1ab-485a-ade0-d8dc2823b0a1")
    public SmDependency getInvertedChannelDep() {
        if (this.invertedChannelDep == null) {
        	this.invertedChannelDep = this.getDependencyDef("InvertedChannel");
        }
        return this.invertedChannelDep;
    }

    @objid ("7e989558-4a05-44cb-9dfd-192eb0f4ed6f")
    public SmDependency getInvokedDep() {
        if (this.invokedDep == null) {
        	this.invokedDep = this.getDependencyDef("Invoked");
        }
        return this.invokedDep;
    }

    @objid ("ea7de4f5-1acd-424f-b2f0-88369c748a5d")
    public SmDependency getSignalSignatureDep() {
        if (this.signalSignatureDep == null) {
        	this.signalSignatureDep = this.getDependencyDef("SignalSignature");
        }
        return this.signalSignatureDep;
    }

    @objid ("57f7bece-681e-41bc-b876-0564537dd246")
    private static class CommunicationMessageObjectFactory implements ISmObjectFactory {
        @objid ("17829012-7ca9-42c8-a070-061c284679e6")
        private CommunicationMessageSmClass smClass;

        @objid ("5ba77d40-f787-4211-88ad-ecffb7442c70")
        public CommunicationMessageObjectFactory(CommunicationMessageSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f2f837b4-8633-4e89-9dc2-06af25feb31e")
        @Override
        public ISmObjectData createData() {
            return new CommunicationMessageData(this.smClass);
        }

        @objid ("19914e0a-d7b9-46a6-a42d-434eb5a84d24")
        @Override
        public SmObjectImpl createImpl() {
            return new CommunicationMessageImpl();
        }

    }

    @objid ("7a493fae-8188-4599-87c1-5638dabc6e0e")
    public static class ArgumentSmAttribute extends SmAttribute {
        @objid ("861d3a1c-b901-48d6-859a-c7d9a1ef0e8e")
        public Object getValue(ISmObjectData data) {
            return ((CommunicationMessageData) data).mArgument;
        }

        @objid ("39569ad5-08a1-47b2-95ee-20959c736fea")
        public void setValue(ISmObjectData data, Object value) {
            ((CommunicationMessageData) data).mArgument = value;
        }

    }

    @objid ("805fda18-72b8-4703-8616-0504f363d254")
    public static class SequenceSmAttribute extends SmAttribute {
        @objid ("207aef8d-f8bd-4ed4-a4d4-7aadfa60863c")
        public Object getValue(ISmObjectData data) {
            return ((CommunicationMessageData) data).mSequence;
        }

        @objid ("e439f1f5-ea22-4c26-95c1-5944b8d4554f")
        public void setValue(ISmObjectData data, Object value) {
            ((CommunicationMessageData) data).mSequence = value;
        }

    }

    @objid ("935b7300-9a10-4e44-b0ae-446688441128")
    public static class SortOfMessageSmAttribute extends SmAttribute {
        @objid ("a87c88ad-6ff8-4c45-ada2-8c88f155d1bd")
        public Object getValue(ISmObjectData data) {
            return ((CommunicationMessageData) data).mSortOfMessage;
        }

        @objid ("23e4e760-9cd0-4080-9a19-6a639754400c")
        public void setValue(ISmObjectData data, Object value) {
            ((CommunicationMessageData) data).mSortOfMessage = value;
        }

    }

    @objid ("41ad14ba-1fca-4cf5-9d0c-13ee9b774cc7")
    public static class RealizedInformationFlowSmDependency extends SmMultipleDependency {
        @objid ("65df23f1-919c-4528-9aa6-3a1c7ecf8595")
        private SmDependency symetricDep;

        @objid ("5c486de1-38ff-4051-ae25-6420eed33970")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CommunicationMessageData)data).mRealizedInformationFlow != null)? ((CommunicationMessageData)data).mRealizedInformationFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("0ca331b7-0fca-4f8e-addd-529c471bc95b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CommunicationMessageData) data).mRealizedInformationFlow = values;
        }

        @objid ("70faa728-d548-438e-bbce-000a11546af1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getRealizingCommunicationMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("409b728a-22e6-4d2f-9154-cdef287e381d")
    public static class ChannelSmDependency extends SmSingleDependency {
        @objid ("15d5aaa6-c044-4539-91db-1c62eba2347c")
        private SmDependency symetricDep;

        @objid ("12002e22-8a53-41ae-8c5e-61708f6fbb55")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationMessageData) data).mChannel;
        }

        @objid ("2eb6dffc-bd26-4ae5-abb2-b0a688878c3c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationMessageData) data).mChannel = value;
        }

        @objid ("24e61156-61ad-46fa-af6d-7b4dc6b8e550")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationChannelSmClass)this.getTarget()).getStartToEndMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e8244301-fd46-4ee8-95a5-de98d148b929")
    public static class InvertedChannelSmDependency extends SmSingleDependency {
        @objid ("98d0bf37-db45-4e63-8a14-b49208a2a919")
        private SmDependency symetricDep;

        @objid ("021b9b47-bb96-4440-94c8-34d5bac07b5f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationMessageData) data).mInvertedChannel;
        }

        @objid ("f1cdd2bf-03d5-47ca-9e9b-68175b7001bf")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationMessageData) data).mInvertedChannel = value;
        }

        @objid ("6b229612-9ece-4ae8-b569-cf79b15a1a6e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationChannelSmClass)this.getTarget()).getEndToStartMessageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("fda57ca2-39dc-4474-bafe-6e771c441114")
    public static class InvokedSmDependency extends SmSingleDependency {
        @objid ("c9676111-0245-4460-be7a-3e454371d0bb")
        private SmDependency symetricDep;

        @objid ("780e5d99-450e-46cb-a2f7-c299ff522dda")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationMessageData) data).mInvoked;
        }

        @objid ("fa18b05a-e76b-46b9-b695-088ce1f72b13")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationMessageData) data).mInvoked = value;
        }

        @objid ("c9b62c00-f037-4753-aaf8-38d8d0f0a5c8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getCommunicationUsageDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a36f5a35-7ca7-435b-a8e0-718e6c553bf0")
    public static class SignalSignatureSmDependency extends SmSingleDependency {
        @objid ("34288f88-c119-4900-9241-18328e92e5a1")
        private SmDependency symetricDep;

        @objid ("4dc135ca-bb7f-49a1-b80b-83df97f2cf69")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationMessageData) data).mSignalSignature;
        }

        @objid ("414749e0-4621-46d1-9f41-3e99a78903e3")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationMessageData) data).mSignalSignature = value;
        }

        @objid ("12a73e70-9a40-45d7-b0a0-ff7824670f9f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getCommunicationUsageDep();
            }
            return this.symetricDep;
        }

    }

}
