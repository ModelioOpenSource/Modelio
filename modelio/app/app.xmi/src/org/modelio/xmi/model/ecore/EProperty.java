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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.EcorePrimitiveTypeMapper;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;

/**
 * This class manages the import of UML Property elements
 * @author ebrosse
 */
@objid ("5c942ba9-9bc1-45fb-b626-1f61d56f9432")
public class EProperty extends EStructuralFeature {
    @objid ("02e84f41-51c9-4d37-ad0f-6c23ab6de794")
    @Override
    public List<UmlModelElement> createObjingElt() {
        Property ecoreElement = (Property) getEcoreElement();
        
        if (ecoreElement.getOwner() instanceof org.eclipse.uml2.uml.Stereotype){
            ProfileUtils.visitProperty(ecoreElement);
        }else{
            List<UmlModelElement> result = new ArrayList<>();
        
            IStandardModelFactory factory = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        
            // association
            if (EcoreModelNavigation.isAssocEnd(ecoreElement)) {
        
                int endNumber = EcoreModelNavigation.getValidEndNumber(ecoreElement.getAssociation());
        
                if (endNumber == 2 ){
                    result.add(factory.createAssociationEnd());
                }else  if (endNumber > 2 ){
                    result.add(factory.createNaryAssociationEnd());
                }
            }
        
            if (EcoreModelNavigation.isPart(ecoreElement)){
                BindableInstance inst = factory.createBindableInstance();
                if (EcoreModelNavigation.isAssocEnd(ecoreElement)){
                    inst.setRepresentedFeature(result.get(0));
                }
                result.add(inst);
            } else if (EcoreModelNavigation.isPort(ecoreElement)){
                result.add( factory.createPort());
            }
        
            if (EcoreModelNavigation.isConnectorEnd(ecoreElement)){
                result.add(factory.createBindableInstance());
            }
        
            if (result.size() == 0 ){
                org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        
                if (ecoreOwner != null){
                    Object objingOwner = ReverseProperties.getInstance().getMappedElement(ecoreOwner);
                    if (objingOwner != null){
                        // case qualifier
                        if ((objingOwner instanceof Classifier) ||
                                ((ecoreOwner instanceof Property)
                                        && (EcoreModelNavigation.isAssocEnd((Property)ecoreOwner)))){
                            //case predefined type
                            if (!((ecoreOwner instanceof Type) && (EcorePrimitiveTypeMapper.isPredefinedType((Type)ecoreOwner)))){
                                result.add(factory.createAttribute());
                            }
                        }else{
                            result.add(factory.createBindableInstance());
                        }
                    }
                }
            }
            return result;
        }
        return null;
    }

    @objid ("aea527ae-2968-4862-a929-0e3afe7a7dc0")
    public EProperty(Property element) {
        super(element);
    }

    @objid ("5679ed55-c778-4f48-ba49-b91d81999fc3")
    @Override
    public void attach(Element objingElt) {
        Property ecoreElement = ((Property) getEcoreElement());
        if (!(ecoreElement.getOwner() instanceof org.eclipse.uml2.uml.Stereotype)){
            if (objingElt instanceof Attribute){
                attachAttribute((Attribute) objingElt);
            } else if ((EcoreModelNavigation.isAssocEnd(ecoreElement))
                    && !(objingElt instanceof Attribute)) {
        
                if (EcoreModelNavigation.isPart(ecoreElement)){
        
                    if (objingElt instanceof BindableInstance){
                        attachBindableInstance(objingElt);
                    }
        
                }else{
        
                    if (objingElt instanceof BindableInstance){
                        attachBindableInstance(objingElt);
                    }
        
                }
        
            } else if ((objingElt instanceof BindableInstance)
                    && !(objingElt instanceof Port)){
                attachBindableInstance(objingElt);
            }else{
                objingElt.delete();
            }
        }else{
            objingElt.delete();
        }
    }

    @objid ("7c9ae24e-cadd-4129-b7aa-5ba8452ff4e2")
    @Override
    public void setProperties(Element objingElt) {
        if (!(((Property) getEcoreElement()).getOwner() instanceof org.eclipse.uml2.uml.Stereotype)){
        
            super.setProperties(objingElt);
            if (objingElt instanceof StructuralFeature)
                setFeature( (StructuralFeature) objingElt);
            else if (objingElt instanceof BindableInstance){
                BindableInstance part = (BindableInstance) objingElt;
                setBase(part);
                UmlModelElement represented = part.getRepresentedFeature();
                if ((represented != null) && (represented instanceof AssociationEnd)){
                    setFeature((StructuralFeature) represented);
                }
            }
        }
    }

