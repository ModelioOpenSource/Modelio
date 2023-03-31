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
package org.modelio.diagram.browser.model.bytype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.modelio.diagram.browser.model.core.AbstractModel;
import org.modelio.gproject.core.IGProject;

@objid ("004067c6-0d4f-10c6-842f-001ec947cd2a")
public class ByTypeModel extends AbstractModel {
    @objid ("f05d6beb-4adb-4379-af5a-4e38183094ba")
    private ByTypeContentProvider byTypeContentProvider;

    @objid ("00408080-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public ITreeContentProvider getContentProvider() {
        return this.byTypeContentProvider;
    }

    @objid ("7068a710-af08-4eea-b642-ee60cb5150d7")
    public  ByTypeModel(IGProject project) {
        this.byTypeContentProvider = new ByTypeContentProvider(project);
    }

}
