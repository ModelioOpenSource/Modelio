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

package org.modelio.platform.model.ui.swt.multistring;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

@objid ("8dc905c9-c068-11e1-8c0a-002564c97630")
class MoveDownButtonListener implements SelectionListener {
    @objid ("8dc905ca-c068-11e1-8c0a-002564c97630")
    private MultiStringEditionComposite dialog = null;

    @objid ("8dc905cb-c068-11e1-8c0a-002564c97630")
    public MoveDownButtonListener(MultiStringEditionComposite dialog) {
        this.dialog = dialog;
    }

    @objid ("8dc905ce-c068-11e1-8c0a-002564c97630")
    @Override
    public void widgetDefaultSelected(SelectionEvent event) {
        moveDown();
    }

    @objid ("8dc905d2-c068-11e1-8c0a-002564c97630")
    @Override
    public void widgetSelected(SelectionEvent event) {
        moveDown();
    }

    @objid ("8dc905d6-c068-11e1-8c0a-002564c97630")
    private void moveDown() {
        List<String> adapters = this.dialog.getSelectedAdapters();
        
        this.dialog.moveDown(adapters);
    }

}
