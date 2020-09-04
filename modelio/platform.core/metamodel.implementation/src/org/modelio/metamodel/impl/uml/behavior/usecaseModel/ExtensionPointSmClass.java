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
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.ExtensionPointData;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseDependencySmClass;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("d143432c-5f40-490c-83b4-207d0eaa677e")
public class ExtensionPointSmClass extends UmlModelElementSmClass {
    @objid ("60979a63-5bf4-4ccb-9ea9-7de2b8d85532")
    private SmAttribute visibilityAtt;

    @objid ("cfd7048a-da3e-4942-ac09-913b4514d28c")
    private SmDependency extendedDep;

    @objid ("7e702678-d587-4fa8-8210-4b37c5ccd8af")
    private SmDependency ownerDep;

    @objid ("4148fe2b-e6db-4812-8ad2-1c1c69c371d3")
    public ExtensionPointSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b6f1ad80-85b4-4098-bbee-8d04b0c1606f")
    @Override
    public String getName() {
        return "ExtensionPoint";
    }

    @objid ("667ee811-ec4e-40d4-b3bd-c84cd4bafe62")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e940a21a-9515-4b78-9f82-aea7a369ff7d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExtensionPoint.class;
    }

    @objid ("161824b5-5a48-472e-af2b-c10e7d781b69")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("63fa686e-c745-4016-b7e3-aa3cef06c3a2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("86bb0a49-3adb-4527-987e-89cffe9ec445")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ExtensionPointObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.visibilityAtt = new VisibilitySmAttribute();
        this.visibilityAtt.init("Visibility", this, VisibilityMode.class );
        registerAttribute(this.visibilityAtt);
        
        
        // Initialize and register the SmDependency
        this.extendedDep = new ExtendedSmDependency();
        this.extendedDep.init("Extended", this, metamodel.getMClass(UseCaseDependency.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC, SmDirective.SMCDTODELETE);
        registerDependency(this.extendedDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(UseCase.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("af6a3953-ca80-48c3-acaf-2b41b6877832")
    public SmAttribute getVisibilityAtt() {
        if (this.visibilityAtt == null) {
        	this.visibilityAtt = this.getAttributeDef("Visibility");
        }
        return this.visibilityAtt;
    }

    @objid ("b14f1f2b-cf49-4a0e-8495-91abf80ca29b")
    public SmDependency getExtendedDep() {
        if (this.extendedDep == null) {
        	this.extendedDep = this.getDependencyDef("Extended");
        }
        return this.extendedDep;
    }

    @objid ("c0bf88e6-ecb3-4aff-969a-d8e04dbbf169")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("5e728a90-f5ed-43a3-951c-b5541f190dfe")
    private static class ExtensionPointObjectFactory implements ISmObjectFactory {
        @objid ("f4cc49a0-d0c7-4f29-a370-6741bbf15fe5")
        private ExtensionPointSmClass smClass;

        @objid ("2070cad9-6c89-49a1-9c06-2a5b1a01f206")
        public ExtensionPointObjectFactory(ExtensionPointSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("cd16971a-4481-4f38-82c7-e90d8d61efdf")
        @Override
        public ISmObjectData createData() {
            return new ExtensionPointData(this.smClass);
        }

        @objid ("14d502d7-1d7f-493b-af04-8e8af86a22f3")
        @Override
        public SmObjectImpl createImpl() {
            return new ExtensionPointImpl();
        }

    }

    @objid ("36c7ac66-dd43-458c-82a1-a44a3aaff364")
    public static class VisibilitySmAttribute extends SmAttribute {
        @objid ("dd1e704d-aba8-48d8-85bf-eeefe995b280")
        public Object getValue(ISmObjectData data) {
            return ((ExtensionPointData) data).mVisibility;
        }

        @objid ("0aadc674-7ff9-4c5f-845b-48f489b4c5b4")
        public void setValue(ISmObjectData data, Object value) {
            ((ExtensionPointData) data).mVisibility = value;
        }

    }

    @objid ("0af8e61b-0782-4b7c-b0c2-2044fbe050a7")
    public static class ExtendedSmDependency extends SmMultipleDependency {
        @objid ("e0459226-5281-41eb-a850-d0a145cd7b43")
        private SmDependency symetricDep;

        @objid ("e309cc27-c437-4044-9723-50d66d69743b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ExtensionPointData)data).mExtended != null)? ((ExtensionPointData)data).mExtended:SmMultipleDependency.EMPTY;
        }

        @objid ("f37a9462-d8af-4db8-ad4a-54c0229a5c41")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ExtensionPointData) data).mExtended = values;
        }

        @objid ("d873c3ca-734d-4cea-acc5-6b89d8133c42")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UseCaseDependencySmClass)this.getTarget()).getExtensionLocationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("7dc9e31b-4ffc-4779-ae31-d80f598cc538")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("5b7cbcf0-2928-437e-9424-e8b52a543340")
        private SmDependency symetricDep;

        @objid ("6a80c3ad-e221-49ef-b937-2e18496ddcdb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExtensionPointData) data).mOwner;
        }

        @objid ("2b8c54a2-d1a5-4fc2-912d-ac7f5f157845")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExtensionPointData) data).mOwner = value;
        }

        @objid ("d7d1a0f4-af66-4481-b3e4-126cd0387513")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UseCaseSmClass)this.getTarget()).getOwnedExtensionDep();
            }
            return this.symetricDep;
        }

    }

}
