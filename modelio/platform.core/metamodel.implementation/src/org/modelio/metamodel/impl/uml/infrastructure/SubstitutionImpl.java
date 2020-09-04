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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.SubstitutionData;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008e0fa8-c4be-1fd8-97fe-001ec947cd2a")
public class SubstitutionImpl extends UmlModelElementImpl implements Substitution {
    @objid ("83249e87-87bb-40e1-a43b-6efa422c5971")
    @Override
    public Classifier getContract() {
        Object obj = getDepVal(((SubstitutionSmClass)getClassOf()).getContractDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("e2821b93-a2d9-4fad-a320-d8f6596a93e7")
    @Override
    public void setContract(Classifier value) {
        appendDepVal(((SubstitutionSmClass)getClassOf()).getContractDep(), (SmObjectImpl)value);
    }

    @objid ("6338c62d-7682-40a0-816a-530f37909082")
    @Override
    public Classifier getSubstitutingClassifier() {
        Object obj = getDepVal(((SubstitutionSmClass)getClassOf()).getSubstitutingClassifierDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("e71eb455-e195-4c33-b2ce-74e1f026e88f")
    @Override
    public void setSubstitutingClassifier(Classifier value) {
        appendDepVal(((SubstitutionSmClass)getClassOf()).getSubstitutingClassifierDep(), (SmObjectImpl)value);
    }

    @objid ("c40b8a73-63d0-4a60-8601-6d20e1dbc4d7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SubstitutingClassifier
        obj = (SmObjectImpl)this.getDepVal(((SubstitutionSmClass)getClassOf()).getSubstitutingClassifierDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("853ff196-a7c4-44cd-9c3f-48e437c5f0be")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SubstitutingClassifier
        dep = ((SubstitutionSmClass)getClassOf()).getSubstitutingClassifierDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("432c1377-c6e7-454f-8dec-787a12f1642a")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitSubstitution(this);
    }

}
