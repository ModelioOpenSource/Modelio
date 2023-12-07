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

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
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
    @objid ("e562402b-0056-47fa-8caf-0fee8fb7afed")
    private SmDependency localAnnotedDep;

    @objid ("210eef03-4aef-4d95-b99a-3a58133a0477")
    public  LocalPropertyTableSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("acd09a7e-eca8-4d1c-888b-1112c6981457")
    @Override
    public String getName() {
        return "LocalPropertyTable";
        
    }

    @objid ("09655d5a-1364-4c92-8108-c69613778a37")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ea4f2ab8-e4ed-4a69-8180-29457fa57834")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return LocalPropertyTable.class;
        
    }

    @objid ("54aaf3b7-33bb-4ec8-b9b6-e81064d24605")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("8271b46c-227f-432e-8e3b-53622031c623")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("138ad095-f999-47fd-8ee1-518cf40ce21e")
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

    @objid ("c19f7e2d-8915-402f-8fdb-c3d1bc204071")
    public SmDependency getLocalAnnotedDep() {
        if (this.localAnnotedDep == null) {
        	this.localAnnotedDep = this.getDependencyDef("LocalAnnoted");
        }
        return this.localAnnotedDep;
    }

    @objid ("becd4464-b754-4194-81b9-54b0a170d9a4")
    @Override
    public boolean areOrphansAllowed() {
        return true;
        
    }

    @objid ("bd28cc78-75ed-47ad-87c8-8daa92ad49ee")
    private static class LocalPropertyTableObjectFactory implements ISmObjectFactory {
        @objid ("304ceb96-0509-4535-971c-8be6dc790add")
        private LocalPropertyTableSmClass smClass;

        @objid ("810158f8-9834-494b-ad27-4256cf3207e7")
        public  LocalPropertyTableObjectFactory(LocalPropertyTableSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("76b482a6-ad45-43b3-a616-f95455847c41")
        @Override
        public ISmObjectData createData() {
            return new LocalPropertyTableData(this.smClass);
        }

        @objid ("be8d772a-2c02-4e3c-a457-85d24c308ebd")
        @Override
        public SmObjectImpl createImpl() {
            return new LocalPropertyTableImpl();
        }

    }

    @objid ("0d5ff448-7ff2-49a0-b5c7-979a9f7c07a1")
    public static class LocalAnnotedSmDependency extends SmSingleDependency {
        @objid ("46550970-296a-4333-ba0b-122980213624")
        private SmDependency symetricDep;

        @objid ("83424b6b-5267-4815-941c-f8e04d174de3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LocalPropertyTableData) data).mLocalAnnoted;
        }

        @objid ("f4fcc900-b373-4ba6-9776-7cf4166e7a4a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LocalPropertyTableData) data).mLocalAnnoted = value;
        }

        @objid ("3c66349e-1098-4c35-9881-43e6349a63bd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getLocalPropertiesDep();
            }
            return this.symetricDep;
            
        }

    }

}