    @objid ("6e4bfc73-fe60-42d4-8032-c16a8fa790a1")
    private void setFeature(StructuralFeature structFeature) {
        boolean roundTripEnable = ReverseProperties.getInstance().isRoundtripEnabled();
        
        if  (structFeature instanceof AssociationEnd) {
            AssociationEnd assocEnd = (AssociationEnd) structFeature;
            setAssociationKind(assocEnd);
            setNavigation(assocEnd);
            setChangeable(assocEnd);
            if (roundTripEnable) {
                setAccessModeEAnnotation(assocEnd);
            }
        
        } else if (structFeature instanceof Attribute) {
            Attribute att = (Attribute) structFeature;
            setType(att);
            setValue(att);
        
            if (roundTripEnable) {
                setTargetIsClassEAnnotation(att);
                setTypeConstraint(att);
                setValueEAnnotation(att);
            }
        } else if (structFeature instanceof NaryAssociationEnd) {
            NaryAssociationEnd naryAssocEnd = (NaryAssociationEnd) structFeature;
            setOwner(naryAssocEnd);
            setNaryAssociationKind(naryAssocEnd);
        }
    }

    @objid ("10535903-9334-4b5b-9307-8a1bbe521731")
    private void setNavigation(AssociationEnd objEnd) {
        AssociationEnd objOppositeEnd = null;
        Property currentEnd = ((Property) getEcoreElement());
        Property oppositeEnd = currentEnd.getOtherEnd();
        
        Object objEnds = ReverseProperties.getInstance().getMappedElement(oppositeEnd);
        if (objEnds instanceof List<?>) {
            List<?> temps = (List<?>) objEnds;
            for (Object temp : temps){
                if (temp instanceof AssociationEnd){
                    objOppositeEnd = (AssociationEnd) temp;
                    break;
                }
            }
        }
        
        if(objOppositeEnd != null){
        
            if(currentEnd.isNavigable()){
                objEnd.setTarget((Classifier)  ReverseProperties.getInstance().getMappedElement(currentEnd.getType())) ;
                objEnd.setOpposite(objOppositeEnd);
                setSource(objEnd, oppositeEnd);
        
            }else{
                objEnd.setOpposite(objOppositeEnd);
        
                if (!oppositeEnd.isNavigable()){
                    setSource(objEnd, oppositeEnd);
                    objEnd.setOpposite(objOppositeEnd);
                }
            }
        
            Association objAssoc = null;
            org.eclipse.uml2.uml.Association assoc = currentEnd.getAssociation();
        
            if (assoc instanceof AssociationClass){
                objAssoc = ((org.modelio.metamodel.uml.statik.Class) ReverseProperties.getInstance().getMappedElement(currentEnd.getAssociation())).getLinkToAssociation().getAssociationPart();
            }else{
                objAssoc = (Association)  ReverseProperties.getInstance().getMappedElement(assoc);
            }
        
            objEnd.setAssociation(objAssoc);
        }else{
            objEnd.delete();
        }
    }

    @objid ("23bd6341-e7ed-4e88-9cf1-d82e92c1f149")
    private void setType(Attribute objingElt) {
        org.eclipse.uml2.uml.Type ecoreType = ((Property) getEcoreElement()).getType();
        
        if (ecoreType != null) {
            // If the Attribute is typed by another element, we get this
            // org.eclipse.uml2.uml.Classifier:
            Object objingType = ReverseProperties.getInstance().getMappedElement(ecoreType);
            if ((objingType != null) && (objingType instanceof GeneralClass))
                objingElt.setType((GeneralClass) objingType);
        }else{
            objingElt.setType(null);
        }
    }

    @objid ("1c934eb7-6fab-4293-b397-c33abf2a7df0")
    private void setAssociationKind(AssociationEnd end) {
        org.eclipse.uml2.uml.AggregationKind ecoreAggType = ((Property) getEcoreElement()).getAggregation();
        if (ecoreAggType != null) {
            switch (ecoreAggType.getValue()) {
            case org.eclipse.uml2.uml.AggregationKind.NONE:
                end.setAggregation(AggregationKind.KINDISASSOCIATION);
                break;
            case org.eclipse.uml2.uml.AggregationKind.SHARED:
                end.setAggregation(AggregationKind.KINDISAGGREGATION);
                break;
            case org.eclipse.uml2.uml.AggregationKind.COMPOSITE:
                end.setAggregation(AggregationKind.KINDISCOMPOSITION);
                break;
            default:
                break;
            }
        }
    }

