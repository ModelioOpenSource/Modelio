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
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ImpactModel v3.6.00
 * 
 * 
 * null
 */
@objid ("919b6a5f-21fd-4465-8fa1-ebcf7cfa168c")
public interface ImpactModel extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("8c07c483-990b-471d-b7bc-0ddc9bc76790")
    public static final String MNAME = "ImpactModel";

    /**
     * The metaclass qualified name.
     */
    @objid ("d115e891-75b4-431a-bc46-78203a02f317")
    public static final String MQNAME = "Infrastructure.ImpactModel";

    /**
     * Getter for relation 'ImpactModel->project'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c7992f4c-18db-4d06-ac25-54cce531cedc")
    ImpactProject getProject();

    /**
     * Setter for relation 'ImpactModel->project'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c78ecd56-e14b-40e1-b9bc-b2cb53618967")
    void setProject(ImpactProject value);

    /**
     * Getter for relation 'ImpactModel->ownedLinks'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3b7a28d1-99e4-49f4-acf8-ad4e2f2e7981")
    EList<ImpactLink> getOwnedLinks();

    /**
     * Filtered Getter for relation 'ImpactModel->ownedLinks'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("082be8f5-9cd9-4235-ac09-69046ace1a4e")
    <T extends ImpactLink> List<T> getOwnedLinks(java.lang.Class<T> filterClass);

}
