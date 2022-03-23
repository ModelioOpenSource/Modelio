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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ChoicePseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("f0a2a591-fb0e-43e8-b25c-f742e385444c")
public class ChoicePseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("dd608142-c41d-4a9e-8c7d-67d9fb4f7a62")
    public  ChoicePseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9f8bd4c0-2b42-46d6-a8bb-d1ff7aff42c0")
    @Override
    public String getName() {
        return "ChoicePseudoState";
        
    }

    @objid ("247757ee-0f00-4cbb-b2d2-6e30de526038")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e4a0d541-f96a-42e7-9b6d-2065a469fd96")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ChoicePseudoState.class;
        
    }

    @objid ("d2b534a6-288d-42bf-adee-3747e27e246e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("fe6e007c-1c10-449d-859d-c5ed214be19b")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ac338dca-33d9-4a26-8e1c-ca23f3dff0f1")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new ChoicePseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("55cdc34f-c062-4bb0-aefa-f9872760093e")
    private static class ChoicePseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("8eb95cbe-830c-4510-b7fc-959aac947ad1")
        private ChoicePseudoStateSmClass smClass;

        @objid ("ba662c5b-2be9-46d6-a135-e51a72521961")
        public  ChoicePseudoStateObjectFactory(ChoicePseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("40288b7f-b22e-4439-a323-56fe3d4aa96c")
        @Override
        public ISmObjectData createData() {
            return new ChoicePseudoStateData(this.smClass);
        }

        @objid ("68f40965-f264-4e97-bd2e-3fa3932244af")
        @Override
        public SmObjectImpl createImpl() {
            return new ChoicePseudoStateImpl();
        }

    }

}
