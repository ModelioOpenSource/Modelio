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
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionSmClass;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
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

@objid ("f80e9074-25b1-4e80-8cc7-1775094c0c0e")
public class ExternProcessorSmClass extends ModelElementSmClass {
    @objid ("3746d0fe-4776-4054-b68d-505e7b50e798")
    private SmAttribute classNameAtt;

    @objid ("b585517c-ec9d-4665-a5e3-8e094ff7aa95")
    private SmDependency ownerQueryDep;

    @objid ("bff2ab20-e89b-4684-a4ce-d6e0b5e44e1e")
    private SmDependency ownerValDefDep;

    @objid ("9dfe26ef-556e-4612-ad2d-936a2bc6169c")
    public  ExternProcessorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d391f5da-cb31-4b8b-969b-9bc4d91e6088")
    @Override
    public String getName() {
        return "ExternProcessor";
        
    }

    @objid ("235194e4-7b7b-4329-83fa-2d5d2145a514")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("716054f2-fc17-493c-97a2-41805e7d1d9a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExternProcessor.class;
        
    }

    @objid ("f999335a-8c73-46fe-a110-0a096257aedc")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("cf72ecf3-f1a1-4afd-9b32-9d3208503a23")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("584f4008-98ed-4b59-918c-c5e6d8537ad6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new ExternProcessorObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.classNameAtt = new ClassNameSmAttribute();
        this.classNameAtt.init("ClassName", this, String.class );
        registerAttribute(this.classNameAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerQueryDep = new OwnerQuerySmDependency();
        this.ownerQueryDep.init("OwnerQuery", this, metamodel.getMClass(QueryDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerQueryDep);
        
        this.ownerValDefDep = new OwnerValDefSmDependency();
        this.ownerValDefDep.init("OwnerValDef", this, metamodel.getMClass(MatrixValueDefinition.MQNAME), 0, 1 );
        registerDependency(this.ownerValDefDep);
        
        
    }

    @objid ("74a8d9a2-cec7-474c-bf8e-6d9512b275e6")
    public SmAttribute getClassNameAtt() {
        if (this.classNameAtt == null) {
        	this.classNameAtt = this.getAttributeDef("ClassName");
        }
        return this.classNameAtt;
    }

    @objid ("ce2b3606-3970-4c12-8aa1-f0845a727ee2")
    public SmDependency getOwnerQueryDep() {
        if (this.ownerQueryDep == null) {
        	this.ownerQueryDep = this.getDependencyDef("OwnerQuery");
        }
        return this.ownerQueryDep;
    }

    @objid ("1a6bd6f5-6411-47b6-b66e-eae73998fbe8")
    public SmDependency getOwnerValDefDep() {
        if (this.ownerValDefDep == null) {
        	this.ownerValDefDep = this.getDependencyDef("OwnerValDef");
        }
        return this.ownerValDefDep;
    }

    @objid ("ef3f808f-5fbd-4869-b27d-68c1ab528f85")
    private static class ExternProcessorObjectFactory implements ISmObjectFactory {
        @objid ("01803af8-feac-4477-b950-31b3a8686d90")
        private ExternProcessorSmClass smClass;

        @objid ("65404f26-0245-452f-bdbc-75a89f01a280")
        public  ExternProcessorObjectFactory(ExternProcessorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("63d3abf1-6ebf-4a4a-8dc8-1ce2bcd69a85")
        @Override
        public ISmObjectData createData() {
            return new ExternProcessorData(this.smClass);
        }

        @objid ("4b39d5e3-253e-4002-8d1a-6c6010413f1d")
        @Override
        public SmObjectImpl createImpl() {
            return new ExternProcessorImpl();
        }

    }

    @objid ("f68b65b1-be73-4302-9ab4-c30c5d5e6da9")
    public static class ClassNameSmAttribute extends SmAttribute {
        @objid ("67e29e57-5bb3-4627-8802-8277719e08e4")
        public Object getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mClassName;
        }

        @objid ("a06c00ee-079e-4129-ad7e-6029d11f908b")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternProcessorData) data).mClassName = value;
        }

    }

    @objid ("b08daa86-591d-4e6c-b904-b797e8794c4f")
    public static class OwnerQuerySmDependency extends SmSingleDependency {
        @objid ("b95aad7e-cff5-4b35-891d-cb7eff8ef2b6")
        private SmDependency symetricDep;

        @objid ("b156b5bb-aec7-4173-ba07-f751b1d11c1b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mOwnerQuery;
        }

        @objid ("85b05408-6bb5-4869-8a01-62d8fda9f1ff")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternProcessorData) data).mOwnerQuery = value;
        }

        @objid ("bf911e21-bfc9-4f68-9552-b22c3f646d48")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getProcessorDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("1f77c72a-d7c7-4495-9015-ad3477eb349b")
    public static class OwnerValDefSmDependency extends SmSingleDependency {
        @objid ("d961a318-5bad-4ef4-83ea-b73fda72a47a")
        private SmDependency symetricDep;

        @objid ("c0c6eff9-b0c5-485b-a220-9b9d6481e868")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mOwnerValDef;
        }

        @objid ("e66987e6-bd63-436b-9b3f-4e1db211f802")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternProcessorData) data).mOwnerValDef = value;
        }

        @objid ("79633b1e-fea2-4a2d-b6f6-ad8e832657b3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixValueDefinitionSmClass)this.getTarget()).getProcessorDep();
            }
            return this.symetricDep;
            
        }

    }

}
