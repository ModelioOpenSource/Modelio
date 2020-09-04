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
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.statik.ClassData;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000247ca-c4bf-1fd8-97fe-001ec947cd2a")
public class ClassImpl extends GeneralClassImpl implements Class {
    @objid ("223fcaf8-4cd4-4fea-a074-74e6e3855320")
    @Override
    public boolean isIsActive() {
        return (Boolean) getAttVal(((ClassSmClass)getClassOf()).getIsActiveAtt());
    }

    @objid ("c96e748b-f5e5-453b-a2ce-1b2a3c165fb8")
    @Override
    public void setIsActive(boolean value) {
        setAttVal(((ClassSmClass)getClassOf()).getIsActiveAtt(), value);
    }

    @objid ("7e42a1ee-fc7d-45d7-8b94-c9b768888d4c")
    @Override
    public boolean isIsMain() {
        return (Boolean) getAttVal(((ClassSmClass)getClassOf()).getIsMainAtt());
    }

    @objid ("2b4272f6-673f-49ee-af0c-1782268394bf")
    @Override
    public void setIsMain(boolean value) {
        setAttVal(((ClassSmClass)getClassOf()).getIsMainAtt(), value);
    }

    @objid ("e0390670-a3cf-4030-903a-5fa74f986287")
    @Override
    public ClassAssociation getLinkToAssociation() {
        Object obj = getDepVal(((ClassSmClass)getClassOf()).getLinkToAssociationDep());
        return (obj instanceof ClassAssociation)? (ClassAssociation)obj : null;
    }

    @objid ("4308e410-7574-4570-9fca-b59e2d2175d9")
    @Override
    public void setLinkToAssociation(ClassAssociation value) {
        appendDepVal(((ClassSmClass)getClassOf()).getLinkToAssociationDep(), (SmObjectImpl)value);
    }

    @objid ("c681c393-7d92-4921-9aec-3d7cb20528cd")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("648b271d-db2b-44f0-9fa1-618fe11f27d2")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("e6116507-7429-4644-a00e-202cb96911ee")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitClass(this);
    }

}
