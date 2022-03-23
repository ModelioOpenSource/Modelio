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

package org.modelio.metamodel.uml.behavior.usecaseModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.VisibilityMode;

/**
 * ExtensionPoint v0.0.9054
 * 
 * 
 * An extension point identifies a point in the behavior of a use case where that behavior can be extended by the behavior of some other (extending) use case, as specified by an extend relationship.
 * 
 * An ExtensionPoint is a feature of a use case that identifies a point where the behavior of a use case can be augmented with elements of another (extending) use case. 
 */
@objid ("00598058-c4bf-1fd8-97fe-001ec947cd2a")
public interface ExtensionPoint extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("0c53c584-461e-4bb1-a30e-e8bf97f0fadd")
    public static final String MNAME = "ExtensionPoint";

    /**
     * The metaclass qualified name.
     */
    @objid ("50c96651-63e2-4c5d-b6c1-2a657f32f169")
    public static final String MQNAME = "Standard.ExtensionPoint";

    /**
     * Getter for attribute 'ExtensionPoint.Visibility'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("ccf36050-2ea2-4fd8-8e51-85f7d206e349")
    VisibilityMode getVisibility();

    /**
     * Setter for attribute 'ExtensionPoint.Visibility'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("6236174c-9c10-45c0-a047-8f3d74c229cd")
    void setVisibility(VisibilityMode value);

    /**
     * Getter for relation 'ExtensionPoint->Extended'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("73d347a3-9fec-4fe0-a62c-46f285a00a38")
    EList<UseCaseDependency> getExtended();

    /**
     * Filtered Getter for relation 'ExtensionPoint->Extended'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c490ca52-4fb9-4fdb-9cd8-3f543b30d760")
    <T extends UseCaseDependency> List<T> getExtended(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ExtensionPoint->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("13352a44-d6df-498f-a338-2da62eca5735")
    UseCase getOwner();

    /**
     * Setter for relation 'ExtensionPoint->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("baac16d9-8aad-41c9-926c-0e899abbd362")
    void setOwner(UseCase value);

}
