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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSubProcessSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnCollaborationSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnArtifactData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("a0fecca7-2a0e-44e5-9042-68ec6e233e00")
public class BpmnArtifactSmClass extends BpmnBaseElementSmClass {
    @objid ("98e52227-a77c-4d17-9ba2-3f454bb91464")
    private SmDependency subProcessDep;

    @objid ("04eb36d8-7969-4974-9d48-df30125c11e2")
    private SmDependency collaborationDep;

    @objid ("8ce7eeda-5a89-45c9-adb7-a2073eb87e61")
    private SmDependency processDep;

    @objid ("6839814e-0f2e-484c-bb9e-ae35bac055a4")
    public BpmnArtifactSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("39514780-3ecd-4554-9e5f-d9b5b0babcc1")
    @Override
    public String getName() {
        return "BpmnArtifact";
    }

    @objid ("c512e976-6cb0-49e5-ae74-3455bb7f912b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("dd632e27-9d56-496f-8dde-e670d86e90e4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnArtifact.class;
    }

    @objid ("b7bfaee3-0072-4439-b9e8-3526e9bfff06")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("3b425585-82ab-4d29-8b8a-029885e9b998")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("d328066e-52e8-4401-b31e-e3f5896c49eb")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnBaseElement.MQNAME);
        this.registerFactory(new BpmnArtifactObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.subProcessDep = new SubProcessSmDependency();
        this.subProcessDep.init("SubProcess", this, metamodel.getMClass(BpmnSubProcess.MQNAME), 0, 1 );
        registerDependency(this.subProcessDep);
        
        this.collaborationDep = new CollaborationSmDependency();
        this.collaborationDep.init("Collaboration", this, metamodel.getMClass(BpmnCollaboration.MQNAME), 0, 1 );
        registerDependency(this.collaborationDep);
        
        this.processDep = new ProcessSmDependency();
        this.processDep.init("Process", this, metamodel.getMClass(BpmnProcess.MQNAME), 0, 1 );
        registerDependency(this.processDep);
    }

    @objid ("75467178-b743-406e-b7b2-b58b5b119e83")
    public SmDependency getSubProcessDep() {
        if (this.subProcessDep == null) {
        	this.subProcessDep = this.getDependencyDef("SubProcess");
        }
        return this.subProcessDep;
    }

    @objid ("a3b0fd9b-e9bf-4f2f-8011-36eb4c37eee3")
    public SmDependency getCollaborationDep() {
        if (this.collaborationDep == null) {
        	this.collaborationDep = this.getDependencyDef("Collaboration");
        }
        return this.collaborationDep;
    }

    @objid ("fff05ea3-9653-4e88-87a9-c636ebc5eaa7")
    public SmDependency getProcessDep() {
        if (this.processDep == null) {
        	this.processDep = this.getDependencyDef("Process");
        }
        return this.processDep;
    }

    @objid ("b2130f92-fa36-4160-9219-d1296cfe8853")
    private static class BpmnArtifactObjectFactory implements ISmObjectFactory {
        @objid ("342246cd-629f-4d18-a80c-66b5b4b2a343")
        private BpmnArtifactSmClass smClass;

        @objid ("85a901cb-2df0-4c69-9a24-91bf1e0185e0")
        public BpmnArtifactObjectFactory(BpmnArtifactSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("56091c2c-dee8-46c1-adf2-dd568948e579")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("827cad5d-de42-4a28-87cd-9a0530712cb6")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("84bde060-8f13-495b-856b-5124db653b9f")
    public static class SubProcessSmDependency extends SmSingleDependency {
        @objid ("6cfd2859-f874-44b6-9528-a69c7160767b")
        private SmDependency symetricDep;

        @objid ("1e8757e0-93d4-4bdf-857a-13971a4776d9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnArtifactData) data).mSubProcess;
        }

        @objid ("27755109-b92d-4931-abe1-9c70d47a9053")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnArtifactData) data).mSubProcess = value;
        }

        @objid ("e9acdb41-15ad-407c-975a-7c3ef0b3781a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnSubProcessSmClass)this.getTarget()).getArtifactDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("cc420b2c-dd9d-499c-ba87-f4ecf630bdf1")
    public static class CollaborationSmDependency extends SmSingleDependency {
        @objid ("1be055b6-6461-45cd-a44d-f9abc5901c5c")
        private SmDependency symetricDep;

        @objid ("7a0eee51-9e0e-448e-af1a-57f1d72b08df")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnArtifactData) data).mCollaboration;
        }

        @objid ("f34ff486-405a-4505-b3d0-726cab65648a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnArtifactData) data).mCollaboration = value;
        }

        @objid ("b8f8ff6c-1fa8-4398-b5e1-bcd49bd0f68b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnCollaborationSmClass)this.getTarget()).getArtifactDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("401b0419-13da-428e-b88e-69b78957b6f4")
    public static class ProcessSmDependency extends SmSingleDependency {
        @objid ("9bcc329d-bfb9-4ec2-b1b7-add92481a8bc")
        private SmDependency symetricDep;

        @objid ("9b715cf8-042f-4b88-b421-f6a48cd39d9f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((BpmnArtifactData) data).mProcess;
        }

        @objid ("e0e083cb-cfa1-404a-94ee-9b9cb14f81c4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((BpmnArtifactData) data).mProcess = value;
        }

        @objid ("7d285852-9512-461d-8ad7-dbcbe2320e95")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnProcessSmClass)this.getTarget()).getArtifactDep();
            }
            return this.symetricDep;
        }

    }

}
