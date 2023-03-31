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

package org.modelio.metamodel.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * DiagramSet v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("006e175c-c4bf-1fd8-97fe-001ec947cd2a")
public interface DiagramSet extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e4989381-3743-48f7-812c-6f2fd6b51dfc")
    public static final String MNAME = "DiagramSet";

    /**
     * The metaclass qualified name.
     */
    @objid ("4be710ea-dae4-4b20-9041-009721928225")
    public static final String MQNAME = "Infrastructure.DiagramSet";

    /**
     * Getter for relation 'DiagramSet->Sub'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("761229bd-7312-44d7-aee4-83a5a68797d4")
    EList<DiagramSet> getSub();

    /**
     * Filtered Getter for relation 'DiagramSet->Sub'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("62c8187a-3506-4719-bac3-c974e46df613")
    <T extends DiagramSet> List<T> getSub(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'DiagramSet->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("27604e88-764f-47d3-8c8e-abbbb05301b9")
    DiagramSet getParent();

    /**
     * Setter for relation 'DiagramSet->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("4e11123c-896c-4874-a1da-a8fb4606960c")
    void setParent(DiagramSet value);

    /**
     * Getter for relation 'DiagramSet->ReferencedDiagram'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a92db1e5-1b2d-4e4b-b28e-6f32ddd682bc")
    EList<AbstractDiagram> getReferencedDiagram();

    /**
     * Filtered Getter for relation 'DiagramSet->ReferencedDiagram'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("4161ef4e-20a0-4aaa-b11c-1025489f575a")
    <T extends AbstractDiagram> List<T> getReferencedDiagram(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'DiagramSet->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("49e5fc7b-9d0b-41f4-b179-2b31951b8233")
    AbstractProject getOwner();

    /**
     * Setter for relation 'DiagramSet->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8586acbd-d937-4641-a86a-9f41271254ee")
    void setOwner(AbstractProject value);
}

