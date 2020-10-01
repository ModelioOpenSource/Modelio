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
import org.modelio.metamodel.uml.infrastructure.ExternElement;

/**
 * MethodologicalLink v2.1.01
 * 
 * 
 * A methodological link allows drawing semantic relationships between elements originating from the same or different metamodels.
 * 
 * They allow building up methodologies such as progressive model refinements, inter models traceability, impact analysis, dependency graphes...
 * 
 * The semantic of a methodological link is defined by its stereotype. A MethodLink without stereotype has no meaning and is not allowed by Modelio.
 * 
 * The module bringing the stereotype defines:
 * - the semantic of the methodological link
 * - the allowed sources and targets of the link
 */
@objid ("1fd5ae12-7cf1-4193-bf37-5acd71f63010")
public interface MethodologicalLink extends Dependency {
    /**
     * The metaclass simple name.
     */
    @objid ("c429c37b-13fc-4e98-91e3-c376cf341794")
    public static final String MNAME = "MethodologicalLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("47907c21-9431-4db5-8632-7051367c092e")
    public static final String MQNAME = "Infrastructure.MethodologicalLink";

    /**
     * Getter for relation 'MethodologicalLink->ExternElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f48cdf02-014d-43c6-9907-a5333e7e0888")
    ExternElement getExternElement();

    /**
     * Setter for relation 'MethodologicalLink->ExternElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5e846696-595e-4299-8864-81652a7619b8")
    void setExternElement(ExternElement value);

}
