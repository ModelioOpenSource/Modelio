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

package org.modelio.gproject.ramc.core.packaging.filters;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * {@link ConfigurableModelFilter} builder service to use to package a model component.
 */
@objid ("6179089a-c746-11e1-96e9-001ec947ccaf")
public class RamcFilterBuilder {
    @objid ("1a5a6fe5-c789-11e1-96e9-001ec947ccaf")
    private final DependencyFilter dependencyFilter;

    @objid ("1a5a6fe9-c789-11e1-96e9-001ec947ccaf")
    private final NoteFilter noteFilter;

    @objid ("1a5a6fea-c789-11e1-96e9-001ec947ccaf")
    private final TaggedValueFilter tagFilter;

    @objid ("5f8a4fe5-289d-41a3-9713-d5cc8061430b")
    private SmMetamodel smMetamodel;

    /**
     * Initialize the builder.
     * 
     * @param smMetamodel the modelio metamodel.
     * @param artifact the model component artifact.
     */
    @objid ("61790927-c746-11e1-96e9-001ec947ccaf")
    public RamcFilterBuilder(SmMetamodel smMetamodel, Artifact artifact) {
        this.smMetamodel = smMetamodel;
        this.dependencyFilter = new DependencyFilter(null, this.smMetamodel.getMExpert());
        this.noteFilter = new NoteFilter(artifact);
        this.tagFilter = new TaggedValueFilter(artifact);
    }

    /**
     * Tells to accept {@link Dependency UML Dependencies} with the given stereotype.
     * 
     * @param type a stereotype on {@link Dependency} metaclass.
     */
    @objid ("6179078e-c746-11e1-96e9-001ec947ccaf")
    public void addDependencyStereotype(final Stereotype type) {
        this.dependencyFilter.addStereotype(type);
    }

    /**
     * Add a type of notes to accept.
     * 
     * @param type a note type
     */
    @objid ("617907d6-c746-11e1-96e9-001ec947ccaf")
    public void addNoteType(final NoteType type) {
        this.noteFilter.addNoteType(type);
    }

    /**
     * Add a type of tags to accept.
     * 
     * @param type a tag type.
     */
    @objid ("6179090a-c746-11e1-96e9-001ec947ccaf")
    public void addTagType(final TagType type) {
        this.tagFilter.addTagType(type);
    }

    /**
     * Build the RAMC packaging model filter.
     * 
     * @return the RAMC model filter.
     */
    @objid ("f166aaa3-c9a1-11e1-96e9-001ec947ccaf")
    public ConfigurableModelFilter getModelFilter() {
        ConfigurableModelFilter modelFilter = new ConfigurableModelFilter(this.smMetamodel);
        
        // Configure filters for recursion
        this.dependencyFilter.setTargetFilter(modelFilter);
        MExpert expert = this.smMetamodel.getMExpert();
        LinkTargetFilter linkFilter = new LinkTargetFilter(expert, modelFilter);
        
        // Add link filters
        for (MClass metaclass : this.smMetamodel.getMClass(Element.class).getSub(true)) {
            if (expert.isLink(metaclass)) {
                if (metaclass.getName().equals(Dependency.class.getSimpleName())) {
                    modelFilter.setFilter(Dependency.class, this.dependencyFilter);
                } else if (metaclass.getName().equals(AssociationEnd.class.getSimpleName())) {
                    modelFilter.setFilter(AssociationEnd.class, new AssociationEndFilter(modelFilter, expert));
                } else {
                    modelFilter.setFilter(((SmClass) metaclass).getJavaInterface(), linkFilter);
                }
            }
        }
        
        // Add node filters
        modelFilter.setFilter(TaggedValue.class, this.tagFilter);
        modelFilter.setFilter(Note.class, this.noteFilter);
        modelFilter.setFilter(NameSpace.class, new NameSpaceFilter());
        modelFilter.setFilter(NaryAssociationEnd.class, new NaryAssociationEndFilter(modelFilter, expert));
        modelFilter.setFilter(NaryLinkEnd.class, new NaryLinkEndFilter(modelFilter, expert));
        FeatureFilter featureFilter = new FeatureFilter();
        modelFilter.setFilter(Attribute.class, featureFilter);
        modelFilter.setFilter(Operation.class, featureFilter);
        IgnoreFilter ignoreFilter = new IgnoreFilter();
        modelFilter.setFilter(AbstractDiagram.class, ignoreFilter);
        modelFilter.setFilter(MatrixDefinition.class, ignoreFilter);
        modelFilter.setFilter(AbstractResource.class, ignoreFilter);
        return modelFilter;
    }

}
