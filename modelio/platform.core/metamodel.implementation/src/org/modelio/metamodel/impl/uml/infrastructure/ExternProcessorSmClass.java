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
import org.modelio.metamodel.impl.uml.infrastructure.ExternProcessorData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
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
    @objid ("7ce84444-6728-41e6-81bb-ed68e0bb9632")
    private SmAttribute classNameAtt;

    @objid ("44ebc43e-4b4e-4553-b38e-856e263891eb")
    private SmDependency ownerQueryDep;

    @objid ("86af58cf-1802-4417-8e5b-e8312d3448a7")
    private SmDependency ownerValDefDep;

    @objid ("676bde14-99f3-461d-9364-a466c4384aef")
    public ExternProcessorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("912f92c7-cae3-4eff-b510-2938662adbde")
    @Override
    public String getName() {
        return "ExternProcessor";
    }

    @objid ("f7ff175d-95a2-49c9-911f-08803d8d762c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("fd83e9b2-840b-4406-a0e5-6a3f14a63151")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExternProcessor.class;
    }

    @objid ("92121f3f-b8cd-41b4-a701-017d4aec7a60")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("e8861fc7-d56a-4f6d-bc96-c49c141467e1")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f92e6848-ec53-445d-b361-db2e18be3298")
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

    @objid ("b874eebe-c2d8-4831-ace5-c3aa47ddb135")
    public SmAttribute getClassNameAtt() {
        if (this.classNameAtt == null) {
        	this.classNameAtt = this.getAttributeDef("ClassName");
        }
        return this.classNameAtt;
    }

    @objid ("3de2463e-3fd3-4ed5-b8fe-fae3e06953b2")
    public SmDependency getOwnerQueryDep() {
        if (this.ownerQueryDep == null) {
        	this.ownerQueryDep = this.getDependencyDef("OwnerQuery");
        }
        return this.ownerQueryDep;
    }

    @objid ("b2a1c586-5b65-4d40-ae34-6d667b079e94")
    public SmDependency getOwnerValDefDep() {
        if (this.ownerValDefDep == null) {
        	this.ownerValDefDep = this.getDependencyDef("OwnerValDef");
        }
        return this.ownerValDefDep;
    }

    @objid ("ef3f808f-5fbd-4869-b27d-68c1ab528f85")
    private static class ExternProcessorObjectFactory implements ISmObjectFactory {
        @objid ("dc6f4ca5-1504-4f0f-9214-c848064927c7")
        private ExternProcessorSmClass smClass;

        @objid ("113ba800-2106-4c77-bb71-42b4a15b9792")
        public ExternProcessorObjectFactory(ExternProcessorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8e3b531a-2374-4df2-bc40-a91b50709e05")
        @Override
        public ISmObjectData createData() {
            return new ExternProcessorData(this.smClass);
        }

        @objid ("711e0c50-8dd1-49a9-b3e7-bdc0b0e2c753")
        @Override
        public SmObjectImpl createImpl() {
            return new ExternProcessorImpl();
        }

    }

    @objid ("f68b65b1-be73-4302-9ab4-c30c5d5e6da9")
    public static class ClassNameSmAttribute extends SmAttribute {
        @objid ("e55d8edd-3668-4c58-a4b8-2a102c22f850")
        public Object getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mClassName;
        }

        @objid ("0c8d84e2-0ed9-4349-9fdf-7cd6fc4eb881")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternProcessorData) data).mClassName = value;
        }

    }

    @objid ("b08daa86-591d-4e6c-b904-b797e8794c4f")
    public static class OwnerQuerySmDependency extends SmSingleDependency {
        @objid ("4bad21e3-9e94-4692-9c9a-7c718d697902")
        private SmDependency symetricDep;

        @objid ("d9820832-9006-4959-a00e-bb87e4dce6d7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mOwnerQuery;
        }

        @objid ("80f84800-9110-4c30-9857-767c1d077312")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternProcessorData) data).mOwnerQuery = value;
        }

        @objid ("004fbb1e-7689-4afd-90b0-c7d95e407045")
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
        @objid ("1f55fd6a-1c98-440b-b2af-5b7669b01da7")
        private SmDependency symetricDep;

        @objid ("d458405e-9352-48ef-93a2-4d368a876716")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mOwnerValDef;
        }

        @objid ("f97b8ee8-9113-4467-9310-fec3abb29b62")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternProcessorData) data).mOwnerValDef = value;
        }

        @objid ("c85dd599-5117-4afd-94ec-608c5e393293")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixValueDefinitionSmClass)this.getTarget()).getProcessorDep();
            }
            return this.symetricDep;
        }

    }

}
