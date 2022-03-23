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

package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("7c2c07e6-b241-4c59-aab8-908f4095c5cf")
public class AbstractPseudoStateSmClass extends StateVertexSmClass {
    @objid ("0c8f5a53-dfc4-4b4c-8106-1009e55c016e")
    public  AbstractPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6e50858b-fab5-4ffa-83ee-f3b49e63fb4a")
    @Override
    public String getName() {
        return "AbstractPseudoState";
        
    }

    @objid ("e742fc94-54eb-44fe-8929-c14a4955fce1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("22f4693f-017f-458a-ac82-653969e7e7f5")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractPseudoState.class;
        
    }

    @objid ("c836d2dd-d5f7-4964-812e-84d709b6d634")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("48c26c53-d32f-4ba5-9219-b0675279cb66")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("1d2e5a19-e229-4d79-a275-05424d10d627")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StateVertex.MQNAME);
        this.registerFactory(new AbstractPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("887b89db-d6e2-4a29-a813-3023f6e26c6c")
    private static class AbstractPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("be5f25b0-4c4b-47b4-bdf1-431aedf94422")
        private AbstractPseudoStateSmClass smClass;

        @objid ("c16f472e-75c4-4925-bdfd-ae3d79820685")
        public  AbstractPseudoStateObjectFactory(AbstractPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("65c75fc7-b633-4571-9015-4dfd00147273")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("22716784-e8cd-44e0-9e49-555cc91ef66e")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

}
