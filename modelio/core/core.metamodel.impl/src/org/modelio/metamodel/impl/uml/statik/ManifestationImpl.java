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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0010c444-c4bf-1fd8-97fe-001ec947cd2a")
public class ManifestationImpl extends UmlModelElementImpl implements Manifestation {
    @objid ("38c2d315-5cb6-47ec-b0ec-175fea566d14")
    @Override
    public UmlModelElement getUtilizedElement() {
        Object obj = getDepVal(((ManifestationSmClass)getClassOf()).getUtilizedElementDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("1d60fe8c-1f7a-4260-85b9-a9046559c04a")
    @Override
    public void setUtilizedElement(UmlModelElement value) {
        appendDepVal(((ManifestationSmClass)getClassOf()).getUtilizedElementDep(), (SmObjectImpl)value);
    }

    @objid ("4bac005c-23cb-498e-b281-155a348ca25b")
    @Override
    public Artifact getOwner() {
        Object obj = getDepVal(((ManifestationSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Artifact)? (Artifact)obj : null;
    }

    @objid ("c5aff523-a760-4bc4-86a7-a079954d04b7")
    @Override
    public void setOwner(Artifact value) {
        appendDepVal(((ManifestationSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("2928da11-fa54-4bba-977b-099394fb4b7f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((ManifestationSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("fd567db8-17bf-4edf-bcf3-09b563309676")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((ManifestationSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("c7e1a3a1-5fa7-479c-91cd-97911262a368")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitManifestation(this);
    }

}
