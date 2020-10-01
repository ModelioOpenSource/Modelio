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

package org.modelio.vcore.model.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IElementNamerService;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;

/**
 * Default implementation of IElementNamer.
 * 
 * This implementation build names for new elements using the smarter available strategy (depending on the metaclass).
 * 
 * 
 * @author phv
 */
@objid ("0067c186-030f-1035-9f91-001ec947cd2a")
public class ElementNamer implements IElementNamerService {
    @objid ("af0af6fc-ac60-4a48-9959-40aca98b1a8e")
    private final MetamodelExtensionPoint<IElementNamer> metamodelExtensionPoint;

    @objid ("0067cd20-030f-1035-9f91-001ec947cd2a")
    @Override
    public String getBaseName(final MClass metaclass) {
        assert (metaclass != null);
        return metaclass.getName();
    }

    /**
     * This implementation uses an auxiliary visitor to generate a name for the object that can be smarter than just its metaclass
     * name.
     */
    @objid ("0068039e-030f-1035-9f91-001ec947cd2a")
    @Override
    public String getBaseName(final MObject object) {
        assert (object != null);
        
        IElementNamer effectiveNamer = this.metamodelExtensionPoint.getService(object.getMClass().getOrigin());
        if (effectiveNamer == null) {
            return object.getMClass().getName();
        } else {
            return effectiveNamer.getBaseName(object);
        }
    }

    /**
     * This implementation is similar to calling: getUniqueName(getBaseName(object), object)
     */
    @objid ("00682c2a-030f-1035-9f91-001ec947cd2a")
    @Override
    public final String getUniqueName(final MObject object) {
        assert (object != null);
        return getUniqueName(getBaseName(object), object);
    }

    /**
     * This implementation returns basename unless this name is already used in which case it is completed with the smaller possible
     * value for X an integer such as basenameX does not exist.
     */
    @objid ("0068621c-030f-1035-9f91-001ec947cd2a")
    @Override
    public String getUniqueName(final String basename, final MObject object) {
        assert (basename != null);
        assert (object != null);
        
        IElementNamer effectiveNamer = this.metamodelExtensionPoint.getService(object.getMClass().getOrigin());
        if (effectiveNamer == null) {
            return getDefaultUniqueName(basename, object);
        } else {
            return effectiveNamer.getUniqueName(basename, object);
        }
    }

    @objid ("0068a6fa-030f-1035-9f91-001ec947cd2a")
    private static Set<String> getSiblingIdentifiers(MObject object) {
        SmDepVal rel = ((SmObjectImpl)object).getCompositionRelation();
        List<MObject> siblings = rel.value.mGet(rel.dep.getSymetric());
        
        final Set<String> results = new HashSet<>();
        for (MObject sibling : siblings) {
            results.add(sibling.getName());
        }
        return results;
    }

    @objid ("2e597709-72d8-4d78-892e-25011066e6a0")
    public ElementNamer() {
        this.metamodelExtensionPoint = new MetamodelExtensionPoint<>();
    }

    @objid ("5045077b-497b-4f62-9f0f-0e21eedb05f4")
    private static String getDefaultUniqueName(String basename, MObject object) {
        assert (basename != null);
        assert (object != null);
        
        final Set<String> siblingIdentifiers = getSiblingIdentifiers(object);
        
        String newName = basename;
        int i = 0;
        while (siblingIdentifiers.contains(newName)) {
            i = i + 1;
            newName = basename + i;
        }
        return newName;
    }

    @objid ("cf047cf9-9d81-4b02-93c7-7ffeeb8f30e8")
    @Override
    public MetamodelExtensionPoint<IElementNamer> getMetamodelExtensionPoint() {
        return this.metamodelExtensionPoint;
    }

}
