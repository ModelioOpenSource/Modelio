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
import org.modelio.metamodel.impl.uml.statik.StructuralFeatureData;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("001f3344-c4bf-1fd8-97fe-001ec947cd2a")
public class StructuralFeatureImpl extends FeatureImpl implements StructuralFeature {
    @objid ("b968b62d-d6f6-482b-9039-17ad3f539c5c")
    @Override
    public KindOfAccess getChangeable() {
        return (KindOfAccess) getAttVal(((StructuralFeatureSmClass)getClassOf()).getChangeableAtt());
    }

    @objid ("08604b39-0d12-4a10-af80-dac1547e92c9")
    @Override
    public void setChangeable(KindOfAccess value) {
        setAttVal(((StructuralFeatureSmClass)getClassOf()).getChangeableAtt(), value);
    }

    @objid ("7770316e-2723-41c1-9aae-00a0751a89d7")
    @Override
    public boolean isIsDerived() {
        return (Boolean) getAttVal(((StructuralFeatureSmClass)getClassOf()).getIsDerivedAtt());
    }

    @objid ("27382dc1-1bbc-4629-af81-8ee0fa5964ec")
    @Override
    public void setIsDerived(boolean value) {
        setAttVal(((StructuralFeatureSmClass)getClassOf()).getIsDerivedAtt(), value);
    }

    @objid ("dfa0938f-4764-462f-b8d1-900677efb1e1")
    @Override
    public boolean isIsOrdered() {
        return (Boolean) getAttVal(((StructuralFeatureSmClass)getClassOf()).getIsOrderedAtt());
    }

    @objid ("68dae501-20cd-442c-a6f1-09ee7dacfbff")
    @Override
    public void setIsOrdered(boolean value) {
        setAttVal(((StructuralFeatureSmClass)getClassOf()).getIsOrderedAtt(), value);
    }

    @objid ("b6a39391-19b0-4381-946a-7a27757c5be7")
    @Override
    public boolean isIsUnique() {
        return (Boolean) getAttVal(((StructuralFeatureSmClass)getClassOf()).getIsUniqueAtt());
    }

    @objid ("a97c61bb-a87a-4e7b-a4fb-159c1ef229dd")
    @Override
    public void setIsUnique(boolean value) {
        setAttVal(((StructuralFeatureSmClass)getClassOf()).getIsUniqueAtt(), value);
    }

    @objid ("1bc4a6e5-1323-4562-8982-d16e6a4b2ef3")
    @Override
    public String getMultiplicityMin() {
        return (String) getAttVal(((StructuralFeatureSmClass)getClassOf()).getMultiplicityMinAtt());
    }

    @objid ("67fbcfe1-7051-4fcf-b778-f9aa81bec7dd")
    @Override
    public void setMultiplicityMin(String value) {
        setAttVal(((StructuralFeatureSmClass)getClassOf()).getMultiplicityMinAtt(), value);
    }

    @objid ("f103e7cb-a7aa-4523-a93f-5b57b505b7d4")
    @Override
    public String getMultiplicityMax() {
        return (String) getAttVal(((StructuralFeatureSmClass)getClassOf()).getMultiplicityMaxAtt());
    }

    @objid ("249b0d21-46fa-4926-ae36-973b19167ac2")
    @Override
    public void setMultiplicityMax(String value) {
        setAttVal(((StructuralFeatureSmClass)getClassOf()).getMultiplicityMaxAtt(), value);
    }

    @objid ("5c59b48f-49fe-46fd-9de9-8e67fd90eede")
    @Override
    public EList<InformationFlow> getRealizedInformationFlow() {
        return new SmList<>(this, ((StructuralFeatureSmClass)getClassOf()).getRealizedInformationFlowDep());
    }

    @objid ("ec1da20e-939f-4f9b-af6d-c9fb7e51b77f")
    @Override
    public <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getRealizedInformationFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("95ef1c92-b109-4f9c-b1d1-7078210f7019")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("0062e984-d026-4ad2-9bf4-00f00575c652")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("1dcab5d6-88e6-492f-9722-f3f9b9c494e7")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitStructuralFeature(this);
    }

}
