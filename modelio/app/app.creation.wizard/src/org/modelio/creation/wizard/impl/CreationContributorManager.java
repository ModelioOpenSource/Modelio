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
package org.modelio.creation.wizard.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.creation.wizard.plugin.CreationWizard;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.osgi.framework.Bundle;

/**
 * Warning: class is to be got from the Eclipse context (to avoid repeated parsing of the extensions)
 */
@objid ("08486180-56ad-4fe1-976f-f51b7ce382e8")
public class CreationContributorManager {
    @objid ("c1077f78-5445-4bfe-ac2b-bf2282320176")
    private static final String DIAGRAM_CREATION_EXTENSIONPOINT_ID = "org.modelio.app.creation.wizard.contributor";

    @objid ("e896e2fb-b635-4b32-be16-d05a48fba8f4")
    private final IEclipseContext context;

    @objid ("7c2c1946-5182-4639-9221-26e995d2b257")
    private final Map<String, ContributorCategory> categories = new HashMap<>();

    @objid ("ddf75f7e-5808-4278-81c4-ba5cfb841532")
    private final Map<String, List<IWizardContributor>> wizards = new HashMap<>();

    @objid ("a0ba5450-492f-4f61-bd08-5c19041e821d")
    public Collection<ContributorCategory> getCategories() {
        return this.categories.values();
    }

    @objid ("e8ab7403-98a5-410d-a2ed-15308b4ee1ae")
    @Inject
     CreationContributorManager(final IEclipseContext context) {
        this.context = context;
        
        for (final IConfigurationElement element : new ExtensionPointContributionManager(CreationContributorManager.DIAGRAM_CREATION_EXTENSIONPOINT_ID).getExtensions("wizard")) {
            parseWizard(element);
        }
        
    }

    @objid ("091eaed9-56ca-4df1-8023-ca98e74d9b35")
    private void parseWizard(final IConfigurationElement element) {
        try {
            Object obj = element.createExecutableExtension("class");
            if (obj instanceof IWizardContributor) {
                final String bundleId = element.getNamespaceIdentifier();
        
                // First, find the category
                String categoryId = element.getAttribute("categoryId");
                ContributorCategory category = this.categories.get(categoryId);
                if (category == null) {
                    String categoryLabel = element.getAttribute("categoryLabel");
                    String categoryIconPath = element.getAttribute("categoryIcon");
                    category = new ContributorCategory(categoryId, categoryLabel, makeImageDescriptor(bundleId, categoryIconPath).createImage());
                    this.categories.put(categoryId, category);
                }
        
                List<IWizardContributor> contribs = this.wizards.get(categoryId);
                if (contribs == null) {
                    contribs = new ArrayList<>();
                    this.wizards.put(categoryId, contribs);
                }
        
                // Build the contribution
                IWizardContributor contrib = (IWizardContributor) obj;
                ContextInjectionFactory.inject(obj, this.context);
        
                contrib.setLabel(element.getAttribute("label"));
                contrib.setDetails(element.getAttribute("details"));
                contrib.setInformation(element.getAttribute("information"));
                contrib.setHelpUrl(element.getAttribute("helpUrl"));
                contrib.setModelViewTemplateId(element.getAttribute("modelViewTemplateId"));
        
                contrib.setIconDescriptor(makeImageDescriptor(bundleId, element.getAttribute("icon")));
                contrib.setPreviewImage(makeImageDescriptor(bundleId, element.getAttribute("previewImage")));
        
                contribs.add(contrib);
            }
        } catch (final Exception e) {
            CreationWizard.LOG.error("Unable to register wizard contribution '%s':\n\t=>'%s'", element.getContributor().getName(), e.getMessage());
            CreationWizard.LOG.debug(e);
        }
        
    }

    @objid ("d99b8e29-e2ba-4fb4-a508-35d33a1ddce6")
    private ImageDescriptor makeImageDescriptor(final String bundleId, final String iconPathString) {
        if (iconPathString == null) {
            return null;
        }
        final Bundle bundle = Platform.getBundle(bundleId);
        final IPath iconPath = new Path(iconPathString);
        final URL iconUrl = FileLocator.find(bundle, iconPath, null);
        return ImageDescriptor.createFromURL(iconUrl);
    }

    @objid ("71aa7f61-5148-449c-a4b4-82dddf508839")
    public List<IWizardContributor> getContributions(ContributorCategory category) {
        return this.wizards.getOrDefault(category.getType(), Collections.emptyList());
    }

    @objid ("193300e6-96e4-46fd-b8d2-04b641022e15")
    public void addContribution(ContributorCategory category, IWizardContributor contributor) {
        String categoryId = category.getType();
        
        // Register category if necessary
        if (!this.categories.containsKey(categoryId) || !this.wizards.containsKey(categoryId)) {
            this.categories.put(categoryId, category);
            this.wizards.put(categoryId, new ArrayList<>());
        }
        
        // Register contribution
        List<IWizardContributor> contribs = this.wizards.get(categoryId);
        ContextInjectionFactory.inject(contributor, this.context);
        contribs.add(contributor);
        
    }

    @objid ("4ab570c6-cdc0-46ad-bcb5-e793dcf969d6")
    public void removeContribution(ContributorCategory category, IWizardContributor contributor) {
        String categoryId = category.getType();
        final List<IWizardContributor> categoryContributors = this.wizards.get(categoryId);
        if (categoryContributors != null) {
            categoryContributors.remove(contributor);
            // Remove category from the list if it has no more contributors
            if (categoryContributors.isEmpty()) {
                this.wizards.remove(categoryId);
                this.categories.remove(categoryId);
                ContextInjectionFactory.uninject(contributor, this.context);
            }
        }
        
    }

}
