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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedDefinitionsData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("b5512d51-8f67-4612-b567-110a04692918")
public class BpmnSharedDefinitionsSmClass extends BehaviorSmClass {
    @objid ("d3568122-a8d1-4d38-82a9-a7d5e44b92e0")
    private SmDependency rootElementDep;

    @objid ("7d0c89ed-da68-4892-849e-c56993ead316")
    public BpmnSharedDefinitionsSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("fea8ac8a-80f1-4fd0-b24b-bd7d1f30653f")
    @Override
    public String getName() {
        return "BpmnSharedDefinitions";
    }

    @objid ("2f7278d0-54e6-4c9f-b30f-b25db6fd80fe")
    @Override
    public Version getVersion() {
        return new Version("2.2.0");
    }

    @objid ("8a53db05-305e-40b2-8ab2-145e12b181d2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSharedDefinitions.class;
    }

    @objid ("cf0a5ffb-770c-4287-b482-24069c58771f")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("62a1fe7c-fde4-4b1f-8a5d-e3cdbf44a4df")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("35263479-168e-4904-a453-db49b6ba728a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new BpmnSharedDefinitionsObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.rootElementDep = new RootElementSmDependency();
        this.rootElementDep.init("RootElement", this, metamodel.getMClass(BpmnSharedElement.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.rootElementDep);
    }

    @objid ("55252b82-bea9-48a1-8c64-424480c8570b")
    public SmDependency getRootElementDep() {
        if (this.rootElementDep == null) {
        	this.rootElementDep = this.getDependencyDef("RootElement");
        }
        return this.rootElementDep;
    }

    @objid ("47c20f05-5cd3-4255-b166-459b3870d304")
    private static class BpmnSharedDefinitionsObjectFactory implements ISmObjectFactory {
        @objid ("d67ae21d-aa43-4030-9074-306c7441ee52")
        private BpmnSharedDefinitionsSmClass smClass;

        @objid ("23956304-f17c-402f-90ee-baa46556d81d")
        public BpmnSharedDefinitionsObjectFactory(BpmnSharedDefinitionsSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6dcffc9b-546a-446a-bc98-e517887b3d8f")
        @Override
        public ISmObjectData createData() {
            return new BpmnSharedDefinitionsData(this.smClass);
        }

        @objid ("39f7c18c-1750-4079-a260-79bd0c8da854")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnSharedDefinitionsImpl();
        }

    }

    @objid ("c419eb44-e373-45ec-a16b-71105b674d3a")
    public static class RootElementSmDependency extends SmMultipleDependency {
        @objid ("4ffdeae8-85a4-48de-9058-b400772c0658")
        private SmDependency symetricDep;

        @objid ("b5b363ce-58a8-4967-a34b-b0e443bb0a8b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnSharedDefinitionsData)data).mRootElement != null)? ((BpmnSharedDefinitionsData)data).mRootElement:SmMultipleDependency.EMPTY;
        }

        @objid ("521cdca4-72a3-47fe-bf40-7cc7a1b84d7a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnSharedDefinitionsData) data).mRootElement = values;
        }

        @objid ("1fd9713b-735d-408e-82f1-2d916bf550c2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSharedElementSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
