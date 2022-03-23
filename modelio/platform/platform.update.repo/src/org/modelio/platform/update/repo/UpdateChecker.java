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
package org.modelio.platform.update.repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.modelio.platform.preferences.AppSharedPreferencesKeys;
import org.modelio.platform.preferences.plugin.Preferences;
import org.modelio.platform.update.repo.plugin.UpdateRepo;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.net.UriPathAccess;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;

/**
 * Handle the Modelio update site to compute updates for Modules, Ramcs and Templates.
 * <p>
 * For each one of these items, a list of {@link UpdateDescriptor} called the 'update plan' should be defined in the update site.
 * </p>
 * <p>
 * Relies on the {@link AppSharedPreferencesKeys#UPDATESITE_PREFKEY} preference to find the update's site address.
 * </p>
 * 
 * @see UpdateChecker#checkUpdate() for more infos.
 */
@objid ("3e15f42c-baa4-4599-8eee-470437f7b482")
public class UpdateChecker {
    @objid ("1e64c20a-ca88-4d99-8ea9-3afc98607216")
    private static final String MODULE_UPDATE_FILE = "/modules/modules.update";

    @objid ("74779113-52a1-4f7f-bed0-15e6d1fe21ac")
    private static final String RAMCS_UPDATE_FILE = "/ramcs/ramcs.update";

    @objid ("d8820af5-23bb-4e87-ba66-8351782ccc5d")
    private static final String TEMPLATES_UPDATE_FILE = "/templates/templates.update";

    @objid ("826bb50d-f886-4606-b32f-c3052a243a79")
    private List<UpdateDescriptor> moduleUpdatePlan;

    @objid ("93c8a476-9bc2-41f5-a927-42bab2bc9af4")
    private List<UpdateDescriptor> ramcUpdatePlan;

    @objid ("6f809bec-4e6d-446b-a3fd-62ec0dc7be04")
    private List<UpdateDescriptor> templateUpdatePlan;

    /**
     * Computes the list of proposed updates available for the given module map.
     * @param referenceModules a map of module names & versions serving as a reference for the update.
     * @param strict if <code>true</code> the proposed updates will be strictly limited to the modules declared in the reference modules (no new modules proposed)
     * @return a list of possible updates, i.e. all module newest versions that do not already belong to the referenceModules map.
     * @throws IOException if the update site couldn't be contacted.
     */
    @objid ("6085c73f-4297-44db-adab-e762a5ce2e8d")
    public List<UpdateDescriptor> getModuleUpdates(Map<String, Version> referenceModules, boolean strict) throws IOException {
        if (this.moduleUpdatePlan == null) {
            // Read the update plan from the update site
            this.moduleUpdatePlan = readUpdatePlan(UpdateChecker.getModuleUpdateFile());
        }
        return computeUpdates(this.moduleUpdatePlan, referenceModules, strict);
    }

    /**
     * Computes the list of proposed updates available for the given ramc map.
     * @param referenceRamcs a map of ramc names & versions serving as a reference for the update.
     * @param strict if <code>true</code> the proposed updates will be strictly limited to the ramcs declared in the reference ramcs (no new ramcs proposed)
     * @return a list of possible updates, i.e. all ramc newest versions that do not already belong to the referenceRamcs map.
     * @throws IOException if the update site couldn't be contacted.
     */
    @objid ("0205b7cd-413b-42e3-a8cc-bf23a25be1ee")
    public List<UpdateDescriptor> getRamcsUpdates(Map<String, Version> referenceRamcs, boolean strict) throws IOException {
        if (this.ramcUpdatePlan == null) {
            // Read the update plan from the update site
            this.ramcUpdatePlan = readUpdatePlan(UpdateChecker.getRamcUpdateFile());
        }
        return computeUpdates(this.ramcUpdatePlan, referenceRamcs, strict);
    }

    /**
     * Computes the list of proposed updates available for the given template map.
     * @param referenceTemplates a map of template names & versions serving as a reference for the update.
     * @param strict if <code>true</code> the proposed updates will be strictly limited to the templates declared in the reference templates (no new templates proposed)
     * @return a list of possible updates, i.e. all template newest versions that do not already belong to the referenceTemplates map.
     * @throws IOException if the update site couldn't be contacted.
     */
    @objid ("3624603a-199c-4cae-bd8c-beefe24934ac")
    public List<UpdateDescriptor> getTemplateUpdates(Map<String, Version> referenceTemplates, boolean strict) throws IOException {
        if (this.templateUpdatePlan == null) {
            // Read the update plan from the update site
            this.templateUpdatePlan = readUpdatePlan(UpdateChecker.getTemplateUpdateFile());
        }
        return computeUpdates(this.templateUpdatePlan, referenceTemplates, strict);
    }

