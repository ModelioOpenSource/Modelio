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
import org.modelio.metamodel.impl.uml.statik.ComponentRealizationData;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("ea0e67c5-5b02-447c-aae3-d3ef908c1f2a")
public class ComponentRealizationImpl extends UmlModelElementImpl implements ComponentRealization {
    @objid ("12267477-5f89-4f94-a1ea-8f71c79215b8")
    @Override
    public Classifier getRealizingClassifier() {
        Object obj = getDepVal(((ComponentRealizationSmClass)getClassOf()).getRealizingClassifierDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("35cc36e7-d76b-4dc7-909a-6d8e5c86143a")
    @Override
    public void setRealizingClassifier(Classifier value) {
        appendDepVal(((ComponentRealizationSmClass)getClassOf()).getRealizingClassifierDep(), (SmObjectImpl)value);
    }

    @objid ("05327655-ca0a-42c8-ba73-8e30376092c6")
    @Override
    public Component getAbstraction() {
        Object obj = getDepVal(((ComponentRealizationSmClass)getClassOf()).getAbstractionDep());
        return (obj instanceof Component)? (Component)obj : null;
    }

    @objid ("c6506e92-8ce6-4469-91dd-2d5b38ef206a")
    @Override
    public void setAbstraction(Component value) {
        appendDepVal(((ComponentRealizationSmClass)getClassOf()).getAbstractionDep(), (SmObjectImpl)value);
    }

    @objid ("ff1864dc-2aed-4e0a-9b6a-f8eee5cb591a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Abstraction
        obj = (SmObjectImpl)this.getDepVal(((ComponentRealizationSmClass)getClassOf()).getAbstractionDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("44d53fa2-792a-4e50-995c-fa5d8bc17af3")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Abstraction
        dep = ((ComponentRealizationSmClass)getClassOf()).getAbstractionDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("22df8e6a-2e31-4bc7-8190-76e1324c90e0")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitComponentRealization(this);
    }

}
