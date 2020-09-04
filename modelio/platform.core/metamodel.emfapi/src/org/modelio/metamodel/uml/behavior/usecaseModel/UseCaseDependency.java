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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.usecaseModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * UseCaseDependency v0.0.9054
 * 
 * 
 * In Modelio, this specific metaclass has been created for the definition of these links. 
 * 
 * Two predefined Stereotypes are defined for this link: <extends>> and <<includes>>. 
 * 
 * UseCaseDependencies belong to their origin UseCase.
 */
@objid ("0058fd18-c4bf-1fd8-97fe-001ec947cd2a")
public interface UseCaseDependency extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("a5845790-0081-432e-a841-6913b94797a4")
    public static final String MNAME = "UseCaseDependency";

    /**
     * The metaclass qualified name.
     */
    @objid ("319bc53b-5157-451d-b534-93abb2a27bcf")
    public static final String MQNAME = "Standard.UseCaseDependency";

    /**
     * Getter for relation 'UseCaseDependency->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9f2fdcbe-c7ff-46c8-937b-981334ea199a")
    UseCase getOrigin();

    /**
     * Setter for relation 'UseCaseDependency->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("92a322f2-75ae-46f0-9573-ce773223ac78")
    void setOrigin(UseCase value);

    /**
     * Getter for relation 'UseCaseDependency->ExtensionLocation'
     * 
     * Metamodel description:
     * <i>An ordered list of extension points belonging to the extended use case, specifying where the respective behavioral fragments of the extending use case are to be inserted. The first fragment in the extending use case is associated with the first extension point in the list, the second fragment with the second point, and so on. (Note that, in most practical cases, the extending use case has just a single behavior fragment, so that the list of extension points is trivial.)</i>
     */
    @objid ("bb4fd0f9-90db-4b6e-9523-8cd2a35fea0c")
    EList<ExtensionPoint> getExtensionLocation();

    /**
     * Filtered Getter for relation 'UseCaseDependency->ExtensionLocation'
     * 
     * Metamodel description:
     * <i>An ordered list of extension points belonging to the extended use case, specifying where the respective behavioral fragments of the extending use case are to be inserted. The first fragment in the extending use case is associated with the first extension point in the list, the second fragment with the second point, and so on. (Note that, in most practical cases, the extending use case has just a single behavior fragment, so that the list of extension points is trivial.)</i>
     */
    @objid ("80208307-e679-45d7-af8d-41d2ec0961e2")
    <T extends ExtensionPoint> List<T> getExtensionLocation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UseCaseDependency->Target'
     * 
     * Metamodel description:
     * <i>In a dependency between UseCases, this defines the link to the target UseCase.</i>
     */
    @objid ("a07b3c1d-4102-46fa-89df-a9bd36e9dcf8")
    UseCase getTarget();

    /**
     * Setter for relation 'UseCaseDependency->Target'
     * 
     * Metamodel description:
     * <i>In a dependency between UseCases, this defines the link to the target UseCase.</i>
     */
    @objid ("ee66410c-36d1-4c6a-890c-d223e23613e2")
    void setTarget(UseCase value);

}
