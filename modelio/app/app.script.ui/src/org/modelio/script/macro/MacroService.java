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
package org.modelio.script.macro;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.core.ModelioEnv;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.script.macro.IMacroService.Scope;
import org.modelio.script.macro.catalog.Catalog;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.script.plugin.Script;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

@objid ("5c36035b-5a91-4d8a-8bf6-f08dd97ed7ae")
@Creatable
public class MacroService implements IMacroService {
    @objid ("cb391d72-178c-4d89-9613-88d1605a3a03")
    private static final String MACROS_SUBDIR = "macros";

    @objid ("95d2dce6-6676-4535-a8cc-abe8a22e6ab4")
    Catalog modelioCatalog;

    @objid ("597ea110-2069-4763-bef5-8071e23ed541")
    Catalog workspaceCatalog;

    @objid ("5ee7779d-7cd2-4edb-ba7d-a33a49d26b6c")
    Catalog projectCatalog;

    @objid ("b5b3d299-7dee-4322-a992-365e704f9e9a")
    @PostConstruct
    void initialize(ModelioEnv env, IProjectService projectService) {
        Path modelioMacroCatalogPath = env.getMacroCatalogPath();
        File modelioMacroCatalog = modelioMacroCatalogPath.toFile();
        if (!modelioMacroCatalog.exists()) {
            createModelioMacroCatalogFile(modelioMacroCatalogPath);
        } else {
            if (modelioMacroCatalog.isDirectory() && modelioMacroCatalog.list().length == 0) {
                createModelioMacroCatalogFile(modelioMacroCatalogPath);
            }
        }
        this.modelioCatalog = new Catalog("Modelio", env.getMacroCatalogPath(), true, null); // The modelio catalog is readonly
        this.workspaceCatalog = new Catalog("Workspace", projectService.getWorkspace().resolve(MacroService.MACROS_SUBDIR), false, null);
        
    }

    @objid ("33adeb7e-96fa-46dd-ab8d-2e4fb15ca60b")
    @Inject
    @Optional
    void onProjectOpened(@EventTopic (ModelioEventTopics.PROJECT_OPENED) final GProject project) {
        if (project != null) {
            MMetamodel metamodel = project.getSession().getMetamodel();
            this.projectCatalog = new Catalog(
                    project.getName(),
                    project.getProjectFileStructure().getProjectDataConfigPath().resolve(MacroService.MACROS_SUBDIR),
                    false, metamodel);
            this.workspaceCatalog.setMetamodel(metamodel);
        }
        
    }

    @objid ("c1325c8e-a2f4-4248-a5f5-b9eac5a721af")
    @Inject
    @Optional
    void onProjectClosed(@SuppressWarnings ("unused")
    @EventTopic (ModelioEventTopics.PROJECT_CLOSED) final GProject project) {
        this.projectCatalog = null;
        this.workspaceCatalog.setMetamodel(null);
        
    }

    @objid ("8525c402-ad5e-492d-9850-f5db8331cd71")
    @Override
    public List<Macro> getMacros() {
        List<Macro> macros = new ArrayList<>();
        macros.addAll(this.modelioCatalog.getMacros());
        macros.addAll(this.workspaceCatalog.getMacros());
        if (this.projectCatalog != null) {
            macros.addAll(this.projectCatalog.getMacros());
        }
        return macros;
    }

    @objid ("b0230437-43c3-4040-a742-71ac9bd9f146")
    @Override
    public List<Macro> getMacros(Collection<MObject> elements) {
        List<Macro> macros = new ArrayList<>();
        for (Macro m : getMacros()) {
            if (m.isRunnableOn(elements)) {
                macros.add(m);
            }
        }
        return macros;
    }

    @objid ("1c2712ca-0514-454e-a71f-0dacc8e19e2c")
    @Override
    public List<Macro> getMacros(Scope scope) {
        switch (scope) {
        case MODELIO:
            return this.modelioCatalog.getMacros();
        case WORSPACE:
            return this.workspaceCatalog.getMacros();
        case PROJECT:
            return this.projectCatalog != null ? this.projectCatalog.getMacros() : new ArrayList<>();
        default:
            return Collections.emptyList();
        }
        
    }

    @objid ("fa9314f7-0569-41c6-91c4-dd6337f002c5")
    @Override
    public void addMacro(Macro macro, Scope scope) {
        switch (scope) {
        case MODELIO:
            this.modelioCatalog.addMacro(macro);
            break;
        case WORSPACE:
            this.workspaceCatalog.addMacro(macro);
            break;
        case PROJECT:
            if (this.projectCatalog != null) {
                this.projectCatalog.addMacro(macro);
            }
            break;
        default:
            break;
        }
        
    }

    @objid ("5b759f97-caab-4012-91eb-fcab398f9635")
    @Override
    public void removeMacro(Macro macro, Scope scope) {
        switch (scope) {
        case MODELIO:
            this.modelioCatalog.removeMacro(macro);
            break;
        case WORSPACE:
            this.workspaceCatalog.removeMacro(macro);
            break;
        case PROJECT:
            if (this.projectCatalog != null) {
                this.projectCatalog.removeMacro(macro);
            }
            break;
        default:
            break;
        }
        
    }

    @objid ("4adc2230-d3d1-45a6-9ec8-8ca8de4b069a")
    @Override
    public Catalog getCatalog(Scope scope) {
        switch (scope) {
        case MODELIO:
            return this.modelioCatalog;
        case WORSPACE:
            return this.workspaceCatalog;
        case PROJECT:
            return this.projectCatalog;
        default:
            return null;
        }
        
    }

    /**
     * Create modelio macro catalog file by extracting the macros embedded in the plugin archive.
     * @param macrosCatalogPath the Modelio macros catalog directory
     */
    @objid ("461e569c-ff21-41af-b990-4dc0e7827a73")
    private void createModelioMacroCatalogFile(Path macrosCatalogPath) {
        String sourceMacrosFileString = getFilePathOf("/res/macros");
        File scriptCatalogFile = new File(sourceMacrosFileString);
        
        try {
            FileUtils.copyDirectoryTo(scriptCatalogFile.toPath(), macrosCatalogPath);
        } catch (IOException e) {
            Script.LOG.error(e);
        }
        File catalogFile = new File(macrosCatalogPath.resolve("scripts.catalog").toString());
        catalogFile.renameTo(new File(macrosCatalogPath.resolve(".catalog").toString()));
        
    }

    @objid ("b1ee3aed-56a0-4a1b-9ac6-322ddcf66b17")
    private String getFilePathOf(String fileName) {
        String path = "";
        Bundle bundle = Platform.getBundle(Script.PLUGIN_ID);
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/" + fileName; // To avoid the space in the bundle path
        URL url = null;
        try {
            url = new URL(s);
            path = FileLocator.toFileURL(url).getPath();
        } catch (IOException e) {
            Script.LOG.error("Cannot extract %s from plugin archive: %s", s, FileUtils.getLocalizedMessage(e));
            Script.LOG.error(e);
        }
        return path;
    }

}
