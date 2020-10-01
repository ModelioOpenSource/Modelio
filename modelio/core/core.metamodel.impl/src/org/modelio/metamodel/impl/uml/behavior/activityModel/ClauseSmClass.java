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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ClauseData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ConditionalNodeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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

@objid ("915a23ae-4b3c-45d1-84e8-f84a3c43f56e")
public class ClauseSmClass extends UmlModelElementSmClass {
    @objid ("ab304836-2743-434f-9b31-316cf1b555ba")
    private SmAttribute testAtt;

    @objid ("f3e2dd6f-ebf2-4e42-b9e2-cd7059bcff2d")
    private SmDependency bodyDep;

    @objid ("ee19f2bc-2dd9-4c29-84fe-4d39a8dfe948")
    private SmDependency ownerDep;

    @objid ("128b8551-226b-451e-8c14-1297a504eb7e")
    public ClauseSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d5ff48e7-c864-4956-9eb4-3526040b1a5e")
    @Override
    public String getName() {
        return "Clause";
    }

    @objid ("8577ff84-5509-4468-a40d-78e5e6c2ed8f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("656b5482-dfe0-436d-a0c8-f9bfca00e088")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Clause.class;
    }

    @objid ("bc6e1b06-d664-4292-9a5a-2fc5a2a8e8c9")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("43fa3d09-875a-413a-9505-0de26f2f6352")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6c1807cb-0218-4714-8a23-469582653025")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ClauseObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.testAtt = new TestSmAttribute();
        this.testAtt.init("Test", this, String.class );
        registerAttribute(this.testAtt);
        
        
        // Initialize and register the SmDependency
        this.bodyDep = new BodySmDependency();
        this.bodyDep.init("Body", this, metamodel.getMClass(ActivityNode.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.bodyDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(ConditionalNode.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("2d7b2602-69df-43f4-a8b2-34f530c9e918")
    public SmAttribute getTestAtt() {
        if (this.testAtt == null) {
        	this.testAtt = this.getAttributeDef("Test");
        }
        return this.testAtt;
    }

    @objid ("40fb067f-ddf6-4974-834b-ece46be175c9")
    public SmDependency getBodyDep() {
        if (this.bodyDep == null) {
        	this.bodyDep = this.getDependencyDef("Body");
        }
        return this.bodyDep;
    }

    @objid ("fbadccc7-8f3d-4905-b28c-b3b51d059b3c")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("fee041f9-8e20-4daf-842e-f02a7d10fbd4")
    private static class ClauseObjectFactory implements ISmObjectFactory {
        @objid ("2a96cf8d-6fb4-4e3a-a6f4-ce44b37db707")
        private ClauseSmClass smClass;

        @objid ("a8f41dce-e6a7-4783-aaac-726e728ee2fa")
        public ClauseObjectFactory(ClauseSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("3d1f6566-e448-40e2-ba4c-f1a0acaa154b")
        @Override
        public ISmObjectData createData() {
            return new ClauseData(this.smClass);
        }

        @objid ("f468ee24-17f7-4f0f-b725-61246db3bb94")
        @Override
        public SmObjectImpl createImpl() {
            return new ClauseImpl();
        }

    }

    @objid ("935c9bc1-149c-4123-908c-33b008660d2e")
    public static class TestSmAttribute extends SmAttribute {
        @objid ("f189b75d-c117-4f67-8756-35914a05da7f")
        public Object getValue(ISmObjectData data) {
            return ((ClauseData) data).mTest;
        }

        @objid ("2a59e11c-9c38-4843-91b6-65b3f523005a")
        public void setValue(ISmObjectData data, Object value) {
            ((ClauseData) data).mTest = value;
        }

    }

    @objid ("c4f323e0-8f2c-43f5-8860-f28a0e95d768")
    public static class BodySmDependency extends SmMultipleDependency {
        @objid ("13c91351-32c0-473e-8bb7-d8cbc83ebd8e")
        private SmDependency symetricDep;

        @objid ("a35865bc-1f4b-4060-97b0-627764bd96b2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ClauseData)data).mBody != null)? ((ClauseData)data).mBody:SmMultipleDependency.EMPTY;
        }

        @objid ("48aadc3f-7acc-467f-ae59-9c6116c83bd7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ClauseData) data).mBody = values;
        }

        @objid ("78f8341e-4872-438a-bb12-e95a9f51771c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityNodeSmClass)this.getTarget()).getOwnerClauseDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("65e5794c-d6f6-45ab-9b29-05d84ed06178")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("3e78d671-7cf3-479f-b633-78b15110ad13")
        private SmDependency symetricDep;

        @objid ("b58ca996-5cdb-41eb-8695-8ce069809445")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ClauseData) data).mOwner;
        }

        @objid ("77e96ca1-03f7-4639-930c-899690b0ceb2")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ClauseData) data).mOwner = value;
        }

        @objid ("e751eeeb-7423-4a6b-94a9-4cc0fcbdc45a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ConditionalNodeSmClass)this.getTarget()).getOwnedClauseDep();
            }
            return this.symetricDep;
        }

    }

}
