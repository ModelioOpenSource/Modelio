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

package org.modelio.diagram.editor.gmdbg;

import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ae0b204d-5a0c-472a-9a97-f38b443670dd")
class XEntry implements Entry<String,String> {
    @objid ("ca452d16-c226-44ee-b79e-8d4a7f857ab5")
    private String k;

    @objid ("f1199944-c90c-44a2-a661-c5f0b87c17ff")
    private String v;

    @objid ("02af05cd-3858-46d6-a594-3ede48341f56")
    public XEntry(String k, String v) {
        this.k = k;
        this.v = v;
    }

    @objid ("f1cd59d9-3aa8-4777-aece-3fa3a4fa44d8")
    @Override
    public String getKey() {
        return this.k;
    }

    @objid ("066671ce-46ca-4cd0-bc85-463e8d8bc0cf")
    @Override
    public String getValue() {
        return this.v;
    }

    @objid ("a0babae6-1992-4777-9208-7666e249c628")
    @Override
    public String setValue(String value) {
        this.v = value;
        return this.v;
    }

}
