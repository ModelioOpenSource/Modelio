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
package org.modelio.platform.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.modelio.platform.core.metamodel.MetamodelExtensionLoader;
import org.modelio.platform.core.plugin.AppCore;
import org.modelio.platform.preferences.plugin.Preferences;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.version.ModelioVersion;

/**
 * The ModelioEnv singleton holds several configuration values for Modelio.
 * <p>
 * ModelioEnv values deal only on the current user environment and on the Modelio version. For example, no ModelioEnv value depends on the currently opened project or the current workspace.
 * 
 * 
 * @author phv
 */
@objid ("002890a6-8562-103f-87fd-001ec947cd2a")
@Creatable
@Singleton
public class ModelioEnv {
    /**
     * The preference key for the modules catalog path.
     */
    @objid ("cc93fe4d-eaad-43f3-b511-732fe3711631")
    public static final String MODULE_PATH_PREFERENCE = "ModuleCatalog.LocalPath";

    /**
     * Identifier of the "edition" variable in the e4 context.
     */
    @objid ("ed5cea4f-e06d-483b-99bb-81e3fb864f73")
    public static final String EDITION_VARIABLE = "org.modelio.platform.core.edition";

    @objid ("0064d462-856d-103f-87fd-001ec947cd2a")
    private Path runtimeDataPath;

    @objid ("0069c58a-7778-1061-84ef-001ec947cd2a")
    protected Path moduleCatalogPath;

    @objid ("008572d0-cdbe-106a-bf4f-001ec947cd2a")
    protected Path macroCatalogPath;

    /**
     * @since 3.6
     */
    @objid ("bbd21ce7-ec32-4d64-afcc-f0168b0871e4")
    private Collection<IGMetamodelExtension> allMetamodelExtensions;

    @objid ("712d3cb3-2842-4e46-8c2c-0060783c001c")
    private Path ramcCachePath;

    @objid ("36102547-d9f7-444e-a416-7d87dde3c814")
    @Inject
    protected IEclipseContext context;

    @objid ("0001d380-de89-1040-a120-001ec947cd2a")
    @PostConstruct
    private void init() {
        final String versionSubpath = ModelioVersion.VERSION.getMajorVersion() + "." + ModelioVersion.VERSION.getMinorVersion();
        
        // Modelio runtime data path
        this.runtimeDataPath = Paths.get(System.getProperty("user.home"), ".modelio", versionSubpath);
        
        // Get the mda.infra preference node, as the module catalog is managed
        // by this plugin
        final IPersistentPreferenceStore prefs = Preferences.getPreferences();
        
        // initialize the default value
        final Path defaultModuleCatalogPath = this.runtimeDataPath.resolve("modules");
        prefs.setDefault(MODULE_PATH_PREFERENCE, defaultModuleCatalogPath.toString());
        
        // Read Modelio modules catalog path
        final String value = prefs.getString(MODULE_PATH_PREFERENCE);
        this.moduleCatalogPath = Paths.get(value);
        
        // Add a preference change listener to update module catalog path.
        prefs.addPropertyChangeListener(new IPropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent event) {
                if (MODULE_PATH_PREFERENCE.equals(event.getProperty())) {
                    ModelioEnv.this.moduleCatalogPath = Paths.get((String) event.getNewValue());
                }
            }
        });
        
        // Modelio macro catalog
        this.macroCatalogPath = Paths.get(this.runtimeDataPath.toString(), "macros");
        
        // Modelio ramc cache
        this.ramcCachePath = Paths.get(this.runtimeDataPath.toString(), "ramcs");
        
        // Create dirs
        try {
            Files.createDirectories(this.runtimeDataPath);
            Files.createDirectories(this.moduleCatalogPath);
            Files.createDirectories(this.macroCatalogPath);
            Files.createDirectories(this.ramcCachePath);
        } catch (final IOException e) {
            AppCore.LOG.error("Failed to create Modelio runtime directories: " + FileUtils.getLocalizedMessage(e));
            AppCore.LOG.error(e);
        }
        
    }

    /**
     * @return the version of Modelio.
     */
    @objid ("004d2236-de99-1040-a120-001ec947cd2a")
    public Version getVersion() {
        return ModelioVersion.VERSION;
    }

    /**
     * Get the Modelio runtime data path inside user home directory.
     * @return the Modelio runtime data path.
     */
    @objid ("00017610-dde4-1040-a120-001ec947cd2a")
    public Path getRuntimeDataPath() {
        return this.runtimeDataPath;
    }

    /**
     * @return the module catalog path
     */
    @objid ("0061210a-77d6-1061-84ef-001ec947cd2a")
    public Path getModuleCatalogPath() {
        return this.moduleCatalogPath;
    }

    /**
     * @return the macros catalog path.
     */
    @objid ("642eaf11-6c54-42ad-bef5-dad8d776e47c")
    public Path getRamcCachePath() {
        return this.ramcCachePath;
    }

    /**
     * @return the macros catalog path.
     */
    @objid ("fdef1a4e-9cff-4bd0-8892-af0e0033c7fb")
    public Path getMacroCatalogPath() {
        return this.macroCatalogPath;
    }

    /**
     * FIXME : it seems the language variant was planned to be stripped out but it is not done. I don't know whether this is intentional or not.
     * @return Returns the string name of the current locale
     */
    @objid ("14b921c9-a69a-4e38-bb78-d860e415e682")
    public static String getLanguage() {
        final String language = Platform.getNL();
        if (language.contains("_")) {
            /* FIXME :return missing !!?! */
            language.substring(0, language.lastIndexOf("_"));
        }
        return language;
    }

    /**
     * Get all metamodel fragments provided by Modelio plugins.
     * @return all default metamodel fragments.
     * @since 4.0
     */
    @objid ("8cbb6ae6-cac5-46aa-8e7c-aa7417cfa239")
    public Collection<IGMetamodelExtension> getAllMetamodelExtensions() {
        if (this.allMetamodelExtensions == null) {
            this.allMetamodelExtensions = MetamodelExtensionLoader.loadAllMetamodelExtensions();
        }
        return this.allMetamodelExtensions;
    }

    /**
     * Get "active" metamodel fragments provided by Modelio plugins.
     * @return a subset of the metamodel fragments returned by {@link #getAllMetamodelExtensions()}
     * @since 3.6
     */
    @objid ("d8517c42-f084-49c8-8b39-63209e264d8c")
    public Collection<IGMetamodelExtension> getActiveMetamodelExtensions() {
        final Collection<String> activeExtensions = MetamodelExtensionLoader.getActiveMetamodelExtensions();
        return getAllMetamodelExtensions().stream().filter(e -> activeExtensions.contains(e.getMmFragment().getName())).collect(Collectors.toList());
    }

    /**
     * @return the name of the current Modelio edition.
     */
    @objid ("4de359ac-5145-4b5d-8f55-814229195051")
    public String getEdition() {
        return (String) this.context.get(EDITION_VARIABLE);
    }

}
