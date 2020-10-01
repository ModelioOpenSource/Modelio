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

package org.modelio.diagram.browser.model.byset;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.modelio.diagram.browser.model.core.AbstractModel;
import org.modelio.gproject.gproject.GProject;

@objid ("00450e3e-0d4f-10c6-842f-001ec947cd2a")
public class BySetModel extends AbstractModel {
    @objid ("a1bb85cd-6150-474a-8fa2-25211a410d33")
    private BySetContentProvider bySetContentProvider;

    @objid ("00452694-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public ITreeContentProvider getContentProvider() {
        
        return this.bySetContentProvider;
    }

    @objid ("77e6b108-8736-4b87-ad4e-c2a565f38fc2")
    public BySetModel(GProject project) {
        this.bySetContentProvider = new BySetContentProvider(project);
    }

}
