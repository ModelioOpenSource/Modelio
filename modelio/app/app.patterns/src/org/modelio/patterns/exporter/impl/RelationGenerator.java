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
package org.modelio.patterns.exporter.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.patterns.exporter.PatternModelAnalysis;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e5ba4016-a1f5-4289-92c3-374851f37d4c")
class RelationGenerator {
    @objid ("cba82d9f-0435-4d3f-be12-709f90a8bfe9")
    private List<Relation> relations;

    @objid ("8ab36f6a-3782-4997-8047-f9a153e0a87b")
    private Set<MObject> rootParents;

    @objid ("ef93673a-0d12-417d-bc43-14bed120d636")
    private ParameterManager paramManager;

    @objid ("de4f14ed-2879-4b0d-b0e8-5b99eb8c039d")
    public PatternModelAnalysis patternModelAnalysis;

    @objid ("e1772880-cd5f-44ef-9a03-454d35bdd1ab")
    private PatternModelAnalysis report;

    @objid ("c5daf89e-dba1-4347-91b4-ca708c32363c")
    public  RelationGenerator(PatternModelAnalysis report, ParameterManager paramManager) {
        this.report = report;
        this.paramManager = paramManager;
        this.relations = new ArrayList<>();
        
    }

    @objid ("daac3d35-682a-415b-95a5-3d07cf1e711c")
    public void addRelation(MObject source, MDependency dependency) {
        for (MObject destination : source.mGet(dependency)) {
            this.relations.add(new Relation(source, destination, dependency));
        }
        
    }

    @objid ("dea57eb1-66c0-4779-aa98-eb374eeb39e4")
    public void generate(FileWriterUtil filewriter) {
        // Export roots are composition parents of each pattern root, except for the pattern itself.
        this.rootParents = new HashSet<>();
        for (ModelElement root : this.report.getRootParameters()) {
            this.rootParents.add(root.getCompositionOwner());
        }
        
        for (Relation relation : this.relations) {
            if (relation.source != null && relation.destination != null) {
                String sourceElement = getMDepResolutionCode(relation.source, relation.relationType);
                String destElement = getElementResolutionCode(relation.destination);
        
                if (sourceElement != null && destElement != null) {
                    filewriter.countWrite(sourceElement + ".add(" + destElement + ");");
                } else {
                    filewriter.countWrite("// " + sourceElement + ".add(" + destElement + ");");
                }
            }
        }
        
    }

    /**
     * @return the resolution code for the an MDep on an element.
     */
    @objid ("674b43ba-4269-4045-952e-6ad49e26c4a4")
    private String getMDepResolutionCode(MObject element, MDependency dependency) {
        if (IdGenerator.getInstance().exists(element)) {
            StringBuilder ret = new StringBuilder();
            ret.append("this.elements.get(");
            ret.append(IdGenerator.getInstance().getId(element));
            ret.append(").mGet(this.elements.get(");
            ret.append(IdGenerator.getInstance().getId(element));
            ret.append(").getMClass().getDependency(\"");
            ret.append(dependency.getName());
            ret.append("\"))");
            return ret.toString();
        }
        return null;
    }

