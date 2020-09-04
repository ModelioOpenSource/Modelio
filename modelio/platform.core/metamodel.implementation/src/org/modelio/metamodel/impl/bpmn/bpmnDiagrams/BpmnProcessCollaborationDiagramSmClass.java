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
package org.modelio.metamodel.impl.bpmn.bpmnDiagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.impl.diagrams.BehaviorDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("226a58ba-6af0-42d1-8b64-93f2d9615af2")
public class BpmnProcessCollaborationDiagramSmClass extends BehaviorDiagramSmClass {
    @objid ("596d5f3d-dd1a-4e15-ad3f-ed26abbedd2c")
    public BpmnProcessCollaborationDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("ade13fb2-819d-407a-b789-5baa832bf10c")
    @Override
    public String getName() {
        return "BpmnProcessCollaborationDiagram";
    }

    @objid ("db7af395-3f4f-42e6-9537-e0d7e9e960a0")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("4522058d-92ed-4167-9e68-7a9a51792949")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnProcessCollaborationDiagram.class;
    }

    @objid ("c4fde8f1-e31e-4b7f-afff-034ec57531b1")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("aa2678ca-c9c4-475c-82a0-b50ec6faa761")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("08640831-91b9-4d93-b26f-a824c52cb579")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BehaviorDiagram.MQNAME);
        this.registerFactory(new BpmnProcessCollaborationDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("02cfe52b-cf34-43d5-a6f9-967260fe7dc7")
    private static class BpmnProcessCollaborationDiagramObjectFactory implements ISmObjectFactory {
        @objid ("f7a3d63e-366a-44f0-9689-6d75ed71c0d3")
        private BpmnProcessCollaborationDiagramSmClass smClass;

        @objid ("dbf34ded-8c07-4ec4-899c-1860ca1e5300")
        public BpmnProcessCollaborationDiagramObjectFactory(BpmnProcessCollaborationDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2906f7e8-2350-49b3-b8d4-8880e25e93b1")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("bb4c17c3-ae13-4609-ba47-d40e24beab1a")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

}
