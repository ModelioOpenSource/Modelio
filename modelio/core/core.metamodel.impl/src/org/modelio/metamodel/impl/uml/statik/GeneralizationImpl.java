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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000cb930-c4bf-1fd8-97fe-001ec947cd2a")
public class GeneralizationImpl extends UmlModelElementImpl implements Generalization {
    @objid ("bf02ea68-03d3-4f6d-bbe5-f726f188cd1a")
    @Override
    public String getDiscriminator() {
        return (String) getAttVal(((GeneralizationSmClass)getClassOf()).getDiscriminatorAtt());
    }

    @objid ("acb9a1fa-a6d0-4918-bd65-eeac0987f507")
    @Override
    public void setDiscriminator(String value) {
        setAttVal(((GeneralizationSmClass)getClassOf()).getDiscriminatorAtt(), value);
    }

    @objid ("97fde83e-6b13-4ec3-bc39-eeb4bec531ec")
    @Override
    public NameSpace getSuperType() {
        Object obj = getDepVal(((GeneralizationSmClass)getClassOf()).getSuperTypeDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("0015a515-eb35-445c-9ad6-c683548fb874")
    @Override
    public void setSuperType(NameSpace value) {
        appendDepVal(((GeneralizationSmClass)getClassOf()).getSuperTypeDep(), (SmObjectImpl)value);
    }

    @objid ("6272f020-7f3f-4366-a3b1-ac5789225ebf")
    @Override
    public NameSpace getSubType() {
        Object obj = getDepVal(((GeneralizationSmClass)getClassOf()).getSubTypeDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("3f15d3f5-1b94-4758-94bc-022b2951c103")
    @Override
    public void setSubType(NameSpace value) {
        appendDepVal(((GeneralizationSmClass)getClassOf()).getSubTypeDep(), (SmObjectImpl)value);
    }

    @objid ("2bab307b-62f7-4374-999a-0fa86e8bb94a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SubType
        obj = (SmObjectImpl)this.getDepVal(((GeneralizationSmClass)getClassOf()).getSubTypeDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("d2882b5f-bb9e-45a9-bfe9-8525e15bafff")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SubType
        dep = ((GeneralizationSmClass)getClassOf()).getSubTypeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("15f996ef-f8fa-4e1b-8992-a0b503de64de")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitGeneralization(this);
    }

}
