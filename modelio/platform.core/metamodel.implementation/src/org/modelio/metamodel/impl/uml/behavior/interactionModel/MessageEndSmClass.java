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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageEndData;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.OccurrenceSpecificationSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("aa51d4fc-b7d0-45a9-94c0-0bca595a6371")
public class MessageEndSmClass extends OccurrenceSpecificationSmClass {
    @objid ("b9d1edc0-fd6f-42a0-8595-7bc8ae2e9065")
    private SmDependency receivedMessageDep;

    @objid ("b1b5584a-4301-47b1-b87d-9b5b6cdc69c5")
    private SmDependency sentMessageDep;

    @objid ("0e08571c-058c-4a6a-8a63-843fd2ba0f76")
    public MessageEndSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a75dd0de-eedb-446a-8644-15dce06bf02e")
    @Override
    public String getName() {
        return "MessageEnd";
    }

    @objid ("247c0c40-31bb-44bd-a59e-45b27ae450ee")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6bd8ab33-3fe9-4295-8f07-4310ff405c25")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MessageEnd.class;
    }

    @objid ("0568206b-2f98-4b03-b64a-603144e30fbc")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("7ba4d514-9bb5-40e2-8489-22119c16856b")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("97390644-ecc7-49eb-b22e-855fed3665c0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(OccurrenceSpecification.MQNAME);
        this.registerFactory(new MessageEndObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.receivedMessageDep = new ReceivedMessageSmDependency();
        this.receivedMessageDep.init("ReceivedMessage", this, metamodel.getMClass(Message.MQNAME), 0, 1 , SmDirective.SMCDTODELETE);
        registerDependency(this.receivedMessageDep);
        
        this.sentMessageDep = new SentMessageSmDependency();
        this.sentMessageDep.init("SentMessage", this, metamodel.getMClass(Message.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT, SmDirective.SMCDTODELETE);
        registerDependency(this.sentMessageDep);
    }

    @objid ("028f1485-fbe9-4400-bea0-1dae9725b188")
    public SmDependency getReceivedMessageDep() {
        if (this.receivedMessageDep == null) {
        	this.receivedMessageDep = this.getDependencyDef("ReceivedMessage");
        }
        return this.receivedMessageDep;
    }

    @objid ("08bf6865-56fe-4787-98dd-02bde20d4860")
    public SmDependency getSentMessageDep() {
        if (this.sentMessageDep == null) {
        	this.sentMessageDep = this.getDependencyDef("SentMessage");
        }
        return this.sentMessageDep;
    }

    @objid ("6223b571-1ce9-40dd-a299-6dbe87484ea4")
    private static class MessageEndObjectFactory implements ISmObjectFactory {
        @objid ("e6c4a297-b468-4cff-a14c-7b3c25e5b66b")
        private MessageEndSmClass smClass;

        @objid ("3ce7c654-1c11-44d4-b76a-640667f2ef16")
        public MessageEndObjectFactory(MessageEndSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b5f288ab-5d98-4dfe-99be-8c348b484a58")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("04a30bb7-94d1-4982-8e3a-6c78eff89f2b")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("3c94d91a-5f2f-4e14-9002-b749bd05c5bf")
    public static class ReceivedMessageSmDependency extends SmSingleDependency {
        @objid ("2166f9fc-2a9b-4b24-b4f9-280d7bd316c3")
        private SmDependency symetricDep;

        @objid ("748ddc3c-4193-417b-beef-1e343f53fb22")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageEndData) data).mReceivedMessage;
        }

        @objid ("c4e21dbc-4249-4d85-b2a3-a0d29bb865ea")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageEndData) data).mReceivedMessage = value;
        }

        @objid ("0dedbe78-093d-45f5-9f7d-a93f7abfcdd3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageSmClass)this.getTarget()).getReceiveEventDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("4d53d6ec-d844-4a1b-a412-2db9fdaf61b3")
    public static class SentMessageSmDependency extends SmSingleDependency {
        @objid ("9b523510-39f4-4855-a59e-d61a7204aebc")
        private SmDependency symetricDep;

        @objid ("fc64ebc1-ce76-46ce-b626-e693daa4a961")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageEndData) data).mSentMessage;
        }

        @objid ("a557cc9c-eb68-45ab-addf-1c2d134bc528")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageEndData) data).mSentMessage = value;
        }

        @objid ("c129c23a-6a2d-4874-b34e-e9cc23397e92")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MessageSmClass)this.getTarget()).getSendEventDep();
            }
            return this.symetricDep;
        }

    }

}
