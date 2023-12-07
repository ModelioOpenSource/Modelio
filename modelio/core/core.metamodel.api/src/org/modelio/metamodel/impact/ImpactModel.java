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
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ImpactModel v3.6.00
 * 
 * 
 * null
 * 
 * 
 */
@objid ("919b6a5f-21fd-4465-8fa1-ebcf7cfa168c")
public interface ImpactModel extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("2977c40a-5744-45ae-9790-25958e684726")
    public static final String MNAME = "ImpactModel";

    /**
     * The metaclass qualified name.
     */
    @objid ("6072d856-a794-45b8-80a1-4ea63d785302")
    public static final String MQNAME = "Infrastructure.ImpactModel";

    /**
     * Getter for relation 'ImpactModel->project'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("077f4fab-b704-4c0f-9c4a-d56e3722097e")
    ImpactProject getProject();

    /**
     * Setter for relation 'ImpactModel->project'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("05ed2f89-cac4-48fe-9f19-6c4f366fd3dd")
    void setProject(ImpactProject value);

    /**
     * Getter for relation 'ImpactModel->ownedLinks'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("44d60220-5218-441a-932b-c4b584a01e32")
    EList<ImpactLink> getOwnedLinks();

    /**
     * Filtered Getter for relation 'ImpactModel->ownedLinks'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("22a3832b-622d-4d38-a643-1e7527ca150d")
    <T extends ImpactLink> List<T> getOwnedLinks(java.lang.Class<T> filterClass);
}

