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

package org.modelio.diagram.browser.model.related;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.modelio.diagram.browser.model.core.AbstractModel;

@objid ("f65dde7e-4884-4e3b-913d-083c7803993f")
public class RelatedModel extends AbstractModel {
    @objid ("711a2782-6d1e-4e85-b2fe-e56da0e1387a")
    private RelatedContentProvider contentProvider = new RelatedContentProvider();

    @objid ("263eb4c3-909d-4ea2-9a04-4e57a0eb8019")
    @Override
    public ITreeContentProvider getContentProvider() {
        
        return this.contentProvider;
    }

}
