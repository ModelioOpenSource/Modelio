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

package org.modelio.metamodel.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;

/**
 * ImpactProject v3.6.00
 * 
 * 
 * null
 * 
 * 
 */
@objid ("8093c6ad-6795-4b1f-aac0-9573aa279431")
public interface ImpactProject extends AbstractProject {
    /**
     * The metaclass simple name.
     */
    @objid ("59b55653-dd3a-44f9-870a-546e466fab28")
    public static final String MNAME = "ImpactProject";

    /**
     * The metaclass qualified name.
     */
    @objid ("3a7054a2-3f2a-4432-8d78-2d2ea85b3c1c")
    public static final String MQNAME = "Infrastructure.ImpactProject";

    /**
     * Getter for relation 'ImpactProject->model'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d49471df-6334-4c55-9071-d553d989c8b0")
    EList<ImpactModel> getModel();

    /**
     * Filtered Getter for relation 'ImpactProject->model'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3f54df91-1844-42b9-8cdc-0338852efdb9")
    <T extends ImpactModel> List<T> getModel(java.lang.Class<T> filterClass);
}