    @objid ("2189457f-8c26-41ea-a2fe-41b83063ffad")
    private List<UpdateDescriptor> computeUpdates(List<UpdateDescriptor> updatePlan, Map<String, Version> referenceItems, boolean strict) {
        List<UpdateDescriptor> updates = new ArrayList<>();
        List<String> notFoundItems = new ArrayList<>();
        
        // Here is the resolution algorithm
        for (UpdateDescriptor updatePlanEntry : updatePlan) {
            String name = updatePlanEntry.getId();
            Version currentVersion = referenceItems.get(name);
            if ((currentVersion != null)) {
                // Item found, is the current version older ?
                if (currentVersion.isOlderThan(new Version(updatePlanEntry.getNewVersion()))) {
                    updates.add(new UpdateDescriptor(name, updatePlanEntry.getLabel(), currentVersion.toString(), updatePlanEntry.getNewVersion(), updatePlanEntry.getDocumentationLink(), updatePlanEntry.getDownloadLink()));
                }
            } else {
                // Item not found in reference items
                if (!strict && !notFoundItems.contains(name)) {
                    notFoundItems.add(name);
                    updates.add(new UpdateDescriptor(name, updatePlanEntry.getLabel(), "", updatePlanEntry.getNewVersion(), updatePlanEntry.getDocumentationLink(), updatePlanEntry.getDownloadLink()));
                }
            }
        }
        return updates;
    }

    /**
     * Get the configured modules update site from the preferences. since Modelio 3.5 this path is defined as: $BASE/$VRC/modules $BASE = path/url from Modelio global preferences $VRC = modelio version
     * @return the modules update URL
     */
    @objid ("692f7365-842d-48e0-954a-8745f925a09c")
    private static String getModuleUpdateFile() {
        String updateSite = UpdateChecker.getUpdateSite();
        return updateSite + UpdateChecker.MODULE_UPDATE_FILE;
    }

    /**
     * Get the configured ramcs update site from the preferences. since Modelio 3.5 this path is defined as: $BASE/$VRC/ramcs $BASE = path/url from Modelio global preferences $VRC = modelio version
     * @return the ramcs update URL
     */
    @objid ("4ec2d007-f355-4205-88cb-bc083a5961f7")
    private static String getRamcUpdateFile() {
        String updateSite = UpdateChecker.getUpdateSite();
        return updateSite + UpdateChecker.RAMCS_UPDATE_FILE;
    }

    /**
     * Get the configured templates update site from the preferences. since Modelio 3.5 this path is defined as: $BASE/$VRC/templates $BASE = path/url from Modelio global preferences $VRC = modelio version
     * @return the templates update URL
     */
    @objid ("dea2a99b-03ad-4324-91e6-4ef534b9f199")
    private static String getTemplateUpdateFile() {
        String updateSite = UpdateChecker.getUpdateSite();
        return updateSite + UpdateChecker.TEMPLATES_UPDATE_FILE;
    }

    @objid ("185a6b92-1322-45b1-b618-d9ed373314c6")
    public static String getUpdateSite() {
        IPreferenceStore appPreferences = Preferences.getPreferences();
        String versionSubdir = String.format("/%s", ModelioVersion.VERSION.toString("V.R"));
        String updateSite = appPreferences.getString(AppSharedPreferencesKeys.UPDATESITE_PREFKEY) + versionSubdir;
        return updateSite;
    }

    /**
     * Checks the update file for the current version of Modelio.
     * @return the list of all updates available in the update file.
     * @throws IOException if the update site couldn't be contacted
     */
    @objid ("c4de2b63-cadc-4450-af4c-3673fa369971")
    private List<UpdateDescriptor> readUpdatePlan(String updateFile) throws IOException {
        List<UpdateDescriptor> updatePlan = new ArrayList<>();
        
        // Read properties file.
        final Properties updateProperties = new Properties();
        
        try (UriPathAccess pathAccess = new UriPathAccess(URIUtil.fromString(updateFile), null)) {
            final Path path = pathAccess.getPath();
            try (BufferedReader in = Files.newBufferedReader(path)) {
                updateProperties.load(in);
            }
        } catch (final URISyntaxException e) {
            throw new IOException(UpdateRepo.I18N.getMessage("UpdateCheckerError.Message", updateFile, e.getLocalizedMessage()), e);
        } catch (final IOException e) {
            throw new IOException(UpdateRepo.I18N.getMessage("UpdateCheckerError.Message", updateFile, FileUtils.getLocalizedMessage(e)), e);
        }
        
        // Parse update properties to build UpdateDescriptor instances where currentVersion is equals to next version
        int cpt = 0;
        String keyPrefix;
        do {
            cpt++;
            keyPrefix = updateProperties.getProperty("last" + cpt);
            if (keyPrefix != null) {
                final String id = keyPrefix.substring(0, keyPrefix.indexOf("."));
                final String currentVersion = keyPrefix.substring(keyPrefix.indexOf(".") + 1);
                final String label = updateProperties.getProperty(keyPrefix + ".label");
                final String nextVersion = updateProperties.getProperty(keyPrefix + ".next");
                final String url = updateProperties.getProperty(keyPrefix + ".url");
                final String downloadLink = updateProperties.getProperty(keyPrefix + ".file");
                updatePlan.add(new UpdateDescriptor(id, label, currentVersion, nextVersion, url, downloadLink));
            }
        } while (keyPrefix != null);
        return updatePlan;
    }

}
