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

package org.modelio.metamodel.impl.mmextensions.standard.factory;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IElementInitializer} interface for Standard metamodel fragment.
 */
@objid ("b6a6cf95-5cc6-4247-a6e7-19cbeb513d4d")
public class ElementInitializer implements IElementInitializer {
    @objid ("85fdcd2d-f732-4c9d-a536-8e813523a41e")
    private final ElementInitializerVisitor visitor;

    @objid ("ebca2674-3a06-4781-84c0-814cb7baada6")
    public ElementInitializer(final IStandardModelFactory standardFactory) {
        final Geometry geometry = new Geometry();
        
        this.visitor = new ElementInitializerVisitor(standardFactory, geometry);
    }

    @objid ("7561bc40-1ca4-49a0-bad9-9210a4cea736")
    @Override
    public void initialize(final MObject element) {
        element.accept(this.visitor);
    }

    @objid ("b538d2af-90f8-4421-898a-2d18319ebd6a")
    @Override
    public void setDefaultValue(final String key, final Object value) {
        switch (key) {
        case "DEFAULT_ATTRIBUTE_VISIBILITY":
            this.visitor.geometry.setDefaultAttributeVisibility((VisibilityMode) value);
            break;
        case "DEFAULT_ATTRIBUTE_TYPE":
            this.visitor.geometry.setDefaultAttributeType((GeneralClass) value);
            break;
        case "DEFAULT_PARAMETER_TYPE":
            this.visitor.geometry.setDefaultParameterType((GeneralClass) value);
            break;
        case "DEFAULT_RETURN_TYPE":
            this.visitor.geometry.setDefaultReturnType((GeneralClass) value);
            break;
        default:
            // unknown key
        }
    }

    @objid ("59e9e4d9-cc4f-4007-abf2-da46b28cc8a0")
    private static class ElementInitializerVisitor extends DefaultModelVisitor {
        @objid ("f349c179-c8d5-4daf-ae55-a4f5a68d906f")
        private static final String DEFAULT_MIMETYPE = "text/plain";

        @objid ("40ffee8b-5ec4-41b7-8f9a-3b277754e4c2")
        private final Geometry geometry;

        @objid ("88d405b5-43bb-4c75-8581-73251de00bdf")
        private final IStandardModelFactory standardFactory;

        @objid ("a340fee5-19dd-430c-9594-1a7a183e06ea")
        public ElementInitializerVisitor(final IStandardModelFactory modelFactory, final Geometry geometry) {
            this.standardFactory = modelFactory;
            this.geometry = geometry;
        }

        @objid ("2e657745-1c33-4ba7-a8b0-d1b2a5b803ba")
        @Override
        public Object visitAssociationEnd(final AssociationEnd theAssociationEnd) {
            if (this.geometry.defaultAttributeVisibility != null) {
                theAssociationEnd.setVisibility(this.geometry.defaultAttributeVisibility);
            }
            return super.visitAssociationEnd(theAssociationEnd);
        }

        @objid ("f5ccb62c-5538-4557-8cff-a3cac1b671f0")
        @Override
        public Object visitAttribute(final Attribute theAttribute) {
            if (this.geometry.defaultAttributeVisibility != null) {
                theAttribute.setVisibility(this.geometry.defaultAttributeVisibility);
            }
            
            theAttribute.setType(this.geometry.defaultAttributeType);
            
            // Init default multiplicity
            theAttribute.setMultiplicityMin("1");
            theAttribute.setMultiplicityMax("1");
            return theAttribute;
        }

        @objid ("70d01473-833b-4862-bc85-434b6c1aecf8")
        @Override
        public Object visitBpmnBoundaryEvent(final BpmnBoundaryEvent theBpmnBoundaryEvent) {
            theBpmnBoundaryEvent.setCancelActivity(true);
            return super.visitBpmnBoundaryEvent(theBpmnBoundaryEvent);
        }

        @objid ("a21016bf-9b49-43ab-8809-56dc93870522")
        @Override
        public Object visitDataType(final DataType theDataType) {
            theDataType.setIsElementary(true);
            return super.visitDataType(theDataType);
        }

