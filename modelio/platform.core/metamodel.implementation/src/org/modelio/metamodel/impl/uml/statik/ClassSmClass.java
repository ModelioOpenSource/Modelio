/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.impl.uml.statik.ClassAssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassData;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.GeneralClass;
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

@objid ("06b8d248-fc77-499d-a7d8-11fed357827c")
public class ClassSmClass extends GeneralClassSmClass {
    @objid ("d70d8da5-cf54-4d4c-86ba-0b5d12518000")
    private SmAttribute isActiveAtt;

    @objid ("2acc4379-0a7f-4553-85cc-fd6578f2094c")
    private SmAttribute isMainAtt;

    @objid ("80685ca3-6c67-4ea3-8472-1bd11ff6af4d")
    private SmDependency linkToAssociationDep;

    @objid ("becc93f5-69fb-43e6-8c8a-3542c7fa8da4")
    public ClassSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("bed8f3d7-9c0e-4d53-a89b-25cfc6a0004a")
    @Override
    public String getName() {
        return "Class";
    }

    @objid ("1ef9bda8-91a9-42b3-9bc7-4b505cd47502")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9e92b0bb-06f0-4bf5-b1c3-19230cf0c41d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Class.class;
    }

    @objid ("b42ba335-16cf-493c-8d17-15c0ab7ba133")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("f77322fc-48bd-4b60-a384-48d14c7567c0")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("74769d15-19a5-4325-99ba-0966cc7cd800")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new ClassObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isActiveAtt = new IsActiveSmAttribute();
        this.isActiveAtt.init("IsActive", this, Boolean.class );
        registerAttribute(this.isActiveAtt);
        
        this.isMainAtt = new IsMainSmAttribute();
        this.isMainAtt.init("IsMain", this, Boolean.class );
        registerAttribute(this.isMainAtt);
        
        
        // Initialize and register the SmDependency
        this.linkToAssociationDep = new LinkToAssociationSmDependency();
        this.linkToAssociationDep.init("LinkToAssociation", this, metamodel.getMClass(ClassAssociation.MQNAME), 0, 1 , SmDirective.SMCDTODELETE);
        registerDependency(this.linkToAssociationDep);
    }

    @objid ("38b87dd8-9605-49aa-8ff4-a6df5bf17b92")
    public SmAttribute getIsActiveAtt() {
        if (this.isActiveAtt == null) {
        	this.isActiveAtt = this.getAttributeDef("IsActive");
        }
        return this.isActiveAtt;
    }

    @objid ("cacb68d9-df25-4b37-b032-cb75f26f8cd9")
    public SmAttribute getIsMainAtt() {
        if (this.isMainAtt == null) {
        	this.isMainAtt = this.getAttributeDef("IsMain");
        }
        return this.isMainAtt;
    }

    @objid ("316e479e-fe49-4161-858a-33ac76d8c0a4")
    public SmDependency getLinkToAssociationDep() {
        if (this.linkToAssociationDep == null) {
        	this.linkToAssociationDep = this.getDependencyDef("LinkToAssociation");
        }
        return this.linkToAssociationDep;
    }

    @objid ("f7fc0235-b412-49fe-94fd-ee66ca3d024f")
    private static class ClassObjectFactory implements ISmObjectFactory {
        @objid ("f2b065b1-142b-4969-94ca-1c7a083018d7")
        private ClassSmClass smClass;

        @objid ("9dc0bfe0-aadc-4fb2-9377-33d94d457ac9")
        public ClassObjectFactory(ClassSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e90fef1b-3c24-49ec-8548-ddee540800fd")
        @Override
        public ISmObjectData createData() {
            return new ClassData(this.smClass);
        }

        @objid ("b3b783ee-f362-41cb-85e9-915e5fa6299b")
        @Override
        public SmObjectImpl createImpl() {
            return new ClassImpl();
        }

    }

    @objid ("4feef9f9-689e-4286-8c99-f7e188ca3ed1")
    public static class IsActiveSmAttribute extends SmAttribute {
        @objid ("3e781293-435b-4e22-b8fe-20092eda80fa")
        public Object getValue(ISmObjectData data) {
            return ((ClassData) data).mIsActive;
        }

        @objid ("a33071bb-ec07-4f0b-a673-147a39aac5e3")
        public void setValue(ISmObjectData data, Object value) {
            ((ClassData) data).mIsActive = value;
        }

    }

    @objid ("5e111d48-f336-48c7-ad25-55e5b7752e73")
    public static class IsMainSmAttribute extends SmAttribute {
        @objid ("fad3b036-d25c-4c6a-9d8c-79abad180dfc")
        public Object getValue(ISmObjectData data) {
            return ((ClassData) data).mIsMain;
        }

        @objid ("d79bed7d-8708-41fe-84bf-e0985d440556")
        public void setValue(ISmObjectData data, Object value) {
            ((ClassData) data).mIsMain = value;
        }

    }

    @objid ("a0a8a5d1-dca9-4c85-b146-52b422e659a5")
    public static class LinkToAssociationSmDependency extends SmSingleDependency {
        @objid ("9eb9582d-f531-45fd-a280-acea5a702636")
        private SmDependency symetricDep;

        @objid ("02a26807-654c-4fcb-b59f-d8ab194cbb41")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ClassData) data).mLinkToAssociation;
        }

        @objid ("62a96f60-cc1a-4f7e-837a-5a3b0a93b86b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ClassData) data).mLinkToAssociation = value;
        }

        @objid ("c187526e-8638-45be-902e-fa6b6c34920a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassAssociationSmClass)this.getTarget()).getClassPartDep();
            }
            return this.symetricDep;
        }

    }

}
