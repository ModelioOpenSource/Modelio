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
package org.modelio.gproject.data.project.todo;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Root class for to-do action descriptors.
 * @since Modelio 3.4
 */
@objid ("af899138-572b-4676-9446-3d7974b80e1a")
public abstract class TodoActionDescriptor {
    /**
     * Get a user friendly label for this action.
     * @return a label string.
     */
    @objid ("bd8f6af5-d15e-4b72-a8e7-ac2dadabd484")
    public abstract String getLocalizedLabel();

    @objid ("ccde3ac3-5d36-42d9-aad9-6d4757f87d66")
    @Override
    public abstract int hashCode();

    @objid ("fe6e076c-50e1-47a1-8d71-192b0426a344")
    @Override
    public abstract boolean equals(Object obj);

    /**
     * @return true only if the descriptor contains usable informations.
     * @since 3.8
     */
    @objid ("22207c58-6129-4ecd-b933-ad06e1bfd631")
    public abstract boolean isValid();

}
