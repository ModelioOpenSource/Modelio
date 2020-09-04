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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixDefinitionData;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionSmClass;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("2e3cf8ca-2a31-49dc-9304-38b05b812c6c")
public class MatrixDefinitionSmClass extends ModelElementSmClass {
    @objid ("de57e7f6-d4c9-4f3d-ae0a-47be4a8a4e1f")
    private SmDependency linesDefinitionDep;

    @objid ("ad25366c-b01d-4904-9a78-984b9ab05b52")
    private SmDependency columnsDefinitionDep;

    @objid ("ae9d1e8e-b422-40e0-a9ca-7e1b76cd66c3")
    private SmDependency valuesDefinitionDep;

    @objid ("c584e7ff-498d-44ee-b0e9-88c81985e1b6")
    private SmDependency depthDefinitionDep;

    @objid ("35d63ab1-35c7-4019-b067-64b6f6264700")
    private SmDependency ownerDep;

    @objid ("5ce46ad5-392c-460e-bd97-6eea8d3caba6")
    public MatrixDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cfd0ba18-3d22-4b24-9d33-7f4d4702478d")
    @Override
    public String getName() {
        return "MatrixDefinition";
    }

    @objid ("0bd8016d-a2d2-4fc5-b73b-9c96a922e8e5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6cc60b2a-0089-4f95-8ea4-c14d382d1655")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return MatrixDefinition.class;
    }

    @objid ("77a05a4a-4ca1-403e-a060-33a09587f769")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("964bbaa3-15cb-447e-a6d4-b42a59a8545a")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("3d243a30-1285-44ef-98ca-8e3986cc6843")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new MatrixDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.linesDefinitionDep = new LinesDefinitionSmDependency();
        this.linesDefinitionDep.init("LinesDefinition", this, metamodel.getMClass(QueryDefinition.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.linesDefinitionDep);
        
        this.columnsDefinitionDep = new ColumnsDefinitionSmDependency();
        this.columnsDefinitionDep.init("ColumnsDefinition", this, metamodel.getMClass(QueryDefinition.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.columnsDefinitionDep);
        
        this.valuesDefinitionDep = new ValuesDefinitionSmDependency();
        this.valuesDefinitionDep.init("ValuesDefinition", this, metamodel.getMClass(MatrixValueDefinition.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.valuesDefinitionDep);
        
        this.depthDefinitionDep = new DepthDefinitionSmDependency();
        this.depthDefinitionDep.init("DepthDefinition", this, metamodel.getMClass(QueryDefinition.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.depthDefinitionDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(ModelElement.MQNAME), 1, 1 );
        registerDependency(this.ownerDep);
    }

    @objid ("11a8c971-d5e4-45e8-9cd2-a5ceaaa57a1e")
    public SmDependency getLinesDefinitionDep() {
        if (this.linesDefinitionDep == null) {
        	this.linesDefinitionDep = this.getDependencyDef("LinesDefinition");
        }
        return this.linesDefinitionDep;
    }

    @objid ("29ef73ff-6c34-42c5-ae70-6f3f9a5da6a9")
    public SmDependency getColumnsDefinitionDep() {
        if (this.columnsDefinitionDep == null) {
        	this.columnsDefinitionDep = this.getDependencyDef("ColumnsDefinition");
        }
        return this.columnsDefinitionDep;
    }

    @objid ("a89f163d-97cf-40dc-b27e-41c1de172990")
    public SmDependency getValuesDefinitionDep() {
        if (this.valuesDefinitionDep == null) {
        	this.valuesDefinitionDep = this.getDependencyDef("ValuesDefinition");
        }
        return this.valuesDefinitionDep;
    }

    @objid ("0d200ec7-b8d8-4361-8ab3-8c8e59ea9e48")
    public SmDependency getDepthDefinitionDep() {
        if (this.depthDefinitionDep == null) {
        	this.depthDefinitionDep = this.getDependencyDef("DepthDefinition");
        }
        return this.depthDefinitionDep;
    }

    @objid ("e2f62316-1fb6-4710-90e6-e69682db0d4e")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("560eaf85-a56b-4d74-8ca7-3df759e15a1d")
    private static class MatrixDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("73668100-e28a-4828-8d8c-a1b7bd55d0f7")
        private MatrixDefinitionSmClass smClass;

        @objid ("de15f900-63fa-4946-98f7-c4c9b97e1e0f")
        public MatrixDefinitionObjectFactory(MatrixDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("5b2aa8dd-f751-4e49-ae55-084b367416a1")
        @Override
        public ISmObjectData createData() {
            return new MatrixDefinitionData(this.smClass);
        }

        @objid ("be691414-fc0c-45bc-92c8-78cf77639ef7")
        @Override
        public SmObjectImpl createImpl() {
            return new MatrixDefinitionImpl();
        }

    }

    @objid ("2cf12629-f437-4d9d-987f-90caa9f63e28")
    public static class LinesDefinitionSmDependency extends SmSingleDependency {
        @objid ("3798c021-c195-4490-9d57-97b34ffe8e70")
        private SmDependency symetricDep;

        @objid ("a9b24dec-c5e9-4c6f-87bc-4bac5558ef22")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mLinesDefinition;
        }

        @objid ("171f9005-ce20-4a58-803d-98c36b961c06")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mLinesDefinition = value;
        }

        @objid ("2f5bb9d8-64a1-4a3d-a6b3-c057d945dc61")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getOwnerAsLineDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("80b42027-5eb8-4762-81ff-83e31d0f5fd3")
    public static class ColumnsDefinitionSmDependency extends SmSingleDependency {
        @objid ("7e2b9c05-584a-4eea-b7b9-4cfdd612a643")
        private SmDependency symetricDep;

        @objid ("82cb0385-15b9-4bd9-a1e6-94067c4a1e3b")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mColumnsDefinition;
        }

        @objid ("23845efe-f1d0-4af7-ba66-bd6c961dcd26")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mColumnsDefinition = value;
        }

        @objid ("a4263d9e-ce54-4433-9e3d-c02f5f5a784e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getOwnerAsColDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("6d85f4cb-3120-4fdd-8325-15ef402c6989")
    public static class ValuesDefinitionSmDependency extends SmSingleDependency {
        @objid ("e40264cc-bdde-4fd7-96f3-7f20b8730a04")
        private SmDependency symetricDep;

        @objid ("ff9f7439-4ca5-4f4f-9269-00889b16fb6f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mValuesDefinition;
        }

        @objid ("46d4428e-70ea-4ba5-85b9-0bfe45d2e2a6")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mValuesDefinition = value;
        }

        @objid ("97a1c7dc-6819-4830-8807-a7f47d329005")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((MatrixValueDefinitionSmClass)this.getTarget()).getMatrixDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1018623d-5ebf-4d7d-be6a-4588e7b2052f")
    public static class DepthDefinitionSmDependency extends SmSingleDependency {
        @objid ("8ecb63da-6c89-4a4e-a817-f4589752b0df")
        private SmDependency symetricDep;

        @objid ("c7007d5f-a9f9-494d-ad6c-ed087d1536e0")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mDepthDefinition;
        }

        @objid ("fe91a21c-d21d-4af0-81c6-811a20286a92")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mDepthDefinition = value;
        }

        @objid ("be98628c-e6c4-47c8-8789-bf728315d6c3")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((QueryDefinitionSmClass)this.getTarget()).getOwnerAsDepthDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("16e1ca50-872c-4aaa-837e-665e33d73c42")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("efce4eec-1236-4149-ac15-618a7846d96b")
        private SmDependency symetricDep;

        @objid ("cf104f14-2149-42ae-a565-cc146dc9303f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((MatrixDefinitionData) data).mOwner;
        }

        @objid ("69e69163-31eb-43ef-ac5b-96c528b0650d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((MatrixDefinitionData) data).mOwner = value;
        }

        @objid ("361ce7cc-5e09-4798-b7e0-c3833aedecd2")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getMatrixDep();
            }
            return this.symetricDep;
        }

    }

}
