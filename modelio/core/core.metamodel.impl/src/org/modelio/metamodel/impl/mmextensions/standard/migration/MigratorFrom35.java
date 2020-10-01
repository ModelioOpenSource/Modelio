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

package org.modelio.metamodel.impl.mmextensions.standard.migration;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.model.spi.mm.MetaclassRenamer;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorReader;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelFragmentDescriptor;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.MofBuilder;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodelFragment;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodelMerger;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmDependency;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Migrator from Modelio 3.5 to 3.6 .
 * <p>
 * <pre>
 * PropertyContainer.DefinedTable:PropertyTableDefinition -->
 * ModuleComponent["LocalModule"].OwnedProfile["Analyst"]:
 * Profile.OwnedReference[name]:
 * MetaclassReference.DefinedTable
 * 
 * 'name' depend du stereotype sur la propertytabledef:
 * - 'dictionary_propertyset'{01ec141c-0000-12fc-0000-000000000000} Stereotype
 * - 'business_rule_propertyset'{01ec141c-0000-1301-0000-000000000000} Stereotype
 * - 'requirement_propertyset'{01ec141c-0000-12f7-0000-000000000000} Stereotype
 * - 'goal_propertyset'{01ec141c-0000-12f2-0000-000000000000} Stereotype
 * - 'risk_propertyset'{679a9417-8f06-4255-a409-1e1f7136e418} Stereotype
 * - 'test_propertyset'{859f8b76-5acc-4a9c-a5eb-973467388b13} Stereotype
 * - sinon AnalystContainer
 * </pre>
 * @author cma
 * @since 3.6
 */
@objid ("76cc0a33-7feb-4f4e-8e72-3c3fc6f17eb9")
class MigratorFrom35 implements IMofRepositoryMigrator {
    @objid ("3fa9f30d-042b-4710-8940-25dba5bd0d06")
    private static final Map<String, String> PropertyTableDefinitionMapping = initPropertyTableDefinitionMapping();

    /**
     * The <<manifestation>> stereotype on Dependency
     */
    @objid ("b59ca05b-e8c2-4150-a8ab-08cdb762ad80")
    private static MRef manifestation_dependency_stereotype = new MRef(Stereotype.MQNAME, "d5bccf8e-79b3-48df-8c79-09200aa52d19", "manifestation");

    @objid ("d1b9f0ec-6769-42ce-8e53-a57652ee823d")
    private MetaclassRenamer mcRenamer;

    @objid ("1741ae9e-9073-48ff-9e24-e32b2e5d4ef4")
    private Map<String, MofSmClass> mcToDelete = new HashMap<>();

    @objid ("ee2dfc14-bb45-4786-a01c-5d2d28952d6c")
    private final MetamodelVersionDescriptor sourceMetamodel;

    @objid ("a315a1be-c7dd-422e-8a9a-b262d457fd18")
    private final MetamodelVersionDescriptor targetMetamodel;

    @objid ("5b1b7ef8-f401-4add-9d19-921cde3bc599")
    public MigratorFrom35(MetamodelVersionDescriptor sourceMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        this.sourceMetamodel = sourceMetamodel;
        this.targetMetamodel = targetMetamodel;
    }

    @objid ("a398ff8b-a5b4-4dda-b8fa-a56658ab2d66")
    @Override
    public MetamodelChangeDescriptor getMetamodelChanges() {
        return new MetamodelChangeDescriptor();
    }

    @objid ("78ec635a-3152-486d-972d-e316059efdef")
    @Override
    public MetamodelVersionDescriptor getSourceMetamodel() {
        return this.sourceMetamodel;
    }

    @objid ("c5d1a7cc-295e-41ca-b6a5-2d6bdcb278e9")
    @Override
    public MetamodelVersionDescriptor getTargetMetamodel() {
        return this.targetMetamodel;
    }

