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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;

/**
 * ImpactProject v3.6.00
 * 
 * 
 * null
 */
@objid ("8093c6ad-6795-4b1f-aac0-9573aa279431")
public interface ImpactProject extends AbstractProject {
    /**
     * The metaclass simple name.
     */
    @objid ("37c01cbd-0311-49a7-9274-6995253ce7a3")
    public static final String MNAME = "ImpactProject";

    /**
     * The metaclass qualified name.
     */
    @objid ("4836b504-9a8a-44ea-8024-c835a26e29be")
    public static final String MQNAME = "Infrastructure.ImpactProject";

    /**
     * Getter for relation 'ImpactProject->model'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d33a0047-ef4f-4278-b528-43b2bccd8c68")
    EList<ImpactModel> getModel();

    /**
     * Filtered Getter for relation 'ImpactProject->model'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("597244dd-fbef-40ff-af0a-17085f198026")
    <T extends ImpactModel> List<T> getModel(java.lang.Class<T> filterClass);

}
