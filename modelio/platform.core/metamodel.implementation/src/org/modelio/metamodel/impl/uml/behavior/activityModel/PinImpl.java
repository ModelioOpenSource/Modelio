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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.PinData;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("003cae42-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class PinImpl extends ObjectNodeImpl implements Pin {
    @objid ("25d5ceaf-1722-4a2a-abcf-a7b1e72c7d99")
    @Override
    public boolean isIsControl() {
        return (Boolean) getAttVal(((PinSmClass)getClassOf()).getIsControlAtt());
    }

    @objid ("84492ea7-828c-4a74-a8f9-5c10fdf0bf77")
    @Override
    public void setIsControl(boolean value) {
        setAttVal(((PinSmClass)getClassOf()).getIsControlAtt(), value);
    }

    @objid ("dc9734c7-7d7a-4cdb-b609-53d106b9050c")
    @Override
    public boolean isIsExpansion() {
        return (Boolean) getAttVal(((PinSmClass)getClassOf()).getIsExpansionAtt());
    }

    @objid ("af556038-6341-4204-8e6b-36ad72a7993a")
    @Override
    public void setIsExpansion(boolean value) {
        setAttVal(((PinSmClass)getClassOf()).getIsExpansionAtt(), value);
    }

    @objid ("4430b28b-d848-46b1-b7af-245371f4600c")
    @Override
    public Parameter getMatched() {
        Object obj = getDepVal(((PinSmClass)getClassOf()).getMatchedDep());
        return (obj instanceof Parameter)? (Parameter)obj : null;
    }

    @objid ("cd6e22e0-4ad5-480d-b74f-9410f45fcd0f")
    @Override
    public void setMatched(Parameter value) {
        appendDepVal(((PinSmClass)getClassOf()).getMatchedDep(), (SmObjectImpl)value);
    }

    @objid ("66a1cf26-b74e-46db-8045-bd9681d1425b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("7e5f5eaa-fb1c-4bd5-8029-eb230d86b420")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("9f17d6c8-2dc1-49ef-9cf2-8277d23e4e31")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitPin(this);
    }

}
