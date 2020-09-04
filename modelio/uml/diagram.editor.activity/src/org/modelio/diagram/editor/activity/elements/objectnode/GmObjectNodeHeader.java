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

package org.modelio.diagram.editor.activity.elements.objectnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.activity.plugin.DiagramEditorActivity;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Header for Object node.
 */
@objid ("2ad255d8-55b6-11e2-877f-002564c97630")
public class GmObjectNodeHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2ad3dc3c-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2ad3dc3f-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates a classifier header
     * @param diagram the owning diagram.
     * @param relatedRef reference to the represented object node.
     */
    @objid ("2ad3dc41-55b6-11e2-877f-002564c97630")
    public GmObjectNodeHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        this.setStackedStereotypes(true);
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("2ad3dc4a-55b6-11e2-877f-002564c97630")
    public GmObjectNodeHeader() {
        // empty constructor for the serialization
    }

    @objid ("2ad3dc4d-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        ObjectNode theInstanceNode = (ObjectNode) getRelatedElement();
        StringBuilder symbol = new StringBuilder();
        
        Instance instance = theInstanceNode.getRepresented();
        Attribute attribute = theInstanceNode.getRepresentedAttribute();
        AssociationEnd assocEnd = theInstanceNode.getRepresentedRole();
        BehaviorParameter behaviorParameter = theInstanceNode
                .getRepresentedRealParameter();
        
        if (instance != null) {
            symbol.append(instance.getName());
        
            NameSpace base = instance.getBase();
            symbol.append(": ");
            if (base != null) {
                symbol.append(base.getName());
            } else {
                symbol.append(/* Messages.getString( */"NoBase"/* ) */);
            }
        } else if (attribute != null) {
            VisibilityMode visibility = attribute.getVisibility();
            symbol.append(getVisibilitySymbol(visibility));
        
            if (attribute.isIsDerived()) {
                symbol.append("/");
            }
        
            symbol.append(attribute.getName());
        
            GeneralClass attributeType = attribute.getType();
        
            symbol.append(" : ");
            if (attributeType != null) {
                symbol.append(attributeType.getName());
            } else {
                symbol.append(/* Messages.getString( */"NoType"/* ) */);
            }
        
            symbol.append(getAttributeMultiplicity(attribute));
        
            String value = attribute.getValue();
            if (value != null && !value.equals("")) {
                symbol.append(" = ");
                symbol.append(value);
            }
        } else if (assocEnd != null) {
            final VisibilityMode visibility = assocEnd.getVisibility();
            symbol.append(getVisibilitySymbol(visibility));
        
            if (assocEnd.isIsDerived()) {
                symbol.append("/");
            }
        
            final String associationEndName = assocEnd.getName();
            if (associationEndName.isEmpty()) {
                symbol.append(/* Messages.getString( */"NoName"/* ) */);
            } else {
                symbol.append(assocEnd.getName());
            }
        
            symbol.append(": ");
        
            // The type
            Classifier target = assocEnd.getTarget();
            if (target != null) {
                symbol.append(target.getName());
            } else {
                symbol.append(DiagramEditorActivity.I18N.getString("NoType"));
            }
        
            // The cardinality
            symbol.append(getAssociationEndMultiplicity(assocEnd));
        } else if (behaviorParameter != null) {
            Parameter theParameter = behaviorParameter.getMapped();
        
            PassingMode passingMode = behaviorParameter.getParameterPassing();
        
            GeneralClass parameterType = behaviorParameter.getType();
        
            if (theParameter != null && theParameter.getComposed() != null) {
                symbol.append(theParameter.getName());
                symbol.append(" ");
        
                if (passingMode == PassingMode.IN) {
                    symbol.append("In");
                }
                if (passingMode == PassingMode.OUT) {
                    symbol.append("Out");
                }
                if (passingMode == PassingMode.INOUT) {
                    symbol.append("Inout");
                }
            } else if (theParameter != null
                    && theParameter.getReturned() != null) {
                symbol.append("Out");
            } else {
                symbol.append(behaviorParameter.getName());
            }
        
            symbol.append(": ");
            if (parameterType != null) {
                symbol.append(parameterType.getName());
            } else {
                symbol.append(/* Messages.getString( */"NoType"/* ) */);
            }
        } else {
            symbol.append(computeObjectNodeLabel(theInstanceNode));
        }
        
        symbol.append(computeOrderingSignature(theInstanceNode));
        return symbol.toString();
        // String mainLabel = "";
        // IObjectNode theObjectNode = (IObjectNode) getRelatedElement();
        // IBehaviorParameter theBehaviorParameter =
        // theObjectNode.getRepresentedRealParameter();
        // IInstance theInstance = theObjectNode.getRepresented();
        // IAttribute theAttribute = theObjectNode.getRepresentedAttribute();
        // IAssociationEnd theAssociationEnd =
        // theObjectNode.getRepresentedRole();
        //
        // if (theBehaviorParameter != null) {
        // if (theBehaviorParameter.getMapped() != null) {
        // mainLabel += computeParameterLabel(theBehaviorParameter.getMapped());
        // } else {
        // mainLabel += computeBehaviorParameterLabel(theBehaviorParameter);
        // }
        // } else if (theInstance != null) {
        // mainLabel += computeInstanceLabel(theInstance);
        // } else if (theAttribute != null) {
        // mainLabel += computeAttributeLabel(theAttribute);
        // } else if (theAssociationEnd != null) {
        // mainLabel += computeAssociationEndLabel(theAssociationEnd);
        // } else {
        // mainLabel += computeObjectNodeLabel(theObjectNode);
        // }
        // mainLabel += computeOrderingSignature(theObjectNode);
        // return mainLabel;
    }

    @objid ("2ad3dc52-55b6-11e2-877f-002564c97630")
    private String computeOrderingSignature(final ObjectNode theObjectNode) {
        String mainLabel = "";
        String orderType;
        switch (theObjectNode.getOrdering()) {
        case UNORDERED:
            orderType = "{unordered}";
            break;
        case ORDERED:
            orderType = "{ordered}";
            break;
        case LIFO:
            orderType = "{LIFO}";
            break;
        case FIFO:
            orderType = "";
            break;
        default:
            orderType = "{!unknown order: " + theObjectNode.getOrdering()
                    + "!}";
        }
        
        mainLabel += " " + orderType;
        return mainLabel;
    }

    @objid ("2ad3dc5a-55b6-11e2-877f-002564c97630")
    private String computeObjectNodeLabel(final ObjectNode theObjectNode) {
        String mainLabel = "";
        // TODO: add style key for namespacing.
        // if (namespacing)
        // {
        // mainLabel += getNamespacing(theObjectNode);
        // }
        mainLabel += theObjectNode.getName();
        
        if (!theObjectNode.getUpperBound().isEmpty()
                && !"1".equals(theObjectNode.getUpperBound())) {
            mainLabel += " [" + theObjectNode.getUpperBound() + "]";
        }
        
        GeneralClass type = theObjectNode.getType();
        if (type != null) {
            mainLabel += ":" + type.getName();
            // GetSymbolVisitor typeSymbolVisitor(*this);
            // type->accept(typeSymbolVisitor);
            // mainLabel += ":" << typeSymbolVisitor.getResult();
        }
        return mainLabel;
    }

    @objid ("2ad3dc62-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        // Default: represent the object node itself
        ObjectNode instanceNode = (ObjectNode) getRelatedElement();
        ModelElement elementToShow = instanceNode;
        // Now look for a bound element.
        if (instanceNode != null) {
            GeneralClass type = instanceNode.getType();
            BehaviorParameter representedRealParameter = instanceNode
                    .getRepresentedRealParameter();
            Instance instance = instanceNode.getRepresented();
            Attribute attribute = instanceNode.getRepresentedAttribute();
            AssociationEnd associationEnd = instanceNode.getRepresentedRole();
            if (type != null) {
                elementToShow = type;
            } else if (representedRealParameter != null) {
        
                Parameter mappedParameter = representedRealParameter
                        .getMapped();
                elementToShow = (mappedParameter != null ? mappedParameter
                        : representedRealParameter);
            } else if (instance != null) {
                elementToShow = instance;
            } else if (attribute != null) {
                elementToShow = attribute;
            } else if (associationEnd != null) {
                elementToShow = associationEnd.getTarget();
            }
        }
        
        if (elementToShow != null) {
            return ElementImageService.getIcon(elementToShow);
        } else {
            return ElementImageService.getIcon(instanceNode);
        }
    }

    @objid ("2ad3dc67-55b6-11e2-877f-002564c97630")
    private StringBuilder getAttributeMultiplicity(final Attribute theAttribute) {
        StringBuilder multiplicity = new StringBuilder();
        
        String multiplicityMinStr = theAttribute.getMultiplicityMin();
        String multiplicityMaxStr = theAttribute.getMultiplicityMax();
        String separator = "";
        
        if (multiplicityMinStr.equals("1") && multiplicityMaxStr.equals("1")) {
            return multiplicity;
        }
        
        if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
            multiplicity.append(" [");
        
            if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                multiplicity.append(multiplicityMinStr);
            } else if (multiplicityMinStr.equals("0")
                    && multiplicityMaxStr.equals("*")) {
                multiplicity.append("*");
            } else {
                if (!multiplicityMinStr.equals("")
                        && !multiplicityMaxStr.equals("")) {
                    separator = "..";
                }
        
                multiplicity.append(multiplicityMinStr);
                multiplicity.append(separator);
                multiplicity.append(multiplicityMaxStr);
            }
            multiplicity.append("]");
        }
        return multiplicity;
    }

    @objid ("2ad3dc6f-55b6-11e2-877f-002564c97630")
    private String getVisibilitySymbol(final VisibilityMode v) {
        switch (v) {
        case PUBLIC:
            return "+";
        case PROTECTED:
            return "#";
        case PRIVATE:
            return "-";
        case PACKAGEVISIBILITY:
            return "\u2248"; // nearly equal
        // return ("\u221e"); // infinite
        // return ("~");
        // return ("\u00a7"); // paragraph
        case VISIBILITYUNDEFINED:
            return "";
        default:
            return "";
        }
    }

    @objid ("2ad562e1-55b6-11e2-877f-002564c97630")
    private StringBuilder getAssociationEndMultiplicity(final AssociationEnd theAssociationEnd) {
        StringBuilder multiplicity = new StringBuilder();
        
        String multiplicityMinStr = theAssociationEnd.getMultiplicityMin();
        String multiplicityMaxStr = theAssociationEnd.getMultiplicityMax();
        String separator = "";
        
        if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
            multiplicity.append(" [");
        
            if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                multiplicity.append(multiplicityMinStr);
            } else if (multiplicityMinStr.equals("0")
                    && multiplicityMaxStr.equals("*")) {
                multiplicity.append("*");
            } else {
                if (!multiplicityMinStr.equals("")
                        && !multiplicityMaxStr.equals("")) {
                    separator = "..";
                }
        
                multiplicity.append(multiplicityMinStr);
                multiplicity.append(separator);
                multiplicity.append(multiplicityMaxStr);
            }
            multiplicity.append("]");
        }
        return multiplicity;
    }

    @objid ("2ad562e9-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmObjectNodeHeader.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("2ad562ef-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmObjectNodeHeader.", Integer.valueOf(GmObjectNodeHeader.MINOR_VERSION));
    }

    @objid ("2ad562f5-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2ad562fa-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmObjectNodeHeader.MAJOR_VERSION;
    }

    @objid ("e75fd6b8-ec0c-496e-91d0-05f33e6f4019")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
                    @Override
                    public String getText() {
                        final ObjectNode theInstanceNode = (ObjectNode) getRelatedElement();
        
                        Instance instance = theInstanceNode.getRepresented();
                        Attribute attribute = theInstanceNode.getRepresentedAttribute();
                        AssociationEnd assocEnd = theInstanceNode.getRepresentedRole();
                        BehaviorParameter behaviorParameter = theInstanceNode.getRepresentedRealParameter();
        
                        if (instance != null) {
                            return instance.getName();
                        }
                        if (attribute != null) {
                            return attribute.getName();
                        }
                        if (assocEnd != null) {
                            return assocEnd.getName();
                        }
                        if (behaviorParameter != null) {
                            return behaviorParameter.getName();
                        } else {
                            return theInstanceNode.getName();
                        }
                    }
        
                    @Override
                    public void setText(String text) {
        
                        final ObjectNode theInstanceNode = (ObjectNode) getRelatedElement();
        
                        Instance instance = theInstanceNode.getRepresented();
                        Attribute attribute = theInstanceNode.getRepresentedAttribute();
                        AssociationEnd assocEnd = theInstanceNode.getRepresentedRole();
                        BehaviorParameter behaviorParameter = theInstanceNode.getRepresentedRealParameter();
        
                        if (instance != null) {
                            theInstanceNode.setName(text);
                            instance.setName(text);
                        }
                        if (attribute != null) {
                            theInstanceNode.setName(text);
                            attribute.setName(text);
                        }
                        if (assocEnd != null) {
                            theInstanceNode.setName(text);
                            assocEnd.setName(text);
                        }
                        if (behaviorParameter != null) {
                            theInstanceNode.setName(text);
                            behaviorParameter.setName(text);
                        } else {
                            theInstanceNode.setName(text);
                        }
        
                    }
                };
    }

}
