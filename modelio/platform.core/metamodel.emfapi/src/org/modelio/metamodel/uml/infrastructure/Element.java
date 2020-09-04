/* 
 * Copyright 2013-2019 Modeliosoft
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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Element v0.0.9054
 * 
 * 
 * <p>Element de mod&eacute;lisation.</p><p>Element repr&eacute;sente tous les &eacute;l&eacute;ments s&eacute;mantiques d&#39;une mod&eacute;lisation, au niveau le plus abstrait. Les Element sont structur&eacute;s par projet, qui constitue pour eux un espace clos de d&eacute;finition.</p><p>Son utilisation est surtout apparente lors de la r&eacute;daction de r&egrave;gles H, pour procurer des r&egrave;gles g&eacute;n&eacute;rales.</p><p>V&eacute;rifie si l&#39;&eacute;l&eacute;ment est bien rattach&eacute; &agrave; son composant.</p>
 */
@objid ("00867d60-c4be-1fd8-97fe-001ec947cd2a")
public interface Element extends EObject, MObject {
    /**
     * The metaclass simple name.
     */
    @objid ("7233ed55-3bc2-4794-a881-58e6aa4ca091")
    public static final String MNAME = "Element";

    /**
     * The metaclass qualified name.
     */
    @objid ("52b2af4f-0c62-4ae5-a782-615160f65bc1")
    public static final String MQNAME = "Infrastructure.Element";

    /**
     * Getter for relation 'Element->DiagramElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f172fb8b-0de2-4060-aa50-6a80ea0b0fee")
    EList<AbstractDiagram> getDiagramElement();

    /**
     * Filtered Getter for relation 'Element->DiagramElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ea8db136-0314-417b-a1b4-0c8df0e06029")
    <T extends AbstractDiagram> List<T> getDiagramElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Element->AddedToQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a528d3d8-3134-49b7-a31f-ac514bcccb53")
    EList<QueryDefinition> getAddedToQuery();

    /**
     * Filtered Getter for relation 'Element->AddedToQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b8dc9001-b60f-47e9-9ab6-4605ef580815")
    <T extends QueryDefinition> List<T> getAddedToQuery(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Element->causedImpact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b679a7d4-7fe8-432c-9804-84f8a6b6565d")
    EList<ImpactLink> getCausedImpact();

    /**
     * Filtered Getter for relation 'Element->causedImpact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("146467da-dca9-46fc-a67a-1e80090bebf0")
    <T extends ImpactLink> List<T> getCausedImpact(java.lang.Class<T> filterClass);

}
