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
package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;

/**
 * ModelTree v0.0.9054
 * 
 * 
 * The ElementOwnerShip association provides a hierarchy of ModelElements that can be managed by the model explorer or by the teamwork facility. 
 * 
 * This metaclass is not part of the UML standard. NameSpace is a typical subclass, which takes advantage of the containment facility provided by ModelTree.
 */
@objid ("00891e1c-c4be-1fd8-97fe-001ec947cd2a")
public interface ModelTree extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("643eeccb-ef08-4ca1-b96e-95777c0059c5")
    public static final String MNAME = "ModelTree";

    /**
     * The metaclass qualified name.
     */
    @objid ("8730a2de-135e-4d1a-8c83-7004895cf555")
    public static final String MQNAME = "Standard.ModelTree";

    /**
     * Getter for relation 'ModelTree->Owner'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("9f71dd66-4735-444f-9e20-4d16dee62492")
    ModelTree getOwner();

    /**
     * Setter for relation 'ModelTree->Owner'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("49056ebd-3ddf-4932-ad50-731bd4e1ef9f")
    void setOwner(ModelTree value);

    /**
     * Getter for relation 'ModelTree->OwnedElement'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("55fe510b-0fcb-4f77-af97-515275b5bda5")
    EList<ModelTree> getOwnedElement();

    /**
     * Filtered Getter for relation 'ModelTree->OwnedElement'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("940c3fde-644e-483c-b9fd-43bb3e3cd4aa")
    <T extends ModelTree> List<T> getOwnedElement(java.lang.Class<T> filterClass);

}
