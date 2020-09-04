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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedDefinitionsSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementData;
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

@objid ("d5f4ebb4-3968-4aeb-aa18-51f916d1551c")
public class BpmnSharedElementSmClass extends BpmnBaseElementSmClass {
    @objid ("849fc03e-d273-4c52-a3bc-164e966f5aed")
    private SmDependency ownerDep;

    @objid ("02b62fad-fa7a-45a2-8df1-00e66f090d2e")
    public BpmnSharedElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ee57e5a4-eb6c-45a6-ad70-6a97a55d4a6b")
    @Override
    public String getName() {
        return "BpmnSharedElement";
    }

    @objid ("aaf04eaf-8c33-4b0b-80a7-17b95bef4de5")
    @Override
    public Version getVersion() {
        return new Version("2.2.0");
    }

    @objid ("0cd6ab6a-9fb2-4167-95f3-2e14916842f2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSharedElement.class;
    }

    @objid ("3282c774-2452-4d87-8ce4-08df8de993d2")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5ffe9df8-4407-4c07-997a-e2f4718b38da")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("0360490b-a9c4-40b9-a886-a510cdc1e452")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnSharedElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(BpmnSharedDefinitions.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("6736667b-d86e-4022-840f-280eef88be69")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("1b298ded-cb75-49f0-b31b-2827087b721b")
    private static class BpmnSharedElementObjectFactory implements ISmObjectFactory {
        @objid ("04905c87-cf3d-4434-9ef3-54ee0fade773")
        private BpmnSharedElementSmClass smClass;

        @objid ("17008dcc-311a-44f9-b829-795d6a2dcc6c")
        public BpmnSharedElementObjectFactory(BpmnSharedElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("670f1044-721d-42ea-8f57-996df2fbcc7d")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("964c4938-c90f-4761-8123-dbe475925540")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("1e92a3d4-7489-4834-8417-f938d95a746d")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("e5076d19-a3a7-4887-8fed-b5925ce357da")
        private SmDependency symetricDep;

        @objid ("cf50d350-41ab-45cb-944b-c6241abf3e89")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnSharedElementData) data).mOwner;
        }

        @objid ("5f725702-1d4d-4f95-98b0-7bbafd65e4f0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnSharedElementData) data).mOwner = value;
        }

        @objid ("d0bb0181-0d0a-4c15-a0e4-8ba326ca6f4b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSharedDefinitionsSmClass)this.getTarget()).getRootElementDep();
            }
            return this.symetricDep;
        }

    }

}
