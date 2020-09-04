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
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallActionData;
import org.modelio.metamodel.uml.behavior.activityModel.CallAction;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("002a9180-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class CallActionImpl extends ActivityActionImpl implements CallAction {
    @objid ("5699bf6f-d3ca-490a-a9ac-9abae29a350c")
    @Override
    public boolean isIsSynchronous() {
        return (Boolean) getAttVal(((CallActionSmClass)getClassOf()).getIsSynchronousAtt());
    }

    @objid ("f98a63a1-ad86-425e-bc33-54d47f48aa5a")
    @Override
    public void setIsSynchronous(boolean value) {
        setAttVal(((CallActionSmClass)getClassOf()).getIsSynchronousAtt(), value);
    }

    @objid ("b1a1815f-b65a-46d3-8bd5-a21dd055a3c9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("472eb14b-9deb-4df5-add3-091965816907")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("cefb31e8-4e0c-4621-b1a6-9e6355b2f2cb")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCallAction(this);
    }

}
