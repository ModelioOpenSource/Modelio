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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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

@objid ("631adaa3-d7f7-47b1-8dba-62b2b07541c7")
public class DiagramSetSmClass extends ModelElementSmClass {
    @objid ("c36231c2-b334-429a-9190-8997944b41fe")
    private SmDependency subDep;

    @objid ("07e464e8-6792-4aa9-83c8-1317133a4d55")
    private SmDependency parentDep;

    @objid ("6165a351-908a-46d0-ac16-269a35fc136c")
    private SmDependency referencedDiagramDep;

    @objid ("d3f03081-0004-4483-9fc5-b60856835dc0")
    private SmDependency ownerDep;

    @objid ("3cb6a054-5b33-4c6d-87de-bc7951e45b39")
    public  DiagramSetSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("78a02033-ecd2-48e3-b7c6-268fafb77e1f")
    @Override
    public String getName() {
        return "DiagramSet";
        
    }

    @objid ("ccf70e13-5eaa-4414-af7c-da0aed9592ed")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ba7e0089-77e9-42d1-bec4-1f37f4b68a0f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DiagramSet.class;
        
    }

    @objid ("704d5ab4-f582-4114-9eec-f56ada4d19ea")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("180b5fc1-4d37-43a6-9138-cd9ee8bfc357")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("4b371dde-c732-46d0-b378-6719b60269ce")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new DiagramSetObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.subDep = new SubSmDependency();
        this.subDep.init("Sub", this, metamodel.getMClass(DiagramSet.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.subDep);
        
        this.parentDep = new ParentSmDependency();
        this.parentDep.init("Parent", this, metamodel.getMClass(DiagramSet.MQNAME), 0, 1 );
        registerDependency(this.parentDep);
        
        this.referencedDiagramDep = new ReferencedDiagramSmDependency();
        this.referencedDiagramDep.init("ReferencedDiagram", this, metamodel.getMClass(AbstractDiagram.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.referencedDiagramDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(AbstractProject.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        
    }

    @objid ("6d39ba2e-3e1c-4799-b42f-36af85a0b599")
    public SmDependency getSubDep() {
        if (this.subDep == null) {
        	this.subDep = this.getDependencyDef("Sub");
        }
        return this.subDep;
    }

    @objid ("2d9bb977-a5e9-4c39-8dc9-ea5833032831")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("9bac2573-4a85-4fcd-b495-d8a1c614930c")
    public SmDependency getReferencedDiagramDep() {
        if (this.referencedDiagramDep == null) {
        	this.referencedDiagramDep = this.getDependencyDef("ReferencedDiagram");
        }
        return this.referencedDiagramDep;
    }

    @objid ("5b2590e8-915c-4cdf-a2a5-f8bcd6eefa2a")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("9c534dcf-2b78-45fc-81a0-d753242319b6")
    private static class DiagramSetObjectFactory implements ISmObjectFactory {
        @objid ("94a0429a-44e1-4b1d-923c-3adbcbb1954d")
        private DiagramSetSmClass smClass;

        @objid ("1de30304-f87f-4987-a49c-92bd762b31d5")
        public  DiagramSetObjectFactory(DiagramSetSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fe685253-b323-4c32-a4ce-da94328cf5c8")
        @Override
        public ISmObjectData createData() {
            return new DiagramSetData(this.smClass);
        }

        @objid ("9e4553e9-3124-40e7-bac7-8f29663cb225")
        @Override
        public SmObjectImpl createImpl() {
            return new DiagramSetImpl();
        }

    }

    @objid ("ba8608bc-1e83-47c5-bd09-d37903a0eaa0")
    public static class SubSmDependency extends SmMultipleDependency {
        @objid ("c71e0e6a-941a-4223-a1eb-6d1f953d02a0")
        private SmDependency symetricDep;

        @objid ("f97e8b54-a1ce-47ba-a8d9-ffeea5348a9d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((DiagramSetData)data).mSub != null)? ((DiagramSetData)data).mSub:SmMultipleDependency.EMPTY;
        }

        @objid ("20f47b1e-b096-4dc8-9cf7-052409d790ce")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((DiagramSetData) data).mSub = values;
            
        }

        @objid ("b38ba14f-bf2d-4463-a9df-c1827e083e76")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getParentDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("1a953680-2cd4-45a8-9eff-321487482312")
    public static class ParentSmDependency extends SmSingleDependency {
        @objid ("6f1f1819-c1d5-4d1a-a8e5-0e9928fd162a")
        private SmDependency symetricDep;

        @objid ("684256fa-bc93-4d54-93c8-61815aab8ac3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DiagramSetData) data).mParent;
        }

        @objid ("b37dbce2-8206-4383-966f-9bb3b8dd95a0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DiagramSetData) data).mParent = value;
        }

        @objid ("9a472e4d-5db5-4188-9f81-60f2dfe77c94")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getSubDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("08a28549-8c8c-4ebd-ad72-e09cf764f9be")
    public static class ReferencedDiagramSmDependency extends SmMultipleDependency {
        @objid ("0aaecac7-293e-4515-89bd-37dff4457014")
        private SmDependency symetricDep;

        @objid ("a1b1e7de-c6b7-40c9-8e95-a4025abc91f2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((DiagramSetData)data).mReferencedDiagram != null)? ((DiagramSetData)data).mReferencedDiagram:SmMultipleDependency.EMPTY;
        }

        @objid ("44248cbc-484e-4843-9cc0-3dcee690984b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((DiagramSetData) data).mReferencedDiagram = values;
            
        }

        @objid ("4b579a32-4436-42f8-9d63-b735e4282fa0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractDiagramSmClass)this.getTarget()).getReferencingSetDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("d193af4e-8c0c-4c15-9386-748ba65c089f")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("cb12c4ea-e124-4168-b62b-3eaa55afd134")
        private SmDependency symetricDep;

        @objid ("1627568c-2ac6-45b8-8557-fadf7bf55bdc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DiagramSetData) data).mOwner;
        }

        @objid ("4deef760-6708-4dde-a06e-67d69e003612")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DiagramSetData) data).mOwner = value;
        }

        @objid ("dd1f4565-9764-4fa4-97e2-00c02871a587")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractProjectSmClass)this.getTarget()).getDiagramRootDep();
            }
            return this.symetricDep;
            
        }

    }

}
