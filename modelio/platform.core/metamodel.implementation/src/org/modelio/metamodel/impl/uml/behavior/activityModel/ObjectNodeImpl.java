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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeData;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00397a06-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ObjectNodeImpl extends ActivityNodeImpl implements ObjectNode {
    @objid ("9d30c961-2984-43e0-8203-25d530bb0c84")
    @Override
    public boolean isIsControlType() {
        return (Boolean) getAttVal(((ObjectNodeSmClass)getClassOf()).getIsControlTypeAtt());
    }

    @objid ("acf88bdc-3412-46da-a06e-db4b919e8e27")
    @Override
    public void setIsControlType(boolean value) {
        setAttVal(((ObjectNodeSmClass)getClassOf()).getIsControlTypeAtt(), value);
    }

    @objid ("f89b3df5-47c1-4fd7-b124-5b4d303229d4")
    @Override
    public ObjectNodeOrderingKind getOrdering() {
        return (ObjectNodeOrderingKind) getAttVal(((ObjectNodeSmClass)getClassOf()).getOrderingAtt());
    }

    @objid ("7a8af728-c717-444a-a893-fdcdab3db39e")
    @Override
    public void setOrdering(ObjectNodeOrderingKind value) {
        setAttVal(((ObjectNodeSmClass)getClassOf()).getOrderingAtt(), value);
    }

    @objid ("25717c59-3c2d-4c16-b7ed-322bbde37af4")
    @Override
    public String getSelectionBehavior() {
        return (String) getAttVal(((ObjectNodeSmClass)getClassOf()).getSelectionBehaviorAtt());
    }

    @objid ("229ee4fb-6faa-4a52-8439-d54bea51f28f")
    @Override
    public void setSelectionBehavior(String value) {
        setAttVal(((ObjectNodeSmClass)getClassOf()).getSelectionBehaviorAtt(), value);
    }

    @objid ("c330fa87-6649-4b9c-a148-dedf640ef673")
    @Override
    public String getUpperBound() {
        return (String) getAttVal(((ObjectNodeSmClass)getClassOf()).getUpperBoundAtt());
    }

    @objid ("99d7ad13-9116-468f-adc7-fcbd297dac45")
    @Override
    public void setUpperBound(String value) {
        setAttVal(((ObjectNodeSmClass)getClassOf()).getUpperBoundAtt(), value);
    }

    @objid ("c9a0a4a5-5569-486c-9a75-aaee99ab456e")
    @Override
    public Instance getRepresented() {
        Object obj = getDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("17d7c67f-98ca-434b-80f2-9b76e072005a")
    @Override
    public void setRepresented(Instance value) {
        appendDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("69e0bfbc-f64b-47cb-ac6d-a37848767b24")
    @Override
    public BehaviorParameter getRepresentedRealParameter() {
        Object obj = getDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedRealParameterDep());
        return (obj instanceof BehaviorParameter)? (BehaviorParameter)obj : null;
    }

    @objid ("8df2833d-7456-45f7-97a9-9ab539e96f37")
    @Override
    public void setRepresentedRealParameter(BehaviorParameter value) {
        appendDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedRealParameterDep(), (SmObjectImpl)value);
    }

    @objid ("1bc8a73a-70f3-4b78-a32d-3ac0bc3a2e26")
    @Override
    public GeneralClass getType() {
        Object obj = getDepVal(((ObjectNodeSmClass)getClassOf()).getTypeDep());
        return (obj instanceof GeneralClass)? (GeneralClass)obj : null;
    }

    @objid ("bca39e1b-313f-4ce0-870c-c8d334bdbd2f")
    @Override
    public void setType(GeneralClass value) {
        appendDepVal(((ObjectNodeSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("41c394e3-3e1a-44a0-a700-0f488d1a8111")
    @Override
    public AssociationEnd getRepresentedRole() {
        Object obj = getDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedRoleDep());
        return (obj instanceof AssociationEnd)? (AssociationEnd)obj : null;
    }

    @objid ("1bd025cb-7bd7-4452-8997-0a971f7c2e85")
    @Override
    public void setRepresentedRole(AssociationEnd value) {
        appendDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedRoleDep(), (SmObjectImpl)value);
    }

    @objid ("06bc4a7b-2833-4190-8b04-8a101ba51e7a")
    @Override
    public Attribute getRepresentedAttribute() {
        Object obj = getDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedAttributeDep());
        return (obj instanceof Attribute)? (Attribute)obj : null;
    }

    @objid ("d3474d52-49c8-4d80-a01a-8f725b2a2841")
    @Override
    public void setRepresentedAttribute(Attribute value) {
        appendDepVal(((ObjectNodeSmClass)getClassOf()).getRepresentedAttributeDep(), (SmObjectImpl)value);
    }

    @objid ("1ee79d54-8b75-4ad5-947d-9725132bef9d")
    @Override
    public State getInState() {
        Object obj = getDepVal(((ObjectNodeSmClass)getClassOf()).getInStateDep());
        return (obj instanceof State)? (State)obj : null;
    }

    @objid ("a17fd958-cd9d-4890-9942-dd083f1f1e85")
    @Override
    public void setInState(State value) {
        appendDepVal(((ObjectNodeSmClass)getClassOf()).getInStateDep(), (SmObjectImpl)value);
    }

    @objid ("b634fd95-ee8c-4347-99ef-41bdcdce0d39")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("9b0fd1e6-04a0-4b4b-9079-4a2696c4ffa8")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("a5127237-70c2-4f71-ab17-79f010201d57")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitObjectNode(this);
    }

}
