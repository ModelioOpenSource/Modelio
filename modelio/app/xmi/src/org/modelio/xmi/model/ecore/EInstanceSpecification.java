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
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Slot;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("37e2e483-b0a7-43d3-84a4-87854e6e8afd")
public class EInstanceSpecification extends ENamedElement {
    @objid ("bbed962e-7c53-4c41-8438-1b76cbe59bf2")
    private Boolean isConnector = false;

    @objid ("cdf203c3-a553-4c4b-ab55-236c51ccc887")
    private Boolean isLink = false;

    @objid ("c7cd2f8a-a528-4830-b4a9-eff8f829ff3f")
    private Boolean isNaryLink = false;

    @objid ("70b5f791-8bbf-4d49-b30e-ff59ac675425")
    private InstanceSpecification ecoreElement = null;

    @objid ("7f121e45-bde8-4c52-bd85-18a939359351")
    @Override
    public Element createObjingElt() {
        ReverseProperties revProp = ReverseProperties.getInstance();
        IStandardModelFactory factory = revProp.getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);  
        
        if (revProp.isRoundtripEnabled()){                  
            if (ObjingEAnnotation.isConnector(this.ecoreElement)){
        
                this.isConnector = true;
                return factory.createConnector();
            }else if (ObjingEAnnotation.isLink(this.ecoreElement)){
        
                int nbEnds = this.ecoreElement.getSlots().size();
                if (nbEnds == 2){
                    this.isLink = true;
                    return factory.createLink();
                }else if (nbEnds > 2){
                    this.isNaryLink = true;
                    return factory.createNaryLink();
                }
            }
        }
        
        
        if (EcoreModelNavigation.isAssocInstance(this.ecoreElement)){
            if (EcoreModelNavigation.isConnector(this.ecoreElement)){
                this.isConnector = true;
                return factory.createConnector();
            }else  if (EcoreModelNavigation.isAssocInstance(this.ecoreElement)){
                this.isLink = true;
                return factory.createLink();
            }
        }
        return factory.createInstance();
    }

    @objid ("4e7bccd1-e03d-4393-8065-d243a4a3df44")
    public EInstanceSpecification(InstanceSpecification element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("6689de36-87c9-4300-8cfb-0cdecc634a02")
    @Override
    public void attach(Element objingElt) {
        if (!(this.isConnector) && !(this.isLink) && !(this.isNaryLink))
            attachInstance(objingElt);
    }

    @objid ("6fabeef9-4702-4477-98f2-5f6465a0a3aa")
    private void attachInstance(Element objingElt) {
        if (!(objingElt instanceof EnumerationLiteral)){
            ReverseProperties revProp = ReverseProperties.getInstance();
        
            org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
            Element owner =  (Element) revProp.getMappedElement(ecoreOwner);
        
            if ((owner != null) &&  (owner instanceof NameSpace))
                ((Instance) objingElt).setOwner((NameSpace)owner);
        
        }
    }

    @objid ("5c1b149e-d18c-4e04-9d7c-bfcc6c223754")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (objingElt instanceof Instance){
            setInstanceProperties((Instance) objingElt);    
        }else if(objingElt instanceof Link){
            setOpposite();
        }else if(objingElt instanceof NaryLink){
            setNaryEnd((NaryLink) objingElt);  
        }
    }

    @objid ("b8423da9-6d78-49b0-90f7-631b0a5e6793")
    private void setAnnotationType(Instance objingElt) {
        String typeName = ObjingEAnnotation.getType(this.ecoreElement);
        if ((typeName != null) && ( !typeName.equals(""))){
            NameSpace objType = AbstractObjingModelNavigation.getPrimitiveType(typeName);
            objingElt.setBase(objType);
        }
    }

    @objid ("b1e8ec48-a309-4132-885b-9f0bd4ef6b7a")
    private void setInstanceProperties(Instance objingElt) {
        setClassifier(objingElt);
        setValue(objingElt);
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setIsConstant( objingElt);           
            setMultiMax( objingElt);
            setMultiMin( objingElt);
            setAnnotationType(objingElt);
        }
    }

    @objid ("a35931c5-8e3a-4ed6-b0ef-f9fdbf8e6f97")
    private void setClassifier(Instance objingElt) {
        if (this.ecoreElement.getClassifiers().size() > 0){
        
            org.eclipse.uml2.uml.Element ecoreClassifier = this.ecoreElement.getClassifiers().get(0);
        
            Element objClassifier = (Element) ReverseProperties.getInstance().getMappedElement(ecoreClassifier);
        
            if ((objClassifier != null) && (objClassifier instanceof NameSpace)){
                objingElt.setBase((NameSpace) objClassifier);
            }
        
        }
    }

    @objid ("cd6e12c7-3271-4001-82cb-c207882df3e7")
    private void setIsConstant(Instance objingElt) {
        objingElt.setIsConstant(ObjingEAnnotation.isConstant(this.ecoreElement));
    }

    @objid ("4ad3192d-081a-45d9-bea2-7c5006cedb82")
    private void setMultiMax(Instance objingElt) {
        String max =  ObjingEAnnotation.getMultiMax(this.ecoreElement);
        if (max != null)
            objingElt.setMultiplicityMax(max);
    }

    @objid ("4385ea91-8ac9-44d9-85f7-30aa64432ef1")
    private void setValue(Instance objingElt) {
        org.eclipse.uml2.uml.ValueSpecification defaultValue = this.ecoreElement.getSpecification();
        if ((defaultValue != null) && (defaultValue instanceof InstanceValue)){
            InstanceSpecification spec = ((InstanceValue) defaultValue).getInstance();
            if (spec != null){
                Object instance  = ReverseProperties.getInstance().getMappedElement(spec);
                if (instance instanceof Instance)
                    try {
                        ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createDependency(
                                objingElt, (Instance) instance, IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2INSTANCEVALUE);
                    } catch (ExtensionNotFoundException e) {
                        Xmi.LOG.warning(Xmi.PLUGIN_ID, e);       
                    }
        
            }
        }else if (ReverseProperties.getInstance().isRoundtripEnabled()){
            String value =  ObjingEAnnotation.getValue(this.ecoreElement);
            if (value != null)
                objingElt.setValue(value);
        }
    }

    @objid ("cac906db-f748-4dde-b47c-710e77d6ee64")
    private void setMultiMin(Instance objingElt) {
        String min =  ObjingEAnnotation.getMultiMin(this.ecoreElement);
        if (min != null)
            objingElt.setMultiplicityMin(min);
    }

    @objid ("fb7b564d-2c15-4304-9dbc-55f0d34686b3")
    private void setOpposite() {
        List<Element> objEnds = new ArrayList<>();
        EList<Slot> slots = this.ecoreElement.getSlots();
        ReverseProperties revProp = ReverseProperties.getInstance();
        for (org.eclipse.uml2.uml.Slot slot: slots ){
            objEnds.add((Element) revProp.getMappedElement(slot));          
        }
        
        
        if ((objEnds.get(0) instanceof LinkEnd) 
                && (objEnds.get(1) instanceof LinkEnd) ){
            ((LinkEnd)objEnds.get(0)).setOpposite((LinkEnd)objEnds.get(1));
            ((LinkEnd)objEnds.get(1)).setOpposite((LinkEnd)objEnds.get(0));
        }
    }

    @objid ("6df39df1-bec8-45f3-8899-fe01923f80a7")
    private void setNaryEnd(NaryLink link) {
        EList<Slot> slots = this.ecoreElement.getSlots();
        ReverseProperties revProp = ReverseProperties.getInstance();
        for (org.eclipse.uml2.uml.Slot slot: slots ){
            NaryLinkEnd objEnds = (NaryLinkEnd) revProp.getMappedElement(slot);
            objEnds.setNaryLink(link);
        }
    }

}
