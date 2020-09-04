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

package org.modelio.gproject.data.project.todo;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Actions to do on project opening.
 * @since Modelio 3.4
 */
@objid ("53866746-f7b5-4ed5-affd-12914a9afa72")
public class TodoDescriptor {
    @objid ("707f3036-a802-479f-b793-7dd1b3ca4355")
    private final List<TodoActionDescriptor> actions = new ArrayList<>();

    /**
     * @return the actions list.
     */
    @objid ("351ce2e2-c0e5-4696-980c-19602500ba08")
    public List<TodoActionDescriptor> getActions() {
        return this.actions;
    }

    @objid ("94807223-14ec-46a7-b86a-25fb878901a6")
    @Override
    public String toString() {
        final int maxLen = 10;
        return "TodoDescriptor [actions=" + this.actions.subList(0, Math.min(this.actions.size(), maxLen)) + "]";
    }

}
