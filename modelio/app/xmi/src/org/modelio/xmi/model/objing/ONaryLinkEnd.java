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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.StringConverter;

/**
 * This class manages the export of NaryLinkEnd
 * 
 * @author ebrosse
 */
@objid ("1801dd67-6a95-48a8-b62d-ec65bc340228")
public class ONaryLinkEnd extends OModelElement {
    @objid ("5064b574-6965-42e7-897c-c66093f8db2a")
    protected NaryLinkEnd objElt = null;

    @objid ("06289d47-a31f-41b6-8dbb-609a3e9c7f1c")
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
        GenerationProperties.getInstance().addWarning(message, this.objElt);
        return null;
    }

    /**
     * Constructor
     * @param param : the exported Modelio LinkEnd
     */
    @objid ("7c662da2-838e-40ba-9df8-1495dc014609")
    public ONaryLinkEnd(final NaryLinkEnd param) {
        super(param);
        this.objElt = param;
    }

    @objid ("51087ee0-2f49-4149-a010-23fc9577c179")
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

    @objid ("c2abee0e-4db2-484c-8ad5-c43922995f92")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.Slot) {
            setSlotProperties((org.eclipse.uml2.uml.Slot) ecoreElt);
        } else if (ecoreElt instanceof org.eclipse.uml2.uml.ConnectorEnd) {
            setConnectorEndProperties((org.eclipse.uml2.uml.ConnectorEnd) ecoreElt);
        }
    }

    @objid ("80fd5010-d457-4c6e-abc4-af5939e98739")
    private void setSlotProperties(org.eclipse.uml2.uml.Slot ecoreElt) {
        setLinked(ecoreElt);
        setDefiningFeature(ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()) {
            ObjingEAnnotation.setIsUnique(ecoreElt, this.objElt.isIsUnique());
            ObjingEAnnotation.setIsOrdered(ecoreElt, this.objElt.isIsOrdered());
            ObjingEAnnotation.setMultiMax(ecoreElt, this.objElt.getMultiplicityMax());
            ObjingEAnnotation.setMultiMin(ecoreElt, this.objElt.getMultiplicityMin());
        }
    }

    @objid ("2d6887e4-4c7e-4a6a-adb9-0e128ad869d6")
    private void setLinked(org.eclipse.uml2.uml.Slot ecoreElt) {
        Instance type = this.objElt.getSource();
        
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

    @objid ("80a24c86-e8da-4b11-8d1e-c527e2924b59")
    private void setDefiningFeature(final org.eclipse.uml2.uml.Slot ecoreElt) {
        NaryLink link = this.objElt.getNaryLink();
        Instance instance = this.objElt.getSource();
        NaryAssociation assoc = link.getModel();
        NameSpace namespace = instance.getBase();
        
        if ((assoc != null) && (namespace != null)) {
        
            NaryAssociationEnd assocEnd = null;
            for (NaryAssociationEnd currentEnd : assoc.getNaryEnd()) {
                if (AbstractObjingModelNavigation.isSubType(currentEnd.getOwner(), namespace)) {
                    assocEnd = currentEnd;
                }
            }
        
            if (assocEnd != null) {
                org.eclipse.uml2.uml.Element ecoreProp = GenerationProperties.getInstance().getMappedElement(assocEnd);
                if (ecoreProp instanceof org.eclipse.uml2.uml.StructuralFeature) {
                    ecoreElt.setDefiningFeature((org.eclipse.uml2.uml.StructuralFeature) ecoreProp);
                    org.eclipse.uml2.uml.Element ecoreAssoc = GenerationProperties.getInstance().getMappedElement(assoc);
                    org.eclipse.uml2.uml.Element instanceSpec = GenerationProperties.getInstance().getMappedElement(link);
                    if ((ecoreAssoc instanceof org.eclipse.uml2.uml.Association)
                            && (instanceSpec instanceof org.eclipse.uml2.uml.InstanceSpecification)) {
                        ((org.eclipse.uml2.uml.InstanceSpecification) instanceSpec).getClassifiers().add((org.eclipse.uml2.uml.Association) ecoreAssoc);
                    }
                }
            }
        }
    }

    @objid ("94a02504-27f0-49e6-beeb-91b0df75bd2f")
    private void attachSlot(org.eclipse.uml2.uml.Slot slot) {
        org.eclipse.uml2.uml.Element owner = GenerationProperties.getInstance().getMappedElement(this.objElt.getNaryLink());
        if (owner instanceof InstanceSpecification) {
            ((InstanceSpecification) owner).getSlots().add(slot);
        }
    }

    @objid ("4862dd71-53fa-4edc-8c99-b0ddef40a222")
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

    @objid ("c7aeaf1d-6376-4fc2-aca0-828cfdaf8c91")
    private void setConnectorEndProperties(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        setIsOrdered(ecoreElt);
        setIsUnique(ecoreElt);
        setMax(ecoreElt);
        setMin(ecoreElt);
        setRole(ecoreElt);
        setPartWithPort(ecoreElt);
        ordered(ecoreElt);
    }

    @objid ("1031b9e9-1910-4f99-9450-ef53562428e5")
    private void ordered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        org.eclipse.uml2.uml.Connector connector = (org.eclipse.uml2.uml.Connector) GenerationProperties.getInstance().getMappedElement(this.objElt.getNaryLink());
        if ((connector.getKind() != null)
                && (connector.getKind().equals(org.eclipse.uml2.uml.ConnectorKind.DELEGATION_LITERAL))) {
        
            connector.getEnds().remove(ecoreElt);
            connector.getEnds().add(connector.getEnds().size(), ecoreElt);
        
        }
    }

    @objid ("9a962852-b71b-4455-b5af-4c85c108c844")
    private void setIsOrdered(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsOrdered(this.objElt.isIsOrdered());
    }

    @objid ("f5e97155-f86f-4a4a-b425-b71a8788f625")
    private void setIsUnique(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        ecoreElt.setIsUnique(this.objElt.isIsUnique());
    }

    @objid ("48d837d3-d546-4812-950d-9f53cabe9022")
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

    @objid ("a7366122-fd0d-4b5a-be62-bb9bf54cf08e")
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

    @objid ("769c31a9-1511-4859-be6d-3a5216a4efda")
    private void setRole(org.eclipse.uml2.uml.ConnectorEnd ecoreElt) {
        org.eclipse.uml2.uml.Element role = GenerationProperties.getInstance().getMappedElement(this.objElt.getSource());
        if (role instanceof org.eclipse.uml2.uml.ConnectableElement) {
            ecoreElt.setRole((org.eclipse.uml2.uml.ConnectableElement) role);
        }
    }

    @objid ("18535ec1-4fcd-4de4-9dab-a87d24fdcc0e")
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