        @objid ("da6f8750-0550-4b21-aaeb-87b253a9da53")
        @Override
        public Object visitElementImport(final ElementImport theElementImport) {
            // Visibility is private by default
            theElementImport.setVisibility(VisibilityMode.PRIVATE);
            return theElementImport;
        }

        @objid ("2b7b04d4-4373-4aca-9135-a4f346e333f8")
        @Override
        public Object visitEnumeration(final Enumeration theEnumeration) {
            theEnumeration.setIsElementary(true);
            return super.visitEnumeration(theEnumeration);
        }

        @objid ("896dd2d8-5c32-46e3-9a25-1ec7d7f303a3")
        @Override
        public Object visitInterface(final Interface theInterface) {
            theInterface.setIsAbstract(true);
            return super.visitInterface(theInterface);
        }

        @objid ("ce8433ba-7ae9-4e6d-a778-881c46288152")
        @Override
        public Object visitInternalTransition(final InternalTransition theInternalTransition) {
            theInternalTransition.setReceivedEvents("Do");
            return super.visitInternalTransition(theInternalTransition);
        }

        @objid ("8b6c7953-b1d9-44ee-859e-6790c67ff6f0")
        @Override
        public Object visitLinkEnd(final LinkEnd linkEnd) {
            final AssociationEnd role = linkEnd.getModel();
            if (role != null) {
                // Copy instantiated role properties here.
                linkEnd.setMultiplicityMin(role.getMultiplicityMin());
                linkEnd.setMultiplicityMax(role.getMultiplicityMax());
                linkEnd.setIsOrdered(role.isIsOrdered());
                linkEnd.setIsUnique(role.isIsUnique());
            }
            return linkEnd;
        }

        @objid ("5208b6b9-65f0-4993-9f71-1b0b15e744d1")
        @Override
        public Object visitPackageImport(final PackageImport thePackageImport) {
            // Visibility is private by default
            thePackageImport.setVisibility(VisibilityMode.PRIVATE);
            return thePackageImport;
        }

        @objid ("389216a1-7235-4a39-aa1a-f5eabd094035")
        @Override
        public Object visitParameter(final Parameter theParameter) {
            // Init multiplicity
            theParameter.setMultiplicityMin("1");
            theParameter.setMultiplicityMax("1");
            
            if (theParameter.getReturned() != null) {
                theParameter.setType(this.geometry.defaultReturnType);
            } else {
                theParameter.setType(this.geometry.defaultParameterType);
            }
            return theParameter;
        }

        @objid ("7de82050-f379-40a5-80df-006ba3d1971d")
        @Override
        public Object visitPort(final Port aPort) {
            aPort.setIsService(true);
            return aPort;
        }

        @objid ("c63ff4e7-57e8-4a8b-9473-4f92e6c0a8ea")
        @Override
        public Object visitTemplateBinding(final TemplateBinding aTemplateBinding) {
            // Call inherited behavior
            super.visitTemplateBinding(aTemplateBinding);
            
            List<TemplateParameter> parameters;
            final Operation op = aTemplateBinding.getInstanciatedTemplateOperation();
            final NameSpace ns = aTemplateBinding.getInstanciatedTemplate();
            if (op != null && aTemplateBinding.getBoundOperation() != null) {
                parameters = op.getTemplate();
            } else if (ns != null) {
                parameters = ns.getTemplate();
            } else {
                parameters = new ArrayList<>();
            }
            
            // Clear all obsolete TemplateParameterSubstitution
            for (final TemplateParameterSubstitution sub : aTemplateBinding.getParameterSubstitution()) {
                if (!parameters.contains(sub.getFormalParameter())) {
                    sub.delete();
                }
            }
            
            // Create missing substitutions
            final List<TemplateParameterSubstitution> substitutions = aTemplateBinding.getParameterSubstitution();
            final List<TemplateParameter> substituedParameters = new ArrayList<>(substitutions.size());
            
            for (final TemplateParameterSubstitution sub : substitutions) {
                substituedParameters.add(sub.getFormalParameter());
            }
            
            for (final TemplateParameter param : parameters) {
                if (!substituedParameters.contains(param)) {
                    if (param.getDefaultType() == null && param.getDefaultValue().isEmpty()) {
                        final TemplateParameterSubstitution newSub = this.standardFactory.createTemplateParameterSubstitution();
                        newSub.setFormalParameter(param);
                        newSub.setName(param.getName());
                        aTemplateBinding.getParameterSubstitution().add(newSub);
                    }
                }
            }
            return aTemplateBinding;
        }

