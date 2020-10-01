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
import org.modelio.metamodel.impl.uml.statik.OperationData;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0014913c-c4bf-1fd8-97fe-001ec947cd2a")
public class OperationImpl extends BehavioralFeatureImpl implements Operation {
    @objid ("71be07ee-3e3e-41c6-abf7-30c3552391ad")
    @Override
    public boolean isConcurrency() {
        return (Boolean) getAttVal(((OperationSmClass)getClassOf()).getConcurrencyAtt());
    }

    @objid ("9b70ba64-2758-4102-9889-e3945edf3d27")
    @Override
    public void setConcurrency(boolean value) {
        setAttVal(((OperationSmClass)getClassOf()).getConcurrencyAtt(), value);
    }

    @objid ("14b9e951-996d-4f1f-bf19-06ab0fcb6351")
    @Override
    public boolean isFinal() {
        return (Boolean) getAttVal(((OperationSmClass)getClassOf()).getFinalAtt());
    }

    @objid ("543dbe6f-27be-4a25-ae56-8dc808fc055a")
    @Override
    public void setFinal(boolean value) {
        setAttVal(((OperationSmClass)getClassOf()).getFinalAtt(), value);
    }

    @objid ("d36dc03c-c7a5-48f5-8ccc-9736ca7a0b5f")
    @Override
    public MethodPassingMode getPassing() {
        return (MethodPassingMode) getAttVal(((OperationSmClass)getClassOf()).getPassingAtt());
    }

    @objid ("892b8540-2401-49bd-a5eb-42248cf879e0")
    @Override
    public void setPassing(MethodPassingMode value) {
        setAttVal(((OperationSmClass)getClassOf()).getPassingAtt(), value);
    }

