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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.LifelineData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0048dfe6-c4bf-1fd8-97fe-001ec947cd2a")
public class LifelineImpl extends UmlModelElementImpl implements Lifeline {
    @objid ("c7d368b6-7f29-488d-9841-0c20e1e76730")
    @Override
    public String getSelector() {
        return (String) getAttVal(((LifelineSmClass)getClassOf()).getSelectorAtt());
    }

    @objid ("8043a63a-457c-40dc-a708-0b957f38a874")
    @Override
    public void setSelector(String value) {
        setAttVal(((LifelineSmClass)getClassOf()).getSelectorAtt(), value);
    }

    @objid ("b6a0daf0-980d-41ac-8941-bad8a12642af")
    @Override
    public EList<InteractionFragment> getCoveredBy() {
        return new SmList<>(this, ((LifelineSmClass)getClassOf()).getCoveredByDep());
    }

    @objid ("20537905-2dae-4189-842e-a859ff2b8c7c")
    @Override
    public <T extends InteractionFragment> List<T> getCoveredBy(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InteractionFragment element : getCoveredBy()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("63a6ae8e-719f-412a-a8af-54a9944d2e48")
    @Override
    public PartDecomposition getDecomposedAs() {
        Object obj = getDepVal(((LifelineSmClass)getClassOf()).getDecomposedAsDep());
        return (obj instanceof PartDecomposition)? (PartDecomposition)obj : null;
    }

    @objid ("b54b64c0-1719-41d0-a07d-aa2598e60be0")
    @Override
    public void setDecomposedAs(PartDecomposition value) {
        appendDepVal(((LifelineSmClass)getClassOf()).getDecomposedAsDep(), (SmObjectImpl)value);
    }

    @objid ("f2de4777-ada6-4e63-91ff-dc3e8362b243")
    @Override
    public Interaction getOwner() {
        Object obj = getDepVal(((LifelineSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Interaction)? (Interaction)obj : null;
    }

    @objid ("9a3a2811-8b57-4626-9b6f-267dc80e6c35")
    @Override
    public void setOwner(Interaction value) {
        appendDepVal(((LifelineSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("6c70a573-a901-494c-ba12-e6503fa8e01e")
    @Override
    public Instance getRepresented() {
        Object obj = getDepVal(((LifelineSmClass)getClassOf()).getRepresentedDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("7576ae33-4549-40b3-98b9-69dfb3b57b65")
    @Override
    public void setRepresented(Instance value) {
        appendDepVal(((LifelineSmClass)getClassOf()).getRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("86782b56-e44e-4800-9c3c-0b4ff69f1c7a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((LifelineSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("4e23a510-4d46-447c-97f0-51ac0294c9dd")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((LifelineSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("21d622a9-e705-4771-8b0b-3715fc2074f7")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitLifeline(this);
    }

}
