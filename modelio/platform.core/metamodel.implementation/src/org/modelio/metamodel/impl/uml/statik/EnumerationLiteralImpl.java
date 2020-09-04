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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.EnumerationLiteralData;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000a7e90-c4bf-1fd8-97fe-001ec947cd2a")
public class EnumerationLiteralImpl extends UmlModelElementImpl implements EnumerationLiteral {
    @objid ("22514ac6-9ef9-4bc4-b02b-a46db35e5c4a")
    @Override
    public Enumeration getValuated() {
        Object obj = getDepVal(((EnumerationLiteralSmClass)getClassOf()).getValuatedDep());
        return (obj instanceof Enumeration)? (Enumeration)obj : null;
    }

    @objid ("49297803-3500-4a50-96b5-25455e538fc4")
    @Override
    public void setValuated(Enumeration value) {
        appendDepVal(((EnumerationLiteralSmClass)getClassOf()).getValuatedDep(), (SmObjectImpl)value);
    }

    @objid ("cacaa3c1-fb35-45c2-8469-380a3649e01a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Valuated
        obj = (SmObjectImpl)this.getDepVal(((EnumerationLiteralSmClass)getClassOf()).getValuatedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("cfb77250-6252-4fb4-93cd-994095d5d6b5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Valuated
        dep = ((EnumerationLiteralSmClass)getClassOf()).getValuatedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("2b1b0126-e6d8-4e81-8550-69b067d9acf8")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitEnumerationLiteral(this);
    }

}
