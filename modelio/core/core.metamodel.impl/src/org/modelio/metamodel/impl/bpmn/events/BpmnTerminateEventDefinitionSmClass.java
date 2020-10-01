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
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("ead508d0-cc97-4e68-9a32-c338b88fa836")
public class BpmnTerminateEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("17554c6f-5cb4-4a34-a5cc-3f21d6650964")
    public BpmnTerminateEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f8c2850c-748a-4101-95cb-b67d7b666082")
    @Override
    public String getName() {
        return "BpmnTerminateEventDefinition";
    }

    @objid ("7fdc35c6-c8b9-418b-a240-7b5bb93bb9b5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("338ca0c4-e1e4-4e38-a3b7-4a085dfdf388")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnTerminateEventDefinition.class;
    }

    @objid ("506facc7-2c1b-4153-a168-ba832696664e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("352050ab-99ef-4873-ad2d-5868b6d20af8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("4d30e239-abce-4d06-b860-2578edd9cb9d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnTerminateEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("1b558904-e88e-4390-9daf-870c22e350ee")
    private static class BpmnTerminateEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("62ef26f9-0421-4cac-a47d-73e5b06aeaea")
        private BpmnTerminateEventDefinitionSmClass smClass;

        @objid ("e33780b8-5862-493d-9e60-25bd880f15cd")
        public BpmnTerminateEventDefinitionObjectFactory(BpmnTerminateEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("5d1bb64d-f74f-4523-9d81-97146a206d52")
        @Override
        public ISmObjectData createData() {
            return new BpmnTerminateEventDefinitionData(this.smClass);
        }

        @objid ("ac1f0b34-a5f1-4619-bf65-2a0a2bc24854")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnTerminateEventDefinitionImpl();
        }

    }

}
