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

package org.modelio.diagram.elements.core.model.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.elements.plugin.DiagramElements;

@objid ("51ea1191-9277-41dd-87a4-4a8212b3bd78")
public class DiagramFactoryRegistry {
    @objid ("cf503476-ced6-4505-9616-62284ddc35fd")
    private static final DiagramFactoryRegistry INSTANCE = new DiagramFactoryRegistry();

    @objid ("afdf0d3b-d3e3-4688-984b-f2854241637b")
    private Map<String, IGmNodeFactory> gmNodeFactories = new HashMap<>();

    @objid ("7ecaa010-7920-45fe-acb1-1fa00a46f7ed")
    private Map<String, IGmLinkFactory> gmLinkFactories = new HashMap<>();

    @objid ("25bd8d40-3e03-4793-8114-cc10122dcbea")
    private Map<String, EditPartFactory> editPartFactories = new HashMap<>();

    @objid ("14c5909d-259f-4e0e-997d-921997cdd4b3")
    private Map<String, List<String>> factoryExtensions = new HashMap<>();

    @objid ("7ec94618-9af1-49dc-9825-3b45f14c84c8")
    public static DiagramFactoryRegistry getInstance() {
        return DiagramFactoryRegistry.INSTANCE;
    }

    /**
     * Register factory extensions.
     * 
     * @param factoryId the identifier for the factory extensions.
     * @param nodeFactory the node factory extension.
     * @param linkFactory the link factory extension.
     * @param editPartFactory the edit part factory extension.
     */
    @objid ("8272aca5-b544-4bd7-9d12-0d75bf996b81")
    public void registerDiagramFactories(String factoryId, IGmNodeFactory nodeFactory, IGmLinkFactory linkFactory, EditPartFactory editPartFactory) {
        this.gmNodeFactories.put(factoryId, nodeFactory);
        this.gmLinkFactories.put(factoryId, linkFactory);
        this.editPartFactories.put(factoryId, editPartFactory);
    }

    /**
     * Get the node factory for this identifier.
     * 
     * @param factoryId the identifier for the factory extension.
     * @return the node factory extension.
     */
    @objid ("9e621f4d-3b32-4f91-afd7-4c71fcd80337")
    public IGmNodeFactory getNodeFactory(String factoryId) {
        return this.gmNodeFactories.get(factoryId);
    }

    /**
     * Get the link factory for this identifier.
     * 
     * @param factoryId the identifier for the factory extension.
     * @return the link factory extension.
     */
    @objid ("def8db97-f3f0-4123-835f-66ee65314e4c")
    public IGmLinkFactory getLinkFactory(String factoryId) {
        return this.gmLinkFactories.get(factoryId);
    }

    /**
     * Get the edit part factory for this identifier.
     * 
     * @param factoryId the identifier for the factory extension.
     * @return the edit part factory extension.
     */
    @objid ("4c25508c-2634-4c44-bec6-250f240936d2")
    public EditPartFactory getEditPartFactory(String factoryId) {
        return this.editPartFactories.get(factoryId);
    }

    /**
     * Register a factory extension.
     * 
     * @param extensionId the identifier for the factory extension.
     * @param extendedFactoryIds identifiers of all extended factories.
     */
    @objid ("04b6da04-84db-45c7-9723-a2c76724a547")
    public void registerExtensions(String extensionId, String... extendedFactoryIds) {
        for (String extendedFactoryId : extendedFactoryIds) {
            List<String> extensions = this.factoryExtensions.computeIfAbsent(extendedFactoryId, k-> new ArrayList<>());
        
            if (!extensions.contains(extensionId)) {
                extensions.add(extensionId);
            } else {
                DiagramElements.LOG.debug("'%s' Factory extension already registered for '%s'" , extensionId, extendedFactoryId);
            }
        }
    }

    /**
     * @param factoryId a factory id
     * @return the factory extensions
     */
    @objid ("18265996-9f83-4b47-bd79-52b2d2686ee5")
    public List<String> getExtensions(String factoryId) {
        return this.factoryExtensions.getOrDefault(factoryId, Collections.emptyList());
    }

}
