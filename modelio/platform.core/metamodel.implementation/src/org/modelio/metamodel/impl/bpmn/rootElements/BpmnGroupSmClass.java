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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnArtifactSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnGroupData;
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

@objid ("ad11c0e8-184a-4703-8339-53a0a144d6b5")
public class BpmnGroupSmClass extends BpmnArtifactSmClass {
    @objid ("639b5bb0-86de-4e81-90d0-97b70a04ce1c")
    private SmAttribute categoryAtt;

    @objid ("edfb787e-de79-4ed1-8f91-c37eff2a7277")
    private SmDependency categorizedDep;

    @objid ("1c050496-fd2d-4744-9b8e-e5ddd1601a4d")
    public BpmnGroupSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("006ae568-bfc8-4bde-97ca-7f65dae3ad3b")
    @Override
    public String getName() {
        return "BpmnGroup";
    }

    @objid ("239bee45-504c-4e2f-839a-4826b3f3f583")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("07360ac4-acd7-47ea-84de-8634d6b42c57")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnGroup.class;
    }

    @objid ("4e6097ed-c6c3-4cbf-b83e-8c9e4d21c4f6")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("24e02292-e089-4a2a-893f-6462a06a418d")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("9b1c6dbc-45bc-4217-82f8-864c66f37853")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnArtifact.MQNAME);
        this.registerFactory(new BpmnGroupObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.categoryAtt = new CategorySmAttribute();
        this.categoryAtt.init("Category", this, String.class );
        registerAttribute(this.categoryAtt);
        
        
        // Initialize and register the SmDependency
        this.categorizedDep = new CategorizedSmDependency();
        this.categorizedDep.init("Categorized", this, metamodel.getMClass(BpmnFlowElement.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.categorizedDep);
    }

    @objid ("21c2a133-32bb-4c3e-9003-2c8edbb5a4ae")
    public SmAttribute getCategoryAtt() {
        if (this.categoryAtt == null) {
        	this.categoryAtt = this.getAttributeDef("Category");
        }
        return this.categoryAtt;
    }

    @objid ("e9ac1317-db08-4ceb-9966-5fa68fd859dd")
    public SmDependency getCategorizedDep() {
        if (this.categorizedDep == null) {
        	this.categorizedDep = this.getDependencyDef("Categorized");
        }
        return this.categorizedDep;
    }

    @objid ("b4307d32-976d-4505-88c1-76b6d3bc5350")
    private static class BpmnGroupObjectFactory implements ISmObjectFactory {
        @objid ("a37594a8-4abe-40a8-b195-35099ef09648")
        private BpmnGroupSmClass smClass;

        @objid ("35dd93c1-7f80-46e4-9caf-47ca2e8deaba")
        public BpmnGroupObjectFactory(BpmnGroupSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d848b22f-4a19-435a-b29c-92398f614b08")
        @Override
        public ISmObjectData createData() {
            return new BpmnGroupData(this.smClass);
        }

        @objid ("a711921d-d6c3-4cdb-b0f0-605e66188189")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnGroupImpl();
        }

    }

    @objid ("9476932b-2318-402f-9ca8-b55371864b89")
    public static class CategorySmAttribute extends SmAttribute {
        @objid ("2f5fd95e-4dc4-48e4-9b73-fd295d96359e")
        public Object getValue(ISmObjectData data) {
            return ((BpmnGroupData) data).mCategory;
        }

        @objid ("c59e43ea-838d-4d5d-be4d-e52df5b7a4d4")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnGroupData) data).mCategory = value;
        }

    }

    @objid ("8bc1f0db-dad9-4bf6-b381-34e5a0f1aac8")
    public static class CategorizedSmDependency extends SmMultipleDependency {
        @objid ("df157118-24fd-4be3-af97-f5559535f594")
        private SmDependency symetricDep;

        @objid ("7545db14-7cb6-47ff-a42f-417aa54239e9")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnGroupData)data).mCategorized != null)? ((BpmnGroupData)data).mCategorized:SmMultipleDependency.EMPTY;
        }

        @objid ("77db5496-6d49-4244-a9ed-a86bc748232c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnGroupData) data).mCategorized = values;
        }

        @objid ("d039d3d5-690a-4e1c-aa08-1154ddc65d97")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnFlowElementSmClass)this.getTarget()).getGroupsDep();
            }
            return this.symetricDep;
        }

    }

}
