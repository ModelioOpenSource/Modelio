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
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.StringConverter;

@objid ("2ab79e11-2c99-4d6c-bc3a-66715fca30fa")
public class OConnectorEnd extends OLinkEnd {
    @objid ("df533af4-5b89-49df-86fa-3f1c7ea2f67d")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        org.eclipse.uml2.uml.Element connector = null;
        
        if (this.objElt.getLink() != null) {
            connector = GenerationProperties.getInstance().getMappedElement(this.objElt.getLink());
        }
        
        if (connector != null) {
            if (connector instanceof org.eclipse.uml2.uml.Connector) {
                return UMLFactory.eINSTANCE.createConnectorEnd();
            } else if (connector instanceof InstanceSpecification) {
                return UMLFactory.eINSTANCE.createSlot();
            }
        }
        
        String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport",
                this.objElt.getName(),
                this.objElt.getClass().getSimpleName());
        String description = Xmi.I18N.getMessage("logFile.warning.unexportedConnector");
        GenerationProperties.getInstance().addWarning(message, this.objElt, description);
        return null;
    }

    @objid ("a5b7652f-d7a9-432a-8126-b530632cb7b0")
    public  OConnectorEnd(ConnectorEnd param) {
        super(param);
        this.objElt = param;
        
    }

    @objid ("daa48618-8f38-4bd0-9a35-98b8af6c6942")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt != null) {
            if (ecoreElt instanceof org.eclipse.uml2.uml.ConnectorEnd) {
                attachConnectorEnd((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
            } else if (ecoreElt instanceof org.eclipse.uml2.uml.Slot) {
                attachSlot((org.eclipse.uml2.uml.Slot) ecoreElt);
            }
        }
        
    }

    @objid ("dd11fffa-098b-4a3b-b014-f8ab0265763c")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt != null) {
            
            if (ecoreElt instanceof org.eclipse.uml2.uml.Slot) {
                
                setLinked((org.eclipse.uml2.uml.Slot) ecoreElt);
                setDefiningFeature((org.eclipse.uml2.uml.Slot) ecoreElt);
                
            } else if (ecoreElt instanceof org.eclipse.uml2.uml.ConnectorEnd) {
                
                setIsOrdered((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setIsUnique((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setMax((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setMin((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setPartWithPort((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                
                if (GenerationProperties.getInstance().isRoundtripEnabled()) {
                    setName((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                    setIsNavigable((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);       
                }
        
                ordered((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
            }
        }
        
    }

    @objid ("7e80f0ee-e5f8-4b86-9341-77e4a2479c3c")
    private void attachConnectorEnd(org.eclipse.uml2.uml.ConnectorEnd connectorEnd) {
        org.eclipse.uml2.uml.Element owner = GenerationProperties.getInstance().getMappedElement(this.objElt.getLink());
        if (owner instanceof org.eclipse.uml2.uml.Connector) {
            ((org.eclipse.uml2.uml.Connector) owner).getEnds().add(connectorEnd);
        }
        
        org.eclipse.uml2.uml.Element role = GenerationProperties.getInstance().getMappedElement(this.objElt.getOwner());
        if (role instanceof org.eclipse.uml2.uml.ConnectableElement) {
            connectorEnd.setRole((org.eclipse.uml2.uml.ConnectableElement) role);
        }
        
    }

    @objid ("ca99e9da-be7f-45ad-becd-07eb5ee7b16a")
    private void attachSlot(org.eclipse.uml2.uml.Slot slot) {
        org.eclipse.uml2.uml.Element owner = GenerationProperties.getInstance().getMappedElement(this.objElt.getLink());
        if (owner instanceof InstanceSpecification) {
            ((InstanceSpecification) owner).getSlots().add(slot);
        }
        
    }

    @objid ("6466163b-27ec-4508-a068-a611930a65a6")
    private void setLinked(org.eclipse.uml2.uml.Slot ecoreElt) {
        org.eclipse.uml2.uml.Element inst = GenerationProperties.getInstance().getMappedElement(this.objElt.getOwner());
        
        if ((inst instanceof org.eclipse.uml2.uml.Slot) && (((org.eclipse.uml2.uml.Slot) inst).getValues().size() == 0)) {
        
            org.eclipse.uml2.uml.Slot slot = (org.eclipse.uml2.uml.Slot) inst;
            InstanceValue instanceValue = UMLFactory.eINSTANCE.createInstanceValue();
            org.eclipse.uml2.uml.ValueSpecification result = ecoreElt.createValue(null, null, instanceValue.eClass());
            ((InstanceValue) result).setInstance(slot.getOwningInstance());
            slot.getValues().add(result);
        
            if (AbstractObjingModelNavigation.isNotNullOrEmpty(this.objElt.getName())) {
                ((InstanceValue) result).setName(this.objElt.getName());
            }
        
        }
        
        ObjingEAnnotation.setOwner(ecoreElt, String.valueOf(this.objElt.getOwner().getUuid().toString()));
        
    }

    @objid ("4e56c1f8-599f-4acf-8a9c-e70607cf008d")
    private void ordered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        org.eclipse.uml2.uml.Element element = GenerationProperties.getInstance().getMappedElement(this.objElt.getLink());
        if (element instanceof org.eclipse.uml2.uml.Connector) {
            org.eclipse.uml2.uml.Connector connector = (org.eclipse.uml2.uml.Connector) element;
        
            if ((connector.getKind() != null) && (connector.getKind().equals(org.eclipse.uml2.uml.ConnectorKind.DELEGATION_LITERAL))) {
                if (this.objElt.isNavigable()) {
                    connector.getEnds().remove(ecoreElt);
                    connector.getEnds().add(connector.getEnds().size(), ecoreElt);
                }
            }
        }
        
    }

    @objid ("af93f357-c4df-4948-9261-91bce6de6aa8")
    private void setDefiningFeature(final org.eclipse.uml2.uml.Slot ecoreElt) {
        Instance instanceOwner = this.objElt.getOwner();
        Link link = this.objElt.getLink();
        if ((instanceOwner != null) && (link != null)) {
            Association assoc = link.getModel();
            NameSpace namespace = instanceOwner.getBase();
            if ((assoc != null) && (namespace != null)) {
                for (AssociationEnd assocEnd : assoc.getEnd()) {
                    if (assocEnd.getOwner().equals(namespace)) {
                        org.eclipse.uml2.uml.Element ecoreProp = GenerationProperties.getInstance().getMappedElement(assocEnd);
                        if (ecoreProp instanceof org.eclipse.uml2.uml.StructuralFeature) {
                            ecoreElt.setDefiningFeature((org.eclipse.uml2.uml.StructuralFeature) ecoreProp);
                        }
                    }
                }
            }
        }
        
    }

    @objid ("125c610d-0ede-45b0-b978-98ae89ea723a")
    private void setName(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(this.objElt.getName())) {
            ObjingEAnnotation.setName(ecoreElt, this.objElt.getName());
        }
        
    }

    @objid ("2c876be9-8412-47d4-ac40-420ed9f9d2cd")
    private void setIsOrdered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsOrdered(this.objElt.isIsOrdered());
    }

    @objid ("0714c268-8728-4ac8-9f9c-63abde79617d")
    private void setIsUnique(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsUnique(this.objElt.isIsUnique());
    }

    @objid ("3a751235-6d9d-4616-b4b7-edd2e5846182")
    private void setIsNavigable(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ObjingEAnnotation.setIsNavigable(ecoreElt, this.objElt.isNavigable());
    }

    @objid ("2ccc85af-b8f0-4b68-b820-8d120baa3d1d")
    private void setMin(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        String objingMultMin = this.objElt.getMultiplicityMin();
        
        // If objingMultMin is "" then we don't set a lower multiplicity for the
        // UML2 element.
        if (!"".equals(objingMultMin)) {
            if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE
                    .equals(objingMultMin)) {
                ecoreElt.setLower(org.eclipse.uml2.uml.LiteralUnlimitedNatural.UNLIMITED);
            } else {
                StringConverter.setFilterEnabled(true);
                Integer min = StringConverter.getInteger(objingMultMin);
                if (min != null) {
                    ecoreElt.setLower(min);
                } else {
                    String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.multiplicityMin", "Connector end");
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMin + "\"", "Integer",
                            contextualMsg);
                    GenerationProperties.getInstance().addInfo(message, this.objElt);
                }
            }
        }
        
    }

    @objid ("f752bd02-e66a-4f31-b141-abbaf4fce989")
    private void setMax(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        String objingMultMax = this.objElt.getMultiplicityMax();
        
        // If objingMultMax is "" then we don't set an upper multiplicity for
        // the UML2 element.
        if (!"".equals(objingMultMax)) {
            if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE
                    .equals(objingMultMax)) {
                ecoreElt.setUpper(org.eclipse.uml2.uml.LiteralUnlimitedNatural.UNLIMITED);
            } else {
                StringConverter.setFilterEnabled(true);
                Integer max = StringConverter.getInteger(objingMultMax);
                if (max != null) {
                    ecoreElt.setUpper(max);
                } else {
                    String contextualMsg = Xmi.I18N.getMessage("logFile.exception.stringConverter.multiplicityMax", "Connector end");
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMax + "\"", "Integer", contextualMsg);
                    GenerationProperties.getInstance().addInfo(message, this.objElt);
                }
            }
        }
        
    }

    @objid ("daf7c8a9-69a9-40e9-822b-a048ad0eea4b")
    private void setPartWithPort(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        MObject linked = this.objElt.getCompositionOwner();
        if (linked instanceof Port) {
            MObject portOwner = linked.getCompositionOwner();
            org.eclipse.uml2.uml.Element part = GenerationProperties.getInstance().getMappedElement(portOwner);
            if (part instanceof Property) {
                ecoreElt.setPartWithPort((Property) part);
            }
        
        }
        
    }

}
