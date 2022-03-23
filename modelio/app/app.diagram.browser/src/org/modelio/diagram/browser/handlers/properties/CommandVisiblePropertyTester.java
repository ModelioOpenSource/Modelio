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
package org.modelio.diagram.browser.handlers.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.browser.model.AllDiagramsNode;
import org.modelio.diagram.browser.model.core.DiagramRef;
import org.modelio.diagram.browser.model.core.VirtualFolder;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;

/**
 * {@link PropertyTester} that tells whether commands are visible for the given selection.
 */
@objid ("78897314-56c2-4216-8e31-b2c16dbf9c30")
public class CommandVisiblePropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("babeda1a-5a9a-45e7-ac22-c92b4481c987")
    public  CommandVisiblePropertyTester() {
        // nothing
    }

    @objid ("0fe17f86-1448-4e86-b545-47580143d634")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;        
        Object[] elements = selection.toArray();
        switch (property) {
            case "createfolder":
                for (Object element : elements) {
                    if (!(element instanceof DiagramRef) && !(element instanceof AbstractDiagram) 
                            && !(element instanceof VirtualFolder) && !(element instanceof IProjectFragment)) {
                        return true;
                    }
                }
                return false;
            case "selectinexplore":
                for (Object element : elements) {
                    if (element instanceof VirtualFolder) {
                        if (((VirtualFolder) element).getRepresentedElement() == null) {
                            return false;
                        }
                    }
                    if (!(element instanceof DiagramSet) && !(element instanceof AllDiagramsNode) 
                            && !(element instanceof IProjectFragment)) {
                        return true;
                    }
                }
                return false;
            case "rename":
            case "delete":
                for (Object element : elements) {
                    if (!(element instanceof VirtualFolder) && !(element instanceof IProjectFragment)) {
                        return true;
                    }
                }
                return false;
            case "cut":
            case "copy":
            case "paste":
                for (Object element : elements) {
                    if (!(element instanceof DiagramRef) && !(element instanceof AbstractDiagram) 
                            && !(element instanceof VirtualFolder) && !(element instanceof IProjectFragment)) {
                        return true;
                    }
                }
                return false;
            default:
                throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
        
    }

}
