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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
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
    @objid ("1075df20-e958-466d-ae84-8f5a58d82cb6")
    @Override
    public ModelElement getDependsOn() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getDependsOnDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("b8086477-a924-4d62-aab4-cab4953fa392")
    @Override
    public void setDependsOn(ModelElement value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getDependsOnDep(), (SmObjectImpl)value);
    }

    @objid ("a5439fcc-5e9f-489c-94da-0f7fea65bb36")
    @Override
    public ModelElement getImpacted() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getImpactedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("fdabd529-d61a-4759-ad8d-ee7778c192fd")
    @Override
    public void setImpacted(ModelElement value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getImpactedDep(), (SmObjectImpl)value);
    }

    @objid ("a4ca5e25-b550-475d-a29b-4164c65036f6")
    @Override
    public EList<Element> getCauses() {
        return new SmList<>(this, ((ImpactLinkSmClass)getClassOf()).getCausesDep());
    }

    @objid ("655164e5-428d-4956-bf73-2bb60b114ea1")
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

    @objid ("16d6a0dd-8f8b-454b-9c4d-0b02444bbf11")
    @Override
    public ImpactModel getOwner() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ImpactModel)? (ImpactModel)obj : null;
    }

    @objid ("c89d2ecc-b496-450f-ad9e-b051facc2c03")
    @Override
    public void setOwner(ImpactModel value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("d0ba0517-0fd3-416e-840f-cdd9d028b298")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("921bd8ee-0b85-465f-95b4-1a8b3851ab84")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("025b552b-3b89-4e9f-a5c9-7f8dcd5ee03e")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitImpactLink(this);
    }

}
