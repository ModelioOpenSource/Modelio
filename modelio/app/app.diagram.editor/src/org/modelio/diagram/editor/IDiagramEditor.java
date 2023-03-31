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
package org.modelio.diagram.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.RootEditPart;

/**
 * Common interface for all diagram editors. Used by DiagramHandle to use AbstractDiagramEditor and SilentDiagramEditor
 * the same way.
 * 
 * @author fpoyer
 */
@objid ("6664e89b-33f7-11e2-95fe-001ec947c8cc")
public interface IDiagramEditor extends IAdaptable {
    /**
     * Return the root edit part of this editor.
     * @return the root edit part of this editor.
     */
    @objid ("6664e89d-33f7-11e2-95fe-001ec947c8cc")
    RootEditPart getRootEditPart();

    /**
     * Notifies the editor that the handle opened on it has been closed. Depending on its nature, the editor might
     * decide to delete itself, release some resources, or ignore this event altogether.
     */
    @objid ("6664e8a0-33f7-11e2-95fe-001ec947c8cc")
    void disposeHandle();

    /**
     * Returns the input for this editor.
     * @return the editor input
     */
    @objid ("6664e8a2-33f7-11e2-95fe-001ec947c8cc")
    DiagramEditorInput getEditorInput();
}

