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

package org.modelio.xmi.model.ecore;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.internal.impl.EnumerationLiteralImpl;
import org.eclipse.uml2.uml.internal.impl.ExtensionImpl;
import org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.reverse.TotalImportMap;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;

@objid ("44857b9e-9717-43e2-9b36-f9601e9d22b0")
public class EElement implements IEElement {
    @objid ("62c56f42-15fd-4b00-a5ac-9f6572d5699a")
    private org.eclipse.uml2.uml.Element ecoreElt = null;

    @objid ("dd9b8f90-a822-4583-995e-31fd15b816a9")
    @Override
    public org.eclipse.uml2.uml.Element getEcoreElement() {
        return this.ecoreElt;
    }

    @objid ("c5bb634b-1044-4ca8-a882-e98f5079b5bd")
    @Override
    public void setStereotypes() {
        Object object = ReverseProperties.getInstance().getMappedElement(this.ecoreElt);
        
        if (object != null) {
            if (object instanceof ModelElement) {
                ModelElement modelElement = (ModelElement) object;
                if ((modelElement.getTag() != null) && (modelElement.getTag().size() == 0)
                        && (modelElement.getExtension().size() == 0)) {
                    for (Object stereo : this.ecoreElt.getAppliedStereotypes()) {
                        setStereotype(modelElement, (org.eclipse.uml2.uml.Stereotype) stereo);
                    }
                }
        
            } else if (object instanceof List<?>) {
                for (Object createdElt : (List<?>) object) {
                    if (createdElt instanceof ModelElement) {
                        ModelElement modelElement = (ModelElement) createdElt;
                        if ((modelElement.getTag() != null) && (modelElement.getTag().size() == 0)
                                && (modelElement.getExtension().size() == 0)) {
                            for (Object stereo : this.ecoreElt.getAppliedStereotypes()) {
                                setStereotype(modelElement, (org.eclipse.uml2.uml.Stereotype) stereo);
                            }
                        }
                    }
                }
            } else if (this.ecoreElt instanceof org.eclipse.uml2.uml.Manifestation) {
                List<Manifestation> list = (ArrayList<Manifestation>) object;
                for (Manifestation manifestation : list) {
                    if ((manifestation.getTag() != null) && (manifestation.getTag().size() == 0) && (manifestation.getExtension().size() == 0)) {
                        for (Object stereo : this.ecoreElt.getAppliedStereotypes()) {
                            setStereotype(manifestation, (org.eclipse.uml2.uml.Stereotype) stereo);
                        }
                    }
                }
            } else if (this.ecoreElt instanceof org.eclipse.uml2.uml.Abstraction) {
                List<Dependency> list = (ArrayList<Dependency>) object;
                for (Dependency abstraction : list) {
                    if ((abstraction.getTag() != null) && (abstraction.getTag().size() == 0) && (abstraction.getExtension().size() == 1)) {
                        for (Object stereo : this.ecoreElt.getAppliedStereotypes()) {
                            setStereotype(abstraction, (org.eclipse.uml2.uml.Stereotype) stereo);
                        }
                    }
                }
            } else if (this.ecoreElt instanceof org.eclipse.uml2.uml.Comment) {
                List<Note> list = (ArrayList<Note>) object;
                for (Note note : list) {
                    if ((note.getTag() != null) && (note.getTag().size() == 0) && (note.getExtension().size() == 0)) {
                        for (Object stereo : this.ecoreElt.getAppliedStereotypes()) {
                            setStereotype(note, (org.eclipse.uml2.uml.Stereotype) stereo);
                        }
                    }
                }
        
            } else if (this.ecoreElt instanceof org.eclipse.uml2.uml.Dependency) {
                List<Dependency> list = (ArrayList<Dependency>) object;
                for (ModelElement dependency : list) {
                    if ((dependency.getTag() != null) && (dependency.getTag().size() == 0) && (dependency.getExtension().size() == 0)) {
                        for (Object stereo : this.ecoreElt.getAppliedStereotypes()) {
                            setStereotype(dependency, (org.eclipse.uml2.uml.Stereotype) stereo);
                        }
                    }
                }
        
            } else if (this.ecoreElt instanceof org.eclipse.uml2.uml.Usage) {
                List<ModelElement> list = (ArrayList<ModelElement>) object;
                for (ModelElement usage : list) {
                    if ((usage.getTag() != null) && (usage.getTag().size() == 0) && (usage.getExtension().size() == 0)) {
                        for (Object stereo : this.ecoreElt.getAppliedStereotypes()) {
                            setStereotype(usage, (org.eclipse.uml2.uml.Stereotype) stereo);
                        }
                    }
                }
            }
        }
    }

    @objid ("71e3b440-e139-4e19-adca-689a8fc4ec0e")
    public EElement(org.eclipse.uml2.uml.Element element) {
        this.ecoreElt = element;
    }

