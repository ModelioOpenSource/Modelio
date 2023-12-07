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

package org.modelio.metamodel.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * AbstractDiagram v0.0.9055
 * 
 * 
 * <p>Graphical representation of a model.</p><p>A diagram is a attached to a ModelElement, and contains a serialized version of the graphical model than can&#39;t be manipulated as-is.</p><p>Elements can be represented in several diagrams at the same time, each diagram type having its own strategy about handling them.</p>
 * 
 * 
 * 
 */
@objid ("006721c2-c4bf-1fd8-97fe-001ec947cd2a")
public interface AbstractDiagram extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("8771665d-5dd5-49dd-b57c-847023e1e3cf")
    public static final String MNAME = "AbstractDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("7e9941eb-87e7-4a2f-8fb8-55371bf3a375")
    public static final String MQNAME = "Infrastructure.AbstractDiagram";

    /**
     * Getter for attribute 'AbstractDiagram.UiDataVersion'
     * 
     * Metamodel description:
     * <i>Serialized contents version.</i>
     * 
     */
    @objid ("5226148f-0c4d-4a9d-9c1f-158b25b1895f")
    int getUiDataVersion();

    /**
     * Setter for attribute 'AbstractDiagram.UiDataVersion'
     * 
     * Metamodel description:
     * <i>Serialized contents version.</i>
     * 
     */
    @objid ("67b2253f-0ef2-4c28-90dd-a781c017147b")
    void setUiDataVersion(int value);

    /**
     * Getter for attribute 'AbstractDiagram.UiData'
     * 
     * Metamodel description:
     * <i><p>Serialized diagram content.</p>
     * </i>
     * 
     */
    @objid ("8f675b58-df22-4e6d-bb6e-f765a72c57ad")
    String getUiData();

    /**
     * Setter for attribute 'AbstractDiagram.UiData'
     * 
     * Metamodel description:
     * <i><p>Serialized diagram content.</p>
     * </i>
     * 
     */
    @objid ("dc799fb9-4952-4760-8463-e8a35f5da204")
    void setUiData(String value);

    /**
     * Getter for attribute 'AbstractDiagram.PreviewData'
     * 
     * Metamodel description:
     * <i><p>HTML preview data enforcing the Data URI scheme: https://en.wikipedia.org/wiki/Data_URI_scheme.</p>
     * </i>
     * 
     */
    @objid ("48e02cc9-7b5b-4fc7-b024-7cc59f230332")
    String getPreviewData();

    /**
     * Setter for attribute 'AbstractDiagram.PreviewData'
     * 
     * Metamodel description:
     * <i><p>HTML preview data enforcing the Data URI scheme: https://en.wikipedia.org/wiki/Data_URI_scheme.</p>
     * </i>
     * 
     */
    @objid ("3d0dfc02-fc00-45a1-b156-870e18ed8156")
    void setPreviewData(String value);

    /**
     * Getter for attribute 'AbstractDiagram.JsStructure'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("77781003-b617-46c1-9a4e-779a99918bc6")
    String getJsStructure();

    /**
     * Setter for attribute 'AbstractDiagram.JsStructure'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8bba8147-e846-4d7c-b553-a8901582324f")
    void setJsStructure(String value);

    /**
     * Getter for relation 'AbstractDiagram->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f4c22367-c6b2-43f5-bd2f-be7a38abe5f3")
    EList<Element> getRepresented();

    /**
     * Filtered Getter for relation 'AbstractDiagram->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2be9b7f4-e8fe-4851-8d7b-fb6e58e4a6c9")
    <T extends Element> List<T> getRepresented(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AbstractDiagram->ReferencingSet'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b4dab28f-67df-4e97-a311-9ed2cfbb455d")
    EList<DiagramSet> getReferencingSet();

    /**
     * Filtered Getter for relation 'AbstractDiagram->ReferencingSet'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("bd8f5080-f7ce-443c-8b4a-01ef57edcff2")
    <T extends DiagramSet> List<T> getReferencingSet(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AbstractDiagram->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("00c0ca0e-2929-4413-8eaa-a2b0df116dcf")
    ModelElement getOrigin();

    /**
     * Setter for relation 'AbstractDiagram->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ac782d6d-8c44-489d-bedd-dd4f601551df")
    void setOrigin(ModelElement value);
}

