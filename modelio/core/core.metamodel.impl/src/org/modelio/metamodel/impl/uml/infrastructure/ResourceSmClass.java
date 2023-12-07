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
    @objid ("26b920d7-d81d-4a66-9481-caa78087872f")
    public  ResourceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a375ad25-3b19-4f75-a0f4-fb516c9d4010")
    @Override
    public String getName() {
        return "Resource";
        
    }

    @objid ("e6fb58b5-d233-43dc-ac87-44cdb13607c7")
    @Override
    public Version getVersion() {
        return new Version("2.1.00");
    }

    @objid ("ca30cc77-2aba-4068-a3fe-37e01f73b49c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Resource.class;
        
    }

    @objid ("29f5c1a8-dc73-48c5-b596-82eb2aa363c9")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("070a7fa6-ffd7-44ba-a980-9eae7bd61702")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("6d6a1e33-ab7a-4a7a-abb6-abe8dd3ab21b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractResource.MQNAME);
        this.registerFactory(new ResourceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("4da3cc9f-58df-4d50-b4d7-50884103a404")
    private static class ResourceObjectFactory implements ISmObjectFactory {
        @objid ("a5ee42c4-8f46-4982-9faa-353b77b91b4a")
        private ResourceSmClass smClass;

        @objid ("066e8efa-0546-4948-9519-869909c67439")
        public  ResourceObjectFactory(ResourceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("aeb2ebee-0a83-4e92-822b-3139c2dc057b")
        @Override
        public ISmObjectData createData() {
            return new ResourceData(this.smClass);
        }

        @objid ("2849a498-ad08-421a-807e-a3f6c8d03b71")
        @Override
        public SmObjectImpl createImpl() {
            return new ResourceImpl();
        }

    }

}
