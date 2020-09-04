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
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TagParameterData;
import org.modelio.metamodel.impl.uml.infrastructure.TaggedValueSmClass;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
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

@objid ("11c38d03-d043-40fd-b747-ca7c1ca5879b")
public class TagParameterSmClass extends ElementSmClass {
    @objid ("9886834c-a77f-48c4-a276-da892a8ff1cf")
    private SmAttribute valueAtt;

    @objid ("94aecb22-e4ff-4e26-8413-13227bbfb867")
    private SmDependency annotedDep;

    @objid ("4bf057c3-60b0-463b-af9d-89a78494885f")
    private SmDependency qualifiedDep;

    @objid ("47eadb97-297d-4e3d-9155-1fd742392053")
    public TagParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9f683a0d-f3bf-4467-b471-a03967a4c282")
    @Override
    public String getName() {
        return "TagParameter";
    }

    @objid ("332f5d08-34ea-4cc9-9367-c472a72484ae")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("dd45d0fc-4281-4fa9-a9fe-a6aa962b5cba")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TagParameter.class;
    }

    @objid ("f4b3f0fa-5b2f-4e74-a3c7-d0cc0f408742")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6403c30e-c332-4256-a49c-015204cf1e93")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("42554540-a585-493f-9cce-e96b61725901")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Element.MQNAME);
        this.registerFactory(new TagParameterObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.valueAtt = new ValueSmAttribute();
        this.valueAtt.init("Value", this, String.class );
        registerAttribute(this.valueAtt);
        
        
        // Initialize and register the SmDependency
        this.annotedDep = new AnnotedSmDependency();
        this.annotedDep.init("Annoted", this, metamodel.getMClass(TaggedValue.MQNAME), 0, 1 );
        registerDependency(this.annotedDep);
        
        this.qualifiedDep = new QualifiedSmDependency();
        this.qualifiedDep.init("Qualified", this, metamodel.getMClass(TaggedValue.MQNAME), 0, 1 );
        registerDependency(this.qualifiedDep);
    }

    @objid ("0772252a-3c75-47e9-b049-41e1b86df8e5")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("51e4862d-ca16-4371-b3f8-fe20d82f99d9")
    public SmDependency getAnnotedDep() {
        if (this.annotedDep == null) {
        	this.annotedDep = this.getDependencyDef("Annoted");
        }
        return this.annotedDep;
    }

    @objid ("b8770be6-894a-4acb-8ec9-59444e8c1e5d")
    public SmDependency getQualifiedDep() {
        if (this.qualifiedDep == null) {
        	this.qualifiedDep = this.getDependencyDef("Qualified");
        }
        return this.qualifiedDep;
    }

    @objid ("9f528095-b90b-4050-bd8c-b4b1f9ffaa3f")
    private static class TagParameterObjectFactory implements ISmObjectFactory {
        @objid ("48b4b3c4-f763-4a15-ad08-0ae3ed96a03b")
        private TagParameterSmClass smClass;

        @objid ("5f9c5dbe-82ec-4c5c-9f53-1e2aedb6a559")
        public TagParameterObjectFactory(TagParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a9928ef6-17f5-43c7-81ba-b93968a01672")
        @Override
        public ISmObjectData createData() {
            return new TagParameterData(this.smClass);
        }

        @objid ("b98c696c-b22a-4c88-80b2-7c7756c5a549")
        @Override
        public SmObjectImpl createImpl() {
            return new TagParameterImpl();
        }

    }

    @objid ("11ac1d03-2c0d-42b8-b8a6-5a8087b9f3ca")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("3160ea70-5265-4b97-bec8-b999d1cf90e2")
        public Object getValue(ISmObjectData data) {
            return ((TagParameterData) data).mValue;
        }

        @objid ("4ab7c7c0-11c4-42b8-917c-02824aa51677")
        public void setValue(ISmObjectData data, Object value) {
            ((TagParameterData) data).mValue = value;
        }

    }

    @objid ("ccf45f66-35e6-41b0-846b-8b833d63859b")
    public static class AnnotedSmDependency extends SmSingleDependency {
        @objid ("a5c6ff3d-5341-4e41-8bbf-866815f183f6")
        private SmDependency symetricDep;

        @objid ("bcc79e90-25f9-4b4a-b1ec-41490149f85b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagParameterData) data).mAnnoted;
        }

        @objid ("7f2577e7-1ef9-415f-adba-2e39a887e3ba")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagParameterData) data).mAnnoted = value;
        }

        @objid ("88407a07-4ed6-47a9-ab00-0f200db09b72")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TaggedValueSmClass)this.getTarget()).getActualDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6d5e4ec4-4ca8-444d-b842-10456211b5e1")
    public static class QualifiedSmDependency extends SmSingleDependency {
        @objid ("98ab8619-cab1-4f0a-8647-745b5d106393")
        private SmDependency symetricDep;

        @objid ("899366f3-1776-43bb-902f-1625fbb49f1f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagParameterData) data).mQualified;
        }

        @objid ("ff4deff7-bb02-40c0-8777-c02a57660620")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagParameterData) data).mQualified = value;
        }

        @objid ("1257acf9-ace5-4f3c-8576-be77166a9bd4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TaggedValueSmClass)this.getTarget()).getQualifierDep();
            }
            return this.symetricDep;
        }

    }

}
