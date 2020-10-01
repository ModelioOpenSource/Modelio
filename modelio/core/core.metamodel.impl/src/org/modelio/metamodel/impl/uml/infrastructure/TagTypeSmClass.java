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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TagTypeData;
import org.modelio.metamodel.impl.uml.infrastructure.TaggedValueSmClass;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
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
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1b205375-7fad-49bb-9e9c-ef69401b364b")
public class TagTypeSmClass extends ModelElementSmClass {
    @objid ("aeb48781-aca9-4b1f-8c98-aa789fb50168")
    private SmAttribute paramNumberAtt;

    @objid ("fb6398db-547b-4b54-b357-b2222b18e8da")
    private SmAttribute isQualifiedAtt;

    @objid ("76dd5a2f-2a5e-4a39-9867-9ab9ef33ed86")
    private SmAttribute belongToPrototypeAtt;

    @objid ("a78fc8ba-9185-431c-ae84-448f00eb6832")
    private SmAttribute isHiddenAtt;

    @objid ("80e2bbed-8137-4167-88e8-c73047da6f2a")
    private SmAttribute labelKeyAtt;

    @objid ("cedce714-a621-4b4c-905f-dad7f7efc34f")
    private SmDependency tagOccurenceDep;

    @objid ("f08861f0-1e68-4bd2-9b19-42558d71b1e6")
    private SmDependency ownerStereotypeDep;

    @objid ("c93dd936-2ea3-4657-8dd2-c0a9081e0b6a")
    private SmDependency ownerReferenceDep;

    @objid ("b89af138-3c1a-4e40-8cf7-06c78baedae8")
    public TagTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("73402421-6fd6-4d77-9d9b-3b0f30474920")
    @Override
    public String getName() {
        return "TagType";
    }

    @objid ("80ceca48-f405-4dbd-89ae-40f573729f3c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9e85fb01-4c24-4795-bb26-83c940e3360e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TagType.class;
    }

    @objid ("ad0a1b6c-2b68-41f8-9007-1988fa44b390")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("381b3c57-2d05-4c19-92b7-1e01e5ef4b9f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("798b27b5-b195-4d2b-bc4a-04bca030c457")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new TagTypeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.paramNumberAtt = new ParamNumberSmAttribute();
        this.paramNumberAtt.init("ParamNumber", this, String.class );
        registerAttribute(this.paramNumberAtt);
        
        this.isQualifiedAtt = new IsQualifiedSmAttribute();
        this.isQualifiedAtt.init("IsQualified", this, Boolean.class );
        registerAttribute(this.isQualifiedAtt);
        
        this.belongToPrototypeAtt = new BelongToPrototypeSmAttribute();
        this.belongToPrototypeAtt.init("BelongToPrototype", this, Boolean.class );
        registerAttribute(this.belongToPrototypeAtt);
        
        this.isHiddenAtt = new IsHiddenSmAttribute();
        this.isHiddenAtt.init("IsHidden", this, Boolean.class );
        registerAttribute(this.isHiddenAtt);
        
        this.labelKeyAtt = new LabelKeySmAttribute();
        this.labelKeyAtt.init("LabelKey", this, String.class );
        registerAttribute(this.labelKeyAtt);
        
        
        // Initialize and register the SmDependency
        this.tagOccurenceDep = new TagOccurenceSmDependency();
        this.tagOccurenceDep.init("TagOccurence", this, metamodel.getMClass(TaggedValue.MQNAME), 0, -1 , SmDirective.SMCDTODELETE, SmDirective.SMCDDYNAMIC);
        registerDependency(this.tagOccurenceDep);
        
        this.ownerStereotypeDep = new OwnerStereotypeSmDependency();
        this.ownerStereotypeDep.init("OwnerStereotype", this, metamodel.getMClass(Stereotype.MQNAME), 0, 1 );
        registerDependency(this.ownerStereotypeDep);
        
        this.ownerReferenceDep = new OwnerReferenceSmDependency();
        this.ownerReferenceDep.init("OwnerReference", this, metamodel.getMClass(MetaclassReference.MQNAME), 0, 1 );
        registerDependency(this.ownerReferenceDep);
    }

    @objid ("be369116-ec84-42ad-824b-7a9eaa3dc989")
    public SmAttribute getParamNumberAtt() {
        if (this.paramNumberAtt == null) {
        	this.paramNumberAtt = this.getAttributeDef("ParamNumber");
        }
        return this.paramNumberAtt;
    }

    @objid ("efd42896-edcf-4c64-b0bc-98805b1af863")
    public SmAttribute getIsQualifiedAtt() {
        if (this.isQualifiedAtt == null) {
        	this.isQualifiedAtt = this.getAttributeDef("IsQualified");
        }
        return this.isQualifiedAtt;
    }

    @objid ("4d9eb10b-bd33-4acc-a47d-b2954ad18575")
    public SmAttribute getBelongToPrototypeAtt() {
        if (this.belongToPrototypeAtt == null) {
        	this.belongToPrototypeAtt = this.getAttributeDef("BelongToPrototype");
        }
        return this.belongToPrototypeAtt;
    }

