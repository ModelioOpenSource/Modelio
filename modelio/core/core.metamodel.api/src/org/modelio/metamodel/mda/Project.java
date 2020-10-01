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
package org.modelio.metamodel.mda;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.statik.Package;

/**
 * Project v1.1.1
 * 
 * 
 * <p>A project in Modelio corresponds to the UML Model concept. A Project in Modelio has a root Package, and in addition a specific configuration, detailing deployed MDACs, MDAC parameter values, and so on. A Project does not belong to any other element.</p>
 */
@objid ("006602e2-c4bf-1fd8-97fe-001ec947cd2a")
public interface Project extends AbstractProject {
    /**
     * The metaclass simple name.
     */
    @objid ("201d6b85-cf8c-4782-9654-fbea4a68634f")
    public static final String MNAME = "Project";

    /**
     * The metaclass qualified name.
     */
    @objid ("331a669b-0737-486e-be05-9213524a6f3c")
    public static final String MQNAME = "Standard.Project";

    /**
     * Getter for attribute 'Project.ProjectContext'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1208ada5-ab49-44d9-b02e-698157262680")
    String getProjectContext();

    /**
     * Setter for attribute 'Project.ProjectContext'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e1e38295-1287-484a-a728-e390c203ce93")
    void setProjectContext(String value);

    /**
     * Getter for attribute 'Project.ProjectDescr'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("84ab8a86-f6dc-4d68-9971-8f14a58d8152")
    String getProjectDescr();

    /**
     * Setter for attribute 'Project.ProjectDescr'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a4eac5b4-1017-4efa-ad5f-a391656f4abc")
    void setProjectDescr(String value);

    /**
     * Getter for relation 'Project->Model'
     * 
     * Metamodel description:
     * <i>Defines the Package associated to the Project (equivalent to the UML Model notion) that is the root of the Project's Package organization.</i>
     */
    @objid ("5e9b679d-6c96-4878-9ce4-1cf1541dfee2")
    EList<Package> getModel();

    /**
     * Filtered Getter for relation 'Project->Model'
     * 
     * Metamodel description:
     * <i>Defines the Package associated to the Project (equivalent to the UML Model notion) that is the root of the Project's Package organization.</i>
     */
    @objid ("f622fd88-5658-4e20-8330-295f30f88d8e")
    <T extends Package> List<T> getModel(java.lang.Class<T> filterClass);

}
