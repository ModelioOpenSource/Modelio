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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.statik.PackageData;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00157246-c4bf-1fd8-97fe-001ec947cd2a")
public class PackageImpl extends NameSpaceImpl implements Package {
    @objid ("725bea86-f183-4f30-9d57-abe99dfc043a")
    @Override
    public boolean isIsInstantiable() {
        return (Boolean) getAttVal(((PackageSmClass)getClassOf()).getIsInstantiableAtt());
    }

    @objid ("c88b14d6-83f5-492c-a666-c4c9f84e928b")
    @Override
    public void setIsInstantiable(boolean value) {
        setAttVal(((PackageSmClass)getClassOf()).getIsInstantiableAtt(), value);
    }

    @objid ("765bac34-8305-4d30-a48a-53fd252b62f4")
    @Override
    public EList<PackageMerge> getReceivingMerge() {
        return new SmList<>(this, ((PackageSmClass)getClassOf()).getReceivingMergeDep());
    }

    @objid ("b7e20d3e-7c4e-4888-8c22-16d23f0309dc")
    @Override
    public <T extends PackageMerge> List<T> getReceivingMerge(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PackageMerge element : getReceivingMerge()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("84d67ecf-0062-41e3-9b53-d43746fac505")
    @Override
    public Project getRepresented() {
        Object obj = getDepVal(((PackageSmClass)getClassOf()).getRepresentedDep());
        return (obj instanceof Project)? (Project)obj : null;
    }

    @objid ("b2a5d229-e844-4ecb-89ac-d52eb2c79741")
    @Override
    public void setRepresented(Project value) {
        appendDepVal(((PackageSmClass)getClassOf()).getRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("d0751a8b-e71c-4479-89f5-08d62804c3cb")
    @Override
    public EList<PackageMerge> getMerge() {
        return new SmList<>(this, ((PackageSmClass)getClassOf()).getMergeDep());
    }

    @objid ("1131f596-c097-422e-a6ac-b89c40021f2a")
    @Override
    public <T extends PackageMerge> List<T> getMerge(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PackageMerge element : getMerge()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("850a4c6b-c188-43f1-a5b4-90092c8678e1")
    @Override
    public EList<PackageImport> getPackageImporting() {
        return new SmList<>(this, ((PackageSmClass)getClassOf()).getPackageImportingDep());
    }

    @objid ("71439198-343b-4ba0-a368-16a79ac22332")
    @Override
    public <T extends PackageImport> List<T> getPackageImporting(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PackageImport element : getPackageImporting()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("91b9354c-0fb6-4344-b83c-fdaf6b30ba30")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Represented
        obj = (SmObjectImpl)this.getDepVal(((PackageSmClass)getClassOf()).getRepresentedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("ab43a319-9be3-409f-b5f8-843a6641a14c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Represented
        dep = ((PackageSmClass)getClassOf()).getRepresentedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("fa078c7e-f9da-4ff0-8da5-8512b7c90c96")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitPackage(this);
    }

}
