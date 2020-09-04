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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.AbstractPseudoStateSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JunctionPseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("4b1536d5-b513-4b9d-a47b-8fd194150113")
public class JunctionPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("7be357a5-ea38-41b9-9bb9-354fafbbd2ee")
    public JunctionPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cab61db5-14ec-4b25-a007-effbe246fe2a")
    @Override
    public String getName() {
        return "JunctionPseudoState";
    }

    @objid ("0e718888-87bc-4883-811d-c18de0a96234")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b4f8c69a-9b26-4bad-809d-b2f26b42a456")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return JunctionPseudoState.class;
    }

    @objid ("5ac07fa9-1a0a-4bbe-8d4b-2c3e2936f080")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4abda032-2d8e-4a4f-8e57-e5f0acf31b06")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("dfc6c01d-6ee5-48b5-a893-bac4952d1b59")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new JunctionPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("a46d59d5-77d7-496b-b76c-510283274503")
    private static class JunctionPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("1cb25a30-c362-4b7c-a117-fe9cd65a3607")
        private JunctionPseudoStateSmClass smClass;

        @objid ("e25b747b-fb46-4434-9030-8a96d7f0af66")
        public JunctionPseudoStateObjectFactory(JunctionPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4495d06b-45ee-4f9d-8db8-b99e2ef9d062")
        @Override
        public ISmObjectData createData() {
            return new JunctionPseudoStateData(this.smClass);
        }

        @objid ("ff3a095e-ee5a-4bd8-8e4a-0a53373ec6b8")
        @Override
        public SmObjectImpl createImpl() {
            return new JunctionPseudoStateImpl();
        }

    }

}
