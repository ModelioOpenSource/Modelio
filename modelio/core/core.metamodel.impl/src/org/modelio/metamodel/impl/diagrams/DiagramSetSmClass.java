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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
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
    @objid ("0ab1e37f-3f0b-4638-8c7c-c592e06016f0")
    private SmDependency subDep;

    @objid ("3c07bdc1-c9dd-4f61-a6ec-6332fddab3bd")
    private SmDependency parentDep;

    @objid ("fe91aa08-c299-49eb-8228-e1dff6991104")
    private SmDependency referencedDiagramDep;

    @objid ("4aa27076-9996-499c-aac3-521e2b877115")
    private SmDependency ownerDep;

    @objid ("19422675-a016-4e6b-b70d-f000139afa40")
    public  DiagramSetSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("869adf1f-6454-46ee-a342-bee0bee4fcb1")
    @Override
    public String getName() {
        return "DiagramSet";
        
    }

    @objid ("290cbe5f-ea95-4d8d-a7a6-1201580b098c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c391d5d9-6a35-4e8b-b2d4-87216a6e47c4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DiagramSet.class;
        
    }

    @objid ("2517b197-c023-4f5c-be7b-526026e0428c")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("f98c2c0f-09de-4332-811d-b8990be2435b")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("43961ae3-32db-4131-bbf4-ff210b8a8cbd")
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

    @objid ("ecb4341a-6746-4083-a440-2b77bad6fecb")
    public SmDependency getSubDep() {
        if (this.subDep == null) {
        	this.subDep = this.getDependencyDef("Sub");
        }
        return this.subDep;
    }

    @objid ("e348ad4e-cc75-4cd0-b32b-19f438669f93")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("362dd0dc-7114-4626-8317-7a5153028075")
    public SmDependency getReferencedDiagramDep() {
        if (this.referencedDiagramDep == null) {
        	this.referencedDiagramDep = this.getDependencyDef("ReferencedDiagram");
        }
        return this.referencedDiagramDep;
    }

    @objid ("0e6e02b2-d91b-4d96-a3bd-80ba50f7299d")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("9c534dcf-2b78-45fc-81a0-d753242319b6")
    private static class DiagramSetObjectFactory implements ISmObjectFactory {
        @objid ("07ebf88f-4112-4fd5-8c30-b9e8b2752a94")
        private DiagramSetSmClass smClass;

        @objid ("b5995054-200a-4d41-bce8-b5e2a9721350")
        public  DiagramSetObjectFactory(DiagramSetSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("55637163-aab4-4168-8946-5420d8232112")
        @Override
        public ISmObjectData createData() {
            return new DiagramSetData(this.smClass);
        }

        @objid ("2a60b9a6-fd0a-4dfd-bb85-5c655cc64b49")
        @Override
        public SmObjectImpl createImpl() {
            return new DiagramSetImpl();
        }

    }

    @objid ("ba8608bc-1e83-47c5-bd09-d37903a0eaa0")
    public static class SubSmDependency extends SmMultipleDependency {
        @objid ("3050c70d-8c4d-4329-b4ac-afc2a4c34897")
        private SmDependency symetricDep;

        @objid ("87f8e1c7-1f11-4771-82ea-f8207b38debe")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((DiagramSetData)data).mSub != null)? ((DiagramSetData)data).mSub:SmMultipleDependency.EMPTY;
        }

        @objid ("ee2cf316-c0f1-498b-a676-6c8e200cb408")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((DiagramSetData) data).mSub = values;
            
        }

        @objid ("2ba7778a-baf8-48fe-9357-0ffc7368e6ad")
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
        @objid ("133a0f7f-84dd-493f-8552-0de6dd5533d4")
        private SmDependency symetricDep;

        @objid ("85d5f0c1-6466-41cf-b32e-e4f54f1332ce")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DiagramSetData) data).mParent;
        }

        @objid ("f67f5236-2111-496b-9aa2-4ddb7ba2094f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DiagramSetData) data).mParent = value;
        }

        @objid ("be05d8c5-b6b7-47c4-80f1-fa3c964c631f")
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
        @objid ("748ea1c9-0e10-40ef-a3c3-fe7575bcbb07")
        private SmDependency symetricDep;

        @objid ("4b5416e8-14a3-4057-9250-5ec0616d1073")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((DiagramSetData)data).mReferencedDiagram != null)? ((DiagramSetData)data).mReferencedDiagram:SmMultipleDependency.EMPTY;
        }

        @objid ("1fb0b889-0319-4a7a-a40c-da0d3e93bc05")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((DiagramSetData) data).mReferencedDiagram = values;
            
        }

        @objid ("bab01fc0-799b-4e95-b6e1-119ac644cdb4")
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
        @objid ("442f05dd-2a20-4246-8184-9b1a202a78ab")
        private SmDependency symetricDep;

        @objid ("c2d2131c-8798-476e-a376-4709aeb96dfc")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DiagramSetData) data).mOwner;
        }

        @objid ("8dc6bdaa-43ad-4fff-b2fc-1d4d5baf2dcf")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DiagramSetData) data).mOwner = value;
        }

        @objid ("aeed647b-a588-4d9f-8105-ddf2efb46caa")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractProjectSmClass)this.getTarget()).getDiagramRootDep();
            }
            return this.symetricDep;
            
        }

    }

}
