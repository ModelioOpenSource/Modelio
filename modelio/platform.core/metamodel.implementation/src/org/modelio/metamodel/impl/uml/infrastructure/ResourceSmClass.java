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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractResourceSmClass;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("955a8b5f-9725-4702-8ec9-55a39db52af4")
public class ResourceSmClass extends AbstractResourceSmClass {
    @objid ("3156eb38-e993-43ef-b575-733e8240a276")
    public ResourceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6b944ce1-c024-4a6d-8008-59699c479125")
    @Override
    public String getName() {
        return "Resource";
    }

    @objid ("e80390cc-a7ee-443c-8733-029068302b79")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("0e8e4de9-c89b-4c91-b5e4-7723c4e05d93")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Resource.class;
    }

    @objid ("3e768d20-572f-4ff5-9f12-6eeb2e852e15")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("91dfed00-5dc5-483d-82ae-8509d442db35")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("d39cfaab-fbd2-46d6-8c29-9d384eaf059a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractResource.MQNAME);
        this.registerFactory(new ResourceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("4da3cc9f-58df-4d50-b4d7-50884103a404")
    private static class ResourceObjectFactory implements ISmObjectFactory {
        @objid ("5865f4e7-b406-44e2-b2d8-93160d474c2e")
        private ResourceSmClass smClass;

        @objid ("5c208899-9a62-4629-b563-487ce93ba314")
        public ResourceObjectFactory(ResourceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0eeeec49-fd79-4472-a9e8-c036426ab705")
        @Override
        public ISmObjectData createData() {
            return new ResourceData(this.smClass);
        }

        @objid ("d0c3fc0e-c56c-4db0-9ba1-f3dfb5e16b1e")
        @Override
        public SmObjectImpl createImpl() {
            return new ResourceImpl();
        }

    }

}
