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
package org.modelio.script.macro;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.script.macro.catalog.Catalog;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("7aee3a8d-b56c-4276-9e2f-fd94f369ac71")
public interface IMacroService {
    @objid ("5532759a-33f3-4819-a58b-fde174eab4e8")
    List<Macro> getMacros(Collection<MObject> elements);

    @objid ("9dfe27f0-ea2b-478d-b282-17bd076ec276")
    List<Macro> getMacros(Scope scope);

    @objid ("87778efa-39a2-4bd8-bafc-f98255a84aca")
    void addMacro(Macro macro, Scope scope);

    @objid ("8abe2782-a1a6-4238-836b-7e0c063435fb")
    void removeMacro(Macro macro, Scope scope);

    @objid ("b1ec0e7d-a1b8-468a-9f78-1494febba33f")
    List<Macro> getMacros();

    @objid ("482212de-acb9-4594-aada-ceb6e14f6a74")
    Catalog getCatalog(Scope scope);

    @objid ("12a0c216-71ee-4240-a761-1f49a2c12b7b")
    enum Scope {
        @objid ("36a3a715-7f91-471c-a3b5-bddaa3013a11")
        MODELIO,
        @objid ("b7c9e818-e291-4225-97bc-9ce402f180b2")
        WORSPACE,
        @objid ("6745a5b5-a13f-4ed0-9884-7ce34121d3bf")
        PROJECT;

    }
}

