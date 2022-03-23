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
package com.sun.star.comp.beans;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("eab9b3bd-a666-4134-bf70-2346eb760f9a")
interface NativeService {
    @objid ("88bf714b-5846-49dc-ab95-7a990157a0ef")
    String getIdentifier();

    @objid ("a260244e-7b4f-4dd7-9b87-88c43121cea7")
    void startupService() throws IOException;

    @objid ("b7081f00-cdcb-4026-8552-1332aff4e283")
    int getStartupTime();

}
