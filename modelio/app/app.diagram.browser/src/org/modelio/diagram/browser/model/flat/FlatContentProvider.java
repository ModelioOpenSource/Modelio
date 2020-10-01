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

package org.modelio.diagram.browser.model.flat;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.diagram.browser.model.AllDiagramsNode;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Content provider for the FlatModel of the diagram browser tree.<br>
 * Flat means that all the diagrams are presented as a single 'flat' list.
 * 
 * The root is an "all diagrams" virtual folder node
 */
@objid ("00273666-0d4f-10c6-842f-001ec947cd2a")
public class FlatContentProvider implements ITreeContentProvider {
    @objid ("00250094-43b1-10c7-842f-001ec947cd2a")
    private Object rootNode;

    @objid ("63f4f9b4-58ed-41bd-9b5b-74bad3916064")
    protected GProject project;

    @objid ("002769ec-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void dispose() {
        // Empty
    }

    /**
     * get the 'root' elements of the tree.
     */
    @objid ("002781ac-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getElements(Object inputElement) {
        return new Object[] { this.rootNode };
    }

    @objid ("0027c270-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object getParent(Object child) {
        if (child instanceof AbstractDiagram)
            return this.rootNode;
        return null;
    }

    /**
     * Get the sub elements of a node of the tree
     */
    @objid ("0027ec5a-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getChildren(Object parent) {
        if (parent instanceof AllDiagramsNode)
            return ((AllDiagramsNode) parent).getChildren(parent);
        return new Object[0];
    }

    @objid ("00282c6a-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof AllDiagramsNode)
            return true;
        return false;
    }

    /**
     * @param session
     */
    @objid ("002856cc-0d4f-10c6-842f-001ec947cd2a")
    public FlatContentProvider(GProject project) {
        this.project = project;
        this.rootNode = new AllDiagramsNode(project, null);
    }

    @objid ("0026aa98-43b1-10c7-842f-001ec947cd2a")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do, data is the project set in C'tor
    }

}
