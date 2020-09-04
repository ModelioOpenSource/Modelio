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

package org.modelio.diagram.browser.model.bycontext;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.modelio.diagram.browser.model.core.AbstractModel;
import org.modelio.gproject.gproject.GProject;

@objid ("002b53f4-0d4f-10c6-842f-001ec947cd2a")
public class ByCtxModel extends AbstractModel {
    @objid ("6e1c123a-888a-49e7-85a6-a34b8ab0f9e6")
    private ByCtxContentProvider byCtxContentProvider;

    @objid ("002b7096-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public ITreeContentProvider getContentProvider() {
        
        return this.byCtxContentProvider;
    }

    @objid ("a2907aff-fb26-48de-948b-1148a24c6ab3")
    public ByCtxModel(GProject project) {
        this.byCtxContentProvider = new ByCtxContentProvider(project);
    }

}
