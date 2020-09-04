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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.PackageMergeData;
import org.modelio.metamodel.impl.uml.statik.PackageSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageMerge;
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

@objid ("ac032bd6-64d6-4ff8-bc0b-7bcf311eb66c")
public class PackageMergeSmClass extends UmlModelElementSmClass {
    @objid ("e7867be2-97db-4383-a9cc-64c6e3a87441")
    private SmDependency mergedPackageDep;

    @objid ("b92ea269-bce1-4420-9326-da296489f778")
    private SmDependency receivingPackageDep;

    @objid ("88217766-2aa8-430c-bd06-a7b8b909b06d")
    public PackageMergeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("601e80ff-e1cd-49fe-96eb-da05a371a132")
    @Override
    public String getName() {
        return "PackageMerge";
    }

    @objid ("412dda85-776c-45ea-91a1-b226b0a09c78")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("42ad9fca-f290-4083-a720-4ce2e4015564")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PackageMerge.class;
    }

    @objid ("c2b1bc39-f147-46e7-826f-2a03ab299adf")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("af941351-cf87-40ae-bd02-0e9a955ac3e2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("00ef0302-080b-41ac-9241-b2000000b165")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new PackageMergeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.mergedPackageDep = new MergedPackageSmDependency();
        this.mergedPackageDep.init("MergedPackage", this, metamodel.getMClass(Package.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.mergedPackageDep);
        
        this.receivingPackageDep = new ReceivingPackageSmDependency();
        this.receivingPackageDep.init("ReceivingPackage", this, metamodel.getMClass(Package.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.receivingPackageDep);
    }

    @objid ("2984f63f-9de0-444e-aa5d-baba136727c2")
    public SmDependency getMergedPackageDep() {
        if (this.mergedPackageDep == null) {
        	this.mergedPackageDep = this.getDependencyDef("MergedPackage");
        }
        return this.mergedPackageDep;
    }

    @objid ("d125543b-5403-41c4-a365-c67021b2508d")
    public SmDependency getReceivingPackageDep() {
        if (this.receivingPackageDep == null) {
        	this.receivingPackageDep = this.getDependencyDef("ReceivingPackage");
        }
        return this.receivingPackageDep;
    }

    @objid ("c751e636-0234-47cc-9453-851bced91a61")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("2add88da-cc1c-463c-af98-45d1d6876f43")
    private static class PackageMergeObjectFactory implements ISmObjectFactory {
        @objid ("99ecc6ca-5f29-4a14-a766-f1385057b8c8")
        private PackageMergeSmClass smClass;

        @objid ("8bf3bc5f-020a-4240-aabc-f6f1f5d45590")
        public PackageMergeObjectFactory(PackageMergeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fcc59b47-6c5a-486f-9951-13ede7fd38be")
        @Override
        public ISmObjectData createData() {
            return new PackageMergeData(this.smClass);
        }

        @objid ("78ed1abb-880e-47b4-ba7e-fd104fa91cf8")
        @Override
        public SmObjectImpl createImpl() {
            return new PackageMergeImpl();
        }

    }

    @objid ("3af300fd-a34d-4ddf-ab6d-f75bb6bfa7ef")
    public static class MergedPackageSmDependency extends SmSingleDependency {
        @objid ("1a8a87da-8daa-42ff-a503-c01a49edf33c")
        private SmDependency symetricDep;

        @objid ("9d7f7a78-9dc8-44bb-83db-25a9f48766a6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PackageMergeData) data).mMergedPackage;
        }

        @objid ("d250a4c5-40d0-4a75-96c8-ea14cec3389a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PackageMergeData) data).mMergedPackage = value;
        }

        @objid ("9a4eac23-2cb3-4302-9169-9c78f3c3ed1a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageSmClass)this.getTarget()).getReceivingMergeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("f5f08777-496f-4b33-ac72-b11a8ed79cf8")
    public static class ReceivingPackageSmDependency extends SmSingleDependency {
        @objid ("885045cb-beb5-4de4-8176-ef4ff3ab8917")
        private SmDependency symetricDep;

        @objid ("7862d21d-98da-4bf4-92de-bb0d9259b95f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PackageMergeData) data).mReceivingPackage;
        }

        @objid ("01a91284-c6e7-4c93-b689-11eda581bdd5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PackageMergeData) data).mReceivingPackage = value;
        }

        @objid ("dfe0120e-4a94-4fa9-b326-887deae6796f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageSmClass)this.getTarget()).getMergeDep();
            }
            return this.symetricDep;
        }

    }

}