    @objid ("3a3fcf74-1097-4d70-84cd-084a31bb377d")
    private void setChangeable(AssociationEnd objingElt) {
        objingElt.setIsChangeable(((Property) getEcoreElement()).isReadOnly());
    }

    @objid ("18a5d2b8-58cb-4c0d-92cc-23e9fbaca1a7")
    private void setValueEAnnotation(Attribute attribute) {
        String value = ObjingEAnnotation.getValue(getEcoreElement());
        if ((value != null) && (!value.equals("")))
            attribute.setValue(value);
    }

    @objid ("50789a5d-e4c1-455d-a3e1-0d3d6beb3842")
    private void setAccessModeEAnnotation(AssociationEnd objingElt) {
        String access = ObjingEAnnotation.getAccessMode(getEcoreElement());
        
        if (access != null){
            if (access.equals(ObjingEAnnotation.READ_VALUE))
                objingElt.setChangeable(KindOfAccess.READ);
            else if (access.equals(ObjingEAnnotation.WRITE_VALUE))
                objingElt.setChangeable(KindOfAccess.WRITE);
        
            else if (access.equals(ObjingEAnnotation.READ_WRITE_VALUE))
                objingElt.setChangeable(KindOfAccess.READWRITE);
            else if (!((Property) getEcoreElement()).isReadOnly())
                objingElt.setChangeable(KindOfAccess.ACCESNONE);
        }
    }

    @objid ("b74bb45f-0314-4466-a789-de7ddebcaf35")
    private void setTargetIsClassEAnnotation(Attribute attribute) {
        attribute.setTargetIsClass(ObjingEAnnotation
                .isTargetIsClass(getEcoreElement()));
    }

    @objid ("de8fc352-0d90-4891-bb92-45d895e07ba5")
    private void setTypeConstraint(Attribute attribute) {
        String typeConstraint = ObjingEAnnotation.getTypeConstraint(getEcoreElement());
        if (typeConstraint != null)
            attribute.setTypeConstraint(typeConstraint);
    }

    @objid ("abb7b465-98e1-4889-a667-fa19d11c43a3")
    private void setValue(Attribute attribute) {
        String defaultValue = ((Property) getEcoreElement()).getDefault();
        
        if (defaultValue != null) {
            attribute.setValue(defaultValue);
        }else{
            org.eclipse.uml2.uml.ValueSpecification value = ((Property) getEcoreElement()).getDefaultValue();
        
            if (value instanceof InstanceValue){
                InstanceSpecification spec = ((InstanceValue) value).getInstance();
                if (spec != null){
                    Object instance = ReverseProperties.getInstance().getMappedElement(spec);
                    if ((instance instanceof Instance)) {
                        try {
                            ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createDependency(
                                    attribute, (Instance) instance, IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2INSTANCEVALUE);
                        } catch (ExtensionNotFoundException e) {
                            Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
                        }
                    }
                }
            }else{
                attribute.setValue(EcoreModelNavigation.getValue(value));
            }
        }
    }

    @objid ("d608a602-2862-46c0-9e2e-0af5579c55e0")
    private void attachAttribute(Attribute objingAttribute) {
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        Object objingOwner = ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
        if (objingOwner instanceof Classifier)
            objingAttribute.setOwner((Classifier) objingOwner);
        else if ((ecoreOwner instanceof Property)
                && (EcoreModelNavigation.isAssocEnd((Property) ecoreOwner))) {
        
            if (objingOwner instanceof List<?>) {
                for (Object objElt : (List<?>) objingOwner){
                    if (objElt instanceof AssociationEnd)
                        objingAttribute.setQualified((AssociationEnd) objElt);
                }
            }
        
        }
    }

