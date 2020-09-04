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
package org.modelio.metamodel.impl.mda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.mda.ProjectData;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectImpl;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MVisitor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("006633d4-c4bf-1fd8-97fe-001ec947cd2a")
public class ProjectImpl extends AbstractProjectImpl implements Project {
    @objid ("9fbcf227-6882-47bf-961f-b399811d96a2")
    @Override
    public String getProjectContext() {
        return (String) getAttVal(((ProjectSmClass)getClassOf()).getProjectContextAtt());
    }

    @objid ("63b29c80-d5c4-4fcf-8548-02f786202224")
    @Override
    public void setProjectContext(String value) {
        setAttVal(((ProjectSmClass)getClassOf()).getProjectContextAtt(), value);
    }

    @objid ("0d729374-0155-4a91-a84d-59eab020cf3d")
    @Override
    public String getProjectDescr() {
        return (String) getAttVal(((ProjectSmClass)getClassOf()).getProjectDescrAtt());
    }

    @objid ("ed1b0d7f-dad8-4ca9-8103-18950c4e53b7")
    @Override
    public void setProjectDescr(String value) {
        setAttVal(((ProjectSmClass)getClassOf()).getProjectDescrAtt(), value);
    }

    @objid ("f239f327-a3d1-425a-b2ab-bcde037e0565")
    @Override
    public EList<Package> getModel() {
        return new SmList<>(this, ((ProjectSmClass)getClassOf()).getModelDep());
    }

    @objid ("d22928d0-2037-45d9-be63-f560381e2cfd")
    @Override
    public <T extends Package> List<T> getModel(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Package element : getModel()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("00b97de7-9c44-4e78-9ed5-43c879476077")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("bcaf06d2-d0a0-47b2-a8d4-60af9380f50c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("9c3c2db6-21c6-4c00-90a9-53752126d9ea")
    @Override
    public Object accept(MVisitor v) {
        if (v instanceof IModelVisitor)
          return accept((IModelVisitor)v);
        else
          return super.accept(v);
    }

    @objid ("1cfb1927-7ab0-43a4-8160-0661a8d0c9ef")
    public Object accept(IModelVisitor v) {
        return v.visitProject(this);
    }

}
