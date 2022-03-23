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
package org.modelio.vcore.smkernel.mapi.services;

import java.util.Map;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;

/**
 * Metamodel related service registry.
 * <p>
 * Allows registering and querying the metamodel for additional services (audit checkers, label provider, ...)
 * 
 * @param <S> the service interface
 * @author cmarin
 * @since 3.6
 */
@objid ("aaf4cba7-aaf5-4e47-9597-1d46e0b7853e")
public class MetamodelExtensionPoint<S> implements IMetamodelServiceProvider<S> {
    /**
     * Instantiated services map.
     * <p>
     * The metamodel fragments are retained by weak reference.
     */
    @objid ("b214061b-3721-441b-8ecd-177db5a9f47e")
    private Map<Class<? extends MMetamodelFragment>, S> providers = new WeakHashMap<>();

    /**
     * Get a service for the given metamodel fragment.
     * <p>
     * Will return null if no matching service provider was registered for the metamodel fragment.
     * @param metamodelFragment the metamodel fragment
     * @return the found service or null.
     */
    @objid ("50572c65-43af-4c52-9573-b871b7b049d3")
    @Override
    public S getService(MMetamodelFragment metamodelFragment) {
        S ret = this.providers.get(metamodelFragment.getClass());
        return ret;
    }

    /**
     * Register a metamodel extension.
     * <p>
     * @param service the implemented service.
     * @param metamodelFragmentClass the supported metamodel fragment class.
     */
    @objid ("ac348043-2f16-4e6a-bd0d-3ca07e1c5315")
    public void registerExtension(S service, Class<? extends MMetamodelFragment> metamodelFragmentClass) {
        this.providers.put(metamodelFragmentClass, service);
    }

    /**
     * Remove a metamodel service provider.
     * @param metamodelFragmentClass the metamodel fragment class to forget.
     */
    @objid ("83500bb8-7cbc-4413-9deb-fd8f8dd6da40")
    public void unregisterExtension(Class<? extends MMetamodelFragment> metamodelFragmentClass) {
        this.providers.remove(metamodelFragmentClass);
    }

    /**
     * Find a service for the given metaclass.
     * <p>
     * Look for a service for the metaclass origin metamodel fragment.
     * If none is found lookup in the metaclass parent hierarchy for metaclasses
     * from other metamodel fragments.
     * <p>
     * Will return null if no matching service provider was found.
     * @param cls a metaclass
     * @return the found service or null.
     */
    @objid ("a4b48e65-49bb-45f9-87d6-e59242296a38")
    @Override
    public S findService(MClass cls) {
        MMetamodelFragment previousMmFrag = cls.getOrigin();
        S ret = getService(previousMmFrag);
        
        MClass curCls = cls;
        while (ret == null && curCls != null) {
            curCls = curCls.getSuper();
            if (curCls != null) {
                MMetamodelFragment curMmFrag = curCls.getOrigin();
                if (previousMmFrag != curMmFrag) {
                    previousMmFrag = curMmFrag;
                    ret = getService(previousMmFrag);
                }
            }
        }
        return ret;
    }

}
