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
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.impl.bpmn.events.BpmnCatchEventSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("98fae2f8-e4cc-4296-8be0-a8346788e6bd")
public class BpmnIntermediateCatchEventSmClass extends BpmnCatchEventSmClass {
    @objid ("b8ca1690-811b-4370-af63-113efdd1cd0f")
    public BpmnIntermediateCatchEventSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f38e5dfd-1cbc-43d3-8456-07b1628438a5")
    @Override
    public String getName() {
        return "BpmnIntermediateCatchEvent";
    }

    @objid ("b46c1598-db03-4d87-82fa-8deef8664ade")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("33956931-7bce-43b8-b60b-d245f5da5d90")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnIntermediateCatchEvent.class;
    }

    @objid ("ab5b7b6f-8e1c-440d-b76b-c8080e381c40")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("dbbe7f39-64e1-48e3-9bab-a2fcf418e0fc")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("6e17d7b6-08d1-410c-93f1-c1b482b14ec3")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnCatchEvent.MQNAME);
        this.registerFactory(new BpmnIntermediateCatchEventObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("faf2d465-3ce2-45fb-b523-b13488382e11")
    private static class BpmnIntermediateCatchEventObjectFactory implements ISmObjectFactory {
        @objid ("0bc8afec-1d32-4b39-8f5b-c63b1432a434")
        private BpmnIntermediateCatchEventSmClass smClass;

        @objid ("2a088275-b618-4a03-b33a-98721f41e8e0")
        public BpmnIntermediateCatchEventObjectFactory(BpmnIntermediateCatchEventSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c22730dc-ec92-4389-aebf-69a54569ac90")
        @Override
        public ISmObjectData createData() {
            return new BpmnIntermediateCatchEventData(this.smClass);
        }

        @objid ("8876d37e-5093-468e-90a4-4ea2ec7af582")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnIntermediateCatchEventImpl();
        }

    }

}
