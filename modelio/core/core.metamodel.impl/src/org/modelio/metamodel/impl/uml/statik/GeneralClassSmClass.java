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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExceptionHandlerSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassData;
import org.modelio.metamodel.impl.uml.statik.ParameterSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Parameter;
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

@objid ("68b143c4-1f67-48f8-9d81-057416dd3db1")
public class GeneralClassSmClass extends ClassifierSmClass {
    @objid ("1dd5c0ea-f612-4589-8925-7b2a5ee4e49f")
    private SmAttribute isElementaryAtt;

    @objid ("82f0bddc-1ecb-4e55-8247-a89591a4752e")
    private SmDependency occurenceDep;

    @objid ("4e8661c2-37b5-4106-a3b1-396ceb6d7c8a")
    private SmDependency exceptionInputDep;

    @objid ("609f44d5-ac7e-4a38-a7b8-41e28b0e9d77")
    private SmDependency objectDep;

    @objid ("e922bd7f-04ee-44a1-9d64-b6fa365ea7c4")
    private SmDependency sRepresentationDep;

    @objid ("5e17b728-b0a1-4b0d-8604-a6177ab2279f")
    private SmDependency occurenceObjectNodeDep;

    @objid ("2f91a452-f59a-4d19-8570-1f6031c83a33")
    public GeneralClassSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("bf213e78-5b49-4bc9-b86f-a7bc111b748a")
    @Override
    public String getName() {
        return "GeneralClass";
    }

    @objid ("835fff8e-9175-4aef-a3e3-cc622a87c2f1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("215e30f2-83d1-42d5-9320-e8ac12cda8f6")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return GeneralClass.class;
    }

