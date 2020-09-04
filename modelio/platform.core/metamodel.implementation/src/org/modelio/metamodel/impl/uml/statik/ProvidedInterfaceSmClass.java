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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.InterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkEndSmClass;
import org.modelio.metamodel.impl.uml.statik.PortSmClass;
import org.modelio.metamodel.impl.uml.statik.ProvidedInterfaceData;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
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

@objid ("f116ca1f-783f-4238-a733-e36b905a594b")
public class ProvidedInterfaceSmClass extends UmlModelElementSmClass {
    @objid ("074f09c3-ff18-43a8-ae7a-3b5b1c1d8645")
    private SmDependency providedElementDep;

    @objid ("88e41171-29bf-477b-a3c6-77aa396eec2f")
    private SmDependency providingDep;

    @objid ("d59d2577-9ab0-4ff9-868d-0bfbaee9b180")
    private SmDependency consumerDep;

    @objid ("180dd4ae-3dde-46e5-b821-66bb9b634fd6")
    private SmDependency naryConsumerDep;

    @objid ("4c72ee7b-0c9d-40bf-8444-ac24dee3a1f2")
    public ProvidedInterfaceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2ecf8936-fccb-4669-835e-079482bbac04")
    @Override
    public String getName() {
        return "ProvidedInterface";
    }

    @objid ("3f9eee73-55e0-4a3d-9801-dc9faa377ea5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("209d3b5e-6c2b-4b5b-a8a8-4651a18651a5")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ProvidedInterface.class;
    }

    @objid ("6a72db15-1902-4fc8-bade-82d8e03a36c0")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("2a827720-fcf2-4f3e-8e98-c0f34e36ffff")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("24927823-0419-4a20-a7b0-1587c6f16903")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ProvidedInterfaceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.providedElementDep = new ProvidedElementSmDependency();
        this.providedElementDep.init("ProvidedElement", this, metamodel.getMClass(Interface.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.providedElementDep);
        
        this.providingDep = new ProvidingSmDependency();
        this.providingDep.init("Providing", this, metamodel.getMClass(Port.MQNAME), 1, 1 );
        registerDependency(this.providingDep);
        
        this.consumerDep = new ConsumerSmDependency();
        this.consumerDep.init("Consumer", this, metamodel.getMClass(LinkEnd.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.consumerDep);
        
        this.naryConsumerDep = new NaryConsumerSmDependency();
        this.naryConsumerDep.init("NaryConsumer", this, metamodel.getMClass(NaryLinkEnd.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.naryConsumerDep);
    }

    @objid ("9fe1a489-61e4-4c73-a4cb-88233fd50699")
    public SmDependency getProvidedElementDep() {
        if (this.providedElementDep == null) {
        	this.providedElementDep = this.getDependencyDef("ProvidedElement");
        }
        return this.providedElementDep;
    }

    @objid ("bf029ad3-3a0d-4fc1-bef4-474f1c0c1075")
    public SmDependency getProvidingDep() {
        if (this.providingDep == null) {
        	this.providingDep = this.getDependencyDef("Providing");
        }
        return this.providingDep;
    }

    @objid ("a40dcd1e-af07-4960-8c92-7116f9037435")
    public SmDependency getConsumerDep() {
        if (this.consumerDep == null) {
        	this.consumerDep = this.getDependencyDef("Consumer");
        }
        return this.consumerDep;
    }

    @objid ("c70d0972-3335-445a-8068-6b40cfb478fb")
    public SmDependency getNaryConsumerDep() {
        if (this.naryConsumerDep == null) {
        	this.naryConsumerDep = this.getDependencyDef("NaryConsumer");
        }
        return this.naryConsumerDep;
    }

    @objid ("d16cd277-142c-45b1-966d-7bbf104a39a1")
    private static class ProvidedInterfaceObjectFactory implements ISmObjectFactory {
        @objid ("ff005ec5-cac9-439b-82b4-3c1cbed14167")
        private ProvidedInterfaceSmClass smClass;

        @objid ("bd17c483-2be5-4609-9860-59f4d49046b0")
        public ProvidedInterfaceObjectFactory(ProvidedInterfaceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a597cffb-da99-4f9d-a49d-449374e16001")
        @Override
        public ISmObjectData createData() {
            return new ProvidedInterfaceData(this.smClass);
        }

        @objid ("968e475e-5487-4268-a2d4-ee9e077117dc")
        @Override
        public SmObjectImpl createImpl() {
            return new ProvidedInterfaceImpl();
        }

    }

    @objid ("c30e375c-cec6-456c-8a82-0326071a3891")
    public static class ProvidedElementSmDependency extends SmMultipleDependency {
        @objid ("e97a7b0f-0db4-48e5-997d-f6dfb38dfb7e")
        private SmDependency symetricDep;

        @objid ("4734ecfa-e12f-4b7c-9ab4-1d98f95db108")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProvidedInterfaceData)data).mProvidedElement != null)? ((ProvidedInterfaceData)data).mProvidedElement:SmMultipleDependency.EMPTY;
        }

        @objid ("3f4963a3-79d2-4b22-82c6-8d6acae43e8f")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProvidedInterfaceData) data).mProvidedElement = values;
        }

        @objid ("8b80612e-3155-457a-b5d4-10c9f27cac1b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InterfaceSmClass)this.getTarget()).getProvidingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("11724740-e012-47e2-9281-726b68c6ce1d")
    public static class ProvidingSmDependency extends SmSingleDependency {
        @objid ("da8e9e2a-4ac4-4170-9de0-a8801e55a8a1")
        private SmDependency symetricDep;

        @objid ("19412283-183d-4622-924e-9ee2de5216c8")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ProvidedInterfaceData) data).mProviding;
        }

        @objid ("055068d3-e7e4-432c-a541-07c652556512")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ProvidedInterfaceData) data).mProviding = value;
        }

        @objid ("a19b77a7-fcb2-4a5b-9829-73f45b01a952")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PortSmClass)this.getTarget()).getProvidedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("95978565-5834-4061-b9f7-9958d1de3f12")
    public static class ConsumerSmDependency extends SmMultipleDependency {
        @objid ("e3ced6cc-b153-439d-be37-d1301c4e3498")
        private SmDependency symetricDep;

        @objid ("98b9c16b-35ba-4ffa-80b7-f78ca7b6359c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProvidedInterfaceData)data).mConsumer != null)? ((ProvidedInterfaceData)data).mConsumer:SmMultipleDependency.EMPTY;
        }

        @objid ("614f06d0-8910-4094-a166-0151b67b9df9")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProvidedInterfaceData) data).mConsumer = values;
        }

        @objid ("6e7a7eef-0f10-4e67-815d-61c2c693f07e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getProviderDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1e73fefa-9135-4b84-95c5-b8f2690a0755")
    public static class NaryConsumerSmDependency extends SmMultipleDependency {
        @objid ("9a9a47d4-5d70-40da-8680-7860c459b1da")
        private SmDependency symetricDep;

        @objid ("f5c3aa04-2598-45ed-b6e5-902c5f98f5f0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProvidedInterfaceData)data).mNaryConsumer != null)? ((ProvidedInterfaceData)data).mNaryConsumer:SmMultipleDependency.EMPTY;
        }

        @objid ("d90ef870-3989-4de4-a49e-9d521e9bf4bf")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProvidedInterfaceData) data).mNaryConsumer = values;
        }

        @objid ("f9fc60d5-ff73-4adf-8f02-fa70465fbea7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkEndSmClass)this.getTarget()).getProviderDep();
            }
            return this.symetricDep;
        }

    }

}
