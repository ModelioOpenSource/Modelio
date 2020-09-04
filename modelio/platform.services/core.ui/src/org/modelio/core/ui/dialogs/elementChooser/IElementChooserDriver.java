/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.core.ui.dialogs.elementChooser;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("26cbb831-186f-11e2-bc4e-002564c97630")
public interface IElementChooserDriver {
    @objid ("26cbb832-186f-11e2-bc4e-002564c97630")
    StructuredViewer createViewer(Composite parent);

    @objid ("26cbb835-186f-11e2-bc4e-002564c97630")
    void performFinish(StructuredViewer leftViewer, List<Object> selection);

    @objid ("26cbb83a-186f-11e2-bc4e-002564c97630")
    void performCancel();

    @objid ("26cbb83b-186f-11e2-bc4e-002564c97630")
    void init(Element element);

    @objid ("26cbb83d-186f-11e2-bc4e-002564c97630")
    String getTitle();

    @objid ("26cbb83f-186f-11e2-bc4e-002564c97630")
    String getLeftLabel();

    @objid ("26cbb841-186f-11e2-bc4e-002564c97630")
    String getShellTitle();

    @objid ("26ce1965-186f-11e2-bc4e-002564c97630")
    String getMessage();

}
