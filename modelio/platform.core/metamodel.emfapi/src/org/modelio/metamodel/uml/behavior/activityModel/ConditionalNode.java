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
package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;

/**
 * ConditionalNode v0.0.9054
 * 
 * 
 * A conditional node consists of one or more clauses. Each clause consists of a test section and a body section. When the conditional node begins execution, the test sections of the clauses are executed. If one or more test sections yield a true value, one of the corresponding body sections will be executed. If more than one test section yields a true value, only one body section will be executed. If no test section yields a true value, then no body section is executed; this may be a semantic error if output values are expected from the conditional node.
 * 
 * An "else" clause is a clause that is a successor to all other clauses in the conditional and whose test part always returns true.
 */
@objid ("002d52f8-c4bf-1fd8-97fe-001ec947cd2a")
public interface ConditionalNode extends StructuredActivityNode {
    /**
     * The metaclass simple name.
     */
    @objid ("a90e006c-e3c5-4850-95fd-51e84fb98d68")
    public static final String MNAME = "ConditionalNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("8682eb21-7c90-4c28-a1be-91c1af34bb6a")
    public static final String MQNAME = "Standard.ConditionalNode";

    /**
     * Getter for attribute 'ConditionalNode.IsDeterminate'
     * 
     * Metamodel description:
     * <i>If true, the modeler asserts that at most one test will succeed. The default value is false. </i>
     */
    @objid ("defca8bb-48fc-4231-a8b0-fe79de6ea3d9")
    boolean isIsDeterminate();

    /**
     * Setter for attribute 'ConditionalNode.IsDeterminate'
     * 
     * Metamodel description:
     * <i>If true, the modeler asserts that at most one test will succeed. The default value is false. </i>
     */
    @objid ("fcf439a2-c1bc-466e-8ed3-92d1343928c6")
    void setIsDeterminate(boolean value);

    /**
     * Getter for attribute 'ConditionalNode.IsAssured'
     * 
     * Metamodel description:
     * <i>If true, the modeler asserts that at least one test will succeed. The default value is false.</i>
     */
    @objid ("79c0023b-67b3-41ed-a135-3b35d348d4ee")
    boolean isIsAssured();

    /**
     * Setter for attribute 'ConditionalNode.IsAssured'
     * 
     * Metamodel description:
     * <i>If true, the modeler asserts that at least one test will succeed. The default value is false.</i>
     */
    @objid ("25cf970a-1e40-4181-af6c-c4f1ab352e7f")
    void setIsAssured(boolean value);

    /**
     * Getter for relation 'ConditionalNode->OwnedClause'
     * 
     * Metamodel description:
     * <i>Set of clauses composing the conditional.</i>
     */
    @objid ("3de557fa-4550-425e-a78a-c99196d62296")
    EList<Clause> getOwnedClause();

    /**
     * Filtered Getter for relation 'ConditionalNode->OwnedClause'
     * 
     * Metamodel description:
     * <i>Set of clauses composing the conditional.</i>
     */
    @objid ("aae00f2c-bd57-441a-8fea-f9ceb955a69e")
    <T extends Clause> List<T> getOwnedClause(java.lang.Class<T> filterClass);

}
