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
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

@objid ("8dcd999e-c068-11e1-8c0a-002564c97630")
class MultiStringContentProvider implements IStructuredContentProvider {
    @objid ("8dcd999f-c068-11e1-8c0a-002564c97630")
    @Override
    public void dispose() {
        // Nothing to do
    }

    @objid ("8dcd99a2-c068-11e1-8c0a-002564c97630")
    @SuppressWarnings("unchecked")
    @Override
    public Object[] getElements(Object inputElement) {
        List<String> elements = (List<String>)inputElement;
        return elements.toArray(new String[elements.size()]);
    }

    @objid ("8dcd99aa-c068-11e1-8c0a-002564c97630")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do
    }

}
