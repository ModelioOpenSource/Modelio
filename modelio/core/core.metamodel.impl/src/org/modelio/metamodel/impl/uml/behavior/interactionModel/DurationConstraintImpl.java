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
import org.modelio.metamodel.impl.uml.behavior.interactionModel.DurationConstraintData;
import org.modelio.metamodel.impl.uml.infrastructure.ConstraintImpl;
import org.modelio.metamodel.uml.behavior.interactionModel.DurationConstraint;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0043f6ca-c4bf-1fd8-97fe-001ec947cd2a")
public class DurationConstraintImpl extends ConstraintImpl implements DurationConstraint {
    @objid ("9ae747ef-5bc3-4ae6-8ae3-87aecb4bdbdf")
    @Override
    public String getDurationMin() {
        return (String) getAttVal(((DurationConstraintSmClass)getClassOf()).getDurationMinAtt());
    }

    @objid ("f1e72e44-0112-40aa-8b90-261cf3914aaa")
    @Override
    public void setDurationMin(String value) {
        setAttVal(((DurationConstraintSmClass)getClassOf()).getDurationMinAtt(), value);
    }

    @objid ("f5799deb-c2f4-4471-b67a-5823910d30c2")
    @Override
    public String getDurationMax() {
        return (String) getAttVal(((DurationConstraintSmClass)getClassOf()).getDurationMaxAtt());
    }

    @objid ("249aceab-e227-4acf-95c9-3005b98cf156")
    @Override
    public void setDurationMax(String value) {
        setAttVal(((DurationConstraintSmClass)getClassOf()).getDurationMaxAtt(), value);
    }

    @objid ("d659b9e2-5657-48cd-adec-6eca1fa458c3")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("ad79bd02-f5de-478e-9128-aebbda006a27")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8ee23e59-594f-4487-aa00-19b9056cf23e")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitDurationConstraint(this);
    }

}
