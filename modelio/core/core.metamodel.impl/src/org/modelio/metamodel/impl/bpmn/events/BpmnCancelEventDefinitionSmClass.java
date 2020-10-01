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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("91036f21-67af-4bbc-b98d-6a3bcdf755fc")
public class BpmnCancelEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("d30d6b06-c62d-499f-9c3d-12e54d0d8567")
    public BpmnCancelEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("126b2f03-fe7c-4d3c-a057-5820221a6b53")
    @Override
    public String getName() {
        return "BpmnCancelEventDefinition";
    }

    @objid ("4261b342-cc28-4a36-8e2d-1da774ea825a")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7c2c36ef-1000-43de-8a4b-cb7ee1591741")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnCancelEventDefinition.class;
    }

    @objid ("97ad4e07-f22a-445a-8630-84fbb0117abc")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("2056f8f7-ddf0-4c2d-a2ac-2428ab0f3983")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6b450e99-21e5-40e1-a941-cf66be982db0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnCancelEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("c1cb4099-8018-4ca7-90c3-d9c2e70d6bd1")
    private static class BpmnCancelEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("031f434d-b658-4259-81d3-c2899b18796f")
        private BpmnCancelEventDefinitionSmClass smClass;

        @objid ("0cca24c8-8ff2-4da9-9e27-f89f721f00b9")
        public BpmnCancelEventDefinitionObjectFactory(BpmnCancelEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("79a8d6e5-5692-4ab8-bee3-0971577d684f")
        @Override
        public ISmObjectData createData() {
            return new BpmnCancelEventDefinitionData(this.smClass);
        }

        @objid ("bd63f825-29b3-4844-8473-f9a7e45fca48")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnCancelEventDefinitionImpl();
        }

    }

}
