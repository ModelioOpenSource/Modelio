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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("d5f35d11-1f2b-4bd0-8170-3ead18d8e775")
    @Override
    public DiagramSet getDiagramRoot() {
        Object obj = getDepVal(((AbstractProjectSmClass)getClassOf()).getDiagramRootDep());
        return (obj instanceof DiagramSet)? (DiagramSet)obj : null;
    }

    @objid ("c98c528a-04f2-4288-8e2c-5fc4da6d27a7")
    @Override
    public void setDiagramRoot(DiagramSet value) {
        appendDepVal(((AbstractProjectSmClass)getClassOf()).getDiagramRootDep(), (SmObjectImpl)value);
    }

    @objid ("1ca40474-482a-4096-b734-7fd8fb5efbc7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("e06d7d2e-1b5a-4e30-80d9-9c3ec0bdf9ba")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("2bcf1f30-388d-46d7-b0b4-76abeddfea57")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractProject(this);
    }

}
