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

package org.modelio.module.propertytab.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("c87d0270-1eba-11e2-9382-bc305ba4815c")
public class ModulePropertyModel implements IModulePropertyTable {
    /**
     * Properties to display for <i>Module</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for other rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("c87d2980-1eba-11e2-9382-bc305ba4815c")
    private List<ModuleProperty> properties;

    @objid ("c87d2984-1eba-11e2-9382-bc305ba4815c")
    private IModulePropertyPage propertyPage;

    @objid ("c87d5090-1eba-11e2-9382-bc305ba4815c")
    private List<MObject> selectedElements;

    @objid ("c87d5093-1eba-11e2-9382-bc305ba4815c")
    private ICoreSession modelingSession;

    @objid ("c87d5094-1eba-11e2-9382-bc305ba4815c")
    public ModulePropertyModel(ICoreSession modelingSession, IModulePropertyPage propertyPage, List<MObject> selectedElements) {
        this.properties = new ArrayList<>();
        this.propertyPage = propertyPage;
        this.selectedElements = selectedElements;
        this.modelingSession = modelingSession;
        
        if (propertyPage != null) {
            propertyPage.update(selectedElements, this);
        }
    }

    @objid ("c87d77a4-1eba-11e2-9382-bc305ba4815c")
    @Override
    public final void addProperty(String key, int value) {
        ModuleProperty property = new ModuleProperty(key, String.class, value, null, null, null, false);
        this.properties.add(property);
    }

    @objid ("c87d9eb1-1eba-11e2-9382-bc305ba4815c")
    @Override
    public final void addProperty(String key, String value) {
        ModuleProperty property = new ModuleProperty(key, String.class, value, null, null, null, false);
        this.properties.add(property);
    }

    @objid ("c87d9eb5-1eba-11e2-9382-bc305ba4815c")
    @Override
    public final void addProperty(String key, boolean value) {
        ModuleProperty property = new ModuleProperty(key, Boolean.class, value, null, null, null, false);
        this.properties.add(property);
    }

    @objid ("c87dc5c2-1eba-11e2-9382-bc305ba4815c")
    @Override
    public final void addProperty(String key, String value, String[] choices) {
        ModuleProperty property = new ModuleProperty(key, String[].class, value, choices, null, null, false);
        this.properties.add(property);
    }

    @objid ("c87decd3-1eba-11e2-9382-bc305ba4815c")
    @Override
    public final void addConsultProperty(String key, String value) {
        ModuleProperty property = new ModuleProperty(key, String.class, value, null, null, null, true);
        this.properties.add(property);
    }

    @objid ("c87e13e0-1eba-11e2-9382-bc305ba4815c")
    @Override
    public void clearTable() {
        this.properties.clear();
        
        if (this.propertyPage != null) {
            this.propertyPage.update(this.selectedElements, this);
        }
    }

    @objid ("c87e13e3-1eba-11e2-9382-bc305ba4815c")
    public void setValueAt(ModuleProperty property, Object value) {
        int row = this.properties.indexOf(property);
        if (this.selectedElements == null) {
            return;
        }
        
        try (ITransaction t = this.modelingSession.getTransactionSupport().createTransaction("Set value on " + this.propertyPage.getName())) {
            this.propertyPage.changeProperty(this.selectedElements, row + 1, String.valueOf(value));
            t.commit();
        } catch (final RuntimeException e) {
            this.propertyPage.getModule().getModuleContext().getLogService().error(e);
        }
    }

    @objid ("c87e3af1-1eba-11e2-9382-bc305ba4815c")
    public List<ModuleProperty> getProperties() {
        return this.properties;
    }

    @objid ("e9a1b062-dd78-4e27-abb4-66a9d1c93502")
    @Override
    public final void addProperty(String key, MObject value, Collection<? extends MClass> acceptedMetaclasses, IMObjectFilter mobjectFilter) {
        ModuleProperty property = new ModuleProperty(key, MObject.class, value, null, acceptedMetaclasses, mobjectFilter, false);
        this.properties.add(property);
    }

    @objid ("962f43b3-e410-49cf-9a59-0517e31ab013")
    public List<MObject> getSelectedElements() {
        return this.selectedElements;
    }

}
