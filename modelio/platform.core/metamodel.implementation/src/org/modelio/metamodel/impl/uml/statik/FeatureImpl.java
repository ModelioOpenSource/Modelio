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
import org.modelio.metamodel.impl.uml.statik.FeatureData;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000b3ab0-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class FeatureImpl extends UmlModelElementImpl implements Feature {
    @objid ("5b9a9d0a-375e-4c1b-b2ba-8a196c09edd4")
    @Override
    public VisibilityMode getVisibility() {
        return (VisibilityMode) getAttVal(((FeatureSmClass)getClassOf()).getVisibilityAtt());
    }

    @objid ("abecd371-4acb-449c-bfc8-0f9083a2b296")
    @Override
    public void setVisibility(VisibilityMode value) {
        setAttVal(((FeatureSmClass)getClassOf()).getVisibilityAtt(), value);
    }

    @objid ("7798c2b2-2d24-4113-9b1d-67a7e756762e")
    @Override
    public boolean isIsClass() {
        return (Boolean) getAttVal(((FeatureSmClass)getClassOf()).getIsClassAtt());
    }

    @objid ("49c5ec28-0773-4a1e-be72-91b809eff305")
    @Override
    public void setIsClass(boolean value) {
        setAttVal(((FeatureSmClass)getClassOf()).getIsClassAtt(), value);
    }

    @objid ("a647a81b-4363-4e47-b09f-75b56e99cbb8")
    @Override
    public boolean isIsAbstract() {
        return (Boolean) getAttVal(((FeatureSmClass)getClassOf()).getIsAbstractAtt());
    }

    @objid ("bcb9de9b-624c-4c2c-a70a-3c99fb73e1b6")
    @Override
    public void setIsAbstract(boolean value) {
        setAttVal(((FeatureSmClass)getClassOf()).getIsAbstractAtt(), value);
    }

    @objid ("da47eb83-24e8-44f4-b3d8-1d17c4de613f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("c7f630cb-3491-4768-8f4f-a06ba4dce67a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8d471bc3-0c14-4d82-956c-4dd886b31f24")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitFeature(this);
    }

}
