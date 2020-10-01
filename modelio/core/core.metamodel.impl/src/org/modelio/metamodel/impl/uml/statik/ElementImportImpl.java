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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.ElementImportData;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00083b94-c4bf-1fd8-97fe-001ec947cd2a")
public class ElementImportImpl extends UmlModelElementImpl implements ElementImport {
    @objid ("1f8b98ae-fb43-49a0-baed-757ec4e77b66")
    @Override
    public VisibilityMode getVisibility() {
        return (VisibilityMode) getAttVal(((ElementImportSmClass)getClassOf()).getVisibilityAtt());
    }

    @objid ("2ab2c1c4-bda4-46d6-9ced-13b96179d03c")
    @Override
    public void setVisibility(VisibilityMode value) {
        setAttVal(((ElementImportSmClass)getClassOf()).getVisibilityAtt(), value);
    }

    @objid ("ddd74cee-d4a8-4263-b3b9-700e76ec370b")
    @Override
    public NameSpace getImportingNameSpace() {
        Object obj = getDepVal(((ElementImportSmClass)getClassOf()).getImportingNameSpaceDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("1cb5fed7-54cb-4396-8a66-f7c34cbc6187")
    @Override
    public void setImportingNameSpace(NameSpace value) {
        appendDepVal(((ElementImportSmClass)getClassOf()).getImportingNameSpaceDep(), (SmObjectImpl)value);
    }

    @objid ("80b9d71b-c736-47a4-9667-49c0c1be2886")
    @Override
    public NameSpace getImportedElement() {
        Object obj = getDepVal(((ElementImportSmClass)getClassOf()).getImportedElementDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("f07611d6-9a1c-4edd-867c-d6cbf3168cca")
    @Override
    public void setImportedElement(NameSpace value) {
        appendDepVal(((ElementImportSmClass)getClassOf()).getImportedElementDep(), (SmObjectImpl)value);
    }

    @objid ("b4bcab4f-3bcf-42eb-aa4a-08edf6f98592")
    @Override
    public Operation getImportingOperation() {
        Object obj = getDepVal(((ElementImportSmClass)getClassOf()).getImportingOperationDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("f138d659-3b2d-45c2-bdae-7a9e04cc8074")
    @Override
    public void setImportingOperation(Operation value) {
        appendDepVal(((ElementImportSmClass)getClassOf()).getImportingOperationDep(), (SmObjectImpl)value);
    }

    @objid ("da824f61-6cdf-4146-9f4a-946d92da968a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ImportingNameSpace
        obj = (SmObjectImpl)this.getDepVal(((ElementImportSmClass)getClassOf()).getImportingNameSpaceDep());
        if (obj != null)
          return obj;
        // ImportingOperation
        obj = (SmObjectImpl)this.getDepVal(((ElementImportSmClass)getClassOf()).getImportingOperationDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("3aa30c78-0f4b-49ab-8777-ceb41280852d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ImportingNameSpace
        dep = ((ElementImportSmClass)getClassOf()).getImportingNameSpaceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // ImportingOperation
        dep = ((ElementImportSmClass)getClassOf()).getImportingOperationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("08deab10-7e77-47d0-aa49-f7047a8c2580")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitElementImport(this);
    }

}
