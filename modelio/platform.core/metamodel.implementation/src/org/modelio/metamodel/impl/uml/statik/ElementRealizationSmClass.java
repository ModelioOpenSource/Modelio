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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractionSmClass;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("5a66d517-b555-404b-b7f5-fbb06071a8be")
public class ElementRealizationSmClass extends AbstractionSmClass {
    @objid ("2c3a8f65-9a7b-4328-9cfe-c5c6910fcb3a")
    public ElementRealizationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("182b383e-6dd1-42ff-8a7f-2d90658ea0c9")
    @Override
    public String getName() {
        return "ElementRealization";
    }

    @objid ("c33d7668-1f43-4e32-8a11-ea93e8749ece")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0b1b513d-5890-4723-ae31-280f086fb93c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ElementRealization.class;
    }

    @objid ("1fbec62f-f0ec-44e5-bdcb-88ac7470a3a4")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("86505014-2044-4344-8343-cd263ba63c4c")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("afc07404-a878-47f0-b971-27167ed38cde")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Abstraction.MQNAME);
        this.registerFactory(new ElementRealizationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("cc39d3c3-2d2e-42a5-a6a7-ff82fa7ed717")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("c5da83fd-5c94-4f55-bbd5-3050c0ee1fd9")
    private static class ElementRealizationObjectFactory implements ISmObjectFactory {
        @objid ("dfb8369d-3506-43e1-9c97-68bff5fce684")
        private ElementRealizationSmClass smClass;

        @objid ("7fd45e4b-b3c6-4e23-910c-490daa9134c4")
        public ElementRealizationObjectFactory(ElementRealizationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8331abb4-a813-4f20-bbc0-2814231a39eb")
        @Override
        public ISmObjectData createData() {
            return new ElementRealizationData(this.smClass);
        }

        @objid ("c1f8b295-3a32-49ec-b0c2-fc9c8fa5fa7c")
        @Override
        public SmObjectImpl createImpl() {
            return new ElementRealizationImpl();
        }

    }

}
