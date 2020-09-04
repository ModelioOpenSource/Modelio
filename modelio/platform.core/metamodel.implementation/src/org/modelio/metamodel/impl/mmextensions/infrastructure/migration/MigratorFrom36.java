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

package org.modelio.metamodel.impl.mmextensions.infrastructure.migration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.model.spi.mm.MetaclassRenamer;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.session.api.blob.BlobInfo;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.blob.IBlobProvider;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.MofBuilder;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodelMerger;
import org.modelio.vcore.smkernel.meta.mof.MofSmAttribute;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Migrator from Modelio 3.6 to 3.7 .
 * <p>
 * @author cma
 * @since 3.7
 */
@objid ("5c1133a8-876c-4b42-9104-7efe6896d5b0")
class MigratorFrom36 implements IMofRepositoryMigrator {
    @objid ("75a69a26-1bc3-4ef6-8339-7fd2655f93ee")
    private static final String renamingsFilePath = "/migration/renamings_infrastructure_2.1.00.properties";

    @objid ("836adac4-d415-4db8-91c7-6c4c9199f6ac")
    private static final String MCLASS_EXTERN_DOCUMENT = "Infrastructure.ExternDocument";

    @objid ("c0cba345-2da6-4011-a780-c628aadf0168")
    private static final String ATT_PATH = "Path";

    @objid ("31b8b8bf-2bc6-4200-9821-d346d536396e")
    private static final String ATT_STORAGE_INFO = "StorageInfo";

    @objid ("c7ba6bdb-5d5a-4bf9-b285-6b2619eda65b")
    private MetaclassRenamer mcRenamer;

    @objid ("f734d0e3-cb53-4f48-a6da-fced77a77031")
    private Map<String, MofSmClass> mcToDelete = new HashMap<>();

    @objid ("877b64d4-3d71-41c2-b754-e857797f4533")
    private final MetamodelVersionDescriptor sourceMetamodel;

    @objid ("f9934493-4715-46d9-8616-9193f72c3450")
    private final MetamodelVersionDescriptor targetMetamodel;

    @objid ("6acc15c0-ee50-4680-8de8-c4ea8eacab46")
    public MigratorFrom36(MetamodelVersionDescriptor sourceMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        this.sourceMetamodel = sourceMetamodel;
        this.targetMetamodel = targetMetamodel;
    }

    @objid ("835af518-d0f1-488d-8ee5-b27fa1c26aa9")
    @Override
    public MetamodelChangeDescriptor getMetamodelChanges() {
        return new MetamodelChangeDescriptor();
    }

    @objid ("1627b277-a6d3-4432-a73e-c7bd73a6bdc5")
    @Override
    public MetamodelVersionDescriptor getSourceMetamodel() {
        return this.sourceMetamodel;
    }

    @objid ("278baee8-10a4-4369-957f-dbfeaf79190c")
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
    @objid ("201ba4c2-362f-4470-bf01-c808f6bad8c5")
    @Override
    public void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException {
        // Load the source metamodel
        new MofMetamodelMerger(metamodel)
        .setTemporary(true)
        .merge(InfrastructureMmMigrationProvider.loadMetamodel(getSourceMetamodel()));
        
        MofSmClass externDocMc = (MofSmClass) metamodel.getMClass(MCLASS_EXTERN_DOCUMENT);
        externDocMc.addAttribute(new MofSmAttribute(externDocMc, ATT_STORAGE_INFO));
        
        // Recreate temporarly new dependencies on obsolete metaclasses so that transmutation don't loose data
        SmClass externDocTypeMc = metamodel.getMClass("Infrastructure.ExternDocumentType");
        try (MofBuilder b = metamodel.builder().setTemporary(true);) {
            MofSmClass resourceTypeMC = (MofSmClass) metamodel.getMClass("Infrastructure.ResourceType");
            
            // this copies old dependency to new metaclass
            b.createDepCopy(externDocTypeMc.findDependencyDef("TypedDoc"), resourceTypeMC).build();
            
        }
        
        assert(metamodel.getMClass(MCLASS_EXTERN_DOCUMENT) != null);
        assert(metamodel.getMClass("Infrastructure.ModelElement").getDependency("Document") != null);
        
        
        // Process metaclasses renamings
        // Read renamed classes
        prepareMetaclassesRenaming(metamodel);
    }

