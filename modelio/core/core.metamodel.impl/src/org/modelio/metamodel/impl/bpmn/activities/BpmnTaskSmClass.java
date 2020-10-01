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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnCallActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTaskData;
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

@objid ("de8fea2f-6f2e-4aed-8b9d-e955335a3d26")
public class BpmnTaskSmClass extends BpmnActivitySmClass {
    @objid ("56aacb82-00f5-44ac-8c3e-820c432ddb7e")
    private SmAttribute isGlobalAtt;

    @objid ("9fbde25d-e1ee-40b1-9f6b-ae52aa1e73b4")
    private SmDependency callerDep;

    @objid ("d10848a7-ccc0-4ac5-a2a8-5d5e3b18761d")
    public BpmnTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f07cb944-b1d9-46b1-9ebb-47ee9a42f8e7")
    @Override
    public String getName() {
        return "BpmnTask";
    }

    @objid ("b905ef4a-c5c2-40eb-94e4-49df778eeb04")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d8c1c947-e69b-41bd-865b-8f8d72d58cff")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnTask.class;
    }

    @objid ("3c2103c2-7f9b-4226-93df-c5b54492b5e6")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("ae23ab35-e6bb-4699-bdea-a0275c5cc7e5")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("fb40a549-2cc6-4815-b468-f6c7b4ce4b70")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnActivity.MQNAME);
        this.registerFactory(new BpmnTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isGlobalAtt = new IsGlobalSmAttribute();
        this.isGlobalAtt.init("IsGlobal", this, Boolean.class );
        registerAttribute(this.isGlobalAtt);
        
        
        // Initialize and register the SmDependency
        this.callerDep = new CallerSmDependency();
        this.callerDep.init("Caller", this, metamodel.getMClass(BpmnCallActivity.MQNAME), 0, -1 );
        registerDependency(this.callerDep);
    }

    @objid ("a49c4edd-39ef-4b3c-8c28-eff83650d61d")
    public SmAttribute getIsGlobalAtt() {
        if (this.isGlobalAtt == null) {
        	this.isGlobalAtt = this.getAttributeDef("IsGlobal");
        }
        return this.isGlobalAtt;
    }

    @objid ("ea18bc6f-ae2f-4ddd-a952-9e7d22459999")
    public SmDependency getCallerDep() {
        if (this.callerDep == null) {
        	this.callerDep = this.getDependencyDef("Caller");
        }
        return this.callerDep;
    }

    @objid ("3d9fe27c-845a-42d3-b889-8473d25e5e5f")
    private static class BpmnTaskObjectFactory implements ISmObjectFactory {
        @objid ("463c39fc-8838-4c4a-ac7a-62dbcfe7af10")
        private BpmnTaskSmClass smClass;

        @objid ("83f985c6-65f7-4cfc-a547-a48f85804652")
        public BpmnTaskObjectFactory(BpmnTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e99747b7-146e-4ce1-9bd9-f2d048d34ace")
        @Override
        public ISmObjectData createData() {
            return new BpmnTaskData(this.smClass);
        }

        @objid ("adf5c0c6-d3eb-4353-81b4-b9b3e4a3e4e9")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnTaskImpl();
        }

    }

    @objid ("6bc714cc-9a44-453b-b892-97f641a9accd")
    public static class IsGlobalSmAttribute extends SmAttribute {
        @objid ("566723f9-0ea0-4110-9c8e-5bae766b5dd9")
        public Object getValue(ISmObjectData data) {
            return ((BpmnTaskData) data).mIsGlobal;
        }

        @objid ("b28bff9f-8063-42e6-949f-2b406ab9c917")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnTaskData) data).mIsGlobal = value;
        }

    }

    @objid ("b807db95-36cf-4b07-9843-84afb8b6d1c5")
    public static class CallerSmDependency extends SmMultipleDependency {
        @objid ("b4eb661f-fc3c-4957-bfb6-6beb5af3d26c")
        private SmDependency symetricDep;

        @objid ("3a0afd22-9c55-4c7b-8a90-b3c995e20bef")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnTaskData)data).mCaller != null)? ((BpmnTaskData)data).mCaller:SmMultipleDependency.EMPTY;
        }

        @objid ("47e9038d-843e-426f-9a7e-a862625e1808")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnTaskData) data).mCaller = values;
        }

        @objid ("cc922642-7789-4ac2-83cc-5ada845c5383")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCallActivitySmClass)this.getTarget()).getCalledGlobalTaskDep();
            }
            return this.symetricDep;
        }

    }

}
