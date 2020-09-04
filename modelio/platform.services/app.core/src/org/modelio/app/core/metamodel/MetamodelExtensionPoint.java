/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.core.metamodel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.RegistryFactory;
import org.modelio.app.core.plugin.AppCore;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Helper to handle plugin extensions for one metamodel related service.
 * <p>
 * This class should be instantiated for each needed extension point.
 * 
 * @author cmarin
 * @since 3.6
 * 
 * @param <S> the metamodel related service interface
 */
@objid ("4113ce1e-5fe3-41c8-8628-54635f4b4d69")
public class MetamodelExtensionPoint<S> {
    @objid ("0a5b9caf-4294-4055-8b06-ed23d8b705bb")
    private final Map<String, S> extensionMap = new HashMap<>(5);

    @objid ("aaddf797-b362-445f-91e0-c98629befeeb")
    private IRegistryEventListener listener;

    /**
     * @param extensionPointId the handled extension point id
     */
    @objid ("720f7e9c-410d-4e58-8a3d-88f6850660fc")
    public MetamodelExtensionPoint(String extensionPointId) {
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        this.listener = new MmServicesListener();
        
        registry.addListener(this.listener, extensionPointId);
        
        IConfigurationElement[] cfels = registry.getConfigurationElementsFor(extensionPointId);
        addExtensionElements(cfels);
    }

    /**
     * Release the registered extension point listener.
     */
    @objid ("31bb755e-ace7-4e9f-acf1-f227e5ac4fa2")
    public void dispose() {
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        if (registry != null) {
            registry.removeListener(this.listener);
        }
    }

    /**
     * Get the service for a model object
     * @param obj a model object
     * @return the found service or null.
     */
    @objid ("eaf30391-703d-4cc5-b2e2-0ed4622f7fc1")
    public S get(MObject obj) {
        return get(obj.getMClass().getOrigin());
    }

    /**
     * Get the service for a metamodel fragment.
     * @param mmf a metamodel fragment.
     * @return the found service or null.
     */
    @objid ("4ef55740-5693-400d-a7ff-3bce0b6c7581")
    public S get(MMetamodelFragment mmf) {
        return this.extensionMap.get(mmf.getName());
    }

    /**
     * Register a service implementation for a metamodel fragment.
     * @param mmName the metamodel fragment name as returned by {@link MMetamodelFragment#getName()}.
     * @param svc the service implementation.
     */
    @objid ("e5047ea0-0799-4866-9afc-2aecd240cf61")
    public void register(String mmName, S svc) {
        this.extensionMap.put(mmName, svc);
    }

    /**
     * Unregister a service implementation for a metamodel fragment.
     * @param mmName the metamodel fragment name as returned by {@link MMetamodelFragment#getName()}.
     * @param svc the service implementation.
     */
    @objid ("0bbfce9b-2d60-49e4-a70d-4aac860a66f0")
    public void unregister(String mmName, S svc) {
        this.extensionMap.remove(mmName, svc);
    }

    /**
     * Register the service implementation for a metamodel fragment.
     * @param mmName the metamodel fragment name as returned by {@link MMetamodelFragment#getName()}.
     */
    @objid ("a578c917-c002-40e2-bc86-5c71ba857090")
    public void removeMetamodel(String mmName) {
        this.extensionMap.remove(mmName);
    }

    /**
     * Register the metamodel related services declared in the given {@link IConfigurationElement} trees.
     * <p>
     * The default implementation looks for "service" child elements with:<ul>
     * <li>a "metamodel" string attribute
     * <li>a "implementation" string attribute representing a java class accessible by the declaring plugin.
     * </ul>
     * @param configurationElements the {@link IConfigurationElement} at the root of the matched plugin extensions.
     */
    @objid ("b18453f3-4211-4f5b-8333-7dfaf6b825bf")
    protected void addExtensionElements(IConfigurationElement[] configurationElements) {
        for (IConfigurationElement ce : configurationElements) {
            String mmf = ce.getAttribute("metamodel");
        
            try {
                @SuppressWarnings("unchecked")
                S svc = (S) ce.createExecutableExtension("implementation");
                register(mmf, svc);
            } catch (CoreException e) {
                AppCore.LOG.error("Failed registering '%s' service provided by '%s' for '%s' metamodel: %s",
                        ce.getAttribute("implementation"),
                        ce.getContributor().getName(),
                        mmf,
                        e.getMessage()
                        );
                AppCore.LOG.error(e);
            }
        }
    }

    /**
     * Unregister the services that were registered with {@link #addExtensionElements(IConfigurationElement[])}.
     * @see #addExtensionElements(IConfigurationElement[])
     * @param configurationElements the configuration elements to remove.
     */
    @objid ("f21efb64-b606-44ed-9ca2-bd509f18463c")
    protected void removeExtensionElements(IConfigurationElement[] configurationElements) {
        for (IConfigurationElement cf : configurationElements) {
            if (cf.getName().equals("services")) {
                for (IConfigurationElement svcEl : cf.getChildren("service")) {
                    String mmf = svcEl.getAttribute("metamodel");
                    removeMetamodel(mmf);
                }
            }
        }
    }

    /**
     * Get all registered services
     * @return a collection of services. Might be empty.
     */
    @objid ("5061d878-c883-43e6-a2d6-63ae2b2bad9e")
    public Collection<S> getAll() {
        return this.extensionMap.values();
    }

    @objid ("4a30929c-64da-4645-a97a-4756c0a7bfe5")
    class MmServicesListener implements IRegistryEventListener {
        @objid ("7dd8ed98-56a7-43b6-a966-e054f4095f35")
        public MmServicesListener() {
        }

        @objid ("aec08f3e-92ca-42ed-9bc5-fe615b2efe54")
        @Override
        public void added(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                addExtensionElements(configurationElements);
            }
        }

        @objid ("48807ddc-ddd4-4261-a993-33c4640f501e")
        @Override
        public void removed(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                removeExtensionElements(configurationElements);
            }
        }

        @objid ("685f30c8-ad3a-4250-ae6d-a352fb0d64ab")
        @Override
        public void added(IExtensionPoint[] extensionPoints) {
            // nothing
        }

        @objid ("13da9bf0-b015-4580-925b-1e4c58a547a6")
        @Override
        public void removed(IExtensionPoint[] extensionPoints) {
            // nothing
        }

    }

}
