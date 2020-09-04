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
package org.modelio.metamodel.impl.bpmn.bpmnDiagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.impl.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("32f2f9cc-ecfb-41bc-a91c-4765f601994c")
public class BpmnCollaborationDiagramSmClass extends BpmnProcessCollaborationDiagramSmClass {
    @objid ("059ac4e8-0854-47ec-a291-70ac6820206e")
    public BpmnCollaborationDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a6726949-f540-4b5d-b973-f2a97e20efaf")
    @Override
    public String getName() {
        return "BpmnCollaborationDiagram";
    }

    @objid ("74f3b8ce-9333-4f82-8894-18207d547565")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7c5c56b7-c3ef-493d-9dce-6e16945169ef")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnCollaborationDiagram.class;
    }

    @objid ("4b705c02-6cac-4ec3-9a7d-f4c5fefc539e")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("b73dbb8f-a362-4a66-8b54-983f73b3bbae")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f6c60a8c-3985-4a76-8010-b82a7ae507db")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnProcessCollaborationDiagram.MQNAME);
        this.registerFactory(new BpmnCollaborationDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("bb6b345c-893a-4c6e-ac5c-9cf039e8871c")
    private static class BpmnCollaborationDiagramObjectFactory implements ISmObjectFactory {
        @objid ("93e76c27-a83b-4881-8d9c-a087cb9b2013")
        private BpmnCollaborationDiagramSmClass smClass;

        @objid ("d17c4c28-eb75-458f-a471-dd32ef9ddb59")
        public BpmnCollaborationDiagramObjectFactory(BpmnCollaborationDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e2d14f2d-b047-4a97-9169-538ea5e53643")
        @Override
        public ISmObjectData createData() {
            return new BpmnCollaborationDiagramData(this.smClass);
        }

        @objid ("fc64a165-9118-46af-b1a8-e72c2c2de2d1")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnCollaborationDiagramImpl();
        }

    }

}
