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
package org.modelio.metamodel.impl.uml.behavior.communicationModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationInteractionData;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationNodeSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
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

@objid ("b5a7bf17-d917-40f6-beec-b07d085a45c0")
public class CommunicationInteractionSmClass extends BehaviorSmClass {
    @objid ("ffe39051-39fa-491b-a9ce-41f81f0adec4")
    private SmDependency ownedDep;

    @objid ("2c85ff40-07dc-45b8-9779-0386cfb05cbe")
    public CommunicationInteractionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("94abd4f3-a6d3-483d-850c-4c18b2f47f2d")
    @Override
    public String getName() {
        return "CommunicationInteraction";
    }

    @objid ("5a631e1b-d1e2-46a5-9d7e-d68a12c9d37a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("fb1b039b-5141-4133-979b-2659139df989")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CommunicationInteraction.class;
    }

    @objid ("d3c6811e-a580-47e8-9faa-222ee52eab4a")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("29b81563-d9c3-42a3-b165-2e928a3d86ff")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("43957448-a6a0-4ee8-bd4f-167b236ac235")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new CommunicationInteractionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.ownedDep = new OwnedSmDependency();
        this.ownedDep.init("Owned", this, metamodel.getMClass(CommunicationNode.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedDep);
    }

    @objid ("0479014b-2132-46ff-a58a-1bd525a94df1")
    public SmDependency getOwnedDep() {
        if (this.ownedDep == null) {
        	this.ownedDep = this.getDependencyDef("Owned");
        }
        return this.ownedDep;
    }

    @objid ("9ee9419c-c416-4517-a292-26e577e67f71")
    private static class CommunicationInteractionObjectFactory implements ISmObjectFactory {
        @objid ("ff18d040-6ed0-4c0f-b50e-d4d78e3cd078")
        private CommunicationInteractionSmClass smClass;

        @objid ("a6d1272d-e5c3-4358-89b2-8a803957acab")
        public CommunicationInteractionObjectFactory(CommunicationInteractionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("246b4d89-66e2-4ade-9f02-9f20d55c4c54")
        @Override
        public ISmObjectData createData() {
            return new CommunicationInteractionData(this.smClass);
        }

        @objid ("60310d22-e8c5-4158-b9fe-9148eb1f015d")
        @Override
        public SmObjectImpl createImpl() {
            return new CommunicationInteractionImpl();
        }

    }

    @objid ("9e8f1996-862a-4285-9347-cf4e68505c06")
    public static class OwnedSmDependency extends SmMultipleDependency {
        @objid ("d519941c-b937-4a08-8f9a-c467ba4fafa2")
        private SmDependency symetricDep;

        @objid ("2cb01454-8fa4-4cd5-9fad-73cce5412af5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CommunicationInteractionData)data).mOwned != null)? ((CommunicationInteractionData)data).mOwned:SmMultipleDependency.EMPTY;
        }

        @objid ("9f7838b1-05fc-4bfd-9bf1-0da3b116b9c5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CommunicationInteractionData) data).mOwned = values;
        }

        @objid ("dc17be00-d76b-4937-b63e-4fd310430a9d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationNodeSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}
