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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.ParameterData;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.ParameterEffectKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00180e5c-c4bf-1fd8-97fe-001ec947cd2a")
public class ParameterImpl extends UmlModelElementImpl implements Parameter {
    @objid ("bb4e1304-814a-435f-81be-422e6315c262")
    @Override
    public PassingMode getParameterPassing() {
        return (PassingMode) getAttVal(((ParameterSmClass)getClassOf()).getParameterPassingAtt());
    }

    @objid ("a22edc4f-1700-4064-b949-f7a1392a103d")
    @Override
    public void setParameterPassing(PassingMode value) {
        setAttVal(((ParameterSmClass)getClassOf()).getParameterPassingAtt(), value);
    }

    @objid ("8ca930f5-1d1d-4e65-b9bc-6cde9b05228c")
    @Override
    public String getMultiplicityMin() {
        return (String) getAttVal(((ParameterSmClass)getClassOf()).getMultiplicityMinAtt());
    }

    @objid ("1d7e8f84-65d1-4f00-9fda-9cb7103d6920")
    @Override
    public void setMultiplicityMin(String value) {
        setAttVal(((ParameterSmClass)getClassOf()).getMultiplicityMinAtt(), value);
    }

    @objid ("c42ea37e-1f40-4000-8461-1c980573bbce")
    @Override
    public String getMultiplicityMax() {
        return (String) getAttVal(((ParameterSmClass)getClassOf()).getMultiplicityMaxAtt());
    }

    @objid ("c6058ae3-42e2-4951-9bb6-bcac99248a3f")
    @Override
    public void setMultiplicityMax(String value) {
        setAttVal(((ParameterSmClass)getClassOf()).getMultiplicityMaxAtt(), value);
    }

    @objid ("02fa4819-da00-42c1-902a-64305cd89026")
    @Override
    public String getTypeConstraint() {
        return (String) getAttVal(((ParameterSmClass)getClassOf()).getTypeConstraintAtt());
    }

    @objid ("cade1090-f5f9-4c09-8adf-3ddddabad002")
    @Override
    public void setTypeConstraint(String value) {
        setAttVal(((ParameterSmClass)getClassOf()).getTypeConstraintAtt(), value);
    }

