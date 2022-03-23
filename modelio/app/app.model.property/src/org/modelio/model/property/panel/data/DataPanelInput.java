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
package org.modelio.model.property.panel.data;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.model.ui.nattable.viewer.model.INatTableViewerContext;

@objid ("e8796b6c-9ea4-46df-b915-e29eb7153aa4")
public class DataPanelInput {
    @objid ("4dd34e0a-d2f7-4525-acbc-f5b62c56c4ef")
    private boolean showHiddenAnnotations;

    @objid ("400d60de-bd12-46d4-9bd9-18210fa6a8ff")
    private INatTableViewerContext context;

    /**
     * Model element whose annotations are edited.
     */
    @objid ("06d07e77-8dbd-4de4-9d25-807affbeeec7")
    private Element typedElement;

    /**
     * Part of annotations displayed in the right part.
     */
    @objid ("11d03ad9-be31-4e39-8877-c0386f392f54")
    private Object typingElement;

    /**
     * @return the project context
     */
    @objid ("89be548a-6f4b-4846-a0bd-99c64b02377b")
    public INatTableViewerContext getContext() {
        return this.context;
    }

    /**
     * @return the typedElement
     */
    @objid ("f3eeb270-f834-4530-a896-bbed51d50f26")
    public Element getTypedElement() {
        return this.typedElement;
    }

    /**
     * @param typedElement the typedElement to set
     */
    @objid ("d3452517-4265-429e-a3fc-ab6a1d9365ae")
    public void setTypedElement(Element typedElement) {
        this.typedElement = typedElement;
    }

    /**
     * @return the typingElement
     */
    @objid ("e36e61f2-f869-41bc-88c8-c7e973c699db")
    public Object getTypingElement() {
        return this.typingElement;
    }

    /**
     * @param typingElement the typingElement to set
     */
    @objid ("337a6591-1a27-4ed0-9c3e-5f0fb796d729")
    public void setTypingElement(Object typingElement) {
        this.typingElement = typingElement;
    }

    /**
     * Build a new panel input.
     * @param projectService the currently opened project.
     * @param modelService service to look for model elements and metamodel extensions.
     * @param pickingService service to activate/deactivate the picking mode.
     * @param activationService service to select elements in the model.
     * @param typedElement Model element whose annotations are edited.
     * @param typingElement Part of annotations displayed in the data panel.
     * @param showHiddenAnnotations whether or not the hidden mda annotations should be shown.
     */
    @objid ("98d05ffe-672f-4bed-89c0-eeba3dff1de2")
    public  DataPanelInput(INatTableViewerContext context, Element typedElement, Object typingElement, boolean showHiddenAnnotations) {
        super();
        this.context = context;
        this.typedElement = typedElement;
        this.typingElement = typingElement;
        setShowHiddenAnnotations(showHiddenAnnotations);
        
    }

    /**
     * Build a new panel input from another one.
     * @param other another panel input.
     */
    @objid ("9a011839-1861-424b-8af0-13b01fb66a80")
    public  DataPanelInput(DataPanelInput other) {
        this.context = other.context;
        this.typedElement = other.typedElement;
        this.typingElement = other.typingElement;
        setShowHiddenAnnotations(other.isShowHiddenAnnotations());
        
    }

    @objid ("7b9f4391-163e-495b-b884-3230f1ac80e1")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.context == null) ? 0 : this.context.hashCode());
        result = prime * result + ((this.typedElement == null) ? 0 : this.typedElement.hashCode());
        result = prime * result + ((this.typingElement == null) ? 0 : this.typingElement.hashCode());
        return result;
    }

    @objid ("9b3fe3de-ce43-41b0-bac7-fc23231c26e5")
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
        DataPanelInput other = (DataPanelInput) obj;
        if (! Objects.equals(this.context, other.context)) {
            return false;
        }
        
        if (this.typedElement == null) {
            if (other.typedElement != null) {
                return false;
            }
        } else if (!this.typedElement.equals(other.typedElement)) {
            return false;
        }
        if (this.typingElement == null) {
            if (other.typingElement != null) {
                return false;
            }
        } else if (!this.typingElement.equals(other.typingElement)) {
            return false;
        }
        return true;
    }

    @objid ("c165d075-44c6-485b-8a56-749107521552")
    public boolean isShowHiddenAnnotations() {
        return this.showHiddenAnnotations;
    }

    @objid ("64e76aed-5069-4952-85b5-8407337ba7fd")
    public final void setShowHiddenAnnotations(boolean showHiddenAnnotations) {
        this.showHiddenAnnotations = showHiddenAnnotations;
    }

}
