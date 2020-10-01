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

package org.modelio.patterns.commands.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link PropertyTester} that tells whether commands are visible for the given selection.
 */
@objid ("7fc36c7a-b2e8-498d-946a-5b84dcfc1660")
public class CommandVisiblePropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("f4ca6686-b09d-4468-9333-6e02c4bcd041")
    public CommandVisiblePropertyTester() {
        // nothing
    }

    @objid ("5bd0f6c5-425d-4434-b532-db358e680b81")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;
        switch (property) {
            case "ispattern":
                final List<Package> selectedPackages = SelectionHelper.toList(selection, Package.class);
                if (selectedPackages.size() == 1) {
                    return selectedPackages.get(0).isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERN);
                }
                return false;
            case "isnotpattern":
                final List<MObject> selectedElements = SelectionHelper.toList(selection, MObject.class);
                if (!selectedElements.isEmpty() && selectedElements.get(0) instanceof ModelElement) {
                    ModelElement element = (ModelElement) selectedElements.get(0);
                    if (element.isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERN)) {
                        return false;
                    }
                    return true;
                }
                return false;
            default:
                throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
    }

}
