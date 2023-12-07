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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.DiagramSet;

/**
 * AbstractProject v3.6.00
 * 
 * 
 * <p>An abstract project in Modelio corresponds to a composition tree root.&nbsp;<span style="line-height:1.6">&nbsp;A Project does not belong to any other element.&nbsp;</span><span style="line-height:1.6">There is at most one instance of each concreate sub metaclass in a model repository.&nbsp;</span></p>
 * 
 * 
 * 
 */
@objid ("cc0b1f9b-020c-4c62-b6b2-186cd42daa2b")
public interface AbstractProject extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("070f8fc1-00ca-4c27-8896-8fe93cd505f5")
    public static final String MNAME = "AbstractProject";

    /**
     * The metaclass qualified name.
     */
    @objid ("33b57573-0dce-4eb2-aa0c-af148b3ed1de")
    public static final String MQNAME = "Infrastructure.AbstractProject";

    /**
     * Getter for relation 'AbstractProject->DiagramRoot'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2b51c0d0-21d8-4cdc-937b-efe155366812")
    DiagramSet getDiagramRoot();

    /**
     * Setter for relation 'AbstractProject->DiagramRoot'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("604e9415-7845-4c3e-9f9b-cac37fc98374")
    void setDiagramRoot(DiagramSet value);
}

