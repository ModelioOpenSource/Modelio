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
import org.modelio.metamodel.impl.uml.statik.AssociationEndData;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00977eda-c4be-1fd8-97fe-001ec947cd2a")
public class AssociationEndImpl extends StructuralFeatureImpl implements AssociationEnd {
    /**
     * Compute the association navigability from all roles construction.
     * 
     * @return the association navigability.
     */
    @objid ("779babaf-b95e-442f-9c0d-af9c01f610c7")
    @Override
    public boolean isNavigable() {
        return getTarget() != null;
    }

    @objid ("00174af8-22ac-1080-943a-001ec947cd2a")
    @Override
    public void setNavigable(final boolean isNavigable) {
        AssociationEnd opposite = getOpposite();
        boolean otherNavigable = opposite.isNavigable();
        
        // The source classifier is the current source or the opposite end's target according to the old navigability
        Classifier source = getSource() != null ? getSource() : opposite.getTarget();
        
        // The target classifier is the opposite end's source or the current target according to the old navigability
        Classifier target = opposite.getSource() != null ? opposite.getSource() : getTarget();
        
        if (isNavigable && !otherNavigable) { // THIS SIDE
            this.setSource(source);
            this.setTarget(target);
        
            opposite.setSource(null);
            opposite.setTarget(null);
        } else if (!isNavigable && otherNavigable) { // OTHER SIDE
            this.setSource(null);
            this.setTarget(null);
        
            opposite.setSource(target);
            opposite.setTarget(source);
        } else if (isNavigable && otherNavigable) { // BOTH SIDES
            this.setSource(source);
            this.setTarget(target);
        
            opposite.setSource(target);
            opposite.setTarget(source);
        } else if (!isNavigable && !otherNavigable) { // NONE
            this.setSource(source);
            this.setTarget(null);
        
            opposite.setSource(target);
            opposite.setTarget(null);
        
        }
    }

