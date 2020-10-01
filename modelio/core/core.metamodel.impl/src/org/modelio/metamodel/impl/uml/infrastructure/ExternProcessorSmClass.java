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
    @objid ("85002ca6-03b0-4da7-9c37-5f0327db594e")
    private SmAttribute classNameAtt;

    @objid ("de892a65-f6c6-4314-be15-2f4bfc869b79")
    private SmDependency ownerQueryDep;

    @objid ("197cace2-b3ff-4be6-bf65-5138056c40cd")
    private SmDependency ownerValDefDep;

    @objid ("cd5a906d-43d4-4e5a-84bf-4b1236c78915")
    public ExternProcessorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4084e5db-2f08-41c0-925c-a19267107428")
    @Override
    public String getName() {
        return "ExternProcessor";
    }

    @objid ("bbb0a678-0486-4704-82e4-79cbd818ae2a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d6c73b9e-9fa0-4196-933d-8500e9f2ed60")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExternProcessor.class;
    }

    @objid ("d2b4a903-1588-4b98-ae6a-b165105e84be")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("a0469a48-d8e6-48bc-aaab-0a14c1d01eb5")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("b4ba9efd-35fa-40b8-913c-242753273776")
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

    @objid ("8b746631-8596-422f-818a-d61e4f505d17")
    public SmAttribute getClassNameAtt() {
        if (this.classNameAtt == null) {
        	this.classNameAtt = this.getAttributeDef("ClassName");
        }
        return this.classNameAtt;
    }

    @objid ("e863b6e6-8691-4aab-8c70-4e23fb86c17b")
    public SmDependency getOwnerQueryDep() {
        if (this.ownerQueryDep == null) {
        	this.ownerQueryDep = this.getDependencyDef("OwnerQuery");
        }
        return this.ownerQueryDep;
    }

    @objid ("3d7eed99-9d3e-404d-8854-3f99303f0158")
    public SmDependency getOwnerValDefDep() {
        if (this.ownerValDefDep == null) {
        	this.ownerValDefDep = this.getDependencyDef("OwnerValDef");
        }
        return this.ownerValDefDep;
    }

    @objid ("ef3f808f-5fbd-4869-b27d-68c1ab528f85")
    private static class ExternProcessorObjectFactory implements ISmObjectFactory {
        @objid ("43b944ad-12f3-4061-a0ad-a980276328a6")
        private ExternProcessorSmClass smClass;

        @objid ("cb69b63a-2e5f-44d9-8e25-e8898b8e5d9c")
        public ExternProcessorObjectFactory(ExternProcessorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d8c40d15-fc2f-4b78-8263-3622f16b3372")
        @Override
        public ISmObjectData createData() {
            return new ExternProcessorData(this.smClass);
        }

        @objid ("601bbc03-6d60-4c26-8f3f-4a958a342b07")
        @Override
        public SmObjectImpl createImpl() {
            return new ExternProcessorImpl();
        }

    }

    @objid ("f68b65b1-be73-4302-9ab4-c30c5d5e6da9")
    public static class ClassNameSmAttribute extends SmAttribute {
        @objid ("2b4c63c5-4bdd-4af3-8b16-2fdcf856a52b")
        public Object getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mClassName;
        }

        @objid ("3e15823f-cb24-4626-a6fc-5a040f93cc92")
        public void setValue(ISmObjectData data, Object value) {
            ((ExternProcessorData) data).mClassName = value;
        }

    }

    @objid ("b08daa86-591d-4e6c-b904-b797e8794c4f")
    public static class OwnerQuerySmDependency extends SmSingleDependency {
        @objid ("dd7c6832-c33e-4c41-9787-da0e3fbbe866")
        private SmDependency symetricDep;

        @objid ("eadaea0f-419f-4e7f-a751-e93229dd7a73")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mOwnerQuery;
        }

        @objid ("f463cce1-5170-47f8-b574-034a4e8e1e08")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternProcessorData) data).mOwnerQuery = value;
        }

        @objid ("5e07d07c-3902-4365-b7ce-3867976a3d1e")
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
        @objid ("8afaa74c-883a-4335-ab90-ec871e0e53c7")
        private SmDependency symetricDep;

        @objid ("acd885ce-e48b-4087-b278-48e08cff84e6")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExternProcessorData) data).mOwnerValDef;
        }

        @objid ("34f5b92c-5bde-4df5-b7d6-6b712c4263c5")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExternProcessorData) data).mOwnerValDef = value;
        }

        @objid ("9da19c7a-6ee7-4e28-8298-1783d66e4b73")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixValueDefinitionSmClass)this.getTarget()).getProcessorDep();
            }
            return this.symetricDep;
        }

    }

}
