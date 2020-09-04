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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.StringConverter;

@objid ("66f57281-af48-4643-9d66-ee7379343aa4")
public class ONaryConnectorEnd extends ONaryLinkEnd {
    @objid ("f59b4da6-4390-4d91-bfeb-7e20e07c36eb")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        org.eclipse.uml2.uml.Element connector = null;
        
        if (this.objElt.getNaryLink() != null) {
            connector = GenerationProperties.getInstance().getMappedElement(this.objElt.getNaryLink());
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

    @objid ("b509e461-9b0a-439e-bf26-386020880ded")
    public ONaryConnectorEnd(NaryConnectorEnd param) {
        super(param);
        this.objElt = param;
    }

    @objid ("5953514f-7c25-4a1a-9d59-d04e10d5bbac")
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

    @objid ("759fce34-3224-41c3-841c-9bdde082cd5d")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (ecoreElt != null) {
            if (ecoreElt instanceof org.eclipse.uml2.uml.Slot) {
                setLinked((org.eclipse.uml2.uml.Slot) ecoreElt);
                setDefiningFeature((org.eclipse.uml2.uml.Slot) ecoreElt);
            } else if (ecoreElt instanceof org.eclipse.uml2.uml.ConnectorEnd) {
                setIsOrdered((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setIsUnique((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setMax((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setMin((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setRole((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                setPartWithPort((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
                ordered((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
            }
        }
    }

    @objid ("8d7900dc-7d05-40d7-a37f-7551a5dd5a97")
    private void attachConnectorEnd(org.eclipse.uml2.uml.ConnectorEnd connectorEnd) {
        org.eclipse.uml2.uml.Element owner = GenerationProperties.getInstance().getMappedElement(this.objElt.getNaryLink());
        if (owner instanceof org.eclipse.uml2.uml.Connector) {
            ((org.eclipse.uml2.uml.Connector) owner).getEnds().add(connectorEnd);
        }
        
        org.eclipse.uml2.uml.Element role = GenerationProperties.getInstance().getMappedElement(this.objElt.getSource());
        if (role instanceof org.eclipse.uml2.uml.ConnectableElement) {
            connectorEnd.setRole((org.eclipse.uml2.uml.ConnectableElement) role);
        }
    }

    @objid ("198fdbdb-9d32-45e6-9270-db6e7e599b76")
    private void attachSlot(org.eclipse.uml2.uml.Slot slot) {
        org.eclipse.uml2.uml.Element owner = GenerationProperties.getInstance().getMappedElement(this.objElt.getNaryLink());
        if (owner instanceof InstanceSpecification) {
            ((InstanceSpecification) owner).getSlots().add(slot);
        }
    }

    @objid ("174fba2a-c0a8-4119-ba8b-b919642815a6")
    private void setLinked(org.eclipse.uml2.uml.Slot ecoreElt) {
        org.eclipse.uml2.uml.Element inst = GenerationProperties.getInstance().getMappedElement(this.objElt.getSource());
        
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
        
        ObjingEAnnotation.setOwner(ecoreElt, String.valueOf(this.objElt.getSource().getUuid().toString()));
    }

    @objid ("1ca790d2-e548-42b3-ac7e-ed1af0cf962e")
    private void ordered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        org.eclipse.uml2.uml.Connector connector = (org.eclipse.uml2.uml.Connector) GenerationProperties.getInstance().getMappedElement(this.objElt.getSource());
        if ((connector.getKind() != null)
                && (connector.getKind().equals(org.eclipse.uml2.uml.ConnectorKind.DELEGATION_LITERAL))) {
        
            connector.getEnds().remove(ecoreElt);
            connector.getEnds().add(connector.getEnds().size(), ecoreElt);
        
        }
    }

    @objid ("aea63bb6-f653-4ef8-86a0-f14f126587a6")
    private void setDefiningFeature(final org.eclipse.uml2.uml.Slot ecoreElt) {
        Instance instanceOwner = this.objElt.getSource();
        NaryLink link = this.objElt.getNaryLink();
        if ((instanceOwner != null) && (link != null)) {
            NaryAssociation assoc = link.getModel();
            NameSpace namespace = instanceOwner.getBase();
            if ((assoc != null) && (namespace != null)) {
                for (NaryAssociationEnd assocEnd : assoc.getNaryEnd()) {
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

    @objid ("3543449a-893d-4f2d-9280-25a38acf29b1")
    private void setIsOrdered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsOrdered(this.objElt.isIsOrdered());
    }

    @objid ("389d0e44-f643-4cda-aa39-c9f65d9c9186")
    private void setIsUnique(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsUnique(this.objElt.isIsUnique());
    }

    @objid ("3aa53916-fc20-48fd-b708-c5081e5120fd")
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
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMin + "\"", "Integer", contextualMsg);
                    GenerationProperties.getInstance().addInfo(message, this.objElt);
                }
            }
        }
    }

    @objid ("ac14f2fd-5255-4eaa-ae48-f16bce44e79f")
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
                    String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.defaultMsg", "String", "\"" + objingMultMax + "\"", "Integer",
                            contextualMsg);
                    GenerationProperties.getInstance().addInfo(message, this.objElt);
                }
            }
        }
    }

    @objid ("43fcb179-b15a-4674-86f5-9d7e3d9155f8")
    private void setRole(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        org.eclipse.uml2.uml.Element role = GenerationProperties.getInstance().getMappedElement(this.objElt.getSource());
        if (role instanceof org.eclipse.uml2.uml.ConnectableElement) {
            ecoreElt.setRole((org.eclipse.uml2.uml.ConnectableElement) role);
        }
    }

    @objid ("7ae2a030-00e5-49f2-a3bc-ed4007dcfc01")
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
