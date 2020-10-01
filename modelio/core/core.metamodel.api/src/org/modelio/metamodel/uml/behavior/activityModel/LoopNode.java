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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * LoopNode v0.0.9054
 * 
 * 
 * First the setup section of the loop node is executed.
 * 
 * When the setup section has completed execution (if the test comes first) or when the body section has completed execution of an iteration, the test section is executed.
 * 
 * When the test section has completed execution, the Boolean value on the designated test expression is examined. If the value is true, the body section is executed again. If the value is false, execution of the loop node is complete.
 * 
 * Modelio specific : 
 * In order to make the Setup and Test sections easier to fill, these are string expressions in Modelio, instead of a set of activity nodes in the OMG UML specification.
 */
@objid ("00372b20-c4bf-1fd8-97fe-001ec947cd2a")
public interface LoopNode extends StructuredActivityNode {
    /**
     * The metaclass simple name.
     */
    @objid ("35f62148-b6a5-4a11-8c7c-29f72d4be54a")
    public static final String MNAME = "LoopNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("34dcff3f-bca3-4e26-a3c0-3fb5bd27b676")
    public static final String MQNAME = "Standard.LoopNode";

    /**
     * Getter for attribute 'LoopNode.IsTestedFirst'
     * 
     * Metamodel description:
     * <i>If true, the test is performed before the first execution of the body. If false, the body is executed once before the test is performed. The default value is false. </i>
     */
    @objid ("d657cfc0-dfc8-4625-b5f6-299a9f2f965f")
    boolean isIsTestedFirst();

    /**
     * Setter for attribute 'LoopNode.IsTestedFirst'
     * 
     * Metamodel description:
     * <i>If true, the test is performed before the first execution of the body. If false, the body is executed once before the test is performed. The default value is false. </i>
     */
    @objid ("f1671d6f-612a-44ab-920d-e180adb3cd02")
    void setIsTestedFirst(boolean value);

    /**
     * Getter for attribute 'LoopNode.Setup'
     * 
     * Metamodel description:
     * <i>Section that initialize values or perform other setup computations for the loop.</i>
     */
    @objid ("570c8038-8ff8-48df-b117-75e42477d819")
    String getSetup();

    /**
     * Setter for attribute 'LoopNode.Setup'
     * 
     * Metamodel description:
     * <i>Section that initialize values or perform other setup computations for the loop.</i>
     */
    @objid ("3429c38f-dcaa-4d70-b045-7131ebd02a13")
    void setSetup(String value);

    /**
     * Getter for attribute 'LoopNode.Test'
     * 
     * Metamodel description:
     * <i>Expression that computes a Boolean value to determine if another execution of the body will be performed.</i>
     */
    @objid ("a6cecf6d-7e14-441f-b1e3-56ad9bd3e49b")
    String getTest();

    /**
     * Setter for attribute 'LoopNode.Test'
     * 
     * Metamodel description:
     * <i>Expression that computes a Boolean value to determine if another execution of the body will be performed.</i>
     */
    @objid ("0a8260af-94da-4423-bd09-3b68857e71ef")
    void setTest(String value);

}
