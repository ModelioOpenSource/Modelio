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

package org.modelio.diagram.diagramauto.handlers.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link PropertyTester} that tells whether commands are visible for the given selection.
 */
@objid ("4a031b93-f7de-49ec-89d8-0607ff78ea8b")
public class CommandVisiblePropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("d9a7dae2-bc44-4f20-aa1b-fe7f26a58307")
    public CommandVisiblePropertyTester() {
        // nothing
    }

    @objid ("5cd4c2fd-f321-47ca-870c-16b8446e1090")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;
        Object element = selection.getFirstElement();
        switch (property) {
        case "packagstructure":
        case "subpackagstructure":
            if (selection.size() == 1 && element instanceof Package && !(element instanceof Profile)) {
                return ((MObject) element).getMClass().isCmsNode();
            }
            return false;
        case "dependencies":
            if (selection.size() == 1 && ((element instanceof Classifier && !(element instanceof ModuleComponent)) || (element instanceof Package && !(element instanceof Profile)))) {
                return ((MObject) element).getMClass().isCmsNode();
            }
            return false;
        case "updatediagram":
            if (SelectionHelper.containsOnly(selection, AbstractDiagram.class)) {
                return true;
            }
            return false;
        default:
            throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
    }

}
