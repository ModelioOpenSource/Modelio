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
package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Feature;

@objid ("47d1b31d-849e-42b8-895c-a5bb9b2028af")
public class OFeature extends ONameSpace {
    @objid ("d202d56c-80d2-4d37-97c4-e0d3b4c621e6")
    public  OFeature(Feature element) {
        super(element);
    }

    @objid ("9bc272f7-08c4-41de-8cbd-d4664debfb16")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        
    }

    @objid ("28b753f9-00eb-47cc-a3eb-b36da7a87040")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("d82da68b-6c6f-4f0a-a0bb-5c316d9f63d7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

}
