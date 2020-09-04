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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectData;
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
    @objid ("4ed38419-c184-493c-8272-b4d3a2579e0c")
    @Override
    public DiagramSet getDiagramRoot() {
        Object obj = getDepVal(((AbstractProjectSmClass)getClassOf()).getDiagramRootDep());
        return (obj instanceof DiagramSet)? (DiagramSet)obj : null;
    }

    @objid ("5344ee03-0579-4bdf-ac27-f4b8d68ff8b6")
    @Override
    public void setDiagramRoot(DiagramSet value) {
        appendDepVal(((AbstractProjectSmClass)getClassOf()).getDiagramRootDep(), (SmObjectImpl)value);
    }

    @objid ("ae25b4de-b6dd-4357-821e-f9511a62d79a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("44708bc3-5627-45e3-85b9-d3bd3acaf68e")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("3de34380-bbbb-4011-be96-e3e99c12ae17")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractProject(this);
    }

}
