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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionNodeKind;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("b33d2d1d-69c9-448e-8262-d8d45c47add2")
public class DecisionMergeNodeSmClass extends ControlNodeSmClass {
    @objid ("4b5ef5b0-f824-4152-8cd0-12d8b24d3327")
    private SmAttribute decisionKindAtt;

    @objid ("a0a87157-7882-41f1-b285-514ce5c8a9db")
    private SmAttribute decisionInputBehaviorAtt;

    @objid ("8beae383-1b89-4528-970c-74b43d1118ab")
    public  DecisionMergeNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7a362d72-84da-4453-a133-483bea71b446")
    @Override
    public String getName() {
        return "DecisionMergeNode";
        
    }

    @objid ("d51f8f98-09d8-4e92-86eb-c6599d1fa277")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f3233678-9283-4644-979e-c87d55141807")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DecisionMergeNode.class;
        
    }

    @objid ("a1cec106-2d8c-44f9-99de-c273d6f5310e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("a45075fb-b31a-4999-9af8-291fe1615aa8")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("3791af5a-dd25-4049-8e6b-cf42d864995c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ControlNode.MQNAME);
        this.registerFactory(new DecisionMergeNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.decisionKindAtt = new DecisionKindSmAttribute();
        this.decisionKindAtt.init("DecisionKind", this, DecisionNodeKind.class );
        registerAttribute(this.decisionKindAtt);
        
        this.decisionInputBehaviorAtt = new DecisionInputBehaviorSmAttribute();
        this.decisionInputBehaviorAtt.init("DecisionInputBehavior", this, String.class );
        registerAttribute(this.decisionInputBehaviorAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("6fb43521-ff50-4a66-b86c-f3f5962c14c6")
    public SmAttribute getDecisionKindAtt() {
        if (this.decisionKindAtt == null) {
        	this.decisionKindAtt = this.getAttributeDef("DecisionKind");
        }
        return this.decisionKindAtt;
    }

    @objid ("9bd4c9cc-0dfb-4942-984c-6d5c01df3d4f")
    public SmAttribute getDecisionInputBehaviorAtt() {
        if (this.decisionInputBehaviorAtt == null) {
        	this.decisionInputBehaviorAtt = this.getAttributeDef("DecisionInputBehavior");
        }
        return this.decisionInputBehaviorAtt;
    }

    @objid ("73b76e3e-fb5e-42f4-a963-5795cab1a183")
    private static class DecisionMergeNodeObjectFactory implements ISmObjectFactory {
        @objid ("aba625fe-e025-446c-8a35-ce75e8197783")
        private DecisionMergeNodeSmClass smClass;

        @objid ("e30cc8eb-adf7-41c4-979a-ad5f577270a3")
        public  DecisionMergeNodeObjectFactory(DecisionMergeNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("41ecedd8-d67b-4211-9205-dce09f2c7a6c")
        @Override
        public ISmObjectData createData() {
            return new DecisionMergeNodeData(this.smClass);
        }

        @objid ("dd05279a-67db-49fb-834c-be9ac131e043")
        @Override
        public SmObjectImpl createImpl() {
            return new DecisionMergeNodeImpl();
        }

    }

    @objid ("ec8e855f-ab11-4edb-ba23-3c066b712050")
    public static class DecisionKindSmAttribute extends SmAttribute {
        @objid ("813c8ae3-3439-4a8d-b9bc-ee2a5dacb8ee")
        public Object getValue(ISmObjectData data) {
            return ((DecisionMergeNodeData) data).mDecisionKind;
        }

        @objid ("5f4b9654-d913-4ef1-ac79-28998e2ba6f7")
        public void setValue(ISmObjectData data, Object value) {
            ((DecisionMergeNodeData) data).mDecisionKind = value;
        }

    }

    @objid ("a661a402-f69d-4681-b7db-254bd48d4e38")
    public static class DecisionInputBehaviorSmAttribute extends SmAttribute {
        @objid ("2be296cd-29d1-48b6-9b59-da4b0f451847")
        public Object getValue(ISmObjectData data) {
            return ((DecisionMergeNodeData) data).mDecisionInputBehavior;
        }

        @objid ("08b15eeb-7a3c-4b64-9c8c-d93c572c8529")
        public void setValue(ISmObjectData data, Object value) {
            ((DecisionMergeNodeData) data).mDecisionInputBehavior = value;
        }

    }

}
