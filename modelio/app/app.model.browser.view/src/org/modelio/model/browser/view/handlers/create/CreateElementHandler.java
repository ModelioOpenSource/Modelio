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
package org.modelio.model.browser.view.handlers.create;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("9ec3b982-ccde-11e1-97e5-001ec947c8cc")
public class CreateElementHandler extends AbstractCreateElementHandler {
    @objid ("9ec61bfa-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected MObject doCreate(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype, IMModelServices mmServices) {
        IModelFactory mmFactory = mmServices.getModelFactory();
        IElementNamer mmNamer = mmServices.getElementNamer();
        
        MObject newElement = mmFactory.createElement(metaclass, owner, dependency);
        if (stereotype != null && newElement instanceof ModelElement) {
            ((ModelElement) newElement).getExtension().add(stereotype);
        }
        newElement.setName(mmNamer.getUniqueName(newElement));
        return newElement;
    }

    @objid ("0052e572-b46e-1006-9c1d-001ec947cd2a")
    @Override
    protected boolean doCanExecute(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype) {
        if (!owner.isModifiable()) {
            return false;
        }
        
        if (dependency != null) {
            return dependency.getMaxCardinality() != 1 || owner.mGet(dependency).isEmpty();
        } else {
            return true;
        }
        
    }

}
