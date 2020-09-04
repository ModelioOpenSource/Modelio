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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
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

@objid ("26197e71-813e-4e29-85ae-553ffa4bcfed")
public class BpmnEndEventSmClass extends BpmnThrowEventSmClass {
    @objid ("48dcb931-18df-4f5b-bdb0-80a805b518a4")
    public BpmnEndEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1a5811a1-576c-4db4-922a-c5eb34dc0fb5")
    @Override
    public String getName() {
        return "BpmnEndEvent";
    }

    @objid ("3f479ce8-a6a9-4340-af3f-45a20bcfa0ce")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("c9e21413-89c5-4ae2-84a4-0cce65445112")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnEndEvent.class;
    }

    @objid ("93b64a34-351d-4954-9856-9abcc9c9801e")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("a7e2cca8-14d2-4604-a87c-9236c9b59a46")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("a8a3f0aa-fe46-4808-98f9-4f4ff3fe19af")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnThrowEvent.MQNAME);
        this.registerFactory(new BpmnEndEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("75605232-2271-4387-a9a8-b9b7f44426a8")
    private static class BpmnEndEventObjectFactory implements ISmObjectFactory {
        @objid ("32aee751-1d34-481a-b93f-3a8f500b415f")
        private BpmnEndEventSmClass smClass;

        @objid ("41e4d45a-0891-4129-ba48-4f003aade6cc")
        public BpmnEndEventObjectFactory(BpmnEndEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1ff42d84-6020-4781-a72b-440a605be0bf")
        @Override
        public ISmObjectData createData() {
            return new BpmnEndEventData(this.smClass);
        }

        @objid ("86df1207-6502-4db7-8851-48fddba8c2ea")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnEndEventImpl();
        }

    }

}
