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
    @objid ("532f8de7-1c87-4460-87c3-b436a3b162e1")
    public DynamicPropertyDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9e6c51c3-69e5-4018-b48c-25552edcd0ad")
    @Override
    public String getName() {
        return "DynamicPropertyDefinition";
    }

    @objid ("6cc541c3-e693-4659-bb60-55704d69da5a")
    @Override
    public Version getVersion() {
        return new Version("1.1.1");
    }

    @objid ("d56f0261-ffdc-4616-9f42-1dce9a00e881")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DynamicPropertyDefinition.class;
    }

    @objid ("961239a5-20eb-4b98-bb7e-78bd750ea9d9")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("30b419cd-bd10-4eeb-882c-7a1dc55f1a78")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f8a5cf51-2d48-4224-9c06-3be69f48bcb1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(PropertyDefinition.MQNAME);
        this.registerFactory(new DynamicPropertyDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("b7859054-bfa5-49ee-85ed-a4ebf2e0fecd")
    private static class DynamicPropertyDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("2106c24f-7fd4-4a43-815b-681de8ad22d0")
        private DynamicPropertyDefinitionSmClass smClass;

        @objid ("7e0d4a63-0b8e-428d-a08b-a99ae259ff7f")
        public DynamicPropertyDefinitionObjectFactory(DynamicPropertyDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2798bdf0-d063-4005-97b8-fccdd11de823")
        @Override
        public ISmObjectData createData() {
            return new DynamicPropertyDefinitionData(this.smClass);
        }

        @objid ("190a3d58-1af5-4235-ab2a-66ea8c666e6a")
        @Override
        public SmObjectImpl createImpl() {
            return new DynamicPropertyDefinitionImpl();
        }

    }

}
