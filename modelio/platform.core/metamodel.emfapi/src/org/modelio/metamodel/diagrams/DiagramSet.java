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
package org.modelio.metamodel.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * DiagramSet v0.0.9054
 * 
 * 
 * null
 */
@objid ("006e175c-c4bf-1fd8-97fe-001ec947cd2a")
public interface DiagramSet extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("ace088d7-b9c9-4343-8e41-116f1d18449c")
    public static final String MNAME = "DiagramSet";

    /**
     * The metaclass qualified name.
     */
    @objid ("d2fc045e-5d16-41fa-b849-b2d4375c6355")
    public static final String MQNAME = "Infrastructure.DiagramSet";

    /**
     * Getter for relation 'DiagramSet->Sub'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("20d432d8-faf7-4d03-a406-cb0a6a93e420")
    EList<DiagramSet> getSub();

    /**
     * Filtered Getter for relation 'DiagramSet->Sub'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ab3aef82-6e5a-437c-b7f6-46ecd9820c62")
    <T extends DiagramSet> List<T> getSub(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'DiagramSet->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1dc8b8b5-5ae0-4e60-81c9-a664fbcb7b98")
    DiagramSet getParent();

    /**
     * Setter for relation 'DiagramSet->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("67d1d257-0f36-43c0-9129-41cd37178614")
    void setParent(DiagramSet value);

    /**
     * Getter for relation 'DiagramSet->ReferencedDiagram'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("61c61e35-4c8a-4a8b-8a84-7b4319e277d6")
    EList<AbstractDiagram> getReferencedDiagram();

    /**
     * Filtered Getter for relation 'DiagramSet->ReferencedDiagram'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4ed34ba1-857e-44be-8af4-f3dfc04533f9")
    <T extends AbstractDiagram> List<T> getReferencedDiagram(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'DiagramSet->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1cbe46ac-f548-44fd-92a4-67432886914f")
    AbstractProject getOwner();

    /**
     * Setter for relation 'DiagramSet->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6f8d9352-555d-4ee8-8f1b-2ae830353ddf")
    void setOwner(AbstractProject value);

}
