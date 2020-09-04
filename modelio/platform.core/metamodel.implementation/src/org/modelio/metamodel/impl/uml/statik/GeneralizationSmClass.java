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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralizationData;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
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

@objid ("0eb120f2-b0a8-4ed0-846c-641a3466a664")
public class GeneralizationSmClass extends UmlModelElementSmClass {
    @objid ("c0686787-536c-4b61-a981-516d8baa4a3c")
    private SmAttribute discriminatorAtt;

    @objid ("3a0f651c-f9e4-4ae2-877a-3ed3ec1ed1eb")
    private SmDependency superTypeDep;

    @objid ("6fd6009e-3a3e-4cb7-ba02-49acb88cb894")
    private SmDependency subTypeDep;

    @objid ("de517abb-449e-4069-87e6-3d5c4ae96f56")
    public GeneralizationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9f7a1de9-168b-4a7c-8fc3-eea9ed00d3df")
    @Override
    public String getName() {
        return "Generalization";
    }

    @objid ("54c27434-9a02-40ab-8175-f3c740ecdded")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("89c00d53-02e4-4766-85cd-9184f5a363cb")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Generalization.class;
    }

    @objid ("6c7193f6-d2a8-4f11-979b-6ff24ca202b5")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("681685bf-9168-4660-b821-1249ad94b2cc")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("7d92c51a-1df2-4c21-a4ba-5647aef6ee0a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new GeneralizationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.discriminatorAtt = new DiscriminatorSmAttribute();
        this.discriminatorAtt.init("Discriminator", this, String.class );
        registerAttribute(this.discriminatorAtt);
        
        
        // Initialize and register the SmDependency
        this.superTypeDep = new SuperTypeSmDependency();
        this.superTypeDep.init("SuperType", this, metamodel.getMClass(NameSpace.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.superTypeDep);
        
        this.subTypeDep = new SubTypeSmDependency();
        this.subTypeDep.init("SubType", this, metamodel.getMClass(NameSpace.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.subTypeDep);
    }

    @objid ("308d2c0e-5ae6-4810-b4fd-ca49d32c6ab9")
    public SmAttribute getDiscriminatorAtt() {
        if (this.discriminatorAtt == null) {
        	this.discriminatorAtt = this.getAttributeDef("Discriminator");
        }
        return this.discriminatorAtt;
    }

    @objid ("607eff1b-54bf-46f0-b1b2-1ba2b2ce7ddb")
    public SmDependency getSuperTypeDep() {
        if (this.superTypeDep == null) {
        	this.superTypeDep = this.getDependencyDef("SuperType");
        }
        return this.superTypeDep;
    }

    @objid ("ca5a7386-ab17-46df-a443-bd40b77ed061")
    public SmDependency getSubTypeDep() {
        if (this.subTypeDep == null) {
        	this.subTypeDep = this.getDependencyDef("SubType");
        }
        return this.subTypeDep;
    }

    @objid ("1a950796-2e4c-4c19-a229-e2ca01f9a969")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("f072cfcf-fdaf-4308-a83c-5f3d23019895")
    private static class GeneralizationObjectFactory implements ISmObjectFactory {
        @objid ("91c76a99-0e71-4034-91f2-61ed7f0a6715")
        private GeneralizationSmClass smClass;

        @objid ("4a0579c6-427b-469a-8522-daccf78a43a9")
        public GeneralizationObjectFactory(GeneralizationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("97f1a4c4-81f6-4e74-8327-504b14ab15d3")
        @Override
        public ISmObjectData createData() {
            return new GeneralizationData(this.smClass);
        }

        @objid ("5a7c9b21-2aeb-45d1-8595-1781a2afdab3")
        @Override
        public SmObjectImpl createImpl() {
            return new GeneralizationImpl();
        }

    }

    @objid ("64096e22-9e9c-47ce-bd55-813ca8922491")
    public static class DiscriminatorSmAttribute extends SmAttribute {
        @objid ("b2d1eb89-d3b0-4b4d-a2a4-aeafc7c7b969")
        public Object getValue(ISmObjectData data) {
            return ((GeneralizationData) data).mDiscriminator;
        }

        @objid ("bc512bb1-705c-4130-8f0f-150e6119116c")
        public void setValue(ISmObjectData data, Object value) {
            ((GeneralizationData) data).mDiscriminator = value;
        }

    }

    @objid ("365dbbb0-cd49-40d7-80d2-4d958b361ef6")
    public static class SuperTypeSmDependency extends SmSingleDependency {
        @objid ("36e9bb74-9e7f-4193-96bc-d2318b14b3fe")
        private SmDependency symetricDep;

        @objid ("0ee34baa-37d7-4ff2-a5b5-0b92f64fab2d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GeneralizationData) data).mSuperType;
        }

        @objid ("5079eebf-079d-41d2-ac88-ba62a189ade8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GeneralizationData) data).mSuperType = value;
        }

        @objid ("92e964ba-fc56-41a4-8365-a5fc3779a428")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getSpecializationDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("d78dcb3f-9dee-4889-b2ec-744bfdfdaa2a")
    public static class SubTypeSmDependency extends SmSingleDependency {
        @objid ("8f7cddee-ff1a-4c1a-8425-c1df6a8a30c2")
        private SmDependency symetricDep;

        @objid ("3cc74024-6533-4ce2-b939-28ed34f7ee29")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((GeneralizationData) data).mSubType;
        }

        @objid ("e8e18d63-552f-410d-a38f-db589a8638c0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((GeneralizationData) data).mSubType = value;
        }

        @objid ("440b7020-a458-4d68-b1cd-bf6895b47e06")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getParentDep();
            }
            return this.symetricDep;
        }

    }

}
