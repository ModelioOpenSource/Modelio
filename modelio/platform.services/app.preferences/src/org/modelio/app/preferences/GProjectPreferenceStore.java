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

package org.modelio.app.preferences;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.common.EventManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.util.SafeRunnable;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.gproject.GProject;

/**
 * Provides a PreferenceStore which persists the values in the properties of a GProject
 * 
 * @author phv
 */
@objid ("23467dd5-3fbd-48be-94a1-f9dfc5137a1e")
public class GProjectPreferenceStore extends EventManager implements IGProjectPreferenceStore {
    @objid ("585f3419-b8dd-4266-8a4a-c3a21e51d1ed")
    private static final String DEFAULT = ".default";

    @objid ("c5bddbf3-d29d-47df-a8f0-c55b625d075e")
    private boolean dirty;

    @objid ("d1a5f8cf-f9a2-42cb-9af4-cfc3791c0eba")
    private String subSet;

    @objid ("4b068e30-1dd4-48e4-b91c-079919c2bb4a")
    private GProject project;

    @objid ("b870b159-1dc7-4e79-a3fb-9748ae7b43fe")
     List<IPropertyChangeListener> listeners = new ArrayList<>();

    @objid ("72a2fec7-bb26-4608-80c4-e0989af69dc6")
    public GProjectPreferenceStore(GProject gProject) {
        assert (gProject != null);
        
        this.project = gProject;
        this.subSet = "";
    }

