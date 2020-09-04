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

package org.modelio.diagram.editor.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Assert;

/**
 * Implementation of {@link IDiagramConfigurerRegistry}.
 * <p>
 * Instantiated by the OSGI framework as an OSGI service.<br>
 * See <code>OSGI-INF/configurer_registry.xml</code>.
 */
@objid ("666c0fab-33f7-11e2-95fe-001ec947c8cc")
public class ConfigurerRegistry implements IDiagramConfigurerRegistry {
    @objid ("666c0fac-33f7-11e2-95fe-001ec947c8cc")
    private Map<String, IDiagramConfigurer> configurers = new HashMap<>();

    @objid ("666e71e6-33f7-11e2-95fe-001ec947c8cc")
    public ConfigurerRegistry() {
        // Nothing to init
    }

    @objid ("666e71e8-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void registerDiagramConfigurer(final String metaclassName, final String stereotype, final IDiagramConfigurer configurer) {
        Assert.isNotNull(metaclassName);
        Assert.isNotNull(configurer);
        
        String key = metaclassName;
        if (stereotype != null) {
            key = key + stereotype;
        }
        this.configurers.put(key, configurer);
    }

    /**
     * Unregisters a {@link IDiagramConfigurer} for the given metaclass and stereotype.
     * @param metaclassName the name of the diagram metaclass the given IDiagramConfigurer is bound to. Must not be <code>null</code> nor
     * empty.
     * @param stereotype the stereotype the given IDiagramConfigurer is bound to. May be <code>null</code> or empty.
     * @param configurer the IDiagramConfigurer to unregister.
     */
    @objid ("666e71f1-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void unregisterDiagramConfigurer(final String metaclassName, final String stereotype, final IDiagramConfigurer configurer) {
        Assert.isNotNull(metaclassName);
        
        String key = metaclassName;
        if (stereotype != null) {
            key = key + stereotype;
        }
        
        if (configurer.equals(this.configurers.get(key))) {
            this.configurers.remove(key);
        }
    }

    @objid ("666e71fb-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public List<IDiagramConfigurer> getConfigurers(final String metaclassName, final List<String> stereotypes) {
        Assert.isNotNull(metaclassName);
        
        List<IDiagramConfigurer> result = new ArrayList<>();
        
        final IDiagramConfigurer classConfigurer = this.configurers.get(metaclassName);
        if (classConfigurer != null)
            result.add(classConfigurer);
        
        if (stereotypes != null) {
            for (String stereotype : stereotypes) {
                String key = metaclassName + stereotype;
                if (this.configurers.containsKey(key)) {
                    result.add(this.configurers.get(key));
                }
            }
        }
        return result;
    }

    @objid ("666e7208-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public IDiagramConfigurer getConfigurer(final String metaclassName) {
        Assert.isNotNull(metaclassName);
        return this.configurers.get(metaclassName);
    }

}
