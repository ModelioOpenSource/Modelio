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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.flows;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageFlowData;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnCollaborationSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
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

@objid ("3eea7bbe-61b6-445e-80b8-5f370b8b7e4b")
public class BpmnMessageFlowSmClass extends BpmnBaseElementSmClass {
    @objid ("c9bfc900-db65-484f-8ee1-e10f5f46de2e")
    private SmDependency messageRefDep;

    @objid ("9b716166-6e40-416e-b167-35d5379d53c5")
    private SmDependency sourceRefDep;

    @objid ("58336c3a-cd8a-466b-a114-12a066d03195")
    private SmDependency targetRefDep;

    @objid ("6c939eb3-ef61-43e5-ad82-fe35c999f23e")
    private SmDependency collaborationDep;

    @objid ("6335467d-4257-4e5e-aaf6-2cc8a2e99822")
    public BpmnMessageFlowSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a5d6a2a8-2b77-4761-96a0-7415a4818ac4")
    @Override
    public String getName() {
        return "BpmnMessageFlow";
    }

    @objid ("8c99c34e-f57c-4e1d-9604-77b408f5a486")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ffb7441c-428a-48b9-b8f6-a6996e30aa33")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnMessageFlow.class;
    }

    @objid ("af56adfb-eaf4-4507-bb55-17112d8c0af4")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("dc555f4f-04d3-46ec-829e-e127bd78c6aa")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("c28a2dad-7203-484a-a62d-8d9e5c78783f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnMessageFlowObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.messageRefDep = new MessageRefSmDependency();
        this.messageRefDep.init("MessageRef", this, metamodel.getMClass(BpmnMessage.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.messageRefDep);
        
        this.sourceRefDep = new SourceRefSmDependency();
        this.sourceRefDep.init("SourceRef", this, metamodel.getMClass(BpmnBaseElement.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE, SmDirective.SMCDPARTOF);
        registerDependency(this.sourceRefDep);
        
        this.targetRefDep = new TargetRefSmDependency();
        this.targetRefDep.init("TargetRef", this, metamodel.getMClass(BpmnBaseElement.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetRefDep);
        
        this.collaborationDep = new CollaborationSmDependency();
        this.collaborationDep.init("Collaboration", this, metamodel.getMClass(BpmnCollaboration.MQNAME), 1, 1 );
        registerDependency(this.collaborationDep);
    }

    @objid ("0886fa10-1713-4340-891d-ed7c5f1ddc42")
    public SmDependency getMessageRefDep() {
        if (this.messageRefDep == null) {
        	this.messageRefDep = this.getDependencyDef("MessageRef");
        }
        return this.messageRefDep;
    }

    @objid ("cc673652-865d-4bed-a7eb-f36a7a15a140")
    public SmDependency getSourceRefDep() {
        if (this.sourceRefDep == null) {
        	this.sourceRefDep = this.getDependencyDef("SourceRef");
        }
        return this.sourceRefDep;
    }

    @objid ("bb417048-3a21-42fc-9d93-c563940aca84")
    public SmDependency getTargetRefDep() {
        if (this.targetRefDep == null) {
        	this.targetRefDep = this.getDependencyDef("TargetRef");
        }
        return this.targetRefDep;
    }

    @objid ("64a76f4c-12b3-4475-a3cd-0ab1b5941d1a")
    public SmDependency getCollaborationDep() {
        if (this.collaborationDep == null) {
        	this.collaborationDep = this.getDependencyDef("Collaboration");
        }
        return this.collaborationDep;
    }

    @objid ("644df1f4-5a10-445f-b02f-6a6c9a4cd77f")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("414d75db-cf8b-42ed-9657-949369e0a56d")
    private static class BpmnMessageFlowObjectFactory implements ISmObjectFactory {
        @objid ("c11a9e54-f43f-4086-8f57-392f328a61bf")
        private BpmnMessageFlowSmClass smClass;

        @objid ("2b14f323-fd2a-4dcc-a494-8b30d8d94f26")
        public BpmnMessageFlowObjectFactory(BpmnMessageFlowSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("619f8219-641f-462a-90b9-fb184e16af3a")
        @Override
        public ISmObjectData createData() {
            return new BpmnMessageFlowData(this.smClass);
        }

        @objid ("29da3ff5-d4a8-4054-a7ce-c511e1b922cd")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnMessageFlowImpl();
        }

    }

    @objid ("900390f2-a6e9-44fe-b6eb-838b12b7c937")
    public static class MessageRefSmDependency extends SmSingleDependency {
        @objid ("a3b78c9c-b177-4a4c-84cf-5874dd5ba949")
        private SmDependency symetricDep;

        @objid ("a3ad1320-e83f-4920-aa0b-cbebc2d4614f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMessageFlowData) data).mMessageRef;
        }

        @objid ("5c86420a-245b-4c53-82c0-7bb3f5570867")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMessageFlowData) data).mMessageRef = value;
        }

        @objid ("d7aff88f-e848-4e9f-951e-78a263795d12")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getMessageFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("93b0e5c8-6e14-463a-968e-0fe7248b0e0a")
    public static class SourceRefSmDependency extends SmSingleDependency {
        @objid ("61500e27-bfe5-455c-bad3-6a53e0457abd")
        private SmDependency symetricDep;

        @objid ("b0bb4845-ea20-4eeb-b693-5ffe228f3b34")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMessageFlowData) data).mSourceRef;
        }

        @objid ("34c3d3eb-385c-4606-8826-dd11de6fb7ce")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMessageFlowData) data).mSourceRef = value;
        }

        @objid ("8e8c95b3-57ee-4398-8db7-48a4056183e2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnBaseElementSmClass)this.getTarget()).getOutgoingFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5857f741-4236-464f-9934-c041ac239a11")
    public static class TargetRefSmDependency extends SmSingleDependency {
        @objid ("5d140886-1fd0-452b-a020-3b66e790a42a")
        private SmDependency symetricDep;

        @objid ("f3be28ed-7ac9-4408-9692-c5f7a2ced0bf")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMessageFlowData) data).mTargetRef;
        }

        @objid ("3e74919a-f9b7-4054-bd77-4974cfad5419")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMessageFlowData) data).mTargetRef = value;
        }

        @objid ("0f3ba64c-d93e-4c32-a4fc-b83f20e57673")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnBaseElementSmClass)this.getTarget()).getIncomingFlowDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0ae70ba2-93c1-4f8c-bf30-d2b7259107af")
    public static class CollaborationSmDependency extends SmSingleDependency {
        @objid ("4b0e045e-365f-40e1-96ea-ef61e8b527a8")
        private SmDependency symetricDep;

        @objid ("65179e4a-6fef-47b1-afee-6702beaa5b85")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnMessageFlowData) data).mCollaboration;
        }

        @objid ("eba2211c-4742-4aa0-a610-917ada4887cc")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnMessageFlowData) data).mCollaboration = value;
        }

        @objid ("96e7cedc-2907-40e9-a16b-8c27555e3f3f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCollaborationSmClass)this.getTarget()).getMessageFlowDep();
            }
            return this.symetricDep;
        }

    }

}
