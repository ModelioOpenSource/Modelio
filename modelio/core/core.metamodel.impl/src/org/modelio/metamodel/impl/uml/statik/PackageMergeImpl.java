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
import org.modelio.metamodel.impl.uml.statik.PackageMergeData;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00172b86-c4bf-1fd8-97fe-001ec947cd2a")
public class PackageMergeImpl extends UmlModelElementImpl implements PackageMerge {
    @objid ("550e2ee5-a6f9-4d16-8e31-9c9e9a7417a6")
    @Override
    public Package getMergedPackage() {
        Object obj = getDepVal(((PackageMergeSmClass)getClassOf()).getMergedPackageDep());
        return (obj instanceof Package)? (Package)obj : null;
    }

    @objid ("4ebd4ac6-1960-419b-bb14-26e446e2dcc4")
    @Override
    public void setMergedPackage(Package value) {
        appendDepVal(((PackageMergeSmClass)getClassOf()).getMergedPackageDep(), (SmObjectImpl)value);
    }

    @objid ("cb2c5644-5c16-4c71-9607-f7068eb63169")
    @Override
    public Package getReceivingPackage() {
        Object obj = getDepVal(((PackageMergeSmClass)getClassOf()).getReceivingPackageDep());
        return (obj instanceof Package)? (Package)obj : null;
    }

    @objid ("39e10255-b359-4c56-aced-42d9e119f147")
    @Override
    public void setReceivingPackage(Package value) {
        appendDepVal(((PackageMergeSmClass)getClassOf()).getReceivingPackageDep(), (SmObjectImpl)value);
    }

    @objid ("052f2907-6129-4847-8b0c-44903da0fe9c")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ReceivingPackage
        obj = (SmObjectImpl)this.getDepVal(((PackageMergeSmClass)getClassOf()).getReceivingPackageDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("fd03bfcd-d0ba-44ba-b412-6f61202d4545")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ReceivingPackage
        dep = ((PackageMergeSmClass)getClassOf()).getReceivingPackageDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("d96b43be-9fc0-486c-ad27-25658cccff5a")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitPackageMerge(this);
    }

}
