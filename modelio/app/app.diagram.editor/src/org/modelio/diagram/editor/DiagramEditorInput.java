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
import org.modelio.diagram.editor.IDiagramEditorInputProvider.GmDiagramCreator;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Abstract diagram editor input, needed to open a diagram.
 * <p>
 * This class is to be extended when implementing a specific diagram type.
 * </p>
 * 
 * @see IDiagramEditorInputProvider
 */
@objid ("65931995-33f7-11e2-95fe-001ec947c8cc")
public abstract class DiagramEditorInput {
    @objid ("65931997-33f7-11e2-95fe-001ec947c8cc")
    private AbstractDiagram diagram;

    @objid ("65931998-33f7-11e2-95fe-001ec947c8cc")
    private GmAbstractDiagram model;

    /**
     * Initialize the editor input.
     * <p>
     * Creates the diagram graphic model and load it from the diagram model element.
     * @param diagram the diagram to edit.
     * @param modelManager the link between the Gm model and the Ob model.
     * @param gmDiagramCreator a small factory to instanciate the Gm diagram itself.
     */
    @objid ("65931999-33f7-11e2-95fe-001ec947c8cc")
    public  DiagramEditorInput(AbstractDiagram diagram, IModelManager modelManager, GmDiagramCreator gmDiagramCreator) {
        this.diagram = diagram;
        this.model = gmDiagramCreator.createDiagram(modelManager, diagram);
        if (this.model != null) {
            // Make the diagram visible at GM level.
            this.model.setVisible(true);
        
            // Load from the persistence.
            this.model.load();
        }
        
    }

    @objid ("659319b8-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings ("rawtypes")
    public Object getAdapter(Class adapter) {
        if (adapter.isInstance(this.diagram)) {
            return this.diagram;
        }
        return null;
    }

    @objid ("659319be-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DiagramEditorInput)) {
            return false;
        }
        DiagramEditorInput other = (DiagramEditorInput) obj;
        if (this.diagram == null) {
            if (other.diagram != null) {
                return false;
            }
        } else if (!this.diagram.equals(other.diagram)) {
            return false;
        }
        return true;
    }

    /**
     * @return the edited diagram.
     */
    @objid ("65957bea-33f7-11e2-95fe-001ec947c8cc")
    public AbstractDiagram getDiagram() {
        return this.diagram;
    }

    @objid ("65957bef-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.diagram == null) ? 0 : this.diagram.hashCode());
        return result;
    }

    /**
     * Release all acquired resources.
     */
    @objid ("65957bf4-33f7-11e2-95fe-001ec947c8cc")
    public void dispose() {
        if (this.model != null) {
            this.model.dispose();
            this.model = null;
        }
        
    }

    /**
     * @return the edited diagram graphic model.
     */
    @objid ("65957c08-33f7-11e2-95fe-001ec947c8cc")
    public IGmDiagram getGmDiagram() {
        return this.model;
    }

    /**
     * Get the editor ID this input is destined to.
     * @return the e4xmi ID of a diagram editor.
     */
    @objid ("f258f912-3996-43fc-9fdf-cb5cb5321893")
    public abstract String getEditorId();

}
