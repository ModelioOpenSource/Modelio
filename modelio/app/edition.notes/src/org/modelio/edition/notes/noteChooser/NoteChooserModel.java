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

package org.modelio.edition.notes.noteChooser;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Small data model for the note chooser dialog.
 */
@objid ("26e385a9-186f-11e2-bc4e-002564c97630")
public class NoteChooserModel {
    @objid ("26e385af-186f-11e2-bc4e-002564c97630")
    private ModelElement element;

    /**
     * Constructor.
     * 
     * @param element the element to create notes on.
     */
    @objid ("26e385b0-186f-11e2-bc4e-002564c97630")
    public NoteChooserModel(ModelElement element) {
        this.element = element;
    }

    /**
     * Get the element beeing edited.
     * 
     * @return a model element
     */
    @objid ("42b724c1-1917-11e2-bc4e-002564c97630")
    public ModelElement getElement() {
        return this.element;
    }

}
