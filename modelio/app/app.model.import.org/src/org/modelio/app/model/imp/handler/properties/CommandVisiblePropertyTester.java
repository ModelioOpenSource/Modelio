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

package org.modelio.app.model.imp.handler.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * {@link PropertyTester} that tells whether commands are visible for the given selection.
 */
@objid ("fd41b4c1-57ca-4e6e-b4b2-f370b090f749")
public class CommandVisiblePropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("22f03899-1ce7-4615-9055-3f3b414286dd")
    public CommandVisiblePropertyTester() {
        // nothing
    }

    @objid ("248692e5-c9f0-4274-8ef7-5f6c5d80b3b5")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;
        switch (property) {
            case "modelimport":
                List<MObject> selObjs = SelectionHelper.toList(selection, MObject.class);
                List<IProjectFragment> selFrags = SelectionHelper.toList(selection, IProjectFragment.class);
                
                for (IProjectFragment object : selFrags) {
                    for (MObject r : object.getRoots()) {
                        MStatus status = r.getStatus();
                        if (! (status.isModifiable() || status.isCmsManaged())) {
                            return false;
                        }
                    }
                }
                
                for (MObject r : selObjs) {
                    MStatus status = r.getStatus();
                    if (! (status.isModifiable() || status.isCmsManaged())) {
                        return false;
                    }
                }
                
                return !selObjs.isEmpty() || ! selFrags.isEmpty();
            default:
                throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
    }

}
