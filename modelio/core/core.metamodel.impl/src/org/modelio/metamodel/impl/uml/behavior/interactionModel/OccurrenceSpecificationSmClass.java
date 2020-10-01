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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.GeneralOrderingSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionFragmentSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.OccurrenceSpecificationData;
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;
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

@objid ("aef59fb3-f2e2-4e01-85ed-20b1b90fb246")
public class OccurrenceSpecificationSmClass extends InteractionFragmentSmClass {
    @objid ("63a2fceb-da13-4251-94ea-ec9e27003310")
    private SmDependency toAfterDep;

    @objid ("0c5b519d-0055-49d1-8c8c-c5215ed6cb85")
    private SmDependency toBeforeDep;

    @objid ("f8c281e3-9c8d-4472-b84d-454754045407")
    public OccurrenceSpecificationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f369ad5c-3e6c-4855-8806-d0872fd932c4")
    @Override
    public String getName() {
        return "OccurrenceSpecification";
    }

    @objid ("099142d5-f52d-47c5-af47-edbeebc22d4c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9f8dbb8c-462b-4b39-b80b-db6b7c92a031")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return OccurrenceSpecification.class;
    }

    @objid ("c8a766af-2b3a-4924-8067-97f735281255")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("7bfa475c-69f2-4b77-851c-d4b3bef463cd")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("fac62a77-9fd2-42b5-9355-f81212cc62c4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(InteractionFragment.MQNAME);
        this.registerFactory(new OccurrenceSpecificationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.toAfterDep = new ToAfterSmDependency();
        this.toAfterDep.init("ToAfter", this, metamodel.getMClass(GeneralOrdering.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.toAfterDep);
        
        this.toBeforeDep = new ToBeforeSmDependency();
        this.toBeforeDep.init("ToBefore", this, metamodel.getMClass(GeneralOrdering.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.toBeforeDep);
    }

    @objid ("0aab069d-39f3-4f08-a308-c55ec4de5040")
    public SmDependency getToAfterDep() {
        if (this.toAfterDep == null) {
        	this.toAfterDep = this.getDependencyDef("ToAfter");
        }
        return this.toAfterDep;
    }

    @objid ("18be8ef0-9a7f-4481-8710-f5c7f4cc8dd4")
    public SmDependency getToBeforeDep() {
        if (this.toBeforeDep == null) {
        	this.toBeforeDep = this.getDependencyDef("ToBefore");
        }
        return this.toBeforeDep;
    }

    @objid ("cac35ac1-7543-49f6-8a9e-c0f72869ad23")
    private static class OccurrenceSpecificationObjectFactory implements ISmObjectFactory {
        @objid ("0feb4943-8326-4490-b4e2-fe628c055b79")
        private OccurrenceSpecificationSmClass smClass;

        @objid ("8b09bd92-b641-41bc-8ee4-b3e80dd38d21")
        public OccurrenceSpecificationObjectFactory(OccurrenceSpecificationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("283c0a28-3e67-4c5c-a6c4-f23ab775ddde")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("7d380f02-0205-4328-b593-82c7d8c180ae")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("4802ce7c-584b-4cf7-9c54-848745f93f81")
    public static class ToAfterSmDependency extends SmMultipleDependency {
        @objid ("c40b3015-5171-4483-b101-ebce3b64c165")
        private SmDependency symetricDep;

        @objid ("f3910c9a-e9d8-4345-9f5f-d10c0addc12d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OccurrenceSpecificationData)data).mToAfter != null)? ((OccurrenceSpecificationData)data).mToAfter:SmMultipleDependency.EMPTY;
        }

        @objid ("70abc0c9-d05b-422a-8249-ac414b75c767")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OccurrenceSpecificationData) data).mToAfter = values;
        }

        @objid ("f42bf38b-eb92-4644-b80c-3f9046127598")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralOrderingSmClass)this.getTarget()).getBeforeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9e24ffb3-b175-4ee2-b916-0885c5633d0c")
    public static class ToBeforeSmDependency extends SmMultipleDependency {
        @objid ("7b05204c-1061-4309-b63d-f987cbb7320e")
        private SmDependency symetricDep;

        @objid ("07cfb255-c485-4ac2-b9f7-7b8ec7816d67")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((OccurrenceSpecificationData)data).mToBefore != null)? ((OccurrenceSpecificationData)data).mToBefore:SmMultipleDependency.EMPTY;
        }

        @objid ("da412e6d-b402-4744-b33a-8e1d95207b56")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((OccurrenceSpecificationData) data).mToBefore = values;
        }

        @objid ("389bffc6-d5a4-4974-96f1-735a457ef5f1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralOrderingSmClass)this.getTarget()).getAfterDep();
            }
            return this.symetricDep;
        }

    }

}