    /**
     * @return the resolution code for the given element in a model, usually some kind of <code>findElementById</code> code.
     */
    @objid ("77572641-40ec-46d4-9363-c13da6caec31")
    private String getElementResolutionCode(MObject element) {
        // Elementary
        if (element instanceof DataType) {
            IUMLTypes umltype = Modelio.getInstance().getModelingSession().getModel().getUmlTypes();
            DataType type = (DataType) element;
            if (((DataType) element).equals(umltype.getBOOLEAN()) || ((DataType) element).equals(umltype.getBOOLEAN())
                    || ((DataType) element).equals(umltype.getBYTE()) || ((DataType) element).equals(umltype.getCHAR())
                    || ((DataType) element).equals(umltype.getDATE()) || ((DataType) element).equals(umltype.getDOUBLE())
                    || ((DataType) element).equals(umltype.getFLOAT()) || ((DataType) element).equals(umltype.getINTEGER())
                    || ((DataType) element).equals(umltype.getLONG()) || ((DataType) element).equals(umltype.getSHORT())
                    || ((DataType) element).equals(umltype.getSTRING()) || ((DataType) element).equals(umltype.getUNDEFINED())) {
                return "umltypes.get" + type.getName().toUpperCase() + "()";
            }
        }
        
        // Root
        for (MObject exportRoot : this.rootParents) {
            if (element.equals(exportRoot)) {
                return this.paramManager.parameterFormater((ModelElement) element);
            }
        }
        
        // Stereotypes
        Class<? extends MObject> type = element.getMClass().getJavaInterface();
        String uuid = element.getUuid().toString();
        if (element instanceof Stereotype) {
            Stereotype stereotype = (Stereotype) element;
        
            this.report.addModuleDependency(stereotype.getModule());
            return getElementResolutionCode(type, uuid);
        }
        
        // TagType
        if (element instanceof TagType) {
            TagType tagtype = (TagType) element;
        
            this.report.addModuleDependency(tagtype.getModule());
            return getElementResolutionCode(type, uuid);
        }
        
        // NoteType
        if (element instanceof NoteType) {
            NoteType notetype = (NoteType) element;
        
            this.report.addModuleDependency(notetype.getModule());
            return getElementResolutionCode(type, uuid);
        }
        
        // ExternDocumentType
        if (element instanceof ResourceType) {
            ResourceType resourceType = (ResourceType) element;
        
            this.report.addModuleDependency(resourceType.getModule());
            return getElementResolutionCode(type, uuid);
        }
        
        // PropertyTableDefinition
        if (element instanceof PropertyTableDefinition) {
            PropertyTableDefinition propertytabledef = (PropertyTableDefinition) element;
        
            Stereotype stereotype = propertytabledef.getOwnerStereotype();
            if (stereotype != null) {
                ModuleComponent module = stereotype.getModule();
                this.report.addModuleDependency(module);
                return getElementResolutionCode(type, uuid);
            }
        }
        
        // RAMC Element
        if (element.getStatus().isRamc()) {
            this.report.addRamcDependency((ModelElement) element);
            return getElementResolutionCode(type, uuid);
        }
        
        // Others Elements
        if (IdGenerator.getInstance().exists(element)) {
            return "elements.get(" + IdGenerator.getInstance().getId(element) + ")";
        }
        if (element instanceof ModelElement) {
            if (((ModelElement) element).isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERNPARAMETER)) {
                return this.paramManager.parameterFormater((ModelElement) element);
            } else if (!(element instanceof AssociationEnd || element instanceof ConnectorEnd || element instanceof ImpactLink)) {
                return this.paramManager.parameterFormater((ModelElement) element);
            }
        }
        return null;
    }

    @objid ("5cd3770a-20ee-447a-bb5e-67b82978f800")
    private String getElementResolutionCode(Class<? extends MObject> type, String uuid) {
        StringBuilder ret = new StringBuilder();
        ret.append("session.findElementById(Metamodel.getJavaInterface(Metamodel.getMClass(");
        ret.append(type.getName());
        ret.append(".class)),\"");
        ret.append(uuid);
        ret.append("\")");
        return ret.toString();
    }

    @objid ("7b8c6021-0f14-43d0-99d6-fd0d7a33b5e2")
    private static class Relation {
        @objid ("d7a17680-fcc4-4cfb-9dc4-3abff57794fe")
        public static final String BIDIRECTIONAL = "BIDIRECTIONAL";

        @objid ("a9929eeb-c295-4006-999a-608a43582615")
        public MDependency relationType;

        @objid ("230e6faa-9cbf-44a2-84d7-cbf14f8e97b0")
        public MObject source;

        @objid ("28dc19ce-edb9-4616-9829-ef2540b95d0d")
        public MObject destination;

        @objid ("678ebde6-cc57-4f14-a85a-9a76e3467e4f")
        public  Relation(MObject source, MObject destination, MDependency relationType) {
            this.source = source;
            this.relationType = relationType;
            this.destination = destination;
            
        }

    }

}
