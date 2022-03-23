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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MVisitor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("c740c401-3304-4a68-b664-2705cd525890")
public class UmlModelElementImpl extends ModelElementImpl implements UmlModelElement {
    @objid ("6b1434f4-6aa2-47ee-8256-11c89eadb14d")
    @Override
    public EList<TemplateParameterSubstitution> getTemplateSubstitution() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getTemplateSubstitutionDep());
    }

    @objid ("43299bd7-529b-402b-985b-8310df0af0ea")
    @Override
    public <T extends TemplateParameterSubstitution> List<T> getTemplateSubstitution(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateParameterSubstitution element : getTemplateSubstitution()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("4ecb219e-780c-433d-a278-bab49545faad")
    @Override
    public EList<TemplateParameter> getDefaultParametering() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getDefaultParameteringDep());
    }

    @objid ("e8863314-8850-4af4-8b29-46236e547473")
    @Override
    public <T extends TemplateParameter> List<T> getDefaultParametering(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateParameter element : getDefaultParametering()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("ea561c6d-adc9-4725-9c62-c549da09af09")
    @Override
    public EList<Binding> getRepresents() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getRepresentsDep());
    }

    @objid ("90856b9f-41e6-43ec-9880-b79ec7c7b4c8")
    @Override
    public <T extends Binding> List<T> getRepresents(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Binding element : getRepresents()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("a2c2a9d0-db2b-49fd-a946-67cc6ee0e225")
    @Override
    public TemplateParameter getOwnerTemplateParameter() {
        Object obj = getDepVal(((UmlModelElementSmClass)getClassOf()).getOwnerTemplateParameterDep());
        return (obj instanceof TemplateParameter)? (TemplateParameter)obj : null;
    }

    @objid ("b346c660-c259-4d74-bbc3-3b408832d261")
    @Override
    public void setOwnerTemplateParameter(TemplateParameter value) {
        appendDepVal(((UmlModelElementSmClass)getClassOf()).getOwnerTemplateParameterDep(), (SmObjectImpl)value);
    }

    @objid ("7d4476b4-9c11-40fd-906a-c863edbc458a")
    @Override
    public EList<ConnectorEnd> getRepresentingEnd() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getRepresentingEndDep());
    }

    @objid ("6afbadbc-9b42-4909-bc4d-bbff39fb5ba2")
    @Override
    public <T extends ConnectorEnd> List<T> getRepresentingEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ConnectorEnd element : getRepresentingEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("5e27de32-53f8-4890-9f12-fe80df274b79")
    @Override
    public EList<ActivityPartition> getRepresentingPartition() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getRepresentingPartitionDep());
    }

    @objid ("d5571eb4-343f-4c9f-b8dd-c8a7c476a792")
    @Override
    public <T extends ActivityPartition> List<T> getRepresentingPartition(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityPartition element : getRepresentingPartition()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("f395e0ce-e779-4d47-9b88-aac0535dc764")
    @Override
    public EList<Constraint> getConstraintDefinition() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getConstraintDefinitionDep());
    }

    @objid ("065de582-3261-4a26-8d3b-c25fee12a9bb")
    @Override
    public <T extends Constraint> List<T> getConstraintDefinition(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Constraint element : getConstraintDefinition()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("21dd3a1e-da68-4e2c-ab16-e66bbe1a72c0")
    @Override
    public EList<TemplateParameter> getTypingParameter() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getTypingParameterDep());
    }

    @objid ("cf5f678e-340b-4370-b2f6-08ed83994fdf")
    @Override
    public <T extends TemplateParameter> List<T> getTypingParameter(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateParameter element : getTypingParameter()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("363b8f85-d358-489d-9963-1c566a08257d")
    @Override
    public EList<Manifestation> getManifesting() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getManifestingDep());
    }

    @objid ("46e252c3-71fa-4538-92d2-fbf71415d145")
    @Override
    public <T extends Manifestation> List<T> getManifesting(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Manifestation element : getManifesting()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("275846d8-0758-482d-90ad-9f122a19f5b1")
    @Override
    public EList<BindableInstance> getRepresentingInstance() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getRepresentingInstanceDep());
    }

    @objid ("8377c599-8132-4bad-8004-b84476fd406a")
    @Override
    public <T extends BindableInstance> List<T> getRepresentingInstance(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BindableInstance element : getRepresentingInstance()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("dcbb1163-9fc2-4273-ad4d-0ca83a44b04b")
    @Override
    public EList<InformationFlow> getReceivedInfo() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getReceivedInfoDep());
    }

    @objid ("2acfefe7-75ea-4c2d-b3dc-769e8de62d63")
    @Override
    public <T extends InformationFlow> List<T> getReceivedInfo(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getReceivedInfo()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("96a4682e-b615-4503-8548-d6f209d32bf1")
    @Override
    public EList<InformationFlow> getSentInfo() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getSentInfoDep());
    }

    @objid ("6cd1aea5-ad65-4fc9-87e4-48722e0fe22f")
    @Override
    public <T extends InformationFlow> List<T> getSentInfo(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getSentInfo()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("03a6c570-2fcf-46ff-aaaf-5b494f465bfa")
    @Override
    public EList<NaryConnector> getRepresentingConnector() {
        return new SmList<>(this, ((UmlModelElementSmClass)getClassOf()).getRepresentingConnectorDep());
    }

    @objid ("96a4a0bf-dd27-409b-a871-4579b1a9e848")
    @Override
    public <T extends NaryConnector> List<T> getRepresentingConnector(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final NaryConnector element : getRepresentingConnector()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("170a7d36-33e9-4f61-9c59-9d8c47c4238e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerTemplateParameter
        obj = (SmObjectImpl)this.getDepVal(((UmlModelElementSmClass)getClassOf()).getOwnerTemplateParameterDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("73f2ea0d-c70f-4710-9713-7c17435a4522")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerTemplateParameter
        dep = ((UmlModelElementSmClass)getClassOf()).getOwnerTemplateParameterDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("bba6a3f9-eb2d-48a3-a653-ddaf6966bcf8")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IModelVisitor)
          return accept((IModelVisitor)v);
        else
          return super.accept(v);
    }

    @objid ("a65d48a9-637a-4303-8bf6-42c62b1fdef3")
    public Object accept(IModelVisitor v) {
        return v.visitUmlModelElement(this);
    }

}
