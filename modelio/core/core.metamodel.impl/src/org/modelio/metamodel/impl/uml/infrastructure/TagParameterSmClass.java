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
    @objid ("6f978b43-2fc4-43f2-9382-5b315c17aac2")
    private SmAttribute valueAtt;

    @objid ("0819f92c-7449-435d-b665-3a8914ad0295")
    private SmDependency annotedDep;

    @objid ("4540b2de-d239-4fc8-91c7-5cd6b65ff3db")
    private SmDependency qualifiedDep;

    @objid ("276e2ff1-14c4-4765-a7e0-9bb1f78b2939")
    public  TagParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b293ff38-ffa7-4e4b-b914-44b0990ef229")
    @Override
    public String getName() {
        return "TagParameter";
        
    }

    @objid ("7e28ca04-cfb3-401d-ad07-07f7a6126cc0")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9e62dbec-72d1-41e9-9811-d868bd226c36")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TagParameter.class;
        
    }

    @objid ("dccc5abd-00a6-4ee0-ae72-c598107bad78")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("5f748d2d-459d-4459-98ca-548723c732e4")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ec8bb827-3b04-4bcf-a947-c33c1d7bc3f6")
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

    @objid ("a25f5beb-bf88-4915-91f7-012446481156")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("89b6e685-952f-41b4-b25f-0982b485c00c")
    public SmDependency getAnnotedDep() {
        if (this.annotedDep == null) {
        	this.annotedDep = this.getDependencyDef("Annoted");
        }
        return this.annotedDep;
    }

    @objid ("58da3661-d85f-4fbc-8afa-0d210f517a42")
    public SmDependency getQualifiedDep() {
        if (this.qualifiedDep == null) {
        	this.qualifiedDep = this.getDependencyDef("Qualified");
        }
        return this.qualifiedDep;
    }

    @objid ("9f528095-b90b-4050-bd8c-b4b1f9ffaa3f")
    private static class TagParameterObjectFactory implements ISmObjectFactory {
        @objid ("55ba523c-b9c6-4d9f-8145-9edbfcebdd3a")
        private TagParameterSmClass smClass;

        @objid ("bfeca077-8867-47de-b5c4-61258499737f")
        public  TagParameterObjectFactory(TagParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("6fefcb33-1c01-4e95-a7eb-2af17f3889fb")
        @Override
        public ISmObjectData createData() {
            return new TagParameterData(this.smClass);
        }

        @objid ("0689495d-3eb0-47fd-a42c-d4f8789b06cf")
        @Override
        public SmObjectImpl createImpl() {
            return new TagParameterImpl();
        }

    }

    @objid ("11ac1d03-2c0d-42b8-b8a6-5a8087b9f3ca")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("f1e37ff8-d2db-432b-a468-d215d2d3298e")
        public Object getValue(ISmObjectData data) {
            return ((TagParameterData) data).mValue;
        }

        @objid ("7727ee05-47a8-46dd-a0e7-8832655770c4")
        public void setValue(ISmObjectData data, Object value) {
            ((TagParameterData) data).mValue = value;
        }

    }

    @objid ("ccf45f66-35e6-41b0-846b-8b833d63859b")
    public static class AnnotedSmDependency extends SmSingleDependency {
        @objid ("5ef8bcd8-6881-447d-8918-6b2d68321b0a")
        private SmDependency symetricDep;

        @objid ("a2aac740-18b0-4029-8e46-b8ddd760dc71")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagParameterData) data).mAnnoted;
        }

        @objid ("b1390458-5142-4b9f-ac8a-8e3acfe5560a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagParameterData) data).mAnnoted = value;
        }

        @objid ("e05756e0-db29-44d7-ad22-eb65dfc55791")
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
        @objid ("038d0d97-ded8-4c18-ad41-3e6c14f3e7bd")
        private SmDependency symetricDep;

        @objid ("e599b07e-4664-429f-bc67-383d41261ced")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagParameterData) data).mQualified;
        }

        @objid ("ea2ca0e5-cc94-488a-bfeb-b17fd88c3c05")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagParameterData) data).mQualified = value;
        }

        @objid ("f7c78a8e-5547-44c4-9320-3bf078758cc2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TaggedValueSmClass)this.getTarget()).getQualifierDep();
            }
            return this.symetricDep;
            
        }

    }

}
