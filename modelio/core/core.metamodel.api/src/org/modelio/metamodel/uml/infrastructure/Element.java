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
 * 
 * 
 * 
 */
@objid ("00867d60-c4be-1fd8-97fe-001ec947cd2a")
public interface Element extends EObject, MObject {
    /**
     * The metaclass simple name.
     */
    @objid ("e3842f20-708b-4c43-b8f0-b9e08af7edcc")
    public static final String MNAME = "Element";

    /**
     * The metaclass qualified name.
     */
    @objid ("93b4b01b-54d0-4235-be14-46c2bc813eaa")
    public static final String MQNAME = "Infrastructure.Element";

    /**
     * Getter for relation 'Element->DiagramElement'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("cfe28a85-703e-4fcb-be4a-d28933b4676a")
    EList<AbstractDiagram> getDiagramElement();

    /**
     * Filtered Getter for relation 'Element->DiagramElement'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3230d9bb-34b1-43d0-96a8-4611ab18fe98")
    <T extends AbstractDiagram> List<T> getDiagramElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Element->AddedToQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a9618eb9-790e-4251-95cc-04e9e8d7dd0c")
    EList<QueryDefinition> getAddedToQuery();

    /**
     * Filtered Getter for relation 'Element->AddedToQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e0520ed2-6c9f-4fe5-a356-8dab6419aa62")
    <T extends QueryDefinition> List<T> getAddedToQuery(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Element->causedImpact'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5e4580e7-d3a5-4c7f-9ecf-0bfd72b1c888")
    EList<ImpactLink> getCausedImpact();

    /**
     * Filtered Getter for relation 'Element->causedImpact'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b2c858eb-6212-44f5-a68f-ee48fcf09c2b")
    <T extends ImpactLink> List<T> getCausedImpact(java.lang.Class<T> filterClass);
}

