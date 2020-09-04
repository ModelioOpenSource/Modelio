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
package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c5216376-c086-4856-b16c-700f39c41c4d")
public class OpaqueBehaviorSmClass extends BehaviorSmClass {
    @objid ("d16badff-6654-41d6-b0ac-88459786041b")
    private SmAttribute bodyAtt;

    @objid ("51240300-395e-4454-87f9-d4f0b6a408ed")
    public OpaqueBehaviorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("3ff3bc7b-e717-4550-91a1-7916560a245a")
    @Override
    public String getName() {
        return "OpaqueBehavior";
    }

    @objid ("fff14c2b-ac53-4122-9010-04d8f936bc44")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2906b308-352f-4203-b7d8-a8f57e09f10a")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return OpaqueBehavior.class;
    }

    @objid ("cfd52edd-fc03-46bd-aa25-0fa893a8656e")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("62d9cda9-eae5-4018-b800-753230711074")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("cf293e14-9028-4b51-9f08-6f494ec49ca5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Behavior.MQNAME);
        this.registerFactory(new OpaqueBehaviorObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.bodyAtt = new BodySmAttribute();
        this.bodyAtt.init("Body", this, String.class );
        registerAttribute(this.bodyAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("b8f7eb20-11ba-4520-932c-7ed690235ef5")
    public SmAttribute getBodyAtt() {
        if (this.bodyAtt == null) {
        	this.bodyAtt = this.getAttributeDef("Body");
        }
        return this.bodyAtt;
    }

    @objid ("83203c78-6bf1-4e46-9788-30356058fa98")
    private static class OpaqueBehaviorObjectFactory implements ISmObjectFactory {
        @objid ("8cdde5aa-fa7a-4d99-a8fe-309e4d734ce5")
        private OpaqueBehaviorSmClass smClass;

        @objid ("99dc9ef2-8134-43bf-96bd-61eeb5cda5be")
        public OpaqueBehaviorObjectFactory(OpaqueBehaviorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c108cf5e-5d97-43cf-9ef1-ebc8befa1159")
        @Override
        public ISmObjectData createData() {
            return new OpaqueBehaviorData(this.smClass);
        }

        @objid ("46427227-7b99-472b-9ef2-44beece2d3b8")
        @Override
        public SmObjectImpl createImpl() {
            return new OpaqueBehaviorImpl();
        }

    }

    @objid ("a674c3fa-a3eb-4a60-a350-e2c86cb9b91a")
    public static class BodySmAttribute extends SmAttribute {
        @objid ("eaac8f9b-61e7-459d-9c95-cddb78cc9949")
        public Object getValue(ISmObjectData data) {
            return ((OpaqueBehaviorData) data).mBody;
        }

        @objid ("9867659b-8ac3-4c5d-99cd-d948a8ca4603")
        public void setValue(ISmObjectData data, Object value) {
            ((OpaqueBehaviorData) data).mBody = value;
        }

    }

}
