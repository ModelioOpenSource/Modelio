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

package org.modelio.diagram.elements.common.embeddeddiagram;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.editparts.LayerManager;

/**
 * Edit part registry for embedded diagrams.
 * <p>
 * Redirects all calls to the root diagram edit part registry except when asking for the
 * layer manager. In this case return the embedded diagram layer manager.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("b9e9e1a6-9ab8-4ace-a601-ccb42b12b479")
class EmbeddedEditPartRegistry implements Map<Object,Object> {
    @objid ("af6fe73d-be14-47d8-8a34-b1e3f7a027ff")
    private final LayerManager localLayerManager;

    @objid ("9a1a35d4-2589-4bec-9544-d674441b838a")
    private final Map<Object, Object> registry;

    /**
     * @param registry the parent diagram edit part registry.
     * @param localLayerManager the embedded diagram layer manager.
     */
    @objid ("fb9507a0-3cff-450a-a5b8-c0e671de37ca")
    public EmbeddedEditPartRegistry(Map<Object, Object> registry, LayerManager localLayerManager) {
        this.registry = registry;
        this.localLayerManager = localLayerManager;
    }

    @objid ("268056db-ac88-4042-8fee-7e3dd6807c99")
    @Override
    public void clear() {
        this.registry.clear();
    }

    @objid ("c3a3b255-4ef3-48a6-a651-c674e2aa24bf")
    @Override
    public boolean containsKey(Object key) {
        return this.registry.containsKey(key);
    }

    @objid ("6db356cd-7b62-4887-9da6-f95a6d1dc3fc")
    @Override
    public boolean containsValue(Object value) {
        return this.registry.containsValue(value);
    }

    @objid ("813ee0a9-1229-4ca5-807a-cec9ed6e5f89")
    @Override
    public Set<java.util.Map.Entry<Object,Object>> entrySet() {
        return this.registry.entrySet();
    }

    @objid ("77a44f95-89ea-41a4-a966-71224de28888")
    @Override
    public Object get(Object key) {
        if (key == LayerManager.ID) {
            return this.localLayerManager;
        }
        return this.registry.get(key);
    }

    @objid ("2423b4a6-5090-41c3-a53d-fc83a9480d71")
    @Override
    public boolean isEmpty() {
        return this.registry.isEmpty();
    }

    @objid ("b87960bf-8ae2-42f3-83fe-0c329660ae2b")
    @Override
    public Set<Object> keySet() {
        return this.registry.keySet();
    }

    @objid ("bdab5680-f5f5-41fe-9ceb-2ea45752fdc2")
    @Override
    public Object put(Object key, Object value) {
        return this.registry.put(key, value);
    }

    @objid ("0f591292-f86b-46ee-bd1c-4ffdc33371e5")
    @Override
    public void putAll(Map<?,?> m) {
        this.registry.putAll(m);
    }

    @objid ("360d6a62-4807-4bd1-b7a2-15674737c5dc")
    @Override
    public Object remove(Object key) {
        return this.registry.remove(key);
    }

    @objid ("52900a2e-3bc6-4b9b-ac55-387285b4657e")
    @Override
    public int size() {
        return this.registry.size();
    }

    @objid ("4596a33f-2118-45f2-8cfc-586b43bb222d")
    @Override
    public Collection<Object> values() {
        return this.registry.values();
    }

}
