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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.StructuredActivityNodeData;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
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
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("5c8fac83-8d4d-4440-9e52-317a1ecc923d")
public class StructuredActivityNodeSmClass extends ActivityActionSmClass {
    @objid ("49fad737-fbb3-43b5-9f6f-691f4b083663")
    private SmAttribute mustIsolateAtt;

    @objid ("527b4098-69c3-4896-83a6-5fcd0df3360e")
    private SmDependency bodyDep;

    @objid ("52fcee85-69aa-4407-921d-d5d6bde38801")
    public StructuredActivityNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("af9bfcd5-7eda-4a9d-980e-b053a209a616")
    @Override
    public String getName() {
        return "StructuredActivityNode";
    }

    @objid ("b8cb4b3e-1647-4f5d-9f15-cada2172c375")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e2a1b057-21d6-48a4-8c30-4a4f13ef5ef3")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return StructuredActivityNode.class;
    }

    @objid ("649aa4cd-22e0-45f3-afdd-fc627a97f2f5")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4c50c175-ab4f-46a5-b981-5f06ddca5947")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("26399ea6-823b-40bf-aae6-8e80c4eac7e1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityAction.MQNAME);
        this.registerFactory(new StructuredActivityNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.mustIsolateAtt = new MustIsolateSmAttribute();
        this.mustIsolateAtt.init("MustIsolate", this, Boolean.class );
        registerAttribute(this.mustIsolateAtt);
        
        
        // Initialize and register the SmDependency
        this.bodyDep = new BodySmDependency();
        this.bodyDep.init("Body", this, metamodel.getMClass(ActivityNode.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.bodyDep);
    }

    @objid ("37bbbbda-6a21-4b51-a364-64f5bc4415e4")
    public SmAttribute getMustIsolateAtt() {
        if (this.mustIsolateAtt == null) {
        	this.mustIsolateAtt = this.getAttributeDef("MustIsolate");
        }
        return this.mustIsolateAtt;
    }

    @objid ("d068d628-7da7-40cd-8aac-f0e414265c12")
    public SmDependency getBodyDep() {
        if (this.bodyDep == null) {
        	this.bodyDep = this.getDependencyDef("Body");
        }
        return this.bodyDep;
    }

    @objid ("5c15601c-2ac1-411e-b0db-ded967586412")
    private static class StructuredActivityNodeObjectFactory implements ISmObjectFactory {
        @objid ("9ba37656-5e57-4084-8e5d-45467777cbbb")
        private StructuredActivityNodeSmClass smClass;

        @objid ("2a731580-f4de-45a9-846d-2f8fbf5a86ca")
        public StructuredActivityNodeObjectFactory(StructuredActivityNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f51c9ce6-8a1d-4e05-a0e1-773578fcd7b3")
        @Override
        public ISmObjectData createData() {
            return new StructuredActivityNodeData(this.smClass);
        }

        @objid ("4e335a1c-b905-42a7-b210-9937d30c39a5")
        @Override
        public SmObjectImpl createImpl() {
            return new StructuredActivityNodeImpl();
        }

    }

    @objid ("e454e4c6-6768-4433-aff9-c680503c2b1f")
    public static class MustIsolateSmAttribute extends SmAttribute {
        @objid ("a804762a-96a2-4845-a87c-e07a6fdfedb4")
        public Object getValue(ISmObjectData data) {
            return ((StructuredActivityNodeData) data).mMustIsolate;
        }

        @objid ("4a510031-415b-43dc-b718-16b22132ce60")
        public void setValue(ISmObjectData data, Object value) {
            ((StructuredActivityNodeData) data).mMustIsolate = value;
        }

    }

    @objid ("a79feadb-a6fd-4a94-909d-668700f3b076")
    public static class BodySmDependency extends SmMultipleDependency {
        @objid ("31272ec4-3f3c-4208-816b-085f657c8845")
        private SmDependency symetricDep;

        @objid ("3690d980-32cd-4525-ab34-ed5f4fecabb5")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((StructuredActivityNodeData)data).mBody != null)? ((StructuredActivityNodeData)data).mBody:SmMultipleDependency.EMPTY;
        }

        @objid ("58aa83ca-7183-4902-b2f5-6b46324b3073")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((StructuredActivityNodeData) data).mBody = values;
        }

        @objid ("1154f2ec-d459-4546-8df5-56f9f90c47f8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ActivityNodeSmClass)this.getTarget()).getOwnerNodeDep();
            }
            return this.symetricDep;
        }

    }

}
