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

package org.modelio.edition.notes.panelprovider.data;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Control;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("f9213763-fb76-413e-a7cb-50dd2c21b9ee")
public interface INoteContent {
    @objid ("7c54427a-2714-4e15-bbe7-055413f76fcb")
    void setInput(final ModelElement note);

    @objid ("86e121e8-6cbc-4cdf-b854-441b3bd2a2ec")
    Control getControl();

    @objid ("21eb54e3-8998-460d-bfb8-15eb524245b9")
    ModelElement getNoteElement();

}
