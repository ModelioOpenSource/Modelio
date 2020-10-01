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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.impl.diagrams.ActivityDiagramData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0067fa70-c4bf-1fd8-97fe-001ec947cd2a")
public class ActivityDiagramImpl extends BehaviorDiagramImpl implements ActivityDiagram {
    @objid ("b4329439-cfc2-414d-af65-64ebdc667a6d")
    @Override
    public boolean isIsVertical() {
        return (Boolean) getAttVal(((ActivityDiagramSmClass)getClassOf()).getIsVerticalAtt());
    }

    @objid ("e628eb5d-d5c2-48aa-b185-9db92222d1c0")
    @Override
    public void setIsVertical(boolean value) {
        setAttVal(((ActivityDiagramSmClass)getClassOf()).getIsVerticalAtt(), value);
    }

    @objid ("3e7bbd59-5014-4c6d-9756-e319b157e5a7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("0b5295b3-445b-456e-a1d6-9cb73692a4e7")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("2606667f-bb88-46e6-a9e9-d237693ee0d4")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitActivityDiagram(this);
    }

}
