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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.BindingSmClass;
import org.modelio.metamodel.impl.uml.statik.CollaborationSmClass;
import org.modelio.metamodel.impl.uml.statik.CollaborationUseData;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
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

@objid ("4b183c29-53e2-461f-8c9e-7d497ca87002")
public class CollaborationUseSmClass extends UmlModelElementSmClass {
    @objid ("ea5e16c0-ca2d-49b1-af97-00151f205b11")
    private SmDependency typeDep;

    @objid ("0942af8b-4a01-481c-92cf-ab5afa7ed1c6")
    private SmDependency nRepresentedDep;

    @objid ("c9bb1604-2f3e-4755-9c28-db94539e762a")
    private SmDependency oRepresentedDep;

    @objid ("90af93e9-0367-4143-96be-3b50d27d5fbc")
    private SmDependency roleBindingDep;

    @objid ("c89a4fdf-9c27-4ac0-bd68-877d817cb6eb")
    public CollaborationUseSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("eaa009c7-dfd0-4cff-b2e5-78e93868c4db")
    @Override
    public String getName() {
        return "CollaborationUse";
    }

    @objid ("49102a73-736c-446e-9782-86e5ea9d11d8")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0d233918-d6c9-4410-af27-5fd0980fe096")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CollaborationUse.class;
    }

    @objid ("f147f80c-9506-45de-8543-bcb00e4852f3")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("b3844de9-3432-4ed0-9ad0-f227ad3f1390")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("22385858-e0a5-4204-b3d5-796d66957eb1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new CollaborationUseObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(Collaboration.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.nRepresentedDep = new NRepresentedSmDependency();
        this.nRepresentedDep.init("NRepresented", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 );
        registerDependency(this.nRepresentedDep);
        
        this.oRepresentedDep = new ORepresentedSmDependency();
        this.oRepresentedDep.init("ORepresented", this, metamodel.getMClass(Operation.MQNAME), 0, 1 );
        registerDependency(this.oRepresentedDep);
        
        this.roleBindingDep = new RoleBindingSmDependency();
        this.roleBindingDep.init("RoleBinding", this, metamodel.getMClass(Binding.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.roleBindingDep);
    }

    @objid ("60d7490f-dc68-4b86-99ef-adb12b5fb0f8")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("122ad30d-df7a-4162-947d-08e49777eb47")
    public SmDependency getNRepresentedDep() {
        if (this.nRepresentedDep == null) {
        	this.nRepresentedDep = this.getDependencyDef("NRepresented");
        }
        return this.nRepresentedDep;
    }

    @objid ("a65292c3-8dba-4a2d-b4a9-7222080a39fe")
    public SmDependency getORepresentedDep() {
        if (this.oRepresentedDep == null) {
        	this.oRepresentedDep = this.getDependencyDef("ORepresented");
        }
        return this.oRepresentedDep;
    }

    @objid ("0241d308-ac31-446e-913c-be4324722254")
    public SmDependency getRoleBindingDep() {
        if (this.roleBindingDep == null) {
        	this.roleBindingDep = this.getDependencyDef("RoleBinding");
        }
        return this.roleBindingDep;
    }

    @objid ("9e00541f-6f03-465e-9e62-9605dc546c1d")
    private static class CollaborationUseObjectFactory implements ISmObjectFactory {
        @objid ("254305f6-e787-4a84-a915-5cfa6c4e7730")
        private CollaborationUseSmClass smClass;

        @objid ("f8f8f0f6-3159-4b83-bb40-e0e13916ea91")
        public CollaborationUseObjectFactory(CollaborationUseSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d2c285b8-37b7-4d19-96cf-ed9f8f73921b")
        @Override
        public ISmObjectData createData() {
            return new CollaborationUseData(this.smClass);
        }

        @objid ("1250c6a6-0241-4f5e-a8e3-719b0b22c85b")
        @Override
        public SmObjectImpl createImpl() {
            return new CollaborationUseImpl();
        }

    }

    @objid ("c07ef95f-fdb5-4a55-9ffb-4c2edf9d2bd0")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("01b029a3-0476-4fa9-ad09-977c21018de9")
        private SmDependency symetricDep;

        @objid ("e5fd5796-5a90-40b8-aaeb-739b7af4a7e9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CollaborationUseData) data).mType;
        }

        @objid ("39d05ad4-9de9-4612-b8d4-3977bbedb13b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CollaborationUseData) data).mType = value;
        }

        @objid ("f1f2b9ad-4620-4dc6-85ff-38ca5fe3b1de")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CollaborationSmClass)this.getTarget()).getOccurrenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("95b70866-165b-4ac5-ab17-ed43a1f4213f")
    public static class NRepresentedSmDependency extends SmSingleDependency {
        @objid ("97b6e069-beb4-48cd-b51c-2f2868765ad9")
        private SmDependency symetricDep;

        @objid ("fdfee0d3-c73f-4e5c-a2d2-e703ab27f933")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CollaborationUseData) data).mNRepresented;
        }

        @objid ("25d0b533-469f-4ec6-a17f-18fe973e7da9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CollaborationUseData) data).mNRepresented = value;
        }

        @objid ("90450862-f239-4134-b57c-9e3ad444aacc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getOwnedCollaborationUseDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("67af4c08-244b-4a3f-8c26-7c731716c5c0")
    public static class ORepresentedSmDependency extends SmSingleDependency {
        @objid ("ff10dad9-3786-41fc-98ea-f11a5fd089fd")
        private SmDependency symetricDep;

        @objid ("17baf75a-93df-46e9-b28e-ea3e5ccb9370")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CollaborationUseData) data).mORepresented;
        }

        @objid ("541ccb18-40fe-43af-826f-28253a534bce")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CollaborationUseData) data).mORepresented = value;
        }

        @objid ("91efcdef-f449-488e-9ffa-093a1822f0cd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getOwnedCollaborationUseDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("cf19e713-b0d8-423e-9ece-70892f402cf5")
    public static class RoleBindingSmDependency extends SmMultipleDependency {
        @objid ("c0007737-14e2-4e3b-9b7e-00d3b42a44d9")
        private SmDependency symetricDep;

        @objid ("f61faeb2-00e1-40f9-8316-1f4e7702d9c4")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CollaborationUseData)data).mRoleBinding != null)? ((CollaborationUseData)data).mRoleBinding:SmMultipleDependency.EMPTY;
        }

        @objid ("f5d258bf-a76d-4974-a11f-84ff3ec961a9")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CollaborationUseData) data).mRoleBinding = values;
        }

        @objid ("b0da99c1-d958-4bcc-a1b4-860d9e778223")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindingSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
