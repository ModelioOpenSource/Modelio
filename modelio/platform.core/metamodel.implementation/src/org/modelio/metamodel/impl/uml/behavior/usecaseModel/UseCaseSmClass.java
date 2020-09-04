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
package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.ExtensionPointSmClass;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseData;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseDependencySmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.statik.GeneralClass;
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

@objid ("a769982c-8238-414b-a66c-83aaf9c76b10")
public class UseCaseSmClass extends GeneralClassSmClass {
    @objid ("22f77703-fd45-4790-8d52-c1ee80ad5e56")
    private SmDependency usedDep;

    @objid ("6f6b2095-94c0-4d9a-a0c3-e723dc3a8f7d")
    private SmDependency ownedExtensionDep;

    @objid ("0d29bf3d-9572-4e9e-a98b-58dbd3bc7937")
    private SmDependency userDep;

    @objid ("0fae14c6-a03a-4a0d-8c8f-15665f6d32c8")
    public UseCaseSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d26693c6-fc9b-4e40-8b70-2f53523e0472")
    @Override
    public String getName() {
        return "UseCase";
    }

    @objid ("1c2c36e7-16ca-4350-a87d-6241f071f264")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2efc672d-11ab-4019-991f-bf3e30261599")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return UseCase.class;
    }

    @objid ("2d8a65c2-76ea-43b8-9154-3a3ddec2d2ca")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("9204c437-2bd0-46af-ac5f-e478b4afae9b")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("32af65bc-e493-4f09-b102-5b448b4d371a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new UseCaseObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.usedDep = new UsedSmDependency();
        this.usedDep.init("Used", this, metamodel.getMClass(UseCaseDependency.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.usedDep);
        
        this.ownedExtensionDep = new OwnedExtensionSmDependency();
        this.ownedExtensionDep.init("OwnedExtension", this, metamodel.getMClass(ExtensionPoint.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedExtensionDep);
        
        this.userDep = new UserSmDependency();
        this.userDep.init("User", this, metamodel.getMClass(UseCaseDependency.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC, SmDirective.SMCDTODELETE);
        registerDependency(this.userDep);
    }

    @objid ("664199d2-a1fe-4fef-8d13-ffc311613b8c")
    public SmDependency getUsedDep() {
        if (this.usedDep == null) {
        	this.usedDep = this.getDependencyDef("Used");
        }
        return this.usedDep;
    }

    @objid ("bc3cbb05-66b9-4d83-add1-21bab7465341")
    public SmDependency getOwnedExtensionDep() {
        if (this.ownedExtensionDep == null) {
        	this.ownedExtensionDep = this.getDependencyDef("OwnedExtension");
        }
        return this.ownedExtensionDep;
    }

    @objid ("664fa3f7-b2c1-4220-a769-373b801c46b4")
    public SmDependency getUserDep() {
        if (this.userDep == null) {
        	this.userDep = this.getDependencyDef("User");
        }
        return this.userDep;
    }

    @objid ("bbaf5205-c9d3-4355-b740-68f5e7f65041")
    private static class UseCaseObjectFactory implements ISmObjectFactory {
        @objid ("8a71f52b-c852-4bbc-b523-efe0aea03367")
        private UseCaseSmClass smClass;

        @objid ("f35a9825-6caa-4fbe-84a1-deb40a783654")
        public UseCaseObjectFactory(UseCaseSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ee73af95-e630-4976-afcb-5539f9e3d6e9")
        @Override
        public ISmObjectData createData() {
            return new UseCaseData(this.smClass);
        }

        @objid ("f687491c-dc94-4141-8486-c9655680669b")
        @Override
        public SmObjectImpl createImpl() {
            return new UseCaseImpl();
        }

    }

    @objid ("aab89479-ee17-4c7f-b3fa-35803af99c53")
    public static class UsedSmDependency extends SmMultipleDependency {
        @objid ("a2a85e36-228b-4567-93aa-b4ae07991624")
        private SmDependency symetricDep;

        @objid ("fb08d554-aadc-478a-a512-7791ab3aa494")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UseCaseData)data).mUsed != null)? ((UseCaseData)data).mUsed:SmMultipleDependency.EMPTY;
        }

        @objid ("42d55ac8-1330-4d71-835a-d61532830233")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UseCaseData) data).mUsed = values;
        }

        @objid ("c0707574-b80b-404c-9df6-1f74123a46f8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UseCaseDependencySmClass)this.getTarget()).getOriginDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e4594661-ee4f-4f5d-9d8a-04f9b1127282")
    public static class OwnedExtensionSmDependency extends SmMultipleDependency {
        @objid ("484c6c29-11e1-4131-a331-57021ee6e34c")
        private SmDependency symetricDep;

        @objid ("75fce910-0b73-4061-a936-eb17ca8f622e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UseCaseData)data).mOwnedExtension != null)? ((UseCaseData)data).mOwnedExtension:SmMultipleDependency.EMPTY;
        }

        @objid ("6ae251a7-b5f4-47eb-a221-b2229bdd78e8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UseCaseData) data).mOwnedExtension = values;
        }

        @objid ("76f97b08-f083-4b19-b3fb-b90dc82c58fa")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExtensionPointSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("59efd104-7076-40e7-949d-610410211462")
    public static class UserSmDependency extends SmMultipleDependency {
        @objid ("d4b8d8c7-7376-4ee5-9fb6-11bf8dd5a740")
        private SmDependency symetricDep;

        @objid ("b4ff5cad-ac69-4e5a-93af-4f9601953932")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UseCaseData)data).mUser != null)? ((UseCaseData)data).mUser:SmMultipleDependency.EMPTY;
        }

        @objid ("b310689b-03cd-4129-951a-40f3ca86d29f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UseCaseData) data).mUser = values;
        }

        @objid ("98a8b4ad-f7ed-4ccb-9e90-dfce97f1c84d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UseCaseDependencySmClass)this.getTarget()).getTargetDep();
            }
            return this.symetricDep;
        }

    }

}
