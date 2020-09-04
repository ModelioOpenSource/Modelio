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

package org.modelio.xmi.model.objing;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("ae6d8d6d-3eec-4158-ad45-7339669fa4a8")
public class OBinding extends OElement implements IOElement {
    @objid ("6706211b-caad-4a03-8645-ae282f977e5b")
    private Binding objElt = null;

    @objid ("718b60a3-ccb6-4374-9423-ff8344c4784e")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createDependency();
    }

    @objid ("117d587e-a45f-45c1-9489-60c621cfc17b")
    public OBinding(Binding param) {
        super(param);
        this.objElt = param;
    }

    @objid ("2435240c-7aec-4476-907c-ea638adbcf48")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        List<MObject> objingClientList = new ArrayList<>();
        
        org.eclipse.uml2.uml.Dependency ecoreDependency = (org.eclipse.uml2.uml.Dependency) ecoreElt;
        
        MObject objingClientTemp = this.objElt.getConnectorEndRole();
        
        if (objingClientTemp!= null){
            objingClientTemp = objingClientTemp.getCompositionOwner();
            if (objingClientTemp!= null){
                objingClientList.add(objingClientTemp);
            }
        }
        
        objingClientTemp = this.objElt.getConnectorRole();
        
        if (objingClientTemp!= null){
            objingClientList.add(objingClientTemp);
        }
        
        objingClientTemp = this.objElt.getRole();
        
        if (objingClientTemp!= null){
            objingClientList.add(objingClientTemp);
        }
        
        if (objingClientList.size() > 0) {
            for (MObject  objingClient : objingClientList){
        
                org.eclipse.uml2.uml.Element ecoreClient =  genProp.getMappedElement(objingClient);
        
                if ((ecoreClient != null) &&  (ecoreClient instanceof org.eclipse.uml2.uml.NamedElement)) {
        
                    if  (! (ecoreDependency.getClients().contains(ecoreClient)))
                        try{
                            ecoreDependency.getClients().add((org.eclipse.uml2.uml.NamedElement)ecoreClient);
                        }catch(ArrayStoreException e){
                            Xmi.LOG.error(e);
                        }
                }
            }
        }
        
        ModelElement objingSupplier = this.objElt.getRepresentedFeature();
        org.eclipse.uml2.uml.Element ecoreSupplier = null;
        
        if (objingSupplier != null){
            // Gets or creates the ecore "Supplier" element:
                ecoreSupplier = genProp.getMappedElement(objingSupplier);
        }
        
        
        CollaborationUse objingCollaboration = (CollaborationUse) this.objElt.getCompositionOwner();
        
         org.eclipse.uml2.uml.CollaborationUse ecoreCollaboration =  (org.eclipse.uml2.uml.CollaborationUse) genProp.getMappedElement(objingCollaboration);
        
        if ((ecoreCollaboration != null) &&
                ((ecoreSupplier != null) && (ecoreSupplier instanceof org.eclipse.uml2.uml.NamedElement ))){
            ecoreDependency.getClients().add(ecoreCollaboration);
        
            ecoreCollaboration.getRoleBindings().add(ecoreDependency);
            ecoreDependency.getSuppliers().add((org.eclipse.uml2.uml.NamedElement)ecoreSupplier);
        }else{
            ecoreDependency.destroy();
        }
    }

    @objid ("10bddb4c-1cab-4bc8-aafd-9193ebc1abfa")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(this.objElt.getCompositionOwner());
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.CollaborationUse){
            ( (org.eclipse.uml2.uml.CollaborationUse) ecoreOwner).getRoleBindings().add((org.eclipse.uml2.uml.Dependency)ecoreElt);
        }
    }

}
