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
package org.modelio.diagram.browser.model.flat;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.modelio.diagram.browser.model.core.AbstractModel;
import org.modelio.gproject.core.IGProject;

@objid ("0026cc44-0d4f-10c6-842f-001ec947cd2a")
public class FlatModel extends AbstractModel {
    @objid ("5613e7cd-ae8c-4285-b38a-4569d55024ca")
    private FlatContentProvider contentProvider;

    @objid ("0026e490-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public ITreeContentProvider getContentProvider() {
        return this.contentProvider;
    }

    @objid ("7229b289-b3c6-477e-a30b-263d06a427aa")
    public  FlatModel(IGProject project) {
        this.contentProvider = new FlatContentProvider(project);
    }

}
