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

package org.modelio.module.propertytab.model;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("c879f530-1eba-11e2-9382-bc305ba4815c")
public class ModuleProperty {
    @objid ("c87a9172-1eba-11e2-9382-bc305ba4815c")
    private boolean readOnly;

    @objid ("04482b41-1ebb-11e2-9382-bc305ba4815c")
    private String name;

    @objid ("04485253-1ebb-11e2-9382-bc305ba4815c")
    private String label;

    @objid ("0448a072-1ebb-11e2-9382-bc305ba4815c")
    private String[] choices = null;

    @objid ("0448ee92-1ebb-11e2-9382-bc305ba4815c")
    private String category;

    @objid ("044915a2-1ebb-11e2-9382-bc305ba4815c")
    private String categoryLabel;

    @objid ("f1d8ffae-e483-4848-976f-437d090b8c90")
    private Collection<? extends MClass> acceptedMetaclasses;

    @objid ("c87a6a62-1eba-11e2-9382-bc305ba4815c")
    private Class<?> type;

    @objid ("71ce08d2-c528-45f2-a3c5-73d6ccfb41d5")
    private Object value;

    @objid ("bad6b33a-ae93-43f0-b75e-fa25552cad0f")
    private IMObjectFilter mobjectFilter;

    @objid ("c87adf91-1eba-11e2-9382-bc305ba4815c")
    public String[] getChoices() {
        return this.choices;
    }

    @objid ("c87b2db4-1eba-11e2-9382-bc305ba4815c")
    public ModuleProperty(String name, Class<?> type, Object value, String[] choices, Collection<? extends MClass> acceptedMetaclasses, IMObjectFilter mobjectFilter, boolean readOnly) {
        super();
        this.name = name;
        this.label = name;
        this.type = type;
        this.value = value;
        this.choices = choices;
        this.readOnly = readOnly;
        this.acceptedMetaclasses = acceptedMetaclasses;
        this.mobjectFilter = mobjectFilter;
        
        if (this.name.startsWith("[") && name.contains("]")) {
            this.category = name.substring(1, name.indexOf("]"));
            this.categoryLabel = this.category;
        } else {
            this.category = null;
        }
    }

    @objid ("c87bc9f3-1eba-11e2-9382-bc305ba4815c")
    public String getName() {
        return this.name;
    }

    @objid ("c87bf101-1eba-11e2-9382-bc305ba4815c")
    public String getLabel() {
        return this.label;
    }

    @objid ("c87c1811-1eba-11e2-9382-bc305ba4815c")
    public Class<?> getType() {
        return this.type;
    }

    @objid ("c87c3f24-1eba-11e2-9382-bc305ba4815c")
    public Object getValue() {
        return this.value;
    }

    @objid ("c87cb450-1eba-11e2-9382-bc305ba4815c")
    public String getCategory() {
        return this.category;
    }

    @objid ("c87cb454-1eba-11e2-9382-bc305ba4815c")
    public String getCategoryLabel() {
        return this.categoryLabel;
    }

    @objid ("c87cdb62-1eba-11e2-9382-bc305ba4815c")
    public boolean isReadOnly() {
        return this.readOnly;
    }

    @objid ("5fb7de17-4cbb-4d68-905c-00dba734e0d4")
    public Collection<? extends MClass> getAcceptedMetaclasses() {
        return this.acceptedMetaclasses;
    }

    @objid ("7e986761-c240-48d3-aefb-1cf119ce2e50")
    public IMObjectFilter getFilter() {
        return this.mobjectFilter;
    }

}
