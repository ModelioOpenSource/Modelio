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
import org.modelio.metamodel.impl.uml.statik.AssociationData;
import org.modelio.metamodel.impl.uml.statik.AssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassAssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Link;
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

@objid ("cd569d79-73f0-4df0-b323-377f6551adad")
public class AssociationSmClass extends UmlModelElementSmClass {
    @objid ("aa46e809-4736-42a2-a8f8-683fe29e7a1b")
    private SmDependency occurenceDep;

    @objid ("f822679d-b4f6-4fec-b0c1-aa420cf8f963")
    private SmDependency endDep;

    @objid ("675aac91-0f89-4a21-bfd0-e8bea63ad615")
    private SmDependency linkToClassDep;

    @objid ("1cc989b5-e25c-48bb-be0e-6dd4144c6f04")
    public AssociationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("67542ed3-b18d-4b12-a939-491b8b0505cf")
    @Override
    public String getName() {
        return "Association";
    }

    @objid ("f1231d2d-82e0-4cfb-9f9b-4fc09d9f83a6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ebd5654e-db7e-4510-a5fe-a1e2a21b4480")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Association.class;
    }

    @objid ("706125fa-8cb9-46e2-91b8-73cc01c7d10c")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("cc7f42d1-2c62-4b41-830f-52789583ac87")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("071d5cb1-4603-486f-a09c-cda2920a21ee")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new AssociationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.occurenceDep = new OccurenceSmDependency();
        this.occurenceDep.init("Occurence", this, metamodel.getMClass(Link.MQNAME), 0, -1 );
        registerDependency(this.occurenceDep);
        
        this.endDep = new EndSmDependency();
        this.endDep.init("End", this, metamodel.getMClass(AssociationEnd.MQNAME), 2, 2 );
        registerDependency(this.endDep);
        
        this.linkToClassDep = new LinkToClassSmDependency();
        this.linkToClassDep.init("LinkToClass", this, metamodel.getMClass(ClassAssociation.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.linkToClassDep);
    }

    @objid ("281946b9-1ae2-42bb-97ae-2e32d3a90597")
    public SmDependency getOccurenceDep() {
        if (this.occurenceDep == null) {
        	this.occurenceDep = this.getDependencyDef("Occurence");
        }
        return this.occurenceDep;
    }

    @objid ("b778a1ed-9fcd-403c-955f-e8bd02a3347f")
    public SmDependency getEndDep() {
        if (this.endDep == null) {
        	this.endDep = this.getDependencyDef("End");
        }
        return this.endDep;
    }

    @objid ("f2dfdd1a-8c80-4232-ae4b-252e4b06ef18")
    public SmDependency getLinkToClassDep() {
        if (this.linkToClassDep == null) {
        	this.linkToClassDep = this.getDependencyDef("LinkToClass");
        }
        return this.linkToClassDep;
    }

    @objid ("4e9b29d5-cdc6-41fb-89fe-96745e40d33c")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("7102af6c-113d-4629-b612-fd6aba8da06f")
    private static class AssociationObjectFactory implements ISmObjectFactory {
        @objid ("334e4126-7062-41f7-ba53-23de1b88df4f")
        private AssociationSmClass smClass;

        @objid ("cbd551f6-32a1-498d-b98b-5584796b63fa")
        public AssociationObjectFactory(AssociationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("919ca256-ac68-4a2e-a657-c1c68db6cf5f")
        @Override
        public ISmObjectData createData() {
            return new AssociationData(this.smClass);
        }

        @objid ("57156d59-e492-4845-92d4-edf42d87acb2")
        @Override
        public SmObjectImpl createImpl() {
            return new AssociationImpl();
        }

    }

    @objid ("4645ffe5-1385-4058-873e-1a048daa0cf0")
    public static class OccurenceSmDependency extends SmMultipleDependency {
        @objid ("9b445b30-a182-4e9c-8efa-0304f0ac6d29")
        private SmDependency symetricDep;

        @objid ("9e5754b1-7550-4f5d-873e-945daed296c6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AssociationData)data).mOccurence != null)? ((AssociationData)data).mOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("a074ec18-41ef-496d-ad98-455bcf1878f3")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AssociationData) data).mOccurence = values;
        }

        @objid ("5a03171a-6a7c-4b15-ad01-710bd9888722")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkSmClass)this.getTarget()).getModelDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6e9d0267-4ef6-4ae7-a541-2de9afa08ee8")
    public static class EndSmDependency extends SmMultipleDependency {
        @objid ("c56b0161-e741-4d47-b551-e6213eb5f539")
        private SmDependency symetricDep;

        @objid ("e06ddabe-8981-4294-8d74-e4bef7a318bd")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AssociationData)data).mEnd != null)? ((AssociationData)data).mEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("fe897490-d76e-436a-a69f-0699a85d1b9d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AssociationData) data).mEnd = values;
        }

        @objid ("4c684ce4-ccab-4002-b97e-90a83bb35fc2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationEndSmClass)this.getTarget()).getAssociationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8cf76135-8885-4813-bcfe-edb480a992a5")
    public static class LinkToClassSmDependency extends SmSingleDependency {
        @objid ("0d5c06f1-ec05-4363-9733-d0e1cb903955")
        private SmDependency symetricDep;

        @objid ("bfa61a18-17f2-44e4-9914-2f4bfd2f4e64")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AssociationData) data).mLinkToClass;
        }

        @objid ("b717fe25-0470-4ccf-a09a-2bc49bbbedda")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AssociationData) data).mLinkToClass = value;
        }

        @objid ("04e36715-1b84-4244-8b22-4ac2029bd088")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassAssociationSmClass)this.getTarget()).getAssociationPartDep();
            }
            return this.symetricDep;
        }

    }

}
