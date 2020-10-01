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

package org.modelio.xmi.model.ecore;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("d37085e7-8de3-44d2-9891-306f268e82ad")
public class EUsage extends EDependency {
    @objid ("6fbbce2b-09c1-4c44-8025-db39b7ec7754")
    @Override
    public List<Element> createObjingElt() {
        return super.createObjingElt();
    }

    @objid ("cae944c9-6288-4573-a786-00ee39ffd6c4")
    public EUsage(org.eclipse.uml2.uml.Usage element) {
        super(element);
    }

}
