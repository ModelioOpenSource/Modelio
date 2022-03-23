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

package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("9d09d883-ae4e-4654-8809-ad2a3437581b")
public class BpmnItemAwareElementSmClass extends BpmnFlowElementSmClass {
    @objid ("f3263f60-2f98-4c15-9a12-e515aa4459a0")
    private SmDependency targetOfDataAssociationDep;

    @objid ("308a285b-680a-4a30-8411-fb0565ffb75c")
    private SmDependency itemSubjectRefDep;

    @objid ("203cead2-c7c6-4616-b455-da3cd542bc15")
    private SmDependency dataStateDep;

    @objid ("b3888237-6937-4924-8e1b-903fa0f4f153")
    private SmDependency sourceOfDataAssociationDep;

    @objid ("7e9b9d36-8177-482c-9720-db6468db18ed")
    public  BpmnItemAwareElementSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2bb90913-ab9d-4518-991a-dd5d348b5a07")
    @Override
    public String getName() {
        return "BpmnItemAwareElement";
        
    }

    @objid ("f2964039-9b00-4b94-bed9-1ddf09037efe")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("bf900739-2f18-43f3-808c-3b282b0ab418")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnItemAwareElement.class;
        
    }

    @objid ("c566e7b9-2ac3-4c89-93e8-86c7630dad5f")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("032f0985-5e16-404c-8330-6d8319e7b1e0")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("da06b242-1bf2-4938-86ad-a3877d1bde61")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnFlowElement.MQNAME);
        this.registerFactory(new BpmnItemAwareElementObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.targetOfDataAssociationDep = new TargetOfDataAssociationSmDependency();
        this.targetOfDataAssociationDep.init("TargetOfDataAssociation", this, metamodel.getMClass(BpmnDataAssociation.MQNAME), 0, -1 );
        registerDependency(this.targetOfDataAssociationDep);
        
        this.itemSubjectRefDep = new ItemSubjectRefSmDependency();
        this.itemSubjectRefDep.init("ItemSubjectRef", this, metamodel.getMClass(BpmnItemDefinition.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.itemSubjectRefDep);
        
        this.dataStateDep = new DataStateSmDependency();
        this.dataStateDep.init("DataState", this, metamodel.getMClass(BpmnDataState.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.dataStateDep);
        
        this.sourceOfDataAssociationDep = new SourceOfDataAssociationSmDependency();
        this.sourceOfDataAssociationDep.init("SourceOfDataAssociation", this, metamodel.getMClass(BpmnDataAssociation.MQNAME), 0, -1 );
        registerDependency(this.sourceOfDataAssociationDep);
        
        
    }

    @objid ("a2f768cf-ed1d-4a7f-b70e-1e448a4c6da2")
    public SmDependency getTargetOfDataAssociationDep() {
        if (this.targetOfDataAssociationDep == null) {
        	this.targetOfDataAssociationDep = this.getDependencyDef("TargetOfDataAssociation");
        }
        return this.targetOfDataAssociationDep;
    }

    @objid ("13832cdf-22e2-48d0-ac6f-e923e79bec4d")
    public SmDependency getItemSubjectRefDep() {
        if (this.itemSubjectRefDep == null) {
        	this.itemSubjectRefDep = this.getDependencyDef("ItemSubjectRef");
        }
        return this.itemSubjectRefDep;
    }

    @objid ("fdca561c-2834-451e-b473-7e4d1ce5f257")
    public SmDependency getDataStateDep() {
        if (this.dataStateDep == null) {
        	this.dataStateDep = this.getDependencyDef("DataState");
        }
        return this.dataStateDep;
    }

    @objid ("9d7178a1-dde9-4979-91a7-02ba884442d3")
    public SmDependency getSourceOfDataAssociationDep() {
        if (this.sourceOfDataAssociationDep == null) {
        	this.sourceOfDataAssociationDep = this.getDependencyDef("SourceOfDataAssociation");
        }
        return this.sourceOfDataAssociationDep;
    }

    @objid ("9c40cf60-4c7a-40a6-b2a9-b2c42198fd15")
    private static class BpmnItemAwareElementObjectFactory implements ISmObjectFactory {
        @objid ("9d4a8c8c-f573-4abd-b519-331321b80f83")
        private BpmnItemAwareElementSmClass smClass;

        @objid ("15e81e70-95aa-42c1-a808-b83622394358")
        public  BpmnItemAwareElementObjectFactory(BpmnItemAwareElementSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fd8d8bcd-eca7-480d-bd40-a2f7707e4c80")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("7a7e38b4-8ab2-44be-b5c2-e771fa1f5219")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("9832015c-71f6-4200-9fcf-fe1e4aaca4c8")
    public static class TargetOfDataAssociationSmDependency extends SmMultipleDependency {
        @objid ("5f0a2613-5211-491f-893a-caa715de3bdc")
        private SmDependency symetricDep;

        @objid ("d6a6c215-ca92-4571-bf70-d21847669c53")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnItemAwareElementData)data).mTargetOfDataAssociation != null)? ((BpmnItemAwareElementData)data).mTargetOfDataAssociation:SmMultipleDependency.EMPTY;
        }

        @objid ("6e64e049-2343-4dd0-900e-ef92a639acc5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnItemAwareElementData) data).mTargetOfDataAssociation = values;
            
        }

        @objid ("1f2bbdb4-58db-45d1-85bb-06ffee8bbe2b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataAssociationSmClass)this.getTarget()).getTargetRefDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("36c9a21f-0a0a-42be-ac55-af8389ec3745")
    public static class ItemSubjectRefSmDependency extends SmSingleDependency {
        @objid ("a54f462e-73e2-402a-a2f1-d6301489fd0a")
        private SmDependency symetricDep;

        @objid ("7d75bd83-47b7-49c7-819e-d06a5443afd5")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnItemAwareElementData) data).mItemSubjectRef;
        }

        @objid ("a17b6573-d90f-45b3-a8d5-09937919093f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnItemAwareElementData) data).mItemSubjectRef = value;
        }

        @objid ("8c38e91f-d6b7-4a07-a430-339b0916ee2b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnItemDefinitionSmClass)this.getTarget()).getTypedItemDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("72fda9e9-167a-4d0c-a052-6828812f01d6")
    public static class DataStateSmDependency extends SmSingleDependency {
        @objid ("8bfb03cf-fd08-48c0-8839-3af5b447ef8d")
        private SmDependency symetricDep;

        @objid ("14b2c1db-e360-47e1-8a6c-a2e95ad31204")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnItemAwareElementData) data).mDataState;
        }

        @objid ("0a4347b4-f7ac-4843-83ba-8093cae57384")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnItemAwareElementData) data).mDataState = value;
        }

        @objid ("98834772-3fe1-4dbc-afb9-af825e6fedc4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataStateSmClass)this.getTarget()).getItemDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("8adfb522-b3c4-428e-94d2-34bf0f7aa494")
    public static class SourceOfDataAssociationSmDependency extends SmMultipleDependency {
        @objid ("4e1f8228-87ff-4405-a4c4-b334139aaf59")
        private SmDependency symetricDep;

        @objid ("25a00d41-dc40-42d1-ae9b-2e9532ecdc96")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnItemAwareElementData)data).mSourceOfDataAssociation != null)? ((BpmnItemAwareElementData)data).mSourceOfDataAssociation:SmMultipleDependency.EMPTY;
        }

        @objid ("8d0033bc-0bbb-4013-a9c8-ae9f6fd5f74e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnItemAwareElementData) data).mSourceOfDataAssociation = values;
            
        }

        @objid ("95c90d51-88ad-4cc2-852d-91c1d0b71ae8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnDataAssociationSmClass)this.getTarget()).getSourceRefDep();
            }
            return this.symetricDep;
            
        }

    }

}
