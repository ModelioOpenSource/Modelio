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
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptTimeEventActionData;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0025b188-c4bf-1fd8-97fe-001ec947cd2a")
public class AcceptTimeEventActionImpl extends ActivityActionImpl implements AcceptTimeEventAction {
    @objid ("889712e8-9290-4471-9fbe-6205825e05c3")
    @Override
    public String getTimeExpresion() {
        return (String) getAttVal(((AcceptTimeEventActionSmClass)getClassOf()).getTimeExpresionAtt());
    }

    @objid ("b9920fe8-be0a-4cca-ab46-db055fa50f8d")
    @Override
    public void setTimeExpresion(String value) {
        setAttVal(((AcceptTimeEventActionSmClass)getClassOf()).getTimeExpresionAtt(), value);
    }

    @objid ("17afd63b-3fd8-4a99-8897-b37c56ad7241")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("f34ec81c-6321-4eb4-928e-2112485c913b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("00df93d6-be39-4267-99be-2a4820314404")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitAcceptTimeEventAction(this);
    }

}