    @objid ("25c7422e-ce34-434f-994d-7f7a5cf5b26b")
    private void setStereotype(ModelElement objElement, org.eclipse.uml2.uml.Stereotype stereotype) {
        ProfileUtils.createObjProfile(stereotype.getProfile());
        ProfileUtils.addExtension(objElement, stereotype);
        
        List<Property> listStereotypeProperties = new ArrayList<>();
        
        for (Object attribute : stereotype.getOwnedAttributes()) {
            if ((attribute instanceof Property) && (!(((Property) attribute).getAssociation() instanceof ExtensionImpl))) {
                listStereotypeProperties.add((Property) attribute);
            }
        }
        
        for (Property property : listStereotypeProperties) {
            setProperties(objElement, stereotype, property, listStereotypeProperties);
        }
    }

    @objid ("2b1ba68d-c450-4c48-b96e-868c1ceb7051")
    private void setProperties(ModelElement objModelElement, org.eclipse.uml2.uml.Stereotype stereotype, Property property, List<Property> listStereotypeProperties) {
        String propertyName = property.getName();
        ProfileUtils.visitProperty(property);
        
        IStandardModelFactory model = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        org.eclipse.uml2.uml.Type ecoreType = property.getType();
        TagType tagType = null;
        String taggedValueId = ObjingEAnnotation.getTaggedValue(property);
        
        Object mappedObject = TotalImportMap.getInstance().get(property);
        if (mappedObject instanceof TagType) {
            tagType = (TagType) mappedObject;
        } else if (mappedObject instanceof List<?>) {
            List<ModelElement> tagTypes = (List<ModelElement>) mappedObject;
            for (ModelElement tagTypeTemp : tagTypes) {
                if ((tagTypeTemp instanceof TagType)
                        && (objModelElement.getExtension().contains(((TagType) tagTypeTemp).getOwnerStereotype()))) {
                    tagType = (TagType) tagTypeTemp;
                }
            }
        }
        
        if (tagType != null) {
        
            if ((taggedValueId != null) && (!taggedValueId.equals(""))) {
        
                Object propertyValue = this.ecoreElt.getValue(stereotype, propertyName);
                if (propertyValue != null) {
                    if (propertyValue.equals(true)) {
                        createTaggedValue(tagType, objModelElement);
                    } else if (!(propertyValue.equals(false))) {
                        setStringProperty(objModelElement, stereotype, tagType, taggedValueId, listStereotypeProperties);
                    }
                }
        
            } else {
                Object value = this.ecoreElt.getValue(stereotype, propertyName);
        
                if ((value != null) && (!value.equals(false))) {
        
                    TaggedValue taggedValue = createTaggedValue(tagType, objModelElement);
        
                    if (!value.equals(true) && (property.getType() != null)) {
        
                        String currentEcoreTypeName = ecoreType.getName();
        
                        if (currentEcoreTypeName == null) {
                            if (ecoreType instanceof PrimitiveTypeImpl) {
                                currentEcoreTypeName = ((PrimitiveTypeImpl) ecoreType).eProxyURI().fragment();
                            } else {
                                currentEcoreTypeName = ecoreType.eResource().getURI().fragment();
                            }
                        }
        
                        if (currentEcoreTypeName.equals("String")) {
                            if (value instanceof String) {
                                model.createTagParameter((String) value, taggedValue);
                            } else if (value instanceof EDataTypeUniqueEList<?>) {
                                EDataTypeUniqueEList<String> tabString = (EDataTypeUniqueEList<String>) value;
                                for (String singleValue : tabString) {
                                    model.createTagParameter(singleValue, taggedValue);
                                }
                            }
        
                        } else if ((ecoreType instanceof org.eclipse.uml2.uml.Class)
                                && (EcoreModelNavigation.isMetaclass((org.eclipse.uml2.uml.Class) ecoreType))
                                && (value instanceof org.eclipse.uml2.uml.Element)) {
        
                            if (value instanceof org.eclipse.uml2.uml.NamedElement) {
                                model.createTagParameter(((org.eclipse.uml2.uml.NamedElement) value).getName(), taggedValue);
                            } else {
                                model.createTagParameter(value.toString(), taggedValue);
                            }
        
                        } else if (value instanceof EnumerationLiteralImpl) {
                            model.createTagParameter(((EnumerationLiteralImpl) value).getName(), taggedValue);
                        } else {
                            model.createTagParameter(value.toString(), taggedValue);
                        }
                    }
        
                }
        
            }
        }
    }