    @objid ("6d21273a-b99a-4e86-a0f5-20812a4b742d")
    @Override
    public EList<ElementImport> getOwnedImport() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getOwnedImportDep());
    }

    @objid ("ec2ff272-7845-485b-9251-eb47e4f2b1f7")
    @Override
    public <T extends ElementImport> List<T> getOwnedImport(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ElementImport element : getOwnedImport()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("66e3ef24-6d9d-4359-b578-993993006c43")
    @Override
    public EList<RaisedException> getThrown() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getThrownDep());
    }

    @objid ("2c06c636-c740-4d07-a72e-4c18a0956315")
    @Override
    public <T extends RaisedException> List<T> getThrown(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final RaisedException element : getThrown()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("5ae8e68f-fe99-44e8-9c7b-d95444904053")
    @Override
    public EList<Operation> getRedefinition() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getRedefinitionDep());
    }

    @objid ("ca770309-c64c-4e5e-817d-071f0851e087")
    @Override
    public <T extends Operation> List<T> getRedefinition(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Operation element : getRedefinition()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8e2d7ad7-ea4e-4b47-8d5a-278894cd6d36")
    @Override
    public EList<Collaboration> getExample() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getExampleDep());
    }

    @objid ("d43cc5b4-a968-4f35-b5c5-c92c22eee3e3")
    @Override
    public <T extends Collaboration> List<T> getExample(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Collaboration element : getExample()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("b6e8e5a1-53a7-4410-9272-31f26794d8b3")
    @Override
    public EList<Signal> getSRepresentation() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getSRepresentationDep());
    }

    @objid ("cb038f2f-216b-4beb-85ed-49b2aad0d506")
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

    @objid ("22c7e3e4-8103-49b2-b476-816f902774fe")
    @Override
    public EList<Behavior> getOwnedBehavior() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getOwnedBehaviorDep());
    }

    @objid ("12d5ba88-f7c2-4e2f-903c-e35658dea960")
    @Override
    public <T extends Behavior> List<T> getOwnedBehavior(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Behavior element : getOwnedBehavior()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("7e72dca1-f9e6-4be4-8631-62e47edaa12c")
    @Override
    public EList<Parameter> getIO() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getIODep());
    }

    @objid ("97ef753e-77c0-4f6e-97b4-629f5b82958b")
    @Override
    public <T extends Parameter> List<T> getIO(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Parameter element : getIO()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d3d9891d-4c62-4101-a948-643f1cb099cb")
    @Override
    public EList<TemplateBinding> getTemplateInstanciation() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getTemplateInstanciationDep());
    }

    @objid ("3a6b2b30-4020-4b42-8cfc-35e2504c461c")
    @Override
    public <T extends TemplateBinding> List<T> getTemplateInstanciation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateBinding element : getTemplateInstanciation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3d24df3f-ac61-42da-9227-e836d2a61714")
    @Override
    public Classifier getOwner() {
        Object obj = getDepVal(((OperationSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("a2537a0b-22ab-45ab-a35a-29c1a92f11d5")
    @Override
    public void setOwner(Classifier value) {
        appendDepVal(((OperationSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("3a7e8dcc-7991-489d-9dc5-25d17bb9cee8")
    @Override
    public EList<PackageImport> getOwnedPackageImport() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getOwnedPackageImportDep());
    }

    @objid ("36b88f67-223f-4b7c-8d1d-2a2f9dc0db57")
    @Override
    public <T extends PackageImport> List<T> getOwnedPackageImport(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PackageImport element : getOwnedPackageImport()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("12398c92-48f3-4272-a7e1-4e1c683d6622")
    @Override
    public Parameter getReturn() {
        Object obj = getDepVal(((OperationSmClass)getClassOf()).getReturnDep());
        return (obj instanceof Parameter)? (Parameter)obj : null;
    }

    @objid ("3ff9e4e5-5072-478e-9491-8dfe45f0fd09")
    @Override
    public void setReturn(Parameter value) {
        appendDepVal(((OperationSmClass)getClassOf()).getReturnDep(), (SmObjectImpl)value);
    }

    @objid ("fb025e12-4a5c-469d-a30c-42a6c6e8ec88")
    @Override
    public EList<TemplateBinding> getInstanciatingBinding() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getInstanciatingBindingDep());
    }

    @objid ("1c512c4d-33e5-4feb-a296-940820a51ecc")
    @Override
    public <T extends TemplateBinding> List<T> getInstanciatingBinding(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateBinding element : getInstanciatingBinding()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("62680231-b264-4812-be6f-5183356f77a5")
    @Override
    public EList<Message> getUsage() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getUsageDep());
    }

    @objid ("fd56a2fa-207f-4b35-b337-1874503ec41c")
    @Override
    public <T extends Message> List<T> getUsage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Message element : getUsage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d600c138-8566-450e-90fc-cf492f2114ec")
    @Override
    public EList<TemplateParameter> getTemplate() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getTemplateDep());
    }

    @objid ("49be270a-871d-4f0a-9c0a-ea2ef45bf091")
    @Override
    public <T extends TemplateParameter> List<T> getTemplate(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateParameter element : getTemplate()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("e8a35e72-d48c-4ea4-af81-b0a74bcc6f95")
    @Override
    public EList<Event> getOccurence() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getOccurenceDep());
    }

    @objid ("4612235c-7ca1-491a-bb07-67da83e62f00")
    @Override
    public <T extends Event> List<T> getOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Event element : getOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("4b6aa7a3-a6ef-4bb2-b9b1-614bae16bcec")
    @Override
    public EList<Transition> getInvoker() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getInvokerDep());
    }

    @objid ("2cbc0f33-61ee-4ffc-861a-03ef93f34501")
    @Override
    public <T extends Transition> List<T> getInvoker(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Transition element : getInvoker()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("4c34d2c2-9768-482b-98fc-9214ce956c68")
    @Override
    public EList<CommunicationMessage> getCommunicationUsage() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getCommunicationUsageDep());
    }

    @objid ("645fd3c0-4906-421b-a40a-f65482d63265")
    @Override
    public <T extends CommunicationMessage> List<T> getCommunicationUsage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationMessage element : getCommunicationUsage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("de2967b7-523a-4042-963b-c7e750836f8b")
    @Override
    public EList<CollaborationUse> getOwnedCollaborationUse() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getOwnedCollaborationUseDep());
    }

    @objid ("1e683979-862b-4669-bcc5-52b38194c462")
    @Override
    public <T extends CollaborationUse> List<T> getOwnedCollaborationUse(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CollaborationUse element : getOwnedCollaborationUse()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("ee2326e3-c130-401b-9473-8ad70526b0c7")
    @Override
    public Operation getRedefines() {
        Object obj = getDepVal(((OperationSmClass)getClassOf()).getRedefinesDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("f2a7ec90-f152-49cc-bb7f-cc18e022dfd0")
    @Override
    public void setRedefines(Operation value) {
        appendDepVal(((OperationSmClass)getClassOf()).getRedefinesDep(), (SmObjectImpl)value);
    }

    @objid ("e6525e98-988e-44c8-95c3-4ce0fba72c15")
    @Override
    public EList<CallOperationAction> getCallingAction() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getCallingActionDep());
    }

    @objid ("2f839899-2bfd-4359-9b55-fa3060e126db")
    @Override
    public <T extends CallOperationAction> List<T> getCallingAction(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CallOperationAction element : getCallingAction()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("1f926e7a-ed93-42a0-b684-bd96b2c0136f")
    @Override
    public EList<AcceptCallEventAction> getEntryPointAction() {
        return new SmList<>(this, ((OperationSmClass)getClassOf()).getEntryPointActionDep());
    }

    @objid ("0497f698-62ed-4176-9e0b-c948b9bd1758")
    @Override
    public <T extends AcceptCallEventAction> List<T> getEntryPointAction(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AcceptCallEventAction element : getEntryPointAction()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("ee9d36e4-842a-4da2-abe4-95c980b54b08")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((OperationSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("cb6e3c1a-35c7-46a8-a073-095bdd35bd1a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((OperationSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("adcdeaba-edb1-4e57-8883-7c8be6646f79")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitOperation(this);
    }

}