    @objid ("9cf9461b-6a69-42e5-8ef6-b1fa215f800b")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("adc2d5f1-b6f7-443d-a9da-39c02ab0e32b")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("6eb35f03-29a0-4df2-b34b-ac702d82bdd1")
    public SmDependency getTagOccurenceDep() {
        if (this.tagOccurenceDep == null) {
        	this.tagOccurenceDep = this.getDependencyDef("TagOccurence");
        }
        return this.tagOccurenceDep;
    }

    @objid ("bbe1bd6b-fed9-4d81-befc-031405146e21")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("9b600755-572f-40d7-8264-ba9d3e01f39f")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("7284290d-7007-4f30-9324-c2adabd984eb")
    private static class TagTypeObjectFactory implements ISmObjectFactory {
        @objid ("1d18f692-1923-4cdb-a3c8-b8729852e396")
        private TagTypeSmClass smClass;

        @objid ("ce9639f6-ee11-47db-9937-8b5daf6ff973")
        public TagTypeObjectFactory(TagTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9a1c41c8-1464-42c4-b154-43ce36010564")
        @Override
        public ISmObjectData createData() {
            return new TagTypeData(this.smClass);
        }

        @objid ("97f497d5-9814-4ea9-a6c8-50839a017f01")
        @Override
        public SmObjectImpl createImpl() {
            return new TagTypeImpl();
        }

    }

    @objid ("40a7eaa2-7dc0-4756-94ea-a01da79caa62")
    public static class ParamNumberSmAttribute extends SmAttribute {
        @objid ("1a998220-e78c-4ddb-9bca-67a6def68da6")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mParamNumber;
        }

        @objid ("344df678-5897-405b-833c-93522153b600")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mParamNumber = value;
        }

    }

    @objid ("7682c17f-c8e1-46b7-8706-3908a825b521")
    public static class IsQualifiedSmAttribute extends SmAttribute {
        @objid ("29211f40-936e-4be4-80e8-b47cc2ae60c6")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mIsQualified;
        }

        @objid ("ea913b7b-20ab-4045-9c03-62c31c7ba93c")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mIsQualified = value;
        }

    }

    @objid ("7441603c-b27a-40dd-8768-7b145b413a57")
    public static class BelongToPrototypeSmAttribute extends SmAttribute {
        @objid ("cdf572ce-9caa-4dfa-ab37-1959d8716f13")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mBelongToPrototype;
        }

        @objid ("c3136f2d-a746-4af1-897b-2b4ee135fbdb")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mBelongToPrototype = value;
        }

    }

    @objid ("780583f5-9070-4bad-abed-9141a92de684")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("ad4f91fb-0a25-4bc1-9502-b734ca2c0545")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mIsHidden;
        }

        @objid ("2c27e0cd-db32-4cc4-87d2-d06d4cb4b341")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mIsHidden = value;
        }

    }

    @objid ("5c9e587a-f853-497f-87c1-2074223c1516")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("3b25fe4c-93ac-47e6-b09f-42a6fa50c10d")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mLabelKey;
        }

        @objid ("9909f73d-f77a-4164-bcc7-b603ec37b5c2")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mLabelKey = value;
        }

    }

    @objid ("fe2b3089-3995-4b65-88f1-1afc589c3eb9")
    public static class TagOccurenceSmDependency extends SmMultipleDependency {
        @objid ("d2b4ff81-2c60-4188-851a-e4792e032ae8")
        private SmDependency symetricDep;

        @objid ("1f79fe2e-8a18-439e-b105-afd934630269")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TagTypeData)data).mTagOccurence != null)? ((TagTypeData)data).mTagOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("2c8d54bb-da37-4884-b706-f7343bd2de7f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TagTypeData) data).mTagOccurence = values;
        }

        @objid ("f2889aae-1bcc-478b-ae24-e5e7c1cc1189")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TaggedValueSmClass)this.getTarget()).getDefinitionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("cf6cb4b6-6d78-4adb-ac09-a27c1804a323")
    public static class OwnerStereotypeSmDependency extends SmSingleDependency {
        @objid ("cc82a0ef-1bbe-459d-8d13-ff909268019a")
        private SmDependency symetricDep;

        @objid ("ccac92eb-7f8f-496a-9bad-cc254d9cfde7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagTypeData) data).mOwnerStereotype;
        }

        @objid ("00432a5b-c999-4155-9034-92080e09324f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagTypeData) data).mOwnerStereotype = value;
        }

        @objid ("40e92800-c780-4778-923c-d23f40b2563b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StereotypeSmClass)this.getTarget()).getDefinedTagTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0b54deaa-b02d-46ab-84d4-a7dbaa85792c")
    public static class OwnerReferenceSmDependency extends SmSingleDependency {
        @objid ("231d85b1-ef56-4d50-bae8-2f7752f8e353")
        private SmDependency symetricDep;

        @objid ("fdb47865-f22a-4e3d-a4a8-25e620b406b1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagTypeData) data).mOwnerReference;
        }

        @objid ("59053208-0906-4580-9daf-c2bc15ca1b41")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagTypeData) data).mOwnerReference = value;
        }

        @objid ("761da125-df98-4c4d-a374-1c4a614f37fd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedTagTypeDep();
            }
            return this.symetricDep;
        }

    }

}
