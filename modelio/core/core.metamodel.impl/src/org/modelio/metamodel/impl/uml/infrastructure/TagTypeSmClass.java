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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
    @objid ("d49f7e4c-8e2c-4dee-a5c6-06b3006ced68")
    private SmAttribute paramNumberAtt;

    @objid ("3911a2d3-a81c-4b89-8614-1664e4c45959")
    private SmAttribute isQualifiedAtt;

    @objid ("b057bfe1-7cad-49cd-9dbd-36b3b480860f")
    private SmAttribute belongToPrototypeAtt;

    @objid ("9fd2c349-4e75-4f50-bdc0-0df827a32805")
    private SmAttribute isHiddenAtt;

    @objid ("a2103a92-b32f-4e3f-a3b6-90763e805c9b")
    private SmAttribute labelKeyAtt;

    @objid ("34c54eba-23a9-4eea-8629-ffc4bd4352f3")
    private SmDependency tagOccurenceDep;

    @objid ("b081341a-4be4-4348-873c-09cb461dbc9e")
    private SmDependency ownerStereotypeDep;

    @objid ("8ec2c0a2-3a39-4133-b56d-7a675da7896c")
    private SmDependency ownerReferenceDep;

    @objid ("102bb096-a078-4af0-8f8e-68905800b093")
    public  TagTypeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5ede56f1-8eb1-4df8-89b0-44f748c70b99")
    @Override
    public String getName() {
        return "TagType";
        
    }

    @objid ("82ceb2ed-46e4-4c03-b936-ad3bac057b80")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0a961a36-0daa-47ba-8a4e-04cfd4e99574")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TagType.class;
        
    }

    @objid ("4f2729fb-5092-44c0-b144-4046c72fd970")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("be9c6d74-6f37-4b01-9a5a-38c2b0b673df")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("2459662c-db4a-43db-9aeb-2b1d678f22f0")
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

    @objid ("25f0fef2-53d3-4c7d-8bbd-a34b63307341")
    public SmAttribute getParamNumberAtt() {
        if (this.paramNumberAtt == null) {
        	this.paramNumberAtt = this.getAttributeDef("ParamNumber");
        }
        return this.paramNumberAtt;
    }

    @objid ("ae1be0a5-952b-4dfe-beee-292cdb7563a8")
    public SmAttribute getIsQualifiedAtt() {
        if (this.isQualifiedAtt == null) {
        	this.isQualifiedAtt = this.getAttributeDef("IsQualified");
        }
        return this.isQualifiedAtt;
    }

    @objid ("1d2362b2-b1a8-4822-9e44-7d6a765234ba")
    public SmAttribute getBelongToPrototypeAtt() {
        if (this.belongToPrototypeAtt == null) {
        	this.belongToPrototypeAtt = this.getAttributeDef("BelongToPrototype");
        }
        return this.belongToPrototypeAtt;
    }

    @objid ("0dcd2cdf-867d-41f9-9dc6-ac4bc1a42ced")
    public SmAttribute getIsHiddenAtt() {
        if (this.isHiddenAtt == null) {
        	this.isHiddenAtt = this.getAttributeDef("IsHidden");
        }
        return this.isHiddenAtt;
    }

    @objid ("1cc24b00-f029-4635-8c84-75aaa35d1a26")
    public SmAttribute getLabelKeyAtt() {
        if (this.labelKeyAtt == null) {
        	this.labelKeyAtt = this.getAttributeDef("LabelKey");
        }
        return this.labelKeyAtt;
    }

    @objid ("c99ad1f7-6464-4b5e-ae5f-034ab32d7124")
    public SmDependency getTagOccurenceDep() {
        if (this.tagOccurenceDep == null) {
        	this.tagOccurenceDep = this.getDependencyDef("TagOccurence");
        }
        return this.tagOccurenceDep;
    }

    @objid ("b48ed816-8d7f-42c6-a5e3-a8dae6b2e39a")
    public SmDependency getOwnerStereotypeDep() {
        if (this.ownerStereotypeDep == null) {
        	this.ownerStereotypeDep = this.getDependencyDef("OwnerStereotype");
        }
        return this.ownerStereotypeDep;
    }

    @objid ("ec130e8f-da47-4cfc-a564-0834925e8fe7")
    public SmDependency getOwnerReferenceDep() {
        if (this.ownerReferenceDep == null) {
        	this.ownerReferenceDep = this.getDependencyDef("OwnerReference");
        }
        return this.ownerReferenceDep;
    }

    @objid ("7284290d-7007-4f30-9324-c2adabd984eb")
    private static class TagTypeObjectFactory implements ISmObjectFactory {
        @objid ("87f31f7e-8330-49b6-b4bf-53412baeda00")
        private TagTypeSmClass smClass;

        @objid ("b793e085-e0a7-41d8-a539-30dbd6675deb")
        public  TagTypeObjectFactory(TagTypeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d4858487-efff-4525-92c3-3f529010a5cb")
        @Override
        public ISmObjectData createData() {
            return new TagTypeData(this.smClass);
        }

        @objid ("e4a24d49-d644-4c12-8022-db9c25e4289c")
        @Override
        public SmObjectImpl createImpl() {
            return new TagTypeImpl();
        }

    }

    @objid ("40a7eaa2-7dc0-4756-94ea-a01da79caa62")
    public static class ParamNumberSmAttribute extends SmAttribute {
        @objid ("2c24611c-dcf5-45c7-9ac9-35a4fba21346")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mParamNumber;
        }

        @objid ("1329693c-2bd7-4650-b758-59ae57824b4b")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mParamNumber = value;
        }

    }

    @objid ("7682c17f-c8e1-46b7-8706-3908a825b521")
    public static class IsQualifiedSmAttribute extends SmAttribute {
        @objid ("0f5150ae-18e0-4dc0-9e5f-72f379b1368d")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mIsQualified;
        }

        @objid ("e515991f-b945-481c-8cfc-009585e575c8")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mIsQualified = value;
        }

    }

    @objid ("7441603c-b27a-40dd-8768-7b145b413a57")
    public static class BelongToPrototypeSmAttribute extends SmAttribute {
        @objid ("eb7c8326-00a4-4b67-be72-d4d2f302b271")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mBelongToPrototype;
        }

        @objid ("23444efb-fed9-4132-b61b-3c2b7383d8bd")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mBelongToPrototype = value;
        }

    }

    @objid ("780583f5-9070-4bad-abed-9141a92de684")
    public static class IsHiddenSmAttribute extends SmAttribute {
        @objid ("2e0a2604-caad-4c11-a147-86e9215217b9")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mIsHidden;
        }

        @objid ("ff1cedd7-3728-4f7d-9243-3406b113f241")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mIsHidden = value;
        }

    }

    @objid ("5c9e587a-f853-497f-87c1-2074223c1516")
    public static class LabelKeySmAttribute extends SmAttribute {
        @objid ("4f748b4b-1521-445b-98ed-940b8f0a3b7f")
        public Object getValue(ISmObjectData data) {
            return ((TagTypeData) data).mLabelKey;
        }

        @objid ("f0e26505-517f-4000-a1fb-13d6c445b6b5")
        public void setValue(ISmObjectData data, Object value) {
            ((TagTypeData) data).mLabelKey = value;
        }

    }

    @objid ("fe2b3089-3995-4b65-88f1-1afc589c3eb9")
    public static class TagOccurenceSmDependency extends SmMultipleDependency {
        @objid ("0e4e0f32-ca31-4b67-a865-580ed0d1c021")
        private SmDependency symetricDep;

        @objid ("a63e4417-b29f-48fa-bd7c-a72d4a3b9a5a")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TagTypeData)data).mTagOccurence != null)? ((TagTypeData)data).mTagOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("6c3af791-c325-4bc3-9d4d-ac1114f8c2d9")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TagTypeData) data).mTagOccurence = values;
            
        }

        @objid ("6a8ae34f-b146-4c9b-a7ed-7639ec7eefcb")
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
        @objid ("95e7e899-39bd-441c-83c7-cf7cf37c6f1e")
        private SmDependency symetricDep;

        @objid ("abc57ef2-cf44-48e8-9504-48d5c3320029")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagTypeData) data).mOwnerStereotype;
        }

        @objid ("dca095c4-573b-414a-a48a-cc5d0b497079")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagTypeData) data).mOwnerStereotype = value;
        }

        @objid ("ac645ac4-40e0-4416-9204-367e11b5d976")
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
        @objid ("3a53b847-0ce9-4e87-9215-460d867d8df2")
        private SmDependency symetricDep;

        @objid ("4a277d39-59ea-47cb-ab12-d754ad43d4b2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TagTypeData) data).mOwnerReference;
        }

        @objid ("09f3baf8-6e94-4e4d-ac1f-526570a6a5e8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TagTypeData) data).mOwnerReference = value;
        }

        @objid ("ed15d2bc-cfd0-4509-b653-cd3164d0bb28")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MetaclassReferenceSmClass)this.getTarget()).getDefinedTagTypeDep();
            }
            return this.symetricDep;
            
        }

    }

}
