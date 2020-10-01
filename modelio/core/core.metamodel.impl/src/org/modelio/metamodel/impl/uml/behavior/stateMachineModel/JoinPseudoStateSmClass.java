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
import org.modelio.metamodel.uml.behavior.stateMachineModel.JoinPseudoState;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("ad158599-e4d5-48f9-8d7a-9611ad5776cb")
public class JoinPseudoStateSmClass extends AbstractPseudoStateSmClass {
    @objid ("1d3286b5-1659-429a-91ae-6c8ada4ef581")
    public JoinPseudoStateSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1776da35-7ad0-4010-9492-264852175d98")
    @Override
    public String getName() {
        return "JoinPseudoState";
    }

    @objid ("9133c6d3-c84e-43e7-a998-af5d53a4067f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("4a9c74b0-1ed3-4629-9307-e510b565fa06")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return JoinPseudoState.class;
    }

    @objid ("dec71aaf-015d-44c7-8ef1-6a04b6a63e9d")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("dd510a5a-f722-4f6e-9c25-99b734ff3760")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("4e10cb78-1452-48e9-a847-1057c49e2bbb")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractPseudoState.MQNAME);
        this.registerFactory(new JoinPseudoStateObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("c0d07e40-271c-4c2b-a530-c82550a1c64a")
    private static class JoinPseudoStateObjectFactory implements ISmObjectFactory {
        @objid ("1533928d-e7da-4a58-8334-0e6d42bb2c4f")
        private JoinPseudoStateSmClass smClass;

        @objid ("690824dd-6e95-40f4-8d78-469473cd2c41")
        public JoinPseudoStateObjectFactory(JoinPseudoStateSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fcddaa0e-3033-4c98-b808-20c750207702")
        @Override
        public ISmObjectData createData() {
            return new JoinPseudoStateData(this.smClass);
        }

        @objid ("494e1a8f-e151-495b-96e2-44514986f005")
        @Override
        public SmObjectImpl createImpl() {
            return new JoinPseudoStateImpl();
        }

    }

}