    /**
     * Modify the metamodel so that it can read the source repository.
     * 
     * @param metamodel the metamodel at the final state
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on fatal failure preventing migration
     */
    @objid ("ba2c134e-7c33-4f13-9704-80f1f8f10914")
    @Override
    public void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException {
        if(metamodel.getFragment("Analyst")==null){
            // Modelio open source without Analyst 2.0.00, load it.
            loadMetamodel(metamodel, "/migration/metamodel_analyst_2.xml");
        }
        
        // Load the source metamodel
        new MofMetamodelMerger(metamodel)
        .setTemporary(true)
        .merge(StandardMmMigrationProvider.loadMetamodel(getSourceMetamodel()));
              
        MofSmClass stdModelElementMc = (MofSmClass) metamodel.getMClass("Standard.ModelElement");
        MofSmClass infraModelElementMc = (MofSmClass) metamodel.getMClass("Infrastructure.ModelElement");
        
        
        // Recreate Analyst.AnalystProject.PropertyRoot : PropertyContainer 
        try (MofBuilder b = metamodel.builder().setTemporary(true);) {
            MofSmClass analystProjectMC = (MofSmClass) metamodel.getMClass("Analyst.AnalystProject");
              
            b.createDep("PropertyRoot")
            .setSource(analystProjectMC)
            .setTarget("Standard.PropertyContainer")
            .setComposition()
            .setOpposite("OwnerProject")
            .build();
            
            // Recreate PropertyTableDefinition.Owner: PropertyContainer [1..1] opposite of PropertyContainer.DefinedTable
            b.createDep("Owner")
            .setSource((MofSmClass) metamodel.getMClass("Infrastructure.PropertyTableDefinition"))
            .setTarget("Standard.PropertyContainer")
            .setNoPartOf()
            .setOpposite("DefinedTable")
            .build();
            
            // BpmnLane.PartitionElement v3.6 is buggy : it points to UmlModelElement but contains Analyst and BPMN elements
            // Recreate it with its opposite to point to ModelElement.
            // It will be correctly migrated on Modelio 3.7.1 metamodel
            MofSmClass bpmnLaneMc = (MofSmClass) metamodel.getMClass("Standard.BpmnLane");
            
            bpmnLaneMc.deleteDependency("PartitionElement");
        
            if (infraModelElementMc.getDependency("BpmnLaneRefs") != null) {
                infraModelElementMc.deleteDependency("BpmnLaneRefs");
            }
            if (stdModelElementMc.getDependency("BpmnLaneRefs") != null) {
                stdModelElementMc.deleteDependency("BpmnLaneRefs");
            }
        
            MofSmDependency partitionDep = b.createDep("PartitionElement")
                    .setSource(bpmnLaneMc)
                    .setTarget(infraModelElementMc)
                    .setCardinality(0, 1)
                    .setPartOf()
                    .createOpposite("BpmnLaneRefs", 0 , -1)
                    .build();
        
            b.createDep("BpmnLaneRefs")
            .setSource(stdModelElementMc)
            .setTarget(bpmnLaneMc)
            .setCardinality(0, -1)
            .setOpposite(partitionDep)
            .build();
        
        }
        
        {
            assert(metamodel.getMClass("Standard.PropertyContainer") != null);
            assert(metamodel.getMClass("Standard.PropertyContainer").getDependency("DefinedTable") != null);
            assert(metamodel.getMClass("Standard.PropertyContainer").getDependency("DefinedType") != null);
        
            assert(metamodel.getMClass("Standard.AnalystProject").getDependency("PropertyRoot") != null);
            assert(metamodel.getMClass("Analyst.AnalystProject").getDependency("PropertyRoot") != null);
        }
        
        {
            MofSmClass bpmnLaneMc = (MofSmClass) metamodel.getMClass("Standard.BpmnLane");
            MDependency stdModelLaneDep = stdModelElementMc.getDependency("BpmnLaneRefs");
            MDependency lanePartitionDep = bpmnLaneMc.getDependency("PartitionElement");
        
            assert(stdModelLaneDep != null);
            assert(stdModelLaneDep.getTarget() == bpmnLaneMc) : stdModelLaneDep;
        
            assert(lanePartitionDep != null);
            assert(lanePartitionDep.getTarget() == infraModelElementMc) : lanePartitionDep;
        }
        
        // Process metaclasses renamings
        // Read renamed classes
        prepareMetaclassesRenaming(metamodel);
    }

