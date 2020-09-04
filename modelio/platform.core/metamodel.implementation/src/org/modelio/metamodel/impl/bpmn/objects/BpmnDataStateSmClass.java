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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataStateData;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemAwareElementSmClass;
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

@objid ("1d15032f-577b-4daa-8a38-531f0d847d64")
public class BpmnDataStateSmClass extends BpmnBaseElementSmClass {
    @objid ("eb46cd06-8085-46d2-b631-d1ccf27a6520")
    private SmDependency itemDep;

    @objid ("6516dd09-a582-4057-a249-d89d884ec02f")
    public BpmnDataStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b925f299-325b-4866-a0d9-96c4039e3e73")
    @Override
    public String getName() {
        return "BpmnDataState";
    }

    @objid ("2f33674b-3533-4b66-bd3c-373caa082fa1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("709921f8-b382-404d-ab4a-310a514dea5f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnDataState.class;
    }

    @objid ("19f21578-e5ed-4c68-bd7f-4eb5a6f19156")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("86596344-ecdc-4cd8-8e19-3140531c1b29")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f5259fc1-002c-4cdd-9915-e165b8e0f6c4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnDataStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.itemDep = new ItemSmDependency();
        this.itemDep.init("Item", this, metamodel.getMClass(BpmnItemAwareElement.MQNAME), 1, 1 );
        registerDependency(this.itemDep);
    }

    @objid ("37f1c238-10b2-4183-baed-63f2c24a3989")
    public SmDependency getItemDep() {
        if (this.itemDep == null) {
        	this.itemDep = this.getDependencyDef("Item");
        }
        return this.itemDep;
    }

    @objid ("95b6f06b-271e-4c66-9bfc-4fba2f432008")
    private static class BpmnDataStateObjectFactory implements ISmObjectFactory {
        @objid ("6bb66591-d2a3-4cd9-9d16-e68cf7493516")
        private BpmnDataStateSmClass smClass;

        @objid ("97cc36db-f5c6-4fea-a0af-22cb03095bb7")
        public BpmnDataStateObjectFactory(BpmnDataStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8e8f31b4-1f9b-4828-9791-f36f51f552b6")
        @Override
        public ISmObjectData createData() {
            return new BpmnDataStateData(this.smClass);
        }

        @objid ("25e3e751-815e-460e-af2a-b114d17a53a8")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnDataStateImpl();
        }

    }

    @objid ("08576930-f569-4f92-b576-681ab3f5547c")
    public static class ItemSmDependency extends SmSingleDependency {
        @objid ("02360bb0-b078-493d-b4c3-e7f8481d24c7")
        private SmDependency symetricDep;

        @objid ("a9c94b24-18cf-4d43-b746-fe99ba3404dd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnDataStateData) data).mItem;
        }

        @objid ("c1667b22-ccac-4ba8-99dc-cc205b8c7c50")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnDataStateData) data).mItem = value;
        }

        @objid ("ae1a1f43-06ad-435d-bb3b-d62c01e3bba7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnItemAwareElementSmClass)this.getTarget()).getDataStateDep();
            }
            return this.symetricDep;
        }

    }

}
