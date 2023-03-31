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
import java.util.Comparator;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.InfrastructureMetamodel;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.impl.mmextensions.standard.migration.from_35.StandardMm9022Migrator;
import org.modelio.metamodel.impl.mmextensions.standard.migration.from_36.StandardMm200Migrator;
import org.modelio.metamodel.impl.mmextensions.standard.migration.from_37.StandardMm210Migrator;
import org.modelio.metamodel.impl.mmextensions.standard.migration.from_37.StandardMm220Migrator;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.mm.AbstractMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigratorProvider;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.model.spi.mm.NoopMofRepositoryMigrator;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.descriptor.MClassDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorReader;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelFragmentDescriptor;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;

/**
 * Migration provider for the standard metamodel (,Analyst , Infrastructure and impact before 3.6).
 * <p>
 * 
 * @author cma
 * @since 3.6
 */
@objid ("3514fe74-6cdc-4082-9ad3-fe484b7ad0f2")
public class StandardMmMigrationProvider implements IMofRepositoryMigratorProvider {
    @objid ("542dfcbb-9fb3-4c88-b0fc-cae961b6b6fd")
    private static final Version MODELIO36X_MM200 = new Version(2, 0, 00);

    @objid ("f13c2bf2-18a1-4e16-8166-4417e57eab42")
    private static final Version MODELIO37X_MM210 = new Version(2, 1, 00);

    @objid ("b96cd377-028b-4ba2-b510-69c73665431f")
    private static final Version MODELIO37X_MM220 = new Version(2, 2, 00);

    @objid ("441d2909-095e-47e2-9409-d1c63871e793")
    private static final Version MODELIO38X_MM230 = new Version(2, 3, 00);

    @objid ("3abc5669-1bb3-4ccb-8d26-9c84e34809b7")
    private static final Version MODELIO35X_MM9022 = new Version(1, 0, 9022);

    @objid ("7b2b9aed-36dc-4b88-a851-2bce0eb9bbdc")
    private static final Version LASTSTANDARDVERSION = new Version(StandardMetamodel.VERSION);

