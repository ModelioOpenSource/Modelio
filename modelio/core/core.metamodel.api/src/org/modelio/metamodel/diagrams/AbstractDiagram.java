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
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * AbstractDiagram v0.0.9054
 * 
 * 
 * <p>Graphical representation of a model.</p><p>A diagram is a attached to a ModelElement, and contains a serialized version of the graphical model than can&#39;t be manipulated as-is.</p><p>Elements can be represented in several diagrams at the same time, each diagram type having its own strategy about handling them.</p>
 */
@objid ("006721c2-c4bf-1fd8-97fe-001ec947cd2a")
public interface AbstractDiagram extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("aef73608-bd59-4b3f-95d9-b26401e1267a")
    public static final String MNAME = "AbstractDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("27b1ac8c-b730-4ac4-a9f2-4ae448b03db4")
    public static final String MQNAME = "Infrastructure.AbstractDiagram";

    /**
     * Getter for attribute 'AbstractDiagram.UiDataVersion'
     * 
     * Metamodel description:
     * <i>Serialized contents version.</i>
     */
    @objid ("1ef35969-b33f-4640-8524-190eeb9c1af5")
    int getUiDataVersion();

    /**
     * Setter for attribute 'AbstractDiagram.UiDataVersion'
     * 
     * Metamodel description:
     * <i>Serialized contents version.</i>
     */
    @objid ("d91514a5-ab12-487f-b36a-cb443716e2fc")
    void setUiDataVersion(int value);

    /**
     * Getter for attribute 'AbstractDiagram.UiData'
     * 
     * Metamodel description:
     * <i><p>Serialized diagram content.</p>
     * </i>
     */
    @objid ("93889e33-4e7f-458f-bba2-0b9ca772251e")
    String getUiData();

    /**
     * Setter for attribute 'AbstractDiagram.UiData'
     * 
     * Metamodel description:
     * <i><p>Serialized diagram content.</p>
     * </i>
     */
    @objid ("45546b97-3e3b-4041-8021-2d54eb7d1b41")
    void setUiData(String value);

    /**
     * Getter for attribute 'AbstractDiagram.PreviewData'
     * 
     * Metamodel description:
     * <i><p>HTML preview data enforcing the Data URI scheme: https://en.wikipedia.org/wiki/Data_URI_scheme.</p>
     * </i>
     */
    @objid ("b5d47a5d-eff3-422d-b0f5-0bf05649167b")
    String getPreviewData();

    /**
     * Setter for attribute 'AbstractDiagram.PreviewData'
     * 
     * Metamodel description:
     * <i><p>HTML preview data enforcing the Data URI scheme: https://en.wikipedia.org/wiki/Data_URI_scheme.</p>
     * </i>
     */
    @objid ("75b60745-0ea7-43b7-9320-4f121c022c4a")
    void setPreviewData(String value);

    /**
     * Getter for relation 'AbstractDiagram->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2f3e2376-857d-4f83-a454-57b99f54bcfc")
    EList<Element> getRepresented();

    /**
     * Filtered Getter for relation 'AbstractDiagram->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9f39d1bf-c96d-4d6f-ba72-8fb5a45afa7f")
    <T extends Element> List<T> getRepresented(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AbstractDiagram->ReferencingSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("61fcbc5f-2a96-4542-8fea-9b0258fc22d8")
    EList<DiagramSet> getReferencingSet();

    /**
     * Filtered Getter for relation 'AbstractDiagram->ReferencingSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fd5f22b4-303a-442e-979c-436fa5f439e6")
    <T extends DiagramSet> List<T> getReferencingSet(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AbstractDiagram->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b73de139-a2b0-49e2-88cd-ccfbe215c425")
    ModelElement getOrigin();

    /**
     * Setter for relation 'AbstractDiagram->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("660e9cab-a4ae-4a2b-8f43-9cca0e7a5e4b")
    void setOrigin(ModelElement value);

}
