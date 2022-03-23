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
package org.modelio.gproject.module.catalog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.module.JaxbModelPersistence;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Required;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments.Jxbv2MetamodelFragment;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles.Jxbv2Style;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2MultiPathes;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2MultiPathes.Jxbv2PathEntry;
import org.modelio.gproject.module.IMetamodelFragmentHandle;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.MetamodelFragmentHandle;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.model.spi.IGMetamodelExtension;

/**
 * Extract from the module.xml to the given target path:<ul>
 * <li> the model RAMC in staticModel.ramc
 * <li> dynamicModel.xml
 * <li> moduleInfos.xml
 * </ul>
 * The files are created only when missing because these operations are expensive.
 * @author cma
 * @since 3.8
 */
@objid ("e6357ed8-8b64-49d9-a3af-dd4312f378e1")
public class ModuleXmlExtractor {
    @objid ("aa1bdd94-d9be-43a4-b3f6-0ed61248096d")
    private final Collection<IGMetamodelExtension> metamodelExtensions;

    /**
     * The path of the module.xml file
     */
    @objid ("721ec581-3926-4741-88b0-3588b52dbd8c")
    private final Path moduleXmlPath;

    @objid ("1d5721d1-a48e-4adf-8bd2-e211c281eec2")
    private final Path targetPath;

    /**
     * @param datapath the module path : the directory containing module.xml
     * @param targetPath the path where to extract the files
     * @param metamodelFragments the metamodel fragments
     */
    @objid ("839ddfdb-fe54-4c46-b1d9-0e97ff465367")
    public  ModuleXmlExtractor(Path moduleXmlPath, Path targetPath, Collection<IGMetamodelExtension> metamodelFragments) {
        this.moduleXmlPath = moduleXmlPath;
        this.targetPath = targetPath;
        this.metamodelExtensions = metamodelFragments;
        
    }

    @objid ("89d27df9-7e59-4afd-bcb5-be273838a107")
    private void extractStaticModel(Path moduleStaticModelPath, Jxbv2Module loadedModule, IModelioProgress monitor) throws IOException {
        try {
            new RamcBuilder(this.metamodelExtensions, loadedModule).createRamc(moduleStaticModelPath, monitor);
        } catch (IOException e) {
            throw new IOException(
                    CoreProject.I18N.getMessage(
                            "ModuleCacheManager.FailedExtractStaticModel",
                            moduleStaticModelPath.getFileName(), 
                            FileUtils.getLocalizedMessage(e)),
                    e);
        }
        
    }

    @objid ("fe467c75-d4a3-41dc-b368-23b4b56bd1c5")
    private void extractModuleInfos(Jxbv2Module loadedModule, Path infosPath) throws IOException {
        Jxbv2Module infosModel = new Jxbv2Module();
        
        // Preserved values
        infosModel.setAuthor(loadedModule.getAuthor());
        infosModel.setBinaryversion(loadedModule.getBinaryversion());
        infosModel.setClazz(loadedModule.getClazz());
        infosModel.setDependencies(loadedModule.getDependencies());
        infosModel.setClassPath(loadedModule.getClassPath());
        infosModel.setMetamodelFragments(loadedModule.getMetamodelFragments());
        
        infosModel.setResources(new Jxbv2Resources());
        // Keep resource files
        final Jxbv2Resources moduleResources = loadedModule.getResources();
        if (moduleResources != null) {
            final Jxbv2Resources infoModelResources = infosModel.getResources();
            infoModelResources.setDocFiles(moduleResources.getDocFiles());
            infoModelResources.setDocTemplates(moduleResources.getDocTemplates());
            infoModelResources.setStyles(moduleResources.getStyles());
            infoModelResources.setMacros(moduleResources.getMacros());
            infoModelResources.setPatterns(moduleResources.getPatterns());
        }
        
        infosModel.setId(loadedModule.getId());
        infosModel.setImage(loadedModule.getImage());
        infosModel.setSchemaLevel(loadedModule.getSchemaLevel());
        infosModel.setUid(loadedModule.getUid());
        infosModel.setVersion(loadedModule.getVersion());
        
        // Cleaned values
        infosModel.setParameters(null);
        infosModel.setPropertyTypes(null);
        infosModel.setProfiles(null);
        
        // Save simplified xml file
        JaxbModelPersistence.saveJaxbModel(infosModel, infosPath);
        
    }

