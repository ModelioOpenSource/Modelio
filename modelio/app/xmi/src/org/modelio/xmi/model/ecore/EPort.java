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
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.reverse.TotalImportMap;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("59b5c0fc-40c2-429c-911e-b256042122e0")
public class EPort extends EFeature {
    @objid ("3528f06a-2fbc-437f-a365-6e6af39d4ce9")
    private org.eclipse.uml2.uml.Port ecoreElement = null;

    @objid ("6200d93c-be5b-4f14-829a-3c7d8b75bb81")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createPort();
    }

    @objid ("93dd9be2-74f3-4860-a5f3-6fb860b89187")
    public EPort(org.eclipse.uml2.uml.Port element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("7b04dc9d-5e18-462e-9801-9fc079157078")
    @Override
    public void attach(Element objingElt) {
        AbstractObjingModelNavigation.attachPort(objingElt, this.ecoreElement);
    }

    @objid ("525e00de-a5b9-4dc2-bc72-97821d3a2c6e")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        setBehavior((Port) objingElt);
        setService((Port) objingElt);
        setRequiredInterfaces((Port) objingElt);
        setProvidedInterfaces((Port) objingElt);
        setType((Port) objingElt);
        setEnd((Port) objingElt);
        setRedefinedPort((Port)objingElt);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setInterfaces((Port) objingElt);
            setPortDirection((Port) objingElt);
        }
    }

    @objid ("97fac66e-4f7a-4845-bca5-c7d6ff63baa3")
    private void setType(Port objingElt) {
        org.eclipse.uml2.uml.Type type = this.ecoreElement.getType();
        if (type != null) {
            Element objType = (Element) ReverseProperties.getInstance().getMappedElement(type);
            if (objType instanceof NameSpace)
                objingElt.setBase((NameSpace) objType);
        
        
            if (type instanceof Interface){
                boolean found = false; 
                for (ProvidedInterface provided : objingElt.getProvided()){
                    if ((provided.getProvidedElement().size() == 1) && (provided.getProvidedElement().get(0).equals(objType))){
                        found = true;
                    }
                }
        
                if (!found) {
                    ProvidedInterface provider =  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createProvidedInterface();
        
                    objingElt.getProvided().add(provider);
                    provider.setProviding(objingElt);
                    provider.getProvidedElement().add((Interface)type);
        
                }
            }
        }
    }

    @objid ("f627d573-5a3a-4330-a4f2-08b6c425d906")
    private void setInterfaces(Port objingElt) {
        Integer temp = ObjingEAnnotation.getNumberProvidedInterface(this.ecoreElement);
        for (int i = 0; i < temp; i++){
            ProvidedInterface providedInterface = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createProvidedInterface();
            providedInterface.setProviding(objingElt);
            objingElt.getProvided().add(providedInterface);
        }
        
        temp = ObjingEAnnotation.getNumberRequiredInterface(this.ecoreElement);
        for (int i = 0; i < temp; i++){
            RequiredInterface requiredInterface = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createRequiredInterface();
            requiredInterface.setRequiring(objingElt);
            objingElt.getRequired().add(requiredInterface);
        }
    }

    @objid ("d2525799-cea9-4920-85d2-44747c2460a0")
    private void setProvidedInterfaces(Port objingElt) {
        org.eclipse.uml2.uml.Type type = this.ecoreElement.getType();
        if (type != null) {
            if (type instanceof org.eclipse.uml2.uml. BehavioredClassifier){
                org.eclipse.uml2.uml. BehavioredClassifier behaClass = (org.eclipse.uml2.uml.BehavioredClassifier)type;
                for (Object object : behaClass.getInterfaceRealizations()){
                    if (object instanceof org.eclipse.uml2.uml.InterfaceRealization){
                        org.eclipse.uml2.uml.InterfaceRealization interfaceRealization = (org.eclipse.uml2.uml.InterfaceRealization) object;
                        Object temp = ReverseProperties.getInstance().getMappedElement((interfaceRealization).getContract());
                        if (temp instanceof Interface){
                            createProvidedInterface(objingElt, (Interface) temp);
                        }
                    }
                }
            }else if (type instanceof org.eclipse.uml2.uml.Interface){
                Object temp = ReverseProperties.getInstance().getMappedElement(type);
                if (temp instanceof Interface){
                    createProvidedInterface(objingElt, (Interface) temp);
                }
            }
        
        }
    }

    @objid ("dd5aac85-cde4-404d-a72a-d2555202e16e")
    private void setRequiredInterfaces(Port objingElt) {
        org.eclipse.uml2.uml.Type type = this.ecoreElement.getType();
        if (type != null) {
            setRequiredInterface(objingElt, type);
            if (type instanceof org.eclipse.uml2.uml.Classifier){
                for (Object superType : ( (org.eclipse.uml2.uml.Classifier)type).getGenerals()){
                    setRequiredInterface(objingElt,  (org.eclipse.uml2.uml.Type) superType);
                }
            }
        }
    }

    @objid ("e760697d-85ac-4e4c-918b-6f45c419f2f0")
    private void setRequiredInterface(Port objingElt, org.eclipse.uml2.uml.Type type) {
        for (Object object : type.getSourceDirectedRelationships()){
            if (object instanceof org.eclipse.uml2.uml.Usage){
                org.eclipse.uml2.uml.Usage usage = (org.eclipse.uml2.uml.Usage) object;
                List<UmlModelElement> usages = new ArrayList<>();
        
                for (Object supplier : usage.getSuppliers()){
                    Object objSupplier =  ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)supplier);
                    if (objSupplier instanceof Interface){
                        RequiredInterface requiredInterface = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createRequiredInterface();
                        requiredInterface.setRequiring(objingElt);
                        requiredInterface.getRequiredElement().add((Interface) objSupplier);
                        objingElt.getRequired().add(requiredInterface);
                        usages.add(requiredInterface);
                    }
                }
        
                TotalImportMap.getInstance().put(usage, usages);
            }
        
        }
    }

    @objid ("e91d10dd-665b-4372-866b-a9adb3f8828f")
    private void setEnd(Port objingElt) {
        for (Object end : this.ecoreElement.getEnds()){
            if (end instanceof org.eclipse.uml2.uml.ConnectorEnd){
                org.eclipse.uml2.uml.ConnectorEnd connectorEnd  = (org.eclipse.uml2.uml.ConnectorEnd) end;
                Property prop = connectorEnd.getPartWithPort();
                if ( prop != null ){
                    Port portClone = clonePort(objingElt, prop);
        
                    Object objConnectorEnd =  ReverseProperties.getInstance().getMappedElement(connectorEnd);
                    if (objConnectorEnd instanceof ConnectorEnd)
                        portClone.getOwnedEnd().add((ConnectorEnd)objConnectorEnd);
                }
            }
        }
    }

    @objid ("a421f23b-0683-4ae3-a7af-4c4747dd8a4f")
    private Port clonePort(final Port objingElt, final Property prop) {
        Port result = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createPort();
        result.setRepresentedFeature(objingElt);
        attachClone(result, prop);
        
        //        IModelTool modelTool = MTools.getModelTool();
        //        for(ProvidedInterface provided : objingElt.getProvided()){
        //            result.getProvided().add((ProvidedInterface) modelTool.cloneElement(provided) );
        //        }
        //        
        //        for(RequiredInterface required : objingElt.getRequired()){
        //            result.getRequired().add((RequiredInterface)   modelTool.cloneElement(required) );
        //        }
        
        result.setIsBehavior(objingElt.isIsBehavior());
        result.setIsService(objingElt.isIsService());
        result.setName(objingElt.getName());
        result.setBase(objingElt.getBase());
        // set Instance properties
        result.setValue(objingElt.getValue());
        result.setMultiplicityMax(objingElt.getMultiplicityMax());
        result.setMultiplicityMin(objingElt.getMultiplicityMin());
        
        result.setIsConstant(objingElt.isIsConstant());
        return result;
    }

    @objid ("7c77f175-566d-4ab5-95d7-ccceeaa4ec88")
    private void setRedefinedPort(final Port objingElt) {
        EList<org.eclipse.uml2.uml.Port> redefinedPorts = this.ecoreElement.getRedefinedPorts();
        
        if ((redefinedPorts != null) && (redefinedPorts.size() > 0)){
            Object redefinedPort = ReverseProperties.getInstance().getMappedElement(redefinedPorts.get(0));
            if (redefinedPort instanceof UmlModelElement){
                objingElt.setRepresentedFeature((UmlModelElement) redefinedPort);
            }
        }
    }

    @objid ("ae38753d-6bac-47c0-b0e8-f87f685b1f5e")
    private void setBehavior(final Port objingElt) {
        objingElt.setIsBehavior(this.ecoreElement.isBehavior());
    }

    @objid ("cd6c88c8-2f4b-42ad-a101-37cbcbf0f138")
    private void setService(final Port objingElt) {
        objingElt.setIsService(this.ecoreElement.isService());
    }

    @objid ("f2287c79-3d39-4763-8735-efcb9088e769")
    private void setPortDirection(final Port objingElt) {
        try{
            objingElt.setDirection(PortOrientation.get(ObjingEAnnotation.getPortDirection(this.ecoreElement)));
        }catch(IllegalArgumentException e){
            Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
        }
    }

    @objid ("84b38f51-e6d8-416d-a0d8-49b67cff782a")
    private void createProvidedInterface(Port port, Interface inter) {
        ProvidedInterface providedInterface = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createProvidedInterface();
        providedInterface.setProviding(port);
        providedInterface.getProvidedElement().add(inter);
        port.getProvided().add(providedInterface);
    }

    @objid ("c54eaed4-f976-4fab-8ac9-2451b0e2c678")
    private void attachClone(Port portClone, Property prop) {
        Object objOwner =  ReverseProperties.getInstance().getMappedElement(prop);
        
        if (objOwner instanceof Instance) {       
            ((Instance) objOwner).getPart().add(portClone);
        }else if (objOwner instanceof List<?>){
        
            for (Object objElt : (List<?>) objOwner){
                if (objElt instanceof Instance)
                    ((Instance) objElt).getPart().add(portClone);
            }       
        }
    }

}
