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

package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.objects.BpmnItemKind;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementSmClass;
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

@objid ("1c7d8bdd-dbfc-4c81-9979-040335d7b02b")
public class BpmnItemDefinitionSmClass extends BpmnSharedElementSmClass {
    @objid ("152705b3-8ef4-4702-b116-d2bbe7d874f6")
    private SmAttribute itemKindAtt;

    @objid ("a5f022a0-f2e7-458a-b412-9b3373bc0bda")
    private SmAttribute isCollectionAtt;

    @objid ("e98d310e-eeb7-40ff-ae2a-d2bfa6eae396")
    private SmDependency typedMessageDep;

    @objid ("94e7f661-ed3a-4f59-8397-e09b17328b3f")
    private SmDependency typedItemDep;

    @objid ("02b89d6c-37e0-47c3-9624-1c6d28d274f5")
    private SmDependency typedResourceParameterDep;

    @objid ("30659a64-1221-4419-95a0-970a5cb55b59")
    public  BpmnItemDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9c4a91b1-a252-4276-b713-2f40e0c64876")
    @Override
    public String getName() {
        return "BpmnItemDefinition";
        
    }

    @objid ("5897cdd5-800b-4e15-84a1-ccdc936b4d4d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0908a6c6-9af3-4372-a1d8-561945a59d84")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnItemDefinition.class;
        
    }

    @objid ("fc5f89ef-26be-44da-af9b-bb2a20b7362d")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("7e3e723c-c44c-4855-9986-4edfb16f9f39")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("a6857982-5433-41d3-8c06-da07a937b6a5")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnSharedElement.MQNAME);
        this.registerFactory(new BpmnItemDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.itemKindAtt = new ItemKindSmAttribute();
        this.itemKindAtt.init("ItemKind", this, BpmnItemKind.class );
        registerAttribute(this.itemKindAtt);
        
        this.isCollectionAtt = new IsCollectionSmAttribute();
        this.isCollectionAtt.init("IsCollection", this, Boolean.class );
        registerAttribute(this.isCollectionAtt);
        
        
        // Initialize and register the SmDependency
        this.typedMessageDep = new TypedMessageSmDependency();
        this.typedMessageDep.init("TypedMessage", this, metamodel.getMClass(BpmnMessage.MQNAME), 0, -1 );
        registerDependency(this.typedMessageDep);
        
        this.typedItemDep = new TypedItemSmDependency();
        this.typedItemDep.init("TypedItem", this, metamodel.getMClass(BpmnItemAwareElement.MQNAME), 0, -1 );
        registerDependency(this.typedItemDep);
        
        this.typedResourceParameterDep = new TypedResourceParameterSmDependency();
        this.typedResourceParameterDep.init("TypedResourceParameter", this, metamodel.getMClass(BpmnResourceParameter.MQNAME), 0, -1 );
        registerDependency(this.typedResourceParameterDep);
        
        
    }

    @objid ("6a06a216-784d-4f99-bff9-3db8759523e5")
    public SmAttribute getItemKindAtt() {
        if (this.itemKindAtt == null) {
        	this.itemKindAtt = this.getAttributeDef("ItemKind");
        }
        return this.itemKindAtt;
    }

    @objid ("3c1f3596-008b-46cd-b184-277fdcb6ca5b")
    public SmAttribute getIsCollectionAtt() {
        if (this.isCollectionAtt == null) {
        	this.isCollectionAtt = this.getAttributeDef("IsCollection");
        }
        return this.isCollectionAtt;
    }

    @objid ("9136f300-5d0a-4905-8669-f0da392a7ff2")
    public SmDependency getTypedMessageDep() {
        if (this.typedMessageDep == null) {
        	this.typedMessageDep = this.getDependencyDef("TypedMessage");
        }
        return this.typedMessageDep;
    }

    @objid ("60af2817-81a9-4a8d-bdd7-128fb83d161f")
    public SmDependency getTypedItemDep() {
        if (this.typedItemDep == null) {
        	this.typedItemDep = this.getDependencyDef("TypedItem");
        }
        return this.typedItemDep;
    }

    @objid ("86ef342c-3903-4cbb-8ea4-4c81aafc0400")
    public SmDependency getTypedResourceParameterDep() {
        if (this.typedResourceParameterDep == null) {
        	this.typedResourceParameterDep = this.getDependencyDef("TypedResourceParameter");
        }
        return this.typedResourceParameterDep;
    }

    @objid ("5724a5ce-5bf3-402b-9b00-6a602cc8dabf")
    private static class BpmnItemDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("bc08caf8-ec26-4908-8df4-9583be357395")
        private BpmnItemDefinitionSmClass smClass;

        @objid ("3b710e7e-8ba1-470a-bd3a-6183cfdb48e6")
        public  BpmnItemDefinitionObjectFactory(BpmnItemDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2e37dd20-6027-4a0e-9e1b-c7da6ffe7078")
        @Override
        public ISmObjectData createData() {
            return new BpmnItemDefinitionData(this.smClass);
        }

        @objid ("d34d38f9-2a91-4ea9-94c0-137ef4e227a8")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnItemDefinitionImpl();
        }

    }

    @objid ("5b915c26-3315-4c4f-9d8a-bbbe7f7622a7")
    public static class ItemKindSmAttribute extends SmAttribute {
        @objid ("eda436a7-fe2a-467a-beb8-8e6193081f48")
        public Object getValue(ISmObjectData data) {
            return ((BpmnItemDefinitionData) data).mItemKind;
        }

        @objid ("9863e446-3d99-4341-96b5-2e0329ae14fa")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnItemDefinitionData) data).mItemKind = value;
        }

    }

    @objid ("267f4aa4-5a61-40df-b221-d854bbb80290")
    public static class IsCollectionSmAttribute extends SmAttribute {
        @objid ("e96efd0a-e4cd-45a2-ae14-8515a9999694")
        public Object getValue(ISmObjectData data) {
            return ((BpmnItemDefinitionData) data).mIsCollection;
        }

        @objid ("6c57f4d2-a703-443c-aae2-98a51f2daf55")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnItemDefinitionData) data).mIsCollection = value;
        }

    }

    @objid ("e6c59e20-11ac-40cf-b760-5460e1ad7630")
    public static class TypedMessageSmDependency extends SmMultipleDependency {
        @objid ("661ffd73-3238-46a5-92f2-9f9db6a26bd0")
        private SmDependency symetricDep;

        @objid ("92da3529-c6b6-4ed5-bba4-63f1d8d39656")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnItemDefinitionData)data).mTypedMessage != null)? ((BpmnItemDefinitionData)data).mTypedMessage:SmMultipleDependency.EMPTY;
        }

        @objid ("30d12242-5028-43ca-949a-9cf8e4422dbe")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnItemDefinitionData) data).mTypedMessage = values;
            
        }

        @objid ("aabed385-55ea-4276-bb9e-89528d4ba5f8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnMessageSmClass)this.getTarget()).getItemRefDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("800923df-367a-4d34-a759-80f4fbacbdac")
    public static class TypedItemSmDependency extends SmMultipleDependency {
        @objid ("a6c77a47-02f2-449e-8752-1dbc86cc254c")
        private SmDependency symetricDep;

        @objid ("9da79565-23e3-4fe9-9312-aba505befaab")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnItemDefinitionData)data).mTypedItem != null)? ((BpmnItemDefinitionData)data).mTypedItem:SmMultipleDependency.EMPTY;
        }

        @objid ("5e895f02-e016-4a4e-949c-fb8025f06187")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnItemDefinitionData) data).mTypedItem = values;
            
        }

        @objid ("ef7aee5a-560e-4395-b7d7-42415f06f0b9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnItemAwareElementSmClass)this.getTarget()).getItemSubjectRefDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("ba46c5eb-000e-42ed-bf21-538906a721b6")
    public static class TypedResourceParameterSmDependency extends SmMultipleDependency {
        @objid ("c1c386a6-e97f-4894-afd7-886d5eddb289")
        private SmDependency symetricDep;

        @objid ("f53e2346-9cbe-412d-874d-f11a464b739b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnItemDefinitionData)data).mTypedResourceParameter != null)? ((BpmnItemDefinitionData)data).mTypedResourceParameter:SmMultipleDependency.EMPTY;
        }

        @objid ("84a51752-379d-428c-850d-c5ed257537fb")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnItemDefinitionData) data).mTypedResourceParameter = values;
            
        }

        @objid ("83ede203-00c8-4267-99c2-9f3a9b84567a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnResourceParameterSmClass)this.getTarget()).getTypeDep();
            }
            return this.symetricDep;
            
        }

    }

}
