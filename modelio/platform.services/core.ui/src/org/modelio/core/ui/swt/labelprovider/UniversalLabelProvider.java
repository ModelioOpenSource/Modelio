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

package org.modelio.core.ui.swt.labelprovider;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.RegistryFactory;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.core.ui.swt.images.IModelioElementLabelProvider;

/**
 * Browser tree label provider loading all extension points by itself.
 */
@objid ("9edd2f05-b8e3-4f7f-a7da-13adb2663468")
public class UniversalLabelProvider extends BrowserLabelProvider {
    @objid ("b8aaf619-c412-470c-b372-621cf3af6273")
    private static final String LABEL_PROVIDER_EXTENSION_POINT = "org.modelio.app.metamodel.browser.labelprovider";

    @objid ("3cd42bd4-1ea3-4b14-a3a2-20b6de0ecf61")
    private IRegistryEventListener listener;

    /**
     * Default c'tor.
     */
    @objid ("a41fd1d2-305e-47b4-9c70-6369e61d3a41")
    public UniversalLabelProvider() {
        super();
        
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        this.listener = new MmServicesListener();
        
        registry.addListener(this.listener, LABEL_PROVIDER_EXTENSION_POINT);
        
        IConfigurationElement[] cfels = registry.getConfigurationElementsFor(LABEL_PROVIDER_EXTENSION_POINT);
        addExtensionElements(cfels);
    }

    /**
     * Register the metamodel related services declared in the given {@link IConfigurationElement} trees.
     * <p>
     * The default implementation looks for "service" child elements with:<ul>
     * <li>a "metamodel" string attribute
     * <li>a "implementation" string attribute representing a java class accessible by the declaring plugin.
     * </ul>
     * 
     * @param configurationElements the {@link IConfigurationElement} at the root of the matched plugin extensions.
     */
    @objid ("44d3d12b-1757-4282-afba-e323d233f90e")
    protected void addExtensionElements(IConfigurationElement[] configurationElements) {
        for (IConfigurationElement ce : configurationElements) {
            String mmf = ce.getAttribute("metamodel");
        
            try {
                IModelioElementLabelProvider svc = (IModelioElementLabelProvider) ce.createExecutableExtension("implementation");
                registerExtension(mmf, svc);
            } catch (CoreException e) {
                CoreUi.LOG.error("Failed registering '%s' service provided by '%s' for '%s' metamodel: %s",
                        ce.getAttribute("implementation"),
                        ce.getContributor().getName(),
                        mmf,
                        e.getMessage()
                        );
                CoreUi.LOG.error(e);
            }
        }
    }

    /**
     * Unregister the services that were registered with {@link #addExtensionElements(IConfigurationElement[])}.
     * @see #addExtensionElements(IConfigurationElement[])
     * 
     * @param configurationElements the configuration elements to remove.
     */
    @objid ("477bf3d3-fc26-411c-8242-e6d96c4abc6d")
    protected void removeExtensionElements(IConfigurationElement[] configurationElements) {
        for (IConfigurationElement cf : configurationElements) {
            if (cf.getName().equals("services")) {
                for (IConfigurationElement svcEl : cf.getChildren("service")) {
                    String mmf = svcEl.getAttribute("metamodel");
                    unregisterExtension(mmf);
                }
            }
        }
    }

    @objid ("8aaa7301-7192-4138-b37b-7a53fd4206df")
    @Override
    public void dispose() {
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        if (registry != null) {
            registry.removeListener(this.listener);
        }
    }

    @objid ("d3126c96-94eb-4dd6-a935-ee400c3a2783")
    private class MmServicesListener implements IRegistryEventListener {
        @objid ("2827cae4-cf6c-4aeb-94ab-b4479e54cba8")
        public MmServicesListener() {
            // Empty
        }

        @objid ("a5ad7d39-de0b-44a1-8f34-1e83d0667c46")
        @Override
        public void added(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                addExtensionElements(configurationElements);
            }
        }

        @objid ("47038afb-4bba-4fdf-b41d-db4a156a2f81")
        @Override
        public void removed(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                removeExtensionElements(configurationElements);
            }
        }

        @objid ("d566e4b8-79ac-4bcc-b6ee-71dee5152367")
        @Override
        public void added(IExtensionPoint[] extensionPoints) {
            // nothing
        }

        @objid ("6fcebfae-9a9e-4210-9c46-ed29ba10e6ff")
        @Override
        public void removed(IExtensionPoint[] extensionPoints) {
            // nothing
        }

    }

}
