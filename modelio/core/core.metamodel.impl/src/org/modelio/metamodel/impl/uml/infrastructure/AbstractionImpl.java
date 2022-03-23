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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.DependencyImpl;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MVisitor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0084cc0e-c4be-1fd8-97fe-001ec947cd2a")
public class AbstractionImpl extends DependencyImpl implements Abstraction {
    @objid ("b6236118-e35b-4b93-89fa-6e90c1eaaf59")
    @Override
    public String getMapping() {
        return (String) getAttVal(((AbstractionSmClass)getClassOf()).getMappingAtt());
    }

    @objid ("42aa0798-d57f-4d5a-a1a6-73fc092b8657")
    @Override
    public void setMapping(String value) {
        setAttVal(((AbstractionSmClass)getClassOf()).getMappingAtt(), value);
    }

    @objid ("19c40654-4d86-4113-af0a-bd990967895e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("776caa72-9598-4d93-a219-33d77280cf86")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("33183fa1-0204-4971-9172-3d2fe0aaf2ff")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IModelVisitor)
          return accept((IModelVisitor)v);
        else
          return super.accept(v);
    }

    @objid ("91b5f566-0cdd-4100-9d9d-8dd4f6bdd9f1")
    public Object accept(IModelVisitor v) {
        return v.visitAbstraction(this);
    }

}
