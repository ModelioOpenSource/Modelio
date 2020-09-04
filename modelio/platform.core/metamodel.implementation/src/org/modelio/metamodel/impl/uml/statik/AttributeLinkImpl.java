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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.AttributeLinkData;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00003354-c4bf-1fd8-97fe-001ec947cd2a")
public class AttributeLinkImpl extends UmlModelElementImpl implements AttributeLink {
    @objid ("d16a7bfb-cd56-4e60-af71-aab2976c9216")
    @Override
    public String getValue() {
        return (String) getAttVal(((AttributeLinkSmClass)getClassOf()).getValueAtt());
    }

    @objid ("3db62d0b-09d2-4e2d-b0cf-96ab75d7b322")
    @Override
    public void setValue(String value) {
        setAttVal(((AttributeLinkSmClass)getClassOf()).getValueAtt(), value);
    }

    @objid ("0b8e6d7f-6140-4de3-aeb5-17429ab9a120")
    @Override
    public Instance getAttributed() {
        Object obj = getDepVal(((AttributeLinkSmClass)getClassOf()).getAttributedDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("e2762520-b52c-4642-8f51-e3eb76d9b961")
    @Override
    public void setAttributed(Instance value) {
        appendDepVal(((AttributeLinkSmClass)getClassOf()).getAttributedDep(), (SmObjectImpl)value);
    }

    @objid ("3a8201cc-891b-4ecf-8833-e78409fb2260")
    @Override
    public Attribute getBase() {
        Object obj = getDepVal(((AttributeLinkSmClass)getClassOf()).getBaseDep());
        return (obj instanceof Attribute)? (Attribute)obj : null;
    }

    @objid ("50d58137-828a-454b-a47d-ef2066f6fbb1")
    @Override
    public void setBase(Attribute value) {
        appendDepVal(((AttributeLinkSmClass)getClassOf()).getBaseDep(), (SmObjectImpl)value);
    }

    @objid ("e229ebde-d019-44bb-adf2-b367531ecdf7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Attributed
        obj = (SmObjectImpl)this.getDepVal(((AttributeLinkSmClass)getClassOf()).getAttributedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a636778a-024a-40e8-bb4f-c23e2bd04abe")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Attributed
        dep = ((AttributeLinkSmClass)getClassOf()).getAttributedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("be420c19-3573-41e1-ba62-27909e1627ce")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitAttributeLink(this);
    }

}
