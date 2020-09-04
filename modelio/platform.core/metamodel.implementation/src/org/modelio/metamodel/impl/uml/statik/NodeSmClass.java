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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.ArtifactSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.NodeData;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Node;
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

@objid ("59646a2c-a1d3-48dc-b061-9200708cc332")
public class NodeSmClass extends ClassifierSmClass {
    @objid ("788f1959-62d5-4a1b-8e35-de16a75d14a4")
    private SmDependency residentDep;

    @objid ("30106a16-e868-4637-bd57-0d4fe8561d71")
    public NodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ef8ceb7c-c970-401b-8f6f-c0f6d74b791a")
    @Override
    public String getName() {
        return "Node";
    }

    @objid ("8836ff79-13a5-4d6d-b2f4-206a189f9d11")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b3847367-905e-4e7b-977c-59b72ac1ec2f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Node.class;
    }

    @objid ("c0b1d8ab-63d5-4e24-a4bd-dc8c5e04256b")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("6b2203bf-c300-488c-8cb7-8d16d3daeee4")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("aa4892ed-73ac-491e-ba62-ddcf827c1395")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Classifier.MQNAME);
        this.registerFactory(new NodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.residentDep = new ResidentSmDependency();
        this.residentDep.init("Resident", this, metamodel.getMClass(Artifact.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.residentDep);
    }

    @objid ("b10afac5-6062-4352-84a6-948bb9afcad6")
    public SmDependency getResidentDep() {
        if (this.residentDep == null) {
        	this.residentDep = this.getDependencyDef("Resident");
        }
        return this.residentDep;
    }

    @objid ("c950b822-9710-41c4-b49b-0134275175e8")
    private static class NodeObjectFactory implements ISmObjectFactory {
        @objid ("15f03199-084e-41ee-a25e-6baea74cfde6")
        private NodeSmClass smClass;

        @objid ("268b04ee-de3d-4886-9026-550a77cd55c7")
        public NodeObjectFactory(NodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("5c3184f5-418c-4e2f-9e8f-2d47816f8ad4")
        @Override
        public ISmObjectData createData() {
            return new NodeData(this.smClass);
        }

        @objid ("80b917df-54c0-4386-b80e-4e0a779a9e30")
        @Override
        public SmObjectImpl createImpl() {
            return new NodeImpl();
        }

    }

    @objid ("817fd4d7-b247-4f17-9352-e74d98b7ae54")
    public static class ResidentSmDependency extends SmMultipleDependency {
        @objid ("f0ce84f0-30f9-41fa-87bb-103d7942da86")
        private SmDependency symetricDep;

        @objid ("10e9258b-a97e-4c5b-82b8-adde65275b08")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NodeData)data).mResident != null)? ((NodeData)data).mResident:SmMultipleDependency.EMPTY;
        }

        @objid ("7a4e2e50-2573-498f-8aeb-90d12419cf16")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NodeData) data).mResident = values;
        }

        @objid ("3e57583f-f272-4a5e-afbc-15ec4812269b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ArtifactSmClass)this.getTarget()).getDeploymentLocationDep();
            }
            return this.symetricDep;
        }

    }

}
