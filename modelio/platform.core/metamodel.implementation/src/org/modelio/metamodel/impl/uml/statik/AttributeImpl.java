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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.statik.AttributeData;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00981c78-c4be-1fd8-97fe-001ec947cd2a")
public class AttributeImpl extends StructuralFeatureImpl implements Attribute {
    @objid ("a936e847-e8e5-48dd-a58a-ddfe7c94c813")
    @Override
    public String getTypeConstraint() {
        return (String) getAttVal(((AttributeSmClass)getClassOf()).getTypeConstraintAtt());
    }

    @objid ("261986ff-1824-4eec-825c-1d8bbca9990d")
    @Override
    public void setTypeConstraint(String value) {
        setAttVal(((AttributeSmClass)getClassOf()).getTypeConstraintAtt(), value);
    }

    @objid ("1bd1b529-c8f2-4be7-9b9b-a85ab71f34aa")
    @Override
    public String getValue() {
        return (String) getAttVal(((AttributeSmClass)getClassOf()).getValueAtt());
    }

    @objid ("8cc88999-f220-48af-b7dc-72ca5204eb81")
    @Override
    public void setValue(String value) {
        setAttVal(((AttributeSmClass)getClassOf()).getValueAtt(), value);
    }

    @objid ("dd95e2ee-f666-47f0-86b8-5875d75458fb")
    @Override
    public boolean isTargetIsClass() {
        return (Boolean) getAttVal(((AttributeSmClass)getClassOf()).getTargetIsClassAtt());
    }

    @objid ("bfd6200d-20b9-4c6d-8322-6358b88c81cd")
    @Override
    public void setTargetIsClass(boolean value) {
        setAttVal(((AttributeSmClass)getClassOf()).getTargetIsClassAtt(), value);
    }

    @objid ("51c91037-8c19-400d-a893-e126bf0d152b")
    @Override
    public GeneralClass getType() {
        Object obj = getDepVal(((AttributeSmClass)getClassOf()).getTypeDep());
        return (obj instanceof GeneralClass)? (GeneralClass)obj : null;
    }

    @objid ("003ea147-7f99-4d0b-b073-9c8d98b676fe")
    @Override
    public void setType(GeneralClass value) {
        appendDepVal(((AttributeSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("901e178a-e72d-4083-accc-9e0295e996c6")
    @Override
    public Classifier getOwner() {
        Object obj = getDepVal(((AttributeSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("97c52e85-4d3c-4662-b30a-cf100beb5d6a")
    @Override
    public void setOwner(Classifier value) {
        appendDepVal(((AttributeSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("54f475ab-a19e-4793-86a4-721283afe7f4")
    @Override
    public EList<AttributeLink> getOccurence() {
        return new SmList<>(this, ((AttributeSmClass)getClassOf()).getOccurenceDep());
    }

    @objid ("29c91af4-c996-4142-b1c9-93ab6b375e02")
    @Override
    public <T extends AttributeLink> List<T> getOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AttributeLink element : getOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("468331ec-9bd6-4b58-91bb-d251b4e1c39f")
    @Override
    public EList<ObjectNode> getRepresentingObjectNode() {
        return new SmList<>(this, ((AttributeSmClass)getClassOf()).getRepresentingObjectNodeDep());
    }

    @objid ("1845722f-800d-4d51-ba8f-6f74065ece15")
    @Override
    public <T extends ObjectNode> List<T> getRepresentingObjectNode(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ObjectNode element : getRepresentingObjectNode()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("da04b36e-4fac-4117-9648-cdafa65f9473")
    @Override
    public AssociationEnd getQualified() {
        Object obj = getDepVal(((AttributeSmClass)getClassOf()).getQualifiedDep());
        return (obj instanceof AssociationEnd)? (AssociationEnd)obj : null;
    }

    @objid ("3133190a-8828-4d40-a675-26747647469b")
    @Override
    public void setQualified(AssociationEnd value) {
        appendDepVal(((AttributeSmClass)getClassOf()).getQualifiedDep(), (SmObjectImpl)value);
    }

    @objid ("a98438b5-aeaa-4f6d-a688-9c75dcb3f222")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((AttributeSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        // Qualified
        obj = (SmObjectImpl)this.getDepVal(((AttributeSmClass)getClassOf()).getQualifiedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("6ebcf563-b31b-4606-a02d-2bcb1ae55e8b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((AttributeSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Qualified
        dep = ((AttributeSmClass)getClassOf()).getQualifiedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("a734fdee-4485-4fb3-a95c-6785cb69c620")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitAttribute(this);
    }

}
