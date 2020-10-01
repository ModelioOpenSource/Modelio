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

package org.modelio.xmi.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Helper class to transform {@link ISelection} to list of {@link MObject}.
 */
@objid ("d6892701-516d-4773-a78d-0b9750061881")
public class SelectionHelper {
    @objid ("f54de13d-12c8-411d-a0f9-2eec776f272b")
    private SelectionHelper() {
        // no instance
    }

    /**
     * transform {@link ISelection} to list of {@link MObject}.
     * 
     * @param selection a selection
     * @return a list of MObject.
     */
    @objid ("5030ccb5-def2-494e-9b4a-cfe6a1f7ffc6")
    public static List<MObject> getElements(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection sel = (IStructuredSelection) selection;
            List<MObject> ret = new ArrayList<>(sel.size());
            
            for (Object o : sel.toList()) { 
                if (o instanceof MObject) {
                    ret.add((MObject)o);
                } else if (o instanceof IAdaptable) {
                    IAdaptable i = (IAdaptable) o;
                    MObject mobject = i.getAdapter(MObject.class);
                    if (mobject != null)
                        ret.add(mobject);
                }
            }
            return ret;
        }
        return Collections.emptyList();
    }

    /**
     * Transform {@link IStructuredSelection} to list of {@link Object}.
     * 
     * @param selection a selection
     * @return a list of Object, empty if the selection is not a IStructuredSelection.
     */
    @objid ("fbe2bc53-d0d3-4bad-9a9e-055bf68fda9c")
    public static List<?> getContent(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection sel = (IStructuredSelection) selection;
            return sel.toList();
        }
        return Collections.emptyList();
    }

    /**
     * Transform {@link IStructuredSelection} to list of T.
     * @param <T> the type of elements to find in the Eclipse selection.
     * 
     * @param selection a selection
     * @param adapterType the type of adapter to look up
     * @return a list of T, empty if the selection is not a IStructuredSelection.
     */
    @objid ("b58bfd49-c507-4559-94b6-13cda50021a2")
    public static <T> List<T> adaptContent(ISelection selection, Class<T> adapterType) {
        IAdapterManager adapterManager = Platform.getAdapterManager();
        
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection sel = (IStructuredSelection) selection;
            List<T> ret = new ArrayList<>(sel.size());            
            
            for(Object o : sel.toList()) {
                T a = adapterManager.getAdapter(o, adapterType);
                if (a != null)
                    ret.add(a);
            }
            return ret;
        }
        return Collections.emptyList();
    }

}
