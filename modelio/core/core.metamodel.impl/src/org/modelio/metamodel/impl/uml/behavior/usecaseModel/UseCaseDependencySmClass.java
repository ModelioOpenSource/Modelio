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

package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
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

@objid ("c0c422fc-6fa7-44af-bc45-b6db86732826")
public class UseCaseDependencySmClass extends UmlModelElementSmClass {
    @objid ("41e5820f-e5b2-467f-a284-964fe2bf9014")
    private SmDependency originDep;

    @objid ("d47eb89f-a9bd-4027-b3d3-7045db68a2a7")
    private SmDependency extensionLocationDep;

    @objid ("8373694b-9ba5-4b24-8372-5da03b862c6b")
    private SmDependency targetDep;

    @objid ("fce16ebb-c284-4519-b42b-88b9a3aaa301")
    public  UseCaseDependencySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("07a4a2b6-7741-46d5-8095-72e3c02c03b4")
    @Override
    public String getName() {
        return "UseCaseDependency";
        
    }

    @objid ("523a7fe9-e334-4d2f-8db8-7746b2f35c53")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3264574c-104f-46e5-85a0-1d7767d1f342")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return UseCaseDependency.class;
        
    }

    @objid ("8cb0b65a-7f16-4144-bb67-0304e90c4093")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("9a2dab36-d017-45be-a554-1451c8defd51")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("5e5c580d-15ca-420a-a9cb-632bca210163")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new UseCaseDependencyObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.originDep = new OriginSmDependency();
        this.originDep.init("Origin", this, metamodel.getMClass(UseCase.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.originDep);
        
        this.extensionLocationDep = new ExtensionLocationSmDependency();
        this.extensionLocationDep.init("ExtensionLocation", this, metamodel.getMClass(ExtensionPoint.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.extensionLocationDep);
        
        this.targetDep = new TargetSmDependency();
        this.targetDep.init("Target", this, metamodel.getMClass(UseCase.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.targetDep);
        
        
    }

    @objid ("323ff11d-67e9-4b71-aff1-34b9f65989ba")
    public SmDependency getOriginDep() {
        if (this.originDep == null) {
        	this.originDep = this.getDependencyDef("Origin");
        }
        return this.originDep;
    }

    @objid ("b9174b01-069d-4ce3-8dd6-d98af5ffd418")
    public SmDependency getExtensionLocationDep() {
        if (this.extensionLocationDep == null) {
        	this.extensionLocationDep = this.getDependencyDef("ExtensionLocation");
        }
        return this.extensionLocationDep;
    }

    @objid ("d72e58ae-f307-4092-8165-e07006a00a39")
    public SmDependency getTargetDep() {
        if (this.targetDep == null) {
        	this.targetDep = this.getDependencyDef("Target");
        }
        return this.targetDep;
    }

    @objid ("244d54d4-8022-421f-a4bc-fd3f548d7d81")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("e795a442-7b92-4e69-b212-79986facf6aa")
    private static class UseCaseDependencyObjectFactory implements ISmObjectFactory {
        @objid ("4743d399-18d1-4f79-a2f7-a3916c6aaf4f")
        private UseCaseDependencySmClass smClass;

        @objid ("c738799a-a685-432e-808c-73db838ce85a")
        public  UseCaseDependencyObjectFactory(UseCaseDependencySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("10a791c4-00c3-4682-81d3-49eebcf72ea6")
        @Override
        public ISmObjectData createData() {
            return new UseCaseDependencyData(this.smClass);
        }

        @objid ("0dcba4f2-76e2-4801-92ca-1894122716d0")
        @Override
        public SmObjectImpl createImpl() {
            return new UseCaseDependencyImpl();
        }

    }

    @objid ("c6404859-d9a0-4e98-b3ca-8bc4f656db68")
    public static class OriginSmDependency extends SmSingleDependency {
        @objid ("88741b23-4555-4c57-a248-ab8d33146133")
        private SmDependency symetricDep;

        @objid ("51229d56-ac91-482d-b63f-d6daf3630f14")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((UseCaseDependencyData) data).mOrigin;
        }

        @objid ("01323905-e0f9-49b8-874b-626f907b2a02")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((UseCaseDependencyData) data).mOrigin = value;
        }

        @objid ("10a0b52b-31fd-4f60-837a-d6207e9ac25e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UseCaseSmClass)this.getTarget()).getUsedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("aa39cc73-77c4-449e-aa30-cfb993b2c60b")
    public static class ExtensionLocationSmDependency extends SmMultipleDependency {
        @objid ("31b420e5-d0fc-4edc-9bfe-c6752577e3da")
        private SmDependency symetricDep;

        @objid ("bd97c57e-b401-4d91-8313-de45b3bedb02")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((UseCaseDependencyData)data).mExtensionLocation != null)? ((UseCaseDependencyData)data).mExtensionLocation:SmMultipleDependency.EMPTY;
        }

        @objid ("51953042-2570-4d3c-a575-f59c8fa6f130")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((UseCaseDependencyData) data).mExtensionLocation = values;
            
        }

        @objid ("8b706001-fbec-4935-894a-26dfe5023cef")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExtensionPointSmClass)this.getTarget()).getExtendedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("92a1bca4-bf13-4346-b01d-0d2ef036f44b")
    public static class TargetSmDependency extends SmSingleDependency {
        @objid ("3c2ef791-c1dd-4389-957d-ee071a87c6a1")
        private SmDependency symetricDep;

        @objid ("3e8e3c62-b857-4a71-ae18-4a5657d2ffd3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((UseCaseDependencyData) data).mTarget;
        }

        @objid ("974b3280-7b4e-4b15-b4ca-879f43d2bd9a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((UseCaseDependencyData) data).mTarget = value;
        }

        @objid ("141b65c9-5539-42fd-aa35-66eee7180f2f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UseCaseSmClass)this.getTarget()).getUserDep();
            }
            return this.symetricDep;
            
        }

    }

}
