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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("21bd450b-56cf-400b-aa3a-0f842af7818d")
    private SmAttribute providerAtt;

    @objid ("baf66f50-f625-4b2a-857f-9cf11e8e4e24")
    private SmAttribute externIdAtt;

    @objid ("c857eca6-b940-4b0c-aa85-55baf1ad224c")
    private SmAttribute locationAtt;

    @objid ("69d36b23-8fb4-4ba8-a6e8-90455b3f9b5f")
    private SmDependency ownerDep;

    @objid ("9d740594-ae06-4e52-80fa-caa59a75840e")
    public  ExternElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f55a8e31-69d4-4a66-b3e2-65d138c12e0f")
    @Override
    public String getName() {
        return "ExternElement";
        
    }

    @objid ("9ea255b7-3c90-4517-88a7-2f4775cd0fc0")
    @Override
    public Version getVersion() {
        return new Version("0.0.0");
    }

    @objid ("ed79dbca-4824-438a-8d41-ba9e0a54eacb")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExternElement.class;
        
    }

    @objid ("452f0dbc-af3f-4a96-92fe-a378d446436e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("2643d9cc-dd18-48df-b035-1aaaf3f94137")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ecbcbcc9-58c8-4a78-97f1-d0e8112b3025")
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

    @objid ("e5494e0b-d13e-48b0-a225-ce92cca719f2")
    public SmAttribute getProviderAtt() {
        if (this.providerAtt == null) {
        	this.providerAtt = this.getAttributeDef("Provider");
        }
        return this.providerAtt;
    }

    @objid ("87b412e0-d15f-496c-8c6c-4d5e68520af5")
    public SmAttribute getExternIdAtt() {
        if (this.externIdAtt == null) {
        	this.externIdAtt = this.getAttributeDef("ExternId");
        }
        return this.externIdAtt;
    }

    @objid ("fb5e9641-3c10-4101-b4f0-033e9bde3228")
    public SmAttribute getLocationAtt() {
        if (this.locationAtt == null) {
        	this.locationAtt = this.getAttributeDef("Location");
        }
        return this.locationAtt;
    }

    @objid ("d7e3093c-3db6-4166-b4f3-2d912c5210be")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("a74a6cae-1052-4cc4-a153-14b8fc53b199")
    private static class ExternElementObjectFactory implements ISmObjectFactory {
        @objid ("f699c652-a7f8-4c1a-a533-f26221c2550a")
        private ExternElementSmClass smClass;

        @objid ("462d53ac-cf37-42e1-a4f3-ab6b46912a4a")
        public  ExternElementObjectFactory(ExternElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fde6d75d-59f9-447e-84d3-960ad1540361")
        @Override
        public ISmObjectData createData() {
            return new ExternElementData(this.smClass);
        }

        @objid ("a4d28811-a483-4a13-a230-0cd01c90f01a")
        @Override
        public SmObjectImpl createImpl() {
            return new ExternElementImpl();
        }

    }

    @objid ("c079e4f5-9bcb-43b1-b963-a5f70f57fc08")
    public static class ProviderSmAttribute extends SmAttribute {
        @objid ("27589a52-950e-4d21-9405-efcbbe9b1cb7")
        public Object getValue(ISmObjectData data) {
            return ((ExternElementData) data).mProvider;
        }

        @objid ("7cbdafaa-e140-4294-91c5-fddf60514324")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternElementData) data).mProvider = value;
        }

    }

    @objid ("c2ef6589-cd06-413b-bf6f-e218bab3aa8f")
    public static class ExternIdSmAttribute extends SmAttribute {
        @objid ("48032e45-ca50-40ac-9262-2134df3deba5")
        public Object getValue(ISmObjectData data) {
            return ((ExternElementData) data).mExternId;
        }

        @objid ("cf6fad49-0845-4071-99eb-a84023d242b2")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternElementData) data).mExternId = value;
        }

    }

    @objid ("8481eb5e-8372-406f-b4a1-a3afa134655c")
    public static class LocationSmAttribute extends SmAttribute {
        @objid ("ba8e46ed-a9cb-49d4-aca0-29268900cdcf")
        public Object getValue(ISmObjectData data) {
            return ((ExternElementData) data).mLocation;
        }

        @objid ("ea1f89b4-d785-454f-9fd1-bebb209cfae9")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternElementData) data).mLocation = value;
        }

    }

    @objid ("d49825ad-1a43-4438-bcd1-d469433cc4ed")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("8a66f52a-4665-467d-98c7-d53a5d77fbe3")
        private SmDependency symetricDep;

        @objid ("421da56b-bcb4-4f99-b8ec-77583a2788b8")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternElementData) data).mOwner;
        }

        @objid ("95204539-1f9c-4ff3-bf00-690476c01244")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternElementData) data).mOwner = value;
        }

        @objid ("b773c4c9-1cdf-4231-9443-96d79054d3b8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MethodologicalLinkSmClass)this.getTarget()).getExternElementDep();
            }
            return this.symetricDep;
            
        }

    }

}
