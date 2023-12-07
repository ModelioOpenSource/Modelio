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
 * 
 * 
 */
@objid ("1fd5ae12-7cf1-4193-bf37-5acd71f63010")
public interface MethodologicalLink extends Dependency {
    /**
     * The metaclass simple name.
     */
    @objid ("16bb785c-3e45-460d-9b0c-65fb74127285")
    public static final String MNAME = "MethodologicalLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("4380ee91-817a-40ee-bd2f-1463a8337ea0")
    public static final String MQNAME = "Infrastructure.MethodologicalLink";

    /**
     * Getter for relation 'MethodologicalLink->ExternElement'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b3223d52-3422-45d7-97c1-a5710796bea6")
    ExternElement getExternElement();

    /**
     * Setter for relation 'MethodologicalLink->ExternElement'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8b9e3882-c8b4-4cfa-8717-9401aad10d99")
    void setExternElement(ExternElement value);
}

