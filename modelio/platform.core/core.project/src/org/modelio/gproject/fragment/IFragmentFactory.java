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

package org.modelio.gproject.fragment;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.FragmentDescriptor;

/**
 * Interface used to instantiate a {@link IProjectFragment} from a fragment description.
 */
@objid ("0f2cbcc0-ab3b-11e1-8392-001ec947ccaf")
public interface IFragmentFactory {
    /**
     * Instantiate a {@link IProjectFragment} from a fragment descriptor.
     * @param fd The fragment descriptor
     * @return a project fragment ready to mount.
     */
    @objid ("49c0ef89-ab3f-11e1-8392-001ec947ccaf")
    IProjectFragment instantiate(FragmentDescriptor fd);

    /**
     * Tells whether the factory supports the given fragment.
     * @param fDesc
     * @return <code>true</code> if the given fragment is supported by the factory, else <code>false</code>
     */
    @objid ("aa7c47ea-0eed-11e2-8e4b-001ec947ccaf")
    boolean supports(FragmentDescriptor fDesc);

}
