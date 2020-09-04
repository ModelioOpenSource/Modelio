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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
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
    @objid ("62a35749-094f-4100-8e1a-860cd6f78b71")
    public static final String MNAME = "ImpactProject";

    /**
     * The metaclass qualified name.
     */
    @objid ("48087c4c-b32f-43fc-9e27-eff54e45600f")
    public static final String MQNAME = "Infrastructure.ImpactProject";

    /**
     * Getter for relation 'ImpactProject->model'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5ad6e589-be57-4318-b585-282e605671c2")
    EList<ImpactModel> getModel();

    /**
     * Filtered Getter for relation 'ImpactProject->model'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ecddab09-2ddd-4ed7-8c92-93d355741e3f")
    <T extends ImpactModel> List<T> getModel(java.lang.Class<T> filterClass);

}
