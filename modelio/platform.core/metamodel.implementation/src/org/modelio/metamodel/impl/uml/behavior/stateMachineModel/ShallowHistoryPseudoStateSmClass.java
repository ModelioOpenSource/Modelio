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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("754f8010-1042-4044-860e-56a58ad680d5")
public class ShallowHistoryPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("64d8418f-5bb0-4c7b-8bd2-151d71498a26")
    public ShallowHistoryPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("87edd5c8-ec26-4f56-b46c-8b82418fe858")
    @Override
    public String getName() {
        return "ShallowHistoryPseudoState";
    }

    @objid ("de230543-12ba-4131-ad5d-865677a28c12")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0dda5c38-f53c-4c2f-b167-dced3102f56c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ShallowHistoryPseudoState.class;
    }

    @objid ("58b34d70-8dd6-4aba-9414-40c17b29f4c4")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("785e1612-7446-4d7e-b9d2-3b0bd70b77ef")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("8b181ed4-2304-4df0-8074-88a1fc70d991")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new ShallowHistoryPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("959ff871-02ad-49b9-af01-c55a292186c0")
    private static class ShallowHistoryPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("966b7c91-5c85-4fd2-a52d-ced043d1ff60")
        private ShallowHistoryPseudoStateSmClass smClass;

        @objid ("712e3e55-1be4-49d4-9f8d-a7d1f0f80e34")
        public ShallowHistoryPseudoStateObjectFactory(ShallowHistoryPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("51731ec5-4063-4df5-b82d-eef3672d1028")
        @Override
        public ISmObjectData createData() {
            return new ShallowHistoryPseudoStateData(this.smClass);
        }

        @objid ("94216e39-284a-4dae-9260-16b556214185")
        @Override
        public SmObjectImpl createImpl() {
            return new ShallowHistoryPseudoStateImpl();
        }

    }

}
