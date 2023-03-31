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

package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * Clause v0.0.9054
 * 
 * 
 * A clause is an element that represents a single branch of a conditional construct, including a test and a body section. The body section is executed only (but not necessarily) if the test section is true.
 * 
 * 
 */
@objid ("002cb618-c4bf-1fd8-97fe-001ec947cd2a")
public interface Clause extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("599983a5-db60-4d11-a15a-a2494db54f8f")
    public static final String MNAME = "Clause";

    /**
     * The metaclass qualified name.
     */
    @objid ("b86ca03c-8a79-4952-80b1-693bb075b56f")
    public static final String MQNAME = "Standard.Clause";

    /**
     * Getter for attribute 'Clause.Test'
     * 
     * Metamodel description:
     * <i>Specifies the result of the test.</i>
     * 
     */
    @objid ("7ab2268a-3aca-419a-8ad3-81e78dff134f")
    String getTest();

    /**
     * Setter for attribute 'Clause.Test'
     * 
     * Metamodel description:
     * <i>Specifies the result of the test.</i>
     * 
     */
    @objid ("f275f8f2-a8e9-4202-b868-bf9a9bef154e")
    void setTest(String value);

    /**
     * Getter for relation 'Clause->Body'
     * 
     * Metamodel description:
     * <i>A nested activity fragment that is executed if the test is true and the clause is chosen over any concurrent clauses that are also true.</i>
     * 
     */
    @objid ("55188136-5c63-4c26-93c2-31311bf79bf8")
    EList<ActivityNode> getBody();

    /**
     * Filtered Getter for relation 'Clause->Body'
     * 
     * Metamodel description:
     * <i>A nested activity fragment that is executed if the test is true and the clause is chosen over any concurrent clauses that are also true.</i>
     * 
     */
    @objid ("6cb43116-2f47-4287-a8c2-fe1c8b007a69")
    <T extends ActivityNode> List<T> getBody(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Clause->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b7a03d1c-9348-44a1-813e-5edd42181163")
    ConditionalNode getOwner();

    /**
     * Setter for relation 'Clause->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0f636aaf-13ec-4932-9589-12d30322c0f0")
    void setOwner(ConditionalNode value);
}