    /**
     * This method ensures that the computed files (ramc, xml ) are properly installed in the entry.
     * <p>
     * The <code>normalizeEntryContents</code> method is designed to parse the module.xml file and to create the module RAMC file only when required because
     * these operations are expensive.
     * @param monitor
     * @throws IOException
     */
    @objid ("60c0a2a3-e0d9-4d57-9995-fdcbf5bfec48")
    public void extractModuleXmlContent(IModelioProgress monitor) throws IOException {
        try {
            if (Files.exists(this.moduleXmlPath)) {
                SubProgress mon = SubProgress.convert(monitor, 3);
        
                Jxbv2Module loadedModule = null;
                // Ensure the static model ramc file exists
                Path staticModelPath = getStaticModelRamcPath();
                if (isToRewrite(staticModelPath)) {
                    // Extract static model from module.xml and create a ramc
                    // from it.
                    Log.trace("ModuleXmlExtractor: Rewrite '%s' from '%s'", staticModelPath, this.moduleXmlPath);
        
                    loadedModule = JaxbModelPersistence.loadJaxbModel(this.moduleXmlPath);
                    extractStaticModel(staticModelPath, loadedModule, mon.newChild(1));
                }
                mon.setWorkRemaining(2);
        
                // Ensure the dynamic model xml file exists
                Path dynamicModelPath = getDynamicModelPath();
                if (isToRewrite(dynamicModelPath)) {
                    // Extract dynamic model from module.xml and create an
                    // xml file from it.
                    Log.trace("ModuleXmlExtractor: Rewrite '%s' from '%s'", dynamicModelPath, this.moduleXmlPath);
                    if (loadedModule == null) {
                        loadedModule = JaxbModelPersistence.loadJaxbModel(this.moduleXmlPath);
                    }
                    extractDynamicModel(dynamicModelPath, loadedModule, mon.newChild(1));
                }
                mon.setWorkRemaining(1);
        
                // Ensure the module infos xml file exists
                Path infosPath = getModuleInfosPath();
                if (isToRewrite(infosPath)) {
                    Log.trace("ModuleXmlExtractor: Rewrite '%s' from '%s'", infosPath, this.moduleXmlPath);
        
                    // Create the file
                    if (loadedModule == null) {
                        loadedModule = JaxbModelPersistence.loadJaxbModel(this.moduleXmlPath);
                    }
        
                    // Create the module info file
                    extractModuleInfos(loadedModule, infosPath);
                }
        
            } else {
                throw new IOException(
                        CoreProject.I18N.getMessage("ModuleCacheManager.NoModuleFound", this.moduleXmlPath)); //$NON-NLS-1$
            }
        } catch (IOException e) {
            IOException e2 = new IOException(CoreProject.I18N.getMessage("ModuleCacheManager.ErrorReadingModule",
                    this.moduleXmlPath, FileUtils.getLocalizedMessage(e)));
            e2.initCause(e);
            throw e2;
        }
        
    }

