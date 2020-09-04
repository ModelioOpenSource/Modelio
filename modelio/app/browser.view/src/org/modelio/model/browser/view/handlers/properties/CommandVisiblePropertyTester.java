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

package org.modelio.model.browser.view.handlers.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

/**
 * {@link PropertyTester} that tells whether commands are visible for the given selection.
 */
@objid ("66adc08b-1bf8-4fe7-b141-1b9518dcfa9a")
public class CommandVisiblePropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("318f4023-36db-48f4-bfa3-3b87eccae663")
    public CommandVisiblePropertyTester() {
        // nothing
    }

    @objid ("b9bd0315-dc85-4ac0-9575-2ac802b5bf4f")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;        
        Object element = selection.getFirstElement();
        Object[] elements = selection.toArray();
        switch (property) {
            case "createstereotype" :
                for (Object elt: elements) {
                    if (elt instanceof Profile || elt instanceof Stereotype) {
                        return false;
                    }
                }
                return selection.size() > 0;
            case "editstereotype":
                if (selection.size() == 1 && element instanceof Stereotype) {
                    return true;
                }
                return false;
            default:
                throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
    }

}