        /**
         * For UseCase a set of notes are created to describe the use case. The main benefit is that the documentation generated by Modelio for the use case will provide "editable text blocs" for the notes and therefore greatly help documentation reverse
         * techniques. The created 'description' note if forced to "plain/text' mime type.
         */
        @objid ("111bba3f-a727-48ca-91e6-f6d1a4b7d1f9")
        @Override
        public Object visitUseCase(final UseCase theUseCase) {
            final String content = "...";
            
            if (theUseCase.getNote("ModelerModule", ModelElement.MQNAME, "description") == null) {
                final Note note = this.standardFactory.createNote("ModelerModule", ModelElement.MQNAME, "description", theUseCase, content);
                note.setMimeType(ElementInitializerVisitor.DEFAULT_MIMETYPE);
            }
            
            final String[] noteTypes = { "constraint", "non-functional constraint", "exception", "precondition", "postcondition" };
            for (final String noteType : noteTypes) {
                try {
                    if (theUseCase.getNote("ModelerModule", UseCase.MQNAME, noteType) == null) {
                        final Note note = this.standardFactory.createNote("ModelerModule", UseCase.MQNAME, noteType, theUseCase, content);
                        note.setMimeType(ElementInitializerVisitor.DEFAULT_MIMETYPE);
                    }
                } catch (final ExtensionNotFoundException e) {
                    Log.error(e);
                }
            }
            return super.visitUseCase(theUseCase);
        }

        @objid ("14b66551-ce58-4068-b828-b7a07ec6acd0")
        @Override
        public Object visitProject(final Project theProject) {
            // Create root package
            final Package newRootPackage = this.standardFactory.createPackage();
            newRootPackage.setName(theProject.getName());
            theProject.getModel().add(newRootPackage);
            
            // Create diagram set root
            final DiagramSet dgRootSet = this.standardFactory.createDiagramSet();
            dgRootSet.setName(theProject.getName());
            theProject.setDiagramRoot(dgRootSet);
            return super.visitProject(theProject);
        }

    }

    /**
     * Some pointers into the current model.
     * <p>
     * All elements may not be available at the instantiation time.
     */
    @objid ("2f6980b7-0b44-4272-96d9-2e22d5045fda")
    private static class Geometry {
        @mdl.prop
        @objid ("3dc85bd8-12f1-4cf7-b604-cfeced71c8b6")
        private VisibilityMode defaultAttributeVisibility = null;

        @mdl.propsetter
        public void setDefaultAttributeVisibility(VisibilityMode value) {
            // Automatically generated method. Please do not modify this code.
            this.defaultAttributeVisibility = value;
        }

        @mdl.prop
        @objid ("9829d7c2-4381-430d-94cf-9cb8c79e833c")
        private GeneralClass defaultAttributeType = null;

        @mdl.propsetter
        public void setDefaultAttributeType(GeneralClass value) {
            // Automatically generated method. Please do not modify this code.
            this.defaultAttributeType = value;
        }

        @mdl.prop
        @objid ("8cddabd5-993b-47f7-879b-b93991079984")
        private GeneralClass defaultParameterType = null;

        @mdl.propsetter
        public void setDefaultParameterType(GeneralClass value) {
            // Automatically generated method. Please do not modify this code.
            this.defaultParameterType = value;
        }

        @mdl.prop
        @objid ("95e11a9b-4828-4d62-9835-90bca017f5f6")
        private GeneralClass defaultReturnType = null;

        @mdl.propsetter
        public void setDefaultReturnType(GeneralClass value) {
            // Automatically generated method. Please do not modify this code.
            this.defaultReturnType = value;
        }

        @objid ("f0e5a23e-573e-448d-88cd-55814eeb2040")
        public Geometry() {
            // Empty
        }

    }

}
