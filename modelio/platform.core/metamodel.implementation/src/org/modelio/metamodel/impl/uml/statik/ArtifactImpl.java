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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.statik.ArtifactData;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0096da48-c4be-1fd8-97fe-001ec947cd2a")
public class ArtifactImpl extends ClassifierImpl implements Artifact {
    @objid ("b7c062b4-5871-4ed9-a6f2-9e81802b0ca7")
    @Override
    public String getFileName() {
        return (String) getAttVal(((ArtifactSmClass)getClassOf()).getFileNameAtt());
    }

    @objid ("28abb645-c234-4444-a144-9b247fdf6ef7")
    @Override
    public void setFileName(String value) {
        setAttVal(((ArtifactSmClass)getClassOf()).getFileNameAtt(), value);
    }

    @objid ("e2192afd-f3ef-455b-b4ac-187d03e67a55")
    @Override
    public EList<Manifestation> getUtilized() {
        return new SmList<>(this, ((ArtifactSmClass)getClassOf()).getUtilizedDep());
    }

    @objid ("7abceb6a-d0d5-4ebb-9d89-712f52267abf")
    @Override
    public <T extends Manifestation> List<T> getUtilized(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Manifestation element : getUtilized()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("35f43958-6743-43e6-9d8a-707417d93e5f")
    @Override
    public EList<Node> getDeploymentLocation() {
        return new SmList<>(this, ((ArtifactSmClass)getClassOf()).getDeploymentLocationDep());
    }

    @objid ("7130533c-9e7c-42cf-86dc-92cdf3960d89")
    @Override
    public <T extends Node> List<T> getDeploymentLocation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Node element : getDeploymentLocation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("eab586c0-de8d-41c5-b615-d5b82ed28551")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("c2f728a8-9da5-432e-9feb-236f26d1c45d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("073871f9-5773-4ee5-b69a-335149d7011f")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitArtifact(this);
    }

}
