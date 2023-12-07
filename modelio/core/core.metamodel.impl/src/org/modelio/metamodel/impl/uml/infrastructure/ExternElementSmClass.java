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
import org.modelio.metamodel.uml.infrastructure.ExternElement;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("bb01b1df-d69b-484e-a283-b96fe3abb407")
public class ExternElementSmClass extends ModelElementSmClass {
    @objid ("f09d368f-cc97-45ab-9797-24bf9705a53a")
    private SmAttribute providerAtt;

    @objid ("91ba2ef9-bf89-4c1f-bf6d-a293c3d07311")
    private SmAttribute externIdAtt;

    @objid ("a0b7aa99-de48-4074-bb05-da3547bf6daa")
    private SmAttribute locationAtt;

    @objid ("74500cb3-5492-4ccf-9e85-79242808174e")
    private SmDependency ownerDep;

    @objid ("6ef09d05-d2e4-450a-bfca-1b5c25133607")
    public  ExternElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("25a8d131-1eab-4a1c-8a13-888849b7ab1f")
    @Override
    public String getName() {
        return "ExternElement";
        
    }

    @objid ("2707bb93-d6a4-41d9-a280-7b83d2a945d2")
    @Override
    public Version getVersion() {
        return new Version("0.0.0");
    }

    @objid ("e67fe198-eddc-4948-9e81-a1aa11f0fbd5")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExternElement.class;
        
    }

    @objid ("0b9f6369-c1eb-4680-b904-a7ad8294bc2d")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("7db94386-fe83-4308-835f-4f4517420bd6")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7829fc0a-c4e4-4ea5-a15b-b5d95d93fbf7")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new ExternElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.providerAtt = new ProviderSmAttribute();
        this.providerAtt.init("Provider", this, String.class );
        registerAttribute(this.providerAtt);
        
        this.externIdAtt = new ExternIdSmAttribute();
        this.externIdAtt.init("ExternId", this, String.class );
        registerAttribute(this.externIdAtt);
        
        this.locationAtt = new LocationSmAttribute();
        this.locationAtt.init("Location", this, String.class );
        registerAttribute(this.locationAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(MethodologicalLink.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
        
        
    }

    @objid ("4af4413e-8b37-4188-9bb6-ce03a1666d35")
    public SmAttribute getProviderAtt() {
        if (this.providerAtt == null) {
        	this.providerAtt = this.getAttributeDef("Provider");
        }
        return this.providerAtt;
    }

    @objid ("f8d401cf-fe91-4c11-ba7b-e4c7b7cac1d6")
    public SmAttribute getExternIdAtt() {
        if (this.externIdAtt == null) {
        	this.externIdAtt = this.getAttributeDef("ExternId");
        }
        return this.externIdAtt;
    }

    @objid ("a326c7f3-fa27-4f6f-b391-3164c537fd25")
    public SmAttribute getLocationAtt() {
        if (this.locationAtt == null) {
        	this.locationAtt = this.getAttributeDef("Location");
        }
        return this.locationAtt;
    }

    @objid ("dca0c6ac-f3af-4de3-9d45-b6ba25d530b9")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("a74a6cae-1052-4cc4-a153-14b8fc53b199")
    private static class ExternElementObjectFactory implements ISmObjectFactory {
        @objid ("f478cc9c-4267-4cc0-a480-8ae8e277d8b6")
        private ExternElementSmClass smClass;

        @objid ("fe67351c-89b6-40d4-9b6a-ad062112f238")
        public  ExternElementObjectFactory(ExternElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d5c7fdab-ead4-47ad-8410-e7e7947799b3")
        @Override
        public ISmObjectData createData() {
            return new ExternElementData(this.smClass);
        }

        @objid ("0a0220b3-aad3-490d-b04e-9b4c5af5b7f5")
        @Override
        public SmObjectImpl createImpl() {
            return new ExternElementImpl();
        }

    }

    @objid ("c079e4f5-9bcb-43b1-b963-a5f70f57fc08")
    public static class ProviderSmAttribute extends SmAttribute {
        @objid ("3b95c45c-4b7b-4c23-a418-642631793cb3")
        public Object getValue(ISmObjectData data) {
            return ((ExternElementData) data).mProvider;
        }

        @objid ("e0ffdb06-09d5-43b2-9783-30b364e7246a")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternElementData) data).mProvider = value;
        }

    }

    @objid ("c2ef6589-cd06-413b-bf6f-e218bab3aa8f")
    public static class ExternIdSmAttribute extends SmAttribute {
        @objid ("c19c7ad1-794d-4da9-a3d4-e7f21a50fabc")
        public Object getValue(ISmObjectData data) {
            return ((ExternElementData) data).mExternId;
        }

        @objid ("b9aae609-c6a7-4430-a6e2-0375562798ac")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternElementData) data).mExternId = value;
        }

    }

    @objid ("8481eb5e-8372-406f-b4a1-a3afa134655c")
    public static class LocationSmAttribute extends SmAttribute {
        @objid ("3f90fdc8-c6af-4ce5-beee-e80ef671e28c")
        public Object getValue(ISmObjectData data) {
            return ((ExternElementData) data).mLocation;
        }

        @objid ("e3da73b8-88e4-4692-a7c8-ee8117314791")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternElementData) data).mLocation = value;
        }

    }

    @objid ("d49825ad-1a43-4438-bcd1-d469433cc4ed")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("16c6bc2e-a9d1-4600-8754-f0ea22f2018b")
        private SmDependency symetricDep;

        @objid ("34e6fc4a-aff5-4333-9a5b-866a7b47eda7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternElementData) data).mOwner;
        }

        @objid ("968f6353-8b5d-4da5-a31e-bdc4a4f3ccb9")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternElementData) data).mOwner = value;
        }

        @objid ("3fdcde7c-df50-42d2-99c7-11b6c4fc1cd3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MethodologicalLinkSmClass)this.getTarget()).getExternElementDep();
            }
            return this.symetricDep;
            
        }

    }

}
