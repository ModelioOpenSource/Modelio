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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("fd1c4e0b-0cd4-4bb5-bc18-359cb4e3c561")
public class AbstractProjectImpl extends ModelElementImpl implements AbstractProject {
    @objid ("64ae87e1-86bd-47fb-87de-8978ae356902")
    @Override
    public DiagramSet getDiagramRoot() {
        Object obj = getDepVal(((AbstractProjectSmClass)getClassOf()).getDiagramRootDep());
        return (obj instanceof DiagramSet)? (DiagramSet)obj : null;
    }

    @objid ("27674284-f44b-4709-8e12-5f2f161904b9")
    @Override
    public void setDiagramRoot(DiagramSet value) {
        appendDepVal(((AbstractProjectSmClass)getClassOf()).getDiagramRootDep(), (SmObjectImpl)value);
    }

    @objid ("543802d8-5edc-4bda-bc86-06a7bb9fa950")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("1f8b419d-d8d1-4274-86ed-4355f7acd803")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("3ad5c64d-2b37-48e4-bbae-db93e87f9c8c")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractProject(this);
    }

}
