/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.impl.uml.statik.InterfaceData;
import org.modelio.metamodel.impl.uml.statik.InterfaceRealizationSmClass;
import org.modelio.metamodel.impl.uml.statik.ProvidedInterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.RequiredInterfaceSmClass;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
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

@objid ("c59234f4-eed7-4a54-9c04-69ac1b210ba1")
public class InterfaceSmClass extends GeneralClassSmClass {
    @objid ("4bec20a4-6f40-4d03-9723-74667f71a455")
    private SmDependency requiringDep;

    @objid ("f088077b-49a6-453e-b560-1e775277f1b1")
    private SmDependency implementedLinkDep;

    @objid ("1156f343-a3e3-4e2f-9ec4-1fa7c20a7f2d")
    private SmDependency providingDep;

    @objid ("63148611-f909-443a-a411-6e0d55bc33bc")
    public InterfaceSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4da73780-5d5b-4048-a0de-61d909b4825b")
    @Override
    public String getName() {
        return "Interface";
    }

    @objid ("f1e66d04-b588-4e56-9232-67c6af75a6da")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("bffcb2d1-becd-468c-9314-096880471f62")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Interface.class;
    }

    @objid ("a1a6209b-2d74-4453-99ab-a76b20235af1")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("3c47ff86-0d61-4ce6-8028-072c5f96bb8b")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("3d86bbab-13f3-4540-bd79-b82d169683f2")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new InterfaceObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.requiringDep = new RequiringSmDependency();
        this.requiringDep.init("Requiring", this, metamodel.getMClass(RequiredInterface.MQNAME), 0, -1 );
        registerDependency(this.requiringDep);
        
        this.implementedLinkDep = new ImplementedLinkSmDependency();
        this.implementedLinkDep.init("ImplementedLink", this, metamodel.getMClass(InterfaceRealization.MQNAME), 0, -1 , SmDirective.SMCDDYNAMIC, SmDirective.SMCDTODELETE);
        registerDependency(this.implementedLinkDep);
        
        this.providingDep = new ProvidingSmDependency();
        this.providingDep.init("Providing", this, metamodel.getMClass(ProvidedInterface.MQNAME), 0, -1 );
        registerDependency(this.providingDep);
    }

    @objid ("d23afdbb-f2c4-48c3-a576-f1f1d4c4f8c0")
    public SmDependency getRequiringDep() {
        if (this.requiringDep == null) {
        	this.requiringDep = this.getDependencyDef("Requiring");
        }
        return this.requiringDep;
    }

    @objid ("5009c740-0e47-474b-9823-86b8a7beb60e")
    public SmDependency getImplementedLinkDep() {
        if (this.implementedLinkDep == null) {
        	this.implementedLinkDep = this.getDependencyDef("ImplementedLink");
        }
        return this.implementedLinkDep;
    }

    @objid ("14b73911-7dc5-4221-a72d-2f8d3c3380b5")
    public SmDependency getProvidingDep() {
        if (this.providingDep == null) {
        	this.providingDep = this.getDependencyDef("Providing");
        }
        return this.providingDep;
    }

    @objid ("5737f9de-e044-43b4-8260-a09bb190d9e9")
    private static class InterfaceObjectFactory implements ISmObjectFactory {
        @objid ("05b591b7-9054-460e-9383-a4f31fd1466c")
        private InterfaceSmClass smClass;

        @objid ("7c4597b8-35d5-4d81-863f-eb5f5fe1fcc2")
        public InterfaceObjectFactory(InterfaceSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8844ec3d-57a6-4076-92de-3784fb1e9953")
        @Override
        public ISmObjectData createData() {
            return new InterfaceData(this.smClass);
        }

        @objid ("abf1015d-05f7-4878-8229-fff3f9c153cc")
        @Override
        public SmObjectImpl createImpl() {
            return new InterfaceImpl();
        }

    }

    @objid ("25c9524a-8ac7-48ca-975f-c27cd35fa98b")
    public static class RequiringSmDependency extends SmMultipleDependency {
        @objid ("e2844604-9af4-4086-85e6-66941015ac5e")
        private SmDependency symetricDep;

        @objid ("5ad40650-da77-434f-a6fc-6d4ebe7e5c5e")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InterfaceData)data).mRequiring != null)? ((InterfaceData)data).mRequiring:SmMultipleDependency.EMPTY;
        }

        @objid ("5116e4e5-efa7-4be7-b2b2-c680bbed6fe6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InterfaceData) data).mRequiring = values;
        }

        @objid ("0d3b57f1-fbdf-4cda-ab76-c95462dcf323")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RequiredInterfaceSmClass)this.getTarget()).getRequiredElementDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c552d7fc-203f-4643-9ed5-68ccc7735479")
    public static class ImplementedLinkSmDependency extends SmMultipleDependency {
        @objid ("b2c05642-c73a-45d7-83d9-033703f426dc")
        private SmDependency symetricDep;

        @objid ("36f94a01-a741-4d55-b635-92dc66741e4c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InterfaceData)data).mImplementedLink != null)? ((InterfaceData)data).mImplementedLink:SmMultipleDependency.EMPTY;
        }

        @objid ("72c76ea3-bbb9-4d3a-bb4a-7a902a2925e0")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InterfaceData) data).mImplementedLink = values;
        }

        @objid ("a43f698e-703b-43bb-b1f3-6c621df47830")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InterfaceRealizationSmClass)this.getTarget()).getImplementedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("436f9b52-892f-4bf6-89da-0ce987609b0e")
    public static class ProvidingSmDependency extends SmMultipleDependency {
        @objid ("188af80a-8e77-4641-99d3-b931a41a3133")
        private SmDependency symetricDep;

        @objid ("786d4c60-a89f-42e7-b846-811fcb0427c0")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InterfaceData)data).mProviding != null)? ((InterfaceData)data).mProviding:SmMultipleDependency.EMPTY;
        }

        @objid ("fa704042-4310-456f-ae80-d508fb7b5391")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InterfaceData) data).mProviding = values;
        }

        @objid ("d537f25d-7356-4dfb-8087-846360584fad")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProvidedInterfaceSmClass)this.getTarget()).getProvidedElementDep();
            }
            return this.symetricDep;
        }

    }

}
