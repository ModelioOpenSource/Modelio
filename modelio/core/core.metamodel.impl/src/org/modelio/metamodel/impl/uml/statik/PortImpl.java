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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0018eeee-c4bf-1fd8-97fe-001ec947cd2a")
public class PortImpl extends BindableInstanceImpl implements Port {
    @objid ("8585db37-7bcc-48a6-894d-2b54bab30155")
    @Override
    public boolean isIsBehavior() {
        return (Boolean) getAttVal(((PortSmClass)getClassOf()).getIsBehaviorAtt());
    }

    @objid ("0cc58ebe-72f7-498d-aa99-bb370234a6a8")
    @Override
    public void setIsBehavior(boolean value) {
        setAttVal(((PortSmClass)getClassOf()).getIsBehaviorAtt(), value);
    }

    @objid ("3464c506-eded-42af-bced-70d3243cf5bb")
    @Override
    public boolean isIsService() {
        return (Boolean) getAttVal(((PortSmClass)getClassOf()).getIsServiceAtt());
    }

    @objid ("fcb49783-a2ff-45bd-9b20-5ffbde3341d3")
    @Override
    public void setIsService(boolean value) {
        setAttVal(((PortSmClass)getClassOf()).getIsServiceAtt(), value);
    }

    @objid ("018194bf-06b6-44f8-84db-e8785f60e2d5")
    @Override
    public boolean isIsConjugated() {
        return (Boolean) getAttVal(((PortSmClass)getClassOf()).getIsConjugatedAtt());
    }

    @objid ("d073cea7-a135-4c26-8bf3-c6163a6c6007")
    @Override
    public void setIsConjugated(boolean value) {
        setAttVal(((PortSmClass)getClassOf()).getIsConjugatedAtt(), value);
    }

    @objid ("5473961c-e3c0-423a-ab8a-bc9a4519b89f")
    @Override
    public PortOrientation getDirection() {
        return (PortOrientation) getAttVal(((PortSmClass)getClassOf()).getDirectionAtt());
    }

    @objid ("735acbcf-988a-4359-9f83-d5ce3eae3c54")
    @Override
    public void setDirection(PortOrientation value) {
        setAttVal(((PortSmClass)getClassOf()).getDirectionAtt(), value);
    }

    @objid ("cf7a03ea-d611-4a39-9a4f-0ef141297d43")
    @Override
    public EList<ProvidedInterface> getProvided() {
        return new SmList<>(this, ((PortSmClass)getClassOf()).getProvidedDep());
    }

    @objid ("a4148c6c-7662-4715-bde6-c1a746d8c29e")
    @Override
    public <T extends ProvidedInterface> List<T> getProvided(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ProvidedInterface element : getProvided()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("a485a0a9-4a1b-496d-8196-d4601b0b51bd")
    @Override
    public EList<RequiredInterface> getRequired() {
        return new SmList<>(this, ((PortSmClass)getClassOf()).getRequiredDep());
    }

    @objid ("0b8e738d-6db1-4041-b638-afc479cedafd")
    @Override
    public <T extends RequiredInterface> List<T> getRequired(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final RequiredInterface element : getRequired()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("0e2bb57d-481f-46cd-989a-6ac09667932c")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("2d58334e-e0fb-48bb-a211-8ba963652484")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("5851f81d-0dcb-40d8-a3af-f71cd96fab1d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitPort(this);
    }

}
