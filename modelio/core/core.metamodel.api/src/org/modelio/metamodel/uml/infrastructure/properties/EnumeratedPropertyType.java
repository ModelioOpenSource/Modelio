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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("41c384a9-852c-455b-a935-af03e1056631")
    public static final String MNAME = "EnumeratedPropertyType";

    /**
     * The metaclass qualified name.
     */
    @objid ("7fc1ec97-ab2c-43b6-90c2-d89938187e32")
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
    @objid ("89fd1a16-ff09-4f51-b39b-791e77ba5a6a")
    EList<PropertyEnumerationLitteral> getLitteral();

    /**
     * Filtered Getter for relation 'EnumeratedPropertyType->Litteral'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("60556e0f-cf97-433d-bec8-62bef5639066")
    <T extends PropertyEnumerationLitteral> List<T> getLitteral(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'EnumeratedPropertyType->OccurenceConfigParam'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ab677af9-3932-4eb3-b6fb-a88d80a39059")
    EList<ModuleParameter> getOccurenceConfigParam();

    /**
     * Filtered Getter for relation 'EnumeratedPropertyType->OccurenceConfigParam'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("772ab581-d603-4708-8f6a-ef4617b0d128")
    <T extends ModuleParameter> List<T> getOccurenceConfigParam(java.lang.Class<T> filterClass);
}

