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

package org.modelio.app.project.conf.dialog.libraries.local.property;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

@objid ("b4c30581-39bd-4bcd-aec8-ef0829063d18")
class FilesLabelProvider extends LabelProvider {
    @objid ("14ffa3e7-759e-4dbb-87b5-baa663c95abe")
    @Override
    public Image getImage(Object obj) {
        return null;
    }

    @objid ("36690200-30f7-4301-b557-b1496ba89830")
    @Override
    public String getText(Object obj) {
        File file = (File) obj;
        return file.getPath();
    }

}
