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
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyDefinitionSmClass;
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
    @objid ("b485795d-5caa-4522-a588-92462b4dc470")
    public DynamicPropertyDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("52009d64-811e-40ae-bd9a-b547fc062bce")
    @Override
    public String getName() {
        return "DynamicPropertyDefinition";
    }

    @objid ("12a04c16-3f95-478e-b159-e9abdd8243c2")
    @Override
    public Version getVersion() {
        return new Version("1.1.1");
    }

    @objid ("7abaafe0-5590-4d4a-9458-41b4c8e87999")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DynamicPropertyDefinition.class;
    }

    @objid ("d4603599-d34e-4b29-9add-5398765b09d2")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("a658408b-082d-44fc-bbab-873cd4e044ae")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("5f573cf2-fcc1-4114-9cab-b1f45a8b68df")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(PropertyDefinition.MQNAME);
        this.registerFactory(new DynamicPropertyDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("b7859054-bfa5-49ee-85ed-a4ebf2e0fecd")
    private static class DynamicPropertyDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("d53dc821-7220-4193-8f33-15b8157a76df")
        private DynamicPropertyDefinitionSmClass smClass;

        @objid ("b21b822c-2101-41eb-9b8f-c5b8df561a53")
        public DynamicPropertyDefinitionObjectFactory(DynamicPropertyDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("849371af-e82c-4294-abf5-2f167984f3e3")
        @Override
        public ISmObjectData createData() {
            return new DynamicPropertyDefinitionData(this.smClass);
        }

        @objid ("abb08a99-f5f5-42b1-a916-e776c7d4c0cd")
        @Override
        public SmObjectImpl createImpl() {
            return new DynamicPropertyDefinitionImpl();
        }

    }

}
