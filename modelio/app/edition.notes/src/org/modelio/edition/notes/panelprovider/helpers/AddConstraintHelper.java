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

package org.modelio.edition.notes.panelprovider.helpers;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.dialogs.elementChooser.ElementChooserDlg;
import org.modelio.edition.notes.constraintChooser.ConstraintChooserDriver;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("7f77292f-8751-468f-a419-84571c8866ea")
public class AddConstraintHelper extends AbstractHelper {
    @objid ("c307787d-587c-41fa-9316-4a0b40bcdc24")
    private ICoreSession session;

    @objid ("25b3f9d2-eec5-4657-87be-af9e0186f3d0")
    private IMModelServices modelServices;

    @objid ("ec5bb39c-6e2a-4dec-b901-1105651cd7bb")
    public AddConstraintHelper(ICoreSession session, IMModelServices modelServices) {
        this.session = session;
        this.modelServices = modelServices;
    }

    @objid ("258f3d3b-e6ca-464c-b62d-685161145bfd")
    public static boolean canExecute(ModelElement element, List<ModelElement> selectedItems) {
        return (element != null) && (element.isModifiable());
    }

    @objid ("8bdf61f2-c177-4d9e-add1-0e39687fe2a8")
    public Constraint execute(Shell parentShell, ModelElement element) {
        ConstraintChooserDriver driver = new ConstraintChooserDriver(this.session, this.modelServices, element);
        ElementChooserDlg dialog = new ElementChooserDlg(parentShell, driver, element);
        // Don't return from open() until window closes
        dialog.setBlockOnOpen(true);
        // Open the main window
        dialog.open();
        return driver.getCreatedConstraint();
    }

}
