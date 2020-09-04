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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.DependencyData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
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
    @objid ("c5235f0d-770a-46cf-8f4a-2a0eda96b5ad")
    private SmDependency impactedDep;

    @objid ("4db5acd0-c1b1-4124-b245-91e9e0199089")
    private SmDependency dependsOnDep;

    @objid ("91ac6090-f9b5-4c0a-8383-1022ba2a8202")
    public DependencySmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f3a5fa12-c85e-4bcb-86d1-3580d78a222c")
    @Override
    public String getName() {
        return "Dependency";
    }

    @objid ("a5b68fa7-b36f-42d5-b527-28fb11637a65")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b0d1c5dd-fd16-4c2e-a343-f88d32ede108")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Dependency.class;
    }

    @objid ("4e6d585b-a6de-4818-971d-f272daa17b15")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("c4276f4d-c736-46e5-8aa7-d6023539edd4")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("36334e08-bf66-4f38-a081-e1da1b919e20")
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

    @objid ("0d52870d-4b1c-43f9-baf3-de3e1a49edc3")
    public SmDependency getImpactedDep() {
        if (this.impactedDep == null) {
        	this.impactedDep = this.getDependencyDef("Impacted");
        }
        return this.impactedDep;
    }

    @objid ("f1ce0452-8c0c-47e2-bbc9-a3107acf3f63")
    public SmDependency getDependsOnDep() {
        if (this.dependsOnDep == null) {
        	this.dependsOnDep = this.getDependencyDef("DependsOn");
        }
        return this.dependsOnDep;
    }

    @objid ("02c9865e-ff5e-4ab2-9e81-cacab76bc0f2")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("c16fdd68-3545-4462-a32f-ae7657f7c6d5")
    private static class DependencyObjectFactory implements ISmObjectFactory {
        @objid ("3975b24c-9cf3-40b0-bc72-e7ec6f978c51")
        private DependencySmClass smClass;

        @objid ("07e5cf2c-0396-4ab4-a993-e682b72e77af")
        public DependencyObjectFactory(DependencySmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ac712c7c-c708-45c6-984c-5ae3f301ff4a")
        @Override
        public ISmObjectData createData() {
            return new DependencyData(this.smClass);
        }

        @objid ("b2b98f5f-1ad5-4869-b4a1-d4a5db2b1f7b")
        @Override
        public SmObjectImpl createImpl() {
            return new DependencyImpl();
        }

    }

    @objid ("b8bc052c-05ea-46a4-af66-746d62c47880")
    public static class DependsOnSmDependency extends SmSingleDependency {
        @objid ("61a4eb8c-23d9-4284-8820-19fa563d8d44")
        private SmDependency symetricDep;

        @objid ("191f6526-bd60-44af-8714-47e8eab7ed0a")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DependencyData) data).mDependsOn;
        }

        @objid ("f7499575-4074-40d3-93a2-c0e27ddf8a4b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DependencyData) data).mDependsOn = value;
        }

        @objid ("cd72ebfa-b88b-43f6-8cac-78cb58b4df7c")
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
        @objid ("41854cef-fa9a-4ec4-b77b-3152f34efff8")
        private SmDependency symetricDep;

        @objid ("8fba0ed7-1ffc-45f7-be89-b7f284c0965e")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DependencyData) data).mImpacted;
        }

        @objid ("338d80a0-7175-4a58-8444-b1e0f6bb3cc1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DependencyData) data).mImpacted = value;
        }

        @objid ("7c319f65-0afd-41a3-8db7-e6636777def0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getDependsOnDependencyDep();
            }
            return this.symetricDep;
        }

    }

}
