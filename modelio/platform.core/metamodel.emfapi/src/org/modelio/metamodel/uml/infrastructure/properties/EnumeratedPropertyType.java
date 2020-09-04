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
package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;

/**
 * EnumeratedPropertyType v0.0.9054
 * 
 * 
 * Enumerated property type. Owns the literals that are allowed as property values.
 */
@objid ("00706656-ec87-1098-b22e-001ec947cd2a")
public interface EnumeratedPropertyType extends PropertyType {
    /**
     * The metaclass simple name.
     */
    @objid ("885f8cd3-5492-450e-b0af-d50f80676271")
    public static final String MNAME = "EnumeratedPropertyType";

    /**
     * The metaclass qualified name.
     */
    @objid ("e069f066-a735-4790-aa54-b4c46ce5d2ef")
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
     */
    @objid ("44b992ad-59f2-4cf0-861f-d05dd82bc623")
    EList<PropertyEnumerationLitteral> getLitteral();

    /**
     * Filtered Getter for relation 'EnumeratedPropertyType->Litteral'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("337fe983-cdfa-4f89-be4e-89841fddbb5a")
    <T extends PropertyEnumerationLitteral> List<T> getLitteral(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'EnumeratedPropertyType->OccurenceConfigParam'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2834a228-d0d2-4918-9a89-64c5b225e105")
    EList<ModuleParameter> getOccurenceConfigParam();

    /**
     * Filtered Getter for relation 'EnumeratedPropertyType->OccurenceConfigParam'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("21a2ec7f-708e-4f77-ad42-3b4a761ee065")
    <T extends ModuleParameter> List<T> getOccurenceConfigParam(java.lang.Class<T> filterClass);

}
