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

package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ElementImpl;
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MVisitor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("004610d6-c4bf-1fd8-97fe-001ec947cd2a")
public class GeneralOrderingImpl extends ElementImpl implements GeneralOrdering {
    @objid ("3195cb89-6dcd-4631-b1ad-bdf8c02fac2b")
    @Override
    public OccurrenceSpecification getBefore() {
        Object obj = getDepVal(((GeneralOrderingSmClass)getClassOf()).getBeforeDep());
        return (obj instanceof OccurrenceSpecification)? (OccurrenceSpecification)obj : null;
    }

    @objid ("d3d1ee24-8059-40f2-9171-59acd402ef63")
    @Override
    public void setBefore(OccurrenceSpecification value) {
        appendDepVal(((GeneralOrderingSmClass)getClassOf()).getBeforeDep(), (SmObjectImpl)value);
    }

    @objid ("9894e388-dc8c-41da-ac20-378b039599e3")
    @Override
    public OccurrenceSpecification getAfter() {
        Object obj = getDepVal(((GeneralOrderingSmClass)getClassOf()).getAfterDep());
        return (obj instanceof OccurrenceSpecification)? (OccurrenceSpecification)obj : null;
    }

    @objid ("62f6cc59-b402-4597-a3a4-8acb6a1a0d6e")
    @Override
    public void setAfter(OccurrenceSpecification value) {
        appendDepVal(((GeneralOrderingSmClass)getClassOf()).getAfterDep(), (SmObjectImpl)value);
    }

    @objid ("5741daf5-8559-405a-bb05-c0a1111b8dc2")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Before
        obj = (SmObjectImpl)this.getDepVal(((GeneralOrderingSmClass)getClassOf()).getBeforeDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("40a01ae9-f296-4a10-a25f-8ca3ad8dee18")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Before
        dep = ((GeneralOrderingSmClass)getClassOf()).getBeforeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("9b0748f9-4fef-4315-92b8-184daff7ed23")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IModelVisitor)
          return accept((IModelVisitor)v);
        else
          return super.accept(v);
    }

    @objid ("031e3037-7326-488b-a3c4-2e02015d358b")
    public Object accept(IModelVisitor v) {
        return v.visitGeneralOrdering(this);
    }

}
