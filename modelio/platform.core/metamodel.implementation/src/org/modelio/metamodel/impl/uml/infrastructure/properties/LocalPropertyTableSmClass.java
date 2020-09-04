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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.LocalPropertyTableData;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
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

@objid ("90df93f3-3d77-459d-8522-3bcaa78554db")
public class LocalPropertyTableSmClass extends PropertyTableSmClass {
    @objid ("b3a0b9d4-e15a-4803-8ec1-c62f93e1e0e0")
    private SmDependency localAnnotedDep;

    @objid ("a9ad7735-effd-4224-ba13-4ea8dd1ab7d1")
    public LocalPropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("749b9f02-d1c1-4e48-ba49-72456a6e6a2d")
    @Override
    public String getName() {
        return "LocalPropertyTable";
    }

    @objid ("ef2d5f09-193a-44b7-8626-032448e2b1d7")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("60906296-46a1-4211-a28f-24d8bb9b2071")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return LocalPropertyTable.class;
    }

    @objid ("35670bd1-76b1-4eb7-b024-bbfcc4068beb")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("ab4afd18-38af-4fa5-94e8-383fbf81e4d4")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e12fd411-d83a-4240-910e-836a1805b701")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(PropertyTable.MQNAME);
        this.registerFactory(new LocalPropertyTableObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.localAnnotedDep = new LocalAnnotedSmDependency();
        this.localAnnotedDep.init("LocalAnnoted", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.localAnnotedDep);
    }

    @objid ("ae47ca21-1b5a-4348-990b-959e5fea10c1")
    public SmDependency getLocalAnnotedDep() {
        if (this.localAnnotedDep == null) {
        	this.localAnnotedDep = this.getDependencyDef("LocalAnnoted");
        }
        return this.localAnnotedDep;
    }

    @objid ("abced0e9-31ad-466d-8151-f20bc0dcc490")
    @Override
    public boolean areOrphansAllowed() {
        return true;
    }

    @objid ("bd28cc78-75ed-47ad-87c8-8daa92ad49ee")
    private static class LocalPropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("109f3809-ee77-49ce-97fb-731b4eee7968")
        private LocalPropertyTableSmClass smClass;

        @objid ("1ed69e0f-8370-41d9-a3e4-ad1b8388c577")
        public LocalPropertyTableObjectFactory(LocalPropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("19718626-0c54-4b7a-8f20-079efba8d459")
        @Override
        public ISmObjectData createData() {
            return new LocalPropertyTableData(this.smClass);
        }

        @objid ("06c2e0a8-c446-4666-b4fe-fbeefa17bd08")
        @Override
        public SmObjectImpl createImpl() {
            return new LocalPropertyTableImpl();
        }

    }

    @objid ("0d5ff448-7ff2-49a0-b5c7-979a9f7c07a1")
    public static class LocalAnnotedSmDependency extends SmSingleDependency {
        @objid ("f27c2baf-74ab-4f06-b931-151f304ade38")
        private SmDependency symetricDep;

        @objid ("7cf3b61b-d1a3-4c1c-bf53-2b303d2237bb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LocalPropertyTableData) data).mLocalAnnoted;
        }

        @objid ("d96b5a67-b6d2-4e34-b3c0-c88a0b6ee957")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LocalPropertyTableData) data).mLocalAnnoted = value;
        }

        @objid ("9f03b5a6-1525-4613-a0af-291a71a71f3f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getLocalPropertiesDep();
            }
            return this.symetricDep;
        }

    }

}
