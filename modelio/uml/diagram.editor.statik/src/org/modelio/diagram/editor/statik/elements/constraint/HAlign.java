/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.constraint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;

/**
 * Horizontal alignment enumeration.
 */
@objid ("50e90e94-a7bc-4de5-8ef8-6b3c8403d626")
public enum HAlign {
    /**
     * Left
     */
    Left (SWT.BEGINNING),
    /**
     * Center
     */
    Center (SWT.CENTER),
    /**
     * Right
     */
    Right (SWT.END);

    @objid ("ad5654bb-5e16-4c54-9e39-cb70e00cf155")
    private final int swtConst;

    @objid ("399a7944-6a56-43a1-81df-34cc4a2afc83")
    HAlign(int swtConst) {
        this.swtConst = swtConst;
    }

    /**
     * @return the matching SWT constant
     */
    @objid ("a4595a16-b403-42c9-9942-e7c63cd29945")
    public int toSwt() {
        return this.swtConst;
    }

}
