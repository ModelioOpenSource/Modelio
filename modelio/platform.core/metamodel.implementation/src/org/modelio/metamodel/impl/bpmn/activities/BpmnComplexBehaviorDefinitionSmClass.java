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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnComplexBehaviorDefinitionData;
import org.modelio.metamodel.impl.bpmn.activities.BpmnMultiInstanceLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnImplicitThrowEventSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1ff7f11f-681a-4bbf-915b-70cf03d97e97")
public class BpmnComplexBehaviorDefinitionSmClass extends BpmnBaseElementSmClass {
    @objid ("dec26897-b29f-4456-94e2-76bae9708eab")
    private SmAttribute conditionAtt;

    @objid ("71e61bd0-8026-44ce-a154-a87a98101419")
    private SmDependency ownerDep;

    @objid ("7800be6f-b208-4f20-ac8c-55b36ebc26c1")
    private SmDependency eventDep;

    @objid ("003dd13f-d857-4448-bdf8-ba94899c1644")
    public BpmnComplexBehaviorDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1f8eff28-5910-4af9-9647-cbb792be4a03")
    @Override
    public String getName() {
        return "BpmnComplexBehaviorDefinition";
    }

    @objid ("8084b8e2-e3d7-4b02-891f-1c2d2c9a6e28")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("91885980-2611-4143-89f2-fc0eef735649")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnComplexBehaviorDefinition.class;
    }

    @objid ("d7bbc34d-0ecf-4c2b-b814-bdc8c63b553b")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("744105e7-8467-445d-bc4b-4b9d2ccb6247")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("3b8ceb34-436e-4d28-9c08-4db03a90729f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnComplexBehaviorDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.conditionAtt = new ConditionSmAttribute();
        this.conditionAtt.init("Condition", this, String.class );
        registerAttribute(this.conditionAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(BpmnMultiInstanceLoopCharacteristics.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        this.eventDep = new EventSmDependency();
        this.eventDep.init("Event", this, metamodel.getMClass(BpmnImplicitThrowEvent.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.eventDep);
    }

    @objid ("6bf6f68c-75d9-41e8-951f-b1df68ccba0a")
    public SmAttribute getConditionAtt() {
        if (this.conditionAtt == null) {
        	this.conditionAtt = this.getAttributeDef("Condition");
        }
        return this.conditionAtt;
    }

    @objid ("c900079f-05b7-4028-aa52-97807f0fa504")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("5996e837-56f2-4c18-a4e8-8127006e94c1")
    public SmDependency getEventDep() {
        if (this.eventDep == null) {
        	this.eventDep = this.getDependencyDef("Event");
        }
        return this.eventDep;
    }

    @objid ("0f87b3e2-1040-46b9-9e43-556f78a3adac")
    private static class BpmnComplexBehaviorDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("b4015571-41e5-423e-acdb-b3a9fb439918")
        private BpmnComplexBehaviorDefinitionSmClass smClass;

        @objid ("149b6fe4-cd48-46d8-8ea5-8944cad813f2")
        public BpmnComplexBehaviorDefinitionObjectFactory(BpmnComplexBehaviorDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3ad42742-c512-423a-946c-d0876b536585")
        @Override
        public ISmObjectData createData() {
            return new BpmnComplexBehaviorDefinitionData(this.smClass);
        }

        @objid ("6521fdca-6365-4768-80c7-4078dca0e493")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnComplexBehaviorDefinitionImpl();
        }

    }

    @objid ("b2e9132b-b740-4a52-8ace-88a624d33771")
    public static class ConditionSmAttribute extends SmAttribute {
        @objid ("bd62e30d-7173-43cd-8615-7ae279d21611")
        public Object getValue(ISmObjectData data) {
            return ((BpmnComplexBehaviorDefinitionData) data).mCondition;
        }

        @objid ("3aeb0231-24db-400c-800e-0f420f2c1817")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnComplexBehaviorDefinitionData) data).mCondition = value;
        }

    }

    @objid ("54aeea88-4ab7-45cc-94ec-c6799458397c")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("18a9ce6d-e359-4191-b2a4-8b9c4751253b")
        private SmDependency symetricDep;

        @objid ("a3cd34a4-e4f6-4d40-bd0e-12548c149492")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnComplexBehaviorDefinitionData) data).mOwner;
        }

        @objid ("ef5fcc39-464a-4770-9c5f-91298a4bb2b9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnComplexBehaviorDefinitionData) data).mOwner = value;
        }

        @objid ("1c08a3cf-55ed-4f5e-852a-bdb3e2df8628")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMultiInstanceLoopCharacteristicsSmClass)this.getTarget()).getComplexBehaviorDefinitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("3b73df91-d11a-4417-b276-f9bccb49f5d1")
    public static class EventSmDependency extends SmSingleDependency {
        @objid ("1956bbc6-5cf9-454f-914c-4008bb6c585e")
        private SmDependency symetricDep;

        @objid ("405b06d3-067f-4fef-9a12-ddb9f022e02e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnComplexBehaviorDefinitionData) data).mEvent;
        }

        @objid ("49857469-90b6-401e-ba98-c0dbe848cbcd")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnComplexBehaviorDefinitionData) data).mEvent = value;
        }

        @objid ("e5059d01-e922-4323-a9ec-c03606fd8e14")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnImplicitThrowEventSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
