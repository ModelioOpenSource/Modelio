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

package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlowEffectKind;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("06e1a3c5-78c9-42ca-8c42-4aa06fd6eeb3")
public class ObjectFlowSmClass extends ActivityEdgeSmClass {
    @objid ("0ffab070-e3af-469c-8a35-94a37eb0d05d")
    private SmAttribute transformationBehaviorAtt;

    @objid ("9c1c3c0a-4ceb-4cdd-8256-82014edf06a9")
    private SmAttribute selectionBehaviorAtt;

    @objid ("ac5bdea3-8f79-47b8-bcf4-9228bff81424")
    private SmAttribute isMultiCastAtt;

    @objid ("6f38fa15-bc60-4e33-862e-bdda2b7e183f")
    private SmAttribute isMultiReceiveAtt;

    @objid ("4744e254-9985-4113-b6d2-0ea022cdd48d")
    private SmAttribute effectAtt;

    @objid ("d336ed0b-2e1f-47c8-829d-81bb9e5f394d")
    public  ObjectFlowSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5c4aafa6-cdb6-413f-a39b-c9d78f724aec")
    @Override
    public String getName() {
        return "ObjectFlow";
        
    }

    @objid ("5da31af4-330d-4692-a615-78cfe995c246")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("afe4cc60-e33a-4f30-9f4e-a2cc6f5a9d75")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ObjectFlow.class;
        
    }

    @objid ("64e4cd48-d55e-43f4-8876-eb16b2475c0f")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("50e4b5ff-0177-4b9c-88b4-6132d9445b05")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("09d824b5-8435-41cf-a537-3fae49fe0009")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityEdge.MQNAME);
        this.registerFactory(new ObjectFlowObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.transformationBehaviorAtt = new TransformationBehaviorSmAttribute();
        this.transformationBehaviorAtt.init("TransformationBehavior", this, String.class );
        registerAttribute(this.transformationBehaviorAtt);
        
        this.selectionBehaviorAtt = new SelectionBehaviorSmAttribute();
        this.selectionBehaviorAtt.init("SelectionBehavior", this, String.class );
        registerAttribute(this.selectionBehaviorAtt);
        
        this.isMultiCastAtt = new IsMultiCastSmAttribute();
        this.isMultiCastAtt.init("IsMultiCast", this, Boolean.class );
        registerAttribute(this.isMultiCastAtt);
        
        this.isMultiReceiveAtt = new IsMultiReceiveSmAttribute();
        this.isMultiReceiveAtt.init("IsMultiReceive", this, Boolean.class );
        registerAttribute(this.isMultiReceiveAtt);
        
        this.effectAtt = new EffectSmAttribute();
        this.effectAtt.init("Effect", this, ObjectFlowEffectKind.class );
        registerAttribute(this.effectAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("abe6989f-bff3-45cb-a866-75195861d102")
    public SmAttribute getTransformationBehaviorAtt() {
        if (this.transformationBehaviorAtt == null) {
        	this.transformationBehaviorAtt = this.getAttributeDef("TransformationBehavior");
        }
        return this.transformationBehaviorAtt;
    }

    @objid ("860581c6-42c0-45c7-922e-cf1018b1aef3")
    public SmAttribute getSelectionBehaviorAtt() {
        if (this.selectionBehaviorAtt == null) {
        	this.selectionBehaviorAtt = this.getAttributeDef("SelectionBehavior");
        }
        return this.selectionBehaviorAtt;
    }

    @objid ("eaa8ddc4-382e-4717-aceb-0532dcef4177")
    public SmAttribute getIsMultiCastAtt() {
        if (this.isMultiCastAtt == null) {
        	this.isMultiCastAtt = this.getAttributeDef("IsMultiCast");
        }
        return this.isMultiCastAtt;
    }

    @objid ("25ff18ec-4c34-46c1-ab80-8cebf5d4e0a6")
    public SmAttribute getIsMultiReceiveAtt() {
        if (this.isMultiReceiveAtt == null) {
        	this.isMultiReceiveAtt = this.getAttributeDef("IsMultiReceive");
        }
        return this.isMultiReceiveAtt;
    }

    @objid ("bd54ed87-162e-4fbd-ade4-3392354ddc12")
    public SmAttribute getEffectAtt() {
        if (this.effectAtt == null) {
        	this.effectAtt = this.getAttributeDef("Effect");
        }
        return this.effectAtt;
    }

    @objid ("f8f312a4-0a3c-4737-8651-bf281eb7881f")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("696306de-fafe-4647-992c-136c5db8e194")
    private static class ObjectFlowObjectFactory implements ISmObjectFactory {
        @objid ("eb2870b0-b112-4fc9-91ff-3758aa7f3920")
        private ObjectFlowSmClass smClass;

        @objid ("448c23df-8875-480b-acb7-77244a9826eb")
        public  ObjectFlowObjectFactory(ObjectFlowSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d13bc467-144b-47f8-aace-cf4af82bf3ad")
        @Override
        public ISmObjectData createData() {
            return new ObjectFlowData(this.smClass);
        }

        @objid ("8d6d0f1a-5457-4fae-84e8-dee0769824e3")
        @Override
        public SmObjectImpl createImpl() {
            return new ObjectFlowImpl();
        }

    }

    @objid ("94c320b7-0696-4e2c-a323-18e9a65428df")
    public static class TransformationBehaviorSmAttribute extends SmAttribute {
        @objid ("7c5c8ae5-face-4f52-b643-9d4c8445edc7")
        public Object getValue(ISmObjectData data) {
            return ((ObjectFlowData) data).mTransformationBehavior;
        }

        @objid ("171f4f0f-3f0e-49bd-ae81-a39c370bdf62")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectFlowData) data).mTransformationBehavior = value;
        }

    }

    @objid ("b6ce9977-d511-4525-a8f7-ab4a9e502c15")
    public static class SelectionBehaviorSmAttribute extends SmAttribute {
        @objid ("31fda24e-12da-4607-8cee-c7b91b7d3763")
        public Object getValue(ISmObjectData data) {
            return ((ObjectFlowData) data).mSelectionBehavior;
        }

        @objid ("fe6bc1f8-4302-446a-afbd-dfafb4b0bf3f")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectFlowData) data).mSelectionBehavior = value;
        }

    }

    @objid ("5584c319-d2a2-45be-b5fc-051fd27b5ed0")
    public static class IsMultiCastSmAttribute extends SmAttribute {
        @objid ("5bb84946-107c-4b5b-8bed-a60422626cf7")
        public Object getValue(ISmObjectData data) {
            return ((ObjectFlowData) data).mIsMultiCast;
        }

        @objid ("dbb1e72f-7ff6-49fc-9a16-f434e07ace6d")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectFlowData) data).mIsMultiCast = value;
        }

    }

    @objid ("86d90f7e-1994-4034-a291-0d4766c80c3d")
    public static class IsMultiReceiveSmAttribute extends SmAttribute {
        @objid ("580b5db7-446b-43d0-bd8e-e7b926417a61")
        public Object getValue(ISmObjectData data) {
            return ((ObjectFlowData) data).mIsMultiReceive;
        }

        @objid ("b7617f3a-de47-4a61-b3a6-693bceadcb94")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectFlowData) data).mIsMultiReceive = value;
        }

    }

    @objid ("1988f857-bd2a-4d55-9c05-d75f971fe031")
    public static class EffectSmAttribute extends SmAttribute {
        @objid ("4a87ea00-f854-40ad-9339-e85b01e87325")
        public Object getValue(ISmObjectData data) {
            return ((ObjectFlowData) data).mEffect;
        }

        @objid ("2e2816c6-2e5b-45ef-aa09-d73015466c19")
        public void setValue(ISmObjectData data, Object value) {
            ((ObjectFlowData) data).mEffect = value;
        }

    }

}