    @objid ("cf9e4a6f-afa5-49f6-88d3-1a6b3535265c")
    @Override
    public IMofRepositoryMigrator getMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor toMetamodel) {
        Version fromVersion = fromMetamodel.getVersion(StandardMetamodel.NAME);
        
        if (fromVersion == null) {
            // standard metamodel absent, we are not involved
            return null;
        } else if (fromVersion.isNewerOrSameThan(LASTSTANDARDVERSION)) {
            // Same or Future version: no retro migration.
            return null;
        } else if (LASTSTANDARDVERSION.isNewerBuildOf(fromVersion)) {
            // Older but build compatible
            return new NoopMofRepositoryMigrator(fromMetamodel, fromMetamodel
                    .copy()
                    .put(StandardMetamodel.NAME, LASTSTANDARDVERSION));
        } else if (fromVersion.isNewerOrSameThan(MODELIO37X_MM220)) {
            // Migrate BPMN from 3.7 mm 2.2.0 to 3.8 mm 2.2.02
            return new StandardMm220Migrator(fromMetamodel, fromMetamodel
                    .copy()
                    .put(StandardMetamodel.NAME, MODELIO38X_MM230));
        } else if (fromVersion.isNewerOrSameThan(MODELIO37X_MM210)) {
            // Migrate BPMN from 3.7 mm 2.1.0 to 3.7 mm 2.2.0
            return new StandardMm210Migrator(fromMetamodel, fromMetamodel
                    .copy()
                    .put(StandardMetamodel.NAME, MODELIO37X_MM220));
        } else if (fromVersion.isNewerOrSameThan(MODELIO36X_MM200)) {
            // Migrate BPMN to 3.7 mm 2.1.0
            return new StandardMm200Migrator(
                    fromMetamodel,
                    fromMetamodel
                            .copy()
                            .put(StandardMetamodel.NAME, MODELIO37X_MM210));
        } else if (fromMetamodel.getVersion(InfrastructureMetamodel.NAME) != null) {
            // At this point there should not be Infrastructure metamodel, we should be Modelio < 3.6
            // Put Modelio > 3.6 migrations before this block.
            // Put Modelio < 3.6 migrations after this block.
            // assert (false) : fromMetamodel.toString();
            Log.error(new IllegalStateException(String.format("No migrator to migrate from %s v%s to v%s ", StandardMetamodel.NAME, fromVersion, LASTSTANDARDVERSION)));
            return null;
        } else {
        
            if (fromVersion.isNewerOrSameThan(MODELIO35X_MM9022)) {
                // Migrate to 3.6
                return new StandardMm9022Migrator(fromMetamodel, fromMetamodel
                        .copy()
                        .put(StandardMetamodel.NAME, MODELIO36X_MM200)
                        .put(InfrastructureMetamodel.NAME, MODELIO36X_MM200)
                        .put("Analyst", MODELIO36X_MM200));
        
            } else if (fromVersion.isNewerOrSameThan(new Version(1, 0, 9016))) {
                /*
                 * Migrate to 9022 (Modelio 3.2): # BusinessRule, Goal, Requirement, Term, GenericAnalystElement are now CMS nodes
                 */
                return new BasicMigrator(fromMetamodel,
                        fromMetamodel
                                .copy()
                                .put(StandardMetamodel.NAME, MODELIO35X_MM9022)).setMetamodelChanges(
                                        new MetamodelChangeDescriptor()
                                                .newCmsNode(StandardMetamodel.NAME, "BusinessRule")
                                                .newCmsNode(StandardMetamodel.NAME, "Goal")
                                                .newCmsNode(StandardMetamodel.NAME, "Requirement")
                                                .newCmsNode(StandardMetamodel.NAME, "Term")
                                                .newCmsNode(StandardMetamodel.NAME, "GenericAnalystElement"));
            } else {
                // older: Not supported, a migrator is probably missing here
                Log.trace("No migrator to migrate from %s v%s to v%s ", StandardMetamodel.NAME, fromVersion, LASTSTANDARDVERSION);
                return null;
            }
        }
        
    }

    @objid ("46379912-9c7d-458c-b35a-bece4192d919")
    public static MetamodelDescriptor loadMetamodel(MetamodelVersionDescriptor fromMetamodel) throws MofMigrationException {
        Version fromVersion = fromMetamodel.getVersion(StandardMetamodel.NAME);
        
        // The version to load is the same or next version for which we have a descriptor file.
        Version toLoad = Stream.of("1.0.9017", "1.0.9018", "1.0.9019", "1.0.9020", "1.0.9021", "1.0.9022", "1.0.9023", "1.0.9024", "1.0.9025", "1.1.00", "2.0.00")
                .map(s -> new Version(s))
                .sorted(Comparator.reverseOrder())
                .filter(v -> v.isOlderOrSameThan(fromVersion))
                .findFirst()
                .orElseThrow(() -> new MofMigrationException(String.format("No metamodel descriptor for version '%s'", fromVersion)));
        
        String path = "/migration/metamodel_" + toLoad.toString() + ".xml";
        try (InputStream is = StandardMmMigrationProvider.class.getResourceAsStream(path)) {
            return MetamodelDescriptorReader.readFrom(is, path);
        } catch (IOException e) {
            throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
        }
        
    }

    @objid ("09d28a33-fe45-4750-ab4b-e226b68fc98b")
    private static class BasicMigrator extends AbstractMofRepositoryMigrator {
        @objid ("7a174947-d8eb-480d-810f-77283b909351")
        public  BasicMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel) {
            super(fromMetamodel, targetMetamodel);
        }

        @objid ("d36b60f9-fc4d-4d93-878a-07ef1801b3e1")
        @Override
        public void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException {
            super.prepareMetamodel(metamodel);
            
            MetamodelDescriptor srcMmDesc = loadMetamodel(getSourceMetamodel());
            // loadCmsNodeChanges(metamodel, srcMmDesc);
            
            metamodel.merge(srcMmDesc);
            
        }

        /**
         * Scan metamodel to look for CMS node metaclass differences.
         * @param metamodel the prepared metamodel
         * @param srcMmDesc the source metamodel descriptor
         * @deprecated Experimental begin of implementation
         */
        @objid ("56401fa7-3a7c-4cf7-a6d9-6b7c3c968a63")
        @Deprecated
        private void loadCmsNodeChanges(MofMetamodel metamodel, MetamodelDescriptor srcMmDesc) {
            MetamodelChangeDescriptor changes = new MetamodelChangeDescriptor();
            // Map<String, MClassDescriptor> oldClasses = new HashMap<>();
            
            for (MetamodelFragmentDescriptor fd : srcMmDesc.getFragments().values()) {
                for (MClassDescriptor md : fd.getMetaclasses()) {
                    // oldClasses.put(fd.getName()+"."+md.getName(), md);
            
                    SmClass existingMc = metamodel.getMClass(fd.getName() + "." + md.getName());
                    if (existingMc == null) {
                        changes.addClass(fd.getName(), md.getName());
                        if (md.isCmsNode()) {
                            changes.newCmsNode(fd.getName(), md.getName());
                        }
                    }
                }
            
            }
            
        }

    }

}
