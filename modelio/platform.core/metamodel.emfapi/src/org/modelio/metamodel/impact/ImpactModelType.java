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
     Metamodel: Infrastructure, version 3.6.00, by Modeliosoft
     Generator version: 3.5.01
     Generated on: Jul 5, 2016
*/
package org.modelio.metamodel.impact;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ImpactModelType v3.6.00
 * 
 * 
 * null
 */
@objid ("0878bf96-f72d-473a-8194-fdb5ec3c50a3")
public interface ImpactModelType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("defcf01d-5726-4f0a-b632-c83e7fa26620")
    public static final String MNAME = "ImpactModelType";

    /**
     * The metaclass qualified name.
     */
    @objid ("c5f37d32-d95c-4f25-9807-daad9c1d30a6")
    public static final String MQNAME = "Infrastructure.ImpactModelType";

    /**
     * Getter for relation 'ImpactModelType->instance'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("89fe242a-0dbb-4ede-97b2-7853a41a88bf")
    ImpactModel getInstance();

    /**
     * Setter for relation 'ImpactModelType->instance'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("99680a90-9b8c-4069-bf89-403855af43f9")
    void setInstance(ImpactModel value);

    /**
     * Getter for relation 'ImpactModelType->project'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("344f3f1e-bcbb-43c4-aa8a-01eeed749983")
    ImpactProject getProject();

    /**
     * Setter for relation 'ImpactModelType->project'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("31e95cd6-d9a3-434e-8e75-1b0623e55d60")
    void setProject(ImpactProject value);

}
