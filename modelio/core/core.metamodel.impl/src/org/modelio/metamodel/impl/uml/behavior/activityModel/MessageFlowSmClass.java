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

package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
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

@objid ("6af4dd1b-0c08-431b-83a8-2f3b5e01d271")
public class MessageFlowSmClass extends ActivityEdgeSmClass {
    @objid ("85f0d0ec-f2cf-483d-ac14-c80fc380cc72")
    private SmDependency targetPartitionDep;

    @objid ("432dd5f8-23e2-423e-b8b4-b42758c8f19e")
    private SmDependency sourcePartitionDep;

    @objid ("5a596743-0e43-4fab-8f63-82370b0b80f0")
    public  MessageFlowSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("907e932f-2833-4b28-b930-99a8c42111e4")
    @Override
    public String getName() {
        return "MessageFlow";
        
    }

    @objid ("5682b405-14ec-4595-8b2b-66f4ceb1013b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("746f6066-794d-4cc6-9b80-ffc54f98a9ed")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MessageFlow.class;
        
    }

    @objid ("044cdff0-1635-42a7-af80-fe7ea85d0d0b")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("26a307cc-5dea-4a8f-8a84-b8a361ea1190")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("b4fe79bb-4126-4abb-ada5-d553b141b55d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityEdge.MQNAME);
        this.registerFactory(new MessageFlowObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.targetPartitionDep = new TargetPartitionSmDependency();
        this.targetPartitionDep.init("TargetPartition", this, metamodel.getMClass(ActivityPartition.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetPartitionDep);
        
        this.sourcePartitionDep = new SourcePartitionSmDependency();
        this.sourcePartitionDep.init("SourcePartition", this, metamodel.getMClass(ActivityPartition.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.sourcePartitionDep);
        
        
    }

    @objid ("1fd43c4c-2f0e-4b25-a8b1-a47e974468f7")
    public SmDependency getTargetPartitionDep() {
        if (this.targetPartitionDep == null) {
        	this.targetPartitionDep = this.getDependencyDef("TargetPartition");
        }
        return this.targetPartitionDep;
    }

    @objid ("3edfcc83-9bf4-4348-8db7-49bb1c721c28")
    public SmDependency getSourcePartitionDep() {
        if (this.sourcePartitionDep == null) {
        	this.sourcePartitionDep = this.getDependencyDef("SourcePartition");
        }
        return this.sourcePartitionDep;
    }

    @objid ("9a55a91f-c459-414f-97e6-42432135e76c")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("f46ca5ff-f9ee-45c3-922a-a93c5744c3e8")
    private static class MessageFlowObjectFactory implements ISmObjectFactory {
        @objid ("8982673c-ee50-43cb-ade3-7a010be2389b")
        private MessageFlowSmClass smClass;

        @objid ("7b835d5a-8fe7-4d3f-8f53-8af5ea108cc9")
        public  MessageFlowObjectFactory(MessageFlowSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("edcdb563-e380-40e0-a41a-756b208132d0")
        @Override
        public ISmObjectData createData() {
            return new MessageFlowData(this.smClass);
        }

        @objid ("ecc227e3-7180-403d-adec-1dd853785435")
        @Override
        public SmObjectImpl createImpl() {
            return new MessageFlowImpl();
        }

    }

    @objid ("a4ce1fe3-0268-480b-9f26-e7b9184e6dd7")
    public static class TargetPartitionSmDependency extends SmSingleDependency {
        @objid ("be89d746-007f-473f-8eb0-eafeed18abb0")
        private SmDependency symetricDep;

        @objid ("9d8f5435-c43c-46ff-91ce-8096c146fc7a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageFlowData) data).mTargetPartition;
        }

        @objid ("2bba30e4-3d56-41f0-be65-d73db1f0049e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageFlowData) data).mTargetPartition = value;
        }

        @objid ("3ca9b833-f2ee-49ba-bfe1-7308acf5ae6d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityPartitionSmClass)this.getTarget()).getIncomingDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("cd246c81-be2b-459d-9279-89164f1f6d9d")
    public static class SourcePartitionSmDependency extends SmSingleDependency {
        @objid ("2e9fa652-c3b9-4d61-95e6-01e3ef179d44")
        private SmDependency symetricDep;

        @objid ("826f1246-c799-462c-b1a7-d4c367109a0d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MessageFlowData) data).mSourcePartition;
        }

        @objid ("0faf0cab-2a4c-4ddd-9057-4945abf01283")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MessageFlowData) data).mSourcePartition = value;
        }

        @objid ("24c09784-5946-4ef5-9135-3a80cb6da54d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityPartitionSmClass)this.getTarget()).getOutgoingDep();
            }
            return this.symetricDep;
            
        }

    }

}
