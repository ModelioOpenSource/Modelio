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
package org.modelio.vcore.smkernel.mapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Acyclic Visitor design pattern applied to {@link MObject}.
 * <p>
 * The Acyclic Visitor pattern allows new functions to be added to existing class hierarchies
 * without affecting those hierarchies,
 * and without creating the dependency cycles that are inherent
 * to the <a href="http://c2.com/cgi/wiki?GangOfFour">GangOfFour</a> <a href="http://c2.com/cgi/wiki?VisitorPattern">VisitorPattern</a>
 * 
 * @see <a href="http://c2.com/cgi/wiki?GangOfFour">http://c2.com/cgi/wiki?AcyclicVisitor</a>
 */
@objid ("0009c1a8-480c-1f35-b94f-001ec947cd2a")
public interface MVisitor {
// no code
    }

