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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
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
    @objid ("5a23b1f4-6e90-477d-ae08-6243288bd87e")
    @Override
    public ModelElement getDependsOn() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getDependsOnDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("8c922b79-ffbd-4e90-a780-942f44177549")
    @Override
    public void setDependsOn(ModelElement value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getDependsOnDep(), (SmObjectImpl)value);
    }

    @objid ("9d429c3f-6611-46d7-9439-f604a4cc2038")
    @Override
    public ModelElement getImpacted() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getImpactedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("97001e65-b9f6-4f10-be1e-e4406f6c992b")
    @Override
    public void setImpacted(ModelElement value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getImpactedDep(), (SmObjectImpl)value);
    }

    @objid ("f71eadff-4c7b-454c-80a5-c7139e02da9e")
    @Override
    public EList<Element> getCauses() {
        return new SmList<>(this, ((ImpactLinkSmClass)getClassOf()).getCausesDep());
    }

    @objid ("163cf99f-242c-4dd4-840b-43eac3865d35")
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

    @objid ("0ecf126f-82b7-43ba-a0ea-563d3d060545")
    @Override
    public ImpactModel getOwner() {
        Object obj = getDepVal(((ImpactLinkSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ImpactModel)? (ImpactModel)obj : null;
    }

    @objid ("63e39d95-4a26-4860-bef3-b37fbd947458")
    @Override
    public void setOwner(ImpactModel value) {
        appendDepVal(((ImpactLinkSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("907e8746-bd36-449d-a036-09ccb52ae2d7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("c3c749cf-956b-4853-9d5d-c2c13ddd6e55")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("424ddd89-beaa-41a0-a459-6cf5eaa2f5d4")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitImpactLink(this);
    }

}
