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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExceptionHandlerSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InputPinData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.PinSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
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

@objid ("116cae41-1412-4845-aca2-dc7851e2ae73")
public class InputPinSmClass extends PinSmClass {
    @objid ("d95e181f-4954-41be-a89b-3c87deebb09f")
    private SmAttribute isSelfAtt;

    @objid ("bb8b213b-9f7b-4c97-8f5b-6fc60abfba14")
    private SmDependency handlerDep;

    @objid ("6d8d84b9-f981-4100-bad3-996f856a04d7")
    private SmDependency inputingDep;

    @objid ("9ac25ee6-f135-4493-89fd-d4c0cc351c20")
    public InputPinSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2a25d5dc-dd43-47da-83c9-46efb9b1276c")
    @Override
    public String getName() {
        return "InputPin";
    }

    @objid ("97e664d7-af4c-4e77-8471-7e05d228d880")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3e882355-c7b7-40fa-831b-9a47bbd4625e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InputPin.class;
    }

    @objid ("fdd3cb49-fcd2-4bdf-ba9a-4e507fd0fae1")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("0fefffcf-eaef-4416-a9ba-07832fa1376f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("bea9970e-6586-40f8-be3e-ef2b401e2e9f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Pin.MQNAME);
        this.registerFactory(new InputPinObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isSelfAtt = new IsSelfSmAttribute();
        this.isSelfAtt.init("IsSelf", this, Boolean.class );
        registerAttribute(this.isSelfAtt);
        
        
        // Initialize and register the SmDependency
        this.handlerDep = new HandlerSmDependency();
        this.handlerDep.init("Handler", this, metamodel.getMClass(ExceptionHandler.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.handlerDep);
        
        this.inputingDep = new InputingSmDependency();
        this.inputingDep.init("Inputing", this, metamodel.getMClass(ActivityAction.MQNAME), 0, 1 );
        registerDependency(this.inputingDep);
    }

    @objid ("39f55910-895e-4e46-a461-65902d04a480")
    public SmAttribute getIsSelfAtt() {
        if (this.isSelfAtt == null) {
        	this.isSelfAtt = this.getAttributeDef("IsSelf");
        }
        return this.isSelfAtt;
    }

    @objid ("becea74a-7fa6-4eb6-ac44-7346ab6104c9")
    public SmDependency getHandlerDep() {
        if (this.handlerDep == null) {
        	this.handlerDep = this.getDependencyDef("Handler");
        }
        return this.handlerDep;
    }

    @objid ("9af152c5-e0bd-4926-aa56-8ffa44976087")
    public SmDependency getInputingDep() {
        if (this.inputingDep == null) {
        	this.inputingDep = this.getDependencyDef("Inputing");
        }
        return this.inputingDep;
    }

    @objid ("ffc93042-6d75-4aac-98a7-3dcf1353b6cd")
    private static class InputPinObjectFactory implements ISmObjectFactory {
        @objid ("17918550-a563-4163-b6ca-eb333895cbdb")
        private InputPinSmClass smClass;

        @objid ("6342471c-9c0b-4bea-b5e2-0c1e52d02c8a")
        public InputPinObjectFactory(InputPinSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8dd9c8c2-fa55-4b3c-9628-0a149ed1b0af")
        @Override
        public ISmObjectData createData() {
            return new InputPinData(this.smClass);
        }

        @objid ("2f6552ad-f842-466e-a786-b0e9b04c4d49")
        @Override
        public SmObjectImpl createImpl() {
            return new InputPinImpl();
        }

    }

    @objid ("fd28bf73-188d-4e33-9552-f5c1cd7f7413")
    public static class IsSelfSmAttribute extends SmAttribute {
        @objid ("87e8f108-2019-430b-b78f-500ba821a750")
        public Object getValue(ISmObjectData data) {
            return ((InputPinData) data).mIsSelf;
        }

        @objid ("2d03985a-e47f-4347-9744-623f86b83e5f")
        public void setValue(ISmObjectData data, Object value) {
            ((InputPinData) data).mIsSelf = value;
        }

    }

    @objid ("25d22883-9110-4e78-8e86-84c118071dcb")
    public static class HandlerSmDependency extends SmMultipleDependency {
        @objid ("2b18733c-b07b-460a-b181-6598dfae832f")
        private SmDependency symetricDep;

        @objid ("11476821-c366-4a1b-86df-8054d6c4aa90")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InputPinData)data).mHandler != null)? ((InputPinData)data).mHandler:SmMultipleDependency.EMPTY;
        }

        @objid ("cfdbdb01-7a4e-4774-9b47-35a085299651")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InputPinData) data).mHandler = values;
        }

        @objid ("b1e7e3c8-bcc6-43c9-9c7a-2d156a88937a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExceptionHandlerSmClass)this.getTarget()).getExceptionInputDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("aaa8907c-be03-4f4e-bc76-3fa5492a3554")
    public static class InputingSmDependency extends SmSingleDependency {
        @objid ("a74418da-0d6a-495e-858c-62114c322339")
        private SmDependency symetricDep;

        @objid ("12fd92de-a760-4dae-903d-cf2060ad232f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InputPinData) data).mInputing;
        }

        @objid ("7897bc6b-f405-4b61-a2fb-f5c3b62e8412")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InputPinData) data).mInputing = value;
        }

        @objid ("9c34b5ea-7b03-4fe5-94c4-02e8dd088a83")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityActionSmClass)this.getTarget()).getInputDep();
            }
            return this.symetricDep;
        }

    }

}
