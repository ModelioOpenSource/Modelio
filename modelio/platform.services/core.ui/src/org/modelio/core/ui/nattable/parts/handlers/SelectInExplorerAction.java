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

package org.modelio.core.ui.nattable.parts.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.selection.action.AbstractMouseSelectionAction;
import org.eclipse.swt.events.MouseEvent;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("9c5f94d0-96f2-4415-afaf-929058a94642")
public class SelectInExplorerAction extends AbstractMouseSelectionAction {
    @objid ("67046b28-0df7-4499-927c-dc3de0c75018")
    private IModelioNavigationService navigationService;

    @objid ("fa8de8e6-ef30-4ef0-870b-8c539a9500d8")
    public SelectInExplorerAction(IModelioNavigationService navigationService) {
        super();
        this.navigationService = navigationService;
    }

    @objid ("dfd7ef92-c43b-426c-b463-bd88f54d44df")
    @Override
    public void run(NatTable natTable, MouseEvent e) {
        int col = natTable.getColumnPositionByX(e.x);
        int row = natTable.getRowPositionByY(e.y);
        
        // Select element in explorer
        INatValue natValue = (INatValue) natTable.getCellByPosition(col, row).getDataValue();
        //INatValue natValue = (INatValue) ((PropertyTableDataModel) natTable.getData()).getValueAt(row, col);
        Object elt = natValue.getValue();
        if (elt instanceof MObject) {
            this.navigationService.fireNavigate((MObject) elt);
        }
    }

}
