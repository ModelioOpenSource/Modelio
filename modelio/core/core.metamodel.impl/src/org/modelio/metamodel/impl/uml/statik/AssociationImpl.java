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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("fc1c8e64-9214-4368-b40c-1cd3ca98af0e")
public class AssociationImpl extends UmlModelElementImpl implements Association {
    @objid ("2b1bab62-c9cc-48df-b34c-e6427c1c26dc")
    @Override
    public SmObjectImpl getCompositionOwner() {
        for (SmObjectImpl obj : this.getDepValList(((AssociationSmClass) getClassOf()).getEndDep())) {
            return obj;
        }
        return super.getCompositionOwner();
    }

    @objid ("12b5fdbd-b680-4038-b500-0ab8b40b6b74")
    @Override
    public SmDepVal getCompositionRelation() {
        for (SmObjectImpl obj : this.getDepValList(((AssociationSmClass) getClassOf()).getEndDep())) {
            return new SmDepVal(((AssociationSmClass) getClassOf()).getEndDep(), obj);
        }
        return super.getCompositionRelation();
    }

    @objid ("953d87c0-b546-410b-80b4-a19f422319c1")
    @Override
    public EList<Link> getOccurence() {
        return new SmList<>(this, ((AssociationSmClass)getClassOf()).getOccurenceDep());
    }

    @objid ("cb00ace5-7211-4a20-a2e0-2ac4430b0927")
    @Override
    public <T extends Link> List<T> getOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Link element : getOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("00fcc2a7-b596-4833-9113-516ec1535ef4")
    @Override
    public EList<AssociationEnd> getEnd() {
        return new SmList<>(this, ((AssociationSmClass)getClassOf()).getEndDep());
    }

    @objid ("d5fe8de3-db28-4d5b-bd99-93015cd46e28")
    @Override
    public <T extends AssociationEnd> List<T> getEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AssociationEnd element : getEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("04abe68d-a30e-4c07-bd05-e14fcf88021b")
    @Override
    public ClassAssociation getLinkToClass() {
        Object obj = getDepVal(((AssociationSmClass)getClassOf()).getLinkToClassDep());
        return (obj instanceof ClassAssociation)? (ClassAssociation)obj : null;
    }

    @objid ("ae0957d2-0464-48da-8662-18429ed8143d")
    @Override
    public void setLinkToClass(ClassAssociation value) {
        appendDepVal(((AssociationSmClass)getClassOf()).getLinkToClassDep(), (SmObjectImpl)value);
    }

    @objid ("7ad9f767-e54c-4b36-80b5-48d844bf71a8")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitAssociation(this);
    }

}