    @objid ("0019e09c-22ac-1080-943a-001ec947cd2a")
    @Override
    public void setSource(final Classifier value, boolean fixModel) {
        if (fixModel) {
            boolean isNavigable = isNavigable();
        
            AssociationEnd opposite = getOpposite();
            boolean otherNavigable = opposite.isNavigable();
        
            Classifier source = value;
        
            // The target classifier is the opposite end's source or the current target according to the old navigability
            Classifier target = opposite.getSource() != null ? opposite.getSource() : getTarget();
        
            if (isNavigable && !otherNavigable) { // THIS SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(null);
                opposite.setTarget(null);
            } else if (!isNavigable && otherNavigable) { // OTHER SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (isNavigable && otherNavigable) { // BOTH SIDES
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (!isNavigable && !otherNavigable) { // NONE
                this.setSource(source);
                this.setTarget(null);
        
                opposite.setSource(target);
                opposite.setTarget(null);
            }
        } else {
            setSource(value);
        }
    }

    @objid ("001b2470-22ac-1080-943a-001ec947cd2a")
    @Override
    public void setTarget(final Classifier value, boolean fixModel) {
        if (fixModel) {
            boolean isNavigable = isNavigable();
        
            AssociationEnd opposite = getOpposite();
            boolean otherNavigable = opposite.isNavigable();
        
            // The source classifier is the current source or the opposite end's target according to the old navigability
            Classifier source = getSource() != null ? getSource() : opposite.getTarget();
        
            Classifier target = value;
        
            if (isNavigable && !otherNavigable) { // THIS SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(null);
                opposite.setTarget(null);
            } else if (!isNavigable && otherNavigable) { // OTHER SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (isNavigable && otherNavigable) { // BOTH SIDES
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (!isNavigable && !otherNavigable) { // NONE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(null);
                opposite.setTarget(null);
            }
        } else {
            setTarget(value);
        }
    }

    @objid ("007041ee-1a19-10a1-88a0-001ec947cd2a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        SmObjectImpl ret = (SmObjectImpl) getSource();
        
        if (ret != null) {
            return ret;
        } else {
            // Return the 'opposite' role only if it has a source:
            // Avoid cycle if both ends have no source.
            // Do not use 'Opposite' role, it is the wrong way and using it has horrible consequences
            // in repository handle initializations.
            AssociationEnd opp = getOppositeOwner();
            if (opp != null && opp.getSource() != null) {
                return (SmObjectImpl) opp;
            } else {
                return null;
            }
        }
    }

    @objid ("6f0ba97d-5b41-11e2-90ce-002564c97630")
    @Override
    public List<SmObjectImpl> getCompositionChildren() {
        final List<SmObjectImpl> compositionChildren = super.getCompositionChildren();
        
        
        // Avoid composition loops...
        // Do not use 'OppositeOwner' role, it is the wrong way and using it may have 
        // horrible consequences in repository handle initializations.
        AssociationEnd opp = getOpposite();
        if (opp != null && opp.getSource() != null) {
            // The other side has a 'strong' owner, do not return it as a child
            compositionChildren.remove(opp);
        }
        return compositionChildren;
    }

    @objid ("0070616a-1a19-10a1-88a0-001ec947cd2a")
    @Override
    public SmDepVal getCompositionRelation() {
        // First look for 'source'
        SmDependency dep = ((AssociationEndSmClass) getClassOf()).getSourceDep();
        SmObjectImpl obj = (SmObjectImpl) getDepVal(dep);
        if (obj != null) {
            return new SmDepVal(dep, obj);
        }
        
        // Return the 'opposite' role only if it has a source:
        // Avoid cycle if both ends have no source.
        AssociationEnd opp = getOpposite();
        
        if (opp != null && opp.getSource() != null) {
            return new SmDepVal(((AssociationEndSmClass) getClassOf()).getOppositeOwnerDep(), opp);
        } else {
            return null;
        }
    }

    @objid ("7d321404-ca63-4d7b-93d6-9eeaed9fe01f")
    @Override
    public Classifier getOwner() {
        Classifier source = getSource();
        if (source != null) {
            return source;
        } else {
            AssociationEnd opposite = getOpposite();
            return opposite != null ? opposite.getTarget() : null;
        }
    }

    @objid ("de5ce14b-fc8c-4ab5-a0b9-2b41683ce4db")
    @Override
    public void afterEraseDepVal(SmDependency dep, SmObjectImpl value) {
        super.afterEraseDepVal(dep, value);
        
        if (dep == ((AssociationEndSmClass) getClassOf()).getSourceDep()) {
            // Workaround bug where the storage handle is not updated
            AssociationEnd opposite = getOpposite();
            if (opposite != null) {
                Classifier src = opposite.getSource();
                if (src != null) {
                    // detach and attach again
                    opposite.setOpposite(null);
                    opposite.setSource(null);
                    opposite.setSource(src);
                    opposite.setOpposite(this);
                }
            }
        
        }
    }

    @objid ("5695fde3-3d4a-426b-9260-1a82a3ff91cd")
    @Override
    public AggregationKind getAggregation() {
        return (AggregationKind) getAttVal(((AssociationEndSmClass)getClassOf()).getAggregationAtt());
    }

    @objid ("fe1157e6-e2ff-406b-b3e5-a4b3d33fec91")
    @Override
    public void setAggregation(AggregationKind value) {
        setAttVal(((AssociationEndSmClass)getClassOf()).getAggregationAtt(), value);
    }

    @objid ("c22008c1-c4ef-4c6f-bfdd-923d322c05c7")
    @Override
    public boolean isIsChangeable() {
        return (Boolean) getAttVal(((AssociationEndSmClass)getClassOf()).getIsChangeableAtt());
    }

    @objid ("5df73f13-932b-4fe4-ac68-d40f71537b8e")
    @Override
    public void setIsChangeable(boolean value) {
        setAttVal(((AssociationEndSmClass)getClassOf()).getIsChangeableAtt(), value);
    }

    @objid ("f08c877b-7800-4bbd-bac7-6295a23ec552")
    @Override
    public Classifier getTarget() {
        Object obj = getDepVal(((AssociationEndSmClass)getClassOf()).getTargetDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("43d2ed9c-ee7f-4c6a-9fd6-2ea90a3b1342")
    @Override
    public void setTarget(Classifier value) {
        appendDepVal(((AssociationEndSmClass)getClassOf()).getTargetDep(), (SmObjectImpl)value);
    }

    @objid ("e83a6859-852c-4714-91aa-7947fe26b2f0")
    @Override
    public AssociationEnd getOppositeOwner() {
        Object obj = getDepVal(((AssociationEndSmClass)getClassOf()).getOppositeOwnerDep());
        return (obj instanceof AssociationEnd)? (AssociationEnd)obj : null;
    }

    @objid ("cb734a59-b49f-4f28-a42a-0cd0ed63c7b4")
    @Override
    public void setOppositeOwner(AssociationEnd value) {
        appendDepVal(((AssociationEndSmClass)getClassOf()).getOppositeOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("3fa607b9-f2f6-4e77-b009-f2fe66cd20f2")
    @Override
    public Classifier getSource() {
        Object obj = getDepVal(((AssociationEndSmClass)getClassOf()).getSourceDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("b7ddbadc-f140-46f0-b96a-d6d5da349d7b")
    @Override
    public void setSource(Classifier value) {
        appendDepVal(((AssociationEndSmClass)getClassOf()).getSourceDep(), (SmObjectImpl)value);
    }

    @objid ("c1414f63-d5c1-407d-8d4e-9330d3ae826a")
    @Override
    public EList<LinkEnd> getOccurence() {
        return new SmList<>(this, ((AssociationEndSmClass)getClassOf()).getOccurenceDep());
    }

    @objid ("814b16c2-289a-4f73-bc06-db49b47b9125")
    @Override
    public <T extends LinkEnd> List<T> getOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final LinkEnd element : getOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("339a1888-3c60-4558-8579-0d81fba26db8")
    @Override
    public EList<InformationFlow> getSent() {
        return new SmList<>(this, ((AssociationEndSmClass)getClassOf()).getSentDep());
    }

    @objid ("c564e324-c87f-4964-bd9a-fab4c7d1f76b")
    @Override
    public <T extends InformationFlow> List<T> getSent(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getSent()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("6be33a45-a359-4549-a0f8-8286f4f975f9")
    @Override
    public EList<Attribute> getQualifier() {
        return new SmList<>(this, ((AssociationEndSmClass)getClassOf()).getQualifierDep());
    }

    @objid ("5532c248-d8a7-4dab-80c4-5ae484c7d400")
    @Override
    public <T extends Attribute> List<T> getQualifier(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Attribute element : getQualifier()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("098d56cf-660b-4d11-971f-28d7d92c9a0f")
    @Override
    public AssociationEnd getOpposite() {
        Object obj = getDepVal(((AssociationEndSmClass)getClassOf()).getOppositeDep());
        return (obj instanceof AssociationEnd)? (AssociationEnd)obj : null;
    }

    @objid ("c63e1b6b-d2b7-48c5-bf00-c893d2b69471")
    @Override
    public void setOpposite(AssociationEnd value) {
        appendDepVal(((AssociationEndSmClass)getClassOf()).getOppositeDep(), (SmObjectImpl)value);
    }

    @objid ("e153368d-0370-4da3-b2bf-b8eef4f37f3e")
    @Override
    public EList<ObjectNode> getRepresentingObjectNode() {
        return new SmList<>(this, ((AssociationEndSmClass)getClassOf()).getRepresentingObjectNodeDep());
    }

    @objid ("256c5f07-b01a-42b4-9516-e54a8305c9ba")
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

    @objid ("c9e63e4e-7f4e-49ec-bf0d-61d8b6ca6d3d")
    @Override
    public Association getAssociation() {
        Object obj = getDepVal(((AssociationEndSmClass)getClassOf()).getAssociationDep());
        return (obj instanceof Association)? (Association)obj : null;
    }

    @objid ("33c3a4d4-4d0b-4b88-8961-25c5cb134129")
    @Override
    public void setAssociation(Association value) {
        appendDepVal(((AssociationEndSmClass)getClassOf()).getAssociationDep(), (SmObjectImpl)value);
    }

    @objid ("4e2d1bd7-6b74-49d2-9a9c-1c7c18d394ff")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitAssociationEnd(this);
    }

}