    /**
     * Extract the dynamic part of the module model in another file ?
     * @param moduleDynamicModelPath path of the file to write
     * @param loadedModule the JAXB module model
     * @param monitor a progress monitor
     * @throws IOException in case of failure
     */
    @objid ("d2a6a04b-1e3b-4732-98f9-d5fe779faaa1")
    private void extractDynamicModel(Path moduleDynamicModelPath, Jxbv2Module loadedModule, IModelioProgress monitor) throws IOException {
        Jxbv2Module dynamicModel = new Jxbv2Module();
        
        // Preserved values
        dynamicModel.setAuthor(loadedModule.getAuthor());
        dynamicModel.setBinaryversion(loadedModule.getBinaryversion());
        dynamicModel.setClassPath(loadedModule.getClassPath());
        dynamicModel.setClazz(loadedModule.getClazz());
        dynamicModel.setDependencies(loadedModule.getDependencies());
        dynamicModel.setGui(loadedModule.getGui());
        dynamicModel.setId(loadedModule.getId());
        dynamicModel.setImage(loadedModule.getImage());
        dynamicModel.setParameters(loadedModule.getParameters());
        dynamicModel.setSchemaLevel(loadedModule.getSchemaLevel());
        dynamicModel.setUid(loadedModule.getUid());
        dynamicModel.setVersion(loadedModule.getVersion());
        
        // Ignored values
        dynamicModel.setResources(new Jxbv2Resources());
        dynamicModel.setProfiles(null);
        dynamicModel.setPropertyTypes(null);
        dynamicModel.setMetamodelFragments(null);
        
        // Save simplified xml file
        JaxbModelPersistence.saveJaxbModel(dynamicModel, moduleDynamicModelPath);
        
        monitor.done();
        
    }

    /**
     * Get a module handle for a module cache directory containing "moduleInfos.xml".
     * @param monitor the progress monitor to use for reporting progress to the user.
     * It is the caller's responsibility to call <code>done()</code> on the given monitor.
     * Accepts <code>null</code>, indicating that no progress should be reported and that
     * the operation cannot be cancelled.
     * @return the module handle
     * @throws IOException in case of failure.
     */
    @objid ("aac13b45-ced4-4242-9bb3-f54dea2087ba")
    public IModuleHandle getModuleHandle(IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, 2);
        
        // ensures that the computed files (ramc, xml) are properly installed in the entry
        extractModuleXmlContent(mon.newChild(1));
        
