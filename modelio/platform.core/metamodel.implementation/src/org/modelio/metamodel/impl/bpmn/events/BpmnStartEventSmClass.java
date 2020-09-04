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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.impl.bpmn.events.BpmnCatchEventSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("d8593693-2159-4274-8e12-551d7ff986fd")
public class BpmnStartEventSmClass extends BpmnCatchEventSmClass {
    @objid ("9072c10e-a405-4461-95e9-204f3cd06923")
    private SmAttribute isInterruptingAtt;

    @objid ("6963207d-fbf4-4889-8b0c-8c7cc9a4a8ce")
    public BpmnStartEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("94a10ff1-ffb9-47bf-b909-78f1a7fb2fda")
    @Override
    public String getName() {
        return "BpmnStartEvent";
    }

    @objid ("bae50689-ed89-40d6-8332-7d1900621e89")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("bab18b28-be04-4180-acf4-c5c9f8fe5741")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnStartEvent.class;
    }

    @objid ("bfddbdbb-28fe-4c51-b5b8-3a2abba4cef4")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5762dc07-1864-41b3-a72d-b3eb2e5bd321")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("4eba2942-dcd1-4777-8426-e4262aa0c286")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnCatchEvent.MQNAME);
        this.registerFactory(new BpmnStartEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isInterruptingAtt = new IsInterruptingSmAttribute();
        this.isInterruptingAtt.init("IsInterrupting", this, Boolean.class );
        registerAttribute(this.isInterruptingAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("5aac2c91-ceea-4b18-906b-05c3101e6187")
    public SmAttribute getIsInterruptingAtt() {
        if (this.isInterruptingAtt == null) {
        	this.isInterruptingAtt = this.getAttributeDef("IsInterrupting");
        }
        return this.isInterruptingAtt;
    }

    @objid ("b7ca2912-1096-4a2c-9a5c-4915b415019c")
    private static class BpmnStartEventObjectFactory implements ISmObjectFactory {
        @objid ("5a825f6f-405e-4af2-b0de-69387c46a8d9")
        private BpmnStartEventSmClass smClass;

        @objid ("64c301cc-5d16-424e-a2ab-569e0b3c6ce1")
        public BpmnStartEventObjectFactory(BpmnStartEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ff6d96b4-1688-439c-af52-1061563c2cae")
        @Override
        public ISmObjectData createData() {
            return new BpmnStartEventData(this.smClass);
        }

        @objid ("b805c887-28f8-451a-a39c-e66666e1f2c6")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnStartEventImpl();
        }

    }

    @objid ("7572cc1d-4472-4920-88fe-292e3552e75f")
    public static class IsInterruptingSmAttribute extends SmAttribute {
        @objid ("881f7c77-8a27-4e2c-bf33-a670ab702150")
        public Object getValue(ISmObjectData data) {
            return ((BpmnStartEventData) data).mIsInterrupting;
        }

        @objid ("1057ddd1-2b4b-468a-9026-b0f8b79a0d50")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnStartEventData) data).mIsInterrupting = value;
        }

    }

}
