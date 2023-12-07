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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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

@objid ("0363e7b8-f4aa-486c-99ad-34c234137424")
public class DependencySmClass extends ModelElementSmClass {
    @objid ("72da09d8-20b5-477f-bf86-862013d62f38")
    private SmDependency impactedDep;

    @objid ("49876f09-4001-41b3-824f-27a6bc0e57bc")
    private SmDependency dependsOnDep;

    @objid ("ff6a0fd5-dc3a-48f5-968a-273d25ca3ce1")
    public  DependencySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0a24b823-4ce9-4bdf-b157-0b33e6507f75")
    @Override
    public String getName() {
        return "Dependency";
        
    }

    @objid ("17643e4f-c796-4ba1-8b1c-159098c57d21")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("bf694d85-b7b8-418d-bda9-3f9765bd0bc0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Dependency.class;
        
    }

    @objid ("44466485-8f56-4cb9-9b26-1ffe036c8f04")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("a8d665cf-05b9-452e-a75d-bb6c19130774")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("f0a1e88a-2d6f-490c-947a-c647f838fe9c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new DependencyObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.impactedDep = new ImpactedSmDependency();
        this.impactedDep.init("Impacted", this, metamodel.getMClass(ModelElement.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.impactedDep);
        
        this.dependsOnDep = new DependsOnSmDependency();
        this.dependsOnDep.init("DependsOn", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.dependsOnDep);
        
        
    }

    @objid ("719ea7a8-0b4d-4867-bf0c-ca45fb8e85bb")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("Impacted");
        }
        return this.impactedDep;
    }

    @objid ("afaeb685-4a23-400f-a49b-5565007e322a")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("DependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("b5ffe15b-ac60-4a57-8319-3b7fbdf02e1c")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("c16fdd68-3545-4462-a32f-ae7657f7c6d5")
    private static class DependencyObjectFactory implements ISmObjectFactory {
        @objid ("2544af9f-307f-4636-adc9-a1d162fb6bd8")
        private DependencySmClass smClass;

        @objid ("72cd115d-ed65-49cb-9367-16c3d2effa82")
        public  DependencyObjectFactory(DependencySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("cfa34822-940a-4c43-8f4b-082fac3ce3b4")
        @Override
        public ISmObjectData createData() {
            return new DependencyData(this.smClass);
        }

        @objid ("22e3cd8d-6709-42ac-b6fc-23ef9cc52f8a")
        @Override
        public SmObjectImpl createImpl() {
            return new DependencyImpl();
        }

    }

    @objid ("b8bc052c-05ea-46a4-af66-746d62c47880")
    public static class DependsOnSmDependency extends SmSingleDependency {
        @objid ("3206186c-6abb-42bb-8756-e49769ab5217")
        private SmDependency symetricDep;

        @objid ("60323197-44f9-411d-bd24-18f72991ea1d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DependencyData) data).mDependsOn;
        }

        @objid ("93745c24-1ac9-43e7-b038-1acb7ca0dd0c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DependencyData) data).mDependsOn = value;
        }

        @objid ("ae95f812-f0fa-4be1-939c-dcde9070bf61")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getImpactedDependencyDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c8421b45-927d-4825-9a44-fd6aabb10c5b")
    public static class ImpactedSmDependency extends SmSingleDependency {
        @objid ("5e5afbd7-d0ca-4863-992c-3ee45d1786e9")
        private SmDependency symetricDep;

        @objid ("4bf8d871-e278-49ec-9ea2-3a62d3ca683d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DependencyData) data).mImpacted;
        }

        @objid ("0889ea5d-7c61-474f-bae4-cf72f4111980")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DependencyData) data).mImpacted = value;
        }

        @objid ("b121098e-ad0d-4a9c-83b9-6912fe5d2e91")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getDependsOnDependencyDep();
            }
            return this.symetricDep;
            
        }

    }

}
