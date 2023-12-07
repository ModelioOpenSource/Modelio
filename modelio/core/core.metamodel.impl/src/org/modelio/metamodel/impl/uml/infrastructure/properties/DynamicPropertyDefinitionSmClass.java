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
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("9212b99c-be1d-494e-af24-84c580bd7cc8")
public class DynamicPropertyDefinitionSmClass extends PropertyDefinitionSmClass {
    @objid ("5ebebc00-2762-4d93-9cfe-cfcc00b6c0bb")
    public  DynamicPropertyDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("94138be9-8c98-4f2c-9bde-6fc90ba6b324")
    @Override
    public String getName() {
        return "DynamicPropertyDefinition";
        
    }

    @objid ("46687652-68f5-4cf9-b73c-d3ae274a2171")
    @Override
    public Version getVersion() {
        return new Version("1.1.1");
    }

    @objid ("d02f31bf-e2c8-49b0-8afb-84d82ca3f2ea")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DynamicPropertyDefinition.class;
        
    }

    @objid ("a8244d41-c889-4d7c-92f6-5d8d3e6e8b0a")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("53dd8a1a-f6d3-41e8-9545-f68cba001142")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("91f6c402-1500-43a2-8f9d-70c10df765b5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(PropertyDefinition.MQNAME);
        this.registerFactory(new DynamicPropertyDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("b7859054-bfa5-49ee-85ed-a4ebf2e0fecd")
    private static class DynamicPropertyDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("e613df5c-f4a9-4476-8cf3-3c1a8156262d")
        private DynamicPropertyDefinitionSmClass smClass;

        @objid ("da2f71fc-ece5-425c-8691-2807c24dbd04")
        public  DynamicPropertyDefinitionObjectFactory(DynamicPropertyDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("bfbd432b-d820-44e2-b379-7897027e7796")
        @Override
        public ISmObjectData createData() {
            return new DynamicPropertyDefinitionData(this.smClass);
        }

        @objid ("34e73d80-9c64-47ab-ae58-3acd5bb0af5b")
        @Override
        public SmObjectImpl createImpl() {
            return new DynamicPropertyDefinitionImpl();
        }

    }

}
