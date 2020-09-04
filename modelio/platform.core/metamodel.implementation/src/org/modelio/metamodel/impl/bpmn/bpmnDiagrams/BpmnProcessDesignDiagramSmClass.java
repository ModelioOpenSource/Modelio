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
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.impl.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("d6b8d28d-7300-4fd1-85aa-087ef09e54d6")
public class BpmnProcessDesignDiagramSmClass extends BpmnProcessCollaborationDiagramSmClass {
    @objid ("5f3c5a6d-2af5-45ed-9c83-8f0d408b7afb")
    public BpmnProcessDesignDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6af1b9cc-a2a5-419d-bc27-6dd85826900d")
    @Override
    public String getName() {
        return "BpmnProcessDesignDiagram";
    }

    @objid ("b66b4445-e51a-40b7-8d62-9a60f71e5da5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1a129e12-ef50-4185-84cf-e5adbcb56770")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnProcessDesignDiagram.class;
    }

    @objid ("61205287-1430-4238-9d58-f838cb9c39f2")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("ac118003-7098-4817-a100-dbf2080e344b")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("816c95b1-1a10-42f1-a6c4-b5f3fab7e9ae")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnProcessCollaborationDiagram.MQNAME);
        this.registerFactory(new BpmnProcessDesignDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("a15c2251-15c5-44de-8f45-19b4ae42e0f0")
    private static class BpmnProcessDesignDiagramObjectFactory implements ISmObjectFactory {
        @objid ("9223bcb8-d9fe-4afb-81a7-f35c3c8bc92c")
        private BpmnProcessDesignDiagramSmClass smClass;

        @objid ("b6f5a8ad-d485-4308-9415-11a6417dd88f")
        public BpmnProcessDesignDiagramObjectFactory(BpmnProcessDesignDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("a5ab1565-0e1d-45e6-a691-1e5ee5c64e22")
        @Override
        public ISmObjectData createData() {
            return new BpmnProcessDesignDiagramData(this.smClass);
        }

        @objid ("07ce43e1-8c0e-4123-b5ec-329b0f4c6843")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnProcessDesignDiagramImpl();
        }

    }

}
