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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.property.panel.data.stereotype.GenericPropertyPanel;

/**
 * This class provide the panel that display the note content, constraint
 * content and the tagged values.
 */
@objid ("8dfe6d86-c068-11e1-8c0a-002564c97630")
public class ContentPanel extends Composite {
    @objid ("8dfe6d8d-c068-11e1-8c0a-002564c97630")
    private IPropertyPanel propertyPanel;

    @objid ("39f3ef0a-2d2a-45f1-9be4-bde97b98bb8a")
    private DataPanelInput input;

    @objid ("2f937597-071a-4937-a83d-b847c768e45e")
    private Element typedElement;

    @objid ("513e945e-fd43-4909-8106-bb96a6b4f385")
    private Object typingElement;

    @objid ("8dfe6d8e-c068-11e1-8c0a-002564c97630")
    public ContentPanel(SashForm sash, int style) {
        super(sash, style);
    }

    /**
     * Set the panel input.
     * 
     * @param newInput the new input
     */
    @objid ("8dfe6d93-c068-11e1-8c0a-002564c97630")
    public void setInput(DataPanelInput newInput) {
        // Do not refresh when elements are the same
        if (this.typedElement == newInput.getTypedElement() && this.typingElement == newInput.getTypingElement()) {
            if (this.propertyPanel != null)
                this.propertyPanel.refresh();
            return;
        }
        
        this.input = newInput;
        this.typedElement = newInput.getTypedElement();
        this.typingElement = newInput.getTypingElement();
        
        // cleanup
        if (this.propertyPanel != null) {
            this.propertyPanel.stop();
            this.propertyPanel.getComposite().dispose();
        }
        
        if (this.typingElement instanceof Stereotype) {
            // Display a Stereotype tagged values set
            this.propertyPanel = new GenericPropertyPanel(this, (ModelElement) this.typedElement,
                    (Stereotype) this.typingElement);
            this.propertyPanel.setInput(newInput);
            this.propertyPanel.refresh();
            this.layout(true, true);
        } else if (this.typingElement instanceof ModuleComponent) {
            // Display a Module tagged values set
            this.propertyPanel = new GenericPropertyPanel(this, (ModelElement) this.typedElement, (ModuleComponent) this.typingElement);
            this.propertyPanel.setInput(newInput);
            //this.propertyPanel.refresh();
            this.layout();
            
            
        } else {
            // Display the element standard meta attributes
            this.propertyPanel = PropertyPanelFactory.createStandardPanel(this, this.typedElement);
            this.propertyPanel.start();
            this.propertyPanel.setInput(newInput);
            this.propertyPanel.refresh();
            this.layout();
        }
    }

    /**
     * @return the edited element.
     */
    @objid ("8dfe6d9b-c068-11e1-8c0a-002564c97630")
    public Element getCurrentInput() {
        if (this.input == null)
            return null;
        return this.input.getTypedElement();
    }

}