    @objid ("cac3ea84-fe37-45a0-9b64-5c05e02fe9eb")
    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener) {
        this.listeners.add(listener);
    }

    @objid ("8fafa29b-3b9c-4ec8-bfa7-0af59296330c")
    @Override
    public boolean contains(String baseName) {
        String name = this.subSet.isEmpty() ? baseName : getPrefixedName(baseName);
        return this.project.getProperties().getProperty(name) != null;
    }

    @objid ("71669798-a1d0-4c47-b432-ae6ad15eeaaa")
    @Override
    public void firePropertyChangeEvent(String name, Object oldValue, Object newValue) {
        if (!this.listeners.isEmpty() && (oldValue == null || !oldValue.equals(newValue))) {
            final PropertyChangeEvent pe = new PropertyChangeEvent(this, name, oldValue, newValue);
            for (IPropertyChangeListener l : this.listeners) {
                final IPropertyChangeListener final_l = l;
                SafeRunnable.run(new SafeRunnable() {
                    @Override
                    public void run() throws Exception {
                        final_l.propertyChange(pe);
                    }
                });
            }
        }
    }

    @objid ("702af4bf-36f8-41d3-9942-84676595a0eb")
    @Override
    public boolean getBoolean(String name) {
        // First, try to get the property 'subset#name'
        if (!this.subSet.isEmpty()) {
            String v = this.project.getProperties().getValue(getPrefixedName(name));
            if (v != null) {
                return Boolean.parseBoolean(v);
            }
        }
        
        // No subset property defined, get the property 'name'
        String v = this.project.getProperties().getValue(name);
        if (v == null) {
            return BOOLEAN_DEFAULT_DEFAULT;
        } else {
            return Boolean.parseBoolean(v);
        }
    }

    @objid ("e3f65795-9c79-4bae-9c75-8a93476b6611")
    @Override
    public boolean getDefaultBoolean(String name) {
        return getBoolean(name + DEFAULT);
    }

    @objid ("0e4a09be-3928-408a-abab-bdac0ec10d65")
    @Override
    public double getDefaultDouble(String name) {
        return getDouble(name + DEFAULT);
    }

    @objid ("1dc6ed60-606a-4416-b28d-4d6fef850098")
    @Override
    public float getDefaultFloat(String name) {
        return getFloat(name + DEFAULT);
    }

    @objid ("7e4727df-6417-4ca7-94ab-1ef0ffbfac4f")
    @Override
    public int getDefaultInt(String name) {
        return getInt(name + DEFAULT);
    }

    @objid ("3d629385-c0be-4302-b235-169b96619884")
    @Override
    public long getDefaultLong(String name) {
        return getLong(name + DEFAULT);
    }

    @objid ("4c5c22db-35e8-4ce4-822a-3e665174658b")
    @Override
    public String getDefaultString(String name) {
        return getString(name + DEFAULT);
    }

    @objid ("5595bc06-1ece-4eb5-a156-18e4df8f8dd4")
    @Override
    public double getDouble(String name) {
        // First, try to get the property 'subset#name'
        if (!this.subSet.isEmpty()) {
            String v = this.project.getProperties().getValue(getPrefixedName(name));
            if (v != null) {
                try {
                    return Double.parseDouble(v);
                } catch (NumberFormatException e) {
                    return DOUBLE_DEFAULT_DEFAULT;
                }
            }
        }
        
        // No subset property defined, get the property 'name'
        String v = this.project.getProperties().getValue(name);
        if (v == null) {
            return DOUBLE_DEFAULT_DEFAULT;
        } else {
            try {
                return Double.parseDouble(v);
            } catch (NumberFormatException e) {
                return DOUBLE_DEFAULT_DEFAULT;
            }
        }
    }

    @objid ("c41545cb-b5f2-476f-9a55-c321c930b4e3")
    @Override
    public float getFloat(String name) {
        // First, try to get the property 'subset#name'
        if (!this.subSet.isEmpty()) {
            String v = this.project.getProperties().getValue(getPrefixedName(name));
            if (v != null) {
                try {
                    return Float.parseFloat(v);
                } catch (NumberFormatException e) {
                    return FLOAT_DEFAULT_DEFAULT;
                }
            }
        }
        
        // No subset property defined, get the property 'name'
        String v = this.project.getProperties().getValue(name);
        if (v == null) {
            return FLOAT_DEFAULT_DEFAULT;
        } else {
            try {
                return Float.parseFloat(v);
            } catch (NumberFormatException e) {
                return FLOAT_DEFAULT_DEFAULT;
            }
        }
    }

    @objid ("1e6b4ddb-c747-4429-8de7-3b0ca3fad8c1")
    @Override
    public int getInt(String name) {
        // First, try to get the property 'subset#name'
        if (!this.subSet.isEmpty()) {
            String v = this.project.getProperties().getValue(getPrefixedName(name));
            if (v != null) {
                try {
                    return Integer.parseInt(v);
                } catch (NumberFormatException e) {
                    return INT_DEFAULT_DEFAULT;
                }
            }
        }
        
        // No subset property defined, get the property 'name'
        String v = this.project.getProperties().getValue(name);
        if (v == null) {
            return INT_DEFAULT_DEFAULT;
        } else {
            try {
                return Integer.parseInt(v);
            } catch (NumberFormatException e) {
                return INT_DEFAULT_DEFAULT;
            }
        }
    }

    @objid ("17d974d2-c9f3-41d8-82b9-c17ada711e98")
    @Override
    public long getLong(String name) {
        // First, try to get the property 'subset#name'
        if (!this.subSet.isEmpty()) {
            String v = this.project.getProperties().getValue(getPrefixedName(name));
            if (v != null) {
                try {
                    return Long.parseLong(v);
                } catch (NumberFormatException e) {
                    return LONG_DEFAULT_DEFAULT;
                }
            }
        }
        
        // No subset property defined, get the property 'name'
        String v = this.project.getProperties().getValue(name);
        if (v == null) {
            return LONG_DEFAULT_DEFAULT;
        } else {
            try {
                return Long.parseLong(v);
            } catch (NumberFormatException e) {
                return LONG_DEFAULT_DEFAULT;
            }
        }
    }

    @objid ("52cc6607-544b-4a89-8629-4652e93078d6")
    @Override
    public String getString(String name) {
        // First, try to get the property 'subset#name'
        if (!this.subSet.isEmpty()) {
            String v = this.project.getProperties().getValue(getPrefixedName(name));
            if (v != null) {
                return v;
            }
        }
        
        // No subset property defined, get the property 'name'
        String v = this.project.getProperties().getValue(name);
        return (v == null) ? STRING_DEFAULT_DEFAULT : v;
    }

    @objid ("a4516ef0-0f1e-49ca-bfa3-35f2b9ccd7bf")
    @Override
    public boolean isDefault(String name) {
        return (!contains(name) && !contains(name + DEFAULT));
    }

    @objid ("29adc049-7c76-409d-881a-e6cb60f1563f")
    @Override
    public boolean needsSaving() {
        return this.dirty;
    }

    @objid ("d6d67319-6e7f-4600-a22d-0a81e1043d64")
    @Override
    public void putValue(String baseName, String value) {
        String name = this.subSet.isEmpty() ? baseName : getPrefixedName(baseName);
        
        Entry old = this.project.getProperties().getProperty(name);
        DefinitionScope scope = (old != null) ? old.getScope() : DefinitionScope.LOCAL;
        
        if (scope == DefinitionScope.SHARED)
            return;
        
        String oldValue = (old != null) ? old.getValue() : null;
        if (oldValue == null || !oldValue.equals(value)) {
            this.project.getProperties().setProperty(name, value, scope);
            this.dirty = true;
            firePropertyChangeEvent(name, oldValue, value);
        }
    }

    @objid ("18427025-4060-4815-a3fe-2971511e6a99")
    @Override
    public void removePropertyChangeListener(IPropertyChangeListener listener) {
        this.listeners.remove(listener);
    }

    @objid ("e71a94f6-7159-4eb3-88d1-6fb04a36c5f8")
    @Override
    public void setDefault(String name, double value) {
        setValue(name + DEFAULT, value);
    }

    @objid ("8ce137f2-b73a-4f16-98b4-7734e213c327")
    @Override
    public void setDefault(String name, float value) {
        setValue(name + DEFAULT, value);
    }

    @objid ("be56fa9c-717c-4aef-ac12-dced77f17f21")
    @Override
    public void setDefault(String name, int value) {
        setValue(name + DEFAULT, value);
    }

    @objid ("8a76f1a3-6ca6-45aa-b0de-67d784c686a1")
    @Override
    public void setDefault(String name, long value) {
        setValue(name + DEFAULT, value);
    }

    @objid ("ae77af4d-a18b-4f59-8bbd-1a86d950c786")
    @Override
    public void setDefault(String name, String defaultObject) {
        setValue(name + DEFAULT, defaultObject);
    }

    @objid ("0592ffd3-4dd6-4f66-bcfd-05d19e760d80")
    @Override
    public void setDefault(String name, boolean value) {
        setValue(name + DEFAULT, value);
    }

    @objid ("c5ac4a63-9ce1-4a2b-beab-fc56c91cd31b")
    @Override
    public void setToDefault(String name) {
        GProperties properties = this.project.getProperties();
        Entry def = properties.getProperty(name + DEFAULT);
        if (def != null) {
            Entry val = properties.getProperty(name);
            Object oldValue = (val != null)? val.getValue() : null;
            String defaultValue = def.getValue();
        
            properties.setProperty(name, defaultValue, def.getScope());
            this.dirty = true;
        
            firePropertyChangeEvent(name, oldValue, defaultValue);
        }
    }

    @objid ("11e7e77f-b6f3-41d3-85a1-8096cd821c70")
    @Override
    public void setValue(String name, double value) {
        putValue(name, Double.toString(value));
    }

    @objid ("ec3c32b1-3193-46b4-bd14-8aa0eea37cdf")
    @Override
    public void setValue(String name, float value) {
        putValue(name, Float.toString(value));
    }

    @objid ("ad9b2a66-3f3b-4926-a440-462626c6a56c")
    @Override
    public void setValue(String name, int value) {
        putValue(name, Integer.toString(value));
    }

    @objid ("5681b32c-8891-413c-b92e-f67c71f6682d")
    @Override
    public void setValue(String name, long value) {
        putValue(name, Long.toString(value));
    }

    @objid ("0c40beb8-7a41-4767-a810-f3d280b37ec8")
    @Override
    public void setValue(String name, String value) {
        putValue(name, value);
    }

    @objid ("684dca77-304e-4f92-b043-ab93981ee23d")
    @Override
    public void setValue(String name, boolean value) {
        putValue(name, Boolean.toString(value));
    }

    @objid ("3652db37-57d8-41b6-8617-be7be58207b1")
    @Override
    public void save() throws IOException {
        // not supported . Effective persistence  in under the responsibility of the project/project service
        // so do not call super and do NOT set this.dirty to false here !
    }

    @objid ("e49b5f1a-73f4-436a-ab7a-007aa6b55f2b")
    public void load() {
        // not supported this.store.load();
    }

    @objid ("69866f3a-e970-4add-adf0-aedb4058c427")
    public void load(InputStream in) {
        // not supported this.store.load(in);
    }

    @objid ("4e4bb5ab-f974-41ca-b833-836e88367ef7")
    public String getSubSet() {
        return this.subSet;
    }

    @objid ("c67adda3-8e42-481a-8f9f-da75bc9d4ccb")
    public void setSubSet(String subSet) {
        this.subSet = subSet;
    }

    @objid ("6d54b51b-264e-4bb6-8a9a-d080f8b99f92")
    public String getPrefixedName(String name) {
        return this.subSet + "#" + name;
    }

    @objid ("d101d1ac-a9f8-4a10-bd96-27fe471fa8ed")
    @Override
    public GProject getProject() {
        return this.project;
    }

    /**
     * Because saving is under the responsibility of the project service, this method is provided to reset the dirty flag.
     */
    @objid ("0745ee70-eb7c-48fd-b9c0-e639dbdfd227")
    public void resetDirty() {
        this.dirty = false;
    }

}
