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

package org.modelio.metamodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.DiagramSetSmClass;
import org.modelio.metamodel.impl.diagrams.GraphDiagramSmClass;
import org.modelio.metamodel.impl.expert.InfrastructureMetamodelExpert;
import org.modelio.metamodel.impl.impact.ImpactDiagramSmClass;
import org.modelio.metamodel.impl.impact.ImpactLinkSmClass;
import org.modelio.metamodel.impl.impact.ImpactModelSmClass;
import org.modelio.metamodel.impl.impact.ImpactProjectSmClass;
import org.modelio.metamodel.impl.mda.ModuleComponentSmClass;
import org.modelio.metamodel.impl.mda.ModuleParameterSmClass;
import org.modelio.metamodel.impl.mmextensions.infrastructure.modelshield.InfrastructureCheckerFactory;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractResourceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.DependencySmClass;
import org.modelio.metamodel.impl.uml.infrastructure.DocumentSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ExternProcessorSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.MethodologicalLinkSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.NoteSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.NoteTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ProfileSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ResourceSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ResourceTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TagParameterSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TagTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.TaggedValueSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.MatrixValueDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.matrix.QueryDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.DynamicPropertyDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.EnumeratedPropertyTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.LocalPropertyTableSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyEnumerationLitteralSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableDefinitionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTypeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.properties.TypedPropertyTableSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;
import org.modelio.vcore.smkernel.meta.AbstractMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependencyTypeChecker;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

@objid ("4f75c2a2-72e6-49e0-abab-ac3a24361ef1")
public final class InfrastructureMetamodelFragment extends AbstractMetamodelFragment {
    @objid ("c76f1c4f-3a74-4b34-a792-df6dedf24b32")
    private static final InfrastructureMetamodelFragment INSTANCE = new InfrastructureMetamodelFragment();

    @objid ("f67781d5-4b9a-4628-a516-36d67af6ee5f")
    @Override
    public final MExpert createMExpert(SmMetamodel mm) {
        return new InfrastructureMetamodelExpert(mm);
    }

    @objid ("717c3755-e65e-49bd-a9b3-c71fb852de8c")
    public static InfrastructureMetamodelFragment getInstance() {
        return INSTANCE;
    }

    @objid ("e8bd1939-3f7a-46ff-8880-b4118a58d74d")
    @Override
    public Collection<VersionedItem<MMetamodelFragment>> getNeededFragments() {
        Collection<VersionedItem<MMetamodelFragment>> ret = new ArrayList<>(super.getNeededFragments());
        
        
        return ret;
    }

    @objid ("57da058c-3190-446f-abb1-d7733899108a")
    public InfrastructureMetamodelFragment() {
        	super("Infrastructure", new Version("2.1.02"), "Modeliosoft", "3.8.00");
    }

    @objid ("bc191702-321d-4dec-b6ce-7aa6b83c00a6")
    @Override
    public List<SmClass> createMetaclasses() {
        	final List<SmClass> metaclasses = new ArrayList<>(41);
        	metaclasses.add(new ElementSmClass(this));
        	metaclasses.add(new ModelElementSmClass(this));
        	metaclasses.add(new AbstractDiagramSmClass(this));
        	metaclasses.add(new ImpactDiagramSmClass(this));
        	metaclasses.add(new GraphDiagramSmClass(this));
        	metaclasses.add(new DiagramSetSmClass(this));
        	metaclasses.add(new AbstractProjectSmClass(this));
        	metaclasses.add(new ModuleComponentSmClass(this));
        	metaclasses.add(new ImpactProjectSmClass(this));
        	metaclasses.add(new AbstractResourceSmClass(this));
        	metaclasses.add(new ResourceSmClass(this));
        	metaclasses.add(new DocumentSmClass(this));
        	metaclasses.add(new PropertyTypeSmClass(this));
        	metaclasses.add(new EnumeratedPropertyTypeSmClass(this));
        	metaclasses.add(new PropertyEnumerationLitteralSmClass(this));
        	metaclasses.add(new PropertyDefinitionSmClass(this));
        	metaclasses.add(new DynamicPropertyDefinitionSmClass(this));
        	metaclasses.add(new PropertyTableDefinitionSmClass(this));
        	metaclasses.add(new ProfileSmClass(this));
        	metaclasses.add(new TagTypeSmClass(this));
        	metaclasses.add(new TaggedValueSmClass(this));
        	metaclasses.add(new StereotypeSmClass(this));
        	metaclasses.add(new NoteTypeSmClass(this));
        	metaclasses.add(new NoteSmClass(this));
        	metaclasses.add(new DependencySmClass(this));
        	metaclasses.add(new MethodologicalLinkSmClass(this));
        	metaclasses.add(new ModuleParameterSmClass(this));
        	metaclasses.add(new ResourceTypeSmClass(this));
        	metaclasses.add(new ImpactLinkSmClass(this));
        	metaclasses.add(new ImpactModelSmClass(this));
        	metaclasses.add(new MatrixDefinitionSmClass(this));
        	metaclasses.add(new ExternProcessorSmClass(this));
        	metaclasses.add(new MetaclassReferenceSmClass(this));
        	metaclasses.add(new QueryDefinitionSmClass(this));
        	metaclasses.add(new TagParameterSmClass(this));
        	metaclasses.add(new PropertyTableSmClass(this));
        	metaclasses.add(new TypedPropertyTableSmClass(this));
        	metaclasses.add(new LocalPropertyTableSmClass(this));
        	metaclasses.add(new MatrixValueDefinitionSmClass(this));
        
        	 return metaclasses;
    }

    /**
     * Create all the model checker classes.
     * @param metamodel the metamodel
     * @return the live model checkers.
     */
    @objid ("4daecaa0-f97e-482a-b91b-eb75b76a3b0b")
    @Override
    public final Collection<SmDependencyTypeChecker> createDependencyCheckers(SmMetamodel metamodel) {
        // This code is automatically generated from all checker classes found in
        //   'null' package.
        final List<SmDependencyTypeChecker> checkers = new ArrayList<>(+0);
        
        
        return checkers;
    }

    @objid ("a70e54e0-60d2-4510-8479-19a9650571b8")
    @Override
    public final boolean isExtension() {
        return false;
    }

    /**
     * Get the model shield checkers factory.
     * @param metamodel the metamodel
     * @return the model shield checkers factory.
     */
    @objid ("55367511-0fb3-4464-9a51-fc1219407a47")
    @Override
    public ICheckerFactory getModelShieldCheckers() {
        return new InfrastructureCheckerFactory();
    }

}
