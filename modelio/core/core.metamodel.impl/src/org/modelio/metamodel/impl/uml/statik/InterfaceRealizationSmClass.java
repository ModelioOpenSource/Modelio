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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.InterfaceRealizationData;
import org.modelio.metamodel.impl.uml.statik.InterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
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

@objid ("af5d6ee8-f94d-4e95-84be-6bebc0cc3fdd")
public class InterfaceRealizationSmClass extends UmlModelElementSmClass {
    @objid ("b483d404-e57b-4733-bc0e-00234263c3d8")
    private SmDependency implementedDep;

    @objid ("fea397c7-93d4-44fb-b374-85e8040abb9f")
    private SmDependency implementerDep;

    @objid ("1dd59abe-e20b-4caa-93c5-e01f83155aba")
    public InterfaceRealizationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e5fd5452-c546-49ec-9c28-0a050b5a4639")
    @Override
    public String getName() {
        return "InterfaceRealization";
    }

    @objid ("f5347e37-669d-426e-96e3-9220e17a6be8")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("48160c9a-d12f-4aff-ae4e-b6be2c5cf45a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InterfaceRealization.class;
    }

    @objid ("2b397d97-4ae5-4a34-9949-2af68cbfb18d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5b1b6556-2e2d-45f0-8410-a63a4eb7f887")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("7aecd5a0-5b24-4bf9-a243-37b834ed6871")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new InterfaceRealizationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.implementedDep = new ImplementedSmDependency();
        this.implementedDep.init("Implemented", this, metamodel.getMClass(Interface.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.implementedDep);
        
        this.implementerDep = new ImplementerSmDependency();
        this.implementerDep.init("Implementer", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.implementerDep);
    }

    @objid ("fc9d2929-29b5-4492-b999-885774f08f8e")
    public SmDependency getImplementedDep() {
        if (this.implementedDep == null) {
        	this.implementedDep = this.getDependencyDef("Implemented");
        }
        return this.implementedDep;
    }

    @objid ("c21401ed-20ac-4c72-8f32-4d5435eda899")
    public SmDependency getImplementerDep() {
        if (this.implementerDep == null) {
        	this.implementerDep = this.getDependencyDef("Implementer");
        }
        return this.implementerDep;
    }

    @objid ("8f3713a5-1a82-4ef6-a24c-344d7bcbb24e")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("90b653a6-2e0e-4edf-ae85-6fe1aa11da73")
    private static class InterfaceRealizationObjectFactory implements ISmObjectFactory {
        @objid ("a5e99d32-4336-46fa-9179-c4bf34652b46")
        private InterfaceRealizationSmClass smClass;

        @objid ("6f780ad1-5810-43ed-b3f7-e6e55625b781")
        public InterfaceRealizationObjectFactory(InterfaceRealizationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("576a59ed-ab12-4a13-81a7-72b4bdaa21be")
        @Override
        public ISmObjectData createData() {
            return new InterfaceRealizationData(this.smClass);
        }

        @objid ("3a764d84-e1fb-4b54-98ab-17cef1262a17")
        @Override
        public SmObjectImpl createImpl() {
            return new InterfaceRealizationImpl();
        }

    }

    @objid ("230d01cc-9694-480a-83eb-6ee4acaef4b0")
    public static class ImplementedSmDependency extends SmSingleDependency {
        @objid ("ef0a27c1-176f-4e32-89a7-185cc0c57895")
        private SmDependency symetricDep;

        @objid ("95291d2a-15b0-4419-a1da-0e79b3c01b9a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InterfaceRealizationData) data).mImplemented;
        }

        @objid ("45cfd93a-e4be-465c-8d0c-f2a907540dfd")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InterfaceRealizationData) data).mImplemented = value;
        }

        @objid ("1d7224c1-0c0f-4e8e-a1cd-b97c23027916")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InterfaceSmClass)this.getTarget()).getImplementedLinkDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("a14f52f3-a855-4427-a79a-ec244d8d17b3")
    public static class ImplementerSmDependency extends SmSingleDependency {
        @objid ("d9044f45-b068-4bf2-a105-f598ea531cd4")
        private SmDependency symetricDep;

        @objid ("6c73915e-9212-4ceb-90f2-a22571a0562d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((InterfaceRealizationData) data).mImplementer;
        }

        @objid ("cd4c0b54-3d25-4bb8-b624-1e9ba906b610")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((InterfaceRealizationData) data).mImplementer = value;
        }

        @objid ("e3e75173-1589-4d55-ba7e-a411c2a027a7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getRealizedDep();
            }
            return this.symetricDep;
        }

    }

}
