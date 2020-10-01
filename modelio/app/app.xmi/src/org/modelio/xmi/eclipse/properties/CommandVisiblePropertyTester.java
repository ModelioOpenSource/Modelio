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

package org.modelio.xmi.eclipse.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.handlers.ExportProfile;
import org.modelio.xmi.handlers.ExportXMI;
import org.modelio.xmi.handlers.ImportProfile;
import org.modelio.xmi.handlers.ImportXMI;
import org.modelio.xmi.handlers.SelectionHelper;

/**
 * {@link PropertyTester} that tells whether SVN commands are visible for the given selection.
 */
@objid ("1da28afe-e5e4-4350-b731-b9d4a0a49855")
public class CommandVisiblePropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("edf6cdf6-497a-40dc-a7c3-abc7cc08a4e9")
    public CommandVisiblePropertyTester() {
        // nothing
    }

    @objid ("2d82bfce-dce7-43e8-9850-fdd9d1669c35")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (! (receiver instanceof ISelection)){
            return false;
        }
        
        List<MObject> selectedElements = SelectionHelper.getElements((ISelection)receiver);
        
        switch (property) {
        
        case "export":
            return ExportXMI.isVisible(selectedElements);
            
        case "import":
            return ImportXMI.isVisible(selectedElements);
            
        case "importprofile":
            return ImportProfile.isVisible(selectedElements);
            
        case "exportprofile":
            return ExportProfile.isVisible(selectedElements);
               
        default:
            throw new IllegalArgumentException(property +" property not supported by "+getClass().getSimpleName());
        }
    }

}
