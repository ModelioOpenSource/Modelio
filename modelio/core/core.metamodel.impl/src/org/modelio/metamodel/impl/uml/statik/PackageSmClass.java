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
import org.modelio.metamodel.impl.mda.ProjectSmClass;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("a668a077-8567-41a2-ba8a-f0ce875e4f05")
public class PackageSmClass extends NameSpaceSmClass {
    @objid ("9a90a5ad-0e33-4938-96a9-cb5759eeb107")
    private SmAttribute isInstantiableAtt;

    @objid ("ccc51907-d208-4b5a-973f-861bbf03575e")
    private SmDependency receivingMergeDep;

    @objid ("3a459998-8225-4680-ad0e-325aefa186d3")
    private SmDependency representedDep;

    @objid ("c78e4d1a-f936-4949-b90d-5f03359ca14e")
    private SmDependency mergeDep;

    @objid ("c06d7a24-2c23-4873-83ab-e7b4528da6c4")
    private SmDependency packageImportingDep;

    @objid ("7bbdaf45-b378-45f8-b158-6b718cdc098e")
    public  PackageSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("bd127206-8df0-4d55-b415-f7795d7ea079")
    @Override
    public String getName() {
        return "Package";
        
    }

    @objid ("5b6bf1a6-f5d7-45c2-9768-8bd4fb0a2fff")
    @Override
    public Version getVersion() {
        return new Version("1.1.1");
    }

