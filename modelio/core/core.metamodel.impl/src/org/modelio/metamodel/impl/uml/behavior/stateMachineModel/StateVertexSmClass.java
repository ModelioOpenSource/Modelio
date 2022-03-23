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

package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("f5cae989-99d6-4bea-a855-1f1a95220a78")
public class StateVertexSmClass extends UmlModelElementSmClass {
    @objid ("37c2ddba-2d6a-4127-8c24-77b6a22f0837")
    private SmDependency outGoingDep;

    @objid ("a34c381d-5958-41d7-a1f6-ec4f1b49cd57")
    private SmDependency incomingDep;

    @objid ("c9b18249-8bdc-45cb-afb6-ee74b66690bf")
    private SmDependency parentDep;

    @objid ("621dd0a0-7ffb-494d-97bf-8a5e8393d105")
    public  StateVertexSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("37db1423-6e3c-47aa-903d-524dc1bfc314")
    @Override
    public String getName() {
        return "StateVertex";
        
    }

    @objid ("87ae1164-330f-433d-a4a6-e1b49bb6ecf5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f934c8f9-129f-4590-8b7b-361dbef206e2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return StateVertex.class;
        
    }

    @objid ("d66c23da-0d86-442c-862f-2c68b652e662")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("d0ac53c6-e0be-4612-9066-921ad46cde30")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("ce044740-1039-4230-912c-3fdd152aff95")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new StateVertexObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.outGoingDep = new OutGoingSmDependency();
        this.outGoingDep.init("OutGoing", this, metamodel.getMClass(Transition.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.outGoingDep);
        
        this.incomingDep = new IncomingSmDependency();
        this.incomingDep.init("Incoming", this, metamodel.getMClass(Transition.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.incomingDep);
        
        this.parentDep = new ParentSmDependency();
        this.parentDep.init("Parent", this, metamodel.getMClass(Region.MQNAME), 0, 1 );
        registerDependency(this.parentDep);
        
        
    }

    @objid ("4bda5bcb-c4b0-4a5b-a3ee-d32aaba9db49")
    public SmDependency getOutGoingDep() {
        if (this.outGoingDep == null) {
        	this.outGoingDep = this.getDependencyDef("OutGoing");
        }
        return this.outGoingDep;
    }

    @objid ("f7fc3f16-d045-481f-9fd6-6dca4b60dba6")
    public SmDependency getIncomingDep() {
        if (this.incomingDep == null) {
        	this.incomingDep = this.getDependencyDef("Incoming");
        }
        return this.incomingDep;
    }

    @objid ("0ea12297-ca6b-48d3-be90-b2ef995faa27")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("4725edae-28c7-437b-88ae-931bee04862d")
    private static class StateVertexObjectFactory implements ISmObjectFactory {
        @objid ("c08fa754-a550-4b20-8143-e40334d864a0")
        private StateVertexSmClass smClass;

        @objid ("4c6da4f3-36db-4acc-a1ad-927c4b1b682d")
        public  StateVertexObjectFactory(StateVertexSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("362ba6ca-fad5-4eba-a6d2-b27f3eb859ff")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("e22e59b7-7a5d-49b1-a174-d13d3c450f50")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("dbf4386d-68d1-4bfd-b199-7fd3c9f2ea47")
    public static class OutGoingSmDependency extends SmMultipleDependency {
        @objid ("885cf604-4766-4672-81b8-5db7fe22ac09")
        private SmDependency symetricDep;

        @objid ("7e325b70-e851-4154-97dd-f5dd60c877a0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateVertexData)data).mOutGoing != null)? ((StateVertexData)data).mOutGoing:SmMultipleDependency.EMPTY;
        }

        @objid ("a88d1408-b4ba-4af3-aa29-231458827777")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateVertexData) data).mOutGoing = values;
            
        }

        @objid ("e86cff6c-18f8-4097-8537-b9a5e7ae2174")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TransitionSmClass)this.getTarget()).getSourceDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("92595478-3ada-434c-ac43-7492dc2cf60c")
    public static class IncomingSmDependency extends SmMultipleDependency {
        @objid ("b274802c-c858-401d-bb41-838988b556b7")
        private SmDependency symetricDep;

        @objid ("a078834b-856d-429a-ac75-356735893b2d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StateVertexData)data).mIncoming != null)? ((StateVertexData)data).mIncoming:SmMultipleDependency.EMPTY;
        }

        @objid ("64e811f0-c39d-43de-a847-501d20a90f32")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StateVertexData) data).mIncoming = values;
            
        }

        @objid ("d7b450e0-cb0d-467b-bd52-549178dac166")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TransitionSmClass)this.getTarget()).getTargetDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("67d14a4c-489f-4d25-8cea-9ebd0f734ca2")
    public static class ParentSmDependency extends SmSingleDependency {
        @objid ("fbcb0375-f30c-4194-a146-6c0ce32a7cbd")
        private SmDependency symetricDep;

        @objid ("46fbfe66-e980-4a66-ae94-8ac1ce54f804")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((StateVertexData) data).mParent;
        }

        @objid ("d9541ee4-50dc-4b0d-9875-478f180d7eb7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((StateVertexData) data).mParent = value;
        }

        @objid ("fddaec9c-62b7-49e3-9b90-b20594e948c4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RegionSmClass)this.getTarget()).getSubDep();
            }
            return this.symetricDep;
            
        }

    }

}
