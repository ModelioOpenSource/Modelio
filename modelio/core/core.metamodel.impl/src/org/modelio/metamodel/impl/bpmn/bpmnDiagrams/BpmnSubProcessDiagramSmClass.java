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

package org.modelio.metamodel.impl.bpmn.bpmnDiagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
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

@objid ("a7dbfe06-55c5-408b-acf6-07a470a082f9")
public class BpmnSubProcessDiagramSmClass extends BehaviorDiagramSmClass {
    @objid ("c803c835-a2e6-489c-b195-6163b1541ec6")
    public  BpmnSubProcessDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c46ce9db-b73e-4f78-8da7-1b1a0a2b2103")
    @Override
    public String getName() {
        return "BpmnSubProcessDiagram";
        
    }

    @objid ("a54dac50-a679-4230-bc9e-68f32294131e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8ec6747f-a292-41ea-a92c-d464940ec9b1")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSubProcessDiagram.class;
        
    }

    @objid ("8d024793-184d-491b-ae3d-dab102377f01")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("ac14748f-bde7-4b33-9e29-9ece49c2814f")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("0d870fc0-f77d-4046-aba7-a3712d028ade")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BehaviorDiagram.MQNAME);
        this.registerFactory(new BpmnSubProcessDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("41592cc1-b0f6-46d7-9ca1-8c87985bc725")
    private static class BpmnSubProcessDiagramObjectFactory implements ISmObjectFactory {
        @objid ("e0048848-5f4c-49c4-894d-15dbeba25a82")
        private BpmnSubProcessDiagramSmClass smClass;

        @objid ("8d96198a-bfb4-4519-9943-6a23e8ac744b")
        public  BpmnSubProcessDiagramObjectFactory(BpmnSubProcessDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a03ca759-22e3-45d4-92ad-e5d015bf9f45")
        @Override
        public ISmObjectData createData() {
            return new BpmnSubProcessDiagramData(this.smClass);
        }

        @objid ("f53897bc-f4af-44d1-afe6-b5bd6cbf5e15")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnSubProcessDiagramImpl();
        }

    }

}
