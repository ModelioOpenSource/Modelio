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
    @objid ("6f5c0586-cab2-4c50-9307-e7acfe231673")
    public ResourceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("644c47ba-a12f-410f-ad2a-91e85482b98b")
    @Override
    public String getName() {
        return "Resource";
    }

    @objid ("434c07d3-c4ba-4d4c-b29e-556e3d802180")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("38b276cf-7d5e-462f-a01a-66106770a3b0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Resource.class;
    }

    @objid ("3b22a2d3-055b-40bc-9818-cdd3b100eee9")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("29cccfe2-a392-4387-9c1c-535f270f9077")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("2b607944-6fe9-48e8-9b20-b9cd27f6a2ee")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractResource.MQNAME);
        this.registerFactory(new ResourceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("4da3cc9f-58df-4d50-b4d7-50884103a404")
    private static class ResourceObjectFactory implements ISmObjectFactory {
        @objid ("f3274823-27b4-4052-8908-05621935ed02")
        private ResourceSmClass smClass;

        @objid ("7db4d896-a402-486d-aa82-4f86c474854a")
        public ResourceObjectFactory(ResourceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("871a1cb4-ceaa-41e0-9c21-01b4a8a50348")
        @Override
        public ISmObjectData createData() {
            return new ResourceData(this.smClass);
        }

        @objid ("308958b1-2bc2-4302-9943-f0c8c9070a94")
        @Override
        public SmObjectImpl createImpl() {
            return new ResourceImpl();
        }

    }

}
