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

package org.modelio.platform.model.view.template.service;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.platform.model.view.template.plugin.ModelViewTemplate;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * Warning: class is to be got from the Eclipse context (to avoid repeated parsing of the extensions)
 */
@objid ("34e896b0-7a45-46e6-94ee-8bb49cae03b0")
public class ModelViewTemplateManager {
    @objid ("4c3c3baf-3c6c-4c3e-b980-5944a56e2c9f")
    private static final String VIEWTEMPLATE_EXTENSIONPOINT_ID = "org.modelio.platform.model.view.template";

    @objid ("cda4af1b-1efd-4167-b0c8-6787acd69b23")
    private final IEclipseContext context;

    @objid ("1da7496e-6a6e-49f2-8a21-2a2236d4d250")
    private final Map<String, IModelViewTemplate<? extends MObject>> templates = new HashMap<>();

    @objid ("b95d97d4-51a1-48ad-8ea3-e64c6e13d8e8")
    @Inject
    ModelViewTemplateManager(final IEclipseContext context) {
        this.context = context;
        
        for (final IConfigurationElement element : new ExtensionPointContributionManager(ModelViewTemplateManager.VIEWTEMPLATE_EXTENSIONPOINT_ID).getExtensions("viewtemplate")) {
            parseTemplate(element);
        }
    }

    @objid ("73ebd479-bc8f-4405-a8ec-a70ad1e302cc")
    private void parseTemplate(final IConfigurationElement element) {
        try {
            Object obj = element.createExecutableExtension("class");
            if (obj instanceof IModelViewTemplate) {
                ContextInjectionFactory.inject(obj, this.context);
                this.templates.put(element.getAttribute("id"), (IModelViewTemplate<?>) obj);
            }
        } catch (final CoreException e) {
            ModelViewTemplate.LOG.error("Unable to register model view template declared %s: %s", element.getContributor().getName(), e.getMessage());
            ModelViewTemplate.LOG.debug(e);
        }
    }

    @objid ("779886a9-4de0-4e92-b461-2758f9f1121b")
    private ImageDescriptor makeImageDescriptor(final String bundleId, final String iconPathString) {
        if (iconPathString == null) {
            return null;
        }
        final Bundle bundle = Platform.getBundle(bundleId);
        final IPath iconPath = new Path(iconPathString);
        final URL iconUrl = FileLocator.find(bundle, iconPath, null);
        return ImageDescriptor.createFromURL(iconUrl);
    }

    @objid ("69198628-444c-4e1a-b93a-77d36182814c")
    public <T extends MObject> IModelViewTemplate<T> get(String id) {
        return (IModelViewTemplate<T>) this.templates.get(id);
    }

    @objid ("a2735cfc-5db3-4651-b0a9-1f34ddc4dfd6")
    public void register(String id, IModelViewTemplate<? extends MObject> template) {
        this.templates.put(id, template);
    }

    @objid ("b88eb5bf-424e-49f6-bb27-0658c2cd903e")
    public void unregister(String id) {
        this.templates.remove(id);
    }

    @objid ("a0c81724-dc46-49cd-845d-21dd90138ead")
    public Collection<IModelViewTemplate<? extends MObject>> getAll() {
        return this.templates.values();
    }

}
