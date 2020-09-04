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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExceptionHandlerSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InputPinSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.OutputPinSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("90f6045c-3241-4b56-a0d9-fb3745c723b3")
public class ActivityActionSmClass extends ActivityNodeSmClass {
    @objid ("ea3855f7-336b-4ab1-95a7-47fb842caa08")
    private SmAttribute isMultipleInstanceAtt;

    @objid ("6d357333-7e85-463f-9301-a88d90ead158")
    private SmAttribute isCompensationAtt;

    @objid ("08fc483b-0a83-4559-b7b0-22393b5f8db3")
    private SmDependency outputDep;

    @objid ("cfa5bedb-a79f-4f25-a894-711c85397c60")
    private SmDependency inputDep;

    @objid ("7d8262ed-24ca-462b-a172-10cde0d09f17")
    private SmDependency handlerDep;

    @objid ("ff75d4dc-28be-495f-9376-c7c88349120d")
    public ActivityActionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6730c330-071c-495e-88e1-3763a560a750")
    @Override
    public String getName() {
        return "ActivityAction";
    }

    @objid ("e00fe84e-5fe9-410c-90eb-fc9face7e583")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d674d565-d3dd-4b69-8957-e81f897aae0a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityAction.class;
    }

    @objid ("e391522c-6648-42dd-8f6d-95428a6bb259")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("7fb532a5-a309-449c-b8a6-eaddceb36580")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("510ceec9-d68a-4e00-a8c8-90af459fa6e9")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityNode.MQNAME);
        this.registerFactory(new ActivityActionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isMultipleInstanceAtt = new IsMultipleInstanceSmAttribute();
        this.isMultipleInstanceAtt.init("IsMultipleInstance", this, Boolean.class );
        registerAttribute(this.isMultipleInstanceAtt);
        
        this.isCompensationAtt = new IsCompensationSmAttribute();
        this.isCompensationAtt.init("IsCompensation", this, Boolean.class );
        registerAttribute(this.isCompensationAtt);
        
        
        // Initialize and register the SmDependency
        this.outputDep = new OutputSmDependency();
        this.outputDep.init("Output", this, metamodel.getMClass(OutputPin.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.outputDep);
        
        this.inputDep = new InputSmDependency();
        this.inputDep.init("Input", this, metamodel.getMClass(InputPin.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.inputDep);
        
        this.handlerDep = new HandlerSmDependency();
        this.handlerDep.init("Handler", this, metamodel.getMClass(ExceptionHandler.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.handlerDep);
    }

    @objid ("ab2dde0c-5726-4140-ade1-b664f3788066")
    public SmAttribute getIsMultipleInstanceAtt() {
        if (this.isMultipleInstanceAtt == null) {
        	this.isMultipleInstanceAtt = this.getAttributeDef("IsMultipleInstance");
        }
        return this.isMultipleInstanceAtt;
    }

    @objid ("7b848bdd-e5a1-4460-a473-9dac88835b4b")
    public SmAttribute getIsCompensationAtt() {
        if (this.isCompensationAtt == null) {
        	this.isCompensationAtt = this.getAttributeDef("IsCompensation");
        }
        return this.isCompensationAtt;
    }

    @objid ("cc6c580d-307f-465f-8629-28fe80419f25")
    public SmDependency getOutputDep() {
        if (this.outputDep == null) {
        	this.outputDep = this.getDependencyDef("Output");
        }
        return this.outputDep;
    }

    @objid ("e83c981d-4e3b-40b7-ab13-b7126fc8806a")
    public SmDependency getInputDep() {
        if (this.inputDep == null) {
        	this.inputDep = this.getDependencyDef("Input");
        }
        return this.inputDep;
    }

    @objid ("491e4265-9d22-4608-812a-d08ff257a62e")
    public SmDependency getHandlerDep() {
        if (this.handlerDep == null) {
        	this.handlerDep = this.getDependencyDef("Handler");
        }
        return this.handlerDep;
    }

    @objid ("0b15066a-7027-4041-8cef-9d41da804c05")
    private static class ActivityActionObjectFactory implements ISmObjectFactory {
        @objid ("86bdc41a-bff8-47e7-939e-2d81fc1cd9d4")
        private ActivityActionSmClass smClass;

        @objid ("e1dbcd8d-3389-4000-9346-58fa072218d7")
        public ActivityActionObjectFactory(ActivityActionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("bdc682bc-aba9-4d02-9e7e-6994ac744521")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("49b218c6-544d-4f2e-a0f3-5cc26043ab81")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("fbf2895e-e719-4e88-89be-aee1cda4e4a4")
    public static class IsMultipleInstanceSmAttribute extends SmAttribute {
        @objid ("8402f686-e3e2-4196-9c6a-38d75c077582")
        public Object getValue(ISmObjectData data) {
            return ((ActivityActionData) data).mIsMultipleInstance;
        }

        @objid ("bef8a966-e4c3-443a-9936-8eb98fa6f14b")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityActionData) data).mIsMultipleInstance = value;
        }

    }

    @objid ("8be3a40f-714d-4574-bd58-9b84f2c6dfb1")
    public static class IsCompensationSmAttribute extends SmAttribute {
        @objid ("d8219a98-53c2-47e6-8e6b-ed1798542922")
        public Object getValue(ISmObjectData data) {
            return ((ActivityActionData) data).mIsCompensation;
        }

        @objid ("84827ff6-b269-4290-a947-4a0c0987bab6")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityActionData) data).mIsCompensation = value;
        }

    }

    @objid ("99711a5b-758f-4497-850d-0d9b30a2e8e1")
    public static class OutputSmDependency extends SmMultipleDependency {
        @objid ("7fb84306-9fe9-4948-a107-ecfa8c99b6c0")
        private SmDependency symetricDep;

        @objid ("4c18b8a3-156e-4c69-974b-8178c0cd4230")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityActionData)data).mOutput != null)? ((ActivityActionData)data).mOutput:SmMultipleDependency.EMPTY;
        }

        @objid ("2fdc9204-cf58-454c-8a9a-a8e56e47e5c7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityActionData) data).mOutput = values;
        }

        @objid ("0a138036-12ed-4386-b50b-4546f02bed7a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OutputPinSmClass)this.getTarget()).getOutputingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("aee183a3-affe-42f3-a82b-0ece36e323ca")
    public static class InputSmDependency extends SmMultipleDependency {
        @objid ("f8c157c6-6304-4336-a7ff-82d508c99fb4")
        private SmDependency symetricDep;

        @objid ("89ea0a31-662b-4cf2-8433-e07a99620646")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityActionData)data).mInput != null)? ((ActivityActionData)data).mInput:SmMultipleDependency.EMPTY;
        }

        @objid ("509363b4-8057-4d9d-af7b-ddc818611821")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityActionData) data).mInput = values;
        }

        @objid ("0ad8992f-e8d3-4837-8d80-5e44d069833b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InputPinSmClass)this.getTarget()).getInputingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("30db67f5-d2f6-44ef-b8a6-df2904c4eb4d")
    public static class HandlerSmDependency extends SmMultipleDependency {
        @objid ("a9ffd022-efcb-4e11-a5c4-e43a7adcd402")
        private SmDependency symetricDep;

        @objid ("2efa658d-aa98-499a-a64d-fca0f12a7305")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ActivityActionData)data).mHandler != null)? ((ActivityActionData)data).mHandler:SmMultipleDependency.EMPTY;
        }

        @objid ("31d61566-18fd-4ab4-8551-ba7e14112640")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ActivityActionData) data).mHandler = values;
        }

        @objid ("3f5d8a8a-b8df-4b56-acd1-a1910299e57c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExceptionHandlerSmClass)this.getTarget()).getProtectedNodeDep();
            }
            return this.symetricDep;
        }

    }

}
