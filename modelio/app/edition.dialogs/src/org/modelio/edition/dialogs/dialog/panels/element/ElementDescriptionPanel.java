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

package org.modelio.edition.dialogs.dialog.panels.element;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ElementDescriptionPanel is a NoteEditPanel configured for ModelerModule#description notes.
 * 
 * @author phv
 */
@objid ("73a49a33-6fe4-4e9d-b17c-bff849f50183")
public class ElementDescriptionPanel extends TypedNotePanel {
    @objid ("2740366e-2eb2-4b25-b7c5-9d91f77d8b1d")
    private static final String MODELER_MODULE = "ModelerModule";

    @objid ("ff6e3770-c15e-46ba-bcbd-db8e531cbd12")
    private static final String DESCRIPTION_NOTE = "description";

    @objid ("780fc09a-d564-4cc3-9ce0-233166542fb6")
    public ElementDescriptionPanel() {
        super(MODELER_MODULE, DESCRIPTION_NOTE);
    }

    /**
     * Specialize to accept selection from the GUI
     */
    @objid ("568a4f20-0ba9-4c04-bc5b-2ebaa5f94bed")
    @Override
    public boolean isRelevantFor(Object obj) {
        if (obj instanceof ISelection) {
            return SelectionHelper.getFirst((ISelection) obj, ModelElement.class) != null;
        } else {
            return obj instanceof ModelElement;
        }
    }

    /**
     * Specialize to process selection from the GUI
     */
    @objid ("a79c9cef-b629-4226-8422-499f9917a55d")
    @Override
    public void setInput(Object input) {
        ModelElement me = input instanceof ModelElement ? (ModelElement) input : SelectionHelper.getFirst((ISelection) input, ModelElement.class);
        super.setInput(me);
    }

}
