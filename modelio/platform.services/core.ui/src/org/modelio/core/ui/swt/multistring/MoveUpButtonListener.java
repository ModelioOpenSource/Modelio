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

package org.modelio.core.ui.swt.multistring;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

@objid ("8dc905d8-c068-11e1-8c0a-002564c97630")
class MoveUpButtonListener implements SelectionListener {
    @objid ("8dc905d9-c068-11e1-8c0a-002564c97630")
    private MultiStringEditionComposite dialog = null;

    @objid ("8dc905da-c068-11e1-8c0a-002564c97630")
    public MoveUpButtonListener(MultiStringEditionComposite dialog) {
        this.dialog = dialog;
    }

    @objid ("8dc905dd-c068-11e1-8c0a-002564c97630")
    @Override
    public void widgetDefaultSelected(SelectionEvent event) {
        moveUp();
    }

    @objid ("8dca8c47-c068-11e1-8c0a-002564c97630")
    @Override
    public void widgetSelected(SelectionEvent event) {
        moveUp();
    }

    @objid ("8dca8c4b-c068-11e1-8c0a-002564c97630")
    private void moveUp() {
        List<String> adapters = this.dialog.getSelectedAdapters();
        
        this.dialog.moveUp(adapters);
    }

}
