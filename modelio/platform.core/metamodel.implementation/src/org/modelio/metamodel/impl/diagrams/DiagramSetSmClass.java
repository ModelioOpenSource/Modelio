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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.DiagramSetData;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("631adaa3-d7f7-47b1-8dba-62b2b07541c7")
public class DiagramSetSmClass extends ModelElementSmClass {
    @objid ("5096eb11-cd78-49e4-87de-e55352eb79b0")
    private SmDependency subDep;

    @objid ("9f296ac5-4fdc-4820-a2cd-51d4364826b6")
    private SmDependency parentDep;

    @objid ("2445bc6f-7f43-4162-8006-7b7008d10329")
    private SmDependency referencedDiagramDep;

    @objid ("d14dd3ba-10ab-4135-83b0-0c4bcac46a5f")
    private SmDependency ownerDep;

    @objid ("2740f68d-4794-47fc-a577-0b45ff1d80d2")
    public DiagramSetSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cef3c895-dea8-4046-91cc-d175268b1303")
    @Override
    public String getName() {
        return "DiagramSet";
    }

    @objid ("813f2b2d-9266-4367-88d8-97115675e34f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2dd8bc6f-88d2-43cb-ac9a-c3db735c3e41")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return DiagramSet.class;
    }

    @objid ("e5a942f0-1b00-4cd9-a505-49ef49a65b52")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("3de3f37d-a196-4b5d-8313-a5cfb6f5bdb8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6ce22392-d5a3-46f2-b81f-90d8b3ce408c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new DiagramSetObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.subDep = new SubSmDependency();
        this.subDep.init("Sub", this, metamodel.getMClass(DiagramSet.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.subDep);
        
        this.parentDep = new ParentSmDependency();
        this.parentDep.init("Parent", this, metamodel.getMClass(DiagramSet.MQNAME), 0, 1 );
        registerDependency(this.parentDep);
        
        this.referencedDiagramDep = new ReferencedDiagramSmDependency();
        this.referencedDiagramDep.init("ReferencedDiagram", this, metamodel.getMClass(AbstractDiagram.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.referencedDiagramDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(AbstractProject.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("487fba0a-f380-45b6-8c65-283c5da32495")
    public SmDependency getSubDep() {
        if (this.subDep == null) {
        	this.subDep = this.getDependencyDef("Sub");
        }
        return this.subDep;
    }

    @objid ("620b61e6-039d-4409-a5fb-b5486463c3d5")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("9fefbe1f-2c59-4e91-8edf-34660821b371")
    public SmDependency getReferencedDiagramDep() {
        if (this.referencedDiagramDep == null) {
        	this.referencedDiagramDep = this.getDependencyDef("ReferencedDiagram");
        }
        return this.referencedDiagramDep;
    }

    @objid ("20a89a8e-d1e6-4b87-b174-908c745d8391")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("9c534dcf-2b78-45fc-81a0-d753242319b6")
    private static class DiagramSetObjectFactory implements ISmObjectFactory {
        @objid ("c7336a30-5e50-4efc-8fd1-e42efd6e972f")
        private DiagramSetSmClass smClass;

        @objid ("7f239c24-584c-4dad-ab00-aa053d6afae7")
        public DiagramSetObjectFactory(DiagramSetSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8759c53c-ee52-4bb9-a41c-332e1a2e1569")
        @Override
        public ISmObjectData createData() {
            return new DiagramSetData(this.smClass);
        }

        @objid ("2b536760-1278-49f4-a2a8-a50b16519f60")
        @Override
        public SmObjectImpl createImpl() {
            return new DiagramSetImpl();
        }

    }

    @objid ("ba8608bc-1e83-47c5-bd09-d37903a0eaa0")
    public static class SubSmDependency extends SmMultipleDependency {
        @objid ("72047611-094e-4d78-8164-d223f363dc41")
        private SmDependency symetricDep;

        @objid ("5d0751ef-d1d1-4ac0-aef1-f9dabdb6b2da")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((DiagramSetData)data).mSub != null)? ((DiagramSetData)data).mSub:SmMultipleDependency.EMPTY;
        }

        @objid ("2bb20e80-e245-4bd0-9cff-470ffb54923e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((DiagramSetData) data).mSub = values;
        }

        @objid ("b4faef09-ad7e-4acc-a532-303d94b97bca")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getParentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1a953680-2cd4-45a8-9eff-321487482312")
    public static class ParentSmDependency extends SmSingleDependency {
        @objid ("23673240-246f-4051-826d-7b92a34e1a6b")
        private SmDependency symetricDep;

        @objid ("92eae042-b89c-4dc4-a066-b6bfb2ae9f60")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DiagramSetData) data).mParent;
        }

        @objid ("889b5aa0-70e3-40ac-95b1-4730868542be")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DiagramSetData) data).mParent = value;
        }

        @objid ("0062e6ad-463f-4bed-b5da-d96080ec9530")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getSubDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("08a28549-8c8c-4ebd-ad72-e09cf764f9be")
    public static class ReferencedDiagramSmDependency extends SmMultipleDependency {
        @objid ("000a4afc-f6db-48c0-8937-3cc1ec15d89a")
        private SmDependency symetricDep;

        @objid ("875f6052-d90a-4210-94b1-18852e6a4cc2")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((DiagramSetData)data).mReferencedDiagram != null)? ((DiagramSetData)data).mReferencedDiagram:SmMultipleDependency.EMPTY;
        }

        @objid ("ac4e217a-26f7-46ad-95de-a1c3609cd070")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((DiagramSetData) data).mReferencedDiagram = values;
        }

        @objid ("9ba0ffc6-e7fd-42b9-8221-ac6719aea105")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractDiagramSmClass)this.getTarget()).getReferencingSetDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("d193af4e-8c0c-4c15-9386-748ba65c089f")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("e44d1ddd-2afc-47c0-b434-cf7bfe8f1b62")
        private SmDependency symetricDep;

        @objid ("1aebf7a7-1e57-4ce8-b644-83decb4f1d71")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((DiagramSetData) data).mOwner;
        }

        @objid ("2c8e66af-b807-4db2-b3e9-caaddd0ca8c1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((DiagramSetData) data).mOwner = value;
        }

        @objid ("db62818d-3d95-436d-a335-f88c891ea99c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AbstractProjectSmClass)this.getTarget()).getDiagramRootDep();
            }
            return this.symetricDep;
        }

    }

}
