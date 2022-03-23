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
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000bf81a-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class GeneralClassImpl extends ClassifierImpl implements GeneralClass {
    @objid ("b1b3e596-5072-4fff-8523-2e5761c78694")
    @Override
    public boolean isIsElementary() {
        return (Boolean) getAttVal(((GeneralClassSmClass)getClassOf()).getIsElementaryAtt());
    }

    @objid ("fa3d0a21-64d2-4e38-84cb-a4f01477804a")
    @Override
    public void setIsElementary(boolean value) {
        setAttVal(((GeneralClassSmClass)getClassOf()).getIsElementaryAtt(), value);
    }

    @objid ("3bd787a9-a69a-48a8-897a-73b42b0db3df")
    @Override
    public EList<Parameter> getOccurence() {
        return new SmList<>(this, ((GeneralClassSmClass)getClassOf()).getOccurenceDep());
    }

    @objid ("c00788d3-bf48-4b33-8597-0d24f53460f2")
    @Override
    public <T extends Parameter> List<T> getOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Parameter element : getOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("46714c72-c8d5-4efb-9116-e2cf9e0bb45e")
    @Override
    public ExceptionHandler getExceptionInput() {
        Object obj = getDepVal(((GeneralClassSmClass)getClassOf()).getExceptionInputDep());
        return (obj instanceof ExceptionHandler)? (ExceptionHandler)obj : null;
    }

    @objid ("532f0b68-5f87-408b-b925-9d4d212f4c0d")
    @Override
    public void setExceptionInput(ExceptionHandler value) {
        appendDepVal(((GeneralClassSmClass)getClassOf()).getExceptionInputDep(), (SmObjectImpl)value);
    }

    @objid ("3d86d4e0-1eb1-404b-84c1-015438797cd5")
    @Override
    public EList<Attribute> getObject() {
        return new SmList<>(this, ((GeneralClassSmClass)getClassOf()).getObjectDep());
    }

    @objid ("4ec3195b-1081-4232-bf52-9f757e758e6f")
    @Override
    public <T extends Attribute> List<T> getObject(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Attribute element : getObject()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("523e571f-466b-43e8-892e-8dc3a95f77a2")
    @Override
    public EList<Signal> getSRepresentation() {
        return new SmList<>(this, ((GeneralClassSmClass)getClassOf()).getSRepresentationDep());
    }

    @objid ("59c5e359-f774-4983-9afd-2da8527a130d")
    @Override
    public <T extends Signal> List<T> getSRepresentation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Signal element : getSRepresentation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("0ec03e2b-1304-4d43-aecd-488c7b10bf99")
    @Override
    public EList<ObjectNode> getOccurenceObjectNode() {
        return new SmList<>(this, ((GeneralClassSmClass)getClassOf()).getOccurenceObjectNodeDep());
    }

    @objid ("9c6de69c-b4bb-4749-b96c-f5db9e2e21b3")
    @Override
    public <T extends ObjectNode> List<T> getOccurenceObjectNode(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ObjectNode element : getOccurenceObjectNode()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("cc770e6b-80df-4c7d-93d2-c05b4ab44350")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("fd557632-2f41-4d1d-9554-4c7d2c4a49c2")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8d325840-af83-49a6-921a-d21bb6c0b152")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitGeneralClass(this);
    }

}
