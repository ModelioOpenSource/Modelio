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
package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseDependencyData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
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

@objid ("00592d06-c4bf-1fd8-97fe-001ec947cd2a")
public class UseCaseDependencyImpl extends UmlModelElementImpl implements UseCaseDependency {
    @objid ("fe39db27-e7ae-4ecd-b41a-b87b869b2944")
    @Override
    public UseCase getOrigin() {
        Object obj = getDepVal(((UseCaseDependencySmClass)getClassOf()).getOriginDep());
        return (obj instanceof UseCase)? (UseCase)obj : null;
    }

    @objid ("004ed8f4-a7ac-4950-8650-e654439990b4")
    @Override
    public void setOrigin(UseCase value) {
        appendDepVal(((UseCaseDependencySmClass)getClassOf()).getOriginDep(), (SmObjectImpl)value);
    }

    @objid ("79ee7b83-62be-456e-9c5d-cebcd1891683")
    @Override
    public EList<ExtensionPoint> getExtensionLocation() {
        return new SmList<>(this, ((UseCaseDependencySmClass)getClassOf()).getExtensionLocationDep());
    }

    @objid ("87baa669-ddff-4ab6-936c-70104301f243")
    @Override
    public <T extends ExtensionPoint> List<T> getExtensionLocation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ExtensionPoint element : getExtensionLocation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("6ee3bfb5-32d7-43e9-840f-484b9204c6bf")
    @Override
    public UseCase getTarget() {
        Object obj = getDepVal(((UseCaseDependencySmClass)getClassOf()).getTargetDep());
        return (obj instanceof UseCase)? (UseCase)obj : null;
    }

    @objid ("4d78ac87-2550-4125-956f-4636419bed64")
    @Override
    public void setTarget(UseCase value) {
        appendDepVal(((UseCaseDependencySmClass)getClassOf()).getTargetDep(), (SmObjectImpl)value);
    }

    @objid ("64032fc3-8d32-4c8c-b1b6-1c30a8b4810e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Origin
        obj = (SmObjectImpl)this.getDepVal(((UseCaseDependencySmClass)getClassOf()).getOriginDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("f451f184-f738-4850-8488-0248646ceb50")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Origin
        dep = ((UseCaseDependencySmClass)getClassOf()).getOriginDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("0c7248c6-277f-4f86-86d8-84f3af9f5d3a")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitUseCaseDependency(this);
    }

}
