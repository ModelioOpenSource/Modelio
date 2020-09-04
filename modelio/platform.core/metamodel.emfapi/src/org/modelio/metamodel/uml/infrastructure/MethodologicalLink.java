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
package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

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
    @objid ("18cd693a-727f-466d-aef5-afb53025a7c9")
    public static final String MNAME = "MethodologicalLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("8cb6537a-66e6-4550-b267-e5834ab4c191")
    public static final String MQNAME = "Infrastructure.MethodologicalLink";

}
