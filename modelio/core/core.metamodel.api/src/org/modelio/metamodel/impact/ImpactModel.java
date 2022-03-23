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
 */
@objid ("919b6a5f-21fd-4465-8fa1-ebcf7cfa168c")
public interface ImpactModel extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("c1dbdc80-5277-4196-a32e-c44c12e7abab")
    public static final String MNAME = "ImpactModel";

    /**
     * The metaclass qualified name.
     */
    @objid ("70e0cad6-e4a4-4cf8-87ef-fdd124801e04")
    public static final String MQNAME = "Infrastructure.ImpactModel";

    /**
     * Getter for relation 'ImpactModel->project'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ae44446a-b0a7-41d0-b43b-f28fe5c4d5d9")
    ImpactProject getProject();

    /**
     * Setter for relation 'ImpactModel->project'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("320b19c0-b20f-4cad-bcbe-37387bc491b0")
    void setProject(ImpactProject value);

    /**
     * Getter for relation 'ImpactModel->ownedLinks'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9fae87fc-db51-49e4-ba35-f755cce22775")
    EList<ImpactLink> getOwnedLinks();

    /**
     * Filtered Getter for relation 'ImpactModel->ownedLinks'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9a8bb454-3077-41a9-a042-e6901ef331af")
    <T extends ImpactLink> List<T> getOwnedLinks(java.lang.Class<T> filterClass);

}
