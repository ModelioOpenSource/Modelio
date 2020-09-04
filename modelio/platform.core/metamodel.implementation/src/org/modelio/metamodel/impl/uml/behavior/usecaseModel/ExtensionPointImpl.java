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
package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.ExtensionPointData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0059a6a0-c4bf-1fd8-97fe-001ec947cd2a")
public class ExtensionPointImpl extends UmlModelElementImpl implements ExtensionPoint {
    @objid ("a1fed220-1c12-4c48-9b6f-35a046389177")
    @Override
    public VisibilityMode getVisibility() {
        return (VisibilityMode) getAttVal(((ExtensionPointSmClass)getClassOf()).getVisibilityAtt());
    }

    @objid ("0d3abe4b-582f-4bb2-9e98-edeb0dfa93ba")
    @Override
    public void setVisibility(VisibilityMode value) {
        setAttVal(((ExtensionPointSmClass)getClassOf()).getVisibilityAtt(), value);
    }

    @objid ("0f3c19f9-5400-4209-bd94-c51755fd6b18")
    @Override
    public EList<UseCaseDependency> getExtended() {
        return new SmList<>(this, ((ExtensionPointSmClass)getClassOf()).getExtendedDep());
    }

    @objid ("b5f4a1ba-cabd-4b6c-911c-fcfa5d5ccb40")
    @Override
    public <T extends UseCaseDependency> List<T> getExtended(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final UseCaseDependency element : getExtended()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("4adcd9f3-a046-4bca-b20e-3281a32942bd")
    @Override
    public UseCase getOwner() {
        Object obj = getDepVal(((ExtensionPointSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof UseCase)? (UseCase)obj : null;
    }

    @objid ("4f9b926e-4e6c-46bd-8cf0-df575f31a542")
    @Override
    public void setOwner(UseCase value) {
        appendDepVal(((ExtensionPointSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("4f6e85f9-0dfd-4294-a37d-797fed4f8707")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((ExtensionPointSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("199280ab-f1b8-4775-85f8-b6ad9c0ca813")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((ExtensionPointSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("044eeea9-1253-4bf4-b7d2-6b989212541d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitExtensionPoint(this);
    }

}
