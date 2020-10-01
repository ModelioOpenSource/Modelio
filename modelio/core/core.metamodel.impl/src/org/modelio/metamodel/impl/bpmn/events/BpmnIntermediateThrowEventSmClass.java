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
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.impl.bpmn.events.BpmnThrowEventSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1cc3ec74-19cc-4a07-b45c-7f1e553dc4da")
public class BpmnIntermediateThrowEventSmClass extends BpmnThrowEventSmClass {
    @objid ("5f28b827-e382-4e61-9183-28c285808fea")
    public BpmnIntermediateThrowEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("370fad7b-bc37-4b83-9671-8909f85bea68")
    @Override
    public String getName() {
        return "BpmnIntermediateThrowEvent";
    }

    @objid ("e90f01c1-44be-4afd-b2cd-20edcfa46c4b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("ad432215-ba42-423a-bff9-e7ea65c3fb43")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnIntermediateThrowEvent.class;
    }

    @objid ("40c48ed8-c516-459e-a36d-28533e13c37c")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("bd776d85-5e5d-4166-b829-fb8fd13545f9")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("d4498c84-352e-456a-bc7e-cfb390930830")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnThrowEvent.MQNAME);
        this.registerFactory(new BpmnIntermediateThrowEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("d2504228-b459-4e42-9913-7b85d41d67e7")
    private static class BpmnIntermediateThrowEventObjectFactory implements ISmObjectFactory {
        @objid ("eebcf720-45da-465d-a6ce-f10583a69abd")
        private BpmnIntermediateThrowEventSmClass smClass;

        @objid ("700ab714-490e-4e56-ba99-cb2ef41a80a7")
        public BpmnIntermediateThrowEventObjectFactory(BpmnIntermediateThrowEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f016ea1a-93b4-473e-b04b-5899d8416ad1")
        @Override
        public ISmObjectData createData() {
            return new BpmnIntermediateThrowEventData(this.smClass);
        }

        @objid ("62c65654-924a-42f1-946e-34b9b116125e")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnIntermediateThrowEventImpl();
        }

    }

}
