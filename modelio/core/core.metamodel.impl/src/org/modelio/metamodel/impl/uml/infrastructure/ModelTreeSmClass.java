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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelTreeData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
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

@objid ("7b1d2689-2014-4c55-a1b7-be6b622341f9")
public class ModelTreeSmClass extends UmlModelElementSmClass {
    @objid ("d9462489-bfd9-493b-bf7c-8f56cdd1a6bd")
    private SmDependency ownerDep;

    @objid ("bda6eb72-ea22-4923-a3dc-f023679678b5")
    private SmDependency ownedElementDep;

    @objid ("2adda4a8-bba9-4b94-9c27-c67dfba238e2")
    public ModelTreeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("31a2f214-957a-490f-9478-2fae151145df")
    @Override
    public String getName() {
        return "ModelTree";
    }

    @objid ("767fe1a7-e053-4025-b753-ddeb11c28a0e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("853505c8-829b-4fe8-a65a-639be2c5472e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ModelTree.class;
    }

    @objid ("abacb31d-546b-4400-9a5d-e56fe28ed581")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("87af6c0d-2144-44ed-9bcd-ddacf765fdd7")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("9b270fb6-f2ff-425a-89df-bbc58569f56f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ModelTreeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(ModelTree.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.ownedElementDep = new OwnedElementSmDependency();
        this.ownedElementDep.init("OwnedElement", this, metamodel.getMClass(ModelTree.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedElementDep);
    }

    @objid ("9daafac9-91d5-4ec9-8cbf-ddbda5a145ed")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("06e1efa4-052c-4790-bbda-4aa405ce9cc9")
    public SmDependency getOwnedElementDep() {
        if (this.ownedElementDep == null) {
        	this.ownedElementDep = this.getDependencyDef("OwnedElement");
        }
        return this.ownedElementDep;
    }

    @objid ("2d0110b5-18e6-4be3-be4f-ec778796f31c")
    private static class ModelTreeObjectFactory implements ISmObjectFactory {
        @objid ("c5b5cce4-b14b-47d2-a30c-d8df9cca80dc")
        private ModelTreeSmClass smClass;

        @objid ("9cd49389-fa88-40fb-9167-eb6a34d038b6")
        public ModelTreeObjectFactory(ModelTreeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("af97c3d0-635c-46b1-bfd5-10d26a829f37")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("3e84e0d8-4aba-4f48-b00f-79625ff0297f")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("57d0f186-e461-438f-b354-a3aceef40f69")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("34611572-868b-4aca-838d-22c30c0a2091")
        private SmDependency symetricDep;

        @objid ("3a211357-ec6b-4e77-ba0e-df8609d620f7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ModelTreeData) data).mOwner;
        }

        @objid ("74a627d9-96da-42b6-8ad2-8f33d5816c2b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ModelTreeData) data).mOwner = value;
        }

        @objid ("64ebae6b-b10c-4b0a-a191-1ce92cf7f33e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelTreeSmClass)this.getTarget()).getOwnedElementDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9b274058-9491-4f7d-a2f4-62838dc7a8bc")
    public static class OwnedElementSmDependency extends SmMultipleDependency {
        @objid ("11acc4b7-bbbe-463c-ae40-b3b828708476")
        private SmDependency symetricDep;

        @objid ("352fd9aa-9622-4998-8abe-9d6fd24ec1a0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ModelTreeData)data).mOwnedElement != null)? ((ModelTreeData)data).mOwnedElement:SmMultipleDependency.EMPTY;
        }

        @objid ("116edc56-22c5-494e-bfb4-6200c720bab3")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ModelTreeData) data).mOwnedElement = values;
        }

        @objid ("8bb8839a-e73b-4d47-9f13-1ba916d08935")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelTreeSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
