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
import org.modelio.metamodel.impl.uml.behavior.activityModel.PinSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorParameterSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.impl.uml.statik.ParameterData;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.ParameterEffectKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
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

@objid ("6b9384d1-347f-4630-884c-d89cf3f86843")
public class ParameterSmClass extends UmlModelElementSmClass {
    @objid ("5f80897a-d131-47af-b7ee-433f0f58aba9")
    private SmAttribute parameterPassingAtt;

    @objid ("0ce09606-c1bf-4f7a-81b2-a67bbaf0bd35")
    private SmAttribute multiplicityMinAtt;

    @objid ("bd47aea2-2c26-4449-bbc2-625debd1a099")
    private SmAttribute multiplicityMaxAtt;

    @objid ("45bea4be-9fe1-420f-9140-2cf28a38cce8")
    private SmAttribute typeConstraintAtt;

    @objid ("09f47258-18b2-42b8-80bf-700911d5e04c")
    private SmAttribute defaultValueAtt;

    @objid ("c3d9b477-0089-487f-8dac-fed47fb3ce85")
    private SmAttribute isOrderedAtt;

    @objid ("03e9d8af-5a6a-409e-89c6-79111bbdc938")
    private SmAttribute isUniqueAtt;

    @objid ("4890000b-31bd-4621-b3db-959941291489")
    private SmAttribute isExceptionAtt;

    @objid ("9cc910d6-434c-492d-bf2e-1e9645b1d03a")
    private SmAttribute isStreamAtt;

    @objid ("66223bb2-7ab5-42a4-a06f-a0addd5193b8")
    private SmAttribute effectAtt;

    @objid ("0e75f02b-81e6-4b57-bd71-38ffe06b9d68")
    private SmDependency typeDep;

    @objid ("44c8cba4-1180-4cca-ad03-72de5df509e7")
    private SmDependency composedDep;

    @objid ("15df270a-da38-41e0-a9ce-69aa55f2effd")
    private SmDependency matchingDep;

    @objid ("57163520-1973-4476-9601-a11c6c950a4b")
    private SmDependency sRepresentationDep;

    @objid ("896a94d9-f76f-4a71-a1b0-a9243df3d543")
    private SmDependency returnedDep;

    @objid ("5827ba82-f3c0-4079-9dd4-0d5219341c34")
    private SmDependency behaviorParamDep;

    @objid ("d38683f1-aba9-473d-b57b-ab936d39672f")
    public ParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9be91c6b-c43b-4644-87e6-6dd9320beab9")
    @Override
    public String getName() {
        return "Parameter";
    }

    @objid ("4fd9ba4d-78b0-4dca-9da7-61019e9ea46b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("74b4d9c8-d12a-4936-aaf3-766319d24f4f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Parameter.class;
    }

    @objid ("d152e6f6-5f7c-44aa-951a-6715e3f99606")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("a4f10d71-c72c-48c7-b162-2237bd78f0d2")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("137f061f-8411-45bc-8dac-8a0affc7a0f8")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ParameterObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.parameterPassingAtt = new ParameterPassingSmAttribute();
        this.parameterPassingAtt.init("ParameterPassing", this, PassingMode.class );
        registerAttribute(this.parameterPassingAtt);
        
        this.multiplicityMinAtt = new MultiplicityMinSmAttribute();
        this.multiplicityMinAtt.init("MultiplicityMin", this, String.class );
        registerAttribute(this.multiplicityMinAtt);
        
        this.multiplicityMaxAtt = new MultiplicityMaxSmAttribute();
        this.multiplicityMaxAtt.init("MultiplicityMax", this, String.class );
        registerAttribute(this.multiplicityMaxAtt);
        
        this.typeConstraintAtt = new TypeConstraintSmAttribute();
        this.typeConstraintAtt.init("TypeConstraint", this, String.class );
        registerAttribute(this.typeConstraintAtt);
        
