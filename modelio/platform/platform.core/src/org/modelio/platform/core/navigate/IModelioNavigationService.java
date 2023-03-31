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
package org.modelio.platform.core.navigate;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.core.IModelioService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * The navigation service fires OSGI events that can be caught by views to change their current selection. The service only deals with MObject based single or multiple selection. Several flavor of <code>fireNavigate()</code> method are proposed for
 * convenience for different situations.
 * 
 * Note: A so called 'empty navigation event' is a navigation event providing a empty list of elements to navigate to. They are used when given parameters does not allow firing a real event containing valid MObjects.
 */
@objid ("005d66aa-a738-10ac-8258-001ec947cd2a")
public interface IModelioNavigationService extends IModelioService {
    /**
     * Fire a navigation event for a given MObject.
     * If the given object is <code>null</code> an 'empty navigation event' is fired.
     */
    @objid ("005c6d86-a86b-10ac-8258-001ec947cd2a")
    void fireNavigate(MObject mObj);

    /**
     * Fire a navigation event for a given list of MObject.
     */
    @objid ("005c79b6-a86b-10ac-8258-001ec947cd2a")
    void fireNavigate(List<MObject> mObjs);

    /**
     * Fire a navigation event for a MObject given by its MRef.
     * 
     * The typical usage if for object references sometimes stored in properties or tag values in the form of string-serialized MRef. Property views can easily make the displayed reference navigable using this flavor of the <code>fireNavigate()</code>
     * method.
     * If the given MRef cannot be resolved in the current session an 'empty navigation event' is fired.
     * @since 5.2
     */
    @objid ("23c64ded-4360-48de-a55e-850f2c7cbb50")
    void fireNavigate(MRef mRef);

    /**
     * Fire a navigation event for a MObject given by its MRef URI.
     * The MRef URI is first resolved into a MRef instance and {@link #fireNavigate(MRef) is called.
     * If the given string is not a well-formed URI, an 'empty navigation event' is fired.
     * @since 5.2
     */
    @objid ("b83954e5-3c90-4527-a478-5f6283d133b3")
    void fireNavigate(String mRefUri);
}

