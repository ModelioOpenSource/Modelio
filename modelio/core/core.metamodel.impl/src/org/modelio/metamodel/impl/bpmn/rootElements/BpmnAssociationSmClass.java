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

package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociationDirection;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("2fa1359f-9d07-4dd6-a0c4-8956cffbb1a4")
public class BpmnAssociationSmClass extends BpmnArtifactSmClass {
    @objid ("17dbf3da-0203-4989-90a6-7172936975ac")
    private SmAttribute associationDirectionAtt;

    @objid ("df35383e-4772-4e0e-9a85-a4af254115d0")
    private SmDependency targetRefDep;

    @objid ("bc078f44-06b0-446f-92af-ea1a405d03a2")
    private SmDependency sourceRefDep;

    @objid ("af21fd4f-4c1d-4d17-9f4f-5c066ce474b3")
    public  BpmnAssociationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6b077c74-02df-43e0-8d09-40aa0eaae3bf")
    @Override
    public String getName() {
        return "BpmnAssociation";
        
    }

    @objid ("5ef0dbd6-3be1-417e-9e15-d30a164aa54d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6981b398-e1a4-477a-a3c2-ca0c4f5b819a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnAssociation.class;
        
    }

    @objid ("1893b827-c4df-4e7f-8216-d1aaa4a6b70e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("357bbbee-786a-4e30-950d-abe5974ce137")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("43edbf23-d6c6-4edf-aa85-1dcb803e7127")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnArtifact.MQNAME);
        this.registerFactory(new BpmnAssociationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.associationDirectionAtt = new AssociationDirectionSmAttribute();
        this.associationDirectionAtt.init("AssociationDirection", this, BpmnAssociationDirection.class );
        registerAttribute(this.associationDirectionAtt);
        
        
        // Initialize and register the SmDependency
        this.targetRefDep = new TargetRefSmDependency();
        this.targetRefDep.init("TargetRef", this, metamodel.getMClass(BpmnBaseElement.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.targetRefDep);
        
        this.sourceRefDep = new SourceRefSmDependency();
        this.sourceRefDep.init("SourceRef", this, metamodel.getMClass(BpmnBaseElement.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.sourceRefDep);
        
        
    }

    @objid ("f5d4f0fc-d202-4e95-8da5-5784aa172da2")
    public SmAttribute getAssociationDirectionAtt() {
        if (this.associationDirectionAtt == null) {
        	this.associationDirectionAtt = this.getAttributeDef("AssociationDirection");
        }
        return this.associationDirectionAtt;
    }

    @objid ("85f7bfe1-5a90-4be9-a95a-9d3e888a1c36")
    public SmDependency getTargetRefDep() {
        if (this.targetRefDep == null) {
        	this.targetRefDep = this.getDependencyDef("TargetRef");
        }
        return this.targetRefDep;
    }

    @objid ("63d6fe70-ce03-4735-931d-84537fdbeccc")
    public SmDependency getSourceRefDep() {
        if (this.sourceRefDep == null) {
        	this.sourceRefDep = this.getDependencyDef("SourceRef");
        }
        return this.sourceRefDep;
    }

    @objid ("f1ff0ff0-819f-431d-a230-fc286958391b")
    private static class BpmnAssociationObjectFactory implements ISmObjectFactory {
        @objid ("90307d19-7d61-4da3-a3c9-0aed0871a17c")
        private BpmnAssociationSmClass smClass;

        @objid ("a61f58b2-db2e-49eb-bdf1-c1fccbdbe175")
        public  BpmnAssociationObjectFactory(BpmnAssociationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("895fa1c5-4ddf-4d97-b4f3-68a3cc0e01f4")
        @Override
        public ISmObjectData createData() {
            return new BpmnAssociationData(this.smClass);
        }

        @objid ("2e1c24e2-a39f-42db-8aab-acfbd3337ea5")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnAssociationImpl();
        }

    }

    @objid ("ccc9f970-f8d8-4ce4-a906-8b5b72d02bbb")
    public static class AssociationDirectionSmAttribute extends SmAttribute {
        @objid ("b3b8534b-28af-4b61-a391-06f23cfc71cf")
        public Object getValue(ISmObjectData data) {
            return ((BpmnAssociationData) data).mAssociationDirection;
        }

        @objid ("01abd8ea-ceb6-4487-8328-1316feb291e5")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnAssociationData) data).mAssociationDirection = value;
        }

    }

    @objid ("27e9cbc3-6187-466d-b4c6-f1f94c98d6cc")
    public static class TargetRefSmDependency extends SmSingleDependency {
        @objid ("e76b6c80-1f16-43b7-b924-fea603b74880")
        private SmDependency symetricDep;

        @objid ("94894cbc-3e7e-4551-b7eb-efb3aebd592c")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnAssociationData) data).mTargetRef;
        }

        @objid ("00864929-8871-4f86-9263-7547a4582f68")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnAssociationData) data).mTargetRef = value;
        }

        @objid ("cc85654e-184f-4c95-82c8-e224b9843577")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnBaseElementSmClass)this.getTarget()).getIncomingAssocDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("91f9340a-0288-4689-a572-9303899ca09e")
    public static class SourceRefSmDependency extends SmSingleDependency {
        @objid ("34a49677-42aa-4ab9-a59c-f70a189c04d5")
        private SmDependency symetricDep;

        @objid ("bf7e4412-5512-4322-b77d-2ff530fc0a73")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnAssociationData) data).mSourceRef;
        }

        @objid ("8f1b88b1-d1c0-46d2-9df2-f618f966145b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnAssociationData) data).mSourceRef = value;
        }

        @objid ("b4b6e0f4-6f11-407c-b91b-94df6bf92b7e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnBaseElementSmClass)this.getTarget()).getOutgoingAssocDep();
            }
            return this.symetricDep;
            
        }

    }

}