        this.defaultValueAtt = new DefaultValueSmAttribute();
        this.defaultValueAtt.init("DefaultValue", this, String.class );
        registerAttribute(this.defaultValueAtt);
        
        this.isOrderedAtt = new IsOrderedSmAttribute();
        this.isOrderedAtt.init("IsOrdered", this, Boolean.class );
        registerAttribute(this.isOrderedAtt);
        
        this.isUniqueAtt = new IsUniqueSmAttribute();
        this.isUniqueAtt.init("IsUnique", this, Boolean.class );
        registerAttribute(this.isUniqueAtt);
        
        this.isExceptionAtt = new IsExceptionSmAttribute();
        this.isExceptionAtt.init("IsException", this, Boolean.class );
        registerAttribute(this.isExceptionAtt);
        
        this.isStreamAtt = new IsStreamSmAttribute();
        this.isStreamAtt.init("IsStream", this, Boolean.class );
        registerAttribute(this.isStreamAtt);
        
        this.effectAtt = new EffectSmAttribute();
        this.effectAtt.init("Effect", this, ParameterEffectKind.class );
        registerAttribute(this.effectAtt);
        
        
        // Initialize and register the SmDependency
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(GeneralClass.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.composedDep = new ComposedSmDependency();
        this.composedDep.init("Composed", this, metamodel.getMClass(Operation.MQNAME), 0, 1 );
        registerDependency(this.composedDep);
        
        this.matchingDep = new MatchingSmDependency();
        this.matchingDep.init("Matching", this, metamodel.getMClass(Pin.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.matchingDep);
        
        this.sRepresentationDep = new SRepresentationSmDependency();
        this.sRepresentationDep.init("SRepresentation", this, metamodel.getMClass(Signal.MQNAME), 0, -1 );
        registerDependency(this.sRepresentationDep);
        
        this.returnedDep = new ReturnedSmDependency();
        this.returnedDep.init("Returned", this, metamodel.getMClass(Operation.MQNAME), 0, 1 );
        registerDependency(this.returnedDep);
        
        this.behaviorParamDep = new BehaviorParamSmDependency();
        this.behaviorParamDep.init("BehaviorParam", this, metamodel.getMClass(BehaviorParameter.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.behaviorParamDep);
    }

    @objid ("c74c1c77-0246-4e9b-936a-419118f4c8e9")
    public SmAttribute getParameterPassingAtt() {
        if (this.parameterPassingAtt == null) {
        	this.parameterPassingAtt = this.getAttributeDef("ParameterPassing");
        }
        return this.parameterPassingAtt;
    }

    @objid ("544155c2-eb56-4348-a125-d1818a7a412e")
    public SmAttribute getMultiplicityMinAtt() {
        if (this.multiplicityMinAtt == null) {
        	this.multiplicityMinAtt = this.getAttributeDef("MultiplicityMin");
        }
        return this.multiplicityMinAtt;
    }

    @objid ("cdb305f1-3f6c-4120-b780-79113335d982")
    public SmAttribute getMultiplicityMaxAtt() {
        if (this.multiplicityMaxAtt == null) {
        	this.multiplicityMaxAtt = this.getAttributeDef("MultiplicityMax");
        }
        return this.multiplicityMaxAtt;
    }

    @objid ("6792512f-f688-4811-b40a-7e33f6c1c741")
    public SmAttribute getTypeConstraintAtt() {
        if (this.typeConstraintAtt == null) {
        	this.typeConstraintAtt = this.getAttributeDef("TypeConstraint");
        }
        return this.typeConstraintAtt;
    }

    @objid ("91949f4d-6fbb-4ff3-8ab5-959d21b5e08c")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("99a78d4c-4a2c-4246-9a89-1353ca0980ea")
    public SmAttribute getIsOrderedAtt() {
        if (this.isOrderedAtt == null) {
        	this.isOrderedAtt = this.getAttributeDef("IsOrdered");
        }
        return this.isOrderedAtt;
    }

    @objid ("2328a03c-f472-4e52-99ec-65fbba5977c8")
    public SmAttribute getIsUniqueAtt() {
        if (this.isUniqueAtt == null) {
        	this.isUniqueAtt = this.getAttributeDef("IsUnique");
        }
        return this.isUniqueAtt;
    }

    @objid ("d9da3ee5-4b17-4e14-a23b-b081074f9230")
    public SmAttribute getIsExceptionAtt() {
        if (this.isExceptionAtt == null) {
        	this.isExceptionAtt = this.getAttributeDef("IsException");
        }
        return this.isExceptionAtt;
    }

    @objid ("5d1cc0b0-500f-4de9-9003-1368afd869b2")
    public SmAttribute getIsStreamAtt() {
        if (this.isStreamAtt == null) {
        	this.isStreamAtt = this.getAttributeDef("IsStream");
        }
        return this.isStreamAtt;
    }

    @objid ("27712911-1a0e-4525-8ba8-517399277351")
    public SmAttribute getEffectAtt() {
        if (this.effectAtt == null) {
        	this.effectAtt = this.getAttributeDef("Effect");
        }
        return this.effectAtt;
    }

    @objid ("5725c22b-bef8-4f76-b85e-1a2b299a78fb")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("55fde05e-d3cb-46c0-92fc-92aeb3181401")
    public SmDependency getComposedDep() {
        if (this.composedDep == null) {
        	this.composedDep = this.getDependencyDef("Composed");
        }
        return this.composedDep;
    }

    @objid ("b029e4db-650a-4fab-9b03-666693eaeed6")
    public SmDependency getMatchingDep() {
        if (this.matchingDep == null) {
        	this.matchingDep = this.getDependencyDef("Matching");
        }
        return this.matchingDep;
    }

    @objid ("ac50cac6-fb51-4b82-90f3-17850d5c3bff")
    public SmDependency getSRepresentationDep() {
        if (this.sRepresentationDep == null) {
        	this.sRepresentationDep = this.getDependencyDef("SRepresentation");
        }
        return this.sRepresentationDep;
    }

    @objid ("600e731a-0c00-4e43-8658-d3bd4ef09e97")
    public SmDependency getReturnedDep() {
        if (this.returnedDep == null) {
        	this.returnedDep = this.getDependencyDef("Returned");
        }
        return this.returnedDep;
    }

    @objid ("cee22a74-1489-4a41-8f31-0b1aa831a19b")
    public SmDependency getBehaviorParamDep() {
        if (this.behaviorParamDep == null) {
        	this.behaviorParamDep = this.getDependencyDef("BehaviorParam");
        }
        return this.behaviorParamDep;
    }

    @objid ("4f764e34-2f72-4218-981e-766619279c27")
    private static class ParameterObjectFactory implements ISmObjectFactory {
        @objid ("58a0d98b-2b71-418d-bbe3-f661d20c9301")
        private ParameterSmClass smClass;

        @objid ("cb583291-be44-4bc2-bc7a-6c3deba96d85")
        public ParameterObjectFactory(ParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9ab10f42-333a-44f2-a205-78d6d540d47d")
        @Override
        public ISmObjectData createData() {
            return new ParameterData(this.smClass);
        }

        @objid ("50d3a5eb-31f0-4aed-984f-95387ed0d55a")
        @Override
        public SmObjectImpl createImpl() {
            return new ParameterImpl();
        }

    }

    @objid ("226fb67c-925c-4e6c-a409-0c8129e7db77")
    public static class ParameterPassingSmAttribute extends SmAttribute {
        @objid ("98a0531d-74f1-4789-ba67-36517cac82bc")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mParameterPassing;
        }

        @objid ("3695f246-3fcf-4e6d-9c9b-8a98174321c6")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mParameterPassing = value;
        }

    }

    @objid ("195eec08-b8e2-4640-ad72-91ab20e6416d")
    public static class MultiplicityMinSmAttribute extends SmAttribute {
        @objid ("1b8cde3b-6363-47a0-b080-32eca7065537")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mMultiplicityMin;
        }

        @objid ("71e214c4-b82b-4da2-922b-04924b545f63")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mMultiplicityMin = value;
        }

    }

    @objid ("01240101-26dc-44b4-926c-d54df911aa2b")
    public static class MultiplicityMaxSmAttribute extends SmAttribute {
        @objid ("7f10c995-0834-4aec-9e3b-11258c65c7e6")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mMultiplicityMax;
        }

        @objid ("ac7bf564-8927-4d8a-8cf3-43d3ff19a338")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mMultiplicityMax = value;
        }

    }

    @objid ("b75a7fb1-02f7-4d0d-8bab-b0cc6a0c1b3c")
    public static class TypeConstraintSmAttribute extends SmAttribute {
        @objid ("a47029cf-3058-4023-bb04-ef7b198fb24a")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mTypeConstraint;
        }

        @objid ("256e613f-0330-40ef-8e79-db515ea2082f")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mTypeConstraint = value;
        }

    }

    @objid ("d2432244-d40a-401d-af04-f774c18c14c7")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("a8cab36e-6527-4add-8980-846fcb23ea16")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mDefaultValue;
        }

        @objid ("92668346-d4e3-43e0-bbb0-a621158fa81d")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mDefaultValue = value;
        }

    }

    @objid ("5033defb-4c46-4a68-bfd9-eb517d881103")
    public static class IsOrderedSmAttribute extends SmAttribute {
        @objid ("7e8740b4-38ff-4a26-890b-b2a1e104b8e3")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mIsOrdered;
        }

        @objid ("babb8f7c-c34e-4bdd-b245-b8dcb0079d55")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mIsOrdered = value;
        }

    }

    @objid ("0224cef2-ca3b-4f44-aed2-ff5b012eb417")
    public static class IsUniqueSmAttribute extends SmAttribute {
        @objid ("6dfd4668-f648-46f5-8415-cf664ccae4b8")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mIsUnique;
        }

        @objid ("b79195e3-0f7e-4f92-8f7f-fa1c50ecbec6")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mIsUnique = value;
        }

    }

    @objid ("15b580d6-8ccc-47b9-990b-4cec266fbffe")
    public static class IsExceptionSmAttribute extends SmAttribute {
        @objid ("05b4b510-a6a7-4948-9303-16c5891cbe76")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mIsException;
        }

        @objid ("eddc28df-8ca0-4b76-a1d4-ca72c5555a18")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mIsException = value;
        }

    }

    @objid ("7d96d865-ce2d-435a-8c78-5143d853cddb")
    public static class IsStreamSmAttribute extends SmAttribute {
        @objid ("03e1f7f5-7f37-4223-ab77-6bf8fea89d27")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mIsStream;
        }

        @objid ("9424a15c-6cca-429f-a020-dfce57aa8810")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mIsStream = value;
        }

    }

    @objid ("bd4f1b2d-fde0-4432-afc3-38b9a46a0d7f")
    public static class EffectSmAttribute extends SmAttribute {
        @objid ("da3faaac-c189-4a1f-b924-7eace450dea3")
        public Object getValue(ISmObjectData data) {
            return ((ParameterData) data).mEffect;
        }

        @objid ("961008d0-5a89-469d-98af-2a2b2af795e9")
        public void setValue(ISmObjectData data, Object value) {
            ((ParameterData) data).mEffect = value;
        }

    }

    @objid ("b5842096-45be-4169-9c26-d8fe1ec5ac3a")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("6d033448-85e2-4b6b-9bbb-aa90bd4d1da0")
        private SmDependency symetricDep;

        @objid ("3e085de8-2b41-4a24-a768-223eb99155e1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ParameterData) data).mType;
        }

        @objid ("968ea339-3b1f-46d9-91ee-13632250b84c")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ParameterData) data).mType = value;
        }

        @objid ("f691bd98-c629-41e3-8293-cca57fd4059b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((GeneralClassSmClass)this.getTarget()).getOccurenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("10d3bf71-4912-49d3-8186-770fef43b0a6")
    public static class ComposedSmDependency extends SmSingleDependency {
        @objid ("ee81d3ca-e18f-464e-bda3-fb75bd648762")
        private SmDependency symetricDep;

        @objid ("7caf94a4-f572-4374-a7cc-54c40ec7afeb")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ParameterData) data).mComposed;
        }

        @objid ("49ba0d4b-b0d9-4470-a025-c03d18bf40d6")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ParameterData) data).mComposed = value;
        }

        @objid ("56e79da0-fca7-4ac3-b2ab-db76dd6c48e0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getIODep();
            }
            return this.symetricDep;
        }

    }

    @objid ("85d00038-2c30-47a7-80e1-9b2488ede377")
    public static class MatchingSmDependency extends SmMultipleDependency {
        @objid ("19f4c8bf-ac87-4a55-a87c-ead43ba14c58")
        private SmDependency symetricDep;

        @objid ("2c2b7307-5380-40f9-b2ae-b60430ea3a50")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ParameterData)data).mMatching != null)? ((ParameterData)data).mMatching:SmMultipleDependency.EMPTY;
        }

        @objid ("419f385b-0317-4897-ab13-9b6a0757115d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ParameterData) data).mMatching = values;
        }

        @objid ("8476fa35-a803-4726-aa94-4a3af13c3606")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PinSmClass)this.getTarget()).getMatchedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c286f89d-b450-4870-a970-02c281221d79")
    public static class SRepresentationSmDependency extends SmMultipleDependency {
        @objid ("2e589514-3cc9-4e3f-901f-15a13f78105f")
        private SmDependency symetricDep;

        @objid ("7a4057d7-30b7-418d-aca7-6704373505c4")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ParameterData)data).mSRepresentation != null)? ((ParameterData)data).mSRepresentation:SmMultipleDependency.EMPTY;
        }

        @objid ("687cfce3-3dfd-4ad3-a11a-5bfc8d3301ad")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ParameterData) data).mSRepresentation = values;
        }

        @objid ("4261bdac-1bce-4c43-82c2-72c4a83b4d0d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((SignalSmClass)this.getTarget()).getPBaseDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6bc0f096-22c2-4710-936f-128a5a697503")
    public static class ReturnedSmDependency extends SmSingleDependency {
        @objid ("42057e99-70fa-4655-899e-535cfe6255db")
        private SmDependency symetricDep;

        @objid ("321e1925-538f-4b22-aead-5d8ae4bd8859")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ParameterData) data).mReturned;
        }

        @objid ("a2dcff12-9a58-4deb-84be-2233b3e1b533")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ParameterData) data).mReturned = value;
        }

        @objid ("2af6f2e2-abd1-4050-b350-86df443f1ab3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getReturnDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0325a71f-914e-46c6-bd80-aa7fd2c222c9")
    public static class BehaviorParamSmDependency extends SmMultipleDependency {
        @objid ("285f2b56-8cac-42eb-9fe0-18b88b0575f6")
        private SmDependency symetricDep;

        @objid ("1b42da94-706f-4d58-86e9-5073025cd0cf")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ParameterData)data).mBehaviorParam != null)? ((ParameterData)data).mBehaviorParam:SmMultipleDependency.EMPTY;
        }

        @objid ("27b38288-457b-4a82-aa5c-21293ccdafd6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ParameterData) data).mBehaviorParam = values;
        }

        @objid ("d07dcda3-fd82-4afa-b6b9-6982159595d2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BehaviorParameterSmClass)this.getTarget()).getMappedDep();
            }
            return this.symetricDep;
        }

    }

}
