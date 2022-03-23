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
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("9180155f-ac91-4133-a0a6-8e16f7ff73f6")
public class OClassifier extends OElement implements IOElement {
    @objid ("011f3179-c9f4-41cd-8f25-de16d837a2a8")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("82c2e6e1-ba06-46ec-9b9a-3c1ea1296f20")
    public  OClassifier(final ModelElement element) {
        super(element);
    }

    @objid ("b4879b5f-d88a-4d56-8b8e-d3c37c2dba1a")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        
    }

    @objid ("356bcbe7-bec9-4b24-bc99-bee9e7f09f09")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        
    }

}
