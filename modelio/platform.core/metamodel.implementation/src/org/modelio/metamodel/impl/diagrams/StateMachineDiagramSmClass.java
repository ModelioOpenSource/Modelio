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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.impl.diagrams.BehaviorDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("05f971dc-3b59-4a75-b579-a10e410a0d7e")
public class StateMachineDiagramSmClass extends BehaviorDiagramSmClass {
    @objid ("6b90f24b-e0a0-4e7e-9295-6099290abe7b")
    public StateMachineDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("212199a1-f044-4fea-a06e-546475b612e7")
    @Override
    public String getName() {
        return "StateMachineDiagram";
    }

    @objid ("583e2c37-f2b8-40ac-9b26-4b178833ad7f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("1704ef4b-5e5c-4082-9f4d-178e2b2f1906")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return StateMachineDiagram.class;
    }

    @objid ("c257384a-6b4d-46cb-a001-245a077935fa")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("e6d20fa9-09eb-47f6-9310-44681c4747d8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("4cf83674-24a1-4c0b-befe-9fdba163da6b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BehaviorDiagram.MQNAME);
        this.registerFactory(new StateMachineDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("1fac7ed7-da96-4b14-80a1-35e56d04cbf7")
    private static class StateMachineDiagramObjectFactory implements ISmObjectFactory {
        @objid ("29236f66-a0b2-48a6-b435-39e68edacae4")
        private StateMachineDiagramSmClass smClass;

        @objid ("cb666b5d-c9ea-4ddc-9fc7-b432cdacde19")
        public StateMachineDiagramObjectFactory(StateMachineDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c6c1222d-066e-45a5-a5af-215b13d6ca3c")
        @Override
        public ISmObjectData createData() {
            return new StateMachineDiagramData(this.smClass);
        }

        @objid ("f631b4a9-ec6e-4904-ad0e-3cfbe416dd67")
        @Override
        public SmObjectImpl createImpl() {
            return new StateMachineDiagramImpl();
        }

    }

}
