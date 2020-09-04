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
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.impl.uml.statik.PackageImportData;
import org.modelio.metamodel.impl.uml.statik.PackageSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("2b5a751d-bf5a-4826-8664-010d1d624c31")
public class PackageImportSmClass extends UmlModelElementSmClass {
    @objid ("4fac188f-7b3b-4db8-943c-b5c389e33878")
    private SmAttribute visibilityAtt;

    @objid ("853a4dc5-785c-4ab9-8824-8a7b3c14d9b1")
    private SmDependency importingOperationDep;

    @objid ("5d91d8ac-ffa3-491f-a195-b9407e9455a9")
    private SmDependency importingNameSpaceDep;

    @objid ("754dd968-9c69-425e-994d-f7af28152583")
    private SmDependency importedPackageDep;

    @objid ("a9d06b02-fdc7-4134-87ff-17a5d40281d2")
    public PackageImportSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7b6ab6cb-98f7-4621-a276-c6cacfc05d40")
    @Override
    public String getName() {
        return "PackageImport";
    }

    @objid ("7d08de56-84e8-4f90-ad89-110d8d6f8df2")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b00e18a8-df05-4cc8-ae56-a57787f048b3")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return PackageImport.class;
    }

    @objid ("165548db-10d5-4941-8c16-47e4ddf46e5b")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("78364865-4a21-44f3-80f5-1f7b699d0821")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("938b7db9-168c-40fa-8fc6-f9a06690270f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new PackageImportObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.visibilityAtt = new VisibilitySmAttribute();
        this.visibilityAtt.init("Visibility", this, VisibilityMode.class );
        registerAttribute(this.visibilityAtt);
        
        
        // Initialize and register the SmDependency
        this.importingOperationDep = new ImportingOperationSmDependency();
        this.importingOperationDep.init("ImportingOperation", this, metamodel.getMClass(Operation.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.importingOperationDep);
        
        this.importingNameSpaceDep = new ImportingNameSpaceSmDependency();
        this.importingNameSpaceDep.init("ImportingNameSpace", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.importingNameSpaceDep);
        
        this.importedPackageDep = new ImportedPackageSmDependency();
        this.importedPackageDep.init("ImportedPackage", this, metamodel.getMClass(Package.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.importedPackageDep);
    }

    @objid ("a8c6d364-7f53-47f1-ab1e-d7cf5c874da5")
    public SmAttribute getVisibilityAtt() {
        if (this.visibilityAtt == null) {
        	this.visibilityAtt = this.getAttributeDef("Visibility");
        }
        return this.visibilityAtt;
    }

    @objid ("2423613d-af81-4461-99db-54faa8cb32c8")
    public SmDependency getImportingOperationDep() {
        if (this.importingOperationDep == null) {
        	this.importingOperationDep = this.getDependencyDef("ImportingOperation");
        }
        return this.importingOperationDep;
    }

    @objid ("ec778ea3-95a2-417c-875d-c89eee3398b0")
    public SmDependency getImportingNameSpaceDep() {
        if (this.importingNameSpaceDep == null) {
        	this.importingNameSpaceDep = this.getDependencyDef("ImportingNameSpace");
        }
        return this.importingNameSpaceDep;
    }

    @objid ("5ffcc37f-1297-4ba0-8061-cd1dfcfcee37")
    public SmDependency getImportedPackageDep() {
        if (this.importedPackageDep == null) {
        	this.importedPackageDep = this.getDependencyDef("ImportedPackage");
        }
        return this.importedPackageDep;
    }

    @objid ("1e3208cc-b4b3-4540-b852-eb59fe0b181f")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("92dc484c-0da9-4e9b-a07b-19a4e20d5190")
    private static class PackageImportObjectFactory implements ISmObjectFactory {
        @objid ("c46a2b00-3cd5-435a-a9a0-e17815d7348d")
        private PackageImportSmClass smClass;

        @objid ("ce9e4ffc-ada7-401d-b0f5-b393db60485d")
        public PackageImportObjectFactory(PackageImportSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("7e845693-e976-44d0-8a72-18bf477249dd")
        @Override
        public ISmObjectData createData() {
            return new PackageImportData(this.smClass);
        }

        @objid ("8031cfd5-3079-43d9-b65c-c4726cf17080")
        @Override
        public SmObjectImpl createImpl() {
            return new PackageImportImpl();
        }

    }

    @objid ("63081d34-6f56-4337-9c49-584697c76772")
    public static class VisibilitySmAttribute extends SmAttribute {
        @objid ("e4aaf170-4f76-48ba-837b-2a08a018aa73")
        public Object getValue(ISmObjectData data) {
            return ((PackageImportData) data).mVisibility;
        }

        @objid ("f56e5cdf-b183-4d7e-b982-42966347ca07")
        public void setValue(ISmObjectData data, Object value) {
            ((PackageImportData) data).mVisibility = value;
        }

    }

    @objid ("98ecc494-2818-4b25-86ae-76dcac30b090")
    public static class ImportingOperationSmDependency extends SmSingleDependency {
        @objid ("1280be54-acd1-4083-9d9e-33800f1b5cd3")
        private SmDependency symetricDep;

        @objid ("5746473b-1557-49bd-bd25-8a0292d5b32b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PackageImportData) data).mImportingOperation;
        }

        @objid ("88ffb271-58b1-4af3-907d-3a6b752b89a5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PackageImportData) data).mImportingOperation = value;
        }

        @objid ("2accf9bb-cd66-4841-afb6-53b05227be67")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getOwnedPackageImportDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("de844e8b-f8ce-4598-bb8e-48f29c549527")
    public static class ImportingNameSpaceSmDependency extends SmSingleDependency {
        @objid ("73455efb-d0e3-4e12-b748-2964f43cc734")
        private SmDependency symetricDep;

        @objid ("d8cb58a4-f0dd-40a0-95b5-eb77240574f2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PackageImportData) data).mImportingNameSpace;
        }

        @objid ("8d6ec02b-b743-4068-8a4d-713056e786e5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PackageImportData) data).mImportingNameSpace = value;
        }

        @objid ("61205128-85ab-47d8-a39c-ed97194ea339")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getOwnedPackageImportDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("13db9bac-5932-46e3-a454-20bab0d87e80")
    public static class ImportedPackageSmDependency extends SmSingleDependency {
        @objid ("4907c72d-fc7c-4600-8ec7-de7be899430a")
        private SmDependency symetricDep;

        @objid ("faa1fc0c-30a8-4b30-ad23-2dd447bfb0a5")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((PackageImportData) data).mImportedPackage;
        }

        @objid ("6ae266d3-cd78-432a-8602-6ed262c70ed9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((PackageImportData) data).mImportedPackage = value;
        }

        @objid ("550d2693-398a-4d1f-9c92-9ed31f36a420")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageSmClass)this.getTarget()).getPackageImportingDep();
            }
            return this.symetricDep;
        }

    }

}
