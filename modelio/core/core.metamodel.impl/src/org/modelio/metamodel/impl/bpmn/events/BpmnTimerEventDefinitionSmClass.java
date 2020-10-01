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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c3a0ab51-036c-4b61-a9f8-2990d9c1f220")
public class BpmnTimerEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("7553d092-197c-4e83-b9f0-12a1af30e680")
    private SmAttribute timeCycleAtt;

    @objid ("76f9bf5a-8e00-4a82-9811-16c4ea881936")
    private SmAttribute timeDateAtt;

    @objid ("21c8622b-1c2e-4c58-a1f8-02bd0128f98b")
    private SmAttribute timeDurationAtt;

    @objid ("139b1770-cd9e-45cd-8adc-612c348eec2f")
    public BpmnTimerEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("11bb264f-f90a-4dc8-8c10-102814ad6808")
    @Override
    public String getName() {
        return "BpmnTimerEventDefinition";
    }

    @objid ("1cd42db5-ee54-4d73-a33f-0acf8c35fdc8")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ca774d5d-c768-4a7e-9581-b763f761923b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnTimerEventDefinition.class;
    }

    @objid ("6543c4c9-2320-4c76-9d49-1846783107c7")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6b007c67-23f6-4d48-af27-fd4f331df7d5")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6c424a99-dd8e-4029-ad3f-4ebd1c8ca7ac")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnTimerEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.timeCycleAtt = new TimeCycleSmAttribute();
        this.timeCycleAtt.init("TimeCycle", this, String.class );
        registerAttribute(this.timeCycleAtt);
        
        this.timeDateAtt = new TimeDateSmAttribute();
        this.timeDateAtt.init("TimeDate", this, String.class );
        registerAttribute(this.timeDateAtt);
        
        this.timeDurationAtt = new TimeDurationSmAttribute();
        this.timeDurationAtt.init("TimeDuration", this, String.class );
        registerAttribute(this.timeDurationAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("9c284ee3-9231-44a9-bf0c-660b7495514d")
    public SmAttribute getTimeCycleAtt() {
        if (this.timeCycleAtt == null) {
        	this.timeCycleAtt = this.getAttributeDef("TimeCycle");
        }
        return this.timeCycleAtt;
    }

    @objid ("653adddf-d2bb-4e51-b055-ef2337332db6")
    public SmAttribute getTimeDateAtt() {
        if (this.timeDateAtt == null) {
        	this.timeDateAtt = this.getAttributeDef("TimeDate");
        }
        return this.timeDateAtt;
    }

    @objid ("cceaffc7-1162-4ba0-b1fc-76655b25df7d")
    public SmAttribute getTimeDurationAtt() {
        if (this.timeDurationAtt == null) {
        	this.timeDurationAtt = this.getAttributeDef("TimeDuration");
        }
        return this.timeDurationAtt;
    }

    @objid ("a1fb3ac6-1319-4d5e-920b-abeb2a2f644f")
    private static class BpmnTimerEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("18fddda0-bded-4e8d-985e-9337f6a1c918")
        private BpmnTimerEventDefinitionSmClass smClass;

        @objid ("823d60e6-9396-4867-94a1-9a566bfbbb69")
        public BpmnTimerEventDefinitionObjectFactory(BpmnTimerEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4326d028-aa37-4135-b123-1831d18a8d98")
        @Override
        public ISmObjectData createData() {
            return new BpmnTimerEventDefinitionData(this.smClass);
        }

        @objid ("b4f7d14a-6bc9-48ca-a621-1522d2ab8f36")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnTimerEventDefinitionImpl();
        }

    }

    @objid ("a7537914-9683-4247-9b3f-c5c07c9bbf67")
    public static class TimeCycleSmAttribute extends SmAttribute {
        @objid ("210b1fae-a1cf-441d-8077-2c9f8d34591c")
        public Object getValue(ISmObjectData data) {
            return ((BpmnTimerEventDefinitionData) data).mTimeCycle;
        }

        @objid ("47e80c2d-4d9f-48cd-a81b-eac62fb6c86f")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnTimerEventDefinitionData) data).mTimeCycle = value;
        }

    }

    @objid ("ba8c1482-7e0b-4f1e-8873-77b2ddcd3e60")
    public static class TimeDateSmAttribute extends SmAttribute {
        @objid ("2369bfe9-d21f-459d-8f05-02fb0a3a4a21")
        public Object getValue(ISmObjectData data) {
            return ((BpmnTimerEventDefinitionData) data).mTimeDate;
        }

        @objid ("9632a943-f5bb-4471-8fb2-4f780f7c8ee4")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnTimerEventDefinitionData) data).mTimeDate = value;
        }

    }

    @objid ("b7e1eb08-8c76-4806-a27b-f129b77ea194")
    public static class TimeDurationSmAttribute extends SmAttribute {
        @objid ("60dca824-dd73-4d75-90e4-499db7c92155")
        public Object getValue(ISmObjectData data) {
            return ((BpmnTimerEventDefinitionData) data).mTimeDuration;
        }

        @objid ("780ccb56-9248-4f30-ae72-7940f8de3ec2")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnTimerEventDefinitionData) data).mTimeDuration = value;
        }

    }

}
