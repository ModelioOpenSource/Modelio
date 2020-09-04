/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("644df315-fea1-40b6-95e5-cc683ce071c7")
public class InitialPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("d8f9be6d-513e-4be2-a521-88764c2f2701")
    public InitialPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("66b3a95f-f5b1-45c2-98f4-0cddfab2976c")
    @Override
    public String getName() {
        return "InitialPseudoState";
    }

    @objid ("46327344-79bc-47e0-adde-55dc5fab351b")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("858c8308-c00b-41cc-8b37-2d3fa9f082ba")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InitialPseudoState.class;
    }

    @objid ("7bb68d82-7357-4ee8-be36-05ac3d2b6a93")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("8a9a363c-099d-4361-b620-e01c9460a87d")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("06b2fbdf-99dd-44ae-8f75-04d41af020c7")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new InitialPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("e05b04d4-15d5-4973-8dc5-671677246c65")
    private static class InitialPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("822854aa-5aaf-4890-a676-7ce9ab6e1705")
        private InitialPseudoStateSmClass smClass;

        @objid ("254926cd-8964-431f-891d-aa0f07e404e7")
        public InitialPseudoStateObjectFactory(InitialPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8addf12c-a320-4a61-9e3b-815eecd2827f")
        @Override
        public ISmObjectData createData() {
            return new InitialPseudoStateData(this.smClass);
        }

        @objid ("964bb7a3-918e-440d-bc81-d97b9549ee08")
        @Override
        public SmObjectImpl createImpl() {
            return new InitialPseudoStateImpl();
        }

    }

}
