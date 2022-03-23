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

package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.DiagramSet;

/**
 * AbstractProject v3.6.00
 * 
 * 
 * <p>An abstract project in Modelio corresponds to a composition tree root.&nbsp;<span style="line-height:1.6">&nbsp;A Project does not belong to any other element.&nbsp;</span><span style="line-height:1.6">There is at most one instance of each concreate sub metaclass in a model repository.&nbsp;</span></p>
 */
@objid ("cc0b1f9b-020c-4c62-b6b2-186cd42daa2b")
public interface AbstractProject extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("4cc33a11-c233-4dc1-8076-90d897d46f1c")
    public static final String MNAME = "AbstractProject";

    /**
     * The metaclass qualified name.
     */
    @objid ("a533a7cf-7b02-46c4-880d-0573d4e35d70")
    public static final String MQNAME = "Infrastructure.AbstractProject";

    /**
     * Getter for relation 'AbstractProject->DiagramRoot'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("870d3c07-b00d-4c38-ae76-79475024e525")
    DiagramSet getDiagramRoot();

    /**
     * Setter for relation 'AbstractProject->DiagramRoot'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a79de47d-acd7-4839-a3b9-230bcef4a0f4")
    void setDiagramRoot(DiagramSet value);

}
