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
package org.modelio.app.ramcs.handlers.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive;

/**
 * {@link PropertyTester} that tells whether commands are visible for the given selection.
 */
@objid ("1b90fd8e-f9f0-4e7f-9878-7bf2b51d1ed5")
public class CommandVisiblePropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("82908551-4edb-4abe-870d-d78068b1f688")
    public  CommandVisiblePropertyTester() {
        // nothing
    }

    @objid ("9a4f1dcd-5cf6-4711-b4c1-e7eeea9c4cd1")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;
        
        switch (property) {
        
        case "create":
            if (selection.size() == 1 && selection.getFirstElement() instanceof Package) {
                Package elt = (Package) selection.getFirstElement();
                return elt.getRepresented() != null;
            }
            return false;
        case "package":
        case "configure":
            if (selection.size() == 1 && selection.getFirstElement() instanceof Artifact) {
                final Artifact artifact = (Artifact) selection.getFirstElement();
                return artifact.isStereotyped(ModelComponentArchive.MdaTypes.STEREOTYPE_ELT);
            }
            return false;
        
        default:
            throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
        
    }

}