    @objid ("f76b2fc2-ef9d-4cb5-82e4-b9a12405fc0d")
    @Override
    public void run(IModelioProgress monitor, IMofSession mofSession) throws MofMigrationException {
        SubProgress mon = SubProgress.convert(monitor, 10);
        
        try {
            deleteObsoleteObjects(mon.newChild(1), mofSession);
            migrateRichNotes(mon.newChild(1), mofSession);
            transmuteRenamedClasses(mon.newChild(7), mofSession);
            renameDeps(mon.newChild(1), mofSession);
            fixStereotypesBaseClass(mon.newChild(1), mofSession);
        } catch (MetaclassNotFoundException e) {
            throw new MofMigrationException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("9c7a53a9-8f2e-4dfe-b19e-1bbc9d0ed99b")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[from "+getSourceMetamodel()+" to "+getTargetMetamodel()+"]";
    }

    @objid ("81751efb-1024-436a-bd01-52a2c2ffdbec")
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
     * Fix {@link Stereotype} and {@link MetaclassReference} base classes so they use the new metaclass names.
     * <p>
     * Use {@link #mcToTransmute} to rename old qualified names to new ones.
     * <p>
     * Requires the elements having been transmuted to the new metaclasses.
     * @param reporter the logger
     * 
     * @param monitor a progress monitor
     * @param mofsession the session
     * @throws org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException on buggy code
     */
    @objid ("ea871433-4fc7-485e-96fb-26dddcc4d519")
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

    @objid ("cca4c6ed-d593-4978-8e30-99e3806534e6")
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

    /**
     * Prepare the metamodel for metaclasses renaming.
     * 
     * @param metamodel the MOF metamodel.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on failure
     */
    @objid ("e4980c3d-b971-46da-a2b9-a47680383fff")
    private void prepareMetaclassesRenaming(MofMetamodel metamodel) throws MofMigrationException {
        this.mcRenamer = new MetaclassRenamer();
        
        Properties properties = new Properties();
        
        
        try (InputStream inputStream = getClass().getResourceAsStream(renamingsFilePath)) {
            assert(inputStream != null);
        
            properties.load(inputStream);
        
            for (Entry<Object, Object> entry : properties.entrySet()) {
                String oldQualifiedName = (String) entry.getKey();
                String newQualifiedName = (String) entry.getValue();
        
                // Decode 3.6 qualified name
                MClassRef oldMcRef = MClassRef.fromQualifiedName(oldQualifiedName);
        
                // Look for the 3.7 metaclass
                MofSmClass newCls = (MofSmClass) metamodel.getMClass(newQualifiedName);
                if (newCls == null) {
                    // Buggy file
                    throw new java.util.InputMismatchException(String.format(
                            "Invalid '%s' file: new '%s' metaclass not found for '%s'.", 
                            renamingsFilePath, 
                            newQualifiedName,
                            oldQualifiedName));
                }
                 
                //MofMetamodelFragment oldMmFragment = metamodel.getOrCreateFragment(oldMcRef.getFragmentName());
        
                MofSmClass oldCls = (MofSmClass) metamodel.getMClass(oldMcRef.getQualifiedName());
                if (oldCls==null) {
                    //// Create a renamed copy of the metaclass
                    //oldCls = metamodel.addCopy(newCls, oldMcRef.getClassName(), oldMmFragment);
                    throw new java.util.InputMismatchException(String.format(
                            "Invalid '%s' file: old '%s' metaclass not found for '%s'.", 
                            renamingsFilePath, 
                            oldQualifiedName,
                            newQualifiedName));
                }
                this.mcRenamer.addClassRenaming(oldCls, newCls);
        
            }
        } catch (IOException e) {
            throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("a998a0ff-c061-4607-9197-cbe580241b63")
    private void transmuteRenamedClasses(IModelioProgress monitor, IMofSession mofSession) {
        this.mcRenamer.transmuteRenamedClasses(monitor, mofSession);
    }

    /**
     * This method must be run before objects are transmutted : the blob Id contains the object identifier.
     * @param monitor
     * @param mofSession
     * @throws MetaclassNotFoundException
     * @throws MofMigrationException
     */
    @objid ("5e3ff6cb-57cc-4154-b137-5a1a36ef0b29")
    private void migrateRichNotes(SubProgress monitor, IMofSession mofSession) throws MetaclassNotFoundException, MofMigrationException {
        SmClass mc = mofSession.getMetaclass(MCLASS_EXTERN_DOCUMENT);
        MAttribute att = mc.getAttribute(ATT_PATH);
        MAttribute newatt = mc.getAttribute(ATT_STORAGE_INFO);
        IRepository repo = mofSession.getTargetRepository();
        byte[] buf = new byte[4096];
        
        for (MofSmObjectImpl obj : mofSession.findByClass(mc, true)) {
            if (obj.isModifiable() && obj.isValid()) {
                String oldPath = (String) obj.mGet(att);
                if (oldPath != null && ! oldPath.isEmpty()) {
                    try {
                        String oldBlobId = "richnote."+obj.getUuid();
                        IBlobInfo oldBlobInfo = repo.readBlobInfo(oldBlobId);
                        if (oldBlobInfo != null) {
                            try(InputStream is = repo.readBlob(oldBlobId);
                                    OutputStream os = repo.writeBlob(new BlobInfo(new MRef(obj),IResourceHandle.BLOB_LOCAL_KEY));) {
                                int read = 0;
                                while ((read = is.read(buf)) > 0) {
                                    os.write(buf, 0 , read);
                                }
                            }
                            repo.removeBlob(oldBlobId);
                        }
                    } catch (IOException e) {
                        throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
                    }
                    
                    obj.mSet(newatt, "blob:"+new File(oldPath).getName());
                }
            }
        }
    }

    @objid ("61b9f5f9-adf4-4c14-a7e7-9a808a660336")
    private void renameDeps(SubProgress monitor, IMofSession mofSession) throws MofMigrationException {
        renameDep(mofSession, ModelElement.MQNAME, "Document", "Attached");
        renameDep(mofSession, Stereotype.MQNAME, "DefinedExternDocumentType", "DefinedResourceType");
        renameDep(mofSession, MetaclassReference.MQNAME, "DefinedExternDocumentType", "DefinedResourceType");
    }

    @objid ("77b2c1fa-b4a7-47f3-bc11-57f02b8b9d07")
    private void renameDep(IMofSession mofSession, String mcName, String oldName, String newName) throws MofMigrationException {
        SmClass mc;
        try {
            mc = mofSession.getMetaclass(mcName);
        
            MDependency oldDep = Objects.requireNonNull(mc.getDependency(oldName), oldName);
            MDependency newDep = Objects.requireNonNull(mc.getDependency(newName), newName);
        
            for (MofSmObjectImpl obj : mofSession.findByClass(mc, true)) {
                if (obj.isModifiable() && obj.isValid()) {
                    List<MObject> oldDepContent = obj.mGet(oldDep);
                    List<MObject> newDepContent = obj.mGet(newDep);
                    List<MObject> oldBase = new ArrayList<>(oldDepContent);
                    for (MObject target : oldBase) {
                        oldDepContent.remove(target);
                        newDepContent.add(target);
                    }
                }
            }
        } catch (MetaclassNotFoundException | RuntimeException e) {
            throw new MofMigrationException(String.format("Moving %s.%s to %s.%s : %s", mcName, oldName, mcName, newName, e.getMessage()), e);
        }
    }

    @objid ("a65f5d21-32a7-482b-9569-ee1e43b17773")
    private static class MigrationBlobProvider implements IBlobProvider {
        @objid ("53a36dcc-8bc8-4ebb-8c2b-6888e949c679")
        @Override
        public Collection<String> getRelatedBlobs(MObject obj) {
            String oldBlobId = "richnote."+obj.getUuid();
            String newBlobId = BlobInfo.computeKey(obj, IResourceHandle.BLOB_LOCAL_KEY);
            return Arrays.asList(oldBlobId, newBlobId);
        }

        @objid ("2eaa9774-ddc6-4351-be7f-6227d27d37c1")
        @Override
        public void objectCopied(MObject from, IRepository fromRepo, MObject to, IRepository toRepo) {
            // ignore
        }

        @objid ("3168a197-ebab-4a07-8da5-a95f3786f1c7")
        @Override
        public void objectsMoved(Collection<? extends MObject> objs, IRepository fromRepo, IRepository destRepo) {
            // ignore
        }

    }

}
