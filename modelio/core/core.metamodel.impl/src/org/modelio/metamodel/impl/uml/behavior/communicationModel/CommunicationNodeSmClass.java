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
package org.modelio.metamodel.impl.uml.behavior.communicationModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationChannelSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationInteractionSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationNodeData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceSmClass;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Instance;
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

@objid ("56558095-d704-48b2-8db0-7279fe43bfa4")
public class CommunicationNodeSmClass extends UmlModelElementSmClass {
    @objid ("f789ee58-432e-4d2a-a131-4cbd17deb9f6")
    private SmAttribute selectorAtt;

    @objid ("873cfcfd-2e9a-4479-b47d-4feeb47dca8a")
    private SmDependency ownerDep;

    @objid ("4e554240-7234-4de4-af62-37b1d6e717b8")
    private SmDependency representedDep;

    @objid ("33b0020b-d043-4608-bdfa-c08c03dbfb88")
    private SmDependency startedDep;

    @objid ("36b29270-dd1f-4a84-990e-7a839895a8a3")
    private SmDependency endedDep;

    @objid ("efacf42a-c0d8-413f-adef-d408f5d6ac6e")
    public CommunicationNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f5ea5fd7-6965-430a-831d-40b176c5d3fb")
    @Override
    public String getName() {
        return "CommunicationNode";
    }

    @objid ("e1495814-9285-45dd-aec4-866dd8dd6598")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("621b3819-d32f-4062-a652-0d6f35af6397")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CommunicationNode.class;
    }

    @objid ("9c215267-e021-446e-85c3-377cbd9cb064")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("1c91b9b8-2c0c-46bb-8257-3f5a36941d5e")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("973ce7e2-b108-4617-9576-ee23f6ff03a1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new CommunicationNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.selectorAtt = new SelectorSmAttribute();
        this.selectorAtt.init("Selector", this, String.class );
        registerAttribute(this.selectorAtt);
        
        
        // Initialize and register the SmDependency
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(CommunicationInteraction.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(Instance.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedDep);
        
        this.startedDep = new StartedSmDependency();
        this.startedDep.init("Started", this, metamodel.getMClass(CommunicationChannel.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT, SmDirective.SMCDTODELETE);
        registerDependency(this.startedDep);
        
        this.endedDep = new EndedSmDependency();
        this.endedDep.init("Ended", this, metamodel.getMClass(CommunicationChannel.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.endedDep);
    }

    @objid ("2163fac9-e384-40b1-a29c-dbcb30b553f3")
    public SmAttribute getSelectorAtt() {
        if (this.selectorAtt == null) {
        	this.selectorAtt = this.getAttributeDef("Selector");
        }
        return this.selectorAtt;
    }

    @objid ("125b6915-c1d8-4797-a62f-13f6359028e0")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("0d8ced35-9b49-4683-a87a-0263e7536f1c")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("6fd8374c-03b8-4dc6-969d-4ed489d2c4a7")
    public SmDependency getStartedDep() {
        if (this.startedDep == null) {
        	this.startedDep = this.getDependencyDef("Started");
        }
        return this.startedDep;
    }

    @objid ("107dda31-0f6b-4330-93c8-e545cb26267a")
    public SmDependency getEndedDep() {
        if (this.endedDep == null) {
        	this.endedDep = this.getDependencyDef("Ended");
        }
        return this.endedDep;
    }

    @objid ("c0884110-3fd3-4da7-86eb-f6b34fc8e63c")
    private static class CommunicationNodeObjectFactory implements ISmObjectFactory {
        @objid ("809d71a9-3f58-4098-8085-a043d4404cc3")
        private CommunicationNodeSmClass smClass;

        @objid ("434cfc93-9d75-4716-b11e-8a429b712598")
        public CommunicationNodeObjectFactory(CommunicationNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9f953708-5d3b-415a-a9b3-074b0ea3b2b2")
        @Override
        public ISmObjectData createData() {
            return new CommunicationNodeData(this.smClass);
        }

        @objid ("3c1125ef-de90-4f89-999b-fa9c7c99f13d")
        @Override
        public SmObjectImpl createImpl() {
            return new CommunicationNodeImpl();
        }

    }

    @objid ("fee409d4-004c-49fd-8645-9e7778a471b3")
    public static class SelectorSmAttribute extends SmAttribute {
        @objid ("e29a6cf4-3fd7-46c6-b8fd-70ce492a22a0")
        public Object getValue(ISmObjectData data) {
            return ((CommunicationNodeData) data).mSelector;
        }

        @objid ("05f0977f-175b-4560-8c41-dd84f439ef3b")
        public void setValue(ISmObjectData data, Object value) {
            ((CommunicationNodeData) data).mSelector = value;
        }

    }

    @objid ("f3b5abb7-378a-4bdd-ae5c-a328e83f964d")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("3875772a-97f1-4408-9e18-378a66633f90")
        private SmDependency symetricDep;

        @objid ("b54d0743-fa72-42bc-90ad-cd1eed3ec5ad")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationNodeData) data).mOwner;
        }

        @objid ("43524e4e-1e0e-4836-8157-bca5c621ac33")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationNodeData) data).mOwner = value;
        }

        @objid ("f14295e6-5b76-48bc-bf39-da28d837f1ca")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationInteractionSmClass)this.getTarget()).getOwnedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("20818289-f575-46b1-b85f-5135e82d0819")
    public static class RepresentedSmDependency extends SmSingleDependency {
        @objid ("943c21d6-8058-4ba8-aae2-3ce0cfd4ed5c")
        private SmDependency symetricDep;

        @objid ("7ee8b087-0bee-4309-883b-c251b8a4c8ce")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationNodeData) data).mRepresented;
        }

        @objid ("d455f9e1-0ff7-4aef-a550-95e4d897c3d6")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationNodeData) data).mRepresented = value;
        }

        @objid ("b7f1415a-8c96-4e91-88ec-906f57b38ff7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getRepresentedCommunicationNodeDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8a197959-d3f1-407f-9794-3e565d155a07")
    public static class StartedSmDependency extends SmMultipleDependency {
        @objid ("ac53a773-547e-4be8-87d3-0d12449e4d70")
        private SmDependency symetricDep;

        @objid ("b8813c31-cbfd-4d19-9d01-89ac51ba86cc")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CommunicationNodeData)data).mStarted != null)? ((CommunicationNodeData)data).mStarted:SmMultipleDependency.EMPTY;
        }

        @objid ("e6b18c45-27e8-4a48-aefd-56651a0e7cc1")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CommunicationNodeData) data).mStarted = values;
        }

        @objid ("4a10f825-dd22-4e4b-b8fa-5cb1a26ffdcd")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationChannelSmClass)this.getTarget()).getStartDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8a240d7e-6b99-4f87-9e38-4bace844fe87")
    public static class EndedSmDependency extends SmMultipleDependency {
        @objid ("ec7f99aa-17bf-470f-8a3f-66c0c4649cbc")
        private SmDependency symetricDep;

        @objid ("60727768-6b97-4625-b632-405e8b559a21")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CommunicationNodeData)data).mEnded != null)? ((CommunicationNodeData)data).mEnded:SmMultipleDependency.EMPTY;
        }

        @objid ("7967b236-15c6-44d6-b2ec-a8d770836844")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CommunicationNodeData) data).mEnded = values;
        }

        @objid ("9fbc60e4-3a9e-4450-b82e-9aa7eb8ff793")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationChannelSmClass)this.getTarget()).getEndDep();
            }
            return this.symetricDep;
        }

    }

}
