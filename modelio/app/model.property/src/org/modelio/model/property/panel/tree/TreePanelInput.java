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

package org.modelio.model.property.panel.tree;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Input of the tree panel.
 */
@objid ("e72bd9e5-022c-46aa-84c8-d1696beec6d0")
public class TreePanelInput {
    @objid ("c7d33447-15ef-481f-bef2-f9099defe3bc")
    private boolean displayHiddenAnnotations;

    /**
     * The element whose annotations are edited.
     */
    @objid ("b7a58827-5c5c-4c3d-b249-7822b700023b")
    private Element typedElement;

    /**
     * The group of edited annotations : a module, a stereotype or null.
     */
    @objid ("c7ac265e-4349-445e-aea6-e355efe339e6")
    private Object preselectedTypingElement;

    /**
     * @param typedElement the element whose annotations are edited
     * @param preselectedTypingElement the initial group of annotations
     * @param displayHiddenAnnotations true to display annotations whose type is set to hidden.
     */
    @objid ("d5268733-74a2-4c9a-acb5-5210e66edf62")
    public TreePanelInput(Element typedElement, Element preselectedTypingElement, boolean displayHiddenAnnotations) {
        super();
        this.typedElement = typedElement;
        this.preselectedTypingElement = preselectedTypingElement;
        this.displayHiddenAnnotations = displayHiddenAnnotations;
    }

    /**
     * Copy constructor.
     * @param other the copied input.
     */
    @objid ("31d89695-1ec8-4c7e-947e-746d7e45e913")
    public TreePanelInput(TreePanelInput other) {
        super();
        this.typedElement = other.typedElement;
        this.preselectedTypingElement = other.preselectedTypingElement;
        this.displayHiddenAnnotations = other.displayHiddenAnnotations;
    }

    @objid ("3e9b1b6d-1ea2-4b98-b870-36971ff7e070")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.displayHiddenAnnotations ? 1231 : 1237);
        result = prime * result + ((this.preselectedTypingElement == null) ? 0 : this.preselectedTypingElement.hashCode());
        result = prime * result + ((this.typedElement == null) ? 0 : this.typedElement.hashCode());
        return result;
    }

    @objid ("3b462ad9-7c44-4b1b-8360-657526dc840e")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TreePanelInput other = (TreePanelInput) obj;
        if (this.displayHiddenAnnotations != other.displayHiddenAnnotations) {
            return false;
        }
        if (this.preselectedTypingElement == null) {
            if (other.preselectedTypingElement != null) {
                return false;
            }
        } else if (!this.preselectedTypingElement.equals(other.preselectedTypingElement)) {
            return false;
        }
        if (this.typedElement == null) {
            if (other.typedElement != null) {
                return false;
            }
        } else if (!this.typedElement.equals(other.typedElement)) {
            return false;
        }
        return true;
    }

    /**
     * @return the typedElement
     */
    @objid ("6b84e04b-f5fd-4f1c-8fe5-dfc57aeb0419")
    public Element getTypedElement() {
        return this.typedElement;
    }

    /**
     * @param typedElement the typedElement to set
     */
    @objid ("40459d2b-85e6-4beb-ad79-a73e067786df")
    public void setTypedElement(Element typedElement) {
        this.typedElement = typedElement;
    }

    /**
     * @return the preselectedTypingElement
     */
    @objid ("da22bde6-5a17-47e7-be98-c9b487df5925")
    public Object getPreselectedTypingElement() {
        return this.preselectedTypingElement;
    }

    /**
     * @param preselectedTypingElement the preselectedTypingElement to set
     */
    @objid ("f256e76a-e686-420d-8df4-ed3d5f456c8a")
    public void setPreselectedTypingElement(Object preselectedTypingElement) {
        this.preselectedTypingElement = preselectedTypingElement;
    }

    /**
     * @return the displayHiddenAnnotations
     */
    @objid ("ca83fbb4-a2d3-4654-b540-94b6a1ab622b")
    public boolean isDisplayHiddenAnnotations() {
        return this.displayHiddenAnnotations;
    }

    /**
     * @param displayHiddenAnnotations the displayHiddenAnnotations to set
     */
    @objid ("dc41dc7d-888e-4ae6-87ff-e5505d30c073")
    public void setDisplayHiddenAnnotations(boolean displayHiddenAnnotations) {
        this.displayHiddenAnnotations = displayHiddenAnnotations;
    }

}
