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
import org.modelio.metamodel.impl.uml.infrastructure.ModelTreeImpl;
import org.modelio.metamodel.impl.uml.statik.NameSpaceData;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0011931a-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class NameSpaceImpl extends ModelTreeImpl implements NameSpace {
    @objid ("2129840a-d6e4-483c-8fa6-d0d60b5f5f31")
    @Override
    public boolean isIsAbstract() {
        return (Boolean) getAttVal(((NameSpaceSmClass)getClassOf()).getIsAbstractAtt());
    }

    @objid ("37b7c63e-fe93-4930-b7ea-364b29a24563")
    @Override
    public void setIsAbstract(boolean value) {
        setAttVal(((NameSpaceSmClass)getClassOf()).getIsAbstractAtt(), value);
    }

    @objid ("7af301d3-08c9-4089-b596-5931af6e9eb6")
    @Override
    public boolean isIsLeaf() {
        return (Boolean) getAttVal(((NameSpaceSmClass)getClassOf()).getIsLeafAtt());
    }

    @objid ("73c161b4-2bd1-4eb7-aa2c-14af0d328c97")
    @Override
    public void setIsLeaf(boolean value) {
        setAttVal(((NameSpaceSmClass)getClassOf()).getIsLeafAtt(), value);
    }

    @objid ("570e900e-2890-4c69-9f12-da259293da12")
    @Override
    public boolean isIsRoot() {
        return (Boolean) getAttVal(((NameSpaceSmClass)getClassOf()).getIsRootAtt());
    }

    @objid ("f9d5b4d7-98c0-46ee-9834-33f03a90da20")
    @Override
    public void setIsRoot(boolean value) {
        setAttVal(((NameSpaceSmClass)getClassOf()).getIsRootAtt(), value);
    }

    @objid ("cc948152-cccd-4e99-925d-43521f366a0b")
    @Override
    public VisibilityMode getVisibility() {
        return (VisibilityMode) getAttVal(((NameSpaceSmClass)getClassOf()).getVisibilityAtt());
    }

    @objid ("7572c353-29d6-4324-b56e-7419d4c776ba")
    @Override
    public void setVisibility(VisibilityMode value) {
        setAttVal(((NameSpaceSmClass)getClassOf()).getVisibilityAtt(), value);
    }

    @objid ("30c99c9f-db41-414e-9ca6-9f0079f49bbb")
    @Override
    public EList<Generalization> getParent() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getParentDep());
    }

    @objid ("e5b3d03c-1d2b-4eed-85d5-b5423673db05")
    @Override
    public <T extends Generalization> List<T> getParent(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Generalization element : getParent()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("08074add-44cc-4c52-ac1c-33b980ce133c")
    @Override
    public EList<TemplateBinding> getTemplateInstanciation() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getTemplateInstanciationDep());
    }

    @objid ("53e2b730-2ebd-4aa8-891e-c400a9d71a15")
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

    @objid ("f14a9c77-7414-433f-b198-6d65bc6a7959")
    @Override
    public EList<Instance> getRepresenting() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getRepresentingDep());
    }

    @objid ("46866b6f-e530-4f67-8cfd-4c79123abbc0")
    @Override
    public <T extends Instance> List<T> getRepresenting(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Instance element : getRepresenting()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("de2f2062-f6a0-4aee-9201-3d865efba4f7")
    @Override
    public EList<Behavior> getOwnedBehavior() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getOwnedBehaviorDep());
    }

    @objid ("a5502dff-8793-4c70-a930-cd6441a20b24")
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

    @objid ("51a4840d-0c5e-49a9-bbed-4b783cee8f1d")
    @Override
    public EList<DataFlow> getReceived() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getReceivedDep());
    }

    @objid ("aed0dca5-73a3-4759-9c99-cedc3ad843bf")
    @Override
    public <T extends DataFlow> List<T> getReceived(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final DataFlow element : getReceived()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("2f482dbe-1386-4bde-9329-c00951527d62")
    @Override
    public EList<InformationFlow> getOwnedInformationFlow() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getOwnedInformationFlowDep());
    }

    @objid ("81204df6-48e5-4a5a-a752-ebc5be6a7f05")
    @Override
    public <T extends InformationFlow> List<T> getOwnedInformationFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getOwnedInformationFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("700d6130-f7b0-4ca6-8868-870f9be1172b")
    @Override
    public EList<ElementImport> getImporting() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getImportingDep());
    }

    @objid ("b5205e82-3716-4a11-b8de-144b1a8d662f")
    @Override
    public <T extends ElementImport> List<T> getImporting(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ElementImport element : getImporting()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3f70fc13-fdcb-40fd-a9d8-8a96f2ac0602")
    @Override
    public EList<DataFlow> getSent() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getSentDep());
    }

    @objid ("a74f9e16-fe4b-4370-bce6-03e0c0e7f67a")
    @Override
    public <T extends DataFlow> List<T> getSent(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final DataFlow element : getSent()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("67a2d9a8-2620-4ebc-904f-5f1bfb4d282b")
    @Override
    public EList<DataFlow> getOwnedDataFlow() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getOwnedDataFlowDep());
    }

    @objid ("3b7d1d8a-7433-4c6c-8cff-acb98e17febf")
    @Override
    public <T extends DataFlow> List<T> getOwnedDataFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final DataFlow element : getOwnedDataFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("90c6b09d-eca8-456b-b791-341c0fe37a8b")
    @Override
    public EList<CollaborationUse> getOwnedCollaborationUse() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getOwnedCollaborationUseDep());
    }

    @objid ("c178b57a-ebd1-4d27-a1da-1498c5d4a7ea")
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

    @objid ("85e15bca-8ea6-40ee-9e99-16bc37c0b0da")
    @Override
    public EList<PackageImport> getOwnedPackageImport() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getOwnedPackageImportDep());
    }

    @objid ("a3515f5e-c4fa-441e-adbc-fa47e492a2a5")
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

    @objid ("426eff8b-8805-4a3c-b2cf-131956f6f500")
    @Override
    public EList<TemplateParameter> getTemplate() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getTemplateDep());
    }

    @objid ("02fc2aaf-dc9f-4a28-a878-a6dc3191c64e")
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

    @objid ("91f6263d-37c0-494d-88e3-f0f27b5a15a7")
    @Override
    public EList<Generalization> getSpecialization() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getSpecializationDep());
    }

    @objid ("b0aa279d-365f-493b-87f0-d9cc04ed5d0c")
    @Override
    public <T extends Generalization> List<T> getSpecialization(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Generalization element : getSpecialization()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("2c92e1de-3b23-4cfe-8549-fc0e3fbb53c4")
    @Override
    public EList<InterfaceRealization> getRealized() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getRealizedDep());
    }

    @objid ("94a659fe-9b0f-44b0-bcd7-01f2cf12c78f")
    @Override
    public <T extends InterfaceRealization> List<T> getRealized(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InterfaceRealization element : getRealized()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("11cb622d-9684-4c7b-b7a5-46a262ce39fd")
    @Override
    public EList<Instance> getDeclared() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getDeclaredDep());
    }

    @objid ("1413cacf-0b71-4bff-86bd-4bf67dc244f3")
    @Override
    public <T extends Instance> List<T> getDeclared(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Instance element : getDeclared()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("81f043f1-6c66-49d3-a0ca-4df8671dbe47")
    @Override
    public EList<TemplateBinding> getInstanciatingBinding() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getInstanciatingBindingDep());
    }

    @objid ("33675134-9724-4c2c-b5b6-dd78f498f7d9")
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

    @objid ("0c40485b-b067-4a0a-a234-de3b5b0b52d9")
    @Override
    public EList<ElementImport> getOwnedImport() {
        return new SmList<>(this, ((NameSpaceSmClass)getClassOf()).getOwnedImportDep());
    }

    @objid ("b4de5097-9989-462d-a05b-a837e7118760")
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

    @objid ("b9cad71e-a2be-4dc3-b333-3ed94aa31308")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("cbe815e0-8823-4047-977a-809fe7b2da95")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("2db77123-e885-4253-9f2b-822f87639787")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitNameSpace(this);
    }

}
