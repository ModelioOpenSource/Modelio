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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * Constraint v0.0.9054
 * 
 * 
 * Constraints can express restrictions and relationships that cannot be expressed using UML notation. They are particularly useful for stating global conditions or conditions that affect a number of elements. 
 * 
 * Constraints can have predefined names, and can also represent pre-conditions, post-conditions and invariants (pre-defined stereotypes). 
 * 
 * The language specific MDACs (C++, Java) add a specific stereotype for the pre-conditions, post-conditions and invariants expressed in these languages, such as, for example, C++Invariant or JavaPreCondition. 
 * 
 * In Modelio, a Constraint is not made up of anything. It is only  managed by specific copy/transfer rules.
 */
@objid ("008538a6-c4be-1fd8-97fe-001ec947cd2a")
public interface Constraint extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e3b22a8c-f57c-4042-aa47-64648b27c63c")
    public static final String MNAME = "Constraint";

    /**
     * The metaclass qualified name.
     */
    @objid ("c1da5a7c-c31c-4628-b785-1953b82411b2")
    public static final String MQNAME = "Standard.Constraint";

    /**
     * Getter for attribute 'Constraint.BaseClass'
     * 
     * Metamodel description:
     * <i>MetaClass whose instances can be constrained by the current Constraint.</i>
     */
    @objid ("f5f6fdb9-1ced-48ee-a47c-b3d75da9f292")
    String getBaseClass();

    /**
     * Setter for attribute 'Constraint.BaseClass'
     * 
     * Metamodel description:
     * <i>MetaClass whose instances can be constrained by the current Constraint.</i>
     */
    @objid ("a7ea0e9f-241d-4f71-9069-f1b8c0b3b689")
    void setBaseClass(String value);

    /**
     * Getter for attribute 'Constraint.Body'
     * 
     * Metamodel description:
     * <i>If the Constraint is not predefined (for example, ordered), then it is expressed in the body. 
     * 
     * Modelio supports natural language. For every generator (C++, Java) some constraints have a dedicated stereotype (JavaPrecondition, C++Invariant), and are taken into account during code generation.</i>
     */
    @objid ("909b4851-8d7c-4995-b242-3a4e6cae45d7")
    String getBody();

    /**
     * Setter for attribute 'Constraint.Body'
     * 
     * Metamodel description:
     * <i>If the Constraint is not predefined (for example, ordered), then it is expressed in the body. 
     * 
     * Modelio supports natural language. For every generator (C++, Java) some constraints have a dedicated stereotype (JavaPrecondition, C++Invariant), and are taken into account during code generation.</i>
     */
    @objid ("77f36e9e-b305-4568-9afa-c169ce267407")
    void setBody(String value);

    /**
     * Getter for attribute 'Constraint.Language'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("de1ae66e-2756-497b-b858-f4bc041c1283")
    String getLanguage();

    /**
     * Setter for attribute 'Constraint.Language'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7550f938-f677-4670-8b8b-8ae708810407")
    void setLanguage(String value);

    /**
     * Getter for relation 'Constraint->ConstrainedElement'
     * 
     * Metamodel description:
     * <i>Defines which elements are concerned by the Constraint.</i>
     */
    @objid ("17710c27-bd22-4ebe-91b9-d7944dc53bc4")
    EList<UmlModelElement> getConstrainedElement();

    /**
     * Filtered Getter for relation 'Constraint->ConstrainedElement'
     * 
     * Metamodel description:
     * <i>Defines which elements are concerned by the Constraint.</i>
     */
    @objid ("6e26cb47-dce0-4f22-ad49-dc96ae016ec0")
    <T extends UmlModelElement> List<T> getConstrainedElement(java.lang.Class<T> filterClass);

}
