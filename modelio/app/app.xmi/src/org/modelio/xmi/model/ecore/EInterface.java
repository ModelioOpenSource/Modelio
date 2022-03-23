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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class handles the import of Ecore org.eclipse.uml2.uml.Interfaces.
 * @author ebrosse
 */
@objid ("88c96440-4128-4a31-a328-ca0c75943437")
public class EInterface extends ENamedElement {
    @objid ("a624f7ca-318b-488f-8814-c554253fdc03")
    private org.eclipse.uml2.uml.Interface ecoreElement = null;

    @objid ("ea883ae4-e4b7-4523-b8bd-00b4c21aedae")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInterface();
    }

    /**
     * @param element : the imported Ecore org.eclipse.uml2.uml.Interface
     */
    @objid ("f7b0430e-6b23-4f82-bf00-4ded415dbec3")
    public  EInterface(final org.eclipse.uml2.uml.Interface element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("b0958dfa-2907-409f-a99a-8cabc953fae1")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
               
        Element objingOwner = (Element) ReverseProperties.getInstance()
        .getMappedElement(ecoreOwner);
        
         if ((objingOwner instanceof ModelTree) && !(objingOwner instanceof Profile)){
            ((Interface) objingElt).setOwner((ModelTree) objingOwner);
        }else
            ((Interface) objingElt).setOwner(ReverseProperties.getInstance().getExternalPackage());
        
    }

    @objid ("e0dd6bcc-3408-40e2-9cc7-4c4708a124cf")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        setAbstract((Interface) objingElt);
        setLeaf((Interface) objingElt);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()) {
            setPrimitiveEAnnotation((Interface) objingElt);
        }
        
    }

    @objid ("bc95b443-af82-4c53-bd6c-d98b025cfb82")
    private void setAbstract(Interface objingElt) {
        objingElt.setIsAbstract(this.ecoreElement.isAbstract());
    }

    @objid ("20efeb63-b46a-4566-bedd-c8d564155aa5")
    private void setLeaf(Interface objingElt) {
        objingElt.setIsLeaf(this.ecoreElement.isLeaf());
    }

    @objid ("2c33dbd7-f8be-4c16-870a-b0b78f7be0f7")
    private void setPrimitiveEAnnotation(Interface objingElt) {
        objingElt.setIsElementary(ObjingEAnnotation.isPrimitive(this.ecoreElement));
    }

}
