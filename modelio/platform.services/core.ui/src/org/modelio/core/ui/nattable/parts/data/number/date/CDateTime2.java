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

package org.modelio.core.ui.nattable.parts.data.number.date;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

@objid ("1bbb9d6b-ac22-43cd-b995-d9b5489d7484")
public class CDateTime2 extends CDateTime {
    @objid ("c063c6f4-c82f-4008-b589-945c1a8443fe")
    public CDateTime2(Composite parent, int style) {
        super(parent, style);
    }

    @objid ("3b54ede5-3ee8-45c8-a097-73ee24704acf")
    public Text getWidget() {
        return this.text.getControl();
    }

}
