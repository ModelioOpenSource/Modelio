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
package org.modelio.platform.preferences;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.modelio.gproject.gproject.GProject;

/**
 * This class implements a preference node for Modelio GProject preferences.
 * It mainly prefixes the preferences keys with a specific 'nodeId' before delegating to its configured store.
 * 
 * @author phv
 */
@objid ("2452eb5c-1e87-490f-b7a7-f2a2aaa7e49c")
public class GProjectPreferenceNode implements IGProjectPreferenceStore {
    @objid ("785619ad-8546-46ef-a72a-4f12eef068b2")
    private String prefix;

    @objid ("d2e27f6b-60e9-40d8-8847-fe824613de2d")
    private GProjectPreferenceStore store;

    /**
     * @param prefsStore - the underlying concrete preference store
     * @param nodeId - the prefix to prepend to keys before calling the concrete store
     */
    @objid ("0bf3fd04-1c41-4a37-93ff-64f61bbb7d35")
    public  GProjectPreferenceNode(GProjectPreferenceStore prefsStore, String nodeId) {
        this.store = prefsStore;
        this.prefix = nodeId + "/";
        
    }

    @objid ("849e48a5-a3f5-4d77-91b8-f86f54ed60ea")
    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener) {
        this.store.addPropertyChangeListener(listener);
    }

    @objid ("4fac134d-ba54-488c-9ad1-69294f7301c8")
    @Override
    public boolean contains(String name) {
        return this.store.contains(this.prefix + name);
    }

    @objid ("e848d59e-f1b5-40ab-80d0-f08d32906ee2")
    @Override
    public void firePropertyChangeEvent(String name, Object oldValue, Object newValue) {
        this.store.firePropertyChangeEvent(this.prefix + name, oldValue, newValue);
    }

    @objid ("ba085ebf-bfcc-4465-802a-1f6e7333d95b")
    @Override
    public boolean getBoolean(String name) {
        return this.store.getBoolean(this.prefix + name);
    }

    @objid ("3203c027-ce54-454a-9529-9433dbc4d5f5")
    @Override
    public boolean getDefaultBoolean(String name) {
        return this.store.getDefaultBoolean(this.prefix + name);
    }

    @objid ("3179c27d-9b0b-45da-8c92-0bc4517117c7")
    @Override
    public double getDefaultDouble(String name) {
        return this.store.getDefaultDouble(this.prefix + name);
    }

    @objid ("e02db7b5-9c0c-4971-b5cb-48acaea30bd1")
    @Override
    public float getDefaultFloat(String name) {
        return this.store.getDefaultFloat(this.prefix + name);
    }

    @objid ("61a62b62-821b-443b-a3fc-e65667eea409")
    @Override
    public int getDefaultInt(String name) {
        return this.store.getDefaultInt(this.prefix + name);
    }

    @objid ("3846ca0b-1b60-4246-af4f-8e0e5eda8137")
    @Override
    public long getDefaultLong(String name) {
        return this.store.getDefaultLong(this.prefix + name);
    }

    @objid ("846d2ec7-d02d-4a9f-9f3a-9c3eab6a5b1d")
    @Override
    public String getDefaultString(String name) {
        return this.store.getDefaultString(this.prefix + name);
    }

    @objid ("42c27587-7009-41be-9143-5d0e6ab2e5ae")
    @Override
    public double getDouble(String name) {
        return this.store.getDouble(this.prefix + name);
    }

    @objid ("25c0556a-721c-4652-b7ea-63e9085e64c7")
    @Override
    public float getFloat(String name) {
        return this.store.getFloat(this.prefix + name);
    }

    @objid ("a18e3e11-0eb6-4087-acb7-3ea9e8e66d80")
    @Override
    public int getInt(String name) {
        return this.store.getInt(this.prefix + name);
    }

    @objid ("ccc9c658-5d60-4218-a09e-896e914714c9")
    @Override
    public long getLong(String name) {
        return this.store.getLong(this.prefix + name);
    }

    @objid ("fc594d2d-bd2c-42b6-80bf-66492afd3fa9")
    @Override
    public String getString(String name) {
        return this.store.getString(this.prefix + name);
    }

    @objid ("e904b5fe-d88e-4d2f-95ea-196f7781753f")
    @Override
    public boolean isDefault(String name) {
        return this.store.isDefault(this.prefix + name);
    }

    @objid ("8a22f849-b2eb-4e7c-ab3d-aba9b874da36")
    @Override
    public boolean needsSaving() {
        return this.store.needsSaving();
    }

    @objid ("1ff5ff7d-9949-4f4b-8dd6-53f32fac5881")
    @Override
    public void putValue(String name, String value) {
        this.store.putValue(this.prefix + name, value);
    }

    @objid ("cfa1c2cf-d159-4282-b54d-3a2f56e74995")
    @Override
    public void removePropertyChangeListener(IPropertyChangeListener listener) {
        this.store.removePropertyChangeListener(listener);
    }

    @objid ("f2e31b92-0b90-473c-9071-5d532c9e763f")
    @Override
    public void setDefault(String name, double value) {
        this.store.setDefault(this.prefix + name, value);
    }

    @objid ("d7119939-e3d5-4098-be2e-a66652621098")
    @Override
    public void setDefault(String name, float value) {
        this.store.setDefault(this.prefix + name, value);
    }

    @objid ("de823fbb-5766-4c11-8fb5-4b5b576c6539")
    @Override
    public void setDefault(String name, int value) {
        this.store.setDefault(this.prefix + name, value);
    }

    @objid ("ad277658-7e98-4f16-aacb-9ee62e3e24f3")
    @Override
    public void setDefault(String name, long value) {
        this.store.setDefault(this.prefix + name, value);
    }

    @objid ("b3bd23bb-0999-4357-a5cc-66aefcde466f")
    @Override
    public void setDefault(String name, String defaultObject) {
        this.store.setDefault(this.prefix + name, defaultObject);
    }

    @objid ("3e0c65cb-1ea8-4157-b7a0-a36c15927034")
    @Override
    public void setDefault(String name, boolean value) {
        this.store.setDefault(this.prefix + name, value);
    }

    @objid ("26591cbf-8f7a-4208-aee7-4086ab4b9ae1")
    @Override
    public void setToDefault(String name) {
        this.store.setToDefault(this.prefix + name);
    }

    @objid ("ccbd96c8-8402-4029-8de0-cf300bc40f8e")
    @Override
    public void setValue(String name, double value) {
        this.store.setValue(this.prefix + name, value);
    }

    @objid ("08eaed7e-0489-4989-89c7-e10a6e11366b")
    @Override
    public void setValue(String name, float value) {
        this.store.setValue(this.prefix + name, value);
    }

    @objid ("e8562ae1-4320-49d5-a40c-50061787811c")
    @Override
    public void setValue(String name, int value) {
        this.store.setValue(this.prefix + name, value);
    }

    @objid ("a7500207-6fa6-4252-8d03-013f70071639")
    @Override
    public void setValue(String name, long value) {
        this.store.setValue(this.prefix + name, value);
    }

    @objid ("29581e74-b7d6-416a-9fad-5aa925a94311")
    @Override
    public void setValue(String name, String value) {
        this.store.setValue(this.prefix + name, value);
    }

    @objid ("659b2904-0d8a-4321-8021-df82b1924027")
    @Override
    public void setValue(String name, boolean value) {
        this.store.setValue(this.prefix + name, value);
    }

    @objid ("281fe696-ef2c-459f-a9c0-bdc60105281b")
    public GProjectPreferenceStore getStore() {
        return this.store;
    }

    @objid ("453d1e9b-4a2d-45f4-9a39-38bd35d47bd7")
    @Override
    public void save() throws IOException {
        this.store.save();
    }

    @objid ("8f2ad262-fbe2-497d-b849-122055359912")
    @Override
    public GProject getProject() {
        return this.store.getProject();
    }

}
