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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impl.impact.ImpactLinkData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("feeb8804-3be0-42cd-b43c-ff7223465a53")
public class ImpactLinkImpl extends ModelElementImpl implements ImpactLink {
    @objid ("2c2a033c-06c7-4559-b1ca-88dcb2c8714b")
    @Override
    public ModelElement getDependsOn() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getDependsOnDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("b13c4a11-d71c-49db-b83d-b10a5d0874eb")
    @Override
    public void setDependsOn(ModelElement value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getDependsOnDep(), (SmObjectImpl)value);
    }

    @objid ("8c24b338-54c2-4d37-bc31-63b90c98cf1e")
    @Override
    public ModelElement getImpacted() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getImpactedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("8d500bef-0d6d-4951-9f40-dd2ac448a06c")
    @Override
    public void setImpacted(ModelElement value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getImpactedDep(), (SmObjectImpl)value);
    }

    @objid ("5cec6b73-b33e-490f-b86f-6171466c2f5d")
    @Override
    public EList<Element> getCauses() {
        return new SmList<>(this, ((ImpactLinkSmClass)getClassOf()).getCausesDep());
    }

    @objid ("0b578da7-e881-40f3-90a6-768f33ee3fb9")
    @Override
    public <T extends Element> List<T> getCauses(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Element element : getCauses()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("c32a3e7e-0091-4c8f-b259-e5434e266a23")
    @Override
    public ImpactModel getOwner() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ImpactModel)? (ImpactModel)obj : null;
    }

    @objid ("4147bbab-2d92-45e7-b61c-520a4f18e85b")
    @Override
    public void setOwner(ImpactModel value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("a26bd5aa-df68-45ad-bf5c-691e18ace025")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("2e4321ae-986e-4514-af25-c4fef8872f87")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("ecea47a4-9dd6-432d-a6af-a58e4176c0ce")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitImpactLink(this);
    }

}