    @objid ("3e3fd273-c599-4840-8309-a71216bf2f77")
    private void setStringProperty(ModelElement objModelElement, org.eclipse.uml2.uml.Stereotype stereotype, TagType tagType, String taggedValueId, final List<Property> listStereotypeProperties) {
        IStandardModelFactory model = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        
        TaggedValue taggedValue = createTaggedValue(tagType, objModelElement);
        List<Property> toBeRemoved = new ArrayList<>();
        
        // Qualifier
        
        for (Property attribute : listStereotypeProperties) {
            String ecoreTaggedValueId = ObjingEAnnotation.getTaggedValue(attribute);
            if (!ObjingEAnnotation.isTagParameter(attribute)
                    && (ecoreTaggedValueId != null)
                    && (ecoreTaggedValueId.equals(taggedValueId))
                    && (tagType.getName().equals(attribute.getName()))) {
        
                Object value = this.ecoreElt.getValue(stereotype, attribute.getName());
        
                if ((value != null) && (!(value instanceof Boolean))) {
                    TagParameter tagParameter = model.createTagParameter();
                    taggedValue.setQualifier(tagParameter);
                    tagParameter.setValue(value.toString());
                }
                toBeRemoved.add(attribute);
            }
        }
        
        listStereotypeProperties.removeAll(toBeRemoved);
        toBeRemoved.clear();
        
        // TagParameter
        
        for (Property attribute : listStereotypeProperties) {
            if (ObjingEAnnotation.isTagParameter(attribute)
                    && (ObjingEAnnotation.getTaggedValue(attribute).equals(taggedValueId))
                    && (tagType.getName().equals(attribute.getName()))) {
        
                Object value = this.ecoreElt.getValue(stereotype, attribute.getName());
        
                if ((value != null) && (!(value instanceof Boolean))) {
                    if (value instanceof EDataTypeUniqueEList) {
                        EDataTypeUniqueEList<String> tabString = (EDataTypeUniqueEList<String>) value;
                        if (tabString.size() != 0) {
                            for (String valeur : tabString) {
                                model.createTagParameter(valeur, taggedValue);
                            }
                        } else {
                            model.createTagParameter("", taggedValue);
                        }
                    } else if (value instanceof String) {
                        model.createTagParameter((String) value, taggedValue);
                    }
                    toBeRemoved.add(attribute);
                }
            }
        }
        
        listStereotypeProperties.removeAll(toBeRemoved);
        toBeRemoved.clear();
    }

// @objid ("08e39ae5-4583-11e0-b54c-00137279a832")
// private void setStereotypesAnnotation(ModelElement modelElement) {
//
// String completeSter = ObjingEAnnotation.getStereotype(this.ecoreElt);
//
// if ((completeSter != null ) && (!completeSter.equals(""))){
// int index = completeSter.lastIndexOf(":");
// String profile = completeSter.substring(0, index);
// String stereotype = completeSter.substring(index+1, completeSter.length());
// List<Stereotype> objStereotypes = new ArrayList<Stereotype>();
//
//
// for (IModule currentModule : ReverseProperties.getInstance().getMModelServices().getModelFactory().getProject().getInstalled()){
// if (currentModule.getName().equals("LocalModule")){
// for(org.eclipse.uml2.uml.Profile currentProfile : currentModule.getOwnedProfile()){
// if (currentProfile.getName().equals(profile)){
// for (Stereotype currentStereotype : currentProfile.getDefinedStereotype()){
// if (currentStereotype.getName().equals(stereotype)){
// objStereotypes.add(currentStereotype);
// break;
// }
// }
// }
// }
// }
// }
//
// if (objStereotypes.size() != 0){
//
// List<Stereotype> objStereotypesClass = ReverseProperties.getInstance().getMModelServices().getStereotypes(modelElement.getClass());
//
// for(Stereotype objStereotype : objStereotypes){
//
// if (objStereotypesClass.contains(objStereotype)){
// IUmlModel model = ReverseProperties.getInstance().getMModelServices().getModelFactory();
// modelElement.getExtension().add(objStereotype);
//
// for (TagType tagType : objStereotype.getDefinedTagType()){
// String value = ObjingEAnnotation.getObjingEAnnotation(this.ecoreElt, tagType.getName());
//
// if (!value.equals("")){
//
// TaggedValue taggedValue = createTaggedValue(tagType, modelElement);
// if ((!tagType.getParamNumber().equals('0')) || (
// tagType.getParamNumber().equals('0') && (value.equalsIgnoreCase("true")))){
// model.createTagParameter(value, taggedValue);
// }
//
// }
// }
// }
// }
// }
//
// }
// }
    @objid ("93c356ea-0c27-42ee-a222-63582ffc2f8a")
    private TaggedValue createTaggedValue(TagType tagType, ModelElement modelElt) {
        TaggedValue taggedValue = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createTaggedValue();
        taggedValue.setDefinition(tagType);
        taggedValue.setAnnoted(modelElt);
        return taggedValue;
    }

    @objid ("e150d3be-529a-457c-a763-536fe675556c")
    @Override
    public Object createObjingElt() {
        return null;
    }

    @objid ("001dcc78-d8a8-43dc-8202-808a5a6da379")
    @Override
    public void attach(Element objingElt) {
    }

    @objid ("fb93c11c-8b66-4493-a661-00f782630505")
    @Override
    public void attach(List<Object> objingElts) {
    }

    @objid ("331fe3b9-69cd-4c6e-a4e1-edc146b11e34")
    @Override
    public void setProperties(Element objingElt) {
    }

}
