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

package org.modelio.metamodel.impl.bpmn.bpmnService;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnParticipantSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("6656867f-8de6-4af5-9946-c6cc4acc1e7c")
public class BpmnInterfaceSmClass extends BpmnSharedElementSmClass {
    @objid ("da60af3e-5ef7-43ba-a478-f3838e973e9d")
    private SmDependency operationDep;

    @objid ("6b778c9d-d64d-4e50-94d6-63bfa6b809bf")
    private SmDependency participantRefDep;

    @objid ("ff6f2743-9d92-46d9-90c3-71958be3cb32")
    public  BpmnInterfaceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("22020f80-6fa6-489d-b2d8-d53e5a7fcf4e")
    @Override
    public String getName() {
        return "BpmnInterface";
        
    }

    @objid ("f1621796-6939-4583-83f1-f2ba42b47ccb")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("19a1a617-938c-4a61-832f-b6800c369d85")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnInterface.class;
        
    }

    @objid ("fb180df8-a62e-49f8-bb7b-18b178df6757")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("d1355309-5988-48bf-891e-a1a812ede1cf")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("2d14d976-b769-4aa0-bb34-439acde086cb")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnSharedElement.MQNAME);
        this.registerFactory(new BpmnInterfaceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.operationDep = new OperationSmDependency();
        this.operationDep.init("Operation", this, metamodel.getMClass(BpmnOperation.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.operationDep);
        
        this.participantRefDep = new ParticipantRefSmDependency();
        this.participantRefDep.init("ParticipantRef", this, metamodel.getMClass(BpmnParticipant.MQNAME), 0, -1 );
        registerDependency(this.participantRefDep);
        
        
    }

    @objid ("196fd503-14f5-4fdd-8b37-d4b11ea20861")
    public SmDependency getOperationDep() {
        if (this.operationDep == null) {
        	this.operationDep = this.getDependencyDef("Operation");
        }
        return this.operationDep;
    }

    @objid ("8f8d28e7-e955-465f-8045-187308bfb4b0")
    public SmDependency getParticipantRefDep() {
        if (this.participantRefDep == null) {
        	this.participantRefDep = this.getDependencyDef("ParticipantRef");
        }
        return this.participantRefDep;
    }

    @objid ("a6dac772-9968-40f2-85bb-9a038721c785")
    private static class BpmnInterfaceObjectFactory implements ISmObjectFactory {
        @objid ("b1c1eddb-a17e-4599-90a5-a09758c2f241")
        private BpmnInterfaceSmClass smClass;

        @objid ("f1fa9cbe-bde2-4457-b300-04d7b5c2d74b")
        public  BpmnInterfaceObjectFactory(BpmnInterfaceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("24f5ccba-8c6e-4250-9783-17d69b3d558f")
        @Override
        public ISmObjectData createData() {
            return new BpmnInterfaceData(this.smClass);
        }

        @objid ("7b57e8ce-e943-4f49-a4ab-69b3a6b1ff28")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnInterfaceImpl();
        }

    }

    @objid ("13f6bfcd-dcd9-4e4a-b153-7ad57ac1d615")
    public static class OperationSmDependency extends SmMultipleDependency {
        @objid ("ca12cde4-f802-433f-a1ff-4f95995aceff")
        private SmDependency symetricDep;

        @objid ("84598af1-b815-455b-ab20-02a83c5bc22b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnInterfaceData)data).mOperation != null)? ((BpmnInterfaceData)data).mOperation:SmMultipleDependency.EMPTY;
        }

        @objid ("9225b6e4-1e38-4f0f-9cd9-0f71416a1c94")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnInterfaceData) data).mOperation = values;
            
        }

        @objid ("83f0fd73-92e0-4aed-8580-f3153eebcae8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnOperationSmClass)this.getTarget()).getBpmnInterfaceRefDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("240f0cc2-41b9-4fe3-a599-00128c8720e6")
    public static class ParticipantRefSmDependency extends SmMultipleDependency {
        @objid ("b5f83772-2e0b-42ba-932d-4b1426e2c08a")
        private SmDependency symetricDep;

        @objid ("1c3dafa5-f1c5-4dfd-b0f8-7f409b56c5bc")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnInterfaceData)data).mParticipantRef != null)? ((BpmnInterfaceData)data).mParticipantRef:SmMultipleDependency.EMPTY;
        }

        @objid ("ebc25c6f-4cfc-40dc-8645-a6600a29cdaf")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnInterfaceData) data).mParticipantRef = values;
            
        }

        @objid ("f7cc2ef9-58ea-4620-ad1b-27cb22600b23")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnParticipantSmClass)this.getTarget()).getInterfaceRefsDep();
            }
            return this.symetricDep;
            
        }

    }

}