    @objid ("2a45efcf-d04b-41eb-bf84-1bbcbae8c629")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Package.class;
        
    }

    @objid ("ef39f1b8-c2aa-4dde-a0c7-dfaf094d73a3")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("58c7dd8b-42df-4285-927f-a8138fef0a06")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ed3c58d3-ef94-471b-a2c7-6b315bd9fe4a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(NameSpace.MQNAME);
        this.registerFactory(new PackageObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isInstantiableAtt = new IsInstantiableSmAttribute();
        this.isInstantiableAtt.init("IsInstantiable", this, Boolean.class );
        registerAttribute(this.isInstantiableAtt);
        
        
        // Initialize and register the SmDependency
        this.receivingMergeDep = new ReceivingMergeSmDependency();
        this.receivingMergeDep.init("ReceivingMerge", this, metamodel.getMClass(PackageMerge.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.receivingMergeDep);
        
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(Project.MQNAME), 0, 1 );
        registerDependency(this.representedDep);
        
        this.mergeDep = new MergeSmDependency();
        this.mergeDep.init("Merge", this, metamodel.getMClass(PackageMerge.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.mergeDep);
        
        this.packageImportingDep = new PackageImportingSmDependency();
        this.packageImportingDep.init("PackageImporting", this, metamodel.getMClass(PackageImport.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.packageImportingDep);
        
        
    }

    @objid ("8557a054-17bf-4feb-9b74-af0a98fe7668")
    public SmAttribute getIsInstantiableAtt() {
        if (this.isInstantiableAtt == null) {
        	this.isInstantiableAtt = this.getAttributeDef("IsInstantiable");
        }
        return this.isInstantiableAtt;
    }

    @objid ("925c31c8-2431-4cfe-aaf9-026a1403eeb5")
    public SmDependency getReceivingMergeDep() {
        if (this.receivingMergeDep == null) {
        	this.receivingMergeDep = this.getDependencyDef("ReceivingMerge");
        }
        return this.receivingMergeDep;
    }

    @objid ("bec52931-8f69-4f8d-bf15-9ca9a673093b")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("95a87911-222b-4cf9-82b1-eac50b3e5cc4")
    public SmDependency getMergeDep() {
        if (this.mergeDep == null) {
        	this.mergeDep = this.getDependencyDef("Merge");
        }
        return this.mergeDep;
    }

    @objid ("14580cb2-36f6-42da-85a4-496b090c3ad0")
    public SmDependency getPackageImportingDep() {
        if (this.packageImportingDep == null) {
        	this.packageImportingDep = this.getDependencyDef("PackageImporting");
        }
        return this.packageImportingDep;
    }

    @objid ("daae0695-3574-4c5c-8bd5-0f5846e75449")
    private static class PackageObjectFactory implements ISmObjectFactory {
        @objid ("f930a91e-ab59-48c8-9a5c-cffc5bb127e1")
        private PackageSmClass smClass;

        @objid ("ff9cc4f3-531a-4e66-be54-6664d91dc84c")
        public  PackageObjectFactory(PackageSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("5d85f8a8-654e-49ed-b511-63eded4e1131")
        @Override
        public ISmObjectData createData() {
            return new PackageData(this.smClass);
        }

        @objid ("89333505-c255-4f15-b9b0-d4f999d4d7ca")
        @Override
        public SmObjectImpl createImpl() {
            return new PackageImpl();
        }

    }

    @objid ("335473bd-2e36-454e-b26a-f23269a039b7")
    public static class IsInstantiableSmAttribute extends SmAttribute {
        @objid ("6a05acad-b8ad-4840-a737-6f035868fb27")
        public Object getValue(ISmObjectData data) {
            return ((PackageData) data).mIsInstantiable;
        }

        @objid ("fea44519-71c0-4e6c-8209-50164f595d45")
        public void setValue(ISmObjectData data, Object value) {
            ((PackageData) data).mIsInstantiable = value;
        }

    }

    @objid ("51c6ffb4-02eb-4e71-a82e-7e25fb9ed891")
    public static class ReceivingMergeSmDependency extends SmMultipleDependency {
        @objid ("68564e60-8b44-4da4-b6ad-c8f7a111efab")
        private SmDependency symetricDep;

        @objid ("d1e74c7c-0eba-4bb2-bc99-cd9308c9ae5e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PackageData)data).mReceivingMerge != null)? ((PackageData)data).mReceivingMerge:SmMultipleDependency.EMPTY;
        }

        @objid ("0ddb9be6-d3b1-49e5-a6ee-ce1fc31e6012")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PackageData) data).mReceivingMerge = values;
            
        }

        @objid ("d82b9ace-f662-4ae7-99c9-434a957d39c2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageMergeSmClass)this.getTarget()).getMergedPackageDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c86ae0a9-e287-4f2e-ae7d-4d47532aed62")
    public static class RepresentedSmDependency extends SmSingleDependency {
        @objid ("e1a35d4a-4688-4190-816e-20ee5874bbec")
        private SmDependency symetricDep;

        @objid ("394c42ad-fbda-4cae-ba22-af0c4147fa6f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PackageData) data).mRepresented;
        }

        @objid ("c588f61c-3577-4cfc-8b75-ba85b3e40d4b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PackageData) data).mRepresented = value;
        }

        @objid ("d94370d6-548e-4af2-8dd8-f9b0ab5711e8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProjectSmClass)this.getTarget()).getModelDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("36c6ac49-d1d9-4fd5-a6f1-e76525bae749")
    public static class MergeSmDependency extends SmMultipleDependency {
        @objid ("2ae711d2-62fe-41da-ad15-2c599774e50e")
        private SmDependency symetricDep;

        @objid ("eca8941a-870b-41db-9779-0830fe0b1675")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PackageData)data).mMerge != null)? ((PackageData)data).mMerge:SmMultipleDependency.EMPTY;
        }

        @objid ("acae8645-4d13-4b2e-9522-e57efbee3468")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PackageData) data).mMerge = values;
            
        }

        @objid ("952f689e-d06d-44bd-91d0-e686073de145")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageMergeSmClass)this.getTarget()).getReceivingPackageDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("1761709a-f0f3-442b-a756-da6715c8fe44")
    public static class PackageImportingSmDependency extends SmMultipleDependency {
        @objid ("bf2f31b9-fe62-4ebf-b002-4d0d74048272")
        private SmDependency symetricDep;

        @objid ("ee76fdae-f8f3-40c2-a398-d7f4fa3075f7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PackageData)data).mPackageImporting != null)? ((PackageData)data).mPackageImporting:SmMultipleDependency.EMPTY;
        }

        @objid ("ee66fc7b-9cfe-462e-a1ea-30dff7967a82")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PackageData) data).mPackageImporting = values;
            
        }

        @objid ("7dea9cd6-b864-4849-a185-258d67e631c0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageImportSmClass)this.getTarget()).getImportedPackageDep();
            }
            return this.symetricDep;
            
        }

    }

}
