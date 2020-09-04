/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseData;
import org.modelio.metamodel.impl.uml.statik.GeneralClassImpl;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0058abf6-c4bf-1fd8-97fe-001ec947cd2a")
public class UseCaseImpl extends GeneralClassImpl implements UseCase {
    @objid ("4f615806-efda-4674-a7b2-3e9ff14e1b7e")
    @Override
    public EList<UseCaseDependency> getUsed() {
        return new SmList<>(this, ((UseCaseSmClass)getClassOf()).getUsedDep());
    }

    @objid ("276777da-764c-4572-9a23-48e3e2aa963c")
    @Override
    public <T extends UseCaseDependency> List<T> getUsed(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final UseCaseDependency element : getUsed()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("42e154f2-22b6-450f-9c2f-70b8327740da")
    @Override
    public EList<ExtensionPoint> getOwnedExtension() {
        return new SmList<>(this, ((UseCaseSmClass)getClassOf()).getOwnedExtensionDep());
    }

    @objid ("a75d58df-c1fd-44b3-bbb8-c4c25c02c540")
    @Override
    public <T extends ExtensionPoint> List<T> getOwnedExtension(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ExtensionPoint element : getOwnedExtension()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("989f9399-573d-4eb8-ac71-b8a5b3f6a356")
    @Override
    public EList<UseCaseDependency> getUser() {
        return new SmList<>(this, ((UseCaseSmClass)getClassOf()).getUserDep());
    }

    @objid ("bfe09e9f-1e37-4dd3-b810-c122df309d3b")
    @Override
    public <T extends UseCaseDependency> List<T> getUser(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final UseCaseDependency element : getUser()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("03b2f665-65a1-4107-87d3-799d62037b70")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("75876715-4dba-412c-9925-ba3b687cfc1c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("3683363e-8a19-4774-928d-259ecac67b55")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitUseCase(this);
    }

}
