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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.BindableInstanceData;
import org.modelio.metamodel.impl.uml.statik.BindingSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
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

@objid ("929c1766-d895-4a01-9a64-35321250afcc")
public class BindableInstanceSmClass extends InstanceSmClass {
    @objid ("7d758333-73d4-4628-ad68-fd3cd7895595")
    private SmDependency clusterDep;

    @objid ("07247713-9599-4cb6-9d31-8a64d990a34b")
    private SmDependency internalOwnerDep;

    @objid ("ef739940-2b2f-46ca-8aee-50c77fb79750")
    private SmDependency representationDep;

    @objid ("731e1600-dae6-4b4f-a0f1-bb9548f9e16b")
    private SmDependency representedFeatureDep;

    @objid ("2ba0b527-1d56-4de4-bfb5-507e5c24b0e9")
    public BindableInstanceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("064c06b3-bcc6-4635-b7bb-12b17f770fc5")
    @Override
    public String getName() {
        return "BindableInstance";
    }

    @objid ("f243f473-1372-450c-9dd8-5db5c3705a11")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("4a53cc57-f18d-45fb-b797-4e19700bb282")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BindableInstance.class;
    }

    @objid ("cf82cb8c-c499-4d6a-964e-17f528e05b4d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("0464bf38-ca7a-4593-9f6f-d719cdb4a64a")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("a14ce21f-9bcb-4bf2-8cff-9385770216d2")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Instance.MQNAME);
        this.registerFactory(new BindableInstanceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.clusterDep = new ClusterSmDependency();
        this.clusterDep.init("Cluster", this, metamodel.getMClass(Instance.MQNAME), 0, 1 );
        registerDependency(this.clusterDep);
        
        this.internalOwnerDep = new InternalOwnerSmDependency();
        this.internalOwnerDep.init("InternalOwner", this, metamodel.getMClass(Classifier.MQNAME), 0, 1 );
        registerDependency(this.internalOwnerDep);
        
        this.representationDep = new RepresentationSmDependency();
        this.representationDep.init("Representation", this, metamodel.getMClass(Binding.MQNAME), 0, -1 );
        registerDependency(this.representationDep);
        
        this.representedFeatureDep = new RepresentedFeatureSmDependency();
        this.representedFeatureDep.init("RepresentedFeature", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedFeatureDep);
    }

    @objid ("829a5ce7-bb6c-495a-9379-bd8d09ca6711")
    public SmDependency getClusterDep() {
        if (this.clusterDep == null) {
        	this.clusterDep = this.getDependencyDef("Cluster");
        }
        return this.clusterDep;
    }

    @objid ("06cc88af-a828-4fee-9bd0-cb1afce8efa1")
    public SmDependency getInternalOwnerDep() {
        if (this.internalOwnerDep == null) {
        	this.internalOwnerDep = this.getDependencyDef("InternalOwner");
        }
        return this.internalOwnerDep;
    }

    @objid ("96e4f4bb-83a6-4649-b0e3-cb84f5172e9d")
    public SmDependency getRepresentationDep() {
        if (this.representationDep == null) {
        	this.representationDep = this.getDependencyDef("Representation");
        }
        return this.representationDep;
    }

    @objid ("634c3521-c5aa-44b5-becf-029a5224e12c")
    public SmDependency getRepresentedFeatureDep() {
        if (this.representedFeatureDep == null) {
        	this.representedFeatureDep = this.getDependencyDef("RepresentedFeature");
        }
        return this.representedFeatureDep;
    }

    @objid ("9cf26865-0d09-4dd3-a0e6-fd48a1f71a29")
    private static class BindableInstanceObjectFactory implements ISmObjectFactory {
        @objid ("c61a60ba-a533-42dc-9107-5057b420cbf1")
        private BindableInstanceSmClass smClass;

        @objid ("a699ad29-13c6-4347-b9f3-94284f244e21")
        public BindableInstanceObjectFactory(BindableInstanceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3dc0399e-29ac-4a40-9bc4-7bda2c80759c")
        @Override
        public ISmObjectData createData() {
            return new BindableInstanceData(this.smClass);
        }

        @objid ("dba2c9e4-5040-4c6b-b329-760eea91b7c8")
        @Override
        public SmObjectImpl createImpl() {
            return new BindableInstanceImpl();
        }

    }

    @objid ("8822c08a-5a58-4c7d-b551-2ada873d7b70")
    public static class ClusterSmDependency extends SmSingleDependency {
        @objid ("71656727-3b74-4652-b84b-91ee3e85ec79")
        private SmDependency symetricDep;

        @objid ("4200c85d-eff5-4d50-a61e-0b5685c6dbbd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindableInstanceData) data).mCluster;
        }

        @objid ("b07d2c85-1575-4d86-971f-338479243326")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindableInstanceData) data).mCluster = value;
        }

        @objid ("7e70a89c-2310-40e7-b148-4566efe09a34")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getPartDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("27ffb7fe-d71c-4f2e-b992-5f8c555f6e99")
    public static class InternalOwnerSmDependency extends SmSingleDependency {
        @objid ("61bea0b6-556f-4876-a07a-15a3cfbb30c7")
        private SmDependency symetricDep;

        @objid ("beb7e7e3-9b63-43dd-9efd-dbaf305f2334")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindableInstanceData) data).mInternalOwner;
        }

        @objid ("50168be6-8b6f-48f8-95dc-a5f222a2bc98")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindableInstanceData) data).mInternalOwner = value;
        }

        @objid ("6d387bc8-9f82-4e46-a795-373bb71a1adb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getInternalStructureDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("eed184a9-9094-451a-afdb-0e6f898d5a3e")
    public static class RepresentationSmDependency extends SmMultipleDependency {
        @objid ("a424cf7f-f702-46ee-8f2b-771d163a3226")
        private SmDependency symetricDep;

        @objid ("fadb0cf5-f6be-4702-a293-7e76f4cc57d1")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BindableInstanceData)data).mRepresentation != null)? ((BindableInstanceData)data).mRepresentation:SmMultipleDependency.EMPTY;
        }

        @objid ("89a41b45-5874-4057-9bb8-7c2c80df3672")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BindableInstanceData) data).mRepresentation = values;
        }

        @objid ("f4aa3e90-0f7e-4196-bad6-5f24d00e5ac1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindingSmClass)this.getTarget()).getRoleDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9fb9688f-85fd-4811-9a44-5bada06fd5b9")
    public static class RepresentedFeatureSmDependency extends SmSingleDependency {
        @objid ("ba0d818b-6ead-43d8-8659-c2aefca9b45a")
        private SmDependency symetricDep;

        @objid ("9aadadc0-9864-4002-ac75-a4b4776cbabf")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BindableInstanceData) data).mRepresentedFeature;
        }

        @objid ("ce02988f-01b0-46aa-b8b9-e927b0fc10e0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BindableInstanceData) data).mRepresentedFeature = value;
        }

        @objid ("37b77d80-5d52-4bdb-a8db-223e2465b89c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getRepresentingInstanceDep();
            }
            return this.symetricDep;
        }

    }

}
