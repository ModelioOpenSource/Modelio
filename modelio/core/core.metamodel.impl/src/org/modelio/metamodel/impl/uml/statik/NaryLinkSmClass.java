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
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationChannelSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkData;
import org.modelio.metamodel.impl.uml.statik.NaryLinkEndSmClass;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
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

@objid ("56a1be66-9aa3-4aee-a7a1-458bda97db4a")
public class NaryLinkSmClass extends UmlModelElementSmClass {
    @objid ("f5e8d70b-2198-428f-9176-7b6557d9b85f")
    private SmDependency naryLinkEndDep;

    @objid ("a3509e02-cbd7-481f-8835-8cb9f916da91")
    private SmDependency modelDep;

    @objid ("af2ba76f-ca7c-415d-93b2-c4f001f3d31c")
    private SmDependency realizedInformationFlowDep;

    @objid ("e66b97e0-0819-4cab-bb9e-c2d8b70f5e3c")
    private SmDependency sentDep;

    @objid ("70dbc32e-08de-4b09-ab93-dbbf14f22484")
    public NaryLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("96e60983-c70e-4aea-b73b-568a2c9bd502")
    @Override
    public String getName() {
        return "NaryLink";
    }

    @objid ("c13b3113-6c8d-4d32-b770-f1c6b2039279")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("47f1ca24-a411-4f21-9319-b754b9aac8c1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NaryLink.class;
    }

    @objid ("7d9a7cd6-a8ea-4090-bb23-597ea1293eb1")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("8c86a451-56ae-4ec7-8c41-7f36b1433612")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6a842e5e-69ae-40f8-9c57-60fe7b14b88e")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new NaryLinkObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.naryLinkEndDep = new NaryLinkEndSmDependency();
        this.naryLinkEndDep.init("NaryLinkEnd", this, metamodel.getMClass(NaryLinkEnd.MQNAME), 0, -1 , SmDirective.SMCDSHAREDCOMPONENT);
        registerDependency(this.naryLinkEndDep);
        
        this.modelDep = new ModelSmDependency();
        this.modelDep.init("Model", this, metamodel.getMClass(NaryAssociation.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.modelDep);
        
        this.realizedInformationFlowDep = new RealizedInformationFlowSmDependency();
        this.realizedInformationFlowDep.init("RealizedInformationFlow", this, metamodel.getMClass(InformationFlow.MQNAME), 0, -1 );
        registerDependency(this.realizedInformationFlowDep);
        
        this.sentDep = new SentSmDependency();
        this.sentDep.init("Sent", this, metamodel.getMClass(CommunicationChannel.MQNAME), 0, -1 );
        registerDependency(this.sentDep);
    }

    @objid ("6412642f-ae9c-41a8-af95-38a3599874fa")
    public SmDependency getNaryLinkEndDep() {
        if (this.naryLinkEndDep == null) {
        	this.naryLinkEndDep = this.getDependencyDef("NaryLinkEnd");
        }
        return this.naryLinkEndDep;
    }

    @objid ("5c81fe4c-39c0-4545-9d53-be1110cc9edd")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("2d64c482-fd9e-4f01-8300-f380dcc5ae3b")
    public SmDependency getRealizedInformationFlowDep() {
        if (this.realizedInformationFlowDep == null) {
        	this.realizedInformationFlowDep = this.getDependencyDef("RealizedInformationFlow");
        }
        return this.realizedInformationFlowDep;
    }

    @objid ("42de236e-a7c6-4e6d-8b64-c85a8985e77f")
    public SmDependency getSentDep() {
        if (this.sentDep == null) {
        	this.sentDep = this.getDependencyDef("Sent");
        }
        return this.sentDep;
    }

    @objid ("daf5d772-4002-46a2-9e0e-903989292865")
    private static class NaryLinkObjectFactory implements ISmObjectFactory {
        @objid ("6abe6569-b8d8-449c-947e-364dacdea5d7")
        private NaryLinkSmClass smClass;

        @objid ("9ac4857c-c4e1-4939-b06d-737decc2f25d")
        public NaryLinkObjectFactory(NaryLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("76d3f31a-9c88-4197-8aed-26ad0dac0219")
        @Override
        public ISmObjectData createData() {
            return new NaryLinkData(this.smClass);
        }

        @objid ("7c5a29df-327d-4216-ab2f-4713f6bf7197")
        @Override
        public SmObjectImpl createImpl() {
            return new NaryLinkImpl();
        }

    }

    @objid ("ed377a92-b085-4726-9ee9-5f7082a7b6c3")
    public static class NaryLinkEndSmDependency extends SmMultipleDependency {
        @objid ("54f97219-4017-4602-a706-661bb2c89c1e")
        private SmDependency symetricDep;

        @objid ("2837f1a0-65c6-41f5-8f42-fd32745e74a9")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NaryLinkData)data).mNaryLinkEnd != null)? ((NaryLinkData)data).mNaryLinkEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("e1bd0edd-0a23-4c9e-ac80-5e8690148b6c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NaryLinkData) data).mNaryLinkEnd = values;
        }

        @objid ("ca87bc6e-9163-459e-9e0d-4e93ef8bba51")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkEndSmClass)this.getTarget()).getNaryLinkDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("02cdd21a-e5eb-496d-af4d-438db75f8b8c")
    public static class ModelSmDependency extends SmSingleDependency {
        @objid ("46d63af0-57d7-4848-b771-ffe9e8f45ba2")
        private SmDependency symetricDep;

        @objid ("6d4ffd1d-0e3e-4661-a05b-f02f74b15f21")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryLinkData) data).mModel;
        }

        @objid ("684f7a56-f557-47f5-beca-60933695428a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryLinkData) data).mModel = value;
        }

        @objid ("f0644876-09d2-4a4e-98e8-2c05c396e96a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryAssociationSmClass)this.getTarget()).getOccurenceDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e2f938e8-923b-43dd-9575-dbfef4105318")
    public static class RealizedInformationFlowSmDependency extends SmMultipleDependency {
        @objid ("e8c592ce-6574-4ff8-81ef-f655c7c1b6f5")
        private SmDependency symetricDep;

        @objid ("54647df9-3a2c-4b0d-a92d-87b4e7fe18c7")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NaryLinkData)data).mRealizedInformationFlow != null)? ((NaryLinkData)data).mRealizedInformationFlow:SmMultipleDependency.EMPTY;
        }

        @objid ("c922c2a6-e140-4a7d-b4df-3d4c235fa417")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NaryLinkData) data).mRealizedInformationFlow = values;
        }

        @objid ("4efb64ac-a30e-49b7-99fb-c86499f48fa5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InformationFlowSmClass)this.getTarget()).getRealizingNaryLinkDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("91d3602e-5aae-4cde-8a76-4a1b5c9d3551")
    public static class SentSmDependency extends SmMultipleDependency {
        @objid ("8a23bef6-3f34-4cee-a4a0-2685544c0f5a")
        private SmDependency symetricDep;

        @objid ("912c7384-b9b2-4929-a3d0-8bb322afbab0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NaryLinkData)data).mSent != null)? ((NaryLinkData)data).mSent:SmMultipleDependency.EMPTY;
        }

        @objid ("76073a6e-1521-408c-90c1-144903074c55")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NaryLinkData) data).mSent = values;
        }

        @objid ("398c146d-4181-42e7-85ec-f97e24a2c497")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationChannelSmClass)this.getTarget()).getNaryChannelDep();
            }
            return this.symetricDep;
        }

    }

}
