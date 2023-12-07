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

package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleParameter;

/**
 * EnumeratedPropertyType v0.0.9054
 * 
 * 
 * Enumerated property type. Owns the literals that are allowed as property values.
 * 
 * 
 */
@objid ("00706656-ec87-1098-b22e-001ec947cd2a")
public interface EnumeratedPropertyType extends PropertyType {
    /**
     * The metaclass simple name.
     */
    @objid ("a4477c52-2693-4532-a831-cba96cd18087")
    public static final String MNAME = "EnumeratedPropertyType";

    /**
     * The metaclass qualified name.
     */
    @objid ("f58aba8e-0f64-401a-9882-4ed1e04a2498")
    public static final String MQNAME = "Infrastructure.EnumeratedPropertyType";

    /**
     * Returns the literal represented by 's' or null if not found
     * @param s @return
     */
    @objid ("980fb52a-eaee-4290-b6b6-bde39e3d8dc7")
    PropertyEnumerationLitteral getLitteral(String s);

    /**
     * Getter for relation 'EnumeratedPropertyType->Litteral'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("91d02754-5579-494d-a538-33dadddf9755")
    EList<PropertyEnumerationLitteral> getLitteral();

    /**
     * Filtered Getter for relation 'EnumeratedPropertyType->Litteral'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("5818bb05-4de1-4126-868d-c741721cbecf")
    <T extends PropertyEnumerationLitteral> List<T> getLitteral(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'EnumeratedPropertyType->OccurenceConfigParam'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2eb76204-ed87-451d-b024-8e6eafd9f9e2")
    EList<ModuleParameter> getOccurenceConfigParam();

    /**
     * Filtered Getter for relation 'EnumeratedPropertyType->OccurenceConfigParam'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8c155da3-72c6-40ee-bc33-a037078280e3")
    <T extends ModuleParameter> List<T> getOccurenceConfigParam(java.lang.Class<T> filterClass);
}

