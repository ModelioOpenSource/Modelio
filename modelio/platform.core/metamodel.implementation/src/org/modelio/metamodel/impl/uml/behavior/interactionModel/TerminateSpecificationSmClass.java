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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.ExecutionOccurenceSpecificationSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.TerminateSpecification;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1015805d-ba8d-4d95-aaeb-7b62d62c50e9")
public class TerminateSpecificationSmClass extends ExecutionOccurenceSpecificationSmClass {
    @objid ("060c060e-f22c-41ab-a1e9-5ba9a3f60c18")
    public TerminateSpecificationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("51e7114f-af62-4286-bb51-19828b3a9613")
    @Override
    public String getName() {
        return "TerminateSpecification";
    }

    @objid ("79ec41f7-d487-4af2-97e9-9ed7e4d2c77e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a3b943ef-1b75-48d0-b6b6-bdc2bcd24b77")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TerminateSpecification.class;
    }

    @objid ("e82e39b1-e594-4f1f-b23b-da5dcbd6ac4f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("51c4b022-c63c-4e66-aead-e5f8d8dbd467")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("c8cbeb4a-7083-41b8-89c1-326db60f14ab")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ExecutionOccurenceSpecification.MQNAME);
        this.registerFactory(new TerminateSpecificationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("27ccbb0f-6986-4aef-8c5a-de837db64da7")
    private static class TerminateSpecificationObjectFactory implements ISmObjectFactory {
        @objid ("347b5216-14ae-44b7-9d7f-30b1cac5a715")
        private TerminateSpecificationSmClass smClass;

        @objid ("ae2fa340-5400-49fe-a076-1a019e28a2e3")
        public TerminateSpecificationObjectFactory(TerminateSpecificationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fbc5ee69-f6a9-46c9-9c8f-73ab94bd5dcb")
        @Override
        public ISmObjectData createData() {
            return new TerminateSpecificationData(this.smClass);
        }

        @objid ("70ecbdd7-02ab-47d2-b311-ea74e46619aa")
        @Override
        public SmObjectImpl createImpl() {
            return new TerminateSpecificationImpl();
        }

    }

}
