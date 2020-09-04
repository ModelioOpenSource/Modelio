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
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.properties.EnumeratedPropertyTypeData;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0070f526-ec87-1098-b22e-001ec947cd2a")
public class EnumeratedPropertyTypeImpl extends PropertyTypeImpl implements EnumeratedPropertyType {
    @objid ("31417e25-109f-4f03-9e0b-812215e9b1ff")
    @Override
    public PropertyEnumerationLitteral getLitteral(String s) {
        for (PropertyEnumerationLitteral l : getLitteral() ) {
            if (l.getName().equals(s))
                    return l;
        }
        return null;
    }

    @objid ("a6323bf9-834a-4837-a8f1-a599c9a1be44")
    @Override
    public EList<PropertyEnumerationLitteral> getLitteral() {
        return new SmList<>(this, ((EnumeratedPropertyTypeSmClass)getClassOf()).getLitteralDep());
    }

    @objid ("8957fd8a-588c-4a72-83b6-5a8f2d6e57ba")
    @Override
    public <T extends PropertyEnumerationLitteral> List<T> getLitteral(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PropertyEnumerationLitteral element : getLitteral()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d97be336-10bc-4c2b-83fa-a2a4ec7f5863")
    @Override
    public EList<ModuleParameter> getOccurenceConfigParam() {
        return new SmList<>(this, ((EnumeratedPropertyTypeSmClass)getClassOf()).getOccurenceConfigParamDep());
    }

    @objid ("4a4ab85f-b770-4083-acf7-63a1b36a4ae6")
    @Override
    public <T extends ModuleParameter> List<T> getOccurenceConfigParam(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ModuleParameter element : getOccurenceConfigParam()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8410edc6-65e9-4a03-b346-d7fe01094143")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("4bed9732-1766-4e56-b1a3-037a187edd90")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("1caf2544-c2ec-4009-bd9d-9f6da2a08858")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitEnumeratedPropertyType(this);
    }

}
