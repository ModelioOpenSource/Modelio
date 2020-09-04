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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ConstraintData;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00856a60-c4be-1fd8-97fe-001ec947cd2a")
public class ConstraintImpl extends UmlModelElementImpl implements Constraint {
    @objid ("006b6ba6-4224-10bf-bd58-001ec947cd2a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        List<SmObjectImpl> list = this.getDepValList(((ConstraintSmClass) getClassOf()).getConstrainedElementDep());
        if (list.isEmpty()) {
            return super.getCompositionOwner();
        } else {
            return list.get(0);
        }
    }

    @objid ("006b90fe-4224-10bf-bd58-001ec947cd2a")
    @Override
    public SmDepVal getCompositionRelation() {
        List<SmObjectImpl> list = this.getDepValList(((ConstraintSmClass) getClassOf()).getConstrainedElementDep());
        if (list.isEmpty()) {
            return super.getCompositionRelation();
        } else {
            return new SmDepVal(((ConstraintSmClass) getClassOf()).getConstrainedElementDep(), list.get(0));
        }
    }

    @objid ("dc0243f1-0d07-41a0-a075-8956cb67cd32")
    @Override
    public void afterEraseDepVal(SmDependency dep, SmObjectImpl value) {
        if (dep == ((ConstraintSmClass) getClassOf()).getConstrainedElementDep()) {
            // Workaround bug where the storage handle is not updated
            EList<UmlModelElement> remainingOwners = getConstrainedElement();
            if (!remainingOwners.isEmpty()) {
                // Remove and add again the first remaining owner.
                // Note : this will trigger recursively the removal & addition of all other owners.
                UmlModelElement r = remainingOwners.get(0);
                r.getConstraintDefinition().remove(this);
        
                r.getConstraintDefinition().add(this);
            }
        }
        
        super.afterEraseDepVal(dep, value);
    }

    @objid ("80772748-239b-41ab-a728-d44e6ba39f9f")
    @Override
    public String getBaseClass() {
        return (String) getAttVal(((ConstraintSmClass)getClassOf()).getBaseClassAtt());
    }

    @objid ("d2b01479-92e7-42a0-ba50-031b41baefa1")
    @Override
    public void setBaseClass(String value) {
        setAttVal(((ConstraintSmClass)getClassOf()).getBaseClassAtt(), value);
    }

    @objid ("602db4a0-a738-4d82-a7d1-5006217cdcf6")
    @Override
    public String getBody() {
        return (String) getAttVal(((ConstraintSmClass)getClassOf()).getBodyAtt());
    }

    @objid ("d75edb9a-19cb-43f1-8ae4-612aa2e186f9")
    @Override
    public void setBody(String value) {
        setAttVal(((ConstraintSmClass)getClassOf()).getBodyAtt(), value);
    }

    @objid ("57e9c310-87d0-4603-87e9-33fd3b7dd9f9")
    @Override
    public String getLanguage() {
        return (String) getAttVal(((ConstraintSmClass)getClassOf()).getLanguageAtt());
    }

    @objid ("c2801e6a-a666-4fad-8d9b-f52afe94d066")
    @Override
    public void setLanguage(String value) {
        setAttVal(((ConstraintSmClass)getClassOf()).getLanguageAtt(), value);
    }

    @objid ("62be6ade-e313-4e38-bcdf-91c379542bad")
    @Override
    public EList<UmlModelElement> getConstrainedElement() {
        return new SmList<>(this, ((ConstraintSmClass)getClassOf()).getConstrainedElementDep());
    }

    @objid ("48ccde0c-14d8-4e5c-a4e5-7cfe29265080")
    @Override
    public <T extends UmlModelElement> List<T> getConstrainedElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final UmlModelElement element : getConstrainedElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d7208105-9508-405d-9579-dad06d3d70c4")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitConstraint(this);
    }

}
