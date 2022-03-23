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

package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;

/**
 * Node v0.0.9054
 * 
 * 
 * In the metamodel, a Node is a subclass of Classifier.  
 * 
 * Nodes may have an internal structure defined in terms of the Parts and Connectors associated with them, which is used to model the deployment of Artifact on Nodes, and also the imbrications of Nodes.  
 * 
 * In Modelio, Nodes belong to their owner NameSpace, which can be a Package, a Class or a Node.
 */
@objid ("0012fb88-c4bf-1fd8-97fe-001ec947cd2a")
public interface Node extends Classifier {
    /**
     * The metaclass simple name.
     */
    @objid ("c75fcc5e-70d7-4b0d-a6df-94dfede42cd3")
    public static final String MNAME = "Node";

    /**
     * The metaclass qualified name.
     */
    @objid ("a04ec00b-6dac-4b7d-81ac-0d72ff816aa4")
    public static final String MQNAME = "Standard.Node";

    /**
     * Getter for relation 'Node->Resident'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1058f12e-96b3-47de-8d5a-c811fa3dcc47")
    EList<Artifact> getResident();

    /**
     * Filtered Getter for relation 'Node->Resident'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7717620d-a8d1-4448-833a-8c90cdbedd43")
    <T extends Artifact> List<T> getResident(java.lang.Class<T> filterClass);

}
