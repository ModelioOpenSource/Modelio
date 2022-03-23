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
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00164f7c-c4bf-1fd8-97fe-001ec947cd2a")
public class PackageImportImpl extends UmlModelElementImpl implements PackageImport {
    @objid ("093c21a8-8a68-4291-aac7-cc7e89def05c")
    @Override
    public VisibilityMode getVisibility() {
        return (VisibilityMode) getAttVal(((PackageImportSmClass)getClassOf()).getVisibilityAtt());
    }

    @objid ("6653b561-5e22-4dc4-9e4c-ebcf23d3cc4a")
    @Override
    public void setVisibility(VisibilityMode value) {
        setAttVal(((PackageImportSmClass)getClassOf()).getVisibilityAtt(), value);
    }

    @objid ("dc44e78a-9bd2-4d05-8e8a-77673b04143e")
    @Override
    public Operation getImportingOperation() {
        Object obj = getDepVal(((PackageImportSmClass)getClassOf()).getImportingOperationDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("5052f38d-a972-45f5-b593-c1070184b1e4")
    @Override
    public void setImportingOperation(Operation value) {
        appendDepVal(((PackageImportSmClass)getClassOf()).getImportingOperationDep(), (SmObjectImpl)value);
    }

    @objid ("8dd35b58-6ba3-42db-be90-7542e15e37d8")
    @Override
    public NameSpace getImportingNameSpace() {
        Object obj = getDepVal(((PackageImportSmClass)getClassOf()).getImportingNameSpaceDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("766b3395-b9cb-466a-91a8-2b2c02aadee9")
    @Override
    public void setImportingNameSpace(NameSpace value) {
        appendDepVal(((PackageImportSmClass)getClassOf()).getImportingNameSpaceDep(), (SmObjectImpl)value);
    }

    @objid ("d996e3a3-171d-4f98-8cbb-3a5bd77bbada")
    @Override
    public Package getImportedPackage() {
        Object obj = getDepVal(((PackageImportSmClass)getClassOf()).getImportedPackageDep());
        return (obj instanceof Package)? (Package)obj : null;
    }

    @objid ("42eb7a5e-5564-4d45-9059-e808e5ee5746")
    @Override
    public void setImportedPackage(Package value) {
        appendDepVal(((PackageImportSmClass)getClassOf()).getImportedPackageDep(), (SmObjectImpl)value);
    }

    @objid ("95e34993-720f-4de8-a64a-47eb3e8f155a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ImportingOperation
        obj = (SmObjectImpl)this.getDepVal(((PackageImportSmClass)getClassOf()).getImportingOperationDep());
        if (obj != null)
          return obj;
        // ImportingNameSpace
        obj = (SmObjectImpl)this.getDepVal(((PackageImportSmClass)getClassOf()).getImportingNameSpaceDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("541cff18-cc67-4600-8df9-09b97396309c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ImportingOperation
        dep = ((PackageImportSmClass)getClassOf()).getImportingOperationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // ImportingNameSpace
        dep = ((PackageImportSmClass)getClassOf()).getImportingNameSpaceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("f0576842-ed4c-47ca-afc2-6e8d350e57eb")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitPackageImport(this);
    }

}
