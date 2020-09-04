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
package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;

/**
 * Profile v3.6.00
 */
@objid ("008c74a4-c4be-1fd8-97fe-001ec947cd2a")
public interface Profile extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("be88fb1d-4586-493a-9274-03dff2b200fe")
    public static final String MNAME = "Profile";

    /**
     * The metaclass qualified name.
     */
    @objid ("2d2a5ef1-0acb-47dc-b6b6-a77733061546")
    public static final String MQNAME = "Infrastructure.Profile";

    /**
     * Getter for relation 'Profile->DefinedStereotype'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("581517ec-d7e2-4779-8abf-61cc40014742")
    EList<Stereotype> getDefinedStereotype();

    /**
     * Filtered Getter for relation 'Profile->DefinedStereotype'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("8b5815f4-9428-4cff-8acf-f6c765d53a8e")
    <T extends Stereotype> List<T> getDefinedStereotype(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Profile->OwnedReference'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("aa355f2e-0162-4a76-94f7-10bd59415e1e")
    EList<MetaclassReference> getOwnedReference();

    /**
     * Filtered Getter for relation 'Profile->OwnedReference'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("3acc5596-b394-4b6f-8378-2e2b7d29f573")
    <T extends MetaclassReference> List<T> getOwnedReference(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Profile->OwnerModule'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("33d36c59-c244-4ee9-b768-89926033f8ea")
    ModuleComponent getOwnerModule();

    /**
     * Setter for relation 'Profile->OwnerModule'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("69175e95-bcf0-492b-afdf-3c4cc017d490")
    void setOwnerModule(ModuleComponent value);

    /**
     * Getter for relation 'Profile->DefinedType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("10b59cd5-6344-4dcf-847e-fcd187c07114")
    EList<PropertyType> getDefinedType();

    /**
     * Filtered Getter for relation 'Profile->DefinedType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("ee31bc52-973c-4588-bc37-28c24ec6e58c")
    <T extends PropertyType> List<T> getDefinedType(java.lang.Class<T> filterClass);

}
