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
import org.modelio.metamodel.impl.uml.statik.ClassifierData;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00039a6c-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ClassifierImpl extends NameSpaceImpl implements Classifier {
    @objid ("80669e77-2580-4550-b6df-996ab890898a")
    @Override
    public EList<Operation> getOwnedOperation() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getOwnedOperationDep());
    }

    @objid ("984c08a0-06d0-41aa-9095-fb9313f43343")
    @Override
    public <T extends Operation> List<T> getOwnedOperation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Operation element : getOwnedOperation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("e9bc5bd7-d2a7-41ae-8ea7-b671805ea489")
    @Override
    public EList<InformationItem> getRepresentation() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getRepresentationDep());
    }

    @objid ("c646fda7-e0be-427e-a02e-1a31b5e1a327")
    @Override
    public <T extends InformationItem> List<T> getRepresentation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationItem element : getRepresentation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("7281463c-1784-4bcf-a590-c93e381de08c")
    @Override
    public EList<Substitution> getSubstitued() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getSubstituedDep());
    }

    @objid ("a89a5608-74bb-4186-a8cc-5a372ea779f9")
    @Override
    public <T extends Substitution> List<T> getSubstitued(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Substitution element : getSubstitued()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("e559edb4-d6a2-400c-bce3-118258e3048d")
    @Override
    public EList<Attribute> getOwnedAttribute() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getOwnedAttributeDep());
    }

    @objid ("3ce967fa-500c-467b-810c-26ef86be8e50")
    @Override
    public <T extends Attribute> List<T> getOwnedAttribute(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Attribute element : getOwnedAttribute()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("2047b117-a012-4a15-9fb3-e142616cc170")
    @Override
    public EList<NaryAssociationEnd> getOwnedNaryEnd() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getOwnedNaryEndDep());
    }

    @objid ("49cbc0d5-8fd8-4920-bb2d-9d797fcc7754")
    @Override
    public <T extends NaryAssociationEnd> List<T> getOwnedNaryEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final NaryAssociationEnd element : getOwnedNaryEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8f7d9ef1-1e3a-4ca0-8687-8eca5ce6b683")
    @Override
    public EList<InformationFlow> getConveyer() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getConveyerDep());
    }

    @objid ("396c1caf-e831-487d-b62f-dd71ceea2c01")
    @Override
    public <T extends InformationFlow> List<T> getConveyer(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getConveyer()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("952bb6fa-703e-4a7c-afbb-be881494e3f9")
    @Override
    public EList<Substitution> getSubstitutingSubstitution() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getSubstitutingSubstitutionDep());
    }

    @objid ("0dcb4810-734d-44f7-b5eb-7e25806bc215")
    @Override
    public <T extends Substitution> List<T> getSubstitutingSubstitution(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Substitution element : getSubstitutingSubstitution()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d1cb60bc-5bd3-418c-a47e-76bded53986a")
    @Override
    public EList<AssociationEnd> getTargetingEnd() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getTargetingEndDep());
    }

    @objid ("c1433062-cbb2-4361-a9dd-9a7756c52203")
    @Override
    public <T extends AssociationEnd> List<T> getTargetingEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AssociationEnd element : getTargetingEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("0f29c135-32e5-4019-b578-296c7b58910f")
    @Override
    public EList<AssociationEnd> getOwnedEnd() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getOwnedEndDep());
    }

    @objid ("555b5699-d5a3-43c2-b160-3ef221c16cba")
    @Override
    public <T extends AssociationEnd> List<T> getOwnedEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AssociationEnd element : getOwnedEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("6c460c1e-9694-4b93-9adc-2cf1a3409720")
    @Override
    public EList<RaisedException> getThrowing() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getThrowingDep());
    }

    @objid ("801bb4e6-a2cd-4162-b405-888570922b9c")
    @Override
    public <T extends RaisedException> List<T> getThrowing(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final RaisedException element : getThrowing()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("f2d8907e-7d69-4aa4-930e-f542a9fd09d7")
    @Override
    public EList<BindableInstance> getInternalStructure() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getInternalStructureDep());
    }

    @objid ("91f3b141-1a36-42af-bae6-c7878ab7d751")
    @Override
    public <T extends BindableInstance> List<T> getInternalStructure(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BindableInstance element : getInternalStructure()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("b11a1d87-dbf0-44e6-aa67-e15f90370c51")
    @Override
    public EList<ComponentRealization> getRealizedComponent() {
        return new SmList<>(this, ((ClassifierSmClass)getClassOf()).getRealizedComponentDep());
    }

    @objid ("665aea07-4a74-45c7-9a2c-6cef2a99400d")
    @Override
    public <T extends ComponentRealization> List<T> getRealizedComponent(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ComponentRealization element : getRealizedComponent()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("843a7941-c1c3-4aca-952b-e32c925ac402")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("98ceb074-6ee5-45c5-b68d-2d041e466dd2")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("e973f512-8f62-418b-8d2e-d081d59d27e9")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitClassifier(this);
    }

}