    @objid ("d598d38c-7c23-414b-a0d7-22dd63ad828b")
    private void attachBindableInstance(Element objingElt) {
        if (!(objingElt instanceof Port)){
            // The objing element is an BindableInstance
        
            Property ecoreElement = ((Property) getEcoreElement());
            BindableInstance objingAttribute = (BindableInstance) objingElt;
        
            org.eclipse.uml2.uml.Element ecoreOwner = ecoreElement.getOwner();
        
            if (ecoreOwner instanceof Property){
                while (ecoreOwner instanceof Property){
                    ecoreOwner = ecoreOwner.getOwner();
                }
            }else if (ecoreOwner instanceof org.eclipse.uml2.uml.Association){
                ecoreOwner = ecoreElement.getType();
            }
        
            Element objingOwner = (Element) ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
            Boolean attached = false ;
        
            if (ReverseProperties.getInstance().isRoundtripEnabled()){
        
                if (ObjingEAnnotation.isDeleted(ecoreElement)){
                    attached = true;
                    objingElt.delete();
        
                }else {
                    String ownerID = ObjingEAnnotation.getOwner(ecoreElement);
        
                    if ((ownerID != null) && ( !ownerID.equals(""))){
        
                        if (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredClassifier)
                            for (Object attribute : ((org.eclipse.uml2.uml.StructuredClassifier) ecoreOwner).getOwnedAttributes()){
                                if (attribute instanceof Property){
                                    Property prop = (Property) attribute;
                                    List<String> ids = ObjingEAnnotation.getObjingIDs(prop);
                                    if (ids.size() > 0){
                                        String id = ids.get(0);
                                        if (id.equals(ownerID)){
                                            if ((((BindableInstance) objingElt).getInternalOwner()== null) && (((BindableInstance) objingElt).getOwner() == null))
                                                objingAttribute.setCluster(((BindableInstance) ReverseProperties.getInstance().getMappedElement(prop)));
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
                    if (objingAttribute.getCluster() == null)
                        objingAttribute.setInternalOwner((Classifier) objingOwner);
        
                }else if ((objingOwner instanceof Collaboration)){
                    objingAttribute.setOwner((Collaboration) objingOwner);
                }else if ((objingOwner instanceof Interaction)){
                    List<?> collabList =  ((Interaction) objingOwner).getOwnedCollaboration();
                    Collaboration collab = null;
                    if (!collabList.isEmpty()){
                        collab = ((Collaboration)collabList.get(0));
                    }else{
                        collab = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCollaboration();
                        collab.setName("locals");
                        ((Interaction) objingOwner).getOwnedCollaboration().add(collab);
                    }
        
                    objingAttribute.setOwner(collab);
                }else{
                    objingAttribute.delete();
                }
            }
        }
    }

    @objid ("b016659d-952e-46d1-858c-407bac359307")
    private void setOwner(NaryAssociationEnd objingElt) {
        Type type = ((Property) getEcoreElement()).getType();
        Object classifier = ReverseProperties.getInstance().getMappedElement(type);
        if (classifier instanceof Classifier){
            attachClassifier((Classifier) classifier, type);
            objingElt.setOwner((Classifier) classifier);
        }
    }

    @objid ("09f2cb4e-44ed-453a-a64b-7dbd35313ed3")
    private void setNaryAssociationKind(NaryAssociationEnd objingElt) {
        Object naryassociation = ReverseProperties.getInstance().getMappedElement(((Property) getEcoreElement()).getAssociation());
        if (naryassociation instanceof NaryAssociation){
            objingElt.setNaryAssociation((NaryAssociation) naryassociation);
            ((NaryAssociation) naryassociation).getNaryEnd().add(objingElt);
        }
    }

    @objid ("63905105-e1a2-4868-b9ce-2fa3a3bea72b")
    private void attachClassifier(Classifier objingClass, Type type) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Element ecoreOwner = type.getOwner();
        
        if (ecoreOwner != null) {
        
            Object objingOwner =  revProp.getMappedElement(ecoreOwner);
            if (objingOwner instanceof Profile) {
                objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage());
            } else if (objingOwner instanceof Node) {
                //rule R44
                objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage());
            } else if (objingOwner instanceof TemplateParameter) {
                objingClass.setOwnerTemplateParameter((TemplateParameter) objingOwner);
            } else if (objingOwner instanceof ModelTree) {
                objingClass.setOwner((ModelTree) objingOwner);
            } else {
                objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage());
            }
        } else {
            objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage());
        }
    }

    @objid ("5f2daf59-3b20-4568-a4c7-6f8feb501e9e")
    private void setSource(AssociationEnd objEnd, Property oppositeEnd) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        Object type = revProp.getMappedElement(oppositeEnd.getType());
        
        if (type instanceof Classifier){
            Classifier classifier = (Classifier) type;
            if (classifier.getCompositionOwner() == null)
                classifier.setOwner(revProp.getExternalPackage());
        
            objEnd.setSource(classifier);
        }
    }

    @objid ("5a11fcaf-0d8c-4521-8709-de281c7c3cab")
    private void setBase(BindableInstance objingElt) {
        Type type =  this.getEcoreElement().getType();
        if (type != null){
            Element base = (Element) ReverseProperties.getInstance().getMappedElement(type);
            if (base instanceof NameSpace){
                objingElt.setBase((NameSpace) base);
            }
        }
    }

}