    @objid ("9ec88a1c-52b6-451c-93fa-7c98cde4069c")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((ParameterSmClass)getClassOf()).getDefaultValueAtt());
    }

    @objid ("97fa870b-504d-400a-958a-4baed24eaedf")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((ParameterSmClass)getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("a16feeee-d6fc-4c17-af6f-dca77d33cea2")
    @Override
    public boolean isIsOrdered() {
        return (Boolean) getAttVal(((ParameterSmClass)getClassOf()).getIsOrderedAtt());
    }

    @objid ("526b1970-664d-4d40-a336-7f4688c0743b")
    @Override
    public void setIsOrdered(boolean value) {
        setAttVal(((ParameterSmClass)getClassOf()).getIsOrderedAtt(), value);
    }

    @objid ("991ca495-5e4e-48de-b3e2-0e63c42d9901")
    @Override
    public boolean isIsUnique() {
        return (Boolean) getAttVal(((ParameterSmClass)getClassOf()).getIsUniqueAtt());
    }

    @objid ("08ef3819-7161-4199-b254-9bbf34d80cba")
    @Override
    public void setIsUnique(boolean value) {
        setAttVal(((ParameterSmClass)getClassOf()).getIsUniqueAtt(), value);
    }

    @objid ("61286057-a2de-4037-b710-3702bcf24f44")
    @Override
    public boolean isIsException() {
        return (Boolean) getAttVal(((ParameterSmClass)getClassOf()).getIsExceptionAtt());
    }

    @objid ("03d1aa43-66da-4e53-be1a-a0059ba4f7fc")
    @Override
    public void setIsException(boolean value) {
        setAttVal(((ParameterSmClass)getClassOf()).getIsExceptionAtt(), value);
    }

    @objid ("dbd3e90f-3e9b-482e-87e4-e4c9723f5d23")
    @Override
    public boolean isIsStream() {
        return (Boolean) getAttVal(((ParameterSmClass)getClassOf()).getIsStreamAtt());
    }

    @objid ("67eaa2c2-d394-416f-acb0-8e3d08b44503")
    @Override
    public void setIsStream(boolean value) {
        setAttVal(((ParameterSmClass)getClassOf()).getIsStreamAtt(), value);
    }

    @objid ("85391b7a-5395-4cbd-849b-fb2987356561")
    @Override
    public ParameterEffectKind getEffect() {
        return (ParameterEffectKind) getAttVal(((ParameterSmClass)getClassOf()).getEffectAtt());
    }

    @objid ("42838514-bf04-4e2f-8167-5fddfd182667")
    @Override
    public void setEffect(ParameterEffectKind value) {
        setAttVal(((ParameterSmClass)getClassOf()).getEffectAtt(), value);
    }

    @objid ("4ee9d224-e113-4241-b6b4-5f38d785d387")
    @Override
    public GeneralClass getType() {
        Object obj = getDepVal(((ParameterSmClass)getClassOf()).getTypeDep());
        return (obj instanceof GeneralClass)? (GeneralClass)obj : null;
    }

    @objid ("bdaef112-6288-4e26-83f6-fff09d708f01")
    @Override
    public void setType(GeneralClass value) {
        appendDepVal(((ParameterSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("edd20d0c-a4c6-42a7-ad86-305461124595")
    @Override
    public Operation getComposed() {
        Object obj = getDepVal(((ParameterSmClass)getClassOf()).getComposedDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("225206cb-8383-4b24-abea-b432948a5971")
    @Override
    public void setComposed(Operation value) {
        appendDepVal(((ParameterSmClass)getClassOf()).getComposedDep(), (SmObjectImpl)value);
    }

    @objid ("722d4769-c2cd-46b8-a21c-e782266686cb")
    @Override
    public EList<Pin> getMatching() {
        return new SmList<>(this, ((ParameterSmClass)getClassOf()).getMatchingDep());
    }

    @objid ("123cb26e-d9d9-4e07-843f-651bb85c5125")
    @Override
    public <T extends Pin> List<T> getMatching(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Pin element : getMatching()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("48b39216-a688-4b94-9f16-3e87cc8981be")
    @Override
    public EList<Signal> getSRepresentation() {
        return new SmList<>(this, ((ParameterSmClass)getClassOf()).getSRepresentationDep());
    }

    @objid ("8b293a99-a43c-4608-a386-92a16a6b85b2")
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

    @objid ("f8122bf0-fb29-453b-8b5e-b6e20acd624f")
    @Override
    public Operation getReturned() {
        Object obj = getDepVal(((ParameterSmClass)getClassOf()).getReturnedDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("e5253761-9ccb-4030-a1bd-80ec18b60e25")
    @Override
    public void setReturned(Operation value) {
        appendDepVal(((ParameterSmClass)getClassOf()).getReturnedDep(), (SmObjectImpl)value);
    }

    @objid ("f4b4ee4b-6cb7-4db5-8a63-49b2c0b982b6")
    @Override
    public EList<BehaviorParameter> getBehaviorParam() {
        return new SmList<>(this, ((ParameterSmClass)getClassOf()).getBehaviorParamDep());
    }

    @objid ("6330de41-7c16-4475-a06d-32245b27152f")
    @Override
    public <T extends BehaviorParameter> List<T> getBehaviorParam(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BehaviorParameter element : getBehaviorParam()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("44e3f6dd-a559-4084-9a2c-578a96d76328")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Composed
        obj = (SmObjectImpl)this.getDepVal(((ParameterSmClass)getClassOf()).getComposedDep());
        if (obj != null)
          return obj;
        // Returned
        obj = (SmObjectImpl)this.getDepVal(((ParameterSmClass)getClassOf()).getReturnedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("e55d3610-3e9a-4e91-b016-96504ef85534")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Composed
        dep = ((ParameterSmClass)getClassOf()).getComposedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Returned
        dep = ((ParameterSmClass)getClassOf()).getReturnedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("4059550f-4d9b-41a8-b2ff-7a7fae1b2e4d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitParameter(this);
    }

}
