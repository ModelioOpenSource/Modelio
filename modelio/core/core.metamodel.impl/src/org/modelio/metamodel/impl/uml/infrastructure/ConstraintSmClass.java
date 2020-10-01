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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ConstraintData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("aacebf16-44ba-45c9-b3f5-a9fd972dba6f")
public class ConstraintSmClass extends UmlModelElementSmClass {
    @objid ("aa057396-9fc8-4c77-8c4f-8cb6adb267e0")
    private SmAttribute baseClassAtt;

    @objid ("8ea576e9-2f6c-4c6e-8782-1fbfa6d242e7")
    private SmAttribute bodyAtt;

    @objid ("19aa1270-387f-4b6d-a735-1e53d236c9f1")
    private SmAttribute languageAtt;

    @objid ("41a5c99e-603b-437c-bd4d-c419f91b6151")
    private SmDependency constrainedElementDep;

    @objid ("7c7e3824-6c4b-46ed-83ca-a9e403e60638")
    public ConstraintSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("71bc161e-a12d-427f-84c5-cd2f72cae33a")
    @Override
    public String getName() {
        return "Constraint";
    }

    @objid ("5060f22a-78ce-4a2c-a674-f05e371a4f14")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("59b9aa78-6827-48b3-a631-f581da68fa3f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Constraint.class;
    }

    @objid ("3c4128ff-db44-436f-ae98-a1ec4ea0ff46")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5ebac175-f8dd-480f-92dc-a6b304dfeca8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("5d7a87a9-b5f3-4f21-bb4a-e76fcc8f63b9")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ConstraintObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.baseClassAtt = new BaseClassSmAttribute();
        this.baseClassAtt.init("BaseClass", this, String.class );
        registerAttribute(this.baseClassAtt);
        
        this.bodyAtt = new BodySmAttribute();
        this.bodyAtt.init("Body", this, String.class );
        registerAttribute(this.bodyAtt);
        
        this.languageAtt = new LanguageSmAttribute();
        this.languageAtt.init("Language", this, String.class );
        registerAttribute(this.languageAtt);
        
        
        // Initialize and register the SmDependency
        this.constrainedElementDep = new ConstrainedElementSmDependency();
        this.constrainedElementDep.init("ConstrainedElement", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.constrainedElementDep);
    }

    @objid ("b3d16337-f58d-4d13-90bc-49029d2528c8")
    public SmAttribute getBaseClassAtt() {
        if (this.baseClassAtt == null) {
        	this.baseClassAtt = this.getAttributeDef("BaseClass");
        }
        return this.baseClassAtt;
    }

    @objid ("8021a69a-a497-4afe-9281-63d47db361dc")
    public SmAttribute getBodyAtt() {
        if (this.bodyAtt == null) {
        	this.bodyAtt = this.getAttributeDef("Body");
        }
        return this.bodyAtt;
    }

    @objid ("83b9ec53-1433-4754-bf9c-71e67f6db740")
    public SmAttribute getLanguageAtt() {
        if (this.languageAtt == null) {
        	this.languageAtt = this.getAttributeDef("Language");
        }
        return this.languageAtt;
    }

    @objid ("078e0ee1-c25b-48b4-8833-aea02d27fdc8")
    public SmDependency getConstrainedElementDep() {
        if (this.constrainedElementDep == null) {
        	this.constrainedElementDep = this.getDependencyDef("ConstrainedElement");
        }
        return this.constrainedElementDep;
    }

    @objid ("72c513e4-ec83-4c95-96bc-3942b7bed3e6")
    private static class ConstraintObjectFactory implements ISmObjectFactory {
        @objid ("701e01d1-ad5f-4ac8-a538-31f33962f0ce")
        private ConstraintSmClass smClass;

        @objid ("9a7ed794-e169-4ce9-b0ca-aa01c5aaa27c")
        public ConstraintObjectFactory(ConstraintSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f3d83a4b-e18b-466a-b9c5-1919c62a27ce")
        @Override
        public ISmObjectData createData() {
            return new ConstraintData(this.smClass);
        }

        @objid ("9a78a9f7-b675-490c-8447-80f84fc0d70f")
        @Override
        public SmObjectImpl createImpl() {
            return new ConstraintImpl();
        }

    }

    @objid ("0e4e0a11-8676-4f76-b989-d2044060e69a")
    public static class BaseClassSmAttribute extends SmAttribute {
        @objid ("5bbd83d5-63f4-4128-a1af-2db185e13c6e")
        public Object getValue(ISmObjectData data) {
            return ((ConstraintData) data).mBaseClass;
        }

        @objid ("a8942ba9-c886-474f-b420-158376ae8ebc")
        public void setValue(ISmObjectData data, Object value) {
            ((ConstraintData) data).mBaseClass = value;
        }

    }

    @objid ("972c98a9-69cd-42e7-ace3-64d6004c616b")
    public static class BodySmAttribute extends SmAttribute {
        @objid ("ed8625e4-82a0-4fea-b9aa-4e240324d8bb")
        public Object getValue(ISmObjectData data) {
            return ((ConstraintData) data).mBody;
        }

        @objid ("b0586d49-1968-4601-8bfe-96e8ae42ec55")
        public void setValue(ISmObjectData data, Object value) {
            ((ConstraintData) data).mBody = value;
        }

    }

    @objid ("71973d15-d883-4d44-a764-5b961e8afe7e")
    public static class LanguageSmAttribute extends SmAttribute {
        @objid ("a759aa4c-622e-4b78-98da-28291c730d9e")
        public Object getValue(ISmObjectData data) {
            return ((ConstraintData) data).mLanguage;
        }

        @objid ("8c9fe8d0-b1ea-47cf-ae85-2be4291fb589")
        public void setValue(ISmObjectData data, Object value) {
            ((ConstraintData) data).mLanguage = value;
        }

    }

    @objid ("a1efa7f4-6240-485c-b7e3-cb1d1c2f3473")
    public static class ConstrainedElementSmDependency extends SmMultipleDependency {
        @objid ("24c8e29e-c376-4831-b6c6-fcfdf7577c0d")
        private SmDependency symetricDep;

        @objid ("8f2480ce-c0c2-4f2a-b8c8-4d91e7215043")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ConstraintData)data).mConstrainedElement != null)? ((ConstraintData)data).mConstrainedElement:SmMultipleDependency.EMPTY;
        }

        @objid ("9df644c3-fd10-4e46-9f38-505300cc9bc8")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ConstraintData) data).mConstrainedElement = values;
        }

        @objid ("98d6d960-32cf-48ad-9544-588814099a1a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getConstraintDefinitionDep();
            }
            return this.symetricDep;
        }

    }

}