        mon.setWorkRemaining(1);
        return new HandleFactory().getModuleHandle(mon.newChild(1), this.targetPath);
    }

    /**
     * @return the extracted moduleInfos.xml file
     */
    @objid ("5be3f166-18ac-4e62-b2ec-581321fc6f68")
    public Path getModuleInfosPath() {
        return this.targetPath.resolve("moduleInfos.xml");
    }

    /**
     * @return the extracted dynamicModel.xml file path
     */
    @objid ("c1028f07-821d-4c4a-8752-c2d26c91a0f8")
    public Path getDynamicModelPath() {
        return this.targetPath.resolve("dynamicModel.xml");
    }

    /**
     * @return the extracted staticModel.ramc file path
     */
    @objid ("319f8e23-a05f-49bd-98c1-3402e2d41798")
    public Path getStaticModelRamcPath() {
        return this.targetPath.resolve("staticModel.ramc");
    }

    @objid ("fd22e769-0740-4067-9f94-775fd35b6bf5")
    private boolean isToRewrite(Path file) throws IOException {
        if (Files.notExists(file)) {
            return true;
        }
        
        FileTime fileDate = Files.getLastModifiedTime(file);
        FileTime moduleXmlTime = Files.getLastModifiedTime(this.moduleXmlPath);
        return moduleXmlTime.compareTo(fileDate) > 0;
    }

    @objid ("0ef1ef40-cb86-4735-95ef-eef7c1b4cebb")
    private static class HandleFactory {
        /**
         * Get the module handle for a module cache directory containing "moduleInfos.xml"
         * @param monitor the progress monitor to use for reporting progress to the user.
         * It is the caller's responsibility to call <code>done()</code> on the given monitor.
         * Accepts <code>null</code>, indicating that no progress should be reported and that
         * the operation cannot be cancelled.
         * @param moduleCachePath the directory containing moduleInfos.xml .
         * @return the module handle
         * @throws IOException in case of failure.
         */
        @objid ("a777d26f-257e-4193-a318-37218dfda44c")
        public IModuleHandle getModuleHandle(IModelioProgress monitor, Path moduleCachePath) throws IOException {
            SubProgress m = SubProgress.convert(monitor, 80);
            try {
            
                // Load the module info file
                Path infosPath = moduleCachePath.resolve("moduleInfos.xml");
                Jxbv2Module moduleFromInfos = JaxbModelPersistence.loadJaxbModel(infosPath);
                return loadModuleInfos(moduleCachePath, moduleFromInfos, m.newChild(20));
            } catch (IOException e) {
                IOException e2 = new IOException(CoreProject.I18N.getMessage("ModuleCacheManager.ErrorReadingModule",
                        moduleCachePath, FileUtils.getLocalizedMessage(e)));
                e2.initCause(e);
                throw e2;
            }
            
        }

        @objid ("2c95c277-f37d-11e1-a3c7-002564c97630")
        private FileModuleStoreHandle loadModuleInfos(Path moduleCachePath, final Jxbv2Module loadedModule, IModelioProgress monitor) throws IOException {
            String uid = loadedModule.getUid();
            String name = loadedModule.getId();
            String mainClassName = loadedModule.getClazz();
            String moduleVersionString = loadedModule.getVersion();
            
            Version moduleVersion;
            if (moduleVersionString != null) {
                moduleVersion = new Version(moduleVersionString);
            } else {
                // Set a default version...
                moduleVersion = new Version(0, 0, 0);
            }
            
            String binaryVersionString = loadedModule.getBinaryversion();
            Version binaryVersion;
            if (binaryVersionString != null) {
                binaryVersion = new Version(binaryVersionString);
            } else {
                // Set a default version...
                binaryVersion = new Version(0, 0, 0);
            }
            
            List<VersionedItem<?>> dependencies = new ArrayList<>();
            List<VersionedItem<?>> weakDependencies = new ArrayList<>();
            List<Path> jarPaths = new ArrayList<>();
            List<Path> docPaths = new ArrayList<>();
            Map<String, Path> stylePaths = new HashMap<>();
            List<IMetamodelFragmentHandle> metamodelFragments = new ArrayList<>();
            
            if (loadedModule.getDependencies() != null) {
                for (Jxbv2Required dep : loadedModule.getDependencies().getRequired()) {
                    dependencies.add(new VersionedItem<Void>(dep.getName(), new Version(dep.getVersion())));
                }
                for (Jxbv2Optional dep : loadedModule.getDependencies().getOptional()) {
                    weakDependencies.add(new VersionedItem<Void>(dep.getName(), new Version(dep.getVersion())));
                }
            }
            
            final Jxbv2MultiPathes classPath = loadedModule.getClassPath();
            if (classPath != null) {
                for (Jxbv2PathEntry pathEntry : classPath.getPathEntry()) {
                    jarPaths.add(Paths.get(pathEntry.getPath()));
                }
            }
            
            if (loadedModule.getResources() != null && loadedModule.getResources().getDocFiles() != null) {
                for (Jxbv2PathEntry pathEntry : loadedModule.getResources().getDocFiles().getPathEntry()) {
                    docPaths.add(Paths.get(pathEntry.getPath()));
                }
            }
            
            if (loadedModule.getResources() != null && loadedModule.getResources().getStyles() != null) {
                for (Jxbv2Style pathEntry : loadedModule.getResources().getStyles().getStyle()) {
                    stylePaths.put(pathEntry.getId(), Paths.get(pathEntry.getPath()));
                }
            }
            
            if (loadedModule.getMetamodelFragments() != null) {
                for (Jxbv2MetamodelFragment fragEntry : loadedModule.getMetamodelFragments().getMetamodelFragment()) {
                    MetamodelFragmentHandle f = new MetamodelFragmentHandle(fragEntry.getId(), readVersion(fragEntry),
                            fragEntry.getVendor(), fragEntry.getVendorVersion(), fragEntry.getClazz());
            
                    metamodelFragments.add(f);
                }
            
            }
            
            monitor.done();
            return new FileModuleStoreHandle(moduleCachePath, name, moduleVersion, uid, mainClassName, binaryVersion,
                                dependencies, weakDependencies, docPaths, jarPaths, stylePaths, metamodelFragments);
            
        }

        @objid ("90f5289c-e966-471d-b494-68f9e12ba9e6")
        private Version readVersion(Jxbv2MetamodelFragment fragEntry) throws IOException {
            try {
                return new Version(fragEntry.getVersion());
            } catch (NumberFormatException e) {
                // invalid version format
                String msg = CoreProject.I18N.getMessage(
                        "ModuleCacheManager.InvalidMmFragmentVersion", 
                        fragEntry.getId(),
                        fragEntry.getVersion(), 
                        e.getLocalizedMessage());
            
                throw new IOException(msg, e);
            }
            
        }

    }

    @objid ("3a72a3bb-d75d-4c8a-8bb5-ffdd9baa960d")
    static class LazyModelioProgress implements IModelioProgress {
        @objid ("4fc8c649-d2f3-43a0-a7d4-3067386512cb")
        boolean parentStarted;

        @objid ("951296f3-cb2a-440f-bb2c-d7ea6d6de527")
        private IModelioProgress parent;

        @objid ("c91d853f-9ade-4e5c-92d1-ae08a17fd5ea")
        private Data startData;

        @objid ("4f505ecd-1b3e-4dab-85c1-44b7811ec1e7")
        @Override
        public void beginTask(String name, int totalWork) {
            this.startData = new Data();
            this.startData.name = name;
            this.startData.totalWork = totalWork;
            
        }

        @objid ("ee149759-99c5-4f57-8896-d8674c816d08")
        @Override
        public void done() {
            if (this.parentStarted) {
                this.parent.done();
            }
            
        }

        @objid ("0b007c99-4037-4675-86d1-8f47430426d9")
        @Override
        public void internalWorked(double work) {
            startParent();
            this.parent.internalWorked(work);
            
        }

        @objid ("79bbd72a-2d20-4d78-90f5-c32375873daa")
        private void startParent() {
            if (this.parentStarted) {
                return;
            }
            
            this.parentStarted = true;
            this.parent.beginTask(this.startData.name, this.startData.totalWork);
            if (this.startData.subTask != null) {
                this.parent.subTask(this.startData.subTask);
            }
            
        }

        @objid ("780f4051-c3c1-4423-aea1-894523a1d55c")
        @Override
        public boolean isCanceled() {
            return this.parent.isCanceled();
        }

        @objid ("af029175-1e1a-49d7-b483-fd10386a2714")
        @Override
        public void setCanceled(boolean value) {
            this.parent.setCanceled(value);
        }

        @objid ("71cdb595-6e06-4ded-83c5-123a1b37600e")
        @Override
        public void setTaskName(String name) {
            if (this.parentStarted) {
                this.parent.setTaskName(name);
            } else {
                this.startData.name = name;
            }
            
        }

        @objid ("39915d3e-6c0e-46f2-b21b-019afa329453")
        @Override
        public void subTask(String name) {
            if (this.parentStarted) {
                this.parent.subTask(name);
            } else {
                this.startData.subTask = name;
            }
            
        }

        @objid ("c1454c63-df2c-4cba-8667-8809fd50217f")
        @Override
        public void worked(int work) {
            startParent();
            this.parent.worked(work);
            
        }

        @objid ("72bc6466-4452-4b2d-b934-e023b47380a8")
        private static class Data {
            @objid ("093b601f-dfca-49e1-945f-ad5b7b2ebb12")
            String name;

            @objid ("6017216b-02bf-43ca-a93f-104308ce05b8")
            String subTask;

            @objid ("41fe3fa8-520f-49b9-8172-42c3e3b90ec8")
            int totalWork;

        }

    }

}
