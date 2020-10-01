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
    @objid ("ff2c7f2e-c071-49c2-b19e-31205eca83e4")
    private SmAttribute valueAtt;

    @objid ("beac1eea-ff8f-46da-ab99-b967d7658109")
    private SmDependency annotedDep;

    @objid ("87e459a3-a9b5-4abd-8acd-abd329ebdac9")
    private SmDependency qualifiedDep;

    @objid ("dbe0da8c-4f7c-481a-adf8-b1d727375134")
    public TagParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("591518eb-ec8c-4dab-819e-78112c388a67")
    @Override
    public String getName() {
        return "TagParameter";
    }

    @objid ("b1390d01-9a84-4604-b9b0-eff348b11f27")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b1bd5855-5774-49d6-90a0-997e101156f0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TagParameter.class;
    }

    @objid ("64857fd7-88d3-46c9-906c-4f301c32ca8d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("1ec40dd7-3396-4ca7-a1b9-57c25bc244a8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f389a3a5-bb55-4ffd-b6be-fae5112814a8")
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

    @objid ("0120af72-bda8-45c2-a341-075f3d3d1296")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("76cb6a6d-21cf-4fd6-9247-1e5620ae2a32")
    public SmDependency getAnnotedDep() {
        if (this.annotedDep == null) {
        	this.annotedDep = this.getDependencyDef("Annoted");
        }
        return this.annotedDep;
    }

    @objid ("aefe53c2-4808-4831-8ff7-0e6563dcbaa2")
    public SmDependency getQualifiedDep() {
        if (this.qualifiedDep == null) {
        	this.qualifiedDep = this.getDependencyDef("Qualified");
        }
        return this.qualifiedDep;
    }

    @objid ("9f528095-b90b-4050-bd8c-b4b1f9ffaa3f")
    private static class TagParameterObjectFactory implements ISmObjectFactory {
        @objid ("248cca3c-7309-4c1c-8495-665ad171c738")
        private TagParameterSmClass smClass;

        @objid ("3bbe9dd6-cf71-4f8e-846a-176c1753834a")
        public TagParameterObjectFactory(TagParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("498bb83b-0b70-46d9-88db-6d02c44a6a53")
        @Override
        public ISmObjectData createData() {
            return new TagParameterData(this.smClass);
        }

        @objid ("8ee483a3-8fde-48f5-b032-f09e83a7e87d")
        @Override
        public SmObjectImpl createImpl() {
            return new TagParameterImpl();
        }

    }

    @objid ("11ac1d03-2c0d-42b8-b8a6-5a8087b9f3ca")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("f4a1ba73-44c9-4a58-b307-9f898348ae74")
        public Object getValue(ISmObjectData data) {
            return ((TagParameterData) data).mValue;
        }

        @objid ("e2f1c871-ca03-4a87-8486-d7ac2f0f607d")
        public void setValue(ISmObjectData data, Object value) {
            ((TagParameterData) data).mValue = value;
        }

    }

    @objid ("ccf45f66-35e6-41b0-846b-8b833d63859b")
    public static class AnnotedSmDependency extends SmSingleDependency {
        @objid ("647b20f1-3a5c-45c1-b6d3-60fa9e46489a")
        private SmDependency symetricDep;

        @objid ("48f77bbd-83f8-48f4-be22-5b318e4cf11c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagParameterData) data).mAnnoted;
        }

        @objid ("876051fa-1752-4833-a097-fc039ace8183")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagParameterData) data).mAnnoted = value;
        }

        @objid ("1b3cdc93-bdfa-4a95-b0c7-dad03f734e6b")
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
        @objid ("c13d2510-0c96-490e-ba42-5e64c5b3b361")
        private SmDependency symetricDep;

        @objid ("21b10538-4829-4a49-bae5-22962352a766")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagParameterData) data).mQualified;
        }

        @objid ("f42c25dd-8778-4ea4-982e-6334e79c89b4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagParameterData) data).mQualified = value;
        }

        @objid ("dfdfab07-5b31-4b60-befa-29def09f3cbc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TaggedValueSmClass)this.getTarget()).getQualifierDep();
            }
            return this.symetricDep;
        }

    }

}
