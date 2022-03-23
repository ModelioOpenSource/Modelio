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
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("b0b2b22d-8502-406a-abb3-042ce2c07a78")
public class BpmnSignalEventDefinitionSmClass extends BpmnEventDefinitionSmClass {
    @objid ("322e7e5a-77d8-4f1d-859c-de8f2667dcf9")
    public  BpmnSignalEventDefinitionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9ca1794f-7411-4bdc-8a2c-bd669dd79b3a")
    @Override
    public String getName() {
        return "BpmnSignalEventDefinition";
        
    }

    @objid ("e77f5e28-f07d-469f-9f1e-d2fae52c2044")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("74c51a93-3aa5-479a-ab46-6b7d44bc48ea")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnSignalEventDefinition.class;
        
    }

    @objid ("472a145c-d051-4b74-a88a-a111b51125e8")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("27646ea6-1ee0-471a-83a6-8033cfc27868")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ca7cbf4e-e13a-433e-8959-2574adcc6185")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnEventDefinition.MQNAME);
        this.registerFactory(new BpmnSignalEventDefinitionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("b760a66e-0264-4a69-a21b-d5788f72cd16")
    private static class BpmnSignalEventDefinitionObjectFactory implements ISmObjectFactory {
        @objid ("cc67a40e-0b24-4de2-a027-c215f9124a08")
        private BpmnSignalEventDefinitionSmClass smClass;

        @objid ("858888ef-6abd-461e-a50a-706033cdd82d")
        public  BpmnSignalEventDefinitionObjectFactory(BpmnSignalEventDefinitionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("118ad89f-233a-4e68-a526-4ceef1cbe38b")
        @Override
        public ISmObjectData createData() {
            return new BpmnSignalEventDefinitionData(this.smClass);
        }

        @objid ("28d25c16-3cd4-4896-828a-83af05c2e35c")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnSignalEventDefinitionImpl();
        }

    }

}
