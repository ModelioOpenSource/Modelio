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

package org.modelio.xmi.model.ecore;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.xmi.reverse.PartialImportMap;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.reverse.TotalImportMap;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("b73dd019-6344-4cf1-b0c5-e51b9e565ba9")
public class EConnectorEnd extends EElement {
    @objid ("66b30de0-edbc-4a7c-98fb-e283c9bc6d28")
    private org.eclipse.uml2.uml.ConnectorEnd ecoreElement = null;

    @objid ("298860cf-bb18-41dd-9358-62802bd5539f")
    private org.eclipse.uml2.uml.ConnectableElement role = null;

    @objid ("4f7f5912-1799-4f46-aa89-017fca0c2a81")
    private Property part = null;

    @objid ("ae7db406-c1d2-46ad-8e99-b8f944f0918d")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createConnectorEnd();
    }

    @objid ("adcb266f-1733-4e4e-aec5-d364148e4d55")
    public EConnectorEnd(org.eclipse.uml2.uml.ConnectorEnd element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("460c419c-998a-43e6-8c86-6d38da7365a5")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Element owner = this.ecoreElement.getOwner(); 
        
        Object  obOwner = null;
        if (owner != null){
            obOwner = revProp.getMappedElement(owner);
            if (obOwner instanceof Link){
                ((Link) obOwner).getLinkEnd().add((LinkEnd) objingElt);
            }
        }
        
        if (((ConnectorEnd)objingElt).getOwner() == null){
            this.role = this.ecoreElement.getRole(); 
            if (this.role != null){
        
                obOwner = revProp.getMappedElement(this.role);
                this.part = this.ecoreElement.getPartWithPort(); 
        
                if (this.part != null){
                    Object obPart = revProp.getMappedElement(this.part);
                    if ((obOwner instanceof Port) && (obPart instanceof Instance)){
                        Port objOwner = (Port) obOwner;
                        objOwner.getOwnedEnd().add((LinkEnd) objingElt);
                        objOwner.setCluster((Instance) obPart);
                    }
        
                }else if (obOwner instanceof Instance) {  
        
                    Instance objOwner = (Instance) obOwner;
                    objOwner.getOwnedEnd().add((LinkEnd) objingElt);
        
                }else if (obOwner instanceof List){
        
                    boolean exist = false;
                    BindableInstance objOwner = null;
                    List<? extends Object> alist = (List<?>) obOwner;
                    for (Object objingTemp : alist){
                        if (objingTemp instanceof BindableInstance){
                            exist = true;
                            objOwner = (BindableInstance) objingTemp;
                            break;
                        }
                    }
        
                    if (!exist){
                        objOwner = revProp.getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createBindableInstance();
                        attachBindableInstance(objOwner);
                        setBindableInstanceProperties(objOwner);
                        
                        List<Object> newOwners = new ArrayList<>();
                        newOwners.addAll(alist);
                        newOwners.add(objOwner);
                        PartialImportMap.getInstance().put(this.role, newOwners);
                        TotalImportMap.getInstance().put(this.role, newOwners);
                    }
        
                    if (objOwner != null)
                        objOwner.getOwnedEnd().add((LinkEnd) objingElt);
        
                }else{
                    objingElt.delete();
                }
            }else{
                objingElt.delete();
            }
        }
    }

    @objid ("09092020-adc1-496f-a848-6e4faf2ff9fb")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        setOpposite((LinkEnd) objingElt);
        setIsOrdered((LinkEnd) objingElt);
        setIsUnique((LinkEnd) objingElt);
        setMax((LinkEnd) objingElt);
        setMin((LinkEnd) objingElt);
        
        if ( ReverseProperties.getInstance().isRoundtripEnabled()){
            setName((LinkEnd) objingElt); 
            setIsNavigable();
        }
    }

    @objid ("1f69600f-ff02-4342-8eb0-c2484a2bb8fe")
    private void setName(LinkEnd linkEnd) {
        linkEnd.setName(ObjingEAnnotation.getName(this.ecoreElement));
    }

    @objid ("05f620a8-a876-4165-8844-a6823b2882c4")
    private void setIsNavigable() {
        org.eclipse.uml2.uml.Element owner = this.ecoreElement.getOwner();
        if (owner instanceof org.eclipse.uml2.uml.Connector) {
            org.eclipse.uml2.uml.Connector connector = (org.eclipse.uml2.uml.Connector) owner;
            ReverseProperties revProp = ReverseProperties.getInstance();
            for (org.eclipse.uml2.uml.ConnectorEnd connectorEnd : connector.getEnds()) {
                Object cEnd = revProp.getMappedElement(connectorEnd);
                if (cEnd instanceof LinkEnd) {
                    ((LinkEnd) cEnd).setNavigable(ObjingEAnnotation.isNavigable(connectorEnd));
                }
            }
        }
    }

    @objid ("97add917-096f-4774-b92e-c417f9d34d45")
    private void setIsOrdered(LinkEnd linkEnd) {
        linkEnd.setIsOrdered(this.ecoreElement.isOrdered());
    }

    @objid ("729aaa99-835c-4fee-b7c6-69ea6385c25e")
    private void setIsUnique(LinkEnd linkEnd) {
        linkEnd.setIsUnique(this.ecoreElement.isUnique());
    }

    @objid ("180ea1b8-95a1-4d0c-ae07-dbe6f3b10e54")
    private void setMin(LinkEnd objingElt) {
        objingElt.setMultiplicityMin(EcoreModelNavigation.getMultiplicityMin(this.ecoreElement));
    }

    @objid ("24b56299-c56a-4253-a748-ad64d94e3581")
    private void setMax(LinkEnd objingElt) {
        objingElt.setMultiplicityMin(EcoreModelNavigation.getMultiplicityMax(this.ecoreElement));
    }

    @objid ("0f1ad3c7-1a39-4422-8af0-90101d44eb71")
    private void setBindableInstanceProperties(Instance objingElt) {
        setName(objingElt);
        setBase(objingElt);
        
        if ( ReverseProperties.getInstance().isRoundtripEnabled()){
            setIsConstant(objingElt);
            setValue(objingElt);
            setMultiMax(objingElt);
            setMultiMin(objingElt);
        }
    }

    @objid ("204ff8e8-81ca-4a9b-aeb4-a942bedcdde9")
    private void setName(Instance objingElt) {
        String  name = this.role.getName();
        if (name != null){
            objingElt.setName(name);
        }
    }

    @objid ("0439e18b-c8e2-4ac5-aa4a-8c6d87a1aa58")
    private void setBase(Instance objingElt) {
        org.eclipse.uml2.uml.Type type = this.role.getType();
        if (type != null){
            Element base =(Element)  ReverseProperties.getInstance().getMappedElement(type);
            if (base instanceof NameSpace)
                objingElt.setBase((NameSpace) base);
        }
    }

    @objid ("5077bc9c-6963-4216-80da-93f8051496ed")
    private void setIsConstant(Instance objingElt) {
        objingElt.setIsConstant(ObjingEAnnotation.isConstant(this.role));
    }

    @objid ("d47ef2a5-cb5e-47d6-8bf9-bbea91da0ad5")
    private void setMultiMax(Instance objingElt) {
        String value =  ObjingEAnnotation.getMultiMax(this.role);
        if (value != null)
            objingElt.setMultiplicityMax(value);
    }

    @objid ("66cd2922-a5c6-4c5f-a2be-41c9b5e22748")
    private void setValue(Instance objingElt) {
        String value =  ObjingEAnnotation.getValue(this.role);
        if (value != null)
            objingElt.setValue(value);
    }

    @objid ("28452a29-af28-4d62-8edb-f2cef46aea4e")
    private void setMultiMin(Instance objingElt) {
        String value =  ObjingEAnnotation.getMultiMin(this.role);
        if (value != null)
            objingElt.setMultiplicityMin(value);
    }

    @objid ("830efd41-8bf7-4252-81e6-ac6a264ef2dc")
    private void attachBindableInstance(BindableInstance objingElt) {
        // The objing element is an BindableInstance
        
        org.eclipse.uml2.uml.Element ecoreOwner = this.role.getOwner();
        
        Element objingOwner = (Element)  ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
        Boolean attached = false ;
        
        if ( ReverseProperties.getInstance().isRoundtripEnabled()){
        
            if (ObjingEAnnotation.isDeleted(this.role)){
                attached = true;
                objingElt.delete();
        
            }else {
                String ownerID = ObjingEAnnotation.getOwner(this.role);  
        
                if ((ownerID != null) && (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredClassifier)){
        
                    for (Object attribute : ((org.eclipse.uml2.uml.StructuredClassifier) ecoreOwner).getOwnedAttributes()){
                        if (attribute instanceof Property){
                            Property prop = (Property) attribute;
                            List<String> ids =  ObjingEAnnotation.getObjingIDs(prop);
                            if (ids.size() > 0){
                                String id =ids.get(0);
                                if (id.equals(ownerID)){
                                    ((BindableInstance)  ReverseProperties.getInstance().getMappedElement(prop)).getPart().add(objingElt);
                                    attached = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        if (!attached){ 
            if (objingOwner instanceof Classifier){
                ((Classifier) objingOwner).getInternalStructure().add(objingElt);
            }else if ((objingOwner instanceof Collaboration))
                objingElt.setOwner((Collaboration) objingOwner);
            else if ((objingOwner instanceof Interaction)){
                List<?> collabList =  ((Interaction) objingOwner).getOwnedCollaboration();
                Collaboration collab = null;
                if (!collabList.isEmpty()){
                    collab = ((Collaboration)collabList.get(0));
                }else{
                    collab = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCollaboration();
                    collab.setName("locals");
                    ((Interaction) objingOwner).getOwnedCollaboration().add(collab);
                }
        
                if (collab.getCompositionOwner() instanceof Behavior)
                    collab.getDeclared().add(objingElt);
                else
                    collab.getRepresentingInstance().add(objingElt);
            }
        }
    }

    @objid ("0373a739-dcf6-4e72-b18c-e283bdea0df6")
    private void setOpposite(LinkEnd objingElt) {
        Link link = objingElt.getLink();
        if (link.getLinkEnd().size() == 2){
            link.getLinkEnd().get(0).setOpposite(link.getLinkEnd().get(1));
            link.getLinkEnd().get(1).setOpposite(link.getLinkEnd().get(0));
        }
    }

}
