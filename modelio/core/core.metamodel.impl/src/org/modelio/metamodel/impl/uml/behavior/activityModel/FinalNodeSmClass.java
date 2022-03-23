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

package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.metamodel.uml.behavior.activityModel.FinalNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("6972dd04-ccd1-4e3b-ae02-750fa545ddbb")
public class FinalNodeSmClass extends ControlNodeSmClass {
    @objid ("2f637edd-722a-47c6-bd34-fe98a5ef44dd")
    public  FinalNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("24ea8822-efdb-4ffd-b8bb-0172b444fc25")
    @Override
    public String getName() {
        return "FinalNode";
        
    }

    @objid ("76043ee7-21fc-4280-8d2e-02255ad9287f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("218df84c-5675-430a-98f5-19ba04f440de")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return FinalNode.class;
        
    }

    @objid ("3160a31b-5f5e-47b8-84cc-fc382af265c8")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("c0c87f92-b541-4c45-9db2-bcc329f43974")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("6804d0f8-129c-4ad3-8d0a-6f6e92b66745")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ControlNode.MQNAME);
        this.registerFactory(new FinalNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("79ac889c-53b9-4369-a293-0741bc21853e")
    private static class FinalNodeObjectFactory implements ISmObjectFactory {
        @objid ("e98a07bd-be28-4826-b7a1-0bfed2477bca")
        private FinalNodeSmClass smClass;

        @objid ("6cce4975-da4a-4c34-8c30-dc4fb9141f43")
        public  FinalNodeObjectFactory(FinalNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("be7f6c69-52b9-4113-843c-fb7efdebed3b")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("37f69c3c-b194-4759-8d60-7833cf1f15a9")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

}