    @objid ("49fdf831-b1c0-4c04-acdf-6ed93cf6c98d")
    @Override
    public void run(IModelioProgress monitor, IMofSession mofSession) throws MofMigrationException {
        SubProgress mon = SubProgress.convert(monitor, 10);
        
        try {
            deleteObsoleteObjects(mon.newChild(1), mofSession);
            transmuteRenamedClasses(mon.newChild(7), mofSession);
            handlePropertyContainer(mon.newChild(1), mofSession);
            fixStereotypesBaseClass(mon.newChild(1), mofSession);
            fixManifestations(mon.newChild(1), mofSession);
        } catch (MetaclassNotFoundException e) {
            throw new MofMigrationException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("3cab1379-1b10-4302-af24-39f559cacbf8")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[from "+getSourceMetamodel()+" to "+getTargetMetamodel()+"]";
    }

    @objid ("5e9a83f3-aa36-4503-94cc-4390e3c3cb33")
    protected void loadMetamodel(MofMetamodel metamodel, String path) throws MofMigrationException {
        try (InputStream is= getClass().getResourceAsStream(path)) {
            MetamodelDescriptor desc = MetamodelDescriptorReader.readFrom(is, path);
            metamodel.merge(desc);
        } catch (IOException e) {
            throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("d41ca2c3-fa12-49a3-95ab-596146be6bf4")
    private static MClassRef decodeQualifiedName(String qualifiedName) {
        int idx = qualifiedName.lastIndexOf('.');
        if (idx == -1) {
            throw new IllegalArgumentException(qualifiedName);
        }
        
        MClassRef ret = new MClassRef(qualifiedName.substring(0, idx), qualifiedName.substring(idx+1));
        return ret;
    }

    @objid ("640c4171-8b20-4b97-8bf7-6686ca3fa94b")
    private void deleteObsoleteObjects(IModelioProgress monitor, IMofSession mofSession) {
        SubProgress mon = SubProgress.convert(monitor, this.mcToDelete.size());
        for (MofSmClass mc : this.mcToDelete.values()) {
            for (MofSmObjectImpl obj : mofSession.findByClass(mc, false)) {
                obj.delete();
            }
            mon.worked(1);
        }
    }

    /**
     * Transform Manifestations toward ModelElements that are not UmlModelElement
     * to Dependency stereotyped &lt;&lt;manifestation>>
     * @param reporter the migration report
     * 
     * @param monitor a progress monitor
     * @param mofsession the migration session
     * @throws org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException should not occur
     */
    @objid ("f20daf4a-70b3-4f92-8529-d546eb289860")
    private void fixManifestations(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        SmClass UmlModelElementCls = mofsession.getMetaclass(UmlModelElement.MQNAME);
        MObject manifStereotype = mofsession.getObjectReference(manifestation_dependency_stereotype);
        
        for (MofSmObjectImpl manif : mofsession.findByClass(Manifestation.MQNAME, true)) { 
            List<MofSmObjectImpl> manifSrc = manif.getDep("Owner");
            List<MofSmObjectImpl> manifTarget = manif.getDep("UtilizedElement");
            for (MofSmObjectImpl target : manifTarget) {
                if (! target.getMClass().hasBase(UmlModelElementCls)) {
                    // The manifestation must be converted to a Dependency <<manifestation>>
                    
                    MofSmObjectImpl newDep = mofsession.createObject(Dependency.MQNAME, "manifest");
                    newDep.getDep("Impacted").addAll(manifSrc);
                    newDep.getDep("DependsOn").add(target);
                    newDep.getDep("Extension").add((MofSmObjectImpl) manifStereotype);
        
                    // delete the manif
                    manif.delete();
        
                    mofsession.getReport().getLogger().printf("    Replaced Manifestation from %s to %s with a Dependency.\n", manifSrc, manifTarget);
                }
            }
        }
    }

    /**
     * Fix Stereotype and MetaclassReference base class so they use qualified name.
     * <p>
     * Use also {@link #mcToTransmute} to rename old qualified names to new ones.
     * <p>
     * Requires the elements having been transmuted to the new metaclasses.
     * @param reporter the logger
     * 
     * @param monitor a progress monitor
     * @param mofsession the session
     * @throws org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException on buggy code
     */
    @objid ("58397d53-11f7-4f31-b63b-ffc0c9e6d205")
    private void fixStereotypesBaseClass(IModelioProgress monitor, IMofSession mofsession) throws MetaclassNotFoundException {
        // Fix Stereotype.BaseClassName
        SmClass stereotypeMc = mofsession.getMetaclass(Stereotype.MQNAME);
        MAttribute baseClassAtt = stereotypeMc.getAttribute("BaseClassName");
        IMigrationReporter reporter = mofsession.getReport();
        
        for (MofSmObjectImpl obj : mofsession.findByClass(stereotypeMc, true)) {
            if (obj.isModifiable() && obj.isValid()) {
                String oldBase = (String) obj.mGet(baseClassAtt);
                if (oldBase != null && ! oldBase.isEmpty()) {
                    String newBase = getNewBaseClassName(oldBase,mofsession);
                    if (! oldBase.equals(newBase)) {
                        obj.mSet(baseClassAtt, newBase);
                        reporter.getLogger().format("   Fixed (%s).BaseClassName from '%s' to '%s'.\n", obj, oldBase, newBase);
                    }
                } else if (mofsession.getTargetRepository().getRepositoryId() == obj.getRepositoryObject().getRepositoryId()){
                    reporter.getLogger().format("   WARN: %s Stereotype has no base metaclass.\n", obj);
                }
            }
        }
        
        // MetaclassReference.ReferencedClassName
        stereotypeMc = mofsession.getMetaclass(MetaclassReference.MQNAME);
        baseClassAtt = stereotypeMc.getAttribute("ReferencedClassName");
        
        for (MofSmObjectImpl obj : mofsession.findByClass(stereotypeMc, true)) {
            if (obj.isModifiable() && obj.isValid()) {
                String oldBase = (String) obj.mGet(baseClassAtt);
                if (oldBase != null) {
                    String newBase = getNewBaseClassName(oldBase,mofsession);
                    if (! Objects.equals(oldBase,newBase)) {
                        obj.mSet(baseClassAtt, newBase);
                        reporter.getLogger().format("   Fixed (%s).ReferencedClassName from '%s' to '%s'.\n", obj, oldBase, newBase);
                    }
                } else {
                    reporter.getLogger().format("   Warn: %s has no ReferencedClassName.\n", obj);
                }
            }
        }
    }

    @objid ("1ec1c744-fe14-4191-b103-cb90fdfdc9c1")
    private String getNewBaseClassName(String oldBase, IMofSession mofsession) {
        SmClass foundMetaclass = mofsession.getMetamodel().getMClass(oldBase);
        String nameToReplace = foundMetaclass != null ? foundMetaclass.getQualifiedName() : oldBase;
        
        MofSmClass entry = this.mcRenamer.getNewMetaclass(nameToReplace);
        if (entry != null) {
            return entry.getQualifiedName();
        } else {
            return nameToReplace;
        }
    }

    @objid ("50d1d3cc-412f-4603-b6e7-b5e1836cbfe9")
    private void handlePropertyContainer(IModelioProgress monitor, IMofSession mofSession) throws MetaclassNotFoundException {
        /*
          PropertyContainer->DefinedType : PropertyType -->
            ModuleComponent["LocalModule"].DefinedPropertyType
        
          PropertyContainer.DefinedTable:PropertyTableDefinition --> 
             ModuleComponent["LocalModule"].OwnedProfile["Analyst"]:
                Profile.OwnedReference[name]:
                    MetaclassReference.DefinedTable
                    
          'name' depend du stereotype sur la propertytabledef:
            - 'dictionary_propertyset'{01ec141c-0000-12fc-0000-000000000000} Stereotype
            - 'business_rule_propertyset'{01ec141c-0000-1301-0000-000000000000} Stereotype
            - 'requirement_propertyset'{01ec141c-0000-12f7-0000-000000000000} Stereotype
            - 'goal_propertyset'{01ec141c-0000-12f2-0000-000000000000} Stereotype
            - 'risk_propertyset'{679a9417-8f06-4255-a409-1e1f7136e418} Stereotype
            - 'test_propertyset'{859f8b76-5acc-4a9c-a5eb-973467388b13} Stereotype
                - sinon AnalystContainer
         */
        
        @SuppressWarnings("resource")
        PrintWriter logger = mofSession.getReport().getLogger();
        
        logger.printf("\n Processing 3.5 Analyst property container...\n");
        
        // Find or create 'LocalModule' module
        SmClass moduleMc = mofSession.getMetaclass("Infrastructure.ModuleComponent");
        SmClass mcRefMc = mofSession.getMetaclass("Infrastructure.MetaclassReference");
        
        MofSmObjectImpl moduleComponent = mofSession
                .findByClass(moduleMc, false).stream()
                .filter(o -> o.getName().equals("LocalModule"))
                .findFirst()
                .orElseGet(
                        () -> mofSession.createObject(moduleMc, "LocalModule")
                        );
        
        
        MofSmObjectImpl analystProfile = null; // 'Analyst' profile under 'LocalModule'
        
        // Iterate all AnalystProject
        Collection<MofSmObjectImpl> analystProjects = mofSession.findByClass("Analyst.AnalystProject", false);
        
        for (MofSmObjectImpl analystProj : analystProjects) {
            Collection<MofSmObjectImpl> propertyContainers = analystProj.getDep("PropertyRoot");
            logger.printf(" Found %d property containers in %s. \n", propertyContainers.size(), analystProj);
        
            // Iterate all PropertyContainers
            for (MofSmObjectImpl propCont : new ArrayList<>(propertyContainers)) {
                // Migrate  PropertyContainer->DefinedType : PropertyType 
                //            --> ModuleComponent["LocalModule"].DefinedPropertyType
                List<MofSmObjectImpl> propertyTypes = propCont.getDep("DefinedType");
                logger.printf("   Found %d property types in %s. \n", propertyTypes.size(), propCont);
                
                for (MofSmObjectImpl typeDef : new ArrayList<>(propertyTypes)) {
                    propertyTypes.remove(typeDef);
                    moduleComponent.getDep("DefinedPropertyType").add(typeDef);
        
                    logger.printf("     Moved %s  \n", typeDef);
                    logger.printf("        from %s \n", propCont);
                    logger.printf("        to %s. \n", moduleComponent);
                }
        
                // Iterates property Table Definitions
                List<MofSmObjectImpl> propertyTableDefinitions = propCont.getDep("DefinedTable");
                logger.printf("   Found %d property table definitions in %s. \n", propertyTableDefinitions.size(), propCont);
        
                for (MofSmObjectImpl tableDef : new ArrayList<>(propertyTableDefinitions)) {
        
                    if (analystProfile == null) {
                        // Find or create 'Analyst' profile under 'LocalModule'
                        analystProfile = mofSession.getOrCreate(
                                moduleComponent, "OwnedProfile", "Infrastructure.Profile", "Analyst");
                    }
        
                    // Get the base metaclass name from the PropertyContainer stereotype
                    String metaclassName = tableDef.getDep("Extension").stream() // look at stereotypes
                            .map(ste -> PropertyTableDefinitionMapping.get(ste.getName())) // get base class name from stereotype
                            .filter(s -> s != null) // ignore unknown stereotypes
                            .findFirst()
                            .orElse("Analyst.AnalystContainer"); // default base class
        
                    // Get or create 'metaclassName' metaclass reference
                    final MofSmObjectImpl analystProfile2 = analystProfile ;
                    MofSmObjectImpl metaclassRef = analystProfile
                            .getDep("OwnedReference")
                            .stream()
                            .filter(o -> o.getAtt("ReferencedClassName").equals(metaclassName))
                            .findFirst()
                            .orElseGet(() -> {
                                // Lambda that creates the missing metaclass reference
                                MofSmObjectImpl obj = mofSession.createObject(mcRefMc);
                                obj.setAttVal("ReferencedClassName", metaclassName);
                                analystProfile2.getDep("OwnedReference").add(obj);
                                
                                logger.printf("     Created '%s' %s in %s.\n", metaclassName, MetaclassReference.MQNAME, analystProfile2);
                                return obj;
                            });
        
                    propertyTableDefinitions.remove(tableDef);
                    metaclassRef.getDep("DefinedTable").add(tableDef);
        
                    logger.printf("     Moved %s  \n", tableDef);
                    logger.printf("        from %s \n", propCont);
                    logger.printf("        to '%s' %s. \n", metaclassRef.getAtt("ReferencedClassName"), metaclassRef);
                }
        
                assert(propCont.getDep("DefinedTable").isEmpty());
                
                propCont.delete();
                logger.printf("   Deleted obsolete %s from %s. \n", propCont, analystProj);
            }
        }
        
        logger.printf(" Processed 3.5 Analyst property containers.\n");
    }

    @objid ("bf92bfe7-ccdd-4097-a24b-e49a41b7677e")
    private static Map<String, String> initPropertyTableDefinitionMapping() {
        MRef dictionary_propertyset = new MRef(Stereotype.MQNAME, "01ec141c-0000-12fc-0000-000000000000", "dictionary_propertyset");
        MRef business_rule_propertyset = new MRef(Stereotype.MQNAME, "01ec141c-0000-1301-0000-000000000000", "business_rule_propertyset");
        MRef requirement_propertyset = new MRef(Stereotype.MQNAME, "01ec141c-0000-12f7-0000-000000000000", "requirement_propertyset");
        MRef goal_propertyset = new MRef(Stereotype.MQNAME, "01ec141c-0000-12f2-0000-000000000000", "goal_propertyset");
        MRef risk_propertyset = new MRef(Stereotype.MQNAME, "679a9417-8f06-4255-a409-1e1f7136e418", "risk_propertyset");
        MRef test_propertyset = new MRef(Stereotype.MQNAME, "859f8b76-5acc-4a9c-a5eb-973467388b13", "test_propertyset");
        
        // use a map by name instead of by ref because stereotypes are reidentified
        // on transmutation.
        Map<String, String> ret = new HashMap<>();
        ret.put(dictionary_propertyset.name, "Analyst.Dictionary");
        ret.put(business_rule_propertyset.name, "Analyst.BusinessRuleContainer"); 
        ret.put(requirement_propertyset.name, "Analyst.RequirementContainer");
        ret.put(goal_propertyset.name, "Analyst.GoalContainer");
        ret.put(risk_propertyset.name, "Analyst.RiskContainer");
        ret.put(test_propertyset.name, "Analyst.TestContainer");
        return ret;
    }

    /**
     * Prepare the metamodel for metaclasses renaming.
     * 
     * @param metamodel the MOF metamodel.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on failure
     */
    @objid ("3c8c0f98-cdf8-4a81-82c0-9aa6eff96907")
    private void prepareMetaclassesRenaming(MofMetamodel metamodel) throws MofMigrationException {
        this.mcRenamer = new MetaclassRenamer();
        
        Properties properties = new Properties();
        
        try (InputStream inputStream = getClass().getResourceAsStream("/migration/renamings_35.properties")) {
            assert(inputStream != null);
        
            properties.load(inputStream);
        
            for (Entry<Object, Object> entry : properties.entrySet()) {
                String oldQualifiedName = (String) entry.getKey();
                String newQualifiedName = (String) entry.getValue();
        
                // Decode 3.5 qualified name
                MClassRef oldMcRef = decodeQualifiedName(oldQualifiedName);
        
                // Look for the 3.6 metaclass
                MofSmClass newCls = (MofSmClass) metamodel.getMClass(newQualifiedName);
                if (newCls != null) {
                    MofMetamodelFragment oldMmFragment = metamodel.getOrCreateFragment(oldMcRef.getFragmentName());
        
                    MofSmClass oldCls = (MofSmClass) metamodel.getMClass(oldMcRef.getQualifiedName());
                    if (oldCls==null) {
                        // Create a renamed copy of the metaclass
                        oldCls = metamodel.addCopy(newCls, oldMcRef.getClassName(), oldMmFragment);
                    }
                    this.mcRenamer.addClassRenaming(oldCls, newCls);
                } else {
                    // Buggy file
                    throw new java.util.InputMismatchException(String.format(
                            "Invalid '%s' file: new '%s' metaclass not found for '%s'.", 
                            "/migration/renamings_35.properties", 
                            newQualifiedName,
                            oldQualifiedName));
                }
            }
        } catch (IOException e) {
            throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    /**
     * Many dependencies were moved from ModelElement to UmlModelElement.
     * Copy back these ones.
     * 
     * @param metamodel the metamodel
     * @param b the MOf metamodel builder
     */
    @objid ("19cccd85-c4d3-4f55-9259-cf33203c6fdc")
    private void prepareMetamodelForUmlModelElement(MofMetamodel metamodel, MofBuilder b) {
        SmClass modelElCls = metamodel.getMClass(ModelElement.MQNAME);
        
        for (SmDependency dep : metamodel.getMClass(UmlModelElement.MQNAME).getSelfDepDef()) {
            b.createDepCopy(dep, modelElCls).build();
        }
    }

    @objid ("f291f57f-2b1e-47aa-ac75-2f3a53ccd416")
    private void transmuteRenamedClasses(IModelioProgress monitor, IMofSession mofSession) {
        this.mcRenamer.transmuteRenamedClasses(monitor, mofSession);
    }

    @objid ("2fedbcf6-feb1-4c17-aee7-a004a7ea3070")
    @Override
    public void completeFinalMetamodelDescriptor(MetamodelDescriptor desc, IMigrationReporter reporter) throws MofMigrationException {
        if (! desc.getFragments().containsKey("Analyst")) {
            try (InputStream is= getClass().getResourceAsStream("/migration/metamodel_analyst_2.xml")) {
                MetamodelDescriptor anadesc = MetamodelDescriptorReader.readFrom(is, "/migration/metamodel_analyst_2.xml");
                MetamodelFragmentDescriptor analystFd = anadesc.getFragments().get("Analyst");
                
                reporter.getLogger().format(" Adding '%s' V%s to the final metamodel descriptor.", analystFd.getName(), analystFd.getVersion());
                
                desc.addFragment(analystFd);
            } catch (IOException e) {
                throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
            }
        }
    }

}
