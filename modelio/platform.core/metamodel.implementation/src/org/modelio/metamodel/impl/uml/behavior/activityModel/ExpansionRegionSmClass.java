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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExpansionNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExpansionRegionData;
import org.modelio.metamodel.impl.uml.behavior.activityModel.StructuredActivityNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionKind;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
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

@objid ("fa02fd4e-8957-4742-9fbc-785a02b815fc")
public class ExpansionRegionSmClass extends StructuredActivityNodeSmClass {
    @objid ("abd210cd-fe27-4f86-b653-2e8c56140be2")
    private SmAttribute modeAtt;

    @objid ("e8972909-946c-4d0f-9c1b-96a208b479af")
    private SmDependency outputElementDep;

    @objid ("1d307c2c-4535-46f7-993a-19da84ed4df8")
    private SmDependency inputElementDep;

    @objid ("58461969-1f20-4709-9d35-26e0237d8e38")
    public ExpansionRegionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("62c77ef7-1a7b-489c-bf29-ee621eb3cd7d")
    @Override
    public String getName() {
        return "ExpansionRegion";
    }

    @objid ("689c1f6f-6395-4f92-8af0-41833ad471ee")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("20313596-ea6c-46ae-a4a5-24ce541a987d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExpansionRegion.class;
    }

    @objid ("272484f4-5f2b-4c48-986a-e00f2baf53a0")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("47fcd579-55fb-4888-ac9d-947728d88051")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("e5c703ba-7ef3-4326-abb5-25c1c8435968")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StructuredActivityNode.MQNAME);
        this.registerFactory(new ExpansionRegionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.modeAtt = new ModeSmAttribute();
        this.modeAtt.init("Mode", this, ExpansionKind.class );
        registerAttribute(this.modeAtt);
        
        
        // Initialize and register the SmDependency
        this.outputElementDep = new OutputElementSmDependency();
        this.outputElementDep.init("OutputElement", this, metamodel.getMClass(ExpansionNode.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT, SmDirective.SMCDPARTOF);
        registerDependency(this.outputElementDep);
        
        this.inputElementDep = new InputElementSmDependency();
        this.inputElementDep.init("InputElement", this, metamodel.getMClass(ExpansionNode.MQNAME), 1, -1 , SmDirective.SMCDCOMPONENT, SmDirective.SMCDPARTOF);
        registerDependency(this.inputElementDep);
    }

    @objid ("d967f1c3-d032-48a6-b9c9-e340dfe5a925")
    public SmAttribute getModeAtt() {
        if (this.modeAtt == null) {
        	this.modeAtt = this.getAttributeDef("Mode");
        }
        return this.modeAtt;
    }

    @objid ("b47a116c-a11a-447e-97b5-119956d211ad")
    public SmDependency getOutputElementDep() {
        if (this.outputElementDep == null) {
        	this.outputElementDep = this.getDependencyDef("OutputElement");
        }
        return this.outputElementDep;
    }

    @objid ("22fca1ec-e8bb-4f5b-84e6-40df320c104e")
    public SmDependency getInputElementDep() {
        if (this.inputElementDep == null) {
        	this.inputElementDep = this.getDependencyDef("InputElement");
        }
        return this.inputElementDep;
    }

    @objid ("f7b0e113-f5f1-4252-afe8-4e7b312ed313")
    private static class ExpansionRegionObjectFactory implements ISmObjectFactory {
        @objid ("53dfa8cc-9c45-44ae-af76-c3d3d9835e6f")
        private ExpansionRegionSmClass smClass;

        @objid ("a02cf9e0-6bf5-4d65-bd8f-b533df56698a")
        public ExpansionRegionObjectFactory(ExpansionRegionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("62f6986c-0243-4868-b5a9-8831b0e6c645")
        @Override
        public ISmObjectData createData() {
            return new ExpansionRegionData(this.smClass);
        }

        @objid ("c8ee8fee-f56f-400e-bbf6-51e2ca668687")
        @Override
        public SmObjectImpl createImpl() {
            return new ExpansionRegionImpl();
        }

    }

    @objid ("039703f7-efea-4f29-b2f7-09ca540a2553")
    public static class ModeSmAttribute extends SmAttribute {
        @objid ("55dda536-b793-415c-a227-2ca0e0d79d1f")
        public Object getValue(ISmObjectData data) {
            return ((ExpansionRegionData) data).mMode;
        }

        @objid ("883c4d75-82d9-4f3d-a511-ad82aed96b2b")
        public void setValue(ISmObjectData data, Object value) {
            ((ExpansionRegionData) data).mMode = value;
        }

    }

    @objid ("3cf4513f-71d6-4a15-b033-710b8fc27ba5")
    public static class OutputElementSmDependency extends SmMultipleDependency {
        @objid ("b96dbfd1-b68b-456e-ba1f-820ab917a7fb")
        private SmDependency symetricDep;

        @objid ("a6e1f164-cfdf-4072-a370-030e6bc4f448")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ExpansionRegionData)data).mOutputElement != null)? ((ExpansionRegionData)data).mOutputElement:SmMultipleDependency.EMPTY;
        }

        @objid ("0dd5f794-5595-4272-95da-6fac7f1634da")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ExpansionRegionData) data).mOutputElement = values;
        }

        @objid ("bb452e73-62b5-48e9-b876-382b9d9101db")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExpansionNodeSmClass)this.getTarget()).getRegionAsOutputDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("e08eab8c-c2ef-43ff-88a0-b1f728b6f185")
    public static class InputElementSmDependency extends SmMultipleDependency {
        @objid ("e057b555-442b-4672-8e1c-92671e7e87eb")
        private SmDependency symetricDep;

        @objid ("536ae8a2-3861-492a-a48b-d80ee81656db")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ExpansionRegionData)data).mInputElement != null)? ((ExpansionRegionData)data).mInputElement:SmMultipleDependency.EMPTY;
        }

        @objid ("0f7bd8cf-b0b0-4319-a912-0f9faadaf6e5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ExpansionRegionData) data).mInputElement = values;
        }

        @objid ("c1947201-8fa3-4a32-8238-e97fddfc7ce5")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExpansionNodeSmClass)this.getTarget()).getRegionAsInputDep();
            }
            return this.symetricDep;
        }

    }

}
