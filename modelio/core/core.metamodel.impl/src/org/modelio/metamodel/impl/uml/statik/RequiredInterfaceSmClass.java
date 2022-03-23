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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.RequiredInterface;
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

@objid ("b4edbdaa-c246-435a-bcdc-03ed72b8aa7d")
public class RequiredInterfaceSmClass extends UmlModelElementSmClass {
    @objid ("0c8aa016-2830-4404-81b0-9ab18b13c3ce")
    private SmDependency requiredElementDep;

    @objid ("1b2bffad-dbad-493a-b886-9ee3b760eeb1")
    private SmDependency providerDep;

    @objid ("82b4e13d-1738-4ef6-aa6b-e7c78c997091")
    private SmDependency requiringDep;

    @objid ("0e215f13-3711-4098-8047-0ff02d52d022")
    private SmDependency naryProviderDep;

    @objid ("bb19dc24-8572-46d0-8c9e-195a5ae64030")
    public  RequiredInterfaceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("aa49fe19-b365-4f9c-8cb7-6006bf3729cc")
    @Override
    public String getName() {
        return "RequiredInterface";
        
    }

    @objid ("6f0b2872-166b-4979-a3c1-b94a2daf1313")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("64bda1c5-600f-4d2a-939e-2aa50e439aeb")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return RequiredInterface.class;
        
    }

    @objid ("74a68dec-0e61-4781-9b3a-a3191d05f170")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("3936cbc3-1430-4337-bdbd-2d5350dacd85")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("1b1327ec-d5e0-4ef1-b4a3-597272e9f046")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new RequiredInterfaceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.requiredElementDep = new RequiredElementSmDependency();
        this.requiredElementDep.init("RequiredElement", this, metamodel.getMClass(Interface.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.requiredElementDep);
        
        this.providerDep = new ProviderSmDependency();
        this.providerDep.init("Provider", this, metamodel.getMClass(LinkEnd.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.providerDep);
        
        this.requiringDep = new RequiringSmDependency();
        this.requiringDep.init("Requiring", this, metamodel.getMClass(Port.MQNAME), 1, 1 );
        registerDependency(this.requiringDep);
        
        this.naryProviderDep = new NaryProviderSmDependency();
        this.naryProviderDep.init("NaryProvider", this, metamodel.getMClass(NaryLinkEnd.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.naryProviderDep);
        
        
    }

    @objid ("7eafd161-d9ad-4f37-8343-413c28956c59")
    public SmDependency getRequiredElementDep() {
        if (this.requiredElementDep == null) {
        	this.requiredElementDep = this.getDependencyDef("RequiredElement");
        }
        return this.requiredElementDep;
    }

    @objid ("23307ae2-c59a-4312-8adc-b8f01167337e")
    public SmDependency getProviderDep() {
        if (this.providerDep == null) {
        	this.providerDep = this.getDependencyDef("Provider");
        }
        return this.providerDep;
    }

    @objid ("5c577e9d-76a2-4703-b601-8bd4bc1e0eb4")
    public SmDependency getRequiringDep() {
        if (this.requiringDep == null) {
        	this.requiringDep = this.getDependencyDef("Requiring");
        }
        return this.requiringDep;
    }

    @objid ("b2dacc0f-066d-4c87-887b-b117e70d8be2")
    public SmDependency getNaryProviderDep() {
        if (this.naryProviderDep == null) {
        	this.naryProviderDep = this.getDependencyDef("NaryProvider");
        }
        return this.naryProviderDep;
    }

    @objid ("baac4aa5-6dc3-40b3-ad18-fc5dfdf368c5")
    private static class RequiredInterfaceObjectFactory implements ISmObjectFactory {
        @objid ("f2700586-20e9-4953-bd3e-fcadffc5ca39")
        private RequiredInterfaceSmClass smClass;

        @objid ("c4e319c8-953c-4180-aa78-5501d63455b7")
        public  RequiredInterfaceObjectFactory(RequiredInterfaceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4b2f3b8f-5e5f-4a60-b091-ab710c6b7d28")
        @Override
        public ISmObjectData createData() {
            return new RequiredInterfaceData(this.smClass);
        }

        @objid ("66ccf32c-d1af-4214-bac6-3e4e14da9f4e")
        @Override
        public SmObjectImpl createImpl() {
            return new RequiredInterfaceImpl();
        }

    }

    @objid ("9975d690-8f71-4e5b-934d-e26acf1f6df6")
    public static class RequiredElementSmDependency extends SmMultipleDependency {
        @objid ("cb8de49e-4895-4957-add4-0ff831b0d108")
        private SmDependency symetricDep;

        @objid ("a1644e56-0d0a-4914-a278-ea64e56c60a2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((RequiredInterfaceData)data).mRequiredElement != null)? ((RequiredInterfaceData)data).mRequiredElement:SmMultipleDependency.EMPTY;
        }

        @objid ("9572ffbf-d3cc-4187-bcff-4685bee13b6d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((RequiredInterfaceData) data).mRequiredElement = values;
            
        }

        @objid ("07d21a09-ed2d-426d-8644-c837301c4e0f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InterfaceSmClass)this.getTarget()).getRequiringDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("81d62d21-d90e-4828-a4ed-a276c1e38d3c")
    public static class ProviderSmDependency extends SmMultipleDependency {
        @objid ("a3d2c801-fa45-45d2-9238-4e5cc13b079f")
        private SmDependency symetricDep;

        @objid ("0ce4d7a1-ded1-4546-a9d0-d43f8c519c15")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((RequiredInterfaceData)data).mProvider != null)? ((RequiredInterfaceData)data).mProvider:SmMultipleDependency.EMPTY;
        }

        @objid ("bd734fa4-e958-49a1-a841-dadd9f3de358")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((RequiredInterfaceData) data).mProvider = values;
            
        }

        @objid ("85276568-c590-462e-a3f0-e510239d67fd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getConsumerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("a11c8d87-eaf6-407b-83dc-85e732647ded")
    public static class RequiringSmDependency extends SmSingleDependency {
        @objid ("78660348-7270-43ff-a5b6-c2f90a785f5a")
        private SmDependency symetricDep;

        @objid ("bfd72a62-5bbc-4cba-82a9-ec0ae38722a3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((RequiredInterfaceData) data).mRequiring;
        }

        @objid ("b207f05d-d655-4be7-8de1-222ff228c465")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((RequiredInterfaceData) data).mRequiring = value;
        }

        @objid ("dfe807c0-e88e-4898-b70b-ef0de26e38cc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PortSmClass)this.getTarget()).getRequiredDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("32d2af6a-0178-46fa-b57a-f8c9cf8ee574")
    public static class NaryProviderSmDependency extends SmMultipleDependency {
        @objid ("9c4ca1b9-d883-48b9-b663-a9eb6bbf70cd")
        private SmDependency symetricDep;

        @objid ("bab17962-1531-44ee-8837-728ffbe54cd7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((RequiredInterfaceData)data).mNaryProvider != null)? ((RequiredInterfaceData)data).mNaryProvider:SmMultipleDependency.EMPTY;
        }

        @objid ("9ebdac27-66fb-4828-9578-554999a25265")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((RequiredInterfaceData) data).mNaryProvider = values;
            
        }

        @objid ("305b1f79-ff51-419f-b9f2-9d46c1a34389")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkEndSmClass)this.getTarget()).getConsumerDep();
            }
            return this.symetricDep;
            
        }

    }

}
