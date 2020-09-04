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
    @objid ("ea759046-e882-487b-8a74-1f2d6cb6d2c4")
    private SmAttribute paramNumberAtt;

    @objid ("a76bde52-a09f-4132-8475-e5eed2af7fe0")
    private SmAttribute isQualifiedAtt;

    @objid ("89ecf32e-06fb-4980-b75f-43d8b5b25a87")
    private SmAttribute belongToPrototypeAtt;

    @objid ("19dd926d-f8f3-4d81-a4b2-06f88c7475f5")
    private SmAttribute isHiddenAtt;

    @objid ("2f75a021-024c-44a7-93be-2ef3b6c7c07c")
    private SmAttribute labelKeyAtt;

    @objid ("e49da713-1be3-4aec-b16f-146c54a7dd67")
    private SmDependency tagOccurenceDep;

    @objid ("25b4c20d-e918-4f37-9383-e33d7900d5c6")
    private SmDependency ownerStereotypeDep;

    @objid ("bc32c51c-fbeb-497c-9392-85e42f5d38a0")
    private SmDependency ownerReferenceDep;

    @objid ("6fdf7d44-ffd7-41b1-a004-f9018356b905")
    public TagTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("37eff22c-0956-4bfe-8cb6-be81472aef05")
    @Override
    public String getName() {
        return "TagType";
    }

    @objid ("383f6b97-e85c-42c1-9bdb-8c63a8d8d531")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7b2dab8f-42df-4687-8e70-268f83c3df48")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TagType.class;
    }

    @objid ("e697b6da-b6cc-4df9-bd52-dc81c21c37d1")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4fc7bfe6-00a3-4d5a-b8cc-0e88dd0e384e")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("bcbbceed-9496-4933-98d5-a09eeb75b395")
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

    @objid ("1ababca7-d81f-4d20-a454-bd68228cfa32")
    public SmAttribute getParamNumberAtt() {
        if (this.paramNumberAtt == null) {
        	this.paramNumberAtt = this.getAttributeDef("ParamNumber");
        }
        return this.paramNumberAtt;
    }

    @objid ("2ab0f350-0a1c-423b-a1d9-8f28927e2dc3")
    public SmAttribute getIsQualifiedAtt() {
        if (this.isQualifiedAtt == null) {
        	this.isQualifiedAtt = this.getAttributeDef("IsQualified");
        }
        return this.isQualifiedAtt;
    }

    @objid ("cf54070c-3d37-4912-a418-5baf064b9128")
    public SmAttribute getBelongToPrototypeAtt() {
        if (this.belongToPrototypeAtt == null) {
        	this.belongToPrototypeAtt = this.getAttributeDef("BelongToPrototype");
        }
        return this.belongToPrototypeAtt;
    }

    @objid ("288b569b-35f5-4e07-a4d6-bd4a0c1aa222")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("3ac65f2a-1259-4e54-bdd3-d466510d0878")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("ebbcd3f7-87a5-4b3c-bece-fd79f36df11e")
    public SmDependency getTagOccurenceDep() {
        if (this.tagOccurenceDep == null) {
        	this.tagOccurenceDep = this.getDependencyDef("TagOccurence");
        }
        return this.tagOccurenceDep;
    }

    @objid ("a9f3cd11-ce2d-4b6f-a179-864723bbfe36")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("97a63681-4f09-4edc-9fae-a2c16b9eec54")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("7284290d-7007-4f30-9324-c2adabd984eb")
    private static class TagTypeObjectFactory implements ISmObjectFactory {
        @objid ("18b59c45-56d5-4f9a-b0ea-2ab757caae55")
        private TagTypeSmClass smClass;

        @objid ("f65736a9-09ad-42fa-9f37-93a65d1eb27e")
        public TagTypeObjectFactory(TagTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("190ce400-8c02-48c8-a2da-5a655eb36310")
        @Override
        public ISmObjectData createData() {
            return new TagTypeData(this.smClass);
        }

        @objid ("9446945b-e2c0-4ba2-b92f-76ed7bd92afd")
        @Override
        public SmObjectImpl createImpl() {
            return new TagTypeImpl();
        }

    }

    @objid ("40a7eaa2-7dc0-4756-94ea-a01da79caa62")
    public static class ParamNumberSmAttribute extends SmAttribute {
        @objid ("73018b4c-aad0-40a3-b99e-de2094a33edd")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mParamNumber;
        }

        @objid ("9c4181d1-b576-44ba-a875-56ea3b612f38")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mParamNumber = value;
        }

    }

    @objid ("7682c17f-c8e1-46b7-8706-3908a825b521")
    public static class IsQualifiedSmAttribute extends SmAttribute {
        @objid ("55614c53-621b-425b-9bf2-a820dccd8944")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mIsQualified;
        }

        @objid ("aea5386a-516d-45ee-b30b-3154af8e18ff")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mIsQualified = value;
        }

    }

    @objid ("7441603c-b27a-40dd-8768-7b145b413a57")
    public static class BelongToPrototypeSmAttribute extends SmAttribute {
        @objid ("0ab9b108-855f-419c-96ad-c768449fe20d")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mBelongToPrototype;
        }

        @objid ("d3e5e5ce-cf8d-470a-b834-0f93d0ca2dea")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mBelongToPrototype = value;
        }

    }

    @objid ("780583f5-9070-4bad-abed-9141a92de684")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("0b456e4f-3e1f-4345-be47-7f3e3c6ca6a5")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mIsHidden;
        }

        @objid ("c792a17c-4126-4125-895a-60451c4c71ed")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mIsHidden = value;
        }

    }

    @objid ("5c9e587a-f853-497f-87c1-2074223c1516")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("e6369ba8-8cc6-4c06-acd4-cecbaedac1cc")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mLabelKey;
        }

        @objid ("c4d4a44b-ffbe-4bf8-985d-d3fdb13c576f")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mLabelKey = value;
        }

    }

    @objid ("fe2b3089-3995-4b65-88f1-1afc589c3eb9")
    public static class TagOccurenceSmDependency extends SmMultipleDependency {
        @objid ("8704b7f0-2465-4dcc-8e3a-ce6e03499f31")
        private SmDependency symetricDep;

        @objid ("d84ab8b9-7fd7-423e-b796-86371d2e4fc8")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TagTypeData)data).mTagOccurence != null)? ((TagTypeData)data).mTagOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("7622b83d-68f1-41f9-82d9-0f12a605c838")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TagTypeData) data).mTagOccurence = values;
        }

        @objid ("b20d82b4-1372-4135-9ea1-5a9825ce6678")
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
        @objid ("7db7dc85-9630-4715-9221-498cebb97c40")
        private SmDependency symetricDep;

        @objid ("9ae8b91f-4618-473f-8d3b-ed72859a4232")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagTypeData) data).mOwnerStereotype;
        }

        @objid ("75de3de0-cbb4-4042-b236-1ed5040b2ca1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagTypeData) data).mOwnerStereotype = value;
        }

        @objid ("08dd6dd2-a56d-4d8c-808f-3e3f94351b42")
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
        @objid ("14676d11-8126-4f3a-85f5-092a33c6a372")
        private SmDependency symetricDep;

        @objid ("c1ff4487-ddf7-4e28-92d8-0afde2023eac")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagTypeData) data).mOwnerReference;
        }

        @objid ("380b770c-bc05-426f-b66a-cde037714c57")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagTypeData) data).mOwnerReference = value;
        }

        @objid ("1d213046-4499-41e8-9d4c-80378259c8b5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedTagTypeDep();
            }
            return this.symetricDep;
        }

    }

}
