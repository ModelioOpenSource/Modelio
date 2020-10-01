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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.exporter.PatternModelAnalysis;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("6a149e04-4311-41c8-b672-91370b0ee49d")
class MetaGenerator {
    @objid ("0bc47bf3-1a92-4dd6-9b1e-12adaae3ac06")
    private RelationGenerator relationGenerator;

    @objid ("fb1c69d4-e0ec-4ec1-9fce-f4699a436855")
    private DiagramGenerator diagramGenerator;

    @objid ("1f5eab05-f4d9-4293-b31d-72cbbe38e10f")
    private ParameterManager paramManager;

    @objid ("202d8ea3-7e18-4688-a921-05e207b43d80")
    public PatternModelAnalysis patternModelAnalysis;

    @objid ("623cfcea-d902-4439-8f41-dc0073b5d9fc")
    private PatternModelAnalysis report;

    @objid ("c2395b0f-40bc-4d00-96af-5fc48c6841dc")
    public MetaGenerator() {
        this.report = new PatternModelAnalysis();
        this.diagramGenerator = new DiagramGenerator();
        this.paramManager = new ParameterManager(this.report);
        this.relationGenerator = new RelationGenerator(this.report, this.paramManager);
    }

    @objid ("69e7397b-e2b3-425f-9104-1615955389a1")
    private void generatePatternCode(FileWriterUtil filewriter, MObject element) {
        generateElementCode(filewriter, element);
        
        /**
         * Bug Workaround
         * getCompositionChildren() for NaryConnector node is cycling
         */
        if (!(element instanceof NaryConnector)) {
            for (MObject children : element.getCompositionChildren()) {
        
                generatePatternCode(filewriter, children);
            }
        
        }
    }

    @objid ("8d72eba4-8838-4868-b468-ca1ed32c107d")
    private void generateElementCode(FileWriterUtil filewriter, MObject element) {
        if (!IdGenerator.getInstance().exists(element)) {
            int elementsIndex = IdGenerator.getInstance().getId(element);
            String metaclass = element.getMClass().getJavaInterface().getName();
        
            // Element Creation
        
            filewriter.countWrite("this.elements.add(this.model.create(" + metaclass + ".class, this.root));");
        
            // Attribute Creation
            for (MAttribute attribute : element.getMClass().getAttributes(true)) {
                if (!attribute.getName().equals("UiData")) {
                    generateAttribute(filewriter, element, attribute, elementsIndex);
                }
            }
        
            // Relations
            for (MDependency dependency : element.getMClass().getDependencies(true)) {
                this.relationGenerator.addRelation(element, dependency);
            }
        
            if (element instanceof AbstractDiagram) {
                this.diagramGenerator.addDiagram((AbstractDiagram) element);
            }
        }
    }

    @objid ("ff7893e4-f4ae-46a3-9892-c418d03dfc75")
    private void generateAttribute(FileWriterUtil filewriter, MObject element, MAttribute attribute, int elementsIndex) {
        final Class<?> attType = attribute.getType();
        final Object attValue = element.mGet(attribute);
        if (attType == UUID.class) {
            // Identifier is already set, don't read it twice and don't
            // reidentify elements.
        } else if (attType == String.class) {
            filewriter.countWrite("this.elements.get(" + elementsIndex + ").mSet(this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\"),\"" + this.paramManager.parameterFormater(attValue.toString(), element) + "\");");
        } else if (attType == Integer.class) {
            filewriter.countWrite("this.elements.get(" + elementsIndex + ").mSet(this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\"),Integer.valueOf(\"" + attValue.toString() + "\"));");
        } else if (attType == Long.class) {
            filewriter.countWrite("this.elements.get(" + elementsIndex + ").mSet(this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\"),Long.valueOf(\"" + attValue.toString() + "\"));");
        } else if (attType == Float.class) {
            filewriter.countWrite("this.elements.get(" + elementsIndex + ").mSet(this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\"),Float.valueOf(\"" + attValue.toString() + "\"));");
        } else if (attType == Double.class) {
            filewriter.countWrite("this.elements.get(" + elementsIndex + ").mSet(this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\"),Double.valueOf(\"" + attValue.toString() + "\"));");
        } else if (attType == Boolean.class) {
            filewriter.countWrite("this.elements.get(" + elementsIndex + ").mSet(this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\"),Boolean.valueOf(\"" + attValue.toString() + "\"));");
        } else if (attType.isEnum()) {
            filewriter.countWrite("this.elements.get(" + elementsIndex + ").mSet(this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\"),Enum.valueOf((java.lang.Class<? extends Enum>)this.elements.get(" + elementsIndex + ").getMClass().getAttribute(\"" + attribute.getName() + "\").getType(), \"" + this.paramManager.parameterFormater(((Enum<?>) attValue).name(), element) + "\"));");
        }
    }

    @objid ("1b878b64-9e2e-4371-9f4e-2adeb19f709d")
    public PatternModelAnalysis getReport() {
        return this.report;
    }

    @objid ("a1f32594-bdef-48f0-9986-0c3fc82203e2")
    public RelationGenerator getRelationGenerator() {
        return this.relationGenerator;
    }

    @objid ("628fe69c-7566-43f5-b9bb-a1b9d4b87062")
    public DiagramGenerator getDiagramGenerator() {
        return this.diagramGenerator;
    }

    @objid ("7af98732-f09b-4aef-9908-5a5309b8d2e9")
    public void generate(FileWriterUtil filewriter, Package modelPattern) {
        for (ModelElement root : computeRoots(modelPattern)) {
            this.report.addRootParameter(root);
            generatePatternCode(filewriter, root);
        }
    }

    /**
     * Compute exported roots for a pattern. They are the first elements in the
     * composition tree that must be exported fully.
     */
    @objid ("1501c806-f847-4d73-ab9e-9bf03592e795")
    private List<ModelElement> computeRoots(Package modelPattern) {
        final List<ModelElement> roots = new ArrayList<>();
        
        for (MObject root : modelPattern.getCompositionChildren()) {
            if (root instanceof ModelElement) {
                computeRoots((ModelElement) root, roots);
            }
        }
        return roots.isEmpty() ? Arrays.asList(modelPattern) : roots;
    }

    @objid ("c91ccc13-235f-4b88-929e-034d2674bbf8")
    private void computeRoots(ModelElement element, List<ModelElement> collectedRoots) {
        MObject owner = element.getCompositionOwner();
        
        if (owner instanceof ModelElement && !(element instanceof Note) && !(element instanceof TaggedValue)) {
            ModelElement ownerElement = (ModelElement) owner;
        
            if (!element.isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERNROOT) && ownerElement.isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERNROOT)) {
                if (!collectedRoots.contains(element)) {
                    collectedRoots.add(element);
                }
            } else if (!element.isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERNROOT) && ownerElement.isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERN)) {
                if (!collectedRoots.contains(element)) {
                    collectedRoots.add(element);
                }
            }
        }
        
        /**
         * Bug Workaround
         * getCompositionChildren() for NaryConnector node is cycling
         */
        
        if (!(element instanceof NaryConnector)) {
            for (MObject childen : element.getCompositionChildren()) {
                if (childen instanceof ModelElement) {
                    computeRoots((ModelElement) childen, collectedRoots);
                }
            }
        }
    }

}
