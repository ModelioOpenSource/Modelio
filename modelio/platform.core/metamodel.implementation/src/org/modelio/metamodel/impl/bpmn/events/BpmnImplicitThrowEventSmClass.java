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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.impl.bpmn.activities.BpmnComplexBehaviorDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnImplicitThrowEventData;
import org.modelio.metamodel.impl.bpmn.events.BpmnThrowEventSmClass;
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

@objid ("0c7e2bb3-5654-4514-8de6-3b68a364fb8f")
public class BpmnImplicitThrowEventSmClass extends BpmnThrowEventSmClass {
    @objid ("666c3e68-36ed-4290-ac55-c8b46b2852e7")
    private SmDependency ownerDep;

    @objid ("b4b5660f-f689-475b-8b9a-c643cab783fc")
    public BpmnImplicitThrowEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2cd0c338-f256-4eb4-8797-9d0f116a8d73")
    @Override
    public String getName() {
        return "BpmnImplicitThrowEvent";
    }

    @objid ("583b946a-7f8e-4748-992c-e463a6b36b8f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("749028ec-5618-41af-a8e0-598298108565")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnImplicitThrowEvent.class;
    }

    @objid ("48f2a5af-9a31-468a-9404-6994108d4351")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("963f6edb-1c68-4306-8817-bbaf70e3ed1e")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("c20a0987-ed04-4925-abf4-629ef243b2ae")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnThrowEvent.MQNAME);
        this.registerFactory(new BpmnImplicitThrowEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(BpmnComplexBehaviorDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("e79b77d9-3272-435c-89ab-538f880a2642")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("b508cbda-c8df-47c2-8745-ac80c6be966e")
    private static class BpmnImplicitThrowEventObjectFactory implements ISmObjectFactory {
        @objid ("00a00eb6-3c6d-4eb5-b7fb-30837bfe2c0d")
        private BpmnImplicitThrowEventSmClass smClass;

        @objid ("8d4086f4-fdb9-4834-b85e-7795939a8be4")
        public BpmnImplicitThrowEventObjectFactory(BpmnImplicitThrowEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f8006d90-a0ee-4e58-bc84-a240423c6d25")
        @Override
        public ISmObjectData createData() {
            return new BpmnImplicitThrowEventData(this.smClass);
        }

        @objid ("146126db-09a5-4332-83ea-e1095a63e7d4")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnImplicitThrowEventImpl();
        }

    }

    @objid ("80713db7-121d-4952-b5cd-052c3ae89fb1")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("9d50d8f1-bc13-4710-b17c-b0c8e8e598d3")
        private SmDependency symetricDep;

        @objid ("0854175a-a703-4815-8bfa-b4099ddb1871")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnImplicitThrowEventData) data).mOwner;
        }

        @objid ("3a3418c3-aaee-4fd7-bbb3-012a9728ad8e")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnImplicitThrowEventData) data).mOwner = value;
        }

        @objid ("89b334ca-347a-4833-8f55-254be4788e2f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnComplexBehaviorDefinitionSmClass)this.getTarget()).getEventDep();
            }
            return this.symetricDep;
        }

    }

}
