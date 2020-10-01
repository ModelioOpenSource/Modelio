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
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.AbstractPseudoStateSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.TerminatePseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1427429a-2d1c-400a-81f2-36a8036426ab")
public class TerminatePseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("875c460c-21ce-4b83-87eb-2658a224f986")
    public TerminatePseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("396aad33-6f7c-4498-8d7e-99feca85380a")
    @Override
    public String getName() {
        return "TerminatePseudoState";
    }

    @objid ("c0d30df5-79b6-4414-b747-423753ed831b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e1438fa5-cc4b-4418-b605-2dc097887751")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TerminatePseudoState.class;
    }

    @objid ("88667452-8717-4cc5-854c-a4cad1c4ed2f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("cd76ea30-dd61-4a16-a1b9-a200cb5a8958")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("fae63e77-3275-4aa6-9ad9-2fd7e16fb076")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new TerminatePseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("19122cb5-e5fd-4992-acb1-79f91ccc2390")
    private static class TerminatePseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("49a990b0-a386-4b59-9c6e-3d656f165628")
        private TerminatePseudoStateSmClass smClass;

        @objid ("15aba4b8-de68-4afb-85fe-a8f8a46557b8")
        public TerminatePseudoStateObjectFactory(TerminatePseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("38c5b5a0-609f-46c5-b2f1-85b338d39c16")
        @Override
        public ISmObjectData createData() {
            return new TerminatePseudoStateData(this.smClass);
        }

        @objid ("32a928e0-e1df-4eff-b5b7-b0ddc2be2b79")
        @Override
        public SmObjectImpl createImpl() {
            return new TerminatePseudoStateImpl();
        }

    }

}
