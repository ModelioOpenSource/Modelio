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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.StringConverter;

/**
 * This class manages the export of LinkEnd
 * 
 * @author ebrosse
 */
@objid ("da23ef13-c418-4fc9-9e22-0386f5a65ef8")
public class OLinkEnd extends OModelElement {
    @objid ("3c084612-ea40-4292-9170-871155a2fe0d")
    protected LinkEnd objElt = null;

    @objid ("6a8dc51e-2592-4ed0-a6db-d1da7ac21906")
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
        GenerationProperties.getInstance().addWarning(message, this.objElt);
        return null;
    }

    /**
     * Constructor
     * @param param : the exported Modelio LinkEnd
     */
    @objid ("8ab4a1df-d09b-4736-a9ad-a94d288ace59")
    public OLinkEnd(final LinkEnd param) {
        super(param);
        this.objElt = param;
    }

    @objid ("3f12a05a-a6a2-4635-a1b3-f243227ebd97")
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

    @objid ("ed25a543-3474-4664-a84a-67cf2de8ffb0")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt instanceof org.eclipse.uml2.uml.Slot) {
            setSlotProperties((org.eclipse.uml2.uml.Slot) ecoreElt);
        } else if (ecoreElt instanceof org.eclipse.uml2.uml.ConnectorEnd) {
            setConnectorEndProperties((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
        }
    }

    @objid ("05ca7df4-08c6-4e3f-be16-a82e8d714194")
    private void setSlotProperties(org.eclipse.uml2.uml.Slot ecoreElt) {
        setLinked(ecoreElt);
        setDefiningFeature(ecoreElt);
        if (GenerationProperties.getInstance().isRoundtripEnabled()) {
            setIsLink();
            ObjingEAnnotation.setIsUnique(ecoreElt, this.objElt.isIsUnique());
            ObjingEAnnotation.setIsNavigable(ecoreElt, this.objElt.isNavigable());
            ObjingEAnnotation.setIsOrdered(ecoreElt, this.objElt.isIsOrdered());
            ObjingEAnnotation.setMultiMax(ecoreElt, this.objElt.getMultiplicityMax());
            ObjingEAnnotation.setMultiMin(ecoreElt, this.objElt.getMultiplicityMin());
            ObjingEAnnotation.setName(ecoreElt, this.objElt.getName());
        }
    }

    @objid ("e9b93591-ae15-406b-be3e-cd0911bec8c5")
    private void setLinked(org.eclipse.uml2.uml.Slot ecoreElt) {
        Instance type = this.objElt.getTarget();
        if (type == null) {
            type = this.objElt.getOpposite().getSource();
        }
        
        org.eclipse.uml2.uml.Element inst = GenerationProperties.getInstance().getMappedElement(type);
        
        if ((inst instanceof InstanceSpecification)
                && (ecoreElt.getValues().size() == 0)) {
        
            InstanceValue instanceValue = UMLFactory.eINSTANCE.createInstanceValue();
            org.eclipse.uml2.uml.ValueSpecification result = ecoreElt.createValue(null, null, instanceValue.eClass());
            ((InstanceValue) result).setInstance((InstanceSpecification) inst);
            ecoreElt.getValues().add(result);
        
            if (AbstractObjingModelNavigation.isNotNullOrEmpty(this.objElt.getName())) {
                ((InstanceValue) result).setName(this.objElt.getName());
            }
        
        }
    }

    @objid ("bb610766-db43-4fa7-ae91-d142e475f245")
    private void setIsLink() {
        Link link = this.objElt.getLink();
        if (link != null) {
            org.eclipse.uml2.uml.Element inst = GenerationProperties.getInstance().getMappedElement(link);
            if (inst instanceof InstanceSpecification) {
                ObjingEAnnotation.setIsLink(inst);
            }
        }
    }

    @objid ("631aef0c-16e9-4a98-b2f1-ae5fdb8d69f7")
    private void setDefiningFeature(final org.eclipse.uml2.uml.Slot ecoreElt) {
        AssociationEnd assocEnd = this.objElt.getModel();
        
        if (assocEnd != null) {
            org.eclipse.uml2.uml.Element ecoreProp = GenerationProperties.getInstance().getMappedElement(assocEnd);
            if (ecoreProp instanceof org.eclipse.uml2.uml.StructuralFeature) {
                ecoreElt.setDefiningFeature((org.eclipse.uml2.uml.StructuralFeature) ecoreProp);
                Association assoc = assocEnd.getAssociation();
                org.eclipse.uml2.uml.Element ecoreAssoc = GenerationProperties.getInstance().getMappedElement(assoc);
                Link link = this.objElt.getLink();
                org.eclipse.uml2.uml.Element instanceSpec = GenerationProperties.getInstance().getMappedElement(link);
                if ((ecoreAssoc instanceof org.eclipse.uml2.uml.Association) && (instanceSpec instanceof org.eclipse.uml2.uml.InstanceSpecification)) {
                    ((org.eclipse.uml2.uml.InstanceSpecification) instanceSpec).getClassifiers().add((org.eclipse.uml2.uml.Association) ecoreAssoc);
                }
            }
        }
    }

    @objid ("0aff6ff1-2197-4cbf-aacc-936fa036cf5e")
    private void attachSlot(org.eclipse.uml2.uml.Slot slot) {
        org.eclipse.uml2.uml.Element owner = GenerationProperties.getInstance().getMappedElement(this.objElt.getLink());
        if (owner instanceof InstanceSpecification) {
            ((InstanceSpecification) owner).getSlots().add(slot);
        }
    }

    @objid ("edf1891b-fb5d-4ec1-8cc0-a4d8de31795c")
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

    @objid ("b4d84f7c-61ac-44ee-b2f2-322c7ee4eb23")
    private void setConnectorEndProperties(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        setIsOrdered(ecoreElt);
        setIsUnique(ecoreElt);
        setMax(ecoreElt);
        setMin(ecoreElt);
        setRole(ecoreElt);
        setPartWithPort(ecoreElt);
        if (GenerationProperties.getInstance().isRoundtripEnabled()) {
            setName(ecoreElt);
            setIsNavigable(ecoreElt);
        
        }
        ordered(ecoreElt);
    }

    @objid ("83ac7a39-c258-4156-b4ef-8423b6377363")
    private void ordered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        org.eclipse.uml2.uml.Connector connector = (org.eclipse.uml2.uml.Connector) GenerationProperties.getInstance().getMappedElement(this.objElt.getLink());
        if ((connector.getKind() != null)
                && (connector.getKind().equals(org.eclipse.uml2.uml.ConnectorKind.DELEGATION_LITERAL))) {
            if (this.objElt.isNavigable()) {
                connector.getEnds().remove(ecoreElt);
                connector.getEnds().add(connector.getEnds().size(), ecoreElt);
            }
        }
    }

    @objid ("729fc14d-e9b2-4a74-b500-f8f3d00d32d1")
    private void setName(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(this.objElt.getName())) {
            ObjingEAnnotation.setName(ecoreElt, this.objElt.getName());
        }
    }

    @objid ("e831004e-17f8-4ee4-a3e8-ffc61f7fdbd3")
    private void setIsOrdered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsOrdered(this.objElt.isIsOrdered());
    }

    @objid ("38a44543-8a89-48a9-b228-c77a3499befb")
    private void setIsUnique(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsUnique(this.objElt.isIsUnique());
    }

    @objid ("bc542b0a-52a7-4578-9134-ef54d127153b")
    private void setIsNavigable(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ObjingEAnnotation.setIsNavigable(ecoreElt, this.objElt.isNavigable());
    }

    @objid ("6674f4ee-ab42-4244-ac0b-19e34bd96fe9")
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

    @objid ("3b83c5cd-01bd-471f-a25a-8710c5969fb4")
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

    @objid ("ec1e556a-0c8f-4b91-af23-aa773be3902b")
    private void setRole(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        org.eclipse.uml2.uml.Element role = GenerationProperties.getInstance().getMappedElement(this.objElt.getOwner());
        if (role instanceof org.eclipse.uml2.uml.ConnectableElement) {
            ecoreElt.setRole((org.eclipse.uml2.uml.ConnectableElement) role);
        }
    }

    @objid ("8c78e34a-91ed-4b2c-8f4a-1091bbe1888a")
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
