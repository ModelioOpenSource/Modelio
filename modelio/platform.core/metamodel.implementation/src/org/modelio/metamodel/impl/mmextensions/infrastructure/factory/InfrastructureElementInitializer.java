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

package org.modelio.metamodel.impl.mmextensions.infrastructure.factory;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of IElementInitializer interface
 */
@objid ("36b8e2b3-727f-448a-b5b7-d477de0e0f3e")
class InfrastructureElementInitializer implements IInfrastructureElementInitializer {
    @objid ("52ed9a81-81f4-4a97-a863-56872d61d167")
    private ElementInitializerVisitor visitor;

    @objid ("a2f36b18-9d4e-408c-9177-da0b1ed37995")
    public InfrastructureElementInitializer(IInfrastructureModelFactory modelFactory) {
        Geometry geometry = new Geometry();
        
        this.visitor = new ElementInitializerVisitor(modelFactory, geometry);
    }

    @objid ("0c244183-e94e-47e8-a925-c87cad9a0024")
    @Override
    public void initialize(MObject element) {
        element.accept(this.visitor);
    }

    @objid ("3273df97-75cc-47a9-8ec0-da759e2f033a")
    @Override
    public void setDefaultValue(String key, Object value) {
        switch (key) {
        case "DEFAULT_NOTE_MIMETYPE":
            this.visitor.geometry.setDefaultNoteMimeType((String) value);
            break;
        default:
            // unknown key
        }
    }

    @objid ("ac1bad52-817e-47ba-b9de-1847830aac58")
    private static class ElementInitializerVisitor extends DefaultInfrastructureVisitor {
        @objid ("d3edb2e5-4c60-4f67-8b0e-04386fcd9029")
        private static final String DEFAULT_MIMETYPE = "text/plain";

        @objid ("30548774-1069-4eba-ab55-9706b36d2e58")
        private Geometry geometry;

        @objid ("eb5a12a4-0c15-41d8-9875-0f9ad748b70d")
        private IInfrastructureModelFactory modelFactory;

        @objid ("8c2cbc73-3cc7-4e59-9542-d2dea6cbea74")
        public ElementInitializerVisitor(final IInfrastructureModelFactory modelFactory, Geometry geometry) {
            this.modelFactory = modelFactory;
            this.geometry = geometry;
        }

        @objid ("bf0571c4-7225-480c-b6f8-6142191d1bd8")
        @Override
        public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition theDynamicPropertyDefinition) {
            CoreSession session = CoreSession.getSession(theDynamicPropertyDefinition);
            
            // Take the MultiElement type...
            for (PropertyType type : session.getModel().findByClass(PropertyType.class)) {
                if (type.getName().equals("MultiElement")) {
                    theDynamicPropertyDefinition.setType(type);
                    break;
                }
            }
            
            // Set 'trace' as the default stereotype
            theDynamicPropertyDefinition.setProperty("Constraints", "DependencyStereotype", "'trace'{01280500-0000-0b37-0000-000000000000} Stereotype");
            return super.visitDynamicPropertyDefinition(theDynamicPropertyDefinition);
        }

        @objid ("f1ee5c9e-d8b8-4084-8317-a57028c1c0dc")
        @Override
        public Object visitEnumeratedPropertyType(EnumeratedPropertyType obj) {
            obj.setBaseType(PropertyBaseType.ENUMERATE);
            return super.visitEnumeratedPropertyType(obj);
        }

        @objid ("246337f6-3a2c-4e79-b254-bc43db769710")
        @Override
        public Object visitMatrixValueDefinition(MatrixValueDefinition theMatrixValueDefinition) {
            if (theMatrixValueDefinition.getParameters() == null) {
                final PropertyTable parameters = this.modelFactory.createPropertyTable();
                theMatrixValueDefinition.setParameters(parameters);
            }
            return super.visitMatrixValueDefinition(theMatrixValueDefinition);
        }

        @objid ("ca16bc5f-132f-454f-ab10-69664b3df566")
        @Override
        public Object visitNote(Note theNote) {
            NoteType descriptionNoteType = this.geometry.getDescriptionNoteType(CoreSession.getSession(theNote));
            if (theNote.getModel() == null) {
                theNote.setModel(descriptionNoteType);
            }
            if (Objects.equals(descriptionNoteType, theNote.getModel())) {
                if (this.geometry.defaultNoteMimeType != null) {
                    theNote.setMimeType(this.geometry.defaultNoteMimeType);
                }
            }
            return super.visitNote(theNote);
        }

        @objid ("921a77ad-0ba2-4f29-9f70-50bdbbee6da9")
        @Override
        public Object visitNoteType(NoteType theNoteType) {
            if (theNoteType.getMimeType().isEmpty()) {
                theNoteType.setMimeType(ElementInitializerVisitor.DEFAULT_MIMETYPE);
            }
            return super.visitNoteType(theNoteType);
        }

        @objid ("ca05fdeb-818a-4215-9243-072abba1fbbf")
        @Override
        public Object visitPropertyDefinition(PropertyDefinition thePropertyDefinition) {
            CoreSession session = CoreSession.getSession(thePropertyDefinition);
            
            if (thePropertyDefinition.getType() == null) {
                // Take the first available type...
                for (PropertyType type : session.getModel().findByClass(PropertyType.class)) {
                    thePropertyDefinition.setType(type);
                    break;
                }
            }
            return super.visitPropertyDefinition(thePropertyDefinition);
        }

        @objid ("9ba05734-594f-40f2-9f9f-1b4823ff1e3d")
        @Override
        public Object visitQueryDefinition(QueryDefinition theQueryDefinition) {
            if (theQueryDefinition.getParameters() == null) {
                final PropertyTable parameters = this.modelFactory.createPropertyTable();
                theQueryDefinition.setParameters(parameters);
            }
            return super.visitQueryDefinition(theQueryDefinition);
        }

    }

    /**
     * Some pointers into the current model.
     * <p>
     * All elements may not be available at the instantiation time.
     */
    @objid ("f43d58bf-183b-4d0f-b538-bf3993a4d4f9")
    private static class Geometry {
        @objid ("c43f16b1-705f-43c4-806b-5e5646be5186")
        private String defaultNoteMimeType;

        @objid ("dac98013-693c-4d05-9d8e-d9489446d289")
        private NoteType descriptionNoteType;

        @objid ("0fea1a4e-4d43-4f7d-9e94-7a55c64d5f6f")
        public NoteType getDescriptionNoteType(ICoreSession coreSession) {
            if ((this.descriptionNoteType == null) || !this.descriptionNoteType.isValid()) {
            
                List<NoteType> noteTypes = new MModelServices(coreSession).findNoteTypes("ModelerModule", ModelElement.MQNAME, "description", coreSession.getMetamodel().getMClass(ModelElement.class));
            
                if (noteTypes.size() > 0) {
                    this.descriptionNoteType = noteTypes.get(0);
                }
            }
            return this.descriptionNoteType;
        }

        @objid ("b222f766-84b6-470a-ac92-e78db8f9f5dd")
        public void setDefaultNoteMimeType(String value) {
            this.defaultNoteMimeType = value;
        }

    }

}
