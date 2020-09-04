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

package org.modelio.creation.wizard.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.core.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.creation.wizard.plugin.CreationWizard;
import org.osgi.framework.Bundle;

/**
 * Warning: class is to be got from the Eclipse context (to avoid repeated parsing of the extensions)
 */
@objid ("18f03503-562e-4cdf-8c3b-fa427fdb1404")
public class CreationContributorManager {
    @objid ("f1dd0eba-b70b-4579-b898-79fd4a7c19b8")
    private static final String DIAGRAM_CREATION_EXTENSION_ID = "org.modelio.creation.wizard.contributor";

    @objid ("076ef47d-c53f-4282-a8d3-d01c140c99da")
    private final Map<String, ContributorCategory> categories;

    @objid ("2e9f2d01-d04e-4049-bd53-5d29ae3616d9")
    private final Map<ContributorCategory, List<IWizardContributor>> contributors;

    @objid ("ac89e574-41eb-4cd2-944e-78be4bc955a6")
    private final IEclipseContext context;

    @objid ("a4a52b67-4997-4326-884d-6f2de4d64c45")
    public void addContributor(final ContributorCategory category, final IWizardContributor contributor) {
        ContextInjectionFactory.inject(contributor, this.context);
        
        List<IWizardContributor> categoryContributors = this.contributors.get(category);
        if (categoryContributors == null) {
            categoryContributors = new ArrayList<>(3);
            this.contributors.put(category, categoryContributors);
        }
        categoryContributors.add(contributor);
    }

    /**
     * @return all contributor categories.
     */
    @objid ("7f28a281-0c62-4816-9866-58172f003ea1")
    public Set<ContributorCategory> getCategories() {
        return this.contributors.keySet();
    }

    /**
     * @return all contributors by reference. Do not modify the returned map !
     */
    @objid ("17bd7a78-f865-4f38-875d-305aa8c3e40a")
    public Map<ContributorCategory, List<IWizardContributor>> getContributorsMap() {
        return this.contributors;
    }

    @objid ("33ef14ca-e20f-46fb-8bad-88124b540bf7")
    public void removeContributor(final ContributorCategory category, final IWizardContributor contributor) {
        final List<IWizardContributor> categoryContributors = this.contributors.get(category);
        if (categoryContributors != null) {
            categoryContributors.remove(contributor);
            // Remove category from the list if it has no more contributors
            if (categoryContributors.isEmpty()) {
                this.contributors.remove(category);
            }
        }
    }

    /**
     * Constructor to be called by E4 introspection only.
     */
    @objid ("ec4ab600-7ada-425b-b519-8cad34824093")
    @Inject
    CreationContributorManager(final IEclipseContext context) {
        this.context = context;
        this.contributors = new HashMap<>();
        this.categories = new HashMap<>();
        readExtensions();
    }

    @objid ("46830209-292e-47f9-ac66-89e2b563d512")
    private ContributorCategory createCategory(final String type, final String label, final String iconPath, final String bundleId) {
        final Image icon = getCategoryIcon(bundleId, iconPath);
        final ContributorCategory category = new ContributorCategory(type, label, icon);
        this.categories.put(type, category);
        return category;
    }

    @objid ("344b7258-31f3-4e86-90a4-d174d4327700")
    private ContributorCategory getCategory(final String type) {
        return this.categories.get(type);
    }

    @objid ("a75520a7-f467-491f-b61e-eed78fe51b8f")
    private Image getCategoryIcon(final String bundleId, final String iconPathString) {
        Image icon = null;
        if (iconPathString != null) {
            final Bundle bundle = Platform.getBundle(bundleId);
            final IPath iconPath = new Path(iconPathString);
            final URL iconUrl = FileLocator.find(bundle, iconPath, null);
            icon = ImageDescriptor.createFromURL(iconUrl).createImage();
        }
        return icon;
    }

    @objid ("d0131972-d2a5-4f5f-9936-70827da34105")
    private void parseCreationContributor(final IConfigurationElement element) {
        final String categoryType = element.getAttribute("categoryType");
        final String categoryLabel = element.getAttribute("categoryLabel");
        final String iconPath = element.getAttribute("categoryIcon");
        Object contributor;
        
        try {
            contributor = element.createExecutableExtension("contributor");
            ContributorCategory category = getCategory(categoryType);
            if (category == null) {
                final String bundleId = element.getNamespaceIdentifier();
                category = createCategory(categoryType, categoryLabel, iconPath, bundleId);
            }
            if (contributor instanceof IWizardContributor) {
                addContributor(category, (IWizardContributor) contributor);
            }
        } catch (final CoreException e) {
            CreationWizard.LOG.error("Unable to register wizard contribution declared by %s: %s", element.getContributor().getName(), e.getMessage());
            CreationWizard.LOG.debug(e);
        }
    }

    @objid ("1d2282fc-21dc-4672-8e99-fe1283dbb429")
    private final void readExtensions() {
        for (final IConfigurationElement element : new ExtensionPointContributionManager(CreationContributorManager.DIAGRAM_CREATION_EXTENSION_ID).getExtensions("creation")) {
            parseCreationContributor(element);
        }
    }

    /**
     * Get all contributors by type (Diagram or Matrix)
     * 
     * @param typeFilter the type of contributors specified by its Java interface
     * class. Cannot be null.
     * @return the filtered contributors
     */
    @objid ("537de727-817b-4cc4-8f77-ee5a71637f02")
    public <T extends IWizardContributor> List<T> getAllContributors(final java.lang.Class<T> typeFilter) {
        return getAllContributors(typeFilter, null);
    }

    /**
     * Get all contributors by type (Diagram or Matrix)
     * 
     * @param typeFilter the type of contributors specified by its Java interface
     * class. Cannot be null.
     * @param nameFilter optional name for further filtering based on strict string
     * equality (ie no regexp). Can be null in which case the name
     * filter does not apply at all.
     * @return the filtered contributors
     */
    @objid ("62ddceb8-b495-40d8-8380-e883e91b42e2")
    @SuppressWarnings("unchecked")
    public <T extends IWizardContributor> List<T> getAllContributors(final java.lang.Class<T> typeFilter, final String nameFilter) {
        final List<T> allContributors = new ArrayList<>();
        
        for (final IWizardContributor contributor : getAllContributors()) {
            if (typeFilter.isAssignableFrom(contributor.getClass())) {
                if (nameFilter == null || contributor.getClass().getSimpleName().equals(nameFilter)) {
                    allContributors.add((T) contributor);
                }
            }
        }
        return allContributors;
    }

    @objid ("103b50dd-4395-4f0c-a5a3-935e8ef5aaff")
    public List<IWizardContributor> getAllContributors() {
        final List<IWizardContributor> allContributors = new ArrayList<>();
        for (final List<IWizardContributor> cons : this.contributors.values()) {
            allContributors.addAll(cons);
        }
        return allContributors;
    }

}
