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
package org.modelio.vcore.smkernel.mapi.fake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Visitor for {@link FakeMObject}.
 */
@objid ("aaa5c9df-3451-4344-a244-414b438c95b1")
public interface FakeMVisitor {
    /**
     * Visit a fake object.
     * @param obj a fake object.
     * @return implementation dependent.
     */
    @objid ("88e0613d-c0ee-4a0d-a696-ebde6b621c4a")
    Object visitFakeMObject(FakeMObject obj);

}