    @objid ("f512fc9e-32a1-4cce-b348-e38250c04984")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("276630a7-0238-47c3-a1f6-4fedf7b460ed")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("88f7abdc-dd51-4162-8361-c0cf19e8d052")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Classifier.MQNAME);
        this.registerFactory(new GeneralClassObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isElementaryAtt = new IsElementarySmAttribute();
        this.isElementaryAtt.init("IsElementary", this, Boolean.class );
        registerAttribute(this.isElementaryAtt);
        
        
        // Initialize and register the SmDependency
        this.occurenceDep = new OccurenceSmDependency();
        this.occurenceDep.init("Occurence", this, metamodel.getMClass(Parameter.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.occurenceDep);
        
        this.exceptionInputDep = new ExceptionInputSmDependency();
        this.exceptionInputDep.init("ExceptionInput", this, metamodel.getMClass(ExceptionHandler.MQNAME), 1, 1 );
        registerDependency(this.exceptionInputDep);
        
        this.objectDep = new ObjectSmDependency();
        this.objectDep.init("Object", this, metamodel.getMClass(Attribute.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.objectDep);
        
        this.sRepresentationDep = new SRepresentationSmDependency();
        this.sRepresentationDep.init("SRepresentation", this, metamodel.getMClass(Signal.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.sRepresentationDep);
        
        this.occurenceObjectNodeDep = new OccurenceObjectNodeSmDependency();
        this.occurenceObjectNodeDep.init("OccurenceObjectNode", this, metamodel.getMClass(ObjectNode.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC);
        registerDependency(this.occurenceObjectNodeDep);
    }

    @objid ("4bf32001-aa23-4dde-8a8d-7444704e8de7")
    public SmAttribute getIsElementaryAtt() {
        if (this.isElementaryAtt == null) {
        	this.isElementaryAtt = this.getAttributeDef("IsElementary");
        }
        return this.isElementaryAtt;
    }

    @objid ("6c07bd66-8bfd-442a-9dab-2f975b9e82ba")
    public SmDependency getOccurenceDep() {
        if (this.occurenceDep == null) {
        	this.occurenceDep = this.getDependencyDef("Occurence");
        }
        return this.occurenceDep;
    }

    @objid ("b8b47745-acff-4372-abe1-a40f2338d5f3")
    public SmDependency getExceptionInputDep() {
        if (this.exceptionInputDep == null) {
        	this.exceptionInputDep = this.getDependencyDef("ExceptionInput");
        }
        return this.exceptionInputDep;
    }

    @objid ("bb2e3ca9-3c00-4f50-9349-fb17afb71c26")
    public SmDependency getObjectDep() {
        if (this.objectDep == null) {
        	this.objectDep = this.getDependencyDef("Object");
        }
        return this.objectDep;
    }

    @objid ("eebd8192-9cec-4010-aa2a-d828aac66f1b")
    public SmDependency getSRepresentationDep() {
        if (this.sRepresentationDep == null) {
        	this.sRepresentationDep = this.getDependencyDef("SRepresentation");
        }
        return this.sRepresentationDep;
    }

    @objid ("d071f623-f6c5-46fd-b806-95789f73d535")
    public SmDependency getOccurenceObjectNodeDep() {
        if (this.occurenceObjectNodeDep == null) {
        	this.occurenceObjectNodeDep = this.getDependencyDef("OccurenceObjectNode");
        }
        return this.occurenceObjectNodeDep;
    }

    @objid ("a11c283b-d830-4958-897b-6a0b5c598402")
    private static class GeneralClassObjectFactory implements ISmObjectFactory {
        @objid ("eaa3adcb-f12e-4961-af71-9e7a04c17122")
        private GeneralClassSmClass smClass;

        @objid ("d8b373b0-29f4-4437-8781-35ea09b234d5")
        public GeneralClassObjectFactory(GeneralClassSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("44ddeeb2-e28d-4c7c-8701-62972c16ddfa")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("02743d2d-6e9d-4810-8074-c81f4d8ecb2a")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("10f28c33-f4e9-4615-8275-d645bde9897a")
    public static class IsElementarySmAttribute extends SmAttribute {
        @objid ("3863e769-e99d-4652-8dee-7715f66e3d1f")
        public Object getValue(ISmObjectData data) {
            return ((GeneralClassData) data).mIsElementary;
        }

        @objid ("4d5f403c-9179-4ee1-b9a9-2bda59dfbebc")
        public void setValue(ISmObjectData data, Object value) {
            ((GeneralClassData) data).mIsElementary = value;
        }

    }

    @objid ("93566c25-d2f9-4da6-b1b8-f48f70dc5f60")
    public static class OccurenceSmDependency extends SmMultipleDependency {
        @objid ("be93b54d-31e4-4158-80d3-1d420531009e")
        private SmDependency symetricDep;

        @objid ("a587f9dd-230e-4565-8bd9-990df86cda02")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((GeneralClassData)data).mOccurence != null)? ((GeneralClassData)data).mOccurence:SmMultipleDependency.EMPTY;
        }

        @objid ("d5380393-fdf4-486e-afe8-efb118a9c1fe")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((GeneralClassData) data).mOccurence = values;
        }

        @objid ("23069d21-08b7-4c4a-b9c7-92d8bd3c8a4f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ParameterSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("212a8494-f590-4db3-a653-edc8568c3320")
    public static class ExceptionInputSmDependency extends SmSingleDependency {
        @objid ("2b9f2cb0-de5d-4ccd-aaaf-d88f89383604")
        private SmDependency symetricDep;

        @objid ("79a33741-77f9-491b-9ea6-527635ba768b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GeneralClassData) data).mExceptionInput;
        }

        @objid ("542a327f-51c8-4133-be48-d4722b353de2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GeneralClassData) data).mExceptionInput = value;
        }

        @objid ("a074adb5-eff1-4402-9eff-8eb01e25445e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExceptionHandlerSmClass)this.getTarget()).getExceptionTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("f2a2b418-22ae-42ed-a08e-ffdac8153dab")
    public static class ObjectSmDependency extends SmMultipleDependency {
        @objid ("81ad825b-caa3-4d0e-87cc-c4e2b01d4303")
        private SmDependency symetricDep;

        @objid ("c17a7d19-e22f-44ea-ac10-d315248d6a04")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((GeneralClassData)data).mObject != null)? ((GeneralClassData)data).mObject:SmMultipleDependency.EMPTY;
        }

        @objid ("2ddcf886-b30e-4861-8adb-6d281e380324")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((GeneralClassData) data).mObject = values;
        }

        @objid ("ecf29a23-6a69-4393-bf4c-a22a0af1613c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AttributeSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("25bdde2b-3763-4477-a05c-e1dc912cf5c3")
    public static class SRepresentationSmDependency extends SmMultipleDependency {
        @objid ("4c8ed57e-697d-4532-a91d-3f7d3d369597")
        private SmDependency symetricDep;

        @objid ("41a1c96b-41ff-42f5-a079-2466d7634db3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((GeneralClassData)data).mSRepresentation != null)? ((GeneralClassData)data).mSRepresentation:SmMultipleDependency.EMPTY;
        }

        @objid ("9c2a4294-a7ff-421c-9569-041df7318767")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((GeneralClassData) data).mSRepresentation = values;
        }

        @objid ("f1f85716-1c26-4ad2-bf02-a7dba1501d08")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getBaseDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("56b6af7a-3f00-406b-87c5-05724138b775")
    public static class OccurenceObjectNodeSmDependency extends SmMultipleDependency {
        @objid ("e2ba976c-1913-4f31-a520-082c046087f9")
        private SmDependency symetricDep;

        @objid ("4ea332e4-ee32-4a9d-8469-5f2064cfcaff")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((GeneralClassData)data).mOccurenceObjectNode != null)? ((GeneralClassData)data).mOccurenceObjectNode:SmMultipleDependency.EMPTY;
        }

        @objid ("90aaac2c-3a03-40c7-9ecc-f7a9f9427584")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((GeneralClassData) data).mOccurenceObjectNode = values;
        }

        @objid ("cf8e8f2f-1af9-4447-a2d3-fd8548f09e84")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ObjectNodeSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
        }

    }

}
