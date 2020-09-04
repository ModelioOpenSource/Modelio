/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.AdHocOrdering;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSubProcessSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("f678f6ce-8a18-4ddc-b925-3df960bae1f4")
public class BpmnAdHocSubProcessSmClass extends BpmnSubProcessSmClass {
    @objid ("2b327532-109f-47b7-90bb-7c8434ca1891")
    private SmAttribute orderingAtt;

    @objid ("d7bda1c5-dde0-4e20-ab60-13f086664b71")
    private SmAttribute cancelRemainingInstancesAtt;

    @objid ("c276b0ef-7f72-456b-82af-b705ecf97ea6")
    private SmAttribute completionConditionAtt;

    @objid ("ad973951-aa79-4563-81ac-7ad090f596e6")
    public BpmnAdHocSubProcessSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("fcea68a7-b6e9-435f-b2aa-54d6a6273134")
    @Override
    public String getName() {
        return "BpmnAdHocSubProcess";
    }

    @objid ("71ae5218-44eb-4f81-a62b-27df2790b989")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("04e28573-4072-467b-849c-147a7b157847")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnAdHocSubProcess.class;
    }

    @objid ("daaacc23-afe7-4a4d-bbf1-29273a54aa58")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("011c64ac-62f0-4529-ad18-37eb2420589f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8b660944-2c3b-49fc-bfd5-0cd3b6ea003f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnSubProcess.MQNAME);
        this.registerFactory(new BpmnAdHocSubProcessObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.orderingAtt = new OrderingSmAttribute();
        this.orderingAtt.init("Ordering", this, AdHocOrdering.class );
        registerAttribute(this.orderingAtt);
        
        this.cancelRemainingInstancesAtt = new CancelRemainingInstancesSmAttribute();
        this.cancelRemainingInstancesAtt.init("CancelRemainingInstances", this, Boolean.class );
        registerAttribute(this.cancelRemainingInstancesAtt);
        
        this.completionConditionAtt = new CompletionConditionSmAttribute();
        this.completionConditionAtt.init("CompletionCondition", this, String.class );
        registerAttribute(this.completionConditionAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("3546649d-4df7-4f8e-91ff-129503b6be25")
    public SmAttribute getOrderingAtt() {
        if (this.orderingAtt == null) {
        	this.orderingAtt = this.getAttributeDef("Ordering");
        }
        return this.orderingAtt;
    }

    @objid ("9218e80d-9f68-40a1-bad5-c70c3d54008f")
    public SmAttribute getCancelRemainingInstancesAtt() {
        if (this.cancelRemainingInstancesAtt == null) {
        	this.cancelRemainingInstancesAtt = this.getAttributeDef("CancelRemainingInstances");
        }
        return this.cancelRemainingInstancesAtt;
    }

    @objid ("f4f0dcdf-923f-4988-9a01-38b9d1aa246e")
    public SmAttribute getCompletionConditionAtt() {
        if (this.completionConditionAtt == null) {
        	this.completionConditionAtt = this.getAttributeDef("CompletionCondition");
        }
        return this.completionConditionAtt;
    }

    @objid ("ebdf7ff8-006a-4f8e-9b37-f4a44feac304")
    private static class BpmnAdHocSubProcessObjectFactory implements ISmObjectFactory {
        @objid ("2db96347-e91b-4a98-9dd1-c3986591f68f")
        private BpmnAdHocSubProcessSmClass smClass;

        @objid ("e97076fd-4dc2-43a7-a00f-f31dab874504")
        public BpmnAdHocSubProcessObjectFactory(BpmnAdHocSubProcessSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3743bc75-56e0-4fc3-b124-c4805e305ff1")
        @Override
        public ISmObjectData createData() {
            return new BpmnAdHocSubProcessData(this.smClass);
        }

        @objid ("2ba0d0bf-8e87-4096-beb7-fa7ef1860561")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnAdHocSubProcessImpl();
        }

    }

    @objid ("1c8c8192-be96-4aa5-b064-4172542c889f")
    public static class OrderingSmAttribute extends SmAttribute {
        @objid ("4cf3a8cd-8073-4568-8254-bb8f2e794535")
        public Object getValue(ISmObjectData data) {
            return ((BpmnAdHocSubProcessData) data).mOrdering;
        }

        @objid ("20537f9e-756e-457e-b8c8-e699ccad73aa")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnAdHocSubProcessData) data).mOrdering = value;
        }

    }

    @objid ("302fa632-3657-4fb2-8387-ad512c727d6e")
    public static class CancelRemainingInstancesSmAttribute extends SmAttribute {
        @objid ("29896d15-8bce-4503-9b9f-14e62de43626")
        public Object getValue(ISmObjectData data) {
            return ((BpmnAdHocSubProcessData) data).mCancelRemainingInstances;
        }

        @objid ("a5b14c8a-0cac-48e5-aad9-565df10b3e22")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnAdHocSubProcessData) data).mCancelRemainingInstances = value;
        }

    }

    @objid ("dadc0fc7-8977-4999-b8ae-b4c201bda585")
    public static class CompletionConditionSmAttribute extends SmAttribute {
        @objid ("9d298603-5271-4db6-80b4-ea704b0f4b66")
        public Object getValue(ISmObjectData data) {
            return ((BpmnAdHocSubProcessData) data).mCompletionCondition;
        }

        @objid ("52dd3da5-fd9a-4e8c-b864-3449b63eb24b")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnAdHocSubProcessData) data).mCompletionCondition = value;
        }

    }

}
